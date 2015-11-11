package master.flame.danmaku.danmaku.model;

public abstract interface IDanmakus
{
  public abstract boolean addItem(BaseDanmaku paramBaseDanmaku);

  public abstract void clear();

  public abstract boolean contains(BaseDanmaku paramBaseDanmaku);

  public abstract BaseDanmaku first();

  public abstract boolean isEmpty();

  public abstract IDanmakuIterator iterator();

  public abstract BaseDanmaku last();

  public abstract boolean removeItem(BaseDanmaku paramBaseDanmaku);

  public abstract void setSubItemsDuplicateMergingEnabled(boolean paramBoolean);

  public abstract int size();

  public abstract IDanmakus sub(long paramLong1, long paramLong2);

  public abstract IDanmakus subnew(long paramLong1, long paramLong2);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.model.IDanmakus
 * JD-Core Version:    0.6.2
 */