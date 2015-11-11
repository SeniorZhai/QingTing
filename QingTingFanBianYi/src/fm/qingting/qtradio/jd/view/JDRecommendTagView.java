package fm.qingting.qtradio.jd.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.jd.data.JDApi;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.utils.ScreenConfiguration;

@SuppressLint({"ClickableViewAccessibility"})
public class JDRecommendTagView extends QtView
  implements ViewElement.OnElementClickListener, View.OnTouchListener
{
  public static final int DISCOVERY_MORE_COLLECTION = -100;
  private final ViewLayout deleteLayout = this.itemLayout.createChildLT(132, 42, 520, 14, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout displayLayout = this.itemLayout.createChildLT(34, 34, 660, 16, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 56, 720, 56, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.itemLayout.createChildLT(5, 32, 18, 22, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mDelete;
  private ImageViewElement mDisplayDelete;
  private ImageViewElement mLabel;
  private OnDeleteListener mListener;
  private ImageViewElement mTag;
  private TextViewElement mTitle;
  private final ViewLayout tagLayout = this.itemLayout.createChildLT(52, 24, 230, 28, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(200, 40, 38, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public JDRecommendTagView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLabel = new ImageViewElement(paramContext);
    this.mLabel.setImageRes(2130837723);
    addElement(this.mLabel);
    this.mTitle = new TextViewElement(paramContext);
    this.mTitle.setColor(SkinManager.getTextColorNormal());
    this.mTitle.setMaxLineLimit(1);
    this.mTitle.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mTitle.setText("京东品牌街");
    addElement(this.mTitle);
    this.mTitle.expandHotPot(ScreenConfiguration.getCustomExtraBound());
    this.mTag = new ImageViewElement(paramContext);
    this.mTag.setImageRes(2130837834);
    addElement(this.mTag);
    this.mDelete = new ImageViewElement(paramContext);
    this.mDelete.setImageRes(2130837832);
    this.mDelete.setVisible(4);
    addElement(this.mDelete);
    this.mDelete.setOnElementClickListener(this);
    this.mDisplayDelete = new ImageViewElement(paramContext);
    this.mDisplayDelete.setImageRes(2130837830);
    addElement(this.mDisplayDelete, paramInt);
    this.mDisplayDelete.setOnElementClickListener(this);
    setOnTouchListener(this);
  }

  public void hideTag()
  {
    this.mTag.setVisible(4);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mDisplayDelete)
      if (this.mDelete.getVisiblity() == 4)
      {
        this.mDelete.setVisible(0);
        JDApi.sendEventMessage("JDADCLOSE");
      }
    while ((paramViewElement != this.mDelete) || (this.mListener == null))
      while (true)
      {
        return;
        this.mDelete.setVisible(4);
      }
    SharedCfg.getInstance().setJdAdDeleteTime(System.currentTimeMillis());
    this.mListener.onDelete();
    JDApi.sendEventMessage("JDADNoIntersting");
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.deleteLayout.scaleToBounds(this.itemLayout);
    this.displayLayout.scaleToBounds(this.itemLayout);
    this.labelLayout.scaleToBounds(this.itemLayout);
    this.tagLayout.scaleToBounds(this.itemLayout);
    this.mDisplayDelete.measure(this.displayLayout);
    this.mLabel.measure(this.labelLayout.leftMargin, this.labelLayout.topMargin, this.labelLayout.getRight(), this.labelLayout.getBottom());
    this.mTitle.measure(this.titleLayout);
    this.mTitle.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mDelete.measure(this.deleteLayout);
    this.mTag.measure(this.tagLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    int k = (int)paramMotionEvent.getX();
    int m = (int)paramMotionEvent.getY();
    if (this.mDelete.getBound().contains(k, m));
    for (int i = 1; ; i = -1)
    {
      int j = i;
      if (i == -1)
      {
        j = i;
        if (this.mDisplayDelete.getBound().contains(k, m))
          j = 2;
      }
      switch (paramMotionEvent.getAction())
      {
      case 2:
      default:
      case 0:
      case 1:
      case 3:
      }
      while (true)
      {
        return false;
        if (j == 1)
        {
          this.mDisplayDelete.setImageRes(2130837831);
        }
        else if (j == 2)
        {
          this.mDelete.setImageRes(2130837833);
          continue;
          if (j == 1)
            this.mDisplayDelete.setImageRes(2130837830);
          else if (j == 2)
            this.mDelete.setImageRes(2130837832);
        }
      }
    }
  }

  public void setListenr(OnDeleteListener paramOnDeleteListener)
  {
    this.mListener = paramOnDeleteListener;
  }

  public static abstract interface OnDeleteListener
  {
    public abstract void onDelete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.jd.view.JDRecommendTagView
 * JD-Core Version:    0.6.2
 */