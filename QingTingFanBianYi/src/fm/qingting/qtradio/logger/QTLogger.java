package fm.qingting.qtradio.logger;

import android.content.Context;
import fm.qingting.framework.utils.MobileState;
import fm.qingting.qtradio.model.BillboardItemNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.ShareBean;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.DateUtil;
import fm.qingting.utils.DeviceInfo;
import fm.qingting.utils.OperatorInfo;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class QTLogger
  implements LoggerAPI
{
  private static QTLogger instance;
  private Context _context;
  public String city;
  private String downloadException;
  public String fmAvailable;
  private String mGetuiClientID = null;
  private String mLocalIp = null;
  private int mSpecialTopicId = 0;
  private String mSpecialTopicName = null;
  public String region;

  private String buildOtherString(String paramString1, String paramString2)
  {
    try
    {
      String str = buildCommonLog();
      if (str == null)
        return null;
      str = "" + str;
      str = str + "\"";
      paramString1 = str + paramString1;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + paramString2;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + "\n";
      return paramString1;
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public static QTLogger getInstance()
  {
    if (instance == null)
      instance = new QTLogger();
    return instance;
  }

  public static String getNetworkInfo(Context paramContext)
  {
    int i = MobileState.getNetWorkType(paramContext);
    if (i == 2)
      return "0";
    if (i == 1)
      return "1";
    if (i == 3)
      return "2";
    if (i == 5)
      return "3";
    return "-1";
  }

  public static String msToDate(long paramLong)
  {
    Date localDate = new Date(paramLong);
    return new SimpleDateFormat("yyyy-MM-dd", Locale.US).format(localDate);
  }

  public void addDownloadExceptionLog(String paramString)
  {
    this.downloadException = paramString;
  }

  public String buildBillboardShowLog(BillboardItemNode paramBillboardItemNode, int paramInt1, int paramInt2)
  {
    if ((paramBillboardItemNode != null) && (paramInt2 < 0));
    return null;
  }

  public String buildCommonLog()
  {
    return buildCommonLog(null, null, null);
  }

  public String buildCommonLog(String paramString1, String paramString2, String paramString3)
  {
    try
    {
      Object localObject = "" + "\"";
      localObject = (String)localObject + DateUtil.getCurrentSeconds();
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + ",";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + "+8";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + ",";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + "Android";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + ",";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + DeviceInfo.getUniqueId(this._context);
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + ",";
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + OperatorInfo.OperatorToStr(OperatorInfo.getOperator(this._context));
      localObject = (String)localObject + "\"";
      localObject = (String)localObject + ",";
      label657: label928: String str;
      if (this._context != null)
      {
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + String.valueOf(AppInfo.getCurrentInternalVersion(this._context));
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        if (this._context == null)
          break label2008;
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + AppInfo.getChannelName(this._context);
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + DeviceInfo.getDeviceName().replace(",", " ");
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + DeviceInfo.getAndroidOsVersion();
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        if (this.mGetuiClientID == null)
          this.mGetuiClientID = GlobalCfg.getInstance(this._context).getGeTuiClientID();
        if ((this.mGetuiClientID == null) || (this.mGetuiClientID.equalsIgnoreCase("")))
          break label2033;
        localObject = (String)localObject + this.mGetuiClientID;
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + getNetworkInfo(this._context);
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        if (this.mLocalIp == null)
          this.mLocalIp = GlobalCfg.getInstance(this._context).getLocalIp();
        if ((this.mLocalIp == null) || (this.mLocalIp.equalsIgnoreCase("")) || (this.mLocalIp.equalsIgnoreCase("\n")))
          break label2058;
        localObject = (String)localObject + this.mLocalIp;
        label1163: localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + "China";
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        str = (String)localObject + "\"";
        localObject = str;
        if (this.region != null)
          localObject = str + this.region;
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        str = (String)localObject + "\"";
        localObject = str;
        if (this.city != null)
          localObject = str + this.city;
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        localObject = (String)localObject + "\"";
        if (this.fmAvailable == null)
          break label2083;
      }
      label2058: label2083: for (localObject = (String)localObject + this.fmAvailable; ; localObject = (String)localObject + "")
      {
        localObject = (String)localObject + "\"";
        localObject = (String)localObject + ",";
        str = (String)localObject + "\"";
        localObject = str;
        if (paramString1 != null)
        {
          localObject = str;
          if (paramString1.trim().length() > 0)
            localObject = str + paramString1;
        }
        paramString1 = (String)localObject + "\"";
        paramString1 = paramString1 + ",";
        localObject = paramString1 + "\"";
        paramString1 = (String)localObject;
        if (paramString2 != null)
        {
          paramString1 = (String)localObject;
          if (paramString2.trim().length() > 0)
            paramString1 = (String)localObject + paramString2;
        }
        paramString1 = paramString1 + "\"";
        paramString1 = paramString1 + ",";
        paramString2 = paramString1 + "\"";
        paramString1 = paramString2;
        if (paramString3 != null)
        {
          paramString1 = paramString2;
          if (paramString3.trim().length() > 0)
            paramString1 = paramString2 + paramString3;
        }
        paramString1 = paramString1 + "\"";
        paramString1 = paramString1 + ",";
        paramString1 = paramString1 + "\"";
        paramString1 = paramString1 + this._context.getPackageName();
        paramString1 = paramString1 + "\"";
        return paramString1 + ",";
        localObject = (String)localObject + "\"\",";
        break;
        label2008: localObject = (String)localObject + "\"\",";
        break label657;
        label2033: localObject = (String)localObject + "";
        break label928;
        localObject = (String)localObject + "";
        break label1163;
      }
    }
    catch (Exception paramString1)
    {
      paramString1.printStackTrace();
    }
    return null;
  }

  public String buildDeviceIdLog(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return null;
      str = buildCommonLog();
    }
    while (str == null);
    try
    {
      str = str + "\"";
      paramString = str + paramString;
      paramString = paramString + "\"";
      paramString = paramString + "\n";
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public String buildDownloadLog(Node paramNode, boolean paramBoolean)
  {
    if ((paramNode != null) && (!paramNode.nodeName.equalsIgnoreCase("program")));
    return null;
  }

  public String buildDownloadLog(ProgramNode paramProgramNode)
  {
    String str = buildCommonLog();
    if (str != null)
    {
      str = str + "\"";
      str = str + paramProgramNode.channelType;
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + paramProgramNode.getCategoryId();
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + paramProgramNode.channelId;
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      paramProgramNode = str + paramProgramNode.uniqueId;
      paramProgramNode = paramProgramNode + "\"";
      return paramProgramNode + "\n";
    }
    return null;
  }

  public String buildEnterIMLog(int paramInt)
  {
    String str = buildCommonLog();
    if (str != null)
      try
      {
        str = str + "\"";
        str = str + String.valueOf(paramInt);
        str = str + "\"";
        str = str + "\n";
        return str;
      }
      catch (Exception localException)
      {
      }
    return null;
  }

  public String buildIMGroupRelationLog(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String str;
    do
    {
      return null;
      str = buildCommonLog();
    }
    while (str == null);
    try
    {
      str = str + "\"";
      paramString1 = str + paramString1;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + paramString2;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + String.valueOf(paramInt);
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + "\n";
      return paramString1;
    }
    catch (Exception paramString1)
    {
    }
    return null;
  }

  public String buildIMSendGroupLog(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String str;
    do
    {
      return null;
      str = buildCommonLog();
    }
    while (str == null);
    try
    {
      str = str + "\"";
      paramString1 = str + paramString1;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + paramString2;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + "\n";
      return paramString1;
    }
    catch (Exception paramString1)
    {
    }
    return null;
  }

  public String buildIMSendUserLog(String paramString1, String paramString2)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String str;
    do
    {
      return null;
      str = buildCommonLog();
    }
    while (str == null);
    try
    {
      str = str + "\"";
      paramString1 = str + paramString1;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + paramString2;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + "\n";
      return paramString1;
    }
    catch (Exception paramString1)
    {
    }
    return null;
  }

  public String buildIMUserRelationLog(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null));
    String str;
    do
    {
      return null;
      str = buildCommonLog();
    }
    while (str == null);
    try
    {
      str = str + "\"";
      paramString1 = str + paramString1;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + paramString2;
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + ",";
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + String.valueOf(paramInt);
      paramString1 = paramString1 + "\"";
      paramString1 = paramString1 + "\n";
      return paramString1;
    }
    catch (Exception paramString1)
    {
    }
    return null;
  }

  public String buildListeneringQualityLog(Node paramNode, double paramDouble, int paramInt, String paramString)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("program")) || (paramString == null));
    while (true)
    {
      return null;
      try
      {
        paramNode = (ProgramNode)paramNode;
        if (!paramNode.isDownloadProgram())
        {
          String str = buildCommonLog();
          if ((str != null) && (paramNode.resId != 0))
          {
            str = str + "\"";
            str = str + paramNode.resId;
            str = str + "\"";
            str = str + ",";
            str = str + "\"";
            str = str + paramNode.id;
            str = str + "\"";
            str = str + ",";
            str = str + "\"";
            if ((paramNode.channelType == 0) && (paramNode.getCurrPlayStatus() == 3));
            for (paramNode = str + "2"; ; paramNode = str + String.valueOf(paramNode.channelType))
            {
              paramNode = paramNode + "\"";
              paramNode = paramNode + ",";
              paramNode = paramNode + "\"";
              paramNode = paramNode + String.valueOf(paramInt);
              paramNode = paramNode + "\"";
              paramNode = paramNode + ",";
              paramNode = paramNode + "\"";
              paramNode = paramNode + String.valueOf(paramDouble);
              paramNode = paramNode + "\"";
              paramNode = paramNode + ",";
              paramNode = paramNode + "\"";
              paramNode = paramNode + String.valueOf(paramString);
              paramNode = paramNode + "\"";
              return paramNode + "\n";
            }
          }
        }
      }
      catch (Exception paramNode)
      {
      }
    }
    return null;
  }

  public String buildProgramsShowLog(List<ProgramNode> paramList, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    if (paramList == null)
      return null;
    if (paramList.size() > 0);
    while (true)
    {
      try
      {
        str1 = "" + "\"";
        str1 = str1 + DeviceInfo.getUniqueId(this._context);
        str1 = str1 + "\"";
        str1 = str1 + ",";
        str1 = str1 + "\"";
        if (paramBoolean)
        {
          str1 = str1 + "1";
          str1 = str1 + "\"";
          str1 = str1 + ",";
          str1 = str1 + "\"";
          str1 = str1 + String.valueOf(paramInt4);
          str1 = str1 + "\"";
          str1 = str1 + ",";
          str1 = str1 + "\"";
          str1 = str1 + paramInt1;
          str1 = str1 + "\"";
          str1 = str1 + ",";
          str1 = str1 + "\"";
          str1 = str1 + paramInt2;
          str1 = str1 + "\"";
          str1 = str1 + ",";
          str1 = str1 + "\"";
          str1 = str1 + paramInt3;
          str1 = str1 + "\"";
          String str3 = str1 + ",";
          if (!paramBoolean)
            break label771;
          str2 = "";
          paramList = str3 + "\"";
          paramList = paramList + str2;
          paramList = paramList + "\"";
          return paramList + "\n";
        }
        str1 = str1 + "0";
        continue;
        String str2 = str1;
        if (paramInt1 >= paramList.size())
          continue;
        if (str1 == null)
        {
          str1 = String.valueOf(((ProgramNode)paramList.get(paramInt1)).id);
        }
        else
        {
          str1 = str1 + "_";
          str1 = str1 + ((ProgramNode)paramList.get(paramInt1)).id;
        }
      }
      catch (Exception paramList)
      {
      }
      return null;
      label771: paramInt1 = 0;
      String str1 = null;
      continue;
      paramInt1 += 1;
    }
  }

  public String buildPublishLog(ShareBean paramShareBean)
  {
    if (paramShareBean == null);
    while (true)
    {
      return null;
      try
      {
        String str = buildCommonLog();
        if (str != null)
        {
          str = str + "\"";
          str = str + paramShareBean.platform;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + String.valueOf(paramShareBean.channelType);
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + paramShareBean.categoryId;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + paramShareBean.channelId;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          if (paramShareBean.programId == 0)
          {
            str = str + "";
            str = str + "\"";
            str = str + ",";
            str = str + "\"";
            if (paramShareBean.snsId != null)
              break label717;
            str = str + "";
            label480: str = str + "\"";
            str = str + ",";
            str = str + "\"";
            if (paramShareBean.broadcasterName != null)
              break label742;
          }
          label717: label742: for (str = str + ""; ; str = str + paramShareBean.broadcasterName)
          {
            str = str + "\"";
            str = str + ",";
            str = str + "\"";
            paramShareBean = str + String.valueOf(paramShareBean.time);
            paramShareBean = paramShareBean + "\"";
            return paramShareBean + "\n";
            str = str + paramShareBean.programId;
            break;
            str = str + paramShareBean.snsId;
            break label480;
          }
        }
      }
      catch (Exception paramShareBean)
      {
      }
    }
    return null;
  }

  public String buildRecommendShowLog(RecommendItemNode paramRecommendItemNode, int paramInt1, int paramInt2, String paramString1, String paramString2)
  {
    return null;
  }

  public String buildResourceUnavailLog(Node paramNode)
  {
    if ((paramNode == null) || (!paramNode.nodeName.equalsIgnoreCase("program")))
      return null;
    while (true)
    {
      try
      {
        ProgramNode localProgramNode = (ProgramNode)paramNode;
        paramNode = buildCommonLog();
        if ((paramNode == null) || (localProgramNode.resId == 0))
          break;
        paramNode = paramNode + "\"";
        paramNode = paramNode + localProgramNode.resId;
        paramNode = paramNode + "\"";
        paramNode = paramNode + ",";
        paramNode = paramNode + "\"";
        paramNode = paramNode + localProgramNode.id;
        paramNode = paramNode + "\"";
        paramNode = paramNode + ",";
        paramNode = paramNode + "\"";
        if (localProgramNode.channelType == 0)
        {
          i = localProgramNode.getCurrPlayStatus();
          if ((localProgramNode.channelType == 0) && (i == 3))
          {
            paramNode = paramNode + "2";
            paramNode = paramNode + "\"";
            paramNode = paramNode + ",";
            paramNode = paramNode + "\"";
            if ((localProgramNode.channelType == 0) && (i == 3))
            {
              paramNode = paramNode + localProgramNode.getSourceUrl();
              paramNode = paramNode + "\"";
              return paramNode + "\n";
            }
          }
          else
          {
            paramNode = paramNode + localProgramNode.channelType;
            continue;
          }
          paramNode = paramNode + "";
          continue;
        }
      }
      catch (Exception paramNode)
      {
        return null;
      }
      int i = 0;
    }
  }

  public String buildSearchKeywordLogString(String paramString, List<SearchItemNode> paramList)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")) || (paramList == null) || (paramList.size() == 0))
      return null;
    while (true)
    {
      String str1;
      int i;
      try
      {
        str1 = buildCommonLog();
        if (str1 == null)
          return null;
        str1 = str1 + "\"";
        paramString = str1 + paramString;
        paramString = paramString + "\"";
        String str2 = paramString + ",";
        paramString = "";
        i = 0;
        if ((i < paramList.size()) && (i < 10))
        {
          if (((SearchItemNode)paramList.get(i)).groupType == 0)
          {
            paramString = paramString + "2_";
            paramString = paramString + ((SearchItemNode)paramList.get(i)).channelId;
            str1 = paramString;
            if (i >= paramList.size() - 1)
              break label497;
            str1 = paramString;
            if (i >= 9)
              break label497;
            str1 = paramString + ":";
            break label497;
          }
          if (((SearchItemNode)paramList.get(i)).groupType == 2)
          {
            paramString = paramString + "3_";
            paramString = paramString + ((SearchItemNode)paramList.get(i)).channelId;
            continue;
          }
          if (((SearchItemNode)paramList.get(i)).groupType == 1)
          {
            paramString = paramString + "4_";
            paramString = paramString + ((SearchItemNode)paramList.get(i)).programId;
            continue;
          }
        }
        else
        {
          paramList = str2 + "\"";
          paramString = paramList + paramString;
          paramString = paramString + "\"";
          paramString = paramString + "\n";
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return null;
      }
      continue;
      label497: i += 1;
      paramString = str1;
    }
  }

  public String buildSearchedClickLog(SearchItemNode paramSearchItemNode)
  {
    Object localObject;
    if (paramSearchItemNode == null)
      localObject = null;
    while (true)
    {
      return localObject;
      String str1 = "";
      try
      {
        int i = InfoManager.getInstance().root().mSearchNode.getSearchIndex(paramSearchItemNode);
        localObject = str1;
        if (i >= 0)
        {
          String str2 = InfoManager.getInstance().root().mSearchNode.getLastKeyword();
          localObject = str1;
          if (str2 != null)
          {
            localObject = str1;
            if (!str2.equalsIgnoreCase(""))
            {
              localObject = buildCommonLog();
              if (localObject == null)
                return null;
              localObject = "" + (String)localObject;
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + str2;
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + ",";
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + InfoManager.getInstance().root().mSearchNode.mSearchPageType;
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + ",";
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + i;
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + ",";
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + paramSearchItemNode.channelId;
              localObject = (String)localObject + "\"";
              localObject = (String)localObject + ",";
              localObject = (String)localObject + "\"";
              paramSearchItemNode = (String)localObject + paramSearchItemNode.programId;
              paramSearchItemNode = paramSearchItemNode + "\"";
              paramSearchItemNode = paramSearchItemNode + "\n";
              return paramSearchItemNode;
            }
          }
        }
      }
      catch (Exception paramSearchItemNode)
      {
        paramSearchItemNode.printStackTrace();
      }
    }
    return null;
  }

  public String buildShareLog(ProgramNode paramProgramNode, int paramInt)
  {
    String str = buildCommonLog();
    if (str != null)
    {
      str = str + "\"";
      str = str + paramInt;
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + paramProgramNode.channelType;
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + paramProgramNode.getCategoryId();
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + paramProgramNode.channelId;
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      paramProgramNode = str + paramProgramNode.uniqueId;
      paramProgramNode = paramProgramNode + "\"";
      return paramProgramNode + "\n";
    }
    return null;
  }

  public String buildSpecialTopicClickLog(int paramInt1, int paramInt2)
  {
    if ((this.mSpecialTopicId == 0) || (this.mSpecialTopicName == null));
    while (true)
    {
      return null;
      try
      {
        String str = buildCommonLog();
        if (str != null)
        {
          str = str + "\"";
          str = str + this.mSpecialTopicId;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + this.mSpecialTopicName;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + paramInt1;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + paramInt2;
          str = str + "\"";
          str = str + "\n";
          return str;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return null;
  }

  public String buildSpecialTopicLog(SpecialTopicNode paramSpecialTopicNode)
  {
    if (paramSpecialTopicNode == null);
    while (true)
    {
      return null;
      try
      {
        String str = buildCommonLog();
        if (str != null)
        {
          str = str + "\"";
          str = str + paramSpecialTopicNode.getApiId();
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + paramSpecialTopicNode.title;
          str = str + "\"";
          str = str + "\n";
          this.mSpecialTopicName = paramSpecialTopicNode.title;
          this.mSpecialTopicId = paramSpecialTopicNode.getApiId();
          return str;
        }
      }
      catch (Exception paramSpecialTopicNode)
      {
      }
    }
    return null;
  }

  public String buildSpeedTest(String paramString, List<String> paramList, List<Long> paramList1)
  {
    if ((paramString == null) || (paramList == null))
      return null;
    try
    {
      String str = buildCommonLog();
      if (str != null)
      {
        str = str + "\"";
        paramString = str + paramString;
        paramString = paramString + "\"";
        str = paramString + ",";
        paramString = "";
        int i = 0;
        while (i < paramList.size())
        {
          paramString = paramString + (String)paramList.get(i);
          paramString = paramString + ";";
          i += 1;
        }
        paramList = str + "\"";
        paramString = paramList + paramString;
        paramList = paramString + "\"";
        paramString = "";
        i = 0;
        while (i < paramList1.size())
        {
          paramString = paramString + String.valueOf(paramList1.get(i));
          paramString = paramString + ";";
          i += 1;
        }
        paramList = paramList + ",";
        paramList = paramList + "\"";
        paramString = paramList + paramString;
        paramString = paramString + "\"";
        paramString = paramString + "\n";
        return paramString;
      }
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  public String buildUserLog()
  {
    String str = buildCommonLog();
    if (str != null)
    {
      str = str + "\"";
      str = str + SharedCfg.getInstance().getChooseGender();
      str = str + "\"";
      str = str + ",";
      str = str + "\"";
      str = str + SharedCfg.getInstance().getChooseUser();
      str = str + "\"";
      return str + "\n";
    }
    return null;
  }

  public String getDownloadException()
  {
    return this.downloadException;
  }

  public String getGeTuiCID()
  {
    if (this.mGetuiClientID == null)
      this.mGetuiClientID = GlobalCfg.getInstance(this._context).getGeTuiClientID();
    return this.mGetuiClientID;
  }

  public void log(String paramString1, String paramString2)
  {
  }

  public void sendCollectionLog(Node paramNode)
  {
    if (paramNode == null);
    while (true)
    {
      return;
      try
      {
        String str = buildCommonLog();
        if ((str != null) && (paramNode != null) && (paramNode.nodeName.equalsIgnoreCase("channel")))
        {
          str = str + "\"";
          str = str + ((ChannelNode)paramNode).channelId;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          str = str + ((ChannelNode)paramNode).title;
          str = str + "\"";
          str = str + ",";
          str = str + "\"";
          paramNode = str + ((ChannelNode)paramNode).categoryId;
          paramNode = paramNode + "\"";
          paramNode = paramNode + ",";
          paramNode = paramNode + "\"";
          paramNode = paramNode + "";
          paramNode = paramNode + "\"";
          DisruptorHelper.produce("favoriteChannels", paramNode + "\n");
          return;
        }
      }
      catch (Exception paramNode)
      {
      }
    }
  }

  public void sendOther(String paramString1, String paramString2)
  {
    if ((paramString1 != null) && (paramString2 != null) && (!paramString1.equalsIgnoreCase("")) && (!paramString2.equalsIgnoreCase("")))
    {
      paramString1 = buildOtherString(paramString1, paramString2);
      if (paramString1 != null)
        DisruptorHelper.produce("other", paramString1);
    }
  }

  public void sendResourceUnavailLog(String paramString)
  {
    if (paramString != null)
      DisruptorHelper.produce("ResourceUnavailable", paramString);
  }

  public void sendSearchLog()
  {
  }

  public void sendSearchResultLog()
  {
  }

  public void setCity(String paramString)
  {
    this.city = paramString;
  }

  public void setContext(Context paramContext)
  {
    this._context = paramContext;
  }

  public void setFMAvailable(String paramString)
  {
    this.fmAvailable = paramString;
  }

  public void setGeTuiCID(String paramString)
  {
    if (paramString != null)
      this.mGetuiClientID = paramString;
  }

  public void setLocalIp(String paramString)
  {
    this.mLocalIp = paramString;
  }

  public void setRegion(String paramString)
  {
    this.region = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.logger.QTLogger
 * JD-Core Version:    0.6.2
 */