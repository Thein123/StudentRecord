package com.example.user.basedata;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class update extends AppCompatActivity implements View.OnClickListener {

    SQLiteDatabase db;

    TextView txtdata;
    EditText edsid;
    EditText edname;
    EditText ednrc;
    EditText edaddress;
    EditText edphone;

    Button btnupdate;
    Button btndelete;
    Button btnclear;
    Button btnshow;

    String studentName;

    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);





    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        txtdata = (TextView) findViewById(R.id.utxtdata);
        edsid = (EditText) findViewById(R.id.usid);
        edname = (EditText) findViewById(R.id.uname);
        ednrc = (EditText) findViewById(R.id.unrc);
        edaddress = (EditText) findViewById(R.id.uaddress);
        edphone = (EditText) findViewById(R.id.uphone);

        btnupdate = (Button) findViewById(R.id.uupdate);
        btnupdate.setOnClickListener(this);
        btnshow = (Button) findViewById(R.id.ushow);
        btnshow.setOnClickListener(this);
        btnclear = (Button) findViewById(R.id.uclear);
        btnclear.setOnClickListener(this);
        btndelete = (Button) findViewById(R.id.udelete);
        btndelete.setOnClickListener(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();
        studentName = myBundle.getString("StudentName");
        //txtdata.setText(studentName);
        createdb();
        Cursor c1 = db.rawQuery("Select * from tblstudent where Name='" + studentName + "'", null);


        while (c1.moveToNext()) {
            edsid.setText(c1.getString(0));
            edname.setText(c1.getString(1));
            ednrc.setText(c1.getString(2));
            edaddress.setText(c1.getString(3));
            edphone.setText(c1.getString(4));

            db.close();
        }

    }

    public void createdb(){
        try {
            db = this.openOrCreateDatabase(
                    "studentDB",
                    MODE_PRIVATE,
                    null);
            //db.close();
        }
        catch (SQLiteException e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void updatetable()
    {
        String sid=edsid.getText().toString();
        String name=edname.getText().toString();
        String nrc=ednrc.getText().toString();
        String address=edaddress.getText().toString();
        String phone=edphone.getText().toString();

        createdb();
        db.beginTransaction();
        try {
            db.execSQL("Update tblStudent set Address='"+ address +"', nrc='"+ nrc +"',phonenumber='"+ phone +"',name='"+ name +"' where sid='"+sid +"'");
            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }

    public void deletestudent()
    {
        String sid=edsid.getText().toString();

        createdb();
        db.beginTransaction();
        try {
            db.execSQL("Delete from tblStudent where sid='"+sid +"'");
            db.setTransactionSuccessful(); //commit your changes
        }
        catch (SQLiteException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        finally {
            db.endTransaction();
            db.close();
        }
    }

    public void showdata()
    {
        txtdata.setText("" );
        createdb();
        Cursor c1 = db.rawQuery("Select * from tblstudent", null);


        while (c1.moveToNext()) {
            txtdata.append("\n" + c1.getInt(0) + " "
                    + c1.getString(1) + " "
                    + c1.getString(2) + " "
                    + c1.getString(3) + " "
                    + c1.getString(4));
        }
        db.close();
    }

    public void cleardata()
    {
        edname.setText("");
        ednrc.setText("");
        edaddress.setText("");
        edphone.setText("");
        edname.requestFocus();

    }


    @Override
    public void onClick(View v) {
        if(v==btnupdate)
        {
            updatetable();

            showdata();
            cleardata();
        }
        if(v==btnshow)
        {
            showdata();
        }
        if(v==btnclear)
        {
            cleardata();
        }
        if(v==btndelete)
        {
            deletestudent();
            showdata();
            cleardata();
        }

    }



}
