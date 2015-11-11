package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.AdvertisementInfoNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.AdvertisementItemNode3rdParty;
import fm.qingting.qtradio.model.IThirdPartyAd;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.view.playview.LineElement;
import java.util.ArrayList;

public class DiscoverAdItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(120, 120, 25, 24, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout deleteLayout = this.itemLayout.createChildLT(132, 42, 520, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout displayDeleteLayout = this.itemLayout.createChildLT(34, 34, 675, 35, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 168, 720, 168, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(670, 1, 25, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private NetImageViewElement mAvatarElement;
  private ButtonViewElement mBg;
  private ImageViewElement mDelete;
  private ImageViewElement mDisplayDelete;
  private AdvertisementItemNode mInfo;
  private LineElement mLineElement;
  private OnDeleteListener mListener;
  private TextViewElement mSubTitleElement;
  private ImageViewElement mTag;
  private TextViewElement mTitleElement;
  private final ViewLayout subLayout = this.itemLayout.createChildLT(540, 90, 170, 75, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout tagLayout = this.itemLayout.createChildLT(52, 24, 170, 30, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout titleLayout = this.itemLayout.createChildLT(475, 40, 225, 18, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverAdItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.mBg = new ButtonViewElement(paramContext);
    this.mBg.setBackgroundColor(0, SkinManager.getCardColor());
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(this);
    this.mAvatarElement = new NetImageViewElement(paramContext);
    this.mAvatarElement.setDefaultImageRes(2130837907);
    this.mAvatarElement.setBoundColor(SkinManager.getDividerColor());
    addElement(this.mAvatarElement, paramInt);
    this.mTitleElement = new TextViewElement(paramContext);
    this.mTitleElement.setColor(SkinManager.getTextColorNormal());
    this.mTitleElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mTitleElement.setMaxLineLimit(2);
    addElement(this.mTitleElement);
    this.mSubTitleElement = new TextViewElement(paramContext);
    this.mSubTitleElement.setColor(SkinManager.getTextColorRecommend());
    this.mSubTitleElement.setMaxLineLimit(2);
    addElement(this.mSubTitleElement);
    this.mLineElement = new LineElement(paramContext);
    this.mLineElement.setOrientation(1);
    this.mLineElement.setColor(SkinManager.getDividerColor());
    addElement(this.mLineElement);
    this.mTag = new ImageViewElement(paramContext);
    this.mTag.setImageRes(2130837834);
    addElement(this.mTag);
    this.mDelete = new ImageViewElement(paramContext);
    this.mDelete.setImageRes(2130837832);
    this.mDelete.setVisible(4);
    addElement(this.mDelete);
    this.mDelete.setOnElementClickListener(this);
    this.mDisplayDelete = new ImageViewElement(paramContext);
    this.mDisplayDelete.setImageRes(2130837830);
    addElement(this.mDisplayDelete, paramInt);
    this.mDisplayDelete.setOnElementClickListener(this);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int j = this.mSubTitleElement.getLineCnt();
    int i = j;
    if (j > 1)
      i = 2;
    this.mSubTitleElement.setMaxLineLimit(i);
    IThirdPartyAd localIThirdPartyAd;
    if ((this.mInfo != null) && ((this.mInfo instanceof AdvertisementItemNode3rdParty)))
    {
      localIThirdPartyAd = ((AdvertisementItemNode3rdParty)this.mInfo).getRawAd();
      if (localIThirdPartyAd == null);
    }
    try
    {
      localIThirdPartyAd.onShow();
      super.onDraw(paramCanvas);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    if (paramViewElement == this.mDisplayDelete)
    {
      if (this.mDelete.getVisiblity() == 4)
        this.mDelete.setVisible(0);
      while (true)
      {
        InfoManager.getInstance().root().mAdvertisementInfoNode.sendMessage("SectionADCLOSE", this.mInfo);
        return;
        this.mDelete.setVisible(4);
      }
    }
    if (paramViewElement == this.mDelete)
    {
      if (this.mListener != null)
        this.mListener.onDelete();
      InfoManager.getInstance().root().mAdvertisementInfoNode.sendMessage("SectionAdNoIntersting", this.mInfo);
      return;
    }
    ControllerManager.getInstance().openSectionAd(this.mInfo);
    InfoManager.getInstance().root().mAdvertisementInfoNode.sendMessage("SectionADClick", this.mInfo);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.itemLayout);
    this.subLayout.scaleToBounds(this.itemLayout);
    this.tagLayout.scaleToBounds(this.itemLayout);
    this.displayDeleteLayout.scaleToBounds(this.itemLayout);
    this.deleteLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mBg.measure(this.itemLayout);
    this.mAvatarElement.measure(this.avatarLayout);
    this.mAvatarElement.setBoundLineWidth(this.lineLayout.height);
    this.mTitleElement.measure(this.titleLayout);
    this.mSubTitleElement.measure(this.subLayout);
    this.mTag.measure(this.tagLayout);
    this.mDelete.measure(this.deleteLayout);
    this.mDisplayDelete.measure(this.displayDeleteLayout);
    this.mLineElement.measure(this.lineLayout.leftMargin, this.itemLayout.height - this.lineLayout.height, this.lineLayout.getRight(), this.itemLayout.height);
    this.mTitleElement.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.mSubTitleElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void parse(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    StringBuffer localStringBuffer = new StringBuffer();
    int j = 0;
    int i = 0;
    if (j < paramString.length())
    {
      char c = paramString.charAt(j);
      switch (i)
      {
      default:
      case 0:
      case 1:
      case 2:
      }
      while (true)
      {
        j += 1;
        break;
        if (c == '\\')
        {
          i = 1;
        }
        else if (c == '|')
        {
          i = 2;
        }
        else
        {
          localStringBuffer.insert(localStringBuffer.length(), c);
          i = 0;
          continue;
          localStringBuffer.insert(localStringBuffer.length(), c);
          i = 0;
          continue;
          localArrayList.add(localStringBuffer.toString());
          localStringBuffer.delete(0, localStringBuffer.length());
          localStringBuffer.insert(localStringBuffer.length(), c);
          i = 0;
        }
      }
    }
    if (i == 0)
      localArrayList.add(localStringBuffer.toString());
    this.mInfo.setTitle((String)localArrayList.get(0));
    if (localArrayList.size() > 1)
      this.mInfo.setDescription((String)localArrayList.get(1));
  }

  public void setListener(OnDeleteListener paramOnDeleteListener)
  {
    this.mListener = paramOnDeleteListener;
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mInfo = ((AdvertisementItemNode)paramObject);
      if ((this.mInfo instanceof AdvertisementItemNode3rdParty))
      {
        paramString = ((AdvertisementItemNode3rdParty)this.mInfo).getRawAd();
        if (paramString != null)
        {
          paramString = paramString.toAdvertisementItemNode();
          this.mAvatarElement.setImageUrl(paramString.image);
          this.mTitleElement.setText(paramString.getTitle(), false);
          this.mSubTitleElement.setText(paramString.getDescription(), false);
        }
      }
    }
    while (!paramString.equalsIgnoreCase("nbl"))
    {
      return;
      parse(this.mInfo.desc);
      this.mAvatarElement.setImageUrl(this.mInfo.image);
      this.mTitleElement.setText(this.mInfo.getTitle(), false);
      this.mSubTitleElement.setText(this.mInfo.getDescription(), false);
      return;
    }
    boolean bool = ((Boolean)paramObject).booleanValue();
    paramString = this.mLineElement;
    if (bool);
    for (int i = 0; ; i = 4)
    {
      paramString.setVisible(i);
      return;
    }
  }

  public static abstract interface OnDeleteListener
  {
    public abstract void onDelete();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverAdItemView
 * JD-Core Version:    0.6.2
 */