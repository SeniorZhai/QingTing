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
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.LiveNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.RadioNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

class CategoryByRegionView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(240, 90, 720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(1, 60, 0, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final Paint mLinePaint = new Paint();
  private Node mLocalNode;
  private List<CategoryNode> mNodes;
  private Node mRadioNode;
  private final ViewLayout narrowLayout = ViewLayout.createViewLayoutWithBoundsLT(180, 90, 720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public CategoryByRegionView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLinePaint.setColor(SkinManager.getDividerColor());
  }

  private void addNewItem(String paramString)
  {
    ButtonViewElement localButtonViewElement = new ButtonViewElement(getContext());
    localButtonViewElement.setBackgroundColor(SkinManager.getBackgroundColor(), 0);
    localButtonViewElement.setTextColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorNormal_New());
    localButtonViewElement.setText(paramString);
    addElement(localButtonViewElement);
    localButtonViewElement.setOnElementClickListener(this);
  }

  private void drawLines(Canvas paramCanvas)
  {
    int k = getChildCount();
    if (k == 0)
      return;
    if (narrow());
    for (int i = 4; ; i = 3)
    {
      int j = 0;
      while (j < k)
      {
        int m;
        if (j % i == 0)
        {
          m = getChildAt(j).getTopMargin();
          paramCanvas.drawLine(0.0F, m, getMeasuredWidth(), m, this.mLinePaint);
        }
        if (j % i != i - 1)
        {
          ViewElement localViewElement = getChildAt(j);
          m = localViewElement.getRightMargin();
          int n = localViewElement.getTopMargin();
          paramCanvas.drawLine(m, this.lineLayout.topMargin + n, m, n + this.lineLayout.getBottom(), this.mLinePaint);
        }
        j += 1;
      }
      break;
    }
  }

  private boolean narrow()
  {
    return getChildCount() == 4;
  }

  private void setData(List<CategoryNode> paramList)
  {
    if (this.mLocalNode != null)
    {
      String str2 = ((Attribute)this.mLocalNode).name;
      String str1 = str2;
      if (str2 != null)
      {
        str1 = str2;
        if (!str2.equalsIgnoreCase("台"))
          str1 = str2 + "台";
      }
      addNewItem(str1);
    }
    int i = 0;
    while (i < paramList.size())
    {
      addNewItem(((CategoryNode)paramList.get(i)).name);
      i += 1;
    }
    if (this.mRadioNode != null)
      addNewItem(((RadioNode)this.mRadioNode).mTitle);
    requestLayout();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLines(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int j = getChildCount();
    int i = 0;
    while (true)
    {
      if (i < j)
      {
        if (paramViewElement != getChildAt(i))
          break label265;
        if (this.mLocalNode == null)
          break label161;
        if (i != 0)
          break label53;
        ControllerManager.getInstance().openTraditionalChannelsView(this.mLocalNode);
        QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", "本地");
      }
      label53: 
      do
      {
        do
        {
          return;
          i -= 1;
          if (i < this.mNodes.size())
          {
            paramViewElement = (CategoryNode)this.mNodes.get(i);
            if (paramViewElement.isRegionCategory())
              ControllerManager.getInstance().openCategoryListView((Node)this.mNodes.get(i));
            while (true)
            {
              QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", paramViewElement.name);
              return;
              ControllerManager.getInstance().openTraditionalChannelsView(paramViewElement);
            }
          }
        }
        while (this.mRadioNode == null);
        ControllerManager.getInstance().openRadioChannelsController(this.mRadioNode);
        QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", "系统收音机");
        return;
        if (i < this.mNodes.size())
        {
          paramViewElement = (CategoryNode)this.mNodes.get(i);
          if (paramViewElement.isRegionCategory())
            ControllerManager.getInstance().openCategoryListView((Node)this.mNodes.get(i));
          while (true)
          {
            QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", paramViewElement.name);
            return;
            ControllerManager.getInstance().openTraditionalChannelsView(paramViewElement);
          }
        }
      }
      while (this.mRadioNode == null);
      label161: ControllerManager.getInstance().openRadioChannelsController(this.mRadioNode);
      QTMSGManage.getInstance().sendStatistcsMessage("v6_category_live_click", "系统收音机");
      return;
      label265: i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int n = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.narrowLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mLinePaint.setStrokeWidth(this.lineLayout.width);
    int i1 = getChildCount();
    if (i1 == 0)
    {
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), 0);
      return;
    }
    boolean bool = narrow();
    int j;
    int k;
    int i;
    label109: ButtonViewElement localButtonViewElement;
    label141: int m;
    if (bool)
    {
      j = 4;
      float f = SkinManager.getInstance().getSubTextSize();
      k = 0;
      paramInt2 = 0;
      i = 0;
      if (k >= i1)
        break label253;
      localButtonViewElement = (ButtonViewElement)getChildAt(k);
      if (!bool)
        break label229;
      localButtonViewElement.measure(this.narrowLayout);
      localButtonViewElement.setTextSize(f);
      if (k % j == 0)
        i = 0;
      localButtonViewElement.setTranslationX(i);
      if (!bool)
        break label241;
      m = this.narrowLayout.width;
      label179: localButtonViewElement.setTranslationY(paramInt2);
      if (k % j != j - 1)
        break label303;
      paramInt2 = this.itemLayout.height + paramInt2;
    }
    label303: 
    while (true)
    {
      k += 1;
      i = m + i;
      break label109;
      j = 3;
      break;
      label229: localButtonViewElement.measure(this.itemLayout);
      break label141;
      label241: m = this.itemLayout.width;
      break label179;
      label253: paramInt2 = View.MeasureSpec.getSize(paramInt1);
      i = this.itemLayout.height;
      k = i1 / j;
      if (i1 % j == 0);
      for (paramInt1 = n; ; paramInt1 = 1)
      {
        setMeasuredDimension(paramInt2, (k + paramInt1) * i);
        return;
      }
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNodes = ((List)paramObject);
      this.mLocalNode = InfoManager.getInstance().root().mContentCategory.mLiveNode.getLocalCategoryNode();
      this.mRadioNode = InfoManager.getInstance().root().mContentCategory.mLiveNode.mRadioNode;
      if (this.mNodes != null)
        setData(this.mNodes);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.CategoryByRegionView
 * JD-Core Version:    0.6.2
 */