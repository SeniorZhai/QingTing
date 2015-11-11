package fm.qingting.utils;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.QTADLocation;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RecommendItemNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ThirdAdv
{
  private static int AirWaveSeq = 0;
  private static ThirdAdv _instance;
  private int appMajorVersion = -1;
  private int appMinorVersion = 0;
  private String mAndroidID;
  private Context mContext;
  private int majorVersion = -1;
  private Map<Integer, List<RecommendItemNode>> mapAdvNode = new HashMap();
  private int microVersion = 0;
  private int minorVersion = 1;

  public static ThirdAdv getInstance()
  {
    if (_instance == null)
      _instance = new ThirdAdv();
    return _instance;
  }

  public String getAirWaveAid()
  {
    try
    {
      if (this.mAndroidID == null)
        this.mAndroidID = Settings.Secure.getString(this.mContext.getContentResolver(), "android_id");
      if ((this.mAndroidID != null) && (this.mAndroidID.length() > 0))
      {
        String str = String.valueOf(AirWaveSeq) + this.mAndroidID.substring(1);
        return str;
      }
    }
    catch (Exception localException)
    {
    }
    return "90wrd56d696e539c";
  }

  public String getAirWaveBID()
  {
    String str = "0A89BCB4E85400C4" + System.currentTimeMillis() / 1000L;
    str = str + "000";
    str = str + AirWaveSeq;
    AirWaveSeq += 1;
    return str;
  }

  public String getAirWaveCarrier()
  {
    int i = OperatorInfo.getOperator(this.mContext);
    if (i == 1)
      return "1";
    if (i == 2)
      return "2";
    if (i == 3)
      return "3";
    return "0";
  }

  public String getAirWaveNetType()
  {
    int i = MobileState.getNetWorkType(this.mContext);
    if (i == 2)
      return "3";
    if (i == 1)
      return "1";
    if (i == 3)
      return "2";
    return "0";
  }

  public int getAppMajorVersion()
  {
    try
    {
      if (this.appMajorVersion != -1)
        return this.appMajorVersion;
      this.appMajorVersion = 4;
      Object localObject = AppInfo.getVersionName(this.mContext);
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
      {
        localObject = ((String)localObject).split(".");
        if (localObject != null)
        {
          this.appMajorVersion = Integer.valueOf(localObject[0]).intValue();
          this.appMinorVersion = Integer.valueOf(localObject[1]).intValue();
        }
      }
      int i = this.appMajorVersion;
      return i;
    }
    catch (Exception localException)
    {
    }
    return this.appMajorVersion;
  }

  public int getAppMinorVersion()
  {
    return this.appMinorVersion;
  }

  public String getChangedAndroidId()
  {
    return DeviceInfo.getChangedAndroidId(this.mContext);
  }

  public String getChangedIMEI()
  {
    return DeviceInfo.getChangedDeviceIMEI(this.mContext, null);
  }

  public String getChangedMac()
  {
    return DeviceInfo.getChangedMacAddress(this.mContext);
  }

  public String getLocalIp()
  {
    Object localObject = InfoManager.getInstance().getADVLocation();
    if ((localObject != null) && (((QTADLocation)localObject).ip != null) && (!((QTADLocation)localObject).ip.equalsIgnoreCase("")))
      return ((QTADLocation)localObject).ip;
    localObject = InfoManager.getInstance().getCurrentLocation();
    if (localObject != null)
      return ((QTLocation)localObject).ip;
    return null;
  }

  public int getMajorVersion()
  {
    try
    {
      if (this.majorVersion != -1)
        return this.majorVersion;
      this.majorVersion = 5;
      Object localObject = DeviceInfo.getAndroidOsVersion();
      if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
      {
        localObject = ((String)localObject).split(".");
        if (localObject != null)
        {
          this.majorVersion = Integer.valueOf(localObject[0]).intValue();
          this.minorVersion = Integer.valueOf(localObject[1]).intValue();
          this.microVersion = Integer.valueOf(localObject[2]).intValue();
        }
      }
      int i = this.majorVersion;
      return i;
    }
    catch (Exception localException)
    {
    }
    return this.majorVersion;
  }

  public int getMicroVersion()
  {
    return this.microVersion;
  }

  public int getMinorVersion()
  {
    return this.minorVersion;
  }

  public String getPhoneModel()
  {
    return Build.MODEL;
  }

  public List<RecommendItemNode> getRecommendNodes(int paramInt)
  {
    List localList = (List)this.mapAdvNode.get(Integer.valueOf(paramInt));
    if (localList == null)
      this.mapAdvNode.put(Integer.valueOf(paramInt), null);
    return localList;
  }

  public int getScreenHeight()
  {
    return ScreenConfiguration.screenHeight;
  }

  public int getScreenWidth()
  {
    return ScreenConfiguration.screenWidth;
  }

  public String getVendor()
  {
    return Build.MANUFACTURER;
  }

  public void init(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.mContext = paramContext;
  }

  public void setAdv(RecommendItemNode paramRecommendItemNode, int paramInt, String paramString)
  {
    if ((paramRecommendItemNode == null) || (paramString == null));
    int i;
    do
    {
      do
      {
        return;
        i = paramInt;
        Object localObject1;
        Object localObject2;
        if (paramInt == -1)
        {
          localObject1 = this.mapAdvNode.entrySet().iterator();
          do
          {
            do
            {
              i = paramInt;
              if (!((Iterator)localObject1).hasNext())
                break;
              localObject2 = (Map.Entry)((Iterator)localObject1).next();
            }
            while (((Map.Entry)localObject2).getValue() != null);
            i = ((Integer)((Map.Entry)localObject2).getKey()).intValue();
            paramInt = i;
          }
          while (i == 0);
        }
        if (i != -1)
        {
          localObject2 = (List)this.mapAdvNode.get(Integer.valueOf(i));
          localObject1 = localObject2;
          if (localObject2 == null)
            localObject1 = new ArrayList();
          if (localObject1 != null)
            ((List)localObject1).add(paramRecommendItemNode);
          this.mapAdvNode.put(Integer.valueOf(i), localObject1);
        }
      }
      while ((paramRecommendItemNode.mNode == null) || (!paramRecommendItemNode.mNode.nodeName.equalsIgnoreCase("activity")));
      if (paramString.equalsIgnoreCase("3"))
      {
        ThirdTracker.getInstance().setThirdAdv((ActivityNode)paramRecommendItemNode.mNode, i);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("4"));
    ThirdTracker.getInstance().setThirdAdvBaidu((ActivityNode)paramRecommendItemNode.mNode, i);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ThirdAdv
 * JD-Core Version:    0.6.2
 */