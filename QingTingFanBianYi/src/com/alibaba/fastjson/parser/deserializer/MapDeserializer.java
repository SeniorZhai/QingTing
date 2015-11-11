package com.alibaba.fastjson.parser.deserializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.DefaultJSONParser.ResolveTask;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONToken;
import com.alibaba.fastjson.parser.ParseContext;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.util.TypeUtils;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.IdentityHashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MapDeserializer
  implements ObjectDeserializer
{
  public static final MapDeserializer instance = new MapDeserializer();

  public static Object parseMap(DefaultJSONParser paramDefaultJSONParser, Map<Object, Object> paramMap, Type paramType1, Type paramType2, Object paramObject)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if ((localJSONLexer.token() != 12) && (localJSONLexer.token() != 16))
      throw new JSONException("syntax error, expect {, actual " + localJSONLexer.tokenName());
    ObjectDeserializer localObjectDeserializer1 = paramDefaultJSONParser.getConfig().getDeserializer(paramType1);
    ObjectDeserializer localObjectDeserializer2 = paramDefaultJSONParser.getConfig().getDeserializer(paramType2);
    localJSONLexer.nextToken(localObjectDeserializer1.getFastMatchToken());
    paramObject = paramDefaultJSONParser.getContext();
    while (true)
    {
      try
      {
        if (localJSONLexer.token() == 13)
        {
          localJSONLexer.nextToken(16);
          return paramMap;
        }
        if ((localJSONLexer.token() != 4) || (!localJSONLexer.isRef()))
          break label350;
        paramMap = null;
        localJSONLexer.nextTokenWithColon(4);
        if (localJSONLexer.token() != 4)
          break label297;
        paramType1 = localJSONLexer.stringVal();
        if ("..".equals(paramType1))
        {
          paramMap = paramObject.getParentContext().getObject();
          localJSONLexer.nextToken(13);
          if (localJSONLexer.token() == 13)
            break label333;
          throw new JSONException("illegal ref");
        }
      }
      finally
      {
        paramDefaultJSONParser.setContext(paramObject);
      }
      if ("$".equals(paramType1))
      {
        for (paramMap = paramObject; paramMap.getParentContext() != null; paramMap = paramMap.getParentContext());
        paramMap = paramMap.getObject();
      }
      else
      {
        paramDefaultJSONParser.addResolveTask(new DefaultJSONParser.ResolveTask(paramObject, paramType1));
        paramDefaultJSONParser.setResolveStatus(1);
        continue;
        label297: throw new JSONException("illegal ref, " + JSONToken.name(localJSONLexer.token()));
        label333: localJSONLexer.nextToken(16);
        paramDefaultJSONParser.setContext(paramObject);
        return paramMap;
        label350: if ((paramMap.size() == 0) && (localJSONLexer.token() == 4) && (JSON.DEFAULT_TYPE_KEY.equals(localJSONLexer.stringVal())))
        {
          localJSONLexer.nextTokenWithColon(4);
          localJSONLexer.nextToken(16);
          if (localJSONLexer.token() == 13)
          {
            localJSONLexer.nextToken();
            paramDefaultJSONParser.setContext(paramObject);
            return paramMap;
          }
          localJSONLexer.nextToken(localObjectDeserializer1.getFastMatchToken());
        }
        Object localObject = localObjectDeserializer1.deserialze(paramDefaultJSONParser, paramType1, null);
        if (localJSONLexer.token() != 17)
          throw new JSONException("syntax error, expect :, actual " + localJSONLexer.token());
        localJSONLexer.nextToken(localObjectDeserializer2.getFastMatchToken());
        paramMap.put(localObject, localObjectDeserializer2.deserialze(paramDefaultJSONParser, paramType2, localObject));
        if (localJSONLexer.token() == 16)
          localJSONLexer.nextToken(localObjectDeserializer1.getFastMatchToken());
      }
    }
  }

  public static Map parseMap(DefaultJSONParser paramDefaultJSONParser, Map<String, Object> paramMap, Type paramType, Object paramObject)
  {
    JSONLexer localJSONLexer = paramDefaultJSONParser.getLexer();
    if (localJSONLexer.token() != 12)
      throw new JSONException("syntax error, expect {, actual " + localJSONLexer.token());
    ParseContext localParseContext = paramDefaultJSONParser.getContext();
    int i;
    label440: label605: 
    do
    {
      Object localObject1;
      do
      {
        int j;
        try
        {
          localJSONLexer.skipWhitespace();
          i = localJSONLexer.getCurrent();
          j = i;
          if (paramDefaultJSONParser.isEnabled(Feature.AllowArbitraryCommas))
            while (true)
            {
              j = i;
              if (i != 44)
                break;
              localJSONLexer.next();
              localJSONLexer.skipWhitespace();
              i = localJSONLexer.getCurrent();
            }
          if (j == 34)
          {
            localObject1 = localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable(), '"');
            localJSONLexer.skipWhitespace();
            if (localJSONLexer.getCurrent() == ':')
              break label440;
            throw new JSONException("expect ':' at " + localJSONLexer.pos());
          }
        }
        finally
        {
          paramDefaultJSONParser.setContext(localParseContext);
        }
        if (j == 125)
        {
          localJSONLexer.next();
          localJSONLexer.resetStringPosition();
          localJSONLexer.nextToken(16);
          paramDefaultJSONParser.setContext(localParseContext);
          return paramMap;
        }
        if (j == 39)
        {
          if (!paramDefaultJSONParser.isEnabled(Feature.AllowSingleQuotes))
            throw new JSONException("syntax error");
          localObject1 = localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable(), '\'');
          localJSONLexer.skipWhitespace();
          if (localJSONLexer.getCurrent() != ':')
            throw new JSONException("expect ':' at " + localJSONLexer.pos());
        }
        else
        {
          if (!paramDefaultJSONParser.isEnabled(Feature.AllowUnQuotedFieldNames))
            throw new JSONException("syntax error");
          localObject1 = localJSONLexer.scanSymbolUnQuoted(paramDefaultJSONParser.getSymbolTable());
          localJSONLexer.skipWhitespace();
          char c = localJSONLexer.getCurrent();
          if (c != ':')
            throw new JSONException("expect ':' at " + localJSONLexer.pos() + ", actual " + c);
        }
        localJSONLexer.next();
        localJSONLexer.skipWhitespace();
        localJSONLexer.getCurrent();
        localJSONLexer.resetStringPosition();
        if (localObject1 != JSON.DEFAULT_TYPE_KEY)
          break label605;
        localObject1 = TypeUtils.loadClass(localJSONLexer.scanSymbol(paramDefaultJSONParser.getSymbolTable(), '"'));
        if (localObject1 != paramMap.getClass())
          break;
        localJSONLexer.nextToken(16);
      }
      while (localJSONLexer.token() != 13);
      localJSONLexer.nextToken(16);
      paramDefaultJSONParser.setContext(localParseContext);
      return paramMap;
      paramMap = paramDefaultJSONParser.getConfig().getDeserializer((Type)localObject1);
      localJSONLexer.nextToken(16);
      paramDefaultJSONParser.setResolveStatus(2);
      if ((localParseContext != null) && (!(paramObject instanceof Integer)))
        paramDefaultJSONParser.popContext();
      paramMap = (Map)paramMap.deserialze(paramDefaultJSONParser, (Type)localObject1, paramObject);
      paramDefaultJSONParser.setContext(localParseContext);
      return paramMap;
      localJSONLexer.nextToken();
      Object localObject2;
      if (localJSONLexer.token() == 8)
      {
        localObject2 = null;
        localJSONLexer.nextToken();
      }
      while (true)
      {
        paramMap.put(localObject1, localObject2);
        paramDefaultJSONParser.checkMapResolve(paramMap, (String)localObject1);
        paramDefaultJSONParser.setContext(localParseContext, localObject2, localObject1);
        i = localJSONLexer.token();
        if ((i != 20) && (i != 15))
          break;
        paramDefaultJSONParser.setContext(localParseContext);
        return paramMap;
        localObject2 = paramDefaultJSONParser.parseObject(paramType);
      }
    }
    while (i != 13);
    localJSONLexer.nextToken();
    paramDefaultJSONParser.setContext(localParseContext);
    return paramMap;
  }

  protected Map<Object, Object> createMap(Type paramType)
  {
    if (paramType == Properties.class)
      return new Properties();
    if (paramType == Hashtable.class)
      return new Hashtable();
    if (paramType == IdentityHashMap.class)
      return new IdentityHashMap();
    if ((paramType == SortedMap.class) || (paramType == TreeMap.class))
      return new TreeMap();
    if ((paramType == ConcurrentMap.class) || (paramType == ConcurrentHashMap.class))
      return new ConcurrentHashMap();
    if ((paramType == Map.class) || (paramType == HashMap.class))
      return new HashMap();
    if (paramType == LinkedHashMap.class)
      return new LinkedHashMap();
    if ((paramType instanceof ParameterizedType))
      return createMap(((ParameterizedType)paramType).getRawType());
    Object localObject = (Class)paramType;
    if (((Class)localObject).isInterface())
      throw new JSONException("unsupport type " + paramType);
    try
    {
      localObject = (Map)((Class)localObject).newInstance();
      return localObject;
    }
    catch (Exception localException)
    {
      throw new JSONException("unsupport type " + paramType, localException);
    }
  }

  public <T> T deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject)
  {
    Object localObject = paramDefaultJSONParser.getLexer();
    if (((JSONLexer)localObject).token() == 8)
    {
      ((JSONLexer)localObject).nextToken(16);
      return null;
    }
    Map localMap = createMap(paramType);
    localObject = paramDefaultJSONParser.getContext();
    try
    {
      paramDefaultJSONParser.setContext((ParseContext)localObject, localMap, paramObject);
      paramType = deserialze(paramDefaultJSONParser, paramType, paramObject, localMap);
      return paramType;
    }
    finally
    {
      paramDefaultJSONParser.setContext((ParseContext)localObject);
    }
    throw paramType;
  }

  protected Object deserialze(DefaultJSONParser paramDefaultJSONParser, Type paramType, Object paramObject, Map paramMap)
  {
    if ((paramType instanceof ParameterizedType))
    {
      Object localObject = (ParameterizedType)paramType;
      paramType = localObject.getActualTypeArguments()[0];
      localObject = localObject.getActualTypeArguments()[1];
      if (String.class == paramType)
        return parseMap(paramDefaultJSONParser, paramMap, (Type)localObject, paramObject);
      return parseMap(paramDefaultJSONParser, paramMap, paramType, (Type)localObject, paramObject);
    }
    return paramDefaultJSONParser.parseObject(paramMap, paramObject);
  }

  public int getFastMatchToken()
  {
    return 12;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.deserializer.MapDeserializer
 * JD-Core Version:    0.6.2
 */