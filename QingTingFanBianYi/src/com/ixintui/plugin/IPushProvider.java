package com.ixintui.plugin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public abstract interface IPushProvider
{
  public abstract int delete(Uri paramUri, String paramString, String[] paramArrayOfString);

  public abstract String getType(Uri paramUri);

  public abstract void init(Context paramContext);

  public abstract Uri insert(Uri paramUri, ContentValues paramContentValues);

  public abstract boolean onCreate();

  public abstract Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2);

  public abstract int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.plugin.IPushProvider
 * JD-Core Version:    0.6.2
 */