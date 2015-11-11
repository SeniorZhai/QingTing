package org.android.agoo.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IElectionService extends IInterface
{
  public abstract void election(String paramString1, long paramLong, String paramString2)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IElectionService
  {
    private static final String a = "org.android.agoo.service.IElectionService";
    static final int b = 1;

    public Stub()
    {
      attachInterface(this, "org.android.agoo.service.IElectionService");
    }

    public static IElectionService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("org.android.agoo.service.IElectionService");
      if ((localIInterface != null) && ((localIInterface instanceof IElectionService)))
        return (IElectionService)localIInterface;
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
        paramParcel2.writeString("org.android.agoo.service.IElectionService");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("org.android.agoo.service.IElectionService");
      election(paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }

    private static class Proxy
      implements IElectionService
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

      public void election(String paramString1, long paramLong, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("org.android.agoo.service.IElectionService");
          localParcel1.writeString(paramString1);
          localParcel1.writeLong(paramLong);
          localParcel1.writeString(paramString2);
          this.a.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramString1;
      }

      public String getInterfaceDescriptor()
      {
        return "org.android.agoo.service.IElectionService";
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.android.agoo.service.IElectionService
 * JD-Core Version:    0.6.2
 */