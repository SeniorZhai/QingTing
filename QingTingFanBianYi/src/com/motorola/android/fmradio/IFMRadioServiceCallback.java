package com.motorola.android.fmradio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFMRadioServiceCallback extends IInterface
{
  public abstract void onCommandComplete(int paramInt1, int paramInt2, String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFMRadioServiceCallback
  {
    private static final String DESCRIPTOR = "com.motorola.android.fmradio.IFMRadioServiceCallback";
    static final int TRANSACTION_onCommandComplete = 1;

    public Stub()
    {
      attachInterface(this, "com.motorola.android.fmradio.IFMRadioServiceCallback");
    }

    public static IFMRadioServiceCallback asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.motorola.android.fmradio.IFMRadioServiceCallback");
      if ((localIInterface != null) && ((localIInterface instanceof IFMRadioServiceCallback)))
        return (IFMRadioServiceCallback)localIInterface;
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
        paramParcel2.writeString("com.motorola.android.fmradio.IFMRadioServiceCallback");
        return true;
      case 1:
      }
      paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioServiceCallback");
      onCommandComplete(paramParcel1.readInt(), paramParcel1.readInt(), paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }

    private static class Proxy
      implements IFMRadioServiceCallback
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public String getInterfaceDescriptor()
      {
        return "com.motorola.android.fmradio.IFMRadioServiceCallback";
      }

      public void onCommandComplete(int paramInt1, int paramInt2, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioServiceCallback");
          localParcel1.writeInt(paramInt1);
          localParcel1.writeInt(paramInt2);
          localParcel1.writeString(paramString);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.motorola.android.fmradio.IFMRadioServiceCallback
 * JD-Core Version:    0.6.2
 */