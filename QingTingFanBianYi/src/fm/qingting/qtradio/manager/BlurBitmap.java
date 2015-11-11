package fm.qingting.qtradio.manager;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import java.io.FileOutputStream;
import java.lang.reflect.Array;

public class BlurBitmap
{
  private int _height = -1;
  private Bitmap _image;
  private int _width = -1;
  private boolean alpha = false;
  private int[] currentPixels;
  private Rect mRect;
  private int[] originalPixels;

  public BlurBitmap(Bitmap paramBitmap)
  {
    this._width = paramBitmap.getWidth();
    this._height = paramBitmap.getHeight();
    this._image = paramBitmap;
    this.mRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    this.originalPixels = new int[this._width * this._height];
    this._image.getPixels(this.originalPixels, 0, this._width, 0, 0, this._width, this._height);
  }

  public BlurBitmap(Bitmap paramBitmap, Rect paramRect)
  {
    this._width = paramRect.width();
    this._height = paramRect.height();
    this._image = paramBitmap;
    this.originalPixels = new int[this._width * this._height];
    this.mRect = paramRect;
    this._image.getPixels(this.originalPixels, 0, this._width, paramRect.left, paramRect.top, this._width, this._height);
  }

  public Bitmap getImage()
  {
    return this._image;
  }

