package fm.qingting.qtradio.view.categoryorder;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.IconManage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.view.dragdrop.DraggableItem;
import fm.qingting.qtradio.view.playview.LineElement;

class ResortItemView extends QtView
  implements DraggableItem
{
  private final ViewLayout buttonLayout = this.itemLayout.createChildLT(70, 70, 55, 50, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(180, 204, 180, 204, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout lineLayout = this.itemLayout.createChildLT(1, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private LineElement mBottomLine;
  private ImageViewElement mIcon;
  private LineElement mRightLine;
  private int mState = 0;
  private TextViewElement mTitle;
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(180, 40, 0, 130, ViewLayout.SCALE_FLAG_SLTCW);

  public ResortItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mIcon = new ImageViewElement(paramContext);
    addElement(this.mIcon);
    this.mTitle = new TextViewElement(paramContext);
    this.mTitle.setColor(SkinManager.getTextColorSubInfo());
    this.mTitle.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitle.setMaxLineLimit(1);
    addElement(this.mTitle);
    this.mRightLine = new LineElement(paramContext);
    this.mRightLine.setOrientation(0);
    this.mRightLine.setColor(SkinManager.getDividerColor());
    addElement(this.mRightLine);
    this.mBottomLine = new LineElement(paramContext);
    this.mBottomLine.setOrientation(1);
    this.mBottomLine.setColor(SkinManager.getDividerColor());
    addElement(this.mBottomLine);
    ignoreSelfTouchEvent();
  }

  public int getState()
  {
    return this.mState;
  }

  public boolean isSelected()
  {
    return false;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.buttonLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mIcon.measure(this.buttonLayout);
    this.mTitle.measure(this.titleLayout);
    this.mTitle.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mRightLine.measure(this.itemLayout.width - this.lineLayout.width, 0, this.itemLayout.width, this.itemLayout.height);
    this.mBottomLine.measure(0, this.itemLayout.height - this.lineLayout.height, this.itemLayout.width, this.itemLayout.height);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void setState(int paramInt)
  {
    if (this.mState != paramInt)
    {
      this.mState = paramInt;
      if (paramInt == 1)
      {
        this.mIcon.setVisible(4);
        this.mTitle.setVisible(4);
      }
    }
    else
    {
      return;
    }
    this.mIcon.setVisible(0);
    this.mTitle.setVisible(0);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (CategoryNode)paramObject;
      this.mIcon.setImageRes(IconManage.getNormalRes(paramString.sectionId));
      this.mTitle.setText(paramString.name);
    }
    while ((paramString.equalsIgnoreCase("setFixed")) || (!paramString.equalsIgnoreCase("setSelected")))
      return;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.categoryorder.ResortItemView
 * JD-Core Version:    0.6.2
 */