package com.example.myapplication

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), OnClickListener {

    private lateinit var gameView: GameView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        gameView = findViewById(R.id.game_view)
        gameView.layoutParams.height = 1250
        gameView.layoutParams.width = 1000
        gameView.paintField.color = Color.YELLOW
        gameView.paintSnake.color = Color.LTGRAY
        gameView.paintHead.color = Color.DKGRAY
    }

    override fun onClick(p0: View?) {
        p0 ?: return
        if (p0.id == R.id.btn_left) {
            if (GameModel.snake.direct != Direction.right) {
                GameModel.snake.moveTo(Direction.left)
            }
        }
        if (p0.id == R.id.btn_right) {
            if (GameModel.snake.direct != Direction.left) {
                GameModel.snake.moveTo(Direction.right)
            }
        }
        if (p0.id == R.id.btn_up) {
            if (GameModel.snake.direct != Direction.down) {
                GameModel.snake.moveTo(Direction.up)
            }
        }
        if (p0.id == R.id.btn_down) {
            if (GameModel.snake.direct != Direction.up) {
                GameModel.snake.moveTo(Direction.down)
            }
        }
    }

}