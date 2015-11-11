package fm.qingting.qtradio.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.view.playview.TraScheduleView;

public class TraScheduleController extends ViewController
{
  private TraScheduleView mainView;

  public TraScheduleController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "traschedule";
    setNavigationBarMode(INavigationSetting.Mode.FULLSCREEN);
    this.mainView = new TraScheduleView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBackground"))
      this.mainView.setBackgroundDrawable((Drawable)paramObject);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("setData"))
      {
        this.mainView.update(paramString, paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("initState"));
    this.mainView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.TraScheduleController
 * JD-Core Version:    0.6.2
 */