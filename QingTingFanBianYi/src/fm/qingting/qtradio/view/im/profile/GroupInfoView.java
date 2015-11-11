package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;

public class GroupInfoView extends QtView
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 30, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(540, 45, 180, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 180, 720, 180, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private TextViewElement mInfoElement;
  private TextViewElement mNamElement;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(540, 45, 180, 40, ViewLayout.SCALE_FLAG_SLTCW);

  public GroupInfoView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837826);
    addElement(this.mAvatarElement, i);
    this.mNamElement = new TextViewElement(paramContext);
    this.mNamElement.setColor(SkinManager.getTextColorNormal());
    this.mNamElement.setMaxLineLimit(1);
    addElement(this.mNamElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setColor(SkinManager.getTextColorSubInfo());
    this.mInfoElement.setMaxLineLimit(1);
    addElement(this.mInfoElement);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNamElement.measure(this.nameLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mNamElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (GroupInfo)paramObject;
      this.mAvatarElement.setImageUrl(paramString.groupThumb);
      this.mNamElement.setText(paramString.groupName, false);
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.GroupInfoView
 * JD-Core Version:    0.6.2
 */