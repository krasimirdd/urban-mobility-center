package app.controller.info

import app.controller.Command
import app.util.Printer.Companion.consolePrintLn
import java.util.*


abstract class InfoCommand<T>(private val list: List<T>) : Command {
    override fun execute() {
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
