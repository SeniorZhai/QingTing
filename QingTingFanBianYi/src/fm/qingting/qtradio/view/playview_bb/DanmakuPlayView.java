package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.BlurManager;
import fm.qingting.qtradio.manager.BlurManager.IImageBluredListener;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RadioChannelNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.AccurateSeekView;
import fm.qingting.utils.ScreenConfiguration;

public class DanmakuPlayView extends ViewGroupViewImpl
  implements BlurManager.IImageBluredListener, IEventHandler
{
  private static final int DISMISS_LENGTH = 2000;
  private final int ACTIONHEIGHT;
  private final int BUTTONHEIGHT;
  private final int PROCESSHEIGHT;
  private final String TAG = "play";
  private final ViewLayout barrageLayout;
  private int fuckingHeight;
  private AccurateSeekView mAccurateSeekView;
  private DanmakuPlayActionsView mActionsView;
  private String mBackgroundUrl;
  private BBDanmakuView mBarrageView;
  private DanmakuPlayButtonsView mButtonsView;
  private Handler mDelayHandler;
  private Runnable mDelayRunnable;
  private String mHasLoadAdvUrl;
  private boolean mHasSetAdvThumb;
  private DanmakuPlayNaviView mNaviView;
  private DanmakuSeekBarView mSeekBarView;
  private DanmakuPlayTextView mTextView;
  private final ViewLayout naviLayout;
  private final ViewLayout playButtonsLayout;
  private final ViewLayout playTextLayout;
  private final ViewLayout processLayout;
  private final ViewLayout standardLayout;

  public DanmakuPlayView(Context paramContext)
  {
    super(paramContext);
    if (ScreenConfiguration.isWideScreen());
    this.PROCESSHEIGHT = 105;
    if (ScreenConfiguration.isWideScreen())
    {
      i = 100;
      this.ACTIONHEIGHT = i;
      if (!ScreenConfiguration.isWideScreen())
        break label403;
    }
    label403: for (int i = 160; ; i = 180)
    {
      this.BUTTONHEIGHT = i;
      this.standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
      this.barrageLayout = this.standardLayout.createChildLT(720, 574, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.processLayout = this.standardLayout.createChildLT(720, this.PROCESSHEIGHT, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
      this.naviLayout = this.standardLayout.createChildLT(720, 98, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.playButtonsLayout = this.standardLayout.createChildLT(720, this.BUTTONHEIGHT, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.playTextLayout = this.standardLayout.createChildLT(720, 98, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.fuckingHeight = 0;
      this.mHasSetAdvThumb = false;
      this.mHasLoadAdvUrl = null;
      this.mDelayHandler = new Handler();
      this.mDelayRunnable = new Runnable()
      {
        public void run()
        {
          DanmakuPlayView.this.hideAccurateSeek();
        }
      };
      BlurManager.getInstance().addListener(this);
      setBackgroundColor(-1);
      this.mNaviView = new DanmakuPlayNaviView(paramContext);
      addView(this.mNaviView);
      this.mNaviView.setEventHandler(this);
      this.mBarrageView = new BBDanmakuView(paramContext);
      addView(this.mBarrageView);
      this.mActionsView = new DanmakuPlayActionsView(paramContext);
      addView(this.mActionsView);
      this.mActionsView.setEventHandler(this);
      this.mButtonsView = new DanmakuPlayButtonsView(paramContext);
      addView(this.mButtonsView);
      this.mButtonsView.setEventHandler(this);
      this.mSeekBarView = new DanmakuSeekBarView(paramContext);
      addView(this.mSeekBarView);
      this.mSeekBarView.setEventHandler(this);
      this.mTextView = new DanmakuPlayTextView(paramContext);
      addView(this.mTextView);
      return;
      i = 110;
      break;
    }
  }

  private void autoLoadAdvUrl()
  {
    if (InfoManager.getInstance().root().isPlayingAd())
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          ControllerManager.getInstance().redirectToActiviyByUrl(DanmakuPlayView.this.mHasLoadAdvUrl, null, true);
        }
      }
      , 2000L);
  }

  private void hideAccurateSeek()
  {
    if (this.mAccurateSeekView != null)
    {
      removeView(this.mAccurateSeekView);
      this.mAccurateSeekView.close(false);
      this.mAccurateSeekView = null;
    }
  }

  private void setAccurateInitStateIfExists()
  {
    if (this.mAccurateSeekView == null)
      return;
    this.mAccurateSeekView.update("leftTimeOffset", this.mSeekBarView.getValue("leftTimeOffset", null));
    this.mAccurateSeekView.update("rightTime", this.mSeekBarView.getValue("rightTime", null));
    this.mAccurateSeekView.update("progress", this.mSeekBarView.getValue("progress", null));
  }

  private void setBackgroundUsingBitmap(Bitmap paramBitmap)
  {
    if ((this.mBackgroundUrl == null) || (paramBitmap == null))
      return;
    try
    {
      Bitmap localBitmap = BlurManager.getInstance().getBluredBitmapInPlay(this.mBackgroundUrl + "play");
      if (localBitmap == null)
      {
        BlurManager.getInstance().blurBitmapInPlay(paramBitmap, -2013265920, new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight()), this.mBackgroundUrl + "play");
        return;
      }
      setProgramBackground(localBitmap);
      return;
    }
    catch (Error paramBitmap)
    {
    }
    catch (Exception paramBitmap)
    {
    }
  }

  private void setProgramBackground(Bitmap paramBitmap)
  {
  }

  private void setProgramBackground(String paramString)
  {
    this.mBackgroundUrl = paramString;
    int i = ScreenConfiguration.getWidth();
    int j = ScreenConfiguration.getHeight();
    Object localObject = ImageLoader.getInstance(getContext()).getImage(paramString, i, j);
    if (localObject == null)
    {
      localObject = new ImageLoaderHandler()
      {
        public void loadImageFinish(boolean paramAnonymousBoolean, String paramAnonymousString, Bitmap paramAnonymousBitmap, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          DanmakuPlayView.this.setBackgroundUsingBitmap(paramAnonymousBitmap);
        }

        public void updateImageViewFinish(boolean paramAnonymousBoolean, ImageView paramAnonymousImageView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
        }
      };
      ImageLoader.getInstance(getContext()).loadImage(paramString, null, this, (ImageLoaderHandler)localObject);
      return;
    }
    setBackgroundUsingBitmap((Bitmap)localObject);
  }

  private void showAccurateSeek()
  {
    if (this.mAccurateSeekView == null)
    {
      this.mAccurateSeekView = new AccurateSeekView(getContext());
      this.mAccurateSeekView.setEventHandler(this);
      addView(this.mAccurateSeekView);
    }
    setAccurateInitStateIfExists();
    this.mDelayHandler.removeCallbacks(this.mDelayRunnable);
  }

  private void showSchedule(int paramInt)
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localObject == null);
    while (!((Node)localObject).nodeName.equalsIgnoreCase("channel"))
      return;
    localObject = (ChannelNode)localObject;
    if (((ChannelNode)localObject).hasEmptyProgramSchedule())
    {
      Toast.makeText(getContext(), "节目单正在加载中", 0).show();
      return;
    }
    if (((ChannelNode)localObject).channelType == 0)
    {
      ControllerManager.getInstance().openTraScheduleController(getBackground(), (ChannelNode)localObject, paramInt);
      return;
    }
    localObject = InfoManager.getInstance().root().getCurrentPlayingNode();
    ControllerManager.getInstance().openChannelDetailControllerWithoutDamaku((Node)localObject);
  }

  public void close(boolean paramBoolean)
  {
    this.mNaviView.close(paramBoolean);
    this.mActionsView.close(paramBoolean);
    this.mButtonsView.close(paramBoolean);
    this.mSeekBarView.close(paramBoolean);
    this.mAccurateSeekView.close(paramBoolean);
    this.mTextView.close(paramBoolean);
  }

  protected void dispatchDraw(Canvas paramCanvas)
  {
    super.dispatchDraw(paramCanvas);
  }

  public Object getValue(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("progressPosition"))
    {
      int i = this.processLayout.height;
      int j = this.playButtonsLayout.height;
      int k = this.playTextLayout.height;
      paramString = (Point)this.mSeekBarView.getValue(paramString, paramObject);
      paramString.y = (k + (i + j) - paramString.y);
      return paramString;
    }
    return super.getValue(paramString, paramObject);
  }

  public void onBlurFinished(String paramString)
  {
    if (TextUtils.equals(paramString, this.mBackgroundUrl + "play"))
      setProgramBackground(this.mBackgroundUrl);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("progresschanged"))
    {
      if (this.mAccurateSeekView != null)
        this.mAccurateSeekView.update(paramString, paramObject2);
      if ((InfoManager.getInstance().root().isPlayingAd()) && ((!this.mHasSetAdvThumb) || (this.mHasLoadAdvUrl == null)))
      {
        paramObject1 = InfoManager.getInstance().root().mAdvertisementInfoNode.getCurrPlayingAdv();
        if ((paramObject1.image != null) && (!paramObject1.image.equalsIgnoreCase("")))
        {
          this.mHasSetAdvThumb = true;
          MobclickAgent.onEvent(getContext(), "PlayviewShowAdv", paramObject1.landing);
        }
      }
    }
    do
    {
      do
      {
        do
          return;
        while ((paramObject1.landing == null) || (paramObject1.landing.equalsIgnoreCase("")) || (this.mHasLoadAdvUrl != null));
        this.mHasLoadAdvUrl = paramObject1.landing;
        autoLoadAdvUrl();
        MobclickAgent.onEvent(getContext(), "PlayviewLoadAdv", paramObject1.landing);
        return;
      }
      while (InfoManager.getInstance().root().isPlayingAd());
      if (this.mHasSetAdvThumb)
        this.mHasSetAdvThumb = false;
      this.mHasLoadAdvUrl = null;
      return;
      if (paramString.equalsIgnoreCase("showSchedule"))
      {
        showSchedule(1);
        return;
      }
      if (paramString.equalsIgnoreCase("checkin"))
      {
        dispatchActionEvent(paramString, paramObject2);
        return;
      }
      if (paramString.equalsIgnoreCase("showAccurateSeek"))
      {
        showAccurateSeek();
        return;
      }
      if (paramString.equalsIgnoreCase("extendDismissLength"))
      {
        this.mDelayHandler.removeCallbacks(this.mDelayRunnable);
        this.mDelayHandler.postDelayed(this.mDelayRunnable, 2000L);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("hideAccurateSeek"));
    this.mDelayHandler.removeCallbacks(this.mDelayRunnable);
    this.mDelayHandler.postDelayed(this.mDelayRunnable, 2000L);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = this.naviLayout.height;
    this.mBarrageView.layout(0, paramInt1, this.barrageLayout.width, this.barrageLayout.height + paramInt1);
    paramInt1 += this.barrageLayout.height;
    paramInt2 = this.standardLayout.height - this.naviLayout.height - this.barrageLayout.height - this.playButtonsLayout.height - this.processLayout.height - this.playTextLayout.height;
    if (this.fuckingHeight < paramInt2)
      this.fuckingHeight = paramInt2;
    this.mActionsView.layout(0, paramInt1, this.standardLayout.width, this.fuckingHeight + paramInt1);
    paramInt1 += this.fuckingHeight;
    this.mSeekBarView.layout(0, paramInt1, this.processLayout.width, this.processLayout.height + paramInt1);
    paramInt1 += this.processLayout.height;
    this.mButtonsView.layout(0, paramInt1, this.playButtonsLayout.width, this.playButtonsLayout.height + paramInt1);
    paramInt1 = this.playButtonsLayout.height;
    this.mTextView.layout(0, this.standardLayout.height - this.playTextLayout.height, this.playTextLayout.width, this.standardLayout.height);
    this.naviLayout.layoutView(this.mNaviView);
    if (this.mAccurateSeekView != null)
      this.mAccurateSeekView.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.processLayout.height - this.playButtonsLayout.height - this.playTextLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.naviLayout.scaleToBounds(this.standardLayout);
    this.naviLayout.measureView(this.mNaviView);
    this.barrageLayout.scaleToBounds(this.standardLayout);
    this.processLayout.scaleToBounds(this.standardLayout);
    this.processLayout.measureView(this.mSeekBarView);
    this.barrageLayout.measureView(this.mBarrageView);
    this.playButtonsLayout.scaleToBounds(this.standardLayout);
    this.playButtonsLayout.measureView(this.mButtonsView);
    paramInt2 = this.standardLayout.height - this.naviLayout.height - this.barrageLayout.height - this.playButtonsLayout.height - this.processLayout.height - this.playTextLayout.height;
    this.mActionsView.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(paramInt2, 1073741824));
    if (this.mAccurateSeekView != null)
      this.mAccurateSeekView.measure(this.playButtonsLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.processLayout.height - this.playButtonsLayout.height - this.playTextLayout.height, 1073741824));
    this.playTextLayout.scaleToBounds(this.standardLayout);
    this.playTextLayout.measureView(this.mTextView);
    if (this.fuckingHeight > paramInt2)
      this.mTextView.isFocus(true);
    while (true)
    {
      setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
      return;
      if (this.fuckingHeight == paramInt2)
        this.mTextView.isFocus(false);
    }
  }

  public void setActivate(boolean paramBoolean)
  {
    if (paramBoolean)
      super.setActivate(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setProgramNode"))
    {
      paramString = (Node)paramObject;
      this.mHasSetAdvThumb = false;
      if (paramString.nodeName.equalsIgnoreCase("program"))
      {
        this.mSeekBarView.update("setNode", paramObject);
        this.mButtonsView.update("setNode", paramObject);
        setAccurateInitStateIfExists();
        paramString = (ProgramNode)paramString;
        if (!paramString.isDownloadProgram())
          break label169;
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        if ((paramString == null) || (!paramString.nodeName.equalsIgnoreCase("channel")))
          break label153;
        this.mNaviView.update("setData", paramString.title);
        this.mActionsView.update("hideFav", null);
        this.mNaviView.update("setSubData", paramObject);
        setProgramBackground(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837881));
      }
    }
    label153: label169: label228: 
    do
    {
      do
      {
        return;
        this.mNaviView.update("setData", "我的下载");
        break;
        if (paramString.channelType != 1)
          break label228;
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        this.mNaviView.update("setSubData", paramObject);
        this.mActionsView.update("showFav", null);
      }
      while (paramString == null);
      this.mNaviView.update("setData", paramString.title);
      return;
      paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
      if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("channel")))
      {
        paramString = (ChannelNode)paramString;
        this.mNaviView.update("setSubData", paramObject);
        this.mNaviView.update("setData", paramString.title);
        setProgramBackground(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837881));
      }
      while (true)
      {
        this.mActionsView.update("showFav", null);
        return;
        if ((paramString != null) && (paramString.nodeName.equalsIgnoreCase("radiochannel")))
        {
          paramString = (RadioChannelNode)paramString;
          this.mNaviView.update("setSubData", paramObject);
          this.mNaviView.update("setData", paramString.channelName);
          setProgramBackground(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, 2130837881));
        }
      }
      if (paramString.equalsIgnoreCase("showSchedule"))
      {
        showSchedule(0);
        return;
      }
      if (paramString.equalsIgnoreCase("setImageBarrage"))
      {
        this.mBarrageView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("setTxtBarrage"))
      {
        this.mBarrageView.update(paramString, paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("addDanmaku"))
      {
        this.mBarrageView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setSendBarrage"));
    this.mBarrageView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuPlayView
 * JD-Core Version:    0.6.2
 */