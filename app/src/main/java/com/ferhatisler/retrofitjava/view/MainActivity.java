package com.ferhatisler.retrofitjava.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.ferhatisler.retrofitjava.adaptor.RecyclerViewAdapter;
import com.ferhatisler.retrofitjava.model.CyroptoModel;
import com.ferhatisler.retrofitjava.R;
import com.ferhatisler.retrofitjava.service.Crypto_apı;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<CyroptoModel> cyroptoModels;
    private  String BASE_URL = "https://raw.githubusercontent.com/";
    Retrofit retrofit ;
    RecyclerView recyclerView ;
    RecyclerViewAdapter recyclerViewAdapter ;

    CompositeDisposable compositeDisposable ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json

        recyclerView = findViewById(R.id.recyclerView);

        //Retrofit
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        loadData();


    }

    private void loadData(){
        Crypto_apı crypto_apı = retrofit.create(Crypto_apı.class);

        compositeDisposable = new CompositeDisposable();
        compositeDisposable.add(crypto_apı.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleResponse));

        /*

        Call<List<CyroptoModel>> call = crypto_apı.getData();

        call.enqueue(new Callback<List<CyroptoModel>>() {
            @Override
            public void onResponse(Call<List<CyroptoModel>> call, Response<List<CyroptoModel>> response) {
                if (response.isSuccessful()){
                    List<CyroptoModel> responseList = response.body();
                    cyroptoModels = new ArrayList<>(responseList);

                    //RecyclerView İşlemlri
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    recyclerViewAdapter= new RecyclerViewAdapter(cyroptoModels);
                    recyclerView.setAdapter(recyclerViewAdapter);


                         for (CyroptoModel cyroptoModel : cyroptoModels){
                        System.out.println(cyroptoModel.currency);
                           System.out.println(cyroptoModel.price);
                    }

                }
            }

            @Override
            public void onFailure(Call<List<CyroptoModel>> call, Throwable t) {
                t.printStackTrace();

            }
        });
  */

    }

    private void handleResponse(List<CyroptoModel> cyroptoModelList){
        cyroptoModels = new ArrayList<>(cyroptoModelList);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerViewAdapter= new RecyclerViewAdapter(cyroptoModels);
        recyclerView.setAdapter(recyclerViewAdapter);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        compositeDisposable.clear();
    }
}