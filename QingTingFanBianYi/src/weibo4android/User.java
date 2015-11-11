package weibo4android;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
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

public class User extends WeiboResponse
  implements Serializable
{
  static final String[] POSSIBLE_ROOT_NAMES = { "user", "sender", "recipient", "retweeting_user" };
  private static final long serialVersionUID = 3473349966713163765L;
  private boolean allowAllActMsg;
  private String avatarlarge;
  private int city;
  private Date createdAt;
  private String description;
  private int favouritesCount;
  private int followersCount;
  private boolean following;
  private int friendsCount;
  private String gender;
  private boolean geoEnabled;
  private long id;
  private String location;
  private String name = "";
  private String profileImageUrl;
  private int province;
  private String screenName;
  private Status status = null;
  private int statusesCount;
  private String url;
  private String userDomain;
  private boolean verified;
  private String verified_type = "";
  private Weibo weibo;

  User(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramElement, paramWeibo);
  }

  User(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    init(paramResponse, paramResponse.asDocument().getDocumentElement(), paramWeibo);
  }

  public User(JSONObject paramJSONObject)
    throws WeiboException
  {
    init(paramJSONObject);
  }

  static List<User> constructResult(Response paramResponse)
    throws WeiboException
  {
    JSONArray localJSONArray = paramResponse.asJSONArray();
    try
    {
      int j = localJSONArray.length();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (true)
      {
        paramResponse = localArrayList;
        if (i >= j)
          break;
        localArrayList.add(new User(localJSONArray.getJSONObject(i)));
        i += 1;
      }
    }
    catch (JSONException paramResponse)
    {
      paramResponse = null;
    }
    return paramResponse;
  }

  public static List<User> constructUser(Response paramResponse)
    throws WeiboException
  {
    paramResponse = paramResponse.asJSONObject();
    ArrayList localArrayList;
    try
    {
      paramResponse = paramResponse.getJSONArray("users");
      int j = paramResponse.length();
      localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new User(paramResponse.getJSONObject(i)));
        i += 1;
      }
    }
    catch (JSONException paramResponse)
    {
      throw new WeiboException(paramResponse);
    }
    return localArrayList;
  }

  public static List<User> constructUsers(Response paramResponse)
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
        localArrayList.add(new User(paramResponse.getJSONObject(i)));
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

  public static List<User> constructUsers(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    Document localDocument = paramResponse.asDocument();
    if (isRootNodeNilClasses(localDocument))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("users", localDocument);
      NodeList localNodeList = localDocument.getDocumentElement().getChildNodes();
      ArrayList localArrayList = new ArrayList(localNodeList.getLength());
      int i = 0;
      while (i < localNodeList.getLength())
      {
        Node localNode = localNodeList.item(i);
        if (localNode.getNodeName().equals("user"))
          localArrayList.add(new User(paramResponse, (Element)localNode, paramWeibo));
        i += 1;
      }
      return localArrayList;
    }
    catch (WeiboException paramResponse)
    {
      if (isRootNodeNilClasses(localDocument))
        return new ArrayList(0);
    }
    throw paramResponse;
  }

  public static UserWapper constructWapperUsers(Response paramResponse)
    throws WeiboException
  {
    paramResponse = paramResponse.asJSONObject();
    try
    {
      JSONArray localJSONArray = paramResponse.getJSONArray("users");
      int j = localJSONArray.length();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new User(localJSONArray.getJSONObject(i)));
        i += 1;
      }
      long l3 = paramResponse.getLong("previous_curosr");
      long l2 = paramResponse.getLong("next_cursor");
      long l1 = l2;
      if (l2 == -1L)
        l1 = paramResponse.getLong("nextCursor");
      paramResponse = new UserWapper(localArrayList, l3, l1);
      return paramResponse;
    }
    catch (JSONException paramResponse)
    {
    }
    throw new WeiboException(paramResponse);
  }

  public static UserWapper constructWapperUsers(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    Document localDocument = paramResponse.asDocument();
    if (isRootNodeNilClasses(localDocument))
      return new UserWapper(new ArrayList(0), 0L, 0L);
    while (true)
    {
      int i;
      try
      {
        ensureRootNodeNameIs("users_list", localDocument);
        Element localElement = localDocument.getDocumentElement();
        NodeList localNodeList = localElement.getElementsByTagName("users");
        if (localNodeList.getLength() == 0)
          return new UserWapper(new ArrayList(0), 0L, 0L);
        localNodeList = ((Element)localNodeList.item(0)).getChildNodes();
        ArrayList localArrayList = new ArrayList(localNodeList.getLength());
        i = 0;
        if (i < localNodeList.getLength())
        {
          Node localNode = localNodeList.item(i);
          if (localNode.getNodeName().equals("user"))
            localArrayList.add(new User(paramResponse, (Element)localNode, paramWeibo));
        }
        else
        {
          long l3 = getChildLong("previous_curosr", localElement);
          long l2 = getChildLong("next_curosr", localElement);
          long l1 = l2;
          if (l2 == -1L)
            l1 = getChildLong("nextCurosr", localElement);
          paramResponse = new UserWapper(localArrayList, l3, l1);
          return paramResponse;
        }
      }
      catch (WeiboException paramResponse)
      {
        if (isRootNodeNilClasses(localDocument))
          return new UserWapper(new ArrayList(0), 0L, 0L);
        throw paramResponse;
      }
      i += 1;
    }
  }

  private void init(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    this.weibo = paramWeibo;
    ensureRootNodeNameIs(POSSIBLE_ROOT_NAMES, paramElement);
    this.id = getChildLong("id", paramElement);
    this.name = getChildText("name", paramElement);
    this.screenName = getChildText("screen_name", paramElement);
    this.location = getChildText("location", paramElement);
    this.description = getChildText("description", paramElement);
    this.profileImageUrl = getChildText("profile_image_url", paramElement);
    this.avatarlarge = getChildText("avatar_large", paramElement);
    this.url = getChildText("url", paramElement);
    this.allowAllActMsg = getChildBoolean("allow_all_act_msg", paramElement);
    this.followersCount = getChildInt("followers_count", paramElement);
    this.friendsCount = getChildInt("friends_count", paramElement);
    this.createdAt = getChildDate("created_at", paramElement);
    this.favouritesCount = getChildInt("favourites_count", paramElement);
    this.following = getChildBoolean("following", paramElement);
    this.statusesCount = getChildInt("statuses_count", paramElement);
    this.geoEnabled = getChildBoolean("geo_enabled", paramElement);
    this.verified = getChildBoolean("verified", paramElement);
    setVerified_type(getChildText("verified_type", paramElement));
    this.userDomain = getChildText("domain", paramElement);
    this.gender = getChildText("gender", paramElement);
    this.province = getChildInt("province", paramElement);
    this.city = getChildInt("city", paramElement);
    this.status = new Status(paramResponse, (Element)paramElement.getElementsByTagName("status").item(0), paramWeibo);
  }

  private void init(JSONObject paramJSONObject)
    throws WeiboException
  {
    if (paramJSONObject != null);
    try
    {
      this.id = paramJSONObject.getLong("id");
      this.name = paramJSONObject.getString("name");
      this.screenName = paramJSONObject.getString("screen_name");
      this.location = paramJSONObject.getString("location");
      this.description = paramJSONObject.getString("description");
      this.profileImageUrl = paramJSONObject.getString("profile_image_url");
      this.avatarlarge = paramJSONObject.getString("avatar_large");
      this.url = paramJSONObject.getString("url");
      this.allowAllActMsg = paramJSONObject.getBoolean("allow_all_act_msg");
      this.followersCount = paramJSONObject.getInt("followers_count");
      this.friendsCount = paramJSONObject.getInt("friends_count");
      this.createdAt = parseDate(paramJSONObject.getString("created_at"), "EEE MMM dd HH:mm:ss z yyyy");
      this.favouritesCount = paramJSONObject.getInt("favourites_count");
      this.following = getBoolean("following", paramJSONObject);
      this.verified = getBoolean("verified", paramJSONObject);
      setVerified_type(paramJSONObject.getString("verified_type"));
      this.statusesCount = paramJSONObject.getInt("statuses_count");
      this.userDomain = paramJSONObject.getString("domain");
      this.gender = paramJSONObject.getString("gender");
      this.province = paramJSONObject.getInt("province");
      this.city = paramJSONObject.getInt("city");
      if (!paramJSONObject.isNull("status"))
        setStatus(new Status(paramJSONObject.getJSONObject("status")));
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
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      paramObject = (User)paramObject;
    }
    while (this.id == paramObject.id);
    return false;
  }

  public URL getAvatarLargeURL()
  {
    try
    {
      URL localURL = new URL(this.avatarlarge);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
    return null;
  }

  public int getCity()
  {
    return this.city;
  }

  public Date getCreatedAt()
  {
    return this.createdAt;
  }

  public String getDescription()
  {
    return this.description;
  }

  public int getFavouritesCount()
  {
    return this.favouritesCount;
  }

  public int getFollowersCount()
  {
    return this.followersCount;
  }

  public int getFriendsCount()
  {
    return this.friendsCount;
  }

  public String getGender()
  {
    return this.gender;
  }

  public long getId()
  {
    return this.id;
  }

  public String getLocation()
  {
    return this.location;
  }

  public String getName()
  {
    return this.name;
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

  public int getProvince()
  {
    return this.province;
  }

  public String getScreenName()
  {
    return this.screenName;
  }

  public Status getStatus()
  {
    return this.status;
  }

  public int getStatusesCount()
  {
    return this.statusesCount;
  }

  public URL getURL()
  {
    try
    {
      URL localURL = new URL(this.url);
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
    return null;
  }

  public String getUserDomain()
  {
    return this.userDomain;
  }

  public String getVerified_type()
  {
    return this.verified_type;
  }

  public int hashCode()
  {
    return (int)(this.id ^ this.id >>> 32) + 31;
  }

  public boolean isAllowAllActMsg()
  {
    return this.allowAllActMsg;
  }

  public boolean isFollowing()
  {
    return this.following;
  }

  public boolean isGeoEnabled()
  {
    return this.geoEnabled;
  }

  public boolean isVerified()
  {
    return this.verified;
  }

  public void setStatus(Status paramStatus)
  {
    this.status = paramStatus;
  }

  public void setVerified_type(String paramString)
  {
    this.verified_type = paramString;
  }

  public String toString()
  {
    return "User{weibo=" + this.weibo + ", id=" + this.id + ", name='" + this.name + '\'' + ", screenName='" + this.screenName + '\'' + ", location='" + this.location + '\'' + ", description='" + this.description + '\'' + ", profileImageUrl='" + this.profileImageUrl + '\'' + ", avatarlarge ='" + this.avatarlarge + '\'' + ", province='" + this.province + '\'' + ", city='" + this.city + '\'' + ", domain ='" + this.userDomain + '\'' + ", gender ='" + this.gender + '\'' + ", url='" + this.url + '\'' + ", allowAllActMsg=" + this.allowAllActMsg + ", followersCount=" + this.followersCount + ", friendsCount=" + this.friendsCount + ", createdAt=" + this.createdAt + ", favouritesCount=" + this.favouritesCount + ", following=" + this.following + ", statusesCount=" + this.statusesCount + ", geoEnabled=" + this.geoEnabled + ", verified=" + this.verified + ", status=" + this.status + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.User
 * JD-Core Version:    0.6.2
 */