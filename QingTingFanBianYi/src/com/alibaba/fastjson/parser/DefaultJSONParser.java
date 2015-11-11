package com.alibaba.fastjson.parser;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.deserializer.ASMJavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.CollectionResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import com.alibaba.fastjson.parser.deserializer.FieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.IntegerDeserializer;
import com.alibaba.fastjson.parser.deserializer.JavaBeanDeserializer;
import com.alibaba.fastjson.parser.deserializer.ListResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.LongDeserializer;
import com.alibaba.fastjson.parser.deserializer.MapResolveFieldDeserializer;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.parser.deserializer.StringDeserializer;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DefaultJSONParser extends AbstractJSONParser
  implements Closeable
{
  public static final int NONE = 0;
  public static final int NeedToResolve = 1;
  public static final int TypeNameRedirect = 2;
  private static final Set<Class<?>> primitiveClasses = new HashSet();
  protected ParserConfig config;
  protected ParseContext context;
  private ParseContext[] contextArray = new ParseContext[8];
  private int contextArrayIndex = 0;
  private DateFormat dateFormat;
  private String dateFormatPattern = JSON.DEFFAULT_DATE_FORMAT;
  private List<ExtraProcessor> extraProcessors = null;
  private List<ExtraTypeProvider> extraTypeProviders = null;
  protected final Object input;
  protected final JSONLexer lexer;
  private int resolveStatus = 0;
  private List<ResolveTask> resolveTaskList;
  protected final SymbolTable symbolTable;

  static
  {
    primitiveClasses.add(Boolean.TYPE);
    primitiveClasses.add(Byte.TYPE);
    primitiveClasses.add(Short.TYPE);
    primitiveClasses.add(Integer.TYPE);
    primitiveClasses.add(Long.TYPE);
    primitiveClasses.add(Float.TYPE);
    primitiveClasses.add(Double.TYPE);
    primitiveClasses.add(Boolean.class);
    primitiveClasses.add(Byte.class);
    primitiveClasses.add(Short.class);
    primitiveClasses.add(Integer.class);
    primitiveClasses.add(Long.class);
    primitiveClasses.add(Float.class);
    primitiveClasses.add(Double.class);
    primitiveClasses.add(BigInteger.class);
    primitiveClasses.add(BigDecimal.class);
    primitiveClasses.add(String.class);
  }

  public DefaultJSONParser(JSONLexer paramJSONLexer)
  {
    this(paramJSONLexer, ParserConfig.getGlobalInstance());
  }

  public DefaultJSONParser(JSONLexer paramJSONLexer, ParserConfig paramParserConfig)
  {
    this(null, paramJSONLexer, paramParserConfig);
  }

  public DefaultJSONParser(Object paramObject, JSONLexer paramJSONLexer, ParserConfig paramParserConfig)
  {
    this.lexer = paramJSONLexer;
    this.input = paramObject;
    this.config = paramParserConfig;
    this.symbolTable = paramParserConfig.getSymbolTable();
    paramJSONLexer.nextToken(12);
  }

  public DefaultJSONParser(String paramString)
  {
    this(paramString, ParserConfig.getGlobalInstance(), JSON.DEFAULT_PARSER_FEATURE);
  }

  public DefaultJSONParser(String paramString, ParserConfig paramParserConfig)
  {
    this(paramString, new JSONScanner(paramString, JSON.DEFAULT_PARSER_FEATURE), paramParserConfig);
  }

  public DefaultJSONParser(String paramString, ParserConfig paramParserConfig, int paramInt)
  {
    this(paramString, new JSONScanner(paramString, paramInt), paramParserConfig);
  }

  public DefaultJSONParser(char[] paramArrayOfChar, int paramInt1, ParserConfig paramParserConfig, int paramInt2)
  {
    this(paramArrayOfChar, new JSONScanner(paramArrayOfChar, paramInt1, paramInt2), paramParserConfig);
  }

  private void addContext(ParseContext paramParseContext)
  {
    int i = this.contextArrayIndex;
    this.contextArrayIndex = (i + 1);
    if (i >= this.contextArray.length)
    {
      ParseContext[] arrayOfParseContext = new ParseContext[this.contextArray.length * 3 / 2];
      System.arraycopy(this.contextArray, 0, arrayOfParseContext, 0, this.contextArray.length);
      this.contextArray = arrayOfParseContext;
    }
    this.contextArray[i] = paramParseContext;
  }

  public final void accept(int paramInt)
  {
    JSONLexer localJSONLexer = getLexer();
    if (localJSONLexer.token() == paramInt)
    {
      localJSONLexer.nextToken();
      return;
    }
    throw new JSONException("syntax error, expect " + JSONToken.name(paramInt) + ", actual " + JSONToken.name(localJSONLexer.token()));
  }

  public final void accept(int paramInt1, int paramInt2)
  {
    JSONLexer localJSONLexer = getLexer();
    if (localJSONLexer.token() == paramInt1)
    {
      localJSONLexer.nextToken(paramInt2);
      return;
    }
    throw new JSONException("syntax error, expect " + JSONToken.name(paramInt1) + ", actual " + JSONToken.name(localJSONLexer.token()));
  }

  public void acceptType(String paramString)
  {
    JSONLexer localJSONLexer = this.lexer;
    localJSONLexer.nextTokenWithColon();
    if (localJSONLexer.token() != 4)
      throw new JSONException("type not match error");
    if (paramString.equals(localJSONLexer.stringVal()))
    {
      localJSONLexer.nextToken();
      if (localJSONLexer.token() == 16)
        localJSONLexer.nextToken();
      return;
    }
    throw new JSONException("type not match error");
  }

  public void addResolveTask(ResolveTask paramResolveTask)
  {
    if (this.resolveTaskList == null)
      this.resolveTaskList = new ArrayList(2);
    this.resolveTaskList.add(paramResolveTask);
  }

  public void checkListResolve(Collection paramCollection)
  {
    if (this.resolveStatus == 1)
    {
      if ((paramCollection instanceof List))
      {
        int i = paramCollection.size();
        paramCollection = (List)paramCollection;
        localResolveTask = getLastResolveTask();
        localResolveTask.setFieldDeserializer(new ListResolveFieldDeserializer(this, paramCollection, i - 1));
        localResolveTask.setOwnerContext(this.context);
        setResolveStatus(0);
      }
    }
    else
      return;
    ResolveTask localResolveTask = getLastResolveTask();
    localResolveTask.setFieldDeserializer(new CollectionResolveFieldDeserializer(this, paramCollection));
    localResolveTask.setOwnerContext(this.context);
    setResolveStatus(0);
  }

  public void checkMapResolve(Map paramMap, String paramString)
  {
    if (this.resolveStatus == 1)
    {
      paramMap = new MapResolveFieldDeserializer(paramMap, paramString);
      paramString = getLastResolveTask();
      paramString.setFieldDeserializer(paramMap);
      paramString.setOwnerContext(this.context);
      setResolveStatus(0);
    }
  }

  public void close()
  {
    JSONLexer localJSONLexer = getLexer();
    try
    {
      if ((isEnabled(Feature.AutoCloseSource)) && (localJSONLexer.token() != 20))
        throw new JSONException("not close json text, token : " + JSONToken.name(localJSONLexer.token()));
    }
    finally
    {
      localJSONLexer.close();
    }
    localJSONLexer.close();
  }

  public void config(Feature paramFeature, boolean paramBoolean)
  {
    getLexer().config(paramFeature, paramBoolean);
  }

  public ParserConfig getConfig()
  {
    return this.config;
  }

  public ParseContext getContext()
  {
    return this.context;
  }

  public String getDateFomartPattern()
  {
    return this.dateFormatPattern;
  }

  public DateFormat getDateFormat()
  {
    if (this.dateFormat == null)
      this.dateFormat = new SimpleDateFormat(this.dateFormatPattern);
    return this.dateFormat;
  }

  public List<ExtraProcessor> getExtraProcessors()
  {
    if (this.extraProcessors == null)
      this.extraProcessors = new ArrayList(2);
    return this.extraProcessors;
  }

  public List<ExtraProcessor> getExtraProcessorsDirect()
  {
    return this.extraProcessors;
  }

  public List<ExtraTypeProvider> getExtraTypeProviders()
  {
    if (this.extraTypeProviders == null)
      this.extraTypeProviders = new ArrayList(2);
    return this.extraTypeProviders;
  }

  public List<ExtraTypeProvider> getExtraTypeProvidersDirect()
  {
    return this.extraTypeProviders;
  }

  public String getInput()
  {
    if ((this.input instanceof char[]))
      return new String((char[])this.input);
    return this.input.toString();
  }

  public ResolveTask getLastResolveTask()
  {
    return (ResolveTask)this.resolveTaskList.get(this.resolveTaskList.size() - 1);
  }

  public JSONLexer getLexer()
  {
    return this.lexer;
  }

  public Object getObject(String paramString)
  {
    int i = 0;
    while (i < this.contextArrayIndex)
    {
      if (paramString.equals(this.contextArray[i].getPath()))
        return this.contextArray[i].getObject();
      i += 1;
    }
    return null;
  }

  public int getResolveStatus()
  {
    return this.resolveStatus;
  }

  public List<ResolveTask> getResolveTaskList()
  {
    if (this.resolveTaskList == null)
      this.resolveTaskList = new ArrayList(2);
    return this.resolveTaskList;
  }

  public List<ResolveTask> getResolveTaskListDirect()
  {
    return this.resolveTaskList;
  }

  public SymbolTable getSymbolTable()
  {
    return this.symbolTable;
  }

  public boolean isEnabled(Feature paramFeature)
  {
    return getLexer().isEnabled(paramFeature);
  }

  public Object parse()
  {
    return parse(null);
  }

  public Object parse(Object paramObject)
  {
    Object localObject1 = null;
    JSONLexer localJSONLexer = getLexer();
    switch (localJSONLexer.token())
    {
    case 5:
    case 10:
    case 11:
    case 13:
    case 15:
    case 16:
    case 17:
    case 18:
    case 19:
    default:
      throw new JSONException("syntax error, pos " + localJSONLexer.getBufferPosition());
    case 21:
      localJSONLexer.nextToken();
      localObject1 = new HashSet();
      parseArray((Collection)localObject1, paramObject);
      paramObject = localObject1;
    case 22:
    case 14:
    case 12:
    case 2:
    case 3:
    case 4:
    case 8:
    case 6:
    case 7:
    case 9:
    case 20:
    }
    do
    {
      return paramObject;
      localJSONLexer.nextToken();
      localObject1 = new TreeSet();
      parseArray((Collection)localObject1, paramObject);
      return localObject1;
      localObject1 = new JSONArray();
      parseArray((Collection)localObject1, paramObject);
      return localObject1;
      return parseObject(new JSONObject(), paramObject);
      paramObject = localJSONLexer.integerValue();
      localJSONLexer.nextToken();
      return paramObject;
      paramObject = localJSONLexer.decimalValue(isEnabled(Feature.UseBigDecimal));
      localJSONLexer.nextToken();
      return paramObject;
      localObject1 = localJSONLexer.stringVal();
      localJSONLexer.nextToken(16);
      if (localJSONLexer.isEnabled(Feature.AllowISO8601DateFormat))
        paramObject = new JSONScanner((String)localObject1);
      try
      {
        if (paramObject.scanISO8601DateIfMatch())
        {
          localObject1 = paramObject.getCalendar().getTime();
          return localObject1;
        }
        return localObject1;
      }
      finally
      {
        paramObject.close();
      }
      localJSONLexer.nextToken();
      return null;
      localJSONLexer.nextToken();
      return Boolean.TRUE;
      localJSONLexer.nextToken();
      return Boolean.FALSE;
      localJSONLexer.nextToken(18);
      if (localJSONLexer.token() != 18)
        throw new JSONException("syntax error");
      localJSONLexer.nextToken(10);
      accept(10);
      long l = localJSONLexer.integerValue().longValue();
      accept(2);
      accept(11);
      return new Date(l);
      paramObject = localObject2;
    }
    while (localJSONLexer.isBlankInput());
    throw new JSONException("unterminated json string, pos " + localJSONLexer.getBufferPosition());
  }

  public <T> List<T> parseArray(Class<T> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    parseArray(paramClass, localArrayList);
    return localArrayList;
  }

  public void parseArray(Class<?> paramClass, Collection paramCollection)
  {
    parseArray(paramClass, paramCollection);
  }

  public void parseArray(Type paramType, Collection paramCollection)
  {
    parseArray(paramType, paramCollection, null);
  }

  public void parseArray(Type paramType, Collection paramCollection, Object paramObject)
  {
    if ((this.lexer.token() == 21) || (this.lexer.token() == 22))
      this.lexer.nextToken();
    if (this.lexer.token() != 14)
      throw new JSONException("exepct '[', but " + JSONToken.name(this.lexer.token()));
    Object localObject;
    ParseContext localParseContext;
    int i;
    if (Integer.TYPE == paramType)
    {
      localObject = IntegerDeserializer.instance;
      this.lexer.nextToken(2);
      localParseContext = getContext();
      setContext(paramCollection, paramObject);
      i = 0;
    }
    while (true)
    {
      try
      {
        if (!isEnabled(Feature.AllowArbitraryCommas))
          break label226;
        if (this.lexer.token() != 16)
          break label226;
        this.lexer.nextToken();
        continue;
      }
      finally
      {
        setContext(localParseContext);
      }
      if (String.class == paramType)
      {
        localObject = StringDeserializer.instance;
        this.lexer.nextToken(4);
        break;
      }
      localObject = this.config.getDeserializer(paramType);
      this.lexer.nextToken(((ObjectDeserializer)localObject).getFastMatchToken());
      break;
      label226: int j = this.lexer.token();
      if (j == 15)
      {
        setContext(localParseContext);
        this.lexer.nextToken(16);
        return;
      }
      if (Integer.TYPE == paramType)
      {
        paramCollection.add(IntegerDeserializer.instance.deserialze(this, null, null));
        if (this.lexer.token() == 16)
          this.lexer.nextToken(((ObjectDeserializer)localObject).getFastMatchToken());
      }
      else
      {
        if (String.class == paramType)
        {
          if (this.lexer.token() == 4)
          {
            paramObject = this.lexer.stringVal();
            this.lexer.nextToken(16);
          }
          while (true)
          {
            paramCollection.add(paramObject);
            break;
            paramObject = parse();
            if (paramObject == null)
              paramObject = null;
            else
              paramObject = paramObject.toString();
          }
        }
        if (this.lexer.token() == 8)
          this.lexer.nextToken();
        for (paramObject = null; ; paramObject = ((ObjectDeserializer)localObject).deserialze(this, paramType, Integer.valueOf(i)))
        {
          paramCollection.add(paramObject);
          checkListResolve(paramCollection);
          break;
        }
      }
      i += 1;
    }
  }

  public final void parseArray(Collection paramCollection)
  {
    parseArray(paramCollection, null);
  }

  public final void parseArray(Collection paramCollection, Object paramObject)
  {
    JSONLexer localJSONLexer = getLexer();
    if ((localJSONLexer.token() == 21) || (localJSONLexer.token() == 22))
      localJSONLexer.nextToken();
    if (localJSONLexer.token() != 14)
      throw new JSONException("syntax error, expect [, actual " + JSONToken.name(localJSONLexer.token()) + ", pos " + localJSONLexer.pos());
    localJSONLexer.nextToken(4);
    ParseContext localParseContext = getContext();
    setContext(paramCollection, paramObject);
    int i = 0;
    try
    {
      if (isEnabled(Feature.AllowArbitraryCommas))
        while (localJSONLexer.token() == 16)
          localJSONLexer.nextToken();
    }
    finally
    {
      setContext(localParseContext);
    }
    switch (localJSONLexer.token())
    {
    case 5:
    case 9:
    case 10:
    case 11:
    case 13:
    case 16:
    case 17:
    case 18:
    case 19:
      label264: paramObject = parse();
    case 2:
    case 3:
    case 4:
    case 6:
    case 7:
    case 12:
    case 14:
    case 8:
    case 15:
    case 20:
    }
    while (true)
    {
      label269: paramCollection.add(paramObject);
      checkListResolve(paramCollection);
      JSONScanner localJSONScanner;
      if (localJSONLexer.token() == 16)
      {
        localJSONLexer.nextToken(4);
        break label551;
        paramObject = localJSONLexer.integerValue();
        localJSONLexer.nextToken(16);
        continue;
        if (localJSONLexer.isEnabled(Feature.UseBigDecimal));
        for (paramObject = localJSONLexer.decimalValue(true); ; paramObject = localJSONLexer.decimalValue(false))
        {
          localJSONLexer.nextToken(16);
          break;
        }
        paramObject = localJSONLexer.stringVal();
        localJSONLexer.nextToken(16);
        if (localJSONLexer.isEnabled(Feature.AllowISO8601DateFormat))
        {
          localJSONScanner = new JSONScanner(paramObject);
          if (localJSONScanner.scanISO8601DateIfMatch())
            paramObject = localJSONScanner.getCalendar().getTime();
        }
      }
      else
      {
        while (true)
        {
          localJSONScanner.close();
          break label269;
          paramObject = Boolean.TRUE;
          localJSONLexer.nextToken(16);
          break label269;
          paramObject = Boolean.FALSE;
          localJSONLexer.nextToken(16);
          break label269;
          paramObject = parseObject(new JSONObject(), Integer.valueOf(i));
          break label269;
          paramObject = new JSONArray();
          parseArray(paramObject, Integer.valueOf(i));
          break label269;
          paramObject = null;
          localJSONLexer.nextToken(4);
          break label269;
          localJSONLexer.nextToken(16);
          setContext(localParseContext);
          return;
          throw new JSONException("unclosed jsonArray");
          break label264;
          label551: i += 1;
          break;
        }
      }
    }
  }

  public Object[] parseArray(Type[] paramArrayOfType)
  {
    if (this.lexer.token() == 8)
    {
      this.lexer.nextToken(16);
      return null;
    }
    if (this.lexer.token() != 14)
      throw new JSONException("syntax error : " + this.lexer.tokenName());
    Object[] arrayOfObject = new Object[paramArrayOfType.length];
    if (paramArrayOfType.length == 0)
    {
      this.lexer.nextToken(15);
      if (this.lexer.token() != 15)
        throw new JSONException("syntax error");
      this.lexer.nextToken(16);
      return new Object[0];
    }
    this.lexer.nextToken(2);
    int i = 0;
    Object localObject;
    if (i < paramArrayOfType.length)
    {
      if (this.lexer.token() != 8)
        break label233;
      localObject = null;
      this.lexer.nextToken(16);
    }
    while (true)
    {
      arrayOfObject[i] = localObject;
      if (this.lexer.token() != 15)
        break;
      if (this.lexer.token() == 15)
        break label710;
      throw new JSONException("syntax error");
      label233: Type localType = paramArrayOfType[i];
      if ((localType == Integer.TYPE) || (localType == Integer.class))
      {
        if (this.lexer.token() == 2)
        {
          localObject = Integer.valueOf(this.lexer.intValue());
          this.lexer.nextToken(16);
        }
        else
        {
          localObject = TypeUtils.cast(parse(), localType, this.config);
        }
      }
      else if (localType == String.class)
      {
        if (this.lexer.token() == 4)
        {
          localObject = this.lexer.stringVal();
          this.lexer.nextToken(16);
        }
        else
        {
          localObject = TypeUtils.cast(parse(), localType, this.config);
        }
      }
      else
      {
        boolean bool2 = false;
        ArrayList localArrayList = null;
        localObject = localArrayList;
        boolean bool1 = bool2;
        if (i == paramArrayOfType.length - 1)
        {
          localObject = localArrayList;
          bool1 = bool2;
          if ((localType instanceof Class))
          {
            localObject = (Class)localType;
            bool1 = ((Class)localObject).isArray();
            localObject = ((Class)localObject).getComponentType();
          }
        }
        if ((bool1) && (this.lexer.token() != 14))
        {
          localArrayList = new ArrayList();
          localObject = this.config.getDeserializer((Type)localObject);
          int j = ((ObjectDeserializer)localObject).getFastMatchToken();
          if (this.lexer.token() != 15)
          {
            while (true)
            {
              localArrayList.add(((ObjectDeserializer)localObject).deserialze(this, localType, null));
              if (this.lexer.token() != 16)
                break;
              this.lexer.nextToken(j);
            }
            if (this.lexer.token() != 15);
          }
          else
          {
            localObject = TypeUtils.cast(localArrayList, localType, this.config);
            continue;
          }
          throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
        }
        else
        {
          localObject = this.config.getDeserializer(localType).deserialze(this, localType, null);
        }
      }
    }
    if (this.lexer.token() != 16)
      throw new JSONException("syntax error :" + JSONToken.name(this.lexer.token()));
    if (i == paramArrayOfType.length - 1)
      this.lexer.nextToken(15);
    while (true)
    {
      i += 1;
      break;
      this.lexer.nextToken(2);
    }
    label710: this.lexer.nextToken(16);
    return arrayOfObject;
  }

  public Object parseArrayWithType(Type paramType)
  {
    if (this.lexer.token() == 8)
    {
      this.lexer.nextToken();
      return null;
    }
    Object localObject1 = ((ParameterizedType)paramType).getActualTypeArguments();
    if (localObject1.length != 1)
      throw new JSONException("not support type " + paramType);
    localObject1 = localObject1[0];
    if ((localObject1 instanceof Class))
    {
      paramType = new ArrayList();
      parseArray((Class)localObject1, paramType);
      return paramType;
    }
    Object localObject2;
    if ((localObject1 instanceof WildcardType))
    {
      localObject2 = (WildcardType)localObject1;
      localObject1 = localObject2.getUpperBounds()[0];
      if (Object.class.equals(localObject1))
      {
        if (((WildcardType)localObject2).getLowerBounds().length == 0)
          return parse();
        throw new JSONException("not support type : " + paramType);
      }
      paramType = new ArrayList();
      parseArray((Class)localObject1, paramType);
      return paramType;
    }
    if ((localObject1 instanceof TypeVariable))
    {
      localObject2 = (TypeVariable)localObject1;
      Type[] arrayOfType = ((TypeVariable)localObject2).getBounds();
      if (arrayOfType.length != 1)
        throw new JSONException("not support : " + localObject2);
      localObject2 = arrayOfType[0];
      if ((localObject2 instanceof Class))
      {
        paramType = new ArrayList();
        parseArray((Class)localObject2, paramType);
        return paramType;
      }
    }
    if ((localObject1 instanceof ParameterizedType))
    {
      paramType = (ParameterizedType)localObject1;
      localObject1 = new ArrayList();
      parseArray(paramType, (Collection)localObject1);
      return localObject1;
    }
    throw new JSONException("TODO : " + paramType);
  }

  public Object parseKey()
  {
    if (this.lexer.token() == 18)
    {
      String str = this.lexer.stringVal();
      this.lexer.nextToken(16);
      return str;
    }
    return parse(null);
  }

  public JSONObject parseObject()
  {
    JSONObject localJSONObject = new JSONObject();
    parseObject(localJSONObject);
    return localJSONObject;
  }

  public <T> T parseObject(Class<T> paramClass)
  {
    return parseObject(paramClass);
  }

  public <T> T parseObject(Type paramType)
  {
    if (this.lexer.token() == 8)
    {
      this.lexer.nextToken();
      return null;
    }
    ObjectDeserializer localObjectDeserializer = this.config.getDeserializer(paramType);
    try
    {
      paramType = localObjectDeserializer.deserialze(this, paramType, null);
      return paramType;
    }
    catch (JSONException paramType)
    {
      throw paramType;
    }
    catch (Throwable paramType)
    {
    }
    throw new JSONException(paramType.getMessage(), paramType);
  }

  public Object parseObject(Map paramMap)
  {
    return parseObject(paramMap, null);
  }

  public final Object parseObject(Map paramMap, Object paramObject)
  {
    JSONLexer localJSONLexer = this.lexer;
    if ((localJSONLexer.token() != 12) && (localJSONLexer.token() != 16))
      throw new JSONException("syntax error, expect {, actual " + localJSONLexer.tokenName());
    ParseContext localParseContext = getContext();
    int j = 0;
    int i;
    int k;
    Object localObject2;
    Object localObject1;
    try
    {
      localJSONLexer.skipWhitespace();
      i = localJSONLexer.getCurrent();
      k = i;
      if (isEnabled(Feature.AllowArbitraryCommas))
        while (true)
        {
          k = i;
          if (i != 44)
            break;
          localJSONLexer.next();
          localJSONLexer.skipWhitespace();
          i = localJSONLexer.getCurrent();
        }
      i = 0;
      if (k == 34)
      {
        localObject2 = localJSONLexer.scanSymbol(this.symbolTable, '"');
        localJSONLexer.skipWhitespace();
        localObject1 = localObject2;
        if (localJSONLexer.getCurrent() == ':')
          break label536;
        throw new JSONException("expect ':' at " + localJSONLexer.pos() + ", name " + localObject2);
      }
    }
    finally
    {
      setContext(localParseContext);
    }
    if (k == 125)
    {
      localJSONLexer.next();
      localJSONLexer.resetStringPosition();
      localJSONLexer.nextToken();
      setContext(localParseContext);
      return paramMap;
    }
    if (k == 39)
    {
      if (!isEnabled(Feature.AllowSingleQuotes))
        throw new JSONException("syntax error");
      localObject1 = localJSONLexer.scanSymbol(this.symbolTable, '\'');
      localJSONLexer.skipWhitespace();
      if (localJSONLexer.getCurrent() != ':')
        throw new JSONException("expect ':' at " + localJSONLexer.pos());
    }
    else
    {
      if (k == 26)
        throw new JSONException("syntax error");
      if (k != 44)
        break label1956;
      throw new JSONException("syntax error");
      label412: localJSONLexer.resetStringPosition();
      localJSONLexer.scanNumber();
      if (localJSONLexer.token() == 2);
      for (localObject2 = localJSONLexer.integerValue(); ; localObject2 = localJSONLexer.decimalValue(true))
      {
        localObject1 = localObject2;
        if (localJSONLexer.getCurrent() == ':')
          break;
        throw new JSONException("expect ':' at " + localJSONLexer.pos() + ", name " + localObject2);
      }
      label520: localJSONLexer.nextToken();
      localObject1 = parse();
      i = 1;
    }
    label536: label622: char c;
    do
    {
      if (i == 0)
      {
        localJSONLexer.next();
        localJSONLexer.skipWhitespace();
      }
      k = localJSONLexer.getCurrent();
      localJSONLexer.resetStringPosition();
      if (localObject1 != JSON.DEFAULT_TYPE_KEY)
        break label903;
      localObject2 = localJSONLexer.scanSymbol(this.symbolTable, '"');
      localObject1 = TypeUtils.loadClass((String)localObject2);
      if (localObject1 != null)
        break label720;
      paramMap.put(JSON.DEFAULT_TYPE_KEY, localObject2);
      break;
      if (!isEnabled(Feature.AllowUnQuotedFieldNames))
        throw new JSONException("syntax error");
      localObject1 = localJSONLexer.scanSymbolUnQuoted(this.symbolTable);
      localJSONLexer.skipWhitespace();
      c = localJSONLexer.getCurrent();
    }
    while (c == ':');
    throw new JSONException("expect ':' at " + localJSONLexer.pos() + ", actual " + c);
    label720: localJSONLexer.nextToken(16);
    if (localJSONLexer.token() == 13)
    {
      localJSONLexer.nextToken(16);
      paramMap = null;
      try
      {
        paramObject = this.config.getDeserializer((Type)localObject1);
        if ((paramObject instanceof ASMJavaBeanDeserializer))
        {
          paramMap = ((ASMJavaBeanDeserializer)paramObject).createInstance(this, (Type)localObject1);
          paramObject = paramMap;
          if (paramMap == null)
            if (localObject1 != Cloneable.class)
              break label831;
        }
        label831: for (paramObject = new HashMap(); ; paramObject = ((Class)localObject1).newInstance())
        {
          setContext(localParseContext);
          return paramObject;
          if (!(paramObject instanceof JavaBeanDeserializer))
            break;
          paramMap = ((JavaBeanDeserializer)paramObject).createInstance(this, (Type)localObject1);
          break;
        }
      }
      catch (Exception paramMap)
      {
        throw new JSONException("create instance error", paramMap);
      }
    }
    setResolveStatus(2);
    if ((this.context != null) && (!(paramObject instanceof Integer)))
      popContext();
    paramMap = this.config.getDeserializer((Type)localObject1).deserialze(this, (Type)localObject1, paramObject);
    setContext(localParseContext);
    return paramMap;
    label903: label978: Object localObject3;
    if (localObject1 == "$ref")
    {
      localJSONLexer.nextToken(4);
      if (localJSONLexer.token() == 4)
      {
        localObject1 = localJSONLexer.stringVal();
        localJSONLexer.nextToken(13);
        paramObject = null;
        paramMap = null;
        if ("@".equals(localObject1))
        {
          if (getContext() == null)
            break label2003;
          paramMap = getContext().getObject();
          if (localJSONLexer.token() != 13)
            throw new JSONException("syntax error");
        }
        else
        {
          if ("..".equals(localObject1))
          {
            paramObject = localParseContext.getParentContext();
            if (paramObject.getObject() != null)
            {
              paramMap = paramObject.getObject();
              break label1997;
            }
            addResolveTask(new ResolveTask(paramObject, (String)localObject1));
            setResolveStatus(1);
            break label1997;
          }
          if ("$".equals(localObject1))
          {
            for (paramMap = localParseContext; paramMap.getParentContext() != null; paramMap = paramMap.getParentContext());
            if (paramMap.getObject() != null)
            {
              paramMap = paramMap.getObject();
              break label2000;
            }
            addResolveTask(new ResolveTask(paramMap, (String)localObject1));
            setResolveStatus(1);
            paramMap = paramObject;
            break label2000;
          }
          addResolveTask(new ResolveTask(localParseContext, (String)localObject1));
          setResolveStatus(1);
          break label2003;
        }
        localJSONLexer.nextToken(16);
        setContext(localParseContext);
        return paramMap;
      }
      else
      {
        throw new JSONException("illegal ref, " + JSONToken.name(localJSONLexer.token()));
      }
    }
    else
    {
      i = j;
      if (j == 0)
      {
        setContext(paramMap, paramObject);
        j = 1;
        i = j;
        if (this.context != null)
        {
          i = j;
          if (!(paramObject instanceof Integer))
          {
            popContext();
            i = j;
          }
        }
      }
      localObject2 = localObject1;
      if (paramMap.getClass() == JSONObject.class)
        if (localObject1 != null)
          break label1404;
      label1404: for (localObject2 = "null"; ; localObject2 = localObject1.toString())
      {
        if (k != 34)
          break label2008;
        localJSONLexer.scanString();
        String str = localJSONLexer.stringVal();
        localObject1 = str;
        localObject3 = localObject1;
        if (localJSONLexer.isEnabled(Feature.AllowISO8601DateFormat))
        {
          localObject3 = new JSONScanner(str);
          if (((JSONScanner)localObject3).scanISO8601DateIfMatch())
            localObject1 = ((JSONScanner)localObject3).getCalendar().getTime();
          ((JSONScanner)localObject3).close();
          localObject3 = localObject1;
        }
        paramMap.put(localObject2, localObject3);
        localJSONLexer.skipWhitespace();
        j = localJSONLexer.getCurrent();
        if (j != 44)
          break label1867;
        localJSONLexer.next();
        j = i;
        break;
      }
    }
    label1414: label1956: 
    while (true)
    {
      localJSONLexer.scanNumber();
      if (localJSONLexer.token() == 2);
      for (localObject1 = localJSONLexer.integerValue(); ; localObject1 = localJSONLexer.numberValue())
      {
        paramMap.put(localObject2, localObject1);
        break;
      }
      label1867: label1997: label2000: label2003: label2008: 
      do
      {
        if (k == 91)
        {
          localJSONLexer.nextToken();
          localObject1 = new JSONArray();
          parseArray((Collection)localObject1, localObject2);
          paramMap.put(localObject2, localObject1);
          if (localJSONLexer.token() == 13)
          {
            localJSONLexer.nextToken();
            setContext(localParseContext);
            return paramMap;
          }
          j = i;
          if (localJSONLexer.token() == 16)
            break;
          throw new JSONException("syntax error");
        }
        if (k == 123)
        {
          localJSONLexer.nextToken();
          localObject1 = parseObject(new JSONObject(), localObject2);
          checkMapResolve(paramMap, localObject2.toString());
          if (paramMap.getClass() == JSONObject.class)
            paramMap.put(localObject2.toString(), localObject1);
          while (true)
          {
            setContext(localParseContext, localObject1, localObject2);
            if (localJSONLexer.token() != 13)
              break;
            localJSONLexer.nextToken();
            setContext(localParseContext);
            setContext(localParseContext);
            return paramMap;
            paramMap.put(localObject2, localObject1);
          }
          j = i;
          if (localJSONLexer.token() == 16)
            break;
          throw new JSONException("syntax error, " + localJSONLexer.tokenName());
        }
        localJSONLexer.nextToken();
        localObject3 = parse();
        localObject1 = localObject2;
        if (paramMap.getClass() == JSONObject.class)
          localObject1 = localObject2.toString();
        paramMap.put(localObject1, localObject3);
        if (localJSONLexer.token() == 13)
        {
          localJSONLexer.nextToken();
          setContext(localParseContext);
          return paramMap;
        }
        j = i;
        if (localJSONLexer.token() == 16)
          break;
        throw new JSONException("syntax error, position at " + localJSONLexer.pos() + ", name " + localObject1);
        if (j == 125)
        {
          localJSONLexer.next();
          localJSONLexer.resetStringPosition();
          localJSONLexer.nextToken();
          setContext(paramMap, paramObject);
          setContext(localParseContext);
          return paramMap;
        }
        throw new JSONException("syntax error, position at " + localJSONLexer.pos() + ", name " + localObject2);
        if (((k >= 48) && (k <= 57)) || (k == 45))
          break label412;
        if (k == 123)
          break label520;
        if (k != 91)
          break label622;
        break label520;
        break label978;
        break label978;
        paramMap = null;
        break label978;
        if ((k >= 48) && (k <= 57))
          break label1414;
      }
      while (k != 45);
    }
  }

  public void parseObject(Object paramObject)
  {
    Class localClass = paramObject.getClass();
    Map localMap = this.config.getFieldDeserializers(localClass);
    if ((this.lexer.token() != 12) && (this.lexer.token() != 16))
    {
      throw new JSONException("syntax error, expect {, actual " + this.lexer.tokenName());
      if ((this.lexer.token() != 16) || (!isEnabled(Feature.AllowArbitraryCommas)))
        break label147;
    }
    label147: FieldDeserializer localFieldDeserializer;
    do
    {
      localObject1 = this.lexer.scanSymbol(this.symbolTable);
      if (localObject1 == null)
      {
        if (this.lexer.token() != 13)
          break;
        this.lexer.nextToken(16);
        return;
      }
      localFieldDeserializer = (FieldDeserializer)localMap.get(localObject1);
      if (localFieldDeserializer != null)
        break label254;
      if (!isEnabled(Feature.IgnoreNotMatch))
        throw new JSONException("setter not found, class " + localClass.getName() + ", property " + (String)localObject1);
      this.lexer.nextTokenWithColon();
      parse();
    }
    while (this.lexer.token() != 13);
    this.lexer.nextToken();
    return;
    label254: Object localObject2 = localFieldDeserializer.getFieldClass();
    Object localObject1 = localFieldDeserializer.getFieldType();
    if (localObject2 == Integer.TYPE)
    {
      this.lexer.nextTokenWithColon(2);
      localObject1 = IntegerDeserializer.instance.deserialze(this, (Type)localObject1, null);
    }
    while (true)
    {
      localFieldDeserializer.setValue(paramObject, localObject1);
      if ((this.lexer.token() == 16) || (this.lexer.token() != 13))
        break;
      this.lexer.nextToken(16);
      return;
      if (localObject2 == String.class)
      {
        this.lexer.nextTokenWithColon(4);
        localObject1 = StringDeserializer.deserialze(this);
      }
      else if (localObject2 == Long.TYPE)
      {
        this.lexer.nextTokenWithColon(2);
        localObject1 = LongDeserializer.instance.deserialze(this, (Type)localObject1, null);
      }
      else
      {
        localObject2 = this.config.getDeserializer((Class)localObject2, (Type)localObject1);
        this.lexer.nextTokenWithColon(((ObjectDeserializer)localObject2).getFastMatchToken());
        localObject1 = ((ObjectDeserializer)localObject2).deserialze(this, (Type)localObject1, null);
      }
    }
  }

  public void popContext()
  {
    if (isEnabled(Feature.DisableCircularReferenceDetect))
      return;
    this.context = this.context.getParentContext();
    this.contextArray[(this.contextArrayIndex - 1)] = null;
    this.contextArrayIndex -= 1;
  }

  public void setConfig(ParserConfig paramParserConfig)
  {
    this.config = paramParserConfig;
  }

  public ParseContext setContext(ParseContext paramParseContext, Object paramObject1, Object paramObject2)
  {
    if (isEnabled(Feature.DisableCircularReferenceDetect))
      return null;
    this.context = new ParseContext(paramParseContext, paramObject1, paramObject2);
    addContext(this.context);
    return this.context;
  }

  public ParseContext setContext(Object paramObject1, Object paramObject2)
  {
    if (isEnabled(Feature.DisableCircularReferenceDetect))
      return null;
    return setContext(this.context, paramObject1, paramObject2);
  }

  public void setContext(ParseContext paramParseContext)
  {
    if (isEnabled(Feature.DisableCircularReferenceDetect))
      return;
    this.context = paramParseContext;
  }

  public void setDateFomrat(DateFormat paramDateFormat)
  {
    this.dateFormat = paramDateFormat;
  }

  public void setDateFormat(String paramString)
  {
    this.dateFormatPattern = paramString;
    this.dateFormat = null;
  }

  public void setResolveStatus(int paramInt)
  {
    this.resolveStatus = paramInt;
  }

  public static class ResolveTask
  {
    private final ParseContext context;
    private FieldDeserializer fieldDeserializer;
    private ParseContext ownerContext;
    private final String referenceValue;

    public ResolveTask(ParseContext paramParseContext, String paramString)
    {
      this.context = paramParseContext;
      this.referenceValue = paramString;
    }

    public ParseContext getContext()
    {
      return this.context;
    }

    public FieldDeserializer getFieldDeserializer()
    {
      return this.fieldDeserializer;
    }

    public ParseContext getOwnerContext()
    {
      return this.ownerContext;
    }

    public String getReferenceValue()
    {
      return this.referenceValue;
    }

    public void setFieldDeserializer(FieldDeserializer paramFieldDeserializer)
    {
      this.fieldDeserializer = paramFieldDeserializer;
    }

    public void setOwnerContext(ParseContext paramParseContext)
    {
      this.ownerContext = paramParseContext;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.parser.DefaultJSONParser
 * JD-Core Version:    0.6.2
 */