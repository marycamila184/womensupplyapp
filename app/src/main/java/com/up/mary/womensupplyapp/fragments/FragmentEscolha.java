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
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.model.empreendedor.Empreendedor;
import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentEscolha extends Fragment{

    private LinearLayout linearLayout;
    private View rootView;
    private Button empreendedor;
    private Button fornecedor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_escolha, container, false);

        //Caso o usuario escolha empreendedor
        empreendedor = (Button) rootView.findViewById(R.id.empreendedor);
        empreendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentLogin loginFragment = new FragmentLogin();
                //Passo o tipo de usuario a ser inserido
                Bundle bundles = new Bundle();
                bundles.putSerializable("escolha", "empreendedor");
                loginFragment.setArguments(bundles);
                fragmentTransaction.replace(R.id.frame, loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        //Caso o usuario escolha fornecedor
        fornecedor = (Button) rootView.findViewById(R.id.fornecedor);
        fornecedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentLogin loginFragment = new FragmentLogin();
                //Passo o tipo de usuario a ser inserido
                Bundle bundles = new Bundle();
                bundles.putSerializable("escolha", "fornecedor");
                loginFragment.setArguments(bundles);
                fragmentTransaction.replace(R.id.frame, loginFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}


