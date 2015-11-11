package com.umeng.message;

import android.content.Context;
import com.umeng.message.entity.UMessage;

public abstract interface UHandler
{
  public abstract void handleMessage(Context paramContext, UMessage paramUMessage);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.message.UHandler
 * JD-Core Version:    0.6.2
 */