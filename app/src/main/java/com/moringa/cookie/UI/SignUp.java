package com.moringa.cookie.UI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SyncStatusObserver;
import android.os.Bundle;
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

public class SignUp extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private ProgressDialog mAuthProgressDialog;
    StringBuilder errMsg = new StringBuilder("Unable to save. Please fix the following errors and try again.\n");

    @BindView (R.id.signupbutton) Button mSignupbutton;
    @BindView(R.id.name) EditText mName;
    @BindView(R.id.email) EditText mEmail;
    @BindView(R.id.password) EditText mPassword;
    @BindView(R.id.confirmPassword) EditText mConfirmPassword;
    @BindView(R.id.loginTextView)
    TextView mLoginTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);

        //Get an instance of firebase authentication..
        mAuth = FirebaseAuth.getInstance();


        createAuthStateListener();
        createAuthProgressDialog();
        mSignupbutton.setOnClickListener(this);
    }

    private void createAuthProgressDialog() {
        mAuthProgressDialog = new ProgressDialog(this);
        mAuthProgressDialog.setTitle("Loading...");
        mAuthProgressDialog.setMessage("Registering  User...");
        mAuthProgressDialog.setCancelable(false);
    }
    private  void createNewUser(){
        ///Get the details from the edit texts and use trim() to remove white spaces
        String name = mName.getText().toString().trim();
        String email = mEmail.getText().toString().trim();
        String password = mPassword.getText().toString().trim();
        String confirmPassword = mConfirmPassword.getText().toString().trim();

        //Create a User in firebase with their email and password
        mAuthProgressDialog.show();
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                mAuthProgressDialog.dismiss();
                //If task is successful, show a toast indicating sign up is successful
                if (task.isSuccessful()){
                    System.out.println("SUCCEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEESSSS");
                }
                //If task is not successful, show a toast indicating sign up is authentication failed
                else {
                Toast.makeText(SignUp.this, "Authentication failed.",
                        Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    //Notifies the user of their current authentication status and if authenticated sends them to the next activity
    private  void createAuthStateListener(){
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null){
                    Toast.makeText(SignUp.this,"REGISTRATION SUCCESSFUL ;",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this,Page1.class);
                    startActivity(intent);
                    finish();
                }
            }
        };

    }

    //add AuthStateListener to  the firebase auth
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    //remove the listener before the activity is destroyed.
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    @Override
    public void onClick(View view) {

        if (view == mLoginTextView) {
            Intent intent = new Intent(SignUp.this, Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }

        if (view == mSignupbutton){
            createNewUser();
        }

        }

    }

