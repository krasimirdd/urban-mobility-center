package app.util

interface Printer {
    companion object {
        fun consolePrintLn(value: String) {
            println(value)
        }

        fun consolePrint(value: String){
            print(value)
        }
    }
}