package com.syahrani.spectaview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.syahrani.spectaview.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private CafeViewAdapter cafeViewAdapter;
    private List <Cafe> data;
    private int DataItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getAllInput();

        cafeViewAdapter = new CafeViewAdapter();
        binding.rvCafe.setLayoutManager(new LinearLayoutManager(this));
        binding.rvCafe.setAdapter(cafeViewAdapter);

        cafeViewAdapter.setOnItemLongClickListener(this::dataClick);
        cafeViewAdapter.setOnItemLongClickListener(new CafeViewAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(View v, DataItem input, int position) {
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
                popupMenu.inflate(R.menu.menu_popup);
                popupMenu.setGravity(Gravity.RIGHT);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        int idMenu = item.getItemId();
                        if (idMenu == R.id.action_edit) {
                            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
                            intent.putExtra("EXTRA DATA", input);
                            startActivity(intent);
                            return true;

                        } else if (idMenu == R.id.action_delete) {
                            if (input != null) {
                                String id = input.getId();
                                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                                builder.setTitle("Konfirmasi");
                                builder.setMessage("Yakin Ingin Menghapus Cafe" + input.getNamaCafe() + "?");
                                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        deleteInput(id);
                                    }
                                });
                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });
                                AlertDialog alertDialog = builder.create();
                                alertDialog.show();
                            } else {
                                // Tampilkan pesan atau lakukan tindakan jika input null
                            }
                            return true;
                        } else {
                            return false;
                        }
                    }
                });
                popupMenu.show();
            }
        });

        binding.fabInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, InputActivity.class);
                startActivity(intent);

            }
        });
    }

    private void dataClick(View view, DataItem item, int i) {
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("EXTRA_DATA", item);
        startActivity(intent);
    }


    private void deleteInput(String id) {
        APIService api = Util.getRetrofit().create(APIService.class);
        Call<DataItem> call = api.deleteCafe(id);
        call.enqueue(new Callback<DataItem>() {
            @Override
            public void onResponse(Call<DataItem> call, Response<DataItem> response) {
                Log.e("MainActivity", "Get Delete Response Code: " + response.code());
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        getAllInput();
                    } else {
                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<DataItem> call, Throwable t) {
                Log.e("MainActivity", "Retrofit Error : " + t.getMessage());
                Toast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getAllInput();
    }

    private void getAllInput() {
        Log.e("MainActivity", "Main GetAllInput()");
        binding.progressbar.setVisibility(View.VISIBLE);
        APIService api = Util.getRetrofit().create(APIService.class);
        Call<Cafe> call = api.getCafe();
        call.enqueue(new Callback<Cafe>() {
            @Override
            public void onResponse(Call<Cafe> call, Response<Cafe> response) {
                binding.progressbar.setVisibility(View.GONE);
                Log.e("MainActivity", "Response: " + response.code());
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = String.valueOf(response.body().getMessage());

                    if (success == 1) {
                        List<DataItem> dataCafe = response.body().getData();

                        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                        cafeViewAdapter.setData(dataCafe);
                        data = new ArrayList<>(DataItem);

                    }
                }
            }

            @Override
            public void onFailure(Call<Cafe> call, Throwable t) {
                binding.progressbar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(MainActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}













