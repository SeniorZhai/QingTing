package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.ShareObjectNode;
import fm.qingting.utils.ScreenConfiguration;

class ShareActionContainerView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final int[] PLATFORM_ICONS = { 2130837973, 2130837978, 2130837976, 2130837974, 2130837975, 2130837977 };
  private final String[] PLATFORM_NAMES = { "朋友圈", "微信好友", "新浪微博", "QQ好友", "QQ空间", "腾讯微博" };
  private final ViewLayout containerLayout = this.standardLayout.createChildLT(204, 220, 22, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = this.containerLayout.createChildLT(136, 220, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private PopActionButtonElement[] mItems;
  private ShareObjectNode mShareObject;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ShareActionContainerView(Context paramContext)
  {
    super(paramContext);
    int j = hashCode();
    this.mItems = new PopActionButtonElement[6];
    while (i < this.mItems.length)
    {
      PopActionButtonElement localPopActionButtonElement = new PopActionButtonElement(paramContext);
      localPopActionButtonElement.setStyle(1);
      localPopActionButtonElement.setAction(this.PLATFORM_NAMES[i], this.PLATFORM_ICONS[i]);
      addElement(localPopActionButtonElement, j);
      this.mItems[i] = localPopActionButtonElement;
      localPopActionButtonElement.setOnElementClickListener(this);
      i += 1;
    }
  }

  private void getPastType(int paramInt)
  {
    int j = 0;
    int i = j;
    switch (paramInt)
    {
    default:
      i = j;
    case 1:
    case 0:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      this.mShareObject.type = i;
      return;
      i = 1;
      continue;
      i = 4;
      continue;
      i = 3;
      continue;
      i = 2;
      continue;
      i = 5;
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i = 0;
    while (true)
    {
      if (i < this.mItems.length)
      {
        if (paramViewElement != this.mItems[i])
          break label83;
        getPastType(i);
        if ((this.mShareObject.type == 4) || (this.mShareObject.type == 5))
          EventDispacthManager.getInstance().dispatchAction("shareToMessage", this.mShareObject);
      }
      else
      {
        return;
      }
      this.mShareObject.message = "";
      EventDispacthManager.getInstance().dispatchAction("shareToPlatform", this.mShareObject);
      return;
      label83: i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(ScreenConfiguration.getWidth(), View.MeasureSpec.getSize(paramInt2));
    this.containerLayout.scaleToBounds(this.standardLayout);
    this.itemLayout.scaleToBounds(this.containerLayout);
    paramInt2 = this.containerLayout.leftMargin;
    paramInt1 = 0;
    while (paramInt1 < this.mItems.length)
    {
      this.mItems[paramInt1].measure(this.itemLayout);
      this.mItems[paramInt1].setTranslationX(paramInt2);
      paramInt2 += this.containerLayout.width;
      paramInt1 += 1;
    }
    setMeasuredDimension(this.containerLayout.leftMargin + paramInt2, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramObject != null)
      this.mShareObject = ((ShareObjectNode)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ShareActionContainerView
 * JD-Core Version:    0.6.2
 */