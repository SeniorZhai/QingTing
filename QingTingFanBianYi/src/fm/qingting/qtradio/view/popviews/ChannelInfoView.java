package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.view.viewmodel.NaviUtil;
import fm.qingting.utils.ScreenConfiguration;

public class ChannelInfoView extends QtView
{
  private final ViewLayout closeLayout;
  private final ViewLayout coverLayout;
  private final ViewLayout infoLayout;
  private final ViewLayout lineLayout;
  private ButtonViewElement mClose;
  private NetImageViewElement mCover;
  private TextViewElement mInfo;
  private TextViewElement mName;
  private int mStatusHeight;
  private String mTag;
  private final Paint mTagBgPaint;
  private final Paint mTagPaint;
  private final RectF mTagRectF;
  private TextViewElement mTagText;
  private final Rect mTextBound;
  private final ViewLayout nameLayout;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout tagTextLayout;

  public ChannelInfoView(Context paramContext)
  {
    super(paramContext);
    ViewLayout localViewLayout = this.standardLayout;
    if (ScreenConfiguration.isUltraWideScreen())
    {
      i = 60;
      this.coverLayout = localViewLayout.createChildLT(400, 400, 0, i, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.standardLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label520;
      i = 30;
      label76: this.nameLayout = localViewLayout.createChildLT(630, 75, 45, i, ViewLayout.SCALE_FLAG_SLTCW);
      this.lineLayout = this.standardLayout.createChildLT(630, 1, 45, 4, ViewLayout.SCALE_FLAG_SLTCW);
      localViewLayout = this.standardLayout;
      if (!ScreenConfiguration.isUltraWideScreen())
        break label526;
    }
    label520: label526: for (int i = 75; ; i = 150)
    {
      this.infoLayout = localViewLayout.createChildLT(630, i, 45, 9, ViewLayout.SCALE_FLAG_SLTCW);
      this.tagTextLayout = this.standardLayout.createChildLT(90, 45, 45, 25, ViewLayout.SCALE_FLAG_SLTCW);
      this.closeLayout = this.standardLayout.createChildLT(50, 50, 630, 30, ViewLayout.SCALE_FLAG_SLTCW);
      this.mTagRectF = new RectF();
      this.mTextBound = new Rect();
      this.mTagPaint = new Paint();
      this.mTagBgPaint = new Paint();
      this.mStatusHeight = 0;
      i = hashCode();
      this.mCover = new NetImageViewElement(paramContext);
      this.mCover.setDefaultImageRes(2130837907);
      addElement(this.mCover, i);
      this.mName = new TextViewElement(paramContext);
      this.mName.setColor(SkinManager.getBackgroundColor());
      this.mName.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mName.setMaxLineLimit(1);
      addElement(this.mName);
      this.mInfo = new TextViewElement(paramContext);
      this.mInfo.setColor(SkinManager.getTextColorSubInfo());
      this.mInfo.setAlignment(Layout.Alignment.ALIGN_NORMAL);
      this.mInfo.setMaxLineLimit(20);
      addElement(this.mInfo);
      this.mTagText = new TextViewElement(paramContext);
      this.mTagText.setText("标签：");
      this.mTagText.setColor(SkinManager.getBackgroundColor());
      this.mTagText.setAlignment(Layout.Alignment.ALIGN_NORMAL);
      this.mTagText.setMaxLineLimit(1);
      addElement(this.mTagText);
      this.mClose = new ButtonViewElement(paramContext);
      this.mClose.setBackground(2130837535, 2130837534);
      addElement(this.mClose, i);
      this.mTagPaint.setColor(SkinManager.getBackgroundColor());
      this.mTagBgPaint.setColor(SkinManager.getTextColorHighlight());
      this.mTagBgPaint.setStyle(Paint.Style.FILL);
      if (QtApiLevelManager.isApiLevelSupported(19))
        this.mStatusHeight = NaviUtil.getStatusBarHeight(getResources());
      return;
      i = 120;
      break;
      i = 60;
      break label76;
    }
  }

  private void drawBg(Canvas paramCanvas)
  {
    paramCanvas.drawColor(-872415232);
    int i = this.coverLayout.topMargin;
    int j = this.mStatusHeight;
    int k = this.coverLayout.height;
    int m = this.nameLayout.topMargin;
    int n = this.nameLayout.height;
    int i1 = this.lineLayout.topMargin;
    int i2 = this.infoLayout.topMargin;
    int i3 = this.mInfo.getHeight();
    this.mTagText.setTranslationY(i + j + k + m + n + i1 + i2 + i3);
  }

  private void drawLine(Canvas paramCanvas)
  {
    int i = this.mStatusHeight;
    int j = this.coverLayout.getBottom();
    int k = this.nameLayout.getBottom();
    int m = this.lineLayout.topMargin;
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.lineLayout.getRight(), i + j + k + m, this.lineLayout.height);
  }

