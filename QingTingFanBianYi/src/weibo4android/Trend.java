package weibo4android;

import java.io.Serializable;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Trend
  implements Serializable
{
  private static final long serialVersionUID = 1925956704460743946L;
  private String name;
  private String query = null;

  public Trend(JSONObject paramJSONObject)
    throws JSONException
  {
    this.name = paramJSONObject.getString("name");
    if (!paramJSONObject.isNull("query"))
      this.query = paramJSONObject.getString("query");
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof Trend))
        return false;
      paramObject = (Trend)paramObject;
      if (!this.name.equals(paramObject.name))
        return false;
      if (this.query == null)
        break;
    }
    while (this.query.equals(paramObject.query));
    while (true)
    {
      return false;
      if (paramObject.query == null)
        break;
    }
  }

  public String getName()
  {
    return this.name;
  }

  public String getQuery()
  {
    return this.query;
  }

  public int hashCode()
  {
    int j = this.name.hashCode();
    if (this.query != null);
    for (int i = this.query.hashCode(); ; i = 0)
      return i + j * 31;
  }

  public String toString()
  {
    return "Trend{name='" + this.name + '\'' + ", query='" + this.query + '\'' + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Trend
 * JD-Core Version:    0.6.2
 */