package fm.qingting.framework.utils;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.Drawable;
import java.io.ByteArrayOutputStream;

public class ImageUtils
{
  public static byte[] bitmap2Bytes(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static Bitmap bytes2Bimap(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte.length != 0)
      return BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    return null;
  }

  public static Bitmap createReflectionImageWithOrigin(Bitmap paramBitmap, float paramFloat, int paramInt1, int paramInt2, int paramInt3)
  {
    return createReflectionImageWithOrigin(paramBitmap, paramFloat, paramInt1, paramInt2, paramInt3, Bitmap.Config.ARGB_8888);
  }

  public static Bitmap createReflectionImageWithOrigin(Bitmap paramBitmap, float paramFloat, int paramInt1, int paramInt2, int paramInt3, Bitmap.Config paramConfig)
  {
    int k = paramBitmap.getWidth();
    int m = paramBitmap.getHeight();
    Object localObject = new Matrix();
    ((Matrix)localObject).preScale(1.0F, -1.0F);
    int i = (int)(m * paramFloat);
    localObject = Bitmap.createBitmap(paramBitmap, 0, m - i, k, i, (Matrix)localObject, false);
    paramConfig = Bitmap.createBitmap(k, m + i + paramInt3, paramConfig);
    Canvas localCanvas = new Canvas(paramConfig);
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, null);
    Paint localPaint = new Paint();
    localPaint.setColor(0);
    localCanvas.drawRect(0.0F, m, k, m + paramInt3, localPaint);
    localCanvas.drawBitmap((Bitmap)localObject, 0.0F, m + paramInt3, null);
    localObject = new Paint();
    i = paramInt1;
    if (paramInt1 < 0)
      i = 0;
    int j = i;
    if (i > 255)
      j = 255;
    paramInt1 = paramInt2;
    if (paramInt2 < 0)
      paramInt1 = 0;
    paramInt2 = paramInt1;
    if (paramInt1 > 255)
      paramInt2 = 255;
    ((Paint)localObject).setShader(new LinearGradient(0.0F, paramBitmap.getHeight() + paramInt3, 0.0F, paramConfig.getHeight(), 16777215 + (j << 24), 16777215 + (paramInt2 << 24), Shader.TileMode.CLAMP));
    ((Paint)localObject).setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
    localCanvas.drawRect(0.0F, m, k, paramConfig.getHeight() + paramInt3, (Paint)localObject);
    return paramConfig;
  }

  public static Bitmap drawableToBitmap(Drawable paramDrawable)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    if (paramDrawable.getOpacity() != -1);
    for (Object localObject = Bitmap.Config.ARGB_8888; ; localObject = Bitmap.Config.RGB_565)
    {
      localObject = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject);
      Canvas localCanvas = new Canvas((Bitmap)localObject);
      paramDrawable.setBounds(0, 0, i, j);
      paramDrawable.draw(localCanvas);
      return localObject;
    }
  }

  public static Bitmap getRoundedCornerBitmap(Bitmap paramBitmap, float paramFloat)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    Rect localRect = new Rect(0, 0, paramBitmap.getWidth(), paramBitmap.getHeight());
    RectF localRectF = new RectF(localRect);
    localPaint.setAntiAlias(true);
    localCanvas.drawARGB(0, 0, 0, 0);
    localPaint.setColor(-12434878);
    localCanvas.drawRoundRect(localRectF, paramFloat, paramFloat, localPaint);
    localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
    localCanvas.drawBitmap(paramBitmap, localRect, localRect, localPaint);
    return localBitmap;
  }

  public static byte[] ninePatchChunkBuider(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    int i = 3;
    int m = 3;
    if (paramInt3 == 0)
      i = 3 - 1;
    int j = i;
    if (paramInt4 == 0)
      j = i - 1;
    int k = j;
    if (paramInt3 + paramInt4 == paramInt1)
      k = 0;
    i = m;
    if (paramInt5 == 0)
      i = 3 - 1;
    j = i;
    if (paramInt6 == 0)
      j = i - 1;
    if (paramInt5 + paramInt6 == paramInt2)
      j = 0;
    i = k * j;
    byte[] arrayOfByte = new byte[i * 4 + 48];
    arrayOfByte[0] = 1;
    arrayOfByte[1] = 2;
    arrayOfByte[2] = 2;
    arrayOfByte[3] = ((byte)i);
    putInt(arrayOfByte, 12, paramInt3);
    putInt(arrayOfByte, 16, paramInt4);
    putInt(arrayOfByte, 20, paramInt5);
    putInt(arrayOfByte, 24, paramInt6);
    putInt(arrayOfByte, 28, 0);
    putInt(arrayOfByte, 32, paramInt3);
    paramInt3 = 32 + 4;
    putInt(arrayOfByte, paramInt3, paramInt1 - paramInt4);
    paramInt1 = paramInt3 + 4;
    putInt(arrayOfByte, paramInt1, paramInt5);
    paramInt1 += 4;
    putInt(arrayOfByte, paramInt1, paramInt2 - paramInt6);
    paramInt2 = paramInt1 + 4;
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= i)
        return arrayOfByte;
      putInt(arrayOfByte, paramInt2, 1);
      paramInt2 += 4;
      paramInt1 += 1;
    }
  }

  public static void putInt(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    paramArrayOfByte[paramInt1] = ((byte)(paramInt2 & 0xFF));
    paramInt2 >>= 8;
    paramArrayOfByte[(paramInt1 + 1)] = ((byte)(paramInt2 & 0xFF));
    paramInt2 >>= 8;
    paramArrayOfByte[(paramInt1 + 2)] = ((byte)(paramInt2 & 0xFF));
    paramArrayOfByte[(paramInt1 + 3)] = ((byte)(paramInt2 >> 8 & 0xFF));
  }

  public static Bitmap zoomBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / i, paramInt2 / j);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.utils.ImageUtils
 * JD-Core Version:    0.6.2
 */