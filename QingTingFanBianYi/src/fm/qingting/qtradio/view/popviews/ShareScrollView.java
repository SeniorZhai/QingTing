package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import fm.qingting.framework.view.HorizontalScrollViewImpl;

class ShareScrollView extends HorizontalScrollViewImpl
{
  private ShareActionContainerView mContainerView;

  public ShareScrollView(Context paramContext)
  {
    super(paramContext);
    setHorizontalScrollBarEnabled(false);
    setHorizontalFadingEdgeEnabled(false);
    this.mContainerView = new ShareActionContainerView(paramContext);
    addView(this.mContainerView);
  }

  public void update(String paramString, Object paramObject)
  {
    this.mContainerView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ShareScrollView
 * JD-Core Version:    0.6.2
 */