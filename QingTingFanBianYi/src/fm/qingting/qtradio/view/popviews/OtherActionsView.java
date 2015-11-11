package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

class OtherActionsView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout cancelLayout = this.standardLayout.createChildLT(720, 100, 0, 320, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 320, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mCancelElement;
  private LineElement mLineElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 420, 720, 420, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public OtherActionsView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getNewPopBgColor());
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setColor(SkinManager.getNewPopTextColor());
    this.mTitleElement.setText("分享内容到", false);
    addElement(this.mTitleElement);
    this.mCancelElement = new ButtonViewElement(paramContext);
    this.mCancelElement.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    this.mCancelElement.setText("取消");
    this.mCancelElement.setTextColor(SkinManager.getNewPopTextColor());
    addElement(this.mCancelElement);
    this.mCancelElement.setOnElementClickListener(this);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (this.mCancelElement == paramViewElement)
      dispatchActionEvent("cancelPop", null);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.cancelLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mCancelElement.measure(this.cancelLayout);
    this.mCancelElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mLineElement.measure(this.lineLayout);
    super.onMeasure(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.OtherActionsView
 * JD-Core Version:    0.6.2
 */