package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.TextView;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class TopTextView extends ViewGroupViewImpl
  implements View.OnClickListener
{
  private int ItemType;
  private IEventHandler eventHandler;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 114, 480, 114, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private TextView textView;
  private String title = " ";

  public TopTextView(Context paramContext)
  {
    super(paramContext);
    this.textView = new TextView(paramContext);
    this.textView.setTextColor(-1);
    this.textView.setGravity(17);
    this.textView.setMaxLines(1);
    addView(this.textView);
    this.textView.setOnClickListener(this);
  }

  public View getView()
  {
    return this;
  }

  public void onClick(View paramView)
  {
    if (this.eventHandler != null)
      this.eventHandler.onEvent(this, "click", Integer.valueOf(this.ItemType));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.textView.layout(this.standardLayout.leftMargin, this.standardLayout.topMargin, this.standardLayout.leftMargin + this.standardLayout.width, this.standardLayout.topMargin + this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.textView.setTextSize(0, SkinManager.getInstance().getNormalTextSize());
    this.standardLayout.measureView(this.textView);
    setMeasuredDimension(this.standardLayout.width, paramInt2);
  }

  public void setEventHandler(IEventHandler paramIEventHandler)
  {
    this.eventHandler = paramIEventHandler;
  }

  public void setItemType(int paramInt)
  {
    this.ItemType = paramInt;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
    this.textView.setText(this.title);
  }

  public void setTitleColor(int paramInt)
  {
    this.textView.setTextColor(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.TopTextView
 * JD-Core Version:    0.6.2
 */