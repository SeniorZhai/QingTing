package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.AbsCheckBoxElement.OnCheckChangeListener;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;

public class TopCheckView extends QtView
{
  private IEventHandler eventHandler;
  private SingleCheckBoxElement mBoxElement;
  private int mItemType;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(106, 98, 720, 114, 0, 8, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CH);

  public TopCheckView(Context paramContext)
  {
    super(paramContext);
    this.mBoxElement = new SingleCheckBoxElement(paramContext);
    addElement(this.mBoxElement);
    this.mBoxElement.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (TopCheckView.this.eventHandler != null)
          TopCheckView.this.eventHandler.onEvent(this, "click", Integer.valueOf(TopCheckView.this.mItemType));
      }
    });
  }

  public View getView()
  {
    return this;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.mBoxElement.measure(this.standardLayout);
    setMeasuredDimension(this.standardLayout.width, paramInt2);
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setItemType(int paramInt)
  {
    this.mItemType = paramInt;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("resetState"))
      this.mBoxElement.uncheck(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.TopCheckView
 * JD-Core Version:    0.6.2
 */