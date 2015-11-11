package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;

public class LabelContainer extends ViewGroup
{
  public LabelContainer(Context paramContext)
  {
    super(paramContext);
  }

  public LabelContainer(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramView.layout(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int k = getChildCount();
    int m = getMeasuredWidth();
    paramInt2 = 0;
    paramInt4 = 0;
    paramInt1 = 0;
    while (paramInt2 < k)
    {
      View localView = getChildAt(paramInt2);
      int i1 = localView.getMeasuredWidth();
      int n = localView.getMeasuredHeight();
      int j = paramInt1 + i1;
      int i = paramInt1;
      paramInt3 = paramInt4;
      paramInt1 = j;
      if (j > m)
      {
        paramInt3 = paramInt4 + n;
        paramInt1 = 0 + i1;
        i = 0;
      }
      setChildFrame(localView, i, paramInt3, paramInt1, n + paramInt3);
      paramInt2 += 1;
      paramInt4 = paramInt3;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int m = getChildCount();
    int n = View.MeasureSpec.getSize(paramInt1);
    int i = 0;
    int j = 0;
    int k = 0;
    if (i < m)
    {
      View localView = getChildAt(i);
      paramInt1 = k;
      paramInt2 = j;
      int i1;
      if (localView != null)
      {
        localView.measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(0, 0));
        paramInt1 = localView.getMeasuredWidth();
        i1 = localView.getMeasuredHeight();
        k += paramInt1;
        if (k <= n)
          break label108;
        paramInt1 = 0 + paramInt1;
        paramInt2 = j + i1;
      }
      while (true)
      {
        i += 1;
        k = paramInt1;
        j = paramInt2;
        break;
        label108: paramInt1 = k;
        paramInt2 = j;
        if (j == 0)
        {
          paramInt2 = j + i1;
          paramInt1 = k;
        }
      }
    }
    setMeasuredDimension(n, j);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.LabelContainer
 * JD-Core Version:    0.6.2
 */