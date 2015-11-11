package fm.qingting.qtradio.view.virtualcategoryview;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import java.util.ArrayList;
import java.util.List;

class LabelsView extends QtView
{
  private final ViewLayout buttonLayout = this.itemLayout.createChildLT(150, 64, 5, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(160, 76, 160, 76, 0, 0, ViewLayout.SLT | ViewLayout.LT | ViewLayout.CH);
  private List<ButtonViewElement> mChildren;
  private int mHash;

  public LabelsView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mHash = hashCode();
  }

  private void clear()
  {
    if (this.mChildren != null)
    {
      int i = this.mChildren.size() - 1;
      while (i >= 0)
      {
        removeElement((ViewElement)this.mChildren.get(i));
        i -= 1;
      }
      this.mChildren.clear();
    }
    dispatchActionEvent("clear", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    if ((this.mChildren == null) || (this.mChildren.size() == 0))
    {
      setMeasuredDimension(0, this.itemLayout.height);
      return;
    }
    this.buttonLayout.scaleToBounds(this.itemLayout);
    int j = this.mChildren.size();
    paramInt1 = 0;
    paramInt2 = i;
    while (paramInt1 < j)
    {
      paramInt2 = this.itemLayout.width;
      paramInt2 = this.buttonLayout.leftMargin + paramInt2 * paramInt1;
      ((ButtonViewElement)this.mChildren.get(paramInt1)).measure(paramInt2, this.buttonLayout.topMargin, this.buttonLayout.width + paramInt2, this.buttonLayout.getBottom());
      paramInt1 += 1;
    }
    setMeasuredDimension(paramInt2 + this.buttonLayout.getRight(), this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("resetFilter"))
    {
      paramString = (List)paramObject;
      if (this.mChildren != null)
      {
        i = this.mChildren.size() - 1;
        while (i >= 0)
        {
          removeElement((ViewElement)this.mChildren.get(i));
          i -= 1;
        }
        this.mChildren.clear();
      }
      if (paramString != null)
      {
        if (this.mChildren == null)
          this.mChildren = new ArrayList();
        i = 0;
        while (i < paramString.size())
        {
          paramObject = new ButtonViewElement(getContext());
          paramObject.setBackground(2130837536, 2130837536);
          paramObject.setTextColor(SkinManager.getTextColorNormal());
          addElement(paramObject, this.mHash);
          paramObject.setTextSize(SkinManager.getInstance().getSubTextSize());
          paramObject.setText(((Attribute)paramString.get(i)).name);
          this.mChildren.add(paramObject);
          i += 1;
        }
      }
      requestLayout();
    }
    while (!paramString.equalsIgnoreCase("clear"))
    {
      int i;
      return;
    }
    clear();
    requestLayout();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualcategoryview.LabelsView
 * JD-Core Version:    0.6.2
 */