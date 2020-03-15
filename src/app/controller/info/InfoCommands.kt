package app.controller.info

import app.domain.Trace
import app.domain.Type
import app.domain.Vehicle
import app.util.Printer.Companion.consolePrintLn
import java.util.*

class VehicleInfoCommand(vehicles: ArrayList<Vehicle>) : InfoCommand<Vehicle>(vehicles)

class TraceInfoCommand(traces: List<Trace>) : InfoCommand<Trace>(traces)

class TypeInfoCommand : InfoCommand<Type>(Type.values().toList())

class ShowMenuCommand : InfoCommand<Unit>(Collections.emptyList()) {
    override fun execute() {
        consolePrintLn(" 1. Информация за маршрутни таксита ")
        consolePrintLn(" 2. Информация за маршрути ")
        consolePrintLn(" 3. Информация за видовете автомобили ")
        consolePrintLn(" 4. Добави автомобил")
        consolePrintLn(" 5. Добави спирка")
        consolePrintLn(" 6. Задай маршрут на автомобил")
        consolePrintLn(" 7. Покажи меню")
        consolePrintLn(" 8. Изключи")
    }
}
