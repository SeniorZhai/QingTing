package fm.qingting.async.http.libcore;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public final class RawHeaders
{
  private static final Comparator<String> FIELD_NAME_COMPARATOR = new Comparator()
  {
    public int compare(String paramAnonymousString1, String paramAnonymousString2)
    {
      if (paramAnonymousString1 == paramAnonymousString2)
        return 0;
      if (paramAnonymousString1 == null)
        return -1;
      if (paramAnonymousString2 == null)
        return 1;
      return String.CASE_INSENSITIVE_ORDER.compare(paramAnonymousString1, paramAnonymousString2);
    }
  };
  private int httpMinorVersion = 1;
  private final List<String> namesAndValues = new ArrayList(20);
  private int responseCode = -1;
  private String responseMessage;
  private String statusLine;

  public RawHeaders()
  {
  }

  public RawHeaders(RawHeaders paramRawHeaders)
  {
    this.namesAndValues.addAll(paramRawHeaders.namesAndValues);
    this.statusLine = paramRawHeaders.statusLine;
    this.httpMinorVersion = paramRawHeaders.httpMinorVersion;
    this.responseCode = paramRawHeaders.responseCode;
    this.responseMessage = paramRawHeaders.responseMessage;
  }

  public static RawHeaders fromMultimap(Map<String, List<String>> paramMap)
  {
    RawHeaders localRawHeaders = new RawHeaders();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      String str = (String)((Map.Entry)localObject).getKey();
      localObject = (List)((Map.Entry)localObject).getValue();
      if (str != null)
        localRawHeaders.addAll(str, (List)localObject);
      else if (!((List)localObject).isEmpty())
        localRawHeaders.setStatusLine((String)((List)localObject).get(((List)localObject).size() - 1));
    }
    return localRawHeaders;
  }

  public void add(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("fieldName == null");
    if (paramString2 == null)
    {
      System.err.println("Ignoring HTTP header field '" + paramString1 + "' because its value is null");
      return;
    }
    this.namesAndValues.add(paramString1);
    this.namesAndValues.add(paramString2.trim());
  }

  public void addAll(String paramString, List<String> paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
      add(paramString, (String)paramList.next());
  }

  public void addLine(String paramString)
  {
    int i = paramString.indexOf(":");
    if (i == -1)
    {
      add("", paramString);
      return;
    }
    add(paramString.substring(0, i), paramString.substring(i + 1));
  }

  public String get(String paramString)
  {
    int i = this.namesAndValues.size() - 2;
    while (i >= 0)
    {
      if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
        return (String)this.namesAndValues.get(i + 1);
      i -= 2;
    }
    return null;
  }

  public RawHeaders getAll(Set<String> paramSet)
  {
    RawHeaders localRawHeaders = new RawHeaders();
    int i = 0;
    while (i < this.namesAndValues.size())
    {
      String str = (String)this.namesAndValues.get(i);
      if (paramSet.contains(str))
        localRawHeaders.add(str, (String)this.namesAndValues.get(i + 1));
      i += 2;
    }
    return localRawHeaders;
  }

  public String getFieldName(int paramInt)
  {
    paramInt *= 2;
    if ((paramInt < 0) || (paramInt >= this.namesAndValues.size()))
      return null;
    return (String)this.namesAndValues.get(paramInt);
  }

  public int getHttpMinorVersion()
  {
    if (this.httpMinorVersion != -1)
      return this.httpMinorVersion;
    return 1;
  }

  public int getResponseCode()
  {
    return this.responseCode;
  }

  public String getResponseMessage()
  {
    return this.responseMessage;
  }

  public String getStatusLine()
  {
    return this.statusLine;
  }

  public String getValue(int paramInt)
  {
    paramInt = paramInt * 2 + 1;
    if ((paramInt < 0) || (paramInt >= this.namesAndValues.size()))
      return null;
    return (String)this.namesAndValues.get(paramInt);
  }

  public int length()
  {
    return this.namesAndValues.size() / 2;
  }

  public void removeAll(String paramString)
  {
    int i = 0;
    while (i < this.namesAndValues.size())
    {
      if (paramString.equalsIgnoreCase((String)this.namesAndValues.get(i)))
      {
        this.namesAndValues.remove(i);
        this.namesAndValues.remove(i);
      }
      i += 2;
    }
  }

  public void set(String paramString1, String paramString2)
  {
    removeAll(paramString1);
    add(paramString1, paramString2);
  }

  public void setStatusLine(String paramString)
  {
    paramString = paramString.trim();
    this.statusLine = paramString;
    if ((paramString == null) || (!paramString.startsWith("HTTP/")));
    int i;
    do
    {
      int k;
      do
      {
        return;
        paramString = paramString.trim();
        k = paramString.indexOf(" ") + 1;
      }
      while (k == 0);
      if (paramString.charAt(k - 2) != '1')
        this.httpMinorVersion = 0;
      int j = k + 3;
      i = j;
      if (j > paramString.length())
        i = paramString.length();
      this.responseCode = Integer.parseInt(paramString.substring(k, i));
    }
    while (i + 1 > paramString.length());
    this.responseMessage = paramString.substring(i + 1);
  }

  public String toHeaderString()
  {
    StringBuilder localStringBuilder = new StringBuilder(256);
    localStringBuilder.append(this.statusLine).append("\r\n");
    int i = 0;
    while (i < this.namesAndValues.size())
    {
      localStringBuilder.append((String)this.namesAndValues.get(i)).append(": ").append((String)this.namesAndValues.get(i + 1)).append("\r\n");
      i += 2;
    }
    localStringBuilder.append("\r\n");
    return localStringBuilder.toString();
  }

  public Map<String, List<String>> toMultimap()
  {
    TreeMap localTreeMap = new TreeMap(FIELD_NAME_COMPARATOR);
    int i = 0;
    while (i < this.namesAndValues.size())
    {
      String str1 = (String)this.namesAndValues.get(i);
      String str2 = (String)this.namesAndValues.get(i + 1);
      ArrayList localArrayList = new ArrayList();
      List localList = (List)localTreeMap.get(str1);
      if (localList != null)
        localArrayList.addAll(localList);
      localArrayList.add(str2);
      localTreeMap.put(str1, Collections.unmodifiableList(localArrayList));
      i += 2;
    }
    if (this.statusLine != null)
      localTreeMap.put(null, Collections.unmodifiableList(Collections.singletonList(this.statusLine)));
    return Collections.unmodifiableMap(localTreeMap);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.async.http.libcore.RawHeaders
 * JD-Core Version:    0.6.2
 */