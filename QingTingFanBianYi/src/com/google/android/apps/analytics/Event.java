package com.google.android.apps.analytics;

class Event
{
  static final String INSTALL_EVENT_CATEGORY = "__##GOOGLEINSTALL##__";
  static final String ITEM_CATEGORY = "__##GOOGLEITEM##__";
  static final String PAGEVIEW_EVENT_CATEGORY = "__##GOOGLEPAGEVIEW##__";
  static final String TRANSACTION_CATEGORY = "__##GOOGLETRANSACTION##__";
  final String accountId;
  final String action;
  private int adHitId;
  private boolean anonymizeIp;
  final String category;
  CustomVariableBuffer customVariableBuffer;
  final long eventId;
  private Item item;
  final String label;
  private int randomVal;
  final int screenHeight;
  final int screenWidth;
  private int timestampCurrent;
  private int timestampFirst;
  private int timestampPrevious;
  private Transaction transaction;
  private boolean useServerTime;
  private int userId;
  final int value;
  private int visits;

  Event(long paramLong, String paramString1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, String paramString2, String paramString3, String paramString4, int paramInt6, int paramInt7, int paramInt8)
  {
    this.eventId = paramLong;
    this.accountId = paramString1;
    this.randomVal = paramInt1;
    this.timestampFirst = paramInt2;
    this.timestampPrevious = paramInt3;
    this.timestampCurrent = paramInt4;
    this.visits = paramInt5;
    this.category = paramString2;
    this.action = paramString3;
    this.label = paramString4;
    this.value = paramInt6;
    this.screenHeight = paramInt8;
    this.screenWidth = paramInt7;
    this.userId = -1;
    this.useServerTime = false;
  }

  Event(Event paramEvent, String paramString)
  {
    this(paramEvent.eventId, paramString, paramEvent.randomVal, paramEvent.timestampFirst, paramEvent.timestampPrevious, paramEvent.timestampCurrent, paramEvent.visits, paramEvent.category, paramEvent.action, paramEvent.label, paramEvent.value, paramEvent.screenWidth, paramEvent.screenHeight);
    this.adHitId = paramEvent.adHitId;
    this.userId = paramEvent.userId;
    this.anonymizeIp = paramEvent.anonymizeIp;
    this.useServerTime = paramEvent.useServerTime;
    this.customVariableBuffer = paramEvent.customVariableBuffer;
    this.transaction = paramEvent.transaction;
    this.item = paramEvent.item;
  }

  Event(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2, int paramInt3)
  {
    this(-1L, paramString1, -1, -1, -1, -1, -1, paramString2, paramString3, paramString4, paramInt1, paramInt2, paramInt3);
  }

  int getAdHitId()
  {
    return this.adHitId;
  }

  boolean getAnonymizeIp()
  {
    return this.anonymizeIp;
  }

  public CustomVariableBuffer getCustomVariableBuffer()
  {
    return this.customVariableBuffer;
  }

  public Item getItem()
  {
    return this.item;
  }

  int getRandomVal()
  {
    return this.randomVal;
  }

  int getTimestampCurrent()
  {
    return this.timestampCurrent;
  }

  int getTimestampFirst()
  {
    return this.timestampFirst;
  }

  int getTimestampPrevious()
  {
    return this.timestampPrevious;
  }

  public Transaction getTransaction()
  {
    return this.transaction;
  }

  boolean getUseServerTime()
  {
    return this.useServerTime;
  }

  int getUserId()
  {
    return this.userId;
  }

  int getVisits()
  {
    return this.visits;
  }

  public boolean isSessionInitialized()
  {
    return this.timestampFirst != -1;
  }

  void setAdHitId(int paramInt)
  {
    this.adHitId = paramInt;
  }

  void setAnonymizeIp(boolean paramBoolean)
  {
    this.anonymizeIp = paramBoolean;
  }

  public void setCustomVariableBuffer(CustomVariableBuffer paramCustomVariableBuffer)
  {
    this.customVariableBuffer = paramCustomVariableBuffer;
  }

  public void setItem(Item paramItem)
  {
    if (!this.category.equals("__##GOOGLEITEM##__"))
      throw new IllegalStateException("Attempted to add an item to an event of type " + this.category);
    this.item = paramItem;
  }

  void setRandomVal(int paramInt)
  {
    this.randomVal = paramInt;
  }

  void setTimestampCurrent(int paramInt)
  {
    this.timestampCurrent = paramInt;
  }

  void setTimestampFirst(int paramInt)
  {
    this.timestampFirst = paramInt;
  }

  void setTimestampPrevious(int paramInt)
  {
    this.timestampPrevious = paramInt;
  }

  public void setTransaction(Transaction paramTransaction)
  {
    if (!this.category.equals("__##GOOGLETRANSACTION##__"))
      throw new IllegalStateException("Attempted to add a transction to an event of type " + this.category);
    this.transaction = paramTransaction;
  }

  void setUseServerTime(boolean paramBoolean)
  {
    this.useServerTime = paramBoolean;
  }

  void setUserId(int paramInt)
  {
    this.userId = paramInt;
  }

  void setVisits(int paramInt)
  {
    this.visits = paramInt;
  }

  public String toString()
  {
    return "id:" + this.eventId + " " + "random:" + this.randomVal + " " + "timestampCurrent:" + this.timestampCurrent + " " + "timestampPrevious:" + this.timestampPrevious + " " + "timestampFirst:" + this.timestampFirst + " " + "visits:" + this.visits + " " + "value:" + this.value + " " + "category:" + this.category + " " + "action:" + this.action + " " + "label:" + this.label + " " + "width:" + this.screenWidth + " " + "height:" + this.screenHeight;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.Event
 * JD-Core Version:    0.6.2
 */