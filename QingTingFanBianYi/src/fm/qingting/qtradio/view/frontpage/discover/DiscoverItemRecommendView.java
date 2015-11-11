package fm.qingting.qtradio.view.frontpage.discover;

import android.content.Context;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.NetImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.fm.PlayerAgent;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.SpecialTopicNode;
import java.util.List;

public class DiscoverItemRecommendView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout boundLayout = this.standardLayout.createChildLT(1, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout gradientLayout = this.standardLayout.createChildLT(214, 54, 10, 173, ViewLayout.SCALE_FLAG_SLTCW);
  private List<RecommendItemNode> mDatas;
  private TextViewElement[] mNames;
  private NetImageViewElement[] mPics;
  private TagElement[] mTags;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(216, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.standardLayout.createChildLT(214, 214, 0, 13, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 328, 720, 328, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public DiscoverItemRecommendView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    initPics(paramContext, paramInt);
    initTags(paramContext);
    initNames(paramContext);
  }

  private String getTagName(RecommendItemNode paramRecommendItemNode)
  {
    Object localObject = paramRecommendItemNode.mNode;
    if (localObject == null)
      localObject = null;
    String str;
    do
    {
      return localObject;
      if (!((Node)localObject).nodeName.equalsIgnoreCase("program"))
        break;
      str = ((ProgramNode)localObject).getChannelName();
      localObject = str;
    }
    while (str != null);
    return paramRecommendItemNode.belongName;
    if (((Node)localObject).nodeName.equalsIgnoreCase("channel"))
      return ((ChannelNode)localObject).title;
    if (((Node)localObject).nodeName.equalsIgnoreCase("specialtopic"))
      return ((SpecialTopicNode)localObject).title;
    return null;
  }

  private void initNames(Context paramContext)
  {
    this.mNames = new TextViewElement[3];
    int i = 0;
    while (i < this.mNames.length)
    {
      TextViewElement localTextViewElement = new TextViewElement(paramContext);
      this.mNames[i] = localTextViewElement;
      localTextViewElement.setAlignment(Layout.Alignment.ALIGN_NORMAL);
      localTextViewElement.setColor(SkinManager.getTextColorRecommend());
      localTextViewElement.setMaxLineLimit(2);
      localTextViewElement.setText("", false);
      addElement(localTextViewElement);
      i += 1;
    }
  }

  private void initPics(Context paramContext, int paramInt)
  {
    this.mPics = new NetImageViewElement[3];
    int i = 0;
    while (i < this.mPics.length)
    {
      NetImageViewElement localNetImageViewElement = new NetImageViewElement(paramContext);
      this.mPics[i] = localNetImageViewElement;
      localNetImageViewElement.setDefaultImageRes(2130837907);
      localNetImageViewElement.setHighlightColor(SkinManager.getItemHighlightMaskColor());
      localNetImageViewElement.setBoundColor(SkinManager.getDividerColor());
      localNetImageViewElement.setOnElementClickListener(this);
      addElement(localNetImageViewElement, paramInt);
      i += 1;
    }
  }

  private void initTags(Context paramContext)
  {
    this.mTags = new TagElement[3];
    int i = 0;
    while (i < this.mTags.length)
    {
      TagElement localTagElement = new TagElement(paramContext);
      this.mTags[i] = localTagElement;
      addElement(localTagElement);
      i += 1;
    }
  }

  private void measureArrayElement()
  {
    int k = (this.standardLayout.width - this.picLayout.width * 3) / 4;
    int m = this.picLayout.topMargin;
    int n = (this.picLayout.width - this.nameLayout.width) / 2;
    int i = 0;
    int j = k;
    while (i < this.mPics.length)
    {
      Object localObject = this.mPics[i];
      ((NetImageViewElement)localObject).measure(j, m, this.picLayout.width + j, this.picLayout.height + m);
      ((NetImageViewElement)localObject).setBoundLineWidth(this.boundLayout.width);
      localObject = this.mTags[i];
      ((TagElement)localObject).measure(j, this.gradientLayout.topMargin, this.gradientLayout.width + j, this.gradientLayout.getBottom());
      ((TagElement)localObject).setTextSize(SkinManager.getInstance().getTeenyTinyTextSize());
      ((TagElement)localObject).setTextColor(-1);
      ((TagElement)localObject).setTextOffset(this.gradientLayout.leftMargin);
      localObject = this.mNames[i];
      ((TextViewElement)localObject).measure(j + n, this.picLayout.height + m, j + n + this.nameLayout.width, this.picLayout.height + m + this.nameLayout.height);
      ((TextViewElement)localObject).setTextSize(SkinManager.getInstance().getRecommendTextSize());
      j += this.picLayout.width + k;
      i += 1;
    }
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i = 0;
    while (true)
    {
      if (i < 3)
      {
        if (this.mPics[i] != paramViewElement)
          break label58;
        if (i < this.mDatas.size())
        {
          PlayerAgent.getInstance().addPlaySource(22);
          ControllerManager.getInstance().openControllerByRecommendNode((Node)this.mDatas.get(i));
        }
      }
      return;
      label58: i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.picLayout.scaleToBounds(this.standardLayout);
    this.nameLayout.scaleToBounds(this.standardLayout);
    this.boundLayout.scaleToBounds(this.standardLayout);
    this.gradientLayout.scaleToBounds(this.standardLayout);
    measureArrayElement();
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.mDatas = ((List)paramObject);
      int j = Math.min(3, this.mDatas.size());
      int i = 0;
      while (i < j)
      {
        paramString = (RecommendItemNode)this.mDatas.get(i);
        this.mPics[i].setVisible(0);
        this.mNames[i].setVisible(0);
        this.mTags[i].setVisible(0);
        paramObject = paramString.getApproximativeThumb(214, 214);
        this.mPics[i].setImageUrl(paramObject, false);
        this.mNames[i].setText(paramString.name, false);
        this.mTags[i].setText(getTagName(paramString));
        i += 1;
      }
      if (j < 3)
      {
        i = j;
        while (i < 3)
        {
          this.mPics[i].setVisible(4);
          this.mNames[i].setVisible(4);
          this.mTags[i].setVisible(4);
          i += 1;
        }
      }
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.frontpage.discover.DiscoverItemRecommendView
 * JD-Core Version:    0.6.2
 */