package com.tencent.b.b;

import android.content.Context;
import java.io.File;

public final class b
{
  private static Context a;

  public static final Context a()
  {
    if (a == null)
      return null;
    return a;
  }

  public static final void a(Context paramContext)
  {
    a = paramContext;
  }

  public static final String b()
  {
    return a().getPackageName();
  }

  public static final File c()
  {
    return a().getFilesDir();
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.b.b.b
 * JD-Core Version:    0.6.2
 */