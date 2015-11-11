package fm.qingting.framework.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class ServerConfig
{
  private static ServerConfig instance = null;
  private HashMap<String, String> allDefaultProxies = new HashMap();
  private HashMap<String, List<String>> allDefaultRoots = new HashMap();
  private HashMap<String, MutableRequestTrait> requests = new HashMap();

  public static ServerConfig getInstance()
  {
    try
    {
      if (instance == null)
        instance = new ServerConfig();
      ServerConfig localServerConfig = instance;
      return localServerConfig;
    }
    finally
    {
    }
  }

  // ERROR //
  private void parse(InputStream paramInputStream)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 26	fm/qingting/framework/data/ServerConfig:allDefaultRoots	Ljava/util/HashMap;
    //   4: invokevirtual 42	java/util/HashMap:clear	()V
    //   7: aload_0
    //   8: getfield 28	fm/qingting/framework/data/ServerConfig:requests	Ljava/util/HashMap;
    //   11: invokevirtual 42	java/util/HashMap:clear	()V
    //   14: new 44	java/io/ByteArrayOutputStream
    //   17: dup
    //   18: invokespecial 45	java/io/ByteArrayOutputStream:<init>	()V
    //   21: astore_2
    //   22: sipush 1024
    //   25: newarray byte
    //   27: astore_3
    //   28: aload_1
    //   29: aload_3
    //   30: iconst_0
    //   31: aload_3
    //   32: arraylength
    //   33: invokevirtual 51	java/io/InputStream:read	([BII)I
    //   36: istore 4
    //   38: iload 4
    //   40: iconst_m1
    //   41: if_icmpne +25 -> 66
    //   44: aload_0
    //   45: new 53	java/lang/String
    //   48: dup
    //   49: aload_2
    //   50: invokevirtual 57	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   53: ldc 59
    //   55: invokespecial 62	java/lang/String:<init>	([BLjava/lang/String;)V
    //   58: invokespecial 66	fm/qingting/framework/data/ServerConfig:parseJson	(Ljava/lang/String;)V
    //   61: aload_1
    //   62: invokevirtual 69	java/io/InputStream:close	()V
    //   65: return
    //   66: aload_2
    //   67: aload_3
    //   68: iconst_0
    //   69: iload 4
    //   71: invokevirtual 73	java/io/ByteArrayOutputStream:write	([BII)V
    //   74: goto -46 -> 28
    //   77: astore_2
    //   78: aload_2
    //   79: invokevirtual 76	java/io/UnsupportedEncodingException:printStackTrace	()V
    //   82: aload_1
    //   83: invokevirtual 69	java/io/InputStream:close	()V
    //   86: return
    //   87: astore_1
    //   88: aload_1
    //   89: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   92: return
    //   93: astore_2
    //   94: aload_2
    //   95: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   98: aload_1
    //   99: invokevirtual 69	java/io/InputStream:close	()V
    //   102: return
    //   103: astore_1
    //   104: aload_1
    //   105: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   108: return
    //   109: astore_2
    //   110: aload_1
    //   111: invokevirtual 69	java/io/InputStream:close	()V
    //   114: aload_2
    //   115: athrow
    //   116: astore_1
    //   117: aload_1
    //   118: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   121: goto -7 -> 114
    //   124: astore_1
    //   125: aload_1
    //   126: invokevirtual 77	java/io/IOException:printStackTrace	()V
    //   129: return
    //
    // Exception table:
    //   from	to	target	type
    //   14	28	77	java/io/UnsupportedEncodingException
    //   28	38	77	java/io/UnsupportedEncodingException
    //   44	61	77	java/io/UnsupportedEncodingException
    //   66	74	77	java/io/UnsupportedEncodingException
    //   82	86	87	java/io/IOException
    //   14	28	93	java/io/IOException
    //   28	38	93	java/io/IOException
    //   44	61	93	java/io/IOException
    //   66	74	93	java/io/IOException
    //   98	102	103	java/io/IOException
    //   14	28	109	finally
    //   28	38	109	finally
    //   44	61	109	finally
    //   66	74	109	finally
    //   78	82	109	finally
    //   94	98	109	finally
    //   110	114	116	java/io/IOException
    //   61	65	124	java/io/IOException
  }

  private void parseJson(String paramString)
  {
    try
    {
      paramString = (JSONObject)JSON.parse(paramString);
      Object localObject1 = paramString.getJSONArray("defaultroots");
      Object localObject2 = new ArrayList();
      int i = 0;
      while (true)
      {
        if (i >= ((JSONArray)localObject1).size())
        {
          setDefaultRoots((List)localObject2, "net");
          localObject2 = paramString.getJSONArray("requests");
          i = 0;
          if (i < ((JSONArray)localObject2).size())
            break;
          return;
        }
        ((List)localObject2).add(((JSONArray)localObject1).getJSONObject(i).getString("root").trim());
        i += 1;
      }
      Object localObject3 = ((JSONArray)localObject2).getJSONObject(i);
      String str1 = ((JSONObject)localObject3).getString("type");
      Object localObject4 = ((JSONObject)localObject3).getString("command");
      String str2 = ((JSONObject)localObject3).getString("datatype");
      String str3 = ((JSONObject)localObject3).getString("datasource");
      String str4 = ((JSONObject)localObject3).getString("method");
      localObject1 = ((JSONObject)localObject3).getString("encoding");
      paramString = (String)localObject1;
      if (localObject1 == null)
        paramString = "utf-8";
      localObject1 = ((JSONObject)localObject3).getString("commandparamencode");
      String str5 = ((JSONObject)localObject3).getString("proxy");
      ArrayList localArrayList = new ArrayList();
      if (localObject4 != null)
        localArrayList.add(localObject4);
      localObject4 = new ArrayList();
      localObject3 = ((JSONObject)localObject3).getJSONArray("roots");
      int j;
      if (localObject3 != null)
        j = 0;
      while (true)
      {
        if (j >= ((JSONArray)localObject3).size())
        {
          addRequest(str1, str3, localArrayList, str4, str2, (List)localObject4, paramString, str5, (String)localObject1);
          i += 1;
          break;
        }
        String str6 = ((JSONArray)localObject3).getJSONObject(j).getString("root");
        if (str6 != null)
          ((List)localObject4).add(str6);
        j += 1;
      }
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }

  public void addRequest(String paramString1, String paramString2, List<String> paramList1, String paramString3, String paramString4, List<String> paramList2, String paramString5, String paramString6, String paramString7)
  {
    paramString2 = new MutableRequestTrait(paramString1, paramString2, paramList1, paramString3, paramString4, paramList2, (List)this.allDefaultRoots.get(paramString2), paramString5, paramString6, paramString7);
    this.requests.put(paramString1, paramString2);
  }

  public String getDefaultProxy(String paramString)
  {
    if (paramString == null)
      return null;
    return (String)this.allDefaultProxies.get(paramString);
  }

  public RequestTrait getRequestTrait(String paramString)
  {
    return (RequestTrait)this.requests.get(paramString);
  }

  public void setDefaultProxy(String paramString1, String paramString2)
  {
    this.allDefaultProxies.put(paramString1, paramString2);
  }

  public void setDefaultRoot(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramString1);
    setDefaultRoots(localArrayList, paramString2);
  }

  public void setDefaultRoots(List<String> paramList, String paramString)
  {
    Object localObject2 = (List)this.allDefaultRoots.get(paramString);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new ArrayList();
      this.allDefaultRoots.put(paramString, localObject1);
    }
    ((List)localObject1).clear();
    paramList = paramList.iterator();
    if (!paramList.hasNext())
      paramList = this.requests.values().iterator();
    while (true)
    {
      if (!paramList.hasNext())
      {
        return;
        ((List)localObject1).add(new String((String)paramList.next()));
        break;
      }
      localObject2 = (MutableRequestTrait)paramList.next();
      if (((MutableRequestTrait)localObject2).dataSource.equalsIgnoreCase(paramString))
        ((MutableRequestTrait)localObject2).setDefaultRoots((List)localObject1);
    }
  }

  public void setEncoding(String paramString1, String paramString2)
  {
    Iterator localIterator = this.requests.values().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      MutableRequestTrait localMutableRequestTrait = (MutableRequestTrait)localIterator.next();
      if (localMutableRequestTrait.dataSource.equalsIgnoreCase(paramString2))
        localMutableRequestTrait.setEncoding(paramString1);
    }
  }

  public void setRequestCommands(List<String> paramList, String paramString)
  {
    paramString = (MutableRequestTrait)this.requests.get(paramString);
    if (paramString == null)
      return;
    paramString.setCommands(paramList);
  }

  public void setRequestRoots(List<String> paramList, String paramString)
  {
    paramString = (MutableRequestTrait)this.requests.get(paramString);
    if (paramString == null)
      return;
    paramString.setRoots(paramList);
  }

  public void setServerConfig(InputStream paramInputStream)
  {
    try
    {
      parse(paramInputStream);
      return;
    }
    finally
    {
      paramInputStream = finally;
    }
    throw paramInputStream;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.ServerConfig
 * JD-Core Version:    0.6.2
 */