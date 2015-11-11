package fm.qingting.qtradio.wo;

import android.os.AsyncTask;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetNetIp extends AsyncTask<Object, Void, String>
{
  IHttpAsyncTaskListener callback;
  private Object executeParams = null;

  public GetNetIp(IHttpAsyncTaskListener paramIHttpAsyncTaskListener)
  {
    this.callback = paramIHttpAsyncTaskListener;
  }

  public static String getIp()
  {
    ArrayList localArrayList = retrieveIps(getRequest("http://iframe.ip138.com/ic.asp"));
    if (localArrayList.size() > 0)
      return (String)localArrayList.get(0);
    localArrayList = retrieveIps(getRequest("http://httpbin.org/ip"));
    if (localArrayList.size() > 0)
      return (String)localArrayList.get(0);
    localArrayList = retrieveIps(getRequest("http://www.net.cn/static/customercare/yourip.asp"));
    if (localArrayList.size() > 0)
      return (String)localArrayList.get(0);
    return "";
  }

  protected static String getRequest(String paramString)
  {
    if (paramString == null)
      return "";
    if (paramString.equals(""))
      return "";
    try
    {
      paramString = (HttpURLConnection)new URL(paramString).openConnection();
      if (paramString.getResponseCode() == 200)
      {
        paramString = paramString.getInputStream();
        localObject = new BufferedReader(new InputStreamReader(paramString, "utf-8"));
        localStringBuilder = new StringBuilder();
        while (true)
        {
          String str = ((BufferedReader)localObject).readLine();
          if (str == null)
            break;
          localStringBuilder.append(str + "\n");
        }
      }
    }
    catch (MalformedURLException paramString)
    {
      StringBuilder localStringBuilder;
      paramString.printStackTrace();
      return null;
      Object localObject = localStringBuilder.toString();
      paramString.close();
      return localObject;
    }
    catch (IOException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }

  protected static ArrayList<String> retrieveIps(String paramString)
  {
    try
    {
      Matcher localMatcher = Pattern.compile("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}").matcher(paramString);
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        paramString = localArrayList;
        if (!localMatcher.find())
          break;
        localArrayList.add(localMatcher.group());
      }
    }
    catch (Exception paramString)
    {
      paramString = new ArrayList();
    }
    return paramString;
  }

  protected String doInBackground(Object[] paramArrayOfObject)
  {
    this.executeParams = paramArrayOfObject[0];
    return getIp();
  }

  protected void onPostExecute(String paramString)
  {
    this.callback.onGetResult(this.executeParams, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.GetNetIp
 * JD-Core Version:    0.6.2
 */