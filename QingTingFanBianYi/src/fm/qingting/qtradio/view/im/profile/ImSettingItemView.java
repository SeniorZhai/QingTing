package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.SwitcherElement;
import fm.qingting.framework.view.SwitcherElement.OnSwitchChangeListener;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.IMAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.settingviews.SettingItem;
import fm.qingting.qtradio.view.settingviews.SettingItem.SettingType;

public class ImSettingItemView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private TextViewElement mInfo;
  private SettingItem mItem;
  private TextViewElement mName;
  private SwitcherElement mSwitcher;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(450, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subInfoLayout = this.itemLayout.createChildLT(550, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout switcherBgLayout = this.itemLayout.createChildLT(96, 58, 585, 26, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout switcherIconLayout = this.switcherBgLayout.createChildLT(60, 58, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);

  public ImSettingItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    addElement(this.mBg, paramInt);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (ImSettingItemView.this.mItem != null)
          ImSettingItemView.this.handleClickEvent(ImSettingItemView.this.mItem.getId());
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
    this.mSwitcher = new SwitcherElement(paramContext);
    this.mSwitcher.setBgRes(2130837981, 2130837980);
    this.mSwitcher.setIconRes(2130837982);
    addElement(this.mSwitcher, paramInt);
    this.mSwitcher.setSwitchChangeListener(new SwitcherElement.OnSwitchChangeListener()
    {
      public void onChanged(boolean paramAnonymousBoolean)
      {
        TextViewElement localTextViewElement;
        if ((ImSettingItemView.this.mItem != null) && (ImSettingItemView.this.mItem.getType() == SettingItem.SettingType.switcher))
        {
          if (!ImSettingItemView.this.mItem.getId().equalsIgnoreCase("recvmsggroup"))
            break label93;
          if (!paramAnonymousBoolean)
            break label80;
          ImSettingItemView.this.dispatchActionEvent("enableGroup", null);
          localTextViewElement = ImSettingItemView.this.mName;
          if (!paramAnonymousBoolean)
            break label141;
        }
        label141: for (int i = SkinManager.getTextColorNormal(); ; i = SkinManager.getTextColorSubInfo())
        {
          localTextViewElement.setColor(i);
          return;
          label80: ImSettingItemView.this.dispatchActionEvent("disableGroup", null);
          break;
          label93: if (!ImSettingItemView.this.mItem.getId().equalsIgnoreCase("recvmsguser"))
            break;
          if (paramAnonymousBoolean)
          {
            ImSettingItemView.this.dispatchActionEvent("enableUser", null);
            break;
          }
          ImSettingItemView.this.dispatchActionEvent("disableUser", null);
          break;
        }
      }
    });
    this.mSwitcher.setVisible(4);
  }

  private void handleClickEvent(String paramString)
  {
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
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.switcherBgLayout.scaleToBounds(this.itemLayout);
    this.switcherIconLayout.scaleToBounds(this.switcherBgLayout);
    this.subInfoLayout.scaleToBounds(this.itemLayout);
    this.switcherBgLayout.topMargin = ((this.itemLayout.height - this.switcherBgLayout.height) / 2);
    this.switcherIconLayout.topMargin = ((this.itemLayout.height - this.switcherIconLayout.height) / 2);
    this.switcherIconLayout.leftMargin = this.switcherBgLayout.leftMargin;
    this.mBg.measure(this.itemLayout);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mName.measure(this.nameLayout);
    this.mInfo.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mInfo.measure(this.subInfoLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.topMargin, this.subInfoLayout.getRight(), this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.getBottom());
    this.mSwitcher.measure(this.switcherBgLayout);
    this.mSwitcher.setIconSize(this.switcherIconLayout.leftMargin, this.switcherIconLayout.topMargin, this.switcherIconLayout.getRight(), this.switcherIconLayout.getBottom());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mItem = ((SettingItem)paramObject);
      this.mName.setText(this.mItem.getName(), true);
      if ((this.mItem.getSubInfo() == null) || (this.mItem.getSubInfo().equalsIgnoreCase("")))
        break label132;
      this.mInfo.setText(this.mItem.getSubInfo());
      this.mInfo.setVisible(0);
      switch (3.$SwitchMap$fm$qingting$qtradio$view$settingviews$SettingItem$SettingType[this.mItem.getType().ordinal()])
      {
      default:
        this.mName.setColor(SkinManager.getTextColorNormal());
        this.mSwitcher.setVisible(4);
      case 1:
      }
    }
    label132: 
    do
    {
      do
      {
        return;
        this.mInfo.setVisible(4);
        break;
        this.mSwitcher.setVisible(0);
      }
      while (!this.mItem.getId().equalsIgnoreCase("recvmsggroup"));
      paramString = this.mItem.getData();
    }
    while (paramString == null);
    paramString = (String)paramString;
    if (IMAgent.getInstance().hasEnabledGroup(paramString))
    {
      this.mSwitcher.switchOn(false);
      return;
    }
    this.mSwitcher.switchOff(false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.ImSettingItemView
 * JD-Core Version:    0.6.2
 */