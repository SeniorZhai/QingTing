package fm.qingting.qtradio.view.settingviews;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import android.widget.Toast;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.SwitcherElement;
import fm.qingting.framework.view.SwitcherElement.OnSwitchChangeListener;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayCacheAgent;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.GlobalCfg;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.qtradio.wo.WoApiRequest;
import fm.qingting.qtradio.wo.WoNetEventListener;
import fm.qingting.utils.QTMSGManage;

public class SettingItemView extends QtView
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(36, 36, 650, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ImageViewElement mArrow;
  private ButtonViewElement mBg;
  private TextViewElement mInfo;
  private SettingItem mItem;
  private LineElement mLineElement;
  private TextViewElement mName;
  private SwitcherElement mSwitcher;
  private final ViewLayout mainTabLayout = this.itemLayout.createChildLT(720, 104, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(450, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subInfoLayout = this.itemLayout.createChildLT(550, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout switcherBgLayout = this.itemLayout.createChildLT(96, 58, 585, 26, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout switcherIconLayout = this.switcherBgLayout.createChildLT(60, 58, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public SettingItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg, paramInt);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (SettingItemView.this.mItem != null)
          SettingItemView.this.handleClickEvent(SettingItemView.this.mItem.getId());
      }
    });
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(1);
    this.mName.setColor(SkinManager.getTextColorNormal());
    this.mName.setAlignment(Layout.Alignment.ALIGN_NORMAL);
    addElement(this.mName);
    this.mInfo = new TextViewElement(paramContext);
    this.mInfo.setMaxLineLimit(1);
    this.mInfo.setColor(SkinManager.getTextColorSubInfo());
    this.mInfo.setAlignment(Layout.Alignment.ALIGN_NORMAL);
    addElement(this.mInfo);
    this.mArrow = new ImageViewElement(paramContext);
    this.mArrow.setImageRes(2130837697);
    addElement(this.mArrow, paramInt);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.mSwitcher = new SwitcherElement(paramContext);
    this.mSwitcher.setBgRes(2130837981, 2130837980);
    this.mSwitcher.setIconRes(2130837982);
    addElement(this.mSwitcher, paramInt);
    this.mSwitcher.setSwitchChangeListener(new SwitcherElement.OnSwitchChangeListener()
    {
      public void onChanged(boolean paramAnonymousBoolean)
      {
        Object localObject;
        if ((SettingItemView.this.mItem != null) && (SettingItemView.this.mItem.getType() == SettingItem.SettingType.switcher))
        {
          if (!SettingItemView.this.mItem.getId().equalsIgnoreCase("remember"))
            break label73;
          InfoManager.getInstance().setAutoSeek(paramAnonymousBoolean);
          localObject = SettingItemView.this.mName;
          if (!paramAnonymousBoolean)
            break label452;
        }
        label452: for (int i = SkinManager.getTextColorNormal(); ; i = SkinManager.getTextColorSubInfo())
        {
          ((TextViewElement)localObject).setColor(i);
          return;
          label73: if (SettingItemView.this.mItem.getId().equalsIgnoreCase("notify"))
          {
            InfoManager.getInstance().setPushSwitch(paramAnonymousBoolean);
            MobclickAgent.onEvent(SettingItemView.this.getContext(), "Switcher", "notify_" + String.valueOf(paramAnonymousBoolean));
            break;
          }
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("autoreserve"))
            break;
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("autoplay"))
          {
            InfoManager.getInstance().setAutoPlayAfterStart(paramAnonymousBoolean);
            MobclickAgent.onEvent(SettingItemView.this.getContext(), "Switcher", "autoplay_" + String.valueOf(paramAnonymousBoolean));
            break;
          }
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("mobile"))
          {
            SettingItemView.this.mInfo.setText(SettingItemView.this.mItem.getSubInfo());
            break;
          }
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("floaticon"))
          {
            MobclickAgent.onEvent(SettingItemView.this.getContext(), "Switcher", "floaticon_" + String.valueOf(paramAnonymousBoolean));
            GlobalCfg.getInstance(SettingItemView.this.getContext()).setFloatState(paramAnonymousBoolean);
            localObject = new Intent("fm.qingting.qtradio.action_float_toggle");
            ((Intent)localObject).putExtra("fm.qingting.qtradio.float_toggle", paramAnonymousBoolean);
            SettingItemView.this.getContext().sendBroadcast((Intent)localObject);
            break;
          }
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("globalpush"))
          {
            GlobalCfg.getInstance(SettingItemView.this.getContext()).setGlobalPush(paramAnonymousBoolean);
            break;
          }
          if (SettingItemView.this.mItem.getId().equalsIgnoreCase("aliaspush"))
          {
            GlobalCfg.getInstance(SettingItemView.this.getContext()).setAliasPush(paramAnonymousBoolean);
            break;
          }
          if (!SettingItemView.this.mItem.getId().equalsIgnoreCase("contentupdatepush"))
            break;
          InfoManager.getInstance().setPushSwitch(paramAnonymousBoolean);
          break;
        }
      }
    });
    this.mSwitcher.setVisible(4);
  }

  private void handleClickEvent(String paramString)
  {
    if (paramString.equalsIgnoreCase("tutorial"));
    do
    {
      return;
      if (paramString.equalsIgnoreCase("help"))
      {
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "help");
        EventDispacthManager.getInstance().dispatchAction("showFeedbackPop", "faq");
        return;
      }
      if (paramString.equalsIgnoreCase("aboutus"))
      {
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "aboutus");
        ControllerManager.getInstance().openAboutQtController();
        return;
      }
      if (paramString.equalsIgnoreCase("faq"))
      {
        ControllerManager.getInstance().openFaqController();
        return;
      }
      if (paramString.equalsIgnoreCase("audioquality"))
      {
        ControllerManager.getInstance().openAudioQualitySettingController();
        return;
      }
      if (paramString.equalsIgnoreCase("alarm"))
      {
        ControllerManager.getInstance().openAlarmControllerListOrAdd();
        return;
      }
      if (paramString.equalsIgnoreCase("pushmessage"))
      {
        ControllerManager.getInstance().openPushMessageController();
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("delcache"))
      {
        this.mItem.setSubInfo("已清空缓存");
        this.mInfo.setText("已清空缓存");
        PlayCacheAgent.getInstance().delCache();
        Toast.makeText(getContext(), "缓存已清空", 1).show();
        invalidate();
        return;
      }
      if (paramString.equalsIgnoreCase("timer"))
      {
        paramString = new Point();
        paramString.x = (this.itemLayout.width / 2);
        paramString.y = (getBottom() + this.mainTabLayout.height);
        EventDispacthManager.getInstance().dispatchAction("showSmallTimer", paramString);
        return;
      }
      if (paramString.equalsIgnoreCase("wo"))
      {
        paramString = getContext();
        if (paramString != null)
        {
          if (!WoNetEventListener.isChinaUnicom(paramString))
          {
            Toast.makeText(paramString, "抱歉，该服务当前只支持联通卡", 1).show();
            return;
          }
          if (WoApiRequest.hasInited())
          {
            ControllerManager.getInstance().openWoController();
            return;
          }
          Toast.makeText(paramString, "网络连接有问题或者正在初始化..", 1).show();
          return;
        }
        ControllerManager.getInstance().openWoController();
        return;
      }
      if (paramString.equalsIgnoreCase("selectdir"))
      {
        ControllerManager.getInstance().openDownloadDirSettingController();
        return;
      }
      if (paramString.equalsIgnoreCase("checkupgrade"))
      {
        OnlineUpdateHelper.getInstance().showUpgradeAlert();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("podcasterenroll"));
    ControllerManager.getInstance().redirectToActiviyByUrl("http://sss.qingting.fm/pugc/introduction/", "主播入驻", true);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if ((this.mItem != null) && (this.mItem.getSubInfo() != null) && (!this.mItem.getSubInfo().equalsIgnoreCase("")))
      this.mName.setTranslationY(0);
    while (true)
    {
      super.onDraw(paramCanvas);
      return;
      this.mName.setTranslationY((this.itemLayout.height - this.nameLayout.height) / 2 - this.nameLayout.topMargin);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.arrowLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.switcherBgLayout.scaleToBounds(this.itemLayout);
    this.switcherIconLayout.scaleToBounds(this.switcherBgLayout);
    this.subInfoLayout.scaleToBounds(this.itemLayout);
    this.mainTabLayout.scaleToBounds(this.itemLayout);
    this.switcherBgLayout.topMargin = ((this.itemLayout.height - this.switcherBgLayout.height) / 2);
    this.switcherIconLayout.topMargin = ((this.itemLayout.height - this.switcherIconLayout.height) / 2);
    this.switcherIconLayout.leftMargin = this.switcherBgLayout.leftMargin;
    this.arrowLayout.topMargin = ((this.itemLayout.height - this.arrowLayout.height) / 2);
    this.mBg.measure(this.itemLayout);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mName.measure(this.nameLayout);
    this.mInfo.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mInfo.measure(this.subInfoLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.topMargin, this.subInfoLayout.getRight(), this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.getBottom());
    this.mArrow.measure(this.arrowLayout);
    this.mSwitcher.measure(this.switcherBgLayout);
    this.mSwitcher.setIconSize(this.switcherIconLayout.leftMargin, this.switcherIconLayout.topMargin, this.switcherIconLayout.getRight(), this.switcherIconLayout.getBottom());
    this.mLineElement.measure(0, this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mItem = ((SettingItem)paramObject);
      this.mName.setText(this.mItem.getName(), true);
      if ((this.mItem.getSubInfo() == null) || (this.mItem.getSubInfo().equalsIgnoreCase("")))
        break label145;
      this.mInfo.setText(this.mItem.getSubInfo());
      this.mInfo.setVisible(0);
      switch (3.$SwitchMap$fm$qingting$qtradio$view$settingviews$SettingItem$SettingType[this.mItem.getType().ordinal()])
      {
      default:
        this.mName.setColor(SkinManager.getTextColorNormal());
        this.mArrow.setVisible(0);
        this.mSwitcher.setVisible(4);
      case 1:
      }
    }
    label145: 
    do
    {
      return;
      this.mInfo.setVisible(4);
      break;
      this.mArrow.setVisible(4);
      this.mSwitcher.setVisible(0);
      if (this.mItem.getId().equalsIgnoreCase("remember"))
      {
        if (InfoManager.getInstance().getAutoSeek())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("notify"))
      {
        if (InfoManager.getInstance().getPushSwitch())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("autoreserve"))
      {
        if (GlobalCfg.getInstance(getContext()).getAutoReserve())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("autoplay"))
      {
        if (InfoManager.getInstance().getAutoPlayAfterStart())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("mobile"))
      {
        if (InfoManager.getInstance().getEnableMobileNetwork())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("floaticon"))
      {
        if (GlobalCfg.getInstance(getContext()).getFloatState())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("globalpush"))
      {
        if (GlobalCfg.getInstance(getContext()).getGlobalPush())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
      if (this.mItem.getId().equalsIgnoreCase("aliaspush"))
      {
        if (GlobalCfg.getInstance(getContext()).getAliasPush())
        {
          this.mName.setColor(SkinManager.getTextColorNormal());
          this.mSwitcher.switchOn(false);
          return;
        }
        this.mName.setColor(SkinManager.getTextColorSubInfo());
        this.mSwitcher.switchOff(false);
        return;
      }
    }
    while (!this.mItem.getId().equalsIgnoreCase("contentupdatepush"));
    if (InfoManager.getInstance().getPushSwitch())
    {
      this.mName.setColor(SkinManager.getTextColorNormal());
      this.mSwitcher.switchOn(false);
      return;
    }
    this.mName.setColor(SkinManager.getTextColorSubInfo());
    this.mSwitcher.switchOff(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.SettingItemView
 * JD-Core Version:    0.6.2
 */