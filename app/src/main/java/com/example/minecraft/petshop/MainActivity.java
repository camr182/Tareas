package com.example.minecraft.petshop;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.minecraft.petshop.Activities.ConfigurarCuenta;
import com.example.minecraft.petshop.Activities.Contacto;
import com.example.minecraft.petshop.Adaptadores.PageAdapter;
import com.example.minecraft.petshop.model.Mascota;
import com.example.minecraft.petshop.restApi.adapter.RestApiAdapter;
import com.example.minecraft.petshop.restApi.model.EndpointsApi;
import com.example.minecraft.petshop.restApi.model.UsuarioResponse;
import com.example.minecraft.petshop.vista.fragments.Fragment_listado_mascotas;
import com.example.minecraft.petshop.vista.fragments.Fragment_perfil_mascota;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;

import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    public TextView nombrePerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar Animes

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

    }



    private ArrayList<android.support.v4.app.Fragment> agregarFragments (){

        ArrayList<android.support.v4.app.Fragment> fragments = new ArrayList<>();

        fragments.add(new Fragment_listado_mascotas());
        fragments.add(new Fragment_perfil_mascota());


        return fragments;

    }


    private void setUpViewPager (){

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setText("Lista Mascotas");
        tabLayout.getTabAt(1).setText("Perfil Mascota");

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.acerca:

                Intent i = new Intent(this,Contacto.class);
                startActivity(i);

            break;

            case R.id.contacto:

                Intent in = new Intent(this,Contacto.class);
                startActivity(in);

                break;

            case R.id.configurarCuenta:

                Intent in2 = new Intent(this,ConfigurarCuenta.class);
                startActivity(in2);

                break;

            case R.id.recibirNotificaciones:

                String id_dispositivo = FirebaseInstanceId.getInstance().getToken();

                nombrePerfil = (TextView) findViewById(R.id.tvNombrePerfil);
                String id_usuario_instagram = nombrePerfil.getText().toString();

                enviarRegistro(id_dispositivo,id_usuario_instagram);
                Toast.makeText(this,"Tu Id de dispositivo y tu usuario ya fueron registrados",Toast.LENGTH_LONG).show();
                break;


        }

        return super.onOptionsItemSelected(item);
    }


    private void  enviarRegistro(String id_dispositivo, String id_usuario_instagram){
        Log.d("id_dispositivo: ",id_dispositivo);
        Log.d("id_usuario_instagram: ",id_usuario_instagram);

        RestApiAdapter restApiAdapter = new RestApiAdapter();
        EndpointsApi endPoints = restApiAdapter.establecerConexionRestHeroku();
        retrofit2.Call<UsuarioResponse> usuarioResponseCall = endPoints.setUsuarioInstagram(id_dispositivo,id_usuario_instagram);

        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(retrofit2.Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
                UsuarioResponse usuarioResponse = response.body();
                //Log.d("TOKEN_FIREBASE",usuarioResponse.getToken());
            }

            @Override
            public void onFailure(retrofit2.Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }


}
