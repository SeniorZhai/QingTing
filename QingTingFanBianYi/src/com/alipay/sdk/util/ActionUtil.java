package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.sys.GlobalContext;
import com.alipay.sdk.tid.TidInfo;

public class ActionUtil
{
  public static String[] a(String paramString)
  {
    int i = paramString.indexOf('(') + 1;
    int j = paramString.lastIndexOf(')');
    if ((i == 0) || (j == -1))
      return null;
    paramString = paramString.substring(i, j).split(",");
    if (paramString != null)
    {
      i = 0;
      while (i < paramString.length)
      {
        if (!TextUtils.isEmpty(paramString[i]))
        {
          paramString[i] = paramString[i].trim();
          paramString[i] = paramString[i].replaceAll("'", "").replaceAll("\"", "");
        }
        i += 1;
      }
    }
    return paramString;
  }

  public static void b(String paramString)
  {
    paramString = a(paramString);
    if (paramString.length != 3);
    Context localContext;
    TidInfo localTidInfo;
    do
    {
      do
        return;
      while (!TextUtils.equals("tid", paramString[0]));
      localContext = GlobalContext.a().b();
      localTidInfo = TidInfo.c();
    }
    while ((TextUtils.isEmpty(paramString[1])) || (TextUtils.isEmpty(paramString[2])));
    localTidInfo.a(paramString[1]);
    localTidInfo.b(paramString[2]);
    localTidInfo.a(localContext);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.util.ActionUtil
 * JD-Core Version:    0.6.2
 */