package com.milesSayItRight.miles.sayitright;



import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.speech.tts.TextToSpeech;
import android.widget.EditText;
import android.widget.Button;
//import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar;

//import com.appnext.admobadapter.AppnextAdMobCustomEvent;
//import com.appnext.admobadapter.AppnextAdMobCustomEventInterstitial;
//import com.appnext.ads.interstitial.InterstitialConfig;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.AdRequest;


//import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    TextToSpeech txtToSpeech;
    EditText entryText;
    menu_Item menu = new menu_Item();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

                 ///ADMOB--ADS//
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-1653600061611425/5005999397");
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
       // mAdView.setAdUnitId("ca-app-pub-1653600061611425/5512200193");
                 ///end ADMOB---ADS//

        ///APPNEXT --- ADS//
        /*InterstitialConfig config = new InterstitialConfig();
        config.setButtonText("Install");
        config.setCategories("Categories");
        config.setSkipText("Skip");
        config.setMute(true);
        config.setBackButtonCanClose(true);
        config.setAutoPlay(true);
        Bundle customEventExtras = new Bundle();
        customEventExtras.putSerializable
                (AppnextAdMobCustomEvent.AppnextConfigurationExtraKey,config);
        AdRequest adRequest1 = new AdRequest.Builder().addCustomEventExtrasBundle
                (AppnextAdMobCustomEventInterstitial.class, customEventExtras).build();
        //mAdView.loadAd(adRequest1);
        ///end APPNEXT --- ADS//*/

        entryText = (EditText) findViewById(R.id.enteredTxt);
        Button converter = (Button) findViewById(R.id.convertBtn);
       // Button languageOp = (Button) findViewById(R.id.langOp);
        converter.bringToFront();

        //this part of the code sets the language to local US English
        txtToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    //String locale = "US";
                    txtToSpeech.setLanguage(Locale.US);
                }
            }
        });
        converter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toBeSpoken = entryText.getText().toString();
                Toast.makeText(getApplicationContext(), toBeSpoken, Toast.LENGTH_LONG).show();
                txtToSpeech.setPitch(menu.returningPitchValue());
                txtToSpeech.speak(toBeSpoken, TextToSpeech.QUEUE_FLUSH, null);
                //entryText.setText("");
            }
        });

    }
    public void onPause(){
        if(txtToSpeech != null){
            txtToSpeech.stop();
            txtToSpeech.shutdown();
        }
          super.onPause();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
            Toast.makeText(getApplicationContext(),"Hello", Toast.LENGTH_SHORT ).show();
            return true;
        }
        if(id == R.id.action_about){
            //Toast.makeText(getApplicationContext(), "ሰላም ለናንተ ይሁን", Toast.LENGTH_LONG).show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            TextView aboutMe = new TextView(this);

            menu.aboutMe(builder,aboutMe);
            return true;
        }
        if (id == R.id.action_lang){
            final AlertDialog.Builder popupDialog = new AlertDialog.Builder(this);
            menu.pitch(popupDialog);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

/*////////////////////////////////////////////////////////////////
    ////   ALERT DIALOG
    ////   OPTIONAL
////////////////////////////////////////////////////////////////
    public void pitchValue( ){

        seekBar.setMax(20);
        seekBar.setKeyProgressIncrement(1);
        //ListView listView = new ListView(this);

        popupDialog.setTitle("Alter Pitch");
        //popupDialog.setView(listView);
        //listView.setCacheColorHint(5);
        //listView.
        //popupDialog.setCancelable(true);


        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        String names [] = {"Mulugeta","Miles", "Engdaw","Mulugeta","Miles", "Engdaw"};
        popupDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        popupDialog.setItems(names, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               Toast.makeText(getApplicationContext(), String.valueOf(which),Toast.LENGTH_LONG).show();

            }
        });
        popupDialog.create();
        popupDialog.show();
    }

    void aboutMe(){

        //String[] about = {"This app was developed by Mulugeta Engdaw."};
        builder.setTitle("About Developer");
        TextView aboutMe = new TextView(this);
        String aboutMulu = "My name is Mulugeta Engdaw. I am a third year college student studying Computer Engineering. I developed" +
                "this app with intention of making pronunciation of English words easier for those who are barely learning English." +
                " Also for those who know English, yet struggle in pronouncing certain words.";
        aboutMe.setText(aboutMulu);
        //aboutMe.setTextColor(11111);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               dialog.dismiss();
            }
        });
        builder.setView(aboutMe);
        builder.create();
        builder.show();


    }*/


}
