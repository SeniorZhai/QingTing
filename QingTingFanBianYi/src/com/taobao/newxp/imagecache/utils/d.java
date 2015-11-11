package com.taobao.newxp.imagecache.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.ImageView;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.controller.ExchangeDataService;
import java.lang.ref.WeakReference;

public abstract class d
{
  private static final String a = "ImageWorker";
  private static final int b = 200;
  private static final int k = 0;
  private static final int l = 1;
  private static final int m = 2;
  private static final int n = 3;
  protected boolean c = false;
  protected Resources d;
  private ImageCache e;
  private ImageCache.a f;
  private Bitmap g;
  private boolean h = true;
  private boolean i = false;
  private final Object j = new Object();

  protected d(Context paramContext)
  {
    this.d = paramContext.getResources();
  }

  public static void a(ImageView paramImageView)
  {
    paramImageView = c(paramImageView);
    if (paramImageView != null)
      paramImageView.a(true);
  }

  private void a(ImageView paramImageView, Drawable paramDrawable)
  {
    if (this.h)
    {
      paramDrawable = new TransitionDrawable(new Drawable[] { new ColorDrawable(17170445), paramDrawable });
      paramImageView.setBackgroundDrawable(new BitmapDrawable(this.d, this.g));
      paramImageView.setImageDrawable(paramDrawable);
      paramDrawable.startTransition(200);
      return;
    }
    paramImageView.setImageDrawable(paramDrawable);
  }

  private static b c(ImageView paramImageView)
  {
    if (paramImageView != null)
    {
      paramImageView = paramImageView.getDrawable();
      if ((paramImageView instanceof a))
        return ((a)paramImageView).a();
    }
    return null;
  }

  public static boolean c(Object paramObject, ImageView paramImageView)
  {
    paramImageView = c(paramImageView);
    if (paramImageView != null)
    {
      Object localObject = b.a(paramImageView);
      if ((localObject == null) || (!localObject.equals(paramObject)))
        paramImageView.a(true);
    }
    else
    {
      return true;
    }
    return false;
  }

  protected abstract Bitmap a(Object paramObject);

  protected void a()
  {
    if (this.e != null)
      this.e.a();
  }

  public void a(Bitmap paramBitmap)
  {
    this.g = paramBitmap;
  }

  public void a(FragmentActivity paramFragmentActivity, String paramString)
  {
    this.f = new ImageCache.a(paramFragmentActivity, paramString);
    this.e = ImageCache.a(paramFragmentActivity.getSupportFragmentManager(), this.f);
    new c().c(new Object[] { Integer.valueOf(1) });
  }

  public void a(FragmentManager paramFragmentManager, ImageCache.a parama)
  {
    this.f = parama;
    this.e = ImageCache.a(paramFragmentManager, this.f);
    new c().c(new Object[] { Integer.valueOf(1) });
  }

  public void a(ImageCache.a parama)
  {
    this.f = parama;
    this.e = ImageCache.a(parama);
    new c().c(new Object[] { Integer.valueOf(1) });
  }

  public void a(Object paramObject, ImageView paramImageView)
  {
    a(paramObject, paramImageView, null);
  }

  public void a(Object paramObject, ImageView paramImageView, int paramInt1, int paramInt2, ExchangeDataService paramExchangeDataService, Promoter paramPromoter, boolean paramBoolean)
  {
    a(paramObject, paramImageView, null, paramInt1, paramInt2, paramExchangeDataService, paramPromoter, paramBoolean);
  }

  public void a(Object paramObject, ImageView paramImageView, String paramString)
  {
    if (paramObject == null)
      return;
    if ((paramString != null) && (paramString.trim().length() > 0))
    {
      com.taobao.newxp.common.a.a.a locala = new com.taobao.newxp.common.a.a.a("");
      locala.a(paramString);
      if (paramObject != null)
        locala.b((String)paramObject);
      com.taobao.newxp.common.a.a.a().b(locala);
    }
    if (this.e != null);
    for (paramString = this.e.a(String.valueOf(paramObject)); ; paramString = null)
    {
      if (paramString != null)
      {
        paramImageView.setImageDrawable(paramString);
        return;
      }
      if (!c(paramObject, paramImageView))
        break;
      paramString = new b(paramImageView);
      paramImageView.setImageDrawable(new a(this.d, this.g, paramString));
      paramString.a(com.taobao.newxp.common.b.a.d, new Object[] { paramObject });
      return;
    }
  }

