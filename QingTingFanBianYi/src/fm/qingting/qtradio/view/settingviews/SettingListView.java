package fm.qingting.qtradio.view.settingviews;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.downloadnew.OnDownloadPathChangeListener;
import fm.qingting.framework.adapter.CustomizedAdapter;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.OnlineUpdateHelper;
import fm.qingting.qtradio.helper.OnlineUpdateHelper.OnUpdateListener;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.social.CloudCenter;
import fm.qingting.qtradio.view.moreContentView.CustomSectionView;
import fm.qingting.qtradio.view.moreContentView.UserinfoItemView;
import fm.qingting.qtradio.view.personalcenter.faq.LargeButtonView;
import fm.qingting.qtradio.wo.WoNetEventListener;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;

public class SettingListView extends ViewGroupViewImpl
  implements IEventHandler, OnDownloadPathChangeListener, OnlineUpdateHelper.OnUpdateListener
{
  private CustomizedAdapter adapter;
  private IAdapterIViewFactory factory;
  private UserinfoItemView mChinaZone;
  private CustomSectionView mCustomSectionView;
  private ListView mListView;
  private boolean mLogin = false;
  private LargeButtonView quitButton;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 800, 480, 800, 0, 0, ViewLayout.FILL);

  public SettingListView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor());
    OnlineUpdateHelper.getInstance().addListener(this);
    this.factory = new IAdapterIViewFactory()
    {
      public IView createView(int paramAnonymousInt)
      {
        return new SettingItemView(SettingListView.this.getContext(), this.val$hash);
      }
    };
    this.adapter = new CustomizedAdapter(new ArrayList(), this.factory);
    this.mListView = new ListView(paramContext);
    this.mListView.setVerticalScrollBarEnabled(false);
    this.mListView.setVerticalFadingEdgeEnabled(false);
    this.mListView.setCacheColorHint(0);
    this.mListView.setDivider(null);
    this.mListView.setHeaderDividersEnabled(false);
    this.mListView.setSelector(17170445);
    this.quitButton = new LargeButtonView(paramContext, "");
    initButtonState(this.mLogin);
    this.mListView.addFooterView(this.quitButton);
    this.mListView.setAdapter(this.adapter);
    this.quitButton.setEventHandler(this);
    addView(this.mListView);
    if ((InfoManager.getInstance().getChinaUnicomZone()) && (WoNetEventListener.isChinaUnicom(getContext())))
    {
      this.mChinaZone = new UserinfoItemView(getContext());
      this.mChinaZone.update("content", Integer.valueOf(9));
      this.mListView.addHeaderView(this.mChinaZone);
      this.mCustomSectionView = new CustomSectionView(getContext());
      this.mListView.addHeaderView(this.mCustomSectionView);
    }
    InfoManager.getInstance().root().mDownLoadInfoNode.addPathChangeListener(this);
  }

  private void changeButtonState(boolean paramBoolean)
  {
    if (this.mLogin == paramBoolean)
      return;
    if (paramBoolean)
    {
      this.quitButton.update("setText", "退出登录");
      this.quitButton.update("setState", Boolean.valueOf(false));
    }
    while (true)
    {
      this.mLogin = paramBoolean;
      return;
      this.quitButton.update("setText", "账号登录");
      this.quitButton.update("setState", Boolean.valueOf(true));
    }
  }

  private void initButtonState(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.quitButton.update("setText", "退出登录");
      this.quitButton.update("setState", Boolean.valueOf(false));
      return;
    }
    this.quitButton.update("setText", "账号登录");
    this.quitButton.update("setState", Boolean.valueOf(true));
  }

  private void initData()
  {
    this.adapter.setData(SettingConfig.getSettingList());
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    InfoManager.getInstance().root().mDownLoadInfoNode.removePathChangeListener(this);
    super.close(paramBoolean);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("onclick"))
    {
      if (this.mLogin)
      {
        CloudCenter.getInstance().logout();
        QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "logout");
        changeButtonState(false);
      }
    }
    else
      return;
    EventDispacthManager.getInstance().dispatchAction("showlogin", null);
    QTMSGManage.getInstance().sendStatistcsMessage("newnavi", "login_setting");
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mListView.layout(0, 0, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height, 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onPathChanged(String paramString)
  {
    initData();
  }

  public void onUpdate()
  {
    initData();
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
      initData();
    while (!paramString.equalsIgnoreCase("loginSuccess"))
      return;
    changeButtonState(true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.settingviews.SettingListView
 * JD-Core Version:    0.6.2
 */