package fm.qingting.qtradio.location;

import android.content.Context;
import android.location.Location;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import android.util.Log;
import fm.qingting.framework.event.IEventHandler;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class BaseLocation
{
  private IEventHandler eventHandler;
  private LocationThread locationThread;
  private Context mContext;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramAnonymousMessage)
    {
      switch (paramAnonymousMessage.what)
      {
      default:
        return;
      case 1:
        paramAnonymousMessage = (Location)paramAnonymousMessage.obj;
        BaseLocation.this.eventHandler.onEvent(BaseLocation.this, "betterlocation", paramAnonymousMessage);
        BaseLocation.this.stopLocation();
        return;
      case 2:
      }
      BaseLocation.this.stopLocation();
    }
  };

  public BaseLocation(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public long GetUTCTime()
  {
    Calendar localCalendar = Calendar.getInstance(Locale.CHINA);
    localCalendar.add(14, -(localCalendar.get(15) + localCalendar.get(16)));
    return localCalendar.getTimeInMillis();
  }

  public ArrayList<Cell> getTypeOfNetwork()
  {
    try
    {
      localObject1 = (TelephonyManager)this.mContext.getSystemService("phone");
      int i = ((TelephonyManager)localObject1).getNetworkType();
      ArrayList localArrayList = new ArrayList();
      int j;
      Object localObject2;
      if ((i == 6) || (i == 4) || (i == 7))
      {
        Object localObject3 = (CdmaCellLocation)((TelephonyManager)localObject1).getCellLocation();
        i = ((CdmaCellLocation)localObject3).getBaseStationId();
        j = ((CdmaCellLocation)localObject3).getNetworkId();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(((CdmaCellLocation)localObject3).getSystemId());
        localObject3 = new Cell();
        ((Cell)localObject3).cellId = i;
        ((Cell)localObject3).locationAreaCode = j;
        ((Cell)localObject3).mobileNetworkCode = ((StringBuilder)localObject2).toString();
        ((Cell)localObject3).mobileCountryCode = ((TelephonyManager)localObject1).getNetworkOperator().substring(0, 3);
        ((Cell)localObject3).radioType = "cdma";
        localArrayList.add(localObject3);
        return localArrayList;
      }
      if (i == 2)
      {
        localObject2 = (GsmCellLocation)((TelephonyManager)localObject1).getCellLocation();
        i = ((GsmCellLocation)localObject2).getCid();
        j = ((GsmCellLocation)localObject2).getLac();
        localObject2 = new Cell();
        ((Cell)localObject2).cellId = i;
        ((Cell)localObject2).locationAreaCode = j;
        ((Cell)localObject2).mobileNetworkCode = ((TelephonyManager)localObject1).getNetworkOperator().substring(3, 5);
        ((Cell)localObject2).mobileCountryCode = ((TelephonyManager)localObject1).getNetworkOperator().substring(0, 3);
        ((Cell)localObject2).radioType = "gsm";
        localArrayList.add(localObject2);
        return localArrayList;
      }
      if (i == 1)
      {
        localObject1 = (GsmCellLocation)((TelephonyManager)localObject1).getCellLocation();
        i = ((GsmCellLocation)localObject1).getCid();
        j = ((GsmCellLocation)localObject1).getLac();
        localObject1 = new Cell();
        ((Cell)localObject1).cellId = i;
        ((Cell)localObject1).locationAreaCode = j;
        ((Cell)localObject1).radioType = "gsm";
        localArrayList.add(localObject1);
        return localArrayList;
      }
      return null;
    }
    catch (Exception localException)
    {
      Object localObject1 = new Message();
      ((Message)localObject1).what = 2;
      this.mHandler.sendMessage((Message)localObject1);
      localException.printStackTrace();
    }
    return null;
  }

  public boolean isNetworkEnabled()
  {
    return (isWIFIEnabled()) || (isTelephonyEnabled());
  }

  public boolean isTelephonyEnabled()
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)this.mContext.getSystemService("phone");
    return (localTelephonyManager != null) && (localTelephonyManager.getNetworkType() != 0);
  }

  public boolean isWIFIEnabled()
  {
    return ((WifiManager)this.mContext.getSystemService("wifi")).isWifiEnabled();
  }

  public void requestData(ArrayList<Cell> paramArrayList)
  {
    if (paramArrayList == null)
    {
      paramArrayList = new Message();
      paramArrayList.what = 2;
      this.mHandler.sendMessage(paramArrayList);
      return;
    }
    Object localObject1 = new DefaultHttpClient();
    Object localObject2 = new HttpPost("http://www.google.com/loc/json");
    JSONObject localJSONObject1 = new JSONObject();
    JSONArray localJSONArray;
    try
    {
      localJSONObject1.put("version", "1.1.0");
      localJSONObject1.put("host", "maps.google.com");
      localJSONObject1.put("home_mobile_country_code", ((Cell)paramArrayList.get(0)).mobileCountryCode);
      localJSONObject1.put("home_mobile_network_code", ((Cell)paramArrayList.get(0)).mobileNetworkCode);
      localJSONObject1.put("radio_type", ((Cell)paramArrayList.get(0)).radioType);
      localJSONObject1.put("request_address", true);
      if ("460".equals(((Cell)paramArrayList.get(0)).mobileCountryCode))
        localJSONObject1.put("address_language", "zh_CN");
      while (true)
      {
        localJSONArray = new JSONArray();
        JSONObject localJSONObject2 = new JSONObject();
        localJSONObject2.put("cell_id", ((Cell)paramArrayList.get(0)).cellId);
        localJSONObject2.put("location_area_code", ((Cell)paramArrayList.get(0)).locationAreaCode);
        localJSONObject2.put("mobile_country_code", ((Cell)paramArrayList.get(0)).mobileCountryCode);
        localJSONObject2.put("mobile_network_code", ((Cell)paramArrayList.get(0)).mobileNetworkCode);
        localJSONObject2.put("age", 0);
        localJSONArray.put(localJSONObject2);
        if (paramArrayList.size() <= 2)
          break;
        int i = 1;
        while (i < paramArrayList.size())
        {
          localJSONObject2 = new JSONObject();
          localJSONObject2.put("cell_id", ((Cell)paramArrayList.get(i)).cellId);
          localJSONObject2.put("location_area_code", ((Cell)paramArrayList.get(i)).locationAreaCode);
          localJSONObject2.put("mobile_country_code", ((Cell)paramArrayList.get(i)).mobileCountryCode);
          localJSONObject2.put("mobile_network_code", ((Cell)paramArrayList.get(i)).mobileNetworkCode);
          localJSONObject2.put("age", 0);
          localJSONArray.put(localJSONObject2);
          i += 1;
        }
        localJSONObject1.put("address_language", "en_US");
      }
    }
    catch (Exception paramArrayList)
    {
      localObject1 = new Message();
      ((Message)localObject1).what = 2;
      this.mHandler.sendMessage((Message)localObject1);
      paramArrayList.printStackTrace();
      return;
    }
    localJSONObject1.put("cell_towers", localJSONArray);
    paramArrayList = new StringEntity(localJSONObject1.toString());
    Log.e("Location send", localJSONObject1.toString());
    ((HttpPost)localObject2).setEntity(paramArrayList);
    localObject1 = new BufferedReader(new InputStreamReader(((DefaultHttpClient)localObject1).execute((HttpUriRequest)localObject2).getEntity().getContent()));
    localObject2 = new StringBuffer();
    for (paramArrayList = ((BufferedReader)localObject1).readLine(); paramArrayList != null; paramArrayList = ((BufferedReader)localObject1).readLine())
    {
      Log.e("Locaiton receive", paramArrayList);
      ((StringBuffer)localObject2).append(paramArrayList);
    }
    if (((StringBuffer)localObject2).length() <= 1)
    {
      paramArrayList = new Message();
      paramArrayList.what = 2;
      this.mHandler.sendMessage(paramArrayList);
      return;
    }
    localObject1 = (JSONObject)new JSONObject(((StringBuffer)localObject2).toString()).get("location");
    paramArrayList = new Location("network");
    paramArrayList.setLatitude(((Double)((JSONObject)localObject1).get("latitude")).doubleValue());
    paramArrayList.setLongitude(((Double)((JSONObject)localObject1).get("longitude")).doubleValue());
    paramArrayList.setAccuracy(Float.parseFloat(((JSONObject)localObject1).get("accuracy").toString()));
    paramArrayList.setTime(GetUTCTime());
    localObject1 = new Message();
    ((Message)localObject1).what = 1;
    ((Message)localObject1).obj = paramArrayList;
    this.mHandler.sendMessage((Message)localObject1);
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void startLocation()
  {
    this.locationThread = new LocationThread();
    this.locationThread.start();
  }

  public void stopLocation()
  {
    if (this.locationThread != null)
    {
      this.locationThread.interrupt();
      this.locationThread = null;
    }
  }

  public class Cell
  {
    public int cellId;
    public int locationAreaCode;
    public String mobileCountryCode;
    public String mobileNetworkCode;
    public String radioType;

    public Cell()
    {
    }
  }

  class LocationThread extends Thread
  {
    LocationThread()
    {
    }

    public void run()
    {
      BaseLocation.this.requestData(BaseLocation.this.getTypeOfNetwork());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.location.BaseLocation
 * JD-Core Version:    0.6.2
 */