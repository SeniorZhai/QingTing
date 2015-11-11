package weibo4android.http;

import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import weibo4android.Configuration;

public class OAuth
  implements Serializable
{
  private static final boolean DEBUG = Configuration.getDebug();
  private static final String HMAC_SHA1 = "HmacSHA1";
  private static final PostParameter OAUTH_SIGNATURE_METHOD = new PostParameter("oauth_signature_method", "HMAC-SHA1");
  private static Random RAND = new Random();
  static final long serialVersionUID = -4368426677157998618L;
  private String consumerKey = "";
  private String consumerSecret;

  public OAuth(String paramString1, String paramString2)
  {
    setConsumerKey(paramString1);
    setConsumerSecret(paramString2);
  }

  public static String constructRequestURL(String paramString)
  {
    int i = paramString.indexOf("?");
    String str1 = paramString;
    if (-1 != i)
      str1 = paramString.substring(0, i);
    i = str1.indexOf("/", 8);
    String str2 = str1.substring(0, i).toLowerCase();
    int j = str2.indexOf(":", 8);
    paramString = str2;
    if (-1 != j)
    {
      if ((!str2.startsWith("http://")) || (!str2.endsWith(":80")))
        break label107;
      paramString = str2.substring(0, j);
    }
    while (true)
    {
      return paramString + str1.substring(i);
      label107: paramString = str2;
      if (str2.startsWith("https://"))
      {
        paramString = str2;
        if (str2.endsWith(":443"))
          paramString = str2.substring(0, j);
      }
    }
  }

  public static String encode(String paramString)
  {
    StringBuffer localStringBuffer = null;
    try
    {
      paramString = URLEncoder.encode(paramString, "UTF-8");
      localStringBuffer = new StringBuffer(paramString.length());
      int i = 0;
      if (i < paramString.length())
      {
        char c = paramString.charAt(i);
        if (c == '*')
          localStringBuffer.append("%2A");
        while (true)
        {
          i += 1;
          break;
          if (c == '+')
          {
            localStringBuffer.append("%20");
          }
          else if ((c == '%') && (i + 1 < paramString.length()) && (paramString.charAt(i + 1) == '7') && (paramString.charAt(i + 2) == 'E'))
          {
            localStringBuffer.append('~');
            i += 2;
          }
          else
          {
            localStringBuffer.append(c);
          }
        }
      }
      return localStringBuffer.toString();
    }
    catch (UnsupportedEncodingException paramString)
    {
      while (true)
        paramString = localStringBuffer;
    }
  }

  public static String encodeParameters(List<PostParameter> paramList)
  {
    return encodeParameters(paramList, "&", false);
  }

  public static String encodeParameters(List<PostParameter> paramList, String paramString, boolean paramBoolean)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      PostParameter localPostParameter = (PostParameter)paramList.next();
      if (localStringBuffer.length() != 0)
      {
        if (paramBoolean)
          localStringBuffer.append("\"");
        localStringBuffer.append(paramString);
      }
      localStringBuffer.append(encode(localPostParameter.name)).append("=");
      if (paramBoolean)
        localStringBuffer.append("\"");
      localStringBuffer.append(encode(localPostParameter.value));
    }
    if ((localStringBuffer.length() != 0) && (paramBoolean))
      localStringBuffer.append("\"");
    return localStringBuffer.toString();
  }

  private void log(String paramString)
  {
    if (DEBUG)
      System.out.println("[" + new Date() + "]" + paramString);
  }

  private void log(String paramString1, String paramString2)
  {
    if (DEBUG)
      log(paramString1 + paramString2);
  }

  public static String normalizeAuthorizationHeaders(List<PostParameter> paramList)
  {
    Collections.sort(paramList);
    return encodeParameters(paramList);
  }

  public static String normalizeRequestParameters(List<PostParameter> paramList)
  {
    Collections.sort(paramList);
    return encodeParameters(paramList);
  }

  public static String normalizeRequestParameters(PostParameter[] paramArrayOfPostParameter)
  {
    return normalizeRequestParameters(toParamList(paramArrayOfPostParameter));
  }

  private void parseGetParameters(String paramString, List<PostParameter> paramList)
  {
    int i = 0;
    int j = paramString.indexOf("?");
    if (-1 != j)
      paramString = paramString.substring(j + 1).split("&");
    while (true)
    {
      try
      {
        j = paramString.length;
        if (i < j)
        {
          String[] arrayOfString = paramString[i].split("=");
          if (arrayOfString.length == 2)
            paramList.add(new PostParameter(URLDecoder.decode(arrayOfString[0], "UTF-8"), URLDecoder.decode(arrayOfString[1], "UTF-8")));
          else
            paramList.add(new PostParameter(URLDecoder.decode(arrayOfString[0], "UTF-8"), ""));
        }
      }
      catch (UnsupportedEncodingException paramString)
      {
      }
      return;
      i += 1;
    }
  }

  public static List<PostParameter> toParamList(PostParameter[] paramArrayOfPostParameter)
  {
    ArrayList localArrayList = new ArrayList(paramArrayOfPostParameter.length);
    localArrayList.addAll(Arrays.asList(paramArrayOfPostParameter));
    return localArrayList;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof OAuth))
        return false;
      paramObject = (OAuth)paramObject;
      if (this.consumerKey != null)
      {
        if (this.consumerKey.equals(paramObject.consumerKey));
      }
      else
        while (paramObject.consumerKey != null)
          return false;
      if (this.consumerSecret == null)
        break;
    }
    while (this.consumerSecret.equals(paramObject.consumerSecret));
    while (true)
    {
      return false;
      if (paramObject.consumerSecret == null)
        break;
    }
  }

  String generateAuthorizationHeader(String paramString1, String paramString2, PostParameter[] paramArrayOfPostParameter, String paramString3, String paramString4, OAuthToken paramOAuthToken)
  {
    PostParameter[] arrayOfPostParameter = paramArrayOfPostParameter;
    if (paramArrayOfPostParameter == null)
      arrayOfPostParameter = new PostParameter[0];
    paramArrayOfPostParameter = new ArrayList(5);
    paramArrayOfPostParameter.add(new PostParameter("oauth_consumer_key", this.consumerKey));
    paramArrayOfPostParameter.add(OAUTH_SIGNATURE_METHOD);
    paramArrayOfPostParameter.add(new PostParameter("oauth_timestamp", paramString4));
    paramArrayOfPostParameter.add(new PostParameter("oauth_nonce", paramString3));
    paramArrayOfPostParameter.add(new PostParameter("oauth_version", "1.0"));
    if (paramOAuthToken != null)
      paramArrayOfPostParameter.add(new PostParameter("oauth_token", paramOAuthToken.getToken()));
    paramString3 = new ArrayList(paramArrayOfPostParameter.size() + arrayOfPostParameter.length);
    paramString3.addAll(paramArrayOfPostParameter);
    paramString3.addAll(toParamList(arrayOfPostParameter));
    parseGetParameters(paramString2, paramString3);
    paramString1 = new StringBuffer(paramString1).append("&").append(encode(constructRequestURL(paramString2))).append("&");
    paramString1.append(encode(normalizeRequestParameters(paramString3)));
    paramString1 = paramString1.toString();
    log("OAuth base string:", paramString1);
    paramString1 = generateSignature(paramString1, paramOAuthToken);
    log("OAuth signature:", paramString1);
    paramArrayOfPostParameter.add(new PostParameter("oauth_signature", paramString1));
    return "OAuth " + encodeParameters(paramArrayOfPostParameter, ",", true);
  }

  String generateAuthorizationHeader(String paramString1, String paramString2, PostParameter[] paramArrayOfPostParameter, OAuthToken paramOAuthToken)
  {
    long l = System.currentTimeMillis() / 1000L;
    return generateAuthorizationHeader(paramString1, paramString2, paramArrayOfPostParameter, String.valueOf(RAND.nextInt() + l), String.valueOf(l), paramOAuthToken);
  }

  String generateSignature(String paramString)
  {
    return generateSignature(paramString, null);
  }

  String generateSignature(String paramString, OAuthToken paramOAuthToken)
  {
    Object localObject = null;
    try
    {
      Mac localMac = Mac.getInstance("HmacSHA1");
      if (paramOAuthToken == null);
      for (paramOAuthToken = new SecretKeySpec((encode(this.consumerSecret) + "&").getBytes(), "HmacSHA1"); ; paramOAuthToken = paramOAuthToken.getSecretKeySpec())
      {
        localMac.init(paramOAuthToken);
        paramString = localMac.doFinal(paramString.getBytes());
        return new BASE64Encoder().encode(paramString);
        if (paramOAuthToken.getSecretKeySpec() == null)
          paramOAuthToken.setSecretKeySpec(new SecretKeySpec((encode(this.consumerSecret) + "&" + encode(paramOAuthToken.getTokenSecret())).getBytes(), "HmacSHA1"));
      }
    }
    catch (InvalidKeyException paramString)
    {
      while (true)
      {
        paramString.printStackTrace();
        paramString = localObject;
      }
    }
    catch (NoSuchAlgorithmException paramString)
    {
      while (true)
        paramString = localObject;
    }
  }

  public int hashCode()
  {
    int j = 0;
    if (this.consumerKey != null);
    for (int i = this.consumerKey.hashCode(); ; i = 0)
    {
      if (this.consumerSecret != null)
        j = this.consumerSecret.hashCode();
      return i * 31 + j;
    }
  }

  public void setConsumerKey(String paramString)
  {
    if (paramString != null);
    while (true)
    {
      this.consumerKey = paramString;
      return;
      paramString = "";
    }
  }

  public void setConsumerSecret(String paramString)
  {
    if (paramString != null);
    while (true)
    {
      this.consumerSecret = paramString;
      return;
      paramString = "";
    }
  }

  public String toString()
  {
    return "OAuth{consumerKey='" + this.consumerKey + '\'' + ", consumerSecret='" + this.consumerSecret + '\'' + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.OAuth
 * JD-Core Version:    0.6.2
 */