package com.example.sande.expandableview;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    ExpandableListView expandableListView;
    Button requestCoAssistance;
    Button subMitButton;
    TextView resultTextView;
    EditText preferredTech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expandableListView = (ExpandableListView) findViewById(R.id.expandableListView);

        ExpandableListViewAdapter expandableListViewAdapter = new ExpandableListViewAdapter(MainActivity.this);
        expandableListView.setAdapter(expandableListViewAdapter);

        requestCoAssistance = findViewById(R.id.requestCoAssistance);
        requestCoAssistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestCoAssistance.class);
                startActivity(intent);
            }
        });
        subMitButton = findViewById(R.id.submitButton);
        resultTextView = findViewById(R.id.resultTextView);
        preferredTech = findViewById(R.id.preferedTech);
        subMitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FeedEntry feedEntry = new FeedEntry();
                ArrayList<FeedEntry> allEntires = feedEntry.getInstances();
                Log.i(TAG, "onClick: done");
                String show = "";
                String data = "";
                for(int i = 0 ; i< allEntires.size(); i++){
                    ArrayList<String> storeData = (ArrayList)allEntires.get(i).getStoreData();

                    for(int j = 0; j<storeData.size();j++){
                        if(!storeData.get(j).equals("") && j == 0){
                            show += allEntires.get(i).getUsername()+"  : ";
                            data += allEntires.get(i).getUsername();
                        }
                        show += storeData.get(j) + " ,";
                        data += storeData.get(j);
                    }
                    if(!data.equals(""))
                    {
                        show+= "\n";
                    }
                    data = "";
                }
                if(!preferredTech.getText().toString().equals("")){
                    show += "Preferred Tech :"+preferredTech.getText().toString();
                }

                Log.i(TAG, "onClick: "+show);
                resultTextView.setText(show);
            }
        });

    }


}
