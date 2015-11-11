package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.playview.LineElement;

public class PersonalProfileView extends QtView
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 30, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout genderLayout = this.itemLayout.createChildLT(19, 18, 180, 53, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(540, 45, 180, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 180, 720, 180, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ImageViewElement mGender;
  private TextViewElement mInfoElement;
  private LineElement mLineElement;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(500, 45, 210, 40, ViewLayout.SCALE_FLAG_SLTCW);

  public PersonalProfileView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837701);
    addElement(this.mAvatarElement, i);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(SkinManager.getTextColorNormal());
    this.mNameElement.setMaxLineLimit(1);
    addElement(this.mNameElement);
    this.mGender = new ImageViewElement(paramContext);
    addElement(this.mGender, i);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.genderLayout.scaleToBounds(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mNameElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height);
    this.mGender.measure(this.genderLayout);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 2130837578;
    int i;
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = ((UserProfile)paramObject).getUserInfo();
      if (paramString != null)
      {
        this.mNameElement.setText(paramString.snsInfo.sns_name, false);
        this.mAvatarElement.setImageUrl(paramString.snsInfo.sns_avatar);
        paramString = paramString.snsInfo.sns_gender;
        i = j;
        if (paramString != null)
        {
          i = j;
          if (paramString.length() != 0)
          {
            if (!paramString.equalsIgnoreCase("n"))
              break label104;
            i = j;
          }
        }
      }
    }
    while (true)
    {
      this.mGender.setImageRes(i);
      invalidate();
      return;
      label104: i = j;
      if (!paramString.equalsIgnoreCase("f"))
        i = 2130837588;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.PersonalProfileView
 * JD-Core Version:    0.6.2
 */