package com.example.pedrogarcia.encrypt0;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Menu extends android.support.v7.app.AppCompatActivity {

    Button button;
    Button button2;
    Button button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent encryptPage = new Intent (Menu.this, Encrypt.class);
                startActivity(encryptPage);
            }
        });
        button2 = (Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent decryptPage = new Intent (Menu.this, Decrypt.class);
                startActivity(decryptPage);
            }
        });
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settingsPage = new Intent (Menu.this, Settings.class);
                startActivity(settingsPage);
            }
        });
    }


}
