package fm.qingting.qtradio.controller;

import android.content.Context;
import android.graphics.drawable.Drawable;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.qtradio.view.playview_bb.DanmakuPlayListView;

public class DanmakuPlayListController extends ViewController
{
  private DanmakuPlayListView mainView;

  public DanmakuPlayListController(Context paramContext)
  {
    super(paramContext);
    this.mainView = new DanmakuPlayListView(paramContext);
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
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.controller.DanmakuPlayListController
 * JD-Core Version:    0.6.2
 */