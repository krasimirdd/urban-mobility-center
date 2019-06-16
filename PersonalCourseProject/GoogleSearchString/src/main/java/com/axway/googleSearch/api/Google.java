package com.axway.googleSearch.api;

import com.axway.googleSearch.constants.BadWords;
import com.axway.googleSearch.exceptions.BadSearchBoundaryException;
import com.axway.googleSearch.exceptions.BadSearchException;
import com.axway.googleSearch.exceptions.BadSearchStringException;
import com.axway.googleSearch.exceptions.BadWordException;
import com.axway.googleSearch.util.FileUtil;
import com.axway.googleSearch.util.GsonUtil;
import com.axway.googleSearch.util.Printer;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static com.axway.googleSearch.constants.Constants.*;

public class Google {
    private static BadWords badWords;
    private static File resultFile = FileUtil.getResultFile(System.getProperty(SYSTEM_PROP_USER_DIR), NAME_OF_RESULT_DIR);

    public static void main(String[] args) {
        Google http = new Google();

        /**
         *  if you want you can add words to the blacklist
         *  @param word the forbidden word
         *  badWords.add(String word);
         */
        badWords = new BadWords();
        try {
            http.sendGet(args[0], Integer.parseInt(args[1]));
//            http.sendGet("axway", 2);
        } catch (BadSearchException bse) {
            System.out.println(bse.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param string   searching string for google
     * @param boundary number of pages
     * @throws IOException        when connection is refused
     * @throws BadSearchException three different custom exceptions for specific wrong input
     */
    private void sendGet(String string, int boundary) throws BadSearchException, IOException {
        if (badWords.contains(string)) {
            throw new BadWordException();
        }
        if ("".equals(string) || " ".equals(string)) {
            throw new BadSearchStringException();
        }
        if (boundary < 1 || boundary > SEARCH_LIMIT) {
            throw new BadSearchBoundaryException();
        }
        String url = GOOGLE_MAIN_PAGE + string + "&start=";

        List<Document> documents = new ArrayList<>();
        for (int i = 0; i < boundary; i++) {
            Connection connection = Jsoup.connect(url + i * 10).userAgent(USER_AGENT);
            System.out.println(RESPONSE_CODE + connection.response().statusCode());

            Document doc = connection.get();
            documents.add(doc);
        }
        System.out.println(printToJson(boundary, documents)
                ? SUCCESS_MESSAGE
                : FAIL_MESSAGE
        );
    }

    /**
     * parses document from connection to list of JSONObjects
     *
     * @param pagesCount
     * @param documents  list of all result pages within the pagesCount
     * @return false if documents are empty, or printer fails to print in file
     * true if successfully wrote in file
     */
    private boolean printToJson(int pagesCount, List<Document> documents) {

        Set<String> titles = new LinkedHashSet<>();
        Set<String> descriptions = new LinkedHashSet<>();
        Set<String> links = new LinkedHashSet<>();

        for (Document document : documents) {

            Elements selectLinks = document.select(LINK_CLASS);
            links.addAll(selectLinks.eachText());
            Elements selectTitles = document.select(TITLE_CLASS);
            titles.addAll(selectTitles.eachText());

            Elements selectDescriptions = document.select(DESCRIPTION_CLASS);
            descriptions.addAll(selectDescriptions.eachText());

        }
        String json = GsonUtil.toJson(pagesCount * 10, titles, descriptions, links);

        if ("".equals(json)) {
            return false;
        }
        try {
            Printer.print(resultFile, json);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}