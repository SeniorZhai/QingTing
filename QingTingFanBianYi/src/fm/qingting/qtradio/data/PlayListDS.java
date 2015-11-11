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

public class PlayListDS
  implements IDataSource
{
  private static PlayListDS instance;

  // ERROR //
  private List<Node> acquirePlaylist(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: new 19	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 20	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: invokestatic 26	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   12: ldc 28
    //   14: invokevirtual 32	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   17: ldc 34
    //   19: aconst_null
    //   20: invokevirtual 40	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   23: astore 5
    //   25: new 42	com/google/gson/Gson
    //   28: dup
    //   29: invokespecial 43	com/google/gson/Gson:<init>	()V
    //   32: astore 6
    //   34: aconst_null
    //   35: astore_2
    //   36: aconst_null
    //   37: astore_1
    //   38: aload 5
    //   40: invokeinterface 49 1 0
    //   45: ifeq +143 -> 188
    //   48: aload 5
    //   50: aload 5
    //   52: ldc 51
    //   54: invokeinterface 55 2 0
    //   59: invokeinterface 59 2 0
    //   64: astore_3
    //   65: aload 5
    //   67: aload 5
    //   69: ldc 61
    //   71: invokeinterface 55 2 0
    //   76: invokeinterface 65 2 0
    //   81: istore 7
    //   83: iload 7
    //   85: ifne +49 -> 134
    //   88: aload 6
    //   90: aload_3
    //   91: ldc 67
    //   93: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   96: checkcast 67	fm/qingting/qtradio/model/ProgramNode
    //   99: astore_3
    //   100: aload_3
    //   101: astore_1
    //   102: aload_1
    //   103: ifnull +98 -> 201
    //   106: aload_2
    //   107: ifnull +13 -> 120
    //   110: aload_2
    //   111: aload_1
    //   112: putfield 77	fm/qingting/qtradio/model/Node:nextSibling	Lfm/qingting/qtradio/model/Node;
    //   115: aload_1
    //   116: aload_2
    //   117: putfield 80	fm/qingting/qtradio/model/Node:prevSibling	Lfm/qingting/qtradio/model/Node;
    //   120: aload 4
    //   122: aload_1
    //   123: invokeinterface 86 2 0
    //   128: pop
    //   129: aload_1
    //   130: astore_2
    //   131: goto -93 -> 38
    //   134: iload 7
    //   136: iconst_2
    //   137: if_icmpne +20 -> 157
    //   140: aload 6
    //   142: aload_3
    //   143: ldc 88
    //   145: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   148: checkcast 88	fm/qingting/qtradio/model/ChannelNode
    //   151: astore_3
    //   152: aload_3
    //   153: astore_1
    //   154: goto -52 -> 102
    //   157: iload 7
    //   159: iconst_3
    //   160: if_icmpne +44 -> 204
    //   163: aload 6
    //   165: aload_3
    //   166: ldc 90
    //   168: invokevirtual 71	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   171: checkcast 90	fm/qingting/qtradio/model/RadioChannelNode
    //   174: astore_3
    //   175: aload_3
    //   176: astore_1
    //   177: goto -75 -> 102
    //   180: astore_3
    //   181: aload_3
    //   182: invokevirtual 93	java/lang/Exception:printStackTrace	()V
    //   185: goto -83 -> 102
    //   188: aload 5
    //   190: invokeinterface 96 1 0
    //   195: aload 4
    //   197: areturn
    //   198: astore_1
    //   199: aconst_null
    //   200: areturn
    //   201: goto -70 -> 131
    //   204: goto -102 -> 102
    //   207: astore_1
    //   208: aload 4
    //   210: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   88	100	180	java/lang/Exception
    //   140	152	180	java/lang/Exception
    //   163	175	180	java/lang/Exception
    //   0	9	198	java/lang/Exception
    //   9	34	207	java/lang/Exception
    //   38	83	207	java/lang/Exception
    //   110	120	207	java/lang/Exception
    //   120	129	207	java/lang/Exception
    //   181	185	207	java/lang/Exception
    //   188	195	207	java/lang/Exception
  }

  private DataToken doAcquireCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquirePlaylist(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertPlaylist(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updatePlaylist(paramDataCommand))));
    return localDataToken;
  }

  public static PlayListDS getInstance()
  {
    if (instance == null)
      instance = new PlayListDS();
    return instance;
  }

  private boolean insertPlaylist(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    while (true)
    {
      int j;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playList");
        localSQLiteDatabase.beginTransaction();
        Gson localGson = new Gson();
        j = 0;
        if (j < paramDataCommand.size())
        {
          Node localNode = (Node)paramDataCommand.get(j);
          int i;
          if (localNode.nodeName.equalsIgnoreCase("program"))
          {
            i = 0;
            localSQLiteDatabase.execSQL("insert into playList(id,type,node) values(?,?,?)", new Object[] { Integer.valueOf(j), Integer.valueOf(i), localGson.toJson(localNode) });
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

  private boolean updatePlaylist(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("nodes");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    while (true)
    {
      int j;
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playList");
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL("delete from playList");
        Gson localGson = new Gson();
        j = 0;
        if (j < paramDataCommand.size())
        {
          Node localNode = (Node)paramDataCommand.get(j);
          int i;
          if (localNode.nodeName.equalsIgnoreCase("program"))
          {
            i = 0;
            localSQLiteDatabase.execSQL("insert into playList(id,type,node) values(?,?,?)", new Object[] { Integer.valueOf(j), Integer.valueOf(i), localGson.toJson(localNode) });
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
    return "PlayListDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_playlist"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_playlist"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_playlist"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PlayListDS
 * JD-Core Version:    0.6.2
 */