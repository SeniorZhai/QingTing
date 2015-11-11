package fm.qingting.qtradio.manager;

import android.util.SparseIntArray;

public class IconManage
{
  private static SparseIntArray sResourcesCache = new SparseIntArray();

  // ERROR //
  public static int getNormalRes(int paramInt)
  {
    // Byte code:
    //   0: getstatic 15	fm/qingting/qtradio/manager/IconManage:sResourcesCache	Landroid/util/SparseIntArray;
    //   3: iload_0
    //   4: invokevirtual 30	android/util/SparseIntArray:get	(I)I
    //   7: istore_2
    //   8: iload_2
    //   9: ifeq +5 -> 14
    //   12: iload_2
    //   13: ireturn
    //   14: ldc 32
    //   16: new 34	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 35	java/lang/StringBuilder:<init>	()V
    //   23: ldc 37
    //   25: invokevirtual 41	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: iload_0
    //   29: invokevirtual 44	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   32: invokevirtual 48	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   35: invokevirtual 54	java/lang/Class:getDeclaredField	(Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   38: aconst_null
    //   39: invokevirtual 60	java/lang/reflect/Field:getInt	(Ljava/lang/Object;)I
    //   42: istore_2
    //   43: getstatic 15	fm/qingting/qtradio/manager/IconManage:sResourcesCache	Landroid/util/SparseIntArray;
    //   46: iload_0
    //   47: iload_2
    //   48: invokevirtual 64	android/util/SparseIntArray:put	(II)V
    //   51: iload_2
    //   52: ireturn
    //   53: astore_1
    //   54: aload_1
    //   55: invokevirtual 67	java/lang/SecurityException:printStackTrace	()V
    //   58: iload_2
    //   59: ireturn
    //   60: astore_1
    //   61: ldc 68
    //   63: istore_2
    //   64: aload_1
    //   65: invokevirtual 69	java/lang/NoSuchFieldException:printStackTrace	()V
    //   68: iload_2
    //   69: ireturn
    //   70: astore_1
    //   71: ldc 68
    //   73: istore_2
    //   74: aload_1
    //   75: invokevirtual 70	java/lang/IllegalArgumentException:printStackTrace	()V
    //   78: iload_2
    //   79: ireturn
    //   80: astore_1
    //   81: ldc 68
    //   83: istore_2
    //   84: aload_1
    //   85: invokevirtual 71	java/lang/IllegalAccessException:printStackTrace	()V
    //   88: iload_2
    //   89: ireturn
    //   90: astore_1
    //   91: goto -7 -> 84
    //   94: astore_1
    //   95: goto -21 -> 74
    //   98: astore_1
    //   99: goto -35 -> 64
    //   102: astore_1
    //   103: ldc 68
    //   105: istore_2
    //   106: goto -52 -> 54
    //
    // Exception table:
    //   from	to	target	type
    //   43	51	53	java/lang/SecurityException
    //   14	43	60	java/lang/NoSuchFieldException
    //   14	43	70	java/lang/IllegalArgumentException
    //   14	43	80	java/lang/IllegalAccessException
    //   43	51	90	java/lang/IllegalAccessException
    //   43	51	94	java/lang/IllegalArgumentException
    //   43	51	98	java/lang/NoSuchFieldException
    //   14	43	102	java/lang/SecurityException
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.manager.IconManage
 * JD-Core Version:    0.6.2
 */