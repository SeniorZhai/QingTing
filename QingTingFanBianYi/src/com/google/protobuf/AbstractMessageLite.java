package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractMessageLite
  implements MessageLite
{
  UninitializedMessageException newUninitializedMessageException()
  {
    return new UninitializedMessageException(this);
  }

  public byte[] toByteArray()
  {
    try
    {
      byte[] arrayOfByte = new byte[getSerializedSize()];
      CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(arrayOfByte);
      writeTo(localCodedOutputStream);
      localCodedOutputStream.checkNoSpaceLeft();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
    }
  }

  public ByteString toByteString()
  {
    try
    {
      Object localObject = ByteString.newCodedBuilder(getSerializedSize());
      writeTo(((ByteString.CodedBuilder)localObject).getCodedOutput());
      localObject = ((ByteString.CodedBuilder)localObject).build();
      return localObject;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", localIOException);
    }
  }

  public void writeDelimitedTo(OutputStream paramOutputStream)
    throws IOException
  {
    int i = getSerializedSize();
    paramOutputStream = CodedOutputStream.newInstance(paramOutputStream, CodedOutputStream.computePreferredBufferSize(CodedOutputStream.computeRawVarint32Size(i) + i));
    paramOutputStream.writeRawVarint32(i);
    writeTo(paramOutputStream);
    paramOutputStream.flush();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = CodedOutputStream.newInstance(paramOutputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
    writeTo(paramOutputStream);
    paramOutputStream.flush();
  }

  public static abstract class Builder<BuilderType extends Builder>
    implements MessageLite.Builder
  {
    protected static <T> void addAll(Iterable<T> paramIterable, Collection<? super T> paramCollection)
    {
      if ((paramIterable instanceof LazyStringList))
      {
        checkForNullValues(((LazyStringList)paramIterable).getUnderlyingElements());
        if (!(paramIterable instanceof Collection))
          break label45;
        paramCollection.addAll((Collection)paramIterable);
      }
      while (true)
      {
        return;
        checkForNullValues(paramIterable);
        break;
        label45: paramIterable = paramIterable.iterator();
        while (paramIterable.hasNext())
          paramCollection.add(paramIterable.next());
      }
    }

    private static void checkForNullValues(Iterable<?> paramIterable)
    {
      paramIterable = paramIterable.iterator();
      while (paramIterable.hasNext())
        if (paramIterable.next() == null)
          throw new NullPointerException();
    }

    protected static UninitializedMessageException newUninitializedMessageException(MessageLite paramMessageLite)
    {
      return new UninitializedMessageException(paramMessageLite);
    }

    public abstract BuilderType clone();

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return mergeDelimitedFrom(paramInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      int i = paramInputStream.read();
      if (i == -1)
        return false;
      mergeFrom(new LimitedInputStream(paramInputStream, CodedInputStream.readRawVarint32(i, paramInputStream)), paramExtensionRegistryLite);
      return true;
    }

    public BuilderType mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      try
      {
        paramByteString = paramByteString.newCodedInput();
        mergeFrom(paramByteString);
        paramByteString.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException paramByteString)
      {
        throw paramByteString;
      }
      catch (IOException paramByteString)
      {
      }
      throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", paramByteString);
    }

    public BuilderType mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        paramByteString = paramByteString.newCodedInput();
        mergeFrom(paramByteString, paramExtensionRegistryLite);
        paramByteString.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException paramByteString)
      {
        throw paramByteString;
      }
      catch (IOException paramByteString)
      {
      }
      throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", paramByteString);
    }

    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public abstract BuilderType mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public BuilderType mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      paramInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(paramInputStream);
      paramInputStream.checkLastTagWas(0);
      return this;
    }

    public BuilderType mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      paramInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(paramInputStream, paramExtensionRegistryLite);
      paramInputStream.checkLastTagWas(0);
      return this;
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      try
      {
        paramArrayOfByte = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(paramArrayOfByte);
        paramArrayOfByte.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException paramArrayOfByte)
      {
        throw paramArrayOfByte;
      }
      catch (IOException paramArrayOfByte)
      {
      }
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", paramArrayOfByte);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        paramArrayOfByte = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(paramArrayOfByte, paramExtensionRegistryLite);
        paramArrayOfByte.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException paramArrayOfByte)
      {
        throw paramArrayOfByte;
      }
      catch (IOException paramArrayOfByte)
      {
      }
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", paramArrayOfByte);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length, paramExtensionRegistryLite);
    }

    static final class LimitedInputStream extends FilterInputStream
    {
      private int limit;

      LimitedInputStream(InputStream paramInputStream, int paramInt)
      {
        super();
        this.limit = paramInt;
      }

      public int available()
        throws IOException
      {
        return Math.min(super.available(), this.limit);
      }

      public int read()
        throws IOException
      {
        int i;
        if (this.limit <= 0)
          i = -1;
        int j;
        do
        {
          return i;
          j = super.read();
          i = j;
        }
        while (j < 0);
        this.limit -= 1;
        return j;
      }

      public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException
      {
        if (this.limit <= 0)
          paramInt1 = -1;
        do
        {
          return paramInt1;
          paramInt2 = super.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, this.limit));
          paramInt1 = paramInt2;
        }
        while (paramInt2 < 0);
        this.limit -= paramInt2;
        return paramInt2;
      }

      public long skip(long paramLong)
        throws IOException
      {
        paramLong = super.skip(Math.min(paramLong, this.limit));
        if (paramLong >= 0L)
          this.limit = ((int)(this.limit - paramLong));
        return paramLong;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.AbstractMessageLite
 * JD-Core Version:    0.6.2
 */