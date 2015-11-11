package fm.qingting.qtradio.view.personalcenter.faq;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class LargeButtonView extends ViewGroupViewImpl
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(630, 90, 45, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private Button mButton;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 150, 720, 150, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private String text = "我要提问";

  public LargeButtonView(Context paramContext, String paramString)
  {
    super(paramContext);
    this.text = paramString;
    this.mButton = ((Button)LayoutInflater.from(paramContext).inflate(2130903052, null));
    this.mButton.setText(this.text);
    addView(this.mButton);
    this.mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LargeButtonView.this.dispatchActionEvent("onclick", null);
      }
    });
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.buttonLayout.layoutView(this.mButton);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.measureView(this.mButton);
    this.mButton.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setText"))
      this.mButton.setText((String)paramObject);
    while ((!paramString.equalsIgnoreCase("setState")) || (!((Boolean)paramObject).booleanValue()))
      return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.faq.LargeButtonView
 * JD-Core Version:    0.6.2
 */