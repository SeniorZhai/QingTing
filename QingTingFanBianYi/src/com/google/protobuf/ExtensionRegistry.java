package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public final class ExtensionRegistry extends ExtensionRegistryLite
{
  private static final ExtensionRegistry EMPTY = new ExtensionRegistry(true);
  private final Map<String, ExtensionInfo> extensionsByName;
  private final Map<DescriptorIntPair, ExtensionInfo> extensionsByNumber;

  private ExtensionRegistry()
  {
    this.extensionsByName = new HashMap();
    this.extensionsByNumber = new HashMap();
  }

  private ExtensionRegistry(ExtensionRegistry paramExtensionRegistry)
  {
    super(paramExtensionRegistry);
    this.extensionsByName = Collections.unmodifiableMap(paramExtensionRegistry.extensionsByName);
    this.extensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistry.extensionsByNumber);
  }

  private ExtensionRegistry(boolean paramBoolean)
  {
    super(ExtensionRegistryLite.getEmptyRegistry());
    this.extensionsByName = Collections.emptyMap();
    this.extensionsByNumber = Collections.emptyMap();
  }

  private void add(ExtensionInfo paramExtensionInfo)
  {
    if (!paramExtensionInfo.descriptor.isExtension())
      throw new IllegalArgumentException("ExtensionRegistry.add() was given a FieldDescriptor for a regular (non-extension) field.");
    this.extensionsByName.put(paramExtensionInfo.descriptor.getFullName(), paramExtensionInfo);
    this.extensionsByNumber.put(new DescriptorIntPair(paramExtensionInfo.descriptor.getContainingType(), paramExtensionInfo.descriptor.getNumber()), paramExtensionInfo);
    Descriptors.FieldDescriptor localFieldDescriptor = paramExtensionInfo.descriptor;
    if ((localFieldDescriptor.getContainingType().getOptions().getMessageSetWireFormat()) && (localFieldDescriptor.getType() == Descriptors.FieldDescriptor.Type.MESSAGE) && (localFieldDescriptor.isOptional()) && (localFieldDescriptor.getExtensionScope() == localFieldDescriptor.getMessageType()))
      this.extensionsByName.put(localFieldDescriptor.getMessageType().getFullName(), paramExtensionInfo);
  }

  public static ExtensionRegistry getEmptyRegistry()
  {
    return EMPTY;
  }

  public static ExtensionRegistry newInstance()
  {
    return new ExtensionRegistry();
  }

  public void add(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    if (paramFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
      throw new IllegalArgumentException("ExtensionRegistry.add() must be provided a default instance when adding an embedded message extension.");
    add(new ExtensionInfo(paramFieldDescriptor, null, null));
  }

  public void add(Descriptors.FieldDescriptor paramFieldDescriptor, Message paramMessage)
  {
    if (paramFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
      throw new IllegalArgumentException("ExtensionRegistry.add() provided a default instance for a non-message extension.");
    add(new ExtensionInfo(paramFieldDescriptor, paramMessage, null));
  }

  public void add(GeneratedMessage.GeneratedExtension<?, ?> paramGeneratedExtension)
  {
    if (paramGeneratedExtension.getDescriptor().getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
    {
      if (paramGeneratedExtension.getMessageDefaultInstance() == null)
        throw new IllegalStateException("Registered message-type extension had null default instance: " + paramGeneratedExtension.getDescriptor().getFullName());
      add(new ExtensionInfo(paramGeneratedExtension.getDescriptor(), paramGeneratedExtension.getMessageDefaultInstance(), null));
      return;
    }
    add(new ExtensionInfo(paramGeneratedExtension.getDescriptor(), null, null));
  }

  public ExtensionInfo findExtensionByName(String paramString)
  {
    return (ExtensionInfo)this.extensionsByName.get(paramString);
  }

  public ExtensionInfo findExtensionByNumber(Descriptors.Descriptor paramDescriptor, int paramInt)
  {
    return (ExtensionInfo)this.extensionsByNumber.get(new DescriptorIntPair(paramDescriptor, paramInt));
  }

  public ExtensionRegistry getUnmodifiable()
  {
    return new ExtensionRegistry(this);
  }

  private static final class DescriptorIntPair
  {
    private final Descriptors.Descriptor descriptor;
    private final int number;

    DescriptorIntPair(Descriptors.Descriptor paramDescriptor, int paramInt)
    {
      this.descriptor = paramDescriptor;
      this.number = paramInt;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof DescriptorIntPair));
      do
      {
        return false;
        paramObject = (DescriptorIntPair)paramObject;
      }
      while ((this.descriptor != paramObject.descriptor) || (this.number != paramObject.number));
      return true;
    }

    public int hashCode()
    {
      return this.descriptor.hashCode() * 65535 + this.number;
    }
  }

  public static final class ExtensionInfo
  {
    public final Message defaultInstance;
    public final Descriptors.FieldDescriptor descriptor;

    private ExtensionInfo(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      this.descriptor = paramFieldDescriptor;
      this.defaultInstance = null;
    }

    private ExtensionInfo(Descriptors.FieldDescriptor paramFieldDescriptor, Message paramMessage)
    {
      this.descriptor = paramFieldDescriptor;
      this.defaultInstance = paramMessage;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.ExtensionRegistry
 * JD-Core Version:    0.6.2
 */