package fm.qingting.qtradio.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.view.playview.PlayListView;

public class PlayListController extends ViewController
{
  private PlayListView mainView;

  public PlayListController(Context paramContext)
  {
    super(paramContext);
    setNavigationBarMode(INavigationSetting.Mode.FULLSCREEN);
    this.mainView = new PlayListView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBackground"))
      this.mainView.setBackgroundDrawable((Drawable)paramObject);
    while (!paramString.equalsIgnoreCase("setData"))
      return;
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    if (this.mainView != null)
      this.mainView.close(false);
    super.controllerDidPopped();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.PlayListController
 * JD-Core Version:    0.6.2
 */