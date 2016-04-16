package com.up.mary.womensupplyapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.model.empreendedor.Empreendedor;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

import java.util.ArrayList;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class AdapterBuscaEmpreendedores extends ArrayAdapter<Empreendedor> {

    private Activity activity;
    private ArrayList<Empreendedor> empreendedores;
    private static LayoutInflater inflater = null;
    private TextView nomeEmpresa;
    private TextView ramo;

    public AdapterBuscaEmpreendedores(Activity activity, int textViewResourceId, ArrayList<Empreendedor> empreendedores) {
        super(activity, textViewResourceId, empreendedores);
        try {
            this.activity = activity;
            this.empreendedores = empreendedores;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    @Override
    public Empreendedor getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return empreendedores.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.fragment_lista_item, null);

                //Colocar o firebase
//                preco = (TextView) vi.findViewById(R.id.textPreco);
//                nomeProduto = (TextView) vi.findViewById(R.id.textNomeProduto);
            }
//            preco.setText(String.valueOf(produtos.get(position).getPreco()));
//            nomeProduto.setText(produtos.get(position).getNomeProduto());
        } catch (Exception e) {

        }
        return vi;
    }
}
