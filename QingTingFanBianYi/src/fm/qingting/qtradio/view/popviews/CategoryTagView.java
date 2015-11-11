package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.EducationManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.QTMSGManage;

public class CategoryTagView extends ViewGroupViewImpl
{
  public static final String CLICK = "ctv_click";
  private static final String STATISTIC_TAG = "v6_resort_startclick";
  private final String ALL = "所有分类";
  private final String SORT = "拖动排序";
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(102, 48, 465, 14, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(606, 2, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Button mButton;
  private boolean mInManage = false;
  private TagLineView mLineView;
  private TextView mTextView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(606, 76, 606, 76, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(300, 50, 50, 13, ViewLayout.SCALE_FLAG_SLTCW);

  public CategoryTagView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLineView = new TagLineView(paramContext);
    addView(this.mLineView);
    this.mTextView = new TextView(paramContext);
    this.mTextView.setTextColor(SkinManager.getTextColorSecondLevel());
    this.mTextView.setText("所有分类");
    this.mTextView.setGravity(19);
    addView(this.mTextView);
    this.mButton = new Button(paramContext);
    this.mButton.setBackgroundResource(2130837553);
    this.mButton.setTextColor(SkinManager.getTextColorHighlight());
    this.mButton.setText("排序");
    addView(this.mButton);
    this.mButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CategoryTagView.this.dispatchActionEvent("ctv_click", null);
        paramAnonymousView = CategoryTagView.this;
        boolean bool;
        Object localObject;
        if (!CategoryTagView.this.mInManage)
        {
          bool = true;
          CategoryTagView.access$102(paramAnonymousView, bool);
          localObject = CategoryTagView.this.mButton;
          if (!CategoryTagView.this.mInManage)
            break label124;
          paramAnonymousView = "完成";
          label54: ((Button)localObject).setText(paramAnonymousView);
          localObject = CategoryTagView.this.mTextView;
          if (!CategoryTagView.this.mInManage)
            break label130;
        }
        label130: for (paramAnonymousView = "拖动排序"; ; paramAnonymousView = "所有分类")
        {
          ((TextView)localObject).setText(paramAnonymousView);
          if (CategoryTagView.this.mInManage)
          {
            if (EducationManager.getInstance().isShown())
              EducationManager.getInstance().cancelTip();
            QTMSGManage.getInstance().sendStatistcsMessage("v6_resort_startclick");
          }
          return;
          bool = false;
          break;
          label124: paramAnonymousView = "排序";
          break label54;
        }
      }
    });
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.textLayout.layoutView(this.mTextView);
    this.buttonLayout.layoutView(this.mButton);
    this.mLineView.layout(0, this.standardLayout.height - this.lineLayout.height, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.textLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.textLayout.measureView(this.mTextView);
    this.buttonLayout.measureView(this.mButton);
    this.lineLayout.measureView(this.mLineView);
    this.mTextView.setTextSize(0, SkinManager.getInstance().getMiddleTextSize());
    this.mButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mButton.setPadding(0, 0, 0, 0);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("reset"))
    {
      this.mButton.setText("排序");
      this.mTextView.setText("所有分类");
      this.mInManage = false;
    }
    do
    {
      do
        return;
      while (!paramString.equalsIgnoreCase("entermanage"));
      this.mButton.setText("完成");
      this.mTextView.setText("拖动排序");
      this.mInManage = true;
    }
    while (!EducationManager.getInstance().isShown());
    EducationManager.getInstance().cancelTip();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CategoryTagView
 * JD-Core Version:    0.6.2
 */