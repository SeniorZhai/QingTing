package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class MyDownloadTopView extends ViewGroupViewImpl
{
  private final ViewLayout leftButtonLayout = this.standardLayout.createChildLT(300, 80, 40, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mAllSelected = false;
  private Button mConfirmButton;
  private Button mSelectButton;
  private final ViewLayout rightButtonLayout = this.standardLayout.createChildLT(300, 80, 380, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 110, 720, 110, 0, 0, ViewLayout.FILL);

  public MyDownloadTopView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAnonymousView == MyDownloadTopView.this.mSelectButton)
        {
          paramAnonymousView = MyDownloadTopView.this;
          if (!MyDownloadTopView.this.mAllSelected)
          {
            bool = true;
            MyDownloadTopView.access$102(paramAnonymousView, bool);
            localButton = MyDownloadTopView.this.mSelectButton;
            if (!MyDownloadTopView.this.mAllSelected)
              break label85;
            paramAnonymousView = "全部开始";
            localButton.setText(paramAnonymousView);
            MyDownloadTopView.this.dispatchActionEvent("startAll", Boolean.valueOf(MyDownloadTopView.this.mAllSelected));
          }
        }
        label85: 
        while (paramAnonymousView != MyDownloadTopView.this.mConfirmButton)
          while (true)
          {
            Button localButton;
            return;
            boolean bool = false;
            continue;
            paramAnonymousView = "全部暂停";
          }
        MyDownloadTopView.this.dispatchActionEvent("deleteAll", null);
      }
    };
    setOnClickListener(local1);
    paramContext = LayoutInflater.from(paramContext);
    this.mSelectButton = ((Button)paramContext.inflate(2130903054, null));
    this.mSelectButton.setText("全部暂停");
    addView(this.mSelectButton);
    this.mSelectButton.setOnClickListener(local1);
    this.mConfirmButton = ((Button)paramContext.inflate(2130903054, null));
    this.mConfirmButton.setText("全部删除");
    addView(this.mConfirmButton);
    this.mConfirmButton.setOnClickListener(local1);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.leftButtonLayout.layoutView(this.mSelectButton);
    this.rightButtonLayout.layoutView(this.mConfirmButton);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.leftButtonLayout.scaleToBounds(this.standardLayout);
    this.rightButtonLayout.scaleToBounds(this.standardLayout);
    this.leftButtonLayout.measureView(this.mSelectButton);
    this.rightButtonLayout.measureView(this.mConfirmButton);
    this.mSelectButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mConfirmButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadTopView
 * JD-Core Version:    0.6.2
 */