package com.emttyapp.customerrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class view_customer extends AppCompatActivity {
DataBaseHelper mydb;
TextView txtresult;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);

        mydb = new DataBaseHelper(this);
        txtresult=(TextView)findViewById(R.id.result);
        btn=(Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickme();
            }
        });

    }
    private void Clickme(){
        Cursor res = mydb.getALLData();
        StringBuffer stringBuffer = new StringBuffer();
        if (res!=null && res.getCount()>0){
            while (res.moveToNext()){
                stringBuffer.append("ID: "+res.getString(0)+"\n");
                stringBuffer.append("CUSTOMER NAME: "+res.getString(1)+"\n");
                stringBuffer.append("MOBILE NUMBER: "+res.getString(2)+"\n");
                stringBuffer.append("DATE: "+res.getString(3)+"\n");
                stringBuffer.append("AMOUNT BALANCE: "+res.getString(4)+"\n");
                stringBuffer.append("\n\n");
            }
            txtresult.setText(stringBuffer.toString());
            Toast.makeText(this,"DATA RETRIEVED SUCESSFULLY",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"NO DATA TO RETRIEVE",Toast.LENGTH_SHORT).show();
        }
    }
}
