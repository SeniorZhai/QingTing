package weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Comment extends WeiboResponse
  implements Serializable
{
  private static final long serialVersionUID = 1608000492860584608L;
  private Date createdAt;
  private long id;
  private String inReplyToScreenName;
  private long inReplyToStatusId;
  private long inReplyToUserId;
  private boolean isFavorited;
  private boolean isTruncated;
  private double latitude = -1.0D;
  private double longitude = -1.0D;
  private String mid;
  private Comment replycomment = null;
  private String source;
  private Status status = null;
  private String text;
  private User user = null;

  public Comment()
  {
  }

  public Comment(String paramString)
    throws WeiboException, JSONException
  {
    paramString = new JSONObject(paramString);
    this.id = paramString.getLong("id");
    this.text = paramString.getString("text");
    this.source = paramString.getString("source");
    this.createdAt = parseDate(paramString.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
    this.status = new Status(paramString.getJSONObject("status"));
    this.user = new User(paramString.getJSONObject("user"));
    this.mid = paramString.getString("mid");
  }

  Comment(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    paramResponse = paramResponse.asJSONObject();
    try
    {
      this.id = paramResponse.getLong("id");
      this.text = paramResponse.getString("text");
      this.source = paramResponse.getString("source");
      this.mid = paramResponse.getString("mid");
      this.createdAt = parseDate(paramResponse.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
      if (!paramResponse.isNull("user"))
        this.user = new User(paramResponse.getJSONObject("user"));
      if (!paramResponse.isNull("status"))
        this.status = new Status(paramResponse.getJSONObject("status"));
      if (!paramResponse.isNull("reply_comment"))
        this.replycomment = new Comment(paramResponse.getJSONObject("reply_comment"));
      return;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + paramResponse.toString(), localJSONException);
    }
  }

  Comment(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramElement, paramWeibo);
  }

  Comment(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramResponse.asDocument().getDocumentElement(), paramWeibo);
  }

  public Comment(JSONObject paramJSONObject)
    throws WeiboException, JSONException
  {
    this.id = paramJSONObject.getLong("id");
    this.text = paramJSONObject.getString("text");
    this.source = paramJSONObject.getString("source");
    this.mid = paramJSONObject.getString("mid");
    this.createdAt = parseDate(paramJSONObject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
    if (!paramJSONObject.isNull("user"))
      this.user = new User(paramJSONObject.getJSONObject("user"));
    if (!paramJSONObject.isNull("status"))
      this.status = new Status(paramJSONObject.getJSONObject("status"));
  }

  static List<Comment> constructComments(Response paramResponse)
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
        localArrayList.add(new Comment(paramResponse.getJSONObject(i)));
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

  static List<Comment> constructComments(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    Document localDocument = paramResponse.asDocument();
    if (isRootNodeNilClasses(localDocument))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("comments", localDocument);
      NodeList localNodeList = localDocument.getDocumentElement().getElementsByTagName("comment");
      int j = localNodeList.getLength();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Comment(paramResponse, (Element)localNodeList.item(i), paramWeibo));
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

  private void init(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    ensureRootNodeNameIs("comment", paramElement);
    this.user = new User(paramResponse, (Element)paramElement.getElementsByTagName("user").item(0), paramWeibo);
    this.status = new Status(paramResponse, (Element)paramElement.getElementsByTagName("status").item(0), paramWeibo);
    this.id = getChildLong("id", paramElement);
    this.text = getChildText("text", paramElement);
    this.source = getChildText("source", paramElement);
    this.createdAt = getChildDate("created_at", paramElement);
    this.mid = getChildText("mid", paramElement);
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
      paramObject = (Comment)paramObject;
    }
    while (this.id == paramObject.id);
    return false;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
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

  public Comment getReplyComment()
  {
    return this.replycomment;
  }

  public String getSource()
  {
    return this.source;
  }

  public Status getStatus()
  {
    return this.status;
  }

  public String getText()
  {
    return this.text;
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

  public boolean isTruncated()
  {
    return this.isTruncated;
  }

  public void setMid(String paramString)
  {
    this.mid = paramString;
  }

  public String toString()
  {
    return "Comment{createdAt=" + this.createdAt + ", id=" + this.id + ", text='" + this.text + '\'' + ", source='" + this.source + '\'' + ", isTruncated=" + this.isTruncated + ", inReplyToStatusId=" + this.inReplyToStatusId + ", inReplyToUserId=" + this.inReplyToUserId + ", isFavorited=" + this.isFavorited + ", inReplyToScreenName='" + this.inReplyToScreenName + '\'' + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", user=" + this.user + ", status=" + this.status + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Comment
 * JD-Core Version:    0.6.2
 */