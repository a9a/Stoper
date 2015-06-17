package osinska.agnieszka.stooper;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.Calendar;


public class MenuStart extends ActionBarActivity {
    Czas czas;
    Chronometer c;
    long temp;
    boolean fl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_start);
        c=(Chronometer) findViewById(R.id.chronometer);
        temp=0;
        fl=false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_start, menu);

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
    public void clickStart(View view) {
        Log.d("get",""+c.getBase());
        Log.d("get2",""+SystemClock.elapsedRealtime());
        c.setBase(SystemClock.elapsedRealtime());
        c.start();
        fl=true;



    }
    public void clickStop(View view){
        //c.setBase(c.getBase()-t);
        if(fl) {
            long wynik = 0;
            c.stop();
            wynik = SystemClock.elapsedRealtime() - c.getBase();
            Calendar k = Calendar.getInstance();
            int d = k.get(Calendar.DATE);
            String data = "" + k.get(Calendar.DATE) + "." + k.get(Calendar.MONTH) + "." + k.get(Calendar.YEAR);
            Log.d("ddd", data);
            czas = new Czas(wynik, data);
            Toast.makeText(getApplicationContext(), "Twoj czas to:\n\t "+czas.rezultatString(), Toast.LENGTH_LONG).show();
            Log.d("stop", czas.rezultatString());
            fl=false;
            dodajDoBazy(czas);
            showNotification(czas.getWynikString());
        }

    }
//    public void clickPause(View view){
//        boolean pause = ((ToggleButton)view).isChecked();
//
//        if(pause){
//            Log.d("kkk", "continue");
//            temp=SystemClock.elapsedRealtime();
//            c.stop();
//
//            Log.d("kkk", "" + (temp));
//
//        }else{
//            c.setBase(temp);
//            c.start();
//
//        }
//    }
    public void dodajDoBazy(Czas czas){
        ZarzadcaBazy zb=new ZarzadcaBazy(this);
        zb.dodajWynik(czas);


    }
    public void showNotification(String st){
        Notification n=new Notification.Builder(this).setContentTitle("Wynik")
                .setContentText(st)
                .setSmallIcon(R.drawable.image_article).build();
        NotificationManager notificationManager=
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(0,n);
    }
}
