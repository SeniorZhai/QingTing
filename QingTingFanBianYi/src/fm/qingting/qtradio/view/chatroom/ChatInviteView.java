package fm.qingting.qtradio.view.chatroom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View.MeasureSpec;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.view.QtListItemView;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.share.ShareUtil;

public class ChatInviteView extends QtListItemView
{
  private final int EMPTY = 20;
  private final int[] PLATFORM_ICONS = { 2130837978, 2130837973, 2130837975, 2130837974, 2130837976, 2130837977 };
  private final String[] PLATFORM_NAMES = { "微信", "朋友圈", "QQ空间", "QQ好友", "新浪微博", "腾讯微博" };
  private final String TITLE = "邀请好友一起收听";
  private final ViewLayout iconLayout = this.itemLayout.createChildLT(120, 120, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout itemLayout = this.standardLayout.createChildLT(180, 200, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Rect mIconRect = new Rect();
  private boolean mInTouchMode = false;
  private float mLastMotionX = 0.0F;
  private float mLastMotionY = 0.0F;
  private final Paint mNamePaint = new Paint();
  private final Paint mPaint = new Paint();
  private int mSelectIndex = -1;
  private Rect mTextBound = new Rect();
  private final Paint mTitlePaint = new Paint();
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(180, 45, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);
  private final ViewLayout titleLayout = this.standardLayout.createChildLT(720, 80, 0, 0, ViewLayout.SCALE_FLAG_SLTCW);

  public ChatInviteView(Context paramContext)
  {
    super(paramContext);
    setItemSelectedEnable();
    this.mNamePaint.setColor(SkinManager.getTextColorNormal());
    this.mTitlePaint.setColor(SkinManager.getTextColorNormal());
    this.mTitlePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
  }

  private void drawBg(Canvas paramCanvas)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(0, getTopMargin(), this.standardLayout.width, getTopMargin() + getThisHeight());
    paramCanvas.drawColor(-1);
    paramCanvas.restoreToCount(i);
  }

  private void drawHighlight(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    int i = paramCanvas.save();
    paramCanvas.clipRect(paramInt1, paramInt2, this.itemLayout.width + paramInt1, this.itemLayout.height + paramInt2);
    paramCanvas.drawColor(SkinManager.getItemHighlightMaskColor());
    paramCanvas.restoreToCount(i);
  }

  private void drawItem(Canvas paramCanvas, int paramInt1, int paramInt2, int paramInt3)
  {
    this.mIconRect.offset(paramInt1, paramInt2);
    paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCache(getResources(), this, this.PLATFORM_ICONS[paramInt3]), null, this.mIconRect, this.mPaint);
    String str = this.PLATFORM_NAMES[paramInt3];
    this.mNamePaint.getTextBounds(str, 0, str.length(), this.mTextBound);
    paramCanvas.drawText(str, (this.itemLayout.width - this.mTextBound.width()) / 2 + paramInt1, this.mIconRect.bottom + (this.nameLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mNamePaint);
    this.mIconRect.offset(-paramInt1, -paramInt2);
  }

  private void drawItems(Canvas paramCanvas)
  {
    int i = 0;
    if (i < 6)
    {
      int k = i % 4 * this.itemLayout.width;
      int m = getTopMargin();
      int n = this.titleLayout.height;
      int i1 = this.itemLayout.height;
      if (i < 4);
      for (int j = 0; ; j = 1)
      {
        j = j * i1 + (n + m);
        if ((isItemPressed()) && (this.mSelectIndex == i))
          drawHighlight(paramCanvas, k, j);
        drawItem(paramCanvas, k, j, i);
        i += 1;
        break;
      }
    }
  }

  private void drawTitle(Canvas paramCanvas)
  {
    this.mTitlePaint.getTextBounds("邀请好友一起收听", 0, "邀请好友一起收听".length(), this.mTextBound);
    paramCanvas.drawText("邀请好友一起收听", (this.standardLayout.width - this.mTextBound.width()) / 2, getTopMargin() + (this.titleLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2, this.mTitlePaint);
  }

  private int getSelectIndex()
  {
    if (this.mLastMotionY < getTopMargin());
    int i;
    do
    {
      int j;
      do
      {
        return -1;
        if ((this.mLastMotionY > getTopMargin()) && (this.mLastMotionY < getTopMargin() + this.titleLayout.height))
          return 20;
        i = (int)Math.floor(this.mLastMotionX / this.itemLayout.width);
        j = (int)Math.floor((this.mLastMotionY - getTopMargin() - this.titleLayout.height) / this.itemLayout.height);
      }
      while ((i < 0) || (j < 0));
      i += j * 4;
    }
    while (i > this.PLATFORM_NAMES.length);
    return i;
  }

  private int getThisHeight()
  {
    int i = this.PLATFORM_NAMES.length;
    int j = i / 4;
    if (i % 4 == 0);
    for (i = 0; ; i = 1)
      return (i + j) * this.itemLayout.height + this.titleLayout.height;
  }

  private int getTopMargin()
  {
    return 0;
  }

  public void close(boolean paramBoolean)
  {
    BitmapResourceCache.getInstance().clearResourceCacheOfOne(this, 0);
    super.close(paramBoolean);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    paramCanvas.setDrawFilter(SkinManager.getInstance().getDrawFilter());
    paramCanvas.save();
    drawBg(paramCanvas);
    drawTitle(paramCanvas);
    drawItems(paramCanvas);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.itemLayout.scaleToBounds(this.standardLayout);
    this.iconLayout.scaleToBounds(this.itemLayout);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.titleLayout.scaleToBounds(this.standardLayout);
    this.mNamePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    paramInt1 = (this.itemLayout.height - this.iconLayout.height - this.nameLayout.height) / 2;
    this.mIconRect.set((this.itemLayout.width - this.iconLayout.width) / 2, paramInt1, (this.itemLayout.width + this.iconLayout.width) / 2, this.iconLayout.height + paramInt1);
    setMeasuredDimension(this.standardLayout.width, getThisHeight());
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((!this.mInTouchMode) && (paramMotionEvent.getAction() != 0));
    do
    {
      do
      {
        int i;
        do
        {
          return true;
          switch (paramMotionEvent.getAction())
          {
          default:
            return true;
          case 0:
            this.mLastMotionX = paramMotionEvent.getX();
            this.mLastMotionY = paramMotionEvent.getY();
            this.mSelectIndex = getSelectIndex();
            if (this.mSelectIndex < 0)
            {
              this.mInTouchMode = false;
              dispatchActionEvent("cancelPop", null);
              return true;
            }
            this.mInTouchMode = true;
            invalidate();
            return true;
          case 2:
            this.mLastMotionX = paramMotionEvent.getX();
            this.mLastMotionY = paramMotionEvent.getY();
            i = getSelectIndex();
          case 3:
          case 1:
          }
        }
        while ((this.mSelectIndex <= -1) || (i == this.mSelectIndex));
        this.mInTouchMode = false;
        this.mSelectIndex = -1;
      }
      while (!isItemPressed());
      invalidate();
      return true;
      this.mInTouchMode = false;
      this.mSelectIndex = -1;
    }
    while (!isItemPressed());
    invalidate();
    return true;
    if ((this.mSelectIndex > -1) && (this.mSelectIndex < this.PLATFORM_NAMES.length))
    {
      MobclickAgent.onEvent(getContext(), "ChatroomInvite", this.PLATFORM_NAMES[this.mSelectIndex]);
      dispatchActionEvent("shareToPlatform", Integer.valueOf(this.mSelectIndex));
      ShareUtil.inviteByPlatform(getContext(), InfoManager.getInstance().root().getCurrentPlayingNode(), this.mSelectIndex);
    }
    this.mInTouchMode = false;
    invalidate();
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.ChatInviteView
 * JD-Core Version:    0.6.2
 */