  public void a(Object paramObject, ImageView paramImageView, String paramString, int paramInt1, int paramInt2, ExchangeDataService paramExchangeDataService, Promoter paramPromoter, boolean paramBoolean)
  {
    if (paramObject == null)
      return;
    if ((paramString != null) && (paramString.trim().length() > 0))
    {
      com.taobao.newxp.common.a.a.a locala = new com.taobao.newxp.common.a.a.a("");
      locala.a(paramString);
      if (paramObject != null)
        locala.b((String)paramObject);
      com.taobao.newxp.common.a.a.a().b(locala);
    }
    if (this.e != null);
    for (paramString = this.e.a(String.valueOf(paramObject)); ; paramString = null)
    {
      if (paramString != null)
      {
        paramImageView.setImageDrawable(paramString);
        return;
      }
      if (!c(paramObject, paramImageView))
        break;
      paramString = new b(paramImageView, paramInt1, paramInt2, paramExchangeDataService, paramPromoter);
      paramImageView.setImageDrawable(new a(this.d, this.g, paramString));
      paramString.a(com.taobao.newxp.common.b.a.d, new Object[] { paramObject, Boolean.valueOf(paramBoolean) });
      return;
    }
  }

  public void a(Object paramObject, ImageView paramImageView, String paramString, boolean paramBoolean)
  {
    if (paramObject == null)
      return;
    if ((paramString != null) && (paramString.trim().length() > 0))
    {
      com.taobao.newxp.common.a.a.a locala = new com.taobao.newxp.common.a.a.a("");
      locala.a(paramString);
      if (paramObject != null)
        locala.b((String)paramObject);
      com.taobao.newxp.common.a.a.a().b(locala);
    }
    if (this.e != null);
    for (paramString = this.e.a(String.valueOf(paramObject)); ; paramString = null)
    {
      if (paramString != null)
      {
        paramImageView.setImageDrawable(paramString);
        return;
      }
      if (!c(paramObject, paramImageView))
        break;
      paramString = new b(paramImageView);
      paramImageView.setImageDrawable(new a(this.d, this.g, paramString));
      paramString.a(com.taobao.newxp.common.b.a.d, new Object[] { paramObject, Boolean.valueOf(paramBoolean) });
      return;
    }
  }

  public void a(boolean paramBoolean)
  {
    this.h = paramBoolean;
  }

  protected void b()
  {
    if (this.e != null)
      this.e.b();
  }

  public void b(int paramInt)
  {
    this.g = BitmapFactory.decodeResource(this.d, paramInt);
  }

  public void b(Object paramObject, ImageView paramImageView)
  {
    a(paramObject, paramImageView, null, true);
  }

  public void b(boolean paramBoolean)
  {
    this.i = paramBoolean;
    c(false);
  }

  protected void c()
  {
    if (this.e != null)
      this.e.c();
  }

  public void c(boolean paramBoolean)
  {
    synchronized (this.j)
    {
      this.c = paramBoolean;
      if (!this.c)
        this.j.notifyAll();
      return;
    }
  }

  protected void d()
  {
    if (this.e != null)
    {
      this.e.d();
      this.e = null;
    }
  }

  protected ImageCache f()
  {
    return this.e;
  }

  public void g()
  {
    new c().c(new Object[] { Integer.valueOf(0) });
  }

  public void h()
  {
    new c().c(new Object[] { Integer.valueOf(2) });
  }

  public void i()
  {
    new c().c(new Object[] { Integer.valueOf(3) });
  }

  private static class a extends BitmapDrawable
  {
    private final WeakReference<d.b> a;

