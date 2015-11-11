package fm.qingting.qtradio.model;

import fm.qingting.qtradio.im.info.GroupInfo;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class ProgramNodeV5 extends Node
{
  public static final int LIVE_PROGRAM = 1;
  public static final int PAST_PROGRAM = 3;
  public static final int RESERVE_PROGRAM = 2;
  private long absoluteEndTime = -1L;
  private long absoluteStartTime = -1L;
  public boolean available = true;
  public String backgroundPic;
  public int belongCatId = 0;
  public int belongChannelId;
  public String belongChannelLetter;
  public String belongChannelName;
  public int belongProgramId;
  public int broadcastDuration = 0;
  public String broadcastEndTime = "23:59";
  public String broadcastTime = "";
  public int categoryId;
  public int channelType = 0;
  public int dayOfWeek;
  public String desc;
  public int dimensionId;
  public Download downloadInfo;
  private transient int endTime = -1;
  public String from;
  public int listenerCnt;
  private List<BroadcastDetail> lstBroadcastDetail;
  public List<String> lstOriginal;
  public List<String> lstPlayUrl = new ArrayList();
  private int mAvailableUrlIndex = -1;
  public String mDownLoadPath;
  public transient GroupInfo mGroupInfo;
  public String mHttpUrl;
  public List<Integer> mLstBitrate;
  public List<String> mLstBitratesPath;
  public List<BroadcasterNode> mLstBroadcasters = new ArrayList();
  private List<String> mLstSourceUrls;
  public List<Integer> mLstTransBitrate;
  private String mOriginalUrls;
  public String mParentCover;
  public String mParentOutline;
  public String mReplay;
  private transient String mReplayHighUrl;
  private transient String mReplayLowUrl;
  private String mReplayQueryString;
  public int mSequence;
  private int mSetting = -1;
  private String mSharedPageUrls;
  private String mSharedUrls;
  private String mSourceUrls;
  public String mTranscode;
  private transient String mTranscodeHightUrl;
  private transient String mTranscodeLowUrl;
  public transient Map<Integer, Integer> mapLinkInfo;
  private transient String nextEndTime = "23:59";
  private transient String nextStartTime = "";
  public String parentDisplayName;
  public String parentName;
  private transient int playNextDW = 0;
  private transient int playNextEndTime = -1;
  private transient int playNextStartTime = -1;
  public String programId;
  public String programType = "program";
  public long publishTime;
  public String resourceId = "";
  public String speicalProgram = "live";
  public int startDayOfWeek;
  private transient int startTime = -1;
  public String title;
  public String topic;
  public int uniqueId;
  private long updateTime;
  public int virtualChannelId;
  public String weiboId;
  public String weiboNick;
  public int weight;

  public ProgramNodeV5()
  {
    this.nodeName = "program";
  }

  private long absoluteBaseTime()
  {
    long l = System.currentTimeMillis() / 1000L / 60L * 60L;
    int j = getDayOfWeek(l);
    l -= getRelativeTime(l * 1000L);
    if (j != this.dayOfWeek)
    {
      int i = j;
      if (j < this.startDayOfWeek)
        i = j + 7;
      return l - (i - this.dayOfWeek) * 24 * 3600;
    }
    return l;
  }

  private String buildTimeParam(long paramLong)
  {
    String str3 = getYear(paramLong);
    String str2 = getMonth(paramLong);
    String str1 = getDayofMonth(paramLong);
    str3 = "" + str3;
    str3 = str3 + "M";
    str2 = str3 + str2;
    str2 = str2 + "D";
    str1 = str2 + str1;
    str1 = str1 + "h";
    str1 = str1 + getHour(paramLong);
    str1 = str1 + "m";
    str1 = str1 + getMinute(paramLong);
    str1 = str1 + "s";
    return str1 + "0";
  }

  private long getAbsoluteBroadcastTime(long paramLong)
  {
    return absoluteBaseTime() + paramLong;
  }

  private String getBitrateUrlsByMode(int paramInt)
  {
    if (paramInt == InfoManager.AUDIO_QUALITY_MODE.SMART.ordinal())
    {
      if (InfoManager.getInstance().hasMobileNetwork())
        return getFirstBitrateUrl();
      return getLastBitrateUrl();
    }
    if (paramInt == InfoManager.AUDIO_QUALITY_MODE.LOW_QUALITY.ordinal())
      return getFirstBitrateUrl();
    if (paramInt == InfoManager.AUDIO_QUALITY_MODE.HIGH_QUALITY.ordinal())
      return getLastBitrateUrl();
    return getTranscodeUrls(InfoManager.AUDIO_QUALITY_MODE.LOW_QUALITY.ordinal());
  }

  private String getBroadcastEndTimeByDW(int paramInt)
  {
    if ((this.lstBroadcastDetail != null) && (this.lstBroadcastDetail.size() > 0))
    {
      int i = 0;
      while (i < this.lstBroadcastDetail.size())
      {
        if (((BroadcastDetail)this.lstBroadcastDetail.get(i)).dayofweek == paramInt)
          return ((BroadcastDetail)this.lstBroadcastDetail.get(i)).endTime;
        i += 1;
      }
    }
    return null;
  }

  private List<BroadcasterNode> getBroadcastNodeByDW(int paramInt)
  {
    return null;
  }

  private String getBroadcastTimeByDW(int paramInt)
  {
    if ((this.lstBroadcastDetail != null) && (this.lstBroadcastDetail.size() > 0))
    {
      int i = 0;
      while (i < this.lstBroadcastDetail.size())
      {
        if (((BroadcastDetail)this.lstBroadcastDetail.get(i)).dayofweek == paramInt)
          return ((BroadcastDetail)this.lstBroadcastDetail.get(i)).startTime;
        i += 1;
      }
    }
    return null;
  }

  private int getDayOfWeek(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return localCalendar.get(7);
  }

  private String getDayofMonth(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.valueOf(localCalendar.get(5));
  }

  private String getFirstBitrateUrl()
  {
    if ((this.mLstBitratesPath != null) && (this.mLstBitratesPath.size() > 0))
      return getUrlsByPath((String)this.mLstBitratesPath.get(0));
    return getTranscodeUrls(InfoManager.AUDIO_QUALITY_MODE.LOW_QUALITY.ordinal());
  }

  private String getHour(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.format(Locale.US, "%02d", new Object[] { Integer.valueOf(localCalendar.get(11)) });
  }

  private String getLastBitrateUrl()
  {
    if ((this.mLstBitratesPath != null) && (this.mLstBitratesPath.size() > 0))
      return getUrlsByPath((String)this.mLstBitratesPath.get(this.mLstBitratesPath.size() - 1));
    return getTranscodeUrls(InfoManager.AUDIO_QUALITY_MODE.HIGH_QUALITY.ordinal());
  }

  private String getMinute(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.format(Locale.US, "%02d", new Object[] { Integer.valueOf(localCalendar.get(12)) });
  }

  private String getMonth(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return String.valueOf(localCalendar.get(2) + 1);
  }

  private int getNearestDW()
  {
    int k = 0;
    int j = Calendar.getInstance().get(7);
    if (j == 7)
      j = 0;
    while (true)
    {
      int i;
      if ((this.lstBroadcastDetail != null) && (this.lstBroadcastDetail.size() > 0))
      {
        int m = ((BroadcastDetail)this.lstBroadcastDetail.get(0)).dayofweek;
        i = 10;
        while (k < this.lstBroadcastDetail.size())
        {
          if ((((BroadcastDetail)this.lstBroadcastDetail.get(k)).dayofweek <= j) || (((BroadcastDetail)this.lstBroadcastDetail.get(k)).dayofweek >= i))
            break label157;
          i = ((BroadcastDetail)this.lstBroadcastDetail.get(k)).dayofweek;
          k += 1;
        }
        j = m;
      }
      while (true)
      {
        if (i == 10)
        {
          if (j < 10)
            return j;
          return -1;
        }
        return i;
        label157: break;
        j = 10;
        i = 10;
      }
    }
  }

  private String getOriginalUrls()
  {
    if (!InfoManager.getInstance().enableOriginalSource())
      return "";
    if ((this.mOriginalUrls == null) && (this.lstOriginal != null))
    {
      String str = "";
      int i = 0;
      while (i < this.lstOriginal.size())
      {
        str = str + (String)this.lstOriginal.get(i);
        str = str + ";;";
        i += 1;
      }
      this.mOriginalUrls = str;
    }
    return this.mOriginalUrls;
  }

  private long getRelativeTime(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(paramLong);
    int i = localCalendar.get(11);
    return localCalendar.get(12) * 60 + i * 60 * 60;
  }

  private int getTime(String paramString)
  {
    try
    {
      paramString = Pattern.compile("\\D+").split(paramString);
      if (paramString.length == 2)
      {
        int i = Integer.parseInt(paramString[0]);
        int j = Integer.parseInt(paramString[1]);
        return j * 60 + i * 3600;
      }
    }
    catch (Exception paramString)
    {
    }
    return -1;
  }

  private String getTranscodeUrls(int paramInt)
  {
    return null;
  }

  private String getUrlsByPath(String paramString)
  {
    return null;
  }

  private String getYear(long paramLong)
  {
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTimeInMillis(1000L * paramLong);
    return new SimpleDateFormat("yy", Locale.CHINESE).format(localCalendar.getTime());
  }

  private String msToTimeFormat(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat("HH:mm", Locale.US).format(localDate);
  }

  public void autoSetAbsoluteEndTime(long paramLong)
  {
    if (this.speicalProgram.equalsIgnoreCase("specific"))
      this.absoluteEndTime = paramLong;
    if (this.endTime == -1)
      this.endTime = ((int)getRelativeTime(paramLong * 1000L));
    if ((this.broadcastEndTime == null) || (this.broadcastEndTime.equalsIgnoreCase("23:59")))
      this.broadcastEndTime = msToTimeFormat(paramLong * 1000L);
  }

  public void autoSetAbsoluteTime(long paramLong)
  {
    if (this.startTime == -1)
      this.startTime = ((int)getRelativeTime(paramLong * 1000L));
    if ((this.broadcastTime == null) || (this.broadcastTime.equalsIgnoreCase("")))
      this.broadcastTime = msToTimeFormat(paramLong * 1000L);
    int i = getDuration();
    if (i > 0)
      autoSetAbsoluteEndTime(i + paramLong);
  }

  public boolean calcNextPlayTime()
  {
    this.playNextDW = getNearestDW();
    if (this.playNextDW == -1)
      return false;
    String str = getBroadcastTimeByDW(this.playNextDW);
    if (str != null)
    {
      this.nextStartTime = str;
      this.playNextStartTime = getTime(this.nextStartTime);
    }
    str = getBroadcastEndTimeByDW(this.playNextDW);
    if (str != null)
    {
      this.nextEndTime = str;
      this.playNextEndTime = getTime(this.nextEndTime);
      if (this.playNextEndTime < this.playNextStartTime)
        this.playNextEndTime += 86400;
    }
    return true;
  }

  public void clearDownloadState()
  {
    this.mAvailableUrlIndex = -1;
  }

  public int endTime()
  {
    if (-1 == this.endTime);
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.broadcastEndTime);
      if (arrayOfString.length == 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.endTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label54: if (this.endTime < startTime())
        this.endTime += 86400;
      return this.endTime;
    }
    catch (Exception localException)
    {
      break label54;
    }
  }

  public long getAbsoluteEndTime()
  {
    if ((this.speicalProgram.equalsIgnoreCase("specific")) && (this.absoluteEndTime != 0L))
      return this.absoluteEndTime;
    return getAbsoluteBroadcastTime(endTime());
  }

  public long getAbsoluteStartTime()
  {
    if ((this.speicalProgram.equalsIgnoreCase("specific")) && (this.absoluteStartTime != 0L))
      return this.absoluteStartTime;
    int i = startTime();
    if (i == -1)
    {
      this.broadcastTime = "00:00";
      this.broadcastEndTime = "23:59";
      return 0L;
    }
    return getAbsoluteBroadcastTime(i);
  }

  public String getBroadCasterNames()
  {
    if (this.mLstBroadcasters == null)
      return "";
    Iterator localIterator = this.mLstBroadcasters.iterator();
    StringBuilder localStringBuilder = new StringBuilder();
    while (localIterator.hasNext())
    {
      BroadcasterNode localBroadcasterNode = (BroadcasterNode)localIterator.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(" ");
      localStringBuilder.append(localBroadcasterNode.nick);
    }
    return localStringBuilder.toString();
  }

  public String getBroadCasterNamesForAt()
  {
    return null;
  }

  public String getBroadCasterWeiboNames()
  {
    return null;
  }

  public String getCurrDownloadUrl()
  {
    return null;
  }

  public int getCurrPlayStatus()
  {
    int j = 2;
    long l1 = System.currentTimeMillis() / 1000L;
    long l2 = getAbsoluteStartTime();
    long l3 = getAbsoluteEndTime();
    if (this.programType.equalsIgnoreCase("program"))
    {
      int i;
      if ((l2 <= l1) && (l3 > l1))
        i = 1;
      do
      {
        do
        {
          return i;
          i = j;
        }
        while (l2 > l1);
        i = j;
      }
      while (l3 >= l1);
      return 3;
    }
    return 3;
  }

  public String getDownLoadUrlPath()
  {
    String str2 = "" + this.mDownLoadPath;
    String str1 = str2;
    if (this.programType.equalsIgnoreCase("program"))
    {
      str1 = str2 + "&start=";
      if ((!this.speicalProgram.equalsIgnoreCase("specific")) || (this.absoluteStartTime == 0L))
        break label177;
    }
    label177: for (str1 = str1 + buildTimeParam(this.absoluteStartTime); ; str1 = str1 + buildTimeParam(getAbsoluteBroadcastTime(startTime())))
    {
      str1 = str1 + "&end=";
      if ((!this.speicalProgram.equalsIgnoreCase("specific")) || (this.absoluteEndTime == 0L))
        break;
      str1 = str1 + buildTimeParam(this.absoluteEndTime);
      return str1;
    }
    return str1 + buildTimeParam(getAbsoluteBroadcastTime(endTime()) + 5L);
  }

  public String getDownloadUrlForVirtualProgram()
  {
    return null;
  }

  public int getDuration()
  {
    if (this.broadcastDuration <= 0)
    {
      this.broadcastDuration = (endTime() - startTime());
      if (this.broadcastDuration <= 0)
        this.broadcastDuration += 86400;
    }
    return this.broadcastDuration;
  }

  public long getNextAbsolutePlayTime()
  {
    int i = 7;
    int j;
    if ((this.playNextDW > 0) && (this.playNextStartTime > 0))
    {
      j = (this.playNextDW + 7 - Calendar.getInstance().get(7)) % 7;
      if (j != 0)
        break label65;
    }
    while (true)
    {
      return getAbsoluteBroadcastTime(this.playNextStartTime) + i * 24 * 3600;
      return -1L;
      label65: i = j;
    }
  }

  public String getNextDownLoadUrl()
  {
    return null;
  }

  public String getReplayQueryString()
  {
    return this.mReplayQueryString;
  }

  public String getReplayUrlByEndTime(long paramLong)
  {
    return null;
  }

  public String getReplayUrls()
  {
    return null;
  }

  public String getReplayUrlsDirectly()
  {
    if (this.mReplayLowUrl != null)
      return this.mReplayLowUrl;
    if (this.mReplayHighUrl != null)
      return this.mReplayHighUrl;
    return null;
  }

  public String getSharedPageUrl()
  {
    return this.mSharedPageUrls;
  }

  public String getSharedSourceUrl()
  {
    if (getCurrPlayStatus() == 3)
    {
      if (this.mSetting == -1)
        this.mSetting = InfoManager.getInstance().getAudioQualitySetting();
      if (this.mSharedUrls == null)
        getTranscodeUrls(this.mSetting);
    }
    return this.mSharedUrls;
  }

  public String getSourceUrls()
  {
    int i = 0;
    int j = InfoManager.getInstance().getAudioQualitySetting();
    if (j != this.mSetting)
    {
      this.mSetting = j;
      i = 1;
    }
    if ((this.mSourceUrls != null) && (this.programType.equalsIgnoreCase("DownloadProgram")))
      return this.mSourceUrls;
    String str;
    if ((this.mSourceUrls == null) || (i != 0))
    {
      this.mSourceUrls = getTranscodeUrls(this.mSetting);
      this.mSourceUrls = getBitrateUrlsByMode(j);
      if (this.mSourceUrls == null)
        break label136;
      str = getOriginalUrls();
      if ((str != null) && (!str.equalsIgnoreCase("")))
        this.mSourceUrls += str;
    }
    while (true)
    {
      return this.mSourceUrls;
      label136: str = getOriginalUrls();
      if (str != null)
        this.mSourceUrls = str;
    }
  }

  public String getSourceUrlsDirectly()
  {
    return this.mSourceUrls;
  }

  public long getUpdateTime()
  {
    return this.updateTime;
  }

  public int getWeight()
  {
    return this.weight;
  }

  public boolean isDownloadProgram()
  {
    return (this.programType != null) && (this.programType.equalsIgnoreCase("DownloadProgram"));
  }

  public void recomputeBroadcastDetail()
  {
    int i = Calendar.getInstance().get(7);
    if (this.channelType == 1)
    {
      this.startDayOfWeek = i;
      this.dayOfWeek = this.startDayOfWeek;
    }
    String str = getBroadcastTimeByDW(i);
    if (str != null)
    {
      this.broadcastTime = str;
      this.startTime = getTime(this.broadcastTime);
    }
    str = getBroadcastEndTimeByDW(i);
    if (str != null)
    {
      this.broadcastEndTime = str;
      this.endTime = getTime(this.broadcastEndTime);
      if (this.endTime < this.startTime)
        this.endTime += 86400;
    }
    this.mLstBroadcasters = getBroadcastNodeByDW(i);
  }

  public void setAbsoluteStartTime(long paramLong)
  {
    if (this.speicalProgram.equalsIgnoreCase("specific"))
      this.absoluteStartTime = paramLong;
    this.startTime = ((int)getRelativeTime(paramLong * 1000L));
    this.broadcastTime = msToTimeFormat(paramLong * 1000L);
  }

  public void setBroadcastDetail(List<BroadcastDetail> paramList)
  {
    this.lstBroadcastDetail = paramList;
    recomputeBroadcastDetail();
  }

  public void setBroadcastEndTimeByDuration(int paramInt)
  {
    if (paramInt < 0)
      return;
    int i = startTime() + paramInt;
    paramInt = i / 3600;
    i -= paramInt * 3600;
    if (paramInt == 0);
    for (this.broadcastEndTime = "00"; i == 0; this.broadcastEndTime = String.valueOf(paramInt))
    {
      this.broadcastEndTime += ":";
      this.broadcastEndTime += "00";
      return;
    }
    this.broadcastEndTime += ":";
    this.broadcastEndTime += String.valueOf(i);
  }

  public void setPlayTimeDetail(List<BroadcastDetail> paramList)
  {
    this.lstBroadcastDetail = paramList;
  }

  public void setSharedPageUrl(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.mSharedPageUrls = paramString;
  }

  public void setSharedSourceUrl(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    this.mSharedUrls = paramString;
  }

  public void setSourceUrls(String paramString)
  {
    if (paramString == null)
      return;
    this.mSourceUrls = paramString;
  }

  public void setUpdateTime(long paramLong)
  {
    this.updateTime = paramLong;
  }

  public int startTime()
  {
    if (-1 == this.startTime);
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.broadcastTime);
      if (arrayOfString.length == 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.startTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label54: return this.startTime;
    }
    catch (Exception localException)
    {
      break label54;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramNodeV5
 * JD-Core Version:    0.6.2
 */