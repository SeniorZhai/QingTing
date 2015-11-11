package weibo4android;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import weibo4android.http.HTMLEntity;
import weibo4android.http.Response;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class WeiboResponse
  implements Serializable
{
  private static final boolean IS_DALVIK = Configuration.isDalvik();
  private static Map<String, SimpleDateFormat> formatMap = new HashMap();
  private static final long serialVersionUID = 3519962197957449562L;
  private transient int rateLimitLimit = -1;
  private transient int rateLimitRemaining = -1;
  private transient long rateLimitReset = -1L;

  public WeiboResponse()
  {
  }

  public WeiboResponse(Response paramResponse)
  {
    String str = paramResponse.getResponseHeader("X-RateLimit-Limit");
    if (str != null)
      this.rateLimitLimit = Integer.parseInt(str);
    str = paramResponse.getResponseHeader("X-RateLimit-Remaining");
    if (str != null)
      this.rateLimitRemaining = Integer.parseInt(str);
    paramResponse = paramResponse.getResponseHeader("X-RateLimit-Reset");
    if (paramResponse != null)
      this.rateLimitReset = Long.parseLong(paramResponse);
  }

  protected static void ensureRootNodeNameIs(String paramString, Document paramDocument)
    throws WeiboException
  {
    paramDocument = paramDocument.getDocumentElement();
    if (!paramString.equals(paramDocument.getNodeName()))
      throw new WeiboException("Unexpected root node name:" + paramDocument.getNodeName() + ". Expected:" + paramString + ". Check the availability of the Weibo API at http://open.t.sina.com.cn/");
  }

  protected static void ensureRootNodeNameIs(String paramString, Element paramElement)
    throws WeiboException
  {
    if (!paramString.equals(paramElement.getNodeName()))
      throw new WeiboException("Unexpected root node name:" + paramElement.getNodeName() + ". Expected:" + paramString + ". Check the availability of the Weibo API at http://open.t.sina.com.cn/.");
  }

  protected static void ensureRootNodeNameIs(String[] paramArrayOfString, Element paramElement)
    throws WeiboException
  {
    String str1 = paramElement.getNodeName();
    int j = paramArrayOfString.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfString[i].equals(str1))
        return;
      i += 1;
    }
    str1 = "";
    i = 0;
    while (i < paramArrayOfString.length)
    {
      String str2 = str1;
      if (i != 0)
        str2 = str1 + " or ";
      str1 = str2 + paramArrayOfString[i];
      i += 1;
    }
    throw new WeiboException("Unexpected root node name:" + paramElement.getNodeName() + ". Expected:" + str1 + ". Check the availability of the Weibo API at http://open.t.sina.com.cn/.");
  }

  protected static boolean getBoolean(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    paramString = paramJSONObject.getString(paramString);
    if ((paramString == null) || ("".equals(paramString)) || ("null".equals(paramString)))
      return false;
    return Boolean.valueOf(paramString).booleanValue();
  }

  protected static boolean getChildBoolean(String paramString, Element paramElement)
  {
    return Boolean.valueOf(getTextContent(paramString, paramElement)).booleanValue();
  }

  protected static Date getChildDate(String paramString, Element paramElement)
    throws WeiboException
  {
    return getChildDate(paramString, paramElement, "EEE MMM d HH:mm:ss z yyyy");
  }

  protected static Date getChildDate(String paramString1, Element paramElement, String paramString2)
    throws WeiboException
  {
    return parseDate(getChildText(paramString1, paramElement), paramString2);
  }

  protected static int getChildInt(String paramString, Element paramElement)
  {
    paramElement = getTextContent(paramString, paramElement);
    if ((paramElement == null) || ("".equals(paramElement)) || ("null".equals(paramString)))
      return -1;
    return Integer.valueOf(paramElement).intValue();
  }

  protected static long getChildLong(String paramString, Element paramElement)
  {
    paramElement = getTextContent(paramString, paramElement);
    if ((paramElement == null) || ("".equals(paramElement)) || ("null".equals(paramString)))
      return -1L;
    return Long.valueOf(paramElement).longValue();
  }

  protected static String getChildText(String paramString, Element paramElement)
  {
    return HTMLEntity.unescape(getTextContent(paramString, paramElement));
  }

  protected static int getInt(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    paramString = paramJSONObject.getString(paramString);
    if ((paramString == null) || ("".equals(paramString)) || ("null".equals(paramString)))
      return -1;
    return Integer.parseInt(paramString);
  }

  protected static long getLong(String paramString, JSONObject paramJSONObject)
    throws JSONException
  {
    paramString = paramJSONObject.getString(paramString);
    if ((paramString == null) || ("".equals(paramString)) || ("null".equals(paramString)))
      return -1L;
    return Long.parseLong(paramString);
  }

  // ERROR //
  protected static String getString(String paramString, JSONObject paramJSONObject, boolean paramBoolean)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_3
    //   2: aload_1
    //   3: aload_0
    //   4: invokevirtual 130	weibo4android/org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   7: astore_0
    //   8: aload_0
    //   9: astore_1
    //   10: iload_2
    //   11: ifeq +12 -> 23
    //   14: aload_0
    //   15: astore_3
    //   16: aload_0
    //   17: ldc 193
    //   19: invokestatic 199	java/net/URLDecoder:decode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   22: astore_1
    //   23: aload_1
    //   24: areturn
    //   25: astore_0
    //   26: aload_3
    //   27: areturn
    //   28: astore_1
    //   29: aload_0
    //   30: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   2	8	25	weibo4android/org/json/JSONException
    //   16	23	25	weibo4android/org/json/JSONException
    //   16	23	28	java/io/UnsupportedEncodingException
  }

  protected static String getTextContent(String paramString, Element paramElement)
  {
    paramString = paramElement.getElementsByTagName(paramString);
    if (paramString.getLength() > 0)
    {
      paramString = paramString.item(0).getFirstChild();
      if (paramString != null)
      {
        paramString = paramString.getNodeValue();
        if (paramString != null)
          return paramString;
        return "";
      }
    }
    return "";
  }

  protected static boolean isRootNodeNilClasses(Document paramDocument)
  {
    paramDocument = paramDocument.getDocumentElement().getNodeName();
    return ("nil-classes".equals(paramDocument)) || ("nilclasses".equals(paramDocument));
  }

  protected static Date parseDate(String paramString1, String arg1)
    throws WeiboException
  {
    if ((paramString1 == null) || ("".equals(paramString1)))
      return null;
    Object localObject1 = (SimpleDateFormat)formatMap.get(???);
    if (localObject1 == null)
    {
      localObject1 = new SimpleDateFormat(???, Locale.ENGLISH);
      ((SimpleDateFormat)localObject1).setTimeZone(TimeZone.getTimeZone("GMT"));
      formatMap.put(???, localObject1);
    }
    while (true)
    {
      try
      {
        synchronized ((String)localObject1)
        {
          localObject1 = ???.parse(paramString1);
          return localObject1;
        }
      }
      catch (ParseException )
      {
        throw new WeiboException("Unexpected format(" + paramString1 + ") returned from sina.com.cn");
      }
      ??? = localObject2;
    }
  }

  public int getRateLimitLimit()
  {
    return this.rateLimitLimit;
  }

  public int getRateLimitRemaining()
  {
    return this.rateLimitRemaining;
  }

  public long getRateLimitReset()
  {
    return this.rateLimitReset;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.WeiboResponse
 * JD-Core Version:    0.6.2
 */