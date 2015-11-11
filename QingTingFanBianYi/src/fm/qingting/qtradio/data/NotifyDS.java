package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.model.ActivityItem;
import fm.qingting.qtradio.model.NotifyMessage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NotifyDS
  implements IDataSource
{
  private static NotifyDS instance;

  // ERROR //
  private void changeNotifyState(String paramString)
  {
    // Byte code:
    //   0: new 23	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 24	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 30	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 32
    //   13: invokevirtual 36	fm/qingting/qtradio/data/DBManager:getWritableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: astore 5
    //   18: aload 5
    //   20: ldc 38
    //   22: aconst_null
    //   23: invokevirtual 44	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   26: astore 6
    //   28: iconst_0
    //   29: istore 8
    //   31: aload 6
    //   33: invokeinterface 50 1 0
    //   38: ifeq +198 -> 236
    //   41: new 52	java/io/ByteArrayInputStream
    //   44: dup
    //   45: aload 6
    //   47: aload 6
    //   49: ldc 54
    //   51: invokeinterface 58 2 0
    //   56: invokeinterface 62 2 0
    //   61: invokespecial 65	java/io/ByteArrayInputStream:<init>	([B)V
    //   64: astore 4
    //   66: new 67	java/io/ObjectInputStream
    //   69: dup
    //   70: aload 4
    //   72: invokespecial 70	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   75: astore 7
    //   77: aload 7
    //   79: invokevirtual 74	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   82: checkcast 23	java/util/ArrayList
    //   85: astore_2
    //   86: aload 7
    //   88: invokevirtual 77	java/io/ObjectInputStream:close	()V
    //   91: aload 4
    //   93: invokevirtual 78	java/io/ByteArrayInputStream:close	()V
    //   96: iload 8
    //   98: istore 9
    //   100: aload_2
    //   101: invokevirtual 82	java/util/ArrayList:size	()I
    //   104: ifle +72 -> 176
    //   107: aload_2
    //   108: invokevirtual 86	java/util/ArrayList:iterator	()Ljava/util/Iterator;
    //   111: astore_3
    //   112: aload_3
    //   113: invokeinterface 91 1 0
    //   118: ifeq +227 -> 345
    //   121: aload_3
    //   122: invokeinterface 94 1 0
    //   127: checkcast 96	fm/qingting/qtradio/model/NotifyMessage
    //   130: astore 4
    //   132: aload_1
    //   133: aload 4
    //   135: getfield 100	fm/qingting/qtradio/model/NotifyMessage:content	Ljava/lang/String;
    //   138: invokevirtual 106	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   141: ifeq +44 -> 185
    //   144: aload 4
    //   146: getfield 110	fm/qingting/qtradio/model/NotifyMessage:state	Lfm/qingting/qtradio/model/NotifyMessage$STATE;
    //   149: getstatic 115	fm/qingting/qtradio/model/NotifyMessage$STATE:FRESH	Lfm/qingting/qtradio/model/NotifyMessage$STATE;
    //   152: if_acmpne +33 -> 185
    //   155: aload 4
    //   157: getstatic 118	fm/qingting/qtradio/model/NotifyMessage$STATE:READ	Lfm/qingting/qtradio/model/NotifyMessage$STATE;
    //   160: putfield 110	fm/qingting/qtradio/model/NotifyMessage:state	Lfm/qingting/qtradio/model/NotifyMessage$STATE;
    //   163: aload_2
    //   164: iload 8
    //   166: aload 4
    //   168: invokevirtual 122	java/util/ArrayList:set	(ILjava/lang/Object;)Ljava/lang/Object;
    //   171: pop
    //   172: iload 8
    //   174: istore 9
    //   176: iload 9
    //   178: istore 8
    //   180: aload_2
    //   181: astore_3
    //   182: goto -151 -> 31
    //   185: iload 8
    //   187: iconst_1
    //   188: iadd
    //   189: istore 8
    //   191: goto -79 -> 112
    //   194: astore 4
    //   196: aload_3
    //   197: astore_2
    //   198: aload 4
    //   200: astore_3
    //   201: aload_3
    //   202: invokevirtual 125	java/io/StreamCorruptedException:printStackTrace	()V
    //   205: goto -25 -> 180
    //   208: astore 4
    //   210: aload_3
    //   211: astore_2
    //   212: aload 4
    //   214: astore_3
    //   215: aload_3
    //   216: invokevirtual 126	java/io/IOException:printStackTrace	()V
    //   219: goto -39 -> 180
    //   222: astore 4
    //   224: aload_3
    //   225: astore_2
    //   226: aload 4
    //   228: astore_3
    //   229: aload_3
    //   230: invokevirtual 127	java/lang/ClassNotFoundException:printStackTrace	()V
    //   233: goto -53 -> 180
    //   236: aload 5
    //   238: ldc 129
    //   240: iconst_1
    //   241: anewarray 4	java/lang/Object
    //   244: dup
    //   245: iconst_0
    //   246: ldc 131
    //   248: aastore
    //   249: invokevirtual 135	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   252: new 137	java/io/ByteArrayOutputStream
    //   255: dup
    //   256: invokespecial 138	java/io/ByteArrayOutputStream:<init>	()V
    //   259: astore_1
    //   260: new 140	java/io/ObjectOutputStream
    //   263: dup
    //   264: aload_1
    //   265: invokespecial 143	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   268: astore_2
    //   269: aload_2
    //   270: aload_3
    //   271: invokevirtual 147	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   274: aload_2
    //   275: invokevirtual 148	java/io/ObjectOutputStream:close	()V
    //   278: aload_1
    //   279: invokevirtual 149	java/io/ByteArrayOutputStream:close	()V
    //   282: aload 5
    //   284: ldc 151
    //   286: iconst_2
    //   287: anewarray 4	java/lang/Object
    //   290: dup
    //   291: iconst_0
    //   292: ldc 131
    //   294: aastore
    //   295: dup
    //   296: iconst_1
    //   297: aload_1
    //   298: invokevirtual 155	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   301: aastore
    //   302: invokevirtual 135	android/database/sqlite/SQLiteDatabase:execSQL	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   305: aload 6
    //   307: invokeinterface 156 1 0
    //   312: return
    //   313: astore_2
    //   314: aload_2
    //   315: invokevirtual 126	java/io/IOException:printStackTrace	()V
    //   318: goto -36 -> 282
    //   321: astore_3
    //   322: goto -93 -> 229
    //   325: astore_3
    //   326: goto -97 -> 229
    //   329: astore_3
    //   330: goto -115 -> 215
    //   333: astore_3
    //   334: goto -119 -> 215
    //   337: astore_3
    //   338: goto -137 -> 201
    //   341: astore_3
    //   342: goto -141 -> 201
    //   345: iload 8
    //   347: istore 9
    //   349: goto -173 -> 176
    //
    // Exception table:
    //   from	to	target	type
    //   66	86	194	java/io/StreamCorruptedException
    //   66	86	208	java/io/IOException
    //   66	86	222	java/lang/ClassNotFoundException
    //   260	282	313	java/io/IOException
    //   86	96	321	java/lang/ClassNotFoundException
    //   100	112	321	java/lang/ClassNotFoundException
    //   112	172	325	java/lang/ClassNotFoundException
    //   86	96	329	java/io/IOException
    //   100	112	329	java/io/IOException
    //   112	172	333	java/io/IOException
    //   86	96	337	java/io/StreamCorruptedException
    //   100	112	337	java/io/StreamCorruptedException
    //   112	172	341	java/io/StreamCorruptedException
  }

  private DataToken doChangeNotifyCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    changeNotifyState((String)paramDataCommand.getParam().get("content"));
    localDataToken.setData(new Result(true, null));
    return localDataToken;
  }

  private DataToken doGetActivitiesCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    paramDataCommand = getActivities();
    if (paramDataCommand == null)
    {
      localDataToken.setData(new Result(false, null));
      return localDataToken;
    }
    localDataToken.setData(new Result(true, paramDataCommand, null, null));
    return localDataToken;
  }

  private DataToken doGetNotifyCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    paramDataCommand = getNotify();
    if (paramDataCommand == null)
    {
      localDataToken.setData(new Result(false, null));
      return localDataToken;
    }
    localDataToken.setData(new Result(true, paramDataCommand, null, null));
    return localDataToken;
  }

  private DataToken doUpdateActivitiesCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    updateActivities((List)paramDataCommand.getParam().get("activities"));
    localDataToken.setData(new Result(true, null));
    return localDataToken;
  }

  private DataToken doUpdateNotifyCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    updateNotify("NotifyList", (List)paramDataCommand.getParam().get("notifylist"));
    localDataToken.setData(new Result(true, null));
    return localDataToken;
  }

  // ERROR //
  private fm.qingting.framework.data.ListData getActivities()
  {
    // Byte code:
    //   0: new 23	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 24	java/util/ArrayList:<init>	()V
    //   7: astore_3
    //   8: invokestatic 30	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 217
    //   13: invokevirtual 220	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc 222
    //   18: aconst_null
    //   19: invokevirtual 44	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   22: astore 4
    //   24: aload 4
    //   26: invokeinterface 50 1 0
    //   31: ifeq +96 -> 127
    //   34: new 52	java/io/ByteArrayInputStream
    //   37: dup
    //   38: aload 4
    //   40: aload 4
    //   42: ldc 224
    //   44: invokeinterface 58 2 0
    //   49: invokeinterface 62 2 0
    //   54: invokespecial 65	java/io/ByteArrayInputStream:<init>	([B)V
    //   57: astore_2
    //   58: new 67	java/io/ObjectInputStream
    //   61: dup
    //   62: aload_2
    //   63: invokespecial 70	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   66: astore 5
    //   68: aload 5
    //   70: invokevirtual 74	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   73: checkcast 226	fm/qingting/qtradio/model/ActivityItem
    //   76: astore_1
    //   77: aload 5
    //   79: invokevirtual 77	java/io/ObjectInputStream:close	()V
    //   82: aload_2
    //   83: invokevirtual 78	java/io/ByteArrayInputStream:close	()V
    //   86: aload_3
    //   87: aload_1
    //   88: invokeinterface 230 2 0
    //   93: pop
    //   94: goto -70 -> 24
    //   97: astore_2
    //   98: aconst_null
    //   99: astore_1
    //   100: aload_2
    //   101: invokevirtual 125	java/io/StreamCorruptedException:printStackTrace	()V
    //   104: goto -18 -> 86
    //   107: astore_2
    //   108: aconst_null
    //   109: astore_1
    //   110: aload_2
    //   111: invokevirtual 126	java/io/IOException:printStackTrace	()V
    //   114: goto -28 -> 86
    //   117: astore_2
    //   118: aconst_null
    //   119: astore_1
    //   120: aload_2
    //   121: invokevirtual 127	java/lang/ClassNotFoundException:printStackTrace	()V
    //   124: goto -38 -> 86
    //   127: aload 4
    //   129: invokeinterface 156 1 0
    //   134: aload_3
    //   135: invokeinterface 231 1 0
    //   140: ifle +13 -> 153
    //   143: new 233	fm/qingting/framework/data/ListData
    //   146: dup
    //   147: aload_3
    //   148: aconst_null
    //   149: invokespecial 236	fm/qingting/framework/data/ListData:<init>	(Ljava/util/List;Lfm/qingting/framework/data/Navigation;)V
    //   152: areturn
    //   153: aconst_null
    //   154: areturn
    //   155: astore_2
    //   156: goto -36 -> 120
    //   159: astore_2
    //   160: goto -50 -> 110
    //   163: astore_2
    //   164: goto -64 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   58	77	97	java/io/StreamCorruptedException
    //   58	77	107	java/io/IOException
    //   58	77	117	java/lang/ClassNotFoundException
    //   77	86	155	java/lang/ClassNotFoundException
    //   77	86	159	java/io/IOException
    //   77	86	163	java/io/StreamCorruptedException
  }

  public static NotifyDS getInstance()
  {
    if (instance == null)
      instance = new NotifyDS();
    return instance;
  }

  // ERROR //
  private fm.qingting.framework.data.ListData getNotify()
  {
    // Byte code:
    //   0: new 23	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 24	java/util/ArrayList:<init>	()V
    //   7: astore_1
    //   8: invokestatic 30	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   11: ldc 32
    //   13: invokevirtual 220	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   16: ldc 38
    //   18: aconst_null
    //   19: invokevirtual 44	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   22: astore 4
    //   24: aload 4
    //   26: invokeinterface 50 1 0
    //   31: ifeq +84 -> 115
    //   34: new 52	java/io/ByteArrayInputStream
    //   37: dup
    //   38: aload 4
    //   40: aload 4
    //   42: ldc 54
    //   44: invokeinterface 58 2 0
    //   49: invokeinterface 62 2 0
    //   54: invokespecial 65	java/io/ByteArrayInputStream:<init>	([B)V
    //   57: astore_3
    //   58: new 67	java/io/ObjectInputStream
    //   61: dup
    //   62: aload_3
    //   63: invokespecial 70	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   66: astore 5
    //   68: aload 5
    //   70: invokevirtual 74	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   73: checkcast 23	java/util/ArrayList
    //   76: astore_2
    //   77: aload 5
    //   79: invokevirtual 77	java/io/ObjectInputStream:close	()V
    //   82: aload_3
    //   83: invokevirtual 78	java/io/ByteArrayInputStream:close	()V
    //   86: aload_2
    //   87: astore_1
    //   88: goto -64 -> 24
    //   91: astore_2
    //   92: aload_2
    //   93: invokevirtual 125	java/io/StreamCorruptedException:printStackTrace	()V
    //   96: goto -8 -> 88
    //   99: astore_2
    //   100: aload_2
    //   101: invokevirtual 126	java/io/IOException:printStackTrace	()V
    //   104: goto -16 -> 88
    //   107: astore_2
    //   108: aload_2
    //   109: invokevirtual 127	java/lang/ClassNotFoundException:printStackTrace	()V
    //   112: goto -24 -> 88
    //   115: aload 4
    //   117: invokeinterface 156 1 0
    //   122: aload_1
    //   123: invokevirtual 82	java/util/ArrayList:size	()I
    //   126: ifle +13 -> 139
    //   129: new 233	fm/qingting/framework/data/ListData
    //   132: dup
    //   133: aload_1
    //   134: aconst_null
    //   135: invokespecial 236	fm/qingting/framework/data/ListData:<init>	(Ljava/util/List;Lfm/qingting/framework/data/Navigation;)V
    //   138: areturn
    //   139: aconst_null
    //   140: areturn
    //   141: astore_3
    //   142: aload_2
    //   143: astore_1
    //   144: aload_3
    //   145: astore_2
    //   146: goto -38 -> 108
    //   149: astore_3
    //   150: aload_2
    //   151: astore_1
    //   152: aload_3
    //   153: astore_2
    //   154: goto -54 -> 100
    //   157: astore_3
    //   158: aload_2
    //   159: astore_1
    //   160: aload_3
    //   161: astore_2
    //   162: goto -70 -> 92
    //
    // Exception table:
    //   from	to	target	type
    //   58	77	91	java/io/StreamCorruptedException
    //   58	77	99	java/io/IOException
    //   58	77	107	java/lang/ClassNotFoundException
    //   77	86	141	java/lang/ClassNotFoundException
    //   77	86	149	java/io/IOException
    //   77	86	157	java/io/StreamCorruptedException
  }

  private void updateActivities(List<ActivityItem> paramList)
  {
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("activity");
    localSQLiteDatabase.execSQL("delete from activitylist");
    paramList = paramList.iterator();
    while (true)
      if (paramList.hasNext())
      {
        ActivityItem localActivityItem = (ActivityItem)paramList.next();
        Object localObject = new ByteArrayOutputStream();
        try
        {
          ObjectOutputStream localObjectOutputStream = new ObjectOutputStream((OutputStream)localObject);
          localObjectOutputStream.writeObject(localActivityItem);
          localObjectOutputStream.close();
          ((ByteArrayOutputStream)localObject).close();
          localObject = ((ByteArrayOutputStream)localObject).toByteArray();
          localSQLiteDatabase.execSQL("insert into activitylist(title,item)values(?,?)", new Object[] { localActivityItem.title, localObject });
        }
        catch (IOException localIOException)
        {
          while (true)
            localIOException.printStackTrace();
        }
      }
  }

  private void updateNotify(String paramString, List<NotifyMessage> paramList)
  {
    SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("notification");
    Cursor localCursor = localSQLiteDatabase.rawQuery("select listname from notification", null);
    do
      if (!localCursor.moveToNext())
        break;
    while (!paramString.equalsIgnoreCase(localCursor.getString(localCursor.getColumnIndex("listname"))));
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
        localSQLiteDatabase.execSQL("delete from notification where listname =?", new Object[] { paramString });
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      try
      {
        ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
        localObjectOutputStream.writeObject(paramList);
        localObjectOutputStream.close();
        localByteArrayOutputStream.close();
        localSQLiteDatabase.execSQL("insert into notification(listname,message)values(?,?)", new Object[] { paramString, localByteArrayOutputStream.toByteArray() });
        localCursor.close();
        return;
      }
      catch (IOException paramList)
      {
        while (true)
          paramList.printStackTrace();
      }
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "Notify";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("getNotify"))
      return doGetNotifyCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updateNotify"))
      return doUpdateNotifyCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("changeNotifyState"))
      return doChangeNotifyCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getActivities"))
      return doGetActivitiesCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updateActivities"))
      return doUpdateActivitiesCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.NotifyDS
 * JD-Core Version:    0.6.2
 */