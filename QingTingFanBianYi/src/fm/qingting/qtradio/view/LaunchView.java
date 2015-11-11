package fm.qingting.qtradio.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.NetImageViewElement.CLAMPTYPE;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.CrystalHelper;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.simpleImageloader.SimpleImageloader;
import fm.qingting.utils.QTMSGManage;

public class LaunchView extends QtView
  implements InfoManager.ISubscribeEventListener, ViewElement.OnElementClickListener
{
  private final int ANZHI = 0;
  private final int ANZHI_NORMAL = 13;
  private final int C360 = 1;
  private final int HUAWEI = 15;
  private final int LESHI = 16;
  private final int LIANXIANG = 6;
  private final int OPPO = 12;
  private final int SOUGOU = 10;
  private final int TAOBAO = 7;
  private final int TENGXUN = 2;
  private final int TYPE_AD = 4;
  private final int TYPE_FR = 2;
  private final int TYPE_NORMAL = 1;
  private final int UNDEF = 8;
  private final int UNION = 9;
  private final int VIVO = 14;
  private final int YDMM = 11;
  private ViewLayout channelLayout;
  private final ViewLayout copyrightLayout = this.standardLayout.createChildLT(1080, 75, 0, 60, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout logoLayout;
  private NetImageViewElement mAdvertisementElement;
  private long mBootTime = 0L;
  private ImageViewElement mChannelElement;
  private boolean mClosed = false;
  private ImageViewElement mLogoElement;
  private ImageViewElement mMask;
  private int mType = 1;
  private TextViewElement mVersionElement;
  private final ViewLayout maskLayout = this.standardLayout.createChildLT(1080, 164, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(1080, 1920, 1080, 1920, 0, 0, ViewLayout.FILL);

  public LaunchView(Context paramContext, boolean paramBoolean)
  {
    super(paramContext);
    int i = hashCode();
    setBackgroundColor(-1);
    this.mVersionElement = new TextViewElement(paramContext);
    this.mVersionElement.setMaxLineLimit(1);
    this.mVersionElement.setColor(-6250336);
    this.mVersionElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mVersionElement.setText(getResources().getString(2131492867), false);
    addElement(this.mVersionElement);
    Object localObject = null;
    if (paramBoolean)
      localObject = SharedCfg.getInstance().getLocalAdvertisementPic();
    initElementAndLayout(getChannel(), false, i);
    this.mAdvertisementElement = new NetImageViewElement(getContext());
    this.mAdvertisementElement.setClampType(NetImageViewElement.CLAMPTYPE.CLIPBOTH);
    this.mAdvertisementElement.setVisible(4);
    addElement(this.mAdvertisementElement, i);
    this.mMask = new ImageViewElement(paramContext);
    this.mMask.setImageRes(2130837812);
    addElement(this.mMask, i);
    this.mMask.setVisible(4);
    if ((!paramBoolean) || (localObject != null));
    try
    {
      long l;
      if (!((String)localObject).equalsIgnoreCase(""))
      {
        localObject = BitmapFactory.decodeFile((String)localObject);
        if (localObject != null)
        {
          this.mAdvertisementElement.setImageBitmap((Bitmap)localObject);
          this.mAdvertisementElement.setOnElementClickListener(this);
          MobclickAgent.onEvent(paramContext, "showAdvertisement");
          l = SystemClock.uptimeMillis();
          if (l - this.mBootTime < 2000L)
            new Handler().postDelayed(new Runnable()
            {
              public void run()
              {
                if (!LaunchView.this.mClosed)
                {
                  LaunchView.this.mAdvertisementElement.setVisible(0);
                  LaunchView.this.mMask.setVisible(0);
                }
              }
            }
            , this.mBootTime + 2000L - l);
        }
      }
      else if (InfoManager.getInstance().enableIClick())
      {
        l = SystemClock.uptimeMillis();
        if (l - this.mBootTime < 2000L)
        {
          new Handler().postDelayed(new Runnable()
          {
            public void run()
            {
              CrystalHelper.openSplashAd(LaunchView.this.getContext());
            }
          }
          , this.mBootTime + 2000L - l);
          return;
        }
        CrystalHelper.openSplashAd(getContext());
        return;
        SharedCfg.getInstance().setLocalAdvertisementPic("");
      }
      return;
    }
    catch (Exception paramContext)
    {
    }
  }

  private int getChannel()
  {
    String str1 = getResources().getString(2131492869);
    if (str1 == null)
      return 8;
    String str2 = getResources().getString(2131492870);
    if ((str2 != null) && (str2.equalsIgnoreCase("yes")));
    for (int i = 1; ; i = 0)
    {
      if (i != 0)
      {
        if ((str1.equalsIgnoreCase("baidu")) || (str1.equalsIgnoreCase("c91")) || (str1.equalsIgnoreCase("anzhuo")))
          return 9;
        if (str1.equalsIgnoreCase("anzhi"))
          return 0;
        if (str1.equalsIgnoreCase("c360"))
          return 1;
        if (str1.equalsIgnoreCase("tengxun"))
          return 2;
        if (str1.equalsIgnoreCase("lianxiang"))
          return 6;
        if (str1.equalsIgnoreCase("taobao"))
          return 7;
        if (str1.equalsIgnoreCase("sougoushichang"))
          return 10;
        if (str1.equalsIgnoreCase("yidongmm"))
          return 11;
        if (str1.equalsIgnoreCase("opponearme"))
          return 12;
        if (str1.equalsIgnoreCase("miyun"))
          return 14;
        if (str1.equalsIgnoreCase("huawei"))
          return 15;
        if (!str1.equalsIgnoreCase("95"))
          break;
        return 16;
      }
      if (!str1.equalsIgnoreCase("anzhi"))
        break;
      return 13;
    }
  }

  private void handleAdvertisementPic()
  {
    String str1 = InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdvertisement().image;
    if (str1 != null)
    {
      InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdvertisement().updateTracker();
      localObject = SharedCfg.getInstance().getNetAdvertisementPic();
      String str2 = SharedCfg.getInstance().getLocalAdvertisementPic();
      if ((localObject != null) && (((String)localObject).equalsIgnoreCase(str1)) && (str2 != null))
        InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdvertisement().onShow();
    }
    else
    {
      return;
    }
    SharedCfg.getInstance().setNetAdvertisementPic(str1);
    Object localObject = new SimpleImageloader();
    ((SimpleImageloader)localObject).ready(true);
    ((SimpleImageloader)localObject).setNetAdvertisementPic(str1);
    ((SimpleImageloader)localObject).execute(new Object[] { str1 });
    InfoManager.getInstance().root().mAdvertisementInfoNode.getSplashAdvertisement().onShow();
  }

  private void initElementAndLayout(int paramInt1, boolean paramBoolean, int paramInt2)
  {
    this.mType = 1;
    if (paramBoolean)
      this.mType |= 4;
    if (paramInt1 != 8)
      this.mType |= 2;
    this.mLogoElement = new ImageViewElement(getContext());
    ImageViewElement localImageViewElement = this.mLogoElement;
    int i;
    if (this.mType == 1)
    {
      i = 2130837810;
      localImageViewElement.setImageRes(i);
      addElement(this.mLogoElement, paramInt2);
      if (this.mType != 1)
        break label142;
      this.logoLayout = this.standardLayout.createChildLT(800, 192, 140, 0, ViewLayout.SCALE_FLAG_SLTCW);
    }
    while (true)
    {
      if ((this.mType & 0x4) != 4)
        break label213;
      initFrWithAd(paramInt1, paramInt2);
      return;
      i = 2130837811;
      break;
      label142: if ((this.mType & 0x4) == 4)
      {
        this.logoLayout = this.standardLayout.createChildLT(381, 228, 50, 60, ViewLayout.SCALE_FLAG_SLTCW);
        this.mVersionElement.setVisible(4);
      }
      else
      {
        this.logoLayout = this.standardLayout.createChildLT(381, 228, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      }
    }
    label213: initFrWithoutAd(paramInt1, paramInt2);
  }

  private void initFrWithAd(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 3:
    case 4:
    case 5:
    case 8:
    default:
      return;
    case 0:
      this.channelLayout = this.standardLayout.createChildLT(248, 248, 600, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837911);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 13:
      this.channelLayout = this.standardLayout.createChildLT(401, 180, 500, 16, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837912);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 1:
      this.channelLayout = this.standardLayout.createChildLT(339, 135, 620, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837910);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 2:
      this.channelLayout = this.standardLayout.createChildLT(412, 51, 560, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837924);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 6:
      this.channelLayout = this.standardLayout.createChildLT(540, 120, 480, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837915);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 7:
      this.channelLayout = this.standardLayout.createChildLT(450, 150, 550, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837919);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 9:
      this.channelLayout = this.standardLayout.createChildLT(338, 180, 660, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837920);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 10:
      this.channelLayout = this.standardLayout.createChildLT(380, 180, 600, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837918);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 11:
      this.channelLayout = this.standardLayout.createChildLT(288, 87, 680, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837923);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 12:
      this.channelLayout = this.standardLayout.createChildLT(300, 69, 660, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837917);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 14:
      this.channelLayout = this.standardLayout.createChildLT(241, 247, 660, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837921);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 15:
      this.channelLayout = this.standardLayout.createChildLT(512, 82, 500, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837914);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 16:
    }
    this.channelLayout = this.standardLayout.createChildLT(400, 154, 600, 40, ViewLayout.SCALE_FLAG_SLTCW);
    this.mChannelElement = new ImageViewElement(getContext());
    this.mChannelElement.setImageRes(2130837916);
    addElement(this.mChannelElement, paramInt2);
  }

  private void initFrWithoutAd(int paramInt1, int paramInt2)
  {
    switch (paramInt1)
    {
    case 3:
    case 4:
    case 5:
    case 8:
    default:
      return;
    case 0:
      this.channelLayout = this.standardLayout.createChildLT(350, 350, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837911);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 13:
      this.channelLayout = this.standardLayout.createChildLT(401, 180, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837912);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 1:
      this.channelLayout = this.standardLayout.createChildLT(339, 135, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837910);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 2:
      this.channelLayout = this.standardLayout.createChildLT(412, 51, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837924);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 6:
      this.channelLayout = this.standardLayout.createChildLT(540, 120, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837915);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 7:
      this.channelLayout = this.standardLayout.createChildLT(450, 150, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837919);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 9:
      this.channelLayout = this.standardLayout.createChildLT(338, 180, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837920);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 10:
      this.channelLayout = this.standardLayout.createChildLT(380, 180, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837918);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 11:
      this.channelLayout = this.standardLayout.createChildLT(288, 87, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837923);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 12:
      this.channelLayout = this.standardLayout.createChildLT(300, 69, 0, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837917);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 14:
      this.channelLayout = this.standardLayout.createChildLT(241, 247, 0, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837921);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 15:
      this.channelLayout = this.standardLayout.createChildLT(512, 82, 0, 50, ViewLayout.SCALE_FLAG_SLTCW);
      this.mChannelElement = new ImageViewElement(getContext());
      this.mChannelElement.setImageRes(2130837914);
      addElement(this.mChannelElement, paramInt2);
      return;
    case 16:
    }
    this.channelLayout = this.standardLayout.createChildLT(400, 155, 0, 40, ViewLayout.SCALE_FLAG_SLTCW);
    this.mChannelElement = new ImageViewElement(getContext());
    this.mChannelElement.setImageRes(2130837916);
    addElement(this.mChannelElement, paramInt2);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mClosed = true;
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement != null);
    try
    {
      paramViewElement = SharedCfg.getInstance().getSplashLanding();
      if ((paramViewElement != null) && (!paramViewElement.equalsIgnoreCase("")))
      {
        if (paramViewElement.startsWith("http"))
        {
          ControllerManager.getInstance().redirectToActiviyByUrl(paramViewElement, "广告", true);
          QTMSGManage.getInstance().sendStatistcsMessage("adv", "frontclick");
          return;
        }
        int i = 0;
        if (paramViewElement.startsWith("/"))
          i = 1;
        paramViewElement = paramViewElement.split("/");
        if ((paramViewElement != null) && (paramViewElement.length > i + 3))
        {
          int j = Integer.valueOf(paramViewElement[i]).intValue();
          int k = Integer.valueOf(paramViewElement[(i + 1)]).intValue();
          int m = Integer.valueOf(paramViewElement[(i + 2)]).intValue();
          i = Integer.valueOf(paramViewElement[(i + 3)]).intValue();
          if (i == 1)
          {
            ControllerManager.getInstance().openChannelDetailController(j, k, m, i, null, true);
            return;
          }
          ControllerManager.getInstance().openPlayController(j, k, m, i, null, true);
        }
      }
      return;
    }
    catch (Exception paramViewElement)
    {
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.logoLayout.scaleToBounds(this.standardLayout);
    this.copyrightLayout.scaleToBounds(this.standardLayout);
    this.maskLayout.scaleToBounds(this.standardLayout);
    if ((this.channelLayout != null) && (this.mChannelElement != null))
    {
      this.channelLayout.scaleToBounds(this.standardLayout);
      if ((this.mType & 0x4) == 4)
        this.mChannelElement.measure(this.channelLayout.leftMargin, this.standardLayout.height - this.logoLayout.topMargin - (this.logoLayout.height + this.channelLayout.height) / 2, this.channelLayout.getRight(), this.standardLayout.height - this.logoLayout.topMargin - (this.logoLayout.height - this.channelLayout.height) / 2);
    }
    else
    {
      this.mAdvertisementElement.measure(0, 0, this.standardLayout.width, this.standardLayout.height);
      this.mVersionElement.measure(0, this.standardLayout.height - this.copyrightLayout.getBottom(), this.standardLayout.width - this.copyrightLayout.leftMargin, this.standardLayout.height - this.copyrightLayout.getTop());
      this.mVersionElement.setTextSize(this.copyrightLayout.height * 0.5F);
      if (this.mType != 1)
        break label506;
      this.mLogoElement.measure((this.standardLayout.width - this.logoLayout.width) / 2, this.standardLayout.height - this.copyrightLayout.getBottom() - this.logoLayout.getBottom(), (this.standardLayout.width + this.logoLayout.width) / 2, this.standardLayout.height - this.copyrightLayout.getBottom() - this.logoLayout.topMargin);
    }
    while (true)
    {
      this.mMask.measure(0, this.standardLayout.height - this.maskLayout.height, this.standardLayout.width, this.standardLayout.height);
      setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
      return;
      this.mChannelElement.measure((this.standardLayout.width - this.channelLayout.width) / 2, this.standardLayout.height - this.copyrightLayout.getBottom() - this.channelLayout.getBottom(), (this.standardLayout.width + this.channelLayout.width) / 2, this.standardLayout.height - this.copyrightLayout.getBottom() - this.channelLayout.topMargin);
      break;
      label506: if ((this.mType & 0x4) == 4)
        this.mLogoElement.measure(this.logoLayout.leftMargin, this.standardLayout.height - this.logoLayout.getBottom(), this.logoLayout.getRight(), this.standardLayout.height - this.logoLayout.topMargin);
      else
        this.mLogoElement.measure((this.standardLayout.width - this.logoLayout.width) / 2, (this.standardLayout.height - this.copyrightLayout.getBottom() - this.channelLayout.getBottom() - this.logoLayout.height) / 2, (this.standardLayout.width + this.logoLayout.width) / 2, (this.standardLayout.height - this.copyrightLayout.getBottom() - this.channelLayout.getBottom() + this.logoLayout.height) / 2);
    }
  }

  public void onNotification(String paramString)
  {
    if (paramString == null);
    while (!paramString.equalsIgnoreCase("RADI"))
      return;
    handleAdvertisementPic();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.LaunchView
 * JD-Core Version:    0.6.2
 */