package com.example.pedrogarcia.encrypt0;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Decrypt extends AppCompatActivity {

    private EditText userString;
    Button button3;
    static int total_code;

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
    static String[] decryptParse(String sentence)
    {

        char[] sen = sentence.toCharArray();
        int length = sentence.length();                                        // length of the user's encrypted input
        String[] word = new String[length];

        int j = 0;
        int i = 0;
        while(j < length)
        {

            word[i] = "";

            while (j < sen.length && Character.isDigit(sen[j]))              //runs till a letter is found
            {
                word[i] += sen[j];                          //puts the number in the char array
                j++;
            }

            while (j < sen.length && Character.isLetter(sen[j]))
            {
                word[i] += sen[j];
                j++;
            }
            i++;
        }
        total_code = i;
        return word;
    }

    static String decryptValFinder(String[] word)
    {
        int total = total_code;
        String chars[] = new String[total];
        String[] letters = new String[total];
        int j,temp_length;
        int[] length = new int[total];

        String output="";
        int[] letter_length = new int[total];
        int[] letter_sum = new int[total];

        int[] digit = new int[total];
        int[] charASCII = new int[total];
        char oneletter,lastletter;
        int i=0,m=0, counter = 0,word_count = -1,pos =0;
        while(i<total) {
            j = 0;
            while (j < word[i].length() && Character.isDigit(word[i].charAt(j)))
            {
                j++;
            }
            digit[i] = Integer.parseInt(word[i].substring(0, j));
            // System.out.println(digit[i]);
            int sum = alg_sum(digit[i]);
            // System.out.println(sum);
            letters[i] = word[i].substring(j, word[i].length());
            letters[i] = letters[i].toUpperCase();
            // System.out.println(letters[i]);
            letter_length[i] = letters[i].length();
            if (letter_length[i] == 1)
            {
                oneletter = letters[i].charAt(0);
                letter_sum[i] = ((int) oneletter) - 65;
                //System.out.println(letter_sum[i]);
            }
            else
            {
                lastletter = letters[i].charAt(letter_length[i] - 1);
                letter_sum[i] = ((letter_length[i] - 1) * 26) + ((int) lastletter) - 65;
                // System.out.println(letter_sum[i]);
            }
            while(m==counter)
            {
                word_count++;
                pos=0;
                length[word_count] = ((letter_sum[i] - sum +1) / sum);
                counter += length[word_count];
                if(word_count>0)
                    System.out.printf(" ");
                    output += " ";
            }
            charASCII[i] = ((digit[i] - length[word_count] - (pos+1)) / length[word_count])-1;
            System.out.printf("%c",(char) (charASCII[i] + 1));
            output += (char) (charASCII[i] + 1);
            i++;
            m++;
            pos++;
        }
        return output;
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
                word = decryptParse(sentence);
                String output = "";
                 if(word != null) {
                        output += decryptValFinder(word);
                    }

                //System.out.println(sentence);
                TextView myTextView = (TextView) findViewById(R.id.textView4);
                myTextView.setText(output);

                myTextView.setVisibility(View.VISIBLE);
            }
        });
    }

}
