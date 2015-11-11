package weibo4android;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class SavedSearch extends WeiboResponse
{
  private static final long serialVersionUID = 3083819860391598212L;
  private Date createdAt;
  private int id;
  private String name;
  private int position;
  private String query;

  SavedSearch(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse.asJSONObject());
  }

  SavedSearch(Response paramResponse, JSONObject paramJSONObject)
    throws WeiboException
  {
    super(paramResponse);
    init(paramJSONObject);
  }

  SavedSearch(JSONObject paramJSONObject)
    throws WeiboException
  {
    init(paramJSONObject);
  }

  static List<SavedSearch> constructSavedSearches(Response paramResponse)
    throws WeiboException
  {
    JSONArray localJSONArray = paramResponse.asJSONArray();
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList(localJSONArray.length());
      int i = 0;
      while (i < localJSONArray.length())
      {
        localArrayList.add(new SavedSearch(paramResponse, localJSONArray.getJSONObject(i)));
        i += 1;
      }
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.asString(), localJSONException);
    }
    return localArrayList;
  }

  private void init(JSONObject paramJSONObject)
    throws WeiboException
  {
    try
    {
      this.createdAt = parseDate(paramJSONObject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
      this.query = getString("query", paramJSONObject, true);
      this.position = getInt("position", paramJSONObject);
      this.name = getString("name", paramJSONObject, true);
      this.id = getInt("id", paramJSONObject);
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof SavedSearch))
        return false;
      paramObject = (SavedSearch)paramObject;
      if (this.id != paramObject.id)
        return false;
      if (this.position != paramObject.position)
        return false;
      if (!this.createdAt.equals(paramObject.createdAt))
        return false;
      if (!this.name.equals(paramObject.name))
        return false;
    }
    while (this.query.equals(paramObject.query));
    return false;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public int getPosition()
  {
    return this.position;
  }

  public String getQuery()
  {
    return this.query;
  }

  public int hashCode()
  {
    return (((this.createdAt.hashCode() * 31 + this.query.hashCode()) * 31 + this.position) * 31 + this.name.hashCode()) * 31 + this.id;
  }

  public String toString()
  {
    return "SavedSearch{createdAt=" + this.createdAt + ", query='" + this.query + '\'' + ", position=" + this.position + ", name='" + this.name + '\'' + ", id=" + this.id + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.SavedSearch
 * JD-Core Version:    0.6.2
 */