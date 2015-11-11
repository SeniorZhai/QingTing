package com.handmark.pulltorefresh.library.internal;

import android.util.Log;

public class Utils
{
  static final String LOG_TAG = "PullToRefresh";

  public static void warnDeprecation(String paramString1, String paramString2)
  {
    Log.w("PullToRefresh", "You're using the deprecated " + paramString1 + " attr, please switch over to " + paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.handmark.pulltorefresh.library.internal.Utils
 * JD-Core Version:    0.6.2
 */