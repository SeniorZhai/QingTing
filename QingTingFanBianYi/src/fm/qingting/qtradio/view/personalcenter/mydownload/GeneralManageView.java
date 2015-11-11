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

public class GeneralManageView extends ViewGroupViewImpl
{
  private final ViewLayout leftButtonLayout = this.standardLayout.createChildLT(300, 69, 40, 12, ViewLayout.SCALE_FLAG_SLTCW);
  private boolean mAllSelected = false;
  private Button mConfirmButton;
  private boolean mEnabled = false;
  private Button mSelectButton;
  private final ViewLayout rightButtonLayout = this.standardLayout.createChildLT(300, 69, 380, 12, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 93, 720, 93, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public GeneralManageView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAnonymousView == GeneralManageView.this.mSelectButton)
        {
          paramAnonymousView = GeneralManageView.this;
          if (!GeneralManageView.this.mAllSelected)
          {
            bool = true;
            GeneralManageView.access$102(paramAnonymousView, bool);
            localButton = GeneralManageView.this.mSelectButton;
            if (!GeneralManageView.this.mAllSelected)
              break label85;
            paramAnonymousView = "取消全选";
            localButton.setText(paramAnonymousView);
            GeneralManageView.this.dispatchActionEvent("selectAll", Boolean.valueOf(GeneralManageView.this.mAllSelected));
          }
        }
        label85: 
        while (paramAnonymousView != GeneralManageView.this.mConfirmButton)
          while (true)
          {
            Button localButton;
            return;
            boolean bool = false;
            continue;
            paramAnonymousView = "全选";
          }
        GeneralManageView.this.dispatchActionEvent("delete", null);
      }
    };
    setOnClickListener(local1);
    paramContext = LayoutInflater.from(paramContext);
    this.mSelectButton = ((Button)paramContext.inflate(2130903054, null));
    this.mSelectButton.setText("全选");
    addView(this.mSelectButton);
    this.mSelectButton.setOnClickListener(local1);
    this.mConfirmButton = ((Button)paramContext.inflate(2130903060, null));
    this.mConfirmButton.setText("删除");
    this.mConfirmButton.setEnabled(false);
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

  public void update(String paramString, Object paramObject)
  {
    boolean bool;
    if (paramString.equalsIgnoreCase("stateChanged"))
    {
      bool = ((Boolean)paramObject).booleanValue();
      if (this.mEnabled != bool);
    }
    else
    {
      return;
    }
    this.mEnabled = bool;
    this.mConfirmButton.setEnabled(this.mEnabled);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.GeneralManageView
 * JD-Core Version:    0.6.2
 */