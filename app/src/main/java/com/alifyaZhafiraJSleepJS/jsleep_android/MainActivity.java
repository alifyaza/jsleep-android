package com.alifyaZhafiraJSleepJS.jsleep_android;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Account;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Room;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Room;


public class MainActivity extends AppCompatActivity {

    public static Account loginacc;
    public static Room roomCookies;
    public static int roomIndex;

    BaseApiService mApiService;
    Context mContext;
    //    static List<Room> allRooms = new ArrayList<Room>();
    public static List<Room> rooms;
    List<Room> roomActivity;
    public static int selectedPos;

    EditText inputList;
    Button buttonNext, buttonPrev, buttonGo;
    ListView list;
    int page = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        list = (ListView) findViewById(R.id.ListViewId);
        /*InputStream filepath = null;
        ArrayList<Room> ListRoom = new ArrayList<>();
        ArrayList<String> listId = new ArrayList<>();
        Gson gson = new Gson();

        try {
            filepath = getAssets().open("randomRoomList.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(filepath));
            Room[] temp = gson.fromJson(reader, Room[].class);
            Collections.addAll(ListRoom, temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Room r : ListRoom ) {
            listId.add(r.name);
        }
        ArrayAdapter<String> roomArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listId);
        ListView listView = findViewById(R.id.ListViewId);
        listView.setAdapter(roomArrayAdapter);*/
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent aboutMe = new Intent (MainActivity.this, AboutMeActivity.class);
        switch (item.getItemId()){
            case R.id.person_button:
                Toast.makeText(this, "Opening Profile", Toast.LENGTH_SHORT).show();
                startActivity(aboutMe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        /*if (MainActivity.loginacc.renter == null) {
            menu.findItem(R.id.create_room).setVisible(false);
        }else{
            menu.findItem(R.id.create_room).setVisible(true);
        }*/
        return true;
    }
}
