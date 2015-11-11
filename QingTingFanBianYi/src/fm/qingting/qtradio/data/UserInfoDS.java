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
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import java.util.Map;

public class UserInfoDS
  implements IDataSource
{
  private static UserInfoDS instance;

  private UserInfo acquireUserInfo(DataCommand paramDataCommand)
  {
    while (true)
    {
      try
      {
        paramDataCommand = (String)paramDataCommand.getParam().get("site");
        if ((paramDataCommand == null) || (paramDataCommand.equalsIgnoreCase("")))
          break;
        paramDataCommand = "select * from userInfos where sns_site = '" + paramDataCommand + "'";
        Cursor localCursor = DBManager.getInstance().getReadableDB("userinfos").rawQuery(paramDataCommand, null);
        paramDataCommand = new UserInfo();
        if (localCursor.moveToNext())
        {
          int i = localCursor.getColumnIndex("sns_id");
          int j = localCursor.getColumnIndex("sns_site");
          int k = localCursor.getColumnIndex("sns_name");
          int m = localCursor.getColumnIndex("sns_account");
          int n = localCursor.getColumnIndex("sns_avatar");
          paramDataCommand.snsInfo.sns_id = localCursor.getString(i);
          paramDataCommand.snsInfo.sns_site = localCursor.getString(j);
          paramDataCommand.snsInfo.sns_name = localCursor.getString(k);
          paramDataCommand.snsInfo.sns_account = localCursor.getString(m);
          paramDataCommand.snsInfo.sns_avatar = localCursor.getString(n);
          localCursor.close();
          return paramDataCommand;
        }
      }
      catch (Exception paramDataCommand)
      {
        return null;
      }
      paramDataCommand = null;
    }
    return null;
  }

  private boolean deleteUserInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = (String)paramDataCommand.getParam().get("site");
    if (paramDataCommand == null)
      paramDataCommand = "delete from userInfos";
    try
    {
      while (true)
      {
        DBManager.getInstance().getWritableDB("userinfos").execSQL(paramDataCommand);
        return true;
        paramDataCommand = "delete from userInfos where sns_site = '" + paramDataCommand + "'";
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

  public static UserInfoDS getInstance()
  {
    if (instance == null)
      instance = new UserInfoDS();
    return instance;
  }

  private boolean insertUserInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = (UserInfo)paramDataCommand.getParam().get("userInfo");
    if (paramDataCommand == null)
      return false;
    try
    {
      DBManager.getInstance().getWritableDB("userinfos").execSQL("insert into userInfos(sns_id,sns_site,sns_name,sns_account,sns_avatar) values(?,?,?,?,?)", new Object[] { paramDataCommand.snsInfo.sns_id, paramDataCommand.snsInfo.sns_site, paramDataCommand.snsInfo.sns_name, paramDataCommand.snsInfo.sns_account, paramDataCommand.snsInfo.sns_avatar });
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
    return "UserInfoDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_user_info"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_user_info"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_user_info"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.UserInfoDS
 * JD-Core Version:    0.6.2
 */