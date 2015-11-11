package com.ixintui.push;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.ixintui.plugin.IPushProvider;
import com.ixintui.pushsdk.a.a;

public class Provider extends ContentProvider
{
  private IPushProvider a;

  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    if (this.a == null)
      return 0;
    return this.a.delete(paramUri, paramString, paramArrayOfString);
  }

  public String getType(Uri paramUri)
  {
    if (this.a == null)
      return null;
    return getType(paramUri);
  }

  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    if (this.a == null)
      return null;
    return this.a.insert(paramUri, paramContentValues);
  }

  public boolean onCreate()
  {
    Context localContext = getContext();
    this.a = ((IPushProvider)a.a(localContext, "com.ixintui.push.PushProviderImpl"));
    if (this.a != null)
    {
      this.a.init(localContext);
      return this.a.onCreate();
    }
    return false;
  }

  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    if (this.a == null)
      return null;
    return this.a.query(paramUri, paramArrayOfString1, paramString1, paramArrayOfString2, paramString2);
  }

  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    if (this.a == null)
      return 0;
    return this.a.update(paramUri, paramContentValues, paramString, paramArrayOfString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.push.Provider
 * JD-Core Version:    0.6.2
 */