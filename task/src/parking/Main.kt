package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var parkingLot: ParkingLot? = null

    while (true) {
        val input = scanner.nextLine().split(' ').toTypedArray()
        val currentAction = input[0]

        if (currentAction !in arrayOf("create", "exit")) {
            if (!checkParkingLot(parkingLot)) {
                continue
            }
        }

        when (currentAction) {
            "create" -> {
                parkingLot = ParkingLot(input[1].toInt())
                println("Created a parking lot with ${parkingLot.capacity} spots.")
            }
            "status" -> println(parkingLot?.getStatus())
            "reg_by_color" -> println(parkingLot?.getCarsByColor(input[1]))
            "spot_by_color" -> println(parkingLot?.getSpotsByColor(input[1]))
            "spot_by_reg" -> println(parkingLot?.getSpotByNumber(input[1]))
            "park" -> {
                val car: Car? = parkingLot?.park(input[1], input[2])
                println(if (car?.spotIdInParkingLot != null) "${car.color} car parked in spot ${car.spotIdInParkingLot}." else "Sorry, the parking lot is full.")
            }
            "leave" -> {
                val spotToLeave: Int = input[1].toInt()
                println(if (parkingLot?.leave(spotToLeave) != false) "Spot $spotToLeave is free." else "There is no car in spot $spotToLeave.")
            }
            "exit" -> break
            else -> println("Wrong action is provided")
        }
    }
}

fun checkParkingLot(parkingLot: ParkingLot?) : Boolean {
    if (parkingLot == null) {
        println("Sorry, a parking lot has not been created.")
        return false
    }

    return true
}
