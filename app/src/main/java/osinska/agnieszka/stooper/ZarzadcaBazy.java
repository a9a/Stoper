package osinska.agnieszka.stooper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

import osinska.agnieszka.stooper.Czas;

/**
 * Created by Aga on 2015-06-15.
 */
public class ZarzadcaBazy extends SQLiteOpenHelper{
    public ZarzadcaBazy(Context context) {
        super(context, "wyniki.db", null, 1);
    }
    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(
                "create table wyniki(" +
                        "nr integer primary key autoincrement," +
                        "wynik text," +
                        "data text," +
                        "notatka text);" +
                        ""
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void dodajWynik(Czas czas) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        wartosci.put("wynik", czas.rezultatString());
        wartosci.put("data", czas.getData());
        wartosci.put("notatka", czas.getTxt());
        db.insertOrThrow("wyniki", null, wartosci);
    }
    public List<Czas> wszystkieCzasy(){
        List<Czas> czasy=new LinkedList<Czas>();
        String[] kolumny={"nr","wynik","data","notatka"};
        SQLiteDatabase db=getReadableDatabase();
        Cursor kursor=db.query("wyniki",kolumny,null, null, null, null, null);
        while(kursor.moveToNext()){
            Czas w=new Czas();
            w.setNr(kursor.getLong(0));
            w.setWynikString(kursor.getString(1));
            w.setData(kursor.getString(2));
            w.setTxt(kursor.getString(3));
            czasy.add(w);
        }
        return czasy;
    }
    public void kasujWynik(long id){
        SQLiteDatabase db=getWritableDatabase();
        String[] argumenty={""+id}; //auto rzutowanie na stringa
        db.delete("wyniki","nr=?", argumenty);
    }
    public void aktualizujWynik(Czas czas){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues wartosci=new ContentValues();
        //wartosci.put("wynik", czas.rezultatString());
        wartosci.put("data", czas.getData());
        wartosci.put("notatka", czas.getTxt());
        String args[]={czas.getNr()+""};
        db.update("wyniki", wartosci, "nr=?", args);
    }
}
