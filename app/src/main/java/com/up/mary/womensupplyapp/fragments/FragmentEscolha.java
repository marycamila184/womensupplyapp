package com.up.mary.womensupplyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.facebook.FacebookSdk;
import com.facebook.login.widget.LoginButton;
import com.firebase.client.Firebase;
import com.up.mary.womensupplyapp.R;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentEscolha extends Fragment{

    private LinearLayout linearLayout;
    private View rootView;
    private Button empreendedor;
    private Button fornecedor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_escolha, container, false);

        empreendedor = (Button) rootView.findViewById(R.id.empreendedor);
        empreendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentEmpreendedor empreendedorFragment = new FragmentEmpreendedor();
                fragmentTransaction.add(R.id.frame, empreendedorFragment);
                fragmentTransaction.commit();
            }
        });

        fornecedor = (Button) rootView.findViewById(R.id.fornecedor);
        empreendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentFornecedor fragmentFornecedor = new FragmentFornecedor();
                fragmentTransaction.add(R.id.frame, fragmentFornecedor);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}


