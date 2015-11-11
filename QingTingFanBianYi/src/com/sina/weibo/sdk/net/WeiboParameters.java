package com.sina.weibo.sdk.net;

import android.graphics.Bitmap;
import android.text.TextUtils;
import com.sina.weibo.sdk.utils.LogUtil;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class WeiboParameters
{
  private static final String DEFAULT_CHARSET = "UTF-8";
  private String mAppKey;
  private LinkedHashMap<String, Object> mParams = new LinkedHashMap();

  public WeiboParameters(String paramString)
  {
    this.mAppKey = paramString;
  }

  @Deprecated
  public void add(String paramString, int paramInt)
  {
    this.mParams.put(paramString, String.valueOf(paramInt));
  }

  @Deprecated
  public void add(String paramString, long paramLong)
  {
    this.mParams.put(paramString, String.valueOf(paramLong));
  }

  @Deprecated
  public void add(String paramString, Object paramObject)
  {
    this.mParams.put(paramString, paramObject.toString());
  }

  @Deprecated
  public void add(String paramString1, String paramString2)
  {
    this.mParams.put(paramString1, paramString2);
  }

  public boolean containsKey(String paramString)
  {
    return this.mParams.containsKey(paramString);
  }

  public boolean containsValue(String paramString)
  {
    return this.mParams.containsValue(paramString);
  }

  public String encodeUrl()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 1;
    Iterator localIterator = this.mParams.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      String str = (String)localIterator.next();
      int j;
      Object localObject;
      if (i != 0)
      {
        j = 0;
        localObject = this.mParams.get(str);
        i = j;
        if (!(localObject instanceof String))
          continue;
        localObject = (String)localObject;
        if (TextUtils.isEmpty((CharSequence)localObject));
      }
      try
      {
        localStringBuilder.append(URLEncoder.encode(str, "UTF-8") + "=" + URLEncoder.encode((String)localObject, "UTF-8"));
        LogUtil.i("encodeUrl", localStringBuilder.toString());
        i = j;
        continue;
        localStringBuilder.append("&");
        j = i;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        while (true)
          localUnsupportedEncodingException.printStackTrace();
      }
    }
  }

  public Object get(String paramString)
  {
    return this.mParams.get(paramString);
  }

  public String getAppKey()
  {
    return this.mAppKey;
  }

  public LinkedHashMap<String, Object> getParams()
  {
    return this.mParams;
  }

  public boolean hasBinaryData()
  {
    Iterator localIterator = this.mParams.keySet().iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext())
        return false;
      localObject = (String)localIterator.next();
      localObject = this.mParams.get(localObject);
    }
    while ((!(localObject instanceof ByteArrayOutputStream)) && (!(localObject instanceof Bitmap)));
    return true;
  }

  public Set<String> keySet()
  {
    return this.mParams.keySet();
  }

  public void put(String paramString, int paramInt)
  {
    this.mParams.put(paramString, String.valueOf(paramInt));
  }

  public void put(String paramString, long paramLong)
  {
    this.mParams.put(paramString, String.valueOf(paramLong));
  }

  public void put(String paramString, Bitmap paramBitmap)
  {
    this.mParams.put(paramString, paramBitmap);
  }

  public void put(String paramString, Object paramObject)
  {
    this.mParams.put(paramString, paramObject.toString());
  }

  public void put(String paramString1, String paramString2)
  {
    this.mParams.put(paramString1, paramString2);
  }

  public void remove(String paramString)
  {
    if (this.mParams.containsKey(paramString))
    {
      this.mParams.remove(paramString);
      this.mParams.remove(this.mParams.get(paramString));
    }
  }

  public void setParams(LinkedHashMap<String, Object> paramLinkedHashMap)
  {
    this.mParams = paramLinkedHashMap;
  }

  public int size()
  {
    return this.mParams.size();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.net.WeiboParameters
 * JD-Core Version:    0.6.2
 */