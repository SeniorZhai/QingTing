package com.alibaba.fastjson;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

public class JSONWriter
  implements Closeable, Flushable
{
  private JSONStreamContext context;
  private JSONSerializer serializer;
  private SerializeWriter writer;

  public JSONWriter(Writer paramWriter)
  {
    this.writer = new SerializeWriter(paramWriter);
    this.serializer = new JSONSerializer(this.writer);
  }

  private void afterWriter()
  {
    if (this.context == null)
      return;
    int k = this.context.getState();
    int j = -1;
    int i = j;
    switch (k)
    {
    default:
      i = j;
    case 1005:
    case 1002:
    case 1001:
    case 1003:
    case 1004:
    }
    while (i != -1)
    {
      this.context.setState(i);
      return;
      i = 1003;
      continue;
      i = 1002;
      continue;
      i = 1005;
    }
  }

  private void beforeWrite()
  {
    if (this.context == null)
      return;
    switch (this.context.getState())
    {
    case 1001:
    case 1004:
    default:
      return;
    case 1002:
      this.writer.write(':');
      return;
    case 1003:
      this.writer.write(',');
      return;
    case 1005:
    }
    this.writer.write(',');
  }

  private void beginStructure()
  {
    int i = this.context.getState();
    switch (i)
    {
    case 1003:
    default:
      throw new JSONException("illegal state : " + i);
    case 1002:
      this.writer.write(':');
    case 1001:
    case 1004:
      return;
    case 1005:
    }
    this.writer.write(',');
  }

  private void endStructure()
  {
    this.context = this.context.getParent();
    if (this.context == null)
      return;
    int k = this.context.getState();
    int j = -1;
    int i = j;
    switch (k)
    {
    default:
      i = j;
    case 1003:
    case 1005:
    case 1002:
    case 1004:
    case 1001:
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

  public void close()
    throws IOException
  {
    this.writer.close();
  }

  public void config(SerializerFeature paramSerializerFeature, boolean paramBoolean)
  {
    this.writer.config(paramSerializerFeature, paramBoolean);
  }

  public void endArray()
  {
    this.writer.write(']');
    endStructure();
  }

  public void endObject()
  {
    this.writer.write('}');
    endStructure();
  }

  public void flush()
    throws IOException
  {
    this.writer.flush();
  }

  public void startArray()
  {
    if (this.context != null)
      beginStructure();
    this.context = new JSONStreamContext(this.context, 1004);
    this.writer.write('[');
  }

  public void startObject()
  {
    if (this.context != null)
      beginStructure();
    this.context = new JSONStreamContext(this.context, 1001);
    this.writer.write('{');
  }

  @Deprecated
  public void writeEndArray()
  {
    endArray();
  }

  @Deprecated
  public void writeEndObject()
  {
    endObject();
  }

  public void writeKey(String paramString)
  {
    writeObject(paramString);
  }

  public void writeObject(Object paramObject)
  {
    beforeWrite();
    this.serializer.write(paramObject);
    afterWriter();
  }

  public void writeObject(String paramString)
  {
    beforeWrite();
    this.serializer.write(paramString);
    afterWriter();
  }

  @Deprecated
  public void writeStartArray()
  {
    startArray();
  }

  @Deprecated
  public void writeStartObject()
  {
    startObject();
  }

  public void writeValue(Object paramObject)
  {
    writeObject(paramObject);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.JSONWriter
 * JD-Core Version:    0.6.2
 */