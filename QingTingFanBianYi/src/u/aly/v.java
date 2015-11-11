package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.umeng.analytics.AnalyticsConfig;
import java.util.Arrays;
import java.util.List;

public class v
{
  private static final String a = "session_start_time";
  private static final String b = "session_end_time";
  private static final String c = "session_id";
  private static final String f = "activities";
  private final String d = "a_start_time";
  private final String e = "a_end_time";

  private String a(Context paramContext, SharedPreferences paramSharedPreferences)
  {
    k localk = k.a(paramContext);
    String str = b(paramContext);
    paramContext = a(paramContext);
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.putString("session_id", str);
    paramSharedPreferences.putLong("session_start_time", System.currentTimeMillis());
    paramSharedPreferences.putLong("session_end_time", 0L);
    paramSharedPreferences.commit();
    if (paramContext != null)
    {
      localk.a(paramContext);
      return str;
    }
    localk.a((af)null);
    return str;
  }

  private void a(SharedPreferences paramSharedPreferences)
  {
    paramSharedPreferences = paramSharedPreferences.edit();
    paramSharedPreferences.remove("session_start_time");
    paramSharedPreferences.remove("session_end_time");
    paramSharedPreferences.remove("session_id");
    paramSharedPreferences.remove("a_start_time");
    paramSharedPreferences.remove("a_end_time");
    paramSharedPreferences.putString("activities", "");
    paramSharedPreferences.commit();
  }

  private boolean b(SharedPreferences paramSharedPreferences)
  {
    long l1 = paramSharedPreferences.getLong("a_start_time", 0L);
    long l2 = paramSharedPreferences.getLong("a_end_time", 0L);
    long l3 = System.currentTimeMillis();
    if ((l1 != 0L) && (l3 - l1 < AnalyticsConfig.kContinueSessionMillis))
      bj.b("MobclickAgent", "onResume called before onPause");
    while (l3 - l2 <= AnalyticsConfig.kContinueSessionMillis)
      return false;
    return true;
  }

  public af a(Context paramContext)
  {
    SharedPreferences localSharedPreferences = u.a(paramContext);
    Object localObject = localSharedPreferences.getString("session_id", null);
    if (localObject == null)
      return null;
    long l3 = localSharedPreferences.getLong("session_start_time", 0L);
    long l4 = localSharedPreferences.getLong("session_end_time", 0L);
    long l1 = 0L;
    if (l4 != 0L)
    {
      long l2 = l4 - l3;
      l1 = l2;
      if (Math.abs(l2) > 86400000L)
        l1 = 0L;
    }
    af localaf = new af();
    localaf.a((String)localObject);
    localaf.a(l3);
    localaf.b(l4);
    localaf.c(l1);
    localObject = AnalyticsConfig.getLocation();
    if (localObject != null)
    {
      localObject = new aw(localObject[0], localObject[1], System.currentTimeMillis());
      if (!localaf.y())
        break label206;
      localaf.a((aw)localObject);
    }
    while (true)
    {
      paramContext = y.a(paramContext);
      if (paramContext != null)
        localaf.a(paramContext);
      paramContext = z.a(localSharedPreferences);
      if ((paramContext != null) && (paramContext.size() > 0))
        localaf.a(paramContext);
      a(localSharedPreferences);
      return localaf;
      label206: localaf.b(Arrays.asList(new aw[] { localObject }));
    }
  }

  public String b(Context paramContext)
  {
    String str = bi.f(paramContext);
    paramContext = AnalyticsConfig.getAppkey(paramContext);
    long l = System.currentTimeMillis();
    if (paramContext == null)
      throw new RuntimeException("Appkey is null or empty, Please check AndroidManifest.xml");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(l).append(paramContext).append(str);
    return bv.a(localStringBuilder.toString());
  }

  public void c(Context paramContext)
  {
    SharedPreferences localSharedPreferences = u.a(paramContext);
    if (localSharedPreferences == null)
      return;
    if (b(localSharedPreferences))
    {
      paramContext = a(paramContext, localSharedPreferences);
      bj.a("MobclickAgent", "Start new session: " + paramContext);
    }
    while (true)
    {
      paramContext = localSharedPreferences.edit();
      paramContext.putLong("a_start_time", System.currentTimeMillis());
      paramContext.putLong("a_end_time", 0L);
      paramContext.commit();
      return;
      paramContext = localSharedPreferences.getString("session_id", null);
      bj.a("MobclickAgent", "Extend current session: " + paramContext);
    }
  }

  public void d(Context paramContext)
  {
    paramContext = u.a(paramContext);
    if (paramContext == null)
      return;
    if ((paramContext.getLong("a_start_time", 0L) == 0L) && (AnalyticsConfig.ACTIVITY_DURATION_OPEN))
    {
      bj.b("MobclickAgent", "onPause called before onResume");
      return;
    }
    long l = System.currentTimeMillis();
    paramContext = paramContext.edit();
    paramContext.putLong("a_start_time", 0L);
    paramContext.putLong("a_end_time", l);
    paramContext.putLong("session_end_time", l);
    paramContext.commit();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.v
 * JD-Core Version:    0.6.2
 */