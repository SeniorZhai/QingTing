package fm.qingting.qtradio.view.frontpage;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.EducationManager;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;
import fm.qingting.qtradio.wo.WoApiRequest;
import fm.qingting.utils.QTMSGManage;

public class MainTabView extends QtView
  implements ViewElement.OnElementClickListener
{
  public static final int TAB_DISCOVERY = 1;
  public static final int TAB_DOWNLOAD = 2;
  public static final int TAB_PERSONAL = 0;
  private static int sNaviHeight = 86;
  private final ViewLayout discoverLayout = this.standardLayout.createChildLT(176, sNaviHeight, 176, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout downloadLayout = this.standardLayout.createChildLT(176, sNaviHeight, 352, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mDiscover;
  private ButtonViewElement mDownload;
  private ButtonViewElement mMe;
  private ButtonViewElement mSearch;
  private final ViewLayout meLayout = this.standardLayout.createChildLT(176, sNaviHeight, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout searchLayout = this.standardLayout.createChildLT(80, 80, 624, 3, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, sNaviHeight, 720, sNaviHeight, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public MainTabView(Context paramContext)
  {
    super(paramContext);
    if ((!QtApiLevelManager.isApiLevelSupported(19)) || (NaviUtil.getStatusBarHeight(getResources()) <= 0))
      setBackgroundColor(SkinManager.getNaviBgColor());
    this.mMe = new ButtonViewElement(paramContext);
    this.mMe.setTextColor(-1, -678492);
    addElement(this.mMe);
    this.mMe.setText("我的");
    this.mMe.setOnElementClickListener(this);
    this.mDiscover = new ButtonViewElement(paramContext);
    this.mDiscover.setTextColor(-1, -678492);
    this.mDiscover.setText("发现");
    addElement(this.mDiscover);
    this.mDiscover.setOnElementClickListener(this);
    this.mDiscover.setSelected(true);
    this.mDownload = new ButtonViewElement(paramContext);
    this.mDownload.setText("下载");
    this.mDownload.setTextColor(-1, -678492);
    addElement(this.mDownload);
    this.mDownload.setOnElementClickListener(this);
    this.mSearch = new ButtonViewElement(paramContext);
    this.mSearch.setBackground(2130837817, 2130837816);
    this.mSearch.setOnElementClickListener(this);
    addElement(this.mSearch);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mSearch)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("search_fromsearchframe");
      QTMSGManage.getInstance().sendStatistcsMessage("newnavi_maintabclick", "SEARCH");
      ControllerManager.getInstance().redirectToSearchView(true);
      if (EducationManager.getInstance().isShown())
        EducationManager.getInstance().cancelTip();
    }
    int j;
    do
    {
      return;
      int i = 2;
      j = 0;
      if (i >= 0)
      {
        ViewElement localViewElement = getChildAt(i);
        if (paramViewElement == localViewElement)
        {
          if (i == 3);
          localViewElement.setSelected(true);
          j = i;
        }
        while (true)
        {
          i -= 1;
          break;
          localViewElement.setSelected(false);
        }
      }
      dispatchActionEvent("selectTab", Integer.valueOf(j));
    }
    while (j != 0);
    WoApiRequest.sendEventMessage("unicomClickMy");
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.meLayout.scaleToBounds(this.standardLayout);
    this.discoverLayout.scaleToBounds(this.standardLayout);
    this.downloadLayout.scaleToBounds(this.standardLayout);
    this.searchLayout.scaleToBounds(this.standardLayout);
    this.mMe.measure(this.meLayout);
    this.mDiscover.measure(this.discoverLayout);
    this.mDownload.measure(this.downloadLayout);
    this.mSearch.measure(this.searchLayout);
    float f = SkinManager.getInstance().getNormalTextSize();
    this.mMe.setTextSize(f);
    this.mDiscover.setTextSize(f);
    this.mDownload.setTextSize(f);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.MainTabView
 * JD-Core Version:    0.6.2
 */