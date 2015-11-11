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
import fm.qingting.qtradio.room.UserInfo;
import java.util.Map;

public class PodcasterDS
  implements IDataSource
{
  private static PodcasterDS instance;

  private UserInfo acquireUserInfo(DataCommand paramDataCommand)
  {
    try
    {
      int i = ((Integer)paramDataCommand.getParam().get("pid")).intValue();
      paramDataCommand = "select * from podcastersInfo where podcasterId = '" + i + "'";
      Cursor localCursor = DBManager.getInstance().getReadableDB("podcastersInfo").rawQuery(paramDataCommand, null);
      if (localCursor.moveToNext())
      {
        paramDataCommand = new Gson();
        localCursor.getColumnIndex("podcasterId");
      }
      for (paramDataCommand = (UserInfo)paramDataCommand.fromJson(localCursor.getString(localCursor.getColumnIndex("data")), UserInfo.class); ; paramDataCommand = null)
      {
        localCursor.close();
        return paramDataCommand;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return null;
  }

  private DataToken doAcquireCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireUserInfo(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateUserInfo(paramDataCommand))));
    return localDataToken;
  }

  public static PodcasterDS getInstance()
  {
    if (instance == null)
      instance = new PodcasterDS();
    return instance;
  }

  private boolean updateUserInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = (UserInfo)paramDataCommand.getParam().get("userInfo");
    if ((paramDataCommand == null) || (paramDataCommand.snsInfo == null))
      return false;
    try
    {
      Gson localGson = new Gson();
      DBManager.getInstance().getWritableDB("podcastersInfo").execSQL("insert or replace into podcastersInfo (podcasterId, data)  values(?,?)", new Object[] { Integer.valueOf(paramDataCommand.podcasterId), localGson.toJson(paramDataCommand) });
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
    return "PodcasterDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_PODCASTER_INFO"))
      return doUpdateCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_PODCASTER_INFO"))
      return doAcquireCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.PodcasterDS
 * JD-Core Version:    0.6.2
 */