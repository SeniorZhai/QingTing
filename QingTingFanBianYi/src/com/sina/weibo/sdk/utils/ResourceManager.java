package com.sina.weibo.sdk.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.StateSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;

public class ResourceManager
{
  private static final String DRAWABLE = "drawable";
  private static final String DRAWABLE_HDPI = "drawable-hdpi";
  private static final String DRAWABLE_LDPI = "drawable-ldpi";
  private static final String DRAWABLE_MDPI = "drawable-mdpi";
  private static final String DRAWABLE_XHDPI = "drawable-xhdpi";
  private static final String DRAWABLE_XXHDPI = "drawable-xxhdpi";
  private static final String[] PRE_INSTALL_DRAWBLE_PATHS = { "drawable-xxhdpi", "drawable-xhdpi", "drawable-hdpi", "drawable-mdpi", "drawable-ldpi", "drawable" };
  private static final String TAG = ResourceManager.class.getName();

  public static ColorStateList createColorStateList(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt2 = { 16842919 };
    int[] arrayOfInt3 = { 16842913 };
    int[] arrayOfInt1 = StateSet.WILD_CARD;
    return new ColorStateList(new int[][] { arrayOfInt2, arrayOfInt3, { 16842908 }, arrayOfInt1 }, new int[] { paramInt2, paramInt2, paramInt2, paramInt1 });
  }

  public static StateListDrawable createStateListDrawable(Context paramContext, String paramString1, String paramString2)
  {
    if (paramString1.indexOf(".9") > -1)
    {
      paramString1 = getNinePatchDrawable(paramContext, paramString1);
      if (paramString2.indexOf(".9") <= -1)
        break label98;
    }
    label98: for (paramContext = getNinePatchDrawable(paramContext, paramString2); ; paramContext = getDrawable(paramContext, paramString2))
    {
      paramString2 = new StateListDrawable();
      paramString2.addState(new int[] { 16842919 }, paramContext);
      paramString2.addState(new int[] { 16842913 }, paramContext);
      paramString2.addState(new int[] { 16842908 }, paramContext);
      paramString2.addState(StateSet.WILD_CARD, paramString1);
      return paramString2;
      paramString1 = getDrawable(paramContext, paramString1);
      break;
    }
  }

  public static int dp2px(Context paramContext, int paramInt)
  {
    paramContext = paramContext.getResources().getDisplayMetrics();
    return (int)(paramInt * paramContext.density + 0.5D);
  }

  private static Drawable extractDrawable(Context paramContext, String paramString)
    throws Exception
  {
    InputStream localInputStream = paramContext.getAssets().open(paramString);
    DisplayMetrics localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
    TypedValue localTypedValue = new TypedValue();
    localTypedValue.density = localDisplayMetrics.densityDpi;
    paramContext = Drawable.createFromResourceStream(paramContext.getResources(), localTypedValue, localInputStream, paramString);
    localInputStream.close();
    return paramContext;
  }

