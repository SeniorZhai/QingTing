package fm.qingting.utils;

import android.content.Context;

public class BaiduAdv
{
  private static BaiduAdv _instance;
  private Context mContext;

  public static BaiduAdv getInstance()
  {
    if (_instance == null)
      _instance = new BaiduAdv();
    return _instance;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.BaiduAdv
 * JD-Core Version:    0.6.2
 */