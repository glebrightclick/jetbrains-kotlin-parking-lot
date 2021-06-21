package parking

class ParkingLot(val capacity: Int) {
    private val spots: Array<Car?> = Array<Car?>(capacity) { null }

    fun getStatus(): String {
        var textArray = emptyArray<String>()

        for (spotIndex in spots.indices) {
            val car: Car? = spots[spotIndex]

            if (car != null) {
                textArray += "${spotIndex + 1} ${car.number} ${car.color}"
            }
        }

        return if (textArray.isNotEmpty()) textArray.joinToString("\n") else "Parking lot is empty."
    }

    fun getCarsByColor(color: String): String {
        var textArray = emptyArray<String>()

        for (spotIndex in spots.indices) {
            val car: Car? = spots[spotIndex]

            if (car != null && color.equals(car.color, ignoreCase = true)) {
                textArray += car.number
            }
        }

        return if (textArray.isNotEmpty()) textArray.joinToString(", ") else "No cars with color $color were found."
    }

    fun getSpotsByColor(color: String): String {
        var textArray = emptyArray<Int>()

        for (spotIndex in spots.indices) {
            val car: Car? = spots[spotIndex]

            if (car != null && color.equals(car.color, ignoreCase = true)) {
                textArray += spotIndex + 1
            }
        }

        return if (textArray.isNotEmpty()) textArray.joinToString(", ") else "No cars with color $color were found."
    }

    fun getSpotByNumber(number: String): String {
        for (car in spots) {
            if (car?.number == number) {
                return "${car.spotIdInParkingLot}"
            }
        }

        return "No cars with registration number $number were found."
    }

    /**
     * @return Int? - spot id for parked car or null if can't park
     */
    fun park(number: String, color: String) : Car? {
        var car: Car? = null

        for (spotIndex in spots.indices) {
            if (spots[spotIndex] == null) {
                car = Car(number, color, spotIndex + 1)
                spots[spotIndex] = car
                break
            }
        }

        return car
    }

    fun leave(spotId: Int) : Boolean {
        val indexToLeave: Int = spotId - 1

        if (indexToLeave !in spots.indices || spots[indexToLeave] == null) {
            return false
        }

        spots[indexToLeave] = null
        return true
    }
}