package com.example.agentesprosaude_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questionario extends AppCompatActivity {

    //iniciar variável
    private Button voltar;
    private Button enviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionario);

        //relacionar variável
        voltar = (Button) findViewById(R.id.voltarBtn);
        enviar = (Button) findViewById(R.id.enviarBtn);

        //volta para a activity EscolhaQuestionario ao clicar no botão
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAct = new Intent(Questionario.this, EscolhaQuestionario.class);
                startActivity(voltarAct);
            }
        });
    }

    /*
    public void eventos(){
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent voltarAct = new Intent(Questionario.this, MainActivity.class);
                voltarAct.putExtra("acao", 1);
                startActivity(voltarAct);
            }
        });
    }*/
}