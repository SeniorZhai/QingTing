package com.intowow.sdk.f;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Build.VERSION;
import android.support.v4.util.LruCache;
import com.intowow.sdk.j.k;

public class a
{
  private static a c = null;
  private String a = null;
  private LruCache<String, a> b = null;

  private a(Activity paramActivity)
  {
    this.a = k.a(paramActivity).b();
    this.b = new LruCache((int)Runtime.getRuntime().maxMemory() / 8)
    {
      protected int a(String paramAnonymousString, a.a paramAnonymousa)
      {
        return paramAnonymousa.b.getRowBytes() * paramAnonymousa.b.getHeight() + 4;
      }
    };
  }

  public static a a(Activity paramActivity)
  {
    if (c == null)
      c = new a(paramActivity);
    return c;
  }

  public void a(String paramString)
  {
    if ((Build.VERSION.SDK_INT <= 10) && (this.b.get(paramString) != null))
      ((a)this.b.get(paramString)).b.recycle();
    this.b.remove(paramString);
  }

  // ERROR //
  public android.graphics.drawable.Drawable b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/intowow/sdk/f/a:b	Landroid/support/v4/util/LruCache;
    //   4: aload_1
    //   5: invokevirtual 65	android/support/v4/util/LruCache:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   8: checkcast 8	com/intowow/sdk/f/a$a
    //   11: astore_2
    //   12: aload_2
    //   13: ifnull +59 -> 72
    //   16: aload_2
    //   17: getfield 68	com/intowow/sdk/f/a$a:b	Landroid/graphics/Bitmap;
    //   20: invokevirtual 85	android/graphics/Bitmap:isRecycled	()Z
    //   23: ifne +49 -> 72
    //   26: aload_2
    //   27: getfield 88	com/intowow/sdk/f/a$a:a	Z
    //   30: ifeq +30 -> 60
    //   33: new 90	android/graphics/drawable/NinePatchDrawable
    //   36: dup
    //   37: aload_2
    //   38: getfield 68	com/intowow/sdk/f/a$a:b	Landroid/graphics/Bitmap;
    //   41: aload_2
    //   42: getfield 68	com/intowow/sdk/f/a$a:b	Landroid/graphics/Bitmap;
    //   45: invokevirtual 94	android/graphics/Bitmap:getNinePatchChunk	()[B
    //   48: new 96	android/graphics/Rect
    //   51: dup
    //   52: invokespecial 97	android/graphics/Rect:<init>	()V
    //   55: aconst_null
    //   56: invokespecial 100	android/graphics/drawable/NinePatchDrawable:<init>	(Landroid/graphics/Bitmap;[BLandroid/graphics/Rect;Ljava/lang/String;)V
    //   59: areturn
    //   60: new 102	android/graphics/drawable/BitmapDrawable
    //   63: dup
    //   64: aload_2
    //   65: getfield 68	com/intowow/sdk/f/a$a:b	Landroid/graphics/Bitmap;
    //   68: invokespecial 105	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   71: areturn
    //   72: aload_0
    //   73: aload_1
    //   74: invokevirtual 107	com/intowow/sdk/f/a:a	(Ljava/lang/String;)V
    //   77: new 109	java/lang/StringBuilder
    //   80: dup
    //   81: aload_0
    //   82: getfield 26	com/intowow/sdk/f/a:a	Ljava/lang/String;
    //   85: invokestatic 115	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   88: invokespecial 117	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   91: aload_1
    //   92: invokevirtual 121	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   95: invokevirtual 124	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   98: invokestatic 130	android/graphics/BitmapFactory:decodeFile	(Ljava/lang/String;)Landroid/graphics/Bitmap;
    //   101: astore_3
    //   102: aload_3
    //   103: invokevirtual 94	android/graphics/Bitmap:getNinePatchChunk	()[B
    //   106: astore_2
    //   107: aload_2
    //   108: invokestatic 136	android/graphics/NinePatch:isNinePatchChunk	([B)Z
    //   111: istore 4
    //   113: iload 4
    //   115: ifeq +46 -> 161
    //   118: new 90	android/graphics/drawable/NinePatchDrawable
    //   121: dup
    //   122: aload_3
    //   123: aload_2
    //   124: new 96	android/graphics/Rect
    //   127: dup
    //   128: invokespecial 97	android/graphics/Rect:<init>	()V
    //   131: aconst_null
    //   132: invokespecial 100	android/graphics/drawable/NinePatchDrawable:<init>	(Landroid/graphics/Bitmap;[BLandroid/graphics/Rect;Ljava/lang/String;)V
    //   135: astore_2
    //   136: aload_0
    //   137: getfield 28	com/intowow/sdk/f/a:b	Landroid/support/v4/util/LruCache;
    //   140: aload_1
    //   141: new 8	com/intowow/sdk/f/a$a
    //   144: dup
    //   145: aload_0
    //   146: iload 4
    //   148: aload_3
    //   149: invokespecial 139	com/intowow/sdk/f/a$a:<init>	(Lcom/intowow/sdk/f/a;ZLandroid/graphics/Bitmap;)V
    //   152: invokevirtual 143	android/support/v4/util/LruCache:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   155: pop
    //   156: aload_2
    //   157: areturn
    //   158: astore_1
    //   159: aload_2
    //   160: areturn
    //   161: new 102	android/graphics/drawable/BitmapDrawable
    //   164: dup
    //   165: aload_3
    //   166: invokespecial 105	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/graphics/Bitmap;)V
    //   169: astore_2
    //   170: goto -34 -> 136
    //   173: astore_1
    //   174: aconst_null
    //   175: areturn
    //   176: astore_1
    //   177: aconst_null
    //   178: areturn
    //   179: astore_1
    //   180: aload_2
    //   181: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   136	156	158	java/lang/Exception
    //   77	113	173	java/lang/Exception
    //   118	136	173	java/lang/Exception
    //   161	170	173	java/lang/Exception
    //   77	113	176	java/lang/OutOfMemoryError
    //   118	136	176	java/lang/OutOfMemoryError
    //   161	170	176	java/lang/OutOfMemoryError
    //   136	156	179	java/lang/OutOfMemoryError
  }

  class a
  {
    public boolean a;
    public Bitmap b;

    public a(boolean paramBitmap, Bitmap arg3)
    {
      this.a = paramBitmap;
      Object localObject;
      this.b = localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.f.a
 * JD-Core Version:    0.6.2
 */