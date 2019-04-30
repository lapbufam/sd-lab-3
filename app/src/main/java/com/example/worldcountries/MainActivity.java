// Daniel Lopes Zordan Filho
// Leonardo Picanço
package com.example.worldcountries;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.worldcountries.Adapter.Adapter;
import com.example.worldcountries.Dao.Repositorio;
import com.example.worldcountries.Model.Pais;
import com.example.worldcountries.Util.HttpRetro;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

  private Repositorio db;
  private Adapter adapter;
  private List<Pais> listaPais;
  private ListView listView;
  private SwipeRefreshLayout swiperefresh;

  @Override
  // Instancia as listas e paises, seta cores, cria um novo repositorio e povoa ele
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    swiperefresh = findViewById((R.id.swiperefresh));
    //seta cores
    swiperefresh.setColorScheme(R.color.colorPrimary, R.color.colorAccent);
    swiperefresh.setOnRefreshListener(this);

    listView = findViewById(R.id.listView);
    listaPais = new ArrayList<Pais>();
    adapter = new Adapter(this, listaPais);

    db = new Repositorio(getBaseContext());

    getDataRetro();

    listView.setAdapter(adapter);
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      // Amplia e mostra a descrição do item clicado
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplication(), listaPais.get(position).toString(), Toast.LENGTH_LONG).show();
      }
    });
  }
  
  // Recupera os dados
  private void getDataRetro() {

    swiperefresh.setRefreshing(true);

    // se tiver conexao faz get, senao pega do sqlite
    if (isConnected()) {

      HttpRetro.getPaisClient().getPais().enqueue(new Callback<List<Pais>>() {
        // Trata a resposta 
        public void onResponse(Call<List<Pais>> call, Response<List<Pais>> response) {
          if (response.isSuccessful()) {
            List<Pais> paisBody = response.body();
            listaPais.clear();

            db.excluirAll();
            for (Pais pais : paisBody) {
              listaPais.add(pais);
              Log.d("Latlng", String.valueOf(pais.getLatlng().size()));
              db.inserir(pais);
            }
            adapter.notifyDataSetChanged();
          } else {
            System.out.println(response.errorBody());
          }
          swiperefresh.setRefreshing(false);
        }

        @Override
        // Trata o erro
        public void onFailure(Call<List<Pais>> call, Throwable t) {
          t.printStackTrace();
        }
      });
    } else {
      swiperefresh.setRefreshing(false);
      Toast.makeText(this,"Sem Conexão, listando países do banco...",Toast.LENGTH_SHORT).show();
      getDataSqlite();
    }
  }
  // Recupera os dados do banco
  private void getDataSqlite() {
    listaPais.clear();
    listaPais.addAll(db.listarPais());
    adapter.notifyDataSetChanged();
  }
  
  // Verifica conexão com o banco
  public Boolean isConnected() {
    ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

    if (cm != null) {
      NetworkInfo ni = cm.getActiveNetworkInfo();
      return ni != null && ni.isConnected();
    }
    return false;
  }

  @Override
  // Atualiza os dados
  public void onRefresh() {
    getDataRetro();
  }
}
