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
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.room.UserInfo;
import java.util.List;
import java.util.Map;

public class IMContactsDS
  implements IDataSource
{
  private static IMContactsDS instance;

  // ERROR //
  private List<GroupInfo> acquireGroupIMConstacts(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   4: ldc 25
    //   6: invokeinterface 31 2 0
    //   11: checkcast 33	java/lang/String
    //   14: astore_1
    //   15: aload_1
    //   16: ifnonnull +5 -> 21
    //   19: aconst_null
    //   20: areturn
    //   21: new 35	java/util/ArrayList
    //   24: dup
    //   25: invokespecial 36	java/util/ArrayList:<init>	()V
    //   28: astore_3
    //   29: new 38	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   36: ldc 41
    //   38: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc 47
    //   47: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore_1
    //   54: invokestatic 57	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   57: ldc 59
    //   59: invokevirtual 63	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   62: aload_1
    //   63: aconst_null
    //   64: invokevirtual 69	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore 4
    //   69: new 71	com/google/gson/Gson
    //   72: dup
    //   73: invokespecial 72	com/google/gson/Gson:<init>	()V
    //   76: astore 5
    //   78: aconst_null
    //   79: astore_1
    //   80: aload 4
    //   82: invokeinterface 78 1 0
    //   87: ifeq +49 -> 136
    //   90: aload 4
    //   92: aload 4
    //   94: ldc 80
    //   96: invokeinterface 84 2 0
    //   101: invokeinterface 88 2 0
    //   106: astore_2
    //   107: aload 5
    //   109: aload_2
    //   110: ldc 90
    //   112: invokevirtual 94	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   115: checkcast 90	fm/qingting/qtradio/im/info/GroupInfo
    //   118: astore_2
    //   119: aload_2
    //   120: astore_1
    //   121: aload_1
    //   122: ifnull +26 -> 148
    //   125: aload_3
    //   126: aload_1
    //   127: invokeinterface 100 2 0
    //   132: pop
    //   133: goto +15 -> 148
    //   136: aload 4
    //   138: invokeinterface 103 1 0
    //   143: aload_3
    //   144: areturn
    //   145: astore_1
    //   146: aconst_null
    //   147: areturn
    //   148: goto -68 -> 80
    //   151: astore_2
    //   152: goto -31 -> 121
    //
    // Exception table:
    //   from	to	target	type
    //   0	15	145	java/lang/Exception
    //   21	78	145	java/lang/Exception
    //   80	107	145	java/lang/Exception
    //   125	133	145	java/lang/Exception
    //   136	143	145	java/lang/Exception
    //   107	119	151	java/lang/Exception
  }

  // ERROR //
  private List<UserInfo> acquireUserIMConstacts(DataCommand paramDataCommand)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 23	fm/qingting/framework/data/DataCommand:getParam	()Ljava/util/Map;
    //   4: ldc 25
    //   6: invokeinterface 31 2 0
    //   11: checkcast 33	java/lang/String
    //   14: astore_1
    //   15: aload_1
    //   16: ifnonnull +5 -> 21
    //   19: aconst_null
    //   20: areturn
    //   21: new 35	java/util/ArrayList
    //   24: dup
    //   25: invokespecial 36	java/util/ArrayList:<init>	()V
    //   28: astore_3
    //   29: new 38	java/lang/StringBuilder
    //   32: dup
    //   33: invokespecial 39	java/lang/StringBuilder:<init>	()V
    //   36: ldc 41
    //   38: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: aload_1
    //   42: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   45: ldc 47
    //   47: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual 51	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: astore_1
    //   54: invokestatic 57	fm/qingting/qtradio/data/DBManager:getInstance	()Lfm/qingting/qtradio/data/DBManager;
    //   57: ldc 59
    //   59: invokevirtual 63	fm/qingting/qtradio/data/DBManager:getReadableDB	(Ljava/lang/String;)Landroid/database/sqlite/SQLiteDatabase;
    //   62: aload_1
    //   63: aconst_null
    //   64: invokevirtual 69	android/database/sqlite/SQLiteDatabase:rawQuery	(Ljava/lang/String;[Ljava/lang/String;)Landroid/database/Cursor;
    //   67: astore 4
    //   69: new 71	com/google/gson/Gson
    //   72: dup
    //   73: invokespecial 72	com/google/gson/Gson:<init>	()V
    //   76: astore 5
    //   78: aconst_null
    //   79: astore_1
    //   80: aload 4
    //   82: invokeinterface 78 1 0
    //   87: ifeq +94 -> 181
    //   90: aload 4
    //   92: ldc 80
    //   94: invokeinterface 84 2 0
    //   99: istore 7
    //   101: aload 4
    //   103: ldc 108
    //   105: invokeinterface 84 2 0
    //   110: istore 8
    //   112: aload 4
    //   114: iload 7
    //   116: invokeinterface 88 2 0
    //   121: astore_2
    //   122: aload 4
    //   124: iload 8
    //   126: invokeinterface 88 2 0
    //   131: astore 6
    //   133: aload 5
    //   135: aload_2
    //   136: ldc 110
    //   138: invokevirtual 94	com/google/gson/Gson:fromJson	(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;
    //   141: checkcast 110	fm/qingting/qtradio/room/SnsInfo
    //   144: astore_2
    //   145: aload_2
    //   146: astore_1
    //   147: aload_1
    //   148: ifnull +45 -> 193
    //   151: new 112	fm/qingting/qtradio/room/UserInfo
    //   154: dup
    //   155: invokespecial 113	fm/qingting/qtradio/room/UserInfo:<init>	()V
    //   158: astore_2
    //   159: aload_2
    //   160: aload 6
    //   162: putfield 117	fm/qingting/qtradio/room/UserInfo:userKey	Ljava/lang/String;
    //   165: aload_2
    //   166: aload_1
    //   167: putfield 121	fm/qingting/qtradio/room/UserInfo:snsInfo	Lfm/qingting/qtradio/room/SnsInfo;
    //   170: aload_3
    //   171: aload_2
    //   172: invokeinterface 100 2 0
    //   177: pop
    //   178: goto +15 -> 193
    //   181: aload 4
    //   183: invokeinterface 103 1 0
    //   188: aload_3
    //   189: areturn
    //   190: astore_1
    //   191: aconst_null
    //   192: areturn
    //   193: goto -113 -> 80
    //   196: astore_2
    //   197: goto -50 -> 147
    //
    // Exception table:
    //   from	to	target	type
    //   0	15	190	java/lang/Exception
    //   21	78	190	java/lang/Exception
    //   80	133	190	java/lang/Exception
    //   151	178	190	java/lang/Exception
    //   181	188	190	java/lang/Exception
    //   133	145	196	java/lang/Exception
  }

  private boolean deleteIMConstacts(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("imContacts").execSQL("delete from imContacts");
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private DataToken doAcquireGroupCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireGroupIMConstacts(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doAcquireUserCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireUserIMConstacts(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteIMConstacts(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertIMConstacts(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateIMConstacts(paramDataCommand))));
    return localDataToken;
  }

  public static IMContactsDS getInstance()
  {
    if (instance == null)
      instance = new IMContactsDS();
    return instance;
  }

  private boolean insertIMConstacts(DataCommand paramDataCommand)
  {
    UserInfo localUserInfo = null;
    paramDataCommand = paramDataCommand.getParam();
    String str = (String)paramDataCommand.get("type");
    if (str == null)
      return false;
    if (str.equalsIgnoreCase("ru"))
      localUserInfo = (UserInfo)paramDataCommand.get("ru");
    for (paramDataCommand = null; ; paramDataCommand = null)
      try
      {
        while (true)
        {
          SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imContacts");
          localSQLiteDatabase.beginTransaction();
          Gson localGson = new Gson();
          if (localUserInfo != null)
            localSQLiteDatabase.execSQL("insert into imContacts(type,info,key) values(?,?,?)", new Object[] { str, localGson.toJson(localUserInfo.snsInfo), localUserInfo.userKey });
          if (paramDataCommand != null)
            localSQLiteDatabase.execSQL("insert into imContacts(type,info,key) values(?,?,?)", new Object[] { str, localGson.toJson(paramDataCommand), paramDataCommand.groupId });
          localSQLiteDatabase.setTransactionSuccessful();
          localSQLiteDatabase.endTransaction();
          return true;
          if (str.equalsIgnoreCase("rg"))
          {
            paramDataCommand = (GroupInfo)paramDataCommand.get("rg");
          }
          else if (str.equalsIgnoreCase("wu"))
          {
            localUserInfo = (UserInfo)paramDataCommand.get("wu");
            paramDataCommand = null;
          }
          else
          {
            if (!str.equalsIgnoreCase("wg"))
              break;
            paramDataCommand = (GroupInfo)paramDataCommand.get("wg");
          }
        }
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
  }

  private boolean updateIMConstacts(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    String str1 = (String)paramDataCommand.get("type");
    if (str1 == null)
      return false;
    List localList;
    if (str1.equalsIgnoreCase("ru"))
    {
      localList = (List)paramDataCommand.get("ru");
      paramDataCommand = null;
    }
    while (true)
    {
      try
      {
        SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("imContacts");
        String str2 = "delete from imContacts where type = '" + str1 + "'";
        localSQLiteDatabase.beginTransaction();
        localSQLiteDatabase.execSQL(str2);
        int i;
        if (localList != null)
        {
          i = 0;
          if (i < localList.size())
          {
            localSQLiteDatabase.execSQL("insert into imContacts(type,info,key) values(?,?,?)", new Object[] { str1, new Gson().toJson(((UserInfo)localList.get(i)).snsInfo), ((UserInfo)localList.get(i)).userKey });
            i += 1;
            continue;
            if (str1.equalsIgnoreCase("rg"))
            {
              paramDataCommand = (List)paramDataCommand.get("rg");
              localList = null;
              continue;
            }
            if (str1.equalsIgnoreCase("wu"))
            {
              localList = (List)paramDataCommand.get("wu");
              paramDataCommand = null;
              continue;
            }
            if (!str1.equalsIgnoreCase("wg"))
              break label353;
            paramDataCommand = (List)paramDataCommand.get("wg");
            localList = null;
            continue;
          }
        }
        if (paramDataCommand != null)
        {
          i = 0;
          if (i < paramDataCommand.size())
          {
            localSQLiteDatabase.execSQL("insert into imContacts(type,info,key) values(?,?,?)", new Object[] { str1, new Gson().toJson(paramDataCommand.get(i)), ((GroupInfo)paramDataCommand.get(i)).groupId });
            i += 1;
            continue;
          }
        }
        localSQLiteDatabase.setTransactionSuccessful();
        localSQLiteDatabase.endTransaction();
        return true;
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
      label353: paramDataCommand = null;
      localList = null;
    }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "IMContactsDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_im_contacts"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_im_group_contacts"))
      return doAcquireGroupCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_im_user_contacts"))
      return doAcquireUserCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_im_contacts"))
      return doDeleteCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_im_contacts"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.IMContactsDS
 * JD-Core Version:    0.6.2
 */