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
import fm.qingting.qtradio.model.ReserveNode;
import java.util.List;
import java.util.Map;

public class ReserveProgramDS
  implements IDataSource
{
  private static ReserveProgramDS instance;
  private int MAX_COUNT = 10;

  // ERROR //
  private List<ReserveNode> acquireReserveProgram(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: new 23	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 24	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 30	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 32
    //   13: invokevirtual 36	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc 38
    //   18: aconst_null
    //   19: invokevirtual 44	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   22: astore 4
    //   24: iconst_0
    //   25: istore 10
    //   27: new 46	com/google/gson/Gson
    //   30: dup
    //   31: invokespecial 47	com/google/gson/Gson:<init>	()V
    //   34: astore 5
    //   36: aconst_null
    //   37: astore_1
    //   38: aload 4
    //   40: invokeinterface 53 1 0
    //   45: ifeq +220 -> 265
    //   48: iload 10
    //   50: aload_0
    //   51: getfield 16	fm/qingting/qtradio/data/ReserveProgramDS:MAX_COUNT	I
    //   54: if_icmpge +211 -> 265
    //   57: aload 4
    //   59: ldc 55
    //   61: invokeinterface 59 2 0
    //   66: istore 11
    //   68: aload 4
    //   70: ldc 61
    //   72: invokeinterface 59 2 0
    //   77: istore 12
    //   79: aload 4
    //   81: ldc 63
    //   83: invokeinterface 59 2 0
    //   88: istore 13
    //   90: aload 4
    //   92: ldc 65
    //   94: invokeinterface 59 2 0
    //   99: istore 14
    //   101: aload 4
    //   103: ldc 67
    //   105: invokeinterface 59 2 0
    //   110: istore 15
    //   112: aload 4
    //   114: iload 11
    //   116: invokeinterface 71 2 0
    //   121: astore_2
    //   122: aload 4
    //   124: iload 12
    //   126: invokeinterface 71 2 0
    //   131: astore 6
    //   133: aload 4
    //   135: iload 13
    //   137: invokeinterface 71 2 0
    //   142: astore 7
    //   144: aload 4
    //   146: iload 14
    //   148: invokeinterface 71 2 0
    //   153: astore 8
    //   155: aload 4
    //   157: iload 15
    //   159: invokeinterface 71 2 0
    //   164: astore 9
    //   166: aload 5
    //   168: aload_2
    //   169: ldc 73
    //   171: invokevirtual 77	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   174: checkcast 79	fm/qingting/qtradio/model/Node
    //   177: astore_2
    //   178: aload_2
    //   179: astore_1
    //   180: aload_1
    //   181: ifnull +103 -> 284
    //   184: new 81	fm/qingting/qtradio/model/ReserveNode
    //   187: dup
    //   188: invokespecial 82	fm/qingting/qtradio/model/ReserveNode:<init>	()V
    //   191: astore_2
    //   192: aload_2
    //   193: aload_1
    //   194: putfield 86	fm/qingting/qtradio/model/ReserveNode:reserveNode	Lfm/qingting/qtradio/model/Node;
    //   197: aload_2
    //   198: aload 6
    //   200: invokestatic 92	java/lang/Long:valueOf	(Ljava/lang/String;)Ljava/lang/Long;
    //   203: invokevirtual 96	java/lang/Long:longValue	()J
    //   206: putfield 100	fm/qingting/qtradio/model/ReserveNode:reserveTime	J
    //   209: aload_2
    //   210: aload 7
    //   212: invokestatic 105	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   215: invokevirtual 109	java/lang/Integer:intValue	()I
    //   218: putfield 111	fm/qingting/qtradio/model/ReserveNode:channelId	I
    //   221: aload_2
    //   222: aload 8
    //   224: putfield 114	fm/qingting/qtradio/model/ReserveNode:programName	Ljava/lang/String;
    //   227: aload_2
    //   228: aload 9
    //   230: invokestatic 105	java/lang/Integer:valueOf	(Ljava/lang/String;)Ljava/lang/Integer;
    //   233: invokevirtual 109	java/lang/Integer:intValue	()I
    //   236: putfield 117	fm/qingting/qtradio/model/ReserveNode:uniqueId	I
    //   239: aload_2
    //   240: getfield 100	fm/qingting/qtradio/model/ReserveNode:reserveTime	J
    //   243: invokestatic 122	java/lang/System:currentTimeMillis	()J
    //   246: ldc2_w 123
    //   249: ldiv
    //   250: lcmp
    //   251: ifle +36 -> 287
    //   254: aload_3
    //   255: aload_2
    //   256: invokeinterface 130 2 0
    //   261: pop
    //   262: goto +25 -> 287
    //   265: aload 4
    //   267: invokeinterface 133 1 0
    //   272: aload_3
    //   273: areturn
    //   274: astore_1
    //   275: aconst_null
    //   276: areturn
    //   277: astore_1
    //   278: aload_3
    //   279: areturn
    //   280: astore_2
    //   281: goto -101 -> 180
    //   284: goto +9 -> 293
    //   287: iload 10
    //   289: iconst_1
    //   290: iadd
    //   291: istore 10
    //   293: goto -255 -> 38
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	274	java/lang/Exception
    //   8	24	277	java/lang/Exception
    //   27	36	277	java/lang/Exception
    //   38	166	277	java/lang/Exception
    //   184	262	277	java/lang/Exception
    //   265	272	277	java/lang/Exception
    //   166	178	280	java/lang/Exception
  }

  private boolean deleteReserveProgram(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("reserveprogram").execSQL("delete from reserveprogram");
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
    localDataToken.setData(new Result(true, acquireReserveProgram(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteReserveProgram(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertReserveProgram(paramDataCommand))));
    return localDataToken;
  }

  public static ReserveProgramDS getInstance()
  {
    if (instance == null)
      instance = new ReserveProgramDS();
    return instance;
  }

  private boolean insertReserveProgram(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("reserveprogram");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("reserveprogram");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        ReserveNode localReserveNode = (ReserveNode)paramDataCommand.get(i);
        int j = localReserveNode.channelId;
        String str = localReserveNode.programName;
        int k = localReserveNode.uniqueId;
        localSQLiteDatabase.execSQL("insert into reserveprogram(time, reserveProgram,channelId,programName,programId) values(?,?,?,?,?)", new Object[] { Long.valueOf(localReserveNode.reserveTime), localGson.toJson(localReserveNode.reserveNode), Integer.valueOf(j), str, Integer.valueOf(k) });
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
    return "ReserveProgramDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insert_reserve_program"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("get_reserve_program"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("delete_reserve_program"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.ReserveProgramDS
 * JD-Core Version:    0.6.2
 */