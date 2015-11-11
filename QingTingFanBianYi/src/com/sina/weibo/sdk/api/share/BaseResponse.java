package com.sina.weibo.sdk.api.share;

import android.content.Context;
import android.os.Bundle;

public abstract class BaseResponse extends Base
{
  public int errCode;
  public String errMsg;
  public String reqPackageName;

  abstract boolean check(Context paramContext, VersionCheckHandler paramVersionCheckHandler);

  public void fromBundle(Bundle paramBundle)
  {
    this.errCode = paramBundle.getInt("_weibo_resp_errcode");
    this.errMsg = paramBundle.getString("_weibo_resp_errstr");
    this.transaction = paramBundle.getString("_weibo_transaction");
    this.reqPackageName = paramBundle.getString("_weibo_appPackage");
  }

  public void toBundle(Bundle paramBundle)
  {
    paramBundle.putInt("_weibo_command_type", getType());
    paramBundle.putInt("_weibo_resp_errcode", this.errCode);
    paramBundle.putString("_weibo_resp_errstr", this.errMsg);
    paramBundle.putString("_weibo_transaction", this.transaction);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.api.share.BaseResponse
 * JD-Core Version:    0.6.2
 */