package weibo4android.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import weibo4android.Configuration;
import weibo4android.WeiboException;
import weibo4android.org.json.JSONArray;
import weibo4android.org.json.JSONException;
import weibo4android.org.json.JSONObject;

public class Response
{
  private static final boolean DEBUG = Configuration.getDebug();
  private static ThreadLocal<DocumentBuilder> builders = new ThreadLocal()
  {
    protected DocumentBuilder initialValue()
    {
      try
      {
        DocumentBuilder localDocumentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
        return localDocumentBuilder;
      }
      catch (ParserConfigurationException localParserConfigurationException)
      {
        throw new ExceptionInInitializerError(localParserConfigurationException);
      }
    }
  };
  private static Pattern escaped = Pattern.compile("&#([0-9]{3,5});");
  private HttpURLConnection con;
  private InputStream is;
  private Document responseAsDocument = null;
  private String responseAsString = null;
  private int statusCode;
  private boolean streamConsumed = false;

  public Response()
  {
  }

  Response(String paramString)
  {
    this.responseAsString = paramString;
  }

  public Response(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    this.con = paramHttpURLConnection;
    this.statusCode = paramHttpURLConnection.getResponseCode();
    InputStream localInputStream = paramHttpURLConnection.getErrorStream();
    this.is = localInputStream;
    if (localInputStream == null)
      this.is = paramHttpURLConnection.getInputStream();
    if ((this.is != null) && ("gzip".equals(paramHttpURLConnection.getContentEncoding())))
      this.is = new GZIPInputStream(this.is);
  }

  private void log(String paramString)
  {
    if (DEBUG)
      System.out.println("[" + new Date() + "]" + paramString);
  }

  public static String unescape(String paramString)
  {
    paramString = escaped.matcher(paramString);
    StringBuffer localStringBuffer = new StringBuffer();
    while (paramString.find())
      paramString.appendReplacement(localStringBuffer, Character.toString((char)Integer.parseInt(paramString.group(1), 10)));
    paramString.appendTail(localStringBuffer);
    return localStringBuffer.toString();
  }

  public Document asDocument()
    throws WeiboException
  {
    if (this.responseAsDocument == null);
    try
    {
      this.responseAsDocument = ((DocumentBuilder)builders.get()).parse(new ByteArrayInputStream(asString().getBytes("UTF-8")));
      return this.responseAsDocument;
    }
    catch (SAXException localSAXException)
    {
      throw new WeiboException("The response body was not well-formed:\n" + this.responseAsString, localSAXException);
    }
    catch (IOException localIOException)
    {
      throw new WeiboException("There's something with the connection.", localIOException);
    }
  }

  public JSONArray asJSONArray()
    throws WeiboException
  {
    try
    {
      JSONArray localJSONArray = new JSONArray(asString());
      return localJSONArray;
    }
    catch (Exception localException)
    {
      throw new WeiboException(localException.getMessage() + ":" + this.responseAsString, localException);
    }
  }

  public JSONObject asJSONObject()
    throws WeiboException
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(asString());
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      throw new WeiboException(localJSONException.getMessage() + ":" + this.responseAsString, localJSONException);
    }
  }

  public InputStreamReader asReader()
  {
    try
    {
      InputStreamReader localInputStreamReader = new InputStreamReader(this.is, "UTF-8");
      return localInputStreamReader;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return new InputStreamReader(this.is);
  }

  public InputStream asStream()
  {
    if (this.streamConsumed)
      throw new IllegalStateException("Stream has already been consumed.");
    return this.is;
  }

  public String asString()
    throws WeiboException
  {
    if (this.responseAsString == null);
    try
    {
      InputStream localInputStream = asStream();
      if (localInputStream == null)
        return null;
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream, "UTF-8"));
      localStringBuffer = new StringBuffer();
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuffer.append(str).append("\n");
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      StringBuffer localStringBuffer;
      throw new WeiboException(localNullPointerException.getMessage(), localNullPointerException);
      this.responseAsString = localStringBuffer.toString();
      if (Configuration.isDalvik())
        this.responseAsString = unescape(this.responseAsString);
      log(this.responseAsString);
      localNullPointerException.close();
      this.con.disconnect();
      this.streamConsumed = true;
      return this.responseAsString;
    }
    catch (IOException localIOException)
    {
      throw new WeiboException(localIOException.getMessage(), localIOException);
    }
  }

  public void disconnect()
  {
    this.con.disconnect();
  }

  public String getResponseAsString()
  {
    return this.responseAsString;
  }

  public String getResponseHeader(String paramString)
  {
    if (this.con != null)
      return this.con.getHeaderField(paramString);
    return null;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public void setResponseAsString(String paramString)
  {
    this.responseAsString = paramString;
  }

  public void setStatusCode(int paramInt)
  {
    this.statusCode = paramInt;
  }

  public String toString()
  {
    if (this.responseAsString != null)
      return this.responseAsString;
    return "Response{statusCode=" + this.statusCode + ", response=" + this.responseAsDocument + ", responseString='" + this.responseAsString + '\'' + ", is=" + this.is + ", con=" + this.con + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.Response
 * JD-Core Version:    0.6.2
 */