package org.android.agoo.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IMessageService extends IInterface
{
  public abstract boolean ping()
    throws RemoteException;

  public abstract void probe()
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IMessageService
  {
    private static final String a = "org.android.agoo.service.IMessageService";
    static final int b = 1;
    static final int c = 2;

    public Stub()
    {
      attachInterface(this, "org.android.agoo.service.IMessageService");
    }

    public static IMessageService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("org.android.agoo.service.IMessageService");
      if ((localIInterface != null) && ((localIInterface instanceof IMessageService)))
        return (IMessageService)localIInterface;
      return new Proxy(paramIBinder);
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
        paramParcel2.writeString("org.android.agoo.service.IMessageService");
        return true;
      case 1:
        paramParcel1.enforceInterface("org.android.agoo.service.IMessageService");
        boolean bool = ping();
        paramParcel2.writeNoException();
        if (bool);
        for (paramInt1 = 1; ; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 2:
      }
      paramParcel1.enforceInterface("org.android.agoo.service.IMessageService");
      probe();
      paramParcel2.writeNoException();
      return true;
    }

    private static class Proxy
      implements IMessageService
    {
      private IBinder a;

      Proxy(IBinder paramIBinder)
      {
        this.a = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.a;
      }

      public String getInterfaceDescriptor()
      {
        return "org.android.agoo.service.IMessageService";
      }

      public boolean ping()
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("org.android.agoo.service.IMessageService");
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          if (i != 0)
            return bool;
          bool = false;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void probe()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("org.android.agoo.service.IMessageService");
          this.a.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.IMessageService
 * JD-Core Version:    0.6.2
 */