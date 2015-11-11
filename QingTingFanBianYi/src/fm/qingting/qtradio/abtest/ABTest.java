package fm.qingting.qtradio.abtest;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.H5Bean;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.ProgramABTestBean;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.utils.LifeTime;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.RangeRandom;

public class ABTest
{
  private static final String ExternalSeperator = ";";
  private static final String InternalSeperator = "-";
  private static final String KEY_ABTEST_H5 = "KEY_ABTEST_H5";
  private static final String KEY_ABTEST_PROGRAM = "KEY_ABTEST_PROGRAM";
  private static final String KEY_ABTEST_USER = "KEY_ABTEST_USER";
  private static final String LogType = "AppAlive";
  private static ABTest _ins;
  private Context _context;
  private boolean hassendH5ABTest = false;
  private String keys_abtest_h5 = null;
  private String keys_abtest_program = null;
  private boolean shouldSelect = false;

  public static String buildCommonLogWithABTest(Context paramContext, boolean paramBoolean)
  {
    String str = buildUserTypeString(paramContext, ABTestConfig.items);
    QTLogger.getInstance().setContext(paramContext);
    QTLogger localQTLogger = QTLogger.getInstance();
    if (LifeTime.isFirstLaunchAfterInstall);
    for (paramContext = "1"; ; paramContext = "0")
    {
      str = localQTLogger.buildCommonLog(str, null, paramContext).trim();
      paramContext = str;
      if (!paramBoolean)
      {
        paramContext = str;
        if (str.charAt(str.length() - 1) == ',')
          paramContext = str.substring(0, str.length() - 1);
      }
      return paramContext;
    }
  }

