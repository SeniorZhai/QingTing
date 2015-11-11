package fm.qingting.qtradio.view.floaticon;

import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import fm.qingting.qtradio.model.BillboardItemNode;
import fm.qingting.qtradio.model.BillboardNode;
import java.util.List;

public enum FloatViewManager
{
  INSTANCE;

  private FloatWindowBigView bigWindow;
  private WindowManager.LayoutParams bigWindowParams;
  private BillboardNode mBillboardNode = new BillboardNode();
  private boolean mEnable = true;
  private float mOffset = 0.140625F;
  private boolean mV2 = judge();
  private WindowManager mWindowManager;
  private FloatWindowSmallView smallWindow;
  private WindowManager.LayoutParams smallWindowParams;

  private WindowManager getWindowManager(Context paramContext)
  {
    if (this.mWindowManager == null)
      this.mWindowManager = ((WindowManager)paramContext.getSystemService("window"));
    return this.mWindowManager;
  }

  private boolean judge()
  {
    return true;
  }

  public void createBigWindow(Context paramContext)
  {
    WindowManager localWindowManager = getWindowManager(paramContext);
    int i = localWindowManager.getDefaultDisplay().getWidth();
    int j = localWindowManager.getDefaultDisplay().getHeight();
    if (this.bigWindow == null)
    {
      this.bigWindow = new FloatWindowBigView(paramContext);
      if (this.bigWindowParams == null)
      {
        this.bigWindowParams = new WindowManager.LayoutParams();
        this.bigWindowParams.x = 0;
        this.bigWindowParams.y = 0;
        this.bigWindowParams.type = 2002;
        this.bigWindowParams.format = 1;
        this.bigWindowParams.gravity = 51;
        this.bigWindowParams.width = i;
        this.bigWindowParams.height = j;
      }
      localWindowManager.addView(this.bigWindow, this.bigWindowParams);
    }
  }

  public void createSmallWindow(Context paramContext)
  {
    float f2 = 70.0F;
    WindowManager localWindowManager = getWindowManager(paramContext);
    int i = localWindowManager.getDefaultDisplay().getWidth();
    int j = localWindowManager.getDefaultDisplay().getHeight();
    float f3 = i;
    if (this.mV2);
    for (float f1 = 128.0F; ; f1 = 70.0F)
    {
      int k = (int)(f1 * f3 / 720.0F);
      f3 = i;
      f1 = f2;
      if (this.mV2)
        f1 = 130.0F;
      int m = (int)(f1 * f3 / 720.0F);
      if (this.smallWindow == null)
      {
        this.smallWindow = new FloatWindowSmallView(paramContext);
        if (this.smallWindowParams == null)
        {
          this.smallWindowParams = new WindowManager.LayoutParams();
          this.smallWindowParams.type = 2002;
          this.smallWindowParams.format = 1;
          this.smallWindowParams.flags = 40;
          this.smallWindowParams.gravity = 51;
          this.smallWindowParams.width = k;
          this.smallWindowParams.height = m;
          this.smallWindowParams.x = i;
          this.smallWindowParams.y = (j / 2);
        }
        this.smallWindow.setParams(this.smallWindowParams);
        localWindowManager.addView(this.smallWindow, this.smallWindowParams);
      }
      return;
    }
  }

  public List<BillboardItemNode> getBillboardChannels()
  {
    if (this.mBillboardNode.restoreChannelFromDB())
      return this.mBillboardNode.getLstBillboardChannel();
    return null;
  }

  public float getOffset()
  {
    return this.mOffset;
  }

  public boolean isEnabled()
  {
    return this.mEnable;
  }

  public boolean isV2()
  {
    return this.mV2;
  }

  public boolean isWindowShowing()
  {
    return (this.smallWindow != null) || (this.bigWindow != null);
  }

  public void removeBigWindow(Context paramContext)
  {
    if (this.bigWindow != null)
    {
      getWindowManager(paramContext).removeView(this.bigWindow);
      this.bigWindow = null;
    }
  }

  public void removeSmallWindow(Context paramContext)
  {
    if (this.smallWindow != null)
    {
      getWindowManager(paramContext).removeView(this.smallWindow);
      this.smallWindow = null;
    }
  }

  public void setEnable(boolean paramBoolean)
  {
    this.mEnable = paramBoolean;
  }

  public void updateUsedPercent(Context paramContext)
  {
    if (this.smallWindow != null);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.floaticon.FloatViewManager
 * JD-Core Version:    0.6.2
 */