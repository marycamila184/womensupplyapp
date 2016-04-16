package com.up.mary.womensupplyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.firebase.client.Firebase;
import com.google.android.gms.plus.model.people.Person;
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.adapters.AdapterProduto;
import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

import java.util.ArrayList;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentListProduto  extends Fragment{

    private LinearLayout linearLayout;
    private View rootView;
    private Fornecedor usuario;
    private Button cadastrar;
    private Button avancar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_list_produtos, container, false);

        Bundle bundle = getArguments();
        usuario = (Fornecedor)bundle.getSerializable("fornecedor");

        cadastrar = (Button) rootView.findViewById(R.id.addProduto);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentAddProduto addFragment = new FragmentAddProduto();
                //Passo o tipo de usuario a ser inserido
                Bundle bundles = new Bundle();
                bundles.putSerializable("fornecedor", usuario);
                addFragment.setArguments(bundles);

                fragmentTransaction.replace(R.id.frame, addFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        avancar = (Button) rootView.findViewById(R.id.listaBusca);
        avancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Chamando o firebase
                Firebase mFirebaseRef = new Firebase(getResources().getString(R.string.firebase_url));
                mFirebaseRef.child("fornecedores").push().setValue(usuario);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentListaBusca lista = new FragmentListaBusca();
                fragmentTransaction.replace(R.id.frame, lista);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        ListView listView = (ListView) rootView.findViewById(R.id.lista);

        AdapterProduto produtosAdapter;
        ArrayList<Produto> listaProdutos  = new ArrayList<Produto>();

        produtosAdapter= new AdapterProduto (getActivity(), 0, listaProdutos);
        listView.setAdapter(produtosAdapter);
        return rootView;
    }
}
