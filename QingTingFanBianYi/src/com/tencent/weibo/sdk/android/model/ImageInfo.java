package com.tencent.weibo.sdk.android.model;

import android.graphics.drawable.Drawable;

public class ImageInfo extends BaseVO
{
  private static final long serialVersionUID = 2647179822312867756L;
  private Drawable drawable;
  private String imageName;
  private String imagePath;
  private String playPath;

  public Drawable getDrawable()
  {
    return this.drawable;
  }

  public String getImageName()
  {
    return this.imageName;
  }

  public String getImagePath()
  {
    return this.imagePath;
  }

  public String getPlayPath()
  {
    return this.playPath;
  }

  public void setDrawable(Drawable paramDrawable)
  {
    this.drawable = paramDrawable;
  }

  public void setImageName(String paramString)
  {
    this.imageName = paramString;
  }

  public void setImagePath(String paramString)
  {
    this.imagePath = paramString;
  }

  public void setPlayPath(String paramString)
  {
    this.playPath = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.model.ImageInfo
 * JD-Core Version:    0.6.2
 */