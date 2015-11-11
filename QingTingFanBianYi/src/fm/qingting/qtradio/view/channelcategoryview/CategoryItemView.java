package fm.qingting.qtradio.view.channelcategoryview;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.view.playview.LineElement;

public class CategoryItemView extends QtView
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 650, 38, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout channelLayout = this.itemLayout.createChildLT(600, 50, 30, 31, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 112, 720, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrow;
  private Attribute mAttribute;
  private ButtonViewElement mBgElement;
  private LineElement mLineElement;
  private TextViewElement mTitle;

  public CategoryItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBgElement = new ButtonViewElement(paramContext);
    this.mBgElement.setBackgroundColor(SkinManager.getBackgroundColor(), 0);
    this.mBgElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ControllerManager.getInstance().openTraditionalChannelsView(CategoryItemView.this.mAttribute);
      }
    });
    addElement(this.mBgElement);
    this.mTitle = new TextViewElement(paramContext);
    this.mTitle.setMaxLineLimit(1);
    this.mTitle.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTitle);
    this.mArrow = new ImageViewElement(paramContext);
    this.mArrow.setImageRes(2130837697);
    addElement(this.mArrow, paramInt);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.channelLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.mBgElement.measure(this.itemLayout);
    this.mTitle.measure(this.channelLayout);
    this.mArrow.measure(this.arrowLayout);
    this.mLineElement.measure(this.lineLayout);
    this.mTitle.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mAttribute = ((Attribute)paramObject);
      this.mTitle.setText(this.mAttribute.name);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.channelcategoryview.CategoryItemView
 * JD-Core Version:    0.6.2
 */