    public a(Resources paramResources, Bitmap paramBitmap, d.b paramb)
    {
      super(paramBitmap);
      this.a = new WeakReference(paramb);
    }

    public d.b a()
    {
      return (d.b)this.a.get();
    }
  }

  private class b extends com.taobao.newxp.common.b.a<Object, Void, BitmapDrawable>
  {
    private String f;
    private Object g;
    private int h;
    private int i;
    private ExchangeDataService j;
    private Promoter k;
    private final WeakReference<ImageView> l;

    public b(ImageView arg2)
    {
      Object localObject;
      this.l = new WeakReference(localObject);
    }

    public b(ImageView paramInt1, int paramInt2, int paramExchangeDataService, ExchangeDataService paramPromoter, Promoter arg6)
    {
      this(paramInt1);
      this.j = paramPromoter;
      Object localObject;
      this.k = localObject;
      this.h = paramInt2;
      this.i = paramExchangeDataService;
    }

    private ImageView h()
    {
      ImageView localImageView = (ImageView)this.l.get();
      if (this == d.b(localImageView))
        return localImageView;
      return null;
    }

    protected void a(BitmapDrawable paramBitmapDrawable)
    {
      if ((e()) || (d.c(d.this)))
        paramBitmapDrawable = null;
      ImageView localImageView = h();
      if ((paramBitmapDrawable != null) && (localImageView != null))
        d.a(d.this, localImageView, paramBitmapDrawable);
    }

    protected void b(BitmapDrawable arg1)
    {
      super.b(???);
      synchronized (d.a(d.this))
      {
        d.a(d.this).notifyAll();
        return;
      }
    }

