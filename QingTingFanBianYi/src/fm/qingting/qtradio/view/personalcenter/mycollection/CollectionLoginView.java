package fm.qingting.qtradio.view.personalcenter.mycollection;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.utils.QTMSGManage;

public class CollectionLoginView extends RelativeLayout
{
  private Button mLoginBtn;
  private RelativeLayout mLoginView = (RelativeLayout)inflate(getContext(), 2130903043, null);

  public CollectionLoginView(Context paramContext)
  {
    super(paramContext);
    addView(this.mLoginView);
    this.mLoginBtn = ((Button)this.mLoginView.findViewById(2131230740));
    this.mLoginBtn.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        EventDispacthManager.getInstance().dispatchAction("showlogin", null);
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "login_collection");
      }
    });
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mLoginView.layout(paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    this.mLoginView.measure(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mycollection.CollectionLoginView
 * JD-Core Version:    0.6.2
 */