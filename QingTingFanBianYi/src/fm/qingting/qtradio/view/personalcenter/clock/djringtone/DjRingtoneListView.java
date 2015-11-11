package fm.qingting.qtradio.view.personalcenter.clock.djringtone;

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
import fm.qingting.qtradio.model.RingToneInfoNode;
import fm.qingting.qtradio.model.RingToneNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.personalcenter.clock.AlarmTagView;
import java.util.ArrayList;
import java.util.List;

public class DjRingtoneListView extends ViewGroupViewImpl
  implements IEventHandler
{
  private DjRingtoneAdapter adapter;
  private int checkList = -1;
  private IAdapterIViewFactory factory;
  private DjRingtoneFooterView footerView;
  private AlarmTagView mTagView;
  private ListView messageHeaderList;
  private int playingIndex = -1;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public DjRingtoneListView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    final int i = hashCode();
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new DJRingtoneItemView(DjRingtoneListView.this.getContext(), i);
      }
    };
    this.footerView = new DjRingtoneFooterView(paramContext, i);
    this.footerView.setEventHandler(this);
    this.adapter = new DjRingtoneAdapter(new ArrayList(), this.factory);
    this.adapter.setEventHandler(this);
    this.messageHeaderList = new ListView(paramContext);
    this.messageHeaderList.addFooterView(this.footerView);
    this.messageHeaderList.setVerticalFadingEdgeEnabled(false);
    this.messageHeaderList.setCacheColorHint(0);
    this.messageHeaderList.setDivider(null);
    this.messageHeaderList.setHeaderDividersEnabled(false);
    this.messageHeaderList.setSelector(17170445);
    this.messageHeaderList.setAdapter(this.adapter);
    this.messageHeaderList.setCacheColorHint(0);
    addView(this.messageHeaderList);
    this.mTagView = new AlarmTagView(paramContext);
    this.mTagView.setTagName("点击列表试听闹铃声");
    addView(this.mTagView);
  }

  private void initData(List<Object> paramList)
  {
    this.adapter.setData(paramList);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("checkIndex"))
      return Integer.valueOf(this.checkList);
    return super.getValue(paramString, paramObject);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      paramObject1 = (ItemParam)paramObject2;
      i = paramObject1.position;
      paramObject1 = paramObject1.type;
      if (paramObject1.equalsIgnoreCase("changeCheckState"))
        if (this.checkList == i)
        {
          this.checkList = -1;
          this.footerView.update("check", null);
          this.adapter.setCheck(this.checkList);
        }
    }
    while ((!paramString.equalsIgnoreCase("uncheckAll")) || (this.checkList == -1))
    {
      int i;
      do
        while (true)
        {
          return;
          if (this.checkList == -1)
          {
            this.checkList = i;
            this.footerView.update("uncheck", null);
          }
          else
          {
            this.checkList = i;
          }
        }
      while (!paramObject1.equalsIgnoreCase("previewRingtone"));
      if (this.playingIndex == i)
      {
        dispatchActionEvent("stopPreview", null);
        this.playingIndex = -1;
        this.adapter.setPlayingIndex(this.playingIndex);
        return;
      }
      this.playingIndex = i;
      this.adapter.setPlayingIndex(this.playingIndex);
      dispatchActionEvent("startPreview", Integer.valueOf(this.playingIndex));
      return;
    }
    this.checkList = -1;
    this.adapter.setCheck(this.checkList);
    this.footerView.update("check", null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mTagView.layout(0, 0, this.standardLayout.width, this.mTagView.getMeasuredHeight());
    this.messageHeaderList.layout(0, this.mTagView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.standardLayout.measureView(this.mTagView);
    this.messageHeaderList.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.mTagView.getMeasuredHeight(), 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      initData((List)paramObject);
    while (true)
    {
      return;
      if (paramString.equalsIgnoreCase("setRingtone"))
      {
        paramString = (String)paramObject;
        if ((paramString == null) || (InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes == null) || (InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.size() == 0))
          break;
        int i = 0;
        while (i < InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.size())
        {
          if (paramString.equalsIgnoreCase(((RingToneNode)InfoManager.getInstance().root().mRingToneInfoNode.mLstRingToneNodes.get(i)).ringToneId))
          {
            this.checkList = i;
            this.adapter.initCheck(this.checkList);
            this.messageHeaderList.setSelection(this.checkList);
            this.footerView.update("uncheck", null);
            return;
          }
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.djringtone.DjRingtoneListView
 * JD-Core Version:    0.6.2
 */