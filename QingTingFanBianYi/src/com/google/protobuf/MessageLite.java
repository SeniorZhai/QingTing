package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public abstract interface MessageLite extends MessageLiteOrBuilder
{
  public abstract Parser<? extends MessageLite> getParserForType();

  public abstract int getSerializedSize();

  public abstract Builder newBuilderForType();

  public abstract Builder toBuilder();

  public abstract byte[] toByteArray();

  public abstract ByteString toByteString();

  public abstract void writeDelimitedTo(OutputStream paramOutputStream)
    throws IOException;

  public abstract void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException;

  public abstract void writeTo(OutputStream paramOutputStream)
    throws IOException;

  public static abstract interface Builder extends MessageLiteOrBuilder, Cloneable
  {
    public abstract MessageLite build();

    public abstract MessageLite buildPartial();

    public abstract Builder clear();

    public abstract Builder clone();

    public abstract boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException;

    public abstract boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public abstract Builder mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException;

    public abstract Builder mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;

    public abstract Builder mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException;

    public abstract Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public abstract Builder mergeFrom(InputStream paramInputStream)
      throws IOException;

    public abstract Builder mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public abstract Builder mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException;

    public abstract Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException;

    public abstract Builder mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;

    public abstract Builder mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.MessageLite
 * JD-Core Version:    0.6.2
 */