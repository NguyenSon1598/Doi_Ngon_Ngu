package com.example.thu4ngay18;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button btnchanglang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loadLocale();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));
        btnchanglang = (Button)findViewById(R.id.btnchangelang);
        btnchanglang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLangguageDialog();
            }
        });

    }
    private void showChangeLangguageDialog(){
        final String[] listitems = {"English","French","Vietnames"};
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("Choose Language...");
        builder.setSingleChoiceItems(listitems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case 0:{
                        setLocale("en");
                        recreate();
                        break;
                    }
                    case 1:{
                        setLocale("fr");
                        recreate();
                        break;
                    }
                    case 2:{
                        setLocale("vi");
                        recreate();
                        break;
                    }
                    default:dialogInterface.dismiss();
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void setLocale(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Setting",MODE_PRIVATE).edit();
        editor.putString("My_lang",lang);
        editor.apply();
    }
    public void loadLocale(){
        SharedPreferences pref = getSharedPreferences("Setting", Activity.MODE_PRIVATE);
        String language = pref.getString("My)lang","");
        setLocale(language);
    }
}
