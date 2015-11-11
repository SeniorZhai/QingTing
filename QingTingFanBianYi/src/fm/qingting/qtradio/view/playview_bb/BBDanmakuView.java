package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.Button;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.utils.OnPlayProcessListener;
import fm.qingting.utils.PlayProcessSyncUtil;
import fm.qingting.utils.QTMSGManage;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import master.flame.danmaku.controller.DrawHandler.Callback;
import master.flame.danmaku.danmaku.loader.IllegalDataException;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.R2LDanmaku;
import master.flame.danmaku.danmaku.model.android.DanmakuGlobalConfig;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import master.flame.danmaku.ui.widget.DanmakuView;

public class BBDanmakuView extends ViewGroupViewImpl
  implements OnPlayProcessListener
{
  private boolean isDanmaku = true;
  private DanmakuImageView mDanmakuImageView;
  private DanmakuView mDanmakuView;
  private List<IMMessage> mImageBarrages;
  private BaseDanmakuParser mParser;
  private int mStartIndex = -1;
  private Button mSwitchButton;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 574, 720, 574, 0, 0, ViewLayout.FILL);
  private final ViewLayout switchLayout = this.standardLayout.createChildLT(128, 50, 572, 20, ViewLayout.SCALE_FLAG_SLTCW);

  public BBDanmakuView(Context paramContext)
  {
    super(paramContext);
    this.mDanmakuImageView = new DanmakuImageView(paramContext);
    addView(this.mDanmakuImageView);
    this.mDanmakuView = new DanmakuView(paramContext);
    addView(this.mDanmakuView);
    DanmakuGlobalConfig.DEFAULT.setDanmakuStyle(0, new float[] { 3.0F }).setDuplicateMergingEnabled(false);
    this.mDanmakuView.enableDanmakuDrawingCache(true);
    PlayProcessSyncUtil.getInstance().addListener(this);
    this.mSwitchButton = new Button(paramContext);
    this.mSwitchButton.setBackgroundResource(2130837510);
    this.mSwitchButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        BBDanmakuView.this.switchButton();
      }
    });
    addView(this.mSwitchButton);
  }

  private BaseDanmakuParser createParser(List<IMMessage> paramList)
  {
    IMMessageDanmakuLoader localIMMessageDanmakuLoader = IMMessageDanmakuLoader.instance();
    try
    {
      localIMMessageDanmakuLoader.load(paramList);
      paramList = new BBDanmakuParserNew();
      paramList.load(localIMMessageDanmakuLoader.getDataSource());
      return paramList;
    }
    catch (IllegalDataException paramList)
    {
      while (true)
        paramList.printStackTrace();
    }
  }

  private void pickImageBarrage()
  {
    if ((this.mImageBarrages == null) || (this.mImageBarrages.size() == 0));
    label176: 
    while (true)
    {
      return;
      int j = PlayProcessSyncUtil.getInstance().getCurrentPlayTime();
      int i = 0;
      while (true)
      {
        if (i >= this.mImageBarrages.size())
          break label176;
        IMMessage localIMMessage = (IMMessage)this.mImageBarrages.get(i);
        if (j >= localIMMessage.publish)
          if (i < this.mImageBarrages.size() - 1)
          {
            if ((j < ((IMMessage)this.mImageBarrages.get(i + 1)).publish) && (this.mStartIndex != i))
            {
              this.mStartIndex = i;
              this.mDanmakuImageView.setDanmaku(localIMMessage);
              preloadNextImage(((IMMessage)this.mImageBarrages.get(i + 1)).mImage);
            }
          }
          else
          {
            if (this.mStartIndex == i)
              break;
            this.mStartIndex = i;
            this.mDanmakuImageView.setDanmaku(localIMMessage);
            return;
          }
        i += 1;
      }
    }
  }

  private void preloadNextImage(String paramString)
  {
    ImageLoader.getInstance(getContext()).getImage(paramString, 0, 0);
  }

  private void sortImageBarrages()
  {
    if (this.mImageBarrages == null)
      return;
    Collections.sort(this.mImageBarrages, new Comparator()
    {
      public int compare(IMMessage paramAnonymousIMMessage1, IMMessage paramAnonymousIMMessage2)
      {
        long l1 = paramAnonymousIMMessage1.publish;
        long l2 = paramAnonymousIMMessage2.publish;
        if (l1 == l2)
          return 0;
        if (l1 < l2)
          return -1;
        return 1;
      }
    });
  }

  private void switchButton()
  {
    if (this.isDanmaku)
    {
      QTMSGManage.getInstance().sendStatistcsMessage("danmaku_switch", "open");
      this.isDanmaku = false;
      this.mSwitchButton.setBackgroundResource(2130837511);
      this.mDanmakuView.hide();
      return;
    }
    QTMSGManage.getInstance().sendStatistcsMessage("danmaku_switch", "close");
    this.isDanmaku = true;
    this.mSwitchButton.setBackgroundResource(2130837510);
    this.mDanmakuView.show();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.mDanmakuImageView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mDanmakuView.layout(0, 0, paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.mSwitchButton.layout(this.switchLayout.leftMargin, this.switchLayout.topMargin, this.switchLayout.getRight(), this.switchLayout.topMargin + this.switchLayout.height);
  }

  public void onManualSeek()
  {
    pickImageBarrage();
    this.mDanmakuView.seekTo(Long.valueOf(PlayProcessSyncUtil.getInstance().getCurrentPlayTime() * 1000));
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.switchLayout.scaleToBounds(this.standardLayout);
    this.switchLayout.measureView(this.mSwitchButton);
    this.mDanmakuImageView.measure(paramInt1, paramInt2);
    this.mDanmakuView.measure(paramInt1, paramInt2);
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
  }

  public void onProcessChanged()
  {
    pickImageBarrage();
  }

  public void onProcessMaxChanged()
  {
  }

  public void onProgressPause()
  {
    this.mDanmakuView.pause();
  }

  public void onProgressResume()
  {
    this.mDanmakuView.resume();
  }

  public void update(String paramString, Object paramObject)
  {
    int j = 1;
    if (paramString.equalsIgnoreCase("setImageBarrage"))
    {
      this.mDanmakuImageView.setThumb();
      this.mDanmakuImageView.resetDanmaku();
      this.mStartIndex = -1;
      this.mImageBarrages = ((List)paramObject);
      sortImageBarrages();
      pickImageBarrage();
    }
    label307: 
    do
    {
      do
      {
        return;
        if (paramString.equalsIgnoreCase("setTxtBarrage"))
        {
          this.mDanmakuView.release();
          if (this.mParser != null)
          {
            this.mParser.release();
            this.mParser = null;
          }
          this.mParser = createParser((List)paramObject);
          this.mDanmakuView.setCallback(new DrawHandler.Callback()
          {
            public void prepared()
            {
              if (PlayerAgent.getInstance().isPlaying())
                BBDanmakuView.this.mDanmakuView.start(PlayProcessSyncUtil.getInstance().getCurrentPlayTime() * 1000);
            }

            public void updateTimer(DanmakuTimer paramAnonymousDanmakuTimer)
            {
            }
          });
          this.mDanmakuView.prepare(this.mParser);
          return;
        }
        if (paramString.equalsIgnoreCase("addDanmaku"))
        {
          paramString = (HashMap)paramObject;
          paramObject = InfoManager.getInstance().getUserProfile().getUserInfo();
          R2LDanmaku localR2LDanmaku = new R2LDanmaku(new Duration(5000L));
          localR2LDanmaku.time = (this.mDanmakuView.getCurrentTime() + 1000L);
          localR2LDanmaku.textSize = SkinManager.getInstance().getNormalTextSize();
          localR2LDanmaku.textColor = -11908534;
          int i = j;
          if (paramObject != null)
          {
            if (paramObject.snsInfo.sns_gender == null)
              i = j;
          }
          else
            if (i == 0)
              break label307;
          for (i = 2130837578; ; i = 2130837588)
          {
            localR2LDanmaku.drawableLeftResid = i;
            localR2LDanmaku.textShadowColor = 0;
            DanmakuFactory.fillText(localR2LDanmaku, (String)paramString.get("text"));
            localR2LDanmaku.index = 0;
            localR2LDanmaku.setTimer(this.mParser.getTimer());
            this.mDanmakuView.addDanmaku(localR2LDanmaku);
            return;
            i = j;
            if (!paramObject.snsInfo.sns_gender.equalsIgnoreCase("m"))
              break;
            i = 0;
            break;
          }
        }
      }
      while (!paramString.equalsIgnoreCase("setSendBarrage"));
      paramString = (IMMessage)paramObject;
    }
    while ((paramString.mImage == null) || (paramString.mImage.equals("")));
    this.mImageBarrages.add(paramString);
    sortImageBarrages();
    pickImageBarrage();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.BBDanmakuView
 * JD-Core Version:    0.6.2
 */