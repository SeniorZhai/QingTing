package com.taobao.newxp.view.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

public class KeyboardListenRelativeLayout extends RelativeLayout
{
  public static final byte KEYBOARD_STATE_HIDE = -2;
  public static final byte KEYBOARD_STATE_INIT = -1;
  public static final byte KEYBOARD_STATE_SHOW = -3;
  private static final String a = KeyboardListenRelativeLayout.class.getSimpleName();
  private boolean b = false;
  private boolean c = false;
  private int d;
  private IOnKeyboardStateChangedListener e;

  public KeyboardListenRelativeLayout(Context paramContext)
  {
    super(paramContext);
  }

  public KeyboardListenRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public KeyboardListenRelativeLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (!this.b)
    {
      this.b = true;
      this.d = paramInt4;
      if (this.e != null)
        this.e.onKeyboardStateChanged(-1);
      paramInt1 = Math.abs(this.d - paramInt4);
      if (!(getContext() instanceof Activity))
        break label227;
      Activity localActivity = (Activity)getContext();
      Rect localRect = new Rect();
      localActivity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
      if (paramInt1 == localRect.top)
        this.d = paramInt4;
    }
    while (true)
    {
      if ((this.b) && (this.d > paramInt4))
      {
        this.c = true;
        if (this.e != null)
          this.e.onKeyboardStateChanged(-3);
      }
      if ((this.b) && (this.c) && (this.d == paramInt4))
      {
        this.c = false;
        if (this.e != null)
          this.e.onKeyboardStateChanged(-2);
      }
      return;
      if (this.d < paramInt4);
      for (paramInt1 = paramInt4; ; paramInt1 = this.d)
      {
        this.d = paramInt1;
        break;
      }
      label227: if (paramInt1 < 100)
        this.d = paramInt4;
    }
  }

  public void setOnKeyboardStateChangedListener(IOnKeyboardStateChangedListener paramIOnKeyboardStateChangedListener)
  {
    this.e = paramIOnKeyboardStateChangedListener;
  }

  public static abstract interface IOnKeyboardStateChangedListener
  {
    public abstract void onKeyboardStateChanged(int paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.KeyboardListenRelativeLayout
 * JD-Core Version:    0.6.2
 */