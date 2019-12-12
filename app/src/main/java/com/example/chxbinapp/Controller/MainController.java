package com.example.chxbinapp.Controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.chxbinapp.Model.AllSport;
import com.example.chxbinapp.Model.RestSports;
import com.example.chxbinapp.Model.RestSportsApi;
import com.example.chxbinapp.View.MainActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainController {

    private MainActivity activity;

    private RestSportsApi restSportsApi;

    public MainController(MainActivity mainActivity) {

        this.activity = mainActivity;
    }

    public void onStart() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.thesportsdb.com/api/v1/json/1/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();


        final SharedPreferences Appcache = PreferenceManager.getDefaultSharedPreferences(activity);
        final SharedPreferences.Editor editor = Appcache.edit();
        restSportsApi = retrofit.create(RestSportsApi.class);




            Call<RestSports> call = restSportsApi.getListSports();
            call.enqueue(new Callback<RestSports>() {
                @Override
                public void onResponse(Call<RestSports> call,
                                       Response<RestSports> response) {
                    RestSports restPlayer = response.body();
                    List<AllSport> listPlayer = restPlayer.getsports();
//                    activity.showList(listPlayer);

                    Gson gson  = new Gson();
                    String json = gson.toJson(listPlayer);
                    editor.putString("Appcache",json);
                    editor.apply();
                    activity.showList(listPlayer);
                }

                @Override
                public void onFailure(Call<RestSports> call, Throwable t) {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
                    Gson gson =  new Gson();
                    String json = preferences.getString("Appcache",null);
                    Type type = new TypeToken<List<AllSport>>() {}.getType();
                    List<AllSport> listAllsport = gson.fromJson(json, type);
                    if(listAllsport==null){
                        System.out.println(call);
                        System.out.println(t);
                        System.out.println("onFailure response body is null");
                        return;


                    }
                    activity.showList(listAllsport);
                }
            });
        }


}

