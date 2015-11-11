package u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;

public class e extends a
{
  private static final String a = "imei";
  private Context b;

  public e(Context paramContext)
  {
    super("imei");
    this.b = paramContext;
  }

  public String f()
  {
    Object localObject = (TelephonyManager)this.b.getSystemService("phone");
    if (localObject == null);
    try
    {
      if (bi.a(this.b, "android.permission.READ_PHONE_STATE"))
      {
        localObject = ((TelephonyManager)localObject).getDeviceId();
        return localObject;
      }
    }
    catch (Exception localException)
    {
      return null;
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.e
 * JD-Core Version:    0.6.2
 */