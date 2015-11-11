package fm.qingting.utils;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import com.umeng.analytics.MobclickAgent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WifiSettingsUtil
{
  private static final String GOOGLE_DNS = "8.8.8.8";
  public static WifiSettingsUtil _instance;
  private Context mContext = null;
  private boolean mHasDoneWifiSetting = false;
  private ArrayList<InetAddress> mLstDnses;
  private WifiConfiguration mWifiConf = null;
  private WifiManager mWifiManager;

  private void buildWifiConf()
  {
    if (this.mContext == null);
    WifiInfo localWifiInfo;
    WifiConfiguration localWifiConfiguration;
    do
    {
      return;
      Object localObject;
      while (!((Iterator)localObject).hasNext())
      {
        do
        {
          do
            this.mWifiManager = ((WifiManager)this.mContext.getSystemService("wifi"));
          while (this.mWifiManager == null);
          localWifiInfo = this.mWifiManager.getConnectionInfo();
          localObject = this.mWifiManager.getConfiguredNetworks();
        }
        while ((localWifiInfo == null) || (localObject == null));
        localObject = ((List)localObject).iterator();
      }
      localWifiConfiguration = (WifiConfiguration)((Iterator)localObject).next();
    }
    while (localWifiConfiguration.networkId != localWifiInfo.getNetworkId());
    this.mWifiConf = localWifiConfiguration;
  }

  public static Object getDeclaredField(Object paramObject, String paramString)
  {
    try
    {
      paramString = paramObject.getClass().getDeclaredField(paramString);
      paramString.setAccessible(true);
      paramObject = paramString.get(paramObject);
      return paramObject;
    }
    catch (Exception paramObject)
    {
    }
    return null;
  }

  public static Object getField(Object paramObject, String paramString)
  {
    try
    {
      paramObject = paramObject.getClass().getField(paramString).get(paramObject);
      return paramObject;
    }
    catch (Exception paramObject)
    {
    }
    return null;
  }

  public static WifiSettingsUtil getInstance()
  {
    if (_instance == null)
      _instance = new WifiSettingsUtil();
    return _instance;
  }

  private boolean isValidDNS(InetAddress paramInetAddress)
  {
    if (paramInetAddress == null);
    do
    {
      return false;
      paramInetAddress = paramInetAddress.getHostAddress();
    }
    while ((paramInetAddress == null) || (paramInetAddress.equalsIgnoreCase("")) || (paramInetAddress.startsWith("192")) || (paramInetAddress.startsWith("127")));
    return true;
  }

  private void setContext(Context paramContext)
  {
    this.mContext = paramContext;
  }

  private boolean setDNS(String paramString)
  {
    if ((this.mWifiConf == null) || (paramString == null))
      return false;
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(paramString);
      Object localObject = this.mWifiConf.getClass().getField("linkProperties");
      if (localObject == null)
        return false;
      ((Field)localObject).setAccessible(true);
      localObject = ((Field)localObject).get(this.mWifiConf);
      Field localField = localObject.getClass().getDeclaredField("mDnses");
      if (localField != null)
      {
        localField.setAccessible(true);
        this.mLstDnses = ((ArrayList)localField.get(localObject));
      }
      if (this.mLstDnses != null)
      {
        if (this.mLstDnses.size() == 0)
        {
          this.mLstDnses.add(localInetAddress);
          return true;
        }
        localObject = (InetAddress)this.mLstDnses.get(0);
        if (localObject != null)
        {
          localObject = ((InetAddress)localObject).getHostAddress();
          if ((localObject != null) && (!((String)localObject).equalsIgnoreCase(paramString)))
          {
            this.mLstDnses.clear();
            this.mLstDnses.add(localInetAddress);
            return true;
          }
        }
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }

  public static void setEnumField(Object paramObject, String paramString1, String paramString2)
  {
    try
    {
      paramString2 = paramObject.getClass().getField(paramString2);
      paramString2.set(paramObject, Enum.valueOf(paramString2.getType(), paramString1));
      return;
    }
    catch (Exception paramObject)
    {
    }
  }

  private void setGateway(InetAddress paramInetAddress)
  {
    paramInetAddress = getField(this.mWifiConf, "linkProperties");
    if (paramInetAddress == null)
      return;
    paramInetAddress = (InetAddress)getDeclaredField(paramInetAddress, "mGateway");
  }

  private void setIpAssignment(String paramString)
  {
    setEnumField(this.mWifiConf, paramString, "ipAssignment");
  }

  public void init(Context paramContext)
  {
    setContext(paramContext);
    buildWifiConf();
  }

  public boolean isValidDNS()
  {
    if (this.mWifiConf == null)
      return false;
    try
    {
      Object localObject = this.mWifiConf.getClass().getField("linkProperties");
      if (localObject == null)
        return false;
      ((Field)localObject).setAccessible(true);
      localObject = ((Field)localObject).get(this.mWifiConf);
      Field localField = localObject.getClass().getDeclaredField("mDnses");
      if (localField != null)
      {
        localField.setAccessible(true);
        this.mLstDnses = ((ArrayList)localField.get(localObject));
      }
      if (this.mLstDnses == null)
        return false;
      if (this.mLstDnses.size() == 0)
        return false;
      boolean bool = isValidDNS((InetAddress)this.mLstDnses.get(0));
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public void setDNS()
  {
    try
    {
      if (this.mHasDoneWifiSetting)
        return;
      setIpAssignment("STATIC");
      if (setDNS("8.8.8.8"))
      {
        MobclickAgent.onEvent(this.mContext, "AutoSetDNS");
        this.mWifiManager.updateNetwork(this.mWifiConf);
        this.mWifiManager.saveConfiguration();
        this.mWifiManager.reassociate();
      }
      this.mHasDoneWifiSetting = true;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void setIpAddress(InetAddress paramInetAddress, int paramInt)
  {
    try
    {
      Object localObject = getField(this.mWifiConf, "linkProperties");
      if (localObject == null)
        return;
      paramInetAddress = Class.forName("android.net.LinkAddress").getConstructor(new Class[] { InetAddress.class, Integer.TYPE }).newInstance(new Object[] { paramInetAddress, Integer.valueOf(paramInt) });
      localObject = (ArrayList)getDeclaredField(localObject, "mLinkAddresses");
      ((ArrayList)localObject).clear();
      ((ArrayList)localObject).add(paramInetAddress);
      return;
    }
    catch (Exception paramInetAddress)
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.WifiSettingsUtil
 * JD-Core Version:    0.6.2
 */