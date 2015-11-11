package com.tendcloud.tenddata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class ad
{
  private static final int e = 60000;
  private static final int f = 60000;
  final String a;
  final int b;
  HttpClient c;
  String d;

  public ad(String paramString1, String paramString2, int paramInt)
  {
    this(f(paramString1), paramString2, null, paramInt);
  }

  public ad(String paramString1, String paramString2, String paramString3, int paramInt)
  {
    this(f(paramString1), paramString2, paramString3, paramInt);
  }

  public ad(X509Certificate paramX509Certificate, String paramString1, String paramString2, int paramInt)
  {
    this.a = paramString1;
    this.b = paramInt;
    this.d = paramString2;
    try
    {
      paramX509Certificate = new a(paramX509Certificate);
      paramX509Certificate.setHostnameVerifier(org.apache.http.conn.ssl.SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
      paramX509Certificate = new Scheme("https", paramX509Certificate, paramInt);
      this.c = e(paramString1);
      this.c.getConnectionManager().getSchemeRegistry().register(paramX509Certificate);
      c(this.a);
      return;
    }
    catch (KeyManagementException paramX509Certificate)
    {
      paramX509Certificate.printStackTrace();
      return;
    }
    catch (UnrecoverableKeyException paramX509Certificate)
    {
      paramX509Certificate.printStackTrace();
      return;
    }
    catch (NoSuchAlgorithmException paramX509Certificate)
    {
      paramX509Certificate.printStackTrace();
      return;
    }
    catch (KeyStoreException paramX509Certificate)
    {
      paramX509Certificate.printStackTrace();
    }
  }

  private HttpClient e(String paramString)
  {
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 60000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 60000);
    HttpConnectionParams.setTcpNoDelay((HttpParams)localObject, true);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    ((DefaultHttpClient)localObject).addRequestInterceptor(new t(this, paramString));
    return localObject;
  }

  // ERROR //
  private static X509Certificate f(String paramString)
  {
    // Byte code:
    //   0: new 131	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_0
    //   5: invokevirtual 137	java/lang/String:getBytes	()[B
    //   8: invokespecial 140	java/io/ByteArrayInputStream:<init>	([B)V
    //   11: astore_0
    //   12: ldc 142
    //   14: invokestatic 148	java/security/cert/CertificateFactory:getInstance	(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
    //   17: aload_0
    //   18: invokevirtual 152	java/security/cert/CertificateFactory:generateCertificate	(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
    //   21: checkcast 154	java/security/cert/X509Certificate
    //   24: astore_0
    //   25: aload_0
    //   26: areturn
    //   27: astore_0
    //   28: aconst_null
    //   29: areturn
    //   30: astore_0
    //   31: aload_0
    //   32: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   12	25	27	java/lang/Exception
    //   12	25	30	finally
  }

  public int a(String paramString, byte[] paramArrayOfByte)
  {
    try
    {
      paramString = new HttpPost(d(paramString));
      paramString.setEntity(new ByteArrayEntity(paramArrayOfByte));
      paramString = a().execute(paramString);
      paramString.getEntity().consumeContent();
      int i = paramString.getStatusLine().getStatusCode();
      return i;
    }
    catch (ClientProtocolException paramString)
    {
      paramString.printStackTrace();
      return 600;
    }
    catch (IOException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
    catch (URISyntaxException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  public final String a(String paramString)
  {
    StringBuilder localStringBuilder;
    try
    {
      paramString = new HttpGet(d(paramString));
      paramString = a().execute(paramString);
      try
      {
        if (paramString.getStatusLine().getStatusCode() == 200)
        {
          InputStreamReader localInputStreamReader = new InputStreamReader(paramString.getEntity().getContent());
          localStringBuilder = new StringBuilder();
          char[] arrayOfChar = new char[64];
          while (true)
          {
            int i = localInputStreamReader.read(arrayOfChar);
            if (i == -1)
              break;
            localStringBuilder.append(arrayOfChar, 0, i);
          }
        }
      }
      finally
      {
        paramString.getEntity().consumeContent();
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    while (true)
    {
      return null;
      String str = localStringBuilder.toString();
      paramString.getEntity().consumeContent();
      return str;
      paramString.getEntity().consumeContent();
    }
  }

  public final String a(String paramString, File paramFile)
  {
    MessageDigest localMessageDigest;
    try
    {
      paramString = new HttpGet(d(paramString));
      paramString = a().execute(paramString);
      try
      {
        if (paramString.getStatusLine().getStatusCode() == 200)
        {
          InputStream localInputStream = paramString.getEntity().getContent();
          paramFile = new FileOutputStream(paramFile);
          localMessageDigest = MessageDigest.getInstance("MD5");
          try
          {
            byte[] arrayOfByte = new byte[4096];
            while (true)
            {
              int i = localInputStream.read(arrayOfByte);
              if (i == -1)
                break;
              paramFile.write(arrayOfByte, 0, i);
              localMessageDigest.update(arrayOfByte, 0, i);
            }
          }
          finally
          {
            paramFile.close();
          }
        }
      }
      finally
      {
        paramString.getEntity().consumeContent();
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    while (true)
    {
      return null;
      paramFile.close();
      paramFile = x.a(localMessageDigest.digest());
      paramString.getEntity().consumeContent();
      return paramFile;
      paramString.getEntity().consumeContent();
    }
  }

  public HttpClient a()
  {
    return this.c;
  }

  public int b(String paramString)
  {
    try
    {
      paramString = new HttpPost(d(paramString));
      paramString = a().execute(paramString);
      paramString.getEntity().consumeContent();
      int i = paramString.getStatusLine().getStatusCode();
      return i;
    }
    catch (ClientProtocolException paramString)
    {
      paramString.printStackTrace();
      return 600;
    }
    catch (IOException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
    catch (URISyntaxException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  protected void c(String paramString)
  {
    try
    {
      this.d = InetAddress.getByName(paramString).getHostAddress();
      return;
    }
    catch (UnknownHostException paramString)
    {
      paramString.printStackTrace();
    }
  }

  protected URI d(String paramString)
  {
    paramString = new URL(paramString);
    return new URL(paramString.getProtocol(), this.d, paramString.getPort(), paramString.getFile()).toURI();
  }

  static class a extends org.apache.http.conn.ssl.SSLSocketFactory
  {
    SSLContext a = SSLContext.getInstance("TLS");

    a(X509Certificate paramX509Certificate)
    {
      super();
      paramX509Certificate = new ad.b(paramX509Certificate);
      this.a.init(null, new TrustManager[] { paramX509Certificate }, null);
    }

    public Socket createSocket()
    {
      return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket paramSocket, String paramString, int paramInt, boolean paramBoolean)
    {
      return this.a.getSocketFactory().createSocket(paramSocket, paramString, paramInt, paramBoolean);
    }
  }

  static class b
    implements X509TrustManager
  {
    X509Certificate a;

    b(X509Certificate paramX509Certificate)
    {
      this.a = paramX509Certificate;
    }

    public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
    }

    public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString)
    {
      int j = paramArrayOfX509Certificate.length;
      int i = 0;
      while (i < j)
      {
        if (paramArrayOfX509Certificate[i].equals(this.a))
          return;
        i += 1;
      }
      throw new CertificateException("no trusted cert");
    }

    public X509Certificate[] getAcceptedIssuers()
    {
      return null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tendcloud.tenddata.ad
 * JD-Core Version:    0.6.2
 */