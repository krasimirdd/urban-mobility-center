package app.domain

enum class Type(var capacity: Int) {
    HATCHBACK(3),
    SEDAN(4),
    STATION_WAGON(4),
    LIMOUSINE(5),
    SUV(5),
    MINIBUS(7),
    BUS(12),
    AUTOBUS(25),
    TRUCK(30);

    fun getValue(type : Type): Int{
        return type.capacity
    }
}
