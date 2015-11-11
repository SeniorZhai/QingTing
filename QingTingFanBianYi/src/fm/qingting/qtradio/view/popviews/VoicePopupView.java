package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;

public class VoicePopupView extends ViewGroupViewImpl
{
  private final ViewLayout frameLayout = this.standardLayout.createChildLT(510, 658, 105, 271, ViewLayout.SCALE_FLAG_SLTCW);
  private FrameView mFrameView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public VoicePopupView(Context paramContext)
  {
    super(paramContext);
    this.mFrameView = new FrameView(paramContext);
    addView(this.mFrameView);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.frameLayout.layoutView(this.mFrameView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.frameLayout.scaleToBounds(this.standardLayout);
    this.frameLayout.measureView(this.mFrameView);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void startSpeech()
  {
    this.mFrameView.startSpeech();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.VoicePopupView
 * JD-Core Version:    0.6.2
 */