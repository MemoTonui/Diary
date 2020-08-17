package com.moringa.diary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.diary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Login extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.loginbutton)
    Button mLoginbutton;
    @BindView(R.id.name2)
    EditText mName2;
    @BindView(R.id.email2)
    EditText mEmail2;
    @BindView(R.id.invalid)
    TextView mInvalid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        ButterKnife.bind(this);

        mLoginbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String email = intent.getStringExtra("email");
        String name2=mName2.getText().toString();
        String email2 =mEmail2.getText().toString();

       /* if (name != name2 || email != email2) {
            mInvalid.setText("Invalid!! Please enter your correct name and email address!!");


        } else {*/

            Intent intent1 = new Intent(Login.this, Page1.class);
            Toast.makeText(Login.this, "Hi " + name2 + " !! You have successfully Logged in", Toast.LENGTH_LONG).show();
            startActivity(intent1);
        }


    }


