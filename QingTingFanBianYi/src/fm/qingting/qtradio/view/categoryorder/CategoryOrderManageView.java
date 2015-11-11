package fm.qingting.qtradio.view.categoryorder;

import android.content.Context;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.view.dragdrop.DragDropAdapter;
import fm.qingting.qtradio.view.dragdrop.DragDropContainer3;
import fm.qingting.qtradio.view.dragdrop.DragDropListener;
import fm.qingting.qtradio.view.popviews.CategoryResortPopView.CategoryResortInfo;
import fm.qingting.utils.ScreenConfiguration;
import java.util.ArrayList;
import java.util.List;

public class CategoryOrderManageView extends ViewGroupViewImpl
{
  public static final String DISABLEDRAGDROP = "disabledragdrop";
  public static final String DRAGDROP_ENABLED = "dragdropenabled";
  public static final String ENABLEDRAGDROP = "enabledragdrop";
  public static final String ITEM_SELECTED = "itemselected";
  private static final String STATISTIC_TAG = "v6_resort_click";
  public static final String TOGGLEDRAGDROP = "toggledragdrop";
  private DragDropContainer3 mContainer;
  private List<CategoryNode> mDatas;
  int mHash = hashCode();
  private LockableScrollView mScrollView;
  private SectionTagView mTagView;
  private SectionTagView mTagView2;

  public CategoryOrderManageView(Context paramContext, List<CategoryNode> paramList)
  {
    super(paramContext);
    this.mDatas = paramList;
    this.mScrollView = new LockableScrollView(paramContext);
    addView(this.mScrollView);
    this.mTagView = new SectionTagView(paramContext);
    this.mTagView.setTitle("常用分类");
    addView(this.mTagView);
    this.mContainer = new DragDropContainer3(paramContext);
    this.mContainer.setDragDropAdapter(new ResortAdapter(null));
    this.mContainer.setBackgroundColor(SkinManager.getCardColor());
    addView(this.mContainer);
    this.mContainer.setDragDropListener(new DragDropListener()
    {
      public void onDragStart(int paramAnonymousInt)
      {
      }

      public void onDrop(int paramAnonymousInt1, int paramAnonymousInt2)
      {
      }

      public void onEnterDragMode(int paramAnonymousInt)
      {
        CategoryOrderManageView.this.dispatchActionEvent("dragdropenabled", Integer.valueOf(paramAnonymousInt));
      }

      public void onItemClick(int paramAnonymousInt)
      {
        CategoryOrderManageView.this.selectItem(paramAnonymousInt);
        CategoryOrderManageView.this.dispatchActionEvent("itemselected", Integer.valueOf(paramAnonymousInt));
        ((CategoryOrderManageView.ResortAdapter)CategoryOrderManageView.this.mContainer.getAdapter()).getNameExternal(paramAnonymousInt);
      }
    });
    this.mTagView2 = new SectionTagView(paramContext);
    this.mTagView2.setTitle("其他分类");
    addView(this.mTagView2);
  }

  private void selectItem(int paramInt)
  {
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mContainer.ensureWindowRemoved();
    super.close(paramBoolean);
  }

  public CategoryResortPopView.CategoryResortInfo getResortInfo()
  {
    return new CategoryResortPopView.CategoryResortInfo(this.mContainer.getSelectedIndex(), this.mContainer.getIndexsList());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt2 = this.mTagView.getMeasuredHeight();
    this.mTagView.layout(0, 0, paramInt3 - paramInt1, paramInt2);
    this.mContainer.layout(0, paramInt2, paramInt3 - paramInt1, this.mContainer.getMeasuredHeight() + paramInt2);
    this.mTagView2.layout(0, this.mContainer.getMeasuredHeight() + paramInt2, paramInt3 - paramInt1, paramInt2 + this.mContainer.getMeasuredHeight() + this.mTagView2.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mTagView.measure(paramInt1, paramInt2);
    this.mContainer.measure(paramInt1, paramInt2);
    this.mContainer.setCorrectionOffset(ScreenConfiguration.getNaviHeight() + this.mTagView.getMeasuredHeight());
    this.mScrollView.measure(paramInt1, paramInt2);
    this.mTagView2.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (CategoryResortPopView.CategoryResortInfo)paramObject;
      this.mContainer.setIndexsList(paramString.getIndexs());
      selectItem(paramString.getSelectItem());
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("setSelectedIndex"))
      {
        selectItem(((Integer)paramObject).intValue());
        return;
      }
      if (paramString.equalsIgnoreCase("disabledragdrop"))
      {
        this.mContainer.disableDragDrop();
        return;
      }
      if (paramString.equalsIgnoreCase("enabledragdrop"))
      {
        this.mContainer.enableDragDrop();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("toggledragdrop"));
    this.mContainer.toggleDragDrop();
  }

  private class ResortAdapter extends DragDropAdapter
  {
    private ResortAdapter()
    {
    }

    private Integer findId(int paramInt)
    {
      ArrayList localArrayList = CategoryResortPopView.CategoryResortInfo.getSortedIdArrayList();
      if ((localArrayList == null) || (paramInt >= localArrayList.size()))
        return Integer.valueOf(-1);
      return (Integer)localArrayList.get(paramInt);
    }

    private CategoryNode getName(int paramInt)
    {
      int i = 0;
      while (i < CategoryOrderManageView.this.mDatas.size())
      {
        CategoryNode localCategoryNode = (CategoryNode)CategoryOrderManageView.this.mDatas.get(i);
        if (paramInt == localCategoryNode.sectionId)
          return localCategoryNode;
        i += 1;
      }
      return null;
    }

    public int getColumnCount()
    {
      return 4;
    }

    public int getCount()
    {
      return CategoryOrderManageView.this.mDatas.size();
    }

    public CategoryNode getNameExternal(int paramInt)
    {
      return getName(findId(paramInt).intValue());
    }

    public IView instantiateItem(int paramInt)
    {
      ResortItemView localResortItemView = new ResortItemView(CategoryOrderManageView.this.getContext(), CategoryOrderManageView.this.mHash);
      localResortItemView.update("setData", getName(findId(paramInt).intValue()));
      if (isFixed(paramInt))
        localResortItemView.update("setFixed", null);
      return localResortItemView;
    }

    public boolean isFixed(int paramInt)
    {
      return paramInt == 0;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.categoryorder.CategoryOrderManageView
 * JD-Core Version:    0.6.2
 */