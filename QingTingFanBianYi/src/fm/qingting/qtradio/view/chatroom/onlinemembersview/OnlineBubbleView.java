package fm.qingting.qtradio.view.chatroom.onlinemembersview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.RectF;
import android.view.View.MeasureSpec;
import fm.qingting.framework.view.ButtonViewElement;
import fm.qingting.framework.view.ImageViewElement;
import fm.qingting.framework.view.QtView;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.framework.view.ViewElement.OnElementClickListener;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.utils.QTMSGManage;

public class OnlineBubbleView extends QtView
{
  private final ViewLayout buttonLayout = this.standardLayout.createChildLT(150, 80, 10, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private UserInfo chatData;
  private Paint grayBgPaint = new Paint();
  private boolean isUp = false;
  private ImageViewElement mBg;
  private RectF mBgRectF = new RectF();
  private ButtonViewElement mLeftElement;
  private Point mPoint;
  private ButtonViewElement mRightElement;
  private final ViewLayout roundLayout = this.standardLayout.createChildLT(5, 5, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout triangleLayout = this.standardLayout.createChildLT(30, 15, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public OnlineBubbleView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(1593835520);
    this.grayBgPaint.setColor(SkinManager.getPopBgColor());
    this.grayBgPaint.setStyle(Paint.Style.FILL);
    ViewElement.OnElementClickListener local1 = new ViewElement.OnElementClickListener()
    {
      public void onElementClick(ViewElement paramAnonymousViewElement)
      {
        if (paramAnonymousViewElement == OnlineBubbleView.this.mLeftElement)
          if (OnlineBubbleView.this.chatData != null)
          {
            OnlineBubbleView.this.dispatchActionEvent("talkWithIt", OnlineBubbleView.this.chatData);
            QTMSGManage.getInstance().sendStatistcsMessage("chat_functionclick", "chatwithit");
          }
        do
        {
          do
          {
            return;
            if (paramAnonymousViewElement != OnlineBubbleView.this.mRightElement)
              break;
          }
          while (OnlineBubbleView.this.chatData == null);
          OnlineBubbleView.this.dispatchActionEvent("lookItsInfo", OnlineBubbleView.this.chatData);
          QTMSGManage.getInstance().sendStatistcsMessage("chat_functionclick", "lookitsInfo");
          return;
        }
        while (paramAnonymousViewElement != OnlineBubbleView.this.mBg);
        OnlineBubbleView.this.dispatchActionEvent("cancelPop", null);
      }
    };
    this.mBg = new ImageViewElement(paramContext);
    addElement(this.mBg);
    this.mBg.setOnElementClickListener(local1);
    this.mLeftElement = new ButtonViewElement(paramContext);
    this.mLeftElement.setBackgroundColor(SkinManager.getPopButtonHighlightColor(), SkinManager.getPopButtonNormalColor());
    this.mLeftElement.setRoundCorner(true);
    this.mLeftElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getPopTextColor());
    this.mLeftElement.setText("对Ta说");
    addElement(this.mLeftElement);
    this.mLeftElement.setOnElementClickListener(local1);
    this.mRightElement = new ButtonViewElement(paramContext);
    this.mRightElement.setBackgroundColor(SkinManager.getPopButtonHighlightColor(), SkinManager.getPopButtonNormalColor());
    this.mRightElement.setRoundCorner(true);
    this.mRightElement.setTextColor(SkinManager.getBackgroundColor(), SkinManager.getPopTextColor());
    this.mRightElement.setText("查看资料");
    addElement(this.mRightElement);
    this.mRightElement.setOnElementClickListener(local1);
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i;
    int j;
    int k;
    boolean bool;
    label74: label105: int m;
    if (this.mRightElement.getVisiblity() == 0)
    {
      i = this.buttonLayout.leftMargin * 3 + this.buttonLayout.width * 2;
      j = this.buttonLayout.height;
      k = this.buttonLayout.leftMargin * 2 + j;
      if (this.mPoint.y >= this.standardLayout.height - k)
        break label282;
      bool = true;
      this.isUp = bool;
      if (!this.isUp)
        break label288;
      j = this.mPoint.y + this.triangleLayout.height - 1;
      m = this.mPoint.x - this.buttonLayout.width / 2 - this.buttonLayout.leftMargin;
      this.mBgRectF.set(m, j, i + m, k + j);
      if (!this.isUp)
        break label310;
      drawTriangleUp(paramCanvas);
    }
    while (true)
    {
      paramCanvas.drawRoundRect(this.mBgRectF, this.roundLayout.width, this.roundLayout.width, this.grayBgPaint);
      this.mLeftElement.setTranslationX(m);
      this.mLeftElement.setTranslationY(this.buttonLayout.leftMargin + j);
      this.mRightElement.setTranslationX(this.buttonLayout.width + m + this.buttonLayout.leftMargin);
      this.mRightElement.setTranslationY(j + this.buttonLayout.leftMargin);
      return;
      i = this.buttonLayout.leftMargin * 2 + this.buttonLayout.width;
      break;
      label282: bool = false;
      break label74;
      label288: j = this.mPoint.y - this.triangleLayout.height - k;
      break label105;
      label310: drawTriangleDown(paramCanvas);
    }
  }

  private void drawTriangleDown(Canvas paramCanvas)
  {
    paramCanvas.drawPath(SkinManager.getInstance().getLowerTriangularPath(this.mPoint.x, this.mPoint.y, this.triangleLayout.width, this.triangleLayout.height), this.grayBgPaint);
  }

  private void drawTriangleUp(Canvas paramCanvas)
  {
    paramCanvas.drawPath(SkinManager.getInstance().getUpperTriangularPath(this.mPoint.x, this.mPoint.y, this.triangleLayout.width, this.triangleLayout.height), this.grayBgPaint);
  }

  private boolean isSupportProfile()
  {
    if (this.chatData == null);
    while ((this.chatData == null) || (this.chatData.userKey == null) || (this.chatData.userKey.equalsIgnoreCase("")))
      return false;
    return true;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.mPoint == null)
      return;
    drawBg(paramCanvas);
    super.onDraw(paramCanvas);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.standardLayout.scaleToBounds(paramInt1, paramInt2);
    this.triangleLayout.scaleToBounds(this.standardLayout);
    this.buttonLayout.scaleToBounds(this.standardLayout);
    this.roundLayout.scaleToBounds(this.standardLayout);
    this.mLeftElement.measure(this.buttonLayout);
    this.mRightElement.measure(this.buttonLayout);
    this.mLeftElement.setRoundCornerRadius(this.roundLayout.width);
    this.mRightElement.setRoundCornerRadius(this.roundLayout.width);
    this.mBg.measure(this.standardLayout);
    this.mLeftElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    this.mRightElement.setTextSize(SkinManager.getInstance().getMiddleTextSize());
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void setChatParam(UserInfo paramUserInfo, Point paramPoint)
  {
    this.mPoint = paramPoint;
    this.chatData = paramUserInfo;
    if (isSupportProfile())
      this.mRightElement.setVisible(0);
    while (true)
    {
      invalidate();
      return;
      this.mRightElement.setVisible(4);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.onlinemembersview.OnlineBubbleView
 * JD-Core Version:    0.6.2
 */