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
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.Node;
import java.util.List;
import java.util.Map;

public class CategoryNodeDS
  implements IDataSource
{
  private static CategoryNodeDS instance;

  // ERROR //
  private List<Node> acquireCategoryNodes(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   4: ldc 25
    //   6: invokeinterface 31 2 0
    //   11: checkcast 33	java/lang/Integer
    //   14: invokevirtual 37	java/lang/Integer:intValue	()I
    //   17: istore 5
    //   19: new 39	java/util/ArrayList
    //   22: dup
    //   23: invokespecial 40	java/util/ArrayList:<init>	()V
    //   26: astore_1
    //   27: new 42	java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial 43	java/lang/StringBuilder:<init>	()V
    //   34: ldc 45
    //   36: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: ldc 51
    //   41: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: iload 5
    //   46: invokevirtual 54	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   49: ldc 56
    //   51: invokevirtual 49	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 60	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore_2
    //   58: invokestatic 66	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   61: ldc 68
    //   63: invokevirtual 72	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   66: aload_2
    //   67: aconst_null
    //   68: invokevirtual 78	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   71: astore_2
    //   72: new 80	com/google/gson/Gson
    //   75: dup
    //   76: invokespecial 81	com/google/gson/Gson:<init>	()V
    //   79: astore_3
    //   80: aload_2
    //   81: invokeinterface 87 1 0
    //   86: ifeq +66 -> 152
    //   89: aload_2
    //   90: aload_2
    //   91: ldc 89
    //   93: invokeinterface 93 2 0
    //   98: invokeinterface 97 2 0
    //   103: astore 4
    //   105: aload_3
    //   106: aload 4
    //   108: ldc 99
    //   110: invokevirtual 103	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   113: checkcast 99	fm/qingting/qtradio/model/CategoryNode
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull -40 -> 80
    //   123: aload 4
    //   125: iload 5
    //   127: putfield 107	fm/qingting/qtradio/model/CategoryNode:parentId	I
    //   130: aload_1
    //   131: aload 4
    //   133: invokeinterface 113 2 0
    //   138: pop
    //   139: goto -59 -> 80
    //   142: astore 4
    //   144: aload 4
    //   146: invokevirtual 116	java/lang/Exception:printStackTrace	()V
    //   149: goto -69 -> 80
    //   152: aload_2
    //   153: invokeinterface 119 1 0
    //   158: aload_1
    //   159: areturn
    //   160: astore_1
    //   161: aconst_null
    //   162: areturn
    //   163: astore_2
    //   164: aload_1
    //   165: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   105	118	142	java/lang/Exception
    //   123	139	142	java/lang/Exception
    //   19	27	160	java/lang/Exception
    //   27	80	163	java/lang/Exception
    //   80	105	163	java/lang/Exception
    //   144	149	163	java/lang/Exception
    //   152	158	163	java/lang/Exception
  }

  private boolean deleteCategoryNodes(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("categoryNodes").execSQL("delete from categoryNodes");
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
    localDataToken.setData(new Result(true, acquireCategoryNodes(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteCategoryNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertCategoryNodes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateCategoryNodes(paramDataCommand))));
    return localDataToken;
  }

  public static CategoryNodeDS getInstance()
  {
    if (instance == null)
      instance = new CategoryNodeDS();
    return instance;
  }

  private boolean insertCategoryNodes(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("categoryNodes");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (true)
      {
        if (i < paramDataCommand.size())
        {
          Node localNode = (Node)paramDataCommand.get(i);
          if (localNode.nodeName.equalsIgnoreCase("category"))
          {
            String str = localGson.toJson(localNode);
            localSQLiteDatabase.execSQL("insert into categoryNodes(id,parentId,categoryNode) values(?,?,?)", new Object[] { Integer.valueOf(((CategoryNode)localNode).categoryId), Integer.valueOf(((CategoryNode)localNode).parentId), str });
          }
        }
        else
        {
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return true;
        }
        i += 1;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateCategoryNodes(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject1).get("nodes");
    int j = ((Integer)((Map)localObject1).get("parentid")).intValue();
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      Object localObject2 = "delete from categoryNodes" + " where parentId = '" + j + "'";
      localObject1 = DBManager.getInstance().getWritableDB("categoryNodes");
      ((SQLiteDatabase)localObject1).beginTransaction();
      ((SQLiteDatabase)localObject1).execSQL((String)localObject2);
      localObject2 = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        Node localNode = (Node)paramDataCommand.get(i);
        String str = ((Gson)localObject2).toJson(localNode);
        ((SQLiteDatabase)localObject1).execSQL("insert into categoryNodes(id,parentId,categoryNode) values(?,?,?)", new Object[] { Integer.valueOf(((CategoryNode)localNode).categoryId), Integer.valueOf(j), str });
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
    return "CategoryNodeDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_category_node"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_category_node"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_category_node"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_category_node"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.CategoryNodeDS
 * JD-Core Version:    0.6.2
 */