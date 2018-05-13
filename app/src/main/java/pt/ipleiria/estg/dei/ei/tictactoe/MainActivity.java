package pt.ipleiria.estg.dei.ei.tictactoe;

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

                final View view = inflater.inflate(R.layout.dialog_players_name, null);

                builder.setView(view)
                        .setTitle("Insert each player name")
                        .setPositiveButton("Start Game", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText et_player1 = view.findViewById(R.id.et_player1);
                                EditText et_player2 = view.findViewById(R.id.et_player2);

                                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                                intent.putExtra("player1", et_player1.getText().toString());
                                intent.putExtra("player2", et_player2.getText().toString());
                                startActivity(intent);
                            }
                        }).
                        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }});
                builder.create().show();
            }
        });
    }
}
