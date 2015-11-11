package weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Status extends WeiboResponse
  implements Serializable
{
  private static final long serialVersionUID = -8795691786466526420L;
  private String bmiddle_pic;
  private int comments_count;
  private Date createdAt;
  private int dataType = 1;
  private long id;
  private String inReplyToScreenName;
  private long inReplyToStatusId;
  private long inReplyToUserId;
  private boolean isFavorited;
  private boolean isTruncated;
  private double latitude = -1.0D;
  private double longitude = -1.0D;
  private String mid;
  private String original_pic;
  private int reposts_count;
  private Status retweeted_status;
  private String source;
  private String text;
  private String thumbnail_pic;
  private User user = null;

  public Status()
  {
  }

  public Status(String paramString)
    throws WeiboException, JSONException
  {
    constructJson(new JSONObject(paramString));
  }

  Status(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    constructJson(paramResponse.asJSONObject());
  }

  Status(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramElement, paramWeibo);
  }

  Status(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramResponse.asDocument().getDocumentElement(), paramWeibo);
  }

  public Status(JSONObject paramJSONObject)
    throws WeiboException, JSONException
  {
    constructJson(paramJSONObject);
  }

  private void constructJson(JSONObject paramJSONObject)
    throws WeiboException
  {
    try
    {
      this.id = paramJSONObject.getLong("id");
      this.text = paramJSONObject.getString("text");
      this.source = paramJSONObject.getString("source");
      this.createdAt = parseDate(paramJSONObject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
      this.inReplyToStatusId = getLong("in_reply_to_status_id", paramJSONObject);
      this.inReplyToUserId = getLong("in_reply_to_user_id", paramJSONObject);
      this.isFavorited = getBoolean("favorited", paramJSONObject);
      this.thumbnail_pic = paramJSONObject.getString("thumbnail_pic");
      this.bmiddle_pic = paramJSONObject.getString("bmiddle_pic");
      this.original_pic = paramJSONObject.getString("original_pic");
      if (!paramJSONObject.isNull("user"))
        this.user = new User(paramJSONObject.getJSONObject("user"));
      this.inReplyToScreenName = paramJSONObject.getString("inReplyToScreenName");
      if (!paramJSONObject.isNull("retweeted_status"))
        this.retweeted_status = new Status(paramJSONObject.getJSONObject("retweeted_status"));
      this.mid = paramJSONObject.getString("mid");
      this.reposts_count = paramJSONObject.getInt("reposts_count");
      this.comments_count = paramJSONObject.getInt("comments_count");
      String str = paramJSONObject.getString("geo");
      if ((str != null) && (!"".equals(str)) && (!"null".equals(str)))
        getGeoInfo(str);
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramJSONObject.toString(), localJSONException);
    }
  }

  static List<Status> constructStatuses(Response paramResponse)
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
        localArrayList.add(new Status(paramResponse.getJSONObject(i)));
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

  static List<Status> constructStatuses(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    Document localDocument = paramResponse.asDocument();
    if (isRootNodeNilClasses(localDocument))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("statuses", localDocument);
      NodeList localNodeList = localDocument.getDocumentElement().getElementsByTagName("status");
      int j = localNodeList.getLength();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Status(paramResponse, (Element)localNodeList.item(i), paramWeibo));
        i += 1;
      }
      return localArrayList;
    }
    catch (WeiboException paramResponse)
    {
      ensureRootNodeNameIs("nil-classes", localDocument);
    }
    return new ArrayList(0);
  }

  private void getGeoInfo(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramString = paramString.toCharArray();
    int j = paramString.length;
    int i = 0;
    while (i < j)
    {
      char c = paramString[i];
      if ((c > '-') && (c < ':'))
        localStringBuffer.append(c);
      if ((c == ',') && (localStringBuffer.length() > 0))
      {
        this.latitude = Double.parseDouble(localStringBuffer.toString());
        localStringBuffer.delete(0, localStringBuffer.length());
      }
      i += 1;
    }
    this.longitude = Double.parseDouble(localStringBuffer.toString());
  }

  private void init(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    ensureRootNodeNameIs("status", paramElement);
    this.user = new User(paramResponse, (Element)paramElement.getElementsByTagName("user").item(0), paramWeibo);
    this.id = getChildLong("id", paramElement);
    this.text = getChildText("text", paramElement);
    this.source = getChildText("source", paramElement);
    this.createdAt = getChildDate("created_at", paramElement);
    this.isTruncated = getChildBoolean("truncated", paramElement);
    this.inReplyToStatusId = getChildLong("in_reply_to_status_id", paramElement);
    this.inReplyToUserId = getChildLong("in_reply_to_user_id", paramElement);
    this.isFavorited = getChildBoolean("favorited", paramElement);
    this.inReplyToScreenName = getChildText("in_reply_to_screen_name", paramElement);
    this.reposts_count = getChildInt("reposts_count", paramElement);
    this.comments_count = getChildInt("comments_count", paramElement);
    Object localObject = paramElement.getElementsByTagName("georss:point");
    if (1 == ((NodeList)localObject).getLength())
    {
      localObject = ((NodeList)localObject).item(0).getFirstChild().getNodeValue().split(" ");
      if (!"null".equals(localObject[0]))
        this.latitude = Double.parseDouble(localObject[0]);
      if (!"null".equals(localObject[1]))
        this.longitude = Double.parseDouble(localObject[1]);
    }
    paramElement = paramElement.getElementsByTagName("retweet_details");
    if (1 == paramElement.getLength())
      this.retweeted_status = new Status(paramResponse, (Element)paramElement.item(0), paramWeibo);
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
      paramObject = (Status)paramObject;
    }
    while (this.id == paramObject.id);
    return false;
  }

  public String getBmiddle_pic()
  {
    return this.bmiddle_pic;
  }

  public int getComments_count()
  {
    return this.comments_count;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
  }

  public int getDataType()
  {
    return this.dataType;
  }

  public long getId()
  {
    return this.id;
  }

  public String getInReplyToScreenName()
  {
    return this.inReplyToScreenName;
  }

  public long getInReplyToStatusId()
  {
    return this.inReplyToStatusId;
  }

  public long getInReplyToUserId()
  {
    return this.inReplyToUserId;
  }

  public double getLatitude()
  {
    return this.latitude;
  }

  public double getLongitude()
  {
    return this.longitude;
  }

  public String getMid()
  {
    return this.mid;
  }

  public String getOriginal_pic()
  {
    return this.original_pic;
  }

  public int getReposts_count()
  {
    return this.reposts_count;
  }

  public Status getRetweeted_status()
  {
    return this.retweeted_status;
  }

  public String getSource()
  {
    return this.source;
  }

  public String getText()
  {
    return this.text;
  }

  public String getThumbnail_pic()
  {
    return this.thumbnail_pic;
  }

  public User getUser()
  {
    return this.user;
  }

  public int hashCode()
  {
    return (int)(this.id ^ this.id >>> 32) + 31;
  }

  public boolean isFavorited()
  {
    return this.isFavorited;
  }

  public boolean isRetweet()
  {
    return this.retweeted_status != null;
  }

  public boolean isTruncated()
  {
    return this.isTruncated;
  }

  public void setBmiddle_pic(String paramString)
  {
    this.bmiddle_pic = paramString;
  }

  public void setComments_count(int paramInt)
  {
    this.comments_count = paramInt;
  }

  public void setCreatedAt(Date paramDate)
  {
    this.createdAt = paramDate;
  }

  public void setDataType(int paramInt)
  {
    this.dataType = paramInt;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setMid(String paramString)
  {
    this.mid = paramString;
  }

  public void setOriginal_pic(String paramString)
  {
    this.original_pic = paramString;
  }

  public void setReposts_count(int paramInt)
  {
    this.reposts_count = paramInt;
  }

  public void setSource(String paramString)
  {
    this.source = paramString;
  }

  public void setText(String paramString)
  {
    this.text = paramString;
  }

  public void setThumbnail_pic(String paramString)
  {
    this.thumbnail_pic = paramString;
  }

  public void setUser(User paramUser)
  {
    this.user = paramUser;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder().append("Status [createdAt=").append(this.createdAt).append(", id=").append(this.id).append(", text=").append(this.text).append(", source=").append(this.source).append(", isTruncated=").append(this.isTruncated).append(", inReplyToStatusId=").append(this.inReplyToStatusId).append(", inReplyToUserId=").append(this.inReplyToUserId).append(", isFavorited=").append(this.isFavorited).append(", inReplyToScreenName=").append(this.inReplyToScreenName).append(", latitude=").append(this.latitude).append(", longitude=").append(this.longitude).append(", thumbnail_pic=").append(this.thumbnail_pic).append(", bmiddle_pic=").append(this.bmiddle_pic).append(", original_pic=").append(this.original_pic).append(",  mid=").append(this.mid).append(", user=").append(this.user).append(", retweeted_status=");
    if (this.retweeted_status == null);
    for (String str = "null"; ; str = this.retweeted_status.toString())
      return str + "]";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Status
 * JD-Core Version:    0.6.2
 */