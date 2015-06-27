package com.rajeshbatth.android_testing.core.auth;

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
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.core.home.HomeActivity;

public class SignUpActivity extends AppCompatActivity {

  @InjectView(R.id.toolbar)
  Toolbar toolbar;

  @InjectView(R.id.username)
  AppCompatEditText username;

  @InjectView(R.id.username_layout)
  TextInputLayout usernameLayout;

  @InjectView(R.id.email)
  AppCompatEditText email;

  @InjectView(R.id.email_layout)
  TextInputLayout emailLayout;

  @InjectView(R.id.password)
  AppCompatEditText password;

  @InjectView(R.id.password_layout)
  TextInputLayout passwordLayout;

  @InjectView(R.id.confirm_password)
  AppCompatEditText confirmPassword;

  @InjectView(R.id.confirm_password_layout)
  TextInputLayout confirmPasswordLayout;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_up);
    ButterKnife.inject(this);
    setSupportActionBar(toolbar);
    setTitle("Sign Up");
  }

  @OnClick(R.id.submit_button)
  void onSubmitClicked() {
    boolean isValid = validate();
    if (isValid) {
      finish();
      startActivity(new Intent(this, HomeActivity.class));
    }
  }

  private boolean validate() {
    if (TextUtils.isEmpty(username.getText().toString())) {
      usernameLayout.setError("Username is empty");
      username.requestFocus();
      return false;
    }
    usernameLayout.setError(null);

    String email = this.email.getText().toString();
    if (TextUtils.isEmpty(email)) {
      emailLayout.setError("Email is empty");
      this.email.requestFocus();
      return false;
    }
    /*if (Misc.validateEmail(email)) {
      emailLayout.setError("Email address is invalid");
      email.requestFocus();
      return false;
    }*/
    emailLayout.setError(null);

    String password = this.password.getText().toString();
    if (TextUtils.isEmpty(password)) {
      passwordLayout.setError("Password is empty");
      this.password.requestFocus();
      return false;
    }

    if (password.length() < SignInActivity.PASSWORD_MIN_LENGTH) {
      passwordLayout.setError("Password should be of minimum 6 characters");
      this.password.requestFocus();
      return false;
    }
    passwordLayout.setError(null);

    String confirmPassword = this.confirmPassword.getText().toString();
    if (!password.equals(confirmPassword)) {
      confirmPasswordLayout.setError("Password does not match");
      this.confirmPassword.requestFocus();
      return false;
    }
    confirmPasswordLayout.setError(null);

    return true;
  }
}
