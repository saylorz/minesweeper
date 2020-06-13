package com.example.minesweeperproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((TextView)findViewById(R.id.textView)).setText("Test");

        initializeGame();
    }

    private Button btnReset;

    public void initializeGame() {
        Log.e("MainActivity","onCreate");
        GameEngine.getInstance().createGrid(this);



        /*Restart game
        btnReset = findViewById(R.id.btnReset);

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                startActivity(getIntent());
            }
        });
        */
    }

}