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
import fm.qingting.qtradio.model.H5Bean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class H5BeanDS
  implements IDataSource
{
  private static H5BeanDS instance;

  private Map<Integer, H5Bean> acquireH5Bean(DataCommand paramDataCommand)
  {
    int i = ((Integer)paramDataCommand.getParam().get("type")).intValue();
    try
    {
      paramDataCommand = new HashMap();
      Object localObject1 = "select * from h5Beans where type = " + "'";
      localObject1 = (String)localObject1 + i;
      localObject1 = (String)localObject1 + "'";
      localObject1 = DBManager.getInstance().getReadableDB("h5beans").rawQuery((String)localObject1, null);
      Gson localGson = new Gson();
      while (((Cursor)localObject1).moveToNext())
      {
        Object localObject2 = ((Cursor)localObject1).getString(((Cursor)localObject1).getColumnIndex("bean"));
        try
        {
          localObject2 = (H5Bean)localGson.fromJson((String)localObject2, H5Bean.class);
          if (localObject2 != null)
            paramDataCommand.put(Integer.valueOf(((H5Bean)localObject2).id), localObject2);
        }
        catch (Exception localException)
        {
        }
      }
      ((Cursor)localObject1).close();
      return paramDataCommand;
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
    localDataToken.setData(new Result(true, acquireH5Bean(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doUpdateCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(updateH5Bean(paramDataCommand))));
    return localDataToken;
  }

  public static H5BeanDS getInstance()
  {
    if (instance == null)
      instance = new H5BeanDS();
    return instance;
  }

  private boolean updateH5Bean(DataCommand paramDataCommand)
  {
    paramDataCommand = paramDataCommand.getParam();
    Object localObject = (Map)paramDataCommand.get("bean");
    int i = ((Integer)paramDataCommand.get("type")).intValue();
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("h5beans");
      paramDataCommand.beginTransaction();
      paramDataCommand.execSQL("delete from h5Beans where type = '" + i + "'");
      if (localObject != null)
      {
        localObject = ((Map)localObject).entrySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          H5Bean localH5Bean = (H5Bean)((Map.Entry)((Iterator)localObject).next()).getValue();
          String str = new Gson().toJson(localH5Bean);
          paramDataCommand.execSQL("insert into h5Beans(id,type,bean) values(?, ?, ?)", new Object[] { Integer.valueOf(localH5Bean.id), Integer.valueOf(localH5Bean.type), str });
        }
      }
      paramDataCommand.setTransactionSuccessful();
      paramDataCommand.endTransaction();
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
    return "H5BeanDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("GETDB_H5_BEAN"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("UPDATEDB_H5_BEAN"))
      return doUpdateCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.H5BeanDS
 * JD-Core Version:    0.6.2
 */