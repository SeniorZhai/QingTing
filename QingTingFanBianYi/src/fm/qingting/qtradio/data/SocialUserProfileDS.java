package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.google.gson.Gson;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.social.UserProfile;
import java.util.List;
import java.util.Map;

public class SocialUserProfileDS
  implements IDataSource
{
  private static SocialUserProfileDS instance;

  private UserProfile acquireUserInfo(DataCommand paramDataCommand)
  {
    try
    {
      paramDataCommand = (String)paramDataCommand.getParam().get("userKey");
      if (paramDataCommand != null)
      {
        if (paramDataCommand.equalsIgnoreCase(""))
          return null;
        paramDataCommand = "select * from socialUserProfile where userKey = '" + paramDataCommand + "'";
        Cursor localCursor = DBManager.getInstance().getReadableDB("socialuser").rawQuery(paramDataCommand, null);
        paramDataCommand = new Gson();
        String str;
        if (localCursor.moveToNext())
          str = localCursor.getString(localCursor.getColumnIndex("userKey"));
        while (true)
        {
          try
          {
            paramDataCommand = (UserProfile)paramDataCommand.fromJson(str, UserProfile.class);
            localCursor.close();
            return paramDataCommand;
          }
          catch (Exception paramDataCommand)
          {
            paramDataCommand = null;
            continue;
          }
          paramDataCommand = null;
        }
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private boolean deleteUserInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("userKey");
    if (paramDataCommand == null)
      paramDataCommand = "delete from socialUserProfile";
    try
    {
      while (true)
      {
        DBManager.getInstance().getWritableDB("socialuser").execSQL(paramDataCommand);
        return true;
        paramDataCommand = "delete from socialUserProfile where userKey = '" + paramDataCommand + "'";
      }
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
    localDataToken.setData(new Result(true, acquireUserInfo(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteUserInfo(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertUserInfo(paramDataCommand))));
    return localDataToken;
  }

  public static SocialUserProfileDS getInstance()
  {
    if (instance == null)
      instance = new SocialUserProfileDS();
    return instance;
  }

  private boolean insertUserInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = (List)paramDataCommand.getParam().get("users");
    if ((paramDataCommand == null) || (paramDataCommand.size() == 0))
      return false;
    try
    {
      SQLiteDatabase localSQLiteDatabase = DBManager.getInstance().getWritableDB("userinfos");
      localSQLiteDatabase.beginTransaction();
      Gson localGson = new Gson();
      int i = 0;
      while (i < paramDataCommand.size())
      {
        String str = localGson.toJson(((UserProfile)paramDataCommand.get(i)).getUserInfo());
        localSQLiteDatabase.execSQL("insert into socialUserProfile(userKey,userProfile) values(?,?)", new Object[] { ((UserProfile)paramDataCommand.get(i)).getUserKey(), str });
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
    return "SocialUserProfileDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_social_user_info"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_social_user_info"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_social_user_info"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.SocialUserProfileDS
 * JD-Core Version:    0.6.2
 */