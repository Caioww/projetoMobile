package com.example.gferreir.projectcleaner;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    EditText edtNome,edtSenha,edtConfirmarSenha;
    Button btnCadastrar,btnCancelar;

    DBHelperReg db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cadastro_activity);

        db = new DBHelperReg(this);

        edtNome=(EditText) findViewById(R.id.edtNome);
        edtSenha =(EditText) findViewById(R.id.edtSenha);
        edtConfirmarSenha=(EditText) findViewById(R.id.edtConfirmSenha);

        btnCadastrar = (Button) findViewById(R.id.btnCadastrarConf);
        btnCancelar = (Button) findViewById(R.id.btnCancelar);


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtNome.getText().toString();
                String senha = edtSenha.getText().toString();
                String confirmar = edtConfirmarSenha.getText().toString();

                if (username.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "Nome não inserido, tente novamente", Toast.LENGTH_SHORT).show();

                } else if (senha.equals("") || confirmar.equals("")) {
                    Toast.makeText(RegistrarActivity.this, "Senha vazia, tente novamente", Toast.LENGTH_SHORT).show();
                } else if (!senha.equals(confirmar)) {
                    Toast.makeText(RegistrarActivity.this, "As senhas não correspondem,tente novamente", Toast.LENGTH_SHORT).show();
                } else {
                    long res = db.CriarUtilizador(username, senha);
                    if (res > 0) {
                        Toast.makeText(RegistrarActivity.this, "Cadastrado com sucesso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistrarActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegistrarActivity.this, "Cadastro invalido tente novamente", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrarActivity.this, MainActivity.class));
            }
        });
    }
}
