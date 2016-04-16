package com.up.mary.womensupplyapp.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.android.gms.analytics.ecommerce.Product;
import com.google.android.gms.plus.model.people.Person;
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

import java.util.ArrayList;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class AdapterProduto extends ArrayAdapter<Produto> {

    private Activity activity;
    private ArrayList<Produto> produtos;
    private static LayoutInflater inflater = null;
    private TextView preco;
    private TextView nomeProduto;

    public AdapterProduto(Activity activity, int textViewResourceId,ArrayList<Produto> produtos) {
        super(activity, textViewResourceId, produtos);
        try {
            this.activity = activity;
            this.produtos = produtos;

            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {

        }
    }

    @Override
    public Produto getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return produtos.size();
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.fragment_lista_produtos_item, null);
                preco = (TextView) vi.findViewById(R.id.textPreco);
                nomeProduto = (TextView) vi.findViewById(R.id.textNomeProduto);
            }
            preco.setText(String.valueOf(produtos.get(position).getPreco()));
            nomeProduto.setText(produtos.get(position).getNomeProduto());
        } catch (Exception e) {

        }
        return vi;
    }
}
