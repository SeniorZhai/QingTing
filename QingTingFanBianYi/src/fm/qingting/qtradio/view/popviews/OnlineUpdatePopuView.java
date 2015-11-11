package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;

public class OnlineUpdatePopuView extends ViewGroupViewImpl
  implements View.OnClickListener
{
  private final RectF mBgRectF = new RectF();
  private TextView mCancelTv;
  private RelativeLayout mContentView = (RelativeLayout)inflate(getContext(), 2130903067, null);
  private TextView mDownloadTv;
  private TextView mMessageTv;
  private RelativeLayout mQuickDonwloadTv;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public OnlineUpdatePopuView(Context paramContext)
  {
    super(paramContext);
    addView(this.mContentView);
    this.mMessageTv = ((TextView)this.mContentView.findViewById(2131230877));
    this.mCancelTv = ((TextView)this.mContentView.findViewById(2131230856));
    this.mDownloadTv = ((TextView)this.mContentView.findViewById(2131230879));
    this.mQuickDonwloadTv = ((RelativeLayout)this.mContentView.findViewById(2131230880));
    if (!OnlineUpdateHelper.getInstance().needQuickDownload())
      this.mQuickDonwloadTv.setVisibility(8);
    this.mCancelTv.setOnClickListener(this);
    this.mDownloadTv.setOnClickListener(this);
    this.mQuickDonwloadTv.setOnClickListener(this);
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
    if (paramView == this.mCancelTv)
    {
      OnlineUpdateHelper.getInstance().sendEventMessage("updateWait");
      dispatchActionEvent("cancelPop", null);
    }
    do
    {
      return;
      if (paramView == this.mDownloadTv)
      {
        OnlineUpdateHelper.getInstance().download();
        dispatchActionEvent("cancelPop", null);
        OnlineUpdateHelper.getInstance().sendEventMessage("updateDownload");
        return;
      }
      if (paramView == this.mQuickDonwloadTv)
      {
        OnlineUpdateHelper.getInstance().quickDownload();
        dispatchActionEvent("cancelPop", null);
        OnlineUpdateHelper.getInstance().sendEventMessage("updateLightDownload");
        return;
      }
    }
    while (paramView != this.mContentView);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = this.mContentView.getMeasuredWidth();
    paramInt3 = this.mContentView.getMeasuredHeight();
    paramInt1 = (this.standardLayout.width - paramInt2) / 2;
    paramInt2 = (paramInt2 + this.standardLayout.width) / 2;
    paramInt3 = this.standardLayout.height - paramInt3;
    paramInt4 = this.standardLayout.height;
    this.mBgRectF.set(paramInt1, paramInt3, paramInt2, paramInt4);
    this.mContentView.layout(paramInt1, paramInt3, paramInt2, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mContentView.measure(paramInt1, -2);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramObject != null)
    {
      paramString = (String)paramObject;
      if (!paramString.equalsIgnoreCase(""))
        this.mMessageTv.setText(paramString);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.OnlineUpdatePopuView
 * JD-Core Version:    0.6.2
 */