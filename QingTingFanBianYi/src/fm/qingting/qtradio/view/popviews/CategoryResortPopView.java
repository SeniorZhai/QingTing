package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.view.dragdrop.DragDropAdapter;
import fm.qingting.qtradio.view.dragdrop.DragDropContainer;
import fm.qingting.qtradio.view.dragdrop.DragDropListener;
import fm.qingting.utils.QTMSGManage;
import fm.qingting.utils.ScreenConfiguration;
import java.util.ArrayList;
import java.util.List;

public class CategoryResortPopView extends ViewGroupViewImpl
{
  public static final String DISABLEDRAGDROP = "disabledragdrop";
  public static final String DRAGDROP_ENABLED = "dragdropenabled";
  public static final String ENABLEDRAGDROP = "enabledragdrop";
  public static final String ITEM_SELECTED = "itemselected";
  private static final String STATISTIC_TAG = "v6_resort_click";
  public static final String TOGGLEDRAGDROP = "toggledragdrop";
  private DragDropContainer mContainer;
  private List<CategoryNode> mDatas;
  int mHash = hashCode();

  public CategoryResortPopView(Context paramContext, List<CategoryNode> paramList)
  {
    super(paramContext);
    this.mDatas = paramList;
    this.mContainer = new DragDropContainer(paramContext);
    this.mContainer.setDragDropAdapter(new ResortAdapter(null));
    addView(this.mContainer);
    this.mContainer.setBackgroundColor(SkinManager.getBackgroundColor_New());
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
        CategoryResortPopView.this.dispatchActionEvent("dragdropenabled", Integer.valueOf(paramAnonymousInt));
      }

      public void onItemClick(int paramAnonymousInt)
      {
        CategoryResortPopView.this.selectItem(paramAnonymousInt);
        CategoryResortPopView.this.dispatchActionEvent("itemselected", Integer.valueOf(paramAnonymousInt));
        String str = ((CategoryResortPopView.ResortAdapter)CategoryResortPopView.this.mContainer.getAdapter()).getNameExternal(paramAnonymousInt);
        if (str != null)
          QTMSGManage.getInstance().sendStatistcsMessage("v6_resort_click", str);
      }
    });
  }

  private void selectItem(int paramInt)
  {
    List localList = this.mContainer.getItems();
    if (localList != null)
    {
      int i = 0;
      if (i < localList.size())
      {
        IView localIView = (IView)localList.get(i);
        if (i == paramInt);
        for (boolean bool = true; ; bool = false)
        {
          localIView.update("setSelected", Boolean.valueOf(bool));
          i += 1;
          break;
        }
      }
    }
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    this.mContainer.ensureWindowRemoved();
    super.close(paramBoolean);
  }

  public CategoryResortInfo getResortInfo()
  {
    return new CategoryResortInfo(this.mContainer.getSelectedIndex(), this.mContainer.getIndexsList());
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mContainer.layout(0, 0, this.mContainer.getMeasuredWidth(), this.mContainer.getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.mContainer.measure(paramInt1, paramInt2);
    this.mContainer.setCorrectionOffset(ScreenConfiguration.getNaviHeight() + ScreenConfiguration.getSubTabHeight());
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (CategoryResortInfo)paramObject;
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

  public static class CategoryResortInfo
  {
    private static ArrayList<Integer> sSortedIdArray;
    private ArrayList<Integer> mIndexs;
    private int mSelectItem;

    public CategoryResortInfo(int paramInt1, int paramInt2)
    {
      this.mSelectItem = paramInt1;
      this.mIndexs = new ArrayList();
      paramInt1 = 0;
      while (paramInt1 < paramInt2)
      {
        this.mIndexs.add(Integer.valueOf(paramInt1));
        paramInt1 += 1;
      }
    }

    public CategoryResortInfo(int paramInt, ArrayList<Integer> paramArrayList)
    {
      this.mSelectItem = paramInt;
      this.mIndexs = paramArrayList;
    }

    private static ArrayList<Integer> fetchSortedIdArray()
    {
      Object localObject = SharedCfg.getInstance().getCategoryOrderString();
      ArrayList localArrayList = new ArrayList();
      if (localObject == null)
        return localArrayList;
      localObject = ((String)localObject).split("_");
      if (localObject == null)
        return localArrayList;
      int i = 0;
      while (true)
      {
        if (i < localObject.length);
        try
        {
          localArrayList.add(Integer.valueOf(localObject[i]));
          label53: i += 1;
          continue;
          return localArrayList;
        }
        catch (Exception localException)
        {
          break label53;
        }
      }
    }

    public static ArrayList<Integer> getSortedIdArrayList()
    {
      if (sSortedIdArray == null)
        sSortedIdArray = fetchSortedIdArray();
      return sSortedIdArray;
    }

    private static void saveOrder()
    {
      if (sSortedIdArray == null)
        return;
      Object localObject = "";
      int i = 0;
      while (i < sSortedIdArray.size())
      {
        String str = (String)localObject + sSortedIdArray.get(i);
        localObject = str;
        if (i != sSortedIdArray.size() - 1)
          localObject = str + "_";
        i += 1;
      }
      SharedCfg.getInstance().saveCategoryOrderString((String)localObject);
    }

    public static void setNewSortedList(ArrayList<Integer> paramArrayList, boolean paramBoolean)
    {
      sSortedIdArray = paramArrayList;
      if (paramBoolean)
        saveOrder();
    }

    public ArrayList<Integer> getIndexs()
    {
      return this.mIndexs;
    }

    public int getSelectItem()
    {
      return this.mSelectItem;
    }
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

    private String getName(int paramInt)
    {
      int i = 0;
      while (i < CategoryResortPopView.this.mDatas.size())
      {
        CategoryNode localCategoryNode = (CategoryNode)CategoryResortPopView.this.mDatas.get(i);
        if (paramInt == localCategoryNode.sectionId)
          return localCategoryNode.name;
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
      return CategoryResortPopView.this.mDatas.size();
    }

    public String getNameExternal(int paramInt)
    {
      return getName(findId(paramInt).intValue());
    }

    public IView instantiateItem(int paramInt)
    {
      ResortItemView localResortItemView = new ResortItemView(CategoryResortPopView.this.getContext(), CategoryResortPopView.this.mHash);
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
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CategoryResortPopView
 * JD-Core Version:    0.6.2
 */