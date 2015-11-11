package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.ScreenConfiguration;
import fm.qingting.utils.UserDataUtil;
import fm.qingting.utils.UserDataUtil.UserDataResponse;
import java.util.ArrayList;
import java.util.List;

public class ChannelDetailCoverView extends ViewGroupViewImpl
  implements View.OnClickListener, UserDataUtil.UserDataResponse
{
  private final ViewLayout infoLayout = this.standardLayout.createChildLT(720, 315, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private String mBackgroundUrl;
  private ChannelNode mChannelNode;
  private ChannelDetailInfoView mInfoView;
  private boolean mIsAdBackgroundUrl;
  private boolean mIsNovel;
  private ChannelOperateView mOperateView;
  private ChannelDetailTagView mTitleView;
  private final ViewLayout operateLayout = this.standardLayout.createChildLT(720, 101, 0, 315, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 484, 720, 484, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 68, 0, 416, ViewLayout.SCALE_FLAG_SLTCW);

  public ChannelDetailCoverView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-1);
    this.mInfoView = new ChannelDetailInfoView(paramContext);
    addView(this.mInfoView);
    this.mOperateView = new ChannelOperateView(paramContext);
    addView(this.mOperateView);
    this.mTitleView = new ChannelDetailTagView(paramContext);
    this.mTitleView.setTagName("节目列表");
    this.mTitleView.setButton(false, false);
    this.mTitleView.setOnButtonClickListener(this);
    addView(this.mTitleView);
  }

  private String getCoverUrl(ChannelNode paramChannelNode)
  {
    if (paramChannelNode == null);
    do
    {
      return null;
      paramChannelNode = InfoManager.getInstance().root().mAdvertisementInfoNode.getAdvertisement(paramChannelNode.channelId);
    }
    while ((paramChannelNode == null) || (paramChannelNode.skin == null) || (paramChannelNode.skin.equalsIgnoreCase("")));
    this.mIsAdBackgroundUrl = true;
    return paramChannelNode.skin;
  }

  private void internalSetBackground(Bitmap paramBitmap)
  {
    setBackgroundDrawable(new TrimedBackgroundDrawable(paramBitmap));
  }

  private void setBackgroundByUrl(String paramString)
  {
    this.mBackgroundUrl = paramString;
    int i = ScreenConfiguration.getWidth();
    int j = ScreenConfiguration.getHeight();
    Object localObject = ImageLoader.getInstance(getContext()).getImage(paramString, i, j);
    if (localObject == null)
    {
      localObject = new ImageLoaderHandler()
      {
        public void loadImageFinish(boolean paramAnonymousBoolean, String paramAnonymousString, Bitmap paramAnonymousBitmap, int paramAnonymousInt1, int paramAnonymousInt2)
        {
          if (TextUtils.equals(ChannelDetailCoverView.this.mBackgroundUrl, paramAnonymousString))
            ChannelDetailCoverView.this.setBackgroundUsingBitmap(paramAnonymousBitmap);
        }

        public void updateImageViewFinish(boolean paramAnonymousBoolean, ImageView paramAnonymousImageView, String paramAnonymousString, Bitmap paramAnonymousBitmap)
        {
        }
      };
      ImageLoader.getInstance(getContext()).loadImage(paramString, null, this, (ImageLoaderHandler)localObject);
      return;
    }
    setBackgroundUsingBitmap((Bitmap)localObject);
  }

  private void setBackgroundUsingBitmap(Bitmap paramBitmap)
  {
    if ((this.mBackgroundUrl == null) || (paramBitmap == null));
    while (true)
    {
      return;
      try
      {
        if (this.mIsAdBackgroundUrl)
        {
          internalSetBackground(paramBitmap);
          return;
        }
      }
      catch (Exception paramBitmap)
      {
      }
      catch (Error paramBitmap)
      {
      }
    }
  }

  private void updateTitleView(ChannelNode paramChannelNode)
  {
    boolean bool = false;
    if (paramChannelNode.programCnt > 0)
      this.mTitleView.setTagName(String.format("共%d期", new Object[] { Integer.valueOf(paramChannelNode.programCnt) }));
    this.mIsNovel = paramChannelNode.isNovelChannel();
    if (this.mIsNovel)
      return;
    int i = InfoManager.getInstance().root().getProgramListOrder(paramChannelNode.channelId);
    this.mTitleView.setButtonRes(new int[] { 2130837709, 2130837710 });
    paramChannelNode = this.mTitleView;
    if (i == 1)
      bool = true;
    paramChannelNode.setButtonChecked(bool);
    this.mTitleView.setButton(true, true);
  }

  public void close(boolean paramBoolean)
  {
    this.mInfoView.close(paramBoolean);
    this.mOperateView.close(paramBoolean);
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  public void onClick(View paramView)
  {
    if (this.mIsNovel)
    {
      dispatchActionEvent("showChapters", null);
      return;
    }
    boolean bool = this.mTitleView.getButtonChecked();
    if (bool);
    for (int i = 1; ; i = 0)
    {
      dispatchActionEvent("changeOrder", Integer.valueOf(i));
      MobclickAgent.onEvent(getContext(), "changeorder", String.valueOf(bool));
      return;
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.infoLayout.layoutView(this.mInfoView);
    this.operateLayout.layoutView(this.mOperateView);
    this.titleLayout.layoutView(this.mTitleView);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.infoLayout.scaleToBounds(this.standardLayout);
    this.operateLayout.scaleToBounds(this.standardLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.infoLayout.measureView(this.mInfoView);
    this.operateLayout.measureView(this.mOperateView);
    this.titleLayout.measureView(this.mTitleView);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onResponse()
  {
    this.mInfoView.update("setData", this.mChannelNode);
  }

  public void setButtonEnable(boolean paramBoolean)
  {
    this.mTitleView.setButtonEnabled(paramBoolean);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("ca"))
      this.mInfoView.update(paramString, paramObject);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("setData"))
      {
        this.mChannelNode = ((ChannelNode)paramObject);
        this.mInfoView.update(paramString, paramObject);
        this.mOperateView.update(paramString, paramObject);
        updateTitleView(this.mChannelNode);
        paramString = new ArrayList();
        paramString.add(this.mChannelNode);
        UserDataUtil.request(this, paramString);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setpodcasterinfo"));
    this.mInfoView.update(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.ChannelDetailCoverView
 * JD-Core Version:    0.6.2
 */