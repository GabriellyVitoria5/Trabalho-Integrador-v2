package ferramentas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import modelo.Evento;

public class EventosDB extends SQLiteOpenHelper {

    private Context contexto;

    public EventosDB (Context cont){
        super(cont, "agente", null, 2);
        contexto = cont;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String criaTabela = "CREATE TABLE IF NOT EXISTS agente(nome TEXT, telefone TEXT, email TEXT, cpf TEXT PRIMARY KEY, senha INTEGER)";

        db.execSQL(criaTabela);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //ficará parado até a atualização da Activity de update (funcionalidade)
        String sql_agente = "DROP TABLE if EXISTS agente";
        db.execSQL(sql_agente);
        onCreate(db);
    }

    public void insereAgente(){

        try(SQLiteDatabase db = this.getWritableDatabase()){

            //criar 4 agentes para testar o login
            String sql1 = "INSERT into agente(nome, telefone, email, cpf, senha) VALUES ('Talita', '988421875', 'talita@gmail.com', '05187443204', 1234)";
            String sql2 = "INSERT into agente(nome, telefone, email, cpf, senha) VALUES ('Fábio', '986176409', 'fabio@gmail.com', '09210754194', 4321)";
            String sql3 = "INSERT into agente(nome, telefone, email, cpf, senha) VALUES ('João', '87549819', 'joao@gmail.com', '11275309651', 1234)";
            String sql4 = "INSERT into agente(nome, telefone, email, cpf, senha) VALUES ('Ana Maria', '87431874', 'anamaria@gmail.com', '11295481231', 1234)";

            //inserir os dados dos 4 agentes no banco de dados
            db.execSQL(sql1);
            db.execSQL(sql2);
            db.execSQL(sql3);
            db.execSQL(sql4);

        }catch (SQLiteException ex){
            ex.printStackTrace();
        }

    }

    public boolean buscaAgente(String cpf, int senha){

        String sql = "SELECT * FROM agente WHERE cpf = " + cpf;

        //executa a sql
        try(SQLiteDatabase db = this.getWritableDatabase()){

            //extrai as informações
            Cursor tupla = db.rawQuery(sql, null);

            //if(tupla.moveToFirst())
            while(tupla.moveToNext()){

                if(cpf.equals(tupla.getString(tupla.getColumnIndex("cpf")))){
                    if(senha == (tupla.getInt(tupla.getColumnIndex("senha")))){
                        return true;
                    }
                }

            }

            db.close();
            tupla.close();

        }catch (SQLiteException ex){
            //mensagem de erro
            System.err.println("Erro  na consulta do banco.");
            ex.printStackTrace();
        }

        return false;

    }
}
