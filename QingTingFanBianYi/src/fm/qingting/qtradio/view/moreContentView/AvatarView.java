package fm.qingting.qtradio.view.moreContentView;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewLayout;

class AvatarView extends QtView
{
  private final ViewLayout avatarLayout = this.standardLayout.createChildLT(142, 142, 6, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private ImageViewElement mDefaultElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(154, 154, 154, 154, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public AvatarView(Context paramContext)
  {
    super(paramContext);
    ignoreSelfTouchEvent();
    this.mDefaultElement = new ImageViewElement(paramContext);
    this.mDefaultElement.setImageRes(2130838011);
    addElement(this.mDefaultElement);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    addElement(this.mAvatarElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.standardLayout);
    this.mDefaultElement.measure(this.standardLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  void setImageUrl(String paramString)
  {
    this.mAvatarElement.setImageUrl(paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.moreContentView.AvatarView
 * JD-Core Version:    0.6.2
 */