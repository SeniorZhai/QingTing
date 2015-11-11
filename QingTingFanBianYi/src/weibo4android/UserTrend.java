package weibo4android;

import java.util.ArrayList;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class UserTrend extends WeiboResponse
{
  private static final long serialVersionUID = 1925956704460743946L;
  private String hotword = null;
  private String num;
  private String trend_id = null;

  public UserTrend()
  {
  }

  public UserTrend(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    paramResponse = paramResponse.asJSONObject();
    try
    {
      this.num = paramResponse.getString("num");
      this.hotword = paramResponse.getString("hotword");
      this.trend_id = paramResponse.getString("trend_id");
      if (paramResponse.getString("topicid") != null)
        this.trend_id = paramResponse.getString("topicid");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.toString(), localJSONException);
    }
  }

  public UserTrend(JSONObject paramJSONObject)
    throws WeiboException
  {
    try
    {
      this.num = paramJSONObject.getString("num");
      this.hotword = paramJSONObject.getString("hotword");
      this.trend_id = paramJSONObject.getString("trend_id");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
    }
  }

  static List<UserTrend> constructTrendList(Response paramResponse)
    throws WeiboException
  {
    ArrayList localArrayList;
    try
    {
      paramResponse = paramResponse.asJSONArray();
      int j = paramResponse.length();
      localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new UserTrend(paramResponse.getJSONObject(i)));
        i += 1;
      }
    }
    catch (JSONException paramResponse)
    {
      throw new WeiboException(paramResponse);
    }
    catch (WeiboException paramResponse)
    {
      throw paramResponse;
    }
    return localArrayList;
  }

  public String getHotword()
  {
    return this.hotword;
  }

  public String getNum()
  {
    return this.num;
  }

  public String getTrend_id()
  {
    return this.trend_id;
  }

  public void setHotword(String paramString)
  {
    this.hotword = paramString;
  }

  public void setNum(String paramString)
  {
    this.num = paramString;
  }

  public void setTrend_id(String paramString)
  {
    this.trend_id = paramString;
  }

  public String toString()
  {
    return "Trend [num=" + this.num + ", hotword=" + this.hotword + ", trend_id=" + this.trend_id + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.UserTrend
 * JD-Core Version:    0.6.2
 */