  public static String buildUserTypeString(Context paramContext, ABTestItem[] paramArrayOfABTestItem)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramArrayOfABTestItem.length)
    {
      ABTestItem localABTestItem = paramArrayOfABTestItem[i];
      String str = GlobalCfg.getInstance(paramContext).getValueFromDB(localABTestItem.OptionName);
      if ((str != null) && (str.trim().length() > 0))
      {
        if (localStringBuffer.length() > 0)
          localStringBuffer.append(";");
        localStringBuffer.append(localABTestItem.number + "-" + str);
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }

  private void fetchCoverage()
  {
    String str = MobclickAgent.getConfigParams(this._context, "ABTestCoverage");
    if (str != null);
    try
    {
      ABTestConfig.coverage = Double.parseDouble(str);
      log("coverage:" + ABTestConfig.coverage);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static String getCommonLogWithPushType(Context paramContext, ABTestItem[] paramArrayOfABTestItem)
  {
    paramArrayOfABTestItem = buildUserTypeString(paramContext, paramArrayOfABTestItem);
    String str = buildUserTypeString(paramContext, ABTestConfig.items);
    QTLogger.getInstance().setContext(paramContext);
    QTLogger localQTLogger = QTLogger.getInstance();
    if (LifeTime.isFirstLaunchAfterInstall);
    for (paramContext = "1"; ; paramContext = "0")
      return localQTLogger.buildCommonLog(str, paramArrayOfABTestItem, paramContext);
  }

  public static ABTest getInstance()
  {
    if (_ins == null)
      _ins = new ABTest();
    return _ins;
  }

  private void init(Context paramContext)
  {
    this._context = paramContext;
    if (LifeTime.isFirstLaunchAfterInstall)
      fetchCoverage();
    this.keys_abtest_h5 = SharedCfg.getInstance().getValue("KEY_ABTEST_H5");
    this.keys_abtest_program = SharedCfg.getInstance().getValue("KEY_ABTEST_PROGRAM");
  }

  private boolean isOptionEnabled(String paramString)
  {
    String str = MobclickAgent.getConfigParams(this._context, paramString);
    if ((str != null) && (str.equalsIgnoreCase("1")))
    {
      log(paramString + " is enabled");
      return true;
    }
    if (str == null)
    {
      paramString = paramString + " null";
      QTMSGManage.getInstance().sendStatistcsMessage("UmengParameterFail", paramString);
    }
    while (true)
    {
      log(paramString);
      return false;
      paramString = paramString + ":" + str;
      QTMSGManage.getInstance().sendStatistcsMessage("UmengParameterFail", paramString);
    }
  }

  private static void log(String paramString)
  {
  }

  private void recordOption(String paramString1, String paramString2)
  {
    log("[recordOption]" + paramString1 + ":" + paramString2);
    GlobalCfg.getInstance(this._context).setValueToDB(paramString1, "String", paramString2);
  }

  private String selectOption(String paramString1, String paramString2)
  {
    if (RangeRandom.randomSwitch())
      return paramString1;
    return paramString2;
  }

  private static void sendABTestLogIfChosen(Context paramContext, ABTestItem[] paramArrayOfABTestItem)
  {
    paramArrayOfABTestItem = buildUserTypeString(paramContext, paramArrayOfABTestItem);
    if ((paramArrayOfABTestItem != null) && (paramArrayOfABTestItem.trim().length() > 0))
    {
      QTLogger localQTLogger = QTLogger.getInstance();
      if (LifeTime.isFirstLaunchAfterInstall);
      for (paramContext = "1"; ; paramContext = "0")
      {
        paramArrayOfABTestItem = localQTLogger.buildCommonLog(paramArrayOfABTestItem, null, paramContext).trim();
        paramContext = paramArrayOfABTestItem;
        if (paramArrayOfABTestItem.charAt(paramArrayOfABTestItem.length() - 1) == ',')
          paramContext = paramArrayOfABTestItem.substring(0, paramArrayOfABTestItem.length() - 1);
        paramContext = paramContext + "\n";
        LogModule.getInstance().send("AppAlive", paramContext);
        return;
      }
    }
    log("user type string is empty. don't send log");
  }

  private boolean shouldSelect()
  {
    return RangeRandom.random(ABTestConfig.coverage);
  }

  public void addH5ABTest(H5Bean paramH5Bean)
  {
    if (!InfoManager.getInstance().enableH5());
    String str;
    label202: label216: 
    do
    {
      do
      {
        return;
        if ((!this.shouldSelect) || (paramH5Bean == null) || (paramH5Bean.abtestNum <= 0))
          break;
        if (this.keys_abtest_h5 == null)
          break label202;
      }
      while (this.keys_abtest_h5.contains(String.valueOf(paramH5Bean.abtestNum)));
      this.keys_abtest_h5 += "_";
      for (this.keys_abtest_h5 += paramH5Bean.abtestNum; ; this.keys_abtest_h5 = String.valueOf(paramH5Bean.abtestNum))
      {
        SharedCfg.getInstance().saveValue("KEY_ABTEST_H5", this.keys_abtest_h5);
        SharedCfg.getInstance().saveValue(String.valueOf(paramH5Bean.abtestNum), selectOption("0", "1"));
        if ((paramH5Bean == null) || (paramH5Bean.abtestNum <= 0) || (paramH5Bean.type != 2))
          break;
        str = SharedCfg.getInstance().getValue(String.valueOf(paramH5Bean.abtestNum));
        if (str == null)
          break;
        if (!str.equalsIgnoreCase("1"))
          break label216;
        PlayerAgent.getInstance().addABTestCategory(paramH5Bean.id, paramH5Bean.abtestNum, 1);
        return;
      }
    }
    while (!str.equalsIgnoreCase("0"));
    PlayerAgent.getInstance().addABTestCategory(paramH5Bean.id, paramH5Bean.abtestNum, 0);
  }

  public void addProgramABTest(ProgramABTestBean paramProgramABTestBean)
  {
    if (!InfoManager.getInstance().enableProgramABTest());
    String str;
    label199: label244: label246: 
    do
      while (true)
      {
        return;
        if ((paramProgramABTestBean != null) && (paramProgramABTestBean.abtestNum > 0))
        {
          if (this.keys_abtest_program == null)
            break label199;
          if (!this.keys_abtest_program.contains(String.valueOf(paramProgramABTestBean.abtestNum)))
          {
            this.keys_abtest_program += "_";
            this.keys_abtest_program += paramProgramABTestBean.abtestNum;
            SharedCfg.getInstance().saveValue("KEY_ABTEST_PROGRAM", this.keys_abtest_program);
            SharedCfg.getInstance().saveValue(String.valueOf(paramProgramABTestBean.abtestNum), selectOption("0", "1"));
          }
        }
        while (true)
        {
          if ((paramProgramABTestBean == null) || (paramProgramABTestBean.abtestNum <= 0) || (paramProgramABTestBean.type != 5))
            break label244;
          str = SharedCfg.getInstance().getValue(String.valueOf(paramProgramABTestBean.abtestNum));
          if (str == null)
            break;
          if (!str.equalsIgnoreCase("1"))
            break label246;
          PlayerAgent.getInstance().addABTestProgram(paramProgramABTestBean.channelId, paramProgramABTestBean.uniqueId, paramProgramABTestBean.abtestNum, 1);
          return;
          this.keys_abtest_program = String.valueOf(paramProgramABTestBean.abtestNum);
          SharedCfg.getInstance().saveValue("KEY_ABTEST_PROGRAM", this.keys_abtest_program);
          SharedCfg.getInstance().saveValue(String.valueOf(paramProgramABTestBean.abtestNum), selectOption("0", "1"));
        }
      }
    while (!str.equalsIgnoreCase("0"));
    PlayerAgent.getInstance().addABTestProgram(paramProgramABTestBean.channelId, paramProgramABTestBean.uniqueId, paramProgramABTestBean.abtestNum, 0);
  }

  public String getOption(String paramString)
  {
    String str = GlobalCfg.getInstance(this._context).getValueFromDB(paramString);
    log("[getOption]" + paramString + ":" + str);
    return str;
  }

  public boolean manualSetOption(ABTestItem paramABTestItem, String paramString)
  {
    if (isOptionEnabled(paramABTestItem.OptionName))
    {
      recordOption(paramABTestItem.OptionName, paramString);
      return true;
    }
    return false;
  }

  public void sendH5ABTest()
  {
    if (!InfoManager.getInstance().enableH5());
    Object localObject2;
    label161: 
    do
    {
      do
      {
        do
          return;
        while ((this.keys_abtest_h5 == null) || (this.keys_abtest_h5.equalsIgnoreCase("")) || (this.hassendH5ABTest));
        localObject3 = this.keys_abtest_h5.split("_");
      }
      while (localObject3 == null);
      int i = 0;
      localObject2 = null;
      if (i < localObject3.length)
      {
        String str1 = localObject3[i];
        localObject1 = localObject2;
        String str2;
        if (str1 != null)
        {
          localObject1 = localObject2;
          if (!str1.equalsIgnoreCase(""))
          {
            str2 = SharedCfg.getInstance().getValue(str1);
            localObject1 = localObject2;
            if (str2 != null)
            {
              localObject1 = localObject2;
              if (!str2.equalsIgnoreCase(""))
                if (localObject2 != null)
                  break label161;
            }
          }
        }
        for (localObject1 = str1 + "-" + str2; ; localObject1 = (String)localObject1 + str1 + "-" + str2)
        {
          i += 1;
          localObject2 = localObject1;
          break;
          localObject1 = (String)localObject2 + ";";
        }
      }
    }
    while (localObject2 == null);
    Object localObject3 = QTLogger.getInstance();
    if (LifeTime.isFirstLaunchAfterInstall);
    for (Object localObject1 = "1"; ; localObject1 = "0")
    {
      localObject2 = ((QTLogger)localObject3).buildCommonLog((String)localObject2, null, (String)localObject1).trim();
      localObject1 = localObject2;
      if (((String)localObject2).charAt(((String)localObject2).length() - 1) == ',')
        localObject1 = ((String)localObject2).substring(0, ((String)localObject2).length() - 1);
      localObject1 = (String)localObject1 + "\n";
      LogModule.getInstance().send("AppAlive", (String)localObject1);
      this.hassendH5ABTest = true;
      return;
    }
  }

  public void startABTest(Context paramContext)
  {
    init(paramContext);
    if (LifeTime.isFirstLaunchAfterInstall)
    {
      log("is First launch after install");
      if (shouldSelect())
      {
        this.shouldSelect = true;
        log("select this user.");
        int i = 0;
        if (i < ABTestConfig.items.length)
        {
          paramContext = ABTestConfig.items[i];
          if (paramContext.generateMethod != ABTestItem.GenerateMethod.Auto);
          while (true)
          {
            i += 1;
            break;
            if (isOptionEnabled(paramContext.OptionName))
              recordOption(paramContext.OptionName, selectOption(paramContext.OptionA, paramContext.OptionB));
          }
        }
        GlobalCfg.getInstance(this._context).saveValueToDB();
        sendABTestLogIfChosen(this._context, ABTestConfig.items);
        return;
      }
      log("don't select this user.");
      return;
    }
    log("Not First launch after install");
    sendABTestLogIfChosen(this._context, ABTestConfig.items);
  }

  public void startABTestForUser()
  {
    int i;
    int j;
    if (LifeTime.isFirstLaunchAfterInstall)
    {
      i = SharedCfg.getInstance().getChooseGender();
      j = SharedCfg.getInstance().getChooseUser();
      if ((i != 0) && (j != 0));
    }
    String str;
    do
    {
      return;
      localObject = "21-" + String.valueOf(i) + String.valueOf(j);
      SharedCfg.getInstance().saveValue("KEY_ABTEST_USER", (String)localObject);
      str = SharedCfg.getInstance().getValue("KEY_ABTEST_USER");
    }
    while ((str == null) || (str.equalsIgnoreCase("")));
    QTLogger localQTLogger = QTLogger.getInstance();
    if (LifeTime.isFirstLaunchAfterInstall);
    for (Object localObject = "1"; ; localObject = "0")
    {
      str = localQTLogger.buildCommonLog(str, null, (String)localObject).trim();
      localObject = str;
      if (str.charAt(str.length() - 1) == ',')
        localObject = str.substring(0, str.length() - 1);
      localObject = (String)localObject + "\n";
      LogModule.getInstance().send("AppAlive", (String)localObject);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.abtest.ABTest
 * JD-Core Version:    0.6.2
 */