package osinska.agnieszka.stooper;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Wyniki extends ActionBarActivity {
    public static final int WYNIK_REQUEST_CODE = 100;
    public static final String WYNIK_DATA = "Czas";

    protected List<Czas> wyniki;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyniki);
        fillList();
        ListView lv = (ListView) findViewById(R.id.listView);
        registerForContextMenu(lv);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wyniki, menu);
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
//    public void wyswietl(){
//        TextView tv=(TextView)findViewById(R.id.textView);
//        tv.setText("");
//        String t="";
//        ZarzadcaBazy zb=new ZarzadcaBazy(this);
//        for(Czas cz: zb.wszystkieCzasy()){
//            t+=cz.getNr()+".   "+cz.getWynikString()+"\t"+cz.getData()+"\n";
//            Log.d("baza",cz.getNr()+".   "+cz.getWynikString()+"   "+cz.getData()+"\n");
//        }

    //}
    private void fillList() {
        ZarzadcaBazy zb=new ZarzadcaBazy(this);
        wyniki = zb.wszystkieCzasy();
        ArrayAdapter<Czas> czasAdapter = new CzasAdapter(this, 0, wyniki);

        ListView lv = (ListView) findViewById(R.id.listView);
        lv.setAdapter(czasAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Czas c=wyniki.get(position);
                wyswietlInfo(c);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        if(v.getId() == R.id.listView){   // Jezeli widokiem jest ListView
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.menu_wyniku, menu);
        }

        super.onCreateContextMenu(menu, v, menuInfo);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //Log.d("item", ""+item.getItemId());
        final String txt;
        if(item.getItemId() == R.id.dodaj) // Usun Studenta
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            final Czas c=wyniki.get(info.position);
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Notatka");
            builder.setIcon(R.drawable.not);
            final EditText input = new EditText(this);
// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
            input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    c.setTxt(input.getText().toString());
                    aktualizuj(c);


                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    Toast.makeText(getApplicationContext(),"Anulowano!",Toast.LENGTH_LONG).show();
                }
            });

            builder.show();

        }else if(item.getItemId()==R.id.usun){
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
            Czas c=wyniki.get(info.position);
            ZarzadcaBazy zb = new ZarzadcaBazy(this);
            zb.kasujWynik(c.getNr());
            fillList();
            Toast.makeText(getApplicationContext(),"Usunieto!",Toast.LENGTH_LONG).show();
        }
        return super.onContextItemSelected(item);
    }
    public void wyswietlInfo(Czas czas){
        Intent i=new Intent(this, WyswietlanieWyniku.class);
        i.putExtra("czas",czas.getWynikString());
        i.putExtra("notatka", czas.getTxt());
        i.putExtra("data",czas.getData());
        startActivity(i);


    }
    public void aktualizuj(Czas czas){
        ZarzadcaBazy zb = new ZarzadcaBazy(this);
        zb.aktualizujWynik(czas);
        Toast.makeText(getApplicationContext(),"Dodano notatke!",Toast.LENGTH_LONG).show();
    }
}
