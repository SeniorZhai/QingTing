package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import android.view.inputmethod.InputMethodManager;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.QTMSGManage;

public class SearchVchannelItemView extends QtView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 150, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonViewElement mBg;
  private TextViewElement mInfo;
  private TextViewElement mName;
  private SearchItemNode mNode;
  private NetImageViewElement mPic;
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(500, 45, 155, 35, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.itemLayout.createChildLT(112, 112, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SearchVchannelItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getItemHighlightMaskColor(), 16777215);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (InfoManager.getInstance().root().mSearchNode.getVoiceSearch())
        {
          InfoManager.getInstance().root().mSearchNode.setVoiceSearch(false);
          QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_click");
        }
        SearchVchannelItemView.this.closeKeyBoard();
        ControllerManager.getInstance().redirectToViewBySearchNode(SearchVchannelItemView.this.mNode);
      }
    });
    this.mPic = new NetImageViewElement(paramContext);
    this.mPic.setDefaultImageRes(2130837907);
    addElement(this.mPic, paramInt);
    this.mName = new TextViewElement(paramContext);
    this.mName.setMaxLineLimit(1);
    this.mName.setColor(SkinManager.getTextColorNormal());
    addElement(this.mName);
    this.mInfo = new TextViewElement(paramContext);
    this.mInfo.setMaxLineLimit(1);
    this.mInfo.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mInfo);
  }

  private boolean closeKeyBoard()
  {
    return ((InputMethodManager)getContext().getSystemService("input_method")).hideSoftInputFromWindow(getApplicationWindowToken(), 0);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    drawLine(paramCanvas);
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mPic.measure(this.picLayout.leftMargin, (this.itemLayout.height - this.picLayout.height) / 2, this.picLayout.getRight(), (this.itemLayout.height + this.picLayout.height) / 2);
    this.mName.measure(this.nameLayout);
    this.mInfo.measure(this.nameLayout.leftMargin, this.nameLayout.getBottom(), this.nameLayout.getRight(), this.nameLayout.getBottom() + this.nameLayout.height);
    this.mName.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfo.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mNode = ((SearchItemNode)paramObject);
      this.mPic.setImageUrl(this.mNode.cover);
      this.mName.setText(this.mNode.name);
      this.mInfo.setText(this.mNode.catName);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchVchannelItemView
 * JD-Core Version:    0.6.2
 */