package pt.ipleiria.estg.dei.ei.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private static final int BOARD_LENGHT = 3;

    private TableLayout tableLayout;
    private TextView tv_player1;
    private TextView tv_player2;
    private TextView tv_score1;
    private TextView tv_score2;
    private Button btn_reset;

    private int[][] boardState = new int[3][3];
    private int winner = 0;
    private int turn = 1;
    private int score1 = 0;
    private int score2 = 0;
    private boolean draw = false;
    private String player1Name;
    private String player2Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        tableLayout = findViewById(R.id.board);
        tv_player1 = findViewById(R.id.tv_player1Name);
        tv_player2 = findViewById(R.id.tv_player2Name);
        tv_score1 = findViewById(R.id.tv_score1);
        tv_score2 = findViewById(R.id.tv_score2);
        btn_reset = findViewById(R.id.btn_reset);

        Intent intent = getIntent();
        player1Name = intent.getStringExtra("player1");
        player2Name = intent.getStringExtra("player2");
        tv_player1.setText(player1Name);
        tv_player2.setText(player2Name);

        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetBoard();
            }
        });

        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {
                final Button button = (Button) tableRow.getChildAt(j);

                final int finalI = i;
                final int finalJ = j;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // TODO Try this!
                        // Toast.makeText(GameActivity.this, "Position: " + finalI + ", " + finalJ, Toast.LENGTH_SHORT).show();

                        if(boardState[finalI][finalJ] == 0) {
                            button.setText(turn == 1 ? "X" : "O");
                            checkRoundEnded(finalI, finalJ);
                            turn = turn == 1 ? 2 : 1;
                            String nextPlayer = turn == 1 ? player1Name : player2Name;
                            Toast.makeText(GameActivity.this, "It's " + nextPlayer + " turn to play.", Toast.LENGTH_SHORT).show();
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
                            builder.setTitle("Invalid play")
                                    .setMessage("Can't play in that box, a player has already placed his mark there.")
                                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.dismiss();
                                        }
                                    });
                            builder.create().show();
                        }
                    }
                });
            }
        }
    }

    public void checkRoundEnded(int x, int y) {
        boardState[x][y] = turn;

        for(int row = 0; row < BOARD_LENGHT; row++) {
            if(boardState[row][0] == turn && boardState[row][1] == turn && boardState[row][2] == turn) {
                winner = turn;
                endRound();
                return;
            }
        }

        for(int column = 0; column < BOARD_LENGHT; column++) {
            if(boardState[0][column] == turn && boardState[1][column] == turn && boardState[2][column] == turn) {
                winner = turn;
                endRound();
                return;
            }
        }

        if((boardState[0][0] == turn && boardState[1][1] == turn && boardState[2][2] == turn) ||
                boardState[2][0] == turn && boardState[1][1] == turn && boardState[0][2] == turn) {
            winner = turn;
            endRound();
            return;
        }

        int tileCounter = 0;
        for (int row = 0; row < BOARD_LENGHT; row++) {
            for (int column = 0; column < BOARD_LENGHT; column++) {
                if(boardState[row][column] != 0) {
                    tileCounter++;
                }
            }
        }

        if(tileCounter == BOARD_LENGHT*BOARD_LENGHT) draw = true;

        if(draw) endRound();
    }

    public void endRound() {
        // increment score
        String msg;
        if(winner == 1) {
            score1++;
            tv_score1.setText(String.valueOf(score1));

        } else if(winner == 2){
            score2++;
            tv_score2.setText(String.valueOf(score2));
        }

        if(score1 == 2 || score2 == 2) {
            gameEnded();
            return;
        }

        if(!draw) {
            msg = winner == 1 ? player1Name + " won!" : player2Name + " won!";
        } else {
            msg = "Draw!";
        }

        createDialog(msg);

    }

    public void resetBoard() {
        boardState = new int[3][3];
        draw = false;
        winner = 0;
        for (int i = 0; i < tableLayout.getChildCount(); i++) {
            TableRow tableRow = (TableRow) tableLayout.getChildAt(i);

            for (int j = 0; j < tableRow.getChildCount(); j++) {
                final Button button = (Button) tableRow.getChildAt(j);
                button.setText("");
            }
        }
    }

    public void createDialog(String msg) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Round over")
                .setMessage(msg)
                .setNeutralButton("Next round", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // reset board
                        resetBoard();
                    }
                });

        builder.setCancelable(false);
        builder.create().show();
    }

    public void gameEnded() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Game finished")
                .setMessage(winner == 1 ? player1Name + " won the game!" : player2Name + " won!")
                .setPositiveButton("Restart Game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        startActivity(getIntent());
                    }
                })
                .setNegativeButton("Back to Menu", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

        builder.setCancelable(false);
        builder.create().show();
    }
}
