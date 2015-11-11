package com.alibaba.fastjson;

import com.alibaba.fastjson.parser.DefaultJSONParser;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.JSONLexer;
import com.alibaba.fastjson.parser.JSONReaderScanner;
import com.alibaba.fastjson.util.IOUtils;
import com.alibaba.fastjson.util.TypeUtils;
import java.io.Closeable;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.Map;

public class JSONReader
  implements Closeable
{
  private JSONStreamContext context;
  private final DefaultJSONParser parser;

  public JSONReader(DefaultJSONParser paramDefaultJSONParser)
  {
    this.parser = paramDefaultJSONParser;
  }

  public JSONReader(JSONLexer paramJSONLexer)
  {
    this(new DefaultJSONParser(paramJSONLexer));
  }

  public JSONReader(Reader paramReader)
  {
    this(new JSONReaderScanner(paramReader));
  }

  private void endStructure()
  {
    this.context = this.context.getParent();
    if (this.context == null);
    while (true)
    {
      return;
      int j = this.context.getState();
      int i = -1;
      switch (j)
      {
      default:
      case 1002:
      case 1004:
      case 1001:
      case 1003:
      }
      while (i != -1)
      {
        this.context.setState(i);
        return;
        i = 1003;
        continue;
        i = 1005;
        continue;
        i = 1002;
      }
    }
  }

  private void readAfter()
  {
    int j = this.context.getState();
    int i = -1;
    switch (j)
    {
    default:
      throw new JSONException("illegal state : " + j);
    case 1001:
      i = 1002;
    case 1005:
    case 1002:
    case 1003:
    case 1004:
    }
    while (true)
    {
      if (i != -1)
        this.context.setState(i);
      return;
      i = 1003;
      continue;
      i = 1002;
      continue;
      i = 1005;
    }
  }

  private void readBefore()
  {
    int i = this.context.getState();
    switch (i)
    {
    default:
      throw new JSONException("illegal state : " + i);
    case 1002:
      this.parser.accept(17);
    case 1001:
    case 1004:
      return;
    case 1003:
      this.parser.accept(16, 18);
      return;
    case 1005:
    }
    this.parser.accept(16);
  }

  private void startStructure()
  {
    switch (this.context.getState())
    {
    default:
      throw new JSONException("illegal state : " + this.context.getState());
    case 1002:
      this.parser.accept(17);
    case 1001:
    case 1004:
      return;
    case 1003:
    case 1005:
    }
    this.parser.accept(16);
  }

  public void close()
  {
    IOUtils.close(this.parser);
  }

  public void config(Feature paramFeature, boolean paramBoolean)
  {
    this.parser.config(paramFeature, paramBoolean);
  }

  public void endArray()
  {
    this.parser.accept(15);
    endStructure();
  }

  public void endObject()
  {
    this.parser.accept(13);
    endStructure();
  }

  public boolean hasNext()
  {
    if (this.context == null)
      throw new JSONException("context is null");
    int i = this.parser.getLexer().token();
    int j = this.context.getState();
    switch (j)
    {
    case 1002:
    default:
      throw new JSONException("illegal state : " + j);
    case 1004:
    case 1005:
      if (i == 15)
        break;
    case 1001:
    case 1003:
    }
    do
    {
      return true;
      return false;
    }
    while (i != 13);
    return false;
  }

  public Integer readInteger()
  {
    Object localObject;
    if (this.context == null)
      localObject = this.parser.parse();
    while (true)
    {
      return TypeUtils.castToInt(localObject);
      readBefore();
      localObject = this.parser.parse();
      readAfter();
    }
  }

  public Long readLong()
  {
    Object localObject;
    if (this.context == null)
      localObject = this.parser.parse();
    while (true)
    {
      return TypeUtils.castToLong(localObject);
      readBefore();
      localObject = this.parser.parse();
      readAfter();
    }
  }

  public Object readObject()
  {
    if (this.context == null)
      return this.parser.parse();
    readBefore();
    switch (this.context.getState())
    {
    case 1002:
    default:
    case 1001:
    case 1003:
    }
    for (Object localObject = this.parser.parse(); ; localObject = this.parser.parseKey())
    {
      readAfter();
      return localObject;
    }
  }

  public <T> T readObject(TypeReference<T> paramTypeReference)
  {
    return readObject(paramTypeReference.getType());
  }

  public <T> T readObject(Class<T> paramClass)
  {
    if (this.context == null)
      return this.parser.parseObject(paramClass);
    readBefore();
    paramClass = this.parser.parseObject(paramClass);
    readAfter();
    return paramClass;
  }

  public <T> T readObject(Type paramType)
  {
    if (this.context == null)
      return this.parser.parseObject(paramType);
    readBefore();
    paramType = this.parser.parseObject(paramType);
    readAfter();
    return paramType;
  }

  public Object readObject(Map paramMap)
  {
    if (this.context == null)
      return this.parser.parseObject(paramMap);
    readBefore();
    paramMap = this.parser.parseObject(paramMap);
    readAfter();
    return paramMap;
  }

  public void readObject(Object paramObject)
  {
    if (this.context == null)
    {
      this.parser.parseObject(paramObject);
      return;
    }
    readBefore();
    this.parser.parseObject(paramObject);
    readAfter();
  }

  public String readString()
  {
    Object localObject;
    if (this.context == null)
      localObject = this.parser.parse();
    while (true)
    {
      return TypeUtils.castToString(localObject);
      readBefore();
      localObject = this.parser.parse();
      readAfter();
    }
  }

  public void startArray()
  {
    if (this.context == null);
    for (this.context = new JSONStreamContext(null, 1004); ; this.context = new JSONStreamContext(this.context, 1004))
    {
      this.parser.accept(14);
      return;
      startStructure();
    }
  }

  public void startObject()
  {
    if (this.context == null);
    for (this.context = new JSONStreamContext(null, 1001); ; this.context = new JSONStreamContext(this.context, 1001))
    {
      this.parser.accept(12, 18);
      return;
      startStructure();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.JSONReader
 * JD-Core Version:    0.6.2
 */