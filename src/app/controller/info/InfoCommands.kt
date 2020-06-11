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
        val string = " 1. Информация за маршрутни таксита \n" +
        " 2. Информация за маршрути \n" +
        " 3. Информация за видовете автомобили \n" +
        " 4. Добави автомобил \n" +
        " 5. Добави спирка \n" +
        " 6. Задай маршрут на автомобил \n" +
        " 7. Покажи меню \n" +
        " 8. Изключи \n"
        consolePrintLn(string)
    }
}
