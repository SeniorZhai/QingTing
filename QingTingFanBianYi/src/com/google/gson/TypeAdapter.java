package com.google.gson;

import com.google.gson.internal.bind.JsonTreeReader;
import com.google.gson.internal.bind.JsonTreeWriter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

public abstract class TypeAdapter<T>
{
  final T fromJson(Reader paramReader)
    throws IOException
  {
    paramReader = new JsonReader(paramReader);
    paramReader.setLenient(true);
    return read(paramReader);
  }

  final T fromJson(String paramString)
    throws IOException
  {
    return fromJson(new StringReader(paramString));
  }

  final T fromJsonTree(JsonElement paramJsonElement)
  {
    try
    {
      paramJsonElement = new JsonTreeReader(paramJsonElement);
      paramJsonElement.setLenient(true);
      paramJsonElement = read(paramJsonElement);
      return paramJsonElement;
    }
    catch (IOException paramJsonElement)
    {
    }
    throw new JsonIOException(paramJsonElement);
  }

  public final TypeAdapter<T> nullSafe()
  {
    return new TypeAdapter()
    {
      public T read(JsonReader paramAnonymousJsonReader)
        throws IOException
      {
        if (paramAnonymousJsonReader.peek() == JsonToken.NULL)
        {
          paramAnonymousJsonReader.nextNull();
          return null;
        }
        return TypeAdapter.this.read(paramAnonymousJsonReader);
      }

      public void write(JsonWriter paramAnonymousJsonWriter, T paramAnonymousT)
        throws IOException
      {
        if (paramAnonymousT == null)
        {
          paramAnonymousJsonWriter.nullValue();
          return;
        }
        TypeAdapter.this.write(paramAnonymousJsonWriter, paramAnonymousT);
      }
    };
  }

  public abstract T read(JsonReader paramJsonReader)
    throws IOException;

  final String toJson(T paramT)
    throws IOException
  {
    StringWriter localStringWriter = new StringWriter();
    toJson(localStringWriter, paramT);
    return localStringWriter.toString();
  }

  final void toJson(Writer paramWriter, T paramT)
    throws IOException
  {
    write(new JsonWriter(paramWriter), paramT);
  }

  final JsonElement toJsonTree(T paramT)
  {
    try
    {
      JsonTreeWriter localJsonTreeWriter = new JsonTreeWriter();
      localJsonTreeWriter.setLenient(true);
      write(localJsonTreeWriter, paramT);
      paramT = localJsonTreeWriter.get();
      return paramT;
    }
    catch (IOException paramT)
    {
    }
    throw new JsonIOException(paramT);
  }

  public abstract void write(JsonWriter paramJsonWriter, T paramT)
    throws IOException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.TypeAdapter
 * JD-Core Version:    0.6.2
 */