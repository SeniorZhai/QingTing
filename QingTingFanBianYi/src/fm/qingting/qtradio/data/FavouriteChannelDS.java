package fm.qingting.qtradio.data;

import android.content.ContentValues;
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
import fm.qingting.qtradio.model.Node;
import java.util.List;
import java.util.Map;

public class FavouriteChannelDS
  implements IDataSource
{
  private static FavouriteChannelDS instance;

  // ERROR //
  private List<Node> acquireFavourites(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: new 19	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 20	java/util/ArrayList:<init>	()V
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
    //   37: ifeq +125 -> 162
    //   40: aload_2
    //   41: aload_2
    //   42: ldc 51
    //   44: invokeinterface 55 2 0
    //   49: invokeinterface 59 2 0
    //   54: astore 4
    //   56: aload_3
    //   57: aload 4
    //   59: ldc 61
    //   61: invokevirtual 65	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   64: checkcast 61	fm/qingting/qtradio/model/ChannelNode
    //   67: astore 5
    //   69: aload 5
    //   71: ifnull +85 -> 156
    //   74: aload 5
    //   76: getfield 69	fm/qingting/qtradio/model/ChannelNode:title	Ljava/lang/String;
    //   79: ifnull +71 -> 150
    //   82: aload 5
    //   84: getfield 69	fm/qingting/qtradio/model/ChannelNode:title	Ljava/lang/String;
    //   87: ldc 71
    //   89: invokevirtual 77	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   92: ifne +58 -> 150
    //   95: aload_1
    //   96: aload 5
    //   98: invokeinterface 83 2 0
    //   103: pop
    //   104: iconst_0
    //   105: istore 6
    //   107: iload 6
    //   109: ifeq -78 -> 31
    //   112: aload_3
    //   113: aload 4
    //   115: ldc 85
    //   117: invokevirtual 65	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   120: checkcast 85	fm/qingting/qtradio/model/ChannelNodeV5
    //   123: astore 4
    //   125: aload 4
    //   127: ifnull -96 -> 31
    //   130: aload_1
    //   131: aload 4
    //   133: invokevirtual 89	fm/qingting/qtradio/model/ChannelNodeV5:convertToChannel	()Lfm/qingting/qtradio/model/ChannelNode;
    //   136: invokeinterface 83 2 0
    //   141: pop
    //   142: goto -111 -> 31
    //   145: astore 4
    //   147: goto -116 -> 31
    //   150: iconst_1
    //   151: istore 6
    //   153: goto -46 -> 107
    //   156: iconst_1
    //   157: istore 6
    //   159: goto -52 -> 107
    //   162: aload_2
    //   163: invokeinterface 92 1 0
    //   168: aload_1
    //   169: areturn
    //   170: astore_1
    //   171: aconst_null
    //   172: areturn
    //   173: astore_2
    //   174: aload_1
    //   175: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   56	69	145	java/lang/Exception
    //   74	104	145	java/lang/Exception
    //   112	125	145	java/lang/Exception
    //   130	142	145	java/lang/Exception
    //   0	8	170	java/lang/Exception
    //   8	31	173	java/lang/Exception
    //   31	56	173	java/lang/Exception
    //   162	168	173	java/lang/Exception
  }

  private boolean deleteFavouriteChannel(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("favouritechannels").execSQL("delete from favouritechannels");
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
    localDataToken.setData(new Result(true, acquireFavourites(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteFavouriteChannel(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertFavourites(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateFavouriteChannel(paramDataCommand))));
    return localDataToken;
  }

  public static FavouriteChannelDS getInstance()
  {
    if (instance == null)
      instance = new FavouriteChannelDS();
    return instance;
  }

  private boolean insertFavourites(DataCommand paramDataCommand)
  {
    Object localObject = paramDataCommand.getParam();
    paramDataCommand = (List)((Map)localObject).get("channels");
    localObject = (Integer)((Map)localObject).get("total");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    ((Integer)localObject).intValue();
    try
    {
      localObject = DBManager.getInstance().getWritableDB("favouritechannels");
      ((SQLiteDatabase)localObject).beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        Node localNode = (Node)paramDataCommand.get(i);
        String str = localGson.toJson(localNode);
        ((SQLiteDatabase)localObject).execSQL("insert into favouritechannels(id, channelNode) values(?, ?)", new Object[] { Integer.valueOf(Integer.valueOf(((ChannelNode)localNode).channelId).intValue()), str });
        i += 1;
      }
      ((SQLiteDatabase)localObject).setTransactionSuccessful();
      ((SQLiteDatabase)localObject).endTransaction();
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateFavouriteChannel(DataCommand paramDataCommand)
  {
    paramDataCommand = (Node)paramDataCommand.getParam().get("channel");
    if (paramDataCommand == null)
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("favouritechannels");
      localSQLiteDatabase.beginTransaction();
      String str = new Gson().toJson(paramDataCommand);
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("id", String.valueOf(((ChannelNode)paramDataCommand).channelId));
      localContentValues.put("channelNode", str);
      localSQLiteDatabase.update("favouritechannels", localContentValues, "id=?", new String[] { String.valueOf(((ChannelNode)paramDataCommand).channelId) });
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
    return "FavouriteChannelDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insert_favourite_channels"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("get_favourite_channels"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("delete_favourite_channels"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("update_favourite_channels"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.FavouriteChannelDS
 * JD-Core Version:    0.6.2
 */