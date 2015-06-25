package com.rajeshbatth.android_testing.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.Toast;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.di.components.AccountsComponent;
import com.rajeshbatth.android_testing.model.User;
import com.rajeshbatth.android_testing.model.http.AuthResponse;
import com.rajeshbatth.android_testing.model.http.UserRequestParams;
import javax.inject.Inject;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SignInActivity extends AppCompatActivity {

  @InjectView(R.id.toolbar) Toolbar toolbar;

  @InjectView(R.id.email) AppCompatEditText email;

  @InjectView(R.id.email_layout) TextInputLayout emailLayout;

  @InjectView(R.id.password) AppCompatEditText password;

  @InjectView(R.id.password_layout) TextInputLayout passwordLayout;

  @Inject AccountsApi accountsApi;

  @Inject AccountsManager accountsManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    ButterKnife.inject(this);
    AccountsComponent.Holder.getAccountsComponent(this).inject(this);
    setSupportActionBar(toolbar);
    setTitle(R.string.sign_in);
  }

  @OnClick(R.id.sign_up) void launchSignUp() {
    startActivity(new Intent(this, SignUpActivity.class));
  }

  @OnClick(R.id.sign_in_button) void signIn() {
    final UserRequestParams params = validate();
    if (params != null) {
      accountsApi.login(params, new Callback<AuthResponse>() {
        @Override public void success(AuthResponse authResponse, Response response) {
          onUserAuthenticated(params);
        }

        @Override public void failure(RetrofitError error) {
          Toast.makeText(SignInActivity.this, "Auth failure", Toast.LENGTH_SHORT).show();
          onUserAuthenticated(params);
        }
      });
    }
  }

  private void onUserAuthenticated(UserRequestParams params) {
    User currentUser = new User();
    currentUser.setEmail(params.getEmail());
    currentUser.setName(params.getName());
    accountsManager.onUserLoggedIn(currentUser);
    finish();
    startActivity(new Intent(SignInActivity.this, HomeActivity.class));
  }

  private UserRequestParams validate() {
    UserRequestParams params = new UserRequestParams();

    String email = this.email.getText().toString();
    if (TextUtils.isEmpty(email)) {
      emailLayout.setError(getString(R.string.error_empty_email));
      this.email.requestFocus();
      return null;
    }
       /* if (Misc.validateEmail(email)) {
            emailLayout.setError("Email address is invalid");
            email.requestFocus();
            return false;
        }*/
    emailLayout.setError(null);
    params.setEmail(email);

    String password = this.password.getText().toString();
    if (TextUtils.isEmpty(password)) {
      passwordLayout.setError(getString(R.string.error_empty_password));
      this.password.requestFocus();
      return null;
    }

    if (password.length() < 6) {
      passwordLayout.setError(getString(R.string.error_password_min_len));
      this.password.requestFocus();
      return null;
    }

    passwordLayout.setError(null);
    params.setPassword(password);
    return params;
  }
}
