package fm.qingting.downloadnew;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import cn.com.iresearch.mapptracker.fm.base64.org.apache.commons.codec.binary.Base64;
import fm.qingting.qtradio.wo.WoApiRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class QTDownloadNetwork extends DefaultNetwork
{
  private static boolean isViaWoProxy = false;
  private final int CHUNK_SIZE = 4194304;

  QTDownloadNetwork(Context paramContext, EventDispatcher paramEventDispatcher, String paramString)
  {
    super(paramContext, paramEventDispatcher, paramString);
  }

  private String calcETag(String paramString)
    throws IOException, NoSuchAlgorithmException
  {
    Log.i(this.TAG, "开始计算下载得到文件的checksum");
    paramString = new File(paramString);
    if ((!paramString.exists()) || (!paramString.isFile()) || (!paramString.canRead()))
    {
      Log.e(this.TAG, "无法读取文件");
      return "";
    }
    long l = paramString.length();
    FileInputStream localFileInputStream = new FileInputStream(paramString);
    int i;
    byte[] arrayOfByte1;
    if (l <= 4194304L)
    {
      paramString = new byte[(int)l];
      localFileInputStream.read(paramString, 0, (int)l);
      paramString = sha1(paramString);
      i = paramString.length;
      arrayOfByte1 = new byte[i + 1];
      System.arraycopy(paramString, 0, arrayOfByte1, 1, i);
      arrayOfByte1[0] = 22;
    }
    for (paramString = urlSafeBase64Encode(arrayOfByte1); ; paramString = urlSafeBase64Encode(arrayOfByte1))
    {
      localFileInputStream.close();
      Log.d(this.TAG, "checksum = " + paramString);
      return paramString;
      int j = (int)(l / 4194304L);
      i = j;
      if (l % 4194304L != 0L)
        i = j + 1;
      paramString = new byte[0];
      j = 0;
      while (j < i)
      {
        arrayOfByte1 = new byte[4194304];
        int k = localFileInputStream.read(arrayOfByte1, 0, 4194304);
        byte[] arrayOfByte2 = new byte[k];
        System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, k);
        arrayOfByte2 = sha1(arrayOfByte2);
        arrayOfByte1 = new byte[arrayOfByte2.length + paramString.length];
        System.arraycopy(paramString, 0, arrayOfByte1, 0, paramString.length);
        System.arraycopy(arrayOfByte2, 0, arrayOfByte1, paramString.length, arrayOfByte2.length);
        j += 1;
        paramString = arrayOfByte1;
      }
      paramString = sha1(paramString);
      arrayOfByte1 = new byte[paramString.length + 1];
      System.arraycopy(paramString, 0, arrayOfByte1, 1, paramString.length);
      arrayOfByte1[0] = -106;
    }
  }

  public static void setIsViaWoProxy(boolean paramBoolean)
  {
    isViaWoProxy = paramBoolean;
  }

  private byte[] sha1(byte[] paramArrayOfByte)
    throws NoSuchAlgorithmException
  {
    return MessageDigest.getInstance("sha1").digest(paramArrayOfByte);
  }

  protected boolean checkFile()
  {
    if (TextUtils.isEmpty(this.mCurTask.mExtra))
      return true;
    String str2 = this.mCurTask.mExtra;
    Object localObject = "";
    try
    {
      String str1 = calcETag(this.mCurTask.mFileName);
      localObject = str1;
      label40: return str2.equalsIgnoreCase((String)localObject);
    }
    catch (Exception localException)
    {
      break label40;
    }
  }

  protected HttpURLConnection openConnection(URL paramURL)
    throws IOException
  {
    if (isViaWoProxy);
    while (true)
    {
      try
      {
        String str1 = WoApiRequest.getProxyServer();
        if (str1 != null)
        {
          String str2 = WoApiRequest.getProxyPortString();
          System.getProperties().put("http.proxySet", "true");
          System.getProperties().put("http.proxyHost", str1);
          System.getProperties().put("http.proxyPort", str2);
          Authenticator.setDefault(new Authenticator()
          {
            protected PasswordAuthentication getPasswordAuthentication()
            {
              return new PasswordAuthentication(WoApiRequest.getAppKey(), WoApiRequest.getAppSecret().toCharArray());
            }
          });
        }
        return (HttpURLConnection)paramURL.openConnection();
      }
      catch (Exception localException)
      {
        WoApiRequest.log("Enable WoProxy", "10", "");
        Log.e(this.TAG, "设置代理失败");
        continue;
      }
      System.getProperties().put("http.proxySet", "false");
    }
  }

  public String urlSafeBase64Encode(byte[] paramArrayOfByte)
  {
    return new String(Base64.encodeBase64(paramArrayOfByte)).replace('+', '-').replace('/', '_');
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.downloadnew.QTDownloadNetwork
 * JD-Core Version:    0.6.2
 */