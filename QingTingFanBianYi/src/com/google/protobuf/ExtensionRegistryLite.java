package com.google.protobuf;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ExtensionRegistryLite
{
  private static final ExtensionRegistryLite EMPTY = new ExtensionRegistryLite(true);
  private static volatile boolean eagerlyParseMessageSets = false;
  private final Map<ObjectIntPair, GeneratedMessageLite.GeneratedExtension<?, ?>> extensionsByNumber;

  ExtensionRegistryLite()
  {
    this.extensionsByNumber = new HashMap();
  }

  ExtensionRegistryLite(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    if (paramExtensionRegistryLite == EMPTY)
    {
      this.extensionsByNumber = Collections.emptyMap();
      return;
    }
    this.extensionsByNumber = Collections.unmodifiableMap(paramExtensionRegistryLite.extensionsByNumber);
  }

  private ExtensionRegistryLite(boolean paramBoolean)
  {
    this.extensionsByNumber = Collections.emptyMap();
  }

  public static ExtensionRegistryLite getEmptyRegistry()
  {
    return EMPTY;
  }

  public static boolean isEagerlyParseMessageSets()
  {
    return eagerlyParseMessageSets;
  }

  public static ExtensionRegistryLite newInstance()
  {
    return new ExtensionRegistryLite();
  }

  public static void setEagerlyParseMessageSets(boolean paramBoolean)
  {
    eagerlyParseMessageSets = paramBoolean;
  }

  public final void add(GeneratedMessageLite.GeneratedExtension<?, ?> paramGeneratedExtension)
  {
    this.extensionsByNumber.put(new ObjectIntPair(paramGeneratedExtension.getContainingTypeDefaultInstance(), paramGeneratedExtension.getNumber()), paramGeneratedExtension);
  }

  public <ContainingType extends MessageLite> GeneratedMessageLite.GeneratedExtension<ContainingType, ?> findLiteExtensionByNumber(ContainingType paramContainingType, int paramInt)
  {
    return (GeneratedMessageLite.GeneratedExtension)this.extensionsByNumber.get(new ObjectIntPair(paramContainingType, paramInt));
  }

  public ExtensionRegistryLite getUnmodifiable()
  {
    return new ExtensionRegistryLite(this);
  }

  private static final class ObjectIntPair
  {
    private final int number;
    private final Object object;

    ObjectIntPair(Object paramObject, int paramInt)
    {
      this.object = paramObject;
      this.number = paramInt;
    }

    public boolean equals(Object paramObject)
    {
      if (!(paramObject instanceof ObjectIntPair));
      do
      {
        return false;
        paramObject = (ObjectIntPair)paramObject;
      }
      while ((this.object != paramObject.object) || (this.number != paramObject.number));
      return true;
    }

    public int hashCode()
    {
      return System.identityHashCode(this.object) * 65535 + this.number;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.ExtensionRegistryLite
 * JD-Core Version:    0.6.2
 */