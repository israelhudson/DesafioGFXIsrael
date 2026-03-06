package com.example.desafiogfxisrael.network;

import com.example.desafiogfxisrael.model.ProductModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("products")
    Call<List<ProductModel>> getProducts();
}
