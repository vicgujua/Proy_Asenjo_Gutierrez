package es.victor.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameDisplay extends AppCompatActivity {

    private TresenRayaTablero tresenRayaTablero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_display);

        Button playAgainBTN = findViewById(R.id.Restart);
        Button homeBTN = findViewById(R.id.home_button);
        TextView playerTurn = findViewById(R.id.player_display);

        playAgainBTN.setVisibility(View.GONE);
        homeBTN.setVisibility(View.GONE);

        String [] playerNames = getIntent().getStringArrayExtra("NOMBRE_JUGADOR");

        if (playerNames != null){
            playerTurn.setText(("Turno de " + playerNames[0]));
        }

        tresenRayaTablero = findViewById(R.id.tresenRayaTablero);

        tresenRayaTablero.setUpGame(playAgainBTN, homeBTN, playerTurn, playerNames);
    }

    public void playAgainButtonClick(View view) {
        //reiniciar la partida
        tresenRayaTablero.resetGame();
        tresenRayaTablero.invalidate();

    }

    public void  homeButtonClick(View view) {
        //volver a la pantalla de inicio
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}