package com.example.agentesprosaude_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ferramentas.EventosDB;

public class MainActivity extends AppCompatActivity {

    //iniciar variáveis
    private TextView titulo1;
    private TextView textoDeLogin;
    private EditText cpfTxt;
    private EditText senhaTxt;
    private Button confirmar;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //relacionar variáveis
        titulo1 = (TextView) findViewById(R.id.titulo1Txt);
        textoDeLogin = (TextView) findViewById(R.id.textoDeLoginTxt);
        cpfTxt = (EditText) findViewById(R.id.cpfTxt);
        senhaTxt = (EditText) findViewById(R.id.senhaTxt);
        confirmar = (Button) findViewById(R.id.confirmarBtn);

        eventos();
        //autenticaUsuario(cpfTxt.getText().toString(), Integer.parseInt(senhaTxt.getText().toString()));
    }

    private void autenticaUsuario(String cpf, int senha){

        EventosDB db = new EventosDB(MainActivity.this);
        db.insereAgente();

        boolean resultado = db.buscaAgente(cpf, senha);

        if(resultado == true){
            eventos();
        }
        else{
            Toast.makeText(MainActivity.this, "Falha ao logar", Toast.LENGTH_LONG).show();
        }

    }

    private void eventos(){

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent trocarAct = new Intent(MainActivity.this, EscolhaQuestionario.class);
                trocarAct.putExtra("acao", 0);
                startActivity(trocarAct);
            }
        });

        /*
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventosDB db = new EventosDB(MainActivity.this);
                db.insereAgente();

                //db.buscaAgente();

                String cpf = cpfTxt.getText().toString();
                int senha = Integer.parseInt(senhaTxt.getText().toString());

                //mostra o nome do banco criado
                //Toast.makeText(MainActivity.this, db.getDatabaseName(), Toast.LENGTH_LONG).show();
            }
        });*/
    }

}