package com.example.sande.expandableview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RequestCoAssistance extends AppCompatActivity{

    Button requestAssistance;
    EditText description;
    EditText duration;
    TextView resultTextView;
    EditText preferredText;
    private static final String TAG = "RequestCoAssistance";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_co_assistance);

}

    public void goToRequestAssistance(View view){
        Intent intent = new Intent(RequestCoAssistance.this,MainActivity.class);
        startActivity(intent);
    }

    public void submit(View view){
        description = (EditText) findViewById(R.id.description);
        String descriptionText = "Description :"+ description.getText().toString();
        duration = (EditText) findViewById(R.id.duration);
        String duraitonText = "Duration "+ duration.getText().toString();
        Log.i(TAG, "submit: description ="+ descriptionText+" / durationText = "+ duraitonText);
        preferredText = findViewById(R.id.preferedTech);
        //String preferred = ""+preferredText.getText().toString();
        description.setText("");
        duration.setText("");
        resultTextView = findViewById(R.id.resultTextView);
        resultTextView.setText("Your information:\n"+descriptionText+"\n"+duraitonText);
    }
}
