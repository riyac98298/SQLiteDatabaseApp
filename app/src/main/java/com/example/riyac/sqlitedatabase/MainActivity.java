package com.example.riyac.sqlitedatabase;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText, editText2;
    Button button, button2;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView)findViewById(R.id.textView);
        editText=(EditText)findViewById(R.id.editText);
        editText2=(EditText)findViewById(R.id.editText2);
        button=(Button)findViewById(R.id.button);
        button2=(Button)findViewById(R.id.button2);

        sqLiteDatabase=openOrCreateDatabase("appdb",MODE_PRIVATE , null);
        sqLiteDatabase.execSQL("create table if not exists apptable(name varchar(30), id int(10))");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=editText.getText().toString();
                String i=editText2.getText().toString();
                sqLiteDatabase.execSQL("insert into apptable values('"+n+"' , '"+i+"')");
                Toast.makeText(getApplicationContext(), "Saved!", Toast.LENGTH_LONG).show();
                editText.setText("");
                editText2.setText("");
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = "select * from apptable";
                Cursor cursor = sqLiteDatabase.rawQuery(s, null);
                cursor.moveToFirst();
                int count = cursor.getCount();
                String str3="";
                for (int i = 0; i < count; i++)
                {
                    String str = cursor.getString(0);
                    String str1 = cursor.getString(1);
                    String str2 = str1 + " " + str;
                    str3+=str2+" "+" \n";
                    cursor.moveToNext();
                }
                textView.setText(str3);
            }
        });
    }
}
