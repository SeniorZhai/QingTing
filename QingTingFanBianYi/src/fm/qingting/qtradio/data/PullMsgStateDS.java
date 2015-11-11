package fm.qingting.qtradio.data;

import android.database.sqlite.SQLiteDatabase;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import java.util.List;
import java.util.Map;

public class PullMsgStateDS
  implements IDataSource
{
  private static PullMsgStateDS instance;

  // ERROR //
  private List<String> acquirePullMsgState(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   4: pop
    //   5: new 25	java/util/ArrayList
    //   8: dup
    //   9: invokespecial 26	java/util/ArrayList:<init>	()V
    //   12: astore_1
    //   13: invokestatic 32	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   16: ldc 34
    //   18: invokevirtual 38	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   21: ldc 40
    //   23: aconst_null
    //   24: invokevirtual 46	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   27: astore_2
    //   28: aload_2
    //   29: invokeinterface 52 1 0
    //   34: ifeq +33 -> 67
    //   37: aload_2
    //   38: aload_2
    //   39: ldc 54
    //   41: invokeinterface 58 2 0
    //   46: invokeinterface 62 2 0
    //   51: astore_3
    //   52: aload_3
    //   53: ifnull -25 -> 28
    //   56: aload_1
    //   57: aload_3
    //   58: invokeinterface 68 2 0
    //   63: pop
    //   64: goto -36 -> 28
    //   67: aload_2
    //   68: invokeinterface 71 1 0
    //   73: aload_1
    //   74: areturn
    //   75: astore_1
    //   76: aconst_null
    //   77: areturn
    //   78: astore_2
    //   79: aload_1
    //   80: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   5	13	75	java/lang/Exception
    //   13	28	78	java/lang/Exception
    //   28	52	78	java/lang/Exception
    //   56	64	78	java/lang/Exception
    //   67	73	78	java/lang/Exception
  }

  private boolean deletePullMsgState(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("pullMsgState").execSQL("delete from pullMsgState");
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
    localDataToken.setData(new Result(true, acquirePullMsgState(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deletePullMsgState(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertPullMsgState(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updatePullMsgState(paramDataCommand))));
    return localDataToken;
  }

  public static PullMsgStateDS getInstance()
  {
    if (instance == null)
      instance = new PullMsgStateDS();
    return instance;
  }

  private boolean insertPullMsgState(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("id");
    if (paramDataCommand == null)
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("pullMsgState");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("insert into pullMsgState(id,state) values(?,?)", new Object[] { paramDataCommand, Integer.valueOf(1) });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updatePullMsgState(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("ids");
    if (paramDataCommand == null)
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("pullMsgState");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from pullMsgState");
      int i = 0;
      while (i < paramDataCommand.size())
      {
        localSQLiteDatabase.execSQL("insert into pullMsgState(id,state) values(?,?)", new Object[] { (String)paramDataCommand.get(i), Integer.valueOf(1) });
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
    return "PullMsgStateDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_pullmsgstate"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_pullmsgstate"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_pullmsgstate"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_pullmsgstate"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PullMsgStateDS
 * JD-Core Version:    0.6.2
 */