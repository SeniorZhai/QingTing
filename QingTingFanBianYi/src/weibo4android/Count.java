package weibo4android;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Count
  implements Serializable
{
  private static final long serialVersionUID = 9076424494907778181L;
  private long comments;
  private long dm;
  private long followers;
  private long id;
  private long mentions;
  private long rt;

  Count(Response paramResponse)
    throws WeiboException
  {
    paramResponse = paramResponse.asJSONObject();
    try
    {
      this.id = paramResponse.getLong("id");
      this.comments = paramResponse.getLong("comments");
      this.rt = paramResponse.getLong("rt");
      this.dm = paramResponse.getLong("dm");
      this.mentions = paramResponse.getLong("mentions");
      this.followers = paramResponse.getLong("followers");
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.toString(), localJSONException);
    }
  }

  public Count(JSONObject paramJSONObject)
    throws WeiboException, JSONException
  {
    this.id = paramJSONObject.getLong("id");
    this.comments = paramJSONObject.getLong("comments");
    this.rt = paramJSONObject.getLong("rt");
    this.dm = paramJSONObject.getLong("dm");
    this.mentions = paramJSONObject.getLong("mentions");
    this.followers = paramJSONObject.getLong("followers");
  }

  static List<Count> constructCounts(Response paramResponse)
    throws WeiboException
  {
    ArrayList localArrayList;
    try
    {
      System.out.println(paramResponse.asString());
      paramResponse = paramResponse.asJSONArray();
      int j = paramResponse.length();
      localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Count(paramResponse.getJSONObject(i)));
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
      paramObject = (Count)paramObject;
    }
    while (this.id == paramObject.id);
    return false;
  }

  public long getComments()
  {
    return this.comments;
  }

  public long getDm()
  {
    return this.dm;
  }

  public long getFollowers()
  {
    return this.followers;
  }

  public long getId()
  {
    return this.id;
  }

  public long getMentions()
  {
    return this.mentions;
  }

  public long getRt()
  {
    return this.rt;
  }

  public int hashCode()
  {
    return (int)(this.id ^ this.id >>> 32) + 31;
  }

  public String toString()
  {
    return "Count{ id=" + this.id + ", comments=" + this.comments + ", rt=" + this.rt + ", dm=" + this.dm + ", mentions=" + this.mentions + ", followers=" + this.followers + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Count
 * JD-Core Version:    0.6.2
 */