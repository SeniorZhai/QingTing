package fm.qingting.qtradio.view.podcaster;

import android.content.Context;
import android.text.Layout.Alignment;
import android.util.Log;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.FansUtils;

public class PodcasterInfoPage1 extends QtView
{
  private final ViewLayout avaLayout = this.standardLayout.createChildLT(120, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout decoLineLayout = this.standardLayout.createChildLT(80, 1, 8, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout fansLayout = this.standardLayout.createChildLT(720, 28, 10, 186, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout genderLayout = this.standardLayout.createChildLT(20, 20, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvaImg;
  private TextViewElement mFansText;
  private ImageViewElement mGenderImg;
  private LineElement mLeftLine;
  private TextViewElement mNameText;
  private UserInfo mPodcasterInfo;
  private LineElement mRightLine;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(720, 40, 0, 146, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 256, 720, 256, 0, 0, ViewLayout.FILL);

  public PodcasterInfoPage1(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mAvaImg = new RoundAvatarElement(paramContext);
    this.mAvaImg.setDefaultImageRes(2130837885);
    addElement(this.mAvaImg, paramInt);
    this.mNameText = new TextViewElement(paramContext);
    this.mNameText.setColor(SkinManager.getTextColorWhite());
    this.mNameText.setMaxLineLimit(1);
    this.mNameText.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mNameText);
    this.mGenderImg = new ImageViewElement(paramContext);
    addElement(this.mGenderImg);
    this.mFansText = new TextViewElement(paramContext);
    this.mFansText.setMaxLineLimit(1);
    this.mFansText.setColor(SkinManager.getTextColorWhite());
    this.mFansText.setAlignment(Layout.Alignment.ALIGN_CENTER);
    addElement(this.mFansText);
    this.mLeftLine = new LineElement(paramContext);
    this.mLeftLine.setColor(-1);
    addElement(this.mLeftLine);
    this.mRightLine = new LineElement(paramContext);
    this.mRightLine.setColor(-1);
    addElement(this.mRightLine);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.avaLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.fansLayout.scaleToBounds(this.standardLayout);
    this.decoLineLayout.scaleToBounds(this.standardLayout);
    this.genderLayout.scaleToBounds(this.standardLayout);
    this.mNameText.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mFansText.setTextSize(SkinManager.getInstance().getTinyTextSize());
    int i = (this.standardLayout.width - this.avaLayout.width) / 2;
    this.mAvaImg.measure(i, this.avaLayout.getTop(), this.avaLayout.width + i, this.avaLayout.getBottom());
    this.mNameText.measure(0, this.nameLayout.getTop(), this.nameLayout.getRight(), this.nameLayout.getBottom());
    int j = this.mNameText.getWidth();
    i = this.nameLayout.getTop() + (this.nameLayout.height - this.genderLayout.height) / 2;
    j = (j + this.standardLayout.width) / 2 + this.genderLayout.getLeft();
    this.mGenderImg.measure(j, i, this.genderLayout.width + j, this.genderLayout.height + i);
    this.mFansText.measure(0, this.fansLayout.getTop(), this.fansLayout.getRight(), this.fansLayout.getBottom());
    j = this.mFansText.getWidth();
    int k = (this.standardLayout.width - j) / 2;
    i = this.fansLayout.getTop() + (this.fansLayout.height - this.decoLineLayout.height) / 2;
    k = k - this.decoLineLayout.getLeft() - this.decoLineLayout.width;
    this.mLeftLine.measure(k, i, this.decoLineLayout.width + k, this.decoLineLayout.height + i);
    j = (j + this.standardLayout.width) / 2 + this.decoLineLayout.getLeft();
    this.mRightLine.measure(j, i, this.decoLineLayout.width + j, this.decoLineLayout.height + i);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mPodcasterInfo = ((UserInfo)paramObject);
      if ((this.mPodcasterInfo != null) && (this.mPodcasterInfo.snsInfo != null))
      {
        this.mAvaImg.setImageUrl(this.mPodcasterInfo.snsInfo.sns_avatar);
        this.mNameText.setText(this.mPodcasterInfo.podcasterName);
        this.mFansText.setText(FansUtils.toString(this.mPodcasterInfo.fansNumber));
        if (!this.mPodcasterInfo.snsInfo.sns_gender.equalsIgnoreCase("m"))
          break label158;
        this.mGenderImg.setVisible(0);
        this.mGenderImg.setImageRes(2130837891);
      }
    }
    while (true)
    {
      this.mFansText.setVisible(4);
      this.mLeftLine.setVisible(4);
      this.mRightLine.setVisible(4);
      requestLayout();
      Log.d("PodcasterInfoHeaderView", "setData");
      return;
      label158: if (this.mPodcasterInfo.snsInfo.sns_gender.equalsIgnoreCase("f"))
      {
        this.mGenderImg.setVisible(0);
        this.mGenderImg.setImageRes(2130837890);
      }
      else
      {
        this.mGenderImg.setVisible(4);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.podcaster.PodcasterInfoPage1
 * JD-Core Version:    0.6.2
 */