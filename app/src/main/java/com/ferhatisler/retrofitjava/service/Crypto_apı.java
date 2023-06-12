package com.ferhatisler.retrofitjava.service;

import com.ferhatisler.retrofitjava.model.CyroptoModel;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.http.GET;

public interface Crypto_apı {
    //https://raw.githubusercontent.com/atilsamancioglu/K21-JSONDataSet/master/crypto.json


    @GET("atilsamancioglu/K21-JSONDataSet/master/crypto.json")
    Observable<List<CyroptoModel>> getData();



    //Call<List<CyroptoModel>> getData();


}
