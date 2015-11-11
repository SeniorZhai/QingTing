package com.tencent.mm.sdk.c;

import android.net.Uri;
import android.provider.BaseColumns;

public final class a
{
  public static final class a
  {
    public static Object a(int paramInt, String paramString)
    {
      String str = paramString;
      switch (paramInt)
      {
      default:
      case 1:
      case 2:
      case 4:
      case 5:
      case 6:
        try
        {
          com.tencent.mm.sdk.b.a.a("MicroMsg.SDK.PluginProvider.Resolver", "unknown type");
          break label82;
          return Integer.valueOf(paramString);
          return Long.valueOf(paramString);
          return Boolean.valueOf(paramString);
          return Float.valueOf(paramString);
          paramString = Double.valueOf(paramString);
          return paramString;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          label82: str = null;
        }
      case 3:
      }
      return str;
    }
  }

  public static final class b
    implements BaseColumns
  {
    public static final Uri CONTENT_URI = Uri.parse("content://com.tencent.mm.sdk.plugin.provider/sharedpref");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.mm.sdk.c.a
 * JD-Core Version:    0.6.2
 */