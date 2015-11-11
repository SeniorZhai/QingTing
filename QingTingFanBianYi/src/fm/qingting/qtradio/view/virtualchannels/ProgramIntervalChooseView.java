package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.Locale;

class ProgramIntervalChooseView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout btnLayout = this.standardLayout.createChildLT(160, 60, 20, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement[] mIntervalButtons;
  private int mProgramCnt = 1000;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 180, 720, 180, 0, 0, ViewLayout.FILL);

  public ProgramIntervalChooseView(Context paramContext)
  {
    super(paramContext);
  }

  private void addButtons()
  {
    if (this.mProgramCnt < 1);
    while (true)
    {
      return;
      int m = (this.mProgramCnt - 1) / 100 + 1;
      this.mIntervalButtons = new ButtonViewElement[m];
      int i = 0;
      while (i < m)
      {
        ButtonViewElement localButtonViewElement = new ButtonViewElement(getContext());
        localButtonViewElement.setBackgroundColor(SkinManager.getPopButtonHighlightColor(), SkinManager.getBackgroundColor());
        localButtonViewElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getTextColorSubInfo());
        int n = getStart(i);
        int k = n + 99;
        int j = k;
        if (k > this.mProgramCnt)
          j = this.mProgramCnt;
        localButtonViewElement.setText(getIntervalText(n, j));
        localButtonViewElement.setOnElementClickListener(this);
        this.mIntervalButtons[i] = localButtonViewElement;
        addElement(localButtonViewElement);
        i += 1;
      }
    }
  }

  private String getIntervalText(int paramInt1, int paramInt2)
  {
    return String.format(Locale.US, "%d-%d", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2) });
  }

  private int getStart(int paramInt)
  {
    return paramInt * 100 + 1;
  }

  private int measureElements()
  {
    int j = 0;
    if (this.mIntervalButtons == null)
      return 0;
    int i1 = (this.standardLayout.width - this.btnLayout.leftMargin * 2 - this.btnLayout.width * 4) / 3;
    int i = this.btnLayout.leftMargin;
    int k = this.btnLayout.height + i1 + i1;
    int m = i1;
    if (j < this.mIntervalButtons.length)
    {
      ButtonViewElement localButtonViewElement = this.mIntervalButtons[j];
      localButtonViewElement.measure(i, m, this.btnLayout.width + i, this.btnLayout.height + m);
      localButtonViewElement.setTextSize(this.btnLayout.height * 0.5F);
      int n;
      if (j % 4 == 3)
      {
        int i2 = this.btnLayout.leftMargin;
        int i3 = m + (this.btnLayout.height + i1);
        n = k;
        m = i3;
        i = i2;
        if (j != this.mIntervalButtons.length - 1)
        {
          n = k + (this.btnLayout.height + i1);
          i = i2;
          m = i3;
        }
      }
      while (true)
      {
        j += 1;
        k = n;
        break;
        i += this.btnLayout.width + i1;
        n = k;
      }
    }
    return k;
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i = 0;
    while (true)
    {
      if (i < this.mIntervalButtons.length)
      {
        if (paramViewElement == this.mIntervalButtons[i])
          dispatchActionEvent("jumptopoint", Integer.valueOf(getStart(i)));
      }
      else
        return;
      i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.btnLayout.scaleToBounds(this.standardLayout);
    paramInt1 = measureElements();
    setMeasuredDimension(this.standardLayout.width, paramInt1);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mProgramCnt = ((Integer)paramObject).intValue();
      addButtons();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ProgramIntervalChooseView
 * JD-Core Version:    0.6.2
 */