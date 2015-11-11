package fm.qingting.qtradio.view.viewmodel;

import android.content.res.Resources;
import android.view.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class NaviUtil
{
  private static int sStatusBarHeight = -1;

  public static int getStatusBarHeight(Resources paramResources)
  {
    if (sStatusBarHeight > 0)
      return sStatusBarHeight;
    int i = 0;
    int j = paramResources.getIdentifier("status_bar_height", "dimen", "android");
    if (j > 0)
      i = paramResources.getDimensionPixelSize(j);
    sStatusBarHeight = i;
    return i;
  }

  public static void setTransparentStatusBar(Window paramWindow)
  {
    try
    {
      Method localMethod = paramWindow.getClass().getMethod("romUI_setStatusBarTransparent", new Class[] { Boolean.TYPE });
      if (localMethod != null)
        localMethod.invoke(paramWindow, new Object[] { Boolean.valueOf(true) });
      return;
    }
    catch (NoSuchMethodException paramWindow)
    {
      paramWindow.printStackTrace();
      return;
    }
    catch (IllegalAccessException paramWindow)
    {
      paramWindow.printStackTrace();
      return;
    }
    catch (IllegalArgumentException paramWindow)
    {
      paramWindow.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramWindow)
    {
      paramWindow.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.viewmodel.NaviUtil
 * JD-Core Version:    0.6.2
 */