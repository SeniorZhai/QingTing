package com.tencent.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.json.JSONException;
import org.json.JSONObject;

public class Util
{
  private static final String a = Util.class.getName();
  private static boolean b = true;
  private static String c = "0123456789ABCDEF";

  private static char a(int paramInt)
  {
    paramInt &= 15;
    if (paramInt < 10)
      return (char)(paramInt + 48);
    return (char)(paramInt - 10 + 97);
  }

  private static String a(HttpResponse paramHttpResponse)
    throws IllegalStateException, IOException
  {
    Object localObject = paramHttpResponse.getEntity().getContent();
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramHttpResponse = paramHttpResponse.getFirstHeader("Content-Encoding");
    if ((paramHttpResponse != null) && (paramHttpResponse.getValue().toLowerCase().indexOf("gzip") > -1));
    for (paramHttpResponse = new GZIPInputStream((InputStream)localObject); ; paramHttpResponse = (HttpResponse)localObject)
    {
      localObject = new byte[512];
      while (true)
      {
        int i = paramHttpResponse.read((byte[])localObject);
        if (i == -1)
          break;
        localByteArrayOutputStream.write((byte[])localObject, 0, i);
      }
      return new String(localByteArrayOutputStream.toByteArray());
    }
  }

  private static void a(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.setComponent(new ComponentName(paramString1, paramString2));
    localIntent.setAction("android.intent.action.VIEW");
    localIntent.addFlags(1073741824);
    localIntent.addFlags(268435456);
    localIntent.setData(Uri.parse(paramString3));
    paramContext.startActivity(localIntent);
  }

  private static boolean a(Context paramContext)
  {
    boolean bool2 = false;
    try
    {
      paramContext = paramContext.getPackageManager().getPackageInfo("com.tencent.mtt", 64);
      Object localObject = paramContext.versionName;
      boolean bool1 = bool2;
      if (SystemUtils.compareVersion((String)localObject, "4.3") >= 0)
      {
        bool1 = bool2;
        if (!((String)localObject).startsWith("4.4"))
        {
          localObject = paramContext.signatures;
          bool1 = bool2;
          if (localObject == null);
        }
      }
      try
      {
        paramContext = MessageDigest.getInstance("MD5");
        paramContext.update(localObject[0].toByteArray());
        localObject = toHexString(paramContext.digest());
        paramContext.reset();
        boolean bool3 = ((String)localObject).equals("d8391a394d4a179e6fe7bdb8a301258b");
        bool1 = bool2;
        if (bool3)
          bool1 = true;
        return bool1;
      }
      catch (NoSuchAlgorithmException paramContext)
      {
        paramContext.printStackTrace();
        return false;
      }
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
    }
    return false;
  }

