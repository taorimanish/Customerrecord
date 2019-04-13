package com.emttyapp.customerrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class update_customer extends AppCompatActivity {
DataBaseHelper myDb;
EditText txtid,txtamount;
Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);
        myDb = new DataBaseHelper(this);
        txtid =(EditText)findViewById(R.id.id);
        txtamount =(EditText)findViewById(R.id.idAmount);
        btn=(Button)findViewById(R.id.updatebtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickme();
            }
        });
        }
        private void Clickme(){
        String id=txtid.getText().toString();
        String amount =txtamount.getText().toString();
        Boolean result =myDb.updateData(id,amount);
        if (result==true)
            Toast.makeText(this,"DATA UPDATED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this,"NO ROWS AFFECTED",Toast.LENGTH_SHORT).show();

        }
}
