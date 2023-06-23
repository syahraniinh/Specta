package com.syahrani.spectaview;

import android.provider.ContactsContract;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService {

    @GET("get")
    Call<Cafe> getCafe();

    @FormUrlEncoded
    @POST("add")
    Call<DataItem>
    addKonser(
            @Field("nama_cafe") String namaCafe,
            @Field("deskripsi_cafe") String deskripsiCafe,
            @Field("lokasi_cafe") String lokasiCafe,
            @Field("rating_cafe") Integer ratingCafe,
            @Field("jam_operasional") String jamOperasional,
            @Field("link_gambar") String linkGambar

    );

    @FormUrlEncoded
    @PUT("update/{id}")
    Call<DataItem>
    updateKonser(
            @Path("id") String id,
            @Field("nama_cafe") String namaCafe,
            @Field("deskripsi_cafe") String deskripsiCafe,
            @Field("lokasi_cafe") String lokasiCafe,
            @Field("rating_cafe") Integer ratingCafe,
            @Field("jam_operasional") String jamOperasional,
            @Field("link_gambar") String linkGambar
    );

    @DELETE("delete/{id}")
    Call<DataItem>
    deleteKonser(
            @Path("id") String id
    );


    Call<DataItem> deleteCafe(String id);
}

