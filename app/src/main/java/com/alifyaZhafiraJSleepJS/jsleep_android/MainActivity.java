package com.alifyaZhafiraJSleepJS.jsleep_android;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Alifya
 * @created 11-Dec-2022 11:32 PM
 *
 */

/* A main screen of the app,
which displays a list of rooms
*/

public class MainActivity extends AppCompatActivity {

    public static Account loginacc;
    static ArrayList<Room> roomList = new ArrayList<Room>();
    public static int index ;
    public static int roomIndex;

    BaseApiService mApiService;
    static BaseApiService mApiServiceStatic;
    Context mContext;

    EditText inputList;
    Button buttonNext, buttonPrev, buttonGo;
    ListView listView;
    int page = 0;
    List<String> nameStr;
    List<Room> acc ;
    List<Room> temp;
    public static List<Room> displayRoom;
    int currentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();*/

        setContentView(R.layout.activity_main);
        mApiService = UtilsApi.getApiService();
        mContext = this;
        listView = findViewById(R.id.ListViewId);
        listView.setOnItemClickListener(this::onItemClick);
        mApiService = UtilsApi.getApiService();
        acc = requestRoom(0,10);
        /*InputStream filepath = null;
        ArrayList<Room> ListRoom = new ArrayList<>();
        ArrayList<String> listId = new ArrayList<>();
        Gson gson = new Gson();

        try {
            filepath = getAssets().open("room.json");
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
        listView.setAdapter(roomArrayAdapter);*/
        buttonNext = findViewById(R.id.next_button);
        buttonPrev = findViewById(R.id.prev_button);

        //to see next page of room list
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(temp.size()>currentPage){
                    currentPage=1;
                    return;
                }
                currentPage++;
                try {
                    acc = requestRoom(currentPage-1, 1);  //return null
                    Toast.makeText(mContext, "page "+currentPage, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        //to see previous page of room list
        buttonPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentPage<=1){
                    currentPage=1;
                    Toast.makeText(mContext, "this is the first page", Toast.LENGTH_SHORT).show();
                    return;
                }
                currentPage--;
                try {
                    acc = requestRoom(currentPage-1, 1);  //return null
                    Toast.makeText(mContext, "page "+currentPage, Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.person_button:
                Intent aboutMe = new Intent (MainActivity.this, AboutMeActivity.class);
                Toast.makeText(this, "Opening Profile", Toast.LENGTH_SHORT).show();
                startActivity(aboutMe);
                return true;
            case R.id.add_button:
                Intent createRoom = new Intent (MainActivity.this, CreateRoomActivity.class);
                Toast.makeText(this, "Create Room Opened", Toast.LENGTH_SHORT).show();
                startActivity(createRoom);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu, menu);
        return true;
    }

    protected List<Room> requestRoom(int page, int pageSize) {
        //System.out.println(pageSize);
        mApiService.getAllRoom(page, pageSize).enqueue(new Callback<List<Room>>() {
            @Override
            public void onResponse(Call<List<Room>> call, Response<List<Room>> response) {
                if (response.isSuccessful()) {
                    temp = response.body();
                    nameStr = getName(temp);
                    System.out.println("name extracted"+temp.toString());
                    ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1,nameStr);
                    listView = (ListView) findViewById(R.id.ListViewId);
                    listView.setAdapter(itemAdapter);
                    Toast.makeText(mContext, "getRoom success", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Room>> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(mContext, "get room failed", Toast.LENGTH_SHORT).show();
            }

        });
        return null;
    }

    public static ArrayList<String> getName(List<Room> list) {
        ArrayList<String> ret = new ArrayList<String>();
        int i;
        for (i = 0; i < list.size(); i++) {
            ret.add(list.get(i).name);
        }
        return ret;
    }
    public void onItemClick (AdapterView<?> l, View v, int position, long id){
        Log.i("ListView", "You clicked Item no : " + id + " at position:" + position);
        // Then you start a new Activity via Intent
        Intent intent = new Intent();
        index = position;
        intent.setClass(this, DetailRoomActivity.class);
        //DetailRoomActivity.tempRoom = temp.get(position);
        intent.putExtra("position", position);
        // Or / And
        intent.putExtra("id", id);
        startActivity(intent);
    }
}
