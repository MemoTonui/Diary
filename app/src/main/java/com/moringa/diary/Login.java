package com.moringa.diary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.signupbutton) Button mSignupbutton;
    @BindView(R.id.name2) EditText mName2;
    @BindView(R.id.email2) EditText mEmail2;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);





    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");

        if( name != mName2.getText().toString() || email != mEmail2.getText().toString()){

        }
        else{
            Intent intent1 = new Intent(Login.this,Page1.class);
           startActivity(intent);
        }

    }
}

