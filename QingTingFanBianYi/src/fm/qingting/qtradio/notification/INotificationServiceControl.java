package fm.qingting.qtradio.notification;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface INotificationServiceControl extends IInterface
{
  public abstract void activityHasQuit()
    throws RemoteException;

  public abstract void activityHasStart()
    throws RemoteException;

  public abstract void addGroup(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void clearNotificationMsg()
    throws RemoteException;

  public abstract void clearUnReadMsg(String paramString)
    throws RemoteException;

  public abstract void createGroup(String paramString1, String paramString2)
    throws RemoteException;

  public abstract void disableGroup(String paramString)
    throws RemoteException;

  public abstract void disableUser(String paramString)
    throws RemoteException;

  public abstract void enableGroup(String paramString)
    throws RemoteException;

  public abstract void exitGroup(String paramString1, String paramString2)
    throws RemoteException;

  public abstract int getUnReadMsg(String paramString)
    throws RemoteException;

  public abstract boolean hasDisabledGroup(String paramString)
    throws RemoteException;

  public abstract void loadLastMsg(String paramString)
    throws RemoteException;

  public abstract void loadMoreGroupMsg(String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void loadMoreUserMsg(String paramString, boolean paramBoolean)
    throws RemoteException;

  public abstract void logout()
    throws RemoteException;

  public abstract void sendGroupMsg(String paramString1, String paramString2, String paramString3)
    throws RemoteException;

  public abstract void sendUserMsg(String paramString1, String paramString2, String paramString3)
    throws RemoteException;

  public abstract void setUserId(String paramString)
    throws RemoteException;

  public abstract void start(String paramString)
    throws RemoteException;

  public static abstract class Stub extends Binder
    implements INotificationServiceControl
  {
    private static final String DESCRIPTOR = "fm.qingting.qtradio.notification.INotificationServiceControl";
    static final int TRANSACTION_activityHasQuit = 2;
    static final int TRANSACTION_activityHasStart = 1;
    static final int TRANSACTION_addGroup = 12;
    static final int TRANSACTION_clearNotificationMsg = 19;
    static final int TRANSACTION_clearUnReadMsg = 18;
    static final int TRANSACTION_createGroup = 14;
    static final int TRANSACTION_disableGroup = 6;
    static final int TRANSACTION_disableUser = 8;
    static final int TRANSACTION_enableGroup = 7;
    static final int TRANSACTION_exitGroup = 13;
    static final int TRANSACTION_getUnReadMsg = 17;
    static final int TRANSACTION_hasDisabledGroup = 9;
    static final int TRANSACTION_loadLastMsg = 20;
    static final int TRANSACTION_loadMoreGroupMsg = 16;
    static final int TRANSACTION_loadMoreUserMsg = 15;
    static final int TRANSACTION_logout = 5;
    static final int TRANSACTION_sendGroupMsg = 11;
    static final int TRANSACTION_sendUserMsg = 10;
    static final int TRANSACTION_setUserId = 4;
    static final int TRANSACTION_start = 3;

    public Stub()
    {
      attachInterface(this, "fm.qingting.qtradio.notification.INotificationServiceControl");
    }

    public static INotificationServiceControl asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null)
        return null;
      IInterface localIInterface = paramIBinder.queryLocalInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
      if ((localIInterface != null) && ((localIInterface instanceof INotificationServiceControl)))
        return (INotificationServiceControl)localIInterface;
      return new Proxy(paramIBinder);
    }

    public IBinder asBinder()
    {
      return this;
    }

    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool1 = false;
      boolean bool2 = false;
      int i = 0;
      String str;
      switch (paramInt1)
      {
      default:
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902:
        paramParcel2.writeString("fm.qingting.qtradio.notification.INotificationServiceControl");
        return true;
      case 1:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        activityHasStart();
        paramParcel2.writeNoException();
        return true;
      case 2:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        activityHasQuit();
        paramParcel2.writeNoException();
        return true;
      case 3:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        start(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 4:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        setUserId(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 5:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        logout();
        paramParcel2.writeNoException();
        return true;
      case 6:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        disableGroup(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 7:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        enableGroup(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 8:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        disableUser(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 9:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        bool1 = hasDisabledGroup(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramInt1 = i;
        if (bool1)
          paramInt1 = 1;
        paramParcel2.writeInt(paramInt1);
        return true;
      case 10:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        sendUserMsg(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 11:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        sendGroupMsg(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 12:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        addGroup(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 13:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        exitGroup(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 14:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        createGroup(paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 15:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0)
          bool1 = true;
        loadMoreUserMsg(str, bool1);
        paramParcel2.writeNoException();
        return true;
      case 16:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        str = paramParcel1.readString();
        bool1 = bool2;
        if (paramParcel1.readInt() != 0)
          bool1 = true;
        loadMoreGroupMsg(str, bool1);
        paramParcel2.writeNoException();
        return true;
      case 17:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        paramInt1 = getUnReadMsg(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeInt(paramInt1);
        return true;
      case 18:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        clearUnReadMsg(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 19:
        paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
        clearNotificationMsg();
        paramParcel2.writeNoException();
        return true;
      case 20:
      }
      paramParcel1.enforceInterface("fm.qingting.qtradio.notification.INotificationServiceControl");
      loadLastMsg(paramParcel1.readString());
      paramParcel2.writeNoException();
      return true;
    }

    private static class Proxy
      implements INotificationServiceControl
    {
      private IBinder mRemote;

      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }

      public void activityHasQuit()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void activityHasStart()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void addGroup(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(12, localParcel1, localParcel2, 0);
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

      public IBinder asBinder()
      {
        return this.mRemote;
      }

      public void clearNotificationMsg()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          this.mRemote.transact(19, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void clearUnReadMsg(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(18, localParcel1, localParcel2, 0);
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

      public void createGroup(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(14, localParcel1, localParcel2, 0);
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

      public void disableGroup(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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

      public void disableUser(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
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

      public void enableGroup(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
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

      public void exitGroup(String paramString1, String paramString2)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          this.mRemote.transact(13, localParcel1, localParcel2, 0);
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
        return "fm.qingting.qtradio.notification.INotificationServiceControl";
      }

      public int getUnReadMsg(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
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
        throw paramString;
      }

      public boolean hasDisabledGroup(String paramString)
        throws RemoteException
      {
        boolean bool = false;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
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
        throw paramString;
      }

      public void loadLastMsg(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(20, localParcel1, localParcel2, 0);
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

      public void loadMoreGroupMsg(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(16, localParcel1, localParcel2, 0);
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

      public void loadMoreUserMsg(String paramString, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          if (paramBoolean)
            i = 1;
          localParcel1.writeInt(i);
          this.mRemote.transact(15, localParcel1, localParcel2, 0);
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

      public void logout()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          this.mRemote.transact(5, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }

      public void sendGroupMsg(String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeString(paramString3);
          this.mRemote.transact(11, localParcel1, localParcel2, 0);
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

      public void sendUserMsg(String paramString1, String paramString2, String paramString3)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeString(paramString3);
          this.mRemote.transact(10, localParcel1, localParcel2, 0);
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

      public void setUserId(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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

      public void start(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("fm.qingting.qtradio.notification.INotificationServiceControl");
          localParcel1.writeString(paramString);
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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
 * Qualified Name:     fm.qingting.qtradio.notification.INotificationServiceControl
 * JD-Core Version:    0.6.2
 */