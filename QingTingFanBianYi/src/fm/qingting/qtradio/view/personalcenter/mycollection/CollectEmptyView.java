package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;
import fm.qingting.utils.QTMSGManage;

class CollectEmptyView extends ViewGroupViewImpl
{
  private EmptyTipsView mEmptyView;
  private Button mLoginBtn;
  private RelativeLayout mLoginView;
  private TextView mReminderView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public CollectEmptyView(Context paramContext)
  {
    super(paramContext);
    this.mEmptyView = new EmptyTipsView(paramContext, 0);
    addView(this.mEmptyView);
    this.mLoginView = ((RelativeLayout)inflate(getContext(), 2130903043, null));
    addView(this.mLoginView);
    this.mLoginBtn = ((Button)this.mLoginView.findViewById(2131230740));
    this.mReminderView = ((TextView)this.mLoginView.findViewById(2131230739));
    this.mLoginBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EventDispacthManager.getInstance().dispatchAction("showlogin", null);
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "login_collection");
      }
    });
    ((ViewGroup.MarginLayoutParams)this.mReminderView.getLayoutParams()).setMargins(0, 0, 0, 0);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mEmptyView.layout(paramInt1, paramInt2, paramInt3, paramInt4);
    paramInt2 = this.mLoginView.getMeasuredHeight();
    this.mLoginView.layout(paramInt1, this.mEmptyView.getContentTop(), paramInt3, paramInt2 + this.mEmptyView.getContentTop());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(i, paramInt2);
    this.mLoginView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec((int)(this.standardLayout.height / 3.0F), -2147483648));
    this.mEmptyView.measure(paramInt1, paramInt1);
    setMeasuredDimension(i, paramInt2);
  }

  public void setLoginVisibility(int paramInt)
  {
    this.mLoginView.setVisibility(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.CollectEmptyView
 * JD-Core Version:    0.6.2
 */