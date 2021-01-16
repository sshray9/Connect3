package com.example.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    // 0 = Circle, 1 = cross

    int activeplayer = 0;
    boolean Gameactive = true;

    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2}; // 2 means unplayed

    int[][] WinningPositions = {{0,1,2}, {3,4,5}, {6,7,8}, {0,3,6}, {1,4,7}, {2,5,8}, {0,4,8}, {2,4,6}};

    public void DropIn(View view) {


        ImageView counter = (ImageView) view;

        System.out.println(counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if (gameState[tappedCounter] == 2 && Gameactive) {

            gameState[tappedCounter] = activeplayer;


            counter.setTranslationY(-1000f);

            if (activeplayer == 0) {

                counter.setImageResource(R.drawable.redd);

                activeplayer = 1;
            } else {

                counter.setImageResource(R.drawable.cc);

                activeplayer = 0;

            }
            counter.animate().translationYBy(1000f).setDuration(30);


            for (int[] WinningPosition : WinningPositions) {

                if (gameState[WinningPosition[0]] == gameState[WinningPosition[1]] &&
                        gameState[WinningPosition[1]] == gameState[WinningPosition[2]] &&
                        gameState[WinningPosition[0]] != 2) {

                    //Someone has Won

                    Gameactive = false;

                    String winner = "Cross";

                    if (gameState[WinningPosition[0]] == 0) {

                        winner = "Circle";

                    }


                    TextView winnermsgg = findViewById(R.id.WinnerMessage);

                    winnermsgg.setText(winner + " has Won!!");

                    LinearLayout layout = findViewById(R.id.PlayAgainLayout);

                    layout.setVisibility(view.VISIBLE);

                }

                else {
                    boolean gameisover = true;

                    for (int counterState : gameState){

                        if (counterState == 2) {

                            gameisover =false;
                        }
                    }

                    if (gameisover ) {

                        TextView winnermsgg = findViewById(R.id.WinnerMessage);

                        winnermsgg.setText(" It's Draw");

                        LinearLayout layout = findViewById(R.id.PlayAgainLayout);

                        layout.setVisibility(view.VISIBLE);

                    }
                }


            }

        }
    }

        public void playagain(View view) {

            Gameactive = true;

            int i;

            LinearLayout layout = (LinearLayout) findViewById(R.id.PlayAgainLayout);

            layout.setVisibility(View.INVISIBLE);

             activeplayer = 0;

            for ( i=0; i<gameState.length; i++) {

                gameState[i] = 2;

            }

            GridLayout gridLayout =(GridLayout) findViewById(R.id.gridlayout);

            for ( i =0; i<gridLayout.getChildCount(); i++){

                ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
            }

        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
