package osinska.agnieszka.stooper;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Aga on 2015-06-16.
 */
public class CzasAdapter extends ArrayAdapter<Czas> {
    Context context;
    List<Czas> czasy;

    public CzasAdapter(Context context, int resource, List<Czas> objects) {
        super(context, resource, objects);

        this.context = context;
        this.czasy = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Czas  czas = czasy.get(position);

        LayoutInflater inflater =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_czas_item, null);
        TextView tvWynik = (TextView) view.findViewById(R.id.tvWynik);
        tvWynik.setText(czas.getWynikString());

        TextView tvData = (TextView) view.findViewById(R.id.tvData);
        tvData.setText(czas.getData());

        return view;
    }

}

