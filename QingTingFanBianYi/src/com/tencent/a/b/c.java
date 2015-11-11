package com.tencent.a.b;

import android.net.wifi.ScanResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public final class c
{
  private static c a;
  private long b = 0L;
  private List<a> c = new ArrayList();
  private List<b> d = new ArrayList();
  private String e;

  public static c a()
  {
    if (a == null)
      a = new c();
    return a;
  }

  private static boolean a(StringBuffer paramStringBuffer)
  {
    boolean bool = false;
    try
    {
      double d1 = new JSONObject(paramStringBuffer.toString()).getJSONObject("location").getDouble("accuracy");
      if (d1 < 5000.0D)
        bool = true;
      return bool;
    }
    catch (Exception paramStringBuffer)
    {
    }
    return false;
  }

  private boolean a(List<ScanResult> paramList)
  {
    if (paramList == null);
    label115: 
    do
    {
      return false;
      int k;
      if (this.d != null)
      {
        int j = 0;
        i = 0;
        k = i;
        if (j < this.d.size())
        {
          String str = ((b)this.d.get(j)).a;
          int m = 0;
          while (true)
          {
            k = i;
            if (str != null)
            {
              k = i;
              if (m < paramList.size())
              {
                if (!str.equals(((ScanResult)paramList.get(m)).BSSID))
                  break label115;
                k = i + 1;
              }
            }
            j += 1;
            i = k;
            break;
            m += 1;
          }
        }
      }
      else
      {
        k = 0;
      }
      int i = paramList.size();
      if ((i >= 6) && (k >= i / 2 + 1))
        return true;
      if ((i < 6) && (k >= 2))
        return true;
    }
    while ((this.d.size() > 2) || (paramList.size() > 2) || (Math.abs(System.currentTimeMillis() - this.b) > 30000L));
    return true;
  }

  public final void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<ScanResult> paramList)
  {
    this.b = System.currentTimeMillis();
    this.e = null;
    this.c.clear();
    Object localObject = new a((byte)0);
    ((a)localObject).a = paramInt1;
    ((a)localObject).b = paramInt2;
    ((a)localObject).c = paramInt3;
    ((a)localObject).d = paramInt4;
    this.c.add(localObject);
    if (paramList != null)
    {
      this.d.clear();
      paramInt1 = 0;
      while (paramInt1 < paramList.size())
      {
        localObject = new b((byte)0);
        ((b)localObject).a = ((ScanResult)paramList.get(paramInt1)).BSSID;
        paramInt2 = ((ScanResult)paramList.get(paramInt1)).level;
        this.d.add(localObject);
        paramInt1 += 1;
      }
    }
  }

  public final void a(String paramString)
  {
    this.e = paramString;
  }

  public final String b(int paramInt1, int paramInt2, int paramInt3, int paramInt4, List<ScanResult> paramList)
  {
    if ((this.e == null) || (this.e.length() < 10));
    label241: label255: 
    do
    {
      return null;
      String str = this.e;
      Object localObject;
      if ((str == null) || (paramList == null))
        localObject = null;
      while (true)
      {
        this.e = ((String)localObject);
        if (this.e == null)
          break;
        if ((this.c == null) || (this.c.size() <= 0))
          break label255;
        localObject = (a)this.c.get(0);
        if ((((a)localObject).a != paramInt1) || (((a)localObject).b != paramInt2) || (((a)localObject).c != paramInt3) || (((a)localObject).d != paramInt4))
          break;
        if (((this.d != null) && (this.d.size() != 0)) || ((paramList != null) && (paramList.size() != 0)))
          break label241;
        return this.e;
        long l = Math.abs(System.currentTimeMillis() - this.b);
        if (((l <= 30000L) || (paramList.size() <= 2)) && ((l <= 45000L) || (paramList.size() > 2)))
        {
          localObject = str;
          if (a(new StringBuffer(str)));
        }
        else
        {
          localObject = null;
        }
      }
      if (a(paramList))
        return this.e;
    }
    while (!a(paramList));
    return this.e;
  }

  public final void b()
  {
    this.e = null;
  }

  static final class a
  {
    public int a = -1;
    public int b = -1;
    public int c = -1;
    public int d = -1;
  }

  static final class b
  {
    public String a = null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.a.b.c
 * JD-Core Version:    0.6.2
 */