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
import java.util.List;
import java.util.Map;

public class AttributesDS
  implements IDataSource
{
  private static AttributesDS instance;

  // ERROR //
  private List<fm.qingting.qtradio.model.Attributes> acquireCategoryAttributes(DataCommand paramDataCommand)
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
    //   86: ifeq +59 -> 145
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
    //   113: checkcast 99	fm/qingting/qtradio/model/Attributes
    //   116: astore 4
    //   118: aload 4
    //   120: ifnull -40 -> 80
    //   123: aload_1
    //   124: aload 4
    //   126: invokeinterface 109 2 0
    //   131: pop
    //   132: goto -52 -> 80
    //   135: astore 4
    //   137: aload 4
    //   139: invokevirtual 112	java/lang/Exception:printStackTrace	()V
    //   142: goto -62 -> 80
    //   145: aload_2
    //   146: invokeinterface 115 1 0
    //   151: aload_1
    //   152: areturn
    //   153: astore_1
    //   154: aconst_null
    //   155: areturn
    //   156: astore_2
    //   157: aload_1
    //   158: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   105	118	135	java/lang/Exception
    //   19	27	153	java/lang/Exception
    //   27	80	156	java/lang/Exception
    //   80	105	156	java/lang/Exception
    //   123	132	156	java/lang/Exception
    //   137	142	156	java/lang/Exception
    //   145	151	156	java/lang/Exception
  }

  private boolean deleteCategoryAttributes(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("categoryAttributes").execSQL("delete from categoryAttributes");
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
    localDataToken.setData(new Result(true, acquireCategoryAttributes(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteCategoryAttributes(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateCategoryAttributes(paramDataCommand))));
    return localDataToken;
  }

  public static AttributesDS getInstance()
  {
    if (instance == null)
      instance = new AttributesDS();
    return instance;
  }

  private boolean updateCategoryAttributes(DataCommand paramDataCommand)
  {
    Object localObject1 = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject1).get("attrs");
    int j = ((Integer)((Map)localObject1).get("catid")).intValue();
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      Object localObject2 = "delete from categoryAttributes" + " where catid = '" + j + "'";
      localObject1 = DBManager.getInstance().getWritableDB("categoryAttributes");
      ((SQLiteDatabase)localObject1).beginTransaction();
      ((SQLiteDatabase)localObject1).execSQL((String)localObject2);
      localObject2 = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        ((SQLiteDatabase)localObject1).execSQL("insert into categoryAttributes(catid,attrs) values(?,?)", new Object[] { Integer.valueOf(j), ((Gson)localObject2).toJson(paramDataCommand.get(i)) });
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
    return "AttributesDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_CATEGORY_ATTRIBUTES"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("DELETEDB_CATEGORY_ATTRIBUTES"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_CATEGORY_ATTRIBUTES"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.AttributesDS
 * JD-Core Version:    0.6.2
 */