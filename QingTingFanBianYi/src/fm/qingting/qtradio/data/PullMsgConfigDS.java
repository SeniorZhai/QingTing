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
import fm.qingting.qtradio.model.Node;
import java.util.List;
import java.util.Map;

public class PullMsgConfigDS
  implements IDataSource
{
  private static PullMsgConfigDS instance;

  // ERROR //
  private List<Node> acquirePullConfig(DataCommand paramDataCommand)
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
    //   42: ifeq +150 -> 192
    //   45: aload 4
    //   47: aload 4
    //   49: ldc 51
    //   51: invokeinterface 55 2 0
    //   56: invokeinterface 59 2 0
    //   61: astore_2
    //   62: aload 4
    //   64: aload 4
    //   66: ldc 61
    //   68: invokeinterface 55 2 0
    //   73: invokeinterface 59 2 0
    //   78: astore 6
    //   80: aload_2
    //   81: ldc 63
    //   83: invokevirtual 69	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   86: ifeq +42 -> 128
    //   89: aload 5
    //   91: aload 6
    //   93: ldc 71
    //   95: invokevirtual 75	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   98: checkcast 77	fm/qingting/qtradio/model/Node
    //   101: astore_2
    //   102: aload_2
    //   103: astore_1
    //   104: aload_1
    //   105: astore_2
    //   106: aload_2
    //   107: astore_1
    //   108: aload_2
    //   109: ifnull -74 -> 35
    //   112: aload_3
    //   113: aload_2
    //   114: invokeinterface 83 2 0
    //   119: pop
    //   120: aload_2
    //   121: astore_1
    //   122: goto -87 -> 35
    //   125: astore_1
    //   126: aload_3
    //   127: areturn
    //   128: aload_2
    //   129: ldc 85
    //   131: invokevirtual 69	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   134: ifeq +21 -> 155
    //   137: aload 5
    //   139: aload 6
    //   141: ldc 87
    //   143: invokevirtual 75	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   146: checkcast 77	fm/qingting/qtradio/model/Node
    //   149: astore_2
    //   150: aload_2
    //   151: astore_1
    //   152: goto -48 -> 104
    //   155: aload_2
    //   156: ldc 89
    //   158: invokevirtual 69	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   161: ifeq +43 -> 204
    //   164: aload 5
    //   166: aload 6
    //   168: ldc 91
    //   170: invokevirtual 75	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   173: checkcast 77	fm/qingting/qtradio/model/Node
    //   176: astore_2
    //   177: aload_2
    //   178: astore_1
    //   179: goto -75 -> 104
    //   182: astore_2
    //   183: aload_2
    //   184: invokevirtual 94	java/lang/Exception:printStackTrace	()V
    //   187: aload_1
    //   188: astore_2
    //   189: goto -83 -> 106
    //   192: aload 4
    //   194: invokeinterface 97 1 0
    //   199: aload_3
    //   200: areturn
    //   201: astore_1
    //   202: aconst_null
    //   203: areturn
    //   204: goto -100 -> 104
    //
    // Exception table:
    //   from	to	target	type
    //   8	33	125	java/lang/Exception
    //   35	80	125	java/lang/Exception
    //   112	120	125	java/lang/Exception
    //   183	187	125	java/lang/Exception
    //   192	199	125	java/lang/Exception
    //   80	102	182	java/lang/Exception
    //   128	150	182	java/lang/Exception
    //   155	177	182	java/lang/Exception
    //   0	8	201	java/lang/Exception
  }

  private boolean deletePullConfig(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("pullMsgConfig").execSQL("delete from pullMsgConfig");
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
    localDataToken.setData(new Result(true, acquirePullConfig(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deletePullConfig(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertPullConfig(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updatePullConfig(paramDataCommand))));
    return localDataToken;
  }

  public static PullMsgConfigDS getInstance()
  {
    if (instance == null)
      instance = new PullMsgConfigDS();
    return instance;
  }

  private boolean insertPullConfig(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("pullMsgConfig");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        Node localNode = (Node)paramDataCommand.get(i);
        String str = localGson.toJson(localNode);
        localSQLiteDatabase.execSQL("insert into pullMsgConfig(id,nodeName,pullNode) values(?,?,?)", new Object[] { String.valueOf(i), localNode.nodeName, str });
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

  private boolean updatePullConfig(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if (paramDataCommand == null)
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("pullMsgConfig");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from pullMsgConfig");
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        Node localNode = (Node)paramDataCommand.get(i);
        String str = localGson.toJson(localNode);
        localSQLiteDatabase.execSQL("insert into pullMsgConfig(id,nodeName,pullNode) values(?,?,?)", new Object[] { String.valueOf(i), localNode.nodeName, str });
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
    return "PullMsgConfigDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_pull_config"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_pull_config"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_pull_config"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_pull_config"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PullMsgConfigDS
 * JD-Core Version:    0.6.2
 */