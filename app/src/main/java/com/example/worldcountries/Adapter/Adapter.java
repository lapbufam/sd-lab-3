package com.example.worldcountries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.worldcountries.Model.Pais;
import com.example.worldcountries.R;

import java.util.List;

public class Adapter extends ArrayAdapter<Pais> {
  public Adapter(Context context, List<Pais> objects) {
    super(context, 0, objects);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Pais pais = getItem(position);

    if (convertView == null) {
      convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_pais, parent, false);
    }

    TextView tvNome = convertView.findViewById(R.id.nome);
    TextView tvRegiao = convertView.findViewById(R.id.regiao);
    TextView tvSubregiao = convertView.findViewById(R.id.subregiao);
    TextView tvPopulacao = convertView.findViewById(R.id.populacao);

    tvNome.setText(pais.getName());
    tvRegiao.setText(pais.getRegion());
    tvSubregiao.setText(pais.getSubregion());
    tvPopulacao.setText("População\n" + Integer.toString(pais.getPopulation()));

    return convertView;
  }
}