package com.taobao.munion.view.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.taobao.munion.Munion;
import com.taobao.munion.base.Log;
import com.taobao.munion.base.ioc.x;
import com.taobao.munion.controller.a.b;
import java.util.Map;
import org.json.JSONObject;

public abstract class MunionEntityView extends RelativeLayout
  implements a.b
{
  private String a;
  protected volatile com.taobao.munion.models.c b;
  protected volatile x c;
  protected boolean d = true;

  public MunionEntityView(Context paramContext)
  {
    super(paramContext);
    b();
  }

  public MunionEntityView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    b();
  }

  public MunionEntityView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    b();
  }

  private void b()
  {
    Map localMap = Munion.getCa(getContext());
    if ((localMap != null) && (localMap.size() > 0))
      com.taobao.munion.base.caches.c.a().a(localMap);
  }

  private void c()
  {
    com.taobao.munion.base.caches.b.a().a(getContext(), null);
    Munion.initAsync(getContext(), this);
  }

  protected abstract void a();

  protected final void a(String paramString, Class<? extends com.taobao.munion.models.c> paramClass)
  {
    if (!TextUtils.isEmpty(paramString))
      try
      {
        if ((this.b == null) || (!paramString.equals(this.b.a())))
        {
          this.b = ((com.taobao.munion.models.c)paramClass.newInstance());
          this.b.a(paramString);
        }
        if (this.d)
          a();
        return;
      }
      catch (Exception paramString)
      {
        throw new RuntimeException(paramString);
      }
    throw new IllegalArgumentException("The Munion id is null.");
  }

  public String getTd()
  {
    return this.a;
  }

  public void onError(com.taobao.munion.exception.b paramb)
  {
    Log.e(paramb, "", new Object[0]);
  }

  public void onInit(Context paramContext, x paramx)
  {
    this.c = paramx;
    if (this.d)
      a();
    Log.i("EntityView initized.", new Object[0]);
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (this.c == null)
      c();
  }

  public void setTd(String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        JSONObject localJSONObject = new JSONObject();
        localJSONObject.put("td", new JSONObject(paramString));
        this.a = localJSONObject.toString();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.munion.view.base.MunionEntityView
 * JD-Core Version:    0.6.2
 */