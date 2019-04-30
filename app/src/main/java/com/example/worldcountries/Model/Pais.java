package com.example.worldcountries.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

// Classe País
public class Pais implements Serializable {

  @SerializedName("id")
  private long id;

  @SerializedName("name")
  private String name;

  @SerializedName("region")
  private String region;

  @SerializedName("subregion")
  private String subregion;

  @SerializedName("population")
  private int population;

  @SerializedName("latlng")
  private ArrayList<Double> latlng;
  
  // Construtor da classe Pais possui os parâmetros: id, nome, region, subregion e population
  public Pais(long id, String name, String region, String subregion, int population) {

    this.id = id;
    this.name = name;
    this.region = region;
    this.subregion = subregion;
    this.population = population;

  }

  @Override
  // Retorna uma string com a descricao em coordenadas do pais
  public String toString() {
    return this.getName() + "\nLatitude: " + this.getLatlng().get(0) + "\nLongitude: " + this.getLatlng().get(1) ;
  }
  
  // Retorna o id do pais
  public long getId() {
    return id;
  }

  // Seta o id do pais
  public void setId(long id) {
    this.id = id;
  }

  // Retorna o nome do pais
  public String getName() {
    return name;
  }

  // Seta o nome do pais
  public void setName(String name) {
    this.name = name;
  }

  // Retorna  a regiao do pais
  public String getRegion() {
    return region;
  }

  // Seta a regiao do pais
  public void setRegion(String region) {
    this.region = region;
  }

  // Retorna a subregiao do pais
  public String getSubregion() {
    return subregion;
  }

  // Seta a subregiao do pais
  public void setSubregion(String subregion) {
    this.subregion = subregion;
  }

  // Retorna a populacao do pais
  public int getPopulation() {
    return population;
  }

  // Seta a populacao do pais
  public void setPopulation(int population) {
    this.population = population;
  }

  // Retorna a latituge e longitudedo pais
  public ArrayList<Double> getLatlng() {
    return latlng;
  }

  // Seta a latituge e longitude do pais
  public void setLatlng(ArrayList<Double> latlng) {
    this.latlng = latlng;
  }
}
