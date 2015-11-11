package com.neusoft.parse;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

public class LoaderImpl
{
  private Map a;
  private boolean b = false;
  private String c;

  public LoaderImpl(Map paramMap)
  {
    this.a = paramMap;
  }

  private Bitmap a(String paramString)
  {
    paramString = b(paramString);
    if (paramString == null)
      return null;
    paramString = this.c + "/" + paramString;
    try
    {
      paramString = BitmapFactory.decodeStream(new FileInputStream(paramString));
      return paramString;
    }
    catch (FileNotFoundException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  private static String b(String paramString)
  {
    Object localObject;
    int i;
    try
    {
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).reset();
      ((MessageDigest)localObject).update(paramString.getBytes("UTF-8"));
      paramString = ((MessageDigest)localObject).digest();
      localObject = new StringBuffer();
      i = 0;
      if (i >= paramString.length)
        return ((StringBuffer)localObject).toString();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      System.out.println("NoSuchAlgorithmException caught!");
      return null;
    }
    catch (UnsupportedEncodingException paramString)
    {
      paramString.printStackTrace();
      return null;
    }
    if (Integer.toHexString(paramString[i] & 0xFF).length() == 1)
      ((StringBuffer)localObject).append("0").append(Integer.toHexString(paramString[i] & 0xFF));
    while (true)
    {
      i += 1;
      break;
      ((StringBuffer)localObject).append(Integer.toHexString(paramString[i] & 0xFF));
    }
  }

  public Bitmap getBitmapFromMemory(String paramString)
  {
    Log.e("chuxl", "getBitmapFromMemory");
    if (this.a.containsKey(paramString));
    synchronized (this.a)
    {
      SoftReference localSoftReference = (SoftReference)this.a.get(paramString);
      if (localSoftReference != null)
      {
        paramString = (Bitmap)localSoftReference.get();
        return paramString;
      }
      Log.e("chuxl", "imageCache111");
      if (this.b)
      {
        Log.e("chuxl", "cache2FileFlag");
        ??? = a(paramString);
        if (??? != null)
          this.a.put(paramString, new SoftReference(???));
        Log.e("chuxl", "imageCache222");
        return ???;
      }
    }
    return null;
  }

  public Bitmap getBitmapFromUrl(String paramString, boolean paramBoolean)
  {
    Log.e("chuxl", "getBitmapFromUrl");
    try
    {
      HttpURLConnection localHttpURLConnection = (HttpURLConnection)new URL(paramString).openConnection();
      InputStream localInputStream = localHttpURLConnection.getInputStream();
      Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream);
      Object localObject;
      if (paramBoolean)
      {
        Log.e("chuxl", "cache2Memory1111");
        this.a.put(paramString, new SoftReference(localBitmap));
        if (this.b)
        {
          Log.e("chuxl", "cache2Memory2222");
          paramString = b(paramString);
          localObject = new File(this.c);
          if (!((File)localObject).exists())
            ((File)localObject).mkdirs();
          paramString = this.c + "/" + paramString;
          new File(paramString);
          paramString = new FileOutputStream(paramString);
          localObject = new byte[8192];
        }
      }
      while (true)
      {
        int i = localInputStream.read((byte[])localObject);
        if (i <= 0)
        {
          localBitmap.compress(Bitmap.CompressFormat.PNG, 70, paramString);
          paramString.close();
          localInputStream.close();
          localHttpURLConnection.disconnect();
          Log.e("chuxl", "bitmap:" + localBitmap);
          return localBitmap;
        }
        paramString.write((byte[])localObject, 0, i);
      }
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }

  public void setCache2File(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public void setCachedDir(String paramString)
  {
    this.c = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.parse.LoaderImpl
 * JD-Core Version:    0.6.2
 */