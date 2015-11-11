package fm.qingting.qtradio.model;

import android.os.Environment;
import android.text.TextUtils;
import android.widget.Toast;
import fm.qingting.downloadnew.DownloadListener;
import fm.qingting.downloadnew.DownloadService;
import fm.qingting.downloadnew.DownloadState;
import fm.qingting.downloadnew.DownloadTask;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.downloadnew.OnDownloadPathChangeListener;
import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.QTApplication;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.utils.LifeTime;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.TimeUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DownLoadInfoNode extends Node
  implements DownloadListener
{
  public static final int EXCEED_DURATION = 1;
  public static final int HAS_EXISTED = 2;
  private static final String SUFFIX = ".cache";
  public static int allVoiceId = 82;
  public static int legacyId;
  public static int mDownloadId = 71;
  private String directory;
  private String downloadDat = "download.dat";
  private transient boolean hasRestoreDownloading = false;
  private boolean hasRestored = false;
  private List<Node> lstDownLoadedRings = new ArrayList();
  private List<Node> lstDownLoadingNodes = new ArrayList();
  private CategoryNode mCategory;
  private List<OnDownloadPathChangeListener> mDownloadPathChangeListener;
  private boolean mNeedSync = false;
  private int mTotalProgramCnt = 0;
  private long mTotalProgramSize = 0L;
  private Map<Integer, ChannelNode> mapChannelNodes = new HashMap();
  private Map<String, String> mapMetaInfo = new HashMap();
  private List<IDownloadInfoEventListener> mlstDLEventListeners = new ArrayList();
  private int refuseErrorCode = 0;

  static
  {
    legacyId = 81;
  }

  public DownLoadInfoNode()
  {
    this.nodeName = "downloadinfo";
    buildCategory();
  }

  private void addChannelNodeToCategory(ChannelNode paramChannelNode)
  {
    if ((paramChannelNode == null) || (this.mCategory == null));
    while (this.mCategory.mLstChannelNodes == null)
      return;
    paramChannelNode.parent = this.mCategory;
    this.mCategory.mLstChannelNodes.add(paramChannelNode);
    this.mapChannelNodes.put(Integer.valueOf(paramChannelNode.channelId), paramChannelNode);
  }

  private void attachProgram(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return;
    Object localObject = (ChannelNode)this.mapChannelNodes.get(Integer.valueOf(paramProgramNode.channelId));
    if (localObject != null)
      if (((ChannelNode)localObject).addDownloadProgramNode(paramProgramNode))
        ((ChannelNode)localObject).programCnt += 1;
    label158: 
    while (true)
    {
      this.mTotalProgramCnt += 1;
      this.mTotalProgramSize += paramProgramNode.downloadInfo.fileSize;
      return;
      ChannelNode localChannelNode;
      if (paramProgramNode.channelId == 360)
      {
        localChannelNode = buildChannelNode("电脑下载", 360);
        localObject = localChannelNode;
        if (localChannelNode != null)
        {
          addChannelNodeToCategory(localChannelNode);
          localObject = localChannelNode;
        }
      }
      while (true)
      {
        if (localObject == null)
          break label158;
        ((ChannelNode)localObject).addDownloadProgramNode(paramProgramNode);
        ((ChannelNode)localObject).programCnt += 1;
        break;
        localChannelNode = buildChannelNode(paramProgramNode.getChannelName(), paramProgramNode.channelId);
        localObject = localChannelNode;
        if (localChannelNode != null)
        {
          addChannelNodeToCategory(localChannelNode);
          localObject = localChannelNode;
        }
      }
    }
  }

  private void buildCategory()
  {
    if (this.mCategory == null)
    {
      this.mCategory = new CategoryNode();
      this.mCategory.parentId = mDownloadId;
      this.mCategory.categoryId = mDownloadId;
      this.mCategory.categoryId = this.mCategory.categoryId;
      this.mCategory.name = "我的下载";
      this.mCategory.type = 2;
      this.mCategory.hasChild = 0;
      this.mCategory.forbidUseDB();
      ChannelNode localChannelNode = buildLegacyChannelNode();
      localChannelNode.parent = this.mCategory;
      this.mCategory.mLstChannelNodes = new ArrayList();
      this.mCategory.mLstChannelNodes.add(localChannelNode);
      this.mapChannelNodes.put(Integer.valueOf(localChannelNode.channelId), localChannelNode);
    }
  }

  private ChannelNode buildChannelNode(String paramString, int paramInt)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return null;
    ChannelNode localChannelNode = new ChannelNode();
    localChannelNode.channelId = paramInt;
    localChannelNode.parent = this.mCategory;
    localChannelNode.categoryId = this.mCategory.categoryId;
    localChannelNode.title = paramString;
    localChannelNode.channelType = 1;
    return localChannelNode;
  }

  private String buildFileNameByNode(Node paramNode, long paramLong)
  {
    if (paramNode == null)
      localObject = null;
    label456: label482: 
    do
    {
      return localObject;
      localObject = "";
      if (paramNode.nodeName.equalsIgnoreCase("program"))
      {
        localObject = (ProgramNode)paramNode;
        paramNode = "" + "o";
        paramNode = paramNode + "_";
        String str;
        if ((((ProgramNode)localObject).parent != null) && (((ProgramNode)localObject).parent.nodeName.equalsIgnoreCase("channel")))
        {
          paramNode = paramNode + ((ChannelNode)((ProgramNode)localObject).parent).channelId;
          paramNode = paramNode + "_";
          paramNode = paramNode + ((ProgramNode)localObject).id;
          paramNode = paramNode + "_";
          if (!((ProgramNode)localObject).title.contains("_"))
            break label456;
          str = ((ProgramNode)localObject).title.replace('_', '.');
        }
        for (paramNode = paramNode + str; ; paramNode = paramNode + ((ProgramNode)localObject).title)
        {
          paramNode = paramNode + "_";
          paramNode = paramNode + ((ProgramNode)localObject).getDuration();
          paramNode = paramNode + "_";
          paramNode = paramNode + String.valueOf(paramLong);
          paramNode = paramNode + "_";
          if (((ProgramNode)localObject).downloadInfo != null)
            break label482;
          paramNode = paramNode + String.valueOf(2);
          paramNode = paramNode + "_";
          return paramNode + "0";
          paramNode = paramNode + "0";
          paramNode = paramNode + "_";
          break;
        }
        paramNode = paramNode + String.valueOf(((ProgramNode)localObject).downloadInfo.state);
        paramNode = paramNode + "_";
        return paramNode + String.valueOf(((ProgramNode)localObject).downloadInfo.progress);
      }
    }
    while (!paramNode.nodeName.equalsIgnoreCase("ringtone"));
    paramNode = (RingToneNode)paramNode;
    Object localObject = "" + "r";
    localObject = (String)localObject + "_";
    localObject = (String)localObject + paramNode.ringToneAlbumId;
    localObject = (String)localObject + "_";
    localObject = (String)localObject + paramNode.ringToneId;
    localObject = (String)localObject + "_";
    localObject = (String)localObject + paramNode.title;
    localObject = (String)localObject + "_";
    localObject = (String)localObject + paramNode.duration;
    localObject = (String)localObject + "_";
    localObject = (String)localObject + String.valueOf(paramLong);
    localObject = (String)localObject + "_";
    if (paramNode.downloadInfo == null)
    {
      paramNode = (String)localObject + String.valueOf(2);
      paramNode = paramNode + "_";
      return paramNode + "0";
    }
    localObject = (String)localObject + String.valueOf(paramNode.downloadInfo.state);
    localObject = (String)localObject + "_";
    return (String)localObject + String.valueOf(paramNode.downloadInfo.progress);
  }

  private ChannelNode buildLegacyChannelNode()
  {
    ChannelNode localChannelNode = new ChannelNode();
    localChannelNode.channelId = Integer.valueOf(legacyId).intValue();
    localChannelNode.categoryId = this.mCategory.categoryId;
    localChannelNode.parent = this.mCategory;
    localChannelNode.title = "以前的下载";
    localChannelNode.channelType = 1;
    return localChannelNode;
  }

  private Node buildNodeByFileName(String paramString, int paramInt, long paramLong)
  {
    if (paramString == null)
      localObject2 = null;
    Object localObject1;
    String[] arrayOfString;
    label69: 
    do
    {
      return localObject2;
      int i = paramString.charAt(0);
      if (i == 111);
      for (localObject1 = new ProgramNode(); ; localObject1 = new RingToneNode())
      {
        arrayOfString = paramString.split("_");
        if (arrayOfString != null)
          break label69;
        return null;
        if (i != 114)
          break;
      }
      return null;
      if (((Node)localObject1).nodeName.equalsIgnoreCase("program"))
      {
        if (arrayOfString.length < 8)
          return null;
        localObject2 = (ProgramNode)localObject1;
        ((ProgramNode)localObject2).channelId = Integer.valueOf(legacyId).intValue();
        ((ProgramNode)localObject2).id = Integer.valueOf(arrayOfString[2]).intValue();
        ((ProgramNode)localObject2).uniqueId = ((ProgramNode)localObject2).id;
        ((ProgramNode)localObject2).title = arrayOfString[3];
        ((ProgramNode)localObject2).duration = Integer.valueOf(arrayOfString[4]).intValue();
        ((ProgramNode)localObject2).dayOfWeek = paramInt;
        ((ProgramNode)localObject2).isDownloadProgram = true;
        ((ProgramNode)localObject2).endTime = "00:01";
        ((ProgramNode)localObject2).channelType = 1;
        str = "file://" + getDownLoadPath();
        str = str + "/";
        ((ProgramNode)localObject2).setSourceUrls(str + paramString);
        str = "file://" + getDownLoadPath();
        str = str + "/";
        ((ProgramNode)localObject2).setSourceUrls(str + ((ProgramNode)localObject2).id);
        ((ProgramNode)localObject2).downloadInfo = new Download();
        ((ProgramNode)localObject2).downloadInfo.id = paramString;
        try
        {
          ((ProgramNode)localObject2).downloadInfo.state = Integer.valueOf(arrayOfString[6]).intValue();
          ((ProgramNode)localObject2).downloadInfo.progress = Integer.valueOf(arrayOfString[7]).intValue();
          ((ProgramNode)localObject2).downloadInfo.fileSize = ((int)((ProgramNode)localObject2).duration * 24 * 125);
          ((ProgramNode)localObject2).downloadInfo.updateTime = Integer.valueOf(arrayOfString[5]).intValue();
          ((ProgramNode)localObject2).downloadInfo.downloadTime = paramLong;
          ((ProgramNode)localObject2).downloadInfo.fileName = paramString;
          return localObject1;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          return localObject1;
        }
      }
      localObject2 = localObject1;
    }
    while (!((Node)localObject1).nodeName.equalsIgnoreCase("ringtone"));
    if (arrayOfString.length < 8)
      return null;
    Object localObject2 = (RingToneNode)localObject1;
    ((RingToneNode)localObject2).ringToneAlbumId = arrayOfString[1];
    ((RingToneNode)localObject2).ringToneId = arrayOfString[2];
    ((RingToneNode)localObject2).title = arrayOfString[3];
    if (((RingToneNode)localObject2).ringToneId.equalsIgnoreCase("0"))
    {
      if ((((RingToneNode)localObject2).title != null) && (((RingToneNode)localObject2).title.equalsIgnoreCase("liji")))
        return null;
      ((RingToneNode)localObject2).ringDesc = "有声世界无限精彩";
      ((RingToneNode)localObject2).title = "蜻蜓娘娘";
    }
    if ((((RingToneNode)localObject2).ringDesc == null) || (((RingToneNode)localObject2).ringDesc.equalsIgnoreCase("")))
      ((RingToneNode)localObject2).ringDesc = ((RingToneNode)localObject2).title;
    ((RingToneNode)localObject2).duration = Integer.valueOf(arrayOfString[4]).intValue();
    ((RingToneNode)localObject2).updatetime = arrayOfString[5];
    ((RingToneNode)localObject2).broadcast_time = String.valueOf(((RingToneNode)localObject2).duration * 1.0F);
    ((RingToneNode)localObject2).type = "DownloadRingTone";
    String str = "file://" + getDownLoadPath();
    str = str + "/";
    ((RingToneNode)localObject2).setListenOnLineUrl(str + paramString);
    ((RingToneNode)localObject2).downloadInfo = new Download();
    ((RingToneNode)localObject2).downloadInfo.id = paramString;
    try
    {
      ((RingToneNode)localObject2).downloadInfo.state = Integer.valueOf(arrayOfString[6]).intValue();
      ((RingToneNode)localObject2).downloadInfo.progress = Integer.valueOf(arrayOfString[7]).intValue();
      ((RingToneNode)localObject2).downloadInfo.fileSize = (((RingToneNode)localObject2).duration * 24 * 125);
      ((RingToneNode)localObject2).downloadInfo.updateTime = Integer.valueOf(((RingToneNode)localObject2).updatetime).intValue();
      ((RingToneNode)localObject2).downloadInfo.fileName = paramString;
      return localObject1;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return localObject1;
  }

  private Node buildNodeBySimpleFileName(String paramString, int paramInt1, int paramInt2)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    ProgramNode localProgramNode;
    do
    {
      return null;
      localProgramNode = new ProgramNode();
      localObject = paramString.split("_");
    }
    while (localObject == null);
    localProgramNode.channelId = 360;
    localProgramNode.id = 360;
    localProgramNode.uniqueId = 360;
    localProgramNode.title = localObject[0];
    localProgramNode.duration = Integer.valueOf(localObject[1]).intValue();
    localProgramNode.dayOfWeek = paramInt2;
    localProgramNode.channelType = 1;
    localProgramNode.isDownloadProgram = true;
    localProgramNode.startTime = "00:01";
    Object localObject = "file://" + getDownLoadPath();
    localObject = (String)localObject + "/";
    localProgramNode.setSourceUrls((String)localObject + paramString);
    localProgramNode.downloadInfo = new Download();
    localProgramNode.downloadInfo.id = paramString;
    try
    {
      localProgramNode.downloadInfo.state = 3;
      localProgramNode.downloadInfo.progress = 100;
      localProgramNode.downloadInfo.fileSize = ((int)localProgramNode.duration * 24 * 125);
      localProgramNode.downloadInfo.updateTime = paramInt1;
      localProgramNode.downloadInfo.downloadTime = paramInt1;
      localProgramNode.downloadInfo.sequence = 0;
      return localProgramNode;
    }
    catch (Exception paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  private void connectAllVoice()
  {
    int i;
    if (this.mCategory.mLstChannelNodes != null)
    {
      Node localNode = null;
      i = 0;
      if (i < this.mCategory.mLstChannelNodes.size())
      {
        List localList = ((ChannelNode)this.mCategory.mLstChannelNodes.get(i)).getAllLstProgramNode();
        if ((localList == null) || (localList.size() <= 0))
          break label121;
        if (localNode != null)
        {
          localNode.nextSibling = ((Node)localList.get(0));
          ((ProgramNode)localList.get(0)).prevSibling = localNode;
        }
        localNode = (Node)localList.get(localList.size() - 1);
      }
    }
    label121: 
    while (true)
    {
      i += 1;
      break;
      return;
    }
  }

  private void dispatchDownLoadEvent(Node paramNode, int paramInt)
  {
    if (paramNode == null);
    while (true)
    {
      return;
      int i = 0;
      while (i < this.mlstDLEventListeners.size())
      {
        ((IDownloadInfoEventListener)this.mlstDLEventListeners.get(i)).onDownLoadInfoUpdated(paramInt, paramNode);
        i += 1;
      }
    }
  }

  private void downloadFailedTips(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    Toast.makeText(InfoManager.getInstance().getContext(), paramString + " 下载失败,可能是网络连不上或者无法在sd卡上创建文件", 1).show();
  }

  private int downloadState(Node paramNode)
  {
    if (paramNode == null);
    do
    {
      do
      {
        return 0;
        this.refuseErrorCode = 0;
      }
      while (!paramNode.nodeName.equalsIgnoreCase("program"));
      if (hasInDownLoadList(paramNode) != -1)
        return 3;
    }
    while (hasInDownLoading(paramNode) == -1);
    return 1;
  }

  private void downloadSuccessTips(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    Toast.makeText(InfoManager.getInstance().getContext(), paramString + " 下载成功.", 1).show();
  }

  private void endDownloadTime(String paramString, boolean paramBoolean)
  {
    if (paramString == null);
    do
    {
      do
      {
        return;
        paramString = findNodeByName(paramString);
      }
      while ((paramString == null) || (!paramString.nodeName.equalsIgnoreCase("program")) || (((ProgramNode)paramString).downloadInfo == null));
      ((ProgramNode)paramString).downloadInfo.downloadEndTime = (System.currentTimeMillis() / 1000L);
      paramString = QTLogger.getInstance().buildDownloadLog(paramString, paramBoolean);
    }
    while (paramString == null);
    LogModule.getInstance().send("DownloadProgram", paramString);
  }

  private Node findNodeByName(String paramString)
  {
    int j = 0;
    Object localObject2;
    int i;
    Object localObject1;
    if ((paramString != null) && (this.lstDownLoadingNodes != null))
    {
      localObject2 = getRealDownloadId(paramString);
      i = 0;
      while (i < this.lstDownLoadingNodes.size())
      {
        localObject1 = (ProgramNode)this.lstDownLoadingNodes.get(i);
        if ((((ProgramNode)localObject1).downloadInfo != null) && (localObject2 != null) && (((ProgramNode)localObject1).downloadInfo.id.equalsIgnoreCase((String)localObject2)))
          return localObject1;
        i += 1;
      }
    }
    if ((paramString != null) && (this.lstDownLoadedRings != null))
    {
      i = j;
      while (true)
      {
        if (i >= this.lstDownLoadedRings.size())
          break label164;
        localObject2 = (RingToneNode)this.lstDownLoadedRings.get(i);
        if (((RingToneNode)localObject2).downloadInfo != null)
        {
          localObject1 = localObject2;
          if (((RingToneNode)localObject2).downloadInfo.id.equalsIgnoreCase(paramString))
            break;
        }
        i += 1;
      }
    }
    label164: return null;
  }

  private int getDayOfWeek()
  {
    return 0;
  }

  private String getDownloadMeta(ProgramNode paramProgramNode, int paramInt)
  {
    if (paramProgramNode == null)
      return null;
    String str1 = "n@" + paramProgramNode.id;
    str1 = str1 + "@";
    str1 = str1 + 0;
    str1 = str1 + "@";
    Object localObject;
    String str2;
    label200: label216: label238: long l1;
    if (paramProgramNode.channelType == 1)
    {
      str1 = str1 + paramInt;
      str1 = str1 + "@";
      str1 = str1 + paramProgramNode.id;
      localObject = str1 + "@";
      str2 = paramProgramNode.getChannelName();
      if (paramProgramNode.title != null)
        break label729;
      str1 = "最新下载";
      if (paramProgramNode.channelType != 1)
        break label792;
      if (str2 != null)
        break label761;
      str2 = str1;
      str2 = (String)localObject + str2;
      str2 = str2 + "@";
      l1 = paramProgramNode.getUpdateTime();
      if (l1 == 0L)
        break label816;
      l1 /= 1000L;
      label282: long l2 = l1;
      if (l1 == 0L)
        l2 = System.currentTimeMillis() / 1000L;
      String str4 = TimeUtil.msToDate7(l2 * 1000L);
      String str3 = str2 + str1;
      str2 = str1;
      localObject = str3;
      if (str4 != null)
      {
        str2 = str1;
        localObject = str3;
        if (paramProgramNode.channelType == 0)
        {
          localObject = str3 + str4;
          str2 = str1 + str4;
        }
      }
      str1 = (String)localObject + "@";
      str1 = str1 + paramProgramNode.getDuration();
      str1 = str1 + "@";
      str1 = str1 + l2;
      str1 = str1 + "@";
      str1 = str1 + paramProgramNode.getSharedSourceUrl();
      str1 = str1 + "@";
      str1 = str1 + paramInt;
      str1 = str1 + "@";
      if (!paramProgramNode.isNovelProgram())
        break label825;
      str1 = str1 + "0";
      label620: str1 = str1 + "@";
      localObject = str1 + String.valueOf(paramProgramNode.sequence);
      if (paramProgramNode.channelType != 1)
        break label888;
    }
    label729: label888: for (str1 = String.valueOf(paramProgramNode.id); ; str1 = paramProgramNode.id + "@" + str2)
    {
      this.mapMetaInfo.put(String.valueOf(paramProgramNode.id), localObject);
      return str1;
      str1 = str1 + paramProgramNode.uniqueId;
      break;
      str1 = paramProgramNode.title.replaceAll("(\r\n|\r|\n|\n\r)", "").replaceAll("(@)", "").replaceAll("(/)", "_");
      break label200;
      label761: str2 = str2.replaceAll("(\r\n|\r|\n|\n\r)", "").replaceAll("(@)", "").replaceAll("(/)", "_");
      break label216;
      str2 = (String)localObject + str1;
      break label238;
      l1 = paramProgramNode.getAbsoluteStartTime();
      break label282;
      if ((paramProgramNode.channelType == 0) && (paramProgramNode.getCurrPlayStatus() == 3))
      {
        str1 = str1 + "2";
        break label620;
      }
      str1 = str1 + "1";
      break label620;
    }
  }

  private Node getNodeByDownloadId(String paramString, int paramInt, long paramLong)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      paramString = null;
    Object localObject;
    while (true)
    {
      return paramString;
      localObject = getRealDownloadId(paramString);
      localObject = (String)this.mapMetaInfo.get(localObject);
      if ((localObject == null) || (((String)localObject).equalsIgnoreCase("")))
        return null;
      String[] arrayOfString = ((String)localObject).split("@");
      if ((arrayOfString == null) || (arrayOfString.length < 10))
        return null;
      localObject = new ProgramNode();
      ((ProgramNode)localObject).channelId = Integer.valueOf(arrayOfString[3]).intValue();
      ((ProgramNode)localObject).id = Integer.valueOf(arrayOfString[4]).intValue();
      ((ProgramNode)localObject).uniqueId = ((ProgramNode)localObject).id;
      ((ProgramNode)localObject).setChannelName(arrayOfString[5]);
      ((ProgramNode)localObject).title = arrayOfString[6];
      ((ProgramNode)localObject).duration = Integer.valueOf(arrayOfString[7]).intValue();
      ((ProgramNode)localObject).dayOfWeek = paramInt;
      ((ProgramNode)localObject).isDownloadProgram = true;
      ((ProgramNode)localObject).channelType = 1;
      String str = "file://" + getDownLoadPath();
      str = str + "/";
      ((ProgramNode)localObject).setSourceUrls(str + paramString);
      str = "file://" + getDownLoadPath();
      str = str + "/";
      ((ProgramNode)localObject).setSourceUrls(str + ((ProgramNode)localObject).id);
      ((ProgramNode)localObject).setSharedSourceUrl(arrayOfString[9]);
      ((ProgramNode)localObject).downloadInfo = new Download();
      ((ProgramNode)localObject).downloadInfo.id = paramString;
      try
      {
        ((ProgramNode)localObject).downloadInfo.state = 3;
        ((ProgramNode)localObject).downloadInfo.progress = 100;
        ((ProgramNode)localObject).downloadInfo.channelId = Integer.valueOf(arrayOfString[3]).intValue();
        ((ProgramNode)localObject).downloadInfo.fileSize = ((int)((ProgramNode)localObject).duration * 24 * 125);
        ((ProgramNode)localObject).downloadInfo.updateTime = Integer.valueOf(arrayOfString[8]).intValue();
        ((ProgramNode)localObject).setUpdateTime(Integer.valueOf(arrayOfString[8]).intValue());
        ((ProgramNode)localObject).downloadInfo.downloadTime = paramLong;
        ((ProgramNode)localObject).downloadInfo.fileName = paramString;
        if (arrayOfString.length > 11)
          ((ProgramNode)localObject).downloadInfo.contentType = Integer.valueOf(arrayOfString[11]).intValue();
        if (arrayOfString.length > 12)
        {
          ((ProgramNode)localObject).downloadInfo.sequence = Integer.valueOf(arrayOfString[12]).intValue();
          ((ProgramNode)localObject).sequence = ((ProgramNode)localObject).downloadInfo.sequence;
        }
        paramString = (String)localObject;
        if (arrayOfString.length > 10)
        {
          ((ProgramNode)localObject).downloadInfo.channelId = Integer.valueOf(arrayOfString[10]).intValue();
          return localObject;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
    return localObject;
  }

  private String getRealDownloadId(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")));
    do
    {
      return null;
      paramString = paramString.split("@");
    }
    while ((paramString == null) || (paramString.length <= 0));
    return paramString[0];
  }

  private DownloadTask getTask(String paramString)
  {
    return DownloadService.getInstance(QTApplication.appContext).getTask(paramString);
  }

  private void onDownloadFailed(String paramString)
  {
    if (paramString == null);
    do
    {
      do
        return;
      while (paramString.endsWith(".cache"));
      paramString = findNodeByName(paramString);
      if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("program")))
      {
        pauseDownLoad(paramString, false);
        downloadFailedTips(((ProgramNode)paramString).title);
        ((ProgramNode)paramString).downloadInfo.state = 4;
        dispatchDownLoadEvent(paramString, 2);
        QTMSGManage.getInstance().sendStatistcsMessage("NewDownload1", "failed");
        QTMSGManage.getInstance().sendStatistcsMessage("NewDownload2", "failed:" + InfoManager.getInstance().getNetWorkType());
        return;
      }
    }
    while ((paramString == null) || (!paramString.nodeName.equalsIgnoreCase("ringtone")));
    ((RingToneNode)paramString).downloadInfo.state = 4;
    dispatchDownLoadEvent(paramString, 2);
  }

  private void onDownloadProcessing(String paramString, int paramInt)
  {
    if (paramString == null);
    do
    {
      do
        return;
      while (paramString.endsWith(".cache"));
      paramString = findNodeByName(paramString);
      if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("program")))
      {
        paramInt = (int)(paramInt / ((ProgramNode)paramString).downloadInfo.fileSize * 100.0D);
        ((ProgramNode)paramString).downloadInfo.state = 1;
        ((ProgramNode)paramString).downloadInfo.progress = paramInt;
        dispatchDownLoadEvent(paramString, 0);
        return;
      }
    }
    while ((paramString == null) || (!paramString.nodeName.equalsIgnoreCase("ringtone")));
    paramInt = (int)(paramInt / ((ProgramNode)paramString).downloadInfo.fileSize * 100.0D);
    ((RingToneNode)paramString).downloadInfo.state = 1;
    ((RingToneNode)paramString).downloadInfo.progress = paramInt;
    dispatchDownLoadEvent(paramString, 0);
  }

  private void onDownloadSuccess(String paramString)
  {
    if (paramString == null);
    Node localNode;
    do
    {
      do
        return;
      while (paramString.endsWith(".cache"));
      localNode = findNodeByName(paramString);
      if ((localNode != null) && (localNode.nodeName.equalsIgnoreCase("program")))
      {
        ((ProgramNode)localNode).downloadInfo.state = 3;
        ((ProgramNode)localNode).downloadInfo.progress = 100;
        downloadSuccessTips(((ProgramNode)localNode).title);
        endDownloadTime(paramString, true);
        attachProgram((ProgramNode)localNode);
        connectAllVoice();
        removeDownloading(localNode);
        dispatchDownLoadEvent(localNode, 1);
        writeToDAT(paramString);
        QTMSGManage.getInstance().sendStatistcsMessage("NewDownload1", "success");
        return;
      }
    }
    while ((localNode == null) || (!localNode.nodeName.equalsIgnoreCase("ringtone")));
    ((RingToneNode)localNode).downloadInfo.state = 3;
    ((RingToneNode)localNode).downloadInfo.progress = 100;
    ((RingToneNode)localNode).ringType = "local";
    this.lstDownLoadedRings.add(localNode);
    InfoManager.getInstance().root().mRingToneInfoNode.addToRingTone(localNode);
    dispatchDownLoadEvent(localNode, 1);
    QTMSGManage.getInstance().sendStatisticsMessageOnceADay("DownloadADay", "success");
  }

  private void parseDAT(String paramString)
  {
    try
    {
      paramString = new FileReader(paramString + "/" + this.downloadDat);
      BufferedReader localBufferedReader = new BufferedReader(paramString);
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        parseItem(str);
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
      return;
    }
    paramString.close();
  }

  private boolean parseDAT()
  {
    String str1 = getDownLoadPath();
    String str2 = Environment.getExternalStorageDirectory() + File.separator + "QTDownloadRadio";
    if (!TextUtils.equals(str1, str2))
      parseDAT(str2);
    parseDAT(str1);
    return false;
  }

  private boolean parseItem(String paramString)
  {
    if (paramString == null);
    String[] arrayOfString;
    do
    {
      return false;
      arrayOfString = paramString.split("@", 7);
    }
    while (arrayOfString == null);
    String str = arrayOfString[1];
    this.mapMetaInfo.put(str, paramString);
    int i = Integer.valueOf(arrayOfString[3]).intValue();
    if (this.mapChannelNodes.get(Integer.valueOf(i)) == null)
    {
      paramString = buildChannelNode(arrayOfString[5], i);
      if (paramString != null)
        addChannelNodeToCategory(paramString);
    }
    return true;
  }

  private void recordDownloadTime(String paramString)
  {
    if (paramString == null);
    do
    {
      return;
      paramString = findNodeByName(paramString);
    }
    while ((paramString == null) || (!paramString.nodeName.equalsIgnoreCase("program")) || (((ProgramNode)paramString).downloadInfo == null));
    ((ProgramNode)paramString).downloadInfo.downloadStartTime = (System.currentTimeMillis() / 1000L);
    ((ProgramNode)paramString).downloadInfo.lastProgress = ((ProgramNode)paramString).downloadInfo.progress;
  }

  private void refineDownloadChannels()
  {
    int j;
    for (int i = 0; i < this.mCategory.mLstChannelNodes.size(); i = j + 1)
    {
      j = i;
      if (((ChannelNode)this.mCategory.mLstChannelNodes.get(i)).hasEmptyProgramSchedule())
      {
        this.mapChannelNodes.remove(Integer.valueOf(((ChannelNode)this.mCategory.mLstChannelNodes.get(i)).channelId));
        this.mCategory.mLstChannelNodes.remove(i);
        j = i - 1;
      }
    }
  }

  private void removeDownloading(Node paramNode)
  {
    int i = hasInDownLoading(paramNode);
    if (i == -1)
      return;
    this.lstDownLoadingNodes.remove(i);
    this.mNeedSync = true;
  }

  private boolean restoreDownloading()
  {
    Node localNode = null;
    if (this.hasRestoreDownloading)
      return true;
    this.hasRestoreDownloading = true;
    Result localResult = DataManager.getInstance().getData("getdb_downloading_program_node", null, null).getResult();
    Object localObject = localNode;
    if (localResult != null)
    {
      localObject = localNode;
      if (localResult.getSuccess())
        localObject = (List)localResult.getData();
    }
    if ((localObject == null) || (((List)localObject).size() == 0))
      return false;
    this.lstDownLoadingNodes = ((List)localObject);
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      localNode = (Node)((Iterator)localObject).next();
      if ((localNode instanceof ProgramNode))
        ((ProgramNode)localNode).downloadInfo.state = 2;
    }
    return true;
  }

  // ERROR //
  private void restoreFromDir(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +24 -> 25
    //   4: new 668	java/io/File
    //   7: dup
    //   8: aload_1
    //   9: invokespecial 740	java/io/File:<init>	(Ljava/lang/String;)V
    //   12: astore_1
    //   13: aload_1
    //   14: invokevirtual 743	java/io/File:exists	()Z
    //   17: ifne +9 -> 26
    //   20: aload_1
    //   21: invokevirtual 746	java/io/File:mkdir	()Z
    //   24: pop
    //   25: return
    //   26: aload_1
    //   27: invokevirtual 749	java/io/File:isDirectory	()Z
    //   30: ifeq -5 -> 25
    //   33: aload_1
    //   34: invokevirtual 753	java/io/File:listFiles	()[Ljava/io/File;
    //   37: astore_3
    //   38: aload_3
    //   39: arraylength
    //   40: iconst_1
    //   41: isub
    //   42: istore 5
    //   44: iload 5
    //   46: iflt +247 -> 293
    //   49: aload_3
    //   50: iload 5
    //   52: aaload
    //   53: astore 4
    //   55: aload 4
    //   57: invokevirtual 756	java/io/File:isFile	()Z
    //   60: ifeq +248 -> 308
    //   63: aload 4
    //   65: invokevirtual 759	java/io/File:length	()J
    //   68: lstore 6
    //   70: lload 6
    //   72: ldc2_w 454
    //   75: lcmp
    //   76: ifle +232 -> 308
    //   79: aload 4
    //   81: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   84: ldc_w 764
    //   87: invokevirtual 564	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   90: ifeq +48 -> 138
    //   93: aload_0
    //   94: aload 4
    //   96: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   99: aload 4
    //   101: invokevirtual 767	java/io/File:lastModified	()J
    //   104: l2i
    //   105: aload_0
    //   106: invokespecial 769	fm/qingting/qtradio/model/DownLoadInfoNode:getDayOfWeek	()I
    //   109: invokespecial 771	fm/qingting/qtradio/model/DownLoadInfoNode:buildNodeBySimpleFileName	(Ljava/lang/String;II)Lfm/qingting/qtradio/model/Node;
    //   112: astore_1
    //   113: aload_1
    //   114: ifnull +98 -> 212
    //   117: aload_1
    //   118: getfield 217	fm/qingting/qtradio/model/Node:nodeName	Ljava/lang/String;
    //   121: ldc_w 773
    //   124: invokevirtual 206	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   127: ifeq +85 -> 212
    //   130: aload_1
    //   131: aload_0
    //   132: putfield 774	fm/qingting/qtradio/model/Node:parent	Lfm/qingting/qtradio/model/Node;
    //   135: goto +173 -> 308
    //   138: aload 4
    //   140: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   143: ldc 20
    //   145: invokevirtual 564	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   148: ifeq +17 -> 165
    //   151: invokestatic 779	fm/qingting/qtradio/fm/PlayCacheAgent:getInstance	()Lfm/qingting/qtradio/fm/PlayCacheAgent;
    //   154: aload 4
    //   156: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   159: invokevirtual 782	fm/qingting/qtradio/fm/PlayCacheAgent:addCacheByName	(Ljava/lang/String;)V
    //   162: goto +146 -> 308
    //   165: aload_0
    //   166: aload 4
    //   168: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   171: aload_0
    //   172: invokespecial 769	fm/qingting/qtradio/model/DownLoadInfoNode:getDayOfWeek	()I
    //   175: aload 4
    //   177: invokevirtual 767	java/io/File:lastModified	()J
    //   180: invokespecial 784	fm/qingting/qtradio/model/DownLoadInfoNode:buildNodeByFileName	(Ljava/lang/String;IJ)Lfm/qingting/qtradio/model/Node;
    //   183: astore_2
    //   184: aload_2
    //   185: astore_1
    //   186: aload_2
    //   187: ifnonnull -74 -> 113
    //   190: aload_0
    //   191: aload 4
    //   193: invokevirtual 762	java/io/File:getName	()Ljava/lang/String;
    //   196: aload_0
    //   197: invokespecial 769	fm/qingting/qtradio/model/DownLoadInfoNode:getDayOfWeek	()I
    //   200: aload 4
    //   202: invokevirtual 767	java/io/File:lastModified	()J
    //   205: invokespecial 786	fm/qingting/qtradio/model/DownLoadInfoNode:getNodeByDownloadId	(Ljava/lang/String;IJ)Lfm/qingting/qtradio/model/Node;
    //   208: astore_1
    //   209: goto -96 -> 113
    //   212: aload_1
    //   213: ifnull +26 -> 239
    //   216: aload_1
    //   217: getfield 217	fm/qingting/qtradio/model/Node:nodeName	Ljava/lang/String;
    //   220: ldc 219
    //   222: invokevirtual 206	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   225: ifeq +14 -> 239
    //   228: aload_0
    //   229: aload_1
    //   230: checkcast 140	fm/qingting/qtradio/model/ProgramNode
    //   233: invokespecial 602	fm/qingting/qtradio/model/DownLoadInfoNode:attachProgram	(Lfm/qingting/qtradio/model/ProgramNode;)V
    //   236: goto +72 -> 308
    //   239: aload_1
    //   240: ifnull +68 -> 308
    //   243: aload_1
    //   244: getfield 217	fm/qingting/qtradio/model/Node:nodeName	Ljava/lang/String;
    //   247: ldc_w 271
    //   250: invokevirtual 206	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   253: ifeq +55 -> 308
    //   256: aload_1
    //   257: checkcast 273	fm/qingting/qtradio/model/RingToneNode
    //   260: ldc_w 615
    //   263: putfield 618	fm/qingting/qtradio/model/RingToneNode:ringType	Ljava/lang/String;
    //   266: aload_0
    //   267: getfield 70	fm/qingting/qtradio/model/DownLoadInfoNode:lstDownLoadedRings	Ljava/util/List;
    //   270: aload_1
    //   271: invokeinterface 121 2 0
    //   276: pop
    //   277: invokestatic 415	fm/qingting/qtradio/model/InfoManager:getInstance	()Lfm/qingting/qtradio/model/InfoManager;
    //   280: invokevirtual 622	fm/qingting/qtradio/model/InfoManager:root	()Lfm/qingting/qtradio/model/RootNode;
    //   283: getfield 628	fm/qingting/qtradio/model/RootNode:mRingToneInfoNode	Lfm/qingting/qtradio/model/RingToneInfoNode;
    //   286: aload_1
    //   287: invokevirtual 633	fm/qingting/qtradio/model/RingToneInfoNode:addToRingTone	(Lfm/qingting/qtradio/model/Node;)V
    //   290: goto +18 -> 308
    //   293: aload_0
    //   294: invokespecial 788	fm/qingting/qtradio/model/DownLoadInfoNode:refineDownloadChannels	()V
    //   297: aload_0
    //   298: invokespecial 604	fm/qingting/qtradio/model/DownLoadInfoNode:connectAllVoice	()V
    //   301: return
    //   302: astore_1
    //   303: return
    //   304: astore_1
    //   305: return
    //   306: astore_1
    //   307: return
    //   308: iload 5
    //   310: iconst_1
    //   311: isub
    //   312: istore 5
    //   314: goto -270 -> 44
    //   317: astore_1
    //   318: goto -10 -> 308
    //
    // Exception table:
    //   from	to	target	type
    //   293	301	302	java/lang/Exception
    //   4	13	304	java/lang/Exception
    //   26	44	304	java/lang/Exception
    //   55	70	304	java/lang/Exception
    //   13	25	306	java/lang/Exception
    //   79	113	317	java/lang/Exception
    //   117	135	317	java/lang/Exception
    //   138	162	317	java/lang/Exception
    //   165	184	317	java/lang/Exception
    //   190	209	317	java/lang/Exception
    //   216	236	317	java/lang/Exception
    //   243	290	317	java/lang/Exception
  }

  private boolean startDownLoad(Node paramNode)
  {
    if (paramNode == null)
      return false;
    if (!paramNode.nodeName.equalsIgnoreCase("program"))
      return false;
    int j;
    int k;
    int i;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      if (((ProgramNode)paramNode).getCurrPlayStatus() != 3)
        return false;
      str1 = QTLogger.getInstance().buildDownloadLog((ProgramNode)paramNode);
      if (str1 != null)
        LogModule.getInstance().send(Constants.DownloadLogType, str1);
      j = ((ProgramNode)paramNode).getDuration();
      k = ((ProgramNode)paramNode).channelId;
      if (((ProgramNode)paramNode).channelType == 1);
      for (i = 1; ; i = 0)
      {
        str1 = ((ProgramNode)paramNode).getDownLoadUrlPath();
        if ((str1 != null) && (!str1.equalsIgnoreCase("")))
          break;
        return false;
      }
    }
    return false;
    String str2 = getDownloadMeta((ProgramNode)paramNode, k);
    Object localObject1 = getRealDownloadId(str2);
    int m = ((ProgramNode)paramNode).resId;
    str2 = DownloadUtils.escapeFileName(str2);
    Object localObject2;
    if ((str2 != null) && (!str2.equalsIgnoreCase("")))
    {
      localObject2 = getNodeByDownloadId(str2, getDayOfWeek(), System.currentTimeMillis() / 1000L);
      if (localObject2 == null)
        return false;
      if (!((Node)localObject2).nodeName.equalsIgnoreCase("program"))
        break label657;
      ((ProgramNode)localObject2).resId = m;
      if (((ProgramNode)localObject2).downloadInfo == null)
        break label657;
      ((ProgramNode)localObject2).downloadInfo.channelId = k;
      ((ProgramNode)localObject2).downloadInfo.type = i;
      ((ProgramNode)localObject2).downloadInfo.downloadPath = str1;
      ((ProgramNode)localObject2).downloadInfo.state = 0;
      ((ProgramNode)localObject2).downloadInfo.progress = 0;
      ((ProgramNode)localObject2).downloadInfo.fileSize = (((ProgramNode)localObject2).getDuration() * 24 * 125);
      ((ProgramNode)localObject2).downloadInfo.sequence = ((ProgramNode)paramNode).sequence;
      ((ProgramNode)localObject2).downloadInfo.fileName = str2;
      ((ProgramNode)localObject2).downloadInfo.id = ((String)localObject1);
    }
    label657: for (String str1 = ((ProgramNode)localObject2).getNextDownLoadUrl(); ; str1 = null)
    {
      if ((str1 == null) || (str1.equalsIgnoreCase("")))
        return false;
      ((Node)localObject2).parent = this;
      if (this.lstDownLoadingNodes.size() == 0)
        this.lstDownLoadingNodes.add(localObject2);
      while (true)
      {
        this.mNeedSync = true;
        localObject1 = new Downloadobject((String)localObject1, str2, str1);
        ((Downloadobject)localObject1).setFileSize(j * 24 * 125);
        localObject2 = new ArrayList();
        ((List)localObject2).add(str1);
        while (true)
        {
          str1 = ((ProgramNode)paramNode).getNextDownLoadUrl();
          if (TextUtils.isEmpty(str1))
            break;
          ((List)localObject2).add(str1);
        }
        Node localNode = (Node)this.lstDownLoadingNodes.get(this.lstDownLoadingNodes.size() - 1);
        if (localNode != null)
        {
          ((Node)localObject2).prevSibling = localNode;
          localNode.nextSibling = ((Node)localObject2);
        }
        this.lstDownLoadingNodes.add(localObject2);
      }
      paramNode = new File(getDownLoadPath(), ((Downloadobject)localObject1).programName);
      paramNode = new DownloadTask(((Downloadobject)localObject1).uuid, (String[])((List)localObject2).toArray(new String[0]), paramNode.getAbsolutePath());
      DownloadService.getInstance(QTApplication.appContext).addTask(paramNode);
      if (!DownloadService.getInstance(QTApplication.appContext).isDownloading())
        DownloadService.getInstance(QTApplication.appContext).start();
      QTMSGManage.getInstance().sendStatisticsMessageOnceADay("DownloadADay", "startLoad");
      recordDownloadTime(str2);
      return true;
      return false;
    }
  }

  private void syncMetaInfo()
  {
    int i = 0;
    while (i < this.lstDownLoadingNodes.size())
    {
      ProgramNode localProgramNode = (ProgramNode)this.lstDownLoadingNodes.get(i);
      if ((localProgramNode != null) && (localProgramNode.downloadInfo != null))
      {
        String str = localProgramNode.downloadInfo.id;
        if ((str != null) && ((String)this.mapMetaInfo.get(str) == null))
          getDownloadMeta(localProgramNode, localProgramNode.downloadInfo.channelId);
      }
      i += 1;
    }
  }

  private void writeDownloadingToDB()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("nodes", this.lstDownLoadingNodes);
    DataManager.getInstance().getData("updatedb_downloading_program_node", null, localHashMap);
  }

  private void writeDownloadingToDB(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return;
    HashMap localHashMap = new HashMap();
    localHashMap.put("node", paramProgramNode);
    DataManager.getInstance().getData("insertdb_downloading_program_node", null, localHashMap);
  }

  private boolean writeToDAT(String paramString)
  {
    if (paramString == null)
      return false;
    paramString = getRealDownloadId(paramString);
    paramString = (String)this.mapMetaInfo.get(paramString);
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return false;
    try
    {
      Object localObject = getDownLoadPath();
      localObject = new FileWriter((String)localObject + "/" + this.downloadDat, true);
      ((FileWriter)localObject).write(paramString);
      ((FileWriter)localObject).write("\r\n");
      ((FileWriter)localObject).flush();
      ((FileWriter)localObject).close();
      return true;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }

  public void addPathChangeListener(OnDownloadPathChangeListener paramOnDownloadPathChangeListener)
  {
    if (this.mDownloadPathChangeListener == null)
      this.mDownloadPathChangeListener = new ArrayList();
    this.mDownloadPathChangeListener.add(paramOnDownloadPathChangeListener);
  }

  public boolean addToDownloadList(Node paramNode)
  {
    if (paramNode == null)
      return false;
    if (!paramNode.nodeName.equalsIgnoreCase("program"))
      return false;
    if ((hasInDownLoadList(paramNode) != -1) || (hasInDownLoading(paramNode) != -1))
      return false;
    if ((((ProgramNode)paramNode).isMusicChannel()) && (!InfoManager.getInstance().allowDownloadMusic(((ProgramNode)paramNode).channelId)))
    {
      Toast.makeText(InfoManager.getInstance().getContext(), "该节目无法下载", 0).show();
      return false;
    }
    if (startDownLoad(paramNode))
    {
      dispatchDownLoadEvent(paramNode, 8);
      return true;
    }
    return false;
  }

  public boolean allowDownLoad(Node paramNode)
  {
    boolean bool2 = false;
    this.refuseErrorCode = 0;
    boolean bool1 = bool2;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      bool1 = bool2;
      if (((ProgramNode)paramNode).getCurrPlayStatus() == 3)
        bool1 = true;
    }
    return bool1;
  }

  public void changeDownloadPath(String paramString)
  {
    this.directory = paramString;
    if (this.mDownloadPathChangeListener != null)
    {
      int i = 0;
      while (i < this.mDownloadPathChangeListener.size())
      {
        ((OnDownloadPathChangeListener)this.mDownloadPathChangeListener.get(i)).onPathChanged(paramString);
        i += 1;
      }
    }
    GlobalCfg.getInstance(null).changeDownloadPath(paramString);
  }

  public void delDownLoad(ChannelNode paramChannelNode, boolean paramBoolean)
  {
    if (paramChannelNode == null);
    ChannelNode localChannelNode;
    do
    {
      return;
      localChannelNode = (ChannelNode)this.mapChannelNodes.get(Integer.valueOf(paramChannelNode.channelId));
    }
    while (localChannelNode == null);
    if (!localChannelNode.hasEmptyProgramSchedule())
    {
      List localList = localChannelNode.getAllLstProgramNode();
      if (localList != null)
      {
        int i = 0;
        while (i < localList.size())
        {
          ProgramNode localProgramNode = (ProgramNode)localList.get(i);
          if ((localProgramNode != null) && (localProgramNode.downloadInfo != null))
          {
            String str = getRealDownloadId(localProgramNode.downloadInfo.id);
            if ((!DownloadService.getInstance(QTApplication.appContext).removeTask(str)) && (localProgramNode.downloadInfo.fileName != null))
              DownloadUtils.deleteFile(new File(getDownLoadPath(), localProgramNode.downloadInfo.fileName).getAbsolutePath());
            this.mTotalProgramCnt -= 1;
            this.mTotalProgramSize -= localProgramNode.downloadInfo.fileSize;
          }
          i += 1;
        }
        localList.clear();
      }
      localChannelNode.programCnt = 0;
    }
    this.mapChannelNodes.remove(Integer.valueOf(localChannelNode.channelId));
    this.mCategory.mLstChannelNodes.remove(localChannelNode);
    dispatchDownLoadEvent(paramChannelNode, 4);
  }

  public void delDownLoad(ProgramNode paramProgramNode, boolean paramBoolean)
  {
    if (paramProgramNode == null);
    do
    {
      do
        return;
      while (hasInDownLoadList(paramProgramNode) == -1);
      if ((paramProgramNode != null) && (paramProgramNode.downloadInfo != null))
      {
        localObject = getRealDownloadId(paramProgramNode.downloadInfo.id);
        if (!DownloadService.getInstance(QTApplication.appContext).removeTask((String)localObject))
          DownloadUtils.deleteFile(new File(getDownLoadPath(), paramProgramNode.downloadInfo.fileName).getAbsolutePath());
      }
      Object localObject = (ChannelNode)this.mapChannelNodes.get(Integer.valueOf(paramProgramNode.channelId));
      if ((localObject != null) && (!((ChannelNode)localObject).hasEmptyProgramSchedule()))
      {
        ((ChannelNode)localObject).delProgramNode(paramProgramNode.id);
        ((ChannelNode)localObject).programCnt -= 1;
      }
      this.mTotalProgramCnt -= 1;
    }
    while (paramProgramNode.downloadInfo == null);
    this.mTotalProgramSize -= paramProgramNode.downloadInfo.fileSize;
  }

  public void delDownLoading(Node paramNode, boolean paramBoolean)
  {
    if (paramNode == null);
    int i;
    do
    {
      return;
      i = hasInDownLoading(paramNode);
    }
    while (i == -1);
    paramNode = (ProgramNode)this.lstDownLoadingNodes.get(i);
    if ((paramNode != null) && (paramNode.downloadInfo != null))
    {
      String str = getRealDownloadId(paramNode.downloadInfo.id);
      if (!DownloadService.getInstance(QTApplication.appContext).removeTask(str))
        DownloadUtils.deleteFile(paramNode.downloadInfo.fileName);
    }
    this.lstDownLoadingNodes.remove(i);
    dispatchDownLoadEvent(paramNode, 4);
    this.mNeedSync = true;
  }

  public int getAllDownloadCount()
  {
    return this.lstDownLoadingNodes.size();
  }

  public List<Node> getAllDownloadingNodes()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(this.lstDownLoadingNodes);
    return localArrayList;
  }

  public Node getCategory()
  {
    return this.mCategory;
  }

  public ChannelNode getChannelNode(int paramInt)
  {
    if ((this.mCategory != null) && (this.mCategory.mLstChannelNodes != null))
    {
      int i = 0;
      while (i < this.mCategory.mLstChannelNodes.size())
      {
        if (((ChannelNode)this.mCategory.mLstChannelNodes.get(i)).channelId == paramInt)
          return (ChannelNode)this.mCategory.mLstChannelNodes.get(i);
        i += 1;
      }
    }
    return null;
  }

  public String getDownLoadPath()
  {
    if (this.directory == null)
      this.directory = GlobalCfg.getInstance(null).getDownloadPath();
    return this.directory;
  }

  public List<Node> getDownLoadedList()
  {
    if (!this.hasRestored)
      restoreFromDir();
    return null;
  }

  public int getDownloadErrorCode()
  {
    return this.refuseErrorCode;
  }

  public String getDownloadedProgramSource(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return null;
    int i;
    Object localObject;
    if (paramProgramNode.channelType == 1)
    {
      i = paramProgramNode.channelId;
      localObject = (ChannelNode)this.mapChannelNodes.get(Integer.valueOf(i));
      if ((localObject != null) && (!((ChannelNode)localObject).hasEmptyProgramSchedule()))
      {
        localObject = ((ChannelNode)localObject).getAllLstProgramNode();
        if (localObject != null)
          i = 0;
      }
    }
    else
    {
      while (true)
      {
        if (i >= ((List)localObject).size())
          break label117;
        if (paramProgramNode.id == ((ProgramNode)((List)localObject).get(i)).id)
        {
          return ((ProgramNode)((List)localObject).get(i)).getSourceUrl();
          i = paramProgramNode.uniqueId;
          break;
        }
        i += 1;
      }
    }
    label117: return null;
  }

  public List<ChannelNode> getLstChannelNodes()
  {
    if (this.mCategory != null)
      return this.mCategory.mLstChannelNodes;
    return null;
  }

  public List<Node> getLstDownloadingNodes()
  {
    if (!this.hasRestoreDownloading)
      restoreDownloading();
    return this.lstDownLoadingNodes;
  }

  public int getTotalProgramCnt()
  {
    return this.mTotalProgramCnt;
  }

  public long getTotalProgramSize()
  {
    return this.mTotalProgramSize;
  }

  public int hasDownLoad(Node paramNode)
  {
    if ((paramNode != null) && (paramNode.nodeName.equalsIgnoreCase("program")))
      return downloadState(paramNode);
    return 0;
  }

  public int hasInDownLoadList(Node paramNode)
  {
    int j = 0;
    if (paramNode == null)
      return -1;
    this.refuseErrorCode = 0;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      ProgramNode localProgramNode = (ProgramNode)paramNode;
      int i;
      int k;
      if (((ProgramNode)paramNode).channelType == 1)
      {
        i = ((ProgramNode)paramNode).channelId;
        paramNode = (ChannelNode)this.mapChannelNodes.get(Integer.valueOf(i));
        if ((paramNode != null) && (!paramNode.hasEmptyProgramSchedule()))
        {
          paramNode = paramNode.getAllLstProgramNode();
          if (paramNode != null)
          {
            k = paramNode.size();
            i = j;
          }
        }
      }
      else
      {
        while (true)
        {
          if (i >= k)
            break label179;
          if (localProgramNode.id == ((ProgramNode)paramNode.get(i)).id)
          {
            if ((localProgramNode.channelType == 0) && (localProgramNode.getAbsoluteStartTime() != ((ProgramNode)paramNode.get(i)).getUpdateTime()))
            {
              return -1;
              i = ((ProgramNode)paramNode).uniqueId;
              break;
            }
            this.refuseErrorCode = 2;
            return i;
          }
          i += 1;
        }
      }
      label179: return -1;
    }
    return -1;
  }

  public int hasInDownLoading(Node paramNode)
  {
    int i = 0;
    if (paramNode == null)
      return -1;
    this.refuseErrorCode = 0;
    if (paramNode.nodeName.equalsIgnoreCase("program"))
    {
      int j = this.lstDownLoadingNodes.size();
      while (i < j)
      {
        if (((ProgramNode)paramNode).id == ((ProgramNode)this.lstDownLoadingNodes.get(i)).id)
        {
          this.refuseErrorCode = 2;
          return i;
        }
        i += 1;
      }
    }
    return -1;
  }

  public boolean hasNodeDownloaded(Node paramNode)
  {
    if (((paramNode instanceof ProgramNode)) && (((ProgramNode)paramNode).downloadInfo != null))
      return ((ProgramNode)paramNode).downloadInfo.state == 3;
    return false;
  }

  public void init()
  {
    DownloadService.getInstance(QTApplication.appContext).addDownloadListener(this);
    parseDAT();
    getDownLoadedList();
    if (!LifeTime.isFirstLaunchAfterInstall)
      getLstDownloadingNodes();
    syncMetaInfo();
  }

  public boolean isSDCardAvailable()
  {
    return DownloadUtils.isSDCardAvailable();
  }

  public void onDownloadEvent(String paramString, DownloadState paramDownloadState, Object paramObject)
  {
    try
    {
      switch (1.$SwitchMap$fm$qingting$downloadnew$DownloadState[paramDownloadState.ordinal()])
      {
      case 1:
        onDownloadFailed(paramString);
        if (paramObject != null)
        {
          QTMSGManage.getInstance().sendStatistcsMessage("NewDownload4", "failed:" + InfoManager.getInstance().getNetWorkType() + "_" + paramObject.toString());
          return;
        }
        break;
      case 4:
        onDownloadProcessing(paramString, 0);
        return;
      case 5:
        paramDownloadState = getTask(paramString);
        if (paramDownloadState != null)
        {
          onDownloadProcessing(paramString, (int)paramDownloadState.getCurSize());
          return;
        }
        break;
      case 6:
        onDownloadSuccess(paramString);
        return;
      case 2:
      case 3:
      }
      return;
    }
    catch (Exception paramString)
    {
    }
  }

  public void pauseDownLoad(Node paramNode, boolean paramBoolean)
  {
    if (paramNode == null);
    do
    {
      int i;
      do
      {
        return;
        i = hasInDownLoading(paramNode);
      }
      while (i == -1);
      paramNode = (ProgramNode)this.lstDownLoadingNodes.get(i);
    }
    while ((paramNode == null) || (paramNode.downloadInfo == null));
    DownloadService.getInstance(QTApplication.appContext).pauseTask(paramNode.downloadInfo.id);
    endDownloadTime(paramNode.downloadInfo.id, paramBoolean);
    writeDownloadingToDB(paramNode);
  }

  public void registerListener(IDownloadInfoEventListener paramIDownloadInfoEventListener)
  {
    if (paramIDownloadInfoEventListener == null)
      return;
    Iterator localIterator = this.mlstDLEventListeners.iterator();
    while (localIterator.hasNext())
      if ((IDownloadInfoEventListener)localIterator.next() == paramIDownloadInfoEventListener)
        return;
    this.mlstDLEventListeners.add(paramIDownloadInfoEventListener);
  }

  public void removePathChangeListener(OnDownloadPathChangeListener paramOnDownloadPathChangeListener)
  {
    if ((this.mDownloadPathChangeListener == null) || (paramOnDownloadPathChangeListener == null))
      return;
    this.mDownloadPathChangeListener.remove(paramOnDownloadPathChangeListener);
  }

  public void restoreFromDir()
  {
    if (this.hasRestored)
      return;
    this.hasRestored = true;
    String str1 = getDownLoadPath();
    String str2 = Environment.getExternalStorageDirectory() + File.separator + "QTDownloadRadio";
    if (!TextUtils.equals(str1, str2))
      restoreFromDir(str2);
    restoreFromDir(str1);
  }

  public void resumeDownLoad(Node paramNode)
  {
    if (paramNode == null);
    do
    {
      int i;
      do
      {
        return;
        i = hasInDownLoading(paramNode);
      }
      while (i == -1);
      paramNode = (ProgramNode)this.lstDownLoadingNodes.get(i);
    }
    while ((paramNode == null) || (paramNode.downloadInfo == null));
    paramNode.downloadInfo.state = 0;
    DownloadService.getInstance(QTApplication.appContext).resumeTask(paramNode.downloadInfo.id);
    if (!DownloadService.getInstance(QTApplication.appContext).isDownloading())
      DownloadService.getInstance(QTApplication.appContext).start();
    recordDownloadTime(paramNode.downloadInfo.id);
  }

  public boolean ringHasDownloaded(Node paramNode)
  {
    if (paramNode == null);
    while (true)
    {
      return false;
      if (paramNode.nodeName.equalsIgnoreCase("ringtone"))
      {
        this.refuseErrorCode = 0;
        int i = 0;
        while (i < this.lstDownLoadedRings.size())
        {
          if (((RingToneNode)this.lstDownLoadedRings.get(i)).ringToneId.equalsIgnoreCase(((RingToneNode)paramNode).ringToneId))
          {
            this.refuseErrorCode = 2;
            return true;
          }
          i += 1;
        }
      }
    }
  }

  public void saveDownloading()
  {
    int i = 0;
    while (i < this.lstDownLoadingNodes.size())
    {
      ProgramNode localProgramNode = (ProgramNode)this.lstDownLoadingNodes.get(i);
      if ((localProgramNode != null) && (localProgramNode.downloadInfo != null))
        DownloadService.getInstance(QTApplication.appContext).pauseTask(localProgramNode.downloadInfo.id);
      i += 1;
    }
    writeDownloadingToDB();
  }

  public boolean startDownLoadRing(Node paramNode)
  {
    if (paramNode == null);
    String str;
    do
    {
      do
        return false;
      while ((ringHasDownloaded(paramNode)) || (!paramNode.nodeName.equalsIgnoreCase("ringtone")));
      l = 0L;
      if (!paramNode.nodeName.equalsIgnoreCase("ringtone"))
        break;
      str = ((RingToneNode)paramNode).getDownLoadUrl();
    }
    while ((str == null) || (str.equalsIgnoreCase("")));
    int i = ((RingToneNode)paramNode).getDuration();
    long l = Long.valueOf(((RingToneNode)paramNode).updatetime).longValue();
    while (true)
    {
      Object localObject1 = buildFileNameByNode(paramNode, l);
      if ((localObject1 == null) || (((String)localObject1).equalsIgnoreCase("")) || (str == null) || (str.equalsIgnoreCase("")))
        break;
      Object localObject2 = buildNodeByFileName((String)localObject1, getDayOfWeek(), System.currentTimeMillis() / 1000L);
      if (localObject2 == null)
        break;
      ((RingToneNode)localObject2).mDownLoadPath = ((RingToneNode)paramNode).mDownLoadPath;
      ((RingToneNode)localObject2).mTranscode = ((RingToneNode)paramNode).mTranscode;
      ((RingToneNode)localObject2).ringDesc = ((RingToneNode)paramNode).ringDesc;
      ((RingToneNode)localObject2).ringType = ((RingToneNode)paramNode).ringType;
      ((RingToneNode)localObject2).type = ((RingToneNode)paramNode).type;
      ((Node)localObject2).parent = this;
      this.lstDownLoadedRings.add(localObject2);
      localObject1 = new Downloadobject((String)localObject1, (String)localObject1, str);
      ((Downloadobject)localObject1).setFileSize(i * 24 * 125);
      localObject2 = new File(getDownLoadPath(), ((Downloadobject)localObject1).programName);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(str);
      while (true)
      {
        str = ((ProgramNode)paramNode).getNextDownLoadUrl();
        if (TextUtils.isEmpty(str))
          break;
        localArrayList.add(str);
      }
      paramNode = new DownloadTask(((Downloadobject)localObject1).uuid, (String[])localArrayList.toArray(new String[0]), ((File)localObject2).getAbsolutePath());
      DownloadService.getInstance(QTApplication.appContext).addTask(paramNode);
      if (!DownloadService.getInstance(QTApplication.appContext).isDownloading())
        DownloadService.getInstance(QTApplication.appContext).start();
      QTMSGManage.getInstance().sendStatisticsMessageOnceADay("DownloadADay", "startLoad");
      return false;
      i = 0;
      str = null;
    }
  }

  public void syncDownloadingToDB()
  {
    if (this.mNeedSync)
      writeDownloadingToDB();
    this.mNeedSync = false;
  }

  public void unregisterListener(IDownloadInfoEventListener paramIDownloadInfoEventListener)
  {
    if (paramIDownloadInfoEventListener == null);
    IDownloadInfoEventListener localIDownloadInfoEventListener;
    do
    {
      return;
      Iterator localIterator;
      while (!localIterator.hasNext())
        localIterator = this.mlstDLEventListeners.iterator();
      localIDownloadInfoEventListener = (IDownloadInfoEventListener)localIterator.next();
    }
    while (localIDownloadInfoEventListener != paramIDownloadInfoEventListener);
    this.mlstDLEventListeners.remove(localIDownloadInfoEventListener);
  }

  public static abstract interface IDownloadInfoEventListener
  {
    public static final int DOWNLOAD_ADDED = 8;
    public static final int DOWNLOAD_COMPLETE = 1;
    public static final int DOWNLOAD_DELETED = 4;
    public static final int DOWNLOAD_FAILED = 2;
    public static final int DOWNLOAD_PROGRESS = 0;

    public abstract void onDownLoadInfoUpdated(int paramInt, Node paramNode);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.DownLoadInfoNode
 * JD-Core Version:    0.6.2
 */