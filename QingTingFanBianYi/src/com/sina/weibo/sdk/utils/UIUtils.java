package com.sina.weibo.sdk.utils;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.widget.Toast;

public class UIUtils
{
  public static void showAlert(Context paramContext, int paramInt1, int paramInt2)
  {
    if (paramContext != null)
      showAlert(paramContext, paramContext.getString(paramInt1), paramContext.getString(paramInt2));
  }

  public static void showAlert(Context paramContext, String paramString1, String paramString2)
  {
    if (paramContext != null)
      new AlertDialog.Builder(paramContext).setTitle(paramString1).setMessage(paramString2).create().show();
  }

  public static void showToast(Context paramContext, int paramInt1, int paramInt2)
  {
    if (paramContext != null)
      Toast.makeText(paramContext, paramInt1, paramInt2).show();
  }

  public static void showToast(Context paramContext, CharSequence paramCharSequence, int paramInt)
  {
    if (paramContext != null)
      Toast.makeText(paramContext, paramCharSequence, paramInt).show();
  }

  public static void showToastInCenter(Context paramContext, int paramInt1, int paramInt2)
  {
    if (paramContext != null)
    {
      paramContext = Toast.makeText(paramContext, paramInt1, paramInt2);
      paramContext.setGravity(17, 0, 0);
      paramContext.show();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.utils.UIUtils
 * JD-Core Version:    0.6.2
 */