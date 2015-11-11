package com.alipay.android.app;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IAlixPay extends IInterface
{
  public abstract String Pay(String paramString)
    throws RemoteException;

  public abstract String prePay(String paramString)
    throws RemoteException;

  public abstract void registerCallback(IRemoteServiceCallback paramIRemoteServiceCallback)
    throws RemoteException;

  public abstract String test()
    throws RemoteException;

  public abstract void unregisterCallback(IRemoteServiceCallback paramIRemoteServiceCallback)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IAlixPay
  {
    private static final String DESCRIPTOR = "com.alipay.android.app.IAlixPay";
    static final int TRANSACTION_Pay = 1;
    static final int TRANSACTION_prePay = 5;
    static final int TRANSACTION_registerCallback = 3;
    static final int TRANSACTION_test = 2;
    static final int TRANSACTION_unregisterCallback = 4;

    public Stub()
    {
      attachInterface(this, "com.alipay.android.app.IAlixPay");
    }

    public static IAlixPay asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.alipay.android.app.IAlixPay");
      if ((localIInterface != null) && ((localIInterface instanceof IAlixPay)))
        return (IAlixPay)localIInterface;
      return new a(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.alipay.android.app.IAlixPay");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.alipay.android.app.IAlixPay");
        paramParcel1 = Pay(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 2:
        paramParcel1.enforceInterface("com.alipay.android.app.IAlixPay");
        paramParcel1 = test();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 3:
        paramParcel1.enforceInterface("com.alipay.android.app.IAlixPay");
        registerCallback(IRemoteServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("com.alipay.android.app.IAlixPay");
        unregisterCallback(IRemoteServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 5:
      }
      paramParcel1.enforceInterface("com.alipay.android.app.IAlixPay");
      paramParcel1 = prePay(paramParcel1.readString());
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }

    private static final class a
      implements IAlixPay
    {
      private IBinder a;

      a(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      private static String a()
      {
        return "com.alipay.android.app.IAlixPay";
      }

      public final String Pay(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IAlixPay");
          localParcel1.writeString(paramString);
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString;
      }

      public final IBinder asBinder()
      {
        return this.a;
      }

      public final String prePay(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IAlixPay");
          localParcel1.writeString(paramString);
          this.a.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString;
      }

      public final void registerCallback(IRemoteServiceCallback paramIRemoteServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IAlixPay");
          if (paramIRemoteServiceCallback != null);
          for (paramIRemoteServiceCallback = paramIRemoteServiceCallback.asBinder(); ; paramIRemoteServiceCallback = null)
          {
            localParcel1.writeStrongBinder(paramIRemoteServiceCallback);
            this.a.transact(3, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIRemoteServiceCallback;
      }

      public final String test()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IAlixPay");
          this.a.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public final void unregisterCallback(IRemoteServiceCallback paramIRemoteServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.alipay.android.app.IAlixPay");
          if (paramIRemoteServiceCallback != null);
          for (paramIRemoteServiceCallback = paramIRemoteServiceCallback.asBinder(); ; paramIRemoteServiceCallback = null)
          {
            localParcel1.writeStrongBinder(paramIRemoteServiceCallback);
            this.a.transact(4, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIRemoteServiceCallback;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alipay.android.app.IAlixPay
 * JD-Core Version:    0.6.2
 */