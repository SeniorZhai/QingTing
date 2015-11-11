package com.taobao.newxp.common.b;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.animation.Animation;
import android.widget.ImageView;
import com.taobao.munion.base.a;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.controller.XpListenersCenter.STATUS;
import com.taobao.newxp.view.common.gif.g;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.Stack;
import java.util.WeakHashMap;

public class d
{
  public static boolean a = false;
  private static final byte b = 0;
  private static final byte c = 1;
  private static final String d = d.class.getName();
  private static final long e = 104857600L;
  private static final long f = 10485760L;
  private static final Map<ImageView, String> g = Collections.synchronizedMap(new WeakHashMap());
  private static Thread h;

  public static Bitmap a(Bitmap paramBitmap)
  {
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
      RectF localRectF = new RectF(localRect);
      localPaint.setAntiAlias(true);
      localCanvas.drawARGB(0, 0, 0, 0);
      localPaint.setColor(-12434878);
      localCanvas.drawRoundRect(localRectF, paramBitmap.getWidth() / 6, paramBitmap.getHeight() / 6, localPaint);
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
      localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
      paramBitmap.recycle();
      return localBitmap;
    }
    catch (OutOfMemoryError paramBitmap)
    {
      com.taobao.newxp.common.Log.e(d, "Cant`t create round corner bitmap. [OutOfMemoryError] ");
      return null;
    }
    catch (NullPointerException paramBitmap)
    {
      return null;
    }
    catch (RuntimeException paramBitmap)
    {
    }
    return null;
  }

  public static String a(Context paramContext, String paramString)
  {
    if (b.c(paramString))
      return null;
    try
    {
      localObject = b(paramString) + ".tmp";
      long l;
      if (AlimmContext.getAliContext().getAppUtils().d())
      {
        paramContext = Environment.getExternalStorageDirectory().getCanonicalPath();
        l = 104857600L;
        paramContext = new File(paramContext + "/download/.um");
        if (!paramContext.exists())
          break label260;
        if (c(paramContext.getCanonicalFile()) > l)
          a(paramContext);
      }
      while (true)
      {
        localObject = new File(paramContext, (String)localObject);
        try
        {
          ((File)localObject).createNewFile();
          paramContext = new FileOutputStream((File)localObject);
          localInputStream = (InputStream)new URL(paramString).openConnection().getContent();
          byte[] arrayOfByte = new byte[4096];
          while (true)
          {
            int i = localInputStream.read(arrayOfByte);
            if (i == -1)
              break;
            paramContext.write(arrayOfByte, 0, i);
          }
        }
        catch (Exception paramContext)
        {
        }
        com.taobao.newxp.common.Log.a(d, paramContext.getStackTrace().toString() + "\t url:\t" + b.a + paramString);
        if ((localObject != null) && (((File)localObject).exists()))
          ((File)localObject).deleteOnExit();
        return null;
        paramContext = paramContext.getCacheDir().getCanonicalPath();
        l = 10485760L;
        break;
        label260: if (!paramContext.mkdirs())
          com.taobao.newxp.common.Log.b(d, "Failed to create directory" + paramContext.getAbsolutePath() + ". Check permission. Make sure WRITE_EXTERNAL_STORAGE is added in your Manifest.xml");
      }
    }
    catch (Exception paramContext)
    {
      Object localObject;
      InputStream localInputStream;
      while (true)
        localObject = null;
      paramContext.flush();
      localInputStream.close();
      paramContext.close();
      paramContext = new File(((File)localObject).getParent(), ((File)localObject).getName().replace(".tmp", ""));
      ((File)localObject).renameTo(paramContext);
      com.taobao.newxp.common.Log.a(d, "download img[" + paramString + "]  to " + paramContext.getCanonicalPath());
      paramContext = paramContext.getCanonicalPath();
    }
    return paramContext;
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, null, null, false);
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean, a parama)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, parama, null, false);
  }

  public static void a(Context paramContext, ImageView paramImageView, String paramString, boolean paramBoolean, a parama, Animation paramAnimation)
  {
    a(paramContext, paramImageView, paramString, paramBoolean, parama, paramAnimation, false);
  }

  public static void a(final Context paramContext, final ImageView paramImageView, final String paramString, final boolean paramBoolean1, final a parama, final Animation paramAnimation, final boolean paramBoolean2)
  {
    if (paramImageView == null);
    do
    {
      return;
      g.put(paramImageView, paramString);
      try
      {
        new d(paramContext, paramString, b(paramContext, paramString))
        {
          public void a(Drawable paramAnonymousDrawable)
          {
            if ((paramAnonymousDrawable instanceof AnimationDrawable))
            {
              d.a(paramContext, paramImageView, paramAnonymousDrawable, true, parama, paramAnimation, false, paramString);
              return;
            }
            d.a(paramContext, paramImageView, paramAnonymousDrawable, paramBoolean1, parama, paramAnimation, paramBoolean2, paramString);
          }

          protected void onPreExecute()
          {
            super.onPreExecute();
            if (parama != null)
            {
              if (1 == this.h)
                parama.a(d.b.a);
            }
            else
              return;
            parama.a(d.b.b);
          }
        }
        .execute(new Object[0]);
        return;
      }
      catch (Exception paramContext)
      {
        com.taobao.newxp.common.Log.b(d, paramContext.toString());
      }
    }
    while (parama == null);
    parama.a(XpListenersCenter.STATUS.FAIL);
  }

  public static void a(Context paramContext, String paramString, final c paramc)
  {
    try
    {
      new d(paramContext, paramString, b(paramContext, paramString))
      {
        public void a(Drawable paramAnonymousDrawable)
        {
          paramc.a(paramAnonymousDrawable);
        }

        protected void onPreExecute()
        {
          super.onPreExecute();
          if (paramc != null)
          {
            if (1 == this.h)
              paramc.a(d.b.a);
          }
          else
            return;
          paramc.a(d.b.b);
        }
      }
      .execute(new Object[0]);
      return;
    }
    catch (Exception paramContext)
    {
      do
        com.taobao.newxp.common.Log.b(d, "", paramContext);
      while (paramc == null);
      paramc.a(null);
    }
  }

  protected static void a(File paramFile)
  {
    try
    {
      if (h == null)
      {
        h = new Thread(new Runnable()
        {
          public void run()
          {
            d.b(this.a);
            d.a(null);
          }
        });
        h.start();
      }
      return;
    }
    catch (Exception paramFile)
    {
      android.util.Log.w(d, "", paramFile);
    }
  }

  private static boolean a(ImageView paramImageView, String paramString)
  {
    paramImageView = (String)g.get(paramImageView);
    return (paramImageView != null) && (!paramImageView.equals(paramString));
  }

  public static File b(Context paramContext, String paramString)
    throws IOException
  {
    paramString = b(paramString);
    if (AlimmContext.getAliContext().getAppUtils().d());
    for (paramContext = Environment.getExternalStorageDirectory().getCanonicalPath(); ; paramContext = paramContext.getCacheDir().getCanonicalPath())
    {
      paramContext = new File(new File(paramContext + "/download/.um"), paramString);
      if (!paramContext.exists())
        break;
      return paramContext;
    }
    return null;
  }

  private static String b(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    String str = "";
    if (i >= 0)
      str = paramString.substring(i);
    return b.a(paramString) + str;
  }

  private static void b(Context paramContext, ImageView paramImageView, Drawable paramDrawable, boolean paramBoolean1, a parama, Animation paramAnimation, boolean paramBoolean2, String paramString)
  {
    paramContext = paramDrawable;
    if (paramBoolean2)
    {
      paramContext = paramDrawable;
      if (paramDrawable == null);
    }
    while (true)
    {
      try
      {
        paramContext = new BitmapDrawable(a(((BitmapDrawable)paramDrawable).getBitmap()));
        break label207;
        if (parama != null)
          parama.a(XpListenersCenter.STATUS.FAIL);
        com.taobao.newxp.common.Log.e(d, "bind drawable failed. drawable [" + paramContext + "]  imageView[+" + paramImageView + "+]");
        return;
        if (a(paramImageView, paramString))
        {
          if (parama == null)
            continue;
          parama.a(XpListenersCenter.STATUS.FAIL);
          continue;
        }
      }
      catch (Exception paramContext)
      {
        com.taobao.newxp.common.Log.b(d, "bind failed", paramContext);
        if (parama == null)
          continue;
        parama.a(XpListenersCenter.STATUS.FAIL);
        continue;
      }
      finally
      {
      }
      if (paramBoolean1 == true)
        paramImageView.setBackgroundDrawable(paramContext);
      while (true)
      {
        if (paramAnimation != null)
          paramImageView.startAnimation(paramAnimation);
        if (parama == null)
          break;
        parama.a(XpListenersCenter.STATUS.SUCCESS);
        break;
        paramImageView.setImageDrawable(paramContext);
      }
      label207: if (paramContext != null)
        if (paramImageView != null);
    }
  }

  private static long c(File paramFile)
  {
    long l2;
    if ((paramFile == null) || (!paramFile.exists()) || (!paramFile.isDirectory()))
    {
      l2 = 0L;
      return l2;
    }
    Stack localStack = new Stack();
    localStack.clear();
    localStack.push(paramFile);
    long l1 = 0L;
    while (true)
    {
      l2 = l1;
      if (localStack.isEmpty())
        break;
      paramFile = ((File)localStack.pop()).listFiles();
      int i = 0;
      if (i < paramFile.length)
      {
        if (paramFile[i].isDirectory())
          localStack.push(paramFile[i]);
        while (true)
        {
          i += 1;
          break;
          l1 = paramFile[i].length() + l1;
        }
      }
    }
  }

  private static Drawable c(String paramString)
  {
    if ((paramString != null) && (paramString.trim().length() > 0));
    switch (d(paramString))
    {
    default:
      return null;
    case 0:
      try
      {
        paramString = Drawable.createFromPath(paramString);
        return paramString;
      }
      catch (OutOfMemoryError paramString)
      {
        com.taobao.newxp.common.Log.e(d, "Resutil fetchImage OutOfMemoryError:" + paramString.toString());
        return null;
      }
    case 1:
    }
    paramString = new File(paramString);
    try
    {
      paramString = new g(new FileInputStream(paramString), null);
      return paramString;
    }
    catch (FileNotFoundException paramString)
    {
      com.taobao.newxp.common.Log.e(d, "Resutil fetchGifImage FileNotFoundException:" + paramString.toString());
    }
    return null;
  }

  private static byte d(String paramString)
  {
    if ((paramString != null) && (paramString.toLowerCase().endsWith("gif")))
      return 1;
    return 0;
  }

  private static boolean d(File paramFile)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramFile != null)
      bool1 = bool2;
    while (true)
    {
      int i;
      try
      {
        if (!paramFile.exists())
          break label106;
        bool1 = bool2;
        if (!paramFile.canWrite())
          break label106;
        if (!paramFile.isDirectory())
          return false;
        paramFile = paramFile.listFiles();
        i = 0;
        if (i < paramFile.length)
          if (paramFile[i].isDirectory())
            d(paramFile[i]);
          else if (new Date().getTime() - paramFile[i].lastModified() > 1800000L)
            paramFile[i].delete();
      }
      catch (Exception paramFile)
      {
        return false;
      }
      bool1 = true;
      label106: return bool1;
      i += 1;
    }
  }

  public static abstract interface a
  {
    public abstract void a(d.b paramb);

    public abstract void a(XpListenersCenter.STATUS paramSTATUS);
  }

  public static enum b
  {
  }

  public static abstract interface c
  {
    public abstract void a(Drawable paramDrawable);

    public abstract void a(d.b paramb);
  }

  static abstract class d extends AsyncTask<Object, Integer, Drawable>
  {
    private Context a;
    private String b;
    private File c;
    int h = 0;

    public d(Context paramContext, String paramString, File paramFile)
    {
      this.c = paramFile;
      this.a = paramContext;
      this.b = paramString;
    }

    protected Drawable a(Object[] paramArrayOfObject)
    {
      if (d.a);
      try
      {
        Thread.sleep(3000L);
        if (1 == this.h)
        {
          paramArrayOfObject = d.a(this.c.getAbsolutePath());
          if (paramArrayOfObject == null)
            this.c.delete();
          com.taobao.newxp.common.Log.c(d.a(), "get drawable from cacheFile.");
          return paramArrayOfObject;
        }
      }
      catch (InterruptedException paramArrayOfObject)
      {
        while (true)
          paramArrayOfObject.printStackTrace();
      }
      while (true)
      {
        try
        {
          d.a(this.a, this.b);
          paramArrayOfObject = d.b(this.a, this.b);
          if ((paramArrayOfObject != null) && (paramArrayOfObject.exists()))
          {
            paramArrayOfObject = d.a(paramArrayOfObject.getAbsolutePath());
            com.taobao.newxp.common.Log.c(d.a(), "get drawable from net else file.");
            return paramArrayOfObject;
          }
        }
        catch (Exception paramArrayOfObject)
        {
          com.taobao.newxp.common.Log.e(d.a(), paramArrayOfObject.toString(), paramArrayOfObject);
          return null;
        }
        paramArrayOfObject = null;
      }
    }

    public abstract void a(Drawable paramDrawable);

    protected void b(Drawable paramDrawable)
    {
      a(paramDrawable);
    }

    protected void onPreExecute()
    {
      super.onPreExecute();
      if ((this.c != null) && (this.c.exists()))
        this.h = 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.common.b.d
 * JD-Core Version:    0.6.2
 */