package fm.qingting.qtradio.view.playview_bb;

import android.content.Context;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.NetImageViewElement.CLAMPTYPE;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.chatroom.broadcastor.RoundAvatarElement;

public class DanmakuImageView extends QtView
{
  private final ViewLayout avatarLayout = this.infoLayout.createChildLT(60, 60, 15, 15, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout infoLayout = this.standardLayout.createChildLT(720, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private RoundAvatarElement mAvatarElement;
  private NetImageViewElement mElement;
  private ButtonViewElement mInfoBackground;
  private TextViewElement mMessageElement;
  private TextViewElement mNameElement;
  private final ViewLayout messageLayout = this.infoLayout.createChildLT(600, 40, 90, 40, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout nameLayout = this.infoLayout.createChildLT(600, 40, 90, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 574, 720, 574, 0, 0, ViewLayout.FILL);

  public DanmakuImageView(Context paramContext)
  {
    super(paramContext);
    this.mElement = new NetImageViewElement(paramContext);
    this.mElement.setClampType(NetImageViewElement.CLAMPTYPE.CLIPBOTH);
    addElement(this.mElement);
    this.mInfoBackground = new ButtonViewElement(paramContext);
    this.mInfoBackground.setBackgroundColor(1426063360, 1426063360);
    addElement(this.mInfoBackground);
    this.mAvatarElement = new RoundAvatarElement(paramContext);
    addElement(this.mAvatarElement);
    this.mNameElement = new TextViewElement(paramContext);
    this.mNameElement.setColor(-1);
    addElement(this.mNameElement);
    this.mMessageElement = new TextViewElement(paramContext);
    this.mMessageElement.setColor(-1);
    addElement(this.mMessageElement);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.infoLayout.scaleToBounds(this.standardLayout);
    this.avatarLayout.scaleToBounds(this.infoLayout);
    this.nameLayout.scaleToBounds(this.infoLayout);
    this.messageLayout.scaleToBounds(this.infoLayout);
    this.mElement.measure(this.standardLayout);
    this.mInfoBackground.measure(this.infoLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mNameElement.measure(this.nameLayout);
    this.mMessageElement.measure(this.messageLayout);
    paramInt1 = this.standardLayout.height - this.infoLayout.height;
    this.mInfoBackground.setTranslationY(paramInt1);
    this.mAvatarElement.setTranslationY(paramInt1);
    this.mNameElement.setTranslationY(paramInt1);
    this.mMessageElement.setTranslationY(paramInt1);
    this.mNameElement.setTextSize(SkinManager.getInstance().getRecommendTextSize());
    this.mMessageElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void resetDanmaku()
  {
    this.mAvatarElement.setImageUrl(null);
    this.mNameElement.setText("", false);
    this.mMessageElement.setText("");
  }

  public void setDanmaku(IMMessage paramIMMessage)
  {
    this.mElement.setImageUrl(paramIMMessage.mImage, false);
    this.mAvatarElement.setImageUrl(paramIMMessage.mFromAvatar);
    this.mNameElement.setText(paramIMMessage.mFromName, false);
    this.mMessageElement.setText(paramIMMessage.mMessage);
  }

  public void setThumb()
  {
    Object localObject = InfoManager.getInstance().root().getCurrentPlayingChannelNode();
    if (localObject != null)
    {
      localObject = ((ChannelNode)localObject).getApproximativeThumb(720, 574, true);
      this.mElement.setImageUrl((String)localObject, false);
      return;
    }
    this.mElement.setImageUrl(null, false);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.playview_bb.DanmakuImageView
 * JD-Core Version:    0.6.2
 */