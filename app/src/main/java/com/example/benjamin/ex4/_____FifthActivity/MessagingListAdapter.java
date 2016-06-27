package com.example.benjamin.ex4._____FifthActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.benjamin.ex4.R;

/**
 * Created by benjamin on 21/06/16.
 */
public class MessagingListAdapter extends ArrayAdapter<String> {
    private final Context context;
    private final String[] values;

    public MessagingListAdapter(Context context, String[] values) {
        super(context, R.layout.message_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.message_item, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.label);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textView.setText(values[position]);
        // Change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("Windows7") || s.startsWith("iPhone")
                || s.startsWith("Solaris")) {
            imageView.setImageResource(R.drawable.bernie_head);
        } else {
            imageView.setImageResource(R.drawable.frozone_head);
        }

        return rowView;
    }
}
