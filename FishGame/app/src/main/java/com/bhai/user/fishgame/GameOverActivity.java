package com.bhai.user.fishgame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    private TextView textView;
    private String str;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        button=findViewById(R.id.game_over);
        textView=findViewById(R.id.text2);
        str=getIntent().getExtras().get("abc").toString();
        textView.setText("Your Score:"+str);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
