package android.support.v4.app;

import android.app.ActionBar;
import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.lang.reflect.Method;

class ActionBarDrawerToggleHoneycomb
{
  private static final String TAG = "ActionBarDrawerToggleHoneycomb";
  private static final int[] THEME_ATTRS = { 16843531 };

  public static Drawable getThemeUpIndicator(Activity paramActivity)
  {
    paramActivity = paramActivity.obtainStyledAttributes(THEME_ATTRS);
    Drawable localDrawable = paramActivity.getDrawable(0);
    paramActivity.recycle();
    return localDrawable;
  }

  public static Object setActionBarDescription(Object paramObject, Activity paramActivity, int paramInt)
  {
    Object localObject = paramObject;
    if (paramObject == null)
      localObject = new SetIndicatorInfo(paramActivity);
    paramObject = (SetIndicatorInfo)localObject;
    if (paramObject.setHomeAsUpIndicator != null);
    try
    {
      paramActivity = paramActivity.getActionBar();
      paramObject.setHomeActionContentDescription.invoke(paramActivity, new Object[] { Integer.valueOf(paramInt) });
      return localObject;
    }
    catch (Exception paramObject)
    {
      Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set content description via JB-MR2 API", paramObject);
    }
    return localObject;
  }

  public static Object setActionBarUpIndicator(Object paramObject, Activity paramActivity, Drawable paramDrawable, int paramInt)
  {
    Object localObject = paramObject;
    if (paramObject == null)
      localObject = new SetIndicatorInfo(paramActivity);
    paramObject = (SetIndicatorInfo)localObject;
    if (paramObject.setHomeAsUpIndicator != null)
      try
      {
        paramActivity = paramActivity.getActionBar();
        paramObject.setHomeAsUpIndicator.invoke(paramActivity, new Object[] { paramDrawable });
        paramObject.setHomeActionContentDescription.invoke(paramActivity, new Object[] { Integer.valueOf(paramInt) });
        return localObject;
      }
      catch (Exception paramObject)
      {
        Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator via JB-MR2 API", paramObject);
        return localObject;
      }
    if (paramObject.upIndicatorView != null)
    {
      paramObject.upIndicatorView.setImageDrawable(paramDrawable);
      return localObject;
    }
    Log.w("ActionBarDrawerToggleHoneycomb", "Couldn't set home-as-up indicator");
    return localObject;
  }

  private static class SetIndicatorInfo
  {
    public Method setHomeActionContentDescription;
    public Method setHomeAsUpIndicator;
    public ImageView upIndicatorView;

    SetIndicatorInfo(Activity paramActivity)
    {
      while (true)
      {
        Object localObject;
        try
        {
          this.setHomeAsUpIndicator = ActionBar.class.getDeclaredMethod("setHomeAsUpIndicator", new Class[] { Drawable.class });
          this.setHomeActionContentDescription = ActionBar.class.getDeclaredMethod("setHomeActionContentDescription", new Class[] { Integer.TYPE });
          return;
        }
        catch (NoSuchMethodException localNoSuchMethodException)
        {
          paramActivity = paramActivity.findViewById(16908332);
          if (paramActivity == null)
            continue;
          localObject = (ViewGroup)paramActivity.getParent();
          if (((ViewGroup)localObject).getChildCount() != 2)
            continue;
          paramActivity = ((ViewGroup)localObject).getChildAt(0);
          localObject = ((ViewGroup)localObject).getChildAt(1);
          if (paramActivity.getId() != 16908332)
            break label113;
        }
        paramActivity = (Activity)localObject;
        label113: 
        while ((paramActivity instanceof ImageView))
        {
          this.upIndicatorView = ((ImageView)paramActivity);
          return;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.app.ActionBarDrawerToggleHoneycomb
 * JD-Core Version:    0.6.2
 */