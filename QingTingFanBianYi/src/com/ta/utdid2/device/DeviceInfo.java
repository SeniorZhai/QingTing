package com.ta.utdid2.device;

import android.content.Context;
import com.ta.utdid2.android.utils.PhoneInfoUtils;
import com.ta.utdid2.android.utils.StringUtils;
import java.util.zip.Adler32;

public class DeviceInfo
{
  static final Object CREATE_DEVICE_METADATA_LOCK = new Object();
  static String HMAC_KEY;
  static final byte UTDID_VERSION_CODE = 1;
  private static Device mDevice = null;

  static
  {
    HMAC_KEY = "d6fc3a4a06adbde89223bvefedc24fecde188aaa9161";
  }

  private static Device _initDeviceMetadata(Context paramContext)
  {
    if (paramContext != null)
      new Device();
    synchronized (CREATE_DEVICE_METADATA_LOCK)
    {
      Object localObject2 = UTUtdid.instance(paramContext).getValue();
      Object localObject1;
      if (!StringUtils.isEmpty((String)localObject2))
      {
        localObject1 = localObject2;
        if (((String)localObject2).endsWith("\n"))
          localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
        localObject2 = new Device();
      }
      try
      {
        long l = System.currentTimeMillis();
        String str = PhoneInfoUtils.getImei(paramContext);
        paramContext = PhoneInfoUtils.getImsi(paramContext);
        ((Device)localObject2).setDeviceId(str);
        ((Device)localObject2).setImei(str);
        ((Device)localObject2).setCreateTimestamp(l);
        ((Device)localObject2).setImsi(paramContext);
        ((Device)localObject2).setUtdid((String)localObject1);
        ((Device)localObject2).setCheckSum(getMetadataCheckSum((Device)localObject2));
        return localObject2;
        break label133;
        throw paramContext;
      }
      finally
      {
      }
      label133: return null;
    }
  }

  public static Device getDevice(Context paramContext)
  {
    while (true)
    {
      try
      {
        if (mDevice != null)
        {
          paramContext = mDevice;
          return paramContext;
        }
        if (paramContext != null)
        {
          paramContext = _initDeviceMetadata(paramContext);
          mDevice = paramContext;
          continue;
        }
      }
      finally
      {
      }
      paramContext = null;
    }
  }

  static long getMetadataCheckSum(Device paramDevice)
  {
    if (paramDevice != null)
    {
      paramDevice = String.format("%s%s%s%s%s", new Object[] { paramDevice.getUtdid(), paramDevice.getDeviceId(), Long.valueOf(paramDevice.getCreateTimestamp()), paramDevice.getImsi(), paramDevice.getImei() });
      if (!StringUtils.isEmpty(paramDevice))
      {
        Adler32 localAdler32 = new Adler32();
        localAdler32.reset();
        localAdler32.update(paramDevice.getBytes());
        return localAdler32.getValue();
      }
    }
    return 0L;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.ta.utdid2.device.DeviceInfo
 * JD-Core Version:    0.6.2
 */