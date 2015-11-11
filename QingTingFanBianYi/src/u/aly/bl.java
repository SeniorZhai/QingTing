package u.aly;

import android.os.AsyncTask;

public class bl extends bo
{
  private static final String a = bl.class.getName();

  public bn.a a(bm parambm)
  {
    parambm = (bn)a(parambm, bn.class);
    if (parambm == null)
      return bn.a.b;
    return parambm.a;
  }

  public void a(bm parambm, a parama)
  {
    try
    {
      new b(parambm, parama).execute(new Integer[0]);
      return;
    }
    catch (Exception parambm)
    {
      do
        bj.b(a, "", parambm);
      while (parama == null);
      parama.a(bn.a.b);
    }
  }

  public static abstract interface a
  {
    public abstract void a();

    public abstract void a(bn.a parama);
  }

  private class b extends AsyncTask<Integer, Integer, bn.a>
  {
    private bm b;
    private bl.a c;

    public b(bm parama, bl.a arg3)
    {
      this.b = parama;
      Object localObject;
      this.c = localObject;
    }

    protected bn.a a(Integer[] paramArrayOfInteger)
    {
      return bl.this.a(this.b);
    }

    protected void a(bn.a parama)
    {
      if (this.c != null)
        this.c.a(parama);
    }

    protected void onPreExecute()
    {
      if (this.c != null)
        this.c.a();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.bl
 * JD-Core Version:    0.6.2
 */