package app.domain

import java.lang.Double.sum
import java.lang.StringBuilder
import java.util.*

data class Trace(val id: Int, val nodes: LinkedList<Node>, val timesTaken: Int) : Entity() {

    private var totalDistance = setTotalDistance()

    var vehicleAssigned: Vehicle? = null

    fun setTotalDistance(): Double {
        var distance = 0.0

        nodes.forEach {
            distance = sum(distance, it.distanceToPrevious!!)
        }

        this.totalDistance = distance
        return distance
    }

    fun getTotalDistance(): Double {
        return this.totalDistance
    }

    override fun toString(): String {

        val sb = StringBuilder()
        sb.append("Номер : ${this.id} : ")
        for (node in nodes) {
            sb.append(node.toString())
        }

        sb.append("Гараж")
        sb.append(System.lineSeparator())
            .append(String.format("Дистанция : %.2f", totalDistance))
            .append(System.lineSeparator())
            .append("Минава се $timesTaken пъти за ден")

        if (vehicleAssigned != null) {
            sb.append(System.lineSeparator())
            sb.append("Автомобил който работи маршрута : ${vehicleAssigned!!.id}")
        }

        return sb.toString()
    }
}
