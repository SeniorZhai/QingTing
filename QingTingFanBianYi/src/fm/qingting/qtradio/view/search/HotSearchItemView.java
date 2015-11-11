package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.text.TextPaint;
import android.view.View;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.search.SearchHotKeyword;
import fm.qingting.utils.QTMSGManage;

public class HotSearchItemView extends QtListItemView
{
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 112, 720, 1200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 40, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private SearchHotKeyword mKeyword;
  private int mMaxWidth;
  private final String mMaxWidthString = "10";
  private final Paint mNormalHeatPaint = new Paint();
  private final Paint mNormalPaint = new Paint();
  private int mRankIndex = 0;
  private final Rect mTextBound = new Rect();
  private final Paint mTopHeatPaint = new Paint();
  private final Paint mTopRankPaint = new Paint();
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(600, 68, 5, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rankLayout = this.itemLayout.createChildLT(50, 60, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rankchangeLayout = this.itemLayout.createChildLT(300, 48, 40, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public HotSearchItemView(Context paramContext)
  {
    super(paramContext);
    this.mTopRankPaint.setColor(SkinManager.getTextColorHighlight());
    this.mTopRankPaint.setTextSkewX(-0.25F);
    this.mNormalPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mNormalPaint.setTextSkewX(-0.25F);
    this.mTopHeatPaint.setColor(SkinManager.getTextColorHighlight());
    this.mNormalHeatPaint.setColor(SkinManager.getTextColorSecondLevel());
    setItemSelectedEnable();
    setOnClickListener(this);
  }

  private void drawBg(Canvas paramCanvas)
  {
    if (!isItemPressed())
    {
      int i = paramCanvas.save();
      paramCanvas.clipRect(0, 0, this.itemLayout.width, this.itemLayout.height);
      paramCanvas.drawColor(SkinManager.getCardColor());
      paramCanvas.restoreToCount(i);
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void drawName(Canvas paramCanvas)
  {
    String str = this.mKeyword.keyword;
    TextPaint localTextPaint = SkinManager.getInstance().getNormalTextPaint();
    localTextPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    paramCanvas.drawText(str, this.rankLayout.leftMargin + this.mMaxWidth + this.nameLayout.leftMargin, (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, localTextPaint);
  }

  private void drawRank(Canvas paramCanvas)
  {
    int i;
    String str;
    float f1;
    float f2;
    if (this.mRankIndex <= 3)
    {
      i = 1;
      str = String.valueOf(this.mRankIndex);
      this.mTopRankPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
      f1 = this.rankLayout.leftMargin;
      f2 = (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
      if (i == 0)
        break label103;
    }
    label103: for (Paint localPaint = this.mTopRankPaint; ; localPaint = this.mNormalPaint)
    {
      paramCanvas.drawText(str, f1, f2, localPaint);
      return;
      i = 0;
      break;
    }
  }

  private void drawRankChange(Canvas paramCanvas)
  {
    String str = String.valueOf(this.mKeyword.searchCnt);
    this.mNormalHeatPaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    float f1 = this.itemLayout.width - this.rankchangeLayout.leftMargin - this.mTextBound.width();
    float f2 = (this.itemLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
    if (this.mRankIndex <= 3);
    for (Paint localPaint = this.mTopHeatPaint; ; localPaint = this.mNormalHeatPaint)
    {
      paramCanvas.drawText(str, f1, f2, localPaint);
      return;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mKeyword == null)
      return;
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    drawRank(paramCanvas);
    drawName(paramCanvas);
    drawRankChange(paramCanvas);
    drawLine(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.rankLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.rankchangeLayout.scaleToBounds(this.itemLayout);
    this.mTopRankPaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mNormalPaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mTopHeatPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mNormalHeatPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mTopRankPaint.getTextBounds("10", 0, "10".length(), this.mTextBound);
    this.mMaxWidth = (this.mTextBound.right + this.mTextBound.left);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  protected void onQtItemClick(View paramView)
  {
    if (this.mKeyword != null)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("search_clickhotkeyword");
      ControllerManager.getInstance().redirectToSearchView(this.mKeyword.keyword, true);
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
      this.mKeyword = ((SearchHotKeyword)paramObject);
    while (!paramString.equalsIgnoreCase("position"))
      return;
    this.mRankIndex = (((Integer)paramObject).intValue() + 1);
    invalidate();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.HotSearchItemView
 * JD-Core Version:    0.6.2
 */