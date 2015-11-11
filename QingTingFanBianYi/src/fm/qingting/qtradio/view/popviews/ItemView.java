package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.playview.LineElement;

class ItemView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 100, 720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 99, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private LineElement mLineElement;
  private TextViewElement mTextViewElement;
  private final ViewLayout textLayout = this.itemLayout.createChildLT(600, 50, 40, 25, ViewLayout.SCALE_FLAG_SLTCW);

  public ItemView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ItemView.this.dispatchActionEvent("click", ItemView.this.mTextViewElement.getText());
      }
    });
    this.mTextViewElement = new TextViewElement(paramContext);
    this.mTextViewElement.setMaxLineLimit(1);
    this.mTextViewElement.setColor(-11908534);
    addElement(this.mTextViewElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
    this.mLineElement.setColor(SkinManager.getDividerColor());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mTextViewElement.measure(this.textLayout);
    this.mLineElement.measure(this.lineLayout);
    this.mTextViewElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
      this.mTextViewElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ItemView
 * JD-Core Version:    0.6.2
 */