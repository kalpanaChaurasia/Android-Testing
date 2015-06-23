package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;

import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.di.components.AccountsComponent;
import com.rajeshbatth.android_testing.model.User;
import com.rajeshbatth.android_testing.model.http.AuthResponse;
import com.rajeshbatth.android_testing.model.http.UserRequestParams;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

    @Inject
    AccountsApi mAccountsApi;

    @Inject
    AccountsManager mAccountsManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.inject(this);
        AccountsComponent.Injector.getAccountsComponent(this).inject(this);
        setSupportActionBar(mToolbar);
        setTitle(R.string.sign_in);
    }


    @OnClick(R.id.sign_up)
    void launchSignUp() {
        startActivity(new Intent(this, SignUpActivity.class));
    }

    @OnClick(R.id.sign_in_button)
    void signIn() {
        final UserRequestParams params = validate();
        if (params != null) {
            mAccountsApi.login(params, new Callback<AuthResponse>() {
                @Override
                public void success(AuthResponse authResponse, Response response) {
                    onUserAuthenticated(params);
                }

                @Override
                public void failure(RetrofitError error) {
                    onUserAuthenticated(params);
//                    Toast.makeText(SignInActivity.this, "Auth failure", Toast.LENGTH_SHORT)
// .show();
                }
            });

        }
    }

    private void onUserAuthenticated(UserRequestParams params) {
        User currentUser = new User();
        currentUser.setEmail(params.getEmail());
        currentUser.setName(params.getName());
        mAccountsManager.onUserLoggedIn(currentUser);
        finish();
        startActivity(new Intent(SignInActivity.this, HomeActivity.class));
    }


    private UserRequestParams validate() {
        UserRequestParams params = new UserRequestParams();

        String email = mEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailLayout.setError(getString(R.string.error_empty_email));
            mEmail.requestFocus();
            return null;
        }
       /* if (Misc.validateEmail(email)) {
            mEmailLayout.setError("Email address is invalid");
            mEmail.requestFocus();
            return false;
        }*/
        mEmailLayout.setError(null);
        params.setEmail(email);

        String password = mPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordLayout.setError(getString(R.string.error_empty_password));
            mPassword.requestFocus();
            return null;
        }

        if (password.length() < 6) {
            mPasswordLayout.setError(getString(R.string.error_password_min_len));
            mPassword.requestFocus();
            return null;
        }

        mPasswordLayout.setError(null);
        params.setPassword(password);
        return params;
    }


}
