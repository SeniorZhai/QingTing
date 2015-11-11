package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class DynamicMessage extends AbstractMessage
{
  private final FieldSet<Descriptors.FieldDescriptor> fields;
  private int memoizedSize = -1;
  private final Descriptors.Descriptor type;
  private final UnknownFieldSet unknownFields;

  private DynamicMessage(Descriptors.Descriptor paramDescriptor, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, UnknownFieldSet paramUnknownFieldSet)
  {
    this.type = paramDescriptor;
    this.fields = paramFieldSet;
    this.unknownFields = paramUnknownFieldSet;
  }

  public static DynamicMessage getDefaultInstance(Descriptors.Descriptor paramDescriptor)
  {
    return new DynamicMessage(paramDescriptor, FieldSet.emptySet(), UnknownFieldSet.getDefaultInstance());
  }

  private static boolean isInitialized(Descriptors.Descriptor paramDescriptor, FieldSet<Descriptors.FieldDescriptor> paramFieldSet)
  {
    paramDescriptor = paramDescriptor.getFields().iterator();
    while (paramDescriptor.hasNext())
    {
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)paramDescriptor.next();
      if ((localFieldDescriptor.isRequired()) && (!paramFieldSet.hasField(localFieldDescriptor)))
        return false;
    }
    return paramFieldSet.isInitialized();
  }

  public static Builder newBuilder(Descriptors.Descriptor paramDescriptor)
  {
    return new Builder(paramDescriptor, null);
  }

  public static Builder newBuilder(Message paramMessage)
  {
    return new Builder(paramMessage.getDescriptorForType(), null).mergeFrom(paramMessage);
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, ByteString paramByteString)
    throws InvalidProtocolBufferException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramByteString)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, ByteString paramByteString, ExtensionRegistry paramExtensionRegistry)
    throws InvalidProtocolBufferException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramByteString, paramExtensionRegistry)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, CodedInputStream paramCodedInputStream)
    throws IOException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramCodedInputStream)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, CodedInputStream paramCodedInputStream, ExtensionRegistry paramExtensionRegistry)
    throws IOException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramCodedInputStream, paramExtensionRegistry)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, InputStream paramInputStream)
    throws IOException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramInputStream)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, InputStream paramInputStream, ExtensionRegistry paramExtensionRegistry)
    throws IOException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramInputStream, paramExtensionRegistry)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, byte[] paramArrayOfByte)
    throws InvalidProtocolBufferException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramArrayOfByte)).buildParsed();
  }

  public static DynamicMessage parseFrom(Descriptors.Descriptor paramDescriptor, byte[] paramArrayOfByte, ExtensionRegistry paramExtensionRegistry)
    throws InvalidProtocolBufferException
  {
    return ((Builder)newBuilder(paramDescriptor).mergeFrom(paramArrayOfByte, paramExtensionRegistry)).buildParsed();
  }

  private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    if (paramFieldDescriptor.getContainingType() != this.type)
      throw new IllegalArgumentException("FieldDescriptor does not match message type.");
  }

  public Map<Descriptors.FieldDescriptor, Object> getAllFields()
  {
    return this.fields.getAllFields();
  }

  public DynamicMessage getDefaultInstanceForType()
  {
    return getDefaultInstance(this.type);
  }

  public Descriptors.Descriptor getDescriptorForType()
  {
    return this.type;
  }

  public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    verifyContainingType(paramFieldDescriptor);
    Object localObject2 = this.fields.getField(paramFieldDescriptor);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      if (paramFieldDescriptor.isRepeated())
        localObject1 = Collections.emptyList();
    }
    else
      return localObject1;
    if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
      return getDefaultInstance(paramFieldDescriptor.getMessageType());
    return paramFieldDescriptor.getDefaultValue();
  }

  public Parser<DynamicMessage> getParserForType()
  {
    return new AbstractParser()
    {
      public DynamicMessage parsePartialFrom(CodedInputStream paramAnonymousCodedInputStream, ExtensionRegistryLite paramAnonymousExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        DynamicMessage.Builder localBuilder = DynamicMessage.newBuilder(DynamicMessage.this.type);
        try
        {
          localBuilder.mergeFrom(paramAnonymousCodedInputStream, paramAnonymousExtensionRegistryLite);
          return localBuilder.buildPartial();
        }
        catch (InvalidProtocolBufferException paramAnonymousCodedInputStream)
        {
          throw paramAnonymousCodedInputStream.setUnfinishedMessage(localBuilder.buildPartial());
        }
        catch (IOException paramAnonymousCodedInputStream)
        {
        }
        throw new InvalidProtocolBufferException(paramAnonymousCodedInputStream.getMessage()).setUnfinishedMessage(localBuilder.buildPartial());
      }
    };
  }

  public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
  {
    verifyContainingType(paramFieldDescriptor);
    return this.fields.getRepeatedField(paramFieldDescriptor, paramInt);
  }

  public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    verifyContainingType(paramFieldDescriptor);
    return this.fields.getRepeatedFieldCount(paramFieldDescriptor);
  }

  public int getSerializedSize()
  {
    int i = this.memoizedSize;
    if (i != -1)
      return i;
    if (this.type.getOptions().getMessageSetWireFormat());
    for (i = this.fields.getMessageSetSerializedSize() + this.unknownFields.getSerializedSizeAsMessageSet(); ; i = this.fields.getSerializedSize() + this.unknownFields.getSerializedSize())
    {
      this.memoizedSize = i;
      return i;
    }
  }

  public UnknownFieldSet getUnknownFields()
  {
    return this.unknownFields;
  }

  public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    verifyContainingType(paramFieldDescriptor);
    return this.fields.hasField(paramFieldDescriptor);
  }

  public boolean isInitialized()
  {
    return isInitialized(this.type, this.fields);
  }

  public Builder newBuilderForType()
  {
    return new Builder(this.type, null);
  }

  public Builder toBuilder()
  {
    return newBuilderForType().mergeFrom(this);
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    if (this.type.getOptions().getMessageSetWireFormat())
    {
      this.fields.writeMessageSetTo(paramCodedOutputStream);
      this.unknownFields.writeAsMessageSetTo(paramCodedOutputStream);
      return;
    }
    this.fields.writeTo(paramCodedOutputStream);
    this.unknownFields.writeTo(paramCodedOutputStream);
  }

  public static final class Builder extends AbstractMessage.Builder<Builder>
  {
    private FieldSet<Descriptors.FieldDescriptor> fields;
    private final Descriptors.Descriptor type;
    private UnknownFieldSet unknownFields;

    private Builder(Descriptors.Descriptor paramDescriptor)
    {
      this.type = paramDescriptor;
      this.fields = FieldSet.newFieldSet();
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
    }

    private DynamicMessage buildParsed()
      throws InvalidProtocolBufferException
    {
      if (!isInitialized())
        throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, this.unknownFields, null)).asInvalidProtocolBufferException();
      return buildPartial();
    }

    private void ensureIsMutable()
    {
      if (this.fields.isImmutable())
        this.fields = this.fields.clone();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != this.type)
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    public Builder addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      verifyContainingType(paramFieldDescriptor);
      ensureIsMutable();
      this.fields.addRepeatedField(paramFieldDescriptor, paramObject);
      return this;
    }

    public DynamicMessage build()
    {
      if (!isInitialized())
        throw newUninitializedMessageException(new DynamicMessage(this.type, this.fields, this.unknownFields, null));
      return buildPartial();
    }

    public DynamicMessage buildPartial()
    {
      this.fields.makeImmutable();
      return new DynamicMessage(this.type, this.fields, this.unknownFields, null);
    }

    public Builder clear()
    {
      if (this.fields.isImmutable())
        this.fields = FieldSet.newFieldSet();
      while (true)
      {
        this.unknownFields = UnknownFieldSet.getDefaultInstance();
        return this;
        this.fields.clear();
      }
    }

    public Builder clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      verifyContainingType(paramFieldDescriptor);
      ensureIsMutable();
      this.fields.clearField(paramFieldDescriptor);
      return this;
    }

    public Builder clone()
    {
      Builder localBuilder = new Builder(this.type);
      localBuilder.fields.mergeFrom(this.fields);
      localBuilder.mergeUnknownFields(this.unknownFields);
      return localBuilder;
    }

    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      return this.fields.getAllFields();
    }

    public DynamicMessage getDefaultInstanceForType()
    {
      return DynamicMessage.getDefaultInstance(this.type);
    }

    public Descriptors.Descriptor getDescriptorForType()
    {
      return this.type;
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      verifyContainingType(paramFieldDescriptor);
      Object localObject2 = this.fields.getField(paramFieldDescriptor);
      Object localObject1 = localObject2;
      if (localObject2 == null)
      {
        if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          localObject1 = DynamicMessage.getDefaultInstance(paramFieldDescriptor.getMessageType());
      }
      else
        return localObject1;
      return paramFieldDescriptor.getDefaultValue();
    }

    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      throw new UnsupportedOperationException("getFieldBuilder() called on a dynamic message type.");
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      verifyContainingType(paramFieldDescriptor);
      return this.fields.getRepeatedField(paramFieldDescriptor, paramInt);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      verifyContainingType(paramFieldDescriptor);
      return this.fields.getRepeatedFieldCount(paramFieldDescriptor);
    }

    public UnknownFieldSet getUnknownFields()
    {
      return this.unknownFields;
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      verifyContainingType(paramFieldDescriptor);
      return this.fields.hasField(paramFieldDescriptor);
    }

    public boolean isInitialized()
    {
      return DynamicMessage.isInitialized(this.type, this.fields);
    }

    public Builder mergeFrom(Message paramMessage)
    {
      if ((paramMessage instanceof DynamicMessage))
      {
        paramMessage = (DynamicMessage)paramMessage;
        if (paramMessage.type != this.type)
          throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
        ensureIsMutable();
        this.fields.mergeFrom(paramMessage.fields);
        mergeUnknownFields(paramMessage.unknownFields);
        return this;
      }
      return (Builder)super.mergeFrom(paramMessage);
    }

    public Builder mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(paramUnknownFieldSet).build();
      return this;
    }

    public Builder newBuilderForField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      verifyContainingType(paramFieldDescriptor);
      if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
        throw new IllegalArgumentException("newBuilderForField is only valid for fields with message type.");
      return new Builder(paramFieldDescriptor.getMessageType());
    }

    public Builder setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      verifyContainingType(paramFieldDescriptor);
      ensureIsMutable();
      this.fields.setField(paramFieldDescriptor, paramObject);
      return this;
    }

    public Builder setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      verifyContainingType(paramFieldDescriptor);
      ensureIsMutable();
      this.fields.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
      return this;
    }

    public Builder setUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = paramUnknownFieldSet;
      return this;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.DynamicMessage
 * JD-Core Version:    0.6.2
 */