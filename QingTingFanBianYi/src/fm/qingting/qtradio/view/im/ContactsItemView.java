package fm.qingting.qtradio.view.im;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.im.sortpinyin.SortModel;
import fm.qingting.qtradio.view.playview.LineElement;

public class ContactsItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(90, 90, 30, 23, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout genderLayout = this.itemLayout.createChildLT(19, 18, 150, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(520, 45, 150, 70, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 136, 720, 136, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ButtonViewElement mBg;
  private UserInfo mDtc;
  private ImageViewElement mGender;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private TextViewElement mTitleElement;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(480, 45, 180, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public ContactsItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setMaxLineLimit(1);
    addElement(this.mTitleElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
    this.mGender = new ImageViewElement(paramContext);
    addElement(this.mGender, paramInt);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    ControllerManager.getInstance().openImUserProfileController(this.mDtc.userKey);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.genderLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height);
    this.mGender.measure(this.genderLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 2130837578;
    int i;
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mDtc = ((SortModel)paramObject).dtc;
      paramString = this.mDtc.snsInfo;
      this.mInfoElement.setText(paramString.signature, false);
      this.mTitleElement.setText(paramString.sns_name, false);
      this.mAvatarElement.setImageUrl(paramString.sns_avatar);
      paramString = paramString.sns_gender;
      i = j;
      if (paramString != null)
      {
        i = j;
        if (paramString.length() != 0)
        {
          if (!paramString.equalsIgnoreCase("n"))
            break label114;
          i = j;
        }
      }
    }
    while (true)
    {
      this.mGender.setImageRes(i);
      invalidate();
      return;
      label114: i = j;
      if (!paramString.equalsIgnoreCase("f"))
        i = 2130837588;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.ContactsItemView
 * JD-Core Version:    0.6.2
 */