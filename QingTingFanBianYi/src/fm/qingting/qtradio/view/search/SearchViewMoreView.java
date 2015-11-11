package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.controller.SearchController;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;

public class SearchViewMoreView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 90, 720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mButtonViewElement;
  private int mType;

  public SearchViewMoreView(Context paramContext)
  {
    super(paramContext);
    this.mButtonViewElement = new ButtonViewElement(paramContext);
    this.mButtonViewElement.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), SkinManager.getCardColor());
    this.mButtonViewElement.setTextColor(SkinManager.getTextColorNormal());
    addElement(this.mButtonViewElement);
    this.mButtonViewElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        paramAnonymousViewElement = ControllerManager.getInstance().getLastViewController();
        if ((paramAnonymousViewElement instanceof SearchController))
          ((SearchController)paramAnonymousViewElement).config("selectTab", Integer.valueOf(SearchViewMoreView.this.mType));
      }
    });
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.mButtonViewElement.measure(this.itemLayout);
    this.mButtonViewElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mType = ((Integer)paramObject).intValue();
      int i = InfoManager.getInstance().root().mSearchNode.getTotalCountByType(this.mType);
      this.mButtonViewElement.setText("查看全部" + i + "个" + SearchNode.TABNAMES[this.mType]);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchViewMoreView
 * JD-Core Version:    0.6.2
 */