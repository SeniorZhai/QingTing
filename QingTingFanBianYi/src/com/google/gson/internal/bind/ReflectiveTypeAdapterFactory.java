package com.google.gson.internal.bind;

import com.google.gson.FieldNamingStrategy;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.annotations.SerializedName;
import com.google.gson.internal..Gson.Types;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.Excluder;
import com.google.gson.internal.ObjectConstructor;
import com.google.gson.internal.Primitives;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public final class ReflectiveTypeAdapterFactory
  implements TypeAdapterFactory
{
  private final ConstructorConstructor constructorConstructor;
  private final Excluder excluder;
  private final FieldNamingStrategy fieldNamingPolicy;

  public ReflectiveTypeAdapterFactory(ConstructorConstructor paramConstructorConstructor, FieldNamingStrategy paramFieldNamingStrategy, Excluder paramExcluder)
  {
    this.constructorConstructor = paramConstructorConstructor;
    this.fieldNamingPolicy = paramFieldNamingStrategy;
    this.excluder = paramExcluder;
  }

  private BoundField createBoundField(final Gson paramGson, final Field paramField, String paramString, final TypeToken<?> paramTypeToken, boolean paramBoolean1, boolean paramBoolean2)
  {
    return new BoundField(paramString, paramBoolean1, paramBoolean2)
    {
      final TypeAdapter<?> typeAdapter = paramGson.getAdapter(paramTypeToken);

      void read(JsonReader paramAnonymousJsonReader, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousJsonReader = this.typeAdapter.read(paramAnonymousJsonReader);
        if ((paramAnonymousJsonReader != null) || (!this.val$isPrimitive))
          paramField.set(paramAnonymousObject, paramAnonymousJsonReader);
      }

      void write(JsonWriter paramAnonymousJsonWriter, Object paramAnonymousObject)
        throws IOException, IllegalAccessException
      {
        paramAnonymousObject = paramField.get(paramAnonymousObject);
        new TypeAdapterRuntimeTypeWrapper(paramGson, this.typeAdapter, paramTypeToken.getType()).write(paramAnonymousJsonWriter, paramAnonymousObject);
      }
    };
  }

  private Map<String, BoundField> getBoundFields(Gson paramGson, TypeToken<?> paramTypeToken, Class<?> paramClass)
  {
    LinkedHashMap localLinkedHashMap = new LinkedHashMap();
    if (paramClass.isInterface());
    while (true)
    {
      return localLinkedHashMap;
      Type localType1 = paramTypeToken.getType();
      while (paramClass != Object.class)
      {
        Field[] arrayOfField = paramClass.getDeclaredFields();
        int j = arrayOfField.length;
        int i = 0;
        if (i < j)
        {
          Object localObject = arrayOfField[i];
          boolean bool1 = excludeField((Field)localObject, true);
          boolean bool2 = excludeField((Field)localObject, false);
          if ((!bool1) && (!bool2));
          do
          {
            i += 1;
            break;
            ((Field)localObject).setAccessible(true);
            Type localType2 = .Gson.Types.resolve(paramTypeToken.getType(), paramClass, ((Field)localObject).getGenericType());
            localObject = createBoundField(paramGson, (Field)localObject, getFieldName((Field)localObject), TypeToken.get(localType2), bool1, bool2);
            localObject = (BoundField)localLinkedHashMap.put(((BoundField)localObject).name, localObject);
          }
          while (localObject == null);
          throw new IllegalArgumentException(localType1 + " declares multiple JSON fields named " + ((BoundField)localObject).name);
        }
        paramTypeToken = TypeToken.get(.Gson.Types.resolve(paramTypeToken.getType(), paramClass, paramClass.getGenericSuperclass()));
        paramClass = paramTypeToken.getRawType();
      }
    }
  }

  private String getFieldName(Field paramField)
  {
    SerializedName localSerializedName = (SerializedName)paramField.getAnnotation(SerializedName.class);
    if (localSerializedName == null)
      return this.fieldNamingPolicy.translateName(paramField);
    return localSerializedName.value();
  }

  public <T> TypeAdapter<T> create(Gson paramGson, TypeToken<T> paramTypeToken)
  {
    Class localClass = paramTypeToken.getRawType();
    if (!Object.class.isAssignableFrom(localClass))
      return null;
    return new Adapter(this.constructorConstructor.getConstructor(paramTypeToken), getBoundFields(paramGson, paramTypeToken, localClass), null);
  }

  public boolean excludeField(Field paramField, boolean paramBoolean)
  {
    return (!this.excluder.excludeClass(paramField.getType(), paramBoolean)) && (!this.excluder.excludeField(paramField, paramBoolean));
  }

  public final class Adapter<T> extends TypeAdapter<T>
  {
    private final Map<String, ReflectiveTypeAdapterFactory.BoundField> boundFields;
    private final ObjectConstructor<T> constructor;

    private Adapter(Map<String, ReflectiveTypeAdapterFactory.BoundField> arg2)
    {
      Object localObject1;
      this.constructor = localObject1;
      Object localObject2;
      this.boundFields = localObject2;
    }

    public T read(JsonReader paramJsonReader)
      throws IOException
    {
      if (paramJsonReader.peek() == JsonToken.NULL)
      {
        paramJsonReader.nextNull();
        return null;
      }
      Object localObject1 = this.constructor.construct();
      try
      {
        paramJsonReader.beginObject();
        while (true)
        {
          if (!paramJsonReader.hasNext())
            break label103;
          localObject2 = paramJsonReader.nextName();
          localObject2 = (ReflectiveTypeAdapterFactory.BoundField)this.boundFields.get(localObject2);
          if ((localObject2 != null) && (((ReflectiveTypeAdapterFactory.BoundField)localObject2).deserialized))
            break;
          paramJsonReader.skipValue();
        }
      }
      catch (IllegalStateException paramJsonReader)
      {
        while (true)
        {
          Object localObject2;
          throw new JsonSyntaxException(paramJsonReader);
          ((ReflectiveTypeAdapterFactory.BoundField)localObject2).read(paramJsonReader, localObject1);
        }
      }
      catch (IllegalAccessException paramJsonReader)
      {
        throw new AssertionError(paramJsonReader);
      }
      label103: paramJsonReader.endObject();
      return localObject1;
    }

    public void write(JsonWriter paramJsonWriter, T paramT)
      throws IOException
    {
      if (paramT == null)
      {
        paramJsonWriter.nullValue();
        return;
      }
      paramJsonWriter.beginObject();
      try
      {
        Iterator localIterator = this.boundFields.values().iterator();
        while (localIterator.hasNext())
        {
          ReflectiveTypeAdapterFactory.BoundField localBoundField = (ReflectiveTypeAdapterFactory.BoundField)localIterator.next();
          if (localBoundField.serialized)
          {
            paramJsonWriter.name(localBoundField.name);
            localBoundField.write(paramJsonWriter, paramT);
          }
        }
      }
      catch (IllegalAccessException paramJsonWriter)
      {
        throw new AssertionError();
      }
      paramJsonWriter.endObject();
    }
  }

  static abstract class BoundField
  {
    final boolean deserialized;
    final String name;
    final boolean serialized;

    protected BoundField(String paramString, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.name = paramString;
      this.serialized = paramBoolean1;
      this.deserialized = paramBoolean2;
    }

    abstract void read(JsonReader paramJsonReader, Object paramObject)
      throws IOException, IllegalAccessException;

    abstract void write(JsonWriter paramJsonWriter, Object paramObject)
      throws IOException, IllegalAccessException;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.gson.internal.bind.ReflectiveTypeAdapterFactory
 * JD-Core Version:    0.6.2
 */