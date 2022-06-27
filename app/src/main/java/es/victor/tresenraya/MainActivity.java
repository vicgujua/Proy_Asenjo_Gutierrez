package es.victor.tresenraya;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.content.Intent;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void PlayButtonClick(View view){
        //pasar a la siguiente pantalla
        Intent intent = new Intent(this, PlayerSetup.class);
        startActivity(intent);
    }
}