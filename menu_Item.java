package com.milesSayItRight.miles.sayitright;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by Miles on 1/14/2017.
 */

 class menu_Item {
    private float seekValue;

    menu_Item(){
        seekValue = 0;
       // builder = new AlertDialog.Builder(this);
    }
    void aboutMe(AlertDialog.Builder builder, TextView aboutMe){
        builder.setTitle("  About Developer");
        String aboutMulu = "My name is Mulugeta Engdaw. I am a third year college student studying Computer Engineering. I developed" +
                " this app with intention of making pronunciation of English words easier for those who are barely learning English." +
                " Also for those who know English, yet struggle in pronouncing certain words.";
        aboutMe.setText(aboutMulu);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(aboutMe);
        builder.create();
        builder.show();


    }
    void pitch(AlertDialog.Builder popupDialog){

        popupDialog.setTitle("Alter Pitch");

        //listView.setCacheColorHint(5);
        //listView.
        //popupDialog.setCancelable(true);
        final String [] listOfPitch = {"0.0","0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8","0.9","1.0"};

        popupDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        popupDialog.setItems(listOfPitch, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                seekValue = Float.parseFloat(listOfPitch[which]);
            }
        });
        popupDialog.create();
        popupDialog.show();

        //return 0.0;
    }
    float returningPitchValue(){
        //float seekVal = seekValue/10;
        return seekValue/10;
    }

}
