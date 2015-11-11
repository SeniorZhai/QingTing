package com.google.protobuf;

public abstract interface ProtocolMessageEnum extends Internal.EnumLite
{
  public abstract Descriptors.EnumDescriptor getDescriptorForType();

  public abstract int getNumber();

  public abstract Descriptors.EnumValueDescriptor getValueDescriptor();
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.ProtocolMessageEnum
 * JD-Core Version:    0.6.2
 */