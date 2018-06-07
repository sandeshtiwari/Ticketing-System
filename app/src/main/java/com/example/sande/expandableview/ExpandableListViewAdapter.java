package com.example.sande.expandableview;

import android.app.ActionBar;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class ExpandableListViewAdapter extends BaseExpandableListAdapter {


    private static final String TAG = "ExpandableListViewAdapt";

    ArrayList<String> list;
    FeedAdapter feedAdapter;
    ListView listView;
    String title = "Skills to select";

    Context context;

    public ExpandableListViewAdapter(Context context)
    {
        this.context = context;
    }
    @Override
    public int getGroupCount() {
        return 1;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return title;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listView;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {

        TextView txtView = new TextView(context);
        txtView.setText(title);
        txtView.setPadding(100,0,0,0);
        //txtView.setTextColor(Color.BLUE);
        txtView.setTextSize(25);
        return txtView;


    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View view, ViewGroup parent) {


        list = new ArrayList<>();
        listView = new ListView(context);
        DownloadData downloadData = new DownloadData();
        downloadData.execute("https://jsonplaceholder.typicode.com/users");

        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.height = 600;
        listView.setLayoutParams(params);
        return listView;

    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }


    private class DownloadData extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            ParseJSON parseJSON = new ParseJSON();
            parseJSON.parse(s);
            //Log.i(TAG, "onPostExecute: data is "+s);

            feedAdapter = new FeedAdapter(context, R.layout.list_record,parseJSON.getSkills());
            listView.setAdapter(feedAdapter);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.i(TAG, "doInBackground: Starts with "+ strings[0]);
            String data = downloadJSON(strings[0]);
            if(data == null){
                Log.e(TAG, "doInBackground: Failed to download");
            }
            return data;
        }

        private String downloadJSON(String urlPath){
            StringBuilder jsonResult = new StringBuilder();
            try{
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                while(data != -1){
                    char current = (char) data;
                    jsonResult.append(current);
                    data = reader.read();
                }
                reader.close();
                return jsonResult.toString();
            }catch(MalformedURLException e){
                Log.e(TAG, "downloadJSON: Invalid URL"+ e.getMessage());
            }catch (IOException e){
                Log.e(TAG, "downloadJSON: IO exceptions reading data"+ e.getMessage());
            }catch (SecurityException e){
                Log.e(TAG, "downloadJSON: Security Exception. Needs permission?" + e.getMessage());
            }
            return null;
        }
    }






}


























