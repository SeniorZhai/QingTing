package weibo4android;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class SearchResult extends WeiboResponse
  implements Serializable
{
  private static final long serialVersionUID = 8227371192527300467L;
  private Date createdAt;
  private String from_user;
  private long from_user_id;
  private long id;
  private String iso_language_code;
  private String profileImageUrl;
  private String source;
  private String text;
  private String to_user;
  private long to_user_id;

  public SearchResult(JSONObject paramJSONObject)
    throws WeiboException, JSONException
  {
    this.createdAt = parseDate(paramJSONObject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
    this.to_user_id = paramJSONObject.getLong("to_user_id");
    this.to_user = paramJSONObject.getString("to_user");
    this.text = paramJSONObject.getString("text");
    this.source = paramJSONObject.getString("source");
    this.id = paramJSONObject.getLong("id");
    this.from_user_id = paramJSONObject.getLong("from_user_id");
    this.from_user = paramJSONObject.getString("from_user");
    this.iso_language_code = paramJSONObject.getString("iso_language_code");
    this.profileImageUrl = paramJSONObject.getString("profile_image_url");
  }

  public static List<SearchResult> constructResults(Response paramResponse)
    throws WeiboException
  {
    paramResponse = paramResponse.asJSONObject();
    ArrayList localArrayList;
    try
    {
      paramResponse = paramResponse.getJSONArray("results");
      int j = paramResponse.length();
      localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new SearchResult(paramResponse.getJSONObject(i)));
        i += 1;
      }
    }
    catch (JSONException paramResponse)
    {
      throw new WeiboException(paramResponse);
    }
    return localArrayList;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (SearchResult)paramObject;
      if (this.from_user == null)
      {
        if (paramObject.from_user != null)
          return false;
      }
      else if (!this.from_user.equals(paramObject.from_user))
        return false;
      if (this.from_user_id != paramObject.from_user_id)
        return false;
      if (this.id != paramObject.id)
        return false;
      if (this.to_user == null)
      {
        if (paramObject.to_user != null)
          return false;
      }
      else if (!this.to_user.equals(paramObject.to_user))
        return false;
    }
    while (this.to_user_id == paramObject.to_user_id);
    return false;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
  }

  public String getFromUser()
  {
    return this.from_user;
  }

  public long getFromUserId()
  {
    return this.from_user_id;
  }

  public long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.iso_language_code;
  }

  public URL getProfileImageURL()
  {
    try
    {
      URL localURL = new URL(this.profileImageUrl);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
    return null;
  }

  public String getSource()
  {
    return this.source;
  }

  public String getText()
  {
    return this.text;
  }

  public String getToUser()
  {
    return this.to_user;
  }

  public long getToUserId()
  {
    return this.to_user_id;
  }

  public int hashCode()
  {
    int j = 0;
    int i;
    int k;
    int m;
    if (this.from_user == null)
    {
      i = 0;
      k = (int)(this.from_user_id ^ this.from_user_id >>> 32);
      m = (int)(this.id ^ this.id >>> 32);
      if (this.to_user != null)
        break label96;
    }
    while (true)
    {
      return ((((i + 31) * 31 + k) * 31 + m) * 31 + j) * 31 + (int)(this.to_user_id ^ this.to_user_id >>> 32);
      i = this.from_user.hashCode();
      break;
      label96: j = this.to_user.hashCode();
    }
  }

  public String toString()
  {
    return "Result{ " + this.to_user_id + "," + this.to_user + "," + this.text + "," + this.id + "," + this.from_user_id + "," + this.from_user + "," + this.iso_language_code + "," + this.source + "," + this.profileImageUrl + "," + this.createdAt + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.SearchResult
 * JD-Core Version:    0.6.2
 */