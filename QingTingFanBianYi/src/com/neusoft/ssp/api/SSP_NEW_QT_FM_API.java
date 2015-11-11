package com.neusoft.ssp.api;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.util.Base64;
import com.neusoft.parse.AsyncImageLoader;
import com.neusoft.parse.DataParser;
import com.neusoft.ssp.protocol.Handle;
import com.neusoft.ssp.protocol.SSPProtocol;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

public class SSP_NEW_QT_FM_API extends SSP_API
{
  private static int c = 1;
  private static int d = 0;
  private static boolean e = false;
  private String a = "1.0";
  private boolean b = false;
  private Timer f;
  private TimerTask g;
  private QTFM_NEW_RequestListener h;

  private SSP_NEW_QT_FM_API(String paramString)
  {
    super(paramString);
  }

  private static String a(String paramString, Object[] paramArrayOfObject)
  {
    SSPProtocol localSSPProtocol = SSPProtocol.getInstance();
    paramArrayOfObject = localSSPProtocol.sspDataNewBaseType(paramString, paramArrayOfObject);
    try
    {
      paramString = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
      paramString.setAccessible(true);
      paramString = (String)paramString.invoke(localSSPProtocol, new Object[] { paramArrayOfObject });
      localSSPProtocol.sspDataRelease(paramArrayOfObject);
      return paramString;
    }
    catch (NoSuchMethodException paramString)
    {
      while (true)
        paramString = null;
    }
    catch (IllegalAccessException paramString)
    {
      while (true)
        paramString = null;
    }
    catch (IllegalArgumentException paramString)
    {
      while (true)
        paramString = null;
    }
    catch (InvocationTargetException paramString)
    {
      while (true)
        paramString = null;
    }
  }

  public static SSP_NEW_QT_FM_API getInstance()
  {
    return f.a();
  }

  public static Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public void LogChuxl(String paramString)
  {
  }

  public void TimeRecord()
  {
    e = false;
    LogChuxl("TimeRecord");
    if (this.f != null)
    {
      this.f.cancel();
      this.f = null;
    }
    if (this.g != null)
    {
      this.g.cancel();
      this.g = null;
    }
    this.f = new Timer();
    this.g = new e(this);
    this.f.schedule(this.g, 4000L, 5000L);
  }

  public String cutImageId(String paramString)
  {
    int i = paramString.lastIndexOf("/");
    Object localObject = paramString.split("\\.");
    localObject = "." + localObject[(localObject.length - 1)];
    return paramString.substring(i + 1).replace((CharSequence)localObject, "");
  }

  public void getImage(Object paramObject, Context paramContext, String paramString, int paramInt)
  {
    AsyncImageLoader localAsyncImageLoader = new AsyncImageLoader(paramContext.getApplicationContext());
    localAsyncImageLoader.setCache2File(true);
    localAsyncImageLoader.setCachedDir(paramContext.getCacheDir().getAbsolutePath());
    LogChuxl("dir:" + paramContext.getCacheDir().getAbsolutePath());
    if (paramString.startsWith("http"))
    {
      localAsyncImageLoader.downloadImage(paramString, true, new d(this, paramObject, paramInt, cutImageId(paramString)));
      return;
    }
    replyImageToCar(paramObject, 1, paramInt, "", null);
  }

  public int getInt(long paramLong, String paramString)
  {
    return Integer.parseInt(getStandardTime(paramLong, paramString));
  }

  public void getMatchResult(Object paramObject, String paramString)
  {
    try
    {
      i = compareVersion(this.a, paramString);
      if (i == 0)
      {
        replyMatchResult(paramObject, 0);
        return;
      }
    }
    catch (Exception paramString)
    {
      int i;
      while (true)
      {
        paramString.printStackTrace();
        i = 0;
      }
      if (i > 0)
      {
        replyMatchResult(paramObject, 1);
        return;
      }
      replyMatchResult(paramObject, 2);
    }
  }

  public int getSeconds(long paramLong)
  {
    return getInt(paramLong, "HH") * 3600 + getInt(paramLong, "mm") * 60 + getInt(paramLong, "ss");
  }

  public String getStandardTime(long paramLong, String paramString)
  {
    paramString = new SimpleDateFormat(paramString, Locale.getDefault());
    paramString.setTimeZone(TimeZone.getTimeZone("GMT+8"));
    Date localDate = new Date(1000L * paramLong);
    paramString.format(localDate);
    return paramString.format(localDate);
  }

  public SSP_NEW_QT_FM_API.CityItem newCityItem()
  {
    return new SSP_NEW_QT_FM_API.CityItem(this);
  }

  public SSP_NEW_QT_FM_API.ClassItem newClassItem()
  {
    return new SSP_NEW_QT_FM_API.ClassItem(this);
  }

  public SSP_NEW_QT_FM_API.LabelItem newLabelItem()
  {
    return new SSP_NEW_QT_FM_API.LabelItem(this);
  }

  public SSP_NEW_QT_FM_API.ProgramItem newProgramItem()
  {
    return new SSP_NEW_QT_FM_API.ProgramItem(this);
  }

  public SSP_NEW_QT_FM_API.RadioItem newRadioItem()
  {
    return new SSP_NEW_QT_FM_API.RadioItem(this);
  }

  public SSP_NEW_QT_FM_API.RecentItem newRecentItem()
  {
    return new SSP_NEW_QT_FM_API.RecentItem(this);
  }

  public SSP_NEW_QT_FM_API.RecommendItem newRecommendItem()
  {
    return new SSP_NEW_QT_FM_API.RecommendItem(this);
  }

