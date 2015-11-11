package com.alipay.android.app;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public abstract interface IRemoteServiceCallback extends IInterface
{
  public abstract boolean isHideLoadingScreen()
    throws RemoteException;

  public abstract void payEnd(boolean paramBoolean, String paramString)
    throws RemoteException;

  public abstract void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IRemoteServiceCallback
  {
    private static final String DESCRIPTOR = "com.alipay.android.app.IRemoteServiceCallback";
    static final int TRANSACTION_isHideLoadingScreen = 3;
    static final int TRANSACTION_payEnd = 2;
    static final int TRANSACTION_startActivity = 1;

    public Stub()
    {
      attachInterface(this, "com.alipay.android.app.IRemoteServiceCallback");
    }

    public static IRemoteServiceCallback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.alipay.android.app.IRemoteServiceCallback");
      if ((localIInterface != null) && ((localIInterface instanceof IRemoteServiceCallback)))
        return (IRemoteServiceCallback)localIInterface;
      return new a(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int i = 0;
      boolean bool = false;
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.alipay.android.app.IRemoteServiceCallback");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
        String str1 = paramParcel1.readString();
        String str2 = paramParcel1.readString();
        paramInt1 = paramParcel1.readInt();
        if (paramParcel1.readInt() != 0);
        for (paramParcel1 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1); ; paramParcel1 = null)
        {
          startActivity(str1, str2, paramInt1, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 2:
        paramParcel1.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
        if (paramParcel1.readInt() != 0)
          bool = true;
        payEnd(bool, paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 3:
      }
      paramParcel1.enforceInterface("com.alipay.android.app.IRemoteServiceCallback");
      bool = isHideLoadingScreen();
      paramParcel2.writeNoException();
      paramInt1 = i;
      if (bool)
        paramInt1 = 1;
      paramParcel2.writeInt(paramInt1);
      return true;
    }

    private static final class a
      implements IRemoteServiceCallback
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      private static String a()
      {
        return "com.alipay.android.app.IRemoteServiceCallback";
      }

      public final IBinder asBinder()
      {
        return this.a;
      }

      public final boolean isHideLoadingScreen()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
          this.a.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public final void payEnd(boolean paramBoolean, String paramString)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          localParcel1.writeString(paramString);
          this.a.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString;
      }

      public final void startActivity(String paramString1, String paramString2, int paramInt, Bundle paramBundle)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IRemoteServiceCallback");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeInt(paramInt);
          if (paramBundle != null)
          {
            localParcel1.writeInt(1);
            paramBundle.writeToParcel(localParcel1, 0);
          }
          while (true)
          {
            this.a.transact(1, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
            localParcel1.writeInt(0);
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString1;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.android.app.IRemoteServiceCallback
 * JD-Core Version:    0.6.2
 */