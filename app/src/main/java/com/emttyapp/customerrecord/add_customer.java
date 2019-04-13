package com.emttyapp.customerrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class add_customer extends AppCompatActivity {
DataBaseHelper myDB;
EditText txtname,txtnumber,txtdate,txtamount;
Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_customer);
        myDB = new DataBaseHelper(this);

        txtname=(EditText)findViewById(R.id.idName);
        txtnumber=(EditText)findViewById(R.id.idNumber);
        txtdate=(EditText)findViewById(R.id.idDate);
        txtamount=(EditText)findViewById(R.id.idAmount);
        btn=(Button)findViewById(R.id.addbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Clickme();
                txtname.getText().clear();
                txtnumber.getText().clear();
                txtdate.getText().clear();
                txtamount.getText().clear();
            }
        });
    }
    void Clickme(){
        String name=txtname.getText().toString();
        String number=txtnumber.getText().toString();
        String date=txtdate.getText().toString();
        String amount=txtamount.getText().toString();
        Boolean result =myDB.insertData(name,number,date,amount);
        if (result==true){
            Toast.makeText(this,"Customer Detail Added Successfully",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"failed to add Customer Detail",Toast.LENGTH_SHORT).show();
        }

    }
}
    class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Customer.db";
    public static final String TABLE_NAME = "Customer_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="NAME";
    public static final String COL_3 ="MOBILE";
    public static final String COL_4 ="DATE";
    public static final String COL_5 ="AMOUNT";

    public DataBaseHelper(Context context){
        super(context, DATABASE_NAME, null, 1);
    }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE "+ TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT ,NAME TEXT NOT NULL " +
                    ",MOBILE TEXT NOT NULL ,DATE TEXT ,AMOUNT TEXT NOT NULL)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int i, int i1) {
            db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        }

        public boolean insertData(String name ,String number , String date, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name);
        contentValues.put(COL_3, number);
        contentValues.put(COL_4, date);
        contentValues.put(COL_5, amount);
        long result = db.insert(TABLE_NAME,null,contentValues);
        db.close();

        if (result==-1)
            return false;
        else
            return true;
    }
    public Cursor getALLData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery(" SELECT * from " + TABLE_NAME ,null);
        return res;
    }

    public Integer deleteData(String id){
        SQLiteDatabase Db = this.getWritableDatabase();
        int i=Db.delete(TABLE_NAME,"ID=?",new String[]{id});
        return i;
    }

    public boolean updateData(String id , String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_5, amount);
        int result=db.update(TABLE_NAME,contentValues,"ID =?",new String[]{id});
        if (result>0){
            return true;
        }
        else {
            return false;
        }
    }


}