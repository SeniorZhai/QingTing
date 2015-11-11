package fm.qingting.qtradio.view.popviews.categoryfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class ManageView extends ViewGroupViewImpl
{
  private final ViewLayout bottomLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.FILL);
  private final ViewLayout leftButtonLayout = this.bottomLayout.createChildLT(300, 80, 40, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private Button mLeftButton;
  private Button mRightButton;
  private final ViewLayout rightButtonLayout = this.bottomLayout.createChildLT(300, 80, 380, 20, ViewLayout.SCALE_FLAG_SLTCW);

  public ManageView(Context paramContext)
  {
    super(paramContext);
    paramContext = LayoutInflater.from(paramContext);
    this.mLeftButton = ((Button)paramContext.inflate(2130903054, null));
    this.mLeftButton.setText("取消");
    addView(this.mLeftButton);
    this.mRightButton = ((Button)paramContext.inflate(2130903060, null));
    this.mRightButton.setText("确定");
    addView(this.mRightButton);
    paramContext = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAnonymousView == ManageView.this.mLeftButton)
          ManageView.this.dispatchActionEvent("clickleft", null);
        while (paramAnonymousView != ManageView.this.mRightButton)
          return;
        ManageView.this.dispatchActionEvent("clickright", null);
      }
    };
    this.mLeftButton.setOnClickListener(paramContext);
    this.mRightButton.setOnClickListener(paramContext);
    setOnClickListener(paramContext);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLeftButton.layout(this.leftButtonLayout.leftMargin, this.leftButtonLayout.topMargin, this.leftButtonLayout.getRight(), this.leftButtonLayout.getBottom());
    this.mRightButton.layout(this.rightButtonLayout.leftMargin, this.rightButtonLayout.topMargin, this.rightButtonLayout.getRight(), this.rightButtonLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.bottomLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.leftButtonLayout.scaleToBounds(this.bottomLayout);
    this.rightButtonLayout.scaleToBounds(this.bottomLayout);
    this.leftButtonLayout.measureView(this.mLeftButton);
    this.rightButtonLayout.measureView(this.mRightButton);
    this.mLeftButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mLeftButton.setPadding(0, 0, 0, 0);
    this.mRightButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mRightButton.setPadding(0, 0, 0, 0);
    setMeasuredDimension(this.bottomLayout.width, this.bottomLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.categoryfilter.ManageView
 * JD-Core Version:    0.6.2
 */