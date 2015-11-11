package fm.qingting.qtradio.wo;

import android.content.Context;
import android.telephony.TelephonyManager;
import fm.qingting.qtradio.manager.INETEventListener;
import fm.qingting.qtradio.manager.NetWorkManage;

public class WoNetEventListener
  implements INETEventListener
{
  private static String TAG = "WoNetEventListener";
  private static WoNetEventListener instance = null;
  private Context _context;
  private int isChinaUnicom = -1;

  private WoNetEventListener()
  {
    NetWorkManage.getInstance().addListener(this);
  }

  public static WoNetEventListener getInstance()
  {
    try
    {
      if (instance == null)
        instance = new WoNetEventListener();
      WoNetEventListener localWoNetEventListener = instance;
      return localWoNetEventListener;
    }
    finally
    {
    }
  }

  public static boolean hasOpenUniCom(String paramString)
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "天津";
    arrayOfString[1] = "福建";
    arrayOfString[2] = "山东";
    arrayOfString[3] = "湖北";
    arrayOfString[4] = "安徽";
    arrayOfString[5] = "黑龙江";
    arrayOfString[6] = "青海";
    arrayOfString[7] = "甘肃";
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (arrayOfString[i].equalsIgnoreCase(paramString))
        return false;
      i += 1;
    }
    return true;
  }

  public static boolean isChinaUnicom(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = ((TelephonyManager)paramContext.getSystemService("phone")).getSimOperator();
      if (paramContext != null)
      {
        if (paramContext.equalsIgnoreCase("46001"))
          return true;
        return !paramContext.equals("");
      }
    }
    return false;
  }

  public void init(Context paramContext)
  {
    this._context = paramContext;
    onNetChanged(NetWorkManage.getInstance().getNetWorkType());
  }

  public boolean isChinaUnicom()
  {
    if (this._context != null)
    {
      String str = ((TelephonyManager)this._context.getSystemService("phone")).getSimOperator();
      if (str != null)
      {
        if (str.equals("46001"))
        {
          this.isChinaUnicom = 1;
          return true;
        }
        if (WoApiRequest.isChinaUnicomNetwork(this._context))
        {
          this.isChinaUnicom = 1;
          return true;
        }
        this.isChinaUnicom = 0;
        return false;
      }
      if (WoApiRequest.isChinaUnicomNetwork(this._context))
      {
        this.isChinaUnicom = 1;
        return true;
      }
      this.isChinaUnicom = 0;
      return false;
    }
    if (this.isChinaUnicom == 1)
      return true;
    return this.isChinaUnicom != 0;
  }

  public boolean isChinaUnicomNet()
  {
    String str = NetWorkManage.getInstance().getNetWorkType();
    return ((str == "3G") || (str == "2G")) && (isChinaUnicom());
  }

  public void onNetChanged(String paramString)
  {
    if (!isChinaUnicom())
      WoApiRequest.disableWoProxy();
    while ((!WoApiRequest.hasOpen()) || (paramString == null))
      return;
    if ((paramString.equalsIgnoreCase("3G")) || (paramString.equalsIgnoreCase("2G")) || (paramString.equalsIgnoreCase("WiFi")))
    {
      if (paramString.equalsIgnoreCase("WiFi"))
        WoApiRequest.disableWoProxy(this._context);
      while (true)
      {
        boolean bool1 = WoApiRequest.getInitState().equals(WoApiRequest.INIT_STAGE.DONE);
        boolean bool2 = WoApiRequest.getInitState().equals(WoApiRequest.INIT_STAGE.NOTHING);
        if ((!bool1) && (!bool2))
          break;
        WoApiRequest.reset();
        WoApiRequest.init(this._context);
        return;
        WoApiRequest.enableWoProxy(this._context);
      }
    }
    paramString = WoApiRequest.checkNetWorkType(this._context);
    if ((paramString == WoApiRequest.NET_TYPE.NET) || (paramString == WoApiRequest.NET_TYPE.WAP))
    {
      WoApiRequest.enableWoProxy(this._context);
      WoApiRequest.reset();
      WoApiRequest.init(this._context);
      return;
    }
    WoApiRequest.disableWoProxy(this._context);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoNetEventListener
 * JD-Core Version:    0.6.2
 */