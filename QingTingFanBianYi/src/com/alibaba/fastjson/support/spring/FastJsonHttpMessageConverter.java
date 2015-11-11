package com.alibaba.fastjson.support.spring;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

public class FastJsonHttpMessageConverter extends AbstractHttpMessageConverter<Object>
{
  public static final Charset UTF8 = Charset.forName("UTF-8");
  private Charset charset = UTF8;
  private SerializerFeature[] features = new SerializerFeature[0];

  public FastJsonHttpMessageConverter()
  {
    super(new MediaType[] { new MediaType("application", "json", UTF8), new MediaType("application", "*+json", UTF8) });
  }

  public Charset getCharset()
  {
    return this.charset;
  }

  public SerializerFeature[] getFeatures()
  {
    return this.features;
  }

  protected Object readInternal(Class<? extends Object> paramClass, HttpInputMessage paramHttpInputMessage)
    throws IOException, HttpMessageNotReadableException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramHttpInputMessage = paramHttpInputMessage.getBody();
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramHttpInputMessage.read(arrayOfByte);
      if (i == -1)
      {
        paramHttpInputMessage = localByteArrayOutputStream.toByteArray();
        return JSON.parseObject(paramHttpInputMessage, 0, paramHttpInputMessage.length, this.charset.newDecoder(), paramClass, new Feature[0]);
      }
      if (i > 0)
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }

  public void setCharset(Charset paramCharset)
  {
    this.charset = paramCharset;
  }

  public void setFeatures(SerializerFeature[] paramArrayOfSerializerFeature)
  {
    this.features = paramArrayOfSerializerFeature;
  }

  protected boolean supports(Class<?> paramClass)
  {
    return true;
  }

  protected void writeInternal(Object paramObject, HttpOutputMessage paramHttpOutputMessage)
    throws IOException, HttpMessageNotWritableException
  {
    paramHttpOutputMessage.getBody().write(JSON.toJSONString(paramObject, this.features).getBytes(this.charset));
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter
 * JD-Core Version:    0.6.2
 */