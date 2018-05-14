package pt.ipleiria.estg.dei.ei.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_player1Name;
    private EditText et_player2Name;
    private Button btnPlay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay = findViewById(R.id.playButton);
        et_player1Name = findViewById(R.id.et_player1Name);
        et_player2Name = findViewById(R.id.et_player2Name);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String player1Name = et_player1Name.getText().toString();
                String player2Name = et_player2Name.getText().toString();

                if(player1Name.isEmpty() || player2Name.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                    builder.setTitle("Error")
                            .setMessage("Please insert the name of each player")
                            .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            });
                } else {
                    Intent intent = new Intent(MainActivity.this, GameActivity.class);
                    intent.putExtra("player1", player1Name);
                    intent.putExtra("player2", player2Name);
                    startActivity(intent);
                }
            }
        });
    }
}
