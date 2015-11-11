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
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.Attributes;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import java.util.List;

class CampusCategoryView extends QtView
  implements ViewElement.OnElementClickListener, InfoManager.ISubscribeEventListener
{
  private final int ID = 82;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(180, 90, 720, 80, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(1, 60, 0, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final Paint mLinePaint = new Paint();
  private CategoryNode mNode;

  public CampusCategoryView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mLinePaint.setColor(SkinManager.getDividerColor());
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
        if (i % 4 == 0)
        {
          k = getChildAt(i).getTopMargin();
          paramCanvas.drawLine(0.0F, k, getMeasuredWidth(), k, this.mLinePaint);
        }
        if (i % 4 != 3)
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

  private Attribute findAttribute(int paramInt)
  {
    List localList = this.mNode.getLstAttributes(true);
    if (localList == null)
      return null;
    int i = 0;
    while (i < localList.size())
    {
      Attributes localAttributes = (Attributes)localList.get(i);
      if (localAttributes.id == 82)
      {
        localList = localAttributes.mLstAttribute;
        if ((localList == null) || (paramInt >= localList.size()))
          break;
        return (Attribute)localList.get(paramInt);
      }
      i += 1;
    }
    return null;
  }

  private void setData(List<Attribute> paramList)
  {
    removeAllElements();
    if (paramList == null)
      return;
    int i = 0;
    while (i < paramList.size())
    {
      ButtonViewElement localButtonViewElement = new ButtonViewElement(getContext());
      localButtonViewElement.setBackgroundColor(SkinManager.getBackgroundColor(), 0);
      localButtonViewElement.setTextColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorNormal_New());
      localButtonViewElement.setText(((Attribute)paramList.get(i)).name);
      addElement(localButtonViewElement);
      localButtonViewElement.setOnElementClickListener(this);
      i += 1;
    }
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
          break label51;
        paramViewElement = findAttribute(i);
        if (paramViewElement != null)
        {
          PlayerAgent.getInstance().addPlaySource(40);
          ControllerManager.getInstance().openChannelListByAttributeController(this.mNode, paramViewElement);
        }
      }
      return;
      label51: i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mLinePaint.setStrokeWidth(this.lineLayout.width);
    int m = getChildCount();
    if (m == 0)
    {
      setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), 0);
      return;
    }
    float f = SkinManager.getInstance().getSubTextSize();
    int j = 0;
    paramInt2 = 0;
    int i = 0;
    int n;
    if (j < m)
    {
      ButtonViewElement localButtonViewElement = (ButtonViewElement)getChildAt(j);
      localButtonViewElement.measure(this.itemLayout);
      localButtonViewElement.setTextSize(f);
      if (j % 4 == 0)
        i = 0;
      localButtonViewElement.setTranslationX(i);
      n = this.itemLayout.width;
      localButtonViewElement.setTranslationY(paramInt2);
      if (j % 4 != 3)
        break label227;
      paramInt2 = this.itemLayout.height + paramInt2;
    }
    label227: 
    while (true)
    {
      j += 1;
      i = n + i;
      break;
      paramInt2 = View.MeasureSpec.getSize(paramInt1);
      i = this.itemLayout.height;
      j = m / 4;
      if (m % 4 == 0);
      for (paramInt1 = k; ; paramInt1 = 1)
      {
        setMeasuredDimension(paramInt2, i * (paramInt1 + j));
        return;
      }
    }
  }

  public void onNotification(String paramString)
  {
    int i;
    if (paramString.equalsIgnoreCase("RECV_ATTRIBUTES"))
    {
      paramString = this.mNode.getLstAttributes(true);
      if (paramString != null)
        i = 0;
    }
    while (true)
    {
      if (i < paramString.size())
      {
        Attributes localAttributes = (Attributes)paramString.get(i);
        if (localAttributes.id == 82)
          setData(localAttributes.mLstAttribute);
      }
      else
      {
        return;
      }
      i += 1;
    }
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mNode = ((CategoryNode)paramObject);
      paramString = this.mNode.getLstAttributes(false);
      if (paramString != null)
        break label55;
      InfoManager.getInstance().loadCategoryAttrs(this.mNode, this.mNode.categoryId, this);
      setData(null);
    }
    while (true)
    {
      return;
      label55: int i = 0;
      while (i < paramString.size())
      {
        paramObject = (Attributes)paramString.get(i);
        if (paramObject.id == 82)
        {
          setData(paramObject.mLstAttribute);
          return;
        }
        i += 1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.CampusCategoryView
 * JD-Core Version:    0.6.2
 */