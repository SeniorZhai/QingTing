package weibo4android;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Trends extends WeiboResponse
  implements Comparable<Trends>
{
  private static final long serialVersionUID = -7151479143843312309L;
  private Date asOf;
  private Date trendAt;
  private Trend[] trends;

  Trends(Response paramResponse, Date paramDate1, Date paramDate2, Trend[] paramArrayOfTrend)
    throws WeiboException
  {
    super(paramResponse);
    this.asOf = paramDate1;
    this.trendAt = paramDate2;
    this.trends = paramArrayOfTrend;
  }

  static Trends constructTrends(Response paramResponse)
    throws WeiboException
  {
    Object localObject = paramResponse.asJSONObject();
    try
    {
      Date localDate = parseDate(((JSONObject)localObject).getString("as_of"));
      localObject = new Trends(paramResponse, localDate, localDate, jsonArrayToTrendArray(((JSONObject)localObject).getJSONArray("trends")));
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.asString(), localJSONException);
    }
  }

  static List<Trends> constructTrendsList(Response paramResponse)
    throws WeiboException
  {
    JSONObject localJSONObject = paramResponse.asJSONObject();
    ArrayList localArrayList;
    while (true)
    {
      String str;
      Trend[] arrayOfTrend;
      try
      {
        Date localDate = parseDate(localJSONObject.getString("as_of"));
        localJSONObject = localJSONObject.getJSONObject("trends");
        localArrayList = new ArrayList(localJSONObject.length());
        Iterator localIterator = localJSONObject.keys();
        if (!localIterator.hasNext())
          break;
        str = (String)localIterator.next();
        arrayOfTrend = jsonArrayToTrendArray(localJSONObject.getJSONArray(str));
        if (str.length() == 19)
        {
          localArrayList.add(new Trends(paramResponse, localDate, parseDate(str, "yyyy-MM-dd HH:mm:ss"), arrayOfTrend));
          continue;
        }
      }
      catch (JSONException localJSONException)
      {
        throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.asString(), localJSONException);
      }
      if (str.length() == 16)
        localArrayList.add(new Trends(paramResponse, localJSONException, parseDate(str, "yyyy-MM-dd HH:mm"), arrayOfTrend));
      else if (str.length() == 10)
        localArrayList.add(new Trends(paramResponse, localJSONException, parseDate(str, "yyyy-MM-dd"), arrayOfTrend));
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }

  private static Trend[] jsonArrayToTrendArray(JSONArray paramJSONArray)
    throws JSONException
  {
    Trend[] arrayOfTrend = new Trend[paramJSONArray.length()];
    int i = 0;
    while (i < paramJSONArray.length())
    {
      arrayOfTrend[i] = new Trend(paramJSONArray.getJSONObject(i));
      i += 1;
    }
    return arrayOfTrend;
  }

  private static Date parseDate(String paramString)
    throws WeiboException
  {
    if (paramString.length() == 10)
      return new Date(Long.parseLong(paramString) * 1000L);
    return WeiboResponse.parseDate(paramString, "EEE, d MMM yyyy HH:mm:ss z");
  }

  public int compareTo(Trends paramTrends)
  {
    return this.trendAt.compareTo(paramTrends.trendAt);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof Trends))
        return false;
      paramObject = (Trends)paramObject;
      if (this.asOf != null)
      {
        if (this.asOf.equals(paramObject.asOf));
      }
      else
        while (paramObject.asOf != null)
          return false;
      if (this.trendAt != null)
      {
        if (this.trendAt.equals(paramObject.trendAt));
      }
      else
        while (paramObject.trendAt != null)
          return false;
    }
    while (Arrays.equals(this.trends, paramObject.trends));
    return false;
  }

  public Date getAsOf()
  {
    return this.asOf;
  }

  public Date getTrendAt()
  {
    return this.trendAt;
  }

  public Trend[] getTrends()
  {
    return this.trends;
  }

  public int hashCode()
  {
    int k = 0;
    int i;
    if (this.asOf != null)
    {
      i = this.asOf.hashCode();
      if (this.trendAt == null)
        break label64;
    }
    label64: for (int j = this.trendAt.hashCode(); ; j = 0)
    {
      if (this.trends != null)
        k = Arrays.hashCode(this.trends);
      return (j + i * 31) * 31 + k;
      i = 0;
      break;
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Trends{asOf=").append(this.asOf).append(", trendAt=").append(this.trendAt).append(", trends=");
    if (this.trends == null);
    for (Object localObject = null; ; localObject = Arrays.asList(this.trends))
      return localObject + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Trends
 * JD-Core Version:    0.6.2
 */