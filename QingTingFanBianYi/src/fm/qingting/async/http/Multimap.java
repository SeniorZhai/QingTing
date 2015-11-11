package fm.qingting.async.http;

import android.net.Uri;
import fm.qingting.async.http.libcore.RawHeaders;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Multimap extends Hashtable<String, List<String>>
  implements Iterable<NameValuePair>
{
  public Multimap()
  {
  }

  public Multimap(RawHeaders paramRawHeaders)
  {
    paramRawHeaders.toMultimap().putAll(this);
  }

  public Multimap(List<NameValuePair> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      NameValuePair localNameValuePair = (NameValuePair)paramList.next();
      add(localNameValuePair.getName(), localNameValuePair.getValue());
    }
  }

  public static Multimap parseHeader(RawHeaders paramRawHeaders, String paramString)
  {
    return parseHeader(paramRawHeaders.get(paramString));
  }

  public static Multimap parseHeader(String paramString)
  {
    Multimap localMultimap = new Multimap();
    String[] arrayOfString = paramString.split(";");
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfString[i].split("=", 2);
      String str = localObject[0].trim();
      paramString = null;
      if (localObject.length > 1)
        paramString = localObject[1];
      localObject = paramString;
      if (paramString != null)
      {
        localObject = paramString;
        if (paramString.endsWith("\""))
        {
          localObject = paramString;
          if (paramString.startsWith("\""))
            localObject = paramString.substring(1, paramString.length() - 1);
        }
      }
      localMultimap.add(str, (String)localObject);
      i += 1;
    }
    return localMultimap;
  }

  public static Multimap parseQuery(String paramString)
  {
    Multimap localMultimap = new Multimap();
    String[] arrayOfString1 = paramString.split("&");
    int j = arrayOfString1.length;
    int i = 0;
    if (i < j)
    {
      String[] arrayOfString2 = arrayOfString1[i].split("=", 2);
      if (arrayOfString2.length == 0);
      while (true)
      {
        i += 1;
        break;
        String str = Uri.decode(arrayOfString2[0]);
        paramString = null;
        if (arrayOfString2.length == 2)
          paramString = Uri.decode(arrayOfString2[1]);
        localMultimap.add(str, paramString);
      }
    }
    return localMultimap;
  }

  public void add(String paramString1, String paramString2)
  {
    List localList = (List)get(paramString1);
    Object localObject = localList;
    if (localList == null)
    {
      localObject = new ArrayList();
      put(paramString1, localObject);
    }
    ((List)localObject).add(paramString2);
  }

  public String getString(String paramString)
  {
    paramString = (List)get(paramString);
    if ((paramString == null) || (paramString.size() == 0))
      return null;
    return (String)paramString.get(0);
  }

  public Iterator<NameValuePair> iterator()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator1 = keySet().iterator();
    while (localIterator1.hasNext())
    {
      String str = (String)localIterator1.next();
      Iterator localIterator2 = ((List)get(str)).iterator();
      while (localIterator2.hasNext())
        localArrayList.add(new BasicNameValuePair(str, (String)localIterator2.next()));
    }
    return localArrayList.iterator();
  }

  public void put(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString2);
    put(paramString1, localArrayList);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.Multimap
 * JD-Core Version:    0.6.2
 */