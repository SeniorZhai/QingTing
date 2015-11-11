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
import fm.qingting.qtradio.model.PlayedMetaData;
import java.util.Map;

public class PlayedMetaDS
  implements IDataSource
{
  private static PlayedMetaDS instance;

  // ERROR //
  private java.util.List<PlayedMetaData> acquirePlayedMeta(DataCommand paramDataCommand)
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
    //   33: aconst_null
    //   34: astore_1
    //   35: aload 4
    //   37: invokeinterface 49 1 0
    //   42: ifeq +57 -> 99
    //   45: aload 4
    //   47: aload 4
    //   49: ldc 51
    //   51: invokeinterface 55 2 0
    //   56: invokeinterface 59 2 0
    //   61: astore_2
    //   62: aload 5
    //   64: aload_2
    //   65: ldc 61
    //   67: invokevirtual 65	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   70: checkcast 61	fm/qingting/qtradio/model/PlayedMetaData
    //   73: astore_2
    //   74: aload_2
    //   75: astore_1
    //   76: aload_1
    //   77: ifnull +37 -> 114
    //   80: aload_3
    //   81: aload_1
    //   82: invokeinterface 71 2 0
    //   87: pop
    //   88: goto +26 -> 114
    //   91: astore_2
    //   92: aload_2
    //   93: invokevirtual 74	java/lang/Exception:printStackTrace	()V
    //   96: goto -20 -> 76
    //   99: aload 4
    //   101: invokeinterface 77 1 0
    //   106: aload_3
    //   107: areturn
    //   108: astore_1
    //   109: aconst_null
    //   110: areturn
    //   111: astore_1
    //   112: aload_3
    //   113: areturn
    //   114: goto -79 -> 35
    //
    // Exception table:
    //   from	to	target	type
    //   62	74	91	java/lang/Exception
    //   0	8	108	java/lang/Exception
    //   8	33	111	java/lang/Exception
    //   35	62	111	java/lang/Exception
    //   80	88	111	java/lang/Exception
    //   92	96	111	java/lang/Exception
    //   99	106	111	java/lang/Exception
  }

  private boolean deletePlayedMeta(DataCommand paramDataCommand)
  {
    paramDataCommand = (PlayedMetaData)paramDataCommand.getParam().get("playedMeta");
    if (paramDataCommand == null)
      return false;
    try
    {
      paramDataCommand = "delete from playedMetaData" + " where id = '" + paramDataCommand.uniqueId + "'";
      DBManager.getInstance().getWritableDB("playedMeta").execSQL(paramDataCommand);
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
    localDataToken.setData(new Result(true, acquirePlayedMeta(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deletePlayedMeta(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertPlayedMeta(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updatePlayedMeta(paramDataCommand))));
    return localDataToken;
  }

  public static PlayedMetaDS getInstance()
  {
    if (instance == null)
      instance = new PlayedMetaDS();
    return instance;
  }

  private boolean insertPlayedMeta(DataCommand paramDataCommand)
  {
    paramDataCommand = (PlayedMetaData)paramDataCommand.getParam().get("playedMeta");
    if (paramDataCommand == null)
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playedMeta");
      localSQLiteDatabase.beginTransaction();
      String str = new Gson().toJson(paramDataCommand);
      localSQLiteDatabase.execSQL("insert into playedMetaData(id,playedMetaData) values(?,?)", new Object[] { Integer.valueOf(paramDataCommand.uniqueId), str });
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updatePlayedMeta(DataCommand paramDataCommand)
  {
    paramDataCommand = (PlayedMetaData)paramDataCommand.getParam().get("playedMeta");
    if (paramDataCommand == null)
      return false;
    try
    {
      String str = "delete from playedMetaData" + " where id = '" + paramDataCommand.uniqueId + "'";
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playedMeta");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL(str);
      str = new Gson().toJson(paramDataCommand);
      localSQLiteDatabase.execSQL("insert into playedMetaData(id,playedMetaData) values(?,?)", new Object[] { Integer.valueOf(paramDataCommand.uniqueId), str });
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
    return "PlayedMetaDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_playedmeta"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_playedmeta"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_playedmeta"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_playedmeta"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PlayedMetaDS
 * JD-Core Version:    0.6.2
 */