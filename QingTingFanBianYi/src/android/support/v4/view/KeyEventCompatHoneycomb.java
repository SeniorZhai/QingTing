package android.support.v4.view;

import android.view.KeyEvent;

class KeyEventCompatHoneycomb
{
  public static boolean metaStateHasModifiers(int paramInt1, int paramInt2)
  {
    return KeyEvent.metaStateHasModifiers(paramInt1, paramInt2);
  }

  public static boolean metaStateHasNoModifiers(int paramInt)
  {
    return KeyEvent.metaStateHasNoModifiers(paramInt);
  }

  public static int normalizeMetaState(int paramInt)
  {
    return KeyEvent.normalizeMetaState(paramInt);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     android.support.v4.view.KeyEventCompatHoneycomb
 * JD-Core Version:    0.6.2
 */