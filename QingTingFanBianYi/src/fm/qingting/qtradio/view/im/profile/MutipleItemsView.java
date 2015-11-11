package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.social.UserProfile;
import java.util.ArrayList;
import java.util.List;

public class MutipleItemsView extends QtView
  implements ViewElement.OnElementClickListener
{
  private static final int MAXCNT = 3;
  private static String VIEWALL = "查看全部";
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(500, 45, 200, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 180, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement[] mInfoElement;
  private List<GroupInfo> mListDatas = new ArrayList();
  private TextViewElement mNumberElement;
  private TextViewElement mTypeElement;
  private final ViewLayout numberLayout = this.itemLayout.createChildLT(160, 45, 0, 65, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout typeLayout = this.itemLayout.createChildLT(160, 45, 0, 17, ViewLayout.SCALE_FLAG_SLTCW);

  public MutipleItemsView(Context paramContext)
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
    this.mInfoElement = new TextViewElement[3];
  }

  private void drawLines(Canvas paramCanvas)
  {
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
    if ((this.mInfoElement != null) && (this.mListDatas != null))
    {
      int j = Math.min(this.mListDatas.size(), 3);
      int i = 0;
      if (i < j)
      {
        if (this.mInfoElement[i] == paramViewElement)
        {
          if ((i != 2) || (this.mListDatas.size() <= 3))
            break label80;
          ControllerManager.getInstance().openMyGroupsController(this.mListDatas);
        }
        while (true)
        {
          i += 1;
          break;
          label80: ControllerManager.getInstance().openImGroupProfileController(((GroupInfo)this.mListDatas.get(i)).groupId);
        }
      }
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = 0;
    int j = 0;
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.typeLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.numberLayout.scaleToBounds(this.itemLayout);
    if (this.mListDatas != null)
    {
      int k = Math.min(3, this.mListDatas.size());
      paramInt2 = 0;
      for (paramInt1 = j; ; paramInt1 = i)
      {
        i = paramInt1;
        if (paramInt2 >= k)
          break;
        TextViewElement localTextViewElement = this.mInfoElement[paramInt2];
        i = paramInt1;
        if (localTextViewElement != null)
        {
          localTextViewElement.measure(this.infoLayout);
          localTextViewElement.setTextSize(SkinManager.getInstance().getSubTextSize());
          localTextViewElement.setTranslationY((this.itemLayout.height - localTextViewElement.getHeight()) / 2 + paramInt1);
          i = paramInt1 + this.itemLayout.height;
        }
        paramInt2 += 1;
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
      this.mListDatas = ((UserProfile)paramObject).getGroups();
      if ((this.mListDatas != null) && (this.mListDatas.size() > 0))
        if (this.mListDatas.size() > 3)
        {
          i = 0;
          if (i < 2)
          {
            if (this.mInfoElement[i] == null)
            {
              paramString = new TextViewElement(getContext());
              paramString.setMaxLineLimit(2);
              paramString.setColor(SkinManager.getTextColorNormal());
              paramString.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
              this.mInfoElement[i] = paramString;
              paramString.setText(((GroupInfo)this.mListDatas.get(i)).groupName, false);
              paramString.setOnElementClickListener(this);
              addElement(paramString);
            }
            while (true)
            {
              i += 1;
              break;
              this.mInfoElement[i].setText(((GroupInfo)this.mListDatas.get(i)).groupName, false);
            }
          }
          if (this.mInfoElement[2] == null)
          {
            paramString = new TextViewElement(getContext());
            paramString.setMaxLineLimit(2);
            paramString.setColor(SkinManager.getTextColorNormal());
            paramString.setAlignment(Layout.Alignment.ALIGN_CENTER);
            paramString.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
            this.mInfoElement[2] = paramString;
            paramString.setText(VIEWALL, false);
            paramString.setOnElementClickListener(this);
            addElement(paramString);
            this.mNumberElement.setText(String.valueOf(this.mListDatas.size()), false);
            requestLayout();
          }
        }
    }
    label297: 
    while (!paramString.equalsIgnoreCase("setType"))
      while (true)
      {
        return;
        paramString = this.mInfoElement[2];
        paramString.setAlignment(Layout.Alignment.ALIGN_CENTER);
        paramString.setText(VIEWALL, false);
        continue;
        int i = 0;
        if (i < this.mListDatas.size())
        {
          if (this.mInfoElement[i] != null)
            break label402;
          paramString = new TextViewElement(getContext());
          paramString.setMaxLineLimit(2);
          paramString.setColor(SkinManager.getTextColorNormal());
          paramString.setAlignment(Layout.Alignment.ALIGN_NORMAL);
          paramString.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
          this.mInfoElement[i] = paramString;
          paramString.setText(((GroupInfo)this.mListDatas.get(i)).groupName, false);
          paramString.setOnElementClickListener(this);
          addElement(paramString);
        }
        while (true)
        {
          i += 1;
          break label297;
          break;
          this.mInfoElement[i].setText(((GroupInfo)this.mListDatas.get(i)).groupName, false);
        }
        this.mNumberElement.setText("0");
      }
    label402: this.mTypeElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.MutipleItemsView
 * JD-Core Version:    0.6.2
 */