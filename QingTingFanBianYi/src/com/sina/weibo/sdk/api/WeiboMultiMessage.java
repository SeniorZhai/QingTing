package com.sina.weibo.sdk.api;

import android.os.Bundle;
import com.sina.weibo.sdk.utils.LogUtil;

public final class WeiboMultiMessage
{
  private static final String TAG = "WeiboMultiMessage";
  public ImageObject imageObject;
  public BaseMediaObject mediaObject;
  public TextObject textObject;

  public WeiboMultiMessage()
  {
  }

  public WeiboMultiMessage(Bundle paramBundle)
  {
    toBundle(paramBundle);
  }

  public boolean checkArgs()
  {
    if ((this.textObject != null) && (!this.textObject.checkArgs()))
    {
      LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject is invalid");
      return false;
    }
    if ((this.imageObject != null) && (!this.imageObject.checkArgs()))
    {
      LogUtil.e("WeiboMultiMessage", "checkArgs fail, imageObject is invalid");
      return false;
    }
    if ((this.mediaObject != null) && (!this.mediaObject.checkArgs()))
    {
      LogUtil.e("WeiboMultiMessage", "checkArgs fail, mediaObject is invalid");
      return false;
    }
    if ((this.textObject == null) && (this.imageObject == null) && (this.mediaObject == null))
    {
      LogUtil.e("WeiboMultiMessage", "checkArgs fail, textObject and imageObject and mediaObject is null");
      return false;
    }
    return true;
  }

  public Bundle toBundle(Bundle paramBundle)
  {
    if (this.textObject != null)
    {
      paramBundle.putParcelable("_weibo_message_text", this.textObject);
      paramBundle.putString("_weibo_message_text_extra", this.textObject.toExtraMediaString());
    }
    if (this.imageObject != null)
    {
      paramBundle.putParcelable("_weibo_message_image", this.imageObject);
      paramBundle.putString("_weibo_message_image_extra", this.imageObject.toExtraMediaString());
    }
    if (this.mediaObject != null)
    {
      paramBundle.putParcelable("_weibo_message_media", this.mediaObject);
      paramBundle.putString("_weibo_message_media_extra", this.mediaObject.toExtraMediaString());
    }
    return paramBundle;
  }

  public WeiboMultiMessage toObject(Bundle paramBundle)
  {
    this.textObject = ((TextObject)paramBundle.getParcelable("_weibo_message_text"));
    if (this.textObject != null)
      this.textObject.toExtraMediaObject(paramBundle.getString("_weibo_message_text_extra"));
    this.imageObject = ((ImageObject)paramBundle.getParcelable("_weibo_message_image"));
    if (this.imageObject != null)
      this.imageObject.toExtraMediaObject(paramBundle.getString("_weibo_message_image_extra"));
    this.mediaObject = ((BaseMediaObject)paramBundle.getParcelable("_weibo_message_media"));
    if (this.mediaObject != null)
      this.mediaObject.toExtraMediaObject(paramBundle.getString("_weibo_message_media_extra"));
    return this;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.WeiboMultiMessage
 * JD-Core Version:    0.6.2
 */