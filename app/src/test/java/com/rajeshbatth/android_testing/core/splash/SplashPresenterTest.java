package com.rajeshbatth.android_testing.core.splash;

import com.rajeshbatth.android_testing.account.AccountsManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

/**
 * Author: Rajesh Batth
 * Date: 21-Jun-2015.
 */
public class SplashPresenterTest {

  private SplashPresenter splashPresenter;
  private AccountsManager accountsManagerMock;
  private SplashPresenter.SplashCallBacks splashCallBacksMock;

  @Before
  public void setUp() {
    accountsManagerMock = Mockito.mock(AccountsManager.class);
    splashCallBacksMock = Mockito.mock(SplashPresenter.SplashCallBacks.class);
    splashPresenter = new SplashPresenter(accountsManagerMock, splashCallBacksMock);
  }

  @Test
  public void testLaunchHome() {
    assert splashPresenter != null;
    Mockito.when(accountsManagerMock.isUserLoggedIn()).thenReturn(true);
    splashPresenter.launchNext();
    Mockito.verify(splashCallBacksMock).launchHome();
  }

  @Test
  public void testLaunchAuth() {
    assert splashPresenter != null;
    Mockito.when(accountsManagerMock.isUserLoggedIn()).thenReturn(false);
    splashPresenter.launchNext();
    Mockito.verify(splashCallBacksMock).launchAuth();
  }
}
