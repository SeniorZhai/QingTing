package fm.qingting.qtradio.view.settingviews;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.AbsCheckBoxElement.OnCheckChangeListener;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.AUDIO_QUALITY_MODE;

public class SingleCheckSettingItemView extends QtView
{
  private final ViewLayout checkLayout = this.itemLayout.createChildLT(48, 48, 622, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private SingleCheckBoxElement mCheck;
  private TextViewElement mInfo;
  private SettingItem mItem;
  private TextViewElement mName;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 45, 30, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout subInfoLayout = this.itemLayout.createChildLT(600, 45, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);

  public SingleCheckSettingItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg, paramInt);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (SingleCheckSettingItemView.this.mItem != null)
          SingleCheckSettingItemView.this.handleClickEvent(SingleCheckSettingItemView.this.mItem.getId());
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
    this.mCheck = new SingleCheckBoxElement(paramContext);
    addElement(this.mCheck, paramInt);
    this.mCheck.setEnabled(false);
    this.mCheck.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (SingleCheckSettingItemView.this.mItem != null)
          SingleCheckSettingItemView.this.handleClickEvent(SingleCheckSettingItemView.this.mItem.getId());
      }
    });
  }

  private void handleClickEvent(String paramString)
  {
    if (this.mCheck.isChecked())
      return;
    if (paramString.equalsIgnoreCase("auto"))
      InfoManager.getInstance().setAudioQualitySetting(InfoManager.AUDIO_QUALITY_MODE.SMART);
    while (true)
    {
      dispatchActionEvent("check", null);
      return;
      if (paramString.equalsIgnoreCase("fluent"))
        InfoManager.getInstance().setAudioQualitySetting(InfoManager.AUDIO_QUALITY_MODE.LOW_QUALITY);
      else if (paramString.equalsIgnoreCase("highquality"))
        InfoManager.getInstance().setAudioQualitySetting(InfoManager.AUDIO_QUALITY_MODE.HIGH_QUALITY);
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
    this.checkLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.subInfoLayout.scaleToBounds(this.itemLayout);
    this.checkLayout.topMargin = ((this.itemLayout.height - this.checkLayout.height) / 2);
    this.mBg.measure(this.itemLayout);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mName.measure(this.nameLayout);
    this.mInfo.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mInfo.measure(this.subInfoLayout.leftMargin, this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.topMargin, this.subInfoLayout.getRight(), this.nameLayout.topMargin + this.nameLayout.height + this.subInfoLayout.getBottom());
    this.mCheck.measure(this.checkLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mItem = ((SettingItem)paramObject);
      this.mName.setText(this.mItem.getName(), true);
      if ((this.mItem.getSubInfo() != null) && (!this.mItem.getSubInfo().equalsIgnoreCase("")))
      {
        this.mInfo.setText(this.mItem.getSubInfo());
        this.mInfo.setVisible(0);
      }
    }
    while (!paramString.equalsIgnoreCase("checkState"))
    {
      return;
      this.mInfo.setVisible(4);
      return;
    }
    if (((Boolean)paramObject).booleanValue())
      this.mCheck.check(false);
    while (true)
    {
      invalidate();
      return;
      this.mCheck.uncheck(false);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.SingleCheckSettingItemView
 * JD-Core Version:    0.6.2
 */