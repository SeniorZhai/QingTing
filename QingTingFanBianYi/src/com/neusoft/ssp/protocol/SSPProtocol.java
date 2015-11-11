package com.neusoft.ssp.protocol;

import android.util.Log;
import java.util.ArrayList;

public class SSPProtocol
{
  static
  {
    try
    {
      System.loadLibrary("sspLib");
      return;
    }
    catch (Exception localException)
    {
      Log.e("SSP", "Load static libary error");
    }
  }

  private String a(Handle paramHandle)
  {
    if ((paramHandle != null) && (paramHandle.address != 0))
      return native_GetString(paramHandle.address);
    return null;
  }

  private static String[] a(String paramString)
  {
    int i = 1;
    if ((paramString == null) || (paramString.length() == 0) || (paramString.length() == 2) || (paramString.length() == 3))
      return null;
    if (paramString.length() == 1)
    {
      if ((paramString.equalsIgnoreCase("i")) || (paramString.equalsIgnoreCase("y")) || (paramString.equalsIgnoreCase("d")) || (paramString.equalsIgnoreCase("s")))
        return new String[] { paramString };
      return null;
    }
    if ((!paramString.startsWith("(")) || (!paramString.endsWith(")")))
      return null;
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (i >= paramString.length() - 1)
        return (String[])localArrayList.toArray(new String[0]);
      String str = paramString.substring(i, i + 1);
      if ((!str.equalsIgnoreCase("i")) && (!str.equalsIgnoreCase("y")) && (!str.equalsIgnoreCase("d")) && (!str.equalsIgnoreCase("s")) && (!str.equalsIgnoreCase("v")))
        break;
      localArrayList.add(str);
      i += 1;
    }
    return null;
  }

  public static SSPProtocol getInstance()
  {
    return a.a();
  }

  private static native int native_AddressSspDataNewArrayType();

  private static native int native_AddressSspDataNewBaseType(String paramString, Object[] paramArrayOfObject);

  private static native String native_GetString(int paramInt);

  private static native int native_SspDataAddArrayType(int paramInt1, int paramInt2);

  private static native ArrayList native_SspDataGetBaseType(int paramInt, String paramString, Object[] paramArrayOfObject);

  private static native ArrayList native_SspDataIterLoop(int paramInt1, int paramInt2, String paramString, Object[] paramArrayOfObject);

  private static native void native_SspDataRelease(int paramInt);

  private static native int native_Trans(String paramString);

  public boolean dataAddArrayType(Handle paramHandle1, Handle paramHandle2)
  {
    if ((paramHandle1 == null) || (paramHandle1.address == 0) || (paramHandle2 == null) || (paramHandle2.address == 0));
    while (native_SspDataAddArrayType(paramHandle1.address, paramHandle2.address) == 0)
      return false;
    return true;
  }

  public Object[] sspDataGetBaseType(Handle paramHandle, String paramString)
  {
    if ((paramHandle == null) || (paramHandle.address == 0));
    do
    {
      String[] arrayOfString;
      do
      {
        return null;
        arrayOfString = a(paramString);
      }
      while (arrayOfString == null);
      paramHandle = native_SspDataGetBaseType(paramHandle.address, paramString, arrayOfString);
    }
    while (paramHandle == null);
    return paramHandle.toArray();
  }

  public Object[] sspDataIterLoop(Handle paramHandle, int paramInt, String paramString)
  {
    if ((paramHandle == null) || (paramHandle.address == 0));
    do
    {
      String[] arrayOfString;
      do
      {
        return null;
        arrayOfString = a(paramString);
      }
      while (arrayOfString == null);
      paramHandle = native_SspDataIterLoop(paramHandle.address, paramInt, paramString, arrayOfString);
    }
    while (paramHandle == null);
    return paramHandle.toArray();
  }

  public Handle sspDataNewArrayType()
  {
    int i = native_AddressSspDataNewArrayType();
    if (i == 0)
      return null;
    return new Handle(i);
  }

  public Handle sspDataNewBaseType(String paramString, Object[] paramArrayOfObject)
  {
    if ((paramString == null) || (paramArrayOfObject == null))
      return null;
    int j = paramArrayOfObject.length;
    Object[] arrayOfObject = new Object[j << 1];
    int i = 0;
    if (i >= j)
    {
      Log.v("xy", "str:" + paramString);
      i = native_AddressSspDataNewBaseType(paramString, arrayOfObject);
      Log.v("xy", "address......" + i);
      if (i == 0)
        return null;
    }
    else
    {
      Object localObject = paramArrayOfObject[i];
      if (paramArrayOfObject[i] == null)
        localObject = "";
      arrayOfObject[(i << 1)] = localObject;
      if ((localObject instanceof Integer))
        arrayOfObject[((i << 1) + 1)] = "i";
      while (true)
      {
        i += 1;
        break;
        if ((localObject instanceof Byte))
        {
          arrayOfObject[((i << 1) + 1)] = "y";
        }
        else if ((localObject instanceof Double))
        {
          arrayOfObject[((i << 1) + 1)] = "d";
        }
        else if ((localObject instanceof String))
        {
          arrayOfObject[((i << 1) + 1)] = "s";
        }
        else if ((localObject instanceof Handle))
        {
          arrayOfObject[((i << 1) + 1)] = "v";
          arrayOfObject[(i << 1)] = Integer.valueOf(((Handle)localObject).address);
        }
      }
    }
    return new Handle(i);
  }

  public void sspDataRelease(Handle paramHandle)
  {
    if ((paramHandle != null) && (paramHandle.address != 0))
    {
      native_SspDataRelease(paramHandle.address);
      paramHandle.address = 0;
    }
  }

  public Handle sspTrans(String paramString)
  {
    Handle localHandle = new Handle(0);
    if (paramString != null)
      localHandle.address = native_Trans(paramString);
    return localHandle;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.protocol.SSPProtocol
 * JD-Core Version:    0.6.2
 */