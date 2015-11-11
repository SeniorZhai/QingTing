package com.neusoft.ssp.aidl;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

public abstract class MyAIDL$Stub extends Binder
  implements MyAIDL
{
  public MyAIDL$Stub()
  {
    attachInterface(this, "com.zxl.aidl.aidl.MyAIDL");
  }

  public static MyAIDL asInterface(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.zxl.aidl.aidl.MyAIDL");
    if ((localIInterface != null) && ((localIInterface instanceof MyAIDL)))
      return (MyAIDL)localIInterface;
    return new b(paramIBinder);
  }

  public IBinder asBinder()
  {
    return this;
  }

  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
  {
    switch (paramInt1)
    {
    default:
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902:
      paramParcel2.writeString("com.zxl.aidl.aidl.MyAIDL");
      return true;
    case 1:
      paramParcel1.enforceInterface("com.zxl.aidl.aidl.MyAIDL");
      register(paramParcel1.readString(), ITaskCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    case 2:
    }
    paramParcel1.enforceInterface("com.zxl.aidl.aidl.MyAIDL");
    send(paramParcel1.readString());
    paramParcel2.writeNoException();
    return true;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.neusoft.ssp.aidl.MyAIDL.Stub
 * JD-Core Version:    0.6.2
 */