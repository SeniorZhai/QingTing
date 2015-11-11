package fm.qingting.utils;

public class NativeEnv
{
  static
  {
    System.loadLibrary("nativeenv");
  }

  public static native String getenv(String paramString);

  public static native boolean setenv(String paramString1, String paramString2, boolean paramBoolean);

  public static native boolean unsetenv(String paramString);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.NativeEnv
 * JD-Core Version:    0.6.2
 */