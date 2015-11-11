package weibo4android;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Tag extends WeiboResponse
  implements Serializable
{
  private static final long serialVersionUID = 2177657076940291492L;
  private String id;
  private String value;

  public Tag(Response paramResponse, Element paramElement)
    throws WeiboException
  {
    ensureRootNodeNameIs("tag", paramElement);
    this.id = getChildText("id", paramElement);
    this.value = getChildText("value", paramElement);
  }

  public Tag(Response paramResponse, Element paramElement, Weibo paramWeibo)
    throws WeiboException
  {
    ensureRootNodeNameIs("tagid", paramElement);
    this.id = paramElement.getNodeName();
    this.value = paramElement.getNodeValue();
  }

  public Tag(Response paramResponse, Element paramElement, Weibo paramWeibo, String paramString)
    throws WeiboException
  {
    ensureRootNodeNameIs("tagid", paramElement);
    this.id = paramElement.getNodeName();
    this.value = paramElement.getNodeValue();
  }

  public Tag(JSONObject paramJSONObject)
    throws WeiboException, JSONException
  {
    if ((paramJSONObject.getString("id") != null) && (paramJSONObject.getString("id").length() != 0));
    for (this.id = paramJSONObject.getString("id"); ; this.id = paramJSONObject.getString("tagid"))
      do
      {
        if ((paramJSONObject.getString("value") != null) && (paramJSONObject.getString("value").length() != 0))
          this.value = paramJSONObject.getString("value");
        return;
      }
      while ((paramJSONObject.getString("tagid") == null) || (paramJSONObject.getString("tagid").length() == 0));
  }

  static List<Tag> constructTags(Response paramResponse)
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
        localArrayList.add(new Tag(paramResponse.getJSONObject(i)));
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

  public static List<Tag> constructTags(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    paramWeibo = paramResponse.asDocument();
    if (isRootNodeNilClasses(paramWeibo))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("tags", paramWeibo);
      NodeList localNodeList = paramWeibo.getDocumentElement().getElementsByTagName("tag");
      int j = localNodeList.getLength();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Tag(paramResponse, (Element)localNodeList.item(i)));
        i += 1;
      }
      return localArrayList;
    }
    catch (WeiboException paramResponse)
    {
      ensureRootNodeNameIs("nil-classes", paramWeibo);
    }
    return new ArrayList(0);
  }

  public static List<Tag> createTags(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    paramWeibo = paramResponse.asDocument();
    if (isRootNodeNilClasses(paramWeibo))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("tagids", paramWeibo);
      NodeList localNodeList = paramWeibo.getDocumentElement().getElementsByTagName("tagid");
      int j = localNodeList.getLength();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Tag(paramResponse, (Element)localNodeList.item(i), null));
        i += 1;
      }
      return localArrayList;
    }
    catch (WeiboException paramResponse)
    {
      ensureRootNodeNameIs("nil-classes", paramWeibo);
    }
    return new ArrayList(0);
  }

  public static List<Tag> destroyTags(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    paramWeibo = paramResponse.asDocument();
    if (isRootNodeNilClasses(paramWeibo))
      return new ArrayList(0);
    try
    {
      ensureRootNodeNameIs("tags", paramWeibo);
      NodeList localNodeList = paramWeibo.getDocumentElement().getElementsByTagName("tagid");
      int j = localNodeList.getLength();
      ArrayList localArrayList = new ArrayList(j);
      int i = 0;
      while (i < j)
      {
        localArrayList.add(new Tag(paramResponse, (Element)localNodeList.item(i), null, null));
        i += 1;
      }
      return localArrayList;
    }
    catch (WeiboException paramResponse)
    {
      ensureRootNodeNameIs("nil-classes", paramWeibo);
    }
    return new ArrayList(0);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        paramObject = (Tag)paramObject;
        if (this.id == null)
        {
          if (paramObject.id != null)
            return false;
        }
        else if (!this.id.equals(paramObject.id))
          return false;
        if (this.value != null)
          break;
      }
      while (paramObject.value == null);
      return false;
    }
    while (this.value.equals(paramObject.value));
    return false;
  }

  public String getId()
  {
    return this.id;
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    int j = 0;
    int i;
    if (this.id == null)
    {
      i = 0;
      if (this.value != null)
        break label39;
    }
    while (true)
    {
      return (i + 31) * 31 + j;
      i = this.id.hashCode();
      break;
      label39: j = this.value.hashCode();
    }
  }

  public String toString()
  {
    return "tags{ " + this.id + "," + this.value + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Tag
 * JD-Core Version:    0.6.2
 */