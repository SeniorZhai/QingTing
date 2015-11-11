package com.tencent.connect.avatar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.BaseApi;
import com.tencent.connect.common.BaseApi.TempRequestListener;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.UiError;
import com.tencent.utils.HttpUtils;
import com.tencent.utils.Util;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.json.JSONException;
import org.json.JSONObject;

public class ImageActivity extends Activity
{
  RelativeLayout a;
  private QQToken b;
  private String c;
  private Handler d;
  private c e;
  private Button f;
  private Button g;
  private b h;
  private TextView i;
  private ProgressBar j;
  private int k = 0;
  private boolean l = false;
  private long m = 0L;
  private int n = 0;
  private int o = 640;
  private int p = 640;
  private Rect q = new Rect();
  private String r;
  private Bitmap s;
  private View.OnClickListener t = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ImageActivity.d(ImageActivity.this).setVisibility(0);
      ImageActivity.e(ImageActivity.this).setEnabled(false);
      ImageActivity.e(ImageActivity.this).setTextColor(Color.rgb(21, 21, 21));
      ImageActivity.f(ImageActivity.this).setEnabled(false);
      ImageActivity.f(ImageActivity.this).setTextColor(Color.rgb(36, 94, 134));
      new Thread(new Runnable()
      {
        public void run()
        {
          ImageActivity.g(ImageActivity.this);
        }
      }).start();
      if (ImageActivity.h(ImageActivity.this))
        ImageActivity.this.a("10657", 0L);
      do
      {
        return;
        long l1 = System.currentTimeMillis();
        long l2 = ImageActivity.i(ImageActivity.this);
        ImageActivity.this.a("10655", l1 - l2);
      }
      while (!ImageActivity.c(ImageActivity.this).b);
      ImageActivity.this.a("10654", 0L);
    }
  };
  private View.OnClickListener u = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      long l1 = System.currentTimeMillis();
      long l2 = ImageActivity.i(ImageActivity.this);
      ImageActivity.this.a("10656", l1 - l2);
      ImageActivity.this.setResult(0);
      ImageActivity.j(ImageActivity.this);
    }
  };
  private IUiListener v = new IUiListener()
  {
    public void onCancel()
    {
    }

    public void onComplete(Object paramAnonymousObject)
    {
      ImageActivity.e(ImageActivity.this).setEnabled(true);
      ImageActivity.e(ImageActivity.this).setTextColor(-1);
      ImageActivity.f(ImageActivity.this).setEnabled(true);
      ImageActivity.f(ImageActivity.this).setTextColor(-1);
      ImageActivity.d(ImageActivity.this).setVisibility(8);
      paramAnonymousObject = (JSONObject)paramAnonymousObject;
      try
      {
        i = paramAnonymousObject.getInt("ret");
        if (i == 0)
        {
          ImageActivity.b(ImageActivity.this, "设置成功", 0);
          ImageActivity.this.a("10658", 0L);
          ImageActivity localImageActivity = ImageActivity.this;
          if ((ImageActivity.k(ImageActivity.this) != null) && (!"".equals(ImageActivity.k(ImageActivity.this))))
          {
            Intent localIntent = new Intent();
            localIntent.setClassName(localImageActivity, ImageActivity.k(ImageActivity.this));
            if (localImageActivity.getPackageManager().resolveActivity(localIntent, 0) != null)
              localImageActivity.startActivity(localIntent);
          }
          ImageActivity.a(ImageActivity.this, 0, paramAnonymousObject.toString(), null, null);
          ImageActivity.j(ImageActivity.this);
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          localJSONException.printStackTrace();
          int i = -1;
        }
        ImageActivity.b(ImageActivity.this, "设置出错了，请重新登录再尝试下呢：）", 1);
      }
    }

    public void onError(UiError paramAnonymousUiError)
    {
      ImageActivity.e(ImageActivity.this).setEnabled(true);
      ImageActivity.e(ImageActivity.this).setTextColor(-1);
      ImageActivity.f(ImageActivity.this).setEnabled(true);
      ImageActivity.f(ImageActivity.this).setTextColor(-1);
      ImageActivity.f(ImageActivity.this).setText("重试");
      ImageActivity.d(ImageActivity.this).setVisibility(8);
      ImageActivity.a(ImageActivity.this, true);
      ImageActivity.b(ImageActivity.this, paramAnonymousUiError.errorMessage, 1);
      ImageActivity.this.a("10660", 0L);
    }
  };
  private IUiListener w = new IUiListener()
  {
    private void a(int paramAnonymousInt)
    {
      if (ImageActivity.m(ImageActivity.this) < 2)
        ImageActivity.n(ImageActivity.this);
    }

    public void onCancel()
    {
    }

    public void onComplete(final Object paramAnonymousObject)
    {
      paramAnonymousObject = (JSONObject)paramAnonymousObject;
      int i = -1;
      try
      {
        int j = paramAnonymousObject.getInt("ret");
        if (j == 0)
        {
          i = j;
          paramAnonymousObject = paramAnonymousObject.getString("nickname");
          i = j;
          ImageActivity.l(ImageActivity.this).post(new Runnable()
          {
            public void run()
            {
              ImageActivity.b(ImageActivity.this, paramAnonymousObject);
            }
          });
          i = j;
          ImageActivity.this.a("10659", 0L);
        }
        for (i = j; ; i = j)
        {
          if (i != 0)
            a(i);
          return;
          i = j;
          ImageActivity.this.a("10661", 0L);
        }
      }
      catch (JSONException paramAnonymousObject)
      {
        while (true)
          paramAnonymousObject.printStackTrace();
      }
    }

    public void onError(UiError paramAnonymousUiError)
    {
      a(0);
    }
  };

  private Bitmap a(String paramString)
    throws IOException
  {
    int i1 = 1;
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    paramString = Uri.parse(paramString);
    InputStream localInputStream = getContentResolver().openInputStream(paramString);
    if (localInputStream == null)
      return null;
    BitmapFactory.decodeStream(localInputStream, null, localOptions);
    localInputStream.close();
    int i3 = localOptions.outWidth;
    int i2 = localOptions.outHeight;
    while (true)
    {
      if (i3 * i2 <= 4194304)
      {
        localOptions.inJustDecodeBounds = false;
        localOptions.inSampleSize = i1;
        return BitmapFactory.decodeStream(getContentResolver().openInputStream(paramString), null, localOptions);
      }
      i3 /= 2;
      i2 /= 2;
      i1 *= 2;
    }
  }

  private View a()
  {
    Object localObject3 = new ViewGroup.LayoutParams(-1, -1);
    Object localObject2 = new ViewGroup.LayoutParams(-1, -1);
    Object localObject1 = new ViewGroup.LayoutParams(-2, -2);
    this.a = new RelativeLayout(this);
    this.a.setLayoutParams((ViewGroup.LayoutParams)localObject3);
    this.a.setBackgroundColor(-16777216);
    localObject3 = new RelativeLayout(this);
    ((RelativeLayout)localObject3).setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.a.addView((View)localObject3);
    this.e = new c(this);
    this.e.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    this.e.setScaleType(ImageView.ScaleType.MATRIX);
    ((RelativeLayout)localObject3).addView(this.e);
    this.h = new b(this);
    localObject2 = new RelativeLayout.LayoutParams((ViewGroup.LayoutParams)localObject2);
    ((RelativeLayout.LayoutParams)localObject2).addRule(14, -1);
    ((RelativeLayout.LayoutParams)localObject2).addRule(15, -1);
    this.h.setLayoutParams((ViewGroup.LayoutParams)localObject2);
    ((RelativeLayout)localObject3).addView(this.h);
    localObject2 = new LinearLayout(this);
    localObject3 = new RelativeLayout.LayoutParams(-2, a.a(this, 80.0F));
    ((RelativeLayout.LayoutParams)localObject3).addRule(14, -1);
    ((LinearLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    ((LinearLayout)localObject2).setOrientation(0);
    ((LinearLayout)localObject2).setGravity(17);
    this.a.addView((View)localObject2);
    localObject3 = new ImageView(this);
    ((ImageView)localObject3).setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 24.0F), a.a(this, 24.0F)));
    ((ImageView)localObject3).setImageDrawable(b("com.tencent.plus.logo.png"));
    ((LinearLayout)localObject2).addView((View)localObject3);
    this.i = new TextView(this);
    localObject3 = new LinearLayout.LayoutParams((ViewGroup.LayoutParams)localObject1);
    ((LinearLayout.LayoutParams)localObject3).leftMargin = a.a(this, 7.0F);
    this.i.setLayoutParams((ViewGroup.LayoutParams)localObject3);
    this.i.setEllipsize(TextUtils.TruncateAt.END);
    this.i.setSingleLine();
    this.i.setTextColor(-1);
    this.i.setTextSize(24.0F);
    this.i.setVisibility(8);
    ((LinearLayout)localObject2).addView(this.i);
    localObject2 = new RelativeLayout(this);
    localObject3 = new RelativeLayout.LayoutParams(-1, a.a(this, 60.0F));
    ((RelativeLayout.LayoutParams)localObject3).addRule(12, -1);
    ((RelativeLayout.LayoutParams)localObject3).addRule(9, -1);
    ((RelativeLayout)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject3);
    ((RelativeLayout)localObject2).setBackgroundDrawable(b("com.tencent.plus.bar.png"));
    int i1 = a.a(this, 10.0F);
    ((RelativeLayout)localObject2).setPadding(i1, i1, i1, 0);
    this.a.addView((View)localObject2);
    localObject3 = new a(this);
    i1 = a.a(this, 14.0F);
    int i2 = a.a(this, 7.0F);
    this.g = new Button(this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(a.a(this, 78.0F), a.a(this, 45.0F));
    this.g.setLayoutParams(localLayoutParams);
    this.g.setText("取消");
    this.g.setTextColor(-1);
    this.g.setTextSize(18.0F);
    this.g.setPadding(i1, i2, i1, i2);
    ((a)localObject3).b(this.g);
    ((RelativeLayout)localObject2).addView(this.g);
    this.f = new Button(this);
    localLayoutParams = new RelativeLayout.LayoutParams(a.a(this, 78.0F), a.a(this, 45.0F));
    localLayoutParams.addRule(11, -1);
    this.f.setLayoutParams(localLayoutParams);
    this.f.setTextColor(-1);
    this.f.setTextSize(18.0F);
    this.f.setPadding(i1, i2, i1, i2);
    this.f.setText("选取");
    ((a)localObject3).a(this.f);
    ((RelativeLayout)localObject2).addView(this.f);
    localObject3 = new TextView(this);
    localLayoutParams = new RelativeLayout.LayoutParams((ViewGroup.LayoutParams)localObject1);
    localLayoutParams.addRule(13, -1);
    ((TextView)localObject3).setLayoutParams(localLayoutParams);
    ((TextView)localObject3).setText("移动和缩放");
    ((TextView)localObject3).setPadding(0, a.a(this, 3.0F), 0, 0);
    ((TextView)localObject3).setTextSize(18.0F);
    ((TextView)localObject3).setTextColor(-1);
    ((RelativeLayout)localObject2).addView((View)localObject3);
    this.j = new ProgressBar(this);
    localObject1 = new RelativeLayout.LayoutParams((ViewGroup.LayoutParams)localObject1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(14, -1);
    ((RelativeLayout.LayoutParams)localObject1).addRule(15, -1);
    this.j.setLayoutParams((ViewGroup.LayoutParams)localObject1);
    this.j.setVisibility(8);
    this.a.addView(this.j);
    return this.a;
  }

  private void a(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    Intent localIntent = new Intent();
    localIntent.putExtra("key_error_code", paramInt);
    localIntent.putExtra("key_error_msg", paramString2);
    localIntent.putExtra("key_error_detail", paramString3);
    localIntent.putExtra("key_response", paramString1);
    setResult(-1, localIntent);
  }

  private void a(Bitmap paramBitmap)
  {
    new QQAvatarImp(this, this.b).setAvator(paramBitmap, this.v);
  }

  private void a(final String paramString, final int paramInt)
  {
    this.d.post(new Runnable()
    {
      public void run()
      {
        ImageActivity.a(ImageActivity.this, paramString, paramInt);
      }
    });
  }

  // ERROR //
  private Drawable b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual 384	com/tencent/connect/avatar/ImageActivity:getAssets	()Landroid/content/res/AssetManager;
    //   4: astore_2
    //   5: aload_2
    //   6: aload_1
    //   7: invokevirtual 390	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   10: astore_2
    //   11: aload_2
    //   12: aload_1
    //   13: invokestatic 396	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   16: astore_1
    //   17: aload_2
    //   18: invokevirtual 136	java/io/InputStream:close	()V
    //   21: aload_1
    //   22: areturn
    //   23: astore_2
    //   24: aconst_null
    //   25: astore_1
    //   26: aload_2
    //   27: invokevirtual 399	java/io/IOException:printStackTrace	()V
    //   30: aload_1
    //   31: areturn
    //   32: astore_2
    //   33: goto -7 -> 26
    //
    // Exception table:
    //   from	to	target	type
    //   5	17	23	java/io/IOException
    //   17	21	32	java/io/IOException
  }

  private void b()
  {
    try
    {
      this.s = a(this.r);
      if (this.s == null)
        throw new IOException("cannot read picture: '" + this.r + "'!");
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      b("图片读取失败，请检查该图片是否有效", 1);
      a(-5, null, "图片读取失败，请检查该图片是否有效", localIOException.getMessage());
      d();
    }
    while (true)
    {
      this.f.setOnClickListener(this.t);
      this.g.setOnClickListener(this.u);
      this.a.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener()
      {
        public void onGlobalLayout()
        {
          ImageActivity.this.a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
          ImageActivity.a(ImageActivity.this, ImageActivity.a(ImageActivity.this).a());
          ImageActivity.c(ImageActivity.this).a(ImageActivity.b(ImageActivity.this));
        }
      });
      return;
      this.e.setImageBitmap(this.s);
    }
  }

  private void b(String paramString, int paramInt)
  {
    paramString = Toast.makeText(this, paramString, 1);
    LinearLayout localLinearLayout = (LinearLayout)paramString.getView();
    ((TextView)localLinearLayout.getChildAt(0)).setPadding(8, 0, 0, 0);
    ImageView localImageView = new ImageView(this);
    localImageView.setLayoutParams(new LinearLayout.LayoutParams(a.a(this, 16.0F), a.a(this, 16.0F)));
    if (paramInt == 0)
      localImageView.setImageDrawable(b("com.tencent.plus.ic_success.png"));
    while (true)
    {
      localLinearLayout.addView(localImageView, 0);
      localLinearLayout.setOrientation(0);
      localLinearLayout.setGravity(17);
      paramString.setView(localLinearLayout);
      paramString.setGravity(17, 0, 0);
      paramString.show();
      return;
      localImageView.setImageDrawable(b("com.tencent.plus.ic_error.png"));
    }
  }

  private void c()
  {
    float f4 = this.q.width();
    Object localObject1 = this.e.getImageMatrix();
    Object localObject2 = new float[9];
    ((Matrix)localObject1).getValues((float[])localObject2);
    float f1 = localObject2[2];
    float f2 = localObject2[5];
    float f3 = localObject2[0];
    f4 = this.o / f4;
    int i1 = (int)((this.q.left - f1) / f3);
    int i2 = (int)((this.q.top - f2) / f3);
    localObject2 = new Matrix();
    ((Matrix)localObject2).set((Matrix)localObject1);
    ((Matrix)localObject2).postScale(f4, f4);
    int i4 = (int)(650.0F / f3);
    int i3 = Math.min(this.s.getWidth() - i1, i4);
    i4 = Math.min(this.s.getHeight() - i2, i4);
    localObject1 = Bitmap.createBitmap(this.s, i1, i2, i3, i4, (Matrix)localObject2, true);
    localObject2 = Bitmap.createBitmap((Bitmap)localObject1, 0, 0, this.o, this.p);
    ((Bitmap)localObject1).recycle();
    a((Bitmap)localObject2);
  }

  private void c(String paramString)
  {
    paramString = d(paramString);
    if (!"".equals(paramString))
    {
      this.i.setText(paramString);
      this.i.setVisibility(0);
    }
  }

  private String d(String paramString)
  {
    return paramString.replaceAll("&gt;", ">").replaceAll("&lt;", "<").replaceAll("&quot;", "\"").replaceAll("&#39;", "'").replaceAll("&amp;", "&");
  }

  private void d()
  {
    finish();
    if (this.n != 0)
      overridePendingTransition(0, this.n);
  }

  private void e()
  {
    this.k += 1;
    new UserInfo(this, this.b).getUserInfo(this.w);
  }

  public void a(String paramString, long paramLong)
  {
    Util.reportBernoulli(this, paramString, paramLong, this.b.getAppId());
  }

  public void onBackPressed()
  {
    setResult(0);
    d();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setRequestedOrientation(1);
    setContentView(a());
    this.d = new Handler();
    paramBundle = getIntent().getBundleExtra("key_params");
    this.r = paramBundle.getString("picture");
    this.c = paramBundle.getString("return_activity");
    String str1 = paramBundle.getString("appid");
    String str2 = paramBundle.getString("access_token");
    long l1 = paramBundle.getLong("expires_in");
    String str3 = paramBundle.getString("openid");
    this.n = paramBundle.getInt("exitAnim");
    this.b = new QQToken(str1);
    this.b.setAccessToken(str2, (l1 - System.currentTimeMillis()) / 1000L + "");
    this.b.setOpenId(str3);
    b();
    e();
    this.m = System.currentTimeMillis();
    a("10653", 0L);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.e.setImageBitmap(null);
    if ((this.s != null) && (!this.s.isRecycled()))
      this.s.recycle();
  }

  private class QQAvatarImp extends BaseApi
  {
    public QQAvatarImp(Context paramQQToken, QQToken arg3)
    {
      super(localQQToken);
    }

    public void setAvator(Bitmap paramBitmap, IUiListener paramIUiListener)
    {
      Bundle localBundle = composeCGIParams();
      Object localObject = new ByteArrayOutputStream();
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 40, (OutputStream)localObject);
      localObject = ((ByteArrayOutputStream)localObject).toByteArray();
      paramBitmap.recycle();
      paramBitmap = new BaseApi.TempRequestListener(this, paramIUiListener);
      localBundle.putByteArray("picture", (byte[])localObject);
      HttpUtils.requestAsync(this.mToken, this.mContext, "user/set_user_face", localBundle, "POST", paramBitmap);
    }
  }

  class a extends View
  {
    public a(Context arg2)
    {
      super();
    }

    public void a(Button paramButton)
    {
      StateListDrawable localStateListDrawable = new StateListDrawable();
      Drawable localDrawable1 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.blue_normal.png");
      Drawable localDrawable2 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.blue_down.png");
      Drawable localDrawable3 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.blue_disable.png");
      localStateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, localDrawable2);
      localStateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.ENABLED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.EMPTY_STATE_SET, localDrawable3);
      paramButton.setBackgroundDrawable(localStateListDrawable);
    }

    public void b(Button paramButton)
    {
      StateListDrawable localStateListDrawable = new StateListDrawable();
      Drawable localDrawable1 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.gray_normal.png");
      Drawable localDrawable2 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.gray_down.png");
      Drawable localDrawable3 = ImageActivity.a(ImageActivity.this, "com.tencent.plus.gray_disable.png");
      localStateListDrawable.addState(View.PRESSED_ENABLED_STATE_SET, localDrawable2);
      localStateListDrawable.addState(View.ENABLED_FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.ENABLED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.FOCUSED_STATE_SET, localDrawable1);
      localStateListDrawable.addState(View.EMPTY_STATE_SET, localDrawable3);
      paramButton.setBackgroundDrawable(localStateListDrawable);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.connect.avatar.ImageActivity
 * JD-Core Version:    0.6.2
 */