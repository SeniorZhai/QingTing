package fm.qingting.qtradio.controller;

import android.content.Context;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.view.INavigationSetting.Mode;
import fm.qingting.qtradio.view.search.SearchView;

public class SearchController extends ViewController
{
  private SearchView mainView;

  public SearchController(Context paramContext)
  {
    super(paramContext);
    this.controllerName = "search";
    setNavigationBarMode(INavigationSetting.Mode.FULLSCREEN);
    this.mainView = new SearchView(paramContext);
    attachView(this.mainView);
  }

  public void config(String paramString, Object paramObject)
  {
    this.mainView.update(paramString, paramObject);
  }

  public void controllerDidPopped()
  {
    this.mainView.close(false);
    super.controllerDidPopped();
  }

  public void controllerDidPushed()
  {
    this.mainView.update("openKeyBoard", null);
    super.controllerDidPushed();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.SearchController
 * JD-Core Version:    0.6.2
 */