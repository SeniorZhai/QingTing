package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.manager.SkinManager;

public class GroupDescView extends QtView
{
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(500, 45, 200, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 180, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mInfoElement;
  private boolean mNeedMatchParentLine = false;
  private TextViewElement mTypeElement;
  private final ViewLayout typeLayout = this.itemLayout.createChildLT(160, 45, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public GroupDescView(Context paramContext)
  {
    super(paramContext);
    this.mTypeElement = new TextViewElement(paramContext);
    this.mTypeElement.setMaxLineLimit(1);
    this.mTypeElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTypeElement.setColor(SkinManager.getTextColorNormal());
    this.mTypeElement.setText("简介");
    addElement(this.mTypeElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setMaxLineLimit(10);
    this.mInfoElement.setColor(SkinManager.getTextColorNormal());
    this.mInfoElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    addElement(this.mInfoElement);
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager localSkinManager = SkinManager.getInstance();
    if (this.mNeedMatchParentLine);
    for (int i = 0; ; i = this.lineLayout.leftMargin)
    {
      localSkinManager.drawHorizontalLine(paramCanvas, i, this.itemLayout.width, getMeasuredHeight() - this.lineLayout.height, this.lineLayout.height);
      return;
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawLine(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.itemLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.typeLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.mInfoElement.measure(this.infoLayout);
    this.mInfoElement.setTextSize(SkinManager.getInstance().getSubTextSize());
    paramInt2 = 0;
    paramInt1 = paramInt2;
    if (this.mInfoElement.getText() != null)
    {
      paramInt1 = paramInt2;
      if (this.mInfoElement.getText().length() > 0)
      {
        paramInt2 = this.mInfoElement.getHeight();
        if (paramInt2 - this.infoLayout.height <= 0)
          break label206;
      }
    }
    label206: for (paramInt1 = this.itemLayout.height + paramInt2 - this.infoLayout.height; ; paramInt1 = this.itemLayout.height)
    {
      this.mInfoElement.setTranslationY((paramInt1 - paramInt2) / 2);
      this.mTypeElement.measure(this.typeLayout);
      this.mTypeElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
      this.mTypeElement.setTranslationY((paramInt1 - this.mTypeElement.getHeight()) / 2);
      setMeasuredDimension(this.itemLayout.width, paramInt1);
      return;
    }
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"))
    {
      paramString = (GroupInfo)paramObject;
      if (paramString != null);
    }
    do
    {
      return;
      if ((paramString.groupDesc == null) || (paramString.groupDesc.length() == 0))
        this.mInfoElement.setText(getResources().getString(2131492888));
      while (true)
      {
        requestLayout();
        return;
        this.mInfoElement.setText(paramString.groupDesc);
      }
      if (paramString.equalsIgnoreCase("needfillline"))
      {
        this.mNeedMatchParentLine = true;
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setType"));
    this.mTypeElement.setText((String)paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.GroupDescView
 * JD-Core Version:    0.6.2
 */