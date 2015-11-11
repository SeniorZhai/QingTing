package com.tencent.utils;

import java.util.ArrayList;

public abstract interface AsynLoadImgBack
{
  public static final int LOAD_IMAGE_COMPLETED = 0;
  public static final int LOAD_IMAGE_IMAGE_FORMAT_ERROR = 3;
  public static final int LOAD_IMAGE_NO_SDCARD = 2;
  public static final int LOAD_IMAGE_PATH_NULL = 1;

  public abstract void batchSaved(int paramInt, ArrayList<String> paramArrayList);

  public abstract void saved(int paramInt, String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.utils.AsynLoadImgBack
 * JD-Core Version:    0.6.2
 */