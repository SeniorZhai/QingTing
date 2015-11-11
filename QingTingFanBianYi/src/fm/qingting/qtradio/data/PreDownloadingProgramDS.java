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
import fm.qingting.qtradio.model.ProgramNode;
import java.util.List;
import java.util.Map;

public class PreDownloadingProgramDS
  implements IDataSource
{
  private static PreDownloadingProgramDS instance;

  // ERROR //
  private List<Node> acquireProgramNodes(DataCommand paramDataCommand)
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
    //   42: ifeq +49 -> 91
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
    //   70: checkcast 61	fm/qingting/qtradio/model/ProgramNode
    //   73: astore_2
    //   74: aload_2
    //   75: astore_1
    //   76: aload_1
    //   77: ifnull +29 -> 106
    //   80: aload_3
    //   81: aload_1
    //   82: invokeinterface 71 2 0
    //   87: pop
    //   88: goto +18 -> 106
    //   91: aload 4
    //   93: invokeinterface 74 1 0
    //   98: aload_3
    //   99: areturn
    //   100: astore_1
    //   101: aconst_null
    //   102: areturn
    //   103: astore_1
    //   104: aload_3
    //   105: areturn
    //   106: goto -71 -> 35
    //   109: astore_2
    //   110: goto -34 -> 76
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	100	java/lang/Exception
    //   8	33	103	java/lang/Exception
    //   35	62	103	java/lang/Exception
    //   80	88	103	java/lang/Exception
    //   91	98	103	java/lang/Exception
    //   62	74	109	java/lang/Exception
  }

  private boolean deleteProgramNodes(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("predownloadingprogramNodes").execSQL("delete from predownloadingNodes");
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

  public static PreDownloadingProgramDS getInstance()
  {
    if (instance == null)
      instance = new PreDownloadingProgramDS();
    return instance;
  }

  private boolean insertProgramNodes(DataCommand paramDataCommand)
  {
    paramDataCommand = (Node)paramDataCommand.getParam().get("node");
    if (paramDataCommand == null)
      return false;
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("predownloadingprogramNodes");
    try
    {
      localSQLiteDatabase.beginTransaction();
      String str = new Gson().toJson(paramDataCommand);
      int i = ((ProgramNode)paramDataCommand).id;
      localSQLiteDatabase.execSQL("delete from predownloadingNodes where id = '" + i + "'");
      localSQLiteDatabase.execSQL("insert into predownloadingNodes(id,programNode) values(?,?)", new Object[] { Integer.valueOf(i), str });
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

  private boolean updateProgramNodes(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("predownloadingprogramNodes");
    try
    {
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from predownloadingNodes");
      Gson localGson = new Gson();
      if (paramDataCommand != null)
      {
        int i = 0;
        while (i < paramDataCommand.size())
        {
          Node localNode = (Node)paramDataCommand.get(i);
          String str = localGson.toJson(localNode);
          localSQLiteDatabase.execSQL("insert into predownloadingNodes(id,programNode) values(?,?)", new Object[] { Integer.valueOf(((ProgramNode)localNode).id), str });
          i += 1;
        }
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
    return "PreDownloadingProgramDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_predownloading_program_node"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_predownloading_program_node"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_predownloading_program_node"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_predownloading_program_node"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PreDownloadingProgramDS
 * JD-Core Version:    0.6.2
 */