package HttpUtils;

import android.content.Context;
import android.util.Base64;
import android.util.Log;
import com.alipay.mobilesecuritysdk.util.CommonUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

public class HttpFetcher
{
  private List<BasicNameValuePair> paramBuilder(String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    if (paramString2 == null)
      return null;
    if (paramBoolean);
    try
    {
      for (paramString2 = CommonUtils.textCompress(paramString2); CommonUtils.isBlank(paramString2); paramString2 = Base64.encodeToString(paramString2.getBytes(), 8))
        return null;
      ArrayList localArrayList = new ArrayList();
      StringBuilder localStringBuilder = new StringBuilder();
      if (!CommonUtils.isBlank(paramString1))
      {
        localArrayList.add(new BasicNameValuePair("serviceId", paramString1));
        localStringBuilder.append("serviceId=");
        localStringBuilder.append(paramString1);
        localStringBuilder.append("&");
      }
      if (!CommonUtils.isBlank(paramString3))
      {
        localArrayList.add(new BasicNameValuePair("version", paramString3));
        localStringBuilder.append("version=");
        localStringBuilder.append(paramString3);
        localStringBuilder.append("&");
      }
      localArrayList.add(new BasicNameValuePair("data", paramString2));
      localStringBuilder.append("data=");
      localStringBuilder.append(paramString2);
      localStringBuilder.append("02000016-0010-0080-8000-10CA006D2CA5");
      localArrayList.add(new BasicNameValuePair("sign", CommonUtils.MD5(localStringBuilder.toString())));
      Log.i("ALP", localStringBuilder.toString() + localArrayList.toString());
      return localArrayList;
    }
    catch (Exception paramString1)
    {
      Log.i("ALP", paramString1.getMessage());
    }
    return null;
  }

  public HttpClient getHttpClient()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 5000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 5000);
    return new DefaultHttpClient(localBasicHttpParams);
  }

  public HttpResponse uploadCollectedData(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean)
  {
    paramContext = null;
    if (paramString3 == null)
      return null;
    try
    {
      paramString1 = new HttpPost(paramString1);
      paramString2 = paramBuilder(paramString2, paramString3, paramString4, paramBoolean);
      if (paramString2 == null)
        return null;
      paramString1.setEntity(new UrlEncodedFormEntity(paramString2, "UTF-8"));
      paramString1 = getHttpClient().execute(paramString1);
      paramContext = paramString1;
      return paramContext;
    }
    catch (Exception paramString1)
    {
      while (true)
        Log.i("ALP", paramString1.getMessage());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     HttpUtils.HttpFetcher
 * JD-Core Version:    0.6.2
 */