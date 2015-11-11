package com.google.protobuf;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class UninitializedMessageException extends RuntimeException
{
  private static final long serialVersionUID = -7466929953374883507L;
  private final List<String> missingFields;

  public UninitializedMessageException(MessageLite paramMessageLite)
  {
    super("Message was missing required fields.  (Lite runtime could not determine which fields were missing).");
    this.missingFields = null;
  }

  public UninitializedMessageException(List<String> paramList)
  {
    super(buildDescription(paramList));
    this.missingFields = paramList;
  }

  private static String buildDescription(List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder("Message missing required fields: ");
    int i = 1;
    paramList = paramList.iterator();
    if (paramList.hasNext())
    {
      String str = (String)paramList.next();
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuilder.append(str);
        break;
        localStringBuilder.append(", ");
      }
    }
    return localStringBuilder.toString();
  }

  public InvalidProtocolBufferException asInvalidProtocolBufferException()
  {
    return new InvalidProtocolBufferException(getMessage());
  }

  public List<String> getMissingFields()
  {
    return Collections.unmodifiableList(this.missingFields);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.UninitializedMessageException
 * JD-Core Version:    0.6.2
 */