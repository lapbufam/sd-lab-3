package com.example.worldcountries.Model;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

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

  public Pais(long id, String name, String region, String subregion, int population) {

    this.id = id;
    this.name = name;
    this.region = region;
    this.subregion = subregion;
    this.population = population;

  }

  @Override
  public String toString() {
    return this.getName() + "\nLatitude: " + this.getLatlng().get(0) + "\nLongitude: " + this.getLatlng().get(1) ;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRegion() {
    return region;
  }

  public void setRegion(String region) {
    this.region = region;
  }

  public String getSubregion() {
    return subregion;
  }

  public void setSubregion(String subregion) {
    this.subregion = subregion;
  }

  public int getPopulation() {
    return population;
  }

  public void setPopulation(int population) {
    this.population = population;
  }

  public ArrayList<Double> getLatlng() {
    return latlng;
  }

  public void setLatlng(ArrayList<Double> latlng) {
    this.latlng = latlng;
  }
}