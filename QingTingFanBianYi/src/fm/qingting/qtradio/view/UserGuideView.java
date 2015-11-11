package fm.qingting.qtradio.view;

import android.content.Context;
import android.view.KeyEvent;
import android.view.View.MeasureSpec;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class UserGuideView extends ViewGroupViewImpl
  implements IEventHandler
{
  static long DURATION = 0L;
  public static final String SHOWMAIN = "showmain";
  private UserGuideCategoryView mCategoryView;
  private int mCurrentPage = 0;
  private UserGuideGenderView mGenderView;
  private UserGuidePeopleView mPeopleView;

  public UserGuideView(Context paramContext)
  {
    super(paramContext);
    this.mCategoryView = new UserGuideCategoryView(paramContext);
    addView(this.mCategoryView);
    this.mCategoryView.setEventHandler(this);
    this.mCategoryView.setVisibility(8);
    this.mPeopleView = new UserGuidePeopleView(paramContext);
    this.mPeopleView.setVisibility(8);
    this.mPeopleView.setEventHandler(this);
    addView(this.mPeopleView);
    this.mGenderView = new UserGuideGenderView(paramContext);
    addView(this.mGenderView);
    this.mGenderView.setEventHandler(this);
  }

  private void changeView(int paramInt)
  {
    int i;
    TranslateAnimation localTranslateAnimation;
    if (paramInt > this.mCurrentPage)
    {
      i = 1;
      if (i == 0)
        break label107;
      localTranslateAnimation = new TranslateAnimation(1, 0.0F, 1, -1.0F, 1, 0.0F, 1, 0.0F);
      localTranslateAnimation.setDuration(DURATION);
      switch (paramInt)
      {
      default:
        label38: label64: switch (this.mCurrentPage)
        {
        case 2:
        default:
        case 0:
        case 1:
        }
        break;
      case 0:
      case 1:
      case 2:
      }
    }
    while (true)
    {
      this.mCurrentPage = paramInt;
      return;
      i = 0;
      break;
      label107: localTranslateAnimation = new TranslateAnimation(1, -1.0F, 1, 0.0F, 1, 0.0F, 1, 0.0F);
      localTranslateAnimation.setDuration(DURATION);
      break label38;
      this.mGenderView.setVisibility(0);
      localTranslateAnimation.setFillAfter(true);
      this.mGenderView.startAnimation(localTranslateAnimation);
      break label64;
      this.mPeopleView.setVisibility(0);
      if (i != 0)
        break label64;
      localTranslateAnimation.setFillAfter(true);
      this.mPeopleView.startAnimation(localTranslateAnimation);
      break label64;
      this.mCategoryView.setVisibility(0);
      this.mCategoryView.setData();
      if ((!InfoManager.getInstance().hasConnectedNetwork()) || (InfoManager.getInstance().getDefaultSpecialTopic() == 0))
        break label64;
      ControllerManager.getInstance().openSpecialTopicController(InfoManager.getInstance().getDefaultSpecialTopic());
      break label64;
      this.mGenderView.startAnimation(localTranslateAnimation);
      localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          UserGuideView.this.mGenderView.setVisibility(8);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      continue;
      if (i != 0)
      {
        this.mPeopleView.startAnimation(localTranslateAnimation);
        localTranslateAnimation.setAnimationListener(new Animation.AnimationListener()
        {
          public void onAnimationEnd(Animation paramAnonymousAnimation)
          {
            UserGuideView.this.mPeopleView.setVisibility(8);
          }

          public void onAnimationRepeat(Animation paramAnonymousAnimation)
          {
          }

          public void onAnimationStart(Animation paramAnonymousAnimation)
          {
          }
        });
      }
    }
  }

  private void sendLog(int paramInt1, int paramInt2, List<CategoryNode> paramList)
  {
    String str2;
    String str1;
    if (paramInt1 == 1)
    {
      str2 = "male";
      QTMSGManage.getInstance().sendStatistcsMessage("ug_gender", str2);
      if (paramInt2 != 1)
        break label106;
      str1 = "worker";
      label28: QTMSGManage.getInstance().sendStatistcsMessage("ug_status", str1);
      str1 = "ug" + "_" + str2 + "_" + str1;
      if ((paramList != null) && (paramList.size() != 0))
        break label137;
      QTMSGManage.getInstance().sendStatistcsMessage(str1, "空空如也");
    }
    while (true)
    {
      return;
      str2 = "female";
      break;
      label106: if (paramInt2 == 2)
      {
        str1 = "student";
        break label28;
      }
      if (paramInt2 == 3)
      {
        str1 = "aged";
        break label28;
      }
      str1 = "other";
      break label28;
      label137: paramInt1 = 0;
      while (paramInt1 < paramList.size())
      {
        QTMSGManage.getInstance().sendStatistcsMessage(str1, ((CategoryNode)paramList.get(paramInt1)).name);
        paramInt1 += 1;
      }
    }
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getKeyCode() == 4) && (paramKeyEvent.getAction() == 1))
    {
      if (this.mCurrentPage > 0)
      {
        changeView(this.mCurrentPage - 1);
        return true;
      }
      return false;
    }
    return false;
  }

  public boolean dispatchKeyEventPreIme(KeyEvent paramKeyEvent)
  {
    return super.dispatchKeyEventPreIme(paramKeyEvent);
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("uggv_next"))
      changeView(1);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("ugpv_next"))
      {
        changeView(2);
        return;
      }
      if (paramString.equalsIgnoreCase("uggv_last"))
      {
        changeView(0);
        return;
      }
      if (paramString.equalsIgnoreCase("ugcv_last"))
      {
        changeView(1);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("ugcv_start"));
    int i = this.mPeopleView.getSelectedPeople();
    InfoManager.getInstance().setUserStatus(i);
    int j = this.mGenderView.getSelectedGender();
    InfoManager.getInstance().setChooseGender(j);
    paramObject1 = this.mCategoryView.getSelectedCategory();
    sendLog(j, i, paramObject1);
    ABTest.getInstance().startABTestForUser();
    dispatchActionEvent("showmain", paramObject1);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mCategoryView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mPeopleView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mGenderView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    SkinManager.getInstance().calculateFontSize(View.MeasureSpec.getSize(paramInt1));
    this.mCategoryView.measure(paramInt1, paramInt2);
    this.mPeopleView.measure(paramInt1, paramInt2);
    this.mGenderView.measure(paramInt1, paramInt2);
    super.onMeasure(paramInt1, paramInt2);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.UserGuideView
 * JD-Core Version:    0.6.2
 */