package fm.qingting.qtradio.view.virtualcategoryview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class ChoosenLabelsView extends ViewGroupViewImpl
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(150, 64, 558, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout labelLayout = this.standardLayout.createChildLT(550, 76, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 2, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Button mButton;
  private LabelsScrollView mLabelsScrollView;
  private TagLineView mLineView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 76, 720, 76, 0, 0, ViewLayout.FILL);

  public ChoosenLabelsView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLabelsScrollView = new LabelsScrollView(paramContext);
    addView(this.mLabelsScrollView);
    this.mButton = ((Button)LayoutInflater.from(paramContext).inflate(2130903051, null));
    this.mButton.setText("清除");
    addView(this.mButton);
    this.mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ChoosenLabelsView.this.clear();
      }
    });
    this.mLineView = new TagLineView(paramContext);
    addView(this.mLineView);
  }

  private void clear()
  {
    dispatchActionEvent("clear", null);
    this.mLabelsScrollView.update("clear", null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.buttonLayout.layoutView(this.mButton);
    this.labelLayout.layoutView(this.mLabelsScrollView);
    this.mLineView.layout(0, this.standardLayout.height - this.lineLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.labelLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.labelLayout.measureView(this.mLabelsScrollView);
    this.buttonLayout.measureView(this.mButton);
    this.lineLayout.measureView(this.mLineView);
    this.mButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mButton.setPadding(0, 0, 0, 0);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mLabelsScrollView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualcategoryview.ChoosenLabelsView
 * JD-Core Version:    0.6.2
 */