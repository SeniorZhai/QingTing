package fm.qingting.qtradio.view.personalcenter.mydownload;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.adapter.IAdapterIViewFactory;
import fm.qingting.framework.adapter.ItemParam;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.tween.FrameTween;
import fm.qingting.framework.tween.FrameTween.SyncType;
import fm.qingting.framework.tween.IMotionHandler;
import fm.qingting.framework.tween.MotionController;
import fm.qingting.framework.tween.TweenProperty;
import fm.qingting.framework.tween.easing.Quad.EaseIn;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ListViewImpl;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.manager.QtApiLevelManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.DownLoadInfoNode;
import fm.qingting.qtradio.model.DownLoadInfoNode.IDownloadInfoEventListener;
import fm.qingting.qtradio.model.Download;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.MutiCheckManageableAdapter;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.MiniPlayerView;
import fm.qingting.qtradio.view.popviews.AlertParam.Builder;
import fm.qingting.qtradio.view.popviews.AlertParam.OnButtonClickListener;
import fm.qingting.qtradio.view.scheduleview.SizeInfo;
import fm.qingting.utils.FuncUtils;
import fm.qingting.utils.ListUtils;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyDownloadProgramView extends ViewGroupViewImpl
  implements IEventHandler, IMotionHandler, DownLoadInfoNode.IDownloadInfoEventListener
{
  private MutiCheckManageableAdapter adapter;
  private final ViewLayout checkbgLayout = this.standardLayout.createChildLT(48, 48, 30, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout downloadMoreLayout = this.standardLayout.createChildLT(720, 100, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private DownloadMoreView downloadMoreView;
  private IAdapterIViewFactory factory;
  private int mButtonOffset = 0;
  private GeneralManageView mConfirmView;
  private List<Node> mList;
  private ListViewImpl mListView;
  private MiniPlayerView mPlayerView;
  private SortTagView mTagView;
  private MotionController motionController;
  private final ViewLayout orderLayout = this.standardLayout.createChildLT(720, 80, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private StorageInfoView storageView;

  public MyDownloadProgramView(Context paramContext)
  {
    super(paramContext);
    try
    {
      setBackgroundColor(SkinManager.getBackgroundColor());
      this.factory = new IAdapterIViewFactory()
      {
        public IView createView(int paramAnonymousInt)
        {
          return new MyDownloadItemView(MyDownloadProgramView.this.getContext(), this.val$hash);
        }
      };
      this.adapter = new MutiCheckManageableAdapter(new ArrayList(), this.factory);
      this.adapter.setEventHandler(this);
      this.mListView = new ListViewImpl(paramContext);
      addView(this.mListView);
      this.mListView.setVerticalScrollBarEnabled(false);
      this.mListView.setVerticalFadingEdgeEnabled(false);
      this.mListView.setCacheColorHint(0);
      this.mListView.setDivider(null);
      this.mListView.setHeaderDividersEnabled(false);
      this.mListView.setSelector(17170445);
      this.mListView.setAdapter(this.adapter);
      this.downloadMoreView = new DownloadMoreView(paramContext);
      addView(this.downloadMoreView);
      this.storageView = new StorageInfoView(paramContext);
      addView(this.storageView);
      this.mPlayerView = new MiniPlayerView(paramContext);
      addView(this.mPlayerView);
      this.mConfirmView = new GeneralManageView(paramContext);
      this.mConfirmView.setEventHandler(this);
      addView(this.mConfirmView);
      this.mTagView = new SortTagView(paramContext);
      addView(this.mTagView);
      this.mTagView.setEventHandler(this);
      InfoManager.getInstance().root().mDownLoadInfoNode.registerListener(this);
      init();
      return;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
        localOutOfMemoryError.printStackTrace();
    }
  }

  private void caculateStorage(List<Node> paramList)
  {
    if (paramList == null)
      return;
    int j;
    int m;
    int k;
    if (paramList != null)
    {
      paramList = paramList.iterator();
      int i = 0;
      j = 0;
      m = i;
      k = j;
      if (!paramList.hasNext())
        break label79;
      ProgramNode localProgramNode = (ProgramNode)paramList.next();
      if (localProgramNode.downloadInfo == null)
        break label97;
      i = localProgramNode.downloadInfo.fileSize + i;
    }
    label79: label97: 
    while (true)
    {
      j += 1;
      break;
      m = 0;
      k = 0;
      this.storageView.update("setUsageInfo", SizeInfo.getStorageInfo(k, m));
      return;
    }
  }

  private void deleteSelected(List<Object> paramList)
  {
    if ((paramList != null) && (paramList.size() > 0))
    {
      int i = paramList.size() - 1;
      while (i >= 0)
      {
        InfoManager.getInstance().root().mDownLoadInfoNode.delDownLoad((ProgramNode)paramList.get(i), true);
        i -= 1;
      }
    }
    this.adapter.resetCheck();
  }

  @TargetApi(11)
  private void hideDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { 0.0F });
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.mPlayerView, "alpha", new float[] { 1.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject).with(localObjectAnimator);
      localAnimatorSet.setDuration(200L);
      localAnimatorSet.start();
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(new TweenProperty("position", 1.0F, 0.0F, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, (List)localObject, FrameTween.SyncType.ASYNC);
  }

  @TargetApi(11)
  private void init()
  {
    if (!QtApiLevelManager.isApiLevelSupported(11))
      this.motionController = new MotionController(this);
  }

  private void layoutMoveableViews()
  {
    this.mConfirmView.layout(0, this.mButtonOffset + this.standardLayout.height, this.standardLayout.width, this.mButtonOffset + this.standardLayout.height + this.mPlayerView.getHeightWithoutShadow());
    this.storageView.layout(0, this.standardLayout.height - this.mPlayerView.getHeightWithoutShadow() - this.storageView.getMeasuredHeight(), this.standardLayout.width, this.standardLayout.height - this.mPlayerView.getHeightWithoutShadow());
    this.mPlayerView.layout(0, this.standardLayout.height - this.mPlayerView.getMeasuredHeight() - this.mButtonOffset, this.standardLayout.width, this.standardLayout.height - this.mButtonOffset);
  }

  @TargetApi(11)
  private void showDeleteButton()
  {
    if (QtApiLevelManager.isApiLevelSupported(11))
    {
      localObject = ObjectAnimator.ofFloat(this.mConfirmView, "translationY", new float[] { -this.mPlayerView.getHeightWithoutShadow() });
      ObjectAnimator localObjectAnimator = ObjectAnimator.ofFloat(this.mPlayerView, "alpha", new float[] { 0.0F });
      AnimatorSet localAnimatorSet = new AnimatorSet();
      localAnimatorSet.play((Animator)localObject).with(localObjectAnimator);
      localAnimatorSet.setDuration(200L);
      localAnimatorSet.start();
      return;
    }
    Object localObject = new ArrayList();
    ((List)localObject).add(new TweenProperty("position", 0.0F, 1.0F, 10.0F, new Quad.EaseIn()));
    FrameTween.to(this.motionController, this, (List)localObject, FrameTween.SyncType.ASYNC);
  }

  public void close(boolean paramBoolean)
  {
    this.mListView.close(paramBoolean);
    this.storageView.close(paramBoolean);
    this.mPlayerView.destroy();
    InfoManager.getInstance().root().mDownLoadInfoNode.unregisterListener(this);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onDownLoadInfoUpdated(int paramInt, Node paramNode)
  {
    if (paramInt == 1)
      this.adapter.notifyDataSetChanged();
  }

  public void onEvent(final Object paramObject1, String paramString, Object paramObject2)
  {
    int i;
    if (paramString.equalsIgnoreCase("itemCallback"))
    {
      i = ((ItemParam)paramObject2).position;
      this.adapter.checkIndex(i);
    }
    label371: 
    while (true)
    {
      return;
      if (paramString.equalsIgnoreCase("stateChanged"))
      {
        if (((Boolean)paramObject2).booleanValue())
        {
          this.mConfirmView.update(paramString, Boolean.valueOf(true));
          return;
        }
        if (!this.adapter.hasCheckedIndexs())
          this.mConfirmView.update(paramString, Boolean.valueOf(false));
      }
      else
      {
        if (paramString.equalsIgnoreCase("selectAll"))
        {
          if (((Boolean)paramObject2).booleanValue())
          {
            this.adapter.checkAll();
            return;
          }
          this.adapter.resetCheck();
          return;
        }
        if (paramString.equalsIgnoreCase("delete"))
        {
          paramString = this.adapter.getCheckList();
          paramObject2 = this.adapter.getData();
          if ((paramString != null) && (paramObject2 != null))
          {
            paramObject1 = new ArrayList();
            while (paramString.hasNext())
            {
              i = ((Integer)paramString.next()).intValue();
              if ((i >= 0) && (i < paramObject2.size()))
                paramObject1.add(paramObject2.get(i));
            }
            if ((paramObject1 == null) || (paramObject1.size() <= 0));
          }
        }
        else
        {
          for (i = paramObject1.size() + 0; ; i = 0)
          {
            if (i == 0)
              break label371;
            paramString = "确认删除这" + i + "个节目吗？";
            paramObject1 = new AlertParam.Builder().setMessage(paramString).addButton("取消").addButton("确定").setListener(new AlertParam.OnButtonClickListener()
            {
              public void onClick(int paramAnonymousInt, boolean paramAnonymousBoolean)
              {
                switch (paramAnonymousInt)
                {
                default:
                  return;
                case 0:
                  EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
                  return;
                case 1:
                }
                EventDispacthManager.getInstance().dispatchAction("cancelPop", null);
                MyDownloadProgramView.this.deleteSelected(paramObject1);
              }
            }).create();
            EventDispacthManager.getInstance().dispatchAction("showAlert", paramObject1);
            return;
            if (!paramString.equalsIgnoreCase("converseOrder"))
              break;
            FuncUtils.revertNodesList(this.mList);
            this.adapter.setData(ListUtils.convertToObjectList(this.mList));
            QTMSGManage.getInstance().sendStatistcsMessage("revertDownload");
            return;
          }
        }
      }
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.downloadMoreView.layout(0, this.orderLayout.height, this.standardLayout.width, this.orderLayout.height + this.downloadMoreLayout.height);
    this.mListView.layout(0, this.orderLayout.height + this.downloadMoreLayout.height, this.standardLayout.width, this.standardLayout.height - this.storageView.getMeasuredHeight() - this.mPlayerView.getHeightWithoutShadow());
    this.orderLayout.layoutView(this.mTagView);
    layoutMoveableViews();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.checkbgLayout.scaleToBounds(this.standardLayout);
    this.orderLayout.scaleToBounds(this.standardLayout);
    this.downloadMoreLayout.scaleToBounds(this.standardLayout);
    this.standardLayout.measureView(this.mConfirmView);
    this.standardLayout.measureView(this.storageView);
    this.orderLayout.measureView(this.mTagView);
    this.standardLayout.measureView(this.mPlayerView);
    this.downloadMoreLayout.measureView(this.downloadMoreView);
    this.mListView.measure(this.standardLayout.getWidthMeasureSpec(), View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - this.storageView.getMeasuredHeight() - this.orderLayout.height - this.mPlayerView.getHeightWithoutShadow(), 1073741824));
    setMeasuredDimension(paramInt1, paramInt2);
  }

  public void onMotionCancel(MotionController paramMotionController)
  {
  }

  public void onMotionComplete(MotionController paramMotionController)
  {
  }

  public void onMotionProgress(MotionController paramMotionController, float paramFloat1, float paramFloat2)
  {
    this.mButtonOffset = ((int)(-this.mPlayerView.getHeightWithoutShadow() * paramFloat1));
    layoutMoveableViews();
  }

  public void onMotionStart(MotionController paramMotionController)
  {
  }

  public void onTargetChange(MotionController paramMotionController, float paramFloat)
  {
  }

  public void setChannel(ChannelNode paramChannelNode)
  {
    this.downloadMoreView.setChannel(paramChannelNode);
  }

  public void update(final String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("showManage"))
    {
      this.adapter.showManage(this.checkbgLayout.leftMargin + this.checkbgLayout.width);
      showDeleteButton();
      this.mTagView.update("setEnable", Boolean.valueOf(false));
      return;
    }
    if (paramString.equalsIgnoreCase("hideManage"))
    {
      this.adapter.hideManage();
      hideDeleteButton();
      this.mTagView.update("setEnable", Boolean.valueOf(true));
      this.adapter.resetCheck();
      return;
    }
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mList = ((List)paramObject);
      paramString = new ArrayList();
      if ((this.mList != null) && (this.mList.size() > 0))
        try
        {
          paramObject = (ProgramNode)this.mList.get(0);
          paramObject = ChannelHelper.getInstance().getChannel(paramObject.channelId, paramObject.channelType).getAllLstProgramNode();
          i = 0;
          while (i < paramObject.size())
          {
            ProgramNode localProgramNode = (ProgramNode)paramObject.get(i);
            if (!paramString.contains(Integer.valueOf(localProgramNode.id)))
              paramString.add(Integer.valueOf(localProgramNode.id));
            i += 1;
          }
        }
        catch (Exception paramObject)
        {
          paramObject.printStackTrace();
        }
      Collections.sort(this.mList, new Comparator()
      {
        public int compare(Node paramAnonymousNode1, Node paramAnonymousNode2)
        {
          paramAnonymousNode1 = (ProgramNode)paramAnonymousNode1;
          paramAnonymousNode2 = (ProgramNode)paramAnonymousNode2;
          int i = paramString.indexOf(Integer.valueOf(paramAnonymousNode1.id));
          int j = paramString.indexOf(Integer.valueOf(paramAnonymousNode2.id));
          if ((i >= 0) && (j >= 0))
            if (i >= j);
          do
          {
            do
            {
              return -1;
              if (i == j)
                return 0;
              return 1;
            }
            while (i >= 0);
            if (j >= 0)
              return 1;
          }
          while (paramAnonymousNode1.sequence < paramAnonymousNode2.sequence);
          if (paramAnonymousNode1.sequence == paramAnonymousNode2.sequence)
            return 0;
          return 1;
        }
      });
      this.adapter.setData(ListUtils.convertToObjectList(this.mList));
      if (this.mList == null)
        break label342;
    }
    label342: for (int i = this.mList.size(); ; i = 0)
    {
      this.mTagView.update("setNumber", Integer.valueOf(i));
      caculateStorage(this.mList);
      return;
      if (!paramString.equalsIgnoreCase("resetCheckList"))
        break;
      caculateStorage(this.mList);
      this.adapter.resetCheck();
      return;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.mydownload.MyDownloadProgramView
 * JD-Core Version:    0.6.2
 */