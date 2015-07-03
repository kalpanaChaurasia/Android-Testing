package com.rajeshbatth.android_testing.core.auth;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import com.rajeshbatth.android_testing.BuildConfig;
import com.rajeshbatth.android_testing.R;
import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.core.BaseActivity;
import com.rajeshbatth.android_testing.core.home.HomeActivity;
import javax.inject.Inject;
import timber.log.Timber;

public class SignInActivity extends BaseActivity implements SignInPresenter.SignInCallbacks {

  public static final int PASSWORD_MIN_LENGTH = 6;

  @InjectView(R.id.toolbar)
  Toolbar toolbar;

  @InjectView(R.id.email)
  AppCompatEditText email;

  @InjectView(R.id.email_layout)
  TextInputLayout emailLayout;

  @InjectView(R.id.password)
  AppCompatEditText password;

  @InjectView(R.id.password_layout)
  TextInputLayout passwordLayout;

  @Inject
  AccountsApi accountsApi;

  @Inject
  AccountsManager accountsManager;

  private SignInPresenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    ButterKnife.inject(this);
    AccountsComponent.Holder.getAccountsComponent(this).inject(this);
    presenter = new SignInPresenter(accountsManager, accountsApi, this);
    setSupportActionBar(toolbar);
    setTitle(R.string.sign_in);
    Timber.d("Git SHA: %s", BuildConfig.GIT_SHA);
  }

  @OnClick(R.id.sign_in_button)
  void signIn() {
    presenter.submit();
  }

  @OnClick(R.id.sign_up)
  void launchSignUp() {
    startActivity(new Intent(this, SignUpActivity.class));
  }

  @Override
  public void launchHome() {
    finish();
    startActivity(new Intent(SignInActivity.this, HomeActivity.class));
  }

  @Override
  public String getEmail() {
    return email.getText().toString();
  }

  @Override
  public String getPassword() {
    return password.getText().toString();
  }

  @Override
  public void hideErrors() {
    passwordLayout.setError(null);
    emailLayout.setError(null);
  }

  @Override
  public void showEmptyEmailError() {
    emailLayout.setError(getString(R.string.error_empty_email));
    this.email.requestFocus();
  }

  @Override
  public void showInvalidEmailError() {
    emailLayout.setError(getString(R.string.error_invalid_email));
    this.email.requestFocus();
  }

  @Override
  public void showEmptyPasswordError() {
    passwordLayout.setError(getString(R.string.error_empty_password));
    this.password.requestFocus();
  }

  @Override
  public void showPasswordMinError() {
    passwordLayout.setError(getString(R.string.error_password_min_len));
    this.password.requestFocus();
  }
}
