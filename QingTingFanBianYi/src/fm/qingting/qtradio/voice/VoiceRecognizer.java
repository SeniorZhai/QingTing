package fm.qingting.qtradio.voice;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.json.JSONException;
import org.json.JSONObject;

public class VoiceRecognizer
{
  private static final String APP_KEY = "Exwb1LH6g4qKAXVjQHYr73au";
  private static final String APP_SECRET = "Exx7lBdM0DAvM3l6X8eyrGQoTkTvIQsu";
  private static final String SERVER_URL = "http://vop.baidu.com/server_api";
  private static final String TOKEN_URL = "https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=Exwb1LH6g4qKAXVjQHYr73au&client_secret=Exx7lBdM0DAvM3l6X8eyrGQoTkTvIQsu";
  private static String mToken;

  public static ArrayList<String> recognize(byte[] paramArrayOfByte)
    throws MalformedURLException, IOException, JSONException
  {
    Object localObject1 = null;
    if (paramArrayOfByte == null);
    while (!token())
      return localObject1;
    localObject1 = (HttpURLConnection)new URL("http://vop.baidu.com/server_api?cuid=22&token=" + mToken).openConnection();
    ((HttpURLConnection)localObject1).setRequestMethod("POST");
    ((HttpURLConnection)localObject1).setRequestProperty("Content-Type", "audio/pcm; rate=8000");
    ((HttpURLConnection)localObject1).setDoInput(true);
    ((HttpURLConnection)localObject1).setDoOutput(true);
    Object localObject2 = new DataOutputStream(((HttpURLConnection)localObject1).getOutputStream());
    ((DataOutputStream)localObject2).write(paramArrayOfByte);
    ((DataOutputStream)localObject2).flush();
    ((DataOutputStream)localObject2).close();
    localObject1 = new JSONObject(request((HttpURLConnection)localObject1)).getString("result");
    paramArrayOfByte = new ArrayList();
    localObject2 = new StringTokenizer(((String)localObject1).substring(2, ((String)localObject1).length() - 3), "，");
    while (true)
    {
      localObject1 = paramArrayOfByte;
      if (!((StringTokenizer)localObject2).hasMoreElements())
        break;
      paramArrayOfByte.add(((StringTokenizer)localObject2).nextToken());
    }
  }

  private static String request(HttpURLConnection paramHttpURLConnection)
    throws UnsupportedEncodingException, IOException
  {
    Object localObject = "";
    if (paramHttpURLConnection.getResponseCode() == 200)
    {
      paramHttpURLConnection = paramHttpURLConnection.getInputStream();
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject = new byte[1024];
      while (true)
      {
        int i = paramHttpURLConnection.read((byte[])localObject, 0, localObject.length);
        if (i == -1)
          break;
        localByteArrayOutputStream.write((byte[])localObject, 0, i);
      }
      localObject = new String(localByteArrayOutputStream.toByteArray(), "UTF-8");
      localByteArrayOutputStream.close();
      paramHttpURLConnection.close();
    }
    return localObject;
  }

  private static boolean token()
    throws UnsupportedEncodingException, IOException, JSONException
  {
    if (mToken == null)
    {
      String str = request((HttpURLConnection)new URL("https://openapi.baidu.com/oauth/2.0/token?grant_type=client_credentials&client_id=Exwb1LH6g4qKAXVjQHYr73au&client_secret=Exx7lBdM0DAvM3l6X8eyrGQoTkTvIQsu").openConnection());
      if (!str.equals(""))
        mToken = new JSONObject(str).getString("access_token");
    }
    return mToken != null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.voice.VoiceRecognizer
 * JD-Core Version:    0.6.2
 */