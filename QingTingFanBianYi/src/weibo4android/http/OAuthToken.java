package weibo4android.http;

import java.io.Serializable;
import javax.crypto.spec.SecretKeySpec;
import weibo4android.WeiboException;

abstract class OAuthToken
  implements Serializable
{
  private static final long serialVersionUID = 2385887178385032767L;
  String[] responseStr = null;
  private transient SecretKeySpec secretKeySpec;
  private String token;
  private String tokenSecret;

  OAuthToken(String paramString)
  {
    this.responseStr = paramString.split("&");
    this.tokenSecret = getParameter("oauth_token_secret");
    this.token = getParameter("oauth_token");
  }

  public OAuthToken(String paramString1, String paramString2)
  {
    this.token = paramString1;
    this.tokenSecret = paramString2;
  }

  OAuthToken(Response paramResponse)
    throws WeiboException
  {
    this(paramResponse.asString());
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    do
    {
      return true;
      if (!(paramObject instanceof OAuthToken))
        return false;
      paramObject = (OAuthToken)paramObject;
      if (this.secretKeySpec != null)
      {
        if (this.secretKeySpec.equals(paramObject.secretKeySpec));
      }
      else
        while (paramObject.secretKeySpec != null)
          return false;
      if (!this.token.equals(paramObject.token))
        return false;
    }
    while (this.tokenSecret.equals(paramObject.tokenSecret));
    return false;
  }

  public String getParameter(String paramString)
  {
    Object localObject2 = null;
    String[] arrayOfString = this.responseStr;
    int j = arrayOfString.length;
    int i = 0;
    while (true)
    {
      Object localObject1 = localObject2;
      if (i < j)
      {
        localObject1 = arrayOfString[i];
        if (((String)localObject1).startsWith(paramString + '='))
          localObject1 = localObject1.split("=")[1].trim();
      }
      else
      {
        return localObject1;
      }
      i += 1;
    }
  }

  SecretKeySpec getSecretKeySpec()
  {
    return this.secretKeySpec;
  }

  public String getToken()
  {
    return this.token;
  }

  public String getTokenSecret()
  {
    return this.tokenSecret;
  }

  public int hashCode()
  {
    int j = this.token.hashCode();
    int k = this.tokenSecret.hashCode();
    if (this.secretKeySpec != null);
    for (int i = this.secretKeySpec.hashCode(); ; i = 0)
      return i + (j * 31 + k) * 31;
  }

  void setSecretKeySpec(SecretKeySpec paramSecretKeySpec)
  {
    this.secretKeySpec = paramSecretKeySpec;
  }

  public String toString()
  {
    return "OAuthToken{token='" + this.token + '\'' + ", tokenSecret='" + this.tokenSecret + '\'' + ", secretKeySpec=" + this.secretKeySpec + '}';
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.OAuthToken
 * JD-Core Version:    0.6.2
 */