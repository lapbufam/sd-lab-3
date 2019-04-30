// Daniel Lopes Zordan Filho
// Leonardo Picanço
package com.example.worldcountries.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.worldcountries.Model.Pais;

import java.util.ArrayList;
import java.util.List;

// Classe repositorio
public class Repositorio {

  private SQLHelper helper;
  private SQLiteDatabase db;

  public Repositorio(Context ctx){
    helper = new SQLHelper(ctx);
  }
  // Insere no repositorio
  public long inserir(Pais pais){
    db = helper.getReadableDatabase();
    ContentValues cv = new ContentValues();
    cv.put(SQLHelper.COLUNA_NOME, pais.getName());
    cv.put(SQLHelper.COLUNA_REGIAO, pais.getRegion());
    cv.put(SQLHelper.COLUNA_SUBREGIAO, pais.getSubregion());
    cv.put(SQLHelper.COLUNA_POPULACAO, pais.getPopulation());
    cv.put(SQLHelper.COLUNA_LATITUDE, pais.getLatlng().size() != 0 ? pais.getLatlng().get(0) : 0.0);
    cv.put(SQLHelper.COLUNA_LONGITUDE, pais.getLatlng().size() != 0 ? pais.getLatlng().get(1) : 0.0);

    long id = db.insert(SQLHelper.TABELA_PAIS, null, cv);

    if(id != -1){
      pais.setId(id);
    }
    db.close();
    return id;
  }
  
  // Limpa repositorio
  public void excluirAll(){
    db = helper.getWritableDatabase();
    db.delete(SQLHelper.TABELA_PAIS, null, null);
    db.close();
  }
  
  // Seleciona no repositorio
  public List<Pais> listarPais() {
    String sql = "SELECT * FROM " + SQLHelper.TABELA_PAIS;
    db = helper.getReadableDatabase();
    Cursor cursor = db.rawQuery(sql, null);
    List<Pais> list = new ArrayList();

    while (cursor.moveToNext()) {
      long id = cursor.getLong(
              cursor.getColumnIndex(SQLHelper.COLUNA_ID)
      );
      String nome = cursor.getString(
              cursor.getColumnIndex(SQLHelper.COLUNA_NOME)
      );
      String regiao = cursor.getString(
              cursor.getColumnIndex(SQLHelper.COLUNA_REGIAO)
      );
      String subregiao = cursor.getString(
              cursor.getColumnIndex(SQLHelper.COLUNA_SUBREGIAO)
      );
      double latitude = cursor.getDouble(
              cursor.getColumnIndex(SQLHelper.COLUNA_LATITUDE)
      );
      double longitude = cursor.getDouble(
              cursor.getColumnIndex(SQLHelper.COLUNA_LONGITUDE)
      );
      int populacao = cursor.getInt(
              cursor.getColumnIndex(SQLHelper.COLUNA_POPULACAO)
      );

      Pais pais = new Pais(id, nome, regiao, subregiao, populacao, latitude, longitude);
      list.add(pais);
    }
    cursor.close();
    return list;
  }

}
