package com.taobao.newxp.view.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.taobao.munion.base.a;
import com.taobao.newxp.Promoter;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.Log;
import com.taobao.newxp.view.container.GridTemplateConfig;
import java.util.ArrayList;
import java.util.List;

public class GridPage extends RelativeLayout
{
  private static final String a = "GridPage";
  private final GridPageAdapter b;
  private final GridTemplateConfig c;
  private final Context d;
  private int e = 0;
  private final List<LinearLayout> f;

  public GridPage(Context paramContext, GridPageAdapter paramGridPageAdapter, GridTemplateConfig paramGridTemplateConfig)
  {
    super(paramContext);
    this.d = paramContext;
    this.b = paramGridPageAdapter;
    this.c = paramGridTemplateConfig;
    this.f = new ArrayList();
    a();
  }

  private void a()
  {
    int i1 = this.b.getCount();
    int i2 = this.c.numColumns;
    int i3 = (int)AlimmContext.getAliContext().getAppUtils().a(this.c.verticalSpacing);
    Log.c("GridPage", "GridPage init params numColums=" + i2 + "   verticalSpacing=" + i3);
    int k;
    int m;
    if (i1 % i2 == 0)
    {
      k = i1 / i2;
      m = 0;
    }
    int j;
    for (int i = 0; ; i = j)
    {
      if (m >= k)
        return;
      LinearLayout localLinearLayout = new LinearLayout(this.d);
      Object localObject = new RelativeLayout.LayoutParams(-1, -2);
      localLinearLayout.setId(m + 10);
      if (m > 0)
        ((RelativeLayout.LayoutParams)localObject).addRule(3, localLinearLayout.getId() - 1);
      if ((i3 > 0) && (m > 0))
        ((RelativeLayout.LayoutParams)localObject).topMargin = i3;
      localLinearLayout.setLayoutParams((ViewGroup.LayoutParams)localObject);
      localLinearLayout.setOrientation(0);
      int n = i;
      j = i;
      while (true)
        if (n < i + i2)
        {
          localObject = new RelativeLayout(this.d);
          LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -1);
          localLayoutParams.weight = 1.0F;
          ((RelativeLayout)localObject).setLayoutParams(localLayoutParams);
          if (j < i1)
            ((RelativeLayout)localObject).addView(this.b.getView(n));
          localLinearLayout.addView((View)localObject);
          n += 1;
          j += 1;
          continue;
          k = i1 / i2 + 1;
          break;
        }
      this.f.add(localLinearLayout);
      addView(localLinearLayout);
      this.e = i1;
      m += 1;
    }
  }

  public void notifyDataSetChanged(boolean paramBoolean)
  {
    if (!paramBoolean)
    {
      if (this.e != this.b.getCount())
      {
        removeAllViews();
        a();
        Log.c("GridPage", "data has changed..");
        return;
      }
      Log.c("GridPage", "data has no changed..");
      return;
    }
    removeAllViews();
    Log.c("GridPage", "pre cast change page.." + getChildCount());
    a();
    Log.c("GridPage", "cast change page.." + getChildCount());
  }

  public static abstract class GridPageAdapter
  {
    List<Promoter> c;
    GridPage.PageInfo d;

    public GridPageAdapter(List<Promoter> paramList, GridPage.PageInfo paramPageInfo)
    {
      this.c = paramList;
      this.d = paramPageInfo;
    }

    public abstract View buildView(int paramInt1, int paramInt2, Promoter paramPromoter);

    public int getCount()
    {
      return this.d.count;
    }

    public View getView(int paramInt)
    {
      int i = paramInt + this.d.sPos;
      return buildView(paramInt, i, (Promoter)this.c.get(i));
    }
  }

  public static class PageInfo
  {
    public int count;
    public int lastPos;
    public int page;
    public boolean pageChange = false;
    public int sPos;
    public boolean show = false;

    public PageInfo(int paramInt1, int paramInt2)
    {
      this.sPos = paramInt1;
      this.count = paramInt2;
      this.lastPos = (paramInt1 + paramInt2 - 1);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.view.widget.GridPage
 * JD-Core Version:    0.6.2
 */