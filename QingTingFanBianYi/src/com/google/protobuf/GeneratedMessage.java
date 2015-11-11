package com.google.protobuf;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public abstract class GeneratedMessage extends AbstractMessage
  implements Serializable
{
  protected static boolean alwaysUseFieldBuilders = false;
  private static final long serialVersionUID = 1L;

  protected GeneratedMessage()
  {
  }

  protected GeneratedMessage(Builder<?> paramBuilder)
  {
  }

  static void enableAlwaysUseFieldBuildersForTesting()
  {
    alwaysUseFieldBuilders = true;
  }

  private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable()
  {
    TreeMap localTreeMap = new TreeMap();
    Iterator localIterator = internalGetFieldAccessorTable().descriptor.getFields().iterator();
    while (localIterator.hasNext())
    {
      Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
      if (localFieldDescriptor.isRepeated())
      {
        List localList = (List)getField(localFieldDescriptor);
        if (!localList.isEmpty())
          localTreeMap.put(localFieldDescriptor, localList);
      }
      else if (hasField(localFieldDescriptor))
      {
        localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
      }
    }
    return localTreeMap;
  }

  private static Method getMethodOrDie(Class paramClass, String paramString, Class[] paramArrayOfClass)
  {
    try
    {
      paramArrayOfClass = paramClass.getMethod(paramString, paramArrayOfClass);
      return paramArrayOfClass;
    }
    catch (NoSuchMethodException paramArrayOfClass)
    {
    }
    throw new RuntimeException("Generated message class \"" + paramClass.getName() + "\" missing method \"" + paramString + "\".", paramArrayOfClass);
  }

  private static Object invokeOrDie(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramArrayOfObject);
      return paramMethod;
    }
    catch (IllegalAccessException paramMethod)
    {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", paramMethod);
    }
    catch (InvocationTargetException paramMethod)
    {
      paramMethod = paramMethod.getCause();
      if ((paramMethod instanceof RuntimeException))
        throw ((RuntimeException)paramMethod);
      if ((paramMethod instanceof Error))
        throw ((Error)paramMethod);
    }
    throw new RuntimeException("Unexpected exception thrown by generated accessor method.", paramMethod);
  }

  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newFileScopedGeneratedExtension(Class paramClass, Message paramMessage)
  {
    return new GeneratedExtension(null, paramClass, paramMessage, null);
  }

  public static <ContainingType extends Message, Type> GeneratedExtension<ContainingType, Type> newMessageScopedGeneratedExtension(Message paramMessage1, final int paramInt, Class paramClass, Message paramMessage2)
  {
    return new GeneratedExtension(new ExtensionDescriptorRetriever()
    {
      public Descriptors.FieldDescriptor getDescriptor()
      {
        return (Descriptors.FieldDescriptor)this.val$scope.getDescriptorForType().getExtensions().get(paramInt);
      }
    }
    , paramClass, paramMessage2, null);
  }

  public Map<Descriptors.FieldDescriptor, Object> getAllFields()
  {
    return Collections.unmodifiableMap(getAllFieldsMutable());
  }

  public Descriptors.Descriptor getDescriptorForType()
  {
    return internalGetFieldAccessorTable().descriptor;
  }

  public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).get(this);
  }

  public Parser<? extends Message> getParserForType()
  {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }

  public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeated(this, paramInt);
  }

  public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).getRepeatedCount(this);
  }

  public UnknownFieldSet getUnknownFields()
  {
    throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
  }

  public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
  {
    return internalGetFieldAccessorTable().getField(paramFieldDescriptor).has(this);
  }

  protected abstract FieldAccessorTable internalGetFieldAccessorTable();

  public boolean isInitialized()
  {
    Iterator localIterator = getDescriptorForType().getFields().iterator();
    while (true)
      if (localIterator.hasNext())
      {
        Object localObject = (Descriptors.FieldDescriptor)localIterator.next();
        if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject)))
          return false;
        if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          if (((Descriptors.FieldDescriptor)localObject).isRepeated())
          {
            localObject = ((List)getField((Descriptors.FieldDescriptor)localObject)).iterator();
            if (!((Iterator)localObject).hasNext())
              continue;
            if (((Message)((Iterator)localObject).next()).isInitialized())
              break;
            return false;
          }
          if ((hasField((Descriptors.FieldDescriptor)localObject)) && (!((Message)getField((Descriptors.FieldDescriptor)localObject)).isInitialized()))
            return false;
        }
      }
    return true;
  }

  protected void makeExtensionsImmutable()
  {
  }

  protected abstract Message.Builder newBuilderForType(BuilderParent paramBuilderParent);

  protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
    throws IOException
  {
    return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
  }

  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new GeneratedMessageLite.SerializedForm(this);
  }

  public static abstract class Builder<BuilderType extends Builder> extends AbstractMessage.Builder<BuilderType>
  {
    private GeneratedMessage.BuilderParent builderParent;
    private boolean isClean;
    private Builder<BuilderType>.BuilderParentImpl meAsParent;
    private UnknownFieldSet unknownFields = UnknownFieldSet.getDefaultInstance();

    protected Builder()
    {
      this(null);
    }

    protected Builder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      this.builderParent = paramBuilderParent;
    }

    private Map<Descriptors.FieldDescriptor, Object> getAllFieldsMutable()
    {
      TreeMap localTreeMap = new TreeMap();
      Iterator localIterator = GeneratedMessage.FieldAccessorTable.access$000(internalGetFieldAccessorTable()).getFields().iterator();
      while (localIterator.hasNext())
      {
        Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)localIterator.next();
        if (localFieldDescriptor.isRepeated())
        {
          List localList = (List)getField(localFieldDescriptor);
          if (!localList.isEmpty())
            localTreeMap.put(localFieldDescriptor, localList);
        }
        else if (hasField(localFieldDescriptor))
        {
          localTreeMap.put(localFieldDescriptor, getField(localFieldDescriptor));
        }
      }
      return localTreeMap;
    }

    public BuilderType addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).addRepeated(this, paramObject);
      return this;
    }

    public BuilderType clear()
    {
      this.unknownFields = UnknownFieldSet.getDefaultInstance();
      onChanged();
      return this;
    }

    public BuilderType clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).clear(this);
      return this;
    }

    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    void dispose()
    {
      this.builderParent = null;
    }

    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      return Collections.unmodifiableMap(getAllFieldsMutable());
    }

    public Descriptors.Descriptor getDescriptorForType()
    {
      return GeneratedMessage.FieldAccessorTable.access$000(internalGetFieldAccessorTable());
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      Object localObject2 = GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).get(this);
      Object localObject1 = localObject2;
      if (paramFieldDescriptor.isRepeated())
        localObject1 = Collections.unmodifiableList((List)localObject2);
      return localObject1;
    }

    public Message.Builder getFieldBuilder(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).getBuilder(this);
    }

    protected GeneratedMessage.BuilderParent getParentForChildren()
    {
      if (this.meAsParent == null)
        this.meAsParent = new BuilderParentImpl(null);
      return this.meAsParent;
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).getRepeated(this, paramInt);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).getRepeatedCount(this);
    }

    public final UnknownFieldSet getUnknownFields()
    {
      return this.unknownFields;
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).has(this);
    }

    protected abstract GeneratedMessage.FieldAccessorTable internalGetFieldAccessorTable();

    protected boolean isClean()
    {
      return this.isClean;
    }

    public boolean isInitialized()
    {
      Iterator localIterator = getDescriptorForType().getFields().iterator();
      while (true)
        if (localIterator.hasNext())
        {
          Object localObject = (Descriptors.FieldDescriptor)localIterator.next();
          if ((((Descriptors.FieldDescriptor)localObject).isRequired()) && (!hasField((Descriptors.FieldDescriptor)localObject)))
            return false;
          if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          {
            if (((Descriptors.FieldDescriptor)localObject).isRepeated())
            {
              localObject = ((List)getField((Descriptors.FieldDescriptor)localObject)).iterator();
              if (!((Iterator)localObject).hasNext())
                continue;
              if (((Message)((Iterator)localObject).next()).isInitialized())
                break;
              return false;
            }
            if ((hasField((Descriptors.FieldDescriptor)localObject)) && (!((Message)getField((Descriptors.FieldDescriptor)localObject)).isInitialized()))
              return false;
          }
        }
      return true;
    }

    protected void markClean()
    {
      this.isClean = true;
    }

    public final BuilderType mergeUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = UnknownFieldSet.newBuilder(this.unknownFields).mergeFrom(paramUnknownFieldSet).build();
      onChanged();
      return this;
    }

    public Message.Builder newBuilderForField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      return GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).newBuilder();
    }

    protected void onBuilt()
    {
      if (this.builderParent != null)
        markClean();
    }

    protected final void onChanged()
    {
      if ((this.isClean) && (this.builderParent != null))
      {
        this.builderParent.markDirty();
        this.isClean = false;
      }
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return paramBuilder.mergeFieldFrom(paramInt, paramCodedInputStream);
    }

    public BuilderType setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).set(this, paramObject);
      return this;
    }

    public BuilderType setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      GeneratedMessage.FieldAccessorTable.access$100(internalGetFieldAccessorTable(), paramFieldDescriptor).setRepeated(this, paramInt, paramObject);
      return this;
    }

    public final BuilderType setUnknownFields(UnknownFieldSet paramUnknownFieldSet)
    {
      this.unknownFields = paramUnknownFieldSet;
      onChanged();
      return this;
    }

    private class BuilderParentImpl
      implements GeneratedMessage.BuilderParent
    {
      private BuilderParentImpl()
      {
      }

      public void markDirty()
      {
        GeneratedMessage.Builder.this.onChanged();
      }
    }
  }

  protected static abstract interface BuilderParent
  {
    public abstract void markDirty();
  }

  public static abstract class ExtendableBuilder<MessageType extends GeneratedMessage.ExtendableMessage, BuilderType extends ExtendableBuilder> extends GeneratedMessage.Builder<BuilderType>
    implements GeneratedMessage.ExtendableMessageOrBuilder<MessageType>
  {
    private FieldSet<Descriptors.FieldDescriptor> extensions = FieldSet.emptySet();

    protected ExtendableBuilder()
    {
    }

    protected ExtendableBuilder(GeneratedMessage.BuilderParent paramBuilderParent)
    {
      super();
    }

    private FieldSet<Descriptors.FieldDescriptor> buildExtensions()
    {
      this.extensions.makeImmutable();
      return this.extensions;
    }

    private void ensureExtensionsIsMutable()
    {
      if (this.extensions.isImmutable())
        this.extensions = this.extensions.clone();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getDescriptor().getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("Extension is for type \"" + paramGeneratedExtension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
    }

    public final <Type> BuilderType addExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.addRepeatedField(localFieldDescriptor, GeneratedMessage.GeneratedExtension.access$1000(paramGeneratedExtension, paramType));
      onChanged();
      return this;
    }

    public BuilderType addRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.addRepeatedField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.addRepeatedField(paramFieldDescriptor, paramObject);
    }

    public BuilderType clear()
    {
      this.extensions = FieldSet.emptySet();
      return (ExtendableBuilder)super.clear();
    }

    public final <Type> BuilderType clearExtension(GeneratedMessage.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.clearField(paramGeneratedExtension.getDescriptor());
      onChanged();
      return this;
    }

    public BuilderType clearField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.clearField(paramFieldDescriptor);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.clearField(paramFieldDescriptor);
    }

    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      Map localMap = super.getAllFieldsMutable();
      localMap.putAll(this.extensions.getAllFields());
      return Collections.unmodifiableMap(localMap);
    }

    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated())
          return Collections.emptyList();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          return paramGeneratedExtension.getMessageDefaultInstance();
        return GeneratedMessage.GeneratedExtension.access$400(paramGeneratedExtension, localFieldDescriptor.getDefaultValue());
      }
      return GeneratedMessage.GeneratedExtension.access$400(paramGeneratedExtension, localObject);
    }

    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return GeneratedMessage.GeneratedExtension.access$500(paramGeneratedExtension, this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }

    public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      paramGeneratedExtension = paramGeneratedExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(paramGeneratedExtension);
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject2 = this.extensions.getField(paramFieldDescriptor);
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
      return super.getField(paramFieldDescriptor);
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }

    public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.getDescriptor());
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }

    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }

    protected final void mergeExtensionFields(GeneratedMessage.ExtendableMessage paramExtendableMessage)
    {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(GeneratedMessage.ExtendableMessage.access$600(paramExtendableMessage));
      onChanged();
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return AbstractMessage.Builder.mergeFieldFrom(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, getDescriptorForType(), this, null, paramInt);
    }

    public final <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.setRepeatedField(localFieldDescriptor, paramInt, GeneratedMessage.GeneratedExtension.access$1000(paramGeneratedExtension, paramType));
      onChanged();
      return this;
    }

    public final <Type> BuilderType setExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      this.extensions.setField(localFieldDescriptor, GeneratedMessage.GeneratedExtension.access$900(paramGeneratedExtension, paramType));
      onChanged();
      return this;
    }

    public BuilderType setField(Descriptors.FieldDescriptor paramFieldDescriptor, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setField(paramFieldDescriptor, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setField(paramFieldDescriptor, paramObject);
    }

    public BuilderType setRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt, Object paramObject)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        ensureExtensionsIsMutable();
        this.extensions.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
        onChanged();
        return this;
      }
      return (ExtendableBuilder)super.setRepeatedField(paramFieldDescriptor, paramInt, paramObject);
    }
  }

  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage> extends GeneratedMessage
    implements GeneratedMessage.ExtendableMessageOrBuilder<MessageType>
  {
    private final FieldSet<Descriptors.FieldDescriptor> extensions;

    protected ExtendableMessage()
    {
      this.extensions = FieldSet.newFieldSet();
    }

    protected ExtendableMessage(GeneratedMessage.ExtendableBuilder<MessageType, ?> paramExtendableBuilder)
    {
      super();
      this.extensions = paramExtendableBuilder.buildExtensions();
    }

    private void verifyContainingType(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
    }

    private void verifyExtensionContainingType(GeneratedMessage.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getDescriptor().getContainingType() != getDescriptorForType())
        throw new IllegalArgumentException("Extension is for type \"" + paramGeneratedExtension.getDescriptor().getContainingType().getFullName() + "\" which does not match message type \"" + getDescriptorForType().getFullName() + "\".");
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    protected int extensionsSerializedSize()
    {
      return this.extensions.getSerializedSize();
    }

    protected int extensionsSerializedSizeAsMessageSet()
    {
      return this.extensions.getMessageSetSerializedSize();
    }

    public Map<Descriptors.FieldDescriptor, Object> getAllFields()
    {
      Map localMap = super.getAllFieldsMutable();
      localMap.putAll(getExtensionFields());
      return Collections.unmodifiableMap(localMap);
    }

    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      Object localObject = this.extensions.getField(localFieldDescriptor);
      if (localObject == null)
      {
        if (localFieldDescriptor.isRepeated())
          return Collections.emptyList();
        if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
          return paramGeneratedExtension.getMessageDefaultInstance();
        return GeneratedMessage.GeneratedExtension.access$400(paramGeneratedExtension, localFieldDescriptor.getDefaultValue());
      }
      return GeneratedMessage.GeneratedExtension.access$400(paramGeneratedExtension, localObject);
    }

    public final <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Descriptors.FieldDescriptor localFieldDescriptor = paramGeneratedExtension.getDescriptor();
      return GeneratedMessage.GeneratedExtension.access$500(paramGeneratedExtension, this.extensions.getRepeatedField(localFieldDescriptor, paramInt));
    }

    public final <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      paramGeneratedExtension = paramGeneratedExtension.getDescriptor();
      return this.extensions.getRepeatedFieldCount(paramGeneratedExtension);
    }

    protected Map<Descriptors.FieldDescriptor, Object> getExtensionFields()
    {
      return this.extensions.getAllFields();
    }

    public Object getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        Object localObject2 = this.extensions.getField(paramFieldDescriptor);
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
      return super.getField(paramFieldDescriptor);
    }

    public Object getRepeatedField(Descriptors.FieldDescriptor paramFieldDescriptor, int paramInt)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedField(paramFieldDescriptor, paramInt);
      }
      return super.getRepeatedField(paramFieldDescriptor, paramInt);
    }

    public int getRepeatedFieldCount(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.getRepeatedFieldCount(paramFieldDescriptor);
      }
      return super.getRepeatedFieldCount(paramFieldDescriptor);
    }

    public final <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(paramGeneratedExtension.getDescriptor());
    }

    public boolean hasField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.isExtension())
      {
        verifyContainingType(paramFieldDescriptor);
        return this.extensions.hasField(paramFieldDescriptor);
      }
      return super.hasField(paramFieldDescriptor);
    }

    public boolean isInitialized()
    {
      return (super.isInitialized()) && (extensionsAreInitialized());
    }

    protected void makeExtensionsImmutable()
    {
      this.extensions.makeImmutable();
    }

    protected ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter()
    {
      return new ExtensionWriter(false, null);
    }

    protected ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter()
    {
      return new ExtensionWriter(true, null);
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, UnknownFieldSet.Builder paramBuilder, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return AbstractMessage.Builder.mergeFieldFrom(paramCodedInputStream, paramBuilder, paramExtensionRegistryLite, getDescriptorForType(), null, this.extensions, paramInt);
    }

    protected class ExtensionWriter
    {
      private final Iterator<Map.Entry<Descriptors.FieldDescriptor, Object>> iter = GeneratedMessage.ExtendableMessage.this.extensions.iterator();
      private final boolean messageSetWireFormat;
      private Map.Entry<Descriptors.FieldDescriptor, Object> next;

      private ExtensionWriter(boolean arg2)
      {
        if (this.iter.hasNext())
          this.next = ((Map.Entry)this.iter.next());
        boolean bool;
        this.messageSetWireFormat = bool;
      }

      public void writeUntil(int paramInt, CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        while ((this.next != null) && (((Descriptors.FieldDescriptor)this.next.getKey()).getNumber() < paramInt))
        {
          Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.next.getKey();
          if ((this.messageSetWireFormat) && (localFieldDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptor.isRepeated()))
            if ((this.next instanceof LazyField.LazyEntry))
              paramCodedOutputStream.writeRawMessageSetExtension(localFieldDescriptor.getNumber(), ((LazyField.LazyEntry)this.next).getField().toByteString());
          while (true)
          {
            if (!this.iter.hasNext())
              break label165;
            this.next = ((Map.Entry)this.iter.next());
            break;
            paramCodedOutputStream.writeMessageSetExtension(localFieldDescriptor.getNumber(), (Message)this.next.getValue());
            continue;
            FieldSet.writeField(localFieldDescriptor, this.next.getValue(), paramCodedOutputStream);
          }
          label165: this.next = null;
        }
      }
    }
  }

  public static abstract interface ExtendableMessageOrBuilder<MessageType extends GeneratedMessage.ExtendableMessage> extends MessageOrBuilder
  {
    public abstract <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension);

    public abstract <Type> Type getExtension(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt);

    public abstract <Type> int getExtensionCount(GeneratedMessage.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension);

    public abstract <Type> boolean hasExtension(GeneratedMessage.GeneratedExtension<MessageType, Type> paramGeneratedExtension);
  }

  private static abstract interface ExtensionDescriptorRetriever
  {
    public abstract Descriptors.FieldDescriptor getDescriptor();
  }

  public static final class FieldAccessorTable
  {
    private String[] camelCaseNames;
    private final Descriptors.Descriptor descriptor;
    private final FieldAccessor[] fields;
    private volatile boolean initialized;

    public FieldAccessorTable(Descriptors.Descriptor paramDescriptor, String[] paramArrayOfString)
    {
      this.descriptor = paramDescriptor;
      this.camelCaseNames = paramArrayOfString;
      this.fields = new FieldAccessor[paramDescriptor.getFields().size()];
      this.initialized = false;
    }

    public FieldAccessorTable(Descriptors.Descriptor paramDescriptor, String[] paramArrayOfString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
    {
      this(paramDescriptor, paramArrayOfString);
      ensureFieldAccessorsInitialized(paramClass, paramClass1);
    }

    private FieldAccessor getField(Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (paramFieldDescriptor.getContainingType() != this.descriptor)
        throw new IllegalArgumentException("FieldDescriptor does not match message type.");
      if (paramFieldDescriptor.isExtension())
        throw new IllegalArgumentException("This type does not have extensions.");
      return this.fields[paramFieldDescriptor.getIndex()];
    }

    public FieldAccessorTable ensureFieldAccessorsInitialized(Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
    {
      if (this.initialized)
        return this;
      try
      {
        if (this.initialized)
          return this;
      }
      finally
      {
      }
      int i = 0;
      while (true)
      {
        if (i < this.fields.length)
        {
          Descriptors.FieldDescriptor localFieldDescriptor = (Descriptors.FieldDescriptor)this.descriptor.getFields().get(i);
          if (localFieldDescriptor.isRepeated())
          {
            if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
              this.fields[i] = new RepeatedMessageFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
            else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
              this.fields[i] = new RepeatedEnumFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
            else
              this.fields[i] = new RepeatedFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
          }
          else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.MESSAGE)
            this.fields[i] = new SingularMessageFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
          else if (localFieldDescriptor.getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
            this.fields[i] = new SingularEnumFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
          else
            this.fields[i] = new SingularFieldAccessor(localFieldDescriptor, this.camelCaseNames[i], paramClass, paramClass1);
        }
        else
        {
          this.initialized = true;
          this.camelCaseNames = null;
          return this;
        }
        i += 1;
      }
    }

    private static abstract interface FieldAccessor
    {
      public abstract void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject);

      public abstract void clear(GeneratedMessage.Builder paramBuilder);

      public abstract Object get(GeneratedMessage.Builder paramBuilder);

      public abstract Object get(GeneratedMessage paramGeneratedMessage);

      public abstract Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder);

      public abstract Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt);

      public abstract Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt);

      public abstract int getRepeatedCount(GeneratedMessage.Builder paramBuilder);

      public abstract int getRepeatedCount(GeneratedMessage paramGeneratedMessage);

      public abstract boolean has(GeneratedMessage.Builder paramBuilder);

      public abstract boolean has(GeneratedMessage paramGeneratedMessage);

      public abstract Message.Builder newBuilder();

      public abstract void set(GeneratedMessage.Builder paramBuilder, Object paramObject);

      public abstract void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject);
    }

    private static final class RepeatedEnumFieldAccessor extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      private final Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });

      RepeatedEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        ArrayList localArrayList = new ArrayList();
        paramBuilder = ((List)super.get(paramBuilder)).iterator();
        while (paramBuilder.hasNext())
        {
          Object localObject = paramBuilder.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        ArrayList localArrayList = new ArrayList();
        paramGeneratedMessage = ((List)super.get(paramGeneratedMessage)).iterator();
        while (paramGeneratedMessage.hasNext())
        {
          Object localObject = paramGeneratedMessage.next();
          localArrayList.add(GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, localObject, new Object[0]));
        }
        return Collections.unmodifiableList(localArrayList);
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramBuilder, paramInt), new Object[0]);
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.getRepeated(paramGeneratedMessage, paramInt), new Object[0]);
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }

    private static class RepeatedFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Method addRepeatedMethod;
      protected final Method clearMethod;
      protected final Method getCountMethod;
      protected final Method getCountMethodBuilder;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final Method getRepeatedMethod;
      protected final Method getRepeatedMethodBuilder;
      protected final Method setRepeatedMethod;
      protected final Class type;

      RepeatedFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        this.getMethod = GeneratedMessage.getMethodOrDie(paramClass, "get" + paramString + "List", new Class[0]);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString + "List", new Class[0]);
        this.getRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass, "get" + paramString, new Class[] { Integer.TYPE });
        this.getRepeatedMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString, new Class[] { Integer.TYPE });
        this.type = this.getRepeatedMethod.getReturnType();
        this.setRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass1, "set" + paramString, new Class[] { Integer.TYPE, this.type });
        this.addRepeatedMethod = GeneratedMessage.getMethodOrDie(paramClass1, "add" + paramString, new Class[] { this.type });
        this.getCountMethod = GeneratedMessage.getMethodOrDie(paramClass, "get" + paramString + "Count", new Class[0]);
        this.getCountMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString + "Count", new Class[0]);
        this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass1, "clear" + paramString, new Class[0]);
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.addRepeatedMethod, paramBuilder, new Object[] { paramObject });
      }

      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }

      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethodBuilder, paramBuilder, new Object[] { Integer.valueOf(paramInt) });
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        return GeneratedMessage.invokeOrDie(this.getRepeatedMethod, paramGeneratedMessage, new Object[] { Integer.valueOf(paramInt) });
      }

      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethodBuilder, paramBuilder, new Object[0])).intValue();
      }

      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        return ((Integer)GeneratedMessage.invokeOrDie(this.getCountMethod, paramGeneratedMessage, new Object[0])).intValue();
      }

      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }

      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("hasField() called on a repeated field.");
      }

      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        clear(paramBuilder);
        paramObject = ((List)paramObject).iterator();
        while (paramObject.hasNext())
          addRepeated(paramBuilder, paramObject.next());
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setRepeatedMethod, paramBuilder, new Object[] { Integer.valueOf(paramInt), paramObject });
      }
    }

    private static final class RepeatedMessageFieldAccessor extends GeneratedMessage.FieldAccessorTable.RepeatedFieldAccessor
    {
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

      RepeatedMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
      }

      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject))
          return paramObject;
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).build();
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.addRepeated(paramBuilder, coerceType(paramObject));
      }

      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        super.setRepeated(paramBuilder, paramInt, coerceType(paramObject));
      }
    }

    private static final class SingularEnumFieldAccessor extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private Method getValueDescriptorMethod = GeneratedMessage.getMethodOrDie(this.type, "getValueDescriptor", new Class[0]);
      private Method valueOfMethod = GeneratedMessage.getMethodOrDie(this.type, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });

      SingularEnumFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramBuilder), new Object[0]);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getValueDescriptorMethod, super.get(paramGeneratedMessage), new Object[0]);
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, GeneratedMessage.invokeOrDie(this.valueOfMethod, null, new Object[] { paramObject }));
      }
    }

    private static class SingularFieldAccessor
      implements GeneratedMessage.FieldAccessorTable.FieldAccessor
    {
      protected final Method clearMethod;
      protected final Method getMethod;
      protected final Method getMethodBuilder;
      protected final Method hasMethod;
      protected final Method hasMethodBuilder;
      protected final Method setMethod;
      protected final Class<?> type;

      SingularFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        this.getMethod = GeneratedMessage.getMethodOrDie(paramClass, "get" + paramString, new Class[0]);
        this.getMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString, new Class[0]);
        this.type = this.getMethod.getReturnType();
        this.setMethod = GeneratedMessage.getMethodOrDie(paramClass1, "set" + paramString, new Class[] { this.type });
        this.hasMethod = GeneratedMessage.getMethodOrDie(paramClass, "has" + paramString, new Class[0]);
        this.hasMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "has" + paramString, new Class[0]);
        this.clearMethod = GeneratedMessage.getMethodOrDie(paramClass1, "clear" + paramString, new Class[0]);
      }

      public void addRepeated(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        throw new UnsupportedOperationException("addRepeatedField() called on a singular field.");
      }

      public void clear(GeneratedMessage.Builder paramBuilder)
      {
        GeneratedMessage.invokeOrDie(this.clearMethod, paramBuilder, new Object[0]);
      }

      public Object get(GeneratedMessage.Builder paramBuilder)
      {
        return GeneratedMessage.invokeOrDie(this.getMethodBuilder, paramBuilder, new Object[0]);
      }

      public Object get(GeneratedMessage paramGeneratedMessage)
      {
        return GeneratedMessage.invokeOrDie(this.getMethod, paramGeneratedMessage, new Object[0]);
      }

      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getFieldBuilder() called on a non-Message type.");
      }

      public Object getRepeated(GeneratedMessage.Builder paramBuilder, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }

      public Object getRepeated(GeneratedMessage paramGeneratedMessage, int paramInt)
      {
        throw new UnsupportedOperationException("getRepeatedField() called on a singular field.");
      }

      public int getRepeatedCount(GeneratedMessage.Builder paramBuilder)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }

      public int getRepeatedCount(GeneratedMessage paramGeneratedMessage)
      {
        throw new UnsupportedOperationException("getRepeatedFieldSize() called on a singular field.");
      }

      public boolean has(GeneratedMessage.Builder paramBuilder)
      {
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethodBuilder, paramBuilder, new Object[0])).booleanValue();
      }

      public boolean has(GeneratedMessage paramGeneratedMessage)
      {
        return ((Boolean)GeneratedMessage.invokeOrDie(this.hasMethod, paramGeneratedMessage, new Object[0])).booleanValue();
      }

      public Message.Builder newBuilder()
      {
        throw new UnsupportedOperationException("newBuilderForField() called on a non-Message type.");
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        GeneratedMessage.invokeOrDie(this.setMethod, paramBuilder, new Object[] { paramObject });
      }

      public void setRepeated(GeneratedMessage.Builder paramBuilder, int paramInt, Object paramObject)
      {
        throw new UnsupportedOperationException("setRepeatedField() called on a singular field.");
      }
    }

    private static final class SingularMessageFieldAccessor extends GeneratedMessage.FieldAccessorTable.SingularFieldAccessor
    {
      private final Method getBuilderMethodBuilder;
      private final Method newBuilderMethod = GeneratedMessage.getMethodOrDie(this.type, "newBuilder", new Class[0]);

      SingularMessageFieldAccessor(Descriptors.FieldDescriptor paramFieldDescriptor, String paramString, Class<? extends GeneratedMessage> paramClass, Class<? extends GeneratedMessage.Builder> paramClass1)
      {
        super(paramString, paramClass, paramClass1);
        this.getBuilderMethodBuilder = GeneratedMessage.getMethodOrDie(paramClass1, "get" + paramString + "Builder", new Class[0]);
      }

      private Object coerceType(Object paramObject)
      {
        if (this.type.isInstance(paramObject))
          return paramObject;
        return ((Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0])).mergeFrom((Message)paramObject).buildPartial();
      }

      public Message.Builder getBuilder(GeneratedMessage.Builder paramBuilder)
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.getBuilderMethodBuilder, paramBuilder, new Object[0]);
      }

      public Message.Builder newBuilder()
      {
        return (Message.Builder)GeneratedMessage.invokeOrDie(this.newBuilderMethod, null, new Object[0]);
      }

      public void set(GeneratedMessage.Builder paramBuilder, Object paramObject)
      {
        super.set(paramBuilder, coerceType(paramObject));
      }
    }
  }

  public static final class GeneratedExtension<ContainingType extends Message, Type>
  {
    private GeneratedMessage.ExtensionDescriptorRetriever descriptorRetriever;
    private final Method enumGetValueDescriptor;
    private final Method enumValueOf;
    private final Message messageDefaultInstance;
    private final Class singularType;

    private GeneratedExtension(GeneratedMessage.ExtensionDescriptorRetriever paramExtensionDescriptorRetriever, Class paramClass, Message paramMessage)
    {
      if ((Message.class.isAssignableFrom(paramClass)) && (!paramClass.isInstance(paramMessage)))
        throw new IllegalArgumentException("Bad messageDefaultInstance for " + paramClass.getName());
      this.descriptorRetriever = paramExtensionDescriptorRetriever;
      this.singularType = paramClass;
      this.messageDefaultInstance = paramMessage;
      if (ProtocolMessageEnum.class.isAssignableFrom(paramClass))
      {
        this.enumValueOf = GeneratedMessage.getMethodOrDie(paramClass, "valueOf", new Class[] { Descriptors.EnumValueDescriptor.class });
        this.enumGetValueDescriptor = GeneratedMessage.getMethodOrDie(paramClass, "getValueDescriptor", new Class[0]);
        return;
      }
      this.enumValueOf = null;
      this.enumGetValueDescriptor = null;
    }

    private Object fromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      if (localFieldDescriptor.isRepeated())
      {
        Object localObject;
        if (localFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.MESSAGE)
        {
          localObject = paramObject;
          if (localFieldDescriptor.getJavaType() != Descriptors.FieldDescriptor.JavaType.ENUM);
        }
        else
        {
          localObject = new ArrayList();
          paramObject = ((List)paramObject).iterator();
          while (paramObject.hasNext())
            ((List)localObject).add(singularFromReflectionType(paramObject.next()));
        }
        return localObject;
      }
      return singularFromReflectionType(paramObject);
    }

    private Object singularFromReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      default:
      case 1:
        do
          return paramObject;
        while (this.singularType.isInstance(paramObject));
        return this.messageDefaultInstance.newBuilderForType().mergeFrom((Message)paramObject).build();
      case 2:
      }
      return GeneratedMessage.invokeOrDie(this.enumValueOf, null, new Object[] { (Descriptors.EnumValueDescriptor)paramObject });
    }

    private Object singularToReflectionType(Object paramObject)
    {
      Descriptors.FieldDescriptor localFieldDescriptor = getDescriptor();
      switch (GeneratedMessage.2.$SwitchMap$com$google$protobuf$Descriptors$FieldDescriptor$JavaType[localFieldDescriptor.getJavaType().ordinal()])
      {
      default:
        return paramObject;
      case 2:
      }
      return GeneratedMessage.invokeOrDie(this.enumGetValueDescriptor, paramObject, new Object[0]);
    }

    private Object toReflectionType(Object paramObject)
    {
      Object localObject = getDescriptor();
      if (((Descriptors.FieldDescriptor)localObject).isRepeated())
      {
        if (((Descriptors.FieldDescriptor)localObject).getJavaType() == Descriptors.FieldDescriptor.JavaType.ENUM)
        {
          localObject = new ArrayList();
          Iterator localIterator = ((List)paramObject).iterator();
          while (true)
          {
            paramObject = localObject;
            if (!localIterator.hasNext())
              break;
            ((List)localObject).add(singularToReflectionType(localIterator.next()));
          }
        }
        return paramObject;
      }
      return singularToReflectionType(paramObject);
    }

    public Descriptors.FieldDescriptor getDescriptor()
    {
      if (this.descriptorRetriever == null)
        throw new IllegalStateException("getDescriptor() called before internalInit()");
      return this.descriptorRetriever.getDescriptor();
    }

    public Message getMessageDefaultInstance()
    {
      return this.messageDefaultInstance;
    }

    public void internalInit(final Descriptors.FieldDescriptor paramFieldDescriptor)
    {
      if (this.descriptorRetriever != null)
        throw new IllegalStateException("Already initialized.");
      this.descriptorRetriever = new GeneratedMessage.ExtensionDescriptorRetriever()
      {
        public Descriptors.FieldDescriptor getDescriptor()
        {
          return paramFieldDescriptor;
        }
      };
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.GeneratedMessage
 * JD-Core Version:    0.6.2
 */