package fm.qingting.qtradio.view.personalcenter.hiddenfeatures;

import android.annotation.TargetApi;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.os.StatFs;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.downloadnew.DownloadUtils;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.net.HTTPConnection;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.SwitcherElement;
import fm.qingting.framework.view.SwitcherElement.OnSwitchChangeListener;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.view.MainView;
import fm.qingting.qtradio.view.settingviews.SettingConfig;
import java.util.List;
import java.util.Locale;

public class HiddenFeaturesItemView extends QtView
{
  private final ViewLayout arrowLayout = this.itemLayout.createChildLT(100, 50, 580, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mArrow;
  private ButtonViewElement mBg;
  private TextViewElement mInfo;
  private HiddenFeaturesItem mItem;
  private TextViewElement mName;
  private SwitcherElement mSwitcher;
  private final ViewLayout mainTabLayout = this.itemLayout.createChildLT(720, 104, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(520, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subInfoLayout = this.itemLayout.createChildLT(520, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout switcherBgLayout = this.itemLayout.createChildLT(96, 58, 585, 26, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout switcherIconLayout = this.switcherBgLayout.createChildLT(60, 58, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public HiddenFeaturesItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg, paramInt);
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
    this.mArrow = new ButtonViewElement(paramContext);
    this.mArrow.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getPopBgColor());
    this.mArrow.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getTextColorNormal());
    this.mArrow.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    addElement(this.mArrow, paramInt);
    this.mArrow.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (HiddenFeaturesItemView.this.mItem.getId().equalsIgnoreCase("launch"))
          ControllerManager.getInstance().openLauncherController();
        do
        {
          do
          {
            return;
            if (HiddenFeaturesItemView.this.mItem.getId().equalsIgnoreCase("copy"))
            {
              HiddenFeaturesItemView.this.copy(HiddenFeaturesItemView.this.mItem.getName());
              return;
            }
          }
          while (!HiddenFeaturesItemView.this.mItem.getId().equalsIgnoreCase("sd"));
          paramAnonymousViewElement = SettingConfig.getStorageList();
        }
        while ((paramAnonymousViewElement == null) || (paramAnonymousViewElement.size() <= 0));
        StringBuilder localStringBuilder = new StringBuilder(String.format(Locale.CHINESE, "共检测到%d个存储路径", new Object[] { Integer.valueOf(paramAnonymousViewElement.size()) }));
        localStringBuilder.append("\n");
        int i = 0;
        while (i < paramAnonymousViewElement.size())
        {
          localStringBuilder.append((String)paramAnonymousViewElement.get(i));
          localStringBuilder.append("\n");
          i += 1;
        }
        if (paramAnonymousViewElement.size() > 1)
        {
          localStringBuilder.append("获取存储空间大小信息\n方式1:");
          localStringBuilder.append(DownloadUtils.getAvailableExternalMemorySize((String)paramAnonymousViewElement.get(1)));
          if (QtApiLevelManager.isApiLevelSupported(18))
          {
            localStringBuilder.append("\n方式2:");
            localStringBuilder.append(HiddenFeaturesItemView.this.getSize((String)paramAnonymousViewElement.get(1)));
          }
        }
        EventDispacthManager.getInstance().dispatchAction("showError", localStringBuilder.toString());
      }
    });
    this.mSwitcher = new SwitcherElement(paramContext);
    this.mSwitcher.setBgRes(2130837981, 2130837980);
    this.mSwitcher.setIconRes(2130837982);
    addElement(this.mSwitcher, paramInt);
    this.mSwitcher.setSwitchChangeListener(new SwitcherElement.OnSwitchChangeListener()
    {
      public void onChanged(boolean paramAnonymousBoolean)
      {
        TextViewElement localTextViewElement;
        if ((HiddenFeaturesItemView.this.mItem != null) && (HiddenFeaturesItemView.this.mItem.getType() == HiddenFeaturesItem.HiddenFeaturesType.SWITCHER))
        {
          if (!HiddenFeaturesItemView.this.mItem.getId().equalsIgnoreCase("debug"))
            break label77;
          SharedCfg.getInstance().switchDevMode(paramAnonymousBoolean);
          HTTPConnection.setDebugMode(paramAnonymousBoolean);
          localTextViewElement = HiddenFeaturesItemView.this.mName;
          if (!paramAnonymousBoolean)
            break label109;
        }
        label77: label109: for (int i = SkinManager.getTextColorNormal(); ; i = SkinManager.getTextColorSubInfo())
        {
          localTextViewElement.setColor(i);
          return;
          if (!HiddenFeaturesItemView.this.mItem.getId().equalsIgnoreCase("doublebackquit"))
            break;
          MainView.sDoubleBackQuit = paramAnonymousBoolean;
          SharedCfg.getInstance().setDoubleBackToQuit(paramAnonymousBoolean);
          break;
        }
      }
    });
    this.mSwitcher.setVisible(4);
  }

  @TargetApi(11)
  private void copy(String paramString)
  {
    String str;
    if (paramString.startsWith("渠道:"))
      str = paramString.substring("渠道:".length(), paramString.length());
    while (QtApiLevelManager.isApiLevelSupported(11))
    {
      ((ClipboardManager)getContext().getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str));
      return;
      str = paramString;
      if (paramString.startsWith("ID:"))
        str = paramString.substring("ID:".length(), paramString.length());
    }
    ((ClipboardManager)getContext().getSystemService("clipboard")).setText(str);
  }

  @TargetApi(18)
  private String getSize(String paramString)
  {
    String str = "";
    try
    {
      paramString = new StatFs(paramString);
      long l1 = paramString.getBlockSizeLong();
      long l2 = paramString.getAvailableBlocksLong();
      paramString = String.valueOf(l2 * l1);
      return paramString;
    }
    catch (Exception localException)
    {
      do
        paramString = str;
      while (localException == null);
      return localException.getMessage();
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if ((this.mItem != null) && (this.mItem.getSubInfo() != null) && (!this.mItem.getSubInfo().equalsIgnoreCase("")))
      this.mName.setTranslationY(0);
    while (true)
    {
      super.onDraw(paramCanvas);
      SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
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
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mItem = ((HiddenFeaturesItem)paramObject);
      this.mName.setText(this.mItem.getName(), true);
      if ((this.mItem.getSubInfo() == null) || (this.mItem.getSubInfo().equalsIgnoreCase("")))
        break label149;
      this.mInfo.setText(this.mItem.getSubInfo());
      this.mInfo.setVisible(0);
      switch (3.$SwitchMap$fm$qingting$qtradio$view$personalcenter$hiddenfeatures$HiddenFeaturesItem$HiddenFeaturesType[this.mItem.getType().ordinal()])
      {
      default:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      this.mName.setColor(SkinManager.getTextColorNormal());
      this.mArrow.setVisible(0);
      this.mSwitcher.setVisible(4);
      label149: 
      do
      {
        return;
        this.mInfo.setVisible(4);
        break;
        this.mArrow.setVisible(4);
        this.mSwitcher.setVisible(0);
        if (this.mItem.getId().equalsIgnoreCase("debug"))
        {
          if (SharedCfg.getInstance().getDevMode())
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
      while (!this.mItem.getId().equalsIgnoreCase("doublebackquit"));
      if (MainView.sDoubleBackQuit)
      {
        this.mName.setColor(SkinManager.getTextColorNormal());
        this.mSwitcher.switchOn(false);
        return;
      }
      this.mName.setColor(SkinManager.getTextColorSubInfo());
      this.mSwitcher.switchOff(false);
      return;
      this.mArrow.setVisible(0);
      this.mSwitcher.setVisible(4);
      this.mArrow.setText(this.mItem.getButtonText());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.hiddenfeatures.HiddenFeaturesItemView
 * JD-Core Version:    0.6.2
 */