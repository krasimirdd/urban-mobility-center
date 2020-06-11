package app.controller.operation

import app.domain.Node
import app.domain.Trace
import app.domain.Type
import app.domain.Vehicle
import app.util.Printer.Companion.consolePrint
import app.util.Printer.Companion.consolePrintLn
import java.util.*

class AddVehicleCommand(private var vehicles: ArrayList<Vehicle>) :
    OpCommand<Vehicle> {
    override fun execute(): ArrayList<Vehicle> {

        val vehicle: Vehicle

        try {
            val builder = Vehicle.Builder()

            consolePrint("Марка - ")
            builder.make(readLine()!!)
            consolePrint("Модел - ")
            builder.model(readLine()!!)
            consolePrint("Година - ")
            builder.year(readLine()!!)
            consolePrint("Вид на автомобила - ")
            builder.capacity(Type.valueOf(readLine()!!))
            consolePrint("Товаропоносимост - ")
            builder.loadCapacity(readLine()!!.toInt())
            consolePrint("Разход [л/100км] - ")
            builder.fuelConsumption((readLine()!!.toDouble()))

            vehicle = builder.build()
            vehicles.add(vehicle)

        } catch (e: IllegalArgumentException) {
            consolePrintLn(e.message!!)
        }

        return vehicles
    }
}

class AddNodeCommand(private var traces: ArrayList<Trace>) :
        OpCommand<Trace> {
    override fun execute(): ArrayList<Trace> {
        val nodeBuilder = Node.Builder()
        consolePrintLn("Име на спирката ")
        nodeBuilder.name(readLine()!!)
        consolePrintLn("Дистанция до предходната спирка")
        nodeBuilder.distanceToPrevious(readLine()!!)
        val node = nodeBuilder.build()

        consolePrintLn("Избери към кой маршрут ще се добави спирката")
        traces.forEach { consolePrintLn(it.id.toString()) }
        val traceId = readLine()!!.toInt()
        traces.forEach {
            if (it.id == traceId) {
                it.nodes.add(node)
                it.setTotalDistance()
                consolePrintLn("Спирката е добавена към $traceId")
                return traces
            }
        }

        consolePrintLn("Маршрута не е намерен")
        return traces
    }
}

class AssignTraceToVehicleCommand(private val traces: ArrayList<Trace>, private val vehicles: ArrayList<Vehicle>) :
        OpCommand<Trace> {
    override fun execute(): ArrayList<Trace> {

        consolePrintLn("Избери автомобила")
        vehicles.forEach { consolePrintLn(it.toString()) }
        val vehicleId = readLine()!!.toInt()
        var vehicle: Vehicle? = null

        var isValidId = false
        vehicles.forEach {
            if (it.id == vehicleId) {
                isValidId = true
                vehicle = it
            }
        }
        if (!isValidId) {
            consolePrintLn("Невалиден автомобил")
            return traces
        }

        consolePrintLn("Избери към кой маршрут ще се добави автомобила")
        traces.forEach { consolePrintLn(it.toString()) }
        val traceId = readLine()!!.toInt()
        traces.forEach {
            if (it.id == traceId) {
                it.vehicleAssigned = vehicle
                val fuelNeeded = vehicle!!.assignToTrace(it)
                consolePrintLn("Автомобил ${vehicle!!.id} работи по маршрут $traceId. ")
                consolePrintLn(String.format("Необходимо гориво за деня : %.2f", fuelNeeded))
                return traces
            }
        }
        return traces
    }
}