  public void process(int paramInt)
  {
    int i = paramInt;
    if (paramInt < 1)
      i = 1;
    this.currentPixels = ((int[])this.originalPixels.clone());
    int i11 = this._width - 1;
    int i12 = this._height - 1;
    paramInt = this._width * this._height;
    int i13 = i + i + 1;
    int[] arrayOfInt1 = new int[paramInt];
    int[] arrayOfInt2 = new int[paramInt];
    int[] arrayOfInt3 = new int[paramInt];
    int[] arrayOfInt4 = new int[Math.max(this._width, this._height)];
    paramInt = i13 + 1 >> 1;
    int j = paramInt * paramInt;
    int[] arrayOfInt5 = new int[j * 256];
    paramInt = 0;
    while (paramInt < j * 256)
    {
      arrayOfInt5[paramInt] = (paramInt / j);
      paramInt += 1;
    }
    int i6 = 0;
    int[][] arrayOfInt = (int[][])Array.newInstance(Integer.TYPE, new int[] { i13, 3 });
    int i14 = i + 1;
    int i5 = 0;
    int i7 = 0;
    int i8;
    int k;
    int m;
    int n;
    int i1;
    int i2;
    int i3;
    int i4;
    int i9;
    int[] arrayOfInt6;
    int i10;
    int i15;
    while (i5 < this._height)
    {
      paramInt = 0;
      i8 = -i;
      k = 0;
      m = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      i3 = 0;
      i4 = 0;
      j = 0;
      if (i8 <= i)
      {
        i9 = this.currentPixels[(Math.min(i11, Math.max(i8, 0)) + i7)];
        arrayOfInt6 = arrayOfInt[(i8 + i)];
        arrayOfInt6[0] = ((0xFF0000 & i9) >> 16);
        arrayOfInt6[1] = ((0xFF00 & i9) >> 8);
        arrayOfInt6[2] = (i9 & 0xFF);
        i9 = i14 - Math.abs(i8);
        i4 += arrayOfInt6[0] * i9;
        i3 += arrayOfInt6[1] * i9;
        i2 += i9 * arrayOfInt6[2];
        if (i8 > 0)
        {
          k += arrayOfInt6[0];
          j += arrayOfInt6[1];
          paramInt += arrayOfInt6[2];
        }
        while (true)
        {
          i8 += 1;
          break;
          i1 += arrayOfInt6[0];
          n += arrayOfInt6[1];
          m += arrayOfInt6[2];
        }
      }
      i8 = i3;
      i10 = 0;
      i3 = i;
      i9 = i4;
      i4 = i2;
      i2 = i10;
      if (i2 < this._width)
      {
        if (!this.alpha)
          if (Color.alpha(this.originalPixels[(this._width * i5 + i2)]) == 255)
            break label756;
        label756: for (boolean bool = true; ; bool = false)
        {
          this.alpha = bool;
          arrayOfInt1[i7] = arrayOfInt5[i9];
          arrayOfInt2[i7] = arrayOfInt5[i8];
          arrayOfInt3[i7] = arrayOfInt5[i4];
          arrayOfInt6 = arrayOfInt[((i3 - i + i13) % i13)];
          int i16 = arrayOfInt6[0];
          i15 = arrayOfInt6[1];
          i10 = arrayOfInt6[2];
          if (i5 == 0)
            arrayOfInt4[i2] = Math.min(i2 + i + 1, i11);
          int i17 = this.currentPixels[(arrayOfInt4[i2] + i6)];
          arrayOfInt6[0] = ((0xFF0000 & i17) >> 16);
          arrayOfInt6[1] = ((0xFF00 & i17) >> 8);
          arrayOfInt6[2] = (i17 & 0xFF);
          k += arrayOfInt6[0];
          j += arrayOfInt6[1];
          paramInt += arrayOfInt6[2];
          i9 = i9 - i1 + k;
          i8 = i8 - n + j;
          i4 = i4 - m + paramInt;
          i3 = (i3 + 1) % i13;
          arrayOfInt6 = arrayOfInt[(i3 % i13)];
          i1 = i1 - i16 + arrayOfInt6[0];
          n = n - i15 + arrayOfInt6[1];
          m = m - i10 + arrayOfInt6[2];
          k -= arrayOfInt6[0];
          j -= arrayOfInt6[1];
          paramInt -= arrayOfInt6[2];
          i7 += 1;
          i2 += 1;
          break;
        }
      }
      i6 += this._width;
      i5 += 1;
    }
    paramInt = 0;
    while (paramInt < this._width)
    {
      i5 = 0;
      i7 = -i * this._width;
      i6 = -i;
      i3 = 0;
      n = 0;
      i1 = 0;
      i2 = 0;
      j = 0;
      m = 0;
      k = 0;
      i4 = 0;
      if (i6 <= i)
      {
        i8 = Math.max(0, i7) + paramInt;
        arrayOfInt6 = arrayOfInt[(i6 + i)];
        arrayOfInt6[0] = arrayOfInt1[i8];
        arrayOfInt6[1] = arrayOfInt2[i8];
        arrayOfInt6[2] = arrayOfInt3[i8];
        i9 = i14 - Math.abs(i6);
        i10 = arrayOfInt1[i8];
        i11 = arrayOfInt2[i8];
        i15 = arrayOfInt3[i8];
        if (i6 > 0)
        {
          i3 += arrayOfInt6[0];
          i4 += arrayOfInt6[1];
          i5 += arrayOfInt6[2];
        }
        while (true)
        {
          i8 = i7;
          if (i6 < i12)
            i8 = i7 + this._width;
          i6 += 1;
          j = i15 * i9 + j;
          m = i11 * i9 + m;
          k = i10 * i9 + k;
          i7 = i8;
          break;
          i2 += arrayOfInt6[0];
          i1 += arrayOfInt6[1];
          n += arrayOfInt6[2];
        }
      }
      i9 = m;
      i10 = k;
      i11 = 0;
      k = paramInt;
      i6 = i5;
      i7 = i4;
      i8 = i3;
      m = n;
      n = i1;
      i1 = i2;
      i2 = i;
      i5 = i10;
      i4 = i9;
      i3 = j;
      j = i11;
      if (j < this._height)
      {
        if (this.alpha)
          this.currentPixels[k] = (0xFF000000 & this.currentPixels[k] | arrayOfInt5[i5] << 16 | arrayOfInt5[i4] << 8 | arrayOfInt5[i3]);
        while (true)
        {
          arrayOfInt6 = arrayOfInt[((i2 - i + i13) % i13)];
          i11 = arrayOfInt6[0];
          i10 = arrayOfInt6[1];
          i9 = arrayOfInt6[2];
          if (paramInt == 0)
            arrayOfInt4[j] = (Math.min(j + i14, i12) * this._width);
          i15 = arrayOfInt4[j] + paramInt;
          arrayOfInt6[0] = arrayOfInt1[i15];
          arrayOfInt6[1] = arrayOfInt2[i15];
          arrayOfInt6[2] = arrayOfInt3[i15];
          i8 += arrayOfInt6[0];
          i7 += arrayOfInt6[1];
          i6 += arrayOfInt6[2];
          i5 = i5 - i1 + i8;
          i4 = i4 - n + i7;
          i3 = i3 - m + i6;
          i2 = (i2 + 1) % i13;
          arrayOfInt6 = arrayOfInt[i2];
          i1 = i1 - i11 + arrayOfInt6[0];
          n = n - i10 + arrayOfInt6[1];
          m = m - i9 + arrayOfInt6[2];
          i8 -= arrayOfInt6[0];
          i7 -= arrayOfInt6[1];
          i6 -= arrayOfInt6[2];
          k += this._width;
          j += 1;
          break;
          this.currentPixels[k] = (0xFF000000 | arrayOfInt5[i5] << 16 | arrayOfInt5[i4] << 8 | arrayOfInt5[i3]);
        }
      }
      paramInt += 1;
    }
  }