  protected void onRecvRequest(String paramString1, String paramString2, int paramInt, String[] paramArrayOfString)
  {
    Hashtable localHashtable;
    if ((this.h != null) && (paramString1 != null) && (paramString2 != null))
    {
      localHashtable = new Hashtable();
      localHashtable.put("funcID", paramString2);
      localHashtable.put("flowID", Integer.valueOf(paramInt));
      if (!paramString2.equalsIgnoreCase("wakeup"))
        break label85;
      replyWakeUp();
      LogChuxl("notifyWakeUp...car...request start");
      this.h.notifyWakeUp();
      LogChuxl("notifyWakeUp...car...request end");
    }
    label85: 
    do
    {
      Object localObject1;
      Object localObject2;
      int i;
      int j;
      do
      {
        do
        {
          return;
          if (paramString2.equalsIgnoreCase("play"))
          {
            if (paramArrayOfString != null)
            {
              localObject1 = paramArrayOfString[0];
              paramArrayOfString = SSPProtocol.getInstance();
              localObject1 = paramArrayOfString.sspTrans((String)localObject1);
              if ((localObject1 != null) && (((Handle)localObject1).getAddress() != 0))
              {
                Object localObject5 = paramArrayOfString.sspDataGetBaseType((Handle)localObject1, "(iiii)");
                localObject2 = localObject5[0];
                Object localObject3 = localObject5[1];
                Object localObject4 = localObject5[2];
                localObject5 = localObject5[3];
                if (((localObject2 instanceof Integer)) && ((localObject3 instanceof Integer)))
                {
                  i = ((Integer)localObject2).intValue();
                  j = ((Integer)localObject3).intValue();
                  int k = ((Integer)localObject4).intValue();
                  int m = ((Integer)localObject5).intValue();
                  LogChuxl("notifyPlayRadio start..");
                  this.h.notifyPlayRadio(localHashtable, i, j, k, m);
                  LogChuxl("notifyPlayRadio end..");
                }
              }
              paramArrayOfString.sspDataRelease((Handle)localObject1);
            }
            e = true;
            LogChuxl("play baowen return start..");
            replay(DataParser.createData(paramInt, paramString1, paramString2, new String[0]));
            TimeRecord();
            LogChuxl("play baowen return end..");
            return;
          }
          if (paramString2.equalsIgnoreCase("playorpause"))
          {
            if (paramArrayOfString != null)
            {
              localObject1 = paramArrayOfString[0];
              paramArrayOfString = SSPProtocol.getInstance();
              localObject1 = paramArrayOfString.sspTrans((String)localObject1);
              if ((localObject1 != null) && (((Handle)localObject1).getAddress() != 0))
              {
                localObject2 = paramArrayOfString.sspDataGetBaseType(localObject1, "i")[0];
                if ((localObject2 instanceof Integer))
                {
                  paramInt = ((Integer)localObject2).intValue();
                  LogChuxl("notifyPlayOrPause...start");
                  this.h.notifyPlayOrPause(localHashtable, paramInt);
                  LogChuxl("notifyPlayOrPause...end");
                }
              }
              paramArrayOfString.sspDataRelease((Handle)localObject1);
            }
            e = true;
            LogChuxl("playorpause fail start...");
            replay(DataParser.createData(0, paramString1, paramString2, new String[0]));
            TimeRecord();
            LogChuxl("playorpause fail end...");
            return;
          }
          if (paramString2.equalsIgnoreCase("pre"))
          {
            LogChuxl("notifyPreProgram start...");
            this.h.notifyPreProgram(localHashtable);
            LogChuxl("notifyPreProgram end...");
            e = true;
            LogChuxl("Pre baowen return start..");
            replay(DataParser.createData(paramInt, paramString1, paramString2, new String[0]));
            TimeRecord();
            LogChuxl("Pre baowen return end..");
            return;
          }
          if (paramString2.equalsIgnoreCase("next"))
          {
            LogChuxl("notifyNextProgram start...");
            this.h.notifyNextProgram(localHashtable);
            LogChuxl("notifyNextProgram end...");
            LogChuxl("next baowen return start..");
            e = true;
            replay(DataParser.createData(paramInt, paramString1, paramString2, new String[0]));
            TimeRecord();
            LogChuxl("next baowen return end..");
            return;
          }
          if (!paramString2.equalsIgnoreCase("collect"))
            break;
        }
        while (paramArrayOfString == null);
        paramString2 = paramArrayOfString[0];
        paramString1 = SSPProtocol.getInstance();
        paramString2 = paramString1.sspTrans(paramString2);
        if ((paramString2 != null) && (paramString2.getAddress() != 0))
        {
          localObject2 = paramString1.sspDataGetBaseType(paramString2, "(iii)");
          paramArrayOfString = localObject2[0];
          localObject1 = localObject2[1];
          localObject2 = localObject2[2];
          if (((paramArrayOfString instanceof Integer)) && ((localObject1 instanceof Integer)))
          {
            paramInt = ((Integer)paramArrayOfString).intValue();
            i = ((Integer)localObject1).intValue();
            j = ((Integer)localObject2).intValue();
            LogChuxl("notifyCollectProgram start..");
            this.h.notifyCollectProgram(localHashtable, paramInt, i, j);
            LogChuxl("notifyCollectProgram end..");
            LogChuxl("replyCollectProgram  return start..");
            replyCollectProgram(localHashtable, 0, paramInt, i);
            LogChuxl("replyCollectProgram return end..");
          }
        }
        paramString1.sspDataRelease(paramString2);
        return;
        if (paramString2.equalsIgnoreCase("classlist"))
        {
          LogChuxl("notifyAllList start...");
          this.h.notifyAllList(localHashtable);
          LogChuxl("notifyAllList end...");
          return;
        }
        if (!paramString2.equalsIgnoreCase("dimensionlist"))
          break;
      }
      while (paramArrayOfString == null);
      paramString2 = paramArrayOfString[0];
      paramString1 = SSPProtocol.getInstance();
      paramString2 = paramString1.sspTrans(paramString2);
      if ((paramString2 != null) && (paramString2.getAddress() != 0))
      {
        localObject1 = paramString1.sspDataGetBaseType(paramString2, "(ii)");
        paramArrayOfString = localObject1[0];
        localObject1 = localObject1[1];
        if (((paramArrayOfString instanceof Integer)) && ((localObject1 instanceof Integer)))
        {
          paramInt = ((Integer)paramArrayOfString).intValue();
          i = ((Integer)localObject1).intValue();
          LogChuxl("notifyLabelList start..");
          this.h.notifyLabelList(localHashtable, paramInt, i);
          LogChuxl("notifyLabelList end..");
        }
      }
      paramString1.sspDataRelease(paramString2);
      return;
      if (paramString2.equalsIgnoreCase("radiolist"))
      {
        LogChuxl("radiolist start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            localObject2 = paramString1.sspDataGetBaseType(paramString2, "(isi)");
            paramArrayOfString = localObject2[0];
            localObject1 = localObject2[1];
            localObject2 = localObject2[2];
            if (((paramArrayOfString instanceof Integer)) && ((localObject1 instanceof String)) && ((localObject2 instanceof Integer)))
            {
              paramInt = ((Integer)paramArrayOfString).intValue();
              paramArrayOfString = (String)localObject1;
              i = ((Integer)localObject2).intValue();
              LogChuxl("notifyRadioList start..");
              this.h.notifyRadioList(localHashtable, paramInt, paramArrayOfString, i);
              LogChuxl("notifyRadioList end..");
            }
          }
          paramString1.sspDataRelease(paramString2);
        }
        LogChuxl("radiolist end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("programlist"))
      {
        LogChuxl("programlist start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            localObject2 = paramString1.sspDataGetBaseType(paramString2, "(iii)");
            paramArrayOfString = localObject2[0];
            localObject1 = localObject2[1];
            localObject2 = localObject2[2];
            if (((paramArrayOfString instanceof Integer)) && ((localObject1 instanceof Integer)))
            {
              paramInt = ((Integer)paramArrayOfString).intValue();
              i = ((Integer)localObject1).intValue();
              j = ((Integer)localObject2).intValue();
              LogChuxl("notifyProgramList start..");
              this.h.notifyProgramList(localHashtable, paramInt, i, j);
              LogChuxl("notifyProgramList end..");
            }
          }
          paramString1.sspDataRelease(paramString2);
        }
        LogChuxl("programlist end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("collectlist"))
      {
        LogChuxl("collectlist start...");
        this.h.notifyCollectList(localHashtable);
        LogChuxl("collectlist end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("recentlist"))
      {
        LogChuxl("recentlist start...");
        this.h.notifyRecentList(localHashtable);
        LogChuxl("recentlist end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("downradiolist"))
      {
        LogChuxl("notifyDownLoadRadioList start...");
        this.h.notifyDownLoadRadioList(localHashtable);
        LogChuxl("notifyDownLoadRadioList end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("downprogramlist"))
      {
        LogChuxl("downProgramList start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            paramArrayOfString = paramString1.sspDataGetBaseType(paramString2, "i")[0];
            if ((paramArrayOfString instanceof Integer))
            {
              paramInt = ((Integer)paramArrayOfString).intValue();
              LogChuxl("notifyDownLoadProgramList start..");
              this.h.notifyDownLoadProgramList(localHashtable, paramInt);
              LogChuxl("notifyDownLoadProgramList end..");
            }
          }
          paramString1.sspDataRelease(paramString2);
        }
        LogChuxl("downProgramList end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("keywordlist"))
      {
        LogChuxl("keywordlist start...");
        this.h.notifyKeyWordList(localHashtable);
        LogChuxl("keywordlist end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("resultlist"))
      {
        LogChuxl("resultList start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            paramArrayOfString = paramString1.sspDataGetBaseType(paramString2, "s")[0];
            if ((paramArrayOfString instanceof String))
            {
              paramArrayOfString = (String)paramArrayOfString;
              LogChuxl("notifyResultList start..");
              this.h.notifyResultList(localHashtable, paramArrayOfString);
              LogChuxl("notifyResultList end..");
            }
          }
          paramString1.sspDataRelease(paramString2);
        }
        LogChuxl("resultList end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("exit"))
      {
        LogChuxl("notifyConnectStatus start..." + c);
        this.h.notifyConnectStatus(c);
        LogChuxl("notifyConnectStatus ..." + c);
        LogChuxl("notifyExitApp start...");
        this.h.notifyExitApp(localHashtable);
        LogChuxl("notifyExitApp end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("citylist"))
      {
        LogChuxl("notifyCityList start...");
        this.h.notifyCityList(localHashtable);
        LogChuxl("notifyCityList end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("liveradiolist"))
      {
        LogChuxl("fmList start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            localObject1 = paramString1.sspDataGetBaseType(paramString2, "(is)");
            paramArrayOfString = localObject1[0];
            localObject1 = localObject1[1];
            if (((paramArrayOfString instanceof Integer)) && ((localObject1 instanceof String)))
            {
              paramInt = ((Integer)paramArrayOfString).intValue();
              paramArrayOfString = (String)localObject1;
              LogChuxl("notifyLiveRadioList start..");
              this.h.notifyLiveRadioList(localHashtable, paramInt, paramArrayOfString);
              LogChuxl("notifyLiveRadioList end..");
            }
          }
          paramString1.sspDataRelease(paramString2);
        }
        LogChuxl("fmList end...");
        return;
      }
      if (paramString2.equalsIgnoreCase("getradiopic"))
      {
        LogChuxl("getRadioPic start...");
        if (paramArrayOfString != null)
        {
          paramString2 = paramArrayOfString[0];
          paramString1 = SSPProtocol.getInstance();
          paramString2 = paramString1.sspTrans(paramString2);
          if ((paramString2 != null) && (paramString2.getAddress() != 0))
          {
            localObject1 = paramString1.sspDataGetBaseType(paramString2, "(si)");
            paramArrayOfString = localObject1[0];
            localObject1 = localObject1[1];
            if (((paramArrayOfString instanceof String)) && ((localObject1 instanceof Integer)))
            {
              paramArrayOfString = (String)paramArrayOfString;
              paramInt = ((Integer)localObject1).intValue();
              LogChuxl("notifyGetRadioPic start..");
              new Thread(new c(this, localHashtable, paramArrayOfString, paramInt)).start();
              LogChuxl("notifyGetRadioPic end...");
            }
          }
        }
        try
        {
          Thread.sleep(1000L);
          replyImageToCar(localHashtable, 1, paramInt, "", null);
          paramString1.sspDataRelease(paramString2);
          LogChuxl("getRadioPic end...");
          return;
        }
        catch (InterruptedException paramArrayOfString)
        {
          while (true)
            paramArrayOfString.printStackTrace();
        }
      }
      if (paramString2.equalsIgnoreCase("connectFail"))
      {
        LogChuxl("notifyConnectStatus 连接断开 去掉遮罩");
        this.h.notifyConnectStatus(c);
        LogChuxl("notifyConnectStatus end");
        return;
      }
      if (paramString2.equalsIgnoreCase("livingradio"))
      {
        LogChuxl("notifyLivingRadio...start");
        this.h.notifyLivingRadio(localHashtable);
        LogChuxl("notifyLivingRadio end");
        return;
      }
      if (paramString2.equalsIgnoreCase("selectlist"))
      {
        LogChuxl("notifySelectList...start");
        this.h.notifySelectList(localHashtable);
        LogChuxl("notifySelectList end");
        return;
      }
    }
    while (!paramString2.equalsIgnoreCase("versionmatch"));
    LogChuxl("versionMatch start...");
    if (paramArrayOfString != null)
    {
      paramString2 = paramArrayOfString[0];
      paramString1 = SSPProtocol.getInstance();
      paramString2 = paramString1.sspTrans(paramString2);
      if ((paramString2 != null) && (paramString2.getAddress() != 0))
      {
        paramArrayOfString = paramString1.sspDataGetBaseType(paramString2, "s")[0];
        if ((paramArrayOfString instanceof String))
          getMatchResult(localHashtable, (String)paramArrayOfString);
      }
      paramString1.sspDataRelease(paramString2);
    }
    LogChuxl("versionMatch end...");
  }

  public void replyAllList(Object paramObject, int paramInt1, int paramInt2, List paramList1, int paramInt3, List paramList2, int paramInt4, List paramList3)
  {
    e = true;
    LogChuxl("replyAllList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Handle localHandle1;
    int i;
    if ((paramList2 != null) && (paramList3 != null))
    {
      localObject1 = SSPProtocol.getInstance();
      LogChuxl("bestList...start");
      localHandle1 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      int k = paramList1.size();
      i = 0;
      if (i >= k)
      {
        LogChuxl("bestList...end");
        paramList1 = ((SSPProtocol)localObject1).sspDataNewArrayType();
        k = paramList2.size();
        LogChuxl("boardcastList...start");
        i = 0;
        label126: if (i < k)
          break label504;
        LogChuxl("boardcastList...end");
        paramList2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
        k = paramList3.size();
        LogChuxl("contentList...start");
        i = 0;
        if (i < k)
          break label661;
        LogChuxl("contentList...end");
        paramList1 = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiviviv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localHandle1, Integer.valueOf(paramInt3), paramList1, Integer.valueOf(paramInt4), paramList2 });
      }
    }
    while (true)
    {
      try
      {
        paramList2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        paramList2.setAccessible(true);
        paramList2 = (String)paramList2.invoke(localObject1, new Object[] { paramList1 });
        ((SSPProtocol)localObject1).sspDataRelease(paramList1);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { paramList2 });
        replay(paramObject);
        LogChuxl("replyAllList...Msg:" + paramObject);
        TimeRecord();
        return;
        int m = ((SSP_NEW_QT_FM_API.ClassItem)paramList1.get(i)).classId;
        String str = ((SSP_NEW_QT_FM_API.ClassItem)paramList1.get(i)).className;
        int n = ((SSP_NEW_QT_FM_API.ClassItem)paramList1.get(i)).sectionId;
        Object localObject2 = ((SSPProtocol)localObject1).sspDataNewBaseType("(isi)", new Object[] { Integer.valueOf(m), str, Integer.valueOf(n) });
        LogChuxl("bestList..." + i + ": beId-" + m + ": beName-" + str + ": sectionId-" + n);
        ((SSPProtocol)localObject1).dataAddArrayType(localHandle1, (Handle)localObject2);
        i += 1;
        break;
        label504: m = ((SSP_NEW_QT_FM_API.ClassItem)paramList2.get(i)).classId;
        str = ((SSP_NEW_QT_FM_API.ClassItem)paramList2.get(i)).className;
        localObject2 = ((SSP_NEW_QT_FM_API.ClassItem)paramList2.get(i)).attrId;
        Handle localHandle2 = ((SSPProtocol)localObject1).sspDataNewBaseType("(iss)", new Object[] { Integer.valueOf(m), str, localObject2 });
        LogChuxl("boardcastList..." + i + ": bId-" + m + ": bName-" + str + ": attrId-" + (String)localObject2);
        ((SSPProtocol)localObject1).dataAddArrayType(paramList1, localHandle2);
        i += 1;
        break label126;
        label661: m = ((SSP_NEW_QT_FM_API.ClassItem)paramList3.get(i)).classId;
        str = ((SSP_NEW_QT_FM_API.ClassItem)paramList3.get(i)).className;
        n = ((SSP_NEW_QT_FM_API.ClassItem)paramList3.get(i)).sectionId;
        localObject2 = ((SSPProtocol)localObject1).sspDataNewBaseType("(isi)", new Object[] { Integer.valueOf(m), str, Integer.valueOf(n) });
        LogChuxl("contentList..." + i + ": cId-" + m + ": cName-" + str + ": sectionId-" + n);
        ((SSPProtocol)localObject1).dataAddArrayType(paramList2, (Handle)localObject2);
        i += 1;
      }
      catch (NoSuchMethodException paramObject)
      {
        LogChuxl("NoSuchMethodException...");
        continue;
      }
      catch (IllegalAccessException paramObject)
      {
        LogChuxl("IllegalAccessException...");
        continue;
      }
      catch (IllegalArgumentException paramObject)
      {
        LogChuxl("IllegalArgumentException...");
        continue;
      }
      catch (InvocationTargetException paramObject)
      {
        LogChuxl("InvocationTargetException...");
        continue;
      }
      LogChuxl("replyAllList...fail..start");
      replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiviviv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0}]}" }));
      LogChuxl("replyAllList...fail..end");
    }
  }

  public void replyCityList(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replyCityList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyCityList...Msg:" + paramObject);
        while (true)
        {
          label212: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(ss)", new Object[] { ((SSP_NEW_QT_FM_API.CityItem)paramList.get(i)).attrId, ((SSP_NEW_QT_FM_API.CityItem)paramList.get(i)).cityName }));
          i += 1;
          break;
          LogChuxl("replyCityList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyCityList...sfail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label212;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label212;
      }
      catch (IllegalAccessException paramObject)
      {
        break label212;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label212;
      }
    }
  }

  public void replyCollectList(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replyCollectList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        LogChuxl("replyCollectList..start.");
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyCollectList...Msg:" + paramObject);
        while (true)
        {
          label219: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(iiss)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioId), Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).type), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioName, ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).newProgramName }));
          i += 1;
          break;
          LogChuxl("replyCollectList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyCollectList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label219;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label219;
      }
      catch (IllegalAccessException paramObject)
      {
        break label219;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label219;
      }
    }
  }

  public void replyCollectProgram(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    e = true;
    LogChuxl("replyCollectProgram start");
    if (paramObject != null)
    {
      paramObject = (Hashtable)paramObject;
      String str = (String)paramObject.get("funcID");
      paramObject = DataParser.createData(((Integer)paramObject.get("flowID")).intValue(), "QINGTING", str, new String[] { a("(iii)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) }) });
      replay(paramObject);
      LogChuxl("replyCollectProgram...Msg:" + paramObject);
      TimeRecord();
    }
  }

  public void replyDownLoadProgramList(Object paramObject, int paramInt1, int paramInt2, int paramInt3, List paramList)
  {
    e = true;
    LogChuxl("replyDownLoadRadioList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyDownLoadRadioList...Msg:" + paramObject);
        while (true)
        {
          label220: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(isi)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).programId), ((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).programName, Integer.valueOf((int)((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).totalTime) }));
          i += 1;
          break;
          LogChuxl("replyDownLoadRadioList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyDownLoadRadioList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label220;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label220;
      }
      catch (IllegalAccessException paramObject)
      {
        break label220;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label220;
      }
    }
  }

  public void replyDownLoadRadioList(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replyDownLoadRadioList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyDownLoadRadioList...Msg:" + paramObject);
        while (true)
        {
          label212: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(isisi)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioName, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).type), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).thumb, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).fileNum) }));
          i += 1;
          break;
          LogChuxl("replyDownLoadRadioList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyDownLoadRadioList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label212;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label212;
      }
      catch (IllegalAccessException paramObject)
      {
        break label212;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label212;
      }
    }
  }

  public void replyExitApp(Object paramObject)
  {
    LogChuxl("replyExitApp...");
    if (paramObject != null)
    {
      paramObject = (Hashtable)paramObject;
      String str = (String)paramObject.get("funcID");
      paramObject = DataParser.createData(((Integer)paramObject.get("flowID")).intValue(), "QINGTING", str, new String[0]);
      replay(paramObject);
      LogChuxl("replyExitApp...Msg:" + paramObject);
    }
  }

  public void replyHeartBeat()
  {
    LogChuxl("replyHeartBeat...");
    String str = DataParser.createData(0, "QINGTING", "heartbeat", new String[0]);
    replay(str);
    LogChuxl("replyHeartBeat...Msg:" + str);
  }

  public void replyImageToCar(Object paramObject, int paramInt1, int paramInt2, String paramString, Bitmap paramBitmap)
  {
    e = true;
    paramObject = (Hashtable)paramObject;
    String str = (String)paramObject.get("funcID");
    int i = ((Integer)paramObject.get("flowID")).intValue();
    if (paramBitmap != null)
    {
      LogChuxl("replyImageToCar...bitmap != null");
      paramObject = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 70, paramObject);
    }
    while (true)
    {
      try
      {
        paramObject.flush();
        paramBitmap = paramObject.toByteArray();
        paramObject.close();
        paramObject = Base64.encodeToString(paramBitmap, 0);
        if (paramObject == null)
          break label199;
        paramObject = a("(iiss)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString, paramObject });
        LogChuxl("replyImageToCar...start");
        replay(DataParser.createData(i, "QINGTING", str, new String[] { paramObject }));
        LogChuxl("replyImageToCar...end");
        TimeRecord();
        return;
      }
      catch (IOException paramObject)
      {
        LogChuxl("IOException:" + paramObject.getMessage());
      }
      paramObject = null;
      continue;
      label199: LogChuxl("replyImageToCar...fail..start");
      replay(DataParser.createData(i, "QINGTING", str, new String[] { "{\"D\":1,\"T\":\"(iiss)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"s\",\"V\":\"\"}]}" }));
      LogChuxl("replyImageToCar...fail..end");
    }
  }

  public void replyKeyWordList(Object paramObject, int paramInt1, int paramInt2, String[] paramArrayOfString)
  {
    e = true;
    LogChuxl("replyKeyWordList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramArrayOfString != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramArrayOfString.length;
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramArrayOfString = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramArrayOfString });
        ((SSPProtocol)localObject1).sspDataRelease(paramArrayOfString);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyKeyWordList...Msg:" + paramObject);
        while (true)
        {
          label208: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("s", new Object[] { paramArrayOfString[i] }));
          i += 1;
          break;
          LogChuxl("replyKeyWordList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyKeyWordList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label208;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label208;
      }
      catch (IllegalAccessException paramObject)
      {
        break label208;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label208;
      }
    }
  }

  public void replyLabelList(Object paramObject, int paramInt1, int paramInt2, int paramInt3, int paramInt4, List paramList)
  {
    e = true;
    LogChuxl("replyLabelList...11111");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int k = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int m;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      m = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= m)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiiiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(k, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyLabelList...Msg:" + paramObject);
        while (true)
        {
          label228: TimeRecord();
          return;
          String str1 = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).attrId;
          String str2 = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).attrName;
          List localList = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).recmdList;
          Handle localHandle = ((SSPProtocol)localObject1).sspDataNewArrayType();
          int j = 0;
          while (true)
          {
            if (j >= localList.size())
            {
              ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(ssiv)", new Object[] { str1, str2, Integer.valueOf(localList.size()), localHandle }));
              i += 1;
              break;
            }
            ((SSPProtocol)localObject1).dataAddArrayType(localHandle, ((SSPProtocol)localObject1).sspDataNewBaseType("(iiiss)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).radioId), Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).id), Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).type), ((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).name, ((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).thumb }));
            j += 1;
          }
          LogChuxl("replyLabelList...fail..start");
          replay(DataParser.createData(k, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiiiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyLabelList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label228;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label228;
      }
      catch (IllegalAccessException paramObject)
      {
        break label228;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label228;
      }
    }
  }

  public void replyLiveRadioList(Object paramObject, int paramInt1, int paramInt2, String paramString, int paramInt3, List paramList)
  {
    e = true;
    LogChuxl("replyLiveRadioList...");
    Object localObject = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject).get("funcID");
    int j = ((Integer)((Hashtable)localObject).get("flowID")).intValue();
    Handle localHandle;
    int k;
    int i;
    if (paramList != null)
    {
      localObject = SSPProtocol.getInstance();
      localHandle = ((SSPProtocol)localObject).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramString = ((SSPProtocol)localObject).sspDataNewBaseType("(iisiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString, Integer.valueOf(paramInt3), localHandle });
      try
      {
        paramList = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        paramList.setAccessible(true);
        paramList = (String)paramList.invoke(localObject, new Object[] { paramString });
        ((SSPProtocol)localObject).sspDataRelease(paramString);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { paramList });
        replay(paramObject);
        LogChuxl("replyLiveRadioList...Msg:" + paramObject);
        while (true)
        {
          label225: TimeRecord();
          return;
          ((SSPProtocol)localObject).dataAddArrayType(localHandle, ((SSPProtocol)localObject).sspDataNewBaseType("(issi)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioName, ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).playingRadio, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).isCollect) }));
          i += 1;
          break;
          LogChuxl("replyLiveRadioList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iisiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyLiveRadioList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label225;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label225;
      }
      catch (IllegalAccessException paramObject)
      {
        break label225;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label225;
      }
    }
  }

  public void replyLivingRadio(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replyLivingRadio...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyLivingRadio...Msg:" + paramObject);
        while (true)
        {
          label212: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(isssi)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioName, ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).playingRadio, ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).thumb, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).isCollect) }));
          i += 1;
          break;
          LogChuxl("replyLivingRadio...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyLivingRadio...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label212;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label212;
      }
      catch (IllegalAccessException paramObject)
      {
        break label212;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label212;
      }
    }
  }

  public void replyMatchResult(Object paramObject, int paramInt)
  {
    e = true;
    LogChuxl("replyMatchResult start");
    String str;
    int i;
    if (paramObject != null)
    {
      paramObject = (Hashtable)paramObject;
      str = (String)paramObject.get("funcID");
      i = ((Integer)paramObject.get("flowID")).intValue();
      paramObject = "";
      switch (paramInt)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      paramObject = DataParser.createData(i, "QINGTING", str, new String[] { a("(is)", new Object[] { Integer.valueOf(paramInt), paramObject }) });
      replay(paramObject);
      LogChuxl("replyMatchResult...Msg:" + paramObject);
      TimeRecord();
      return;
      paramObject = "版本相同";
      continue;
      paramObject = "车机版本低于手机版本，请在手机助手更新栏中进行更新";
      continue;
      paramObject = "手机版本低于车机版本，请在手机助手更新栏中进行更新";
    }
  }

  public void replyProgramList(Object paramObject, int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, int paramInt4, List paramList)
  {
    e = true;
    LogChuxl("replyProgramList...");
    Object localObject = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject).get("funcID");
    int j = ((Integer)((Hashtable)localObject).get("flowID")).intValue();
    Handle localHandle;
    int k;
    int i;
    if (paramList != null)
    {
      localObject = SSPProtocol.getInstance();
      localHandle = ((SSPProtocol)localObject).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramString1 = ((SSPProtocol)localObject).sspDataNewBaseType("(isiisiv)", new Object[] { Integer.valueOf(paramInt1), paramString1, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramString2, Integer.valueOf(paramInt4), localHandle });
      try
      {
        paramString2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        paramString2.setAccessible(true);
        paramString2 = (String)paramString2.invoke(localObject, new Object[] { paramString1 });
        ((SSPProtocol)localObject).sspDataRelease(paramString1);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { paramString2 });
        replay(paramObject);
        LogChuxl("replyProgramList...Msg:" + paramObject);
        while (true)
        {
          label237: TimeRecord();
          return;
          ((SSPProtocol)localObject).dataAddArrayType(localHandle, ((SSPProtocol)localObject).sspDataNewBaseType("(iss)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).programId), ((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).programName, getStandardTime(((SSP_NEW_QT_FM_API.ProgramItem)paramList.get(i)).updateTime / 1000L, "yyyy年MM月dd日 HH:mm:ss") }));
          i += 1;
          break;
          LogChuxl("replyProgramList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(isiisiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyProgramList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label237;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label237;
      }
      catch (IllegalAccessException paramObject)
      {
        break label237;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label237;
      }
    }
  }

  public void replyRadioList(Object paramObject, int paramInt1, int paramInt2, String paramString, int paramInt3, List paramList)
  {
    e = true;
    LogChuxl("replyRadioList...");
    Object localObject = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject).get("funcID");
    int j = ((Integer)((Hashtable)localObject).get("flowID")).intValue();
    Handle localHandle;
    int k;
    int i;
    if (paramList != null)
    {
      localObject = SSPProtocol.getInstance();
      localHandle = ((SSPProtocol)localObject).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramString = ((SSPProtocol)localObject).sspDataNewBaseType("(iisiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramString, Integer.valueOf(paramInt3), localHandle });
      try
      {
        paramList = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        paramList.setAccessible(true);
        paramList = (String)paramList.invoke(localObject, new Object[] { paramString });
        ((SSPProtocol)localObject).sspDataRelease(paramString);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { paramList });
        replay(paramObject);
        LogChuxl("replyRadioList...Msg:" + paramObject);
        while (true)
        {
          label225: TimeRecord();
          return;
          ((SSPProtocol)localObject).dataAddArrayType(localHandle, ((SSPProtocol)localObject).sspDataNewBaseType("(iisis)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).type), Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).radioName, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).isCollect), ((SSP_NEW_QT_FM_API.RadioItem)paramList.get(i)).thumb }));
          i += 1;
          break;
          LogChuxl("replyRadioList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iisiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyRadioList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label225;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label225;
      }
      catch (IllegalAccessException paramObject)
      {
        break label225;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label225;
      }
    }
  }

  public void replyRecentList(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replyRencentList...");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int j = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int k;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      k = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replyRencentList...Msg:" + paramObject);
        while (true)
        {
          label212: TimeRecord();
          return;
          ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(iiiisss)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).radioType), Integer.valueOf(((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).radioId), Integer.valueOf(((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).type), Integer.valueOf(((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).id), ((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).thumb, ((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).name, getStandardTime(((SSP_NEW_QT_FM_API.RecentItem)paramList.get(i)).listenTime, "yyyy-MM-dd HH:mm:ss") }));
          i += 1;
          break;
          LogChuxl("replyRencentList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replyRencentList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label212;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label212;
      }
      catch (IllegalAccessException paramObject)
      {
        break label212;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label212;
      }
    }
  }

  public void replyResultList(Object paramObject, int paramInt1, int paramInt2, int paramInt3, List paramList1, int paramInt4, List paramList2, int paramInt5, List paramList3)
  {
    e = true;
    LogChuxl("replyResultList...");
    Object localObject = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject).get("funcID");
    int j = ((Integer)((Hashtable)localObject).get("flowID")).intValue();
    Handle localHandle;
    int k;
    int i;
    if ((paramList1 != null) && (paramList2 != null) && (paramList3 != null))
    {
      localObject = SSPProtocol.getInstance();
      localHandle = ((SSPProtocol)localObject).sspDataNewArrayType();
      k = paramList1.size();
      i = 0;
    }
    while (true)
    {
      if (i >= k)
      {
        paramList1 = ((SSPProtocol)localObject).sspDataNewArrayType();
        k = paramList2.size();
        i = 0;
        label110: if (i < k)
          break label423;
        paramList2 = ((SSPProtocol)localObject).sspDataNewArrayType();
        k = paramList3.size();
        i = 0;
        label136: if (i < k)
          break label530;
        paramList1 = ((SSPProtocol)localObject).sspDataNewBaseType("(iiiviviv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), localHandle, Integer.valueOf(paramInt4), paramList1, Integer.valueOf(paramInt5), paramList2 });
      }
      try
      {
        paramList2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        paramList2.setAccessible(true);
        paramList2 = (String)paramList2.invoke(localObject, new Object[] { paramList1 });
        ((SSPProtocol)localObject).sspDataRelease(paramList1);
        paramObject = DataParser.createData(j, "QINGTING", paramObject, new String[] { paramList2 });
        replay(paramObject);
        LogChuxl("replyResultList...Msg:" + paramObject);
        while (true)
        {
          label311: TimeRecord();
          return;
          ((SSPProtocol)localObject).dataAddArrayType(localHandle, ((SSPProtocol)localObject).sspDataNewBaseType("(isis)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList1.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList1.get(i)).radioName, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList1.get(i)).type), ((SSP_NEW_QT_FM_API.RadioItem)paramList1.get(i)).className }));
          i += 1;
          break;
          label423: ((SSPProtocol)localObject).dataAddArrayType(paramList1, ((SSPProtocol)localObject).sspDataNewBaseType("(isis)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList2.get(i)).radioId), ((SSP_NEW_QT_FM_API.RadioItem)paramList2.get(i)).radioName, Integer.valueOf(((SSP_NEW_QT_FM_API.RadioItem)paramList2.get(i)).type), ((SSP_NEW_QT_FM_API.RadioItem)paramList2.get(i)).className }));
          i += 1;
          break label110;
          label530: ((SSPProtocol)localObject).dataAddArrayType(paramList2, ((SSPProtocol)localObject).sspDataNewBaseType("(issiis)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).programId), ((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).programName, ((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).radioName, Integer.valueOf(((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).radioId), Integer.valueOf(((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).radioType), ((SSP_NEW_QT_FM_API.ProgramItem)paramList3.get(i)).thumb }));
          i += 1;
          break label136;
          LogChuxl("replyResultList...fail..start");
          replay(DataParser.createData(j, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiiviviv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":0,\"T\":\"i\",\"V\":0}]}" }));
          LogChuxl("replyResultList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label311;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label311;
      }
      catch (IllegalAccessException paramObject)
      {
        break label311;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label311;
      }
    }
  }

  public void replySelectList(Object paramObject, int paramInt1, int paramInt2, List paramList)
  {
    e = true;
    LogChuxl("replySelectList...11111");
    Object localObject1 = (Hashtable)paramObject;
    paramObject = (String)((Hashtable)localObject1).get("funcID");
    int k = ((Integer)((Hashtable)localObject1).get("flowID")).intValue();
    Object localObject2;
    int m;
    int i;
    if (paramList != null)
    {
      localObject1 = SSPProtocol.getInstance();
      localObject2 = ((SSPProtocol)localObject1).sspDataNewArrayType();
      m = paramList.size();
      i = 0;
    }
    while (true)
    {
      if (i >= m)
        paramList = ((SSPProtocol)localObject1).sspDataNewBaseType("(iiv)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), localObject2 });
      try
      {
        localObject2 = SSPProtocol.class.getDeclaredMethod("a", new Class[] { Handle.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = (String)((Method)localObject2).invoke(localObject1, new Object[] { paramList });
        ((SSPProtocol)localObject1).sspDataRelease(paramList);
        paramObject = DataParser.createData(k, "QINGTING", paramObject, new String[] { localObject2 });
        replay(paramObject);
        LogChuxl("replySelectList...Msg:" + paramObject);
        while (true)
        {
          label212: TimeRecord();
          return;
          String str1 = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).attrId;
          String str2 = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).attrName;
          List localList = ((SSP_NEW_QT_FM_API.LabelItem)paramList.get(i)).recmdList;
          Handle localHandle = ((SSPProtocol)localObject1).sspDataNewArrayType();
          int j = 0;
          while (true)
          {
            if (j >= localList.size())
            {
              ((SSPProtocol)localObject1).dataAddArrayType((Handle)localObject2, ((SSPProtocol)localObject1).sspDataNewBaseType("(ssiv)", new Object[] { str1, str2, Integer.valueOf(localList.size()), localHandle }));
              i += 1;
              break;
            }
            ((SSPProtocol)localObject1).dataAddArrayType(localHandle, ((SSPProtocol)localObject1).sspDataNewBaseType("(iiiss)", new Object[] { Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).radioId), Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).id), Integer.valueOf(((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).type), ((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).name, ((SSP_NEW_QT_FM_API.RecommendItem)localList.get(j)).thumb }));
            j += 1;
          }
          LogChuxl("replySelectList...fail..start");
          replay(DataParser.createData(k, "QINGTING", paramObject, new String[] { "{\"D\":1,\"T\":\"(iiv)\",\"V\":[{\"D\":0,\"T\":\"i\",\"V\":1},{\"D\":0,\"T\":\"i\",\"V\":0},{\"D\":1,\"T\":\"a\",\"V\":[]}]}" }));
          LogChuxl("replySelectList...fail..end");
        }
      }
      catch (InvocationTargetException paramObject)
      {
        break label212;
      }
      catch (IllegalArgumentException paramObject)
      {
        break label212;
      }
      catch (IllegalAccessException paramObject)
      {
        break label212;
      }
      catch (NoSuchMethodException paramObject)
      {
        break label212;
      }
    }
  }

  public void replyWakeUp()
  {
    e = true;
    LogChuxl("notifyConnectStatus 显示遮罩..start");
    this.h.notifyConnectStatus(0);
    LogChuxl("notifyConnectStatus 显示遮罩..end");
    LogChuxl("replyWakeUp start");
    replay(DataParser.createData(0, "QINGTING", "wakeup", new String[0]));
    LogChuxl("replyWakeUp end");
    TimeRecord();
  }

  public void sendCarTip(String paramString)
  {
    e = true;
    LogChuxl("sendCarTip...start");
    paramString = DataParser.createData(0, "QINGTING", "cartip", new String[] { a("s", new Object[] { paramString }) });
    replay(paramString);
    LogChuxl("sendCarTip...Msg:" + paramString);
    TimeRecord();
  }

  public void sendImageToCar(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    e = true;
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramBitmap != null)
    {
      localObject1 = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.PNG, 70, (OutputStream)localObject1);
    }
    try
    {
      ((ByteArrayOutputStream)localObject1).flush();
      paramBitmap = ((ByteArrayOutputStream)localObject1).toByteArray();
      ((ByteArrayOutputStream)localObject1).close();
      localObject1 = Base64.encodeToString(paramBitmap, 0);
      if (localObject1 != null)
      {
        paramString2 = a("(ss)", new Object[] { paramString2, localObject1 });
        LogChuxl("sendImageToCar...start");
        replay(DataParser.createData(0, "QINGTING", paramString1, new String[] { paramString2 }));
        LogChuxl("sendImageToCar...end");
      }
      while (true)
      {
        TimeRecord();
        return;
        LogChuxl("sendImageToCar...fail..start");
        replay(DataParser.createData(0, "QINGTING", paramString1, new String[] { "{\"D\":1,\"T\":\"(ss)\",\"V\":[{\"D\":0,\"T\":\"s\",\"V\":\"\"},{\"D\":0,\"T\":\"s\",\"V\":\"\"}]}" }));
        LogChuxl("sendImageToCar...fail..end");
      }
    }
    catch (IOException paramBitmap)
    {
      while (true)
        localObject1 = localObject2;
    }
  }

  public void sendPlayOrPause(int paramInt)
  {
    e = true;
    LogChuxl("sendPlayOrPause...start...");
    String str = DataParser.createData(0, "QINGTING", "phoneplayorpause", new String[] { a("i", new Object[] { Integer.valueOf(paramInt) }) });
    replay(str);
    LogChuxl("sendPlayOrPause...msg:" + str);
    LogChuxl("sendPlayOrPause...end...");
    TimeRecord();
  }

  public void sendProgramInfo(String paramString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, String paramString2, String paramString3, int paramInt8, int paramInt9, String paramString4)
  {
    e = true;
    paramString2 = a("(iiiiiiissiis)", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5), Integer.valueOf(getSeconds(paramInt6)), Integer.valueOf(getSeconds(paramInt7)), paramString2, paramString3, Integer.valueOf(paramInt8), Integer.valueOf(paramInt9), paramString4 });
    LogChuxl("sendProgramInfo...start");
    LogChuxl("curTime" + paramInt4);
    LogChuxl("totalTime" + paramInt5);
    LogChuxl("startTime" + paramInt6);
    LogChuxl("endTime" + paramInt7);
    LogChuxl("getSeconds(startTime)" + getSeconds(paramInt6));
    LogChuxl("getSeconds(endTime)" + getSeconds(paramInt7));
    LogChuxl("radioId" + paramInt8);
    LogChuxl("radioType" + paramInt9);
    LogChuxl("radioName" + paramString4);
    paramString1 = DataParser.createData(0, "QINGTING", paramString1, new String[] { paramString2 });
    replay(paramString1);
    LogChuxl("sendProgramInfo...end");
    LogChuxl("sendProgramInfo...Msg:" + paramString1);
    TimeRecord();
  }

  public void setListener(QTFM_NEW_RequestListener paramQTFM_NEW_RequestListener)
  {
    this.h = paramQTFM_NEW_RequestListener;
    TimeRecord();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.api.SSP_NEW_QT_FM_API
 * JD-Core Version:    0.6.2
 */