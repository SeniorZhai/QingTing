package fm.qingting.qtradio.view.podcaster;

import android.content.Context;
import android.text.Layout.Alignment;
import android.text.TextUtils;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;

public class PodcasterInfoPage2 extends QtView
{
  private UserInfo mPodcasterInfo;
  private TextViewElement mSignatureText;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 256, 720, 256, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.standardLayout.createChildLT(584, 256, 68, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public PodcasterInfoPage2(Context paramContext)
  {
    super(paramContext);
    this.mSignatureText = new TextViewElement(paramContext);
    this.mSignatureText.setColor(SkinManager.getTextColorWhite());
    this.mSignatureText.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mSignatureText.setMaxLineLimit(4);
    addElement(this.mSignatureText);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.textLayout.scaleToBounds(this.standardLayout);
    this.mSignatureText.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mSignatureText.measure(this.textLayout);
    int i = (paramInt2 - this.mSignatureText.getHeight()) / 2;
    this.mSignatureText.setTranslationY(i);
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mPodcasterInfo = ((UserInfo)paramObject);
      if ((this.mPodcasterInfo != null) && (this.mPodcasterInfo.snsInfo != null))
      {
        paramObject = this.mPodcasterInfo.snsInfo.signature;
        paramString = paramObject;
        if (TextUtils.isEmpty(paramObject))
          paramString = "暂无个性签名";
        this.mSignatureText.setText(paramString);
        requestLayout();
      }
    }
    else
    {
      return;
    }
    this.mSignatureText.setText("");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.podcaster.PodcasterInfoPage2
 * JD-Core Version:    0.6.2
 */