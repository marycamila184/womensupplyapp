package com.up.mary.womensupplyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.android.gms.plus.model.people.Person;
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.adapters.AdapterProduto;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

import java.util.ArrayList;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentListaBusca extends Fragment {

    private LinearLayout linearLayout;
    private View rootView;
    private Button cadastrar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list, container, false);

        ListView listView = (ListView) rootView.findViewById(R.id.lista);

        AdapterProduto produtosAdapter;
        ArrayList<Produto> listaProdutos  = new ArrayList<Produto>();

        produtosAdapter= new AdapterProduto (getActivity(), 0, listaProdutos);
        listView.setAdapter(produtosAdapter);
        return rootView;
    }
}
