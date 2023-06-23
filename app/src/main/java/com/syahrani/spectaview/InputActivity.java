package com.syahrani.spectaview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.syahrani.spectaview.databinding.ActivityInputBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InputActivity extends AppCompatActivity {
    private ActivityInputBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInputBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama_cafe = binding.etNamaCafe.getText().toString();
                String deskripsi_cafe = binding.etDeskripsiCafe.getText().toString();
                String lokasi_cafe = binding.etLokasiCafe.getText().toString();
                String rating_cafe = binding.etRatingCafe.getText().toString();
                String jam_operasional = binding.etJamOperasional.getText().toString();
                String link_gambar = binding.etGambarCafe.getText().toString();

                boolean bolehInputNamaCafe = Util.inputError(nama_cafe,binding.etNamaCafe, "nama cafe");
                boolean bolehInputDeskripsiCafe = Util.inputError(deskripsi_cafe,binding.etDeskripsiCafe, "deskripsi cafe");
                boolean bolehInputLokasiCafe = Util.inputError(lokasi_cafe,binding.etLokasiCafe, "lokasi cafe");
                boolean bolehInputRatingCafe = Util.inputError(rating_cafe,binding.etRatingCafe, "rating cafe");
                boolean bolehInputJamOperasional = Util.inputError(jam_operasional,binding.etJamOperasional, "jam operasional");
                boolean bolehInputLinkGambar = Util.inputError(link_gambar,binding.etGambarCafe, "link gambar");

                if (bolehInputNamaCafe && bolehInputDeskripsiCafe && bolehInputLokasiCafe
                        && bolehInputRatingCafe && bolehInputJamOperasional && bolehInputJamOperasional && bolehInputLinkGambar) {
                    saveCafeToAPI(nama_cafe, deskripsi_cafe, lokasi_cafe, rating_cafe, jam_operasional, link_gambar);
                }

                }
        });
    }



    private void saveCafeToAPI(String nama_cafe, String deskripsi_cafe, String lokasi_cafe, String rating_cafe, String jam_operasional, String link_gambar) {
        binding.progressbar.setVisibility(View.VISIBLE);
        APIService api = Util.getRetrofit().create(APIService.class);
        Call<DataItem> call = api.addKonser(nama_cafe,deskripsi_cafe,lokasi_cafe, Integer.valueOf(rating_cafe), jam_operasional, link_gambar);
        call.enqueue(new Callback<DataItem>() {
            @Override
            public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                binding.progressbar.setVisibility(View.VISIBLE);
                if (response.code()==200){
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1){
                        Toast.makeText(InputActivity.this,message, Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        Toast.makeText(InputActivity.this,message, Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }else {
                    Toast.makeText(InputActivity.this,"Response" + response.code(), Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            public void onFailure(Call<DataItem> call, Throwable t) {
                binding.progressbar.setVisibility(View.VISIBLE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(InputActivity.this, "Retrofit Error : " + t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}

