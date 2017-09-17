package com.example.pedrogarcia.encrypt0;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.*;
import android.content.ClipboardManager;
public class Encrypt extends AppCompatActivity {

    private EditText userString;
    Button button3,button5;

    static int total_code;
    String out;

    static String[] encryptParse(String sentence) 				//splits the sentence into words
    {
        int length; // length of the user's sentence input
        length = sentence.length();

        String[] word = new String[length]; // this variable will store the words from the sentence the user inputed

        word = sentence.split(" ");

        return word;
    }

    static int alg_sum(int n)
    { //calculate the sum of the digits of a number

        int sum = 0;

        while (n > 0)
        {
            sum = sum + (n%10);
            n /= 10;
        }

        return sum;
    }

    static String encrypt(String word) //receive a word and print its encrypted string
    {
        Scanner scan = new Scanner(System.in);
        Random ran = new Random();
        int len = word.length(); //length of the word
        int[] ascii_val =  new int[len]; //ascii value of letters
        int temp_value = 0; //numeric part of encryption
        String temp_word = word; //save the word
        String encrypted_string = ""; //store the encrypted string
        String capital_word = word.toUpperCase(); //store the encrtyped string converted to capital words
        String letters; //alphabetic part of encryption
        int sum;
        int t, remainder, quotient;
        int random, random_case1, random_case2;

        for (int i = 0; i < len; i++)
        {
            ascii_val[i] = (int) word.charAt(i); //get the ascii value

            temp_value = (len * ascii_val[i]) + len + i+1; //calculate the numeric part of the encrypted letter

            sum = alg_sum(temp_value);
            //System.out.println(sum);
            t = (sum*len) + (sum - 1);
            //System.out.println(t);
            quotient = t/26;
            random_case1 = ran.nextInt(2);
            if(random_case1 == 1)
            {
                remainder = (t%26) + 65;
                quotient = t/26;
            }
            else
                remainder = (t%26) + 97;

            letters = "";

            while (quotient != 0)
            {
                random_case2 = ran.nextInt(2);
                if(random_case2 == 1)
                    random = ran.nextInt(26) + 65;
                else
                    random = ran.nextInt(26) + 97;
                letters = letters + (char)random;
                quotient--;
            }
            letters = letters + (char)remainder;
            //c = (char)(((temp_value * (len - i)%26)+65);

            encrypted_string = encrypted_string + temp_value + letters;
        }
        return(encrypted_string);
    }



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypt);
        button3 = (Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i;

                userString = (EditText) findViewById(R.id.editText);

                String sentence = userString.getText().toString();
                String[] word = new String[100];
                word = encryptParse(sentence);
                String output = "";
                for (i = 0; i < word.length; i++)
                {
                    if(word[i] != null) {
                        output += encrypt(word[i]);
                    } else {
                        break;
                    }
                }

                //System.out.println(sentence);
                TextView myTextView = (TextView) findViewById(R.id.textView4);
                myTextView.setText(output);
                out = output;
                myTextView.setVisibility(View.VISIBLE);
            }
        });
    }

}
