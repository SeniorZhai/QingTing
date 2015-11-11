package com.tencent.weibo.sdk.android.network;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Matrix;
import android.util.Log;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReqParam
{
  public Bitmap mBitmap = null;
  private Map<String, String> mParams = new HashMap();

  public void addParam(String paramString, Object paramObject)
  {
    this.mParams.put(paramString, paramObject.toString());
  }

  public void addParam(String paramString1, String paramString2)
  {
    this.mParams.put(paramString1, paramString2);
  }

  public void addParam(String paramString, byte[] paramArrayOfByte)
  {
    double d = paramArrayOfByte.length / 1024;
    if (d > 400.0D)
    {
      paramArrayOfByte = new ByteArrayOutputStream();
      d /= 400.0D;
      zoomImage(this.mBitmap, this.mBitmap.getWidth() / Math.sqrt(d), this.mBitmap.getHeight() / Math.sqrt(d)).compress(Bitmap.CompressFormat.JPEG, 100, paramArrayOfByte);
      paramArrayOfByte = paramArrayOfByte.toByteArray();
    }
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (true)
    {
      if (i >= paramArrayOfByte.length)
      {
        Log.d("buffer=======", localStringBuffer.toString());
        this.mParams.put(paramString, localStringBuffer.toString());
        return;
      }
      localStringBuffer.append((char)paramArrayOfByte[i]);
      i += 1;
    }
  }

  public Map<String, String> getmParams()
  {
    return this.mParams;
  }

  public void setBitmap(Bitmap paramBitmap)
  {
    this.mBitmap = paramBitmap;
  }

  public void setmParams(Map<String, String> paramMap)
  {
    this.mParams = paramMap;
  }

  public String toString()
  {
    Object localObject2 = new ArrayList();
    Object localObject1 = this.mParams.keySet().iterator();
    if (!((Iterator)localObject1).hasNext())
    {
      Collections.sort((List)localObject2, new Comparator()
      {
        public int compare(String paramAnonymousString1, String paramAnonymousString2)
        {
          if (paramAnonymousString1.compareTo(paramAnonymousString2) > 0)
            return 1;
          if (paramAnonymousString1.compareTo(paramAnonymousString2) < 0)
            return -1;
          return 0;
        }
      });
      localObject1 = new StringBuffer();
      localObject2 = ((List)localObject2).iterator();
    }
    while (true)
    {
      if (!((Iterator)localObject2).hasNext())
      {
        Log.d("p-----", ((StringBuffer)localObject1).toString());
        return ((StringBuffer)localObject1).toString().replaceAll("\n", "").replaceAll("\r", "");
        ((List)localObject2).add((String)((Iterator)localObject1).next());
        break;
      }
      String str = (String)((Iterator)localObject2).next();
      if (!str.equals("pic"))
      {
        ((StringBuffer)localObject1).append(str);
        ((StringBuffer)localObject1).append("=");
        ((StringBuffer)localObject1).append((String)this.mParams.get(str));
        ((StringBuffer)localObject1).append("&");
      }
    }
  }

  public Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.network.ReqParam
 * JD-Core Version:    0.6.2
 */