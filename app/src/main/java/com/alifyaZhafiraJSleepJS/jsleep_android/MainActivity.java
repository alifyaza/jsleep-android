package com.alifyaZhafiraJSleepJS.jsleep_android;

import static com.alifyaZhafiraJSleepJS.jsleep_android.Utils.getAssetJsonData;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

import com.google.gson.*;

public class MainActivity<json> extends AppCompatActivity {

    /*String data = getAssetJsonData(getApplicationContext());
    Type type = new TypeToken<class>(){}.getType();
    class properties = new Gson().fromJson(data, type);*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

}