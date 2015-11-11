package fm.qingting.qtradio.view.moreContentView;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.alarm.ClockManager;
import fm.qingting.qtradio.alarm.ClockManager.IClockListener;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.im.IMAgent.IMEventListener;
import fm.qingting.qtradio.im.LatestMessages;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Clock;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.RootNode.IInfoUpdateEventListener;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.MiniPlayerPlaceHolder;
import fm.qingting.qtradio.wo.IHttpAsyncTaskListener;
import fm.qingting.qtradio.wo.WoApiRequest;
import fm.qingting.qtradio.wo.WoApiRequest.OnCompletedInitListener;
import fm.qingting.qtradio.wo.WoNetEventListener;
import java.util.ArrayList;
import java.util.List;

public class UserInfoView extends ViewGroupViewImpl
  implements RootNode.IInfoUpdateEventListener, InfoManager.ISubscribeEventListener, IMAgent.IMEventListener, ClockManager.IClockListener, IHttpAsyncTaskListener, WoApiRequest.OnCompletedInitListener
{
  private SectionAdapter adapter;
  private ISectionAdapterIViewFactory factory;
  private int mLatestMessageCnt = 0;
  private ListView mListView;
  private UserTitleView mTitleView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public UserInfoView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    this.factory = new ISectionAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return null;
        case 0:
          return new CustomSectionView(UserInfoView.this.getContext());
        case 1:
          return new UserinfoItemView(UserInfoView.this.getContext());
        case 2:
        }
        return new BannerItemView(UserInfoView.this.getContext());
      }
    };
    this.adapter = new SectionAdapter(new ArrayList(), this.factory)
    {
      public int getViewTypeCount()
      {
        return 3;
      }
    };
    this.mListView = new ListView(paramContext);
    this.mTitleView = new UserTitleView(paramContext);
    this.mListView.addHeaderView(this.mTitleView);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setSelector(new ColorDrawable(0));
    MiniPlayerPlaceHolder.wrapListView(paramContext, this.mListView);
    this.mListView.setAdapter(this.adapter);
    addView(this.mListView);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 0);
    InfoManager.getInstance().root().registerInfoUpdateListener(this, 11);
    InfoManager.getInstance().registerViewTime(this);
    InfoManager.getInstance().registerSubscribeEventListener(this, "RUIU");
    IMAgent.getInstance().registerIMEventListener(this, "RECV_LIST_MSG");
    IMAgent.getInstance().registerIMEventListener(this, "RECV_SINGLE_MSG");
    ClockManager.getInstance().addListener(this);
    LatestMessages.resetBaseTime();
    if (WoNetEventListener.isChinaUnicom(getContext()))
    {
      generateSectionList(true);
      return;
    }
    generateSectionList(false);
  }

  private void generateSectionList(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new SectionItem(2, null));
    localArrayList.add(new SectionItem(1, Integer.valueOf(0)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(10)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(1)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(2)));
    localArrayList.add(new SectionItem(0, null));
    if (InfoManager.getInstance().getWemartMall() != null)
    {
      localArrayList.add(new SectionItem(1, Integer.valueOf(12)));
      localArrayList.add(new SectionItem(0, null));
    }
    if ((InfoManager.getInstance().getLstGameBean() != null) && (InfoManager.getInstance().getLstGameBean().size() > 0))
    {
      localArrayList.add(new SectionItem(1, Integer.valueOf(11)));
      localArrayList.add(new SectionItem(0, null));
    }
    if (paramBoolean)
      localArrayList.add(new SectionItem(1, Integer.valueOf(8)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(4)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(3)));
    localArrayList.add(new SectionItem(0, null));
    localArrayList.add(new SectionItem(1, Integer.valueOf(5)));
    localArrayList.add(new SectionItem(1, Integer.valueOf(6)));
    localArrayList.add(new SectionItem(0, null));
    localArrayList.add(new SectionItem(1, Integer.valueOf(7)));
    localArrayList.add(new SectionItem(0, null));
    this.adapter.setData(localArrayList);
  }

  private void initData()
  {
    this.mTitleView.update("setUser", InfoManager.getInstance().getUserProfile());
    LatestMessages.loadUnreadMsgs(false);
  }

  private void invalidateCertainView(int paramInt)
  {
    int i = 0;
    while (i < this.adapter.getCount())
    {
      IView localIView = (IView)this.mListView.getChildAt(i);
      if (localIView != null)
      {
        Object localObject = localIView.getValue("type", null);
        if ((localObject != null) && (((Integer)localObject).intValue() == paramInt))
          localIView.getView().invalidate();
      }
      i += 1;
    }
  }

  private void invalidateVisibleChildren()
  {
    int i = 0;
    while (i < this.adapter.getCount())
    {
      View localView = this.mListView.getChildAt(i);
      if (localView != null)
        localView.invalidate();
      i += 1;
    }
  }

  public void close(boolean paramBoolean)
  {
    IMAgent.getInstance().unRegisterIMEventListener("RECV_LAST_MSG_ACTION", this);
    IMAgent.getInstance().unRegisterIMEventListener("RECV_LIST_MSG", this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().unregisterViewTime(this);
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(0, this);
    InfoManager.getInstance().root().unRegisterInfoUpdateListener(11, this);
    super.close(paramBoolean);
  }

  public void onClockTime(int paramInt)
  {
    if (ClockManager.getInstance().getTimerAvailable())
      invalidateCertainView(5);
  }

  public void onCompletedInit()
  {
    if (WoApiRequest.hasOpen())
      generateSectionList(true);
  }

  // ERROR //
  public void onGetResult(Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: aload_1
    //   1: checkcast 314	java/lang/String
    //   4: astore_1
    //   5: aload_1
    //   6: ifnull +82 -> 88
    //   9: aload_1
    //   10: ldc_w 316
    //   13: invokevirtual 320	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   16: istore_3
    //   17: iload_3
    //   18: ifeq +70 -> 88
    //   21: aload_2
    //   22: checkcast 314	java/lang/String
    //   25: invokestatic 324	fm/qingting/qtradio/wo/WoApiRequest:parseJsonString	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   28: astore_1
    //   29: aload_1
    //   30: ldc_w 326
    //   33: invokevirtual 332	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   36: astore_1
    //   37: aload_1
    //   38: invokestatic 324	fm/qingting/qtradio/wo/WoApiRequest:parseJsonString	(Ljava/lang/String;)Lorg/json/JSONObject;
    //   41: astore_1
    //   42: aload_1
    //   43: ifnull +45 -> 88
    //   46: aload_1
    //   47: ldc_w 334
    //   50: invokevirtual 332	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   53: ldc_w 336
    //   56: invokevirtual 320	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   59: ifeq +29 -> 88
    //   62: aload_1
    //   63: ldc_w 338
    //   66: invokevirtual 332	org/json/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   69: invokestatic 341	fm/qingting/qtradio/wo/WoNetEventListener:hasOpenUniCom	(Ljava/lang/String;)Z
    //   72: ifeq +16 -> 88
    //   75: aload_0
    //   76: invokevirtual 182	fm/qingting/qtradio/view/moreContentView/UserInfoView:getContext	()Landroid/content/Context;
    //   79: invokestatic 344	fm/qingting/qtradio/wo/WoApiRequest:init	(Landroid/content/Context;)Z
    //   82: pop
    //   83: aload_0
    //   84: iconst_1
    //   85: invokespecial 191	fm/qingting/qtradio/view/moreContentView/UserInfoView:generateSectionList	(Z)V
    //   88: return
    //   89: astore_1
    //   90: aload_1
    //   91: invokevirtual 347	java/lang/Exception:printStackTrace	()V
    //   94: return
    //   95: astore_1
    //   96: aload_1
    //   97: invokevirtual 347	java/lang/Exception:printStackTrace	()V
    //   100: return
    //   101: astore_1
    //   102: return
    //   103: astore_1
    //   104: return
    //
    // Exception table:
    //   from	to	target	type
    //   29	37	89	java/lang/Exception
    //   46	88	95	java/lang/Exception
    //   21	29	101	java/lang/Exception
    //   0	5	103	java/lang/Exception
    //   9	17	103	java/lang/Exception
    //   37	42	103	java/lang/Exception
    //   90	94	103	java/lang/Exception
    //   96	100	103	java/lang/Exception
  }

  public boolean onIMEvent(String paramString, IMMessage paramIMMessage)
  {
    if (paramString.equalsIgnoreCase("RECV_SINGLE_MSG"))
    {
      if (paramIMMessage.chatType != 1)
        break label32;
      LatestMessages.putMessage(paramIMMessage.mFromGroupId, paramIMMessage);
    }
    while (true)
    {
      invalidateCertainView(4);
      return false;
      label32: paramString = InfoManager.getInstance().getUserProfile().getUserKey();
      if ((paramIMMessage.mToUserId != null) && (paramIMMessage.mToUserId.length() > 0) && (!paramString.equalsIgnoreCase(paramIMMessage.mToUserId)))
        LatestMessages.putMessage(paramIMMessage.mToUserId, paramIMMessage);
      else if ((paramIMMessage.mFromID != null) && (paramIMMessage.mFromID.length() > 0))
        LatestMessages.putMessage(paramIMMessage.mFromID, paramIMMessage);
    }
  }

  public boolean onIMListMsg(String paramString, List<IMMessage> paramList)
  {
    return false;
  }

  public void onInfoUpdated(int paramInt)
  {
    if (paramInt == 0)
      invalidateCertainView(0);
    while ((paramInt != 11) || (this.adapter.getItem(0).type != 2))
      return;
    this.adapter.getData().remove(0);
    this.adapter.notifyDataSetChanged();
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
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("viewTimeUpdated"))
      invalidateCertainView(0);
    while (!paramString.equalsIgnoreCase("RUIU"))
      return;
    this.mTitleView.update("setUser", InfoManager.getInstance().getUserProfile());
    LatestMessages.loadUnreadMsgs(false);
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void onTime(Clock paramClock)
  {
  }

  public void onTimeStart(Clock paramClock)
  {
    if ((paramClock.type == 2) && (ClockManager.getInstance().getTimerAvailable()))
      invalidateCertainView(5);
  }

  public void onTimeStop(Clock paramClock)
  {
  }

  public void onTimerRemoved()
  {
    invalidateCertainView(5);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      initData();
    while (!paramString.equalsIgnoreCase("refreshView"))
      return;
    invalidateVisibleChildren();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.UserInfoView
 * JD-Core Version:    0.6.2
 */