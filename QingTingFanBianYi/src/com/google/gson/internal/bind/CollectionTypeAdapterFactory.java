package com.google.gson.internal.bind;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;

public final class CollectionTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;

  public CollectionTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor)
  {
    this.constructorConstructor = paramConstructorConstructor;
  }

  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Type localType = paramTypeToken.getType();
    Class localClass = paramTypeToken.getRawType();
    if (!Collection.class.isAssignableFrom(localClass))
      return null;
    localType = .Gson.Types.getCollectionElementType(localType, localClass);
    return new Adapter(paramGson, localType, paramGson.getAdapter(TypeToken.get(localType)), this.constructorConstructor.getConstructor(paramTypeToken));
  }

  private final class Adapter<E> extends TypeAdapter<Collection<E>>
  {
    private final ObjectConstructor<? extends Collection<E>> constructor;
    private final TypeAdapter<E> elementTypeAdapter;

    public Adapter(Type paramTypeAdapter, TypeAdapter<E> paramObjectConstructor, ObjectConstructor<? extends Collection<E>> arg4)
    {
      TypeAdapter localTypeAdapter;
      this.elementTypeAdapter = new TypeAdapterRuntimeTypeWrapper(paramTypeAdapter, localTypeAdapter, paramObjectConstructor);
      Object localObject;
      this.constructor = localObject;
    }

    public Collection<E> read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Collection localCollection = (Collection)this.constructor.construct();
      paramJsonReader.beginArray();
      while (paramJsonReader.hasNext())
        localCollection.add(this.elementTypeAdapter.read(paramJsonReader));
      paramJsonReader.endArray();
      return localCollection;
    }

    public void write(JsonWriter paramJsonWriter, Collection<E> paramCollection)
      throws IOException
    {
      if (paramCollection == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginArray();
      paramCollection = paramCollection.iterator();
      while (paramCollection.hasNext())
      {
        Object localObject = paramCollection.next();
        this.elementTypeAdapter.write(paramJsonWriter, localObject);
      }
      paramJsonWriter.endArray();
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.bind.CollectionTypeAdapterFactory
 * JD-Core Version:    0.6.2
 */