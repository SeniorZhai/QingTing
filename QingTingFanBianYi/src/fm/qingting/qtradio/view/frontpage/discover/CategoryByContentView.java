package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

class CategoryByContentView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(240, 90, 720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(1, 60, 0, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private CollapseElement mCollapsElement;
  private boolean mIsCollapsed = true;
  private final Paint mLinePaint = new Paint();
  private List<CategoryNode> mNodes;
  private boolean mUseCollapse = false;

  public CategoryByContentView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
  }

  private void drawLines(Canvas paramCanvas)
  {
    int j = getChildCount();
    if (j == 0);
    while (true)
    {
      return;
      int i = 0;
      while (i < j)
      {
        int k;
        if (i % 3 == 0)
        {
          k = getChildAt(i).getTopMargin();
          paramCanvas.drawLine(0.0F, k, getMeasuredWidth(), k, this.mLinePaint);
        }
        if (i % 3 != 2)
        {
          ViewElement localViewElement = getChildAt(i);
          k = localViewElement.getRightMargin();
          int m = localViewElement.getTopMargin();
          paramCanvas.drawLine(k, this.lineLayout.topMargin + m, k, m + this.lineLayout.getBottom(), this.mLinePaint);
        }
        i += 1;
      }
    }
  }

  private void setData(List<CategoryNode> paramList)
  {
    if (paramList.size() > 6);
    for (boolean bool = true; ; bool = false)
    {
      this.mUseCollapse = bool;
      int i = 0;
      while (i < paramList.size())
      {
        ButtonViewElement localButtonViewElement = new ButtonViewElement(getContext());
        localButtonViewElement.setBackgroundColor(SkinManager.getBackgroundColor(), 0);
        localButtonViewElement.setTextColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorNormal_New());
        String str2 = ((CategoryNode)paramList.get(i)).name;
        String str1 = str2;
        if (!str2.endsWith("台"))
          str1 = str2 + "台";
        localButtonViewElement.setText(str1);
        addElement(localButtonViewElement);
        localButtonViewElement.setOnElementClickListener(this);
        i += 1;
      }
    }
    this.mCollapsElement = new CollapseElement(getContext());
    addElement(this.mCollapsElement);
    this.mCollapsElement.setOnElementClickListener(this);
    this.mLinePaint.setColor(SkinManager.getDividerColor());
    requestLayout();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLines(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i = 0;
    boolean bool = false;
    if (paramViewElement == this.mCollapsElement)
    {
      if (!this.mIsCollapsed)
        bool = true;
      this.mIsCollapsed = bool;
      this.mCollapsElement.toggle();
      requestLayout();
    }
    while (true)
    {
      return;
      int j = getChildCount();
      while (i < j)
      {
        if (paramViewElement == getChildAt(i))
        {
          paramViewElement = (CategoryNode)this.mNodes.get(i);
          ControllerManager.getInstance().openTraditionalChannelsView(paramViewElement);
          QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", paramViewElement.name);
          return;
        }
        i += 1;
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int n = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mLinePaint.setStrokeWidth(this.lineLayout.width);
    paramInt2 = getChildCount();
    if ((paramInt2 == 0) || (paramInt2 == 1))
    {
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), 0);
      return;
    }
    float f = SkinManager.getInstance().getSubTextSize();
    int i;
    int k;
    int j;
    label100: ButtonViewElement localButtonViewElement;
    int m;
    int i1;
    if (this.mUseCollapse)
      if (this.mIsCollapsed)
      {
        i = 5;
        k = 0;
        j = 0;
        paramInt2 = 0;
        if (k >= i)
          break label222;
        localButtonViewElement = (ButtonViewElement)getChildAt(k);
        localButtonViewElement.measure(this.itemLayout);
        localButtonViewElement.setTextSize(f);
        m = paramInt2;
        if (k % 3 == 0)
          m = 0;
        localButtonViewElement.setTranslationX(m);
        i1 = this.itemLayout.width;
        localButtonViewElement.setTranslationY(j);
        if (k % 3 != 2)
          break label361;
      }
    label222: label361: for (paramInt2 = this.itemLayout.height + j; ; paramInt2 = j)
    {
      k += 1;
      j = paramInt2;
      paramInt2 = i1 + m;
      break label100;
      i = paramInt2 - 1;
      break;
      i = paramInt2 - 1;
      break;
      if ((this.mUseCollapse) && (this.mIsCollapsed))
      {
        localButtonViewElement = (ButtonViewElement)getChildAt(5);
        localButtonViewElement.measure(this.itemLayout);
        localButtonViewElement.setTranslationY(this.itemLayout.height + j);
      }
      this.mCollapsElement.measure(this.itemLayout);
      if (i % 3 == 0)
        paramInt2 = 0;
      this.mCollapsElement.setTranslationX(paramInt2);
      this.mCollapsElement.setTranslationY(j);
      k = i + 1;
      paramInt2 = View.MeasureSpec.getSize(paramInt1);
      i = this.itemLayout.height;
      j = k / 3;
      if (k % 3 == 0);
      for (paramInt1 = n; ; paramInt1 = 1)
      {
        setMeasuredDimension(paramInt2, (j + paramInt1) * i);
        return;
      }
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNodes = ((List)paramObject);
      if (this.mNodes != null)
        setData(this.mNodes);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.CategoryByContentView
 * JD-Core Version:    0.6.2
 */