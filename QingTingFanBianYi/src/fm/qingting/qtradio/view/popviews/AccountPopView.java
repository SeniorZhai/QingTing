package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.HorizontalScrollViewImpl;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.social.CloudCenter.OnLoginEventListerner;
import fm.qingting.qtradio.view.playview.LineElement;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;

public class AccountPopView extends ViewGroupViewImpl
  implements IEventHandler
{
  private final ViewLayout containerLayout = this.standardLayout.createChildLT(720, 420, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private LoginScrollView mActionsContainerView;
  private CloudCenter.OnLoginEventListerner mListerner;
  private LoginOtherActionsView mOtherActionsView;
  private final ViewLayout scrollLayout = this.containerLayout.createChildLT(720, 220, 0, 100, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public AccountPopView(Context paramContext)
  {
    super(paramContext);
    this.mOtherActionsView = new LoginOtherActionsView(paramContext);
    addView(this.mOtherActionsView);
    this.mOtherActionsView.setEventHandler(this);
    this.mActionsContainerView = new LoginScrollView(paramContext);
    addView(this.mActionsContainerView);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.standardLayout.height - this.containerLayout.height))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("cancelPop"))
      dispatchActionEvent(paramString, paramObject2);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mOtherActionsView.layout(0, this.standardLayout.height - this.containerLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mActionsContainerView.layout(0, this.standardLayout.height - this.containerLayout.height + this.scrollLayout.topMargin, this.standardLayout.width, this.standardLayout.height - this.containerLayout.height + this.scrollLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.containerLayout.scaleToBounds(this.standardLayout);
    this.scrollLayout.scaleToBounds(this.containerLayout);
    this.containerLayout.measureView(this.mOtherActionsView);
    this.scrollLayout.measureView(this.mActionsContainerView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      if (paramObject == null)
        this.mListerner = null;
    }
    else
      return;
    this.mListerner = ((CloudCenter.OnLoginEventListerner)paramObject);
  }

  class LoginActionContentView extends QtView
    implements ViewElement.OnElementClickListener
  {
    private final String[] EVENT_TYPES = { "redirectToWechat", "redirectToQQ", "redirectToSina", "redirectToTencent" };
    private final int[] PLATFORM_ICONS = { 2130837978, 2130837974, 2130837976, 2130837977 };
    private final String[] PLATFORM_NAMES = { "微信", "QQ", "新浪微博", "腾讯微博" };
    private final ViewLayout containerLayout = this.standardLayout.createChildLT(204, 220, 22, 0, ViewLayout.SCALE_FLAG_SLTCW);
    private final ViewLayout itemLayout = this.containerLayout.createChildLT(136, 220, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
    private PopActionButtonElement[] mItems;
    private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

    public LoginActionContentView(Context arg2)
    {
      super();
      int j = hashCode();
      this.mItems = new PopActionButtonElement[4];
      while (i < this.mItems.length)
      {
        this$1 = new PopActionButtonElement(localContext);
        AccountPopView.this.setStyle(1);
        AccountPopView.this.setAction(this.PLATFORM_NAMES[i], this.PLATFORM_ICONS[i]);
        addElement(AccountPopView.this, j);
        this.mItems[i] = AccountPopView.this;
        AccountPopView.this.setOnElementClickListener(this);
        i += 1;
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
          if (paramViewElement == this.mItems[i])
          {
            if (InfoManager.getInstance().getBindRecommendShare())
            {
              InfoManager.getInstance().setBindRecommendShare(false);
              QTMSGManage.getInstance().sendStatistcsMessage("loginRecommendShare", this.PLATFORM_NAMES[i]);
            }
            EventDispacthManager.getInstance().dispatchAction(this.EVENT_TYPES[i], AccountPopView.this.mListerner);
          }
        }
        else
          return;
        i += 1;
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
  }

  class LoginOtherActionsView extends QtView
    implements ViewElement.OnElementClickListener
  {
    private final ViewLayout cancelLayout = this.standardLayout.createChildLT(720, 100, 0, 320, ViewLayout.SCALE_FLAG_SLTCW);
    private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 320, ViewLayout.SCALE_FLAG_SLTCW);
    private ButtonViewElement mCancelElement;
    private LineElement mLineElement;
    private TextViewElement mTitleElement;
    private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 420, 720, 420, 0, 0, ViewLayout.FILL);
    private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

    public LoginOtherActionsView(Context arg2)
    {
      super();
      setBackgroundColor(SkinManager.getNewPopBgColor());
      this.mTitleElement = new TextViewElement(localContext);
      this.mTitleElement.setMaxLineLimit(1);
      this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mTitleElement.setColor(SkinManager.getNewPopTextColor());
      if (InfoManager.getInstance().forceLogin())
        this.mTitleElement.setText("听说登录后再下载,速度快到不行哟!", false);
      while (true)
      {
        addElement(this.mTitleElement);
        this.mCancelElement = new ButtonViewElement(localContext);
        this.mCancelElement.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 0);
        this.mCancelElement.setText("取消");
        this.mCancelElement.setTextColor(SkinManager.getNewPopTextColor());
        addElement(this.mCancelElement);
        this.mCancelElement.setOnElementClickListener(this);
        this.mLineElement = new LineElement(localContext);
        this.mLineElement.setColor(SkinManager.getDividerColor());
        this.mLineElement.setOrientation(1);
        addElement(this.mLineElement);
        return;
        this.mTitleElement.setText("请登录", false);
      }
    }

    public void close(boolean paramBoolean)
    {
      BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
      super.close(paramBoolean);
    }

    public void onElementClick(ViewElement paramViewElement)
    {
      if (this.mCancelElement == paramViewElement)
        dispatchActionEvent("cancelPop", null);
    }

    protected void onMeasure(int paramInt1, int paramInt2)
    {
      this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
      this.titleLayout.scaleToBounds(this.standardLayout);
      this.cancelLayout.scaleToBounds(this.standardLayout);
      this.lineLayout.scaleToBounds(this.standardLayout);
      this.mTitleElement.measure(this.titleLayout);
      this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
      this.mCancelElement.measure(this.cancelLayout);
      this.mCancelElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
      this.mLineElement.measure(this.lineLayout);
      super.onMeasure(paramInt1, paramInt2);
    }
  }

  class LoginScrollView extends HorizontalScrollViewImpl
  {
    private AccountPopView.LoginActionContentView mContentView;

    public LoginScrollView(Context arg2)
    {
      super();
      setHorizontalScrollBarEnabled(false);
      setHorizontalFadingEdgeEnabled(false);
      this.mContentView = new AccountPopView.LoginActionContentView(AccountPopView.this, localContext);
      addView(this.mContentView);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.AccountPopView
 * JD-Core Version:    0.6.2
 */