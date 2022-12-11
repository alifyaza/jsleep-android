package com.alifyaZhafiraJSleepJS.jsleep_android;

import static com.alifyaZhafiraJSleepJS.jsleep_android.R.id.nameRoom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.alifyaZhafiraJSleepJS.jsleep_android.model.BedType;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.City;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Facility;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Price;
import com.alifyaZhafiraJSleepJS.jsleep_android.model.Room;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.BaseApiService;
import com.alifyaZhafiraJSleepJS.jsleep_android.request.UtilsApi;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateRoomActivity extends AppCompatActivity {
    BaseApiService mApiService;
    Context mContext;
    EditText nameRoom, priceRoom, addressRoom, sizeRoom;
    Spinner SpinnerBedType, SpinnerCity;
    Button Create;
    CheckBox Ac, Wifi, Bathtub, Balcony, fitness, pool, restaurant, refrigerator;
    BedType bedType;
    Price price;
    City city;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_room);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        nameRoom = findViewById(R.id.nameRoom);
        priceRoom = findViewById(R.id.priceRoom);
        addressRoom = findViewById(R.id.addressRoom);
        sizeRoom = findViewById(R.id.sizeRoom);

        SpinnerBedType = (Spinner) findViewById(R.id.SpinnerBed);
        SpinnerCity = (Spinner) findViewById(R.id.SpinnerCity);

        Ac = findViewById(R.id.CheckAC);
        Wifi = findViewById(R.id.CheckWiFi);
        Bathtub = findViewById(R.id.CheckBathTub);
        Balcony = findViewById(R.id.CheckBalcony);
        fitness = findViewById(R.id.CheckFitness);
        pool = findViewById(R.id.CheckSwimming);
        restaurant = findViewById(R.id.CheckRestaurant);
        refrigerator = findViewById(R.id.CheckRefrigirator);

        SpinnerBedType.setAdapter(new ArrayAdapter<BedType>(this, android.R.layout.simple_spinner_item, BedType.values()));
        SpinnerCity.setAdapter(new ArrayAdapter<City>(this, android.R.layout.simple_spinner_item, City.values()));

        Create = findViewById(R.id.CreateRoomButton);

        Create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("onclick success");
                ArrayList<Facility> facilities = new ArrayList<>();
                if (Ac.isChecked()) {
                    facilities.add(Facility.AC);
                }
                if (Wifi.isChecked()) {
                    facilities.add(Facility.WiFi);
                }
                if (Bathtub.isChecked()) {
                    facilities.add(Facility.Bathtub);
                }
                if (Balcony.isChecked()) {
                    facilities.add(Facility.Balcony);
                }
                if (fitness.isChecked()) {
                    facilities.add(Facility.FitnessCenter);
                }
                if (pool.isChecked()) {
                    facilities.add(Facility.SwimmingPool);
                }
                if (restaurant.isChecked()) {
                    facilities.add(Facility.Restaurant);
                }
                if (refrigerator.isChecked()) {
                    facilities.add(Facility.Refrigerator);
                }
                String bed = SpinnerBedType.getSelectedItem().toString();
                String cityStr = SpinnerCity.getSelectedItem().toString();

                bedType = BedType.valueOf(bed);
                city = City.valueOf(cityStr);

                Integer priceObj = new Integer(priceRoom.getText().toString());
                Integer sizeObj = new Integer(sizeRoom.getText().toString());

                int IntSize = priceObj.parseInt(sizeRoom.getText().toString());
                int IntPrice = sizeObj.parseInt(priceRoom.getText().toString());

                System.out.println("request before");

                requestRoom(MainActivity.loginacc.id, nameRoom.getText().toString(), IntSize, IntPrice, facilities, city, addressRoom.getText().toString(), bedType);
            }
        });
    }

    /*public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.home_button:
                Intent aboutMe = new Intent (CreateRoomActivity.this, MainActivity.class);
                Toast.makeText(this, "Opening Home", Toast.LENGTH_SHORT).show();
                startActivity(aboutMe);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }*/

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    protected Room requestRoom(int id, String name, int size, int price, ArrayList<Facility> facility, City city, String address, BedType bedType) {
        //System.out.println(facility);
        mApiService.createRoom(id, name, size, price, facility, city, address, bedType).enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(mContext, "Berhasil buat room", Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(CreateRoomActivity.this, MainActivity.class);
                    startActivity(move);
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Toast.makeText(mContext, "Fail To Create Room", Toast.LENGTH_SHORT).show();
            }
        });
        return null;
    }
}
