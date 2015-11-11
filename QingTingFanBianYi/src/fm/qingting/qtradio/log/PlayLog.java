package fm.qingting.qtradio.log;

import android.content.Context;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.ProgramABTestBean;
import java.util.ArrayList;
import java.util.List;

public class PlayLog
{
  public static final int ALARM = 12;
  public static final int ALIAS_PUSH = 32;
  public static final int CATEGORY_ALL = 26;
  public static final int CATEGORY_BIG = 25;
  public static final int CATEGORY_FORM = 40;
  public static final int CATEGORY_MORE = 34;
  public static final int CATEGORY_SMALL = 36;
  public static final int CATEGORY_TOPIC = 27;
  public static final int COLLECTION = 4;
  public static final int CONENT_UPDATE_PUSH = 28;
  public static final int CONTINUE_PLAY_PUSH = 30;
  public static final int DONGRUAN = 55;
  public static final int DOWNLOAD = 6;
  public static final int FRONTPAGE_BIG = 21;
  public static final int FRONTPAGE_BILLBOARD = 24;
  public static final int FRONTPAGE_MORE = 33;
  public static final int FRONTPAGE_SMALL = 22;
  public static final int FRONTPAGE_TOPIC = 23;
  public static final int GLOBAL_PUSH = 29;
  public static final int H5_BANNER = 46;
  public static final int H5_CATEGORY_BIG = 47;
  public static final int H5_CATEGORY_SMALL = 48;
  public static final int H5_GEZI_MORE = 53;
  public static final int H5_LIST = 49;
  public static final int H5_LIST_MORE = 54;
  public static final int H5_PODCASTER = 52;
  public static final int H5_RANK = 50;
  public static final int H5_SPECIAL_TOPIIC = 51;
  public static final int LAST_PLAYED = 13;
  public static final int LIVE_CHANNEL_CATEGORY = 38;
  public static final int LIVE_CHANNEL_PUSH = 39;
  public static final int NOVEL_PUSH = 31;
  public static final int OTHER = 0;
  public static final int PLAYHISTORY = 7;
  public static final int PODCASTER = 44;
  public static final String PlayLog = "playlogv6";
  public static final int RANK = 45;
  public static final int RECOMMEND_LIVE_CHANNEL = 37;
  public static final int RESERVE = 8;
  public static final int SEARCH = 5;
  public static final int SPECIAL_TOPIC = 41;
  public static final int SYSTEM_RADIO = 9;
  public static final int USER_FILTER = 35;
  private static PlayLog instance;
  private int SEND_INTERVAL = 2;
  private int mCategoryId = 0;
  private int mChannelId = 0;
  private int mChannelType = 0;
  private String mCity;
  private List<Integer> mLstABtestCatid = new ArrayList();
  private List<Integer> mLstABtestNum = new ArrayList();
  private List<Integer> mLstABtestOption = new ArrayList();
  private List<ProgramABTestBean> mLstProgramABTest = new ArrayList();
  private int mProgramId = 0;
  private int mProgramType = 1;
  private String mRegion;
  private long mSendPlayLogTime = 0L;
  private int mUniqueId = 0;
  private int playSource = 0;

  public static PlayLog getInstance()
  {
    if (instance == null)
      instance = new PlayLog();
    return instance;
  }

  private int hasCategoryABtest(int paramInt)
  {
    int i = 0;
    while (i < this.mLstABtestCatid.size())
    {
      if (((Integer)this.mLstABtestCatid.get(i)).intValue() == paramInt)
        return i;
      i += 1;
    }
    return -1;
  }

  private int hasProgramABTest(int paramInt1, int paramInt2)
  {
    int j;
    if (this.mChannelType == 1)
    {
      j = -1;
      return j;
    }
    int i = 0;
    while (true)
    {
      if (i >= this.mLstProgramABTest.size())
        break label79;
      if (((ProgramABTestBean)this.mLstProgramABTest.get(i)).channelId == paramInt1)
      {
        j = i;
        if (((ProgramABTestBean)this.mLstProgramABTest.get(i)).uniqueId == paramInt2)
          break;
      }
      i += 1;
    }
    label79: return -1;
  }

