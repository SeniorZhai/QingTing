package fm.qingting.qtradio.view.personalcenter.timer;

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
import fm.qingting.qtradio.alarm.ClockManager;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Clock;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SingleCheckAdapter;
import fm.qingting.qtradio.view.settingviews.SettingItem;
import fm.qingting.qtradio.view.settingviews.SettingItem.SettingType;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.List;

public class TimerPickView extends ViewGroupViewImpl
  implements IEventHandler
{
  private static final String[] TIMER_IDS = { "ten", "twenty", "thirty", "sixty", "ninety", "twohour" };
  private static final int[] TIMER_LENGTHS = { 600, 1200, 1800, 3600, 5400, 7200 };
  private static final String[] TIMER_TIMES = { "10分钟", "20分钟", "30分钟", "60分钟", "90分钟", "120分钟" };
  private SingleCheckAdapter adapter;
  private IAdapterIViewFactory factory;
  private ListView mListView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public TimerPickView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new TimerItemView(TimerPickView.this.getContext(), this.val$hash);
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
    addView(this.mListView);
  }

  private void addTimer(String paramString)
  {
    int i = 0;
    if (paramString.equalsIgnoreCase("cancel"))
    {
      ClockManager.getInstance().removeTimer();
      GlobalCfg.getInstance(getContext()).setQuitTime(9223372036854775807L);
      PlayerAgent.getInstance().stopQuitTimer();
      InfoManager.getInstance().setQuitAfterPlay(false);
      return;
    }
    long l1;
    long l2;
    if (paramString.equalsIgnoreCase("quitafterplay"))
    {
      paramString = InfoManager.getInstance().root().getCurrentPlayingNode();
      int j;
      if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("program")))
      {
        j = ((ProgramNode)paramString).getCurrPlayStatus();
        if (j != 1)
          break label175;
        l1 = System.currentTimeMillis() / 1000L;
        i = (int)(((ProgramNode)paramString).getAbsoluteEndTime() - l1);
      }
      while (true)
      {
        if (i > 0)
        {
          ClockManager.getInstance().addTimer(new Clock(2, i, true));
          l1 = System.currentTimeMillis() / 1000L;
          l2 = i;
          GlobalCfg.getInstance(getContext()).setQuitTime(l1 + l2);
        }
        PlayerAgent.getInstance().startQuitTimer();
        InfoManager.getInstance().setQuitAfterPlay(true);
        return;
        label175: if (j == 3)
        {
          i = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
          i = ((ProgramNode)paramString).getDuration() - i;
        }
      }
    }
    i = 0;
    if (i < TIMER_IDS.length)
      if (!TIMER_IDS[i].equalsIgnoreCase(paramString));
    for (i = TIMER_LENGTHS[i]; ; i = 0)
    {
      ClockManager.getInstance().addTimer(new Clock(2, i, true));
      InfoManager.getInstance().setQuitAfterPlay(false);
      l1 = System.currentTimeMillis() / 1000L;
      l2 = i;
      GlobalCfg.getInstance(getContext()).setQuitTime(l1 + l2);
      PlayerAgent.getInstance().startQuitTimer();
      QTMSGManage.getInstance().sendStatistcsMessage("timer_add");
      return;
      i += 1;
      break;
    }
  }

  private void initData()
  {
    int j = 0;
    Object localObject = new ArrayList();
    int i = 0;
    while (i < TIMER_IDS.length)
    {
      ((List)localObject).add(new SettingItem(TIMER_TIMES[i], SettingItem.SettingType.check, TIMER_IDS[i]));
      i += 1;
    }
    ((List)localObject).add(new SettingItem("当前节目播放完关闭", SettingItem.SettingType.check, "quitafterplay"));
    ((List)localObject).add(new SettingItem("取消定时关闭", SettingItem.SettingType.check, "cancel"));
    this.adapter.setData((List)localObject);
    localObject = ClockManager.getInstance().getTimer();
    if (localObject != null)
    {
      int k = ((Clock)localObject).getTime();
      i = j;
      if (i >= TIMER_LENGTHS.length)
        break label203;
      if (TIMER_LENGTHS[i] != k);
    }
    label203: for (j = i; ; j = -1)
    {
      i = j;
      if (j == -1)
      {
        i = j;
        if (!InfoManager.getInstance().getQuitAfterPlay());
      }
      for (i = 6; ; i = 7)
      {
        j = i;
        if (i == -1)
          j = 7;
        if (j > -1)
          this.adapter.checkIndex(j);
        return;
        i += 1;
        break;
      }
    }
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
        addTimer((String)paramObject1.param);
        ControllerManager.getInstance().popLastController();
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
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.timer.TimerPickView
 * JD-Core Version:    0.6.2
 */