package fm.qingting.qtradio.jd.view;

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
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.jd.data.JDApi;
import fm.qingting.qtradio.jd.data.JDApi.OnResponseListener;
import fm.qingting.qtradio.jd.data.Response;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.utils.QTMSGManage;
import java.util.ArrayList;

public class JDRecommendItemView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout boundLayout = this.standardLayout.createChildLT(1, 1, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout gradientLayout = this.standardLayout.createChildLT(214, 54, 10, 173, ViewLayout.SCALE_FLAG_SLTCW);
  private ArrayList<CommodityInfo> mDataList;
  private boolean mHasExplosure;
  private TextViewElement[] mNames;
  private NetImageViewElement[] mPics;
  private TagElement[] mPrices;
  private final ViewLayout nameLayout = this.standardLayout.createChildLT(216, 90, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout picLayout = this.standardLayout.createChildLT(214, 214, 0, 13, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 328, 720, 328, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public JDRecommendItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    setBackgroundColor(SkinManager.getCardColor());
    initPics(paramContext, paramInt);
    initTags(paramContext);
    initNames(paramContext);
    this.mHasExplosure = false;
  }

  private void initNames(Context paramContext)
  {
    this.mNames = new TextViewElement[3];
    int i = 0;
    while (i < this.mNames.length)
    {
      TextViewElement localTextViewElement = new TextViewElement(paramContext);
      this.mNames[i] = localTextViewElement;
      localTextViewElement.setMaxLineLimit(2);
      localTextViewElement.setAlignment(Layout.Alignment.ALIGN_NORMAL);
      localTextViewElement.setColor(SkinManager.getTextColorRecommend());
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
    this.mPrices = new TagElement[3];
    int i = 0;
    while (i < this.mPrices.length)
    {
      TagElement localTagElement = new TagElement(paramContext);
      this.mPrices[i] = localTagElement;
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
      localObject = this.mPrices[i];
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
          break label61;
        if (i < this.mDataList.size())
        {
          ControllerManager.getInstance().openJDShop((CommodityInfo)this.mDataList.get(i));
          JDApi.sendEventMessage("JDADClick");
          QTMSGManage.getInstance().sendStatistcsMessage("jdclick", "real");
        }
      }
      return;
      label61: i += 1;
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
      this.mDataList = ((ArrayList)paramObject);
      int j = Math.min(3, this.mDataList.size());
      int i = 0;
      while (i < j)
      {
        paramString = (CommodityInfo)this.mDataList.get(i);
        this.mPics[i].setVisible(0);
        this.mNames[i].setVisible(0);
        this.mPrices[i].setVisible(0);
        paramObject = paramString.getAvatar();
        this.mPics[i].setImageUrl(paramObject, false);
        this.mNames[i].setText(paramString.getTitle(), false);
        this.mPrices[i].setText("￥" + paramString.getPrice());
        if (!this.mHasExplosure)
        {
          JDApi.feedback(new JDApi.OnResponseListener()
          {
            public void onResponse(Response paramAnonymousResponse)
            {
            }
          }
          , ((CommodityInfo)this.mDataList.get(i)).getTrackUrl());
          JDApi.sendEventMessage("JDADExplosure");
        }
        i += 1;
      }
      this.mHasExplosure = true;
      if (j < 3)
      {
        i = j;
        while (i < 3)
        {
          this.mPics[i].setVisible(4);
          this.mNames[i].setVisible(4);
          this.mPrices[i].setVisible(4);
          i += 1;
        }
      }
      invalidate();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.jd.view.JDRecommendItemView
 * JD-Core Version:    0.6.2
 */