package com.example.sande.expandableview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class FeedAdapter extends ArrayAdapter{
    private static final String TAG = "FeedAdapter";
    private final int layoutResource;
    private final LayoutInflater layoutInflater;
    private List<FeedEntry> skills;


    public FeedAdapter(@NonNull Context context, int layoutResource, List<FeedEntry> skills) {
        super(context, layoutResource);
        this.layoutResource = layoutResource;
        this.layoutInflater = LayoutInflater.from(context);
        this.skills = skills;
    }

    @Override
    public int getCount() {
        return skills.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //return super.getView(position, convertView, parent);
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = layoutInflater.inflate(layoutResource, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        final FeedEntry current = skills.get(position);
        viewHolder.username.setText(current.getUsername());



        // Alert dialog here
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        final String[] list = {"please","god"};
//        boolean[] checkedItems = {false, false};
        final String[] list = current.getArray();
        boolean[] checkedItems = new boolean[list.length];
        builder.setTitle(current.getUsername());
        builder.setMultiChoiceItems(list, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                //Toast.makeText(getContext(),list[which], Toast.LENGTH_SHORT).show();
                if(current.checkIfStored(list[which])){
                    current.removeStoreData(list[which]);
                    Toast.makeText(getContext(),"removed "+list[which], Toast.LENGTH_SHORT).show();
                }
                else{
                    current.addStoreData(list[which]);
                    Toast.makeText(getContext(),"Added "+list[which], Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        final AlertDialog ad = builder.create();
        viewHolder.username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.show();
            }
        });



        return convertView;






    }

    private class ViewHolder{
        final TextView username;
        ViewHolder(View v){
            this.username = v.findViewById(R.id.username);
        }
    }
}
