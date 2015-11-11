package fm.qingting.qtradio.view.im.profile;

import android.content.Context;
import android.graphics.Canvas;
import android.text.Layout.Alignment;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.TextViewElement;
import fm.qingting.framework.view.TextViewElement.VerticalAlignment;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.social.UserProfile;

public class SingleItemView extends QtView
{
  private static final String DEFAULT_SIGNATURE = "这家伙很懒，什么都没留下..";
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(500, 45, 200, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 120, 720, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 180, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private TextViewElement mInfoElement;
  private boolean mNeedMatchParentLine = false;
  private TextViewElement mTypeElement;
  private final ViewLayout typeLayout = this.itemLayout.createChildLT(160, 45, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public SingleItemView(Context paramContext)
  {
    super(paramContext);
    this.mTypeElement = new TextViewElement(paramContext);
    this.mTypeElement.setMaxLineLimit(1);
    this.mTypeElement.setAlignment(Layout.Alignment.ALIGN_CENTER);
    this.mTypeElement.setColor(SkinManager.getTextColorNormal());
    addElement(this.mTypeElement);
    this.mInfoElement = new TextViewElement(paramContext);
    this.mInfoElement.setMaxLineLimit(5);
    this.mInfoElement.setColor(SkinManager.getTextColorNormal());
    this.mInfoElement.setVerticalAlignment(TextViewElement.VerticalAlignment.CENTER);
    this.mInfoElement.setText("这家伙很懒，什么都没留下..", false);
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
    paramInt2 = this.mInfoElement.getHeight();
    if (paramInt2 - this.infoLayout.height > 0);
    for (paramInt1 = this.itemLayout.height + paramInt2 - this.infoLayout.height; ; paramInt1 = this.itemLayout.height)
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
      paramString = ((UserProfile)paramObject).getUserInfo();
      if (paramString != null);
    }
    do
    {
      return;
      if ((paramString.snsInfo.signature != null) && (paramString.snsInfo.signature.length() > 0))
        this.mInfoElement.setText(paramString.snsInfo.signature, true);
      requestLayout();
      return;
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
 * Qualified Name:     fm.qingting.qtradio.view.im.profile.SingleItemView
 * JD-Core Version:    0.6.2
 */