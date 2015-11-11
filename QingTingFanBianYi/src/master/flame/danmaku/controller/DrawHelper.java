package master.flame.danmaku.controller;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;

public class DrawHelper
{
  public static Paint PAINT;
  public static Paint PAINT_FPS;
  public static RectF RECT = new RectF();
  private static boolean USE_DRAWCOLOR_MODE_CLEAR;
  private static boolean USE_DRAWCOLOR_TO_CLEAR_CANVAS = true;

  static
  {
    USE_DRAWCOLOR_MODE_CLEAR = true;
    PAINT = new Paint();
    PAINT.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    PAINT.setColor(0);
  }

  public static void clearCanvas(Canvas paramCanvas)
  {
    if (USE_DRAWCOLOR_TO_CLEAR_CANVAS)
    {
      if (USE_DRAWCOLOR_MODE_CLEAR)
      {
        paramCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
        return;
      }
      paramCanvas.drawColor(0);
      return;
    }
    RECT.set(0.0F, 0.0F, paramCanvas.getWidth(), paramCanvas.getHeight());
    clearCanvas(paramCanvas, RECT);
  }

  public static void clearCanvas(Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    RECT.set(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    clearCanvas(paramCanvas, RECT);
  }

  private static void clearCanvas(Canvas paramCanvas, RectF paramRectF)
  {
    if ((paramRectF.width() <= 0.0F) || (paramRectF.height() <= 0.0F))
      return;
    paramCanvas.drawRect(paramRectF, PAINT);
  }

  public static void drawFPS(Canvas paramCanvas, String paramString)
  {
    if (PAINT_FPS == null)
    {
      PAINT_FPS = new Paint();
      PAINT_FPS.setColor(-65536);
      PAINT_FPS.setTextSize(30.0F);
    }
    int i = paramCanvas.getHeight() - 50;
    clearCanvas(paramCanvas, 10.0F, i - 50, (int)(PAINT_FPS.measureText(paramString) + 20.0F), paramCanvas.getHeight());
    paramCanvas.drawText(paramString, 10.0F, i, PAINT_FPS);
  }

  public static void fillTransparent(Canvas paramCanvas)
  {
    paramCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
  }

  public static void useDrawColorToClearCanvas(boolean paramBoolean1, boolean paramBoolean2)
  {
    USE_DRAWCOLOR_TO_CLEAR_CANVAS = paramBoolean1;
    USE_DRAWCOLOR_MODE_CLEAR = paramBoolean2;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.controller.DrawHelper
 * JD-Core Version:    0.6.2
 */