package fm.qingting.framework.data;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Map<Ljava.lang.String;Ljava.lang.Object;>;
import java.util.Set;

public class RequestTrait
{
  protected String command;
  protected String commandParamEncode;
  protected ArrayList<String> commands;
  protected String dataSource;
  protected String dataType;
  protected ArrayList<String> defaultRoots;
  protected String encoding;
  private List<String> fullCommands;
  protected int index = 0;
  protected String method;
  protected String proxy;
  protected ArrayList<String> roots;
  protected String type;

  public RequestTrait(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList1, List<String> paramList2, String paramString6, String paramString7, String paramString8)
  {
    this(paramString1, paramString2, new ArrayList(), paramString4, paramString5, paramList1, paramList2, paramString6, paramString7, paramString8);
    this.commands.add(paramString3);
    resetFullCommands();
  }

  public RequestTrait(String paramString1, String paramString2, List<String> paramList1, String paramString3, String paramString4, List<String> paramList2, List<String> paramList3, String paramString5, String paramString6, String paramString7)
  {
    this.type = paramString1;
    this.dataSource = paramString2;
    this.commands = buildArrayList(paramList1);
    this.method = paramString3;
    this.dataType = paramString4;
    this.roots = buildArrayList(paramList2);
    this.defaultRoots = buildArrayList(paramList3);
    this.encoding = paramString5;
    this.proxy = paramString6;
    this.commandParamEncode = paramString7;
    resetFullCommands();
  }

  private String buildCommand(String paramString, Map<String, Object> paramMap)
  {
    int i;
    Iterator localIterator;
    if ((this.commandParamEncode != null) && (!this.commandParamEncode.equalsIgnoreCase("")))
    {
      i = 1;
      localIterator = paramMap.entrySet().iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
      {
        return paramString;
        i = 0;
        break;
      }
      Object localObject = (Map.Entry)localIterator.next();
      paramMap = (String)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      if (!(localObject instanceof String))
        continue;
      String str = String.format("{%s}", new Object[] { paramMap });
      localObject = (String)localObject;
      paramMap = (Map<String, Object>)localObject;
      if (i != 0);
      try
      {
        paramMap = URLEncoder.encode((String)localObject, this.commandParamEncode);
        paramString = paramString.replace(str, paramMap);
      }
      catch (UnsupportedEncodingException paramMap)
      {
        while (true)
        {
          paramMap.printStackTrace();
          paramMap = (Map<String, Object>)localObject;
        }
      }
    }
  }

  private Map<String, Object> filterParamWithCommand(String paramString, Map<String, Object> paramMap)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localHashMap;
      String str = (String)localIterator.next();
      if (paramString.indexOf(String.format("{%s}", new Object[] { str })) == -1)
        localHashMap.put(str, paramMap.get(str));
    }
  }

  private void resetCommand()
  {
    try
    {
      if (this.fullCommands.size() == 0)
      {
        this.command = null;
        return;
      }
      if (this.fullCommands.size() <= this.index)
        this.index = 0;
      this.command = ((String)this.fullCommands.get(this.index));
      return;
    }
    catch (Exception localException)
    {
    }
  }

  protected ArrayList<String> buildArrayList(List<String> paramList)
  {
    if (paramList == null)
    {
      paramList = null;
      return paramList;
    }
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      paramList = localArrayList;
      if (!localIterator.hasNext())
        break;
      localArrayList.add((String)localIterator.next());
    }
  }

  public String commandWithParam(Map<String, Object> paramMap)
  {
    if (paramMap == null)
      return getCommand();
    return buildCommand(getCommand(), paramMap);
  }

  public Map<String, Object> extendedParamWithParam(Map<String, Object> paramMap)
  {
    return filterParamWithCommand(this.command, paramMap);
  }

  public String getCommand()
  {
    if (this.command != null)
      return this.command;
    resetCommand();
    return this.command;
  }

  public String getCommandParamEncode()
  {
    return this.commandParamEncode;
  }

  public String getDataSource()
  {
    return this.dataSource;
  }

  public String getDataType()
  {
    return this.dataType;
  }

  public String getEncoding()
  {
    return this.encoding;
  }

  public String getMethod()
  {
    return this.method;
  }

  public String getNextCommand()
  {
    this.index += 1;
    if (this.index >= this.fullCommands.size())
      this.index = 0;
    resetCommand();
    return this.command;
  }

  public String getProxy()
  {
    return this.proxy;
  }

  public int getTotalCommands()
  {
    return this.fullCommands.size();
  }

  public String getType()
  {
    return this.type;
  }

  public String nextCommandWithParam(Map<String, Object> paramMap)
  {
    if (paramMap == null)
      return getNextCommand();
    return buildCommand(getNextCommand(), paramMap);
  }

  protected void resetFullCommands()
  {
    this.index = 0;
    this.fullCommands = new ArrayList();
    if (this.commands == null);
    label68: label210: 
    while (true)
    {
      return;
      int i;
      ArrayList localArrayList;
      Iterator localIterator1;
      if (((this.roots == null) || (this.roots.size() <= 0)) && ((this.defaultRoots == null) || (this.defaultRoots.size() <= 0)))
      {
        i = 0;
        if (i != 0)
          break label165;
        localArrayList = null;
        localIterator1 = this.commands.iterator();
      }
      while (true)
      {
        if (!localIterator1.hasNext())
          break label210;
        String str1 = (String)localIterator1.next();
        if (i != 0)
        {
          Iterator localIterator2 = localArrayList.iterator();
          while (localIterator2.hasNext())
          {
            String str2 = (String)localIterator2.next();
            this.fullCommands.add(String.format("%s%s", new Object[] { str2, str1 }));
          }
          continue;
          i = 1;
          break;
          if ((this.roots != null) && (this.roots.size() > 0))
          {
            localArrayList = this.roots;
            break label68;
          }
          localArrayList = this.defaultRoots;
          break label68;
        }
        this.fullCommands.add(str1);
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.RequestTrait
 * JD-Core Version:    0.6.2
 */