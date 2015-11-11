package fm.qingting.qtradio.view;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.AbsCheckBoxElement.OnCheckChangeListener;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;

class UserGuidePeopleView extends QtView
{
  static final String LAST = "uggv_last";
  static final String NEXT = "ugpv_next";
  private ViewLayout checkLayout = this.standardLayout.createChildLT(216, 280, 0, 368, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout indicatorLayout = this.standardLayout.createChildLT(720, 40, 0, 19, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout lastLayout = this.standardLayout.createChildLT(300, 40, 50, 19, ViewLayout.SCALE_FLAG_SLTCW);
  private CheckElement mElseElement;
  private TextViewElement mIndicator;
  private LastElement mLastElement;
  private ButtonViewElement mNextElement;
  private CheckElement mOlderElement;
  private CheckElement mStudentElement;
  private TextViewElement mSubTitleElement;
  private TextViewElement mTitleElement;
  private CheckElement mWorkerElement;
  private ViewLayout nextLayout = this.standardLayout.createChildLT(630, 90, 45, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout roundLayout = this.standardLayout.createChildLT(10, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout secondCheckLayout = this.standardLayout.createChildLT(216, 280, 0, 710, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1280, 720, 1280, 0, 0, ViewLayout.FILL);
  private ViewLayout subTitleLayout = this.standardLayout.createChildLT(720, 40, 0, 273, ViewLayout.SCALE_FLAG_SLTCW);
  private ViewLayout titleLayout = this.standardLayout.createChildLT(720, 70, 0, 170, ViewLayout.SCALE_FLAG_SLTCW);

  public UserGuidePeopleView(Context paramContext)
  {
    super(paramContext);
    int i = hashCode();
    setBackgroundResource(2130837998);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setMaxLineLimit(1);
    this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setText("请您选择", false);
    addElement(this.mTitleElement);
    this.mSubTitleElement = new TextViewElement(paramContext);
    this.mSubTitleElement.setMaxLineLimit(1);
    this.mSubTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mSubTitleElement.setColor(SkinManager.getTextColorSubInfo());
    this.mSubTitleElement.setText("为您推荐更合适的内容", false);
    addElement(this.mSubTitleElement);
    this.mWorkerElement = new CheckElement(paramContext);
    this.mWorkerElement.setParam("上班族", 2130838007);
    addElement(this.mWorkerElement, i);
    this.mStudentElement = new CheckElement(paramContext);
    this.mStudentElement.setParam("学生", 2130838006);
    addElement(this.mStudentElement, i);
    this.mOlderElement = new CheckElement(paramContext);
    this.mOlderElement.setParam("中老年", 2130838005);
    addElement(this.mOlderElement, i);
    this.mElseElement = new CheckElement(paramContext);
    this.mElseElement.setParam("其他", 2130838004);
    addElement(this.mElseElement, i);
    this.mWorkerElement.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          UserGuidePeopleView.this.mStudentElement.uncheck(false);
          UserGuidePeopleView.this.mOlderElement.uncheck(false);
          UserGuidePeopleView.this.mElseElement.uncheck(false);
          UserGuidePeopleView.this.mNextElement.setEnable(true);
        }
      }
    });
    this.mStudentElement.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          UserGuidePeopleView.this.mWorkerElement.uncheck(false);
          UserGuidePeopleView.this.mOlderElement.uncheck(false);
          UserGuidePeopleView.this.mElseElement.uncheck(false);
          UserGuidePeopleView.this.mNextElement.setEnable(true);
        }
      }
    });
    this.mOlderElement.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          UserGuidePeopleView.this.mWorkerElement.uncheck(false);
          UserGuidePeopleView.this.mStudentElement.uncheck(false);
          UserGuidePeopleView.this.mElseElement.uncheck(false);
          UserGuidePeopleView.this.mNextElement.setEnable(true);
        }
      }
    });
    this.mElseElement.setOnCheckChangeListener(new AbsCheckBoxElement.OnCheckChangeListener()
    {
      public void onCheckChanged(boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
        {
          UserGuidePeopleView.this.mWorkerElement.uncheck(false);
          UserGuidePeopleView.this.mOlderElement.uncheck(false);
          UserGuidePeopleView.this.mStudentElement.uncheck(false);
          UserGuidePeopleView.this.mNextElement.setEnable(true);
        }
      }
    });
    this.mIndicator = new TextViewElement(paramContext);
    this.mIndicator.setMaxLineLimit(1);
    this.mIndicator.setColor(-8355712);
    this.mIndicator.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mIndicator.setText("2/3", false);
    addElement(this.mIndicator);
    this.mNextElement = new ButtonViewElement(paramContext);
    this.mNextElement.setBackgroundColor(SkinManager.getTextColorHighlight(), SkinManager.getTextColorHighlight(), -3158065);
    this.mNextElement.setRoundCorner(true);
    this.mNextElement.setTextColor(-1, -1, -1);
    this.mNextElement.setText("下一步");
    this.mNextElement.setEnable(false);
    addElement(this.mNextElement);
    this.mNextElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        UserGuidePeopleView.this.dispatchActionEvent("ugpv_next", null);
      }
    });
    this.mLastElement = new LastElement(paramContext);
    this.mLastElement.setOnElementClickListener(new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        UserGuidePeopleView.this.dispatchActionEvent("uggv_last", null);
      }
    });
    addElement(this.mLastElement, i);
  }

  public int getSelectedPeople()
  {
    if (this.mWorkerElement.isChecked())
      return 1;
    if (this.mStudentElement.isChecked())
      return 2;
    if (this.mOlderElement.isChecked())
      return 3;
    return 4;
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.checkLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.subTitleLayout.scaleToBounds(this.standardLayout);
    this.secondCheckLayout.scaleToBounds(this.standardLayout);
    this.nextLayout.scaleToBounds(this.standardLayout);
    this.indicatorLayout.scaleToBounds(this.standardLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.lastLayout.scaleToBounds(this.standardLayout);
    paramInt1 = this.checkLayout.topMargin;
    paramInt2 = (this.standardLayout.width - this.checkLayout.width * 2) / 3;
    this.mWorkerElement.measure(paramInt2, paramInt1, this.checkLayout.width + paramInt2, this.checkLayout.height + paramInt1);
    paramInt2 = paramInt2 + paramInt2 + this.checkLayout.width;
    this.mStudentElement.measure(paramInt2, paramInt1, this.checkLayout.width + paramInt2, this.checkLayout.height + paramInt1);
    paramInt2 = (this.standardLayout.width - this.checkLayout.width * 2) / 3;
    paramInt1 = this.secondCheckLayout.topMargin;
    this.mOlderElement.measure(paramInt2, paramInt1, this.checkLayout.width + paramInt2, this.checkLayout.height + paramInt1);
    paramInt2 = paramInt2 + paramInt2 + this.checkLayout.width;
    this.mElseElement.measure(paramInt2, paramInt1, this.checkLayout.width + paramInt2, this.checkLayout.height + paramInt1);
    paramInt1 = this.titleLayout.topMargin;
    this.mTitleElement.measure(0, paramInt1, this.standardLayout.width, this.titleLayout.height + paramInt1);
    paramInt1 = this.subTitleLayout.topMargin;
    this.mSubTitleElement.measure(0, paramInt1, this.standardLayout.width, this.subTitleLayout.height + paramInt1);
    this.mNextElement.measure(this.nextLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom(), this.nextLayout.getRight(), this.standardLayout.height - this.nextLayout.topMargin);
    this.mIndicator.measure(this.indicatorLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom() - this.indicatorLayout.getBottom(), this.indicatorLayout.getRight(), this.standardLayout.height - this.nextLayout.getBottom() - this.indicatorLayout.topMargin);
    this.mLastElement.measure(this.lastLayout.leftMargin, this.standardLayout.height - this.nextLayout.getBottom() - this.lastLayout.getBottom(), this.lastLayout.getRight(), this.standardLayout.height - this.nextLayout.getBottom() - this.lastLayout.topMargin);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getLargeTextSize());
    this.mSubTitleElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mIndicator.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mNextElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mNextElement.setRoundCornerRadius(this.roundLayout.width);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.UserGuidePeopleView
 * JD-Core Version:    0.6.2
 */