package com.syahrani.spectaview;

import android.text.TextUtils;
import android.widget.EditText;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Util {
    public static final String PREFERENCE_FILE_KEY = Util.class.getPackage().getName();
    private static final String BASE_URL = " ";
    public static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static boolean inputError(String value, EditText et, String namafield){
        boolean bolehInput;
        if (TextUtils.isEmpty(value)){
            bolehInput = false;
            et.setError(namafield+"Tidak Boleh Kosong!");
        }else {
            bolehInput = true;
        }
        return bolehInput;
    }
}

