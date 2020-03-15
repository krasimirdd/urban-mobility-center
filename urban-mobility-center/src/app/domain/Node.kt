package app.domain

import java.lang.IllegalArgumentException
import java.lang.StringBuilder

class Node private constructor(
    private val name: String?,
    val distanceToPrevious: Double?
) : Entity() {

    data class Builder(
        var name: String? = null,
        var distanceToPrevious: Double? = null
    ) {

        fun name(name: String) = apply {
            if (name.isEmpty()) {
                throw IllegalArgumentException("Моля задайте име на спирката")
            }
            this.name = name
        }

        fun distanceToPrevious(distanceToPrevious: String) = apply {
            try {
                val distanceToPreviousDouble = distanceToPrevious.toDouble()

                if (distanceToPreviousDouble < 0.0) {
                    throw IllegalArgumentException("Моля задайте валидно число за дистанция")
                }
                this.distanceToPrevious = distanceToPreviousDouble
            } catch (e: Exception) {

            }
        }

        fun build() = Node(name, distanceToPrevious)
    }

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append(this.name).append(" -> ")
        return sb.toString()
    }
}
