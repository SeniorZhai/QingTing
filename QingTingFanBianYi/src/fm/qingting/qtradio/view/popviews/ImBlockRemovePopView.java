package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.content.res.Resources;
import android.text.Layout.Alignment;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.BlackListItem;
import fm.qingting.qtradio.im.ImBlackList;
import fm.qingting.qtradio.manager.SkinManager;
import java.util.ArrayList;
import java.util.List;

public class ImBlockRemovePopView extends QtView
{
  private final ViewLayout buttonsLayout = this.standardLayout.createChildLT(720, 200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonArrayElement mArrayElement;
  private ButtonViewElement mBg;
  private BlackListItem mMessage;
  private TextViewElement mNameElement;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(600, 60, 60, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public ImBlockRemovePopView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getNewPopBgColor(), SkinManager.getNewPopBgColor());
    addElement(this.mBg);
    Resources localResources = getResources();
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setMaxLineLimit(2);
    this.mNameElement.setColor(SkinManager.getTextColorSubInfo());
    this.mNameElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mNameElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNameElement.setText(localResources.getString(2131492890), false);
    addElement(this.mNameElement);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(new ButtonItem(localResources.getString(2131492883), 4));
    localArrayList.add(new ButtonItem(localResources.getString(2131492882), 6));
    this.mArrayElement = new ButtonArrayElement(paramContext);
    this.mArrayElement.setButtons(localArrayList);
    addElement(this.mArrayElement);
    this.mArrayElement.setOnArrayClickListenrer(new ButtonArrayElement.OnButtonArrayClickListener()
    {
      public void OnArrayClick(ViewElement paramAnonymousViewElement, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return;
        case 0:
          ImBlackList.removeFromBlackList(ImBlockRemovePopView.this.getContext(), ImBlockRemovePopView.this.mMessage);
          ImBlockRemovePopView.this.dispatchActionEvent("cancelPop", null);
          return;
        case 1:
        }
        ImBlockRemovePopView.this.dispatchActionEvent("cancelPop", null);
      }
    });
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.mBg.getTopMargin()))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.buttonsLayout.scaleToBounds(this.standardLayout);
    this.mArrayElement.measure(0, this.standardLayout.height - this.buttonsLayout.height, this.buttonsLayout.width, this.standardLayout.height);
    this.mNameElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mNameElement.measure(this.nameLayout);
    int i = this.mNameElement.getHeight();
    this.mBg.measure(0, this.standardLayout.height - this.buttonsLayout.height - i - this.nameLayout.topMargin * 2, this.standardLayout.width, this.standardLayout.height);
    this.mNameElement.setTranslationY(this.standardLayout.height - this.buttonsLayout.height - i - this.nameLayout.topMargin * 2);
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
      this.mMessage = ((BlackListItem)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.ImBlockRemovePopView
 * JD-Core Version:    0.6.2
 */