package com.bluerose.linkup;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class LoginActivity extends AppCompatActivity {

    private MaterialEditText email, password;
    Button btn_login;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        btn_login= findViewById(R.id.btn_login);

        auth = FirebaseAuth.getInstance();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txt_email, txt_password;
                txt_email = String.valueOf(email.getText());
                txt_password = String.valueOf(password.getText());

                if(TextUtils.isEmpty(txt_email) || TextUtils.isEmpty(txt_password))
                {
                    Toast.makeText(LoginActivity.this, "Both Fields are required !!", Toast.LENGTH_SHORT).show();
                } else {
                   auth.signInWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()) {
                               Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                               intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                               startActivity(intent);
                               finish();
                           } else {
                               Toast.makeText(LoginActivity.this, "Authentication Failed !!", Toast.LENGTH_SHORT).show();
                           }
                       }
                   });
                }
            }
        });
    }
}
