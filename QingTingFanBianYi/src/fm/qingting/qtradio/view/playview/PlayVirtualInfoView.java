package fm.qingting.qtradio.view.playview;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.utils.ScreenConfiguration;
import java.util.List;

public class PlayVirtualInfoView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final int THUMBBOUND;
  private final ViewLayout infoLayout;
  private int mAbsoluteTranslationY;
  private TextViewElement mInfoElement;
  private String mThumb;
  private NetImageViewElement mThumbElement;
  private TextViewElement mTitleElement;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 744, 720, 744, 0, 0, ViewLayout.FILL);
  private final ViewLayout thumbLayout;
  private final ViewLayout titleLayout;

  public PlayVirtualInfoView(Context paramContext)
  {
    super(paramContext);
    int i;
    if (ScreenConfiguration.isUltraWideScreen())
      i = 240;
    while (true)
    {
      this.THUMBBOUND = i;
      this.thumbLayout = this.standardLayout.createChildLT(this.THUMBBOUND, this.THUMBBOUND, 360 - this.THUMBBOUND / 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
      this.titleLayout = this.standardLayout.createChildLT(700, 64, 10, 20, ViewLayout.SCALE_FLAG_SLTCW);
      this.infoLayout = this.standardLayout.createChildLT(700, 45, 10, 2, ViewLayout.SCALE_FLAG_SLTCW);
      this.mAbsoluteTranslationY = 0;
      this.mThumb = "";
      this.mThumbElement = new NetImageViewElement(paramContext);
      addElement(this.mThumbElement);
      this.mThumbElement.setOnElementClickListener(this);
      this.mTitleElement = new TextViewElement(paramContext);
      this.mTitleElement.setMaxLineLimit(1);
      this.mTitleElement.setColor(-1);
      this.mTitleElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      this.mTitleElement.setText("祝您开心每一天！", false);
      addElement(this.mTitleElement);
      this.mInfoElement = new TextViewElement(paramContext);
      this.mInfoElement.setMaxLineLimit(1);
      this.mInfoElement.setColor(SkinManager.getNewPlaySubColor());
      this.mInfoElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
      addElement(this.mInfoElement);
      return;
      if (ScreenConfiguration.isWideScreen())
        i = 300;
      else
        i = 360;
    }
  }

  private String getBroadCasters(List<BroadcasterNode> paramList)
  {
    if ((paramList == null) || (paramList.size() == 0))
      return "";
    StringBuilder localStringBuilder = new StringBuilder("主播");
    int i = 0;
    while (i < paramList.size())
    {
      localStringBuilder.append(" " + ((BroadcasterNode)paramList.get(i)).nick);
      i += 1;
    }
    return localStringBuilder.toString();
  }

  @TargetApi(11)
  private void liftTitle(int paramInt)
  {
    this.mThumbElement.setVisible(4);
    this.mAbsoluteTranslationY = paramInt;
    invalidate();
  }

  @TargetApi(11)
  private void resetTitlePosition()
  {
    this.mThumbElement.setVisible(0);
    this.mAbsoluteTranslationY = 0;
    invalidate();
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    String str = this.mInfoElement.getText();
    int i;
    if ((str == null) || (str.length() == 0))
    {
      i = (this.standardLayout.height - this.thumbLayout.height - this.titleLayout.getBottom()) / 2;
      this.mThumbElement.setTranslationY(i);
      if (this.mAbsoluteTranslationY != 0)
        this.mTitleElement.setTranslationY(this.mAbsoluteTranslationY);
    }
    while (true)
    {
      super.onDraw(paramCanvas);
      return;
      this.mTitleElement.setTranslationY(i + this.thumbLayout.height);
      continue;
      i = (this.standardLayout.height - this.thumbLayout.height - this.titleLayout.getBottom() - this.infoLayout.getBottom()) / 2;
      this.mThumbElement.setTranslationY(i);
      if (this.mAbsoluteTranslationY != 0)
      {
        this.mTitleElement.setTranslationY(this.mAbsoluteTranslationY);
        this.mInfoElement.setTranslationY(this.mAbsoluteTranslationY + this.titleLayout.getBottom());
      }
      else
      {
        this.mTitleElement.setTranslationY(this.thumbLayout.height + i);
        this.mInfoElement.setTranslationY(i + this.thumbLayout.height + this.titleLayout.getBottom());
      }
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if ((paramViewElement == this.mThumbElement) && (InfoManager.getInstance().root().isPlayingAd()))
    {
      paramViewElement = InfoManager.getInstance().root().mAdvertisementInfoNode.getCurrPlayingAdv();
      if ((paramViewElement == null) || (paramViewElement.landing == null) || (paramViewElement.landing.equalsIgnoreCase("")) || (paramViewElement.image == null) || (paramViewElement.image.equalsIgnoreCase("")))
        break label101;
      ControllerManager.getInstance().redirectToActiviyByUrl(paramViewElement.landing, null, true);
      MobclickAgent.onEvent(getContext(), "PlayviewClickAdv", paramViewElement.landing);
    }
    label101: 
    while ((paramViewElement == null) || (paramViewElement.internal_landing == null) || (paramViewElement.internal_landing.equalsIgnoreCase("")))
      return;
    ControllerManager.getInstance().openChannelDetailController(paramViewElement.internal_catid, paramViewElement.internal_channelid, paramViewElement.interval_programid, paramViewElement.interval_channeltype, null, true);
    MobclickAgent.onEvent(getContext(), "PlayviewClickAdv", paramViewElement.internal_landing);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.thumbLayout.scaleToBounds(this.standardLayout);
    this.infoLayout.scaleToBounds(this.standardLayout);
    this.mTitleElement.measure(this.titleLayout);
    this.mThumbElement.measure(this.thumbLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    super.onMeasure(paramInt1, paramInt2);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setNode"))
    {
      paramString = (Node)paramObject;
      if (paramString.nodeName.equalsIgnoreCase("program"))
      {
        this.mTitleElement.setText(((ProgramNode)paramString).title, false);
        this.mInfoElement.setText(getBroadCasters(((ProgramNode)paramString).lstBroadcaster));
        paramString = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
        if (paramString != null)
        {
          paramString = paramString.getApproximativeThumb(240, 240, true);
          if ((paramString != null) && (paramString.length() > 0) && (this.mThumb != null) && (!this.mThumb.equalsIgnoreCase(paramString)))
          {
            this.mThumb = paramString;
            this.mThumbElement.setImageUrl(paramString);
          }
        }
      }
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("setThumb"))
      {
        this.mThumbElement.setImageUrl((String)paramObject);
        return;
      }
      if (paramString.equalsIgnoreCase("recoverthumb"))
      {
        this.mThumbElement.setImageUrl(this.mThumb);
        return;
      }
      if (paramString.equalsIgnoreCase("liftTitle"))
      {
        liftTitle((this.standardLayout.height - this.mTitleElement.getHeight()) / 2);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("resetTitle"));
    resetTitlePosition();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview.PlayVirtualInfoView
 * JD-Core Version:    0.6.2
 */