  private static View extractView(Context paramContext, String paramString, ViewGroup paramViewGroup)
    throws Exception
  {
    paramString = paramContext.getAssets().openXmlResourceParser(paramString);
    return ((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(paramString, paramViewGroup);
  }

  private static String getAppropriatePathOfDrawable(Context paramContext, String paramString)
  {
    String str1;
    if (TextUtils.isEmpty(paramString))
    {
      LogUtil.e(TAG, "id is NOT correct!");
      str1 = null;
      return str1;
    }
    String str2 = getCurrentDpiFolder(paramContext);
    LogUtil.d(TAG, "find Appropriate path...");
    int j = -1;
    int k = -1;
    int n = -1;
    int i = 0;
    label44: if (i >= PRE_INSTALL_DRAWBLE_PATHS.length)
    {
      i = n;
      label57: if ((j <= 0) || (i <= 0))
        break label198;
      if (Math.abs(k - i) > Math.abs(k - j))
        break label191;
    }
    while (true)
    {
      if (i >= 0)
        break label242;
      LogUtil.e(TAG, "Not find the appropriate path for drawable");
      return null;
      if (PRE_INSTALL_DRAWBLE_PATHS[i].equals(str2))
        k = i;
      str1 = PRE_INSTALL_DRAWBLE_PATHS[i] + "/" + paramString;
      int m = j;
      if (isFileExisted(paramContext, str1))
      {
        if (k == i)
          break;
        if (k >= 0)
          break label188;
        m = i;
      }
      i += 1;
      j = m;
      break label44;
      label188: break label57;
      label191: i = j;
      continue;
      label198: if ((j > 0) && (i < 0))
      {
        i = j;
      }
      else if ((j >= 0) || (i <= 0))
      {
        i = -1;
        LogUtil.e(TAG, "Not find the appropriate path for drawable");
      }
    }
    label242: return PRE_INSTALL_DRAWBLE_PATHS[i] + "/" + paramString;
  }

  private static String getCurrentDpiFolder(Context paramContext)
  {
    int i = paramContext.getResources().getDisplayMetrics().densityDpi;
    if (i <= 120)
      return "drawable-ldpi";
    if ((i > 120) && (i <= 160))
      return "drawable-mdpi";
    if ((i > 160) && (i <= 240))
      return "drawable-hdpi";
    if ((i > 240) && (i <= 320))
      return "drawable-xhdpi";
    return "drawable-xxhdpi";
  }

  public static Drawable getDrawable(Context paramContext, String paramString)
  {
    return getDrawableFromAssert(paramContext, getAppropriatePathOfDrawable(paramContext, paramString), false);
  }

  // ERROR //
  private static Drawable getDrawableFromAssert(Context paramContext, String paramString, boolean paramBoolean)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 112	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   4: astore 5
    //   6: aconst_null
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aload 5
    //   13: aload_1
    //   14: invokevirtual 118	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   17: astore_1
    //   18: aload_1
    //   19: ifnull +197 -> 216
    //   22: aload_1
    //   23: astore_3
    //   24: aload_1
    //   25: astore 4
    //   27: aload_1
    //   28: invokestatic 230	android/graphics/BitmapFactory:decodeStream	(Ljava/io/InputStream;)Landroid/graphics/Bitmap;
    //   31: astore 5
    //   33: aload_1
    //   34: astore_3
    //   35: aload_1
    //   36: astore 4
    //   38: aload_0
    //   39: invokevirtual 91	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   42: invokevirtual 97	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   45: astore 6
    //   47: iload_2
    //   48: ifeq +74 -> 122
    //   51: aload_1
    //   52: astore_3
    //   53: aload_1
    //   54: astore 4
    //   56: aload_0
    //   57: invokevirtual 91	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   60: invokevirtual 234	android/content/res/Resources:getConfiguration	()Landroid/content/res/Configuration;
    //   63: astore 7
    //   65: aload_1
    //   66: astore_3
    //   67: aload_1
    //   68: astore 4
    //   70: new 236	android/graphics/drawable/NinePatchDrawable
    //   73: dup
    //   74: new 93	android/content/res/Resources
    //   77: dup
    //   78: aload_0
    //   79: invokevirtual 112	android/content/Context:getAssets	()Landroid/content/res/AssetManager;
    //   82: aload 6
    //   84: aload 7
    //   86: invokespecial 239	android/content/res/Resources:<init>	(Landroid/content/res/AssetManager;Landroid/util/DisplayMetrics;Landroid/content/res/Configuration;)V
    //   89: aload 5
    //   91: aload 5
    //   93: invokevirtual 245	android/graphics/Bitmap:getNinePatchChunk	()[B
    //   96: new 247	android/graphics/Rect
    //   99: dup
    //   100: iconst_0
    //   101: iconst_0
    //   102: iconst_0
    //   103: iconst_0
    //   104: invokespecial 250	android/graphics/Rect:<init>	(IIII)V
    //   107: aconst_null
    //   108: invokespecial 253	android/graphics/drawable/NinePatchDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;[BLandroid/graphics/Rect;Ljava/lang/String;)V
    //   111: astore_0
    //   112: aload_1
    //   113: ifnull +7 -> 120
    //   116: aload_1
    //   117: invokevirtual 138	java/io/InputStream:close	()V
    //   120: aload_0
    //   121: areturn
    //   122: aload_1
    //   123: astore_3
    //   124: aload_1
    //   125: astore 4
    //   127: aload 5
    //   129: aload 6
    //   131: getfield 125	android/util/DisplayMetrics:densityDpi	I
    //   134: invokevirtual 257	android/graphics/Bitmap:setDensity	(I)V
    //   137: aload_1
    //   138: astore_3
    //   139: aload_1
    //   140: astore 4
    //   142: new 259	android/graphics/drawable/BitmapDrawable
    //   145: dup
    //   146: aload_0
    //   147: invokevirtual 91	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   150: aload 5
    //   152: invokespecial 262	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
    //   155: astore_0
    //   156: goto -44 -> 112
    //   159: astore_0
    //   160: aload_3
    //   161: astore 4
    //   163: aload_0
    //   164: invokevirtual 265	java/io/IOException:printStackTrace	()V
    //   167: aload_3
    //   168: ifnull +46 -> 214
    //   171: aload_3
    //   172: invokevirtual 138	java/io/InputStream:close	()V
    //   175: aconst_null
    //   176: areturn
    //   177: astore_0
    //   178: aload_0
    //   179: invokevirtual 265	java/io/IOException:printStackTrace	()V
    //   182: goto -7 -> 175
    //   185: astore_0
    //   186: aload 4
    //   188: ifnull +8 -> 196
    //   191: aload 4
    //   193: invokevirtual 138	java/io/InputStream:close	()V
    //   196: aload_0
    //   197: athrow
    //   198: astore_1
    //   199: aload_1
    //   200: invokevirtual 265	java/io/IOException:printStackTrace	()V
    //   203: goto -7 -> 196
    //   206: astore_1
    //   207: aload_1
    //   208: invokevirtual 265	java/io/IOException:printStackTrace	()V
    //   211: goto -91 -> 120
    //   214: aconst_null
    //   215: areturn
    //   216: aconst_null
    //   217: astore_0
    //   218: goto -106 -> 112
    //
    // Exception table:
    //   from	to	target	type
    //   11	18	159	java/io/IOException
    //   27	33	159	java/io/IOException
    //   38	47	159	java/io/IOException
    //   56	65	159	java/io/IOException
    //   70	112	159	java/io/IOException
    //   127	137	159	java/io/IOException
    //   142	156	159	java/io/IOException
    //   171	175	177	java/io/IOException
    //   11	18	185	finally
    //   27	33	185	finally
    //   38	47	185	finally
    //   56	65	185	finally
    //   70	112	185	finally
    //   127	137	185	finally
    //   142	156	185	finally
    //   163	167	185	finally
    //   191	196	198	java/io/IOException
    //   116	120	206	java/io/IOException
  }

  public static Locale getLanguage()
  {
    Locale localLocale = Locale.getDefault();
    if ((Locale.SIMPLIFIED_CHINESE.equals(localLocale)) || (Locale.TRADITIONAL_CHINESE.equals(localLocale)))
      return localLocale;
    return Locale.ENGLISH;
  }

  public static Drawable getNinePatchDrawable(Context paramContext, String paramString)
  {
    return getDrawableFromAssert(paramContext, getAppropriatePathOfDrawable(paramContext, paramString), true);
  }

  public static String getString(Context paramContext, String paramString1, String paramString2, String paramString3)
  {
    paramContext = getLanguage();
    if (Locale.SIMPLIFIED_CHINESE.equals(paramContext))
      return paramString2;
    if (Locale.TRADITIONAL_CHINESE.equals(paramContext))
      return paramString3;
    return paramString1;
  }

  private static boolean isFileExisted(Context paramContext, String paramString)
  {
    if ((paramContext == null) || (TextUtils.isEmpty(paramString)));
    Context localContext;
    while (true)
    {
      return false;
      Object localObject2 = paramContext.getAssets();
      Object localObject1 = null;
      paramContext = null;
      try
      {
        localObject2 = ((AssetManager)localObject2).open(paramString);
        paramContext = (Context)localObject2;
        localObject1 = localObject2;
        LogUtil.d(TAG, "file [" + paramString + "] existed");
        if (localObject2 != null);
        try
        {
          ((InputStream)localObject2).close();
          return true;
        }
        catch (IOException paramContext)
        {
          while (true)
            paramContext.printStackTrace();
        }
      }
      catch (IOException localIOException)
      {
        localContext = paramContext;
        LogUtil.d(TAG, "file [" + paramString + "] NOT existed");
        if (paramContext != null)
          try
          {
            paramContext.close();
            return false;
          }
          catch (IOException paramContext)
          {
            paramContext.printStackTrace();
            return false;
          }
      }
      finally
      {
        if (localContext == null);
      }
    }
    try
    {
      localContext.close();
      throw paramContext;
    }
    catch (IOException paramString)
    {
      while (true)
        paramString.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.ResourceManager
 * JD-Core Version:    0.6.2
 */