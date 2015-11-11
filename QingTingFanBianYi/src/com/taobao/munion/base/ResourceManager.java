package com.taobao.munion.base;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ResourceManager
{
  private static final String[] b = { "back.png", "back_click.png", "close.png", "close_click.png", "forward.png", "forward_click.png", "reflush.png", "reflush_click.png", "closebox.png", "closebox_click.png" };
  Map<String, Object> a = new HashMap();
  private h c = new h();
  private Context d;

  private Drawable b(String paramString)
  {
    try
    {
      paramString = Drawable.createFromStream(this.c.b(this.d, paramString), paramString);
      return paramString;
    }
    catch (IOException paramString)
    {
      Log.e(paramString, "", new Object[0]);
    }
    return null;
  }

  public Object a(String paramString)
  {
    Object localObject2 = this.a.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
      localObject1 = b(paramString);
    return localObject1;
  }

  public void init(Context paramContext)
  {
    this.d = paramContext;
    paramContext = b;
    int j = paramContext.length;
    int i = 0;
    while (i < j)
    {
      String str = paramContext[i];
      Drawable localDrawable = b(str);
      if (localDrawable != null)
        this.a.put(str, localDrawable);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.base.ResourceManager
 * JD-Core Version:    0.6.2
 */