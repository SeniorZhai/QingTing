package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;

class ResortItemView extends QtView
{
  private final ViewLayout buttonLayout;
  private final ViewLayout itemLayout;
  private ButtonViewElement mButtonElement;

  public ResortItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    int i;
    if (ScreenConfiguration.isUltraWideScreen())
    {
      i = 82;
      this.itemLayout = ViewLayout.createViewLayoutWithBoundsLT(180, i, 180, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      paramContext = this.itemLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label139;
      i = 7;
    }
    while (true)
    {
      this.buttonLayout = paramContext.createChildLT(154, 68, 13, i, ViewLayout.SCALE_FLAG_SLTCW);
      this.mButtonElement = new ButtonViewElement(getContext());
      this.mButtonElement.setBackground(2130837536, 2130837536);
      this.mButtonElement.setTextColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorRecommend());
      addElement(this.mButtonElement, paramInt);
      ignoreSelfTouchEvent();
      return;
      if (ScreenConfiguration.isWideScreen())
      {
        i = 92;
        break;
      }
      i = 102;
      break;
      label139: if (ScreenConfiguration.isWideScreen())
        i = 12;
      else
        i = 17;
    }
  }

  public boolean isSelected()
  {
    return this.mButtonElement.isSelected();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.buttonLayout.scaleToBounds(this.itemLayout);
    this.mButtonElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mButtonElement.measure(this.buttonLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (String)paramObject;
      this.mButtonElement.setText(paramString);
    }
    boolean bool1;
    boolean bool2;
    do
    {
      do
      {
        return;
        if (paramString.equalsIgnoreCase("setFixed"))
        {
          this.mButtonElement.setBackground(0, 0);
          this.mButtonElement.setTextColor(SkinManager.getTextColorThirdLevel());
          return;
        }
      }
      while (!paramString.equalsIgnoreCase("setSelected"));
      bool1 = this.mButtonElement.isSelected();
      bool2 = ((Boolean)paramObject).booleanValue();
    }
    while (!(bool1 ^ bool2));
    this.mButtonElement.setSelected(bool2);
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ResortItemView
 * JD-Core Version:    0.6.2
 */