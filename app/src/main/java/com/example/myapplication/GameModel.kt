package com.example.myapplication

object GameModel {
    var columnCount = 8
    var rowCount = 10
    var snake: Snake = Snake()
    var fruit: Fruit? = null

    init {
        addFruit()
    }

    fun reset() {
        snake.reset()
        addFruit()
    }

    fun addFruit() {
        var x = (0 until columnCount).random()
        var y = (0 until rowCount).random()
        while ((x == snake.xHead && y == snake.yHead) || snake.tail.contains(Pair(x,y))) {
            x = (0 until columnCount).random()
            y = (0 until rowCount).random()
        }
        fruit = Fruit(x, y, R.drawable.apple)
    }

    fun score(): String {
        return snake.tail.size.toString()
    }
}