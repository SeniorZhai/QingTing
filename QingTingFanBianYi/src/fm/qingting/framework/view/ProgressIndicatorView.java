package fm.qingting.framework.view;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.widget.ImageView;

public class ProgressIndicatorView extends ImageView
{
  private AnimationDrawable animationDrawable = buildAnimationDrawable();
  private boolean autoRun = true;

  public ProgressIndicatorView(Context paramContext)
  {
    super(paramContext);
    setBackgroundDrawable(this.animationDrawable);
  }

  public static AnimationDrawable buildAnimationDrawable()
  {
    return buildAnimationDrawable(40, 150, -1);
  }

  public static AnimationDrawable buildAnimationDrawable(int paramInt1, int paramInt2)
  {
    return buildAnimationDrawable(paramInt1, paramInt2, -1);
  }

  public static AnimationDrawable buildAnimationDrawable(int paramInt1, int paramInt2, int paramInt3)
  {
    float f1 = paramInt1 / 2.0F;
    float f2 = paramInt1 / 2.0F;
    float f3 = paramInt1 / 2.0F;
    float f4 = paramInt1 / 10.0F / 2.0F;
    float f5 = paramInt1 / 4.0F + f4 / 4.0F;
    AnimationDrawable localAnimationDrawable = new AnimationDrawable();
    localAnimationDrawable.setOneShot(false);
    Path localPath = new Path();
    localPath.addRect(f3 - f4, f4, f3 + f4, f5 - f4, Path.Direction.CCW);
    localPath.addCircle(f3, f4, f4, Path.Direction.CCW);
    localPath.addCircle(f3, f5 - f4, f4, Path.Direction.CCW);
    int i = 0;
    if (i >= 12)
      return localAnimationDrawable;
    Drawable[] arrayOfDrawable = new Drawable[12];
    int j = 0;
    while (true)
    {
      if (j >= 12)
      {
        localAnimationDrawable.addFrame(new LayerDrawable(arrayOfDrawable), paramInt2);
        i += 1;
        break;
      }
      Object localObject1 = new Matrix();
      ((Matrix)localObject1).setRotate((i - j) * 30, f1, f2);
      Object localObject2 = new Path();
      localPath.transform((Matrix)localObject1, (Path)localObject2);
      localObject1 = new ShapeDrawable(new PathShape((Path)localObject2, paramInt1, paramInt1));
      ((ShapeDrawable)localObject1).setIntrinsicWidth(paramInt1);
      ((ShapeDrawable)localObject1).setIntrinsicHeight(paramInt1);
      ((ShapeDrawable)localObject1).setBounds(new Rect(0, 0, paramInt1, paramInt1));
      ((ShapeDrawable)localObject1).setPadding(new Rect(0, 0, 0, 0));
      localObject2 = ((ShapeDrawable)localObject1).getPaint();
      ((Paint)localObject2).setColor((255 - j * 14 << 24) + paramInt3);
      ((Paint)localObject2).setStyle(Paint.Style.FILL);
      ((Paint)localObject2).setAntiAlias(true);
      arrayOfDrawable[j] = localObject1;
      j += 1;
    }
  }

  public void setAutoRun(boolean paramBoolean)
  {
    this.autoRun = paramBoolean;
  }

  public void setVisibility(int paramInt)
  {
    super.setVisibility(paramInt);
    if (!this.autoRun)
      return;
    if (paramInt == 0)
    {
      start();
      return;
    }
    stop();
  }

  public void start()
  {
    if (this.animationDrawable != null)
      this.animationDrawable.start();
  }

  public void stop()
  {
    if (this.animationDrawable != null)
      this.animationDrawable.stop();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.ProgressIndicatorView
 * JD-Core Version:    0.6.2
 */