package com.moringa.diary.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.moringa.diary.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    StringBuilder errMsg = new StringBuilder("Unable to save. Please fix the following errors and try again.\n");

    @BindView (R.id.signupbutton) Button mSignupbutton;
    @BindView(R.id.name) EditText mName;
    @BindView(R.id.email) EditText mEmail;
    @BindView(R.id.age) EditText mAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);
        ButterKnife.bind(this);

        mSignupbutton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if(mName == null || mEmail == null || mAge == null){
            errMsg.append("- Please fill in all spaces.\n");
        }
        else {
            String name = mName.getText().toString();
            String email = mEmail.getText().toString();
            Intent intent = new Intent(SignUp.this,Login.class);
            intent.putExtra("name",name);
            intent.putExtra("email",email);
            startActivity(intent);

        }

    }
}
