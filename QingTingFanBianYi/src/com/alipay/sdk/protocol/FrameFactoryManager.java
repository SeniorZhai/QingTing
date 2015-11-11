package com.alipay.sdk.protocol;

import com.alipay.sdk.exception.AppErrorException;
import com.alipay.sdk.exception.FailOperatingException;
import com.alipay.sdk.exception.NetErrorException;

public class FrameFactoryManager
{
  public static FrameData a(FrameData paramFrameData)
    throws NetErrorException, FailOperatingException, AppErrorException
  {
    if (paramFrameData == null)
      throw new AppErrorException(FrameFactoryManager.class, "frame data is null");
    MiniFrameFactory localMiniFrameFactory = new MiniFrameFactory();
    MiniWindowFrame localMiniWindowFrame = MiniFrameFactory.a(paramFrameData);
    if (localMiniWindowFrame == null);
    while (true)
    {
      localMiniFrameFactory.b(paramFrameData);
      return paramFrameData;
      paramFrameData = localMiniWindowFrame;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.sdk.protocol.FrameFactoryManager
 * JD-Core Version:    0.6.2
 */