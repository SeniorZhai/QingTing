package com.taobao.newxp.net;

import android.text.TextUtils;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.download.d;
import com.taobao.newxp.common.b.b;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class i extends d
{
  public i(String paramString)
  {
    super(paramString);
  }

  public static String a(String paramString1, String paramString2, String paramString3)
  {
    Object localObject = paramString1;
    if (!TextUtils.isEmpty(paramString1))
    {
      localObject = paramString1;
      if (!TextUtils.isEmpty(paramString3))
      {
        int i = paramString1.indexOf(paramString2 + "=");
        localObject = paramString1;
        if (i != -1)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append(paramString1.substring(0, i)).append(paramString2 + "=").append(paramString3);
          i = paramString1.indexOf("&", i);
          if (i != -1)
            ((StringBuilder)localObject).append(paramString1.substring(i));
          localObject = ((StringBuilder)localObject).toString();
        }
      }
    }
    return localObject;
  }

  public String f()
  {
    String str1 = super.f();
    for (Object localObject1 = str1; ; localObject1 = localException1)
      try
      {
        Object localObject2 = b.a();
        localObject1 = str1;
        Object localObject3 = localObject2.split(" ")[0];
        localObject1 = str1;
        localObject2 = localObject2.split(" ")[1];
        localObject1 = str1;
        str1 = a(str1, "date", (String)localObject3);
        localObject1 = str1;
        str1 = a(str1, "time", (String)localObject2);
        localObject1 = str1;
        str1 = a(str1, "ts", "" + System.currentTimeMillis());
        localObject1 = str1;
        localObject3 = a();
        if (localObject3 != null)
        {
          localObject1 = str1;
          boolean bool = localObject3.getClass().isArray();
          if (bool)
            try
            {
              localObject1 = (Long[])localObject3;
              long l1 = localObject1[0].longValue();
              long l2 = localObject1[1].longValue();
              long l3 = localObject1[2].longValue();
              localObject1 = new HashMap();
              ((Map)localObject1).put("dsize", String.valueOf(l1));
              ((Map)localObject1).put("dtime", localObject2);
              float f = 0.0F;
              if (l2 > 0L)
                f = (float)l1 / (float)l2;
              ((Map)localObject1).put("dpcent", String.valueOf((int)(f * 100.0F)));
              ((Map)localObject1).put("ptimes", String.valueOf(l3));
              if (localObject1 == null)
                continue;
              localObject2 = new StringBuilder(str1);
              localObject3 = ((Map)localObject1).keySet().iterator();
              while (((Iterator)localObject3).hasNext())
              {
                String str2 = (String)((Iterator)localObject3).next();
                ((StringBuilder)localObject2).append("&" + str2 + "=" + (String)((Map)localObject1).get(str2));
              }
            }
            catch (Exception localException2)
            {
              localObject1 = str1;
              Log.e(localException2, "format extra download params failed.", new Object[0]);
            }
        }
        return str1;
        localObject1 = localException2.toString();
        return localObject1;
      }
      catch (Exception localException1)
      {
        return localObject1;
      }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.i
 * JD-Core Version:    0.6.2
 */