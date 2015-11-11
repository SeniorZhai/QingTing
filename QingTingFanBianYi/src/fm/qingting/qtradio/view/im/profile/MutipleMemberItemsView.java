package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.UserInfo;
import java.util.List;

public class MutipleMemberItemsView extends QtView
  implements ViewElement.OnElementClickListener
{
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(520, 120, 200, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 180, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private int mChildrenCnt = 0;
  private MemberItemElement[] mInfoElement;
  private List<UserInfo> mListDatas;
  private TextViewElement mNumberElement;
  private TextViewElement mTypeElement;
  private final ViewLayout numberLayout = this.itemLayout.createChildLT(160, 45, 0, 65, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout typeLayout = this.itemLayout.createChildLT(160, 45, 0, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public MutipleMemberItemsView(Context paramContext)
  {
    super(paramContext);
    this.mTypeElement = new TextViewElement(paramContext);
    this.mTypeElement.setMaxLineLimit(1);
    this.mTypeElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTypeElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTypeElement);
    this.mNumberElement = new TextViewElement(paramContext);
    this.mNumberElement.setMaxLineLimit(1);
    this.mNumberElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mNumberElement.setColor(SkinManager.getTextColorSubInfo());
    addElement(this.mNumberElement);
  }

  private void drawLines(Canvas paramCanvas)
  {
    if (this.mInfoElement == null)
      return;
    int i = 0;
    while (i < this.mInfoElement.length - 1)
    {
      if (this.mInfoElement[i] != null)
        SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height * i + this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
      i += 1;
    }
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, 0, this.itemLayout.width, getMeasuredHeight() - this.lineLayout.height, this.lineLayout.height);
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLines(paramCanvas);
  }

  public void onElementClick(ViewElement paramViewElement)
  {
    int i = 0;
    while (i < this.mInfoElement.length)
    {
      if (paramViewElement == this.mInfoElement[i])
        ControllerManager.getInstance().openImUserProfileController(((UserInfo)this.mListDatas.get(i)).userKey);
      i += 1;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int k = 0;
    int j = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.typeLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.numberLayout.scaleToBounds(this.itemLayout);
    int i = k;
    if (this.mListDatas != null)
    {
      i = k;
      if (this.mListDatas.size() > 0)
      {
        i = k;
        if (this.mInfoElement != null)
        {
          k = Math.min(this.mListDatas.size(), this.mInfoElement.length);
          paramInt2 = 0;
          for (paramInt1 = j; ; paramInt1 = i)
          {
            i = paramInt1;
            if (paramInt2 >= k)
              break;
            MemberItemElement localMemberItemElement = this.mInfoElement[paramInt2];
            i = paramInt1;
            if (localMemberItemElement != null)
            {
              localMemberItemElement.measure(this.infoLayout);
              localMemberItemElement.setTranslationY(paramInt1);
              i = paramInt1 + this.itemLayout.height;
            }
            paramInt2 += 1;
          }
        }
      }
    }
    this.mTypeElement.measure(this.typeLayout);
    this.mTypeElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mNumberElement.measure(this.numberLayout);
    this.mNumberElement.setTextSize(SkinManager.getInstance().getTinyTextSize());
    setMeasuredDimension(this.itemLayout.width, i);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      this.mListDatas = ((List)paramObject);
      if ((this.mListDatas == null) || (this.mListDatas.size() == 0))
        requestLayout();
    }
    else
    {
      return;
    }
    paramString = this.mInfoElement;
    this.mTypeElement.setText("群主", false);
    int j = this.mListDatas.size();
    int i;
    if (this.mChildrenCnt == 0)
    {
      this.mInfoElement = new MemberItemElement[j];
      i = 0;
      while (i < j)
      {
        paramString = new MemberItemElement(getContext());
        this.mInfoElement[i] = paramString;
        paramString.setData((UserInfo)this.mListDatas.get(i));
        addElement(paramString);
        paramString.setOnElementClickListener(this);
        i += 1;
      }
      this.mChildrenCnt = j;
    }
    while (true)
    {
      this.mNumberElement.setText(String.valueOf(j), false);
      requestLayout();
      return;
      if (j > this.mChildrenCnt)
      {
        this.mInfoElement = new MemberItemElement[j];
        System.arraycopy(paramString, 0, this.mInfoElement, 0, this.mChildrenCnt);
        i = 0;
        while (i < this.mChildrenCnt)
        {
          this.mInfoElement[i].setData((UserInfo)this.mListDatas.get(i));
          i += 1;
        }
        i = this.mChildrenCnt;
        while (i < j)
        {
          paramString = new MemberItemElement(getContext());
          this.mInfoElement[i] = paramString;
          paramString.setData((UserInfo)this.mListDatas.get(i));
          addElement(paramString);
          paramString.setOnElementClickListener(this);
          i += 1;
        }
        this.mChildrenCnt = j;
      }
      else
      {
        i = 0;
        while (i < j)
        {
          this.mInfoElement[i].setData((UserInfo)this.mListDatas.get(i));
          i += 1;
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.MutipleMemberItemsView
 * JD-Core Version:    0.6.2
 */