package fm.qingting.qtradio.view.personalcenter.faq;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.FaqItem;
import fm.qingting.qtradio.view.playview.LineElement;

public class FaqItemView extends QtView
{
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mAnswerElement;
  private LineElement mLineElement;
  private TextViewElement mQuestionElement;
  private final ViewLayout paddingLayout = this.standardLayout.createChildLT(720, 10, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout questionLayout = this.standardLayout.createChildLT(660, 40, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 800, 720, 800, 0, 0, ViewLayout.FILL);

  public FaqItemView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    this.mQuestionElement = new TextViewElement(paramContext);
    this.mQuestionElement.setMaxLineLimit(3);
    this.mQuestionElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mQuestionElement);
    this.mAnswerElement = new TextViewElement(paramContext);
    this.mAnswerElement.setColor(SkinManager.getTextColorSubInfo());
    this.mAnswerElement.setMaxLineLimit(20);
    addElement(this.mAnswerElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    this.mLineElement.setOrientation(1);
    addElement(this.mLineElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.questionLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.paddingLayout.scaleToBounds(this.standardLayout);
    this.mQuestionElement.measure(this.questionLayout);
    this.mQuestionElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mAnswerElement.measure(this.questionLayout);
    this.mAnswerElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mAnswerElement.setTranslationY(this.mQuestionElement.getHeight() + this.paddingLayout.height);
    paramInt1 = this.mQuestionElement.getHeight() + this.mAnswerElement.getHeight() + this.paddingLayout.height * 3;
    this.mLineElement.measure(this.lineLayout.leftMargin, paramInt1 - this.lineLayout.height, this.lineLayout.getRight(), paramInt1);
    setMeasuredDimension(this.standardLayout.width, paramInt1);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      paramString = (FaqItem)paramObject;
      this.mQuestionElement.setText(paramString.getQuestion());
      paramString = paramString.getAnswer().replace("@@@", "\n");
      this.mAnswerElement.setText(paramString);
      requestLayout();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.faq.FaqItemView
 * JD-Core Version:    0.6.2
 */