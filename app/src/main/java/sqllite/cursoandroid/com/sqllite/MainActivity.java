package sqllite.cursoandroid.com.sqllite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        try {

            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //tabela
           bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3) ) ");
          //  bancoDados.execSQL("DROP TABLE pessoas");

           //Inserir dados
           // bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Mariana', 18) ");
           // bancoDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ana',20) ");
            bancoDados.execSQL("UPDATE pessoas SET idade = 60 WHERE id = '3' ");
           //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Marcos'");


            /*cursor permite percorrer os dados que foram inseridos
              método rawQuery para recuperar as pessoas da tabela*/
            Cursor cursor = bancoDados.rawQuery("SELECT nome, idade ,id FROM pessoas ", null);

            //recupera indice de cada coluna
            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");
            int indiceColunaId = cursor.getColumnIndex("id");

            //Volta o cursor para o começo
            cursor.moveToFirst();

            while (cursor != null) {

                Log.i("RESULTADO - nome: ", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade: ", cursor.getString(indiceColunaIdade));
                Log.i("RESULTADO - id: ", cursor.getString(indiceColunaId));

                cursor.moveToNext();
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
