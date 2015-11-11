package fm.qingting.qtradio.push.task;

import android.content.Context;
import fm.qingting.qtradio.stat.PlayRecord;
import java.util.Comparator;

public class TaskCollectResumableProgram extends TaskCollectPush
{
  private static final int RecentDays = 7;
  private Context _context;

  public TaskCollectResumableProgram(Context paramContext)
  {
    super("TaskCollectResumableProgram");
    this._context = paramContext;
  }

  public void begin()
  {
  }

  class TimeComparator
    implements Comparator<PlayRecord>
  {
    TimeComparator()
    {
    }

    public int compare(PlayRecord paramPlayRecord1, PlayRecord paramPlayRecord2)
    {
      if (paramPlayRecord1.time > paramPlayRecord2.time)
        return -1;
      if (paramPlayRecord1.time == paramPlayRecord2.time)
        return 0;
      return 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.task.TaskCollectResumableProgram
 * JD-Core Version:    0.6.2
 */