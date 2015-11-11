package fm.qingting.qtradio.view.personalcenter.clock.daysetting;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.AlarmDaySettingAdapter;
import java.util.ArrayList;
import java.util.List;

public class AlarmDaySettingView extends ViewGroupViewImpl
  implements IEventHandler
{
  private AlarmDaySettingAdapter adapter;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public AlarmDaySettingView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new AlarmDaySettingItemView(AlarmDaySettingView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new AlarmDaySettingAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    paramContext = new ArrayList();
    paramContext.add("星期一");
    paramContext.add("星期二");
    paramContext.add("星期三");
    paramContext.add("星期四");
    paramContext.add("星期五");
    paramContext.add("星期六");
    paramContext.add("星期日");
    paramContext.add("只响一次");
    this.adapter.setData(paramContext);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("day"))
      return Integer.valueOf(this.adapter.getCheckList());
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      if (paramObject1.type.equalsIgnoreCase("itemClick"))
      {
        i = paramObject1.position;
        this.adapter.checkIndex(i);
      }
    }
    while (!paramString.equalsIgnoreCase("chooseRepeat"))
    {
      int i;
      return;
    }
    dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.standardLayout.measureView(this.mListView);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("day"))
      this.adapter.init(((Integer)paramObject).intValue());
    while ((!paramString.equalsIgnoreCase("isRepeat")) || (((Boolean)paramObject).booleanValue()))
      return;
    this.adapter.resetCheck();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.daysetting.AlarmDaySettingView
 * JD-Core Version:    0.6.2
 */