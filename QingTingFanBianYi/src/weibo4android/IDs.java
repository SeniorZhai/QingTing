package weibo4android;

import java.util.Arrays;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import weibo4android.http.Response;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class IDs extends WeiboResponse
{
  private static String[] ROOT_NODE_NAMES = { "id_list", "ids" };
  private static final long serialVersionUID = -6585026560164704953L;
  private long[] ids;
  private long nextCursor;
  private long previousCursor;

  IDs(Response paramResponse)
    throws WeiboException
  {
    super(paramResponse);
    paramResponse = paramResponse.asDocument().getDocumentElement();
    ensureRootNodeNameIs(ROOT_NODE_NAMES, paramResponse);
    NodeList localNodeList = paramResponse.getElementsByTagName("id");
    this.ids = new long[localNodeList.getLength()];
    int i = 0;
    while (i < localNodeList.getLength())
      try
      {
        this.ids[i] = Long.parseLong(localNodeList.item(i).getFirstChild().getNodeValue());
        i += 1;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw new WeiboException("Weibo API returned malformed response: " + paramResponse, localNumberFormatException);
      }
    this.previousCursor = getChildLong("previous_cursor", paramResponse);
    this.nextCursor = getChildLong("next_cursor", paramResponse);
  }

  IDs(Response paramResponse, Weibo paramWeibo)
    throws WeiboException
  {
    super(paramResponse);
    if ("[]\n".equals(paramResponse.asString()))
    {
      this.previousCursor = 0L;
      this.nextCursor = 0L;
      this.ids = new long[0];
    }
    while (true)
    {
      return;
      paramResponse = paramResponse.asJSONObject();
      try
      {
        this.previousCursor = paramResponse.getLong("previous_cursor");
        this.nextCursor = paramResponse.getLong("next_cursor");
        if (!paramResponse.isNull("ids"))
        {
          paramResponse = paramResponse.getJSONArray("ids");
          int j = paramResponse.length();
          this.ids = new long[j];
          while (i < j)
          {
            this.ids[i] = paramResponse.getLong(i);
            i += 1;
          }
        }
      }
      catch (JSONException paramResponse)
      {
      }
    }
    throw new WeiboException(paramResponse);
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof IDs))
        return false;
      paramObject = (IDs)paramObject;
    }
    while (Arrays.equals(this.ids, paramObject.ids));
    return false;
  }

  public long[] getIDs()
  {
    return this.ids;
  }

  public long getNextCursor()
  {
    return this.nextCursor;
  }

  public long getPreviousCursor()
  {
    return this.previousCursor;
  }

  public boolean hasNext()
  {
    return 0L != this.nextCursor;
  }

  public boolean hasPrevious()
  {
    return 0L != this.previousCursor;
  }

  public int hashCode()
  {
    if (this.ids != null)
      return Arrays.hashCode(this.ids);
    return 0;
  }

  public String toString()
  {
    return "IDs{ids=" + this.ids + ", previousCursor=" + this.previousCursor + ", nextCursor=" + this.nextCursor + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.IDs
 * JD-Core Version:    0.6.2
 */