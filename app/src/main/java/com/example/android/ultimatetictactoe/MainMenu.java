package com.example.android.ultimatetictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        final Button aiButtonEasy = findViewById(R.id.play_against_ai_easy);
        final Button aiButtonHard = findViewById(R.id.play_against_ai_hard);
        final Button multiplayerButton = findViewById(R.id.multiplayer_button);

        aiButtonEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                intent.putExtra("AI_SELECTED", true);
                intent.putExtra("DIFFICULTY_LEVEL", "EASY");
                startActivity(intent);
            }
        });

        aiButtonHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                intent.putExtra("AI_SELECTED", true);
                intent.putExtra("DIFFICULTY_LEVEL", "HARD");
                startActivity(intent);
            }
        });

        multiplayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainMenu.this, MainActivity.class);
                intent.putExtra("AI_SELECTED", false);
                startActivity(intent);
            }
        });
    }
}