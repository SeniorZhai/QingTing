package master.flame.danmaku.danmaku.model;

public abstract interface IDrawingCache<T>
{
  public abstract void build(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean);

  public abstract void decreaseReference();

  public abstract void destroy();

  public abstract void erase();

  public abstract T get();

  public abstract boolean hasReferences();

  public abstract int height();

  public abstract void increaseReference();

  public abstract int size();

  public abstract int width();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.IDrawingCache
 * JD-Core Version:    0.6.2
 */