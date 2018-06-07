package com.example.sande.expandableview;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ParseJSON {

    private static final String TAG = "ParseJSON";
    private ArrayList<FeedEntry> skills;

    public ParseJSON() {
        this.skills = new ArrayList<>();
    }

    public ArrayList<FeedEntry> getSkills() {
        return skills;
    }

    public boolean parse(String jsonData){
        //boolean status = true;
        FeedEntry currentRecord = null;
        //boolean inEntry = false;
        //String textValue = "";
        try{
            JSONArray initArray = new JSONArray(jsonData);
            for(int i = 0; i < initArray.length(); i++){
                JSONObject object = initArray.getJSONObject(i);
                String username = object.getString("username");
                JSONObject address = object.getJSONObject("address");
                String street = address.getString("street");
                String suite = address.getString("suite");
                String city = address.getString("city");
                currentRecord = new FeedEntry(username,street,suite,city);
                Log.i(TAG, "parse: "+ currentRecord);
                /////////////////////////////////////////// do something here
                currentRecord.addToList(street);
                currentRecord.addToList(suite);
                currentRecord.addToList(city);
                skills.add(currentRecord);
            }
        }catch (JSONException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
