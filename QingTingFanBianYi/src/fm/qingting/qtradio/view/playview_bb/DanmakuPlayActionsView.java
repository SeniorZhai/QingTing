package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.alarm.ClockManager;
import fm.qingting.qtradio.alarm.ClockManager.IClockListener;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Clock;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.util.Locale;

public class DanmakuPlayActionsView extends QtView
  implements ViewElement.OnElementClickListener, RootNode.IInfoUpdateEventListener, ClockManager.IClockListener
{
  private final String MODEL_TIMER = "%02d'%02d";
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(60, 60, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement[] mElements;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 110, 720, 110, 0, 0, ViewLayout.FILL);

  public DanmakuPlayActionsView(Context paramContext)
  {
    super(paramContext);
    int j = hashCode();
    int k = ScreenConfiguration.getCustomExtraBound();
    this.mElements = new ButtonViewElement[4];
    int i = 0;
    while (i < this.mElements.length)
    {
      ButtonViewElement localButtonViewElement = new ButtonViewElement(paramContext);
      localButtonViewElement.setOnElementClickListener(this);
      this.mElements[i] = localButtonViewElement;
      addElement(localButtonViewElement, j);
      localButtonViewElement.expandHotPot(k * 2);
      i += 1;
    }
    if (isFaved())
      this.mElements[0].setBackground(2130837530, 2130837530);
    while (true)
    {
      this.mElements[1].setBackground(2130837531, 2130837531);
      this.mElements[2].setBackground(2130837533, 2130837533);
      this.mElements[3].setBackground(2130837528, 2130837528);
      InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
      ClockManager.getInstance().addListener(this);
      return;
      this.mElements[0].setBackground(2130837529, 2130837529);
    }
  }

  private boolean isFaved()
  {
    ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    return InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localChannelNode);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onClockTime(int paramInt)
  {
    ClockManager localClockManager = ClockManager.getInstance();
    if (ClockManager.getInstance().getTimerAvailable())
    {
      int i = localClockManager.getTimerLeft();
      paramInt = i;
      if (i < 0)
        paramInt = 0;
      String.format(Locale.US, "%02d'%02d", new Object[] { Integer.valueOf(paramInt / 60), Integer.valueOf(paramInt % 60) });
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i;
    if (paramViewElement == this.mElements[3])
    {
      i = InfoManager.getInstance().getEnableNewCheckin();
      if (i == 2)
      {
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkinABTestNew");
        QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestNew");
        ControllerManager.getInstance().redirectToLocalWebView("http://qtime.qingting.fm/check?from=1", "签到", true);
      }
    }
    do
    {
      do
      {
        return;
        if (i == 1)
        {
          QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkinABTestOld");
          QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestOld");
          dispatchActionEvent("checkin", null);
          return;
        }
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkin");
        dispatchActionEvent("checkin", null);
        return;
        if (paramViewElement == this.mElements[0])
        {
          QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "collection");
          paramViewElement = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
          CollectionNode localCollectionNode = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode;
          if (localCollectionNode.isExisted(paramViewElement))
          {
            localCollectionNode.deleteFavNode(paramViewElement);
            return;
          }
          localCollectionNode.addFavNode(paramViewElement);
          return;
        }
        if (paramViewElement != this.mElements[1])
          break;
        EventDispacthManager.getInstance().dispatchAction("shareChoose", InfoManager.getInstance().root().getCurrentPlayingNode());
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "share");
        paramViewElement = InfoManager.getInstance().root().getCurrentPlayingNode();
      }
      while ((paramViewElement == null) || (!paramViewElement.nodeName.equalsIgnoreCase("program")));
      i = ((ProgramNode)paramViewElement).id;
      QTMSGManage.getInstance().sendStatistcsMessage("danmaku_share", "" + i);
      return;
    }
    while (paramViewElement != this.mElements[2]);
    ControllerManager.getInstance().openTimerSettingController();
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
    {
      ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localChannelNode))
        this.mElements[0].setBackground(2130837530, 2130837530);
    }
    else
    {
      return;
    }
    this.mElements[0].setBackground(2130837529, 2130837529);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    label50: int i;
    if (this.mElements[0].getVisiblity() == 0)
    {
      paramInt1 = 1;
      if (paramInt1 == 0)
        break label163;
      paramInt2 = this.mElements.length;
      i = (this.standardLayout.width - this.itemLayout.width * paramInt2) / (paramInt2 + 1);
      if (paramInt1 == 0)
        break label174;
    }
    label163: label174: for (paramInt1 = 0; ; paramInt1 = 1)
    {
      int j = (this.standardLayout.height - this.itemLayout.height) / 2;
      paramInt2 = i;
      while (paramInt1 < this.mElements.length)
      {
        this.mElements[paramInt1].measure(paramInt2, j, this.itemLayout.width + paramInt2, this.itemLayout.height + j);
        paramInt2 += this.itemLayout.width + i;
        paramInt1 += 1;
      }
      paramInt1 = 0;
      break;
      paramInt2 = this.mElements.length - 1;
      break label50;
    }
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onTime(Clock paramClock)
  {
  }

  public void onTimeStart(Clock paramClock)
  {
    if (paramClock.type == 2)
    {
      paramClock = ClockManager.getInstance();
      if (ClockManager.getInstance().getTimerAvailable())
      {
        int j = paramClock.getTimerLeft();
        int i = j;
        if (j < 0)
          i = 0;
        String.format(Locale.US, "%02d'%02d", new Object[] { Integer.valueOf(i / 60), Integer.valueOf(i % 60) });
      }
    }
  }

  public void onTimeStop(Clock paramClock)
  {
  }

  public void onTimerRemoved()
  {
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    if (paramString.equalsIgnoreCase("showFav"))
      if (isFaved())
      {
        this.mElements[0].setBackground(2130837530, 2130837530);
        i = this.mElements[0].getVisiblity();
        this.mElements[0].setVisible(0);
        if (i == 4)
          requestLayout();
      }
    do
    {
      do
      {
        return;
        this.mElements[0].setBackground(2130837529, 2130837529);
        break;
      }
      while (!paramString.equalsIgnoreCase("hideFav"));
      i = this.mElements[0].getVisiblity();
      this.mElements[0].setVisible(4);
    }
    while (i != 0);
    requestLayout();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuPlayActionsView
 * JD-Core Version:    0.6.2
 */