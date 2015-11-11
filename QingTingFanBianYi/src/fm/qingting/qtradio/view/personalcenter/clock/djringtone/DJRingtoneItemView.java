package fm.qingting.qtradio.view.personalcenter.clock.djringtone;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import fm.qingting.framework.utils.BitmapResourceCache;
import fm.qingting.framework.utils.ImageLoader;
import fm.qingting.framework.utils.ImageLoaderHandler;
import fm.qingting.framework.view.ViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.Download;
import fm.qingting.qtradio.model.RingToneNode;

public class DJRingtoneItemView extends ViewImpl
  implements View.OnClickListener, ImageLoaderHandler
{
  private int TouchSlop = 20;
  private final ViewLayout avatarLayout = this.itemLayout.createChildLT(135, 135, 30, 6, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkStateLayout = this.checkbgLayout.createChildLT(30, 22, 2, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private final ViewLayout checkbgLayout = this.itemLayout.createChildLT(48, 48, 622, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private boolean checked = false;
  private TextPaint eclipsePaint = new TextPaint();
  private DrawFilter filter;
  private int hashCode = -15;
  private final ViewLayout iconLayout = this.itemLayout.createChildLT(49, 49, 0, 8, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private Paint infoHighlightPaint = new Paint();
  private final ViewLayout infoLayout = this.itemLayout.createChildLT(500, 45, 185, 10, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint infoPaint = new Paint();
  private boolean isPlaying = false;
  private final ViewLayout itemLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 180, 720, 800, 0, 0, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private final ViewLayout lineLayout = this.itemLayout.createChildLT(720, 1, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint mAvatarPaint = new Paint();
  private RectF mAvatarRectF = new RectF();
  private Paint mCheckBgPaint = new Paint();
  private Rect mCheckRect = new Rect();
  private Paint mCheckStatePaint = new Paint();
  private Rect mDefaultAvatarRect = new Rect();
  private RectF mOriginRectF = new RectF();
  private Paint mPaint = new Paint();
  private Rect mPlayRect = new Rect();
  private Rect mTextBound = new Rect();
  private Paint nameHighlightPaint = new Paint();
  private final ViewLayout nameLayout = this.itemLayout.createChildLT(500, 45, 185, 20, ViewLayout.SCALE_FLAG_SLTCW);
  private Paint namePaint = new Paint();
  private final ViewLayout playStateLayout = this.itemLayout.createChildLT(36, 36, 30, 0, ViewLayout.SCALE_FLAG_SLTCW);
  private String ringtoneId;
  private RingToneNode ringtoneInfo;
  private float touchDownX = 0.0F;
  private float touchDownY = 0.0F;

  public DJRingtoneItemView(Context paramContext, int paramInt)
  {
    super(paramContext);
    this.hashCode = paramInt;
    setBackgroundColor(SkinManager.getCardColor());
    this.namePaint.setColor(SkinManager.getTextColorNormal());
    this.infoPaint.setColor(SkinManager.getTextColorSubInfo());
    this.nameHighlightPaint.setColor(SkinManager.getTextColorHighlight());
    this.infoHighlightPaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mCheckStatePaint.setColor(SkinManager.getTextColorHighlight());
    this.mCheckBgPaint.setStyle(Paint.Style.STROKE);
    this.mCheckStatePaint.setStyle(Paint.Style.FILL);
    this.filter = SkinManager.getInstance().getDrawFilter();
    setOnClickListener(this);
    this.mAvatarPaint.setAntiAlias(true);
  }

  private void drawAvatar(Canvas paramCanvas, String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hashCode, 2130837907), null, this.mDefaultAvatarRect, this.mPaint);
    Bitmap localBitmap;
    int i;
    int j;
    do
    {
      return;
      localBitmap = ImageLoader.getInstance(getContext()).getImage(paramString, this.avatarLayout.width, this.avatarLayout.height);
      if (localBitmap == null)
      {
        paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hashCode, 2130837907), null, this.mDefaultAvatarRect, this.mPaint);
        ImageLoader.getInstance(getContext()).loadImage(paramString, null, this, this.avatarLayout.width, this.avatarLayout.height, this);
        return;
      }
      i = localBitmap.getWidth();
      j = localBitmap.getHeight();
      k = Math.min(i, j);
    }
    while (k == 0);
    float f1 = this.mAvatarRectF.width() / k;
    float f2 = (i - k) / 2;
    float f3 = (j - k) / 2;
    this.mAvatarPaint.setShader(new BitmapShader(localBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP));
    int k = paramCanvas.save();
    paramCanvas.scale(f1, f1, this.mAvatarRectF.left, this.mAvatarRectF.top);
    paramCanvas.translate(this.mAvatarRectF.left - f2, this.mAvatarRectF.top - f3);
    this.mOriginRectF.set(0.0F, 0.0F, i, j);
    paramCanvas.drawRoundRect(this.mOriginRectF, this.avatarLayout.topMargin / f1, this.avatarLayout.topMargin / f1, this.mAvatarPaint);
    paramCanvas.restoreToCount(k);
  }

  private void drawCheckState(Canvas paramCanvas)
  {
    if (this.checked)
    {
      paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckStatePaint);
      paramCanvas.drawBitmap(BitmapResourceCache.getInstance().getResourceCacheByParent(getResources(), this.hashCode, 2130837754), null, this.mCheckRect, this.mPaint);
      return;
    }
    paramCanvas.drawCircle(this.mCheckRect.centerX(), this.mCheckRect.centerY(), this.checkbgLayout.width / 2, this.mCheckBgPaint);
  }

  private void drawInfo(Canvas paramCanvas, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Object localObject = paramString1;
    if (paramString1 == null)
      localObject = "";
    paramString1 = (String)localObject;
    if (((String)localObject).equalsIgnoreCase(""))
      paramString1 = paramString2;
    this.namePaint.getTextBounds(paramString1, 0, paramString1.length(), this.mTextBound);
    float f1 = this.nameLayout.leftMargin;
    float f2 = this.nameLayout.topMargin + (this.nameLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
    int i;
    if (this.checked)
    {
      localObject = this.nameHighlightPaint;
      paramCanvas.drawText(paramString1, f1, f2, (Paint)localObject);
      paramString1 = BitmapResourceCache.getInstance();
      localObject = getResources();
      int j = this.hashCode;
      if (!this.isPlaying)
        break label573;
      i = 2130837908;
      label141: paramString1 = paramString1.getResourceCacheByParent((Resources)localObject, j, i);
      this.mPlayRect.set(this.nameLayout.leftMargin + this.mTextBound.width() + this.playStateLayout.width, this.nameLayout.topMargin + (this.nameLayout.height - this.playStateLayout.height) / 2, this.nameLayout.leftMargin + this.mTextBound.width() + this.playStateLayout.width + this.playStateLayout.width, this.nameLayout.topMargin + (this.nameLayout.height + this.playStateLayout.height) / 2);
      paramCanvas.drawBitmap(paramString1, null, this.mPlayRect, this.mPaint);
      paramString1 = paramString2;
      if (paramString2 == null)
        paramString1 = "";
      localObject = paramString1;
      paramString2 = paramString3;
      if (paramString4 != null)
      {
        localObject = paramString1;
        paramString2 = paramString3;
        if (!paramString4.equalsIgnoreCase(""))
        {
          localObject = TextUtils.ellipsize(paramString4, this.eclipsePaint, this.checkbgLayout.leftMargin - this.nameLayout.leftMargin, TextUtils.TruncateAt.END).toString();
          paramString2 = paramString1 + " " + paramString3;
        }
      }
      this.infoPaint.getTextBounds((String)localObject, 0, ((String)localObject).length(), this.mTextBound);
      f1 = this.infoLayout.leftMargin;
      f2 = this.nameLayout.getBottom() + this.infoLayout.topMargin + (this.infoLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
      if (!this.checked)
        break label581;
      paramString1 = this.infoHighlightPaint;
      label459: paramCanvas.drawText((String)localObject, f1, f2, paramString1);
      this.infoPaint.getTextBounds(paramString2, 0, paramString2.length(), this.mTextBound);
      f1 = this.infoLayout.leftMargin;
      f2 = this.nameLayout.getBottom() + this.infoLayout.getBottom() + (this.infoLayout.height - this.mTextBound.top - this.mTextBound.bottom) / 2;
      if (!this.checked)
        break label589;
    }
    label573: label581: label589: for (paramString1 = this.infoHighlightPaint; ; paramString1 = this.infoPaint)
    {
      paramCanvas.drawText(paramString2, f1, f2, paramString1);
      return;
      localObject = this.namePaint;
      break;
      i = 2130837909;
      break label141;
      paramString1 = this.infoPaint;
      break label459;
    }
  }

  private void drawItem(Canvas paramCanvas, RingToneNode paramRingToneNode)
  {
    if (paramRingToneNode == null)
      return;
    BroadcasterNode localBroadcasterNode = paramRingToneNode.getBroadcaster();
    Object localObject;
    int i;
    if (localBroadcasterNode == null)
    {
      localObject = "";
      drawAvatar(paramCanvas, (String)localObject);
      str = "";
      if (paramRingToneNode.downloadInfo != null)
      {
        i = paramRingToneNode.downloadInfo.fileSize;
        localObject = str;
        if (i > 0)
        {
          if (i >= 1000)
            break label137;
          localObject = "" + String.format("%dB  ", new Object[] { Integer.valueOf(i) });
        }
        label98: if (localBroadcasterNode != null)
          break label245;
      }
    }
    label137: label245: for (String str = null; ; str = localBroadcasterNode.nick)
    {
      drawInfo(paramCanvas, str, paramRingToneNode.ringDesc, (String)localObject, paramRingToneNode.getBelongRadio());
      drawCheckState(paramCanvas);
      return;
      localObject = localBroadcasterNode.avatar;
      break;
      if (i < 1000000)
      {
        localObject = "" + String.format("%dkB  ", new Object[] { Integer.valueOf(i / 1000) });
        break label98;
      }
      localObject = str;
      if (i >= 1000000000)
        break label98;
      localObject = "" + String.format("%.1fMB  ", new Object[] { Float.valueOf(i / 1000000.0F) });
      break label98;
    }
  }

  private void drawLine(Canvas paramCanvas)
  {
    SkinManager.getInstance().drawHorizontalLine(paramCanvas, this.lineLayout.leftMargin, this.itemLayout.width, this.itemLayout.height - this.lineLayout.height, this.lineLayout.height);
  }

  private void findSelected()
  {
    if (this.ringtoneId == null);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 1:
    case 2:
    case 3:
    default:
    case 0:
    }
    while (true)
    {
      return super.dispatchTouchEvent(paramMotionEvent);
      this.touchDownY = paramMotionEvent.getY();
      this.touchDownX = paramMotionEvent.getX();
    }
  }

  public void loadImageFinish(boolean paramBoolean, String paramString, Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    if (paramBoolean)
      invalidate();
  }

  public void onClick(View paramView)
  {
    int i = (this.itemLayout.height - this.checkbgLayout.height) / 2;
    if ((this.touchDownX > this.checkbgLayout.leftMargin - this.TouchSlop) && (this.touchDownX < this.checkbgLayout.leftMargin + this.checkbgLayout.width + this.TouchSlop) && (this.touchDownY > i - this.TouchSlop) && (this.touchDownY < this.itemLayout.height - i + this.TouchSlop))
    {
      dispatchActionEvent("changeCheckState", null);
      return;
    }
    dispatchActionEvent("previewRingtone", null);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.ringtoneInfo == null)
      return;
    paramCanvas.save();
    paramCanvas.setDrawFilter(this.filter);
    drawLine(paramCanvas);
    drawItem(paramCanvas, this.ringtoneInfo);
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    this.itemLayout.scaleToBounds(paramInt1, paramInt2);
    this.nameLayout.scaleToBounds(this.itemLayout);
    this.lineLayout.scaleToBounds(this.itemLayout);
    this.avatarLayout.scaleToBounds(this.itemLayout);
    this.infoLayout.scaleToBounds(this.itemLayout);
    this.playStateLayout.scaleToBounds(this.itemLayout);
    this.iconLayout.scaleToBounds(this.itemLayout);
    this.checkbgLayout.scaleToBounds(this.itemLayout);
    this.checkStateLayout.scaleToBounds(this.checkbgLayout);
    this.mCheckBgPaint.setStrokeWidth(this.checkStateLayout.leftMargin);
    this.mCheckRect.set(this.checkbgLayout.leftMargin + (this.checkbgLayout.width - this.checkStateLayout.width) / 2, (this.itemLayout.height - this.checkStateLayout.height) / 2, this.checkbgLayout.leftMargin + (this.checkbgLayout.width + this.checkStateLayout.width) / 2, (this.itemLayout.height + this.checkStateLayout.height) / 2);
    this.namePaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.nameHighlightPaint.setTextSize(SkinManager.getInstance().getNormalTextSize());
    this.infoPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.eclipsePaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.infoHighlightPaint.setTextSize(SkinManager.getInstance().getSubTextSize());
    this.mAvatarRectF.set(this.avatarLayout.leftMargin, (this.itemLayout.height - this.avatarLayout.height) / 2, this.avatarLayout.leftMargin + this.avatarLayout.width, (this.itemLayout.height + this.avatarLayout.height) / 2);
    this.mDefaultAvatarRect.set(this.avatarLayout.leftMargin, (this.itemLayout.height - this.avatarLayout.height) / 2, this.avatarLayout.leftMargin + this.avatarLayout.width, (this.itemLayout.height + this.avatarLayout.height) / 2);
    setMeasuredDimension(this.itemLayout.width, this.itemLayout.height);
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("content"))
    {
      this.ringtoneInfo = ((RingToneNode)paramObject);
      invalidate();
    }
    do
    {
      return;
      if (paramString.equalsIgnoreCase("uncheck"))
      {
        this.checked = false;
        invalidate();
        return;
      }
      if (paramString.equalsIgnoreCase("check"))
      {
        this.checked = true;
        invalidate();
        return;
      }
      if (paramString.equalsIgnoreCase("setData"))
      {
        findSelected();
        invalidate();
        return;
      }
      if (paramString.equalsIgnoreCase("setChecked"))
      {
        this.ringtoneId = ((String)paramObject);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("setPlay"));
    this.isPlaying = ((Boolean)paramObject).booleanValue();
    invalidate();
  }

  public void updateImageViewFinish(boolean paramBoolean, ImageView paramImageView, String paramString, Bitmap paramBitmap)
  {
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.personalcenter.clock.djringtone.DJRingtoneItemView
 * JD-Core Version:    0.6.2
 */