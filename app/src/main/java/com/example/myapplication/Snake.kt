package com.example.myapplication

import java.util.Deque
import java.util.LinkedList

class Snake {

    var tail: Deque<Pair<Int,Int>> = LinkedList()
    var xHead = 0
    var yHead = 0
    var direct: Direction = Direction.stop

    fun reset() {
        direct = Direction.stop
        xHead = 0
        yHead = 0
        tail.clear()
    }

    fun moveTo(d : Direction) {
        direct = d
    }

    fun move(): Boolean {
        val oldHead = Pair(xHead, yHead)
        if (direct == Direction.stop) {
            return true
        }
        if (direct == Direction.left) {
            xHead = (xHead - 1).mod(GameModel.columnCount)
        }
        if (direct == Direction.right) {
            xHead = (xHead + 1).mod(GameModel.columnCount)
        }
        if (direct == Direction.up) {
            yHead = (yHead - 1).mod(GameModel.rowCount)
        }
        if (direct == Direction.down) {
            yHead = (yHead + 1).mod(GameModel.rowCount)
        }
        if (GameModel.fruit!!.column == xHead && GameModel.fruit!!.row == yHead) {
            tail.addFirst(oldHead)
        } else if (!tail.isEmpty()) {
            tail.pollLast()
            tail.addFirst(oldHead)
        }
        return !tail.contains(Pair(xHead, yHead))
    }
}