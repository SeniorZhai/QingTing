package u.aly;

import android.content.Context;
import android.content.SharedPreferences;

public class u
{
  private static final String a = "umeng_general_config";

  public static SharedPreferences a(Context paramContext)
  {
    return paramContext.getSharedPreferences("umeng_general_config", 0);
  }

  public static SharedPreferences a(Context paramContext, String paramString)
  {
    return paramContext.getSharedPreferences(paramString, 0);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.u
 * JD-Core Version:    0.6.2
 */