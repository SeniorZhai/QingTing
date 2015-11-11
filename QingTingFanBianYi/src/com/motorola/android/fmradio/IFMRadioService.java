package com.motorola.android.fmradio;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IFMRadioService extends IInterface
{
  public abstract boolean getAudioMode()
    throws RemoteException;

  public abstract boolean getAudioType()
    throws RemoteException;

  public abstract int getBand()
    throws RemoteException;

  public abstract boolean getCurrentFreq()
    throws RemoteException;

  public abstract int getMaxFrequence()
    throws RemoteException;

  public abstract int getMinFrequence()
    throws RemoteException;

  public abstract String getRDSStationName()
    throws RemoteException;

  public abstract boolean getRSSI()
    throws RemoteException;

  public abstract int getRdsPI()
    throws RemoteException;

  public abstract String getRdsPS()
    throws RemoteException;

  public abstract int getRdsPTY()
    throws RemoteException;

  public abstract String getRdsRT()
    throws RemoteException;

  public abstract String getRdsRTPLUS()
    throws RemoteException;

  public abstract int getStepUnit()
    throws RemoteException;

  public abstract boolean getVolume()
    throws RemoteException;

  public abstract boolean isMute()
    throws RemoteException;

  public abstract boolean isRdsEnable()
    throws RemoteException;

  public abstract void registerCallback(IFMRadioServiceCallback paramIFMRadioServiceCallback)
    throws RemoteException;

  public abstract boolean scan()
    throws RemoteException;

  public abstract boolean seek(int paramInt)
    throws RemoteException;

  public abstract boolean setAudioMode(int paramInt)
    throws RemoteException;

  public abstract boolean setBand(int paramInt)
    throws RemoteException;

  public abstract boolean setMute(int paramInt)
    throws RemoteException;

  public abstract boolean setRSSI(int paramInt)
    throws RemoteException;

  public abstract boolean setRdsEnable(boolean paramBoolean, int paramInt)
    throws RemoteException;

  public abstract boolean setVolume(int paramInt)
    throws RemoteException;

  public abstract boolean stopScan()
    throws RemoteException;

  public abstract boolean stopSeek()
    throws RemoteException;

  public abstract boolean tune(int paramInt)
    throws RemoteException;

  public abstract void unregisterCallback(IFMRadioServiceCallback paramIFMRadioServiceCallback)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements IFMRadioService
  {
    private static final String DESCRIPTOR = "com.motorola.android.fmradio.IFMRadioService";
    static final int TRANSACTION_getAudioMode = 4;
    static final int TRANSACTION_getAudioType = 22;
    static final int TRANSACTION_getBand = 13;
    static final int TRANSACTION_getCurrentFreq = 2;
    static final int TRANSACTION_getMaxFrequence = 16;
    static final int TRANSACTION_getMinFrequence = 15;
    static final int TRANSACTION_getRDSStationName = 30;
    static final int TRANSACTION_getRSSI = 23;
    static final int TRANSACTION_getRdsPI = 27;
    static final int TRANSACTION_getRdsPS = 24;
    static final int TRANSACTION_getRdsPTY = 28;
    static final int TRANSACTION_getRdsRT = 25;
    static final int TRANSACTION_getRdsRTPLUS = 26;
    static final int TRANSACTION_getStepUnit = 17;
    static final int TRANSACTION_getVolume = 12;
    static final int TRANSACTION_isMute = 6;
    static final int TRANSACTION_isRdsEnable = 21;
    static final int TRANSACTION_registerCallback = 18;
    static final int TRANSACTION_scan = 8;
    static final int TRANSACTION_seek = 7;
    static final int TRANSACTION_setAudioMode = 3;
    static final int TRANSACTION_setBand = 14;
    static final int TRANSACTION_setMute = 5;
    static final int TRANSACTION_setRSSI = 29;
    static final int TRANSACTION_setRdsEnable = 20;
    static final int TRANSACTION_setVolume = 11;
    static final int TRANSACTION_stopScan = 10;
    static final int TRANSACTION_stopSeek = 9;
    static final int TRANSACTION_tune = 1;
    static final int TRANSACTION_unregisterCallback = 19;

    public Stub()
    {
      attachInterface(this, "com.motorola.android.fmradio.IFMRadioService");
    }

    public static IFMRadioService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.motorola.android.fmradio.IFMRadioService");
      if ((localIInterface != null) && ((localIInterface instanceof IFMRadioService)))
        return (IFMRadioService)localIInterface;
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
      int m = 0;
      int n = 0;
      int i1 = 0;
      int i2 = 0;
      int i3 = 0;
      int i4 = 0;
      int i5 = 0;
      int i6 = 0;
      int i7 = 0;
      int i8 = 0;
      int i9 = 0;
      int i10 = 0;
      int i11 = 0;
      int i12 = 0;
      int i13 = 0;
      int i = 0;
      boolean bool;
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("com.motorola.android.fmradio.IFMRadioService");
        return true;
      case 1:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = tune(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 2:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = getCurrentFreq();
        paramParcel2.writeNoException();
        paramInt1 = j;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 3:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = setAudioMode(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = k;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 4:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = getAudioMode();
        paramParcel2.writeNoException();
        paramInt1 = m;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 5:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = setMute(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = n;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 6:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = isMute();
        paramParcel2.writeNoException();
        paramInt1 = i1;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 7:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = seek(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = i2;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 8:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = scan();
        paramParcel2.writeNoException();
        paramInt1 = i3;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 9:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = stopSeek();
        paramParcel2.writeNoException();
        paramInt1 = i4;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 10:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = stopScan();
        paramParcel2.writeNoException();
        paramInt1 = i5;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 11:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = setVolume(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = i6;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 12:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = getVolume();
        paramParcel2.writeNoException();
        paramInt1 = i7;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 13:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getBand();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 14:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = setBand(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = i8;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 15:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getMinFrequence();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 16:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getMaxFrequence();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 17:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getStepUnit();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 18:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        registerCallback(IFMRadioServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 19:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        unregisterCallback(IFMRadioServiceCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 20:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        if (paramParcel1.readInt() != 0);
        for (bool = true; ; bool = false)
        {
          bool = setRdsEnable(bool, paramParcel1.readInt());
          paramParcel2.writeNoException();
          paramInt1 = i9;
          if (bool)
            paramInt1 = 1;
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      case 21:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = isRdsEnable();
        paramParcel2.writeNoException();
        paramInt1 = i10;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 22:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = getAudioType();
        paramParcel2.writeNoException();
        paramInt1 = i11;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 23:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = getRSSI();
        paramParcel2.writeNoException();
        paramInt1 = i12;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 24:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramParcel1 = getRdsPS();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 25:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramParcel1 = getRdsRT();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 26:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramParcel1 = getRdsRTPLUS();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 27:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getRdsPI();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 28:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        paramInt1 = getRdsPTY();
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 29:
        paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
        bool = setRSSI(paramParcel1.readInt());
        paramParcel2.writeNoException();
        paramInt1 = i13;
        if (bool)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 30:
      }
      paramParcel1.enforceInterface("com.motorola.android.fmradio.IFMRadioService");
      paramParcel1 = getRDSStationName();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }

    private static class Proxy
      implements IFMRadioService
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

      public boolean getAudioMode()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public boolean getAudioType()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(22, localParcel1, localParcel2, 0);
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

      public int getBand()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
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

      public boolean getCurrentFreq()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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

      public String getInterfaceDescriptor()
      {
        return "com.motorola.android.fmradio.IFMRadioService";
      }

      public int getMaxFrequence()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
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

      public int getMinFrequence()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
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

      public String getRDSStationName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(30, localParcel1, localParcel2, 0);
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

      public boolean getRSSI()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(23, localParcel1, localParcel2, 0);
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

      public int getRdsPI()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(27, localParcel1, localParcel2, 0);
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

      public String getRdsPS()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(24, localParcel1, localParcel2, 0);
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

      public int getRdsPTY()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(28, localParcel1, localParcel2, 0);
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

      public String getRdsRT()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(25, localParcel1, localParcel2, 0);
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

      public String getRdsRTPLUS()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(26, localParcel1, localParcel2, 0);
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

      public int getStepUnit()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(17, localParcel1, localParcel2, 0);
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

      public boolean getVolume()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
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

      public boolean isMute()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
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

      public boolean isRdsEnable()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(21, localParcel1, localParcel2, 0);
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

      public void registerCallback(IFMRadioServiceCallback paramIFMRadioServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          if (paramIFMRadioServiceCallback != null);
          for (paramIFMRadioServiceCallback = paramIFMRadioServiceCallback.asBinder(); ; paramIFMRadioServiceCallback = null)
          {
            localParcel1.writeStrongBinder(paramIFMRadioServiceCallback);
            this.mRemote.transact(18, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIFMRadioServiceCallback;
      }

      public boolean scan()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
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

      public boolean seek(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setAudioMode(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setBand(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(14, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setMute(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setRSSI(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(29, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setRdsEnable(boolean paramBoolean, int paramInt)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          int i;
          if (paramBoolean)
          {
            i = 1;
            localParcel1.writeInt(i);
            localParcel1.writeInt(paramInt);
            this.mRemote.transact(20, localParcel1, localParcel2, 0);
            localParcel2.readException();
            paramInt = localParcel2.readInt();
            if (paramInt == 0)
              break label87;
          }
          label87: for (paramBoolean = bool; ; paramBoolean = false)
          {
            return paramBoolean;
            i = 0;
            break;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean setVolume(int paramInt)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            bool = true;
          return bool;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public boolean stopScan()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
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

      public boolean stopSeek()
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
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

      public boolean tune(int paramInt)
        throws RemoteException
      {
        boolean bool = true;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          localParcel1.writeInt(paramInt);
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramInt = localParcel2.readInt();
          if (paramInt != 0)
            return bool;
          bool = false;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void unregisterCallback(IFMRadioServiceCallback paramIFMRadioServiceCallback)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.motorola.android.fmradio.IFMRadioService");
          if (paramIFMRadioServiceCallback != null);
          for (paramIFMRadioServiceCallback = paramIFMRadioServiceCallback.asBinder(); ; paramIFMRadioServiceCallback = null)
          {
            localParcel1.writeStrongBinder(paramIFMRadioServiceCallback);
            this.mRemote.transact(19, localParcel1, localParcel2, 0);
            localParcel2.readException();
            return;
          }
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
        throw paramIFMRadioServiceCallback;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.motorola.android.fmradio.IFMRadioService
 * JD-Core Version:    0.6.2
 */