  private void drawTag(Canvas paramCanvas)
  {
    if ((this.mTag == null) || (this.mTag.equalsIgnoreCase("")))
      return;
    this.mTagPaint.getTextBounds(this.mTag, 0, this.mTag.length(), this.mTextBound);
    this.mTagRectF.set(this.mTagText.getRightMargin(), this.mTagText.getTopMargin(), this.mTagText.getRightMargin() + this.mTextBound.width() + this.tagTextLayout.height, this.mTagText.getBottomMargin());
    paramCanvas.drawRoundRect(this.mTagRectF, this.tagTextLayout.height / 2.0F, this.tagTextLayout.height / 2.0F, this.mTagBgPaint);
    paramCanvas.drawText(this.mTag, this.mTagText.getRightMargin() + this.tagTextLayout.height / 2.0F, (this.mTagText.getTopMargin() + this.mTagText.getBottomMargin() - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTagPaint);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    drawBg(paramCanvas);
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
    drawTag(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.coverLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.infoLayout.scaleToBounds(this.standardLayout);
    this.tagTextLayout.scaleToBounds(this.standardLayout);
    this.closeLayout.scaleToBounds(this.standardLayout);
    this.mClose.measure(this.closeLayout.leftMargin, this.mStatusHeight + this.closeLayout.topMargin, this.closeLayout.getRight(), this.mStatusHeight + this.closeLayout.getBottom());
    paramInt1 = this.coverLayout.topMargin + this.mStatusHeight;
    this.mCover.measure((this.standardLayout.width - this.coverLayout.width) / 2, paramInt1, (this.standardLayout.width + this.coverLayout.width) / 2, this.coverLayout.height + paramInt1);
    paramInt1 = paramInt1 + this.coverLayout.height + this.nameLayout.topMargin;
    this.mName.measure(this.nameLayout.leftMargin, paramInt1, this.nameLayout.getRight(), this.nameLayout.height + paramInt1);
    paramInt1 = paramInt1 + this.nameLayout.height + this.lineLayout.topMargin + this.infoLayout.topMargin;
    this.mInfo.measure(this.infoLayout.leftMargin, paramInt1, this.infoLayout.getRight(), this.infoLayout.height + paramInt1);
    TextViewElement localTextViewElement = this.mInfo;
    float f2 = this.infoLayout.height;
    if (ScreenConfiguration.isUltraWideScreen());
    for (float f1 = 0.36F; ; f1 = 0.18F)
    {
      localTextViewElement.setTextSize(f1 * f2);
      this.mTagText.measure(this.tagTextLayout.leftMargin, this.tagTextLayout.topMargin, this.tagTextLayout.getRight(), this.tagTextLayout.topMargin + this.tagTextLayout.height);
      this.mName.setTextSize(this.nameLayout.height * 0.5F);
      this.mTagText.setTextSize(this.tagTextLayout.height * 0.6F);
      this.mTagPaint.setTextSize(this.tagTextLayout.height * 0.6F);
      setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
      return;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 1)
      dispatchActionEvent("cancelPop", null);
    return super.onTouchEvent(paramMotionEvent);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      paramString = (ChannelNode)paramObject;
      this.mCover.setImageUrl(paramString.getApproximativeThumb(400, 400, true));
      this.mName.setText(paramString.title, false);
      paramObject = paramString.parent;
      if (paramObject == null)
        break label146;
      if (!paramObject.nodeName.equalsIgnoreCase("category"))
        break label97;
      this.mTag = ((CategoryNode)paramObject).name;
    }
    label146: 
    while (paramString.isNovelChannel())
    {
      this.mInfo.setText(paramString.desc);
      return;
      label97: if (paramObject.nodeName.equalsIgnoreCase("subcategory"))
      {
        paramObject = paramObject.parent;
        if ((paramObject != null) && (paramObject.nodeName.equalsIgnoreCase("category")))
        {
          this.mTag = ((CategoryNode)paramObject).name;
          continue;
          this.mTag = "";
        }
      }
    }
    this.mInfo.setText(paramString.desc);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ChannelInfoView
 * JD-Core Version:    0.6.2
 */