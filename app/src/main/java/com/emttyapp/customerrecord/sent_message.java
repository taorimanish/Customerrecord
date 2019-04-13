package com.emttyapp.customerrecord;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class sent_message extends Activity {
Button msgbtn;
DataBaseHelper myDB;

private static final int MY_PERMISSIONS_REQUEST_SEND_SMS=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sent_message);
        myDB =new DataBaseHelper(this);
        msgbtn=(Button)findViewById(R.id.msgbtn);
        }

    public void sendSms(View v){
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);
        if (permissionCheck== PackageManager.PERMISSION_GRANTED){
            MyMessage();
        }else {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},MY_PERMISSIONS_REQUEST_SEND_SMS);
        }
    }

        public void MyMessage(){
            Cursor cursor = myDB.getALLData();
            boolean sent=false;
            if (cursor.moveToFirst())
            {
                do
                {   String message=("HELLO SIR \n Your Amount  Of " +cursor.getString(4)+"Rs  Is Still Balance. Please Pay It soon.(If paid please ignore the message)\n " +
                        "THANK YOU \n SHREE KALASH TILES \n NAGPUR0");
                    String number = cursor.getString(2);//Column Number should be on 1st Position
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(number, null, message, null, null);
                }while(cursor.moveToNext());
                sent=true;
            }
            if (sent==true)
                Toast.makeText(this,"MESSAGE SENT SUCCESSFULLY",Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this,"FAILED TO SENT",Toast.LENGTH_SHORT).show();
            cursor.close();
        }
        
}
