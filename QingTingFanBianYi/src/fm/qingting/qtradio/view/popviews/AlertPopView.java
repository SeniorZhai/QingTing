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
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

public class AlertPopView extends QtView
{
  private final ViewLayout bgLayout = this.standardLayout.createChildLT(610, 300, 55, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout buttonLayout = this.bgLayout.createChildLT(610, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkBgLayout = this.bgLayout.createChildLT(38, 38, 30, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.bgLayout.createChildLT(610, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private AlertParam mAlertParam;
  private TextViewElement mAlertTitle;
  private Paint mBgPaint = new Paint();
  private final RectF mBgRectF = new RectF();
  private ButtonRowElement mButtonRow;
  private CheckBoxElement mCheckBox;
  private TextViewElement mContent;
  private boolean mHasTip = false;
  private Paint mLinePaint = new Paint();
  private final Rect mLineRect = new Rect();
  private TextViewElement mTip;
  private String mTitle = "提醒";
  private final ViewLayout roundLayout = this.bgLayout.createChildLT(10, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout seperateLayout = this.bgLayout.createChildLT(550, 3, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout textLayout = this.bgLayout.createChildLT(550, 265, 30, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout tipLayout = this.bgLayout.createChildLT(450, 45, 8, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.bgLayout.createChildLT(610, 75, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public AlertPopView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    this.mAlertTitle = new TextViewElement(paramContext);
    this.mAlertTitle.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mAlertTitle.setColor(SkinManager.getPopTextColor());
    this.mAlertTitle.setMaxLineLimit(1);
    this.mAlertTitle.setText(this.mTitle);
    addElement(this.mAlertTitle);
    this.mButtonRow = new ButtonRowElement(paramContext);
    this.mButtonRow.setOnRowClickListenrer(new ButtonRowElement.OnButtonRowClickListener()
    {
      public void OnRowClick(ViewElement paramAnonymousViewElement, int paramAnonymousInt)
      {
        if ((AlertPopView.this.mAlertParam != null) && (AlertPopView.this.mAlertParam.getListener() != null))
        {
          paramAnonymousViewElement = AlertPopView.this.mAlertParam.getListener();
          if (!AlertPopView.this.mHasTip)
            break label64;
        }
        label64: for (boolean bool = AlertPopView.this.mCheckBox.isChecked(); ; bool = false)
        {
          paramAnonymousViewElement.onClick(paramAnonymousInt, bool);
          return;
        }
      }
    });
    addElement(this.mButtonRow, i);
    this.mContent = new TextViewElement(paramContext);
    this.mContent.setColor(SkinManager.getTextColorNormal());
    this.mContent.setAlignment(Layout.Alignment.ALIGN_NORMAL);
    addElement(this.mContent);
    this.mCheckBox = new CheckBoxElement(paramContext);
    addElement(this.mCheckBox, i);
    this.mCheckBox.setVisible(4);
    this.mTip = new TextViewElement(paramContext);
    this.mTip.setColor(SkinManager.getTextColorSubInfo());
    this.mTip.setText("不再提示", false);
    this.mTip.setAlignment(Layout.Alignment.ALIGN_NORMAL);
    this.mTip.setMaxLineLimit(1);
    addElement(this.mTip);
    this.mTip.setVisible(4);
    this.mBgPaint.setColor(SkinManager.getPopBgColor());
    this.mBgPaint.setStyle(Paint.Style.FILL);
    this.mLinePaint.setColor(SkinManager.getTextColorHighlight());
    this.mLinePaint.setStyle(Paint.Style.FILL);
  }

  private void drawBg(Canvas paramCanvas)
  {
    int j = this.mAlertTitle.getHeight();
    int k = this.mContent.getHeight();
    if (this.mHasTip);
    for (int i = this.checkBgLayout.height + this.checkBgLayout.topMargin * 2; ; i = 0)
    {
      int m = this.textLayout.topMargin + k + this.buttonLayout.height + i + j;
      int n = (this.standardLayout.height - m) / 2;
      this.mBgRectF.set(this.bgLayout.leftMargin, n, this.bgLayout.leftMargin + this.bgLayout.width, m + n);
      paramCanvas.drawRoundRect(this.mBgRectF, this.roundLayout.width, this.roundLayout.height, this.mBgPaint);
      this.mAlertTitle.setTranslationY(n);
      this.mContent.setTranslationY(this.buttonLayout.topMargin + n + j);
      this.mButtonRow.setTranslationY(i + (this.textLayout.topMargin + n + k) + j);
      if (this.mHasTip)
      {
        this.mCheckBox.setTranslationY(this.textLayout.topMargin + n + k + this.checkBgLayout.topMargin + j);
        this.mTip.setTranslationY(k + n + this.checkBgLayout.topMargin + j + this.textLayout.topMargin);
      }
      this.mLineRect.offset(0, n + j);
      paramCanvas.drawRect(this.mLineRect, this.mLinePaint);
      this.mLineRect.offset(0, -n - j);
      return;
    }
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (!this.mBgRectF.contains(paramMotionEvent.getX(), paramMotionEvent.getY())))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    drawBg(paramCanvas);
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.bgLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.bgLayout);
    this.textLayout.scaleToBounds(this.bgLayout);
    this.roundLayout.scaleToBounds(this.bgLayout);
    this.checkBgLayout.scaleToBounds(this.bgLayout);
    this.tipLayout.scaleToBounds(this.bgLayout);
    this.titleLayout.scaleToBounds(this.bgLayout);
    this.seperateLayout.scaleToBounds(this.bgLayout);
    this.lineLayout.scaleToBounds(this.bgLayout);
    this.mLineRect.set(this.bgLayout.leftMargin + this.seperateLayout.leftMargin, 0, this.bgLayout.leftMargin + this.seperateLayout.leftMargin + this.seperateLayout.width, this.seperateLayout.height);
    this.mAlertTitle.measure(this.bgLayout.leftMargin, 0, this.bgLayout.leftMargin + this.titleLayout.width, this.titleLayout.height);
    this.mAlertTitle.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mContent.measure(this.bgLayout.leftMargin + this.textLayout.leftMargin, 0, this.bgLayout.leftMargin + this.textLayout.leftMargin + this.textLayout.width, this.textLayout.height);
    this.mContent.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mButtonRow.measure(this.bgLayout.leftMargin, 0, this.bgLayout.leftMargin + this.buttonLayout.width, this.buttonLayout.height);
    this.mButtonRow.setOtherParams(this.roundLayout.width, this.lineLayout.height);
    this.mCheckBox.measure(this.bgLayout.leftMargin + this.checkBgLayout.leftMargin, 0, this.bgLayout.leftMargin + this.checkBgLayout.leftMargin + this.checkBgLayout.width, this.checkBgLayout.height);
    this.mTip.measure(this.mCheckBox.getRightMargin() + this.tipLayout.leftMargin, this.mCheckBox.getTopMargin(), this.mCheckBox.getRightMargin() + this.tipLayout.leftMargin + this.tipLayout.width, this.mCheckBox.getBottomMargin());
    this.mTip.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.mAlertParam = ((AlertParam)paramObject);
      this.mContent.setText(this.mAlertParam.getMsg());
      this.mButtonRow.setButtons(this.mAlertParam.getButtons());
      this.mHasTip = this.mAlertParam.hasForbidBox();
      if (!this.mHasTip)
        break label87;
      this.mCheckBox.setVisible(0);
      this.mTip.setVisible(0);
    }
    while (true)
    {
      invalidate();
      return;
      label87: this.mCheckBox.setVisible(4);
      this.mTip.setVisible(4);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.AlertPopView
 * JD-Core Version:    0.6.2
 */