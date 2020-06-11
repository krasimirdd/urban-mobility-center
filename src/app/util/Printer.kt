package app.util

import java.io.File
import java.time.Instant
import java.util.*

interface Printer {
    companion object {
        private val file = File("src\\result")
        val uuid = Random().nextLong()

        fun consolePrintLn(value: String) {
            println(value)
            filePrint(value)
        }

        fun consolePrint(value: String) {
            print(value)
        }

        private fun filePrint(value: String) {
            val date = Date.from(Instant.now())

            file.appendText(System.lineSeparator())
            file.appendText("$date[$uuid]  -  $value")
        }
    }
}
