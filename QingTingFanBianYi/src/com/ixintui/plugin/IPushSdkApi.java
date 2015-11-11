package com.ixintui.plugin;

import android.content.Context;
import java.util.List;

public abstract interface IPushSdkApi
{
  public abstract void addTags(Context paramContext, List paramList);

  public abstract void bindAlias(Context paramContext, String paramString);

  public abstract void configure(Context paramContext, String paramString);

  public abstract void deleteTags(Context paramContext, List paramList);

  public abstract void enableStat(Context paramContext, boolean paramBoolean);

  public abstract void isSuspended(Context paramContext);

  public abstract boolean isWrapperCompatible(Context paramContext, int paramInt);

  public abstract void listTags(Context paramContext);

  public abstract void register(Context paramContext, int paramInt, String paramString1, String paramString2);

  public abstract void resumePush(Context paramContext);

  public abstract void suspendPush(Context paramContext);

  public abstract void unbindAlias(Context paramContext, String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ixintui.plugin.IPushSdkApi
 * JD-Core Version:    0.6.2
 */