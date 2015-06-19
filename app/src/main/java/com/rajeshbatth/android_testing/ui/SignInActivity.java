package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.rajeshbatth.android_testing.R;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignInActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.email)
    AppCompatEditText mEmail;

    @InjectView(R.id.email_layout)
    TextInputLayout mEmailLayout;

    @InjectView(R.id.password)
    AppCompatEditText mPassword;

    @InjectView(R.id.password_layout)
    TextInputLayout mPasswordLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        setTitle("Sign In");
    }

    @OnClick(R.id.sign_in_button)
    void signIn() {
        if (validate()) {
            finish();
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    private boolean validate() {

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailLayout.setError(getString(R.string.error_empty_email));
            mEmail.requestFocus();
            return false;
        }
       /* if (Misc.validateEmail(email)) {
            mEmailLayout.setError("Email address is invalid");
            mEmail.requestFocus();
            return false;
        }*/
        mEmailLayout.setError(null);

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordLayout.setError(getString(R.string.error_empty_password));
            mPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            mPasswordLayout.setError(getString(R.string.error_password_min_len));
            mPassword.requestFocus();
            return false;
        }
        mPasswordLayout.setError(null);
        return true;
    }


    @OnClick(R.id.sign_up)
    void launchSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

}
