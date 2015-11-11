package com.taobao.newxp.common.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;

public class PersistentCookieStore
  implements CookieStore
{
  private static final String COOKIE_NAME_PREFIX = "cookie_";
  private static final String COOKIE_NAME_STORE = "names";
  private static final String COOKIE_PREFS = "CookiePrefsFile";
  public static final String TAG = "Alimunion";
  private static SharedPreferences cookiePrefs;
  private final ConcurrentHashMap<String, Cookie> cookies;

  public PersistentCookieStore(Context paramContext)
  {
    cookiePrefs = paramContext.getSharedPreferences("CookiePrefsFile", 0);
    this.cookies = new ConcurrentHashMap();
    paramContext = cookiePrefs.getString("names", null);
    if (paramContext != null)
    {
      paramContext = TextUtils.split(paramContext, ",");
      int j = paramContext.length;
      while (i < j)
      {
        String str = paramContext[i];
        Object localObject = cookiePrefs.getString("cookie_" + str, null);
        if (localObject != null)
        {
          localObject = decodeCookie((String)localObject);
          if (localObject != null)
            this.cookies.put(str, localObject);
        }
        i += 1;
      }
      clearExpired(new Date());
    }
  }

  public void addCookie(Cookie paramCookie)
  {
    String str = paramCookie.getName() + paramCookie.getDomain();
    this.cookies.put(str, paramCookie);
    SharedPreferences.Editor localEditor = cookiePrefs.edit();
    localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
    localEditor.putString("cookie_" + str, encodeCookie(new SerializableCookie(paramCookie)));
    localEditor.commit();
  }

  protected String byteArrayToHexString(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length * 2);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      int k = paramArrayOfByte[i] & 0xFF;
      if (k < 16)
        localStringBuffer.append('0');
      localStringBuffer.append(Integer.toHexString(k));
      i += 1;
    }
    return localStringBuffer.toString().toUpperCase();
  }

  public void clear()
  {
    SharedPreferences.Editor localEditor = cookiePrefs.edit();
    Iterator localIterator = this.cookies.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localEditor.remove("cookie_" + str);
    }
    localEditor.remove("names");
    localEditor.commit();
    this.cookies.clear();
  }

  public boolean clearExpired(Date paramDate)
  {
    SharedPreferences.Editor localEditor = cookiePrefs.edit();
    Iterator localIterator = this.cookies.entrySet().iterator();
    boolean bool = false;
    if (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      String str = (String)localEntry.getKey();
      if (!((Cookie)localEntry.getValue()).isExpired(paramDate))
        break label155;
      this.cookies.remove(str);
      localEditor.remove("cookie_" + str);
      bool = true;
    }
    label155: 
    while (true)
    {
      break;
      if (bool)
        localEditor.putString("names", TextUtils.join(",", this.cookies.keySet()));
      localEditor.commit();
      return bool;
    }
  }

  protected Cookie decodeCookie(String paramString)
  {
    paramString = new ByteArrayInputStream(hexStringToByteArray(paramString));
    try
    {
      paramString = ((SerializableCookie)new ObjectInputStream(paramString).readObject()).getCookie();
      return paramString;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  protected String encodeCookie(SerializableCookie paramSerializableCookie)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(paramSerializableCookie);
      return byteArrayToHexString(localByteArrayOutputStream.toByteArray());
    }
    catch (Exception paramSerializableCookie)
    {
    }
    return null;
  }

  public List<Cookie> getCookies()
  {
    return new ArrayList(this.cookies.values());
  }

  protected byte[] hexStringToByteArray(String paramString)
  {
    int j = paramString.length();
    byte[] arrayOfByte = new byte[j / 2];
    int i = 0;
    while (i < j)
    {
      arrayOfByte[(i / 2)] = ((byte)((Character.digit(paramString.charAt(i), 16) << 4) + Character.digit(paramString.charAt(i + 1), 16)));
      i += 2;
    }
    return arrayOfByte;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.persistence.PersistentCookieStore
 * JD-Core Version:    0.6.2
 */