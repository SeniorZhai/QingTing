package u.aly;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Build.VERSION;
import com.umeng.analytics.AnalyticsConfig;
import com.umeng.analytics.a;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class r
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  private final int d = 1;
  private String e;
  private String f = "10.0.0.172";
  private int g = 80;
  private Context h;
  private w i;
  private f j;

  public r(Context paramContext)
  {
    this.h = paramContext;
    this.j = h.b(paramContext);
    this.e = a(paramContext);
  }

  private String a(Context paramContext)
  {
    StringBuffer localStringBuffer1 = new StringBuffer();
    localStringBuffer1.append("Android");
    localStringBuffer1.append("/");
    localStringBuffer1.append("5.2.4");
    localStringBuffer1.append(" ");
    try
    {
      StringBuffer localStringBuffer2 = new StringBuffer();
      localStringBuffer2.append(bi.v(paramContext));
      localStringBuffer2.append("/");
      localStringBuffer2.append(bi.d(paramContext));
      localStringBuffer2.append(" ");
      localStringBuffer2.append(Build.MODEL);
      localStringBuffer2.append("/");
      localStringBuffer2.append(Build.VERSION.RELEASE);
      localStringBuffer2.append(" ");
      localStringBuffer2.append(bv.a(AnalyticsConfig.getAppkey(paramContext)));
      localStringBuffer1.append(URLEncoder.encode(localStringBuffer2.toString(), "UTF-8"));
      return localStringBuffer1.toString();
    }
    catch (Exception paramContext)
    {
      while (true)
        paramContext.printStackTrace();
    }
  }

  private boolean a()
  {
    if (this.h.getPackageManager().checkPermission("android.permission.ACCESS_NETWORK_STATE", this.h.getPackageName()) != 0)
      return false;
    try
    {
      Object localObject = ((ConnectivityManager)this.h.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localObject != null) && (((NetworkInfo)localObject).getType() != 1))
      {
        localObject = ((NetworkInfo)localObject).getExtraInfo();
        if (localObject != null)
          if ((!((String)localObject).equals("cmwap")) && (!((String)localObject).equals("3gwap")))
          {
            boolean bool = ((String)localObject).equals("uniwap");
            if (!bool);
          }
          else
          {
            return true;
          }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  private byte[] a(byte[] paramArrayOfByte, String paramString)
  {
    HttpPost localHttpPost = new HttpPost(paramString);
    Object localObject = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout((HttpParams)localObject, 10000);
    HttpConnectionParams.setSoTimeout((HttpParams)localObject, 30000);
    localObject = new DefaultHttpClient((HttpParams)localObject);
    localHttpPost.addHeader("X-Umeng-Sdk", this.e);
    localHttpPost.addHeader("Msg-Type", "envelope");
    try
    {
      if (a())
      {
        HttpHost localHttpHost = new HttpHost(this.f, this.g);
        ((HttpClient)localObject).getParams().setParameter("http.route.default-proxy", localHttpHost);
      }
      localHttpPost.setEntity(new InputStreamEntity(new ByteArrayInputStream(paramArrayOfByte), paramArrayOfByte.length));
      if (this.i != null)
        this.i.e();
      paramArrayOfByte = ((HttpClient)localObject).execute(localHttpPost);
      if (this.i != null)
        this.i.f();
      int k = paramArrayOfByte.getStatusLine().getStatusCode();
      bj.a("MobclickAgent", "status code : " + k);
      if (paramArrayOfByte.getStatusLine().getStatusCode() == 200)
      {
        bj.a("MobclickAgent", "Sent message to " + paramString);
        paramArrayOfByte = paramArrayOfByte.getEntity();
        if (paramArrayOfByte != null)
        {
          paramArrayOfByte = paramArrayOfByte.getContent();
          try
          {
            paramString = bv.b(paramArrayOfByte);
            return paramString;
          }
          finally
          {
            bv.c(paramArrayOfByte);
          }
        }
      }
    }
    catch (ClientProtocolException paramArrayOfByte)
    {
      bj.b("MobclickAgent", "ClientProtocolException,Failed to send message.", paramArrayOfByte);
      return null;
      return null;
      return null;
    }
    catch (IOException paramArrayOfByte)
    {
      bj.b("MobclickAgent", "IOException,Failed to send message.", paramArrayOfByte);
    }
    return null;
  }

  private int b(byte[] paramArrayOfByte)
  {
    bb localbb = new bb();
    cc localcc = new cc(new cr.a());
    try
    {
      localcc.a(localbb, paramArrayOfByte);
      if (localbb.a == 1)
      {
        this.j.b(localbb.j());
        this.j.c();
      }
      bj.a("MobclickAgent", "send log:" + localbb.f());
      if (localbb.a == 1)
        return 2;
    }
    catch (Exception paramArrayOfByte)
    {
      while (true)
        paramArrayOfByte.printStackTrace();
    }
    return 3;
  }

  public int a(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte1 = null;
    int k = 0;
    byte[] arrayOfByte2;
    while (true)
    {
      arrayOfByte2 = arrayOfByte1;
      if (k < a.f.length)
      {
        arrayOfByte1 = a(paramArrayOfByte, a.f[k]);
        if (arrayOfByte1 == null)
          break label56;
        arrayOfByte2 = arrayOfByte1;
        if (this.i != null)
        {
          this.i.c();
          arrayOfByte2 = arrayOfByte1;
        }
      }
      if (arrayOfByte2 != null)
        break;
      return 1;
      label56: if (this.i != null)
        this.i.d();
      k += 1;
    }
    return b(arrayOfByte2);
  }

  public void a(w paramw)
  {
    this.i = paramw;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.r
 * JD-Core Version:    0.6.2
 */