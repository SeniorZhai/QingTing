package fm.qingting.qtradio.view;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ContentCategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.model.VirtualNode;
import fm.qingting.utils.ScreenConfiguration;
import java.util.ArrayList;
import java.util.List;

class UserGuideCategoryView extends QtView
{
  static final String LAST = "ugcv_last";
  static final String START = "ugcv_start";
  private ViewLayout firstLayout;
  private ViewLayout indicatorLayout;
  private ViewLayout itemLayout;
  private ViewLayout lastLayout;
  private boolean mDataSet;
  private TextViewElement mIndicator;
  private LastElement mLastElement;
  private ButtonViewElement mNextElement;
  private TextViewElement mTitleElement;
  private ViewLayout nextLayout;
  private ViewLayout roundLayout;
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1280, 720, 1280, 0, 0, ViewLayout.FILL);
  private ViewLayout titleLayout = this.standardLayout.createChildLT(720, 70, 0, 170, ViewLayout.SCALE_FLAG_SLTCW);

  public UserGuideCategoryView(Context paramContext)
  {
    super(paramContext);
    ViewLayout localViewLayout = this.standardLayout;
    if (ScreenConfiguration.isUltraWideScreen())
    {
      i = 76;
      this.firstLayout = localViewLayout.createChildLT(180, i, 0, 280, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.standardLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label450;
    }
    label450: for (int i = 76; ; i = 80)
    {
      this.itemLayout = localViewLayout.createChildLT(180, i, 0, 35, ViewLayout.SCALE_FLAG_SLTCW);
      this.nextLayout = this.standardLayout.createChildLT(630, 90, 45, 30, ViewLayout.SCALE_FLAG_SLTCW);
      this.indicatorLayout = this.standardLayout.createChildLT(720, 40, 0, 19, ViewLayout.SCALE_FLAG_SLTCW);
      this.roundLayout = this.standardLayout.createChildLT(10, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.lastLayout = this.standardLayout.createChildLT(300, 40, 50, 19, ViewLayout.SCALE_FLAG_SLTCW);
      this.mDataSet = false;
      setBackgroundResource(2130837998);
      this.mTitleElement = new TextViewElement(paramContext);
      this.mTitleElement.setMaxLineLimit(1);
      this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mTitleElement.setColor(SkinManager.getTextColorNormal());
      this.mTitleElement.setText("请选择您感兴趣的内容", false);
      addElement(this.mTitleElement);
      this.mIndicator = new TextViewElement(paramContext);
      this.mIndicator.setMaxLineLimit(1);
      this.mIndicator.setColor(-8355712);
      this.mIndicator.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mIndicator.setText("3/3", false);
      addElement(this.mIndicator);
      this.mNextElement = new ButtonViewElement(paramContext);
      this.mNextElement.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorHighlight(), -3158065);
      this.mNextElement.setRoundCorner(true);
      this.mNextElement.setTextColor(-1, -1, -1);
      this.mNextElement.setText("开启您的收听之旅");
      addElement(this.mNextElement);
      this.mNextElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
      {
        public void onElementClick(ViewElement paramAnonymousViewElement)
        {
          UserGuideCategoryView.this.dispatchActionEvent("ugcv_start", null);
        }
      });
      this.mLastElement = new LastElement(paramContext);
      this.mLastElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
      {
        public void onElementClick(ViewElement paramAnonymousViewElement)
        {
          UserGuideCategoryView.this.dispatchActionEvent("ugcv_last", null);
        }
      });
      addElement(this.mLastElement);
      return;
      i = 80;
      break;
    }
  }

  public List<CategoryNode> getSelectedCategory()
  {
    List localList = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes();
    if (localList == null)
      return null;
    int j = getChildCount();
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < j)
    {
      ViewElement localViewElement = getChildAt(i);
      if (((localViewElement instanceof SmallCheckElement)) && (((SmallCheckElement)localViewElement).isChecked()) && (i < localList.size() + 3))
        localArrayList.add(localList.get(i - 3));
      i += 1;
    }
    return localArrayList;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.firstLayout.scaleToBounds(this.standardLayout);
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.nextLayout.scaleToBounds(this.standardLayout);
    this.indicatorLayout.scaleToBounds(this.standardLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.lastLayout.scaleToBounds(this.standardLayout);
    paramInt1 = this.titleLayout.topMargin;
    this.mTitleElement.measure(0, paramInt1, this.standardLayout.width, this.titleLayout.height + paramInt1);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getLargeTextSize());
    this.mNextElement.measure(this.nextLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom(), this.nextLayout.getRight(), this.standardLayout.height - this.nextLayout.topMargin);
    this.mIndicator.measure(this.indicatorLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom() - this.indicatorLayout.getBottom(), this.indicatorLayout.getRight(), this.standardLayout.height - this.nextLayout.getBottom() - this.indicatorLayout.topMargin);
    this.mLastElement.measure(this.lastLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom() - this.lastLayout.getBottom(), this.lastLayout.getRight(), this.standardLayout.height - this.nextLayout.getBottom() - this.lastLayout.topMargin);
    this.mIndicator.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mNextElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mNextElement.setRoundCornerRadius(this.roundLayout.width);
    paramInt1 = this.firstLayout.topMargin;
    int j = getChildCount();
    int i;
    int k;
    if (j > 4)
    {
      i = 4;
      paramInt2 = 0;
      if (i < j)
      {
        int m = i - 4;
        if (m % 4 == 0)
          paramInt2 = 0;
        getChildAt(i).measure(paramInt2, paramInt1, this.itemLayout.width + paramInt2, this.itemLayout.height + paramInt1);
        k = this.itemLayout.width;
        if (m % 4 != 3)
          break label494;
        paramInt1 = this.itemLayout.height + paramInt1;
      }
    }
    label494: 
    while (true)
    {
      i += 1;
      paramInt2 = k + paramInt2;
      break;
      setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
      return;
    }
  }

  void setData()
  {
    int k = 0;
    if (this.mDataSet)
      return;
    Object localObject1;
    ArrayList localArrayList;
    int i;
    Object localObject2;
    while (true)
    {
      this.mDataSet = true;
      String str = InfoManager.getInstance().getUserguideRecommend();
      localObject1 = InfoManager.getInstance().root().mContentCategory.mVirtualNode.getLstCategoryNodes();
      localArrayList = new ArrayList();
      if ((localObject1 != null) && (((List)localObject1).size() != 0))
        break;
      localArrayList.add("电台");
      localArrayList.add("小说");
      localArrayList.add("音乐");
      localArrayList.add("读报");
      localArrayList.add("新闻");
      localArrayList.add("相声小品");
      localArrayList.add("脱口秀");
      localArrayList.add("情感");
      localArrayList.add("健康");
      localArrayList.add("军事");
      localArrayList.add("历史");
      localArrayList.add("儿童");
      localArrayList.add("娱乐");
      localArrayList.add("女性");
      localArrayList.add("搞笑");
      localArrayList.add("教育");
      localArrayList.add("外语");
      localArrayList.add("公开课");
      localArrayList.add("评书");
      localArrayList.add("戏曲");
      localArrayList.add("财经");
      localArrayList.add("科技");
      localArrayList.add("汽车");
      localArrayList.add("体育");
      localArrayList.add("校园");
      localArrayList.add("游戏");
      localArrayList.add("动漫");
      localArrayList.add("广播剧");
      localArrayList.add("主播");
      i = k;
      while (i < localArrayList.size())
      {
        localObject1 = new SmallCheckElement(getContext());
        localObject2 = (String)localArrayList.get(i);
        ((SmallCheckElement)localObject1).setParam((String)localObject2);
        if (str != null)
          ((SmallCheckElement)localObject1).setMarkEnabled(str.contains((CharSequence)localObject2));
        addElement((ViewElement)localObject1);
        i += 1;
      }
    }
    int j = 0;
    while (true)
    {
      i = k;
      if (j >= ((List)localObject1).size())
        break;
      localObject2 = (CategoryNode)((List)localObject1).get(j);
      if (((CategoryNode)localObject2).sectionId != 0)
        localArrayList.add(((CategoryNode)localObject2).name);
      j += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.UserGuideCategoryView
 * JD-Core Version:    0.6.2
 */