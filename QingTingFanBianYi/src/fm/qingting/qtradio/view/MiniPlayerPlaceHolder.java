package fm.qingting.qtradio.view;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.ScreenConfiguration;
import java.util.Calendar;

public class MiniPlayerPlaceHolder extends QtView
{
  private TextViewElement mTextViewElement;

  private MiniPlayerPlaceHolder(Context paramContext)
  {
    super(paramContext);
    this.mTextViewElement = new TextViewElement(paramContext);
    this.mTextViewElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTextViewElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mTextViewElement.setColor(SkinManager.getTextColorHighlight());
    this.mTextViewElement.setText(getEaster(paramContext), false);
    addElement(this.mTextViewElement);
  }

  private String getEaster(Context paramContext)
  {
    paramContext = Calendar.getInstance();
    int i = paramContext.get(2);
    int j = paramContext.get(5);
    if ((i == 9) && (j == 28));
    return null;
  }

  public static void wrapListView(Context paramContext, ListView paramListView)
  {
    paramListView.addFooterView(new MiniPlayerPlaceHolder(paramContext), null, false);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt2 = ScreenConfiguration.getMiniHeight();
    this.mTextViewElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mTextViewElement.measure(0, 0, View.MeasureSpec.getSize(paramInt1), paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.MiniPlayerPlaceHolder
 * JD-Core Version:    0.6.2
 */