package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build.VERSION;
import android.util.Log;
import com.google.gson.Gson;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ProgramNodesRevDS
  implements IDataSource
{
  static final String TAG = "ProgramNodesRevDS";
  private static ProgramNodesRevDS instance;

  // ERROR //
  private List<Node> acquireProgramNodes(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_1
    //   3: invokevirtual 27	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   6: ldc 29
    //   8: invokeinterface 35 2 0
    //   13: checkcast 37	java/lang/Integer
    //   16: astore_1
    //   17: new 39	java/lang/StringBuilder
    //   20: dup
    //   21: invokespecial 40	java/lang/StringBuilder:<init>	()V
    //   24: ldc 42
    //   26: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: aload_1
    //   30: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   33: ldc 51
    //   35: invokevirtual 46	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: invokevirtual 55	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   41: astore_1
    //   42: new 57	java/util/ArrayList
    //   45: dup
    //   46: invokespecial 58	java/util/ArrayList:<init>	()V
    //   49: astore_3
    //   50: invokestatic 64	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   53: ldc 66
    //   55: invokevirtual 70	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   58: aload_1
    //   59: aconst_null
    //   60: invokevirtual 76	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   63: astore 4
    //   65: new 78	com/google/gson/Gson
    //   68: dup
    //   69: invokespecial 79	com/google/gson/Gson:<init>	()V
    //   72: astore 5
    //   74: aload_2
    //   75: astore_1
    //   76: aload 4
    //   78: invokeinterface 85 1 0
    //   83: ifeq +63 -> 146
    //   86: aload 4
    //   88: aload 4
    //   90: ldc 87
    //   92: invokeinterface 91 2 0
    //   97: invokeinterface 95 2 0
    //   102: astore_2
    //   103: aload 5
    //   105: aload_2
    //   106: ldc 97
    //   108: invokevirtual 101	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   111: checkcast 103	fm/qingting/qtradio/model/Node
    //   114: astore_2
    //   115: aload_1
    //   116: ifnull +17 -> 133
    //   119: aload_2
    //   120: ifnull +13 -> 133
    //   123: aload_2
    //   124: aload_1
    //   125: putfield 107	fm/qingting/qtradio/model/Node:prevSibling	Lfm/qingting/qtradio/model/Node;
    //   128: aload_1
    //   129: aload_2
    //   130: putfield 110	fm/qingting/qtradio/model/Node:nextSibling	Lfm/qingting/qtradio/model/Node;
    //   133: aload_3
    //   134: aload_2
    //   135: invokeinterface 116 2 0
    //   140: pop
    //   141: aload_2
    //   142: astore_1
    //   143: goto -67 -> 76
    //   146: aload 4
    //   148: invokeinterface 119 1 0
    //   153: aload_3
    //   154: areturn
    //   155: astore_1
    //   156: aconst_null
    //   157: areturn
    //   158: astore_1
    //   159: aload_3
    //   160: areturn
    //   161: astore_2
    //   162: goto -19 -> 143
    //   165: astore_1
    //   166: aload_2
    //   167: astore_1
    //   168: goto -25 -> 143
    //
    // Exception table:
    //   from	to	target	type
    //   2	50	155	java/lang/Exception
    //   50	74	158	java/lang/Exception
    //   76	103	158	java/lang/Exception
    //   146	153	158	java/lang/Exception
    //   103	115	161	java/lang/Exception
    //   123	133	161	java/lang/Exception
    //   133	141	165	java/lang/Exception
  }

  private boolean deleteProgramNodes(DataCommand paramDataCommand)
  {
    int i = ((Integer)paramDataCommand.getParam().get("cid")).intValue();
    try
    {
      paramDataCommand = "delete from programNodesRev" + " where cid = '" + i + "'";
      DBManager.getInstance().getWritableDB("programNodesRev").execSQL(paramDataCommand);
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
    localDataToken.setData(new Result(true, acquireProgramNodes(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteProgramNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertProgramNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateProgramNodes(paramDataCommand))));
    return localDataToken;
  }

  public static ProgramNodesRevDS getInstance()
  {
    if (instance == null)
      instance = new ProgramNodesRevDS();
    return instance;
  }

  private boolean insertProgramNodes(DataCommand paramDataCommand)
  {
    int i = 0;
    Object localObject = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject).get("nodes");
    int j = ((Integer)((Map)localObject).get("cid")).intValue();
    int k = ((Integer)((Map)localObject).get("dw")).intValue();
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    localObject = DBManager.getInstance().getWritableDB("programNodesRev");
    try
    {
      ((SQLiteDatabase)localObject).beginTransaction();
      Gson localGson = new Gson();
      while (i < paramDataCommand.size())
      {
        Node localNode = (Node)paramDataCommand.get(i);
        String str = localGson.toJson(localNode);
        ((SQLiteDatabase)localObject).execSQL("insert into programNodesRev(cid,pid,dw,programNode) values(?, ?, ?, ?)", new Object[] { Integer.valueOf(j), Integer.valueOf(((ProgramNode)localNode).uniqueId), Integer.valueOf(k), str });
        i += 1;
      }
      ((SQLiteDatabase)localObject).setTransactionSuccessful();
      ((SQLiteDatabase)localObject).endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
      while (true)
        paramDataCommand.printStackTrace();
    }
  }

  private boolean updateProgramNodes(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject1).get("nodes");
    int j = ((Integer)((Map)localObject1).get("id")).intValue();
    int k = ((Integer)((Map)localObject1).get("size")).intValue();
    localObject1 = DBManager.getInstance().getWritableDB("programNodesRev");
    try
    {
      ((SQLiteDatabase)localObject1).beginTransaction();
      Object localObject3;
      if (Build.VERSION.SDK_INT >= 11)
      {
        long l = DatabaseUtils.queryNumEntries((SQLiteDatabase)localObject1, "programNodesRev");
        Log.d("ProgramNodesRevDS", "sym:数据库缓存节目单数量:" + l);
        if (l > 10000L)
        {
          localObject2 = ((SQLiteDatabase)localObject1).query(true, "programNodesRev", new String[] { "cid" }, null, null, null, null, null, null);
          localObject3 = new ArrayList();
          if (localObject2 != null)
            while (((Cursor)localObject2).moveToNext())
              ((List)localObject3).add(((Cursor)localObject2).getString(0));
          localObject2 = ((List)localObject3).iterator();
          while (((Iterator)localObject2).hasNext())
          {
            localObject3 = (String)((Iterator)localObject2).next();
            if (DatabaseUtils.queryNumEntries((SQLiteDatabase)localObject1, "programNodesRev", "cid=?", new String[] { localObject3 }) < 300L)
            {
              Log.d("ProgramNodesRevDS", String.format("sym:数据库删除id为%s的缓存", new Object[] { localObject3 }));
              ((SQLiteDatabase)localObject1).delete("programNodesRev", "cid=?", new String[] { localObject3 });
            }
          }
        }
      }
      ((SQLiteDatabase)localObject1).execSQL("delete from programNodesRev" + " where cid = '" + j + "'");
      Object localObject2 = new Gson();
      int i = 0;
      while ((i < paramDataCommand.size()) && (i < k))
      {
        localObject3 = (Node)paramDataCommand.get(i);
        String str = ((Gson)localObject2).toJson(localObject3);
        ((SQLiteDatabase)localObject1).execSQL("insert into programNodesRev(cid,pid,dw,programNode) values(?, ?, ?, ?)", new Object[] { Integer.valueOf(j), Integer.valueOf(((ProgramNode)localObject3).uniqueId), Integer.valueOf(0), str });
        i += 1;
      }
      ((SQLiteDatabase)localObject1).setTransactionSuccessful();
      ((SQLiteDatabase)localObject1).endTransaction();
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
    return "ProgramNodesRevDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_program_node_rev"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_program_node_rev"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_program_node_rev"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_program_node_rev"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.ProgramNodesRevDS
 * JD-Core Version:    0.6.2
 */