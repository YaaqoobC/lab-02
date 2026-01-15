package com.example.listycity;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //Declare the Vars to reference later:
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button add_btn, del_btn, confirm_btn ;

    ConstraintLayout confirmationLayout;
    EditText add_city_text;
    String new_city;
    String remove_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        String[] cities = {
                "Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin",
                "Vienna", "Tokyo", "Beijing", "Osaka", "New Delhi"
        };

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        // Getting the city that has most recently been clicked by getting the position
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                remove_city = cityList.getItemAtPosition(position).toString();
            }
        });

        add_btn = findViewById(R.id.add_city_btn);
        confirm_btn = findViewById(R.id.confirm_btn);
        del_btn = findViewById(R.id.del_city_btn);
        confirmationLayout = findViewById(R.id.confirm_layout);
        add_city_text = findViewById(R.id.add_city_text_box);

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add new data to the list
                confirmationLayout.setVisibility(VISIBLE);
                add_city_text.requestFocus();
            }
        });

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmationLayout.setVisibility(INVISIBLE);
                new_city = String.valueOf(add_city_text.getText());

                dataList.add(new_city);
                cityAdapter.notifyDataSetChanged();
//                add_city_text.clearFocus();
                add_city_text.setText("");
            }
        });

        del_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pop the value from the list
                dataList.remove(remove_city);
                cityAdapter.notifyDataSetChanged();
                remove_city = null;
            }
        });


//        EdgeToEdge.enable(this);
//        setContentView(R.layout.activity_main);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
}