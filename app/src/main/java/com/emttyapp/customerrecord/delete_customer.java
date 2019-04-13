package com.emttyapp.customerrecord;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class delete_customer extends AppCompatActivity {
DataBaseHelper myDb;
EditText txtid;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_customer);
        myDb = new DataBaseHelper(this);
        txtid=(EditText)findViewById(R.id.id);
        btn=(Button)findViewById(R.id.deletebtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickme();
                txtid.getText().clear();
            }
        });
    }

    private void Clickme(){
        String id = txtid.getText().toString();
        int result = myDb.deleteData(id);
        Toast.makeText(this,result+" :ROWS AFFECTED",Toast.LENGTH_SHORT).show();

    }
}
