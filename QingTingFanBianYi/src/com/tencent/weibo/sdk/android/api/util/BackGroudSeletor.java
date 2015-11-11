package com.tencent.weibo.sdk.android.api.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class BackGroudSeletor
{
  static int[] EMPTY_STATE_SET = new int[0];
  static int[] ENABLED_STATE_SET;
  static int[] PRESSED_ENABLED_STATE_SET = { 16842910, 16842919 };
  private static String pix = "";

  static
  {
    ENABLED_STATE_SET = new int[] { 16842910 };
  }

  public static StateListDrawable createBgByImageIds(String[] paramArrayOfString, Context paramContext)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    Drawable localDrawable = getdrawble(paramArrayOfString[0], paramContext);
    paramArrayOfString = getdrawble(paramArrayOfString[1], paramContext);
    localStateListDrawable.addState(PRESSED_ENABLED_STATE_SET, paramArrayOfString);
    localStateListDrawable.addState(ENABLED_STATE_SET, localDrawable);
    localStateListDrawable.addState(EMPTY_STATE_SET, localDrawable);
    return localStateListDrawable;
  }

  public static String getPix()
  {
    return pix;
  }

  public static Drawable getdrawble(String paramString, Context paramContext)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      File localFile = new File(paramString + pix + ".png");
      localObject1 = localObject2;
      String str = paramString + pix + ".png";
      localObject1 = localObject2;
      if (!localFile.isFile())
      {
        localObject1 = localObject2;
        str = paramString + "480x800" + ".png";
      }
      localObject1 = localObject2;
      paramString = BitmapFactory.decodeStream(paramContext.getAssets().open(str));
      localObject1 = paramString;
      paramString = new BitmapDrawable(paramString);
      return paramString;
    }
    catch (IOException paramString)
    {
      if (localObject1 != null)
        ((Bitmap)localObject1).recycle();
      paramString.printStackTrace();
    }
    return null;
  }

  public static void setPix(String paramString)
  {
    pix = paramString;
  }

  public static InputStream zipPic(InputStream paramInputStream)
  {
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.api.util.BackGroudSeletor
 * JD-Core Version:    0.6.2
 */