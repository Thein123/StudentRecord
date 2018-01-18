package com.example.user.basedata;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    SQLiteDatabase db;
    TextView txtdata;
    Button btnsave;
    Button btnshow;
    Button btnclear;
    Button btnupdate;

    //EditText edsid;
    EditText edname;
    EditText ednrc;
    EditText edaddress;
    EditText edphone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtdata=(TextView)findViewById(R.id.txtdata);
        //edsid=(EditText)findViewById(R.id.sid);
        edname=(EditText)findViewById(R.id.name);
        ednrc=(EditText)findViewById(R.id.nrc);

        edaddress=(EditText)findViewById(R.id.address);
        edphone=(EditText)findViewById(R.id.phone);

        btnsave=(Button)findViewById(R.id.save);
        btnsave.setOnClickListener(this);
        btnshow=(Button)findViewById(R.id.show);
        btnshow.setOnClickListener(this);
        btnclear=(Button)findViewById(R.id.clear);
        btnclear.setOnClickListener(this);
        btnupdate=(Button)findViewById(R.id.update);
        btnupdate.setOnClickListener(this);

        View saveBtn=findViewById(R.id.save);
        View updateBtn=findViewById(R.id.update);
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

        if(existtable()) {
            createtable();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createdb(){
        try {
            db = this.openOrCreateDatabase(
                    "studentDB",
                    MODE_PRIVATE,
                    null);

        }
        catch (SQLiteException e) {

            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void createtable(){
        createdb();
        db.beginTransaction();
        try {
            db.execSQL("Create table tblstudent( SID integer primary key autoincrement, name text, nrc text, address text, phonenumber text)" );


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

    public boolean existtable()
    {
        createdb();
        int result = db.rawQuery("SELECT name FROM sqlite_master  WHERE type='table' AND name='tblstudent' ", null).getCount();
        db.close();
        if (result == 0) {
            return true;
        }
        else {
            return false;
        }

    }

    public void inserttable()
    {
        //String sid=edsid.getText().toString();
        String name=edname.getText().toString();
        String nrc=ednrc.getText().toString();
        String address=edaddress.getText().toString();
        String phone=edphone.getText().toString();

        createdb();
        db.beginTransaction();
        try {
            db.execSQL("Insert into tblstudent(name,nrc,address,phonenumber) values ('"+ name +"','"+ nrc +"','"+ address +"','"+ phone +"')");
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

    public void cleardata()
    {
        edname.setText("");
        ednrc.setText("");
        edaddress.setText("");
        edphone.setText("");
        edname.requestFocus();

    }

    public void showdata()
    {
        txtdata.setText("" );
        createdb();
        Cursor c1 = db.rawQuery("Select * from tblstudent",null);


        while (c1.moveToNext()) {
            txtdata.append("\n" + c1.getInt(0) + " "
                    + c1.getString(1) + " "
                    + c1.getString(2) + " "
                    + c1.getString(3) + " "
                    + c1.getString(4));
        }
        db.close();
    }

    public void gotolist()
    {
        Intent listintent = new Intent (MainActivity.this,ListActivity.class);
        startActivity(listintent);
    }


    @Override
    public void onClick(View v) {
        if(v==btnsave)
        {
            inserttable();
            cleardata();
            showdata();
        }
        if(v==btnshow)
        {
            showdata();
        }
        if(v==btnclear)
        {
            cleardata();
        }
        if(v==btnupdate)
        {
            gotolist();
            //cleardata();
            //showdata();
        }
    }
}
