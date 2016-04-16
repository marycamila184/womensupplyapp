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

import com.firebase.client.Firebase;
import com.up.mary.womensupplyapp.R;
import com.up.mary.womensupplyapp.model.empreendedor.Empreendedor;
import com.up.mary.womensupplyapp.model.fornecedor.Fornecedor;
import com.up.mary.womensupplyapp.model.fornecedor.Produto;

import java.util.ArrayList;

/**
 * Created by Avell B155 MAX on 16/04/2016.
 */
public class FragmentFornecedor  extends Fragment{

    private LinearLayout linearLayout;
    private View rootView;

    private Button cadastro;
    private EditText nomeResponsavel;
    private EditText emailResponsavel;
    private EditText nomeEmpresa;
    private EditText ramo;
    private EditText cnpj;
    private EditText endereco;
    private EditText bairro;
    private EditText cidade;
    private EditText telefone;
    private EditText email;

    private Fornecedor usuario = new Fornecedor();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_cadastro, container, false);
        Bundle bundle = getArguments();
        usuario = (Fornecedor)bundle.getSerializable("fornecedor");

        nomeResponsavel = (EditText) rootView.findViewById(R.id.nome_responsavel);
        nomeResponsavel.setText(usuario.getNomeResponsavel());

        emailResponsavel = (EditText) rootView.findViewById(R.id.email_responsavel);
        emailResponsavel.setText(usuario.getEmailResponsavel());

        cadastro = (Button) rootView.findViewById(R.id.cadastro);
        cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nomeEmpresa = (EditText) rootView.findViewById(R.id.nome);
                ramo= (EditText) rootView.findViewById(R.id.ramo);
                cnpj= (EditText) rootView.findViewById(R.id.cnpj);
                endereco= (EditText) rootView.findViewById(R.id.endereco);
                bairro= (EditText) rootView.findViewById(R.id.bairro);
                cidade= (EditText) rootView.findViewById(R.id.cidade);
                telefone= (EditText) rootView.findViewById(R.id.telefone);
                email= (EditText) rootView.findViewById(R.id.email);

                usuario.setNomeEmpresa(nomeEmpresa.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setRamoEmpresa(ramo.getText().toString());
                usuario.setCnpj(cnpj.getText().toString());
                usuario.setEndereco(endereco.getText().toString());
                usuario.setBairro(bairro.getText().toString());
                usuario.setCidade(cidade.getText().toString());
                usuario.setTelefone(telefone.getText().toString());
                usuario.setRanking(5);

                usuario.setListaProdutos(new ArrayList<Produto>());

                //Indo para a tela de produtos
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                FragmentListProduto lista = new FragmentListProduto();

                Bundle bundles = new Bundle();
                bundles.putSerializable("fornecedor", usuario);
                lista.setArguments(bundles);

                fragmentTransaction.replace(R.id.frame, lista);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return rootView;
    }
}