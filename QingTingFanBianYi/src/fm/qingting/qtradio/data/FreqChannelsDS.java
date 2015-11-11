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
import fm.qingting.qtradio.model.FreqChannel;
import fm.qingting.qtradio.model.FreqChannelInfoNode;
import fm.qingting.qtradio.model.Node;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FreqChannelsDS
  implements IDataSource
{
  private static FreqChannelsDS instance;

  private List<FreqChannel> acquireFreqChannels(DataCommand paramDataCommand)
  {
    Object localObject = (String)paramDataCommand.getParam().get("city");
    paramDataCommand = new ArrayList();
    try
    {
      localObject = "select * from freqChannels where city = '" + (String)localObject + "'";
      localObject = DBManager.getInstance().getReadableDB("freqChannels").rawQuery((String)localObject, null);
      while (((Cursor)localObject).moveToNext())
      {
        int i = ((Cursor)localObject).getColumnIndex("channelid");
        int j = ((Cursor)localObject).getColumnIndex("channelname");
        int k = ((Cursor)localObject).getColumnIndex("freq");
        int m = ((Cursor)localObject).getColumnIndex("city");
        FreqChannel localFreqChannel = new FreqChannel();
        localFreqChannel.channelId = ((Cursor)localObject).getInt(i);
        localFreqChannel.channelName = ((Cursor)localObject).getString(j);
        localFreqChannel.channelFreq = ((Cursor)localObject).getString(k);
        localFreqChannel.city = ((Cursor)localObject).getString(m);
        paramDataCommand.add(localFreqChannel);
      }
      ((Cursor)localObject).close();
      return paramDataCommand;
    }
    catch (Exception localException)
    {
    }
    return paramDataCommand;
  }

  private boolean deleteFreqChannels(DataCommand paramDataCommand)
  {
    try
    {
      DBManager.getInstance().getWritableDB("freqChannels").execSQL("delete from freqChannels");
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
    localDataToken.setData(new Result(true, acquireFreqChannels(paramDataCommand)));
    return localDataToken;
  }

  private DataToken doDeleteCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(deleteFreqChannels(paramDataCommand))));
    return localDataToken;
  }

  private DataToken doInsertCommand(DataCommand paramDataCommand)
  {
    DataToken localDataToken = new DataToken();
    localDataToken.setDataInfo(paramDataCommand);
    localDataToken.setData(new Result(true, Boolean.valueOf(insertFreqChannels(paramDataCommand))));
    return localDataToken;
  }

  public static FreqChannelsDS getInstance()
  {
    if (instance == null)
      instance = new FreqChannelsDS();
    return instance;
  }

  private boolean insertFreqChannels(DataCommand paramDataCommand)
  {
    paramDataCommand = (Node)paramDataCommand.getParam().get("freqs");
    if (paramDataCommand == null)
      return false;
    Object localObject = (FreqChannelInfoNode)paramDataCommand;
    try
    {
      paramDataCommand = DBManager.getInstance().getWritableDB("freqChannels");
      localObject = ((FreqChannelInfoNode)localObject).mapFreqChannel.entrySet().iterator();
      while (((Iterator)localObject).hasNext())
      {
        List localList = (List)((Map.Entry)((Iterator)localObject).next()).getValue();
        int i = 0;
        while (i < localList.size())
        {
          paramDataCommand.execSQL("insert into freqChannels(channelid,channelname,city,freq) values(?,?,?,?)", new Object[] { Integer.valueOf(((FreqChannel)localList.get(i)).channelId), ((FreqChannel)localList.get(i)).channelName, ((FreqChannel)localList.get(i)).city, ((FreqChannel)localList.get(i)).channelFreq });
          i += 1;
        }
      }
    }
    catch (Exception paramDataCommand)
    {
      return false;
    }
    return true;
  }

  public void addParser(IDataParser paramIDataParser)
  {
  }

  public String dataSourceName()
  {
    return "FreqChannelsDS";
  }

  public IDataToken doCommand(DataCommand paramDataCommand, IDataRecvHandler paramIDataRecvHandler)
  {
    paramIDataRecvHandler = paramDataCommand.getCurrentCommand();
    if (paramIDataRecvHandler.equalsIgnoreCase("insertdb_freq_channels"))
      return doInsertCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("getdb_freq_channels"))
      return doAcquireCommand(paramDataCommand);
    if (paramIDataRecvHandler.equalsIgnoreCase("deletedb_freq_channels"))
      return doDeleteCommand(paramDataCommand);
    return null;
  }

  public boolean isSynchronous(String paramString, Map<String, Object> paramMap)
  {
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.data.FreqChannelsDS
 * JD-Core Version:    0.6.2
 */