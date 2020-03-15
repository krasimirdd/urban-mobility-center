package app.domain

import kotlin.random.Random


class Vehicle private constructor(
    val id: Int?,
    private val make: String?,
    private val model: String?,
    private val year: String?,
    private val capacity: Type,
    private val loadCapacity: Int?,
    private val fuelConsumption: Double
) : Entity() {

    private var isAssign = false

    fun assignToTrace(trace: Trace): Double {
        val fuelForLap = calculateFuelForLap(trace.getTotalDistance())
        isAssign = true
        return fuelForLap * trace.timesTaken
    }

    private fun calculateFuelForLap(distance: Double): Double {
        return this.fuelConsumption * distance / 100
    }

    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("Марка : $make | ")
            .append("Модел : $model | ")
            .append("Година : $year |")
            .append("Места : ${capacity.getValue(capacity)} | ")
            .append("Товаропоносимост : $loadCapacity | ")
            .append("Разход [л/100км] : $fuelConsumption | ")
            .append("Зает : $isAssign | ")
            .append("Номер на автомобила : $id")

        return sb.toString()
    }

    data class Builder(
        var id: Int? = Random.nextInt(1000,9999),
        var make: String? = null,
        var model: String? = null,
        var year: String? = null,
        var capacity: Type = Type.HATCHBACK,
        var loadCapacity: Int? = 1500,
        var fuelConsumption: Double = 10.0
    ) {

        fun make(make: String) = apply {
            if (make.isEmpty()) {
                throw IllegalArgumentException("Моля задайте марка")
            }
            this.make = make
        }

        fun model(model: String) = apply {
            if (model.isEmpty()) {
                throw IllegalArgumentException("Моля задайте модел")
            }
            this.model = model
        }

        fun year(year: String) = apply {
            if (year.isEmpty()) {
                throw IllegalArgumentException("Моля задайте година")
            }
            this.year = year
        }

        fun capacity(capacity: Type) = apply {
            if (capacity.ordinal > Type.values().size - 1 || capacity.ordinal < 0) {
                throw IllegalArgumentException("Моля задайте валиден тип на автомобила")
            }
            this.capacity = capacity
        }

        fun loadCapacity(loadCapacity: Int) = apply {
            if (loadCapacity <= 300) {
                throw IllegalArgumentException("Моля задайте валидно число за товаропоносимост [ > 300 ]")
            }
            this.loadCapacity = loadCapacity
        }

        fun fuelConsumption(fuelConsumption: Double) = apply {
            if (fuelConsumption < 1) {
                throw IllegalArgumentException("Моля задайте валидно число за разход на гориво")
            }
            this.fuelConsumption = fuelConsumption
        }

        fun build() = Vehicle(id, make, model, year, capacity, loadCapacity, fuelConsumption)
    }
}