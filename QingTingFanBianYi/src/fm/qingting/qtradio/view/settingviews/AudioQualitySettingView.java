package fm.qingting.qtradio.view.settingviews;

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
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.SingleCheckAdapter;
import java.util.ArrayList;
import java.util.List;

public class AudioQualitySettingView extends ViewGroupViewImpl
  implements IEventHandler
{
  private SingleCheckAdapter adapter;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public AudioQualitySettingView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new SingleCheckSettingItemView(AudioQualitySettingView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new SingleCheckAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setSelector(17170445);
    this.mListView.setAdapter(this.adapter);
    this.mListView.setCacheColorHint(0);
    addView(this.mListView);
  }

  private void initData()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new SettingItem("智能模式（推荐）", SettingItem.SettingType.check, "auto", "WIFI下使用高品质模式，2G/3G使用流畅模式"));
    localArrayList.add(new SettingItem("流畅模式", SettingItem.SettingType.check, "fluent", "流量消耗小，适合在2G/3G网络下使用"));
    localArrayList.add(new SettingItem("高品质模式", SettingItem.SettingType.check, "highquality", "音质更好，推荐在WIFI下使用"));
    this.adapter.setData(localArrayList);
    int i = InfoManager.getInstance().getAudioQualitySetting();
    if (i > -1)
      this.adapter.checkIndex(i);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      if (paramObject1.type.equalsIgnoreCase("check"))
      {
        int i = paramObject1.position;
        this.adapter.checkIndex(i);
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.standardLayout.layoutView(this.mListView);
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
    if (paramString.equalsIgnoreCase("setData"))
      initData();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.AudioQualitySettingView
 * JD-Core Version:    0.6.2
 */