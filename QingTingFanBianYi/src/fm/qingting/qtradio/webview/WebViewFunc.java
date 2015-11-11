package fm.qingting.qtradio.webview;

import android.os.Handler;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.helper.ProgramHelper;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramScheduleList;
import fm.qingting.qtradio.model.ReserveInfoNode;
import fm.qingting.qtradio.model.RootNode;
import java.util.ArrayList;
import java.util.List;

public class WebViewFunc
{
  private static WebViewFunc _instance;
  private WebView _webview;
  private final Handler addFavHandler = new Handler();
  private Runnable addFavRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doAddFav();
    }
  };
  private final Handler cancelFavHandler = new Handler();
  private Runnable cancelFavRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doCancelFav();
    }
  };
  private final Handler cancelReserveHandler = new Handler();
  private Runnable cancelReserveRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doCancelReserve();
    }
  };
  private WebViewBean favBean;
  private final Handler hasFavHandler = new Handler();
  private Runnable hasFavRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doHasFav();
    }
  };
  private final Handler hasReserveHandler = new Handler();
  private Runnable hasReserveRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doHasReserve();
    }
  };
  private WebViewBean reserveBean;
  private final Handler reserveHandler = new Handler();
  private Runnable reserveRunnable = new Runnable()
  {
    public void run()
    {
      WebViewFunc.this.doAddReserve();
    }
  };

  private void _addReserve(ProgramNode paramProgramNode)
  {
    if (paramProgramNode == null)
      return;
    InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.addReserveNode(paramProgramNode);
  }

  private boolean _hasFav(int paramInt)
  {
    return InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(paramInt);
  }

  private boolean _hasReserve(int paramInt)
  {
    return InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.isExisted(paramInt) != -1;
  }

  private void doAddFav()
  {
    if ((this.favBean != null) && (this.favBean._channelid > 0))
    {
      ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(this.favBean._channelid, this.favBean._channeltype);
      ChannelNode localChannelNode1 = localChannelNode2;
      if (localChannelNode2 == null)
        localChannelNode1 = ChannelHelper.getInstance().getFakeChannel(this.favBean._channelid, this.favBean._catid, this.favBean._channelname, this.favBean._channeltype);
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode(localChannelNode1);
    }
  }

  private void doAddReserve()
  {
    if ((this.reserveBean == null) || (this.reserveBean._programid == 0));
    ProgramScheduleList localProgramScheduleList;
    do
    {
      return;
      localProgramScheduleList = ProgramHelper.getInstance().getProgramSchedule(this.reserveBean._channelid, this.reserveBean._channeltype, false);
    }
    while (localProgramScheduleList == null);
    _addReserve(localProgramScheduleList.getProgramNode(this.reserveBean._programid));
  }

  private void doCancelFav()
  {
    if ((this.favBean != null) && (this.favBean._channelid > 0))
    {
      ChannelNode localChannelNode2 = ChannelHelper.getInstance().getChannel(this.favBean._channelid, this.favBean._channeltype);
      ChannelNode localChannelNode1 = localChannelNode2;
      if (localChannelNode2 == null)
        localChannelNode1 = ChannelHelper.getInstance().getFakeChannel(this.favBean._channelid, this.favBean._catid, this.favBean._channelname, this.favBean._channeltype);
      InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.deleteFavNode(localChannelNode1);
    }
  }

  private void doCancelReserve()
  {
    if (this.reserveBean == null)
      return;
    InfoManager.getInstance().root().mPersonalCenterNode.reserveNode.cancelReserve(this.reserveBean._programid);
  }

  private void doHasFav()
  {
    if (this.favBean == null)
      return;
    String str = null;
    int i = 0;
    if (i < this.favBean._vChannelids.size())
    {
      if (!_hasFav(((Integer)this.favBean._vChannelids.get(i)).intValue()))
        break label166;
      if (str == null)
      {
        str = "{\"vchannel_ids\":[";
        label61: str = str + String.valueOf(this.favBean._vChannelids.get(i));
      }
    }
    label166: 
    while (true)
    {
      i += 1;
      break;
      str = str + ",";
      break label61;
      if (str != null);
      for (str = str + "]}"; ; str = "{\"vchannel_ids\":[]}")
      {
        invokeCallBack(str, this.favBean);
        return;
      }
    }
  }

  private void doHasReserve()
  {
    if (this.reserveBean == null)
      return;
    String str = null;
    int i = 0;
    if (i < this.reserveBean._vProgramids.size())
    {
      if (!_hasReserve(((Integer)this.reserveBean._vProgramids.get(i)).intValue()))
        break label167;
      if (str == null)
      {
        str = "{\"vprogram_ids\":[";
        label62: str = str + String.valueOf(this.reserveBean._vProgramids.get(i));
      }
    }
    label167: 
    while (true)
    {
      i += 1;
      break;
      str = str + ",";
      break label62;
      if (str != null);
      for (str = str + "]}"; ; str = "{\"vprogram_ids\":[]}")
      {
        invokeCallBack(str, this.reserveBean);
        return;
      }
    }
  }

  public static WebViewFunc getInstance()
  {
    try
    {
      if (_instance == null)
        _instance = new WebViewFunc();
      WebViewFunc localWebViewFunc = _instance;
      return localWebViewFunc;
    }
    finally
    {
    }
  }

  private void invokeCallBack(String paramString, WebViewBean paramWebViewBean)
  {
    if (paramWebViewBean == null)
      return;
    WebViewBean.access$1602(paramWebViewBean, "javascript:");
    WebViewBean.access$1684(paramWebViewBean, paramWebViewBean._callBack);
    if (paramWebViewBean._callBackParams == null)
    {
      WebViewBean.access$1684(paramWebViewBean, "(null");
      label37: if (paramString == null)
        break label143;
      WebViewBean.access$1684(paramWebViewBean, ",'" + paramString + "'");
    }
    while (true)
    {
      WebViewBean.access$1684(paramWebViewBean, ")");
      if ((this._webview == null) || (paramWebViewBean._callBackJs == null))
        break;
      this._webview.loadUrl(paramWebViewBean._callBackJs);
      return;
      WebViewBean.access$1684(paramWebViewBean, "('" + paramWebViewBean._callBackParams + "'");
      break label37;
      label143: WebViewBean.access$1684(paramWebViewBean, ",null");
    }
  }

  private void parseResInfo(String paramString, WebViewBean paramWebViewBean)
  {
    int j = 0;
    if ((paramWebViewBean == null) || (paramString == null));
    while (true)
    {
      return;
      try
      {
        paramString = JSON.parseObject(paramString);
        Object localObject = paramString.getString("cat_id");
        WebViewBean.access$602(paramWebViewBean, 0);
        WebViewBean.access$702(paramWebViewBean, 0);
        WebViewBean.access$802(paramWebViewBean, 0);
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          WebViewBean.access$602(paramWebViewBean, Integer.valueOf((String)localObject).intValue());
        localObject = paramString.getString("channel_id");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          WebViewBean.access$702(paramWebViewBean, Integer.valueOf((String)localObject).intValue());
        localObject = paramString.getString("program_id");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          WebViewBean.access$802(paramWebViewBean, Integer.valueOf((String)localObject).intValue());
        WebViewBean.access$902(paramWebViewBean, paramString.getString("channel_name"));
        WebViewBean.access$1002(paramWebViewBean, paramString.getIntValue("channel_type"));
        localObject = paramString.getJSONArray("vprogram_ids");
        int i;
        if (localObject != null)
        {
          paramWebViewBean._vProgramids.clear();
          i = 0;
          while (i < ((JSONArray)localObject).size())
          {
            paramWebViewBean._vProgramids.add(Integer.valueOf(((JSONArray)localObject).getIntValue(i)));
            i += 1;
          }
        }
        paramString = paramString.getJSONArray("vchannel_ids");
        if (paramString != null)
        {
          paramWebViewBean._vChannelids.clear();
          i = j;
          while (i < paramString.size())
          {
            paramWebViewBean._vChannelids.add(Integer.valueOf(paramString.getIntValue(i)));
            i += 1;
          }
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
    }
  }

  @JavascriptInterface
  public void AddFav(String paramString1, String paramString2, String paramString3)
  {
    this.favBean = new WebViewBean(null);
    WebViewBean.access$1402(this.favBean, paramString2);
    WebViewBean.access$1502(this.favBean, paramString3);
    parseResInfo(paramString1, this.favBean);
    this.addFavHandler.postDelayed(this.addFavRunnable, 1L);
  }

  @JavascriptInterface
  public void AddReserve(String paramString1, String paramString2, String paramString3)
  {
    this.reserveBean = new WebViewBean(null);
    WebViewBean.access$1402(this.reserveBean, paramString2);
    WebViewBean.access$1502(this.reserveBean, paramString3);
    parseResInfo(paramString1, this.reserveBean);
    this.reserveHandler.postDelayed(this.reserveRunnable, 1L);
  }

  @JavascriptInterface
  public void CancelFav(String paramString1, String paramString2, String paramString3)
  {
    this.favBean = new WebViewBean(null);
    WebViewBean.access$1402(this.favBean, paramString2);
    WebViewBean.access$1502(this.favBean, paramString3);
    parseResInfo(paramString1, this.favBean);
    this.cancelFavHandler.postDelayed(this.cancelFavRunnable, 1L);
  }

  @JavascriptInterface
  public void CancelReserve(String paramString1, String paramString2, String paramString3)
  {
    this.reserveBean = new WebViewBean(null);
    WebViewBean.access$1402(this.reserveBean, paramString2);
    WebViewBean.access$1502(this.reserveBean, paramString3);
    parseResInfo(paramString1, this.reserveBean);
    this.cancelReserveHandler.postDelayed(this.cancelReserveRunnable, 1L);
  }

  @JavascriptInterface
  public void hasFav(String paramString1, String paramString2, String paramString3)
  {
    this.favBean = new WebViewBean(null);
    WebViewBean.access$1402(this.favBean, paramString2);
    WebViewBean.access$1502(this.favBean, paramString3);
    parseResInfo(paramString1, this.favBean);
    this.hasFavHandler.postDelayed(this.hasFavRunnable, 1L);
  }

  @JavascriptInterface
  public void hasReserve(String paramString1, String paramString2, String paramString3)
  {
    this.reserveBean = new WebViewBean(null);
    WebViewBean.access$1402(this.reserveBean, paramString2);
    WebViewBean.access$1502(this.reserveBean, paramString3);
    parseResInfo(paramString1, this.reserveBean);
    this.hasReserveHandler.postDelayed(this.hasReserveRunnable, 1L);
  }

  public void setWebview(WebView paramWebView)
  {
    this._webview = paramWebView;
  }

  private class WebViewBean
  {
    private String _callBack;
    private String _callBackJs;
    private String _callBackParams;
    private int _catid;
    private int _channelid;
    private String _channelname;
    private int _channeltype = 1;
    private int _programid;
    private List<Integer> _vChannelids = new ArrayList();
    private List<Integer> _vProgramids = new ArrayList();

    private WebViewBean()
    {
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.webview.WebViewFunc
 * JD-Core Version:    0.6.2
 */