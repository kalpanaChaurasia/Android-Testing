package com.rajeshbatth.android_testing.core.auth;

import com.rajeshbatth.android_testing.account.AccountsManager;
import com.rajeshbatth.android_testing.api.AccountsApi;
import com.rajeshbatth.android_testing.model.http.AuthResponse;
import com.rajeshbatth.android_testing.model.http.UserRequestParams;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import retrofit.Callback;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by user on 6/28/2015.
 */
public class SignInPresenterTest {

  private static final String VALID_EMAIL = "rajesh@gmail.com";

  private AccountsManager accountsManagerMock;
  private AccountsApi accountsApiMock;
  private SignInPresenter signInPresenter;
  private SignInPresenter.SignInCallbacks callbacks;

  @Before
  public void setUp() throws Exception {
    accountsApiMock = Mockito.mock(AccountsApi.class);
    accountsManagerMock = Mockito.mock(AccountsManager.class);
    callbacks = Mockito.mock(SignInPresenter.SignInCallbacks.class);
    signInPresenter = new SignInPresenter(accountsManagerMock, accountsApiMock, callbacks);
  }

  @Test
  public void testValidate() throws Exception {
    when(callbacks.getEmail()).thenReturn("");
    when(callbacks.getPassword()).thenReturn("");
    signInPresenter.validate();
    verify(callbacks).hideErrors();
    verify(callbacks).showEmptyEmailError();

    when(callbacks.getEmail()).thenReturn("rajesh");
    when(callbacks.getPassword()).thenReturn("");
    signInPresenter.validate();
    verify(callbacks).showInvalidEmailError();

    when(callbacks.getEmail()).thenReturn(VALID_EMAIL);
    when(callbacks.getPassword()).thenReturn("");
    signInPresenter.validate();
    verify(callbacks).showEmptyPasswordError();

    when(callbacks.getEmail()).thenReturn(VALID_EMAIL);
    when(callbacks.getPassword()).thenReturn("12345");
    signInPresenter.validate();
    verify(callbacks).showPasswordMinError();

    when(callbacks.getEmail()).thenReturn(VALID_EMAIL);
    when(callbacks.getPassword()).thenReturn("123456");
    boolean isValid = signInPresenter.validate();
    assertTrue(isValid);
  }

  @Test
  public void testSubmit() throws Exception {
    when(callbacks.getEmail()).thenReturn(VALID_EMAIL);
    when(callbacks.getPassword()).thenReturn("123456");
    signInPresenter.submit();

    ArgumentCaptor<UserRequestParams> arg1 = ArgumentCaptor.forClass(UserRequestParams.class);
    Class<Callback<AuthResponse>> respClass =
        (Class<Callback<AuthResponse>>) (Class) Callback.class;
    ArgumentCaptor<Callback<AuthResponse>> argument2 = ArgumentCaptor.forClass(respClass);
    verify(accountsApiMock).login(arg1.capture(), argument2.capture());

    UserRequestParams reqParam = arg1.getValue();
    assertEquals(reqParam.getEmail(), VALID_EMAIL);

    Callback<AuthResponse> respCallback = argument2.getValue();
    respCallback.failure(null);
    verify(callbacks).launchHome();

    respCallback.success(null, null);
    verify(callbacks, atLeastOnce()).launchHome();
  }
}