package fm.qingting.qtradio.view.popviews.categoryfilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.LayoutAnimationController;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import fm.qingting.framework.controller.ViewController;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.Attributes;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CategoryFilterPopView extends ViewGroupViewImpl
  implements InfoManager.ISubscribeEventListener, IEventHandler
{
  private static final String STATISTIC_TAG_FILTER = "v6_filter_filter";
  private static final String STATISTIC_TAG_SHOW = "v6_filter_show";
  private final ViewLayout bottomLayout = this.standardLayout.createChildLT(720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ManageView mBottomView;
  private LinearLayout mContainer;
  private CategoryNode mNode;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public CategoryFilterPopView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getBackgroundColor_New());
    this.mContainer = ((LinearLayout)LayoutInflater.from(paramContext).inflate(2130903042, null));
    addView(this.mContainer);
    this.mContainer.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
      }
    });
    this.mBottomView = new ManageView(paramContext);
    this.mBottomView.setEventHandler(this);
    addView(this.mBottomView);
  }

  private List<Attribute> fetchAttributes()
  {
    ArrayList localArrayList = new ArrayList();
    LinearLayout localLinearLayout = (LinearLayout)this.mContainer.findViewById(2131230737);
    int i = 0;
    while (i < localLinearLayout.getChildCount())
    {
      Object localObject = localLinearLayout.getChildAt(i);
      if ((localObject instanceof FilterView))
      {
        localObject = (Attribute)((FilterView)localObject).getValue("ga", null);
        if (localObject != null)
          localArrayList.add(localObject);
      }
      i += 1;
    }
    if (localArrayList.size() > 0)
      QTMSGManage.getInstance().sendStatistcsMessage("v6_filter_filter", this.mNode.name);
    return localArrayList;
  }

  private void resetFilterIfNeed()
  {
    ViewController localViewController = ControllerManager.getInstance().getLastViewController();
    if (((localViewController != null) && (localViewController.controllerName.equalsIgnoreCase("vcacc"))) || (localViewController.controllerName.equalsIgnoreCase("vcacc")))
    {
      PlayerAgent.getInstance().addPlaySource(35);
      localViewController.config("resetFilter", fetchAttributes());
    }
  }

  private void setData(List<Attributes> paramList, List<Attribute> paramList1)
  {
    LinearLayout localLinearLayout = (LinearLayout)this.mContainer.findViewById(2131230737);
    localLinearLayout.removeAllViews();
    if (paramList == null)
      return;
    int i = 0;
    while (i < paramList.size())
    {
      Object localObject = new FilterTagView(getContext());
      ((FilterTagView)localObject).update("setData", ((Attributes)paramList.get(i)).name);
      localLinearLayout.addView((View)localObject);
      localObject = new FilterView(getContext());
      ((FilterView)localObject).update("setInitAttributes", paramList1);
      ((FilterView)localObject).update("setData", ((Attributes)paramList.get(i)).mLstAttribute);
      localLinearLayout.addView((View)localObject);
      i += 1;
    }
    paramList = new TranslateAnimation(1, -0.5F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
    paramList.setDuration(200L);
    paramList = new LayoutAnimationController(paramList);
    paramList.setOrder(0);
    paramList.setDelay(0.1F);
    localLinearLayout.setLayoutAnimation(paramList);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("clickleft"))
      dispatchActionEvent("cancelPop", null);
    while (!paramString.equalsIgnoreCase("clickright"))
      return;
    resetFilterIfNeed();
    dispatchActionEvent("cancelPop", null);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContainer.layout(0, 0, this.standardLayout.width, this.standardLayout.height - this.bottomLayout.height);
    this.mBottomView.layout(0, this.standardLayout.height - this.bottomLayout.height, this.bottomLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.bottomLayout.scaleToBounds(this.standardLayout);
    this.mContainer.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.bottomLayout.height, 1073741824));
    this.bottomLayout.measureView(this.mBottomView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RECV_ATTRIBUTES"))
      setData(this.mNode.mLstAttributes, null);
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      paramString = (HashMap)paramObject;
      this.mNode = ((CategoryNode)paramString.get("node"));
      paramString = (List)paramString.get("attributes");
      QTMSGManage.getInstance().sendStatistcsMessage("v6_filter_show", this.mNode.name);
      if (this.mNode.mLstAttributes == null)
      {
        InfoManager.getInstance().loadCategoryAttrs(this.mNode, this.mNode.categoryId, this);
        setData(null, paramString);
      }
    }
    else
    {
      return;
    }
    setData(this.mNode.mLstAttributes, paramString);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.categoryfilter.CategoryFilterPopView
 * JD-Core Version:    0.6.2
 */