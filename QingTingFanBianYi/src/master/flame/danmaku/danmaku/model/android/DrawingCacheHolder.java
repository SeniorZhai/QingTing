package master.flame.danmaku.danmaku.model.android;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import java.lang.reflect.Array;
import tv.cjump.jni.NativeBitmapFactory;

public class DrawingCacheHolder
{
  public Bitmap bitmap;
  public Bitmap[][] bitmapArray;
  public Canvas canvas;
  public boolean drawn;
  public Object extra;
  public int height;
  private int mDensity;
  public int width;

  public DrawingCacheHolder()
  {
  }

  public DrawingCacheHolder(int paramInt1, int paramInt2)
  {
    buildCache(paramInt1, paramInt2, 0, true);
  }

  public DrawingCacheHolder(int paramInt1, int paramInt2, int paramInt3)
  {
    this.mDensity = paramInt3;
    buildCache(paramInt1, paramInt2, paramInt3, true);
  }

  private void eraseBitmap(Bitmap paramBitmap)
  {
    if ((paramBitmap != null) && (!paramBitmap.isRecycled()))
      paramBitmap.eraseColor(0);
  }

  private void eraseBitmapArray()
  {
    if (this.bitmapArray != null)
    {
      int i = 0;
      while (i < this.bitmapArray.length)
      {
        int j = 0;
        while (j < this.bitmapArray[i].length)
        {
          eraseBitmap(this.bitmapArray[i][j]);
          j += 1;
        }
        i += 1;
      }
    }
  }

  private void recycleBitmapArray()
  {
    if (this.bitmapArray != null)
    {
      int i = 0;
      while (i < this.bitmapArray.length)
      {
        int j = 0;
        while (j < this.bitmapArray[i].length)
        {
          if (this.bitmapArray[i][j] != null)
          {
            this.bitmapArray[i][j].recycle();
            this.bitmapArray[i][j] = null;
          }
          j += 1;
        }
        i += 1;
      }
      this.bitmapArray = ((Bitmap[][])null);
    }
  }

  public void buildCache(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = 1;
    if (paramBoolean)
      if ((paramInt1 != this.width) || (paramInt2 != this.height));
    while ((i != 0) && (this.bitmap != null) && (!this.bitmap.isRecycled()))
    {
      this.canvas.setBitmap(null);
      this.bitmap.eraseColor(0);
      this.canvas.setBitmap(this.bitmap);
      recycleBitmapArray();
      return;
      i = 0;
      continue;
      if ((paramInt1 > this.width) || (paramInt2 > this.height))
        i = 0;
    }
    if (this.bitmap != null)
      recycle();
    this.width = paramInt1;
    this.height = paramInt2;
    this.bitmap = NativeBitmapFactory.createBitmap(paramInt1, paramInt2, Bitmap.Config.ARGB_8888);
    if (paramInt3 > 0)
    {
      this.mDensity = paramInt3;
      this.bitmap.setDensity(paramInt3);
    }
    if (this.canvas == null)
    {
      this.canvas = new Canvas(this.bitmap);
      this.canvas.setDensity(paramInt3);
      return;
    }
    this.canvas.setBitmap(this.bitmap);
  }

  public final boolean draw(Canvas paramCanvas, float paramFloat1, float paramFloat2, Paint paramPaint)
  {
    boolean bool2 = false;
    label111: boolean bool1;
    if (this.bitmapArray != null)
    {
      int i = 0;
      while (i < this.bitmapArray.length)
      {
        int j = 0;
        if (j < this.bitmapArray[i].length)
        {
          Bitmap localBitmap = this.bitmapArray[i][j];
          float f1;
          if ((localBitmap != null) && (!localBitmap.isRecycled()))
          {
            f1 = localBitmap.getWidth() * j + paramFloat1;
            if ((f1 <= paramCanvas.getWidth()) && (localBitmap.getWidth() + f1 >= 0.0F))
              break label111;
          }
          while (true)
          {
            j += 1;
            break;
            float f2 = localBitmap.getHeight() * i + paramFloat2;
            if ((f2 <= paramCanvas.getHeight()) && (localBitmap.getHeight() + f2 >= 0.0F))
              paramCanvas.drawBitmap(localBitmap, f1, f2, paramPaint);
          }
        }
        i += 1;
      }
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      }
      while (this.bitmap == null);
      bool1 = bool2;
    }
    while (this.bitmap.isRecycled());
    paramCanvas.drawBitmap(this.bitmap, paramFloat1, paramFloat2, paramPaint);
    return true;
  }

  public void erase()
  {
    eraseBitmap(this.bitmap);
    eraseBitmapArray();
  }

  public void recycle()
  {
    this.height = 0;
    this.width = 0;
    if (this.bitmap != null)
    {
      this.bitmap.recycle();
      this.bitmap = null;
    }
    recycleBitmapArray();
    this.extra = null;
  }

  @SuppressLint({"NewApi"})
  public void splitWith(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    recycleBitmapArray();
    if ((this.width <= 0) || (this.height <= 0) || (this.bitmap == null) || (this.bitmap.isRecycled()));
    while ((this.width <= paramInt3) && (this.height <= paramInt4))
      return;
    paramInt1 = Math.min(paramInt3, paramInt1);
    paramInt2 = Math.min(paramInt4, paramInt2);
    paramInt3 = this.width / paramInt1;
    label107: int i;
    int j;
    Bitmap[][] arrayOfBitmap;
    Rect localRect1;
    Rect localRect2;
    if (this.width % paramInt1 == 0)
    {
      paramInt1 = 0;
      paramInt3 += paramInt1;
      paramInt4 = this.height / paramInt2;
      if (this.height % paramInt2 != 0)
        break label346;
      paramInt1 = 0;
      paramInt4 += paramInt1;
      i = this.width / paramInt3;
      j = this.height / paramInt4;
      arrayOfBitmap = (Bitmap[][])Array.newInstance(Bitmap.class, new int[] { paramInt4, paramInt3 });
      if (this.canvas == null)
      {
        this.canvas = new Canvas();
        if (this.mDensity > 0)
          this.canvas.setDensity(this.mDensity);
      }
      localRect1 = new Rect();
      localRect2 = new Rect();
      paramInt1 = 0;
    }
    while (true)
    {
      if (paramInt1 >= paramInt4)
        break label358;
      paramInt2 = 0;
      while (true)
        if (paramInt2 < paramInt3)
        {
          Bitmap[] arrayOfBitmap1 = arrayOfBitmap[paramInt1];
          Bitmap localBitmap = NativeBitmapFactory.createBitmap(i, j, Bitmap.Config.ARGB_8888);
          arrayOfBitmap1[paramInt2] = localBitmap;
          if (this.mDensity > 0)
            localBitmap.setDensity(this.mDensity);
          this.canvas.setBitmap(localBitmap);
          int k = paramInt2 * i;
          int m = paramInt1 * j;
          localRect1.set(k, m, k + i, m + j);
          localRect2.set(0, 0, localBitmap.getWidth(), localBitmap.getHeight());
          this.canvas.drawBitmap(this.bitmap, localRect1, localRect2, null);
          paramInt2 += 1;
          continue;
          paramInt1 = 1;
          break;
          label346: paramInt1 = 1;
          break label107;
        }
      paramInt1 += 1;
    }
    label358: this.canvas.setBitmap(this.bitmap);
    this.bitmapArray = arrayOfBitmap;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.android.DrawingCacheHolder
 * JD-Core Version:    0.6.2
 */