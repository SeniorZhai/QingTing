package fm.qingting.qtradio.view.navigation.items;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class TopCustomButton extends ViewGroupViewImpl
  implements View.OnClickListener
{
  private int ItemType;
  private ViewLayout buttonLayout = this.standardLayout.createChildLT(114, 66, 10, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private IEventHandler eventHandler;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(144, 86, 702, 86, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CH);
  private Button textView;

  public TopCustomButton(Context paramContext)
  {
    super(paramContext);
    this.textView = ((Button)LayoutInflater.from(paramContext).inflate(2130903053, null));
    this.textView.setGravity(17);
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
    paramInt1 = getMeasuredHeight();
    this.textView.layout(this.buttonLayout.leftMargin, (paramInt1 - this.buttonLayout.height) / 2, this.buttonLayout.getRight(), (paramInt1 + this.buttonLayout.height) / 2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.textView.setTextSize(0, SkinManager.getInstance().getTinyTextSize());
    this.buttonLayout.measureView(this.textView);
    this.textView.setPadding(0, 0, 0, 0);
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
    this.textView.setText(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.navigation.items.TopCustomButton
 * JD-Core Version:    0.6.2
 */