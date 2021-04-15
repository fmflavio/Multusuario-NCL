package com.example.fmflavio.polemorse;

import android.content.Context;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button buttonMA, buttonAM;
    private EditText editTextInput, editTextOutput;

    private char [] alfabeto = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    private String [] morse = { ".-" , "-..." , "-.-." , "-.." , "." , "..-." , "--." , "...." , ".." , ".---" , "-.-" , ".-.." , "--" , "-." , "---" , ".--." , "--.-" ,  ".-." , "..." , "-" , "..-" , "...-" , ".--" , "-..-" , "-.--" , "--.." };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Vibrator vibe = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        final ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 100);

        buttonAM = (Button) findViewById(R.id.buttonAM);
        buttonMA = (Button) findViewById(R.id.buttonMA);
        editTextInput = (EditText)findViewById(R.id.editTextInput);
        editTextOutput = (EditText)findViewById(R.id.editTextOutput);
        editTextInput.setFocusable(true);
        editTextOutput.setKeyListener(null);

        Toast.makeText(getApplicationContext(), R.string.message, Toast.LENGTH_LONG).show();

        buttonMA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextOutput.setText(morseToAlphabet(editTextInput.getText().toString()));
                vibe.vibrate(40);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);
                Toast.makeText(getApplicationContext(), R.string.translater_m_a, Toast.LENGTH_SHORT).show();
            }
        });
        buttonAM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextOutput.setText(alphabetToMorse(editTextInput.getText().toString()));
                vibe.vibrate(40);
                toneG.startTone(ToneGenerator.TONE_CDMA_ALERT_CALL_GUARD, 50);
                Toast.makeText(getApplicationContext(), R.string.translater_a_m, Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected String morseToAlphabet(String input){
        String saida = "";
        String[] palavra = new String[1];
        palavra[0] = input;
        for (String letra: palavra ){
            String[] caracteres = letra.split(" ");
            for(int h = 0;h < caracteres.length;h++){
                for(int i = 0; i < morse.length; i++){
                    if(caracteres[h].equals(morse[i])){
                        saida = saida.concat(alfabeto[i]+" ");
                    }
                }
            }
        }
        return saida;
    }

    protected String alphabetToMorse(String input){
        String saida = "";
        input = input.toLowerCase().trim();
        for ( int x = 0; x < input.length(); x++ ){
            for ( int y = 0; y < alfabeto.length; y++ ){
                if ( alfabeto [ y ] == input.charAt ( x ) ) {
                    saida = saida+morse[y]+" ";
                }
            }
        }
        return saida;
    }
}
