package u.aly;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class l
{
  private final String a = "umeng_event_snapshot";
  private boolean b = false;
  private SharedPreferences c;
  private Map<String, ArrayList<aa>> d = new HashMap();

  public l(Context paramContext)
  {
    this.c = u.a(paramContext, "umeng_event_snapshot");
  }

  private void c(String paramString)
  {
    Object localObject = null;
    if (this.d.containsKey(paramString))
    {
      localObject = (ArrayList)this.d.get(paramString);
      while (((ArrayList)localObject).size() > 4)
        ((ArrayList)localObject).remove(0);
      localObject = s.a((Serializable)localObject);
    }
    this.c.edit().putString(paramString, (String)localObject).commit();
  }

  private boolean d(String paramString)
  {
    if (this.d.containsKey(paramString))
      return true;
    Object localObject = this.c.getString(paramString, null);
    if (localObject != null)
    {
      localObject = (ArrayList)s.a((String)localObject);
      if (localObject != null)
      {
        this.d.put(paramString, localObject);
        return true;
      }
    }
    return false;
  }

  public int a(String paramString)
  {
    if (this.d.containsKey(paramString))
      return ((ArrayList)this.d.get(paramString)).size();
    return 0;
  }

  public void a(String paramString, aa paramaa)
  {
    if (this.b)
      d(paramString);
    if (this.d.containsKey(paramString))
      ((ArrayList)this.d.get(paramString)).add(paramaa);
    while (true)
    {
      if (this.b)
        c(paramString);
      return;
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(paramaa);
      this.d.put(paramString, localArrayList);
    }
  }

  public void a(boolean paramBoolean)
  {
    this.b = paramBoolean;
  }

  public aa b(String paramString)
  {
    if (this.b)
      d(paramString);
    if (this.d.containsKey(paramString))
    {
      localObject = (ArrayList)this.d.get(paramString);
      if (((ArrayList)localObject).size() <= 0);
    }
    for (Object localObject = (aa)((ArrayList)localObject).remove(((ArrayList)localObject).size() - 1); ; localObject = null)
    {
      if (this.b)
        c(paramString);
      return localObject;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     u.aly.l
 * JD-Core Version:    0.6.2
 */