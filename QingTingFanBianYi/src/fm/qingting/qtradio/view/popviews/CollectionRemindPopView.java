package fm.qingting.qtradio.view.popviews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.CollectionRemindManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.CollectionNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.PersonalCenterNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.QTMSGManage;
import java.util.Locale;

public class CollectionRemindPopView extends ViewGroupViewImpl
{
  private final String TIPMODEL_TRA = "好听就收藏[%s]，帮你更快找到它";
  private final String TIPMODEL_VIRTUAL = "好听就收藏[%s]，更新及时告诉你";
  private final int USER_ACCEPT = 1;
  private final int USER_CANCEL = 0;
  private final int USER_CANCEL_FOREVER = 2;
  private final int USER_IGNORE = 3;
  private final ViewLayout leftButtonLayout = this.panelLayout.createChildLT(300, 80, 40, 230, ViewLayout.SCALE_FLAG_SLTCW);
  private Button mLeftButton;
  private ChannelNode mNode;
  private InfoPanelView mPanelView = new InfoPanelView(paramContext, hashCode());
  private Button mRightButton;
  private int mUserChoice = 3;
  private final ViewLayout panelLayout = this.standardLayout.createChildLT(720, 330, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout rightButtonLayout = this.panelLayout.createChildLT(300, 80, 380, 230, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public CollectionRemindPopView(Context paramContext)
  {
    super(paramContext);
    addView(this.mPanelView);
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramAnonymousView == CollectionRemindPopView.this.mLeftButton)
        {
          bool = ((Boolean)CollectionRemindPopView.this.mPanelView.getValue("isChecked", null)).booleanValue();
          localQTMSGManage = QTMSGManage.getInstance();
          if (bool)
          {
            paramAnonymousView = "nomore";
            localQTMSGManage.sendStatistcsMessage("cr_later", paramAnonymousView);
            paramAnonymousView = CollectionRemindPopView.this;
            if (!bool)
              break label86;
            i = 2;
            CollectionRemindPopView.access$202(paramAnonymousView, i);
            CollectionRemindPopView.this.dispatchActionEvent("cancelPop", null);
          }
        }
        label86: 
        while (paramAnonymousView != CollectionRemindPopView.this.mRightButton)
          while (true)
          {
            boolean bool;
            QTMSGManage localQTMSGManage;
            return;
            paramAnonymousView = "later";
            continue;
            int i = 0;
          }
        CollectionRemindPopView.access$202(CollectionRemindPopView.this, 1);
        InfoManager.getInstance().root().mPersonalCenterNode.myCollectionNode.addFavNode(CollectionRemindPopView.this.mNode);
        paramAnonymousView = CollectionRemindManager.getSource();
        QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteUserClickFavorite", paramAnonymousView);
        CollectionRemindPopView.this.dispatchActionEvent("cancelPop", null);
      }
    };
    paramContext = LayoutInflater.from(paramContext);
    this.mLeftButton = ((Button)paramContext.inflate(2130903054, null));
    this.mLeftButton.setText("以后再说");
    addView(this.mLeftButton);
    this.mLeftButton.setOnClickListener(local1);
    this.mRightButton = ((Button)paramContext.inflate(2130903060, null));
    this.mRightButton.setText("马上收藏");
    addView(this.mRightButton);
    this.mRightButton.setOnClickListener(local1);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (paramMotionEvent.getY() < this.standardLayout.height - this.panelLayout.height))
    {
      dispatchActionEvent("cancelPop", null);
      return true;
    }
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mPanelView.layout(0, this.standardLayout.height - this.panelLayout.height, this.standardLayout.width, this.standardLayout.height);
    this.mLeftButton.layout(this.leftButtonLayout.leftMargin, this.standardLayout.height - this.panelLayout.height + this.leftButtonLayout.topMargin, this.leftButtonLayout.getRight(), this.standardLayout.height - this.panelLayout.height + this.leftButtonLayout.getBottom());
    this.mRightButton.layout(this.rightButtonLayout.leftMargin, this.standardLayout.height - this.panelLayout.height + this.rightButtonLayout.topMargin, this.rightButtonLayout.getRight(), this.standardLayout.height - this.panelLayout.height + this.rightButtonLayout.getBottom());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.panelLayout.scaleToBounds(this.standardLayout);
    this.leftButtonLayout.scaleToBounds(this.panelLayout);
    this.rightButtonLayout.scaleToBounds(this.panelLayout);
    this.panelLayout.measureView(this.mPanelView);
    this.leftButtonLayout.measureView(this.mLeftButton);
    this.rightButtonLayout.measureView(this.mRightButton);
    this.mLeftButton.setPadding(0, 0, 0, 0);
    this.mRightButton.setPadding(0, 0, 0, 0);
    this.mLeftButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    this.mRightButton.setTextSize(0, SkinManager.getInstance().getSubTextSize());
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setBubbleData"))
    {
      this.mNode = ((ChannelNode)paramObject);
      paramObject = this.mPanelView;
      localLocale = Locale.CHINESE;
      if (this.mNode.channelType == 0)
      {
        paramString = "好听就收藏[%s]，帮你更快找到它";
        paramObject.update("setData", String.format(localLocale, paramString, new Object[] { this.mNode.title }));
      }
    }
    while (!paramString.equalsIgnoreCase("hide"))
      while (true)
      {
        Locale localLocale;
        return;
        paramString = "好听就收藏[%s]，更新及时告诉你";
      }
    paramString = CollectionRemindManager.getSource();
    switch (this.mUserChoice)
    {
    default:
      return;
    case 0:
      if (((Boolean)this.mPanelView.getValue("isChecked", null)).booleanValue())
      {
        QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteUserSelectDoNotHint", paramString);
        CollectionRemindManager.userBan(getContext(), Integer.toString(this.mNode.channelId));
        return;
      }
    case 1:
      CollectionRemindManager.userAccept(getContext(), Integer.toString(this.mNode.channelId));
      return;
    case 2:
      QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteUserSelectDoNotHint", paramString);
      CollectionRemindManager.userBan(getContext(), Integer.toString(this.mNode.channelId));
      return;
      QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteUserClickLaterOn", paramString);
      CollectionRemindManager.userIgnore(getContext(), Integer.toString(this.mNode.channelId));
      return;
    case 3:
    }
    QTMSGManage.getInstance().sendStatistcsMessage("pHintFavoriteUserClickOutside", paramString);
    CollectionRemindManager.userIgnore(getContext(), Integer.toString(this.mNode.channelId));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.popviews.CollectionRemindPopView
 * JD-Core Version:    0.6.2
 */