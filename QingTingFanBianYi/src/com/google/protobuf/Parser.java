package com.google.protobuf;

import java.io.InputStream;

public abstract interface Parser<MessageType>
{
  public abstract MessageType parseDelimitedFrom(InputStream paramInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(ByteString paramByteString)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(CodedInputStream paramCodedInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(InputStream paramInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialDelimitedFrom(InputStream paramInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(ByteString paramByteString)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(CodedInputStream paramCodedInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(InputStream paramInputStream)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(byte[] paramArrayOfByte)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;

  public abstract MessageType parsePartialFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
    throws InvalidProtocolBufferException;
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.Parser
 * JD-Core Version:    0.6.2
 */