  private static void log(String paramString)
  {
  }

  public void addABTestCategory(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mLstABtestCatid.add(Integer.valueOf(paramInt1));
    this.mLstABtestNum.add(Integer.valueOf(paramInt2));
    this.mLstABtestOption.add(Integer.valueOf(paramInt3));
  }

  public void addABTestProgram(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    ProgramABTestBean localProgramABTestBean = new ProgramABTestBean();
    localProgramABTestBean.channelId = paramInt1;
    localProgramABTestBean.uniqueId = paramInt2;
    localProgramABTestBean.abtestNum = paramInt3;
    localProgramABTestBean.abtestOption = paramInt4;
    this.mLstProgramABTest.add(localProgramABTestBean);
  }

  public void addCommnPlayLog(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    this.mCategoryId = paramInt1;
    this.mChannelId = paramInt2;
    this.mProgramType = paramInt3;
    this.mProgramId = paramInt4;
    this.mUniqueId = paramInt5;
    this.mChannelType = paramInt6;
  }

  public void addSource(int paramInt)
  {
    this.playSource = paramInt;
    log("addSource: " + this.playSource);
  }

  public int getSource()
  {
    return this.playSource;
  }

  public boolean hasValidPlayInfo()
  {
    return (this.mCategoryId != 0) && (this.mChannelId != 0);
  }

  public void sendPlayLog(Context paramContext, int paramInt)
  {
    if ((paramContext == null) || (this.mCategoryId == 0) || (this.mChannelId == 0))
      break label18;
    label18: 
    while (System.currentTimeMillis() / 1000L - this.mSendPlayLogTime < this.SEND_INTERVAL)
      return;
    this.mSendPlayLogTime = (System.currentTimeMillis() / 1000L);
    QTLogger.getInstance().setContext(paramContext);
    int i = hasProgramABTest(this.mChannelId, this.mUniqueId);
    if ((i != -1) && (i < this.mLstProgramABTest.size()))
    {
      paramContext = ((ProgramABTestBean)this.mLstProgramABTest.get(i)).abtestNum + "-" + ((ProgramABTestBean)this.mLstProgramABTest.get(i)).abtestOption;
      paramContext = QTLogger.getInstance().buildCommonLog(paramContext, null, null);
    }
    while (true)
    {
      Object localObject = paramContext;
      if (paramContext == null)
        localObject = QTLogger.getInstance().buildCommonLog();
      if (localObject == null)
        break;
      paramContext = (String)localObject + "\"" + this.mCategoryId + "\"";
      paramContext = paramContext + ",\"" + this.mChannelId + "\"";
      paramContext = paramContext + ",\"" + this.mProgramId + "\"";
      if (this.mChannelType == 0);
      for (paramContext = paramContext + ",\"" + this.mUniqueId + "\""; ; paramContext = paramContext + ",\"\"")
      {
        paramContext = paramContext + ",\"" + paramInt + "\"";
        paramContext = paramContext + ",\"" + this.playSource + "\"";
        paramContext = paramContext + ",\"" + this.mProgramType + "\"";
        LogModule.getInstance().send("playlogv6", paramContext);
        return;
        i = hasCategoryABtest(this.mCategoryId);
        if ((i == -1) || (i >= this.mLstABtestNum.size()))
          break label538;
        paramContext = this.mLstABtestNum.get(i) + "-" + this.mLstABtestOption.get(i);
        paramContext = QTLogger.getInstance().buildCommonLog(paramContext, null, null);
        break;
      }
      label538: paramContext = null;
    }
  }

  public void setLocation(String paramString1, String paramString2)
  {
    this.mCity = paramString1;
    this.mRegion = paramString2;
    QTLogger.getInstance().setCity(paramString1);
    QTLogger.getInstance().setRegion(paramString2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.log.PlayLog
 * JD-Core Version:    0.6.2
 */