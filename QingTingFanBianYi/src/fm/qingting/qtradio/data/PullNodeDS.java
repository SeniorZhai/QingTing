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

public class PullNodeDS
  implements IDataSource
{
  private static PullNodeDS instance;

  // ERROR //
  private List<Node> acquirePulllist(DataCommand paramDataCommand)
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
    //   42: ifeq +146 -> 188
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
    //   73: invokeinterface 65 2 0
    //   78: istore 6
    //   80: iload 6
    //   82: ifne +41 -> 123
    //   85: aload 5
    //   87: aload_2
    //   88: ldc 67
    //   90: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   93: checkcast 67	fm/qingting/qtradio/model/ProgramNode
    //   96: astore_2
    //   97: aload_2
    //   98: astore_1
    //   99: aload_1
    //   100: astore_2
    //   101: aload_2
    //   102: astore_1
    //   103: aload_2
    //   104: ifnull -69 -> 35
    //   107: aload_3
    //   108: aload_2
    //   109: invokeinterface 77 2 0
    //   114: pop
    //   115: aload_2
    //   116: astore_1
    //   117: goto -82 -> 35
    //   120: astore_1
    //   121: aload_3
    //   122: areturn
    //   123: iload 6
    //   125: iconst_1
    //   126: if_icmpne +6 -> 132
    //   129: goto -30 -> 99
    //   132: iload 6
    //   134: iconst_2
    //   135: if_icmpne +20 -> 155
    //   138: aload 5
    //   140: aload_2
    //   141: ldc 79
    //   143: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   146: checkcast 79	fm/qingting/qtradio/model/ChannelNode
    //   149: astore_2
    //   150: aload_2
    //   151: astore_1
    //   152: goto -53 -> 99
    //   155: iload 6
    //   157: iconst_3
    //   158: if_icmpne +42 -> 200
    //   161: aload 5
    //   163: aload_2
    //   164: ldc 81
    //   166: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   169: checkcast 81	fm/qingting/qtradio/model/RadioChannelNode
    //   172: astore_2
    //   173: aload_2
    //   174: astore_1
    //   175: goto -76 -> 99
    //   178: astore_2
    //   179: aload_2
    //   180: invokevirtual 84	java/lang/Exception:printStackTrace	()V
    //   183: aload_1
    //   184: astore_2
    //   185: goto -84 -> 101
    //   188: aload 4
    //   190: invokeinterface 87 1 0
    //   195: aload_3
    //   196: areturn
    //   197: astore_1
    //   198: aconst_null
    //   199: areturn
    //   200: goto -101 -> 99
    //
    // Exception table:
    //   from	to	target	type
    //   8	33	120	java/lang/Exception
    //   35	80	120	java/lang/Exception
    //   107	115	120	java/lang/Exception
    //   179	183	120	java/lang/Exception
    //   188	195	120	java/lang/Exception
    //   85	97	178	java/lang/Exception
    //   138	150	178	java/lang/Exception
    //   161	173	178	java/lang/Exception
    //   0	8	197	java/lang/Exception
  }

  private boolean delPulllist(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("pullList");
      paramDataCommand.beginTransaction();
      paramDataCommand.execSQL("delete from pullList");
      paramDataCommand.setTransactionSuccessful();
      paramDataCommand.endTransaction();
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
    localDataToken.setData(new Result(true, acquirePulllist(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(delPulllist(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updatePulllist(paramDataCommand))));
    return localDataToken;
  }

  public static PullNodeDS getInstance()
  {
    if (instance == null)
      instance = new PullNodeDS();
    return instance;
  }

  private boolean updatePulllist(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    while (true)
    {
      int j;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("pullList");
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL("delete from pullList");
        Gson localGson = new Gson();
        j = 0;
        if (j < paramDataCommand.size())
        {
          Node localNode = (Node)paramDataCommand.get(j);
          int i;
          if (localNode.nodeName.equalsIgnoreCase("program"))
          {
            i = 0;
            localSQLiteDatabase.execSQL("insert into pullList(id,type,node) values(?,?,?)", new Object[] { Integer.valueOf(j), Integer.valueOf(i), localGson.toJson(localNode) });
          }
          else
          {
            if (localNode.nodeName.equalsIgnoreCase("ondemandprogram"))
            {
              i = 1;
              continue;
            }
            if (localNode.nodeName.equalsIgnoreCase("channel"))
            {
              i = 2;
              continue;
            }
            if (localNode.nodeName.equalsIgnoreCase("radiochannel"))
            {
              i = 3;
              continue;
            }
          }
        }
        else
        {
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return true;
        }
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      j += 1;
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "PullNodeDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_pull_node"))
      return doUpdateCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_pull_node"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_pull_node"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PullNodeDS
 * JD-Core Version:    0.6.2
 */