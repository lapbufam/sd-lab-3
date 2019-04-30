package com.example.worldcountries.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLHelper extends SQLiteOpenHelper {

  private static final String NOME_BANCO = "dbPais";
  private static final int VERSAO_BANCO = 1;
  public static final String TABELA_PAIS = "pais_tabela";
  public static final String COLUNA_ID = "_id";
  public static final String COLUNA_NOME = "nome";
  public static final String COLUNA_REGIAO = "endereco";
  public static final String COLUNA_SUBREGIAO = "bairro";
  public static final String COLUNA_POPULACAO = "latitude";
  public static final String COLUNA_LONGITUDE = "longitude";
  public static final String COLUNA_LATITUDE = "servicos";


  public SQLHelper(Context context) {
    super(context, NOME_BANCO, null, VERSAO_BANCO);
  }

  public void onCreate(SQLiteDatabase sqLiteDatabase) {
    sqLiteDatabase.execSQL(
            "CREATE TABLE " + TABELA_PAIS + " ( " +
                    COLUNA_ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT," +
                    COLUNA_NOME + " TEXT, " +
                    COLUNA_REGIAO + " TEXT, " +
                    COLUNA_SUBREGIAO + " TEXT, " +
                    COLUNA_LATITUDE + " TEXT, " +
                    COLUNA_LONGITUDE + " TEXT, " +
                    COLUNA_POPULACAO + " INTEGER)"
    );

  }

  public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
    // para as próximas versões
  }

}
