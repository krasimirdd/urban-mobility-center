package app

import app.controller.operation.AddNodeCommand
import app.controller.operation.AddVehicleCommand
import app.controller.operation.AssignTraceToVehicleCommand
import app.controller.info.InfoCommandProcessor
import app.domain.Node
import app.controller.operation.OperationCommandProcessor
import app.controller.info.ShowMenuCommand
import app.domain.Trace
import app.controller.info.TraceInfoCommand
import app.controller.info.TypeInfoCommand
import app.domain.Vehicle
import app.controller.info.VehicleInfoCommand
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList

fun main() {
    var vehicles = ArrayList<Vehicle>()
    var traces = ArrayList<Trace>()
    var nodes = LinkedList<Node>()

    fun initNodes(): LinkedList<Node> {

        val node1 = Node.Builder().name("НСА")
            .distanceToPrevious("0").build()
        val node2 = Node.Builder().name("Зимен Дворец")
            .distanceToPrevious("2.1").build()
        val node3 = Node.Builder().name("Детски Ясли")
            .distanceToPrevious("1.3").build()
        val node4 = Node.Builder().name("Христо Ботев")
            .distanceToPrevious("0.9").build()
        val node5 = Node.Builder().name("Детски Дом")
            .distanceToPrevious("1.1").build()
        val node6 = Node.Builder().name("Телекомуникации")
            .distanceToPrevious("3.7").build()

        nodes.add(node1)
        nodes.add(node2)
        nodes.add(node3)
        nodes.add(node4)
        nodes.add(node5)
        nodes.add(node6)
        return nodes
    }

    fun initTraces(nodes: LinkedList<Node>): ArrayList<Trace> {
        val trace = Trace(94, nodes, nodes.size * 5)
        traces.add(trace)
        return traces
    }

    fun init() {
        nodes = initNodes()
        traces = initTraces(nodes)
    }

    run {
        init()
        var running = true
        var input = 7

        while (running) {
            val infoProcessor = InfoCommandProcessor()
            val oprationProcessorVehicle =
                OperationCommandProcessor<Vehicle>()
            val oprationProcessorTrace = OperationCommandProcessor<Trace>()

            when (input) {
                1 -> infoProcessor.addToQueue(VehicleInfoCommand(vehicles))
                2 -> infoProcessor.addToQueue(TraceInfoCommand(traces))
                3 -> infoProcessor.addToQueue(TypeInfoCommand())
                4 -> oprationProcessorVehicle.addToQueue(
                    AddVehicleCommand(
                        vehicles
                    )
                )
                5 -> oprationProcessorTrace.addToQueue(AddNodeCommand(traces))
                6 -> oprationProcessorTrace.addToQueue(
                    AssignTraceToVehicleCommand(
                        traces,
                        vehicles
                    )
                )
                7 -> infoProcessor.addToQueue(ShowMenuCommand())
                8 -> running = false
            }

            infoProcessor.processCommand()
            oprationProcessorVehicle.processCommand()
            oprationProcessorTrace.processCommand()
            try {
                input = readLine()!!.toInt()

            } catch (e: Exception) {
                input = 7
            }
        }
    }
}
