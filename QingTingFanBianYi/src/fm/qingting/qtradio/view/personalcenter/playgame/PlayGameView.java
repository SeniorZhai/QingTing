package fm.qingting.qtradio.view.personalcenter.playgame;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.personalcenter.mydownload.EmptyTipsView;

public class PlayGameView extends ViewGroupViewImpl
{
  private EmptyTipsView mEmptyView;
  private PlayGameListView mListView;
  private MiniPlayerView mPlayerView;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public PlayGameView(Context paramContext)
  {
    super(paramContext);
    try
    {
      setBackgroundColor(SkinManager.getBackgroundColor());
      this.mEmptyView = new EmptyTipsView(paramContext, 14);
      addView(this.mEmptyView);
      this.mListView = new PlayGameListView(paramContext);
      addView(this.mListView);
      this.mListView.setEmptyView(this.mEmptyView);
      this.mPlayerView = new MiniPlayerView(paramContext);
      addView(this.mPlayerView);
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
        localOutOfMemoryError.printStackTrace();
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mListView.close(paramBoolean);
    this.mEmptyView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mEmptyView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.standardLayout.measureView(this.mPlayerView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    this.mEmptyView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      this.mListView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.playgame.PlayGameView
 * JD-Core Version:    0.6.2
 */