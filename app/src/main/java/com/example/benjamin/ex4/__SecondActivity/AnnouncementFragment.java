package com.example.benjamin.ex4.__SecondActivity;

/**
 * Created by benjamin on 16/06/16.
 */
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.benjamin.ex4.R;

/**
 * A simple {@link android.support.v4.app.Fragment} subclass.
 */
public class AnnouncementFragment extends Fragment {
    private String content;
    private Drawable image;
    private RadioButton radioButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.first_activity_fragment_slide, container, false);
        TextView textBoxToChange = (TextView) rootView.findViewById(R.id.announcement);
        textBoxToChange.setText(content);
        ImageView imageToChange = (ImageView) rootView.findViewById(R.id.image);
        imageToChange.setImageDrawable(image);
        return rootView;
    }
    public void setText(String text)
    {
        content = text;
    }
    public void setImage(Drawable currentImage)
    {
        image = currentImage;
    }
    public void setRadioButton(RadioButton radioButton)
    {
        this.radioButton = radioButton;
    }
    //optional radio button to toggle with
    public void toggleRadioButton()
    {
        this.radioButton.toggle();
    }
}

