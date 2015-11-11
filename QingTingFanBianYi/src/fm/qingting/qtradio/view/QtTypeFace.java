package fm.qingting.qtradio.view;

import android.content.Context;
import android.graphics.Typeface;

public class QtTypeFace
{
  private static Typeface tf = null;

  public static Typeface getTypeFace(Context paramContext)
  {
    if (tf == null)
      tf = Typeface.createFromAsset(paramContext.getAssets(), "fonts/RobotoRegular.ttf");
    return tf;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.QtTypeFace
 * JD-Core Version:    0.6.2
 */