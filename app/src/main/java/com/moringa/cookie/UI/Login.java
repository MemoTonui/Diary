package com.moringa.cookie.UI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.moringa.cookie.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @BindView(R.id.loginbutton)
    Button mLoginbutton;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.email2)
    EditText mEmail2;
    @BindView(R.id.invalid)
    TextView mInvalid;
    @BindView(R.id.registerTextView) TextView mRegisterTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        mLoginbutton.setOnClickListener(this);
        mRegisterTextView.setOnClickListener(this);
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    Intent intent = new Intent(Login.this,Page1.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };
    }

    @Override
    public void onClick(View view) {
         if(view == mRegisterTextView){
             Intent intent = new Intent(Login.this,SignUp.class);
             startActivity(intent);
             finish();
         }
         if(view == mLoginbutton){
            loginWithPassword();
         }

        }


        private void loginWithPassword(){
        String email = mEmail2.getText().toString().trim();
        String password = mPassword.getText().toString().trim();

            if (email.equals("")) {
                mEmail2.setError("Please enter your email");
                return;
            }
            if (password.equals("")){
                mPassword.setError("Please enter a password ");
                return;
            }

            mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Login.this, "Hi  !! You have successfully Logged in", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(Login.this, "Login Failed..Please enter correct details.", Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthStateListener != null) {
            mAuth.removeAuthStateListener(mAuthStateListener);
        }
    }


    }


