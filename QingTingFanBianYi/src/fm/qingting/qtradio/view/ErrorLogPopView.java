package fm.qingting.qtradio.view;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;

public class ErrorLogPopView extends QtView
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(300, 60, 40, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout logLayout = this.standardLayout.createChildLT(700, 600, 10, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mCopyElement;
  private ButtonViewElement mHidElement;
  private TextViewElement mLogElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ErrorLogPopView(Context paramContext)
  {
    super(paramContext);
    this.mLogElement = new TextViewElement(paramContext);
    this.mLogElement.setMaxLineLimit(20);
    this.mLogElement.setColor(SkinManager.getBackgroundColor());
    this.mLogElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    addElement(this.mLogElement);
    this.mCopyElement = new ButtonViewElement(paramContext);
    this.mCopyElement.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getGeneralButtonColor());
    this.mCopyElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getTextColorNormal());
    this.mCopyElement.setText("复制");
    addElement(this.mCopyElement);
    this.mCopyElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        paramAnonymousViewElement = ErrorLogPopView.this.mLogElement.getText();
        if (paramAnonymousViewElement != null)
          ErrorLogPopView.this.copy(paramAnonymousViewElement);
      }
    });
    this.mHidElement = new ButtonViewElement(paramContext);
    this.mHidElement.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getGeneralButtonColor());
    this.mHidElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getTextColorNormal());
    this.mHidElement.setText("取消");
    addElement(this.mHidElement);
    this.mHidElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        ErrorLogPopView.this.dispatchActionEvent("cancelPop", null);
      }
    });
  }

  @TargetApi(11)
  private void copy(String paramString)
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ClipboardManager)getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, paramString));
      return;
    }
    ((ClipboardManager)getContext().getSystemService("clipboard")).setText(paramString);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.logLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.mLogElement.measure(this.logLayout);
    this.mCopyElement.measure(this.buttonLayout.leftMargin, this.standardLayout.height - this.buttonLayout.getBottom(), this.buttonLayout.getRight(), this.standardLayout.height - this.buttonLayout.topMargin);
    this.mHidElement.measure(this.buttonLayout.getRight() + this.buttonLayout.leftMargin, this.standardLayout.height - this.buttonLayout.getBottom(), this.buttonLayout.getRight() * 2, this.standardLayout.height - this.buttonLayout.topMargin);
    this.mCopyElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mHidElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
      this.mLogElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.ErrorLogPopView
 * JD-Core Version:    0.6.2
 */