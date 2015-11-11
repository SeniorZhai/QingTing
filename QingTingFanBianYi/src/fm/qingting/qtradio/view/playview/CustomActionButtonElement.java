package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class CustomActionButtonElement extends ViewElement
{
  private final ViewLayout iconLayout = this.itemLayout.createChildLT(50, 50, 50, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(150, 100, 150, 100, 0, 0, ViewLayout.FILL);
  private ButtonViewElement mIcon;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(150, 40, 0, 56, ViewLayout.SCALE_FLAG_SLTCW);

  public CustomActionButtonElement(Context paramContext)
  {
    super(paramContext);
    this.mIcon = new ButtonViewElement(paramContext);
    this.mIcon.setBelonging(this);
    this.mIcon.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
      }
    });
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(1);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setColor(SkinManager.getNewPlaySubColor());
    this.mNameElement.setBelonging(this);
  }

  public boolean handleTouchEvent(MotionEvent paramMotionEvent)
  {
    this.mIcon.handleTouchEvent(paramMotionEvent);
    return super.handleTouchEvent(paramMotionEvent);
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    int i = getLeftMargin();
    int j = getTopMargin();
    this.mIcon.setTranslationX(i);
    this.mIcon.setTranslationY(j);
    this.mNameElement.setTranslationX(i);
    this.mNameElement.setTranslationY(j);
    this.mIcon.draw(paramCanvas);
    this.mNameElement.draw(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.itemLayout.scaleToBounds(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.iconLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mIcon.measure(this.iconLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
  }

  public void setAction(String paramString)
  {
    this.mNameElement.setText(paramString);
    invalidateElement();
  }

  public void setAction(String paramString, int paramInt1, int paramInt2)
  {
    this.mIcon.setBackground(paramInt1, paramInt2);
    this.mNameElement.setText(paramString);
    invalidateElement();
  }

  public void setNameColor(int paramInt)
  {
    this.mNameElement.setColor(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.CustomActionButtonElement
 * JD-Core Version:    0.6.2
 */