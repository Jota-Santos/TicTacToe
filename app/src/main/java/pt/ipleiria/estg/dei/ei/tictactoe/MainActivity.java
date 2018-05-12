package pt.ipleiria.estg.dei.ei.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnPlay = findViewById(R.id.playButton);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                LayoutInflater inflater = MainActivity.this.getLayoutInflater();

                builder.setView(inflater.inflate(R.layout.dialog_players_name, null));

                AlertDialog dialog = builder.create();

                final EditText et_player1 = dialog.findViewById(R.id.et_player1);
                final EditText et_player2 = dialog.findViewById(R.id.et_player2);

                builder.setMessage("Insert the names of each player: ")
                        .setPositiveButton("Start Game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                String player1Name = et_player1.getText().toString();
                                String player2Name = et_player2.getText().toString();

//                                if(player1Name.isEmpty() && player2Name.isEmpty()) {
//                                    // you must pick a name for rach player
//                                } else if(player1Name.equals(player2Name)) {
//                                    // The names can't be equal
//                                } else {
//                                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
//                                    intent.putExtra("player1", player1Name);
//                                    intent.putExtra("player2", player2Name);
//                                    startActivity(intent);
//                                }
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                builder.show();
            }
        });
    }
}
