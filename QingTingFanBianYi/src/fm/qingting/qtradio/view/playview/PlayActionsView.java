package fm.qingting.qtradio.view.playview;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.alarm.ClockManager;
import fm.qingting.qtradio.alarm.ClockManager.IClockListener;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.doubleclick.DoubleClick;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Clock;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.utils.QTMSGManage;
import java.util.Locale;

public class PlayActionsView extends QtView
  implements ViewElement.OnElementClickListener, RootNode.IInfoUpdateEventListener, ClockManager.IClockListener
{
  private final String MODEL_TIMER = "%02d'%02d";
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(150, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private CustomActionButtonElement[] mElements;
  private String mall = null;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 110, 720, 110, 0, 0, ViewLayout.FILL);

  public PlayActionsView(Context paramContext)
  {
    super(paramContext);
    int j = hashCode();
    this.mElements = new CustomActionButtonElement[5];
    int i = 0;
    while (i < this.mElements.length)
    {
      CustomActionButtonElement localCustomActionButtonElement = new CustomActionButtonElement(paramContext);
      localCustomActionButtonElement.setOnElementClickListener(this);
      this.mElements[i] = localCustomActionButtonElement;
      addElement(localCustomActionButtonElement, j);
      i += 1;
    }
    if (isFaved())
      this.mElements[0].setAction("已收藏", 2130837773, 2130837774);
    while (true)
    {
      this.mElements[1].setAction("定时", 2130837793, 2130837792);
      this.mElements[2].setAction("分享", 2130837791, 2130837790);
      this.mElements[3].setAction("签到", 2130837766, 2130837765);
      this.mElements[4].setAction("特价", 2130837777, 2130837777);
      this.mElements[4].setVisible(4);
      InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
      ClockManager.getInstance().addListener(this);
      return;
      this.mElements[0].setAction("收藏", 2130837773, 2130837772);
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
    Object localObject = ClockManager.getInstance();
    if (ClockManager.getInstance().getTimerAvailable())
    {
      int i = ((ClockManager)localObject).getTimerLeft();
      paramInt = i;
      if (i < 0)
        paramInt = 0;
      localObject = String.format(Locale.US, "%02d'%02d", new Object[] { Integer.valueOf(paramInt / 60), Integer.valueOf(paramInt % 60) });
      this.mElements[1].setAction((String)localObject);
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mElements[2])
    {
      EventDispacthManager.getInstance().dispatchAction("shareChoose", InfoManager.getInstance().root().getCurrentPlayingNode());
      QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "share");
      DoubleClick.getInstance().visitButton("分享");
    }
    do
    {
      return;
      if (paramViewElement == this.mElements[0])
      {
        QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "collection");
        paramViewElement = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        CollectionNode localCollectionNode = InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode;
        if (localCollectionNode.isExisted(paramViewElement))
          localCollectionNode.deleteFavNode(paramViewElement);
        while (true)
        {
          DoubleClick.getInstance().visitButton("收藏");
          return;
          localCollectionNode.addFavNode(paramViewElement);
        }
      }
      if (paramViewElement == this.mElements[3])
      {
        int i = InfoManager.getInstance().getEnableNewCheckin();
        if (i == 2)
        {
          QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkinABTestNew");
          QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestNew");
          ControllerManager.getInstance().redirectToLocalWebView("http://qtime.qingting.fm/check?from=1", "签到", true);
        }
        while (true)
        {
          DoubleClick.getInstance().visitButton("签到");
          return;
          if (i == 1)
          {
            QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkinABTestOld");
            QTMSGManage.getInstance().sendStatistcsMessage("checkinABTestOld");
            dispatchActionEvent("checkin", null);
          }
          else
          {
            QTMSGManage.getInstance().sendStatistcsMessage("PlayView", "checkin");
            dispatchActionEvent("checkin", null);
          }
        }
      }
      if (paramViewElement == this.mElements[1])
      {
        ControllerManager.getInstance().openTimerSettingController();
        DoubleClick.getInstance().visitButton("定时");
        return;
      }
    }
    while (paramViewElement != this.mElements[4]);
    QTMSGManage.getInstance().sendStatistcsMessage("mallActionClick");
    ControllerManager.getInstance().redirectToLocalWebView(this.mall, "特价", false);
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
    {
      ChannelNode localChannelNode = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if (InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.isExisted(localChannelNode))
        this.mElements[0].setAction("已收藏", 2130837773, 2130837774);
    }
    else
    {
      return;
    }
    this.mElements[0].setAction("收藏", 2130837773, 2130837772);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 1;
    int j = 0;
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    if (this.mElements[0].getVisiblity() == 0)
    {
      paramInt1 = 1;
      if (this.mElements[4].getVisiblity() != 0)
        break label195;
      paramInt2 = i;
      label59: i = this.mElements.length;
      if (paramInt1 != 0)
        break label219;
    }
    label195: label219: for (paramInt1 = i - 1; ; paramInt1 = i)
    {
      i = paramInt1;
      if (paramInt2 == 0)
        i = paramInt1 - 1;
      int k = (this.standardLayout.width - this.itemLayout.width * i) / (i + 1);
      paramInt2 = k;
      paramInt1 = j;
      while (true)
        if (paramInt1 < this.mElements.length)
        {
          i = paramInt2;
          if (this.mElements[paramInt1].getVisiblity() == 0)
          {
            this.mElements[paramInt1].measure(paramInt2, this.itemLayout.topMargin, this.itemLayout.width + paramInt2, this.itemLayout.getBottom());
            i = paramInt2 + (this.itemLayout.width + k);
          }
          paramInt1 += 1;
          paramInt2 = i;
          continue;
          paramInt1 = 0;
          break;
          paramInt2 = 0;
          break label59;
        }
      setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
      return;
    }
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
        paramClock = String.format(Locale.US, "%02d'%02d", new Object[] { Integer.valueOf(i / 60), Integer.valueOf(i % 60) });
        this.mElements[1].setAction(paramClock);
        this.mElements[1].setNameColor(-1);
      }
    }
  }

  public void onTimeStop(Clock paramClock)
  {
  }

  public void onTimerRemoved()
  {
    this.mElements[1].setAction("定时");
    this.mElements[1].setNameColor(SkinManager.getNewPlaySubColor());
  }

  public void update(String paramString, Object paramObject)
  {
    int i;
    if (paramString.equalsIgnoreCase("showFav"))
      if (isFaved())
      {
        this.mElements[0].setAction("已收藏", 2130837773, 2130837774);
        i = this.mElements[0].getVisiblity();
        this.mElements[0].setVisible(0);
        if (i == 4)
          requestLayout();
      }
    label121: 
    do
    {
      do
      {
        do
        {
          return;
          this.mElements[0].setAction("收藏", 2130837773, 2130837772);
          break;
          if (!paramString.equalsIgnoreCase("hideFav"))
            break label121;
          i = this.mElements[0].getVisiblity();
          this.mElements[0].setVisible(4);
        }
        while (i != 0);
        requestLayout();
        return;
        if (!paramString.equalsIgnoreCase("showMall"))
          break label168;
      }
      while (this.mElements[4].getVisiblity() != 4);
      this.mall = ((String)paramObject);
      this.mElements[4].setVisible(0);
      requestLayout();
      return;
    }
    while ((!paramString.equalsIgnoreCase("hideMall")) || (this.mElements[4].getVisiblity() != 0));
    label168: this.mElements[4].setVisible(4);
    requestLayout();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayActionsView
 * JD-Core Version:    0.6.2
 */