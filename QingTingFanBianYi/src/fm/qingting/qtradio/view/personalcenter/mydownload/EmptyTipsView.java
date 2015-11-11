package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class EmptyTipsView extends QtView
{
  private final ViewLayout iconLayout = this.standardLayout.createChildLT(200, 200, 260, 230, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private TextViewElement mContentElement;
  private ImageViewElement mIconElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(720, 45, 0, 26, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 45, 0, 50, ViewLayout.SCALE_FLAG_SLTCW);

  public EmptyTipsView(Context paramContext)
  {
    this(paramContext, 0);
  }

  public EmptyTipsView(Context paramContext, int paramInt)
  {
    super(paramContext);
    int i = hashCode();
    this.mIconElement = new ImageViewElement(paramContext);
    this.mIconElement.setImageRes(EmptyTipUtil.getRes(paramInt));
    addElement(this.mIconElement, i);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTitleElement);
    this.mTitleElement.setText(EmptyTipUtil.getTitle(paramInt));
    this.mContentElement = new TextViewElement(paramContext);
    this.mContentElement.setMaxLineLimit(2);
    this.mContentElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mContentElement.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mContentElement);
    this.mContentElement.setText(EmptyTipUtil.getContent(paramInt));
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public int getContentTop()
  {
    return this.mContentElement.getTopMargin();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.iconLayout.scaleToBounds(this.standardLayout);
    this.textLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.mIconElement.measure(this.iconLayout);
    this.mTitleElement.measure(this.titleLayout.leftMargin, this.iconLayout.getBottom() + this.titleLayout.topMargin, this.titleLayout.getRight(), this.iconLayout.getBottom() + this.titleLayout.getBottom());
    this.mContentElement.measure(this.textLayout.leftMargin, this.iconLayout.getBottom() + this.titleLayout.getBottom() + this.textLayout.topMargin, this.textLayout.getRight(), this.iconLayout.getBottom() + this.titleLayout.getBottom() + this.textLayout.getBottom());
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mContentElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setContent(String paramString)
  {
    this.mContentElement.setText(paramString);
  }

  public void setTitle(String paramString)
  {
    this.mTitleElement.setText(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView
 * JD-Core Version:    0.6.2
 */