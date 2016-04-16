package com.up.mary.womensupplyapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentAddProduto extends Fragment{

    private LinearLayout linearLayout;
    private View rootView;
    private Button cadastrar;
    private EditText preco;
    private EditText nomeProduto;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cadastro_produto, container, false);

        cadastrar = (Button) rootView.findViewById(R.id.addProduto);
        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                preco = (EditText) rootView.findViewById(R.id.preco_produto);
                nomeProduto = (EditText) rootView.findViewById(R.id.nome_produto);

                Bundle bundle = getArguments();
                Fornecedor fornecedor = (Fornecedor) bundle.getSerializable("fornecedor");

                Produto produto=new Produto();
                produto.setNomeProduto(nomeProduto.getText().toString());
                produto.setPreco(Float.parseFloat(preco.getText().toString()));

                fornecedor.getListaProdutos().add(produto);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentListProduto lista = new FragmentListProduto();

                //Passo o usuario
                Bundle bundles = new Bundle();
                bundles.putSerializable("fornecedor", fornecedor);
                lista.setArguments(bundles);

                fragmentTransaction.replace(R.id.frame, lista);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });


        return rootView;
    }
}
