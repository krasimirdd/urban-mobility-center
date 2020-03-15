package app.controller.operation

import java.util.*

interface OpCommand<T> {
    fun execute(): ArrayList<T>
}

class OperationCommandProcessor<T> {
    private val queue = ArrayList<OpCommand<T>>()

    fun addToQueue(command: OpCommand<T>): OperationCommandProcessor<T> = apply {
        queue.add(command)
    }

    fun processCommand(): OperationCommandProcessor<T> = apply {
        queue.forEach {
            it.execute()
        }
        queue.clear()
    }
}
