package android.media;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFMSystemService extends IInterface
{
  public abstract boolean Acquire(String paramString)
    throws RemoteException;

  public abstract int SearchNextStation()
    throws RemoteException;

  public abstract int SearchPrevStation()
    throws RemoteException;

  public abstract double getFrequency()
    throws RemoteException;

  public abstract int getVolum()
    throws RemoteException;

  public abstract boolean isFmActive()
    throws RemoteException;

  public abstract void radioDisable()
    throws RemoteException;

  public abstract void radioEnable()
    throws RemoteException;

  public abstract boolean release()
    throws RemoteException;

  public abstract void setStation(double paramDouble)
    throws RemoteException;

  public abstract void setVolum(int paramInt)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFMSystemService
  {
    private static final String DESCRIPTOR = "android.media.IFMSystemService";
    static final int TRANSACTION_Acquire = 1;
    static final int TRANSACTION_SearchNextStation = 2;
    static final int TRANSACTION_SearchPrevStation = 3;
    static final int TRANSACTION_getFrequency = 4;
    static final int TRANSACTION_getVolum = 5;
    static final int TRANSACTION_isFmActive = 6;
    static final int TRANSACTION_radioDisable = 8;
    static final int TRANSACTION_radioEnable = 7;
    static final int TRANSACTION_release = 9;
    static final int TRANSACTION_setStation = 10;
    static final int TRANSACTION_setVolum = 11;

    public Stub()
    {
      attachInterface(this, "android.media.IFMSystemService");
    }

    public static IFMSystemService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("android.media.IFMSystemService");
      if ((localIInterface != null) && ((localIInterface instanceof IFMSystemService)))
        return (IFMSystemService)localIInterface;
      return new Proxy(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      int j = 0;
      int k = 0;
      int i = 0;
      boolean bool;
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("android.media.IFMSystemService");
        return true;
      case 1:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        bool = Acquire(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 2:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        paramInt1 = SearchNextStation();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        paramInt1 = SearchPrevStation();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 4:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        double d = getFrequency();
        paramParcel2.writeNoException();
        paramParcel2.writeDouble(d);
        return true;
      case 5:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        paramInt1 = getVolum();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 6:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        bool = isFmActive();
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 7:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        radioEnable();
        paramParcel2.writeNoException();
        return true;
      case 8:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        radioDisable();
        paramParcel2.writeNoException();
        return true;
      case 9:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        bool = release();
        paramParcel2.writeNoException();
        paramInt1 = k;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 10:
        paramParcel1.enforceInterface("android.media.IFMSystemService");
        setStation(paramParcel1.readDouble());
        paramParcel2.writeNoException();
        return true;
      case 11:
      }
      paramParcel1.enforceInterface("android.media.IFMSystemService");
      setVolum(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }

    private static class Proxy
      implements IFMSystemService
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public boolean Acquire(String paramString)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          localParcel1.writeString(paramString);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
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

      public int SearchNextStation()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public int SearchPrevStation()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public double getFrequency()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          double d = localParcel2.readDouble();
          return d;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public String getInterfaceDescriptor()
      {
        return "android.media.IFMSystemService";
      }

      public int getVolum()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          int i = localParcel2.readInt();
          return i;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean isFmActive()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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

      public void radioDisable()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void radioEnable()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean release()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
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

      public void setStation(double paramDouble)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          localParcel1.writeDouble(paramDouble);
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void setVolum(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("android.media.IFMSystemService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
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
 * Qualified Name:     android.media.IFMSystemService
 * JD-Core Version:    0.6.2
 */