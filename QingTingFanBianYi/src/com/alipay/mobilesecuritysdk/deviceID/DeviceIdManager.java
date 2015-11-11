package com.alipay.mobilesecuritysdk.deviceID;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DeviceIdManager
{
  public static Map<String, String> strMap = null;
  private final Context mcontext;

  public DeviceIdManager(Context paramContext)
  {
    this.mcontext = paramContext;
    LOG.init(paramContext);
  }

  private void Update(final Context paramContext, final Map<String, String> paramMap)
  {
    try
    {
      new Thread(new Runnable()
      {
        public void run()
        {
          try
          {
            DeviceIdModel localDeviceIdModel = new DeviceIdModel();
            localDeviceIdModel.Init(paramContext, paramMap);
            localDeviceIdModel.UpdateId(paramContext, DeviceIdManager.strMap);
            return;
          }
          catch (Throwable localThrowable)
          {
            ArrayList localArrayList = new ArrayList();
            if ((paramMap.get("tid") != null) && (((String)paramMap.get("tid")).length() > 20))
              localArrayList.add(((String)paramMap.get("tid")).substring(0, 20));
            if ((paramMap.get("utdid") != null) && (((String)paramMap.get("utdid")).length() > 20))
              localArrayList.add(((String)paramMap.get("utdid")).substring(0, 20));
            localArrayList.add(LOG.getStackString(localThrowable));
            LOG.logMessage(localArrayList);
          }
        }
      }).start();
      return;
    }
    finally
    {
      paramContext = finally;
    }
    throw paramContext;
  }

  private void UpdateLog()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          LOG.uploadLogFile();
          return;
        }
        catch (Throwable localThrowable)
        {
          ArrayList localArrayList = new ArrayList();
          localArrayList.add("");
          localArrayList.add("");
          localArrayList.add("");
          localArrayList.add(LOG.getStackString(localThrowable));
          LOG.logMessage(localArrayList);
        }
      }
    }).start();
  }

  public String GetApDid(Map<String, String> paramMap)
  {
    ArrayList localArrayList = null;
    Object localObject2 = null;
    UpdateLog();
    Object localObject1 = localArrayList;
    try
    {
      DeviceIdModel localDeviceIdModel = new DeviceIdModel();
      localObject1 = localArrayList;
      strMap = localDeviceIdModel.GetPrivateData(this.mcontext);
      localObject1 = localArrayList;
      if (strMap == null)
      {
        localObject1 = localArrayList;
        localObject2 = new ArrayList();
        localObject1 = localArrayList;
        if (paramMap.get("tid") != null)
        {
          localObject1 = localArrayList;
          if (((String)paramMap.get("tid")).length() > 20)
          {
            localObject1 = localArrayList;
            ((List)localObject2).add(((String)paramMap.get("tid")).substring(0, 20));
          }
        }
        localObject1 = localArrayList;
        if (paramMap.get("utdid") != null)
        {
          localObject1 = localArrayList;
          if (((String)paramMap.get("utdid")).length() > 20)
          {
            localObject1 = localArrayList;
            ((List)localObject2).add(((String)paramMap.get("utdid")).substring(0, 20));
          }
        }
        localObject1 = localArrayList;
        ((List)localObject2).add("model.GetPrivateData(mcontext)  strMap is null");
        localObject1 = localArrayList;
        LOG.logMessage((List)localObject2);
        localObject1 = localArrayList;
        Update(this.mcontext, paramMap);
        return null;
      }
      localObject1 = localArrayList;
      if (localDeviceIdModel.CheckPrivateData(strMap))
      {
        localObject1 = localArrayList;
        localObject2 = (String)strMap.get("deviceId");
        localObject1 = localObject2;
        localArrayList = new ArrayList();
        localObject1 = localObject2;
        if (paramMap.get("tid") != null)
        {
          localObject1 = localObject2;
          if (((String)paramMap.get("tid")).length() > 20)
          {
            localObject1 = localObject2;
            localArrayList.add(((String)paramMap.get("tid")).substring(0, 20));
          }
        }
        localObject1 = localObject2;
        if (paramMap.get("utdid") != null)
        {
          localObject1 = localObject2;
          if (((String)paramMap.get("utdid")).length() > 20)
          {
            localObject1 = localObject2;
            localArrayList.add(((String)paramMap.get("utdid")).substring(0, 20));
          }
        }
        localObject1 = localObject2;
        localArrayList.add("GetApDid  deviceID is " + (String)localObject2);
        localObject1 = localObject2;
        LOG.logMessage(localArrayList);
      }
      localObject1 = localObject2;
      Update(this.mcontext, paramMap);
      localObject1 = localObject2;
      return localObject1;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localArrayList = new ArrayList();
        if ((paramMap.get("tid") != null) && (((String)paramMap.get("tid")).length() > 20))
          localArrayList.add(((String)paramMap.get("tid")).substring(0, 20));
        if ((paramMap.get("utdid") != null) && (((String)paramMap.get("utdid")).length() > 20))
          localArrayList.add(((String)paramMap.get("utdid")).substring(0, 20));
        localArrayList.add(LOG.getStackString(localException));
        LOG.logMessage(localArrayList);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.deviceID.DeviceIdManager
 * JD-Core Version:    0.6.2
 */