class CoffeeMachine (
    private var nCups: Int = 9, private var mlWater: Int = 400, private var mlMilk: Int = 540,
    private var gBeans: Int = 120, private var money: Int = 550){

    private fun displayStock() {
        println(
            """
                The coffee machine has:
                $mlWater ml of water
                $mlMilk ml of milk
                $gBeans g of coffee beans
                $nCups disposable cups
                ${'$'}$money of money
            """.trimIndent()
        )
    }

    fun selectMode(mode: String){
        println("> $mode")
        when(mode) {
            "buy" -> buyCoffee()
            "fill" -> fillMachine()
            "take" -> takeMoney()
            "remaining" -> displayStock()
            else -> println("Invalid command!")
        }
    }

    private fun checkSupply(coffeeIngredients: List<Int>): Boolean {
        if (mlWater < coffeeIngredients[0]) println("Sorry, not enough water!")
        else if (mlMilk < coffeeIngredients[1]) println("Sorry, not enough milk!")
        else if (gBeans < coffeeIngredients[2]) println("Sorry, not enough beans!")
        else if (nCups< coffeeIngredients[3]) println("Sorry, not enough cups!")
        else return true
        return false
    }

    private fun buyCoffee() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:")
        // Ingredients order: Water, Milk, Beans, Cups and Money
        val typeCoffee = readln()
        println("> $typeCoffee")
        val coffeeIngredients = when(typeCoffee) {
            "1" -> listOf(250, 0, 16, 1, 4)
            "2" -> listOf(350, 75, 20, 1, 7)
            "3" -> listOf(200, 100, 12, 1, 6)
            else -> emptyList()
        }
        if (coffeeIngredients.isEmpty()) {
            println("Invalid command")
            return
        }
        if (checkSupply(coffeeIngredients)) {
            println("I have enough resources, making you a coffee!")
            mlWater -= coffeeIngredients[0]
            mlMilk -= coffeeIngredients[1]
            gBeans -= coffeeIngredients[2]
            nCups -= coffeeIngredients[3]
            money += coffeeIngredients[4]
        }
    }

    private fun fillMachine() {
        println("Write how many ml of water you want to add: ")
        val addWater = readln().toInt()
        println("> $addWater")
        mlWater += addWater
        println("Write how many ml of milk you want to add:")
        val addMilk = readln().toInt()
        println("> $addMilk")
        mlMilk += addMilk
        println("Write how many grams of coffee beans you want to add:")
        val addBeans = readln().toInt()
        println("> $addBeans")
        gBeans += addBeans
        println("Write how many disposable cups you want to add: ")
        val addCups = readln().toInt()
        println("> $addCups")
        nCups += addCups
    }

    private fun takeMoney() {
        println("I gave you \$$money")
        money = 0
    }
}

fun main() {
    val coffeeMachine = CoffeeMachine()
    while (true) {
        println("Write action (buy, fill, take, remaining, exit):")
        val mode = readln()
        if (mode == "exit") break
        coffeeMachine.selectMode(mode)
    }
}
