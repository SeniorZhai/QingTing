package fm.qingting.qtradio.view.moreContentView;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

public class CustomSectionView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 12, 720, 12, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private LineElement mLineElement;
  private LineElement mSecondElement;

  public CustomSectionView(Context paramContext)
  {
    super(paramContext);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.mSecondElement = new LineElement(paramContext);
    this.mSecondElement.setOrientation(1);
    this.mSecondElement.setColor(SkinManager.getDividerColor());
    addElement(this.mSecondElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.lineLayout.topMargin, this.lineLayout.getRight(), this.lineLayout.getBottom());
    this.mSecondElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("nbl"))
    {
      boolean bool = ((Boolean)paramObject).booleanValue();
      paramString = this.mSecondElement;
      if (!bool)
        break label37;
    }
    label37: for (int i = 0; ; i = 4)
    {
      paramString.setVisible(i);
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.CustomSectionView
 * JD-Core Version:    0.6.2
 */