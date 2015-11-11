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
import fm.qingting.qtradio.model.PingInfoV6;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MediaCenterDS
  implements IDataSource
{
  private static MediaCenterDS instance;

  // ERROR //
  private Map<String, List<PingInfoV6>> acquireDataCenters(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: new 19	java/util/HashMap
    //   3: dup
    //   4: invokespecial 20	java/util/HashMap:<init>	()V
    //   7: astore_1
    //   8: invokestatic 26	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 28
    //   13: invokevirtual 32	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc 34
    //   18: aconst_null
    //   19: invokevirtual 40	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   22: astore_2
    //   23: new 42	com/google/gson/Gson
    //   26: dup
    //   27: invokespecial 43	com/google/gson/Gson:<init>	()V
    //   30: astore_3
    //   31: aload_2
    //   32: invokeinterface 49 1 0
    //   37: ifeq +124 -> 161
    //   40: aload_2
    //   41: aload_2
    //   42: ldc 51
    //   44: invokeinterface 55 2 0
    //   49: invokeinterface 59 2 0
    //   54: astore 4
    //   56: aload_2
    //   57: aload_2
    //   58: ldc 61
    //   60: invokeinterface 55 2 0
    //   65: invokeinterface 59 2 0
    //   70: astore 5
    //   72: aload_3
    //   73: aload 5
    //   75: ldc 63
    //   77: invokevirtual 67	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   80: checkcast 63	fm/qingting/qtradio/model/PingInfoV6
    //   83: astore 5
    //   85: aload 5
    //   87: ifnull -56 -> 31
    //   90: aload 4
    //   92: ifnull -61 -> 31
    //   95: aload_1
    //   96: aload 4
    //   98: invokeinterface 73 2 0
    //   103: ifnull +25 -> 128
    //   106: aload_1
    //   107: aload 4
    //   109: invokeinterface 73 2 0
    //   114: checkcast 75	java/util/List
    //   117: aload 5
    //   119: invokeinterface 79 2 0
    //   124: pop
    //   125: goto -94 -> 31
    //   128: new 81	java/util/ArrayList
    //   131: dup
    //   132: invokespecial 82	java/util/ArrayList:<init>	()V
    //   135: astore 6
    //   137: aload 6
    //   139: aload 5
    //   141: invokeinterface 79 2 0
    //   146: pop
    //   147: aload_1
    //   148: aload 4
    //   150: aload 6
    //   152: invokeinterface 86 3 0
    //   157: pop
    //   158: goto -127 -> 31
    //   161: aload_2
    //   162: invokeinterface 89 1 0
    //   167: aload_1
    //   168: areturn
    //   169: astore_1
    //   170: aconst_null
    //   171: areturn
    //   172: astore 4
    //   174: goto -143 -> 31
    //
    // Exception table:
    //   from	to	target	type
    //   8	31	169	java/lang/Exception
    //   31	72	169	java/lang/Exception
    //   161	167	169	java/lang/Exception
    //   72	85	172	java/lang/Exception
    //   95	125	172	java/lang/Exception
    //   128	158	172	java/lang/Exception
  }

  private boolean deleteDataCenters(DataCommand paramDataCommand)
  {
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("mediaCenterDS");
      paramDataCommand.beginTransaction();
      paramDataCommand.execSQL("delete from mediaCenterDS");
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
    localDataToken.setData(new Result(true, acquireDataCenters(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteDataCenters(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateDataCenters(paramDataCommand))));
    return localDataToken;
  }

  public static MediaCenterDS getInstance()
  {
    if (instance == null)
      instance = new MediaCenterDS();
    return instance;
  }

  private boolean updateDataCenters(DataCommand paramDataCommand)
  {
    try
    {
      Object localObject1 = (HashMap)paramDataCommand.getParam().get("mediacenter");
      if ((localObject1 != null) && (((HashMap)localObject1).size() != 0))
      {
        paramDataCommand = DBManager.getInstance().getWritableDB("mediaCenterDS");
        paramDataCommand.beginTransaction();
        paramDataCommand.execSQL("delete from mediaCenterDS");
        localObject1 = ((HashMap)localObject1).entrySet().iterator();
        while (((Iterator)localObject1).hasNext())
        {
          Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
          List localList = (List)((Map.Entry)localObject2).getValue();
          localObject2 = (String)((Map.Entry)localObject2).getKey();
          if ((localList != null) && (localObject2 != null))
          {
            Gson localGson = new Gson();
            int i = 0;
            while (i < localList.size())
            {
              paramDataCommand.execSQL("insert into mediaCenterDS(type,pinginfo) values(?,?)", new Object[] { localObject2, localGson.toJson((PingInfoV6)localList.get(i)) });
              i += 1;
            }
          }
        }
        paramDataCommand.setTransactionSuccessful();
        paramDataCommand.endTransaction();
        return true;
      }
    }
    catch (Exception paramDataCommand)
    {
      return false;
    }
    return false;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "mediaCenterDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_MEDIA_CENTER"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("DELETEDB_MEDIA_CENTER"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_MEDIA_CENTER"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.MediaCenterDS
 * JD-Core Version:    0.6.2
 */