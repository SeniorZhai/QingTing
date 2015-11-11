package com.google.protobuf;

import java.util.List;
import java.util.Map;

public abstract interface MessageOrBuilder extends MessageLiteOrBuilder
{
  public abstract List<String> findInitializationErrors();

  public abstract Map<Descriptors.FieldDescriptor, Object> getAllFields();

  public abstract Message getDefaultInstanceForType();

  public abstract Descriptors.Descriptor getDescriptorForType();

  public abstract Object getField(Descriptors.FieldDescriptor paramFieldDescriptor);

  public abstract String getInitializationErrorString();

  public abstract Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt);

  public abstract int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor);

  public abstract UnknownFieldSet getUnknownFields();

  public abstract boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor);
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.MessageOrBuilder
 * JD-Core Version:    0.6.2
 */