package fm.qingting.qtradio.data;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Pair;
import fm.qingting.framework.data.DataCommand;
import fm.qingting.framework.data.DataToken;
import fm.qingting.framework.data.IDataParser;
import fm.qingting.framework.data.IDataRecvHandler;
import fm.qingting.framework.data.IDataSource;
import fm.qingting.framework.data.IDataToken;
import fm.qingting.framework.data.Result;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyPodcasterDS
  implements IDataSource
{
  private static MyPodcasterDS instance;

  private boolean acquireFollowInfo(DataCommand paramDataCommand)
  {
    try
    {
      paramDataCommand = paramDataCommand.getParam();
      int i = ((Integer)paramDataCommand.get("pid")).intValue();
      paramDataCommand = (String)paramDataCommand.get("ukey");
      paramDataCommand = DBManager.getInstance().getReadableDB("podcasterFollow").rawQuery("select * from myPodcaster where podcasterId = ? AND userKey = ?", new String[] { String.valueOf(i), paramDataCommand });
      if (paramDataCommand.moveToNext());
      for (boolean bool = true; ; bool = false)
      {
        paramDataCommand.close();
        return bool;
      }
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private List<Pair> acquireFollowInfoAll(DataCommand paramDataCommand)
  {
    Object localObject = (String)paramDataCommand.getParam().get("ukey");
    paramDataCommand = new ArrayList();
    try
    {
      localObject = DBManager.getInstance().getReadableDB("podcasterFollow").rawQuery("select podcasterId,updateTime from myPodcaster where userKey=? order by followTime DESC", new String[] { localObject });
      int i = ((Cursor)localObject).getColumnIndex("podcasterId");
      int j = ((Cursor)localObject).getColumnIndex("updateTime");
      while (((Cursor)localObject).moveToNext())
        paramDataCommand.add(new Pair(Integer.valueOf(((Cursor)localObject).getInt(i)), Long.valueOf(((Cursor)localObject).getLong(j))));
      ((Cursor)localObject).close();
      return paramDataCommand;
    }
    catch (Exception localException)
    {
    }
    return paramDataCommand;
  }

  private boolean deleteFollowInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int i = ((Integer)paramDataCommand.get("pid")).intValue();
    paramDataCommand = (String)paramDataCommand.get("ukey");
    try
    {
      DBManager.getInstance().getWritableDB("podcasterFollow").execSQL("delete from myPodcaster where podcasterId=? AND userKey =?", new Object[] { Integer.valueOf(i), paramDataCommand });
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
    localDataToken.setData(new Result(true, Boolean.valueOf(acquireFollowInfo(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doAcquireCommandAll(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, acquireFollowInfoAll(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleltCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteFollowInfo(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doGetLatestProgramId(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, getLatestProgramId(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateFollowInfo(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doUpdateLatestProgramId(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateLatestProgramId(paramDataCommand))));
    return localDataToken;
  }

  public static MyPodcasterDS getInstance()
  {
    if (instance == null)
      instance = new MyPodcasterDS();
    return instance;
  }

  private Integer getLatestProgramId(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int i = ((Integer)paramDataCommand.get("pid")).intValue();
    paramDataCommand = (String)paramDataCommand.get("ukey");
    try
    {
      paramDataCommand = DBManager.getInstance().getReadableDB("podcasterFollow").rawQuery("select lpId from myPodcaster where podcasterId=? AND userKey =?", new String[] { String.valueOf(i), paramDataCommand });
      if (paramDataCommand.moveToFirst())
        i = paramDataCommand.getInt(0);
    }
    catch (Exception paramDataCommand)
    {
      while (true)
      {
        try
        {
          paramDataCommand.close();
          return Integer.valueOf(i);
          paramDataCommand = paramDataCommand;
          i = 0;
          continue;
        }
        catch (Exception paramDataCommand)
        {
          continue;
        }
        i = 0;
      }
    }
  }

  private boolean updateFollowInfo(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int i = ((Integer)paramDataCommand.get("pid")).intValue();
    String str = (String)paramDataCommand.get("ukey");
    long l1 = ((Long)paramDataCommand.get("uptime")).longValue();
    long l2 = System.currentTimeMillis();
    try
    {
      DBManager.getInstance().getWritableDB("podcasterFollow").execSQL("insert into myPodcaster (podcasterId,followTime,userKey,updateTime) values(?,?,?,?)", new Object[] { Integer.valueOf(i), Long.valueOf(l2), str, Long.valueOf(l1) });
      return true;
    }
    catch (Exception paramDataCommand)
    {
    }
    return false;
  }

  private boolean updateLatestProgramId(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    int i = ((Integer)paramDataCommand.get("pid")).intValue();
    String str = (String)paramDataCommand.get("ukey");
    long l = ((Long)paramDataCommand.get("uptime")).longValue();
    try
    {
      DBManager.getInstance().getWritableDB("podcasterFollow").execSQL("update myPodcaster set updateTime=? where podcasterId=? AND userKey =?", new Object[] { Long.valueOf(l), Integer.valueOf(i), str });
      return true;
    }
    catch (Exception paramDataCommand)
    {
      paramDataCommand.printStackTrace();
    }
    return false;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "MyPodcasterDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_PODCASTER_FOLLOW_INFO"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_ALL_PODCASTER_FOLLOW_INFO"))
      return doAcquireCommandAll(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("INSERTDB_PODCASTER_FOLLOW_INFO"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("DELETEDB_PODCASTER_FOLLOW_INFO"))
      return doDeleltCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_PODCASTER_LATEST_PROGRAME"))
      return doGetLatestProgramId(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_PODCASTER_LATEST_PROGRAME"))
      return doUpdateLatestProgramId(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.MyPodcasterDS
 * JD-Core Version:    0.6.2
 */