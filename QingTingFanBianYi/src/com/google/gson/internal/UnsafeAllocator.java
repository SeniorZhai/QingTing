package com.google.gson.internal;

import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public abstract class UnsafeAllocator
{
  public static UnsafeAllocator create()
  {
    try
    {
      Object localObject1 = Class.forName("sun.misc.Unsafe");
      final Object localObject4 = ((Class)localObject1).getDeclaredField("theUnsafe");
      ((Field)localObject4).setAccessible(true);
      localObject4 = ((Field)localObject4).get(null);
      localObject1 = new UnsafeAllocator()
      {
        public <T> T newInstance(Class<T> paramAnonymousClass)
          throws Exception
        {
          return this.val$allocateInstance.invoke(localObject4, new Object[] { paramAnonymousClass });
        }
      };
      return localObject1;
    }
    catch (Exception localException1)
    {
      try
      {
        Object localObject2 = ObjectInputStream.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Class.class });
        ((Method)localObject2).setAccessible(true);
        localObject2 = new UnsafeAllocator()
        {
          public <T> T newInstance(Class<T> paramAnonymousClass)
            throws Exception
          {
            return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Object.class });
          }
        };
        return localObject2;
      }
      catch (Exception localException2)
      {
        try
        {
          Object localObject3 = ObjectStreamClass.class.getDeclaredMethod("getConstructorId", new Class[] { Class.class });
          ((Method)localObject3).setAccessible(true);
          final int i = ((Integer)((Method)localObject3).invoke(null, new Object[] { Object.class })).intValue();
          localObject3 = ObjectStreamClass.class.getDeclaredMethod("newInstance", new Class[] { Class.class, Integer.TYPE });
          ((Method)localObject3).setAccessible(true);
          localObject3 = new UnsafeAllocator()
          {
            public <T> T newInstance(Class<T> paramAnonymousClass)
              throws Exception
            {
              return this.val$newInstance.invoke(null, new Object[] { paramAnonymousClass, Integer.valueOf(i) });
            }
          };
          return localObject3;
        }
        catch (Exception localException3)
        {
        }
      }
    }
    return new UnsafeAllocator()
    {
      public <T> T newInstance(Class<T> paramAnonymousClass)
      {
        throw new UnsupportedOperationException("Cannot allocate " + paramAnonymousClass);
      }
    };
  }

  public abstract <T> T newInstance(Class<T> paramClass)
    throws Exception;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.UnsafeAllocator
 * JD-Core Version:    0.6.2
 */