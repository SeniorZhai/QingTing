package com.tencent.open;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.tencent.connect.auth.QQToken;
import com.tencent.tauth.IUiListener;
import com.tencent.utils.Util;

public class SocialApi
{
  private SocialApiIml a;

  public SocialApi(Context paramContext, QQToken paramQQToken)
  {
    this.a = new SocialApiIml(paramContext, paramQQToken);
  }

  public void ask(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.ask(paramActivity, paramBundle, paramIUiListener);
  }

  public void brag(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.brag(paramActivity, paramBundle, paramIUiListener);
  }

  public void challenge(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.challenge(paramActivity, paramBundle, paramIUiListener);
  }

  public boolean checkVoiceApi(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    paramBundle.putString("version", Util.getAppVersion(paramActivity));
    this.a.grade(paramActivity, paramBundle, paramIUiListener);
    return true;
  }

  public void gift(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.gift(paramActivity, paramBundle, paramIUiListener);
  }

  public void grade(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    paramBundle.putString("version", Util.getAppVersion(paramActivity));
    this.a.grade(paramActivity, paramBundle, paramIUiListener);
  }

  public void invite(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.invite(paramActivity, paramBundle, paramIUiListener);
  }

  public void reactive(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.reactive(paramActivity, paramBundle, paramIUiListener);
  }

  public void story(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    this.a.story(paramActivity, paramBundle, paramIUiListener);
  }

  public void voice(Activity paramActivity, Bundle paramBundle, IUiListener paramIUiListener)
  {
    paramBundle.putString("version", Util.getAppVersion(paramActivity));
    this.a.voice(paramActivity, paramBundle, paramIUiListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.open.SocialApi
 * JD-Core Version:    0.6.2
 */