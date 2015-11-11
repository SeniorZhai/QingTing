package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

class RefreshHeader extends ViewGroup
{
  private RotateAnimation arrowAnimation;
  private ImageView arrowImage;
  private ViewLayout arrowLayout = ViewLayout.createViewLayoutWithBoundsLT(36, 60, 480, 80, 72, 10, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private ViewLayout contentLayout = ViewLayout.createViewLayoutWithBoundsLT(480, 80, 480, 80, 0, 0, ViewLayout.CW);
  private AnimationDrawable loadingAnimation;
  private ImageView loadingImage;
  private ViewLayout loadingLayout = ViewLayout.createViewLayoutWithBoundsLT(40, 40, 480, 80, 70, 20, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private boolean onRefreshing = false;
  private float position = -1.0F;
  private boolean reverseArrow = false;
  private RotateAnimation reverseArrowAnimation;
  private boolean sizeChanged = false;
  private ViewLayout tipLayout = ViewLayout.createViewLayoutWithBoundsLT(280, 30, 480, 80, 100, 10, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private TextView tipText;
  private int topExtendPadding = 100;
  private ViewLayout updateLayout = ViewLayout.createViewLayoutWithBoundsLT(280, 30, 480, 80, 100, 40, ViewLayout.LT | ViewLayout.SLT | ViewLayout.CW);
  private TextView updateText;
  private long updateTime = 0L;

  public RefreshHeader(Context paramContext)
  {
    super(paramContext);
    this.arrowImage = new ImageView(paramContext);
    this.arrowImage.setImageDrawable(buildLargeArrowShape());
    addView(this.arrowImage);
    this.loadingImage = new ImageView(paramContext);
    this.loadingAnimation = ProgressIndicatorView.buildAnimationDrawable(40, 150);
    this.loadingImage.setBackgroundDrawable(this.loadingAnimation);
    this.loadingImage.setVisibility(4);
    addView(this.loadingImage);
    this.tipText = new TextView(paramContext);
    this.tipText.setTextColor(-1);
    this.tipText.setGravity(17);
    addView(this.tipText);
    this.updateText = new TextView(paramContext);
    this.updateText.setTextColor(-1);
    this.updateText.setGravity(17);
    addView(this.updateText);
    this.arrowAnimation = new RotateAnimation(0.0F, -180.0F, 1, 0.5F, 1, 0.5F);
    this.arrowAnimation.setInterpolator(new LinearInterpolator());
    this.arrowAnimation.setDuration(250L);
    this.arrowAnimation.setFillAfter(true);
    this.reverseArrowAnimation = new RotateAnimation(-180.0F, 0.0F, 1, 0.5F, 1, 0.5F);
    this.reverseArrowAnimation.setInterpolator(new LinearInterpolator());
    this.reverseArrowAnimation.setDuration(200L);
    this.reverseArrowAnimation.setFillAfter(true);
  }

  protected Drawable buildLargeArrowShape()
  {
    float f1 = 35 / 2.0F;
    float f2 = 18 / 2.0F;
    int i = 80 - 10;
    Object localObject = new Path();
    ((Path)localObject).moveTo(f1, i);
    ((Path)localObject).lineTo(0.0F, 52);
    ((Path)localObject).lineTo(f2, 52);
    ((Path)localObject).lineTo(f2, 34);
    ((Path)localObject).lineTo(36 - f2, 34);
    ((Path)localObject).lineTo(36 - f2, 52);
    ((Path)localObject).lineTo(36, 52);
    ((Path)localObject).lineTo(f1, i);
    int j = 34 - 4;
    i = 0;
    while (true)
    {
      if (i >= 2)
      {
        localObject = new ShapeDrawable(new PathShape((Path)localObject, 36, 80));
        ((ShapeDrawable)localObject).setIntrinsicWidth(36);
        ((ShapeDrawable)localObject).setIntrinsicHeight(80);
        ((ShapeDrawable)localObject).setBounds(new Rect(0, 0, 36, 80));
        ((ShapeDrawable)localObject).setPadding(new Rect(0, 0, 0, 0));
        Paint localPaint = ((ShapeDrawable)localObject).getPaint();
        localPaint.setStyle(Paint.Style.FILL);
        localPaint.setAntiAlias(true);
        f1 = 80;
        Shader.TileMode localTileMode = Shader.TileMode.CLAMP;
        localPaint.setShader(new LinearGradient(0.0F, 0.0F, 0.0F, f1, new int[] { 16777215, -1 }, new float[] { 0.0F, 1.0F }, localTileMode));
        return localObject;
      }
      ((Path)localObject).addRect(f2, j - 4, 36 - f2, j, Path.Direction.CCW);
      j -= 8;
      i += 1;
    }
  }

  protected Drawable buildMiddleArrowShape()
  {
    float f1 = 35 / 2.0F;
    float f2 = 18 / 2.0F;
    Object localObject1 = new Path();
    ((Path)localObject1).moveTo(f1, 80);
    ((Path)localObject1).lineTo(0.0F, 56);
    ((Path)localObject1).lineTo(f2, 56);
    ((Path)localObject1).lineTo(f2, 51);
    ((Path)localObject1).lineTo(36 - f2, 51);
    ((Path)localObject1).lineTo(36 - f2, 56);
    ((Path)localObject1).lineTo(36, 56);
    ((Path)localObject1).lineTo(f1, 80);
    Object localObject2 = new ShapeDrawable(new PathShape((Path)localObject1, 36, 80));
    ((ShapeDrawable)localObject2).setIntrinsicWidth(36);
    ((ShapeDrawable)localObject2).setIntrinsicHeight(80);
    ((ShapeDrawable)localObject2).setBounds(new Rect(0, 0, 36, 80));
    ((ShapeDrawable)localObject2).setPadding(new Rect(0, 0, 0, 0));
    localObject1 = ((ShapeDrawable)localObject2).getPaint();
    ((Paint)localObject1).setColor(-1);
    ((Paint)localObject1).setStyle(Paint.Style.FILL);
    ((Paint)localObject1).setAntiAlias(true);
    localObject1 = new Drawable[4];
    localObject1[0] = localObject2;
    int j = 51 - 6;
    int i = 0;
    while (true)
    {
      if (i >= 3)
        return new LayerDrawable((Drawable[])localObject1);
      localObject2 = new Path();
      ((Path)localObject2).addRect(f2, j - 10, 36 - f2, j, Path.Direction.CCW);
      localObject2 = new ShapeDrawable(new PathShape((Path)localObject2, 36, 80));
      ((ShapeDrawable)localObject2).setIntrinsicWidth(36);
      ((ShapeDrawable)localObject2).setIntrinsicHeight(80);
      ((ShapeDrawable)localObject2).setBounds(new Rect(0, 0, 36, 80));
      ((ShapeDrawable)localObject2).setPadding(new Rect(0, 0, 0, 0));
      Paint localPaint = ((ShapeDrawable)localObject2).getPaint();
      localPaint.setColor(new int[] { -570425345, -1711276033, 1442840575 }[i]);
      localPaint.setStyle(Paint.Style.FILL);
      localPaint.setAntiAlias(true);
      localObject1[(i + 1)] = localObject2;
      j -= 16;
      i += 1;
    }
  }

  public int getContentHeight()
  {
    return this.contentLayout.height;
  }

  public float getPosition()
  {
    return this.position;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = getPaddingTop();
    this.arrowImage.layout(this.arrowLayout.getLeft(), this.arrowLayout.getTop() + paramInt1, this.arrowLayout.getRight(), this.arrowLayout.getBottom() + paramInt1);
    this.loadingImage.layout(this.loadingLayout.getLeft(), this.loadingLayout.getTop() + paramInt1, this.loadingLayout.getRight(), this.loadingLayout.getBottom() + paramInt1);
    this.tipText.layout(this.tipLayout.getLeft(), this.tipLayout.getTop() + paramInt1, this.tipLayout.getRight(), this.tipLayout.getBottom() + paramInt1);
    this.updateText.layout(this.updateLayout.getLeft(), this.updateLayout.getTop() + paramInt1, this.updateLayout.getRight(), this.updateLayout.getBottom() + paramInt1);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    paramInt2 = View.MeasureSpec.getSize(paramInt2);
    int i = this.contentLayout.height;
    this.contentLayout.scaleToBounds(paramInt1, paramInt2);
    this.arrowLayout.scaleToBounds(this.contentLayout);
    this.loadingLayout.scaleToBounds(this.contentLayout);
    this.tipLayout.scaleToBounds(this.contentLayout);
    this.updateLayout.scaleToBounds(this.contentLayout);
    if (i != this.contentLayout.height)
      this.sizeChanged = true;
    setPosition(this.position);
    this.arrowImage.measure(this.arrowLayout.getWidthMeasureSpec(), this.arrowLayout.getHeightMeasureSpec());
    this.tipText.setTextSize(0, this.tipLayout.height * 0.7F);
    this.tipText.measure(this.tipLayout.getWidthMeasureSpec(), this.tipLayout.getHeightMeasureSpec());
    this.updateText.setTextSize(0, this.updateLayout.height * 0.7F);
    this.updateText.measure(this.updateLayout.getWidthMeasureSpec(), this.updateLayout.getHeightMeasureSpec());
    setMeasuredDimension(this.contentLayout.width, this.contentLayout.height + getPaddingTop() + getPaddingBottom());
  }

  public void setOnRefreshing(boolean paramBoolean)
  {
    int j = 0;
    this.onRefreshing = paramBoolean;
    this.tipText.setText("正在刷新，请稍后");
    setUpdateTime();
    if (this.onRefreshing)
      this.arrowImage.clearAnimation();
    ImageView localImageView = this.arrowImage;
    if (this.onRefreshing)
    {
      i = 4;
      localImageView.setVisibility(i);
      localImageView = this.loadingImage;
      if (!this.onRefreshing)
        break label81;
    }
    label81: for (int i = j; ; i = 4)
    {
      localImageView.setVisibility(i);
      return;
      i = 0;
      break;
    }
  }

  public void setPosition(float paramFloat)
  {
    setPosition(paramFloat, false);
  }

  public void setPosition(float paramFloat, boolean paramBoolean)
  {
    int i;
    if ((!this.sizeChanged) && (paramFloat == this.position) && (!paramBoolean))
    {
      i = 0;
      this.position = paramFloat;
      setPadding(0, (int)(this.position * this.contentLayout.height + (this.position + 1.0F) * this.topExtendPadding), 0, 0);
      if (i != 0)
        break label71;
    }
    label71: 
    do
    {
      return;
      i = 1;
      break;
      requestLayout();
      this.sizeChanged = false;
      if (this.onRefreshing)
      {
        this.tipText.setText("正在刷新，请稍后");
        return;
      }
      if ((this.position >= 0.0F) && (!this.reverseArrow))
      {
        this.arrowImage.clearAnimation();
        this.arrowImage.startAnimation(this.arrowAnimation);
        this.reverseArrow = true;
        this.tipText.setText("松开即可刷新");
        return;
      }
    }
    while ((this.position >= 0.0F) || (!this.reverseArrow));
    this.arrowImage.clearAnimation();
    this.arrowImage.startAnimation(this.reverseArrowAnimation);
    this.reverseArrow = false;
    this.tipText.setText("下拉可以刷新");
  }

  public void setTopExtendPadding(int paramInt)
  {
    this.topExtendPadding = paramInt;
    setPosition(this.position, true);
  }

  public void setUpdateTime()
  {
    setUpdateTime(this.updateTime);
  }

  public void setUpdateTime(long paramLong)
  {
    this.updateTime = paramLong;
    long l1 = Calendar.getInstance().getTimeInMillis() - this.updateTime;
    String str;
    if (this.updateTime == 0L)
      str = "";
    while (true)
    {
      this.updateText.setText(str);
      return;
      if (l1 < 0L)
      {
        str = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(paramLong));
      }
      else if (l1 < 60000L)
      {
        str = String.format("最后更新： %d 秒前", new Object[] { Integer.valueOf((int)(l1 / 1000L)) });
      }
      else
      {
        long l2 = 60000L * 60L;
        if (l1 < l2)
        {
          str = String.format("最后更新： %d 分钟前", new Object[] { Integer.valueOf((int)(l1 / 60000L)) });
        }
        else
        {
          l2 *= 24L;
          if (l1 < l2)
          {
            str = String.format("最后更新： %d 小时前", new Object[] { Integer.valueOf((int)(l1 / 3600000L)) });
          }
          else
          {
            l2 *= 7L;
            if (l1 < l2)
            {
              str = String.format("最后更新： %d 天前", new Object[] { Integer.valueOf((int)(l1 / 86400000L)) });
            }
            else
            {
              l2 *= 4L;
              if (l1 < l2)
                str = String.format("最后更新： %d 周前", new Object[] { Integer.valueOf((int)(l1 / 604800000L)) });
              else if (l1 < l2 * 13L)
                str = String.format("最后更新： %d 个月之前", new Object[] { Integer.valueOf((int)(l1 / -1875767296L)) });
              else
                str = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(paramLong));
            }
          }
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.RefreshHeader
 * JD-Core Version:    0.6.2
 */