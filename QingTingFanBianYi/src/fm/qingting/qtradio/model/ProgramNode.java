package fm.qingting.qtradio.model;

import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.utils.TimeUtil;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class ProgramNode extends Node
{
  public static final int LIVE_PROGRAM = 1;
  public static final int PAST_PROGRAM = 3;
  public static final int RESERVE_PROGRAM = 2;
  private long _createTime = 0L;
  private long _updateTime = 0L;
  private long absoluteEndTime = -1L;
  private long absoluteStartTime = -1L;
  public boolean available = true;
  private int broadcastEndTime = -1;
  private int broadcastStartTime = -1;
  private int categoryId = -1;
  public int channelId;
  private String channelName;
  public int channelRatingStar = -1;
  public int channelType;
  public String createTime;
  public int dayOfWeek;
  public Download downloadInfo;
  public double duration;
  public String endTime;
  public int groupId = 0;
  public int id;
  public boolean isDownloadProgram = false;
  public List<String> lstAudioPath;
  public List<Integer> lstBitrate;
  public List<BroadcasterNode> lstBroadcaster;
  private int mAvailableUrlIndex = -1;
  private String mHighBitrateSource;
  public boolean mLiveInVirtual = false;
  private String mLowBitrateSource;
  private int mSetting = -1;
  public String mShareSourceUrl;
  public transient Map<Integer, Integer> mapLinkInfo;
  public int resId;
  public int sequence;
  public String startTime;
  public String title;
  public int uniqueId;
  public String updateTime;

  public ProgramNode()
  {
    this.nodeName = "program";
  }

  private long absoluteBaseTime()
  {
    long l1 = System.currentTimeMillis() / 1000L / 60L * 60L;
    int i = Calendar.getInstance().get(7);
    long l2 = l1 - TimeUtil.absoluteTimeToRelative(l1);
    l1 = l2;
    if (i != this.dayOfWeek)
    {
      if (i == 7)
      {
        if (this.dayOfWeek != 1)
          break label89;
        l1 = l2 + 86400L;
      }
    }
    else
      return l1;
    if ((i == 1) && (this.dayOfWeek == 7))
      return l2 - 86400L;
    label89: if (i < this.dayOfWeek)
      return l2 + (this.dayOfWeek - i) * 24 * 3600;
    return l2 - (i - this.dayOfWeek) * 24 * 3600;
  }

  private String buildTimeParam(long paramLong)
  {
    String str3 = TimeUtil.getYear(paramLong);
    String str2 = TimeUtil.getMonth(paramLong);
    String str1 = TimeUtil.getDayofMonth(paramLong);
    str3 = "" + str3;
    str3 = str3 + "M";
    str2 = str3 + str2;
    str2 = str2 + "D";
    str1 = str2 + str1;
    str1 = str1 + "h";
    str1 = str1 + TimeUtil.getHour(paramLong);
    str1 = str1 + "m";
    str1 = str1 + TimeUtil.getMinute(paramLong);
    str1 = str1 + "s";
    return str1 + "0";
  }

  private long getAbsoluteBroadcastTime(long paramLong)
  {
    return absoluteBaseTime() + paramLong;
  }

  private void setShareSourceUrl()
  {
    int j = 64;
    int i = j;
    if (this.lstBitrate != null)
    {
      i = j;
      if (this.lstBitrate.size() > 0)
        i = ((Integer)this.lstBitrate.get(this.lstBitrate.size() - 1)).intValue();
    }
    if (this.channelType == 0)
      if (getCurrPlayStatus() == 1)
        this.mShareSourceUrl = MediaCenter.getInstance().getShareUrl("radiohls", String.valueOf(this.resId), i);
    while (this.channelType != 1)
    {
      return;
      this.mShareSourceUrl = MediaCenter.getInstance().getShareReplayUrl(String.valueOf(this.resId), i, buildTimeParam(getAbsoluteStartTime()), buildTimeParam(getAbsoluteEndTime()));
      return;
    }
    String str2 = "";
    String str1 = str2;
    if (this.lstAudioPath != null)
    {
      str1 = str2;
      if (this.lstAudioPath.size() > 0)
        str1 = (String)this.lstAudioPath.get(this.lstAudioPath.size() - 1);
    }
    this.mShareSourceUrl = MediaCenter.getInstance().getShareUrl("virutalchannel", str1, 24);
  }

  public void clearDownloadState()
  {
    this.mAvailableUrlIndex = -1;
  }

  public int endTime()
  {
    if ((this.broadcastEndTime == -1) && (this.endTime != null));
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.endTime);
      if (arrayOfString.length >= 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.broadcastEndTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label60: if ((this.broadcastEndTime < startTime()) && (this.endTime != null))
        this.broadcastEndTime += 86400;
      return this.broadcastEndTime;
    }
    catch (Exception localException)
    {
      break label60;
    }
  }

  public long getAbsoluteEndTime()
  {
    if (this.absoluteEndTime >= 0L)
      return this.absoluteEndTime;
    if (endTime() == -1)
    {
      this.broadcastEndTime = getDuration();
      this.absoluteEndTime = this.broadcastEndTime;
      if (this.channelType == 1)
        return this.broadcastEndTime;
    }
    return getAbsoluteBroadcastTime(this.broadcastEndTime);
  }

  public long getAbsoluteStartTime()
  {
    if (this.absoluteStartTime >= 0L)
      return this.absoluteStartTime;
    int i = startTime();
    if (i == -1)
    {
      this.startTime = "00:00";
      this.broadcastStartTime = 0;
    }
    for (this.absoluteStartTime = 0L; ; this.absoluteStartTime = getAbsoluteBroadcastTime(i))
      return this.absoluteStartTime;
  }

  public String getBroadCasterNames()
  {
    if (this.lstBroadcaster == null)
      return "";
    Iterator localIterator = this.lstBroadcaster.iterator();
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
    if (this.lstBroadcaster == null)
      return "";
    Iterator localIterator = this.lstBroadcaster.iterator();
    StringBuilder localStringBuilder = new StringBuilder();
    while (localIterator.hasNext())
    {
      BroadcasterNode localBroadcasterNode = (BroadcasterNode)localIterator.next();
      if ((localBroadcasterNode.weiboName != null) && (!localBroadcasterNode.weiboName.equalsIgnoreCase("")) && (localBroadcasterNode.weiboName.equalsIgnoreCase("未知")))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(" ");
        localStringBuilder.append("@");
        localStringBuilder.append(localBroadcasterNode.weiboName);
      }
      else
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(" ");
        localStringBuilder.append(localBroadcasterNode.nick);
      }
    }
    return localStringBuilder.toString();
  }

  public String getBroadCasterWeiboNames()
  {
    if (this.lstBroadcaster == null)
      return "";
    Iterator localIterator = this.lstBroadcaster.iterator();
    StringBuilder localStringBuilder = new StringBuilder();
    while (localIterator.hasNext())
    {
      BroadcasterNode localBroadcasterNode = (BroadcasterNode)localIterator.next();
      if ((localBroadcasterNode.weiboName != null) && (!localBroadcasterNode.weiboName.equalsIgnoreCase("")))
      {
        if (localStringBuilder.length() > 0)
          localStringBuilder.append(" ");
        localStringBuilder.append("@");
        localStringBuilder.append(localBroadcasterNode.weiboName);
      }
    }
    return localStringBuilder.toString();
  }

  public int getCategoryId()
  {
    int i = -1;
    if (this.isDownloadProgram)
      i = DownLoadInfoNode.mDownloadId;
    ChannelNode localChannelNode;
    do
    {
      do
      {
        return i;
        if (this.categoryId != -1)
          return this.categoryId;
        if (!this.mLiveInVirtual)
          break;
        localChannelNode = ChannelHelper.getInstance().getChannel(this.channelId, 1);
      }
      while (localChannelNode == null);
      this.categoryId = localChannelNode.categoryId;
      return localChannelNode.categoryId;
      localChannelNode = ChannelHelper.getInstance().getChannel(this.channelId, this.channelType);
    }
    while (localChannelNode == null);
    this.categoryId = localChannelNode.categoryId;
    return localChannelNode.categoryId;
  }

  public String getChannelName()
  {
    ChannelNode localChannelNode;
    if ((this.channelName == null) || (this.channelName.equalsIgnoreCase("")))
    {
      if (!this.mLiveInVirtual)
        break label55;
      localChannelNode = ChannelHelper.getInstance().getChannel(this.channelId, 1);
      if (localChannelNode != null)
        this.channelName = localChannelNode.title;
    }
    while (true)
    {
      return this.channelName;
      label55: localChannelNode = ChannelHelper.getInstance().getChannel(this.channelId, this.channelType);
      if (localChannelNode != null)
        this.channelName = localChannelNode.title;
    }
  }

  public long getCreateTime()
  {
    if (this.createTime == null)
      return 0L;
    if (this._createTime > 0L)
      return this._createTime;
    this._createTime = TimeUtil.dateToMS(this.createTime);
    return this._createTime;
  }

  public int getCurrPlayStatus()
  {
    int j = 3;
    long l1 = System.currentTimeMillis() / 1000L;
    long l2 = getAbsoluteStartTime();
    long l3 = getAbsoluteEndTime();
    int i = j;
    if (this.channelType == 0)
    {
      if ((l2 > l1) || (l3 <= l1))
        break label49;
      i = 1;
    }
    label49: 
    do
    {
      return i;
      if (l2 > l1)
        return 2;
      i = j;
    }
    while (l3 >= l1);
    return 3;
  }

  public String getDownLoadUrlPath()
  {
    if (this.downloadInfo == null)
      this.downloadInfo = new Download();
    if ((this.downloadInfo != null) && (this.downloadInfo.downloadPath != null) && (!this.downloadInfo.downloadPath.equalsIgnoreCase("")))
      return this.downloadInfo.downloadPath;
    if ((this.channelType == 1) && (this.lstAudioPath != null) && (this.lstAudioPath.size() > 0));
    for (this.downloadInfo.downloadPath = MediaCenter.getInstance().getVirtualProgramDownloadPath("virutalchannel", (String)this.lstAudioPath.get(0), 24); ; this.downloadInfo.downloadPath = MediaCenter.getInstance().getReplayDownloadPath("radiodownload", String.valueOf(this.resId), 24, buildTimeParam(getAbsoluteStartTime()), buildTimeParam(getAbsoluteEndTime())))
      return this.downloadInfo.downloadPath;
  }

  public int getDuration()
  {
    if (this.duration > 0.0D)
      return (int)this.duration;
    this.duration = (endTime() - startTime());
    return (int)this.duration;
  }

  public String getHighBitrateSource()
  {
    if ((this.mHighBitrateSource != null) && (!this.mHighBitrateSource.equalsIgnoreCase("")))
      return this.mHighBitrateSource;
    if ((this.lstBitrate != null) && (this.lstBitrate.size() > 0));
    for (int i = ((Integer)this.lstBitrate.get(this.lstBitrate.size() - 1)).intValue(); ; i = 24)
    {
      if ((this.channelType == 0) || (this.mLiveInVirtual))
        if (getCurrPlayStatus() == 1)
          this.mHighBitrateSource = MediaCenter.getInstance().getPlayUrls("radiohls", String.valueOf(this.resId), i, this.channelId);
      while (true)
      {
        return this.mHighBitrateSource;
        this.mHighBitrateSource = MediaCenter.getInstance().getReplayUrls(String.valueOf(this.resId), i, buildTimeParam(getAbsoluteStartTime()), buildTimeParam(getAbsoluteEndTime()));
        continue;
        if (this.channelType == 1)
        {
          String str2 = "";
          String str1 = str2;
          if (this.lstAudioPath != null)
          {
            str1 = str2;
            if (this.lstAudioPath.size() > 0)
              str1 = (String)this.lstAudioPath.get(this.lstAudioPath.size() - 1);
          }
          this.mHighBitrateSource = MediaCenter.getInstance().getPlayUrls("virutalchannel", str1, 24, this.channelId);
        }
      }
    }
  }

  public String getLowBitrateSource()
  {
    if ((this.mLowBitrateSource != null) && (!this.mLowBitrateSource.equalsIgnoreCase("")))
      return this.mLowBitrateSource;
    if ((this.lstBitrate != null) && (this.lstBitrate.size() > 0));
    for (int i = ((Integer)this.lstBitrate.get(0)).intValue(); ; i = 24)
    {
      if ((this.channelType == 0) || (this.mLiveInVirtual))
        if (getCurrPlayStatus() == 1)
          this.mLowBitrateSource = MediaCenter.getInstance().getPlayUrls("radiohls", String.valueOf(this.resId), i, this.channelId);
      while (true)
      {
        return this.mLowBitrateSource;
        this.mLowBitrateSource = MediaCenter.getInstance().getReplayUrls(String.valueOf(this.resId), i, buildTimeParam(getAbsoluteStartTime()), buildTimeParam(getAbsoluteEndTime()));
        continue;
        if (this.channelType == 1)
        {
          String str2 = "";
          String str1 = str2;
          if (this.lstAudioPath != null)
          {
            str1 = str2;
            if (this.lstAudioPath.size() > 0)
              str1 = (String)this.lstAudioPath.get(0);
          }
          this.mLowBitrateSource = MediaCenter.getInstance().getPlayUrls("virutalchannel", str1, 24, this.channelId);
        }
      }
    }
  }

  public String getNextDownLoadUrl()
  {
    Object localObject = null;
    if (this.downloadInfo == null)
      return null;
    int i = this.downloadInfo.type;
    if (i == 0)
      localObject = MediaCenter.getInstance().getPingInfo("radiodownload");
    while (true)
    {
      this.mAvailableUrlIndex += 1;
      if (localObject == null)
        break;
      i = this.mAvailableUrlIndex;
      if (i >= ((List)localObject).size())
        break;
      localObject = "http://" + ((PingInfoV6)((List)localObject).get(i)).getDomainIP();
      return (String)localObject + this.downloadInfo.downloadPath;
      if (i == 1)
        localObject = MediaCenter.getInstance().getPingInfo("virutalchannel");
    }
    return "";
  }

  public ProgramNode getNextSibling()
  {
    if (this.nextSibling != null)
      return (ProgramNode)this.nextSibling;
    Object localObject;
    if (this.isDownloadProgram)
    {
      localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if (localObject != null)
      {
        localObject = ((ChannelNode)localObject).getProgramNodeByProgramId(this.id);
        if (localObject != null)
          return (ProgramNode)((ProgramNode)localObject).nextSibling;
      }
    }
    else
    {
      localObject = ProgramHelper.getInstance().getProgramSchedule(this.channelId, this.channelType, true);
      if (localObject != null)
      {
        localObject = ((ProgramScheduleList)localObject).getProgramNode(this.id);
        if (localObject != null)
          return (ProgramNode)((ProgramNode)localObject).nextSibling;
      }
    }
    return null;
  }

  public ProgramNode getPrevSibling()
  {
    if (this.prevSibling != null)
      return (ProgramNode)this.prevSibling;
    Object localObject;
    if (this.isDownloadProgram)
    {
      localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if (localObject != null)
      {
        localObject = ((ChannelNode)localObject).getProgramNodeByProgramId(this.id);
        if (localObject != null)
          return (ProgramNode)((ProgramNode)localObject).prevSibling;
      }
    }
    else
    {
      localObject = ProgramHelper.getInstance().getProgramSchedule(this.channelId, this.channelType, true);
      if (localObject != null)
      {
        localObject = ((ProgramScheduleList)localObject).getProgramNode(this.id);
        if (localObject != null)
          return (ProgramNode)((ProgramNode)localObject).prevSibling;
      }
    }
    return null;
  }

  public int getProgramType()
  {
    int i = Constants.PROGRAM_PAST;
    if (getCurrPlayStatus() == 3)
      i = Constants.PROGRAM_PAST;
    while (getCurrPlayStatus() != 1)
      return i;
    return Constants.PROGRAM_LIVE;
  }

  public String getSharedSourcePath()
  {
    Object localObject2;
    if ((this.mShareSourceUrl == null) || (this.mShareSourceUrl.equalsIgnoreCase("")))
    {
      localObject2 = null;
      return localObject2;
    }
    int j = 0;
    int i = 0;
    Object localObject1 = "";
    while (true)
    {
      localObject2 = localObject1;
      if (i >= this.mShareSourceUrl.length())
        break;
      int k = j;
      if (this.mShareSourceUrl.charAt(i) == '/')
      {
        k = j;
        if (j < 3)
          k = j + 1;
      }
      localObject2 = localObject1;
      if (this.mShareSourceUrl.charAt(i) == '?')
        break;
      localObject2 = localObject1;
      if (k == 3)
        localObject2 = (String)localObject1 + this.mShareSourceUrl.charAt(i);
      i += 1;
      localObject1 = localObject2;
      j = k;
    }
  }

  public String getSharedSourceUrl()
  {
    if ((this.mShareSourceUrl == null) || (this.mShareSourceUrl.equalsIgnoreCase("")))
      setShareSourceUrl();
    return this.mShareSourceUrl;
  }

  public String getSourceUrl()
  {
    if (this.mSetting == -1)
      this.mSetting = InfoManager.getInstance().getAudioQualitySetting();
    if (this.mSetting == InfoManager.AUDIO_QUALITY_MODE.HIGH_QUALITY.ordinal())
      return getHighBitrateSource();
    return getLowBitrateSource();
  }

  public long getUpdateTime()
  {
    if (this.updateTime == null)
      return 0L;
    if (this._updateTime > 0L)
      return this._updateTime;
    this._updateTime = TimeUtil.dateToMS(this.updateTime);
    return this._updateTime;
  }

  public boolean isDownloadProgram()
  {
    return this.isDownloadProgram;
  }

  public boolean isLiveProgram()
  {
    return this.channelType == 0;
  }

  public boolean isMusicChannel()
  {
    return (this.channelType == 1) && (getCategoryId() == 523);
  }

  public boolean isNovelProgram()
  {
    return false;
  }

  public void setAbsoluteEndTime(long paramLong)
  {
    this.absoluteEndTime = paramLong;
  }

  public void setAbsoluteStartTime(long paramLong)
  {
    this.absoluteStartTime = paramLong;
  }

  public void setCategoryId(int paramInt)
  {
    if (paramInt != -1)
      this.categoryId = paramInt;
  }

  public void setChannelName(String paramString)
  {
    this.channelName = paramString;
  }

  public void setCreateTime(long paramLong)
  {
    this._createTime = paramLong;
  }

  public void setSharedSourceUrl(String paramString)
  {
    this.mShareSourceUrl = paramString;
  }

  public void setSourceUrls(String paramString)
  {
    if ((this.mLowBitrateSource != null) && (!this.mLowBitrateSource.equalsIgnoreCase("")))
    {
      this.mLowBitrateSource += ";;";
      this.mLowBitrateSource += paramString;
    }
    for (this.mLowBitrateSource += ";;"; (this.mHighBitrateSource != null) && (!this.mHighBitrateSource.equalsIgnoreCase("")); this.mLowBitrateSource = paramString)
    {
      this.mHighBitrateSource += ";;";
      this.mHighBitrateSource += paramString;
      this.mHighBitrateSource += ";;";
      return;
    }
    this.mHighBitrateSource = paramString;
  }

  public void setUpdateTime(long paramLong)
  {
    this._updateTime = paramLong;
  }

  public int startTime()
  {
    if ((-1 == this.broadcastStartTime) && (this.startTime != null));
    try
    {
      String[] arrayOfString = Pattern.compile("\\D+").split(this.startTime);
      if (arrayOfString.length >= 2)
      {
        int i = Integer.parseInt(arrayOfString[0]);
        this.broadcastStartTime = (Integer.parseInt(arrayOfString[1]) * 60 + i * 3600);
      }
      label60: return this.broadcastStartTime;
    }
    catch (Exception localException)
    {
      break label60;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.ProgramNode
 * JD-Core Version:    0.6.2
 */