  public static Bundle decodeUrl(String paramString)
  {
    Bundle localBundle = new Bundle();
    if (paramString != null)
    {
      paramString = paramString.split("&");
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        String[] arrayOfString = paramString[i].split("=");
        if (arrayOfString.length == 2)
          localBundle.putString(URLDecoder.decode(arrayOfString[0]), URLDecoder.decode(arrayOfString[1]));
        i += 1;
      }
    }
    return localBundle;
  }

  public static JSONObject decodeUrlToJson(JSONObject paramJSONObject, String paramString)
  {
    JSONObject localJSONObject = paramJSONObject;
    if (paramJSONObject == null)
      localJSONObject = new JSONObject();
    if (paramString != null)
    {
      paramJSONObject = paramString.split("&");
      int j = paramJSONObject.length;
      int i = 0;
      while (true)
        if (i < j)
        {
          paramString = paramJSONObject[i].split("=");
          if (paramString.length == 2);
          try
          {
            localJSONObject.put(URLDecoder.decode(paramString[0]), URLDecoder.decode(paramString[1]));
            i += 1;
          }
          catch (JSONException paramString)
          {
            while (true)
              paramString.printStackTrace();
          }
        }
    }
    return localJSONObject;
  }

  public static String encodePostBody(Bundle paramBundle, String paramString)
  {
    if (paramBundle == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    int j = paramBundle.size();
    Iterator localIterator = paramBundle.keySet().iterator();
    int i = -1;
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      i += 1;
      Object localObject = paramBundle.get(str);
      if ((localObject instanceof String))
      {
        localStringBuilder.append("Content-Disposition: form-data; name=\"" + str + "\"" + "\r\n" + "\r\n" + (String)localObject);
        if (i < j - 1)
          localStringBuilder.append("\r\n--" + paramString + "\r\n");
      }
    }
    return localStringBuilder.toString();
  }

  public static String encodeUrl(Bundle paramBundle)
  {
    if (paramBundle == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramBundle.keySet().iterator();
    int i = 1;
    while (localIterator.hasNext())
    {
      Object localObject1 = (String)localIterator.next();
      Object localObject2 = paramBundle.get((String)localObject1);
      if (((localObject2 instanceof String)) || ((localObject2 instanceof String[])))
        if ((localObject2 instanceof String[]))
        {
          int j;
          if (i != 0)
          {
            i = 0;
            localStringBuilder.append(URLEncoder.encode((String)localObject1) + "=");
            localObject1 = (String[])paramBundle.getStringArray((String)localObject1);
            if (localObject1 == null)
              break label206;
            j = 0;
            label129: if (j >= localObject1.length)
              break label206;
            if (j != 0)
              break label172;
            localStringBuilder.append(URLEncoder.encode(localObject1[j]));
          }
          while (true)
          {
            j += 1;
            break label129;
            localStringBuilder.append("&");
            break;
            label172: localStringBuilder.append(URLEncoder.encode("," + localObject1[j]));
          }
        }
        else
        {
          label206: if (i != 0)
            i = 0;
          while (true)
          {
            localStringBuilder.append(URLEncoder.encode((String)localObject1) + "=" + URLEncoder.encode(paramBundle.getString((String)localObject1)));
            break;
            localStringBuilder.append("&");
          }
        }
    }
    return localStringBuilder.toString();
  }

  public static String encrypt(String paramString)
  {
    try
    {
      Object localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramString.getBytes());
      byte[] arrayOfByte = ((MessageDigest)localObject).digest();
      localObject = paramString;
      if (arrayOfByte != null)
      {
        localObject = new StringBuilder();
        int j = arrayOfByte.length;
        int i = 0;
        while (i < j)
        {
          int k = arrayOfByte[i];
          ((StringBuilder)localObject).append(a(k >>> 4));
          ((StringBuilder)localObject).append(a(k));
          i += 1;
        }
        localObject = ((StringBuilder)localObject).toString();
      }
      return localObject;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      localNoSuchAlgorithmException.printStackTrace();
    }
    return paramString;
  }

  public static boolean fileExists(String paramString)
  {
    if (paramString == null);
    do
    {
      return false;
      paramString = new File(paramString);
    }
    while ((paramString == null) || (!paramString.exists()));
    return true;
  }

  public static String getAppVersion(Context paramContext)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    try
    {
      paramContext = localPackageManager.getPackageInfo(paramContext.getPackageName(), 0).versionName;
      return paramContext;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.e(a, "getAppVersion error" + paramContext.getMessage());
      paramContext.printStackTrace();
    }
    return "";
  }

  public static final String getApplicationLable(Context paramContext)
  {
    if (paramContext != null)
    {
      paramContext = paramContext.getPackageManager().getApplicationLabel(paramContext.getApplicationInfo());
      if (paramContext != null)
        return paramContext.toString();
    }
    return null;
  }

  public static String getUserIp()
  {
    try
    {
      InetAddress localInetAddress;
      do
      {
        localObject = NetworkInterface.getNetworkInterfaces();
        Enumeration localEnumeration;
        while (!localEnumeration.hasMoreElements())
        {
          if ((localObject == null) || (!((Enumeration)localObject).hasMoreElements()))
            break;
          localEnumeration = ((NetworkInterface)((Enumeration)localObject).nextElement()).getInetAddresses();
        }
        localInetAddress = (InetAddress)localEnumeration.nextElement();
      }
      while (localInetAddress.isLoopbackAddress());
      Object localObject = localInetAddress.getHostAddress().toString();
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      logd("Tencent-Util", localSocketException.toString());
    }
    return "";
  }

  public static boolean hasSDCard()
  {
    File localFile = null;
    if (Environment.getExternalStorageState().equals("mounted"))
      localFile = Environment.getExternalStorageDirectory();
    return localFile != null;
  }

  public static String hexToString(String paramString)
  {
    int i = 0;
    String str = paramString;
    if ("0x".equals(paramString.substring(0, 2)))
      str = paramString.substring(2);
    paramString = new byte[str.length() / 2];
    while (true)
      if (i < paramString.length)
        try
        {
          paramString[i] = ((byte)(Integer.parseInt(str.substring(i * 2, i * 2 + 2), 16) & 0xFF));
          i += 1;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
    try
    {
      paramString = new String(paramString, "utf-8");
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return str;
  }

  public static boolean isEmpty(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0);
  }

  public static boolean isMobileQQSupportShare(Context paramContext)
  {
    boolean bool = false;
    paramContext = paramContext.getPackageManager();
    try
    {
      int i = SystemUtils.compareVersion(paramContext.getPackageInfo("com.tencent.mobileqq", 0).versionName, "4.1");
      if (i >= 0)
        bool = true;
      return bool;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.d("checkMobileQQ", "error");
    }
    return false;
  }

  public static final boolean isValidPath(String paramString)
  {
    if (paramString == null);
    do
    {
      return false;
      paramString = new File(paramString);
    }
    while ((paramString == null) || (!paramString.exists()));
    return true;
  }

  public static final boolean isValidUrl(String paramString)
  {
    if (paramString == null);
    while ((!paramString.startsWith("http://")) && (!paramString.startsWith("https://")))
      return false;
    return true;
  }

  public static void logd(String paramString1, String paramString2)
  {
    if (b)
      Log.d(paramString1, paramString2);
  }

  public static boolean openBrowser(Context paramContext, String paramString)
  {
    try
    {
      bool = a(paramContext);
      if (bool);
      try
      {
        a(paramContext, "com.tencent.mtt", "com.tencent.mtt.MainActivity", paramString);
        break label126;
        a(paramContext, "com.android.browser", "com.android.browser.BrowserActivity", paramString);
      }
      catch (Exception localException1)
      {
      }
      if (bool)
        try
        {
          a(paramContext, "com.android.browser", "com.android.browser.BrowserActivity", paramString);
        }
        catch (Exception localException2)
        {
          try
          {
            a(paramContext, "com.google.android.browser", "com.android.browser.BrowserActivity", paramString);
          }
          catch (Exception localException3)
          {
            try
            {
              a(paramContext, "com.android.chrome", "com.google.android.apps.chrome.Main", paramString);
            }
            catch (Exception paramContext)
            {
              return false;
            }
          }
        }
      else
        try
        {
          a(paramContext, "com.google.android.browser", "com.android.browser.BrowserActivity", paramString);
        }
        catch (Exception localException4)
        {
          try
          {
            a(paramContext, "com.android.chrome", "com.google.android.apps.chrome.Main", paramString);
          }
          catch (Exception paramContext)
          {
            return false;
          }
        }
    }
    catch (Exception localException5)
    {
      while (true)
        boolean bool = false;
    }
    label126: return true;
  }

  public static JSONObject parseJson(String paramString)
    throws JSONException
  {
    String str = paramString;
    if (paramString.equals("false"))
      str = "{value : false}";
    paramString = str;
    if (str.equals("true"))
      paramString = "{value : true}";
    str = paramString;
    if (paramString.contains("allback("))
      str = paramString.replaceFirst("[\\s\\S]*allback\\(([\\s\\S]*)\\);[^\\)]*\\z", "$1").trim();
    paramString = str;
    if (str.contains("online"))
      paramString = "{online:" + str.charAt(str.length() - 2) + "}";
    return new JSONObject(paramString);
  }

  public static Bundle parseUrl(String paramString)
  {
    paramString = paramString.replace("auth://", "http://");
    try
    {
      paramString = new URL(paramString);
      Bundle localBundle = decodeUrl(paramString.getQuery());
      localBundle.putAll(decodeUrl(paramString.getRef()));
      return localBundle;
    }
    catch (MalformedURLException paramString)
    {
    }
    return new Bundle();
  }

  public static JSONObject parseUrlToJson(String paramString)
  {
    paramString = paramString.replace("auth://", "http://");
    try
    {
      paramString = new URL(paramString);
      JSONObject localJSONObject = decodeUrlToJson(null, paramString.getQuery());
      decodeUrlToJson(localJSONObject, paramString.getRef());
      return localJSONObject;
    }
    catch (MalformedURLException paramString)
    {
    }
    return new JSONObject();
  }

  public static void reportBernoulli(Context paramContext, String paramString1, long paramLong, String paramString2)
  {
    final Bundle localBundle = new Bundle();
    localBundle.putString("appid_for_getting_config", paramString2);
    localBundle.putString("strValue", paramString2);
    localBundle.putString("nValue", paramString1);
    localBundle.putString("qver", "2.2.1");
    if (paramLong != 0L)
      localBundle.putLong("elt", paramLong);
    new Thread()
    {
      public void run()
      {
        try
        {
          HttpUtils.openUrl2(this.a, "http://cgi.qplus.com/report/report", "GET", localBundle);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    .start();
  }

  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    paramContext = new AlertDialog.Builder(paramContext);
    paramContext.setTitle(paramString1);
    paramContext.setMessage(paramString2);
    paramContext.create().show();
  }

  // ERROR //
  public static final String subString(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 5
    //   3: aload_0
    //   4: invokestatic 592	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   7: ifeq +8 -> 15
    //   10: ldc 255
    //   12: astore_2
    //   13: aload_2
    //   14: areturn
    //   15: aload_2
    //   16: invokestatic 592	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   19: ifne +152 -> 171
    //   22: aload_2
    //   23: astore 4
    //   25: aload_0
    //   26: astore_2
    //   27: aload_0
    //   28: aload 4
    //   30: invokevirtual 595	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   33: arraylength
    //   34: iload_1
    //   35: if_icmple -22 -> 13
    //   38: iconst_0
    //   39: istore 6
    //   41: aload_0
    //   42: astore_2
    //   43: iload 5
    //   45: aload_0
    //   46: invokevirtual 426	java/lang/String:length	()I
    //   49: if_icmpge -36 -> 13
    //   52: aload_0
    //   53: iload 5
    //   55: iload 5
    //   57: iconst_1
    //   58: iadd
    //   59: invokevirtual 420	java/lang/String:substring	(II)Ljava/lang/String;
    //   62: aload 4
    //   64: invokevirtual 595	java/lang/String:getBytes	(Ljava/lang/String;)[B
    //   67: arraylength
    //   68: istore 7
    //   70: iload 6
    //   72: iload 7
    //   74: iadd
    //   75: iload_1
    //   76: if_icmple +43 -> 119
    //   79: aload_0
    //   80: iconst_0
    //   81: iload 5
    //   83: invokevirtual 420	java/lang/String:substring	(II)Ljava/lang/String;
    //   86: astore_2
    //   87: aload_2
    //   88: astore_0
    //   89: aload_0
    //   90: astore_2
    //   91: aload_3
    //   92: invokestatic 592	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   95: ifne +22 -> 117
    //   98: new 257	java/lang/StringBuilder
    //   101: dup
    //   102: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   105: aload_0
    //   106: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   109: aload_3
    //   110: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   113: invokevirtual 299	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: astore_2
    //   117: aload_2
    //   118: areturn
    //   119: iload 6
    //   121: iload 7
    //   123: iadd
    //   124: istore 6
    //   126: iload 5
    //   128: iconst_1
    //   129: iadd
    //   130: istore 5
    //   132: goto -91 -> 41
    //   135: astore_2
    //   136: getstatic 601	java/lang/System:out	Ljava/io/PrintStream;
    //   139: new 257	java/lang/StringBuilder
    //   142: dup
    //   143: invokespecial 258	java/lang/StringBuilder:<init>	()V
    //   146: ldc_w 603
    //   149: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   152: aload_2
    //   153: invokevirtual 604	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   156: invokevirtual 292	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   159: invokevirtual 299	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   162: invokevirtual 609	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   165: aload_0
    //   166: areturn
    //   167: astore_2
    //   168: goto -32 -> 136
    //   171: ldc_w 611
    //   174: astore 4
    //   176: goto -151 -> 25
    //
    // Exception table:
    //   from	to	target	type
    //   27	38	135	java/lang/Exception
    //   43	70	135	java/lang/Exception
    //   79	87	135	java/lang/Exception
    //   91	117	167	java/lang/Exception
  }

  public static String toHexString(String paramString)
  {
    paramString = paramString.getBytes();
    StringBuilder localStringBuilder = new StringBuilder(paramString.length * 2);
    int i = 0;
    while (i < paramString.length)
    {
      localStringBuilder.append(c.charAt((paramString[i] & 0xF0) >> 4));
      localStringBuilder.append(c.charAt((paramString[i] & 0xF) >> 0));
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static String toHexString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder(paramArrayOfByte.length * 2);
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      String str2 = Integer.toString(paramArrayOfByte[i] & 0xFF, 16);
      String str1 = str2;
      if (str2.length() == 1)
        str1 = "0" + str2;
      localStringBuilder.append(str1);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  public static Statistic upload(Context paramContext, String paramString, Bundle paramBundle)
    throws MalformedURLException, IOException, HttpUtils.NetworkUnavailableException, HttpUtils.HttpStatusException
  {
    if (paramContext != null)
    {
      localObject1 = (ConnectivityManager)paramContext.getSystemService("connectivity");
      if (localObject1 != null)
      {
        localObject1 = ((ConnectivityManager)localObject1).getActiveNetworkInfo();
        if ((localObject1 == null) || (!((NetworkInfo)localObject1).isAvailable()))
          throw new HttpUtils.NetworkUnavailableException("network unavailable");
      }
    }
    paramBundle = new Bundle(paramBundle);
    Object localObject1 = paramBundle.getString("appid_for_getting_config");
    paramBundle.remove("appid_for_getting_config");
    paramContext = HttpUtils.getHttpClient(paramContext, (String)localObject1, paramString);
    paramString = new HttpPost(paramString);
    localObject1 = new Bundle();
    Object localObject2 = paramBundle.keySet().iterator();
    Object localObject3;
    while (((Iterator)localObject2).hasNext())
    {
      localObject3 = (String)((Iterator)localObject2).next();
      Object localObject4 = paramBundle.get((String)localObject3);
      if ((localObject4 instanceof byte[]))
        ((Bundle)localObject1).putByteArray((String)localObject3, (byte[])localObject4);
    }
    paramString.setHeader("Content-Type", "multipart/form-data; boundary=3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f");
    paramString.setHeader("Connection", "Keep-Alive");
    localObject2 = new ByteArrayOutputStream();
    ((ByteArrayOutputStream)localObject2).write("--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
    ((ByteArrayOutputStream)localObject2).write(encodePostBody(paramBundle, "3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f").getBytes());
    if (!((Bundle)localObject1).isEmpty())
    {
      int k = ((Bundle)localObject1).size();
      ((ByteArrayOutputStream)localObject2).write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
      paramBundle = ((Bundle)localObject1).keySet().iterator();
      i = -1;
      while (paramBundle.hasNext())
      {
        localObject3 = (String)paramBundle.next();
        j = i + 1;
        ((ByteArrayOutputStream)localObject2).write(("Content-Disposition: form-data; name=\"" + (String)localObject3 + "\"; filename=\"" + "value.file" + "\"" + "\r\n").getBytes());
        ((ByteArrayOutputStream)localObject2).write("Content-Type: application/octet-stream\r\n\r\n".getBytes());
        localObject3 = ((Bundle)localObject1).getByteArray((String)localObject3);
        if (localObject3 != null)
          ((ByteArrayOutputStream)localObject2).write((byte[])localObject3);
        i = j;
        if (j < k - 1)
        {
          ((ByteArrayOutputStream)localObject2).write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f\r\n".getBytes());
          i = j;
        }
      }
    }
    ((ByteArrayOutputStream)localObject2).write("\r\n--3i2ndDfv2rTHiSisAbouNdArYfORhtTPEefj3q2f--\r\n".getBytes());
    paramBundle = ((ByteArrayOutputStream)localObject2).toByteArray();
    int i = paramBundle.length;
    ((ByteArrayOutputStream)localObject2).close();
    paramString.setEntity(new ByteArrayEntity(paramBundle));
    paramContext = paramContext.execute(paramString);
    int j = paramContext.getStatusLine().getStatusCode();
    if (j == 200)
      return new Statistic(a(paramContext), i + 0);
    throw new HttpUtils.HttpStatusException("http status code error:" + j);
  }

  public static class Statistic
  {
    public long reqSize;
    public String response;
    public long rspSize;

    public Statistic(String paramString, int paramInt)
    {
      this.response = paramString;
      this.reqSize = paramInt;
      if (this.response != null)
        this.rspSize = this.response.length();
    }
  }

  private static class TBufferedOutputStream extends BufferedOutputStream
  {
    private int a = 0;

    public TBufferedOutputStream(OutputStream paramOutputStream)
    {
      super();
    }

    public int getLength()
    {
      return this.a;
    }

    public void write(byte[] paramArrayOfByte)
      throws IOException
    {
      super.write(paramArrayOfByte);
      this.a += paramArrayOfByte.length;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.utils.Util
 * JD-Core Version:    0.6.2
 */