    // ERROR //
    protected BitmapDrawable e(Object[] paramArrayOfObject)
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore_3
      //   2: aload_0
      //   3: aload_1
      //   4: iconst_0
      //   5: aaload
      //   6: putfield 54	com/taobao/newxp/imagecache/utils/d$b:g	Ljava/lang/Object;
      //   9: aload_0
      //   10: getfield 54	com/taobao/newxp/imagecache/utils/d$b:g	Ljava/lang/Object;
      //   13: invokestatic 107	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
      //   16: astore 5
      //   18: aload_0
      //   19: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   22: invokestatic 90	com/taobao/newxp/imagecache/utils/d:a	(Lcom/taobao/newxp/imagecache/utils/d;)Ljava/lang/Object;
      //   25: astore_2
      //   26: aload_2
      //   27: monitorenter
      //   28: aload_0
      //   29: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   32: getfield 110	com/taobao/newxp/imagecache/utils/d:c	Z
      //   35: ifeq +32 -> 67
      //   38: aload_0
      //   39: invokevirtual 72	com/taobao/newxp/imagecache/utils/d$b:e	()Z
      //   42: istore 6
      //   44: iload 6
      //   46: ifne +21 -> 67
      //   49: aload_0
      //   50: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   53: invokestatic 90	com/taobao/newxp/imagecache/utils/d:a	(Lcom/taobao/newxp/imagecache/utils/d;)Ljava/lang/Object;
      //   56: invokevirtual 113	java/lang/Object:wait	()V
      //   59: goto -31 -> 28
      //   62: astore 4
      //   64: goto -36 -> 28
      //   67: aload_2
      //   68: monitorexit
      //   69: aload_0
      //   70: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   73: invokestatic 116	com/taobao/newxp/imagecache/utils/d:b	(Lcom/taobao/newxp/imagecache/utils/d;)Lcom/taobao/newxp/imagecache/utils/ImageCache;
      //   76: ifnull +237 -> 313
      //   79: aload_0
      //   80: invokevirtual 72	com/taobao/newxp/imagecache/utils/d$b:e	()Z
      //   83: ifne +230 -> 313
      //   86: aload_0
      //   87: invokespecial 78	com/taobao/newxp/imagecache/utils/d$b:h	()Landroid/widget/ImageView;
      //   90: ifnull +223 -> 313
      //   93: aload_0
      //   94: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   97: invokestatic 76	com/taobao/newxp/imagecache/utils/d:c	(Lcom/taobao/newxp/imagecache/utils/d;)Z
      //   100: ifne +213 -> 313
      //   103: aload_0
      //   104: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   107: invokestatic 116	com/taobao/newxp/imagecache/utils/d:b	(Lcom/taobao/newxp/imagecache/utils/d;)Lcom/taobao/newxp/imagecache/utils/ImageCache;
      //   110: aload 5
      //   112: invokevirtual 121	com/taobao/newxp/imagecache/utils/ImageCache:b	(Ljava/lang/String;)Landroid/graphics/Bitmap;
      //   115: astore_2
      //   116: aload_2
      //   117: ifnonnull +232 -> 349
      //   120: aload_0
      //   121: invokevirtual 72	com/taobao/newxp/imagecache/utils/d$b:e	()Z
      //   124: ifne +225 -> 349
      //   127: aload_0
      //   128: invokespecial 78	com/taobao/newxp/imagecache/utils/d$b:h	()Landroid/widget/ImageView;
      //   131: ifnull +218 -> 349
      //   134: aload_0
      //   135: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   138: invokestatic 76	com/taobao/newxp/imagecache/utils/d:c	(Lcom/taobao/newxp/imagecache/utils/d;)Z
      //   141: ifne +208 -> 349
      //   144: aload_0
      //   145: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   148: aload_1
      //   149: iconst_0
      //   150: aaload
      //   151: invokevirtual 124	com/taobao/newxp/imagecache/utils/d:a	(Ljava/lang/Object;)Landroid/graphics/Bitmap;
      //   154: astore 4
      //   156: aload 4
      //   158: astore_2
      //   159: aload_2
      //   160: ifnull +117 -> 277
      //   163: iconst_1
      //   164: aload_1
      //   165: iconst_1
      //   166: aaload
      //   167: checkcast 126	java/lang/Boolean
      //   170: invokevirtual 129	java/lang/Boolean:booleanValue	()Z
      //   173: if_icmpne +204 -> 377
      //   176: aload_2
      //   177: invokestatic 134	com/taobao/newxp/common/b/d:a	(Landroid/graphics/Bitmap;)Landroid/graphics/Bitmap;
      //   180: astore_1
      //   181: aload_0
      //   182: getfield 44	com/taobao/newxp/imagecache/utils/d$b:j	Lcom/taobao/newxp/controller/ExchangeDataService;
      //   185: ifnull +43 -> 228
      //   188: aload_1
      //   189: invokevirtual 140	android/graphics/Bitmap:getWidth	()I
      //   192: aload_0
      //   193: getfield 48	com/taobao/newxp/imagecache/utils/d$b:h	I
      //   196: if_icmpne +14 -> 210
      //   199: aload_1
      //   200: invokevirtual 143	android/graphics/Bitmap:getHeight	()I
      //   203: aload_0
      //   204: getfield 50	com/taobao/newxp/imagecache/utils/d$b:i	I
      //   207: if_icmpeq +21 -> 228
      //   210: aload_0
      //   211: getfield 44	com/taobao/newxp/imagecache/utils/d$b:j	Lcom/taobao/newxp/controller/ExchangeDataService;
      //   214: iconst_1
      //   215: anewarray 145	com/taobao/newxp/Promoter
      //   218: dup
      //   219: iconst_0
      //   220: aload_0
      //   221: getfield 46	com/taobao/newxp/imagecache/utils/d$b:k	Lcom/taobao/newxp/Promoter;
      //   224: aastore
      //   225: invokevirtual 151	com/taobao/newxp/controller/ExchangeDataService:reportNoMatchImage	([Lcom/taobao/newxp/Promoter;)V
      //   228: invokestatic 156	com/taobao/newxp/imagecache/utils/e:d	()Z
      //   231: ifeq +127 -> 358
      //   234: new 83	android/graphics/drawable/BitmapDrawable
      //   237: dup
      //   238: aload_0
      //   239: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   242: getfield 159	com/taobao/newxp/imagecache/utils/d:d	Landroid/content/res/Resources;
      //   245: aload_1
      //   246: invokespecial 162	android/graphics/drawable/BitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
      //   249: astore_1
      //   250: aload_1
      //   251: astore_3
      //   252: aload_0
      //   253: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   256: invokestatic 116	com/taobao/newxp/imagecache/utils/d:b	(Lcom/taobao/newxp/imagecache/utils/d;)Lcom/taobao/newxp/imagecache/utils/ImageCache;
      //   259: ifnull +18 -> 277
      //   262: aload_0
      //   263: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   266: invokestatic 116	com/taobao/newxp/imagecache/utils/d:b	(Lcom/taobao/newxp/imagecache/utils/d;)Lcom/taobao/newxp/imagecache/utils/ImageCache;
      //   269: aload 5
      //   271: aload_1
      //   272: invokevirtual 165	com/taobao/newxp/imagecache/utils/ImageCache:a	(Ljava/lang/String;Landroid/graphics/drawable/BitmapDrawable;)V
      //   275: aload_1
      //   276: astore_3
      //   277: aload_3
      //   278: areturn
      //   279: astore_1
      //   280: aload_2
      //   281: monitorexit
      //   282: aload_1
      //   283: athrow
      //   284: astore_2
      //   285: ldc 167
      //   287: new 169	java/lang/StringBuilder
      //   290: dup
      //   291: invokespecial 170	java/lang/StringBuilder:<init>	()V
      //   294: ldc 172
      //   296: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   299: aload_2
      //   300: invokevirtual 180	java/lang/Exception:toString	()Ljava/lang/String;
      //   303: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   306: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   309: invokestatic 186	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   312: pop
      //   313: aconst_null
      //   314: astore_2
      //   315: goto -199 -> 116
      //   318: astore 4
      //   320: ldc 167
      //   322: new 169	java/lang/StringBuilder
      //   325: dup
      //   326: invokespecial 170	java/lang/StringBuilder:<init>	()V
      //   329: ldc 172
      //   331: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   334: aload 4
      //   336: invokevirtual 180	java/lang/Exception:toString	()Ljava/lang/String;
      //   339: invokevirtual 176	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   342: invokevirtual 181	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   345: invokestatic 186	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
      //   348: pop
      //   349: goto -190 -> 159
      //   352: astore_1
      //   353: aload_2
      //   354: astore_1
      //   355: goto -174 -> 181
      //   358: new 188	com/taobao/newxp/view/widget/RecyclingBitmapDrawable
      //   361: dup
      //   362: aload_0
      //   363: getfield 28	com/taobao/newxp/imagecache/utils/d$b:e	Lcom/taobao/newxp/imagecache/utils/d;
      //   366: getfield 159	com/taobao/newxp/imagecache/utils/d:d	Landroid/content/res/Resources;
      //   369: aload_1
      //   370: invokespecial 189	com/taobao/newxp/view/widget/RecyclingBitmapDrawable:<init>	(Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
      //   373: astore_1
      //   374: goto -124 -> 250
      //   377: aload_2
      //   378: astore_1
      //   379: goto -198 -> 181
      //
      // Exception table:
      //   from	to	target	type
      //   49	59	62	java/lang/InterruptedException
      //   28	44	279	finally
      //   49	59	279	finally
      //   67	69	279	finally
      //   280	282	279	finally
      //   103	116	284	java/lang/Exception
      //   144	156	318	java/lang/Exception
      //   163	181	352	java/lang/Exception
    }
  }

  protected class c extends com.taobao.newxp.common.b.a<Object, Void, Void>
  {
    protected c()
    {
    }

    protected Void e(Object[] paramArrayOfObject)
    {
      try
      {
        switch (((Integer)paramArrayOfObject[0]).intValue())
        {
        case 0:
          d.this.b();
        case 1:
        case 2:
        case 3:
        }
      }
      catch (Exception paramArrayOfObject)
      {
        Log.e("ImageWorker", "", paramArrayOfObject);
      }
      d.this.a();
      break label93;
      d.this.c();
      break label93;
      d.this.d();
      label93: return null;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.imagecache.utils.d
 * JD-Core Version:    0.6.2
 */