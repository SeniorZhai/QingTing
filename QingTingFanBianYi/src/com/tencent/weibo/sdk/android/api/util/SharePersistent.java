package com.tencent.weibo.sdk.android.api.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.tencent.weibo.sdk.android.model.AccountModel;

public class SharePersistent
{
  private static final String FILE_NAME = "ANDROID_SDK";
  private static SharePersistent instance;

  public static SharePersistent getInstance()
  {
    if (instance == null)
      instance = new SharePersistent();
    return instance;
  }

  public boolean clear(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("ANDROID_SDK", 0).edit().clear().commit();
  }

  public String get(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("ANDROID_SDK", 0).getString(paramString, "");
  }

  public AccountModel getAccount(Context paramContext)
  {
    AccountModel localAccountModel = new AccountModel();
    paramContext = paramContext.getSharedPreferences("ANDROID_SDK", 0);
    localAccountModel.setAccessToken(paramContext.getString("ACCESS_TOKEN", ""));
    localAccountModel.setExpiresIn(paramContext.getLong("EXPIRES_IN", 0L));
    localAccountModel.setOpenID(paramContext.getString("OPEN_ID", ""));
    localAccountModel.setOpenKey(paramContext.getString("OPEN_KEY", ""));
    localAccountModel.setRefreshToken(paramContext.getString("REFRESH_TOKEN", ""));
    localAccountModel.setName(paramContext.getString("NAME", ""));
    localAccountModel.setNike(paramContext.getString("NICK", ""));
    return localAccountModel;
  }

  public long getLong(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences("ANDROID_SDK", 0).getLong(paramString, 0L);
  }

  public boolean put(Context paramContext, String paramString, long paramLong)
  {
    paramContext = paramContext.getSharedPreferences("ANDROID_SDK", 0).edit();
    paramContext.putLong(paramString, paramLong);
    return paramContext.commit();
  }

  public boolean put(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = paramContext.getSharedPreferences("ANDROID_SDK", 0).edit();
    paramContext.putString(paramString1, paramString2);
    return paramContext.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.SharePersistent
 * JD-Core Version:    0.6.2
 */