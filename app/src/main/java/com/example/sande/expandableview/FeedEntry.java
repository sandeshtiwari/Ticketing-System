package com.example.sande.expandableview;

import java.util.ArrayList;
import java.util.List;

public class FeedEntry {

    private String username;
    private String street;
    private String suite;
    private String city;
    private ArrayList<String> showData = new ArrayList<>();
    private ArrayList<String> storeData = new ArrayList<>();

    private static ArrayList instances = new ArrayList();

    public FeedEntry(){

    }

    public ArrayList<String> getStoreData() {
        return storeData;
    }

    public FeedEntry(String username, String street, String suite, String city) {
        this.username = username;
        this.street = street;
        this.suite = suite;
        this.city = city;
        instances.add(this);
    }

    public static ArrayList getInstances(){
        return instances;
    }

    public void addToList(String item){
        showData.add(item);
    }
    public String[] getArray(){
        return showData.toArray(new String[showData.size()]);
    }

    public boolean checkIfStored(String data){
        if(storeData.contains(data)){
            return true;
        }
        return false;
    }
    public void addStoreData(String data){
        storeData.add(data);
    }
    public void removeStoreData(String data){
        storeData.remove(data);
    }



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "FeedEntry{" +
                "username='" + username + '\'' +
                ", street='" + street + '\'' +
                ", suite='" + suite + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
