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
import java.util.List;
import java.util.Map;

public class IMUserInfoDS
  implements IDataSource
{
  private static IMUserInfoDS instance;

  private UserInfo acquireIMUserInfo(DataCommand paramDataCommand)
  {
    while (true)
    {
      try
      {
        paramDataCommand = (String)paramDataCommand.getParam().get("id");
        if (paramDataCommand == null)
          return null;
        paramDataCommand = "select * from imUserInfo where id = '" + paramDataCommand + "'";
        Cursor localCursor = DBManager.getInstance().getReadableDB("imUserInfo").rawQuery(paramDataCommand, null);
        if (localCursor.moveToNext())
        {
          paramDataCommand = new UserInfo();
          int i = localCursor.getColumnIndex("avatar");
          int j = localCursor.getColumnIndex("id");
          int k = localCursor.getColumnIndex("name");
          int m = localCursor.getColumnIndex("gender");
          paramDataCommand.userId = localCursor.getString(j);
          paramDataCommand.userKey = paramDataCommand.userId;
          paramDataCommand.snsInfo.sns_name = localCursor.getString(k);
          paramDataCommand.snsInfo.sns_avatar = localCursor.getString(i);
          paramDataCommand.snsInfo.sns_gender = localCursor.getString(m);
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
  }

  private DataToken doAcquireUserCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireIMUserInfo(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doUpdateUserCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateIMUser(paramDataCommand))));
    return localDataToken;
  }

  public static IMUserInfoDS getInstance()
  {
    if (instance == null)
      instance = new IMUserInfoDS();
    return instance;
  }

  private boolean updateIMUser(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    Object localObject2 = (String)paramDataCommand.get("type");
    if (localObject2 == null)
      return false;
    Object localObject1 = null;
    if (((String)localObject2).equalsIgnoreCase("lu"))
      localObject1 = (List)paramDataCommand.get("lu");
    label343: for (paramDataCommand = null; ; paramDataCommand = null)
      try
      {
        localObject2 = DBManager.getInstance().getWritableDB("imUserInfo");
        if (paramDataCommand != null)
        {
          localObject1 = "delete from imUserInfo where id = '" + paramDataCommand.userKey + "'";
          ((SQLiteDatabase)localObject2).beginTransaction();
          ((SQLiteDatabase)localObject2).execSQL((String)localObject1);
          ((SQLiteDatabase)localObject2).execSQL("insert into imUserInfo(id,avatar,name,gender) values(?,?,?,?)", new Object[] { paramDataCommand.userKey, paramDataCommand.snsInfo.sns_avatar, paramDataCommand.snsInfo.sns_name, paramDataCommand.snsInfo.sns_gender });
        }
        while (true)
        {
          ((SQLiteDatabase)localObject2).setTransactionSuccessful();
          ((SQLiteDatabase)localObject2).endTransaction();
          return true;
          if (!((String)localObject2).equalsIgnoreCase("u"))
            break label343;
          paramDataCommand = (UserInfo)paramDataCommand.get("u");
          break;
          if (localObject1 != null)
          {
            int i = 0;
            while (i < ((List)localObject1).size())
            {
              paramDataCommand = "delete from imUserInfo where id = '" + ((UserInfo)((List)localObject1).get(i)).userKey + "'";
              ((SQLiteDatabase)localObject2).beginTransaction();
              ((SQLiteDatabase)localObject2).execSQL(paramDataCommand);
              ((SQLiteDatabase)localObject2).execSQL("insert into imUserInfo(id,avatar,name,gender) values(?,?,?,?)", new Object[] { ((UserInfo)((List)localObject1).get(i)).userKey, ((UserInfo)((List)localObject1).get(i)).snsInfo.sns_avatar, ((UserInfo)((List)localObject1).get(i)).snsInfo.sns_name, ((UserInfo)((List)localObject1).get(i)).snsInfo.sns_gender });
              i += 1;
            }
          }
        }
      }
      catch (Exception paramDataCommand)
      {
        return false;
      }
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "IMUserInfoDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_im_user_info"))
      return doAcquireUserCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("updatedb_im_user_info"))
      return doUpdateUserCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.IMUserInfoDS
 * JD-Core Version:    0.6.2
 */