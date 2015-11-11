package com.mediatek.FMRadio;

public class FMRadioNative
{
  private static boolean isLoadSuccess = false;

  public static native short activeAF();

  public static native short activeTA();

  public static native short[] autoscan();

  public static native boolean closedev();

  public static native short deactiveTA();

  public static native short[] getAFList();

  public static native int[] getHardwareVersion();

  public static native byte[] getLRText();

  public static native short getPI();

  public static native byte[] getPS();

  public static native byte getPTY();

  public static native int getchipid();

  public static boolean isAvailable()
  {
    return isLoadSuccess;
  }

  public static native int isFMPoweredUp();

  public static native int isRDSsupport();

  public static void load()
  {
    try
    {
      System.loadLibrary("fmjni");
      isLoadSuccess = true;
      return;
    }
    catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
    {
      localUnsatisfiedLinkError.printStackTrace();
    }
  }

  public static native boolean opendev();

  public static native boolean powerdown(int paramInt);

  public static native boolean powerup(float paramFloat);

  public static native int rdsset(boolean paramBoolean);

  public static native short readCapArray();

  public static native short readRdsBler();

  public static native int readRssi();

  static native short readrds();

  public static native short[] scannew(int paramInt1, int paramInt2, int paramInt3);

  public static native float seek(float paramFloat, boolean paramBoolean);

  public static native int seeknew(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6);

  public static native boolean setFMViaBTController(boolean paramBoolean);

  public static native boolean setStereoMono(boolean paramBoolean);

  public static native int setmute(boolean paramBoolean);

  public static native boolean stereoMono();

  public static native boolean stopscan();

  public static native int switchAntenna(int paramInt);

  public static native boolean tune(float paramFloat);

  public static native boolean tunenew(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.mediatek.FMRadio.FMRadioNative
 * JD-Core Version:    0.6.2
 */