package master.flame.danmaku.danmaku.parser;

public abstract interface IDataSource<T>
{
  public static final String SCHEME_FILE_TAG = "file";
  public static final String SCHEME_HTTPS_TAG = "https";
  public static final String SCHEME_HTTP_TAG = "http";

  public abstract T data();

  public abstract void release();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.IDataSource
 * JD-Core Version:    0.6.2
 */