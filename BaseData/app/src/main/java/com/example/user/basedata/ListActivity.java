package com.example.user.basedata;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListActivity extends android.app.ListActivity{


    SQLiteDatabase db;
    TextView selection;
    String[] items;                //= { "Apple", "Banana", "PineApple", "Watermelon"};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);



        selectdata();

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items));
        //selection=(TextView)findViewById(R.id.selection);

    }

    public void selectdata()
    {
        //txtdata.setText("");
        createdb();
        Cursor c1 = db.rawQuery("Select Name from tblstudent",null);

        int c=c1.getCount();
        int i=0;
        items=new String[c];
        while (c1.moveToNext()) {

            items[i]=c1.getString(0);
            i++;
        }
        db.close();
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



    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //String text = " position:" + position + " " + items[position];
        //selection.setText(text);


        Intent updateintent = new Intent (ListActivity.this,update.class);
        Bundle myData = new Bundle();
        myData.putString("StudentName",items[position].toString());
        updateintent.putExtras(myData);
        startActivityForResult(updateintent,100);
    }
}
