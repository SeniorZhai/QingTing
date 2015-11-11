package fm.qingting.qtradio.mobAgent;

import android.content.Context;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.data.IMonitorMobAgent;
import java.util.HashMap;
import java.util.Map;

public class mobAgentOption
  implements IMonitorMobAgent
{
  public static final String WEIBO_CAI = "weibo_cai_option_new";
  public static final String WEIBO_CREATE = "weibo_message_create_new";
  public static final String WEIBO_DING = "weibo_ding_option_new";
  public static final String WEIBO_FLOWER = "weibo_flower_click_new";
  public static final String WEIBO_FRIEND = "weibo_friend_click_new";
  public static final String WEIBO_LEAVE = "weibo_message_leave_new";
  public static final String WEIBO_LOGOUT = "weibo_logout_click_new";
  public static final String WEIBO_REPO = "weibo_repo_option_new";
  public static final String WEIBO_SIGN = "weibo_sign_click_new";
  public static final String WEIBO_TOMYWEIBO = "weibo_tomyweibo_click_new";
  private static mobAgentOption instance;
  private Context _context;
  private String _event;
  private String _option;
  private String _param;
  private String _pos;
  private String _source;
  private String _start;

  public static mobAgentOption getInstance()
  {
    try
    {
      if (instance == null)
        instance = new mobAgentOption();
      mobAgentOption localmobAgentOption = instance;
      return localmobAgentOption;
    }
    finally
    {
    }
  }

  public String getEvent()
  {
    return this._event;
  }

  public String getPos()
  {
    return this._pos;
  }

  public String getSource()
  {
    return this._source;
  }

  public void init(Context paramContext)
  {
    this._context = paramContext;
    this._event = "";
  }

  public void sendMobAgentAPI(String paramString1, String paramString2, Object paramObject)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (this._context == null))
      return;
    if ((paramString1.equalsIgnoreCase("channel_regions")) || (paramString1.equalsIgnoreCase("channel_countries")))
      paramString1 = "api_channel_regions";
    while (true)
    {
      paramString2 = (Map)paramObject;
      if (paramString2 == null)
        break;
      ((Long)paramString2.get("duration")).longValue();
      if (paramString1.equalsIgnoreCase(""))
        break;
      return;
      if (paramString1.equalsIgnoreCase("channel_types"))
        paramString1 = "api_channel_types";
      else if (paramString1.equalsIgnoreCase("region_channels"))
        paramString1 = "api_channel_regionchannel_r";
      else if (paramString1.equalsIgnoreCase("type_channels"))
        paramString1 = "api_channel_regionchannel_t";
      else if (paramString1.equalsIgnoreCase("week_program_schedule"))
        paramString1 = "api_channel_playbillv2";
      else if (paramString1.equalsIgnoreCase("recommended_channels"))
        paramString1 = "api_channel_recommend";
      else if (paramString1.equalsIgnoreCase("search_channels"))
        paramString1 = "api_search_channel";
      else if (paramString1.equalsIgnoreCase("app_key"))
        paramString1 = "api_vbo_appkey";
      else if (paramString1.equalsIgnoreCase("bootstrap"))
        paramString1 = "api_bootstrap";
      else if (paramString1.equalsIgnoreCase("uniad"))
        paramString1 = "api_vbo_uniad";
      else if (paramString1.equalsIgnoreCase("program_topics_list"))
        paramString1 = "api_vbo_programwids";
      else if (paramString1.equalsIgnoreCase("radio_topic_list"))
        paramString1 = "api_vbo_channelwids";
      else if (paramString1.equalsIgnoreCase("global_top_posts"))
        paramString1 = "api_vbo_topwidv2";
      else if (paramString1.equalsIgnoreCase("get_ip_location"))
        paramString1 = "api_utils_iplocation";
      else
        paramString1 = "api_request_other";
    }
  }

  public void sendMobAgentAPIERROR(String paramString1, String paramString2, Object paramObject)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (this._context == null))
      return;
    if ((paramString1.equalsIgnoreCase("channel_regions")) || (paramString1.equalsIgnoreCase("channel_countries")))
      paramString1 = "api_channel_regions_error";
    while (true)
    {
      paramObject = (Map)paramObject;
      if (paramObject == null)
        break;
      long l = ((Long)paramObject.get("duration")).longValue();
      paramObject = (String)paramObject.get("request");
      if ((paramObject == null) || (paramString1.equalsIgnoreCase("")))
        break;
      MobclickAgent.onEventDuration(this._context, paramString1, paramString2, l);
      paramString2 = paramString1 + "_detail";
      if (paramObject.indexOf("googleapis") != -1)
        break;
      int i = paramObject.indexOf("/api/");
      paramString1 = paramObject;
      if (i != -1)
        paramString1 = paramObject.substring(i + 5);
      MobclickAgent.onEvent(this._context, paramString2, paramString1);
      return;
      if (paramString1.equalsIgnoreCase("channel_types"))
        paramString1 = "api_channel_types_error";
      else if (paramString1.equalsIgnoreCase("region_channels"))
        paramString1 = "api_channel_regionchannel_r_error";
      else if (paramString1.equalsIgnoreCase("type_channels"))
        paramString1 = "api_channel_regionchannel_t_error";
      else if (paramString1.equalsIgnoreCase("week_program_schedule"))
        paramString1 = "api_channel_playbillv2_error";
      else if (paramString1.equalsIgnoreCase("recommended_channels"))
        paramString1 = "api_channel_recommend_error";
      else if (paramString1.equalsIgnoreCase("search_channels"))
        paramString1 = "api_search_channel_error";
      else if (paramString1.equalsIgnoreCase("app_key"))
        paramString1 = "api_vbo_appkey_error";
      else if (paramString1.equalsIgnoreCase("bootstrap"))
        paramString1 = "api_bootstrap_error";
      else if (paramString1.equalsIgnoreCase("uniad"))
        paramString1 = "api_vbo_uniad_error";
      else if (paramString1.equalsIgnoreCase("program_topics_list"))
        paramString1 = "api_vbo_programwids_error";
      else if (paramString1.equalsIgnoreCase("radio_topic_list"))
        paramString1 = "api_vbo_channelwids_error";
      else if (paramString1.equalsIgnoreCase("global_top_posts"))
        paramString1 = "api_vbo_topwidv2_error";
      else if (paramString1.equalsIgnoreCase("get_ip_location"))
        paramString1 = "api_utils_iplocation_error";
      else
        paramString1 = "api_request_other_error";
    }
  }

  public void sendMobAgentAPITIMEOUT(String paramString, Object paramObject)
  {
    if (this._context == null);
    do
    {
      return;
      paramObject = (Map)paramObject;
    }
    while (paramObject == null);
    long l = ((Long)paramObject.get("duration")).longValue();
    MobclickAgent.onEventDuration(this._context, "api_request_timeout", paramString, l);
  }

  public void sendMobAgentAPIUNKNOWHOST(String paramString, Object paramObject)
  {
    if (this._context == null);
    do
    {
      return;
      paramObject = (Map)paramObject;
    }
    while (paramObject == null);
    long l = ((Long)paramObject.get("duration")).longValue();
    MobclickAgent.onEventDuration(this._context, "api_request_unknowhost", paramString, l);
  }

  public void sendMobAgentEvent(String paramString)
  {
    if (this._context == null)
      return;
    MobclickAgent.onEvent(this._context, paramString);
  }

  public void sendMobAgentEvent(String paramString1, String paramString2)
  {
    if (this._context == null)
      return;
    MobclickAgent.onEvent(this._context, paramString1, paramString2);
  }

  public void sendMobAgentEventDuration(String paramString, Long paramLong)
  {
    if (this._context == null)
      return;
    MobclickAgent.onEventDuration(this._context, paramString, paramLong.longValue());
  }

  public void sendMobAgentEventDuration(String paramString1, String paramString2, Long paramLong)
  {
    if (this._context == null)
      return;
    MobclickAgent.onEventDuration(this._context, paramString1, paramString2, paramLong.longValue());
  }

  public void sendMobClickAgent(int paramInt)
  {
    if (paramInt == 0);
    HashMap localHashMap;
    do
    {
      do
        return;
      while ((this._event == null) || (this._event.equalsIgnoreCase("")) || (this._event == null));
      localHashMap = new HashMap();
      if ((paramInt & 0x1) == 1)
        localHashMap.put("source", this._source);
      if ((paramInt & 0x10) == 16)
        localHashMap.put("haslogin", this._start);
      if (((paramInt & 0x100) != 256) || ((paramInt & 0x1000) == 4096))
        localHashMap.put("resultOption", this._option);
      if ((paramInt & 0x10000) == 65536)
        localHashMap.put("selectParam", this._param);
    }
    while ((localHashMap == null) || (localHashMap.size() <= 0));
    MobclickAgent.onEvent(this._context, this._event, localHashMap);
  }

  public void sendMobClickAgentCustom(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    if ((paramString1 == null) || (paramString1.equalsIgnoreCase("")) || (this._context == null))
      return;
    paramString3 = new HashMap();
    if ((paramString2 != null) && (!paramString2.equalsIgnoreCase("")))
      paramString3.put("source", paramString2);
    if ((paramString4 != null) && (!paramString4.equalsIgnoreCase("")))
      paramString3.put("resultOption", paramString4);
    if ((paramString5 != null) && (!paramString5.equalsIgnoreCase("")))
      paramString3.put("selectParam", "");
    MobclickAgent.onEvent(this._context, paramString1, paramString3);
  }

  public void sendMobClickAgentLog(int paramInt)
  {
    if (paramInt == 0);
    HashMap localHashMap;
    do
    {
      do
        return;
      while ((this._event == null) || (this._event.equalsIgnoreCase("")) || (this._event == null));
      localHashMap = new HashMap();
      if ((paramInt & 0x1) == 1)
        localHashMap.put("source", this._source);
      if ((paramInt & 0x10) == 16)
        localHashMap.put("haslogin", this._start);
      if (((paramInt & 0x100) != 256) || ((paramInt & 0x1000) == 4096))
        localHashMap.put("loginViewOption", this._option);
      if ((paramInt & 0x10000) == 65536)
        localHashMap.put("selectParam", this._param);
    }
    while ((localHashMap == null) || (localHashMap.size() <= 0));
    MobclickAgent.onEvent(this._context, this._event, localHashMap);
  }

  public void setEvent(String paramString)
  {
    this._event = paramString;
  }

  public void setOption(String paramString)
  {
    this._option = paramString;
  }

  public void setParam(String paramString)
  {
    this._param = paramString;
  }

  public void setPos(String paramString)
  {
    this._pos = paramString;
  }

  public void setSource(String paramString)
  {
    this._source = paramString;
  }

  public void setStart(String paramString)
  {
    this._start = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.mobAgent.mobAgentOption
 * JD-Core Version:    0.6.2
 */