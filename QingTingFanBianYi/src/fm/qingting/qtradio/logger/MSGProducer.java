package fm.qingting.qtradio.logger;

public class MSGProducer extends Thread
  implements Runnable
{
  public static long count = 0L;

  public static long getCount()
  {
    return count;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.logger.MSGProducer
 * JD-Core Version:    0.6.2
 */