package com.intowow.sdk.j;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.intowow.sdk.b.e;
import com.intowow.sdk.i.a;
import com.intowow.sdk.i.a.a;

public class n
{
  public static AlertDialog a(Activity paramActivity, String paramString1, String paramString2, String paramString3, String paramString4, boolean paramBoolean, j.a parama)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setCancelable(paramBoolean);
    if (paramString1 != null)
      paramActivity.setTitle(paramString1);
    paramActivity.setMessage(paramString2);
    if (paramString4 != null)
      paramActivity.setNegativeButton(paramString4, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          if (n.this != null)
            n.this.b(paramAnonymousDialogInterface);
        }
      });
    if (paramString3 != null)
      paramActivity.setPositiveButton(paramString3, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          paramAnonymousDialogInterface.dismiss();
          if (n.this != null)
            n.this.a(paramAnonymousDialogInterface);
        }
      });
    if (parama != null)
      paramActivity.setOnCancelListener(parama.a());
    return paramActivity.create();
  }

  public static View.OnTouchListener a(Drawable paramDrawable1, final Drawable paramDrawable2)
  {
    return new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (paramAnonymousMotionEvent.getAction() == 0)
          ((ImageButton)paramAnonymousView).setBackgroundDrawable(n.this);
        while (true)
        {
          return false;
          if ((paramAnonymousMotionEvent.getAction() == 1) || (paramAnonymousMotionEvent.getAction() == 3))
            ((ImageButton)paramAnonymousView).setBackgroundDrawable(paramDrawable2);
        }
      }
    };
  }

  public static void a(final Activity paramActivity, final String paramString1, ImageView paramImageView, final String paramString2, final boolean paramBoolean)
  {
    if ((paramActivity == null) || (paramString1 == null) || (paramImageView == null) || (paramString2 == null))
      return;
    paramImageView.setTag(paramString2);
    e.a(paramActivity).j().a(new a.a()
    {
      public void a()
      {
        n.this.setBackgroundColor(0);
      }

      public void a(int paramAnonymousInt, String paramAnonymousString)
      {
      }

      public void a(final Bitmap paramAnonymousBitmap)
      {
        if (paramActivity != null)
        {
          final BitmapDrawable localBitmapDrawable = new BitmapDrawable(paramAnonymousBitmap);
          paramActivity.runOnUiThread(new Runnable()
          {
            public void run()
            {
              if ((this.b == null) || (!this.b.getTag().equals(this.c)) || (paramAnonymousBitmap == null) || (paramAnonymousBitmap.isRecycled()));
              do
              {
                return;
                this.b.setBackgroundDrawable(localBitmapDrawable);
              }
              while (!this.f);
              AlphaAnimation localAlphaAnimation = new AlphaAnimation(0.0F, 1.0F);
              localAlphaAnimation.setDuration(500L);
              this.b.setAnimation(localAlphaAnimation);
              localAlphaAnimation.start();
            }
          });
        }
      }

      public String b()
      {
        return paramString2;
      }

      public String c()
      {
        return paramString1;
      }
    });
  }

  public static void a(ViewGroup paramViewGroup, View[] paramArrayOfView)
  {
    int j = paramArrayOfView.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      View localView = paramArrayOfView[i];
      if (localView != null)
        paramViewGroup.addView(localView);
      i += 1;
    }
  }

  public static void a(TextView paramTextView, int paramInt1, String paramString, int paramInt2, int paramInt3, RelativeLayout.LayoutParams paramLayoutParams, boolean paramBoolean1, boolean paramBoolean2, int paramInt4)
  {
    if (paramInt1 > 0)
      paramTextView.setId(paramInt1);
    paramTextView.setSingleLine(paramBoolean1);
    paramTextView.setTextColor(paramInt3);
    if (paramInt2 > 0)
      paramTextView.setTextSize(0, paramInt2);
    if (paramLayoutParams != null)
      paramTextView.setLayoutParams(paramLayoutParams);
    if (paramBoolean2)
      paramTextView.setEllipsize(TextUtils.TruncateAt.END);
    if (paramInt4 > 0)
      paramTextView.setMaxLines(paramInt4);
    paramTextView.setText(paramString);
  }

  public static void a(View[] paramArrayOfView)
  {
    int j = paramArrayOfView.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      View localView = paramArrayOfView[i];
      if ((localView != null) && (localView.getVisibility() != 0))
        localView.setVisibility(0);
      i += 1;
    }
  }

  public static void b(View[] paramArrayOfView)
  {
    int j = paramArrayOfView.length;
    int i = 0;
    while (true)
    {
      if (i >= j)
        return;
      View localView = paramArrayOfView[i];
      if ((localView != null) && (localView.getVisibility() != 8))
        localView.setVisibility(8);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.intowow.sdk.j.n
 * JD-Core Version:    0.6.2
 */