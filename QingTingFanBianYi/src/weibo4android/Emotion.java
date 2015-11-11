package weibo4android;

import java.util.ArrayList;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Emotion extends WeiboResponse
{
  private static final long serialVersionUID = -4096813631691846494L;
  private String category;
  private boolean is_common;
  private boolean is_hot;
  private int order_number;
  private String phrase;
  private String type;
  private String url;

  public Emotion()
  {
  }

  public Emotion(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    paramResponse = paramResponse.asJSONObject();
    try
    {
      this.phrase = paramResponse.getString("phrase");
      this.type = paramResponse.getString("type");
      this.url = paramResponse.getString("url");
      this.is_hot = paramResponse.getBoolean("is_hot");
      this.order_number = paramResponse.getInt("order_number");
      this.category = paramResponse.getString("category");
      this.is_common = paramResponse.getBoolean("is_common");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.toString(), localJSONException);
    }
  }

  public Emotion(JSONObject paramJSONObject)
    throws WeiboException
  {
    try
    {
      this.phrase = paramJSONObject.getString("phrase");
      this.type = paramJSONObject.getString("type");
      this.url = paramJSONObject.getString("url");
      this.is_hot = paramJSONObject.getBoolean("is_hot");
      this.order_number = paramJSONObject.getInt("order_number");
      this.category = paramJSONObject.getString("category");
      this.is_common = paramJSONObject.getBoolean("is_common");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
    }
  }

  static List<Emotion> constructEmotions(Response paramResponse)
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
        localArrayList.add(new Emotion(paramResponse.getJSONObject(i)));
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

  public String getCategory()
  {
    return this.category;
  }

  public int getOrder_number()
  {
    return this.order_number;
  }

  public String getPhrase()
  {
    return this.phrase;
  }

  public String getType()
  {
    return this.type;
  }

  public String getUrl()
  {
    return this.url;
  }

  public boolean isIs_common()
  {
    return this.is_common;
  }

  public boolean isIs_hot()
  {
    return this.is_hot;
  }

  public void setCategory(String paramString)
  {
    this.category = paramString;
  }

  public void setIs_common(boolean paramBoolean)
  {
    this.is_common = paramBoolean;
  }

  public void setIs_hot(boolean paramBoolean)
  {
    this.is_hot = paramBoolean;
  }

  public void setOrder_number(int paramInt)
  {
    this.order_number = paramInt;
  }

  public void setPhrase(String paramString)
  {
    this.phrase = paramString;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public void setUrl(String paramString)
  {
    this.url = paramString;
  }

  public String toString()
  {
    return "Emotion [phrase=" + this.phrase + ", type=" + this.type + ", url=" + this.url + ", is_hot=" + this.is_hot + ", is_common=" + this.is_common + ", order_number=" + this.order_number + ", category=" + this.category + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Emotion
 * JD-Core Version:    0.6.2
 */