package fm.qingting.qtradio.model;

import fm.qingting.framework.data.DataManager;
import fm.qingting.framework.data.IResultToken;
import fm.qingting.framework.data.Result;
import fm.qingting.utils.DeviceInfo;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MediaCenter
{
  public static final String LIVE_CHANNEL_DOWNLOAD = "radiodownload";
  public static final String LIVE_CHANNEL_PLAY = "radiohls";
  public static final String VIRUTAL_CHANNEL = "virutalchannel";
  public static MediaCenter _instance;
  private List<PingInfoV6> lstRes = new ArrayList();
  public String mDeviceId;
  public Map<String, List<PingInfoV6>> mapMediaCenters = new HashMap();
  public Map<String, List<PingInfoV6>> mapMediaCentersToDB;
  public String region = "CN";

  public static MediaCenter getInstance()
  {
    if (_instance == null)
      _instance = new MediaCenter();
    return _instance;
  }

  private List<PingInfoV6> getLstPingInfoAfterPing(String paramString1, String paramString2)
  {
    int m = 0;
    if ((paramString1 == null) || (paramString2 == null))
      paramString1 = null;
    List localList;
    do
    {
      return paramString1;
      localList = (List)this.mapMediaCenters.get(paramString1);
      if ((localList == null) || (localList.size() == 0))
        return null;
      if ((paramString1.equalsIgnoreCase("radiohls")) || (paramString1.equalsIgnoreCase("radiodownload")))
        paramString2 = this.mDeviceId;
      i = 0;
      j = 0;
      while (i < localList.size())
      {
        j += ((PingInfoV6)localList.get(i)).weight;
        i += 1;
      }
      paramString1 = localList;
    }
    while (j == 0);
    long l = hashCode(paramString2) % j;
    int i = 0;
    int k = 0;
    int j = 0;
    if (i < localList.size())
    {
      k += ((PingInfoV6)localList.get(i)).weight;
      if ((j > l) || (l > k));
    }
    while (true)
    {
      this.lstRes.clear();
      j = 0;
      while (true)
      {
        k = m;
        if (j >= localList.size())
          break;
        paramString1 = new PingInfoV6();
        paramString1.update((PingInfoV6)localList.get(j));
        this.lstRes.add(paramString1);
        j += 1;
      }
      i += 1;
      j = k;
      break;
      while (k < this.lstRes.size())
      {
        if (k != i)
          ((PingInfoV6)this.lstRes.get(k)).setResult(((PingInfoV6)localList.get(k)).getReachTime());
        k += 1;
      }
      double d1 = ((PingInfoV6)this.lstRes.get(i)).getReachTime();
      double d2 = ((PingInfoV6)this.lstRes.get(i)).pcc;
      ((PingInfoV6)this.lstRes.get(i)).setResult(d1 - d2);
      Collections.sort(this.lstRes, new PingInfoV6Comparator());
      return this.lstRes;
      i = 0;
    }
  }

  private long hashCode(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return 0L;
    try
    {
      long l = ByteBuffer.wrap(DeviceInfo.md5(paramString).getBytes()).getLong();
      return l;
    }
    catch (Exception paramString)
    {
    }
    return 9223372036854775807L;
  }

  public List<PingInfoV6> getPingInfo(String paramString)
  {
    if (paramString == null)
      return null;
    return (List)this.mapMediaCenters.get(paramString);
  }

  public String getPlayUrls(String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    Object localObject1 = null;
    Object localObject2 = localObject1;
    if (paramString1 != null)
    {
      localObject2 = localObject1;
      if (paramString2 != null)
      {
        localObject2 = localObject1;
        if (!paramString2.equalsIgnoreCase(""))
        {
          if (!paramString2.equalsIgnoreCase("0"))
            break label48;
          localObject2 = localObject1;
        }
      }
    }
    label48: List localList;
    do
    {
      do
      {
        return localObject2;
        localList = getLstPingInfoAfterPing(paramString1, paramString2);
        localObject2 = localObject1;
      }
      while (localList == null);
      localObject2 = localObject1;
    }
    while (localList.size() == 0);
    localObject2 = "";
    localObject1 = localObject2;
    if (paramString1 == "radiohls")
    {
      localObject1 = localObject2;
      if (InfoManager.getInstance().isTestLiveChannel(Integer.valueOf(paramString2).intValue()))
      {
        localObject1 = "rtmp://rtmplive.qingting.fm/qtrtmp/" + paramString2;
        localObject1 = (String)localObject1 + "?deviceid=" + InfoManager.getInstance().getDeviceId();
        localObject1 = (String)localObject1 + "&cid=" + paramInt2;
        localObject1 = (String)localObject1 + "&phonetype=Android";
        localObject1 = (String)localObject1 + "&region=" + this.region;
        localObject1 = (String)localObject1 + ";;";
      }
    }
    int i = 0;
    while (true)
    {
      localObject2 = localObject1;
      if (i >= localList.size())
        break;
      localObject2 = (String)localObject1 + "http://";
      localObject1 = (PingInfoV6)localList.get(i);
      localObject2 = (String)localObject2 + ((PingInfoV6)localObject1).getDomainIP();
      localObject2 = (String)localObject2 + ((PingInfoV6)localObject1).getAccessUrl(paramString2, this.mDeviceId, paramInt1);
      localObject1 = localObject2;
      if (paramString1 == "radiohls")
      {
        localObject1 = (String)localObject2 + "&cid=" + paramInt2;
        localObject1 = (String)localObject1 + "&phonetype=Android";
        localObject1 = (String)localObject1 + "&region=" + this.region;
      }
      localObject1 = (String)localObject1 + ";;";
      i += 1;
    }
  }

  public String getReplayDownloadPath(String paramString1, String paramString2, int paramInt, String paramString3, String paramString4)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.equalsIgnoreCase("")));
    do
    {
      return null;
      paramString1 = getLstPingInfoAfterPing(paramString1, paramString2);
    }
    while ((paramString1 == null) || (paramString1.size() == 0) || (paramString1.size() >= 0));
    return ((PingInfoV6)paramString1.get(0)).getReplayUrl(paramString2, this.mDeviceId, paramInt, paramString3, paramString4);
  }

  public String getReplayUrls(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    Object localObject1 = null;
    Object localObject2 = localObject1;
    if (paramString1 != null)
    {
      localObject2 = localObject1;
      if (!paramString1.equalsIgnoreCase(""))
      {
        localObject2 = localObject1;
        if (paramString2 != null)
        {
          if (paramString3 != null)
            break label44;
          localObject2 = localObject1;
        }
      }
    }
    label44: List localList;
    do
    {
      do
      {
        return localObject2;
        localList = getLstPingInfoAfterPing("radiohls", paramString1);
        localObject2 = localObject1;
      }
      while (localList == null);
      localObject2 = localObject1;
    }
    while (localList.size() == 0);
    int i = 0;
    localObject1 = "";
    while (true)
    {
      localObject2 = localObject1;
      if (i >= localList.size())
        break;
      localObject2 = (String)localObject1 + "http://";
      localObject1 = (PingInfoV6)localList.get(i);
      localObject2 = (String)localObject2 + ((PingInfoV6)localObject1).getDomainIP();
      localObject1 = (String)localObject2 + ((PingInfoV6)localObject1).getReplayUrl(paramString1, this.mDeviceId, paramInt, paramString2, paramString3);
      localObject1 = (String)localObject1 + ";;";
      i += 1;
    }
  }

  public String getShareReplayUrl(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (paramString2 == null) || (paramString3 == null) || (paramString1.equalsIgnoreCase("0")));
    do
    {
      return null;
      localObject = getLstPingInfoAfterPing("radiohls", paramString1);
    }
    while ((localObject == null) || (((List)localObject).size() == 0));
    Object localObject = (PingInfoV6)((List)localObject).get(0);
    String str = "http://" + "hls.hz.qingting.fm";
    paramString1 = str + ((PingInfoV6)localObject).getReplayUrl(paramString1, this.mDeviceId, paramInt, paramString2, paramString3);
    return paramString1 + ";;";
  }

  public String getShareUrl(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.equalsIgnoreCase("")));
    do
    {
      return null;
      localObject = getLstPingInfoAfterPing(paramString1, paramString2);
    }
    while ((localObject == null) || (((List)localObject).size() == 0));
    String str = "http://";
    Object localObject = (PingInfoV6)((List)localObject).get(0);
    if (paramString1.equalsIgnoreCase("radiohls"))
      str = "http://" + "hls.hz.qingting.fm";
    while (true)
    {
      return str + ((PingInfoV6)localObject).getAccessUrl(paramString2, this.mDeviceId, paramInt);
      if (paramString1.equalsIgnoreCase("virutalchannel"))
        str = "http://" + "od.qingting.fm";
    }
  }

  public String getVirtualProgramDownloadPath(String paramString1, String paramString2, int paramInt)
  {
    if ((paramString1 == null) || (paramString2 == null) || (paramString2.equalsIgnoreCase("")));
    do
    {
      return null;
      paramString1 = getLstPingInfoAfterPing(paramString1, paramString2);
    }
    while ((paramString1 == null) || (paramString1.size() == 0) || (paramString1.size() >= 0));
    return ((PingInfoV6)paramString1.get(0)).getAccessUrl(paramString2, this.mDeviceId, paramInt);
  }

  public void init(String paramString)
  {
    this.mDeviceId = paramString;
  }

  public void pkMediaCenter()
  {
    if (this.mapMediaCenters.size() == 0)
      return;
    PickDataCenterV6 localPickDataCenterV6 = new PickDataCenterV6();
    localPickDataCenterV6.setDataCenterInfo(this.mapMediaCenters);
    localPickDataCenterV6.start();
  }

  public void restoreMediaCenter()
  {
    Map localMap = null;
    Result localResult = DataManager.getInstance().getData("GETDB_MEDIA_CENTER", null, null).getResult();
    if (localResult.getSuccess())
      localMap = (Map)localResult.getData();
    if ((localMap != null) && (localMap.size() > 0))
      this.mapMediaCenters = localMap;
  }

  public void setMediaCenter(MediaCenter paramMediaCenter)
  {
    if (this.mapMediaCenters.size() == 0)
      this.mapMediaCenters = paramMediaCenter.mapMediaCenters;
    this.mapMediaCentersToDB = paramMediaCenter.mapMediaCenters;
  }

  public void setRegion(String paramString)
  {
    this.region = paramString;
  }

  public void updateMediaCenter()
  {
    if ((this.mapMediaCentersToDB != null) && (this.mapMediaCentersToDB.size() != 0))
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("mediacenter", this.mapMediaCentersToDB);
      DataManager.getInstance().getData("UPDATEDB_MEDIA_CENTER", null, localHashMap);
    }
  }

  class PingInfoV6Comparator
    implements Comparator<PingInfoV6>
  {
    PingInfoV6Comparator()
    {
    }

    public int compare(PingInfoV6 paramPingInfoV61, PingInfoV6 paramPingInfoV62)
    {
      if (paramPingInfoV61.getResult() > paramPingInfoV62.getResult())
        return 1;
      if (paramPingInfoV61.getResult() < paramPingInfoV62.getResult())
        return -1;
      return 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.MediaCenter
 * JD-Core Version:    0.6.2
 */