package com.alipay.mobilesecuritysdk;

import android.content.Context;
import android.util.Log;
import com.alipay.mobilesecuritysdk.datainfo.GeoResponseInfo;
import com.alipay.mobilesecuritysdk.datainfo.SdkConfig;
import com.alipay.mobilesecuritysdk.datainfo.UploadInfo;
import com.alipay.mobilesecuritysdk.face.SecurityClientMobile;
import com.alipay.mobilesecuritysdk.model.CollectedInfo;
import com.alipay.mobilesecuritysdk.model.DataProfile;
import com.alipay.mobilesecuritysdk.model.Upload;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.io.File;
import java.util.List;

public class MainHandler
{
  public int mainhandler(Context paramContext, List<String> paramList, boolean paramBoolean)
  {
    if (!paramBoolean)
      return 1;
    DataProfile localDataProfile = new DataProfile();
    Upload localUpload = new Upload(paramContext);
    UploadInfo localUploadInfo = new UploadInfo();
    Object localObject1 = new CollectedInfo();
    try
    {
      if (CommonUtils.isBlankCollection(paramList))
      {
        if (SecurityClientMobile.isDebug())
          Log.i("ALP", "tid is empty, quit!");
      }
      else
      {
        long l = System.currentTimeMillis();
        SdkConfig localSdkConfig = localDataProfile.getConfigs(paramContext.getFilesDir().getPath());
        if (localSdkConfig == null)
        {
          if (!SecurityClientMobile.isDebug())
            break label620;
          Log.i("ALP", "loadConfig is null");
          break label620;
        }
        if (Thread.currentThread().isInterrupted())
          return 1;
        Object localObject2;
        if (CommonUtils.outOfDate(localSdkConfig.getMainSwitchLUT(), 86400000L, localSdkConfig.getMainSwitchInterval()))
        {
          localObject2 = localUpload.communicateSwitch();
          if ((localObject2 != null) && (((GeoResponseInfo)localObject2).isSuccess()))
            if (!CommonUtils.isBlank(((GeoResponseInfo)localObject2).getMainSwitchState()))
            {
              if (SecurityClientMobile.isDebug())
                Log.i("ALP", "main switch updated.");
              if (!CommonUtils.equalsIgnoreCase(((GeoResponseInfo)localObject2).getMainSwitchState(), "on"))
                break label259;
              localSdkConfig.setMainSwitchState("on");
            }
        }
        while (true)
        {
          localSdkConfig.setMainSwitchLUT(l);
          localDataProfile.saveConfigs(localSdkConfig, paramContext.getFilesDir().getPath() + File.separator + "seccliconfig.xml");
          if (!Thread.currentThread().isInterrupted())
            break;
          return 1;
          label259: localSdkConfig.setMainSwitchState("off");
        }
        if (!CommonUtils.equalsIgnoreCase("on", localSdkConfig.getMainSwitchState()))
        {
          if (!SecurityClientMobile.isDebug())
            break label625;
          Log.i("ALP", "main switch is off, quit!");
          break label625;
        }
        if (CommonUtils.outOfDate(localSdkConfig.getLocateLUT(), 60000L, localSdkConfig.getLocateInterval()))
        {
          localObject2 = ((CollectedInfo)localObject1).collectLocateInfos(paramContext);
          if ((localObject2 != null) && (((List)localObject2).size() > 0))
          {
            if (SecurityClientMobile.isDebug())
              Log.i("ALP", "location collected.");
            localUploadInfo.setLocates((List)localObject2);
            localSdkConfig.setLocateLUT(l);
          }
        }
        if (Thread.currentThread().isInterrupted())
          return 1;
        if (CommonUtils.outOfDate(localSdkConfig.getAppLUT(), 86400000L, localSdkConfig.getAppInterval()))
        {
          localObject1 = ((CollectedInfo)localObject1).collectappInfos(paramContext);
          if ((localObject1 != null) && (((List)localObject1).size() > 0))
          {
            if (SecurityClientMobile.isDebug())
              Log.i("ALP", "app info collected.");
            localUploadInfo.setAppinfos((List)localObject1);
            localSdkConfig.setAppLUT(l);
          }
        }
        if (Thread.currentThread().isInterrupted())
          return 1;
        localUpload.setInfo(localUploadInfo);
        paramList = localUpload.uploadData(paramList, localSdkConfig);
        if ((paramList != null) && (paramList.isSuccess()))
        {
          if (SecurityClientMobile.isDebug())
            Log.i("ALP", "data have been upload.");
          if (paramList.getMainSwitchInterval() > 0)
            localSdkConfig.setMainSwitchInterval(paramList.getMainSwitchInterval());
          if (paramList.getLocateInterval() > 0)
            localSdkConfig.setLocateInterval(paramList.getLocateInterval());
          if (paramList.getAppInterval() > 0)
            localSdkConfig.setAppInterval(paramList.getAppInterval());
          if (paramList.getLocationMaxLines() > 0)
            localSdkConfig.setLocationMaxLines(paramList.getLocationMaxLines());
          localDataProfile.cleanUploadFiles(paramContext.getFilesDir().getPath());
        }
        localDataProfile.saveConfigs(localSdkConfig, paramContext.getFilesDir().getPath() + File.separator + "seccliconfig.xml");
        return 0;
      }
      return 1;
      label620: return 1;
    }
    catch (Exception paramContext)
    {
      return 1;
    }
    label625: return 0;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.mobilesecuritysdk.MainHandler
 * JD-Core Version:    0.6.2
 */