package com.example.root.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import api.ApiService;
import api.model.EvenList;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private static final String KEY="5afb893431a35665e4128301ef2f2028";
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.text);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call=apiService.contributorsBySimpleGetCall(KEY,"1/1");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try{
                    JsonObject jsonObject = new JsonParser().parse(response.body().string()).getAsJsonObject();

                    JsonArray jsonArray = jsonObject.getAsJsonArray("result");

                    Gson gson=new Gson();
                    ArrayList<EvenList> evenLists=new ArrayList<EvenList>();
                    for(JsonElement evenlist : jsonArray){
                        EvenList evenList=gson.fromJson(evenlist,EvenList.class);
                        evenLists.add(evenList);
                    }
                    textView.setText(response.body().string());

                }catch (Exception e){}
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
