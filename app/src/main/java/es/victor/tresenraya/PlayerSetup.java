package es.victor.tresenraya;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class PlayerSetup extends AppCompatActivity {

    private EditText jugador1;
    private EditText jugador2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_setup);

        jugador1 = findViewById(R.id.jugador1Nombre);
        jugador2 = findViewById(R.id.jugador2Nombre);
    }

    public void submitButtonClick (View view){
        //Guardar los nombres de los jugadores
        String jugador1Nombre = jugador1.getText().toString();
        String jugador2Nombre = jugador2.getText().toString();

        Intent intent = new Intent(this, GameDisplay.class);
        intent.putExtra("NOMBRE_JUGADOR", new String[] {jugador1Nombre , jugador2Nombre});
        startActivity(intent);
    }
}