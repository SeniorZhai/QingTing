package com.alipay.mobilesecuritysdk.model;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.location.Location;
import android.location.LocationManager;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Base64;
import android.util.Log;
import com.alipay.mobilesecuritysdk.constant.LocationNameEnum;
import com.alipay.mobilesecuritysdk.datainfo.AppInfo;
import com.alipay.mobilesecuritysdk.datainfo.LocationInfo;
import com.alipay.mobilesecuritysdk.datainfo.WifiCollectInfo;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class CollectedInfo
{
  private final int MODULUS_FIX = 8;
  private DataProfile profile = new DataProfile();

  private List<WifiCollectInfo> GetWifiInfos(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = (WifiManager)paramContext.getSystemService("wifi");
    try
    {
      if (((WifiManager)localObject1).isWifiEnabled())
      {
        paramContext = ((WifiManager)localObject1).getConnectionInfo();
        Object localObject2 = new WifiCollectInfo();
        ((WifiCollectInfo)localObject2).setMbssid(paramContext.getBSSID());
        ((WifiCollectInfo)localObject2).setMssid(Base64.encodeToString(paramContext.getSSID().getBytes(), 8));
        ((WifiCollectInfo)localObject2).setMlevel(paramContext.getRssi());
        ((WifiCollectInfo)localObject2).setMiscurrent(true);
        localArrayList.add(localObject2);
        localObject1 = ((WifiManager)localObject1).getScanResults().iterator();
        while (true)
        {
          if (!((Iterator)localObject1).hasNext())
            return localArrayList;
          localObject2 = (ScanResult)((Iterator)localObject1).next();
          if ((!((ScanResult)localObject2).BSSID.equals(paramContext.getBSSID())) && (!((ScanResult)localObject2).SSID.equals(paramContext.getSSID())))
          {
            WifiCollectInfo localWifiCollectInfo = new WifiCollectInfo();
            localWifiCollectInfo.setMbssid(((ScanResult)localObject2).BSSID);
            localWifiCollectInfo.setMssid(Base64.encodeToString(((ScanResult)localObject2).SSID.getBytes(), 8));
            localWifiCollectInfo.setMlevel(((ScanResult)localObject2).level);
            localWifiCollectInfo.setMiscurrent(false);
            localArrayList.add(localWifiCollectInfo);
          }
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.d("GetWifiInfos", paramContext.getLocalizedMessage());
    }
    return null;
  }

  private void SetPhoneType(TelephonyManager paramTelephonyManager, LocationInfo paramLocationInfo, int paramInt)
  {
    String str3 = "";
    String str2 = "";
    String str1 = "";
    String str4 = "";
    Object localObject5;
    Object localObject6;
    Object localObject7;
    Object localObject8;
    if (paramInt == 2)
    {
      localObject5 = str1;
      localObject6 = str4;
      localObject7 = str3;
      localObject8 = str2;
    }
    while (true)
    {
      Object localObject4;
      try
      {
        CdmaCellLocation localCdmaCellLocation = (CdmaCellLocation)paramTelephonyManager.getCellLocation();
        localObject1 = str1;
        localObject4 = str4;
        localObject2 = str3;
        localObject3 = str2;
        if (localCdmaCellLocation != null)
        {
          localObject1 = str1;
          localObject4 = str4;
          localObject2 = str3;
          localObject3 = str2;
          localObject5 = str1;
          localObject6 = str4;
          localObject7 = str3;
          localObject8 = str2;
          if (CommonUtils.isBlank(paramLocationInfo.getLatitude()))
          {
            localObject1 = str1;
            localObject4 = str4;
            localObject2 = str3;
            localObject3 = str2;
            localObject5 = str1;
            localObject6 = str4;
            localObject7 = str3;
            localObject8 = str2;
            if (CommonUtils.isBlank(paramLocationInfo.getLongitude()))
            {
              localObject5 = str1;
              localObject6 = str4;
              localObject7 = str3;
              localObject8 = str2;
              localObject4 = String.valueOf(localCdmaCellLocation.getNetworkId());
              localObject5 = str1;
              localObject6 = localObject4;
              localObject7 = str3;
              localObject8 = str2;
              localObject2 = paramTelephonyManager.getNetworkOperator().substring(0, 3);
              localObject5 = str1;
              localObject6 = localObject4;
              localObject7 = localObject2;
              localObject8 = str2;
              localObject3 = String.valueOf(localCdmaCellLocation.getSystemId());
              localObject5 = str1;
              localObject6 = localObject4;
              localObject7 = localObject2;
              localObject8 = localObject3;
              localObject1 = String.valueOf(localCdmaCellLocation.getBaseStationId());
              localObject5 = localObject1;
              localObject6 = localObject4;
              localObject7 = localObject2;
              localObject8 = localObject3;
              paramLocationInfo.setLatitude(localCdmaCellLocation.getBaseStationLatitude());
              localObject5 = localObject1;
              localObject6 = localObject4;
              localObject7 = localObject2;
              localObject8 = localObject3;
              paramLocationInfo.setLongitude(localCdmaCellLocation.getBaseStationLongitude());
            }
          }
        }
        paramLocationInfo.setMcc((String)localObject2);
        paramLocationInfo.setMnc((String)localObject3);
        paramLocationInfo.setCid((String)localObject1);
        paramLocationInfo.setLac((String)localObject4);
        return;
      }
      catch (Exception paramTelephonyManager)
      {
        Log.i("gettelphonetype PHONE_TYPE_CDMA", paramTelephonyManager.getLocalizedMessage());
        localObject1 = localObject5;
        localObject4 = localObject6;
        localObject2 = localObject7;
        localObject3 = localObject8;
        continue;
      }
      Object localObject1 = str1;
      Object localObject2 = str3;
      Object localObject3 = str2;
      try
      {
        localObject5 = (GsmCellLocation)paramTelephonyManager.getCellLocation();
        localObject1 = str1;
        localObject4 = str4;
        localObject2 = str3;
        localObject3 = str2;
        if (localObject5 != null)
        {
          localObject1 = str1;
          localObject2 = str3;
          localObject3 = str2;
          str3 = paramTelephonyManager.getNetworkOperator().substring(0, 3);
          localObject1 = str1;
          localObject2 = str3;
          localObject3 = str2;
          paramTelephonyManager = paramTelephonyManager.getNetworkOperator().substring(3, 5);
          localObject1 = str1;
          localObject2 = str3;
          localObject3 = paramTelephonyManager;
          localObject4 = String.valueOf(((GsmCellLocation)localObject5).getCid());
          localObject1 = localObject4;
          localObject2 = str3;
          localObject3 = paramTelephonyManager;
          paramInt = ((GsmCellLocation)localObject5).getLac();
          localObject2 = String.valueOf(paramInt);
          localObject1 = localObject4;
          localObject4 = localObject2;
          localObject2 = str3;
          localObject3 = paramTelephonyManager;
        }
      }
      catch (Exception paramTelephonyManager)
      {
        Log.i("gettelphonetype", paramTelephonyManager.getLocalizedMessage());
        localObject4 = str4;
      }
    }
  }

  private String getSignatureHash(byte[] paramArrayOfByte)
  {
    while (true)
    {
      int j;
      int k;
      int i;
      try
      {
        paramArrayOfByte = ((X509Certificate)CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(paramArrayOfByte))).getPublicKey().toString();
        int m = paramArrayOfByte.indexOf("modulus");
        j = paramArrayOfByte.indexOf("\n", m + 8);
        k = paramArrayOfByte.indexOf(",", m + 8);
        i = -1;
        if ((j < 0) && (k > 0))
        {
          i = k;
          if (i < 0)
          {
            paramArrayOfByte = paramArrayOfByte.substring(m + 8).trim();
            return CommonUtils.MD5(paramArrayOfByte);
          }
          paramArrayOfByte = paramArrayOfByte.substring(m + 8, i).trim();
          continue;
        }
      }
      catch (Exception paramArrayOfByte)
      {
        Log.i("ALP", paramArrayOfByte.getMessage());
        return null;
      }
      if ((k < 0) && (j > 0))
        i = j;
      else if (j < k)
        i = j;
      else if (k < j)
        i = k;
    }
  }

  public String GetLocationInfo(Context paramContext, List<String> paramList)
  {
    List localList = collectLocateInfos(paramContext);
    this.profile.setTid(paramList);
    return this.profile.LocationToString(paramContext.getFilesDir().getPath() + File.separator, localList);
  }

  public List<LocationInfo> collectLocateInfos(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    int j = 0;
    try
    {
      LocationInfo localLocationInfo = new LocationInfo();
      localLocationInfo.setTime(CommonUtils.convertDate2String(new Date()));
      localLocationInfo.setCid("");
      localLocationInfo.setLac("");
      localLocationInfo.setLatitude("");
      localLocationInfo.setLongitude("");
      localLocationInfo.setMcc("");
      localLocationInfo.setMnc("");
      localLocationInfo.setPhonetype("");
      Object localObject = (LocationManager)paramContext.getSystemService("location");
      int i = j;
      if (((LocationManager)localObject).isProviderEnabled("network"))
      {
        SecLocationListener localSecLocationListener = new SecLocationListener();
        ((LocationManager)localObject).requestLocationUpdates("network", 300000L, 0.0F, localSecLocationListener, Looper.getMainLooper());
        ((LocationManager)localObject).removeUpdates(localSecLocationListener);
        localObject = ((LocationManager)localObject).getLastKnownLocation("network");
        i = j;
        if (localObject != null)
        {
          i = 1;
          localLocationInfo.setLatitude(((Location)localObject).getLatitude());
          localLocationInfo.setLongitude(((Location)localObject).getLongitude());
        }
      }
      localObject = (TelephonyManager)paramContext.getSystemService("phone");
      if (((TelephonyManager)localObject).getPhoneType() == 2)
      {
        localLocationInfo.setPhonetype(LocationNameEnum.CDMA.getValue());
        if (i == 0)
          SetPhoneType((TelephonyManager)localObject, localLocationInfo, 2);
      }
      while (true)
      {
        paramContext = GetWifiInfos(paramContext);
        if ((paramContext != null) && (paramContext.size() > 0))
          localLocationInfo.setWifi(paramContext);
        localArrayList.add(localLocationInfo);
        if (localArrayList.size() <= 0)
          break;
        return localArrayList;
        localLocationInfo.setPhonetype(LocationNameEnum.GSM.getValue());
        SetPhoneType((TelephonyManager)localObject, localLocationInfo, 1);
      }
    }
    catch (Exception paramContext)
    {
      Log.i("ALP", paramContext.getMessage());
      return null;
    }
    return null;
  }

  public List<AppInfo> collectappInfos(Context paramContext)
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      paramContext = paramContext.getPackageManager();
      Iterator localIterator = paramContext.getInstalledPackages(4096).iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return localArrayList;
        PackageInfo localPackageInfo = (PackageInfo)localIterator.next();
        if (((paramContext.checkPermission("android.permission.READ_SMS", localPackageInfo.packageName) == 0) || (paramContext.checkPermission("android.permission.RECEIVE_SMS", localPackageInfo.packageName) == 0)) && ((paramContext.checkPermission("android.permission.SEND_SMS", localPackageInfo.packageName) == 0) || (paramContext.checkPermission("android.permission.INTERNET", localPackageInfo.packageName) == 0)))
        {
          AppInfo localAppInfo = new AppInfo();
          localAppInfo.setPkgName(localPackageInfo.packageName);
          localAppInfo.setPkeyhash(getSignatureHash(paramContext.getPackageInfo(localPackageInfo.packageName, 64).signatures[0].toByteArray()));
          if (localAppInfo.validate())
            localArrayList.add(localAppInfo);
        }
      }
    }
    catch (Exception paramContext)
    {
      Log.i("ALP", paramContext.getMessage());
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.model.CollectedInfo
 * JD-Core Version:    0.6.2
 */