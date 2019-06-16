package com.axway.googleSearch.constants;

import java.util.ArrayList;
import java.util.List;

public class BadWords {
    private static List<String> forbidden;

    public void add(String word) {
        forbidden.add(word);
    }

    public void add(List<String> words) {
        forbidden.addAll(words);
    }

    public List<String> list() {
        return new ArrayList<>(forbidden);
    }

    public boolean contains(String word) {
        return forbidden.contains(word);
    }

    public BadWords() {
        forbidden = new ArrayList<>();
    }
}
