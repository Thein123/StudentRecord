/*
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import java.util.ArrayList;
import java.util.List;
public class DataManipulator {
    private static final String DATABASE_Name="mydatabase.db";
    private static final int DATABASE_VERSION=1;
    static final String TABLE_NAME="newtable";
    private static Context context;
    static SQLiteDatabase db;
    private SQLiteStatement insertStml;
    private static final String INSERT="insert into"+TABLE_NAME+"(SID,Name,NRC,Address,Phone)values(?,?,?,?)";
    public DataManipulator(Context context){
        DataManipulator.context=context;
        OpenHelper openHelper=new OpenHelper(DataManipulator.context);
        DataManipulator.db=openHelper.getWritableDatabase();
        this.insertStml=DataManipulator.db.compileStatement(INSERT);

    }
    public long insert(String sid,String name,String nrc,String address,String phone){
        this.insertStml.bindString(1,sid);
        this.insertStml.bindString(2, name);
        this.insertStml.bindString(3, nrc);
        this.insertStml.bindString(4, address);
        this.insertStml.bindString(5,phone);
        return this.insertStml.executeInsert();

    }
    public void deleteAll(){
        db.delete(TABLE_NAME, null, null);
    }
    public List<String>selectAll(){
        List<String[]>list=new ArrayList<String[]>();
        Cursor cursor=db.query(TABLE_NAME.new String[]{
                "sid","name","nrc","Address","Phone"
        },null,null,null,"name asc");
        int x=0;
        if(cursor.moveToFirst()){
            do{
                String[]b1=new
                        String[]{
                        cursor.getString(1),cursor.getString(1),cursor.getString(2),cursor.getString(3),
                        cursor.getString(4),cursor.getString(5)};

                }
            list.add(b1);
            x=x+1;
            } while(cursor.moveToNext());

        }
          if(cursor!=null&&!cursor.isClosed()){
        cursor.close();
    }
        cursor.close();
        return list;
}
public void delete(int rowId){
    db.delete(TABLE_NAME,null,null);
}
private static class OpenHelper extends SQLiteOpenHelper{
    OpenHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

}
*/