  public Bitmap returnBlurredImage(int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(this._width, this._height, Bitmap.Config.RGB_565);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawBitmap(this._image, this.mRect, new Rect(0, 0, this._width, this._height), new Paint());
    localBitmap.setPixels(this.currentPixels, 0, this._width, 0, 0, this._width, this._height);
    if (paramInt != 0)
      localCanvas.drawColor(paramInt);
    return localBitmap;
  }

  public Bitmap returnBlurredImage(Bitmap paramBitmap)
  {
    Bitmap localBitmap = Bitmap.createBitmap(this._width, this._height, Bitmap.Config.RGB_565);
    Canvas localCanvas = new Canvas(localBitmap);
    Rect localRect = new Rect(0, 0, this._width, this._height);
    localCanvas.drawBitmap(this._image, this.mRect, localRect, new Paint());
    localBitmap.setPixels(this.currentPixels, 0, this._width, 0, 0, this._width, this._height);
    if (paramBitmap != null)
      localCanvas.drawBitmap(paramBitmap, null, localRect, new Paint());
    return localBitmap;
  }

  public Bitmap returnBlurredImage(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(this._width, this._height + paramInt, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawBitmap(this._image, this.mRect, new Rect(0, paramInt, this._width, this._height + paramInt), new Paint());
    localBitmap.setPixels(this.currentPixels, 0, this._width, 0, paramInt, this._width, this._height);
    if (paramBitmap != null)
      localCanvas.drawBitmap(paramBitmap, null, new Rect(0, 0, this._width, this._height + paramInt), new Paint());
    return localBitmap;
  }

  public Bitmap returnBlurredImageInPlay(int paramInt)
  {
    Bitmap localBitmap = Bitmap.createBitmap(this._width, this._height, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    localCanvas.drawBitmap(this._image, this.mRect, new Rect(0, 0, this._width, this._height), new Paint());
    localBitmap.setPixels(this.currentPixels, 0, this._width, 0, 0, this._width, this._height);
    if (paramInt != 0)
      localCanvas.drawColor(paramInt);
    return localBitmap;
  }

  public void saveIntoFile(String paramString)
  {
    Bitmap localBitmap = Bitmap.createBitmap(this._image.getWidth(), this._image.getHeight(), Bitmap.Config.RGB_565);
    new Canvas(localBitmap).drawBitmap(this._image, 0.0F, 0.0F, new Paint());
    localBitmap.setPixels(this.currentPixels, 0, this._width, 0, 0, this._width, this._height);
    try
    {
      paramString = new FileOutputStream(paramString);
      localBitmap.compress(Bitmap.CompressFormat.PNG, 90, paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.BlurBitmap
 * JD-Core Version:    0.6.2
 */