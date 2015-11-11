package fm.qingting.utils;

import android.content.Context;
import android.telephony.TelephonyManager;

public class OperatorInfo
{
  public static final int CHINAMOBILE = 1;
  public static final int CHINATELECOM = 3;
  public static final int CHINAUNICOM = 2;
  public static final int OTHER = 4;
  private static int mOperator = -1;

  public static String OperatorToStr(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "其他";
    case 1:
      return "中国移动";
    case 2:
      return "中国联通";
    case 3:
      return "中国电信";
    case 4:
    }
    return "其他";
  }

  public static int getOperator(Context paramContext)
  {
    if (paramContext == null)
      return 4;
    if (mOperator != -1)
      return mOperator;
    try
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSubscriberId();
      if (paramContext == null)
      {
        mOperator = 4;
        return mOperator;
      }
      if ((paramContext.startsWith("46000")) || (paramContext.startsWith("46002")))
        mOperator = 1;
      while (true)
      {
        return mOperator;
        if (paramContext.startsWith("46001"))
          mOperator = 2;
        else if (paramContext.startsWith("46003"))
          mOperator = 3;
        else
          mOperator = 4;
      }
    }
    catch (Exception paramContext)
    {
    }
    return 4;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.OperatorInfo
 * JD-Core Version:    0.6.2
 */