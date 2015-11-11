package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import java.lang.reflect.Method;

public class y
{
  private static final String a = "uptr";
  private static final String b = "dntr";

  public static be a(Context paramContext)
  {
    long[] arrayOfLong;
    label142: label144: 
    do
    {
      long l1;
      long l2;
      try
      {
        be localbe = new be();
        arrayOfLong = b(paramContext);
        if ((arrayOfLong[0] > 0L) && (arrayOfLong[1] > 0L))
        {
          paramContext = u.a(paramContext);
          l1 = paramContext.getLong("uptr", -1L);
          l2 = paramContext.getLong("dntr", -1L);
          paramContext.edit().putLong("uptr", arrayOfLong[1]).putLong("dntr", arrayOfLong[0]).commit();
          if (l1 <= 0L)
            break label142;
          if (l2 > 0L)
            break label144;
          break label142;
          localbe.c((int)arrayOfLong[0]);
          localbe.a((int)arrayOfLong[1]);
          return localbe;
        }
      }
      catch (Exception paramContext)
      {
        bj.e("MobclickAgent", "sdk less than 2.2 has get no traffic");
        return null;
      }
      return null;
      return null;
      arrayOfLong[0] -= l2;
      arrayOfLong[1] -= l1;
    }
    while ((arrayOfLong[0] > 0L) && (arrayOfLong[1] > 0L));
    return null;
  }

  private static long[] b(Context paramContext)
    throws Exception
  {
    Object localObject = Class.forName("android.net.TrafficStats");
    Method localMethod = ((Class)localObject).getMethod("getUidRxBytes", new Class[] { Integer.TYPE });
    localObject = ((Class)localObject).getMethod("getUidTxBytes", new Class[] { Integer.TYPE });
    int i = paramContext.getApplicationInfo().uid;
    if (i == -1)
      return null;
    return new long[] { ((Long)localMethod.invoke(null, new Object[] { Integer.valueOf(i) })).longValue(), ((Long)((Method)localObject).invoke(null, new Object[] { Integer.valueOf(i) })).longValue() };
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.y
 * JD-Core Version:    0.6.2
 */