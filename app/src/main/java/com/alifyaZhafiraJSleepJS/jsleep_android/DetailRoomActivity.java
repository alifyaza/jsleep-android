package com.alifyaZhafiraJSleepJS.jsleep_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.Room;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

/* The activity is responsible for displaying
details about a hotel room in a layout
 */
public class DetailRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;

    /*The details about the hotel room
    are obtained from a Room object*/

    Room ClickRoom = MainActivity.displayRoom.get(MainActivity.index);
    TextView NameHotel,priceRoom, addressRoom, sizeRoom, bedType;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_room);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        /* set to the corresponding
        views in the layout*/

        mApiService = UtilsApi.getApiService();
        NameHotel = findViewById(R.id.NameOutput);
        NameHotel.setText(ClickRoom.name);
        priceRoom = findViewById(R.id.PriceOutput);
        priceRoom.setText(String.valueOf((ClickRoom.price.price)));
        addressRoom = findViewById(R.id.AddressOutput);
        addressRoom.setText(ClickRoom.address);
        sizeRoom = findViewById(R.id.SizeOutput);
        sizeRoom.setText(String.valueOf(ClickRoom.size));
        bedType = findViewById(R.id.BedTypeOutput);
        bedType.setText(ClickRoom.bedType.toString());

    }
    /*public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_button:
                Intent move = new Intent (DetailRoomActivity.this, MainActivity.class);
                Toast.makeText(this, "Opening Home", Toast.LENGTH_SHORT).show();
                startActivity(move);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }*/
}