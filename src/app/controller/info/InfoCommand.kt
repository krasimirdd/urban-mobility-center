package app.controller.info

import app.controller.Command
import app.util.Printer.Companion.consolePrintLn
import java.util.*


abstract class InfoCommand<T>(private val list: List<T>) : Command {

    override fun execute() {
        if (list.isEmpty()) {
            consolePrintLn("No entries")
        }
        list.forEach {
            consolePrintLn(it.toString())
        }
    }
}

class InfoCommandProcessor {
    private val queue = ArrayList<Command>()

    fun addToQueue(command: Command): InfoCommandProcessor = apply {
        queue.add(command)
    }

    fun processCommand(): InfoCommandProcessor = apply {
        queue.forEach {
            it.execute()
        }
        queue.clear()
    }
}
