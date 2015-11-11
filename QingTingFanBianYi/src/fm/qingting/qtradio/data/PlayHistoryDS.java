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
import fm.qingting.qtradio.model.PlayHistoryNode;
import java.util.List;
import java.util.Map;

public class PlayHistoryDS
  implements IDataSource
{
  private static PlayHistoryDS instance;

  // ERROR //
  private List<PlayHistoryNode> acquirePlayHistory(DataCommand paramDataCommand)
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
    //   42: ifeq +350 -> 392
    //   45: aload 4
    //   47: ldc 51
    //   49: invokeinterface 55 2 0
    //   54: istore 12
    //   56: aload 4
    //   58: ldc 57
    //   60: invokeinterface 55 2 0
    //   65: istore 13
    //   67: aload 4
    //   69: ldc 59
    //   71: invokeinterface 55 2 0
    //   76: istore 14
    //   78: aload 4
    //   80: ldc 61
    //   82: invokeinterface 55 2 0
    //   87: istore 15
    //   89: aload 4
    //   91: ldc 63
    //   93: invokeinterface 55 2 0
    //   98: istore 11
    //   100: aload 4
    //   102: ldc 65
    //   104: invokeinterface 55 2 0
    //   109: istore 9
    //   111: aload 4
    //   113: ldc 67
    //   115: invokeinterface 55 2 0
    //   120: istore 10
    //   122: aload 4
    //   124: iload 12
    //   126: invokeinterface 71 2 0
    //   131: astore_2
    //   132: aload 4
    //   134: iload 13
    //   136: invokeinterface 75 2 0
    //   141: lstore 16
    //   143: aload 4
    //   145: iload 14
    //   147: invokeinterface 71 2 0
    //   152: astore 8
    //   154: aload 4
    //   156: iload 15
    //   158: invokeinterface 79 2 0
    //   163: istore 12
    //   165: aload 4
    //   167: iload 11
    //   169: invokeinterface 79 2 0
    //   174: istore 11
    //   176: aload 4
    //   178: iload 9
    //   180: invokeinterface 75 2 0
    //   185: lstore 18
    //   187: aload 4
    //   189: iload 10
    //   191: invokeinterface 71 2 0
    //   196: astore 6
    //   198: aload 4
    //   200: aload 4
    //   202: ldc 81
    //   204: invokeinterface 55 2 0
    //   209: invokeinterface 71 2 0
    //   214: astore 7
    //   216: iconst_0
    //   217: istore 9
    //   219: iload 9
    //   221: aload_3
    //   222: invokeinterface 87 1 0
    //   227: if_icmpge +22 -> 249
    //   230: aload_3
    //   231: iload 9
    //   233: invokeinterface 91 2 0
    //   238: checkcast 93	fm/qingting/qtradio/model/PlayHistoryNode
    //   241: getfield 96	fm/qingting/qtradio/model/PlayHistoryNode:channelId	I
    //   244: iload 12
    //   246: if_icmpne +161 -> 407
    //   249: iload 9
    //   251: aload_3
    //   252: invokeinterface 87 1 0
    //   257: if_icmpne -222 -> 35
    //   260: aload 8
    //   262: ldc 98
    //   264: invokevirtual 104	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   267: istore 20
    //   269: iload 20
    //   271: ifeq +86 -> 357
    //   274: aload 5
    //   276: aload_2
    //   277: ldc 106
    //   279: invokevirtual 110	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   282: checkcast 106	fm/qingting/qtradio/model/ProgramNode
    //   285: astore_2
    //   286: aload_2
    //   287: astore_1
    //   288: new 93	fm/qingting/qtradio/model/PlayHistoryNode
    //   291: dup
    //   292: invokespecial 111	fm/qingting/qtradio/model/PlayHistoryNode:<init>	()V
    //   295: astore_2
    //   296: aload_1
    //   297: ifnull +8 -> 305
    //   300: aload_1
    //   301: aload_2
    //   302: putfield 117	fm/qingting/qtradio/model/Node:parent	Lfm/qingting/qtradio/model/Node;
    //   305: aload_2
    //   306: aload_1
    //   307: putfield 119	fm/qingting/qtradio/model/PlayHistoryNode:playNode	Lfm/qingting/qtradio/model/Node;
    //   310: aload_2
    //   311: lload 16
    //   313: putfield 123	fm/qingting/qtradio/model/PlayHistoryNode:playTime	J
    //   316: aload_2
    //   317: iload 11
    //   319: putfield 126	fm/qingting/qtradio/model/PlayHistoryNode:categoryId	I
    //   322: aload_2
    //   323: iload 12
    //   325: putfield 96	fm/qingting/qtradio/model/PlayHistoryNode:channelId	I
    //   328: aload_2
    //   329: lload 18
    //   331: putfield 129	fm/qingting/qtradio/model/PlayHistoryNode:playContent	J
    //   334: aload_2
    //   335: aload 6
    //   337: putfield 132	fm/qingting/qtradio/model/PlayHistoryNode:channelName	Ljava/lang/String;
    //   340: aload_2
    //   341: aload 7
    //   343: putfield 134	fm/qingting/qtradio/model/PlayHistoryNode:channelThumb	Ljava/lang/String;
    //   346: aload_3
    //   347: aload_2
    //   348: invokeinterface 138 2 0
    //   353: pop
    //   354: goto -319 -> 35
    //   357: aload 8
    //   359: ldc 140
    //   361: invokevirtual 104	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   364: istore 20
    //   366: iload 20
    //   368: ifeq +36 -> 404
    //   371: aload 5
    //   373: aload_2
    //   374: ldc 142
    //   376: invokevirtual 110	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   379: checkcast 142	fm/qingting/qtradio/model/ChannelNode
    //   382: astore_2
    //   383: aload_2
    //   384: astore_1
    //   385: goto -97 -> 288
    //   388: astore_2
    //   389: goto -101 -> 288
    //   392: aload 4
    //   394: invokeinterface 145 1 0
    //   399: aload_3
    //   400: areturn
    //   401: astore_1
    //   402: aconst_null
    //   403: areturn
    //   404: goto -116 -> 288
    //   407: iload 9
    //   409: iconst_1
    //   410: iadd
    //   411: istore 9
    //   413: goto -194 -> 219
    //   416: astore_2
    //   417: goto -129 -> 288
    //
    // Exception table:
    //   from	to	target	type
    //   371	383	388	java/lang/Exception
    //   0	33	401	java/lang/Exception
    //   35	216	401	java/lang/Exception
    //   219	249	401	java/lang/Exception
    //   249	269	401	java/lang/Exception
    //   288	296	401	java/lang/Exception
    //   300	305	401	java/lang/Exception
    //   305	354	401	java/lang/Exception
    //   357	366	401	java/lang/Exception
    //   392	399	401	java/lang/Exception
    //   274	286	416	java/lang/Exception
  }

  private boolean delInsertPlayHistory(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("playhistory");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    int j = 0;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playhistory");
      localSQLiteDatabase.beginTransaction();
      localSQLiteDatabase.execSQL("delete from playhistory");
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        PlayHistoryNode localPlayHistoryNode = (PlayHistoryNode)paramDataCommand.get(i);
        long l1 = localPlayHistoryNode.playTime;
        String str1 = localGson.toJson(localPlayHistoryNode.playNode);
        int k = localPlayHistoryNode.channelId;
        int m = localPlayHistoryNode.categoryId;
        long l2 = localPlayHistoryNode.playContent;
        String str2 = localPlayHistoryNode.channelName;
        String str3 = localPlayHistoryNode.channelThumb;
        j += 1;
        localSQLiteDatabase.execSQL("insert into playhistory(id,nodename,playNode,time,channelId,catId,subCatId,playPosition,channelName,channelThumb) values(?,?,?, ?, ?,?,?,?,?,?)", new Object[] { Integer.valueOf(j), ((PlayHistoryNode)paramDataCommand.get(i)).playNode.nodeName, str1, Long.valueOf(l1), Integer.valueOf(k), Integer.valueOf(m), Integer.valueOf(localPlayHistoryNode.subCatId), Long.valueOf(l2), str2, str3 });
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

  private boolean deletePlayHistory(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("playhistory").execSQL("delete from playhistory");
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
    localDataToken.setData(new Result(true, acquirePlayHistory(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDelInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(delInsertPlayHistory(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deletePlayHistory(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertPlayHistory(paramDataCommand))));
    return localDataToken;
  }

  public static PlayHistoryDS getInstance()
  {
    if (instance == null)
      instance = new PlayHistoryDS();
    return instance;
  }

  private boolean insertPlayHistory(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("playhistory");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("playhistory");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int j = 0;
      int i = 0;
      while (i < paramDataCommand.size())
      {
        Object localObject = (PlayHistoryNode)paramDataCommand.get(i);
        long l1 = ((PlayHistoryNode)localObject).playTime;
        String str1 = localGson.toJson(((PlayHistoryNode)localObject).playNode);
        int k = ((PlayHistoryNode)localObject).channelId;
        int m = ((PlayHistoryNode)localObject).categoryId;
        long l2 = ((PlayHistoryNode)localObject).playContent;
        String str2 = ((PlayHistoryNode)localObject).channelName;
        localObject = ((PlayHistoryNode)localObject).channelThumb;
        j += 1;
        localSQLiteDatabase.execSQL("insert into playhistory(id,nodename,playNode,time,channelId,catId,playPosition,channelName,channelThumb) values(?,?, ?, ?,?,?,?,?,?)", new Object[] { Integer.valueOf(j), ((PlayHistoryNode)paramDataCommand.get(i)).playNode.nodeName, str1, Long.valueOf(l1), Integer.valueOf(k), Integer.valueOf(m), Long.valueOf(l2), str2, localObject });
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
    return "PlayHistoryDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_play_history"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_play_history"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_play_history"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("delinsertdb_play_history"))
      return doDelInsertCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PlayHistoryDS
 * JD-Core Version:    0.6.2
 */