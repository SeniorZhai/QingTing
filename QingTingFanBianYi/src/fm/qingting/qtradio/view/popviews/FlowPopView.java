package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.wo.WoApiRequest;

public class FlowPopView extends ViewGroupViewImpl
  implements View.OnClickListener
{
  private final RectF mBgRectF = new RectF();
  private ImageView mCancelIv;
  private RelativeLayout mContentView;
  private ImageView mOpenIv;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public FlowPopView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(2130706432);
    this.mContentView = ((RelativeLayout)inflate(getContext(), 2130903058, null));
    addView(this.mContentView);
    this.mCancelIv = ((ImageView)this.mContentView.findViewById(2131230856));
    this.mOpenIv = ((ImageView)this.mContentView.findViewById(2131230857));
    this.mCancelIv.setOnClickListener(this);
    this.mOpenIv.setOnClickListener(this);
    this.mContentView.setOnClickListener(this);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (!this.mBgRectF.contains(paramMotionEvent.getX(), paramMotionEvent.getY())))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.mCancelIv);
    while (true)
    {
      dispatchActionEvent("cancelPop", null);
      return;
      if (paramView == this.mOpenIv)
        if (WoApiRequest.hasInited())
        {
          WoApiRequest.sendEventMessage("unicomPopupOpen");
          ControllerManager.getInstance().openWoController();
        }
        else
        {
          WoApiRequest.sendEventMessage("unicomPopupFailed");
          Toast.makeText(getContext(), "网络连接有问题或者正在初始化..", 1).show();
        }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt3 = this.mContentView.getMeasuredWidth();
    paramInt2 = this.mContentView.getMeasuredHeight();
    paramInt1 = (this.standardLayout.width - paramInt3) / 2;
    paramInt3 = (paramInt3 + this.standardLayout.width) / 2;
    paramInt4 = (this.standardLayout.height - paramInt2) / 2;
    paramInt2 += paramInt4;
    this.mBgRectF.set(paramInt1, paramInt4, paramInt3, paramInt2);
    this.mContentView.layout(paramInt1, paramInt4, paramInt3, paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    paramInt1 = View.MeasureSpec.makeMeasureSpec((int)(View.MeasureSpec.getSize(paramInt1) * 0.8D), -2147483648);
    this.mContentView.measure(paramInt1, paramInt1);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.FlowPopView
 * JD-Core Version:    0.6.2
 */