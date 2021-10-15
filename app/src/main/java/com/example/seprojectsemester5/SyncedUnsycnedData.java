package com.example.seprojectsemester5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SyncedUnsycnedData extends AppCompatActivity {

    SQLiteDBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synced_unsycned_data);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.data);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DB = new SQLiteDBHelper(this);

        Cursor res = DB.getData();
        if(res.getCount() == 0){
            Toast.makeText(getApplicationContext(), "No Entries Found", Toast.LENGTH_SHORT).show();
        }
        else{
            ArrayList<ArrayList<String>> DATA = new ArrayList<ArrayList<String>>();
            while (res.moveToNext()){
                ArrayList<String> a = new ArrayList<>();
                a.add(res.getString(0));
                a.add(res.getString(1));
                a.add(res.getString(2));
                a.add(res.getString(3));
                a.add(res.getString(4));
                a.add(res.getString(5));
                DATA.add(a);
            }

            int N = DATA.size();
            String[] aadharNumber = new String[N];
            String[] name = new String[N];
            String[] age = new String[N];
            String[] gender = new String[N];
            String[] pin = new String[N];
            String[] phone = new String[N];

            for(int i = 0; i < N; i ++){
                aadharNumber[i] = "UID " + DATA.get(i).get(0);
                name[i] = DATA.get(i).get(1);
                age[i] = DATA.get(i).get(2);
                gender[i] = DATA.get(i).get(5);
                pin[i] = DATA.get(i).get(3);
                phone[i] = DATA.get(i).get(4);
            }

            recyclerView.setAdapter(new UnsyncedDataAdapter(aadharNumber, name, age, gender, pin, phone));

        }

    }
}