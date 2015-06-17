package osinska.agnieszka.stooper;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class WyswietlanieWyniku extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wyswietlanie_wyniku);
        Bundle bundle=getIntent().getExtras();
        TextView tv1=(TextView)findViewById(R.id.wyswietlWynik);
        TextView tv2=(TextView)findViewById(R.id.tvNotatka);
        TextView tv3=(TextView)findViewById(R.id.wyswietlData);
        tv1.setText(bundle.getString("czas"));
        tv2.setText(bundle.getString("notatka"));
        tv3.setText(bundle.getString("data"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_wyswietlanie_wyniku, menu);
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
}
