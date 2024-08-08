package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.os.CountDownTimer
import android.util.AttributeSet
import android.view.View

class GameView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {

    private var timer = object : CountDownTimer(Long.MAX_VALUE, 500) {
        override fun onTick(p0: Long) {
            if (!GameModel.snake.move()) {
                onFinish()
            }
            if (GameModel.fruit!!.column == GameModel.snake.xHead &&
                GameModel.fruit!!.row == GameModel.snake.yHead) {
                GameModel.addFruit()
            }
            invalidate()
        }

        override fun onFinish() {
            val intent = Intent(context, GameOverActivity::class.java)
            context!!.startActivity(intent)
            (context as? Activity)?.finish()
            cancel()
        }
    }

    init {
        timer.start()
    }

    private var cellWidth: Float = 0f

    private var cellHeight: Float = 0f

    var paintField = Paint()
    var paintSnake = Paint()
    var paintHead = Paint()

    override fun onDraw(canvas: Canvas) {
        cellWidth = width.toFloat() / GameModel.columnCount
        cellHeight = height.toFloat()/ GameModel.rowCount
        drawField(canvas)
        drawFruit(canvas)
        drawSnake(canvas)
    }

    private fun drawField(canvas: Canvas) {
        for (row in 0 until GameModel.rowCount) {
            for (column in 0 until GameModel.columnCount) {
                canvas.drawRect(getRectF(column, row), paintField)
            }
        }
    }

    private fun drawSnake(canvas: Canvas) {
        canvas.drawRect(getRectF(GameModel.snake.xHead, GameModel.snake.yHead), paintHead)
        for (p in GameModel.snake.tail) {
            canvas.drawRect( getRectF(p.first, p.second), paintSnake)
        }
    }

    private fun drawFruit(canvas: Canvas) {
        GameModel.fruit ?: return
        val bitmap = BitmapFactory.decodeResource(resources, GameModel.fruit!!.resourceId)
        canvas.drawBitmap(bitmap, null, getRectF(GameModel.fruit!!.column, GameModel.fruit!!.row), paintField)
    }

    private fun getRectF(column: Int, row: Int): RectF {
        return RectF(column * cellWidth, row * cellHeight,
            (column + 1) * cellWidth, (row + 1) * cellHeight)
    }
}