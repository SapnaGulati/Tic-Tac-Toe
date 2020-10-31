package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    Button restart;
    Button exit;
    TextView s1;
    TextView s2;
    TextView status;
    boolean activeGame = true;
    int activePlayer = 0;
    int score1 = 0, score2 = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        restart = findViewById(R.id.restartButton);
        exit = findViewById(R.id.exitButton);
        status = findViewById(R.id.status);
        s1 = findViewById(R.id.s1);
        s2 = findViewById(R.id.s2);
        s1.setText(Integer.toString(score1));
        s2.setText(Integer.toString(score2));
    }

    @SuppressLint("SetTextI18n")
    public void gameWin(View view) {
        for (int[] winPosition : winPositions) {
            activeGame = false;
            if (gameState[winPosition[0]] == 0 && gameState[winPosition[1]] == 0 && gameState[winPosition[2]] == 0) {
                score1++;
                status.setText(R.string.win1);
                s1.setText(Integer.toString(score1));
                reset(view);
                break;
            } else if (gameState[winPosition[0]] == 1 && gameState[winPosition[1]] == 1 && gameState[winPosition[2]] == 1) {
                score2++;
                status.setText(R.string.win);
                s2.setText(Integer.toString(score2));
                reset(view);
                break;
            }
        }
    }

    @SuppressLint("SetTextI18n")
    public void gridFull(View view){
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[2]] == 0) {
                activeGame = false;
                score1++;
                status.setText(R.string.win1);
                s1.setText(Integer.toString(score1));
                reset(view);
                break;
            } else if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] == 1) {
                activeGame = false;
                score2++;
                status.setText(R.string.win);
                s2.setText(Integer.toString(score2));
                reset(view);
                break;
            } else if (gameState[winPosition[0]] != gameState[winPosition[1]] &&
                    gameState[winPosition[1]] != gameState[winPosition[2]]) {
                activeGame = false;
                status.setText(R.string.draw);
                reset(view);
            }
        }
    }

    public void playerTap (View view) {
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if (gameState[tappedImage] == 2 && activeGame) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.o);
                activePlayer = 1;
                status.setText(R.string.turn);
            }
            else if(activePlayer == 1){
                img.setImageResource(R.drawable.x);
                activePlayer = 0;
                status.setText(R.string.turn1);
            }
            img.animate().translationYBy(1000f).setDuration(300);
            for(int[] winPosition: winPositions) {
                if (gameState[winPosition[0]] != 2 && gameState[winPosition[1]] != 2 && gameState[winPosition[2]] != 2 && gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]]) {
                    gameWin(view);
                    break;
                }
                else if (gameState[0] != 2 && gameState[1] != 2 && gameState[2] != 2 && gameState[3] != 2 && gameState[4] != 2 && gameState[5] != 2 && gameState[6] != 2 && gameState[7] != 2 && gameState[8] != 2) {
                    gridFull(view);
                    break;
                }
            }

        }
    }

    public void reset(View view){
        restart.setVisibility(View.VISIBLE);
        exit.setVisibility(View.VISIBLE);
    }

    public void restartGame(View view) {
        activeGame = true;
        restart.setVisibility(View.INVISIBLE);
        exit.setVisibility(View.INVISIBLE);
        activePlayer = 0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView9)).setImageResource(0);
        status.setText(R.string.turn);
    }

    public void exitGame(View view) {
        finish();
    }
}