package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

public class RecentSearchTagView extends QtView
{
  private final ViewLayout clearLayout = this.standardLayout.createChildLT(120, 40, 580, 14, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout iconLayout = this.standardLayout.createChildLT(32, 32, 548, 17, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mClearElement;
  private ImageViewElement mIconElement;
  private LineElement mLineElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 68, 720, 68, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 40, 40, 14, ViewLayout.SCALE_FLAG_SLTCW);

  public RecentSearchTagView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getTagBackgroundColor());
    int i = hashCode();
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setText("最近搜索", false);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setColor(-11184811);
    addElement(this.mTitleElement);
    ViewElement.OnElementClickListener local1 = new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        RecentSearchTagView.this.dispatchActionEvent("clearRecent", null);
      }
    };
    this.mIconElement = new ImageViewElement(paramContext);
    this.mIconElement.setImageRes(2130837935);
    addElement(this.mIconElement, i);
    this.mIconElement.setOnElementClickListener(local1);
    this.mClearElement = new ButtonViewElement(paramContext);
    this.mClearElement.setText("清除全部");
    this.mClearElement.setTextColor(SkinManager.getTextColorHighlight(), -11184811);
    addElement(this.mClearElement);
    this.mClearElement.setOnElementClickListener(local1);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.iconLayout.scaleToBounds(this.standardLayout);
    this.clearLayout.scaleToBounds(this.standardLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mIconElement.measure(this.iconLayout);
    this.mClearElement.measure(this.clearLayout);
    this.mLineElement.measure(0, this.standardLayout.height - this.lineLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mClearElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.RecentSearchTagView
 * JD-Core Version:    0.6.2
 */