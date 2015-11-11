package fm.qingting.social;

import java.util.HashMap;

public class SocialEventListener
  implements ISocialEventListener
{
  protected Object mRealListener = null;
  private HashMap<String, Object> map;

  public Object getListener()
  {
    return this.mRealListener;
  }

  public Object getValue(String paramString)
  {
    if ((this.map != null) && (this.map.containsKey(paramString)))
      return this.map.get(paramString);
    return null;
  }

  public void onCancel(Object paramObject)
  {
  }

  public void onComplete(Object paramObject1, Object paramObject2)
  {
  }

  public void onException(Object paramObject)
  {
  }

  public void setValue(String paramString, Object paramObject)
  {
    if (this.map == null)
      this.map = new HashMap();
    this.map.put(paramString, paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.social.SocialEventListener
 * JD-Core Version:    0.6.2
 */