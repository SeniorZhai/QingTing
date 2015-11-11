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
import fm.qingting.qtradio.model.ChannelNode;
import java.util.List;
import java.util.Map;

public class ChannelNodesDS
  implements IDataSource
{
  private static ChannelNodesDS instance;

  // ERROR //
  private List<fm.qingting.qtradio.model.Node> acquireChannelNodes(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   6: ldc 25
    //   8: invokeinterface 31 2 0
    //   13: checkcast 33	java/lang/String
    //   16: astore_1
    //   17: aload_1
    //   18: ifnull +167 -> 185
    //   21: aload_1
    //   22: ldc 35
    //   24: invokevirtual 39	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   27: ifeq +6 -> 33
    //   30: goto +155 -> 185
    //   33: new 41	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   40: ldc 44
    //   42: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: aload_1
    //   46: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: ldc 50
    //   51: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore_1
    //   58: new 56	java/util/ArrayList
    //   61: dup
    //   62: invokespecial 57	java/util/ArrayList:<init>	()V
    //   65: astore 4
    //   67: invokestatic 63	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   70: ldc 65
    //   72: invokevirtual 69	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   75: aload_1
    //   76: aconst_null
    //   77: invokevirtual 75	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   80: astore 5
    //   82: new 77	com/google/gson/Gson
    //   85: dup
    //   86: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   89: astore 6
    //   91: aconst_null
    //   92: astore_2
    //   93: aload_3
    //   94: astore_1
    //   95: aload 5
    //   97: invokeinterface 84 1 0
    //   102: ifeq +66 -> 168
    //   105: aload 5
    //   107: aload 5
    //   109: ldc 86
    //   111: invokeinterface 90 2 0
    //   116: invokeinterface 94 2 0
    //   121: astore_3
    //   122: aload 6
    //   124: aload_3
    //   125: ldc 96
    //   127: invokevirtual 100	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   130: checkcast 96	fm/qingting/qtradio/model/ChannelNode
    //   133: astore_3
    //   134: aload_3
    //   135: astore_1
    //   136: aload_2
    //   137: ifnull +17 -> 154
    //   140: aload_1
    //   141: ifnull +13 -> 154
    //   144: aload_1
    //   145: aload_2
    //   146: putfield 106	fm/qingting/qtradio/model/Node:prevSibling	Lfm/qingting/qtradio/model/Node;
    //   149: aload_2
    //   150: aload_1
    //   151: putfield 109	fm/qingting/qtradio/model/Node:nextSibling	Lfm/qingting/qtradio/model/Node;
    //   154: aload 4
    //   156: aload_1
    //   157: invokeinterface 115 2 0
    //   162: pop
    //   163: aload_1
    //   164: astore_2
    //   165: goto -70 -> 95
    //   168: aload 5
    //   170: invokeinterface 118 1 0
    //   175: aload 4
    //   177: areturn
    //   178: astore_1
    //   179: aconst_null
    //   180: areturn
    //   181: astore_1
    //   182: aload 4
    //   184: areturn
    //   185: aconst_null
    //   186: areturn
    //   187: astore_3
    //   188: goto -52 -> 136
    //
    // Exception table:
    //   from	to	target	type
    //   2	17	178	java/lang/Exception
    //   21	30	178	java/lang/Exception
    //   33	67	178	java/lang/Exception
    //   67	91	181	java/lang/Exception
    //   95	122	181	java/lang/Exception
    //   144	154	181	java/lang/Exception
    //   154	163	181	java/lang/Exception
    //   168	175	181	java/lang/Exception
    //   122	134	187	java/lang/Exception
  }

  private boolean deleteChannelNodes(DataCommand paramDataCommand)
  {
    int i = ((Integer)paramDataCommand.getParam().get("catid")).intValue();
    try
    {
      paramDataCommand = "delete from channelNodes" + " where catid = '" + i + "'";
      DBManager.getInstance().getWritableDB("channelNodesv6").execSQL(paramDataCommand);
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
    localDataToken.setData(new Result(true, acquireChannelNodes(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteChannelNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doGetCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, getChannelNodes(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertChannelNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateChannelCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateChannelNode(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateChannelNodes(paramDataCommand))));
    return localDataToken;
  }

  // ERROR //
  private ChannelNode getChannelNodes(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   4: astore_1
    //   5: aload_1
    //   6: ldc 191
    //   8: invokeinterface 31 2 0
    //   13: checkcast 126	java/lang/Integer
    //   16: invokevirtual 130	java/lang/Integer:intValue	()I
    //   19: istore 5
    //   21: aload_1
    //   22: ldc 193
    //   24: invokeinterface 31 2 0
    //   29: checkcast 126	java/lang/Integer
    //   32: invokevirtual 130	java/lang/Integer:intValue	()I
    //   35: istore 6
    //   37: new 41	java/lang/StringBuilder
    //   40: dup
    //   41: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   44: ldc 195
    //   46: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: iload 5
    //   51: invokevirtual 137	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   54: ldc 197
    //   56: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   59: iload 6
    //   61: invokevirtual 137	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   64: ldc 50
    //   66: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: invokevirtual 54	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   72: astore_1
    //   73: invokestatic 63	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   76: ldc 65
    //   78: invokevirtual 69	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   81: aload_1
    //   82: aconst_null
    //   83: invokevirtual 75	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   86: astore_3
    //   87: new 77	com/google/gson/Gson
    //   90: dup
    //   91: invokespecial 78	com/google/gson/Gson:<init>	()V
    //   94: astore 4
    //   96: aconst_null
    //   97: astore_1
    //   98: aload_3
    //   99: invokeinterface 84 1 0
    //   104: ifeq +78 -> 182
    //   107: aload_3
    //   108: aload_3
    //   109: ldc 86
    //   111: invokeinterface 90 2 0
    //   116: invokeinterface 94 2 0
    //   121: astore_2
    //   122: aload 4
    //   124: aload_2
    //   125: ldc 96
    //   127: invokevirtual 100	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   130: checkcast 96	fm/qingting/qtradio/model/ChannelNode
    //   133: astore_2
    //   134: aload_2
    //   135: astore_1
    //   136: aload_2
    //   137: getfield 201	fm/qingting/qtradio/model/ChannelNode:title	Ljava/lang/String;
    //   140: ifnull +32 -> 172
    //   143: aload_2
    //   144: getfield 201	fm/qingting/qtradio/model/ChannelNode:title	Ljava/lang/String;
    //   147: ldc 203
    //   149: invokevirtual 39	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   152: istore 7
    //   154: aload_2
    //   155: astore_1
    //   156: iload 7
    //   158: ifne +14 -> 172
    //   161: aload_3
    //   162: invokeinterface 118 1 0
    //   167: aload_2
    //   168: areturn
    //   169: astore_1
    //   170: aload_2
    //   171: astore_1
    //   172: goto -74 -> 98
    //   175: astore_1
    //   176: aconst_null
    //   177: areturn
    //   178: astore_2
    //   179: goto -7 -> 172
    //   182: aload_1
    //   183: astore_2
    //   184: goto -23 -> 161
    //
    // Exception table:
    //   from	to	target	type
    //   136	154	169	java/lang/Exception
    //   0	96	175	java/lang/Exception
    //   98	122	175	java/lang/Exception
    //   161	167	175	java/lang/Exception
    //   122	134	178	java/lang/Exception
  }

  public static ChannelNodesDS getInstance()
  {
    if (instance == null)
      instance = new ChannelNodesDS();
    return instance;
  }

  private boolean insertChannelNodes(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("channelNodesv6");
    Gson localGson = new Gson();
    try
    {
      localSQLiteDatabase.beginTransaction();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        ChannelNode localChannelNode = (ChannelNode)paramDataCommand.get(i);
        String str = localGson.toJson(localChannelNode);
        localSQLiteDatabase.execSQL("insert into channelNodes(catid,type,channelid,channelNode, key) values(?, ?, ?, ?, ?)", new Object[] { Integer.valueOf(localChannelNode.categoryId), Integer.valueOf(localChannelNode.channelType), Integer.valueOf(localChannelNode.channelId), str, null });
        i += 1;
      }
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
      while (true)
        paramDataCommand.printStackTrace();
    }
  }

  private boolean updateChannelNode(DataCommand paramDataCommand)
  {
    Object localObject = paramDataCommand.getParam();
    paramDataCommand = (ChannelNode)((Map)localObject).get("node");
    int i = ((Integer)((Map)localObject).get("channelid")).intValue();
    if ((paramDataCommand == null) || (i == 0))
      return false;
    String str = "delete from channelNodes" + " where channelid = '" + i + "'";
    localObject = DBManager.getInstance().getWritableDB("channelNodesv6");
    try
    {
      ((SQLiteDatabase)localObject).beginTransaction();
      ((SQLiteDatabase)localObject).execSQL(str);
      str = new Gson().toJson(paramDataCommand);
      ((SQLiteDatabase)localObject).execSQL("insert into channelNodes(catid,type,channelid,channelNode,key) values(?, ?, ?, ?, ?)", new Object[] { Integer.valueOf(paramDataCommand.categoryId), Integer.valueOf(paramDataCommand.channelType), Integer.valueOf(paramDataCommand.channelId), str, null });
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

  private boolean updateChannelNodes(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject1).get("nodes");
    localObject1 = (String)((Map)localObject1).get("key");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0) || (localObject1 == null))
      return false;
    Object localObject2 = "delete from channelNodes" + " where key = '" + (String)localObject1 + "'";
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("channelNodesv6");
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL((String)localObject2);
      localObject2 = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        ChannelNode localChannelNode = (ChannelNode)paramDataCommand.get(i);
        String str = ((Gson)localObject2).toJson(localChannelNode);
        localSQLiteDatabase.execSQL("insert into channelNodes(catid,type,channelid,channelNode,key) values(?, ?, ?, ?, ?)", new Object[] { Integer.valueOf(localChannelNode.categoryId), Integer.valueOf(localChannelNode.channelType), Integer.valueOf(localChannelNode.channelId), str, localObject1 });
        i += 1;
      }
      localSQLiteDatabase.setTransactionSuccessful();
      localSQLiteDatabase.endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
      while (true)
        paramDataCommand.printStackTrace();
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "ChannelNodesDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("INSERTDB_CHANNEL_NODE"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_CHANNEL_NODE"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("DELETEDB_CHANNEL_NODE"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_CHANNEL_NODE"))
      return doUpdateCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_A_CHANNEL_NODE"))
      return doUpdateChannelCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_CHANNEL_INFO"))
      return doGetCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.ChannelNodesDS
 * JD-Core Version:    0.6.2
 */