package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.content.res.Resources;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.List;

public class MainMenuView extends QtView
{
  private final ViewLayout lineLayout = this.standardLayout.createChildLT(720, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private ButtonArrayElement mArrayElement;
  private ButtonViewElement mBg;
  private final ViewLayout menuLayout = this.standardLayout.createChildLT(720, 400, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public MainMenuView(Context paramContext)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(SkinManager.getNewPopBgColor(), SkinManager.getNewPopBgColor());
    addElement(this.mBg);
    this.mArrayElement = new ButtonArrayElement(paramContext);
    addElement(this.mArrayElement);
    paramContext = new ArrayList();
    Resources localResources = getResources();
    paramContext.add(new ButtonItem(localResources.getString(2131492884), 0));
    paramContext.add(new ButtonItem(localResources.getString(2131492885), 0));
    paramContext.add(new ButtonItem(localResources.getString(2131492886), 0));
    paramContext.add(new ButtonItem(localResources.getString(2131492887), 0));
    this.mArrayElement.setButtons(paramContext);
    this.mArrayElement.setOnArrayClickListenrer(new ButtonArrayElement.OnButtonArrayClickListener()
    {
      public void OnArrayClick(ViewElement paramAnonymousViewElement, int paramAnonymousInt)
      {
        switch (paramAnonymousInt)
        {
        default:
          return;
        case 0:
          MainMenuView.this.dispatchActionEvent("cancelPop", null);
          ControllerManager.getInstance().redirectToMyCollectionView();
          return;
        case 1:
          MainMenuView.this.dispatchActionEvent("cancelPop", null);
          ControllerManager.getInstance().redirectToHistoryView();
          return;
        case 2:
          MainMenuView.this.dispatchActionEvent("cancelPop", null);
          ControllerManager.getInstance().redirectToSearchView(false);
          QTMSGManage.getInstance().sendStatistcsMessage("search_frommenu");
          return;
        case 3:
        }
        MainMenuView.this.dispatchActionEvent("showQuitAlert", null);
      }
    });
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.standardLayout.height - this.menuLayout.height))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.menuLayout.scaleToBounds(this.standardLayout);
    this.lineLayout.scaleToBounds(this.standardLayout);
    this.mArrayElement.measure(0, this.standardLayout.height - this.menuLayout.height, this.menuLayout.width, this.standardLayout.height);
    this.mBg.measure(0, this.standardLayout.height - this.menuLayout.height, this.menuLayout.width, this.standardLayout.height);
    this.mArrayElement.setOtherParams(this.lineLayout.height);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.MainMenuView
 * JD-Core Version:    0.6.2
 */