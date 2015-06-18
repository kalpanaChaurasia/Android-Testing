package com.rajeshbatth.android_testing;

import com.rajeshbatth.android_testing.utils.Misc;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    @InjectView(R.id.username)
    AppCompatEditText mUsername;

    @InjectView(R.id.username_layout)
    TextInputLayout mUsernameLayout;

    @InjectView(R.id.email)
    AppCompatEditText mEmail;

    @InjectView(R.id.email_layout)
    TextInputLayout mEmailLayout;

    @InjectView(R.id.password)
    AppCompatEditText mPassword;

    @InjectView(R.id.password_layout)
    TextInputLayout mPasswordLayout;

    @InjectView(R.id.confirm_password)
    AppCompatEditText mConfirmPassword;

    @InjectView(R.id.confirm_password_layout)
    TextInputLayout mConfirmPasswordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.inject(this);
        setSupportActionBar(mToolbar);
        setTitle("Sign Up");
    }

    @OnClick(R.id.submit_button)
    void onSubmitClicked() {
        boolean isValid = validate();
        if (isValid) {
            startActivity(new Intent(this, HomeActivity.class));
        }
    }

    private boolean validate() {
        if (TextUtils.isEmpty(mUsername.getText().toString())) {
            mUsernameLayout.setError("Username is empty");
            mUsername.requestFocus();
            return false;
        }
        mUsernameLayout.setError(null);

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailLayout.setError("Email is empty");
            mEmail.requestFocus();
            return false;
        }
        /*if (Misc.validateEmail(email)) {
            mEmailLayout.setError("Email address is invalid");
            mEmail.requestFocus();
            return false;
        }*/
        mEmailLayout.setError(null);

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordLayout.setError("Password is empty");
            mPassword.requestFocus();
            return false;
        }

        if (password.length() < 6) {
            mPasswordLayout.setError("Password should be of minimum 6 characters");
            mPassword.requestFocus();
            return false;
        }
        mPasswordLayout.setError(null);

        String confirmPassword = mConfirmPassword.getText().toString();
        if (!password.equals(confirmPassword)) {
            mConfirmPasswordLayout.setError("Password does not match");
            mConfirmPassword.requestFocus();
            return false;
        }
        mConfirmPasswordLayout.setError(null);

        return true;
    }


}
