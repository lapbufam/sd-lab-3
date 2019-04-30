// Daniel Lopes Zordan Filho
// Leonardo Picanço
package com.example.worldcountries.Util;

import com.example.worldcountries.Model.Pais;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

// Classe HttpRetro
public class HttpRetro {
  private static final String BASE_URL = "https://restcountries.eu/";

  //Inicializa Retrofit
  public static PaisInterface getPaisClient() {
    Retrofit restAdapter = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    return restAdapter.create(PaisInterface.class);
  }

  // Interface com métodos de requisicao
  public interface PaisInterface {
    @GET("rest/v1/all")
    Call<List<Pais>> getPais();
  }
}
