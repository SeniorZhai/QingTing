package com.google.protobuf;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public abstract class AbstractMessage extends AbstractMessageLite
  implements Message
{
  private int memoizedSize = -1;

  private static String delimitWithCommas(List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (localStringBuilder.length() > 0)
        localStringBuilder.append(", ");
      localStringBuilder.append(str);
    }
    return localStringBuilder.toString();
  }

  protected static int hashBoolean(boolean paramBoolean)
  {
    if (paramBoolean)
      return 1231;
    return 1237;
  }

  protected static int hashEnum(Internal.EnumLite paramEnumLite)
  {
    return paramEnumLite.getNumber();
  }

  protected static int hashEnumList(List<? extends Internal.EnumLite> paramList)
  {
    int i = 1;
    paramList = paramList.iterator();
    while (paramList.hasNext())
      i = i * 31 + hashEnum((Internal.EnumLite)paramList.next());
    return i;
  }

  protected static int hashLong(long paramLong)
  {
    return (int)(paramLong >>> 32 ^ paramLong);
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == this);
    do
    {
      return true;
      if (!(paramObject instanceof Message))
        return false;
      paramObject = (Message)paramObject;
      if (getDescriptorForType() != paramObject.getDescriptorForType())
        return false;
    }
    while ((getAllFields().equals(paramObject.getAllFields())) && (getUnknownFields().equals(paramObject.getUnknownFields())));
    return false;
  }

  public List<String> findInitializationErrors()
  {
    return Builder.findMissingFields(this);
  }

  public String getInitializationErrorString()
  {
    return delimitWithCommas(findInitializationErrors());
  }

  public int getSerializedSize()
  {
    int i = this.memoizedSize;
    if (i != -1)
      return i;
    i = 0;
    boolean bool = getDescriptorForType().getOptions().getMessageSetWireFormat();
    Object localObject1 = getAllFields().entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject2).getKey();
      localObject2 = ((Map.Entry)localObject2).getValue();
      if ((bool) && (localFieldDescriptor.isExtension()) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (!localFieldDescriptor.isRepeated()))
        i += CodedOutputStream.computeMessageSetExtensionSize(localFieldDescriptor.getNumber(), (Message)localObject2);
      else
        i += FieldSet.computeFieldSize(localFieldDescriptor, localObject2);
    }
    localObject1 = getUnknownFields();
    if (bool)
      i += ((UnknownFieldSet)localObject1).getSerializedSizeAsMessageSet();
    while (true)
    {
      this.memoizedSize = i;
      return i;
      i += ((UnknownFieldSet)localObject1).getSerializedSize();
    }
  }

  public int hashCode()
  {
    return hashFields(getDescriptorForType().hashCode() + 779, getAllFields()) * 29 + getUnknownFields().hashCode();
  }

  protected int hashFields(int paramInt, Map<Descriptors.FieldDescriptor, Object> paramMap)
  {
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject = (Map.Entry)paramMap.next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
      localObject = ((Map.Entry)localObject).getValue();
      paramInt = paramInt * 37 + localFieldDescriptor.getNumber();
      if (localFieldDescriptor.getType() != Descriptors.FieldDescriptor.Type.ENUM)
        paramInt = paramInt * 53 + localObject.hashCode();
      else if (localFieldDescriptor.isRepeated())
        paramInt = paramInt * 53 + hashEnumList((List)localObject);
      else
        paramInt = paramInt * 53 + hashEnum((Internal.EnumLite)localObject);
    }
    return paramInt;
  }

  public boolean isInitialized()
  {
    Iterator localIterator = getDescriptorForType().getFields().iterator();
    Object localObject;
    while (localIterator.hasNext())
    {
      localObject = (Descriptors.FieldDescriptor)localIterator.next();
      if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject)))
        return false;
    }
    localIterator = getAllFields().entrySet().iterator();
    while (true)
      if (localIterator.hasNext())
      {
        localObject = (Map.Entry)localIterator.next();
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          if (localFieldDescriptor.isRepeated())
          {
            localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
            if (!((Iterator)localObject).hasNext())
              continue;
            if (((Message)((Iterator)localObject).next()).isInitialized())
              break;
            return false;
          }
          if (!((Message)((Map.Entry)localObject).getValue()).isInitialized())
            return false;
        }
      }
    return true;
  }

  UninitializedMessageException newUninitializedMessageException()
  {
    return Builder.newUninitializedMessageException(this);
  }

  public final String toString()
  {
    return TextFormat.printToString(this);
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    boolean bool = getDescriptorForType().getOptions().getMessageSetWireFormat();
    Object localObject1 = getAllFields().entrySet().iterator();
    while (((Iterator)localObject1).hasNext())
    {
      Object localObject2 = (Map.Entry)((Iterator)localObject1).next();
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject2).getKey();
      localObject2 = ((Map.Entry)localObject2).getValue();
      if ((bool) && (localFieldDescriptor.isExtension()) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (!localFieldDescriptor.isRepeated()))
        paramCodedOutputStream.writeMessageSetExtension(localFieldDescriptor.getNumber(), (Message)localObject2);
      else
        FieldSet.writeField(localFieldDescriptor, localObject2, paramCodedOutputStream);
    }
    localObject1 = getUnknownFields();
    if (bool)
    {
      ((UnknownFieldSet)localObject1).writeAsMessageSetTo(paramCodedOutputStream);
      return;
    }
    ((UnknownFieldSet)localObject1).writeTo(paramCodedOutputStream);
  }

  public static abstract class Builder<BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType>
    implements Message.Builder
  {
    private static void addRepeatedField(Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramBuilder != null)
      {
        paramBuilder.addRepeatedField(paramFieldDescriptor, paramObject);
        return;
      }
      paramFieldSet.addRepeatedField(paramFieldDescriptor, paramObject);
    }

    private static void eagerlyMergeMessageSetExtension(CodedInputStream paramCodedInputStream, ExtensionRegistry.ExtensionInfo paramExtensionInfo, ExtensionRegistryLite paramExtensionRegistryLite, Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet)
      throws IOException
    {
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtensionInfo.descriptor;
      if (hasOriginalMessage(paramBuilder, paramFieldSet, localFieldDescriptor))
      {
        paramExtensionInfo = getOriginalMessage(paramBuilder, paramFieldSet, localFieldDescriptor).toBuilder();
        paramCodedInputStream.readMessage(paramExtensionInfo, paramExtensionRegistryLite);
      }
      for (paramCodedInputStream = paramExtensionInfo.buildPartial(); paramBuilder != null; paramCodedInputStream = (Message)paramCodedInputStream.readMessage(paramExtensionInfo.defaultInstance.getParserForType(), paramExtensionRegistryLite))
      {
        paramBuilder.setField(localFieldDescriptor, paramCodedInputStream);
        return;
      }
      paramFieldSet.setField(localFieldDescriptor, paramCodedInputStream);
    }

    private static List<String> findMissingFields(MessageOrBuilder paramMessageOrBuilder)
    {
      ArrayList localArrayList = new ArrayList();
      findMissingFields(paramMessageOrBuilder, "", localArrayList);
      return localArrayList;
    }

    private static void findMissingFields(MessageOrBuilder paramMessageOrBuilder, String paramString, List<String> paramList)
    {
      Iterator localIterator = paramMessageOrBuilder.getDescriptorForType().getFields().iterator();
      Descriptors.FieldDescriptor localFieldDescriptor;
      while (localIterator.hasNext())
      {
        localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
        if ((localFieldDescriptor.isRequired()) && (!paramMessageOrBuilder.hasField(localFieldDescriptor)))
          paramList.add(paramString + localFieldDescriptor.getName());
      }
      localIterator = paramMessageOrBuilder.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
        localObject = ((Map.Entry)localObject).getValue();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          if (localFieldDescriptor.isRepeated())
          {
            int i = 0;
            localObject = ((List)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              findMissingFields((MessageOrBuilder)((Iterator)localObject).next(), subMessagePrefix(paramString, localFieldDescriptor, i), paramList);
              i += 1;
            }
          }
          else if (paramMessageOrBuilder.hasField(localFieldDescriptor))
          {
            findMissingFields((MessageOrBuilder)localObject, subMessagePrefix(paramString, localFieldDescriptor, -1), paramList);
          }
      }
    }

    private static Message getOriginalMessage(Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramBuilder != null)
        return (Message)paramBuilder.getField(paramFieldDescriptor);
      return (Message)paramFieldSet.getField(paramFieldDescriptor);
    }

    private static boolean hasOriginalMessage(Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramBuilder != null)
        return paramBuilder.hasField(paramFieldDescriptor);
      return paramFieldSet.hasField(paramFieldDescriptor);
    }

    static boolean mergeFieldFrom(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Descriptors.Descriptor paramDescriptor, Message.Builder paramBuilder1, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, int paramInt)
      throws IOException
    {
      if ((paramDescriptor.getOptions().getMessageSetWireFormat()) && (paramInt == WireFormat.MESSAGE_SET_ITEM_TAG))
      {
        mergeMessageSetExtensionFromCodedStream(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, paramDescriptor, paramBuilder1, paramFieldSet);
        return true;
      }
      int m = WireFormat.getTagWireType(paramInt);
      int k = WireFormat.getTagFieldNumber(paramInt);
      Object localObject = null;
      int j;
      int i;
      if (paramDescriptor.isExtensionNumber(k))
        if ((paramExtensionRegistryLite instanceof ExtensionRegistry))
        {
          paramDescriptor = ((ExtensionRegistry)paramExtensionRegistryLite).findExtensionByNumber(paramDescriptor, k);
          if (paramDescriptor == null)
          {
            paramDescriptor = null;
            j = 0;
            i = 0;
            if (paramDescriptor != null)
              break label205;
            j = 1;
          }
        }
      while (true)
      {
        if (j == 0)
          break label256;
        return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
        Descriptors.FieldDescriptor localFieldDescriptor = paramDescriptor.descriptor;
        Message localMessage = paramDescriptor.defaultInstance;
        localObject = localMessage;
        paramDescriptor = localFieldDescriptor;
        if (localMessage != null)
          break;
        localObject = localMessage;
        paramDescriptor = localFieldDescriptor;
        if (localFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
          break;
        throw new IllegalStateException("Message-typed extension lacked default instance: " + localFieldDescriptor.getFullName());
        paramDescriptor = null;
        break;
        if (paramBuilder1 != null)
        {
          paramDescriptor = paramDescriptor.findFieldByNumber(k);
          break;
        }
        paramDescriptor = null;
        break;
        label205: if (m == FieldSet.getWireFormatForFieldType(paramDescriptor.getLiteType(), false))
          i = 0;
        else if ((paramDescriptor.isPackable()) && (m == FieldSet.getWireFormatForFieldType(paramDescriptor.getLiteType(), true)))
          i = 1;
        else
          j = 1;
      }
      label256: if (i != 0)
      {
        paramInt = paramCodedInputStream.pushLimit(paramCodedInputStream.readRawVarint32());
        if (paramDescriptor.getLiteType() == WireFormat.FieldType.ENUM)
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
          {
            i = paramCodedInputStream.readEnum();
            paramBuilder = paramDescriptor.getEnumType().findValueByNumber(i);
            if (paramBuilder == null)
              return true;
            addRepeatedField(paramBuilder1, paramFieldSet, paramDescriptor, paramBuilder);
          }
        while (paramCodedInputStream.getBytesUntilLimit() > 0)
          addRepeatedField(paramBuilder1, paramFieldSet, paramDescriptor, FieldSet.readPrimitiveField(paramCodedInputStream, paramDescriptor.getLiteType()));
        paramCodedInputStream.popLimit(paramInt);
      }
      while (true)
      {
        return true;
        switch (AbstractMessage.1.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$Type[paramDescriptor.getType().ordinal()])
        {
        default:
          paramCodedInputStream = FieldSet.readPrimitiveField(paramCodedInputStream, paramDescriptor.getLiteType());
        case 1:
        case 2:
        case 3:
        }
        while (true)
          if (paramDescriptor.isRepeated())
          {
            addRepeatedField(paramBuilder1, paramFieldSet, paramDescriptor, paramCodedInputStream);
            break;
            if (localObject != null);
            for (paramBuilder = localObject.newBuilderForType(); ; paramBuilder = paramBuilder1.newBuilderForField(paramDescriptor))
            {
              if (!paramDescriptor.isRepeated())
                mergeOriginalMessage(paramBuilder1, paramFieldSet, paramDescriptor, paramBuilder);
              paramCodedInputStream.readGroup(paramDescriptor.getNumber(), paramBuilder, paramExtensionRegistryLite);
              paramCodedInputStream = paramBuilder.buildPartial();
              break;
            }
            if (localObject != null);
            for (paramBuilder = localObject.newBuilderForType(); ; paramBuilder = paramBuilder1.newBuilderForField(paramDescriptor))
            {
              if (!paramDescriptor.isRepeated())
                mergeOriginalMessage(paramBuilder1, paramFieldSet, paramDescriptor, paramBuilder);
              paramCodedInputStream.readMessage(paramBuilder, paramExtensionRegistryLite);
              paramCodedInputStream = paramBuilder.buildPartial();
              break;
            }
            paramInt = paramCodedInputStream.readEnum();
            paramExtensionRegistryLite = paramDescriptor.getEnumType().findValueByNumber(paramInt);
            paramCodedInputStream = paramExtensionRegistryLite;
            if (paramExtensionRegistryLite == null)
            {
              paramBuilder.mergeVarintField(k, paramInt);
              return true;
            }
          }
        setField(paramBuilder1, paramFieldSet, paramDescriptor, paramCodedInputStream);
      }
    }

    private static void mergeMessageSetExtensionFromBytes(ByteString paramByteString, ExtensionRegistry.ExtensionInfo paramExtensionInfo, ExtensionRegistryLite paramExtensionRegistryLite, Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet)
      throws IOException
    {
      Descriptors.FieldDescriptor localFieldDescriptor = paramExtensionInfo.descriptor;
      boolean bool = hasOriginalMessage(paramBuilder, paramFieldSet, localFieldDescriptor);
      if ((bool) || (ExtensionRegistryLite.isEagerlyParseMessageSets()))
      {
        if (bool)
        {
          paramExtensionInfo = getOriginalMessage(paramBuilder, paramFieldSet, localFieldDescriptor).toBuilder();
          paramExtensionInfo.mergeFrom(paramByteString, paramExtensionRegistryLite);
        }
        for (paramByteString = paramExtensionInfo.buildPartial(); ; paramByteString = (Message)paramExtensionInfo.defaultInstance.getParserForType().parsePartialFrom(paramByteString, paramExtensionRegistryLite))
        {
          setField(paramBuilder, paramFieldSet, localFieldDescriptor, paramByteString);
          return;
        }
      }
      paramByteString = new LazyField(paramExtensionInfo.defaultInstance, paramExtensionRegistryLite, paramByteString);
      if (paramBuilder != null)
      {
        if ((paramBuilder instanceof GeneratedMessage.ExtendableBuilder))
        {
          paramBuilder.setField(localFieldDescriptor, paramByteString);
          return;
        }
        paramBuilder.setField(localFieldDescriptor, paramByteString.getValue());
        return;
      }
      paramFieldSet.setField(localFieldDescriptor, paramByteString);
    }

    private static void mergeMessageSetExtensionFromCodedStream(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, Descriptors.Descriptor paramDescriptor, Message.Builder paramBuilder1, FieldSet<Descriptors.FieldDescriptor> paramFieldSet)
      throws IOException
    {
      int i = 0;
      ByteString localByteString = null;
      ExtensionRegistry.ExtensionInfo localExtensionInfo = null;
      int j = paramCodedInputStream.readTag();
      if (j == 0)
      {
        label20: paramCodedInputStream.checkLastTagWas(WireFormat.MESSAGE_SET_ITEM_END_TAG);
        if ((localByteString != null) && (i != 0))
        {
          if (localExtensionInfo == null)
            break label170;
          mergeMessageSetExtensionFromBytes(localByteString, localExtensionInfo, paramExtensionRegistryLite, paramBuilder1, paramFieldSet);
        }
      }
      label170: 
      while (localByteString == null)
      {
        return;
        if (j == WireFormat.MESSAGE_SET_TYPE_ID_TAG)
        {
          j = paramCodedInputStream.readUInt32();
          i = j;
          if (j == 0)
            break;
          i = j;
          if (!(paramExtensionRegistryLite instanceof ExtensionRegistry))
            break;
          localExtensionInfo = ((ExtensionRegistry)paramExtensionRegistryLite).findExtensionByNumber(paramDescriptor, j);
          i = j;
          break;
        }
        if (j == WireFormat.MESSAGE_SET_MESSAGE_TAG)
        {
          if ((i != 0) && (localExtensionInfo != null) && (ExtensionRegistryLite.isEagerlyParseMessageSets()))
          {
            eagerlyMergeMessageSetExtension(paramCodedInputStream, localExtensionInfo, paramExtensionRegistryLite, paramBuilder1, paramFieldSet);
            localByteString = null;
            break;
          }
          localByteString = paramCodedInputStream.readBytes();
          break;
        }
        if (paramCodedInputStream.skipField(j))
          break;
        break label20;
      }
      paramBuilder.mergeField(i, UnknownFieldSet.Field.newBuilder().addLengthDelimited(localByteString).build());
    }

    private static void mergeOriginalMessage(Message.Builder paramBuilder1, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, Descriptors.FieldDescriptor paramFieldDescriptor, Message.Builder paramBuilder2)
    {
      paramBuilder1 = getOriginalMessage(paramBuilder1, paramFieldSet, paramFieldDescriptor);
      if (paramBuilder1 != null)
        paramBuilder2.mergeFrom(paramBuilder1);
    }

    protected static UninitializedMessageException newUninitializedMessageException(Message paramMessage)
    {
      return new UninitializedMessageException(findMissingFields(paramMessage));
    }

    private static void setField(Message.Builder paramBuilder, FieldSet<Descriptors.FieldDescriptor> paramFieldSet, Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramBuilder != null)
      {
        paramBuilder.setField(paramFieldDescriptor, paramObject);
        return;
      }
      paramFieldSet.setField(paramFieldDescriptor, paramObject);
    }

    private static String subMessagePrefix(String paramString, Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      paramString = new StringBuilder(paramString);
      if (paramFieldDescriptor.isExtension())
        paramString.append('(').append(paramFieldDescriptor.getFullName()).append(')');
      while (true)
      {
        if (paramInt != -1)
          paramString.append('[').append(paramInt).append(']');
        paramString.append('.');
        return paramString.toString();
        paramString.append(paramFieldDescriptor.getName());
      }
    }

    public BuilderType clear()
    {
      Iterator localIterator = getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
        clearField((Descriptors.FieldDescriptor)((Map.Entry)localIterator.next()).getKey());
      return this;
    }

    public abstract BuilderType clone();

    public List<String> findInitializationErrors()
    {
      return findMissingFields(this);
    }

    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      throw new UnsupportedOperationException("getFieldBuilder() called on an unsupported message type.");
    }

    public String getInitializationErrorString()
    {
      return AbstractMessage.delimitWithCommas(findInitializationErrors());
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream);
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return super.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite);
    }

    public BuilderType mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString);
    }

    public BuilderType mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramByteString, paramExtensionRegistryLite);
    }

    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistry.getEmptyRegistry());
    }

    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      UnknownFieldSet.Builder localBuilder = UnknownFieldSet.newBuilder(getUnknownFields());
      int i = paramCodedInputStream.readTag();
      if (i == 0);
      while (true)
      {
        setUnknownFields(localBuilder.build());
        return this;
        if (mergeFieldFrom(paramCodedInputStream, localBuilder, paramExtensionRegistryLite, getDescriptorForType(), this, null, i))
          break;
      }
    }

    public BuilderType mergeFrom(Message paramMessage)
    {
      if (paramMessage.getDescriptorForType() != getDescriptorForType())
        throw new IllegalArgumentException("mergeFrom(Message) can only merge messages of the same type.");
      Iterator localIterator = paramMessage.getAllFields().entrySet().iterator();
      while (localIterator.hasNext())
      {
        Object localObject = (Map.Entry)localIterator.next();
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)((Map.Entry)localObject).getKey();
        if (localFieldDescriptor.isRepeated())
        {
          localObject = ((List)((Map.Entry)localObject).getValue()).iterator();
          while (((Iterator)localObject).hasNext())
            addRepeatedField(localFieldDescriptor, ((Iterator)localObject).next());
        }
        else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          Message localMessage = (Message)getField(localFieldDescriptor);
          if (localMessage == localMessage.getDefaultInstanceForType())
            setField(localFieldDescriptor, ((Map.Entry)localObject).getValue());
          else
            setField(localFieldDescriptor, localMessage.newBuilderForType().mergeFrom(localMessage).mergeFrom((Message)((Map.Entry)localObject).getValue()).build());
        }
        else
        {
          setField(localFieldDescriptor, ((Map.Entry)localObject).getValue());
        }
      }
      mergeUnknownFields(paramMessage.getUnknownFields());
      return this;
    }

    public BuilderType mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream);
    }

    public BuilderType mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return (Builder)super.mergeFrom(paramInputStream, paramExtensionRegistryLite);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramInt1, paramInt2, paramExtensionRegistryLite);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return (Builder)super.mergeFrom(paramArrayOfByte, paramExtensionRegistryLite);
    }

    public BuilderType mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      setUnknownFields(UnknownFieldSet.newBuilder(getUnknownFields()).mergeFrom(paramUnknownFieldSet).build());
      return this;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.AbstractMessage
 * JD-Core Version:    0.6.2
 */