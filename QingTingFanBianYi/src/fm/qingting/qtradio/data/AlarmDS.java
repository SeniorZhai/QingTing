package fm.qingting.qtradio.data;

import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.AlarmInfo;
import java.util.List;
import java.util.Map;

public class AlarmDS
  implements IDataSource
{
  private static AlarmDS instance;

  // ERROR //
  private List<AlarmInfo> acquireAlarmInfos(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: new 19	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 20	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 26	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 28
    //   13: invokevirtual 32	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc 34
    //   18: aconst_null
    //   19: invokevirtual 40	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   22: astore 4
    //   24: new 42	com/google/gson/Gson
    //   27: dup
    //   28: invokespecial 43	com/google/gson/Gson:<init>	()V
    //   31: astore 5
    //   33: aload 4
    //   35: invokeinterface 49 1 0
    //   40: ifeq +106 -> 146
    //   43: aload 4
    //   45: aload 4
    //   47: ldc 51
    //   49: invokeinterface 55 2 0
    //   54: invokeinterface 59 2 0
    //   59: astore_1
    //   60: aload 5
    //   62: aload_1
    //   63: ldc 61
    //   65: invokevirtual 65	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   68: checkcast 61	fm/qingting/qtradio/model/AlarmInfo
    //   71: astore_2
    //   72: aconst_null
    //   73: astore_1
    //   74: aload_2
    //   75: ifnull +43 -> 118
    //   78: aload_3
    //   79: aload_2
    //   80: invokeinterface 71 2 0
    //   85: pop
    //   86: goto -53 -> 33
    //   89: astore_1
    //   90: aload_3
    //   91: areturn
    //   92: astore_2
    //   93: aload 5
    //   95: aload_1
    //   96: ldc 73
    //   98: invokevirtual 65	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   101: checkcast 73	fm/qingting/qtradio/model/AlarmInfoLegacy
    //   104: astore_1
    //   105: aconst_null
    //   106: astore_2
    //   107: goto -33 -> 74
    //   110: astore_1
    //   111: aconst_null
    //   112: astore_1
    //   113: aconst_null
    //   114: astore_2
    //   115: goto -41 -> 74
    //   118: aload_1
    //   119: ifnull -86 -> 33
    //   122: new 61	fm/qingting/qtradio/model/AlarmInfo
    //   125: dup
    //   126: invokespecial 74	fm/qingting/qtradio/model/AlarmInfo:<init>	()V
    //   129: astore_2
    //   130: aload_2
    //   131: aload_1
    //   132: invokevirtual 78	fm/qingting/qtradio/model/AlarmInfo:update	(Lfm/qingting/qtradio/model/AlarmInfoLegacy;)V
    //   135: aload_3
    //   136: aload_2
    //   137: invokeinterface 71 2 0
    //   142: pop
    //   143: goto -110 -> 33
    //   146: aload 4
    //   148: invokeinterface 81 1 0
    //   153: aload_3
    //   154: areturn
    //   155: astore_1
    //   156: aconst_null
    //   157: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   8	33	89	java/lang/Exception
    //   33	60	89	java/lang/Exception
    //   78	86	89	java/lang/Exception
    //   122	143	89	java/lang/Exception
    //   146	153	89	java/lang/Exception
    //   60	72	92	java/lang/Exception
    //   93	105	110	java/lang/Exception
    //   0	8	155	java/lang/Exception
  }

  private boolean deleteAlarmInfos(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("alarmInfos").execSQL("delete from alarmInfos");
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private DataToken doAcquireCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireAlarmInfos(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteAlarmInfos(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertAlarmInfos(paramDataCommand))));
    return localDataToken;
  }

  public static AlarmDS getInstance()
  {
    if (instance == null)
      instance = new AlarmDS();
    return instance;
  }

  private boolean insertAlarmInfos(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("alarmInfos");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("alarmInfos");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        localSQLiteDatabase.execSQL("insert into alarmInfos(alarmInfo) values(?)", new Object[] { localGson.toJson((AlarmInfo)paramDataCommand.get(i)) });
        i += 1;
      }
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "AlarmDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_alarm_info"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_alarm_info"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_alarm_info"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.AlarmDS
 * JD-Core Version:    0.6.2
 */