package com.google.android.apps.analytics;

public class Item
{
  private final String itemCategory;
  private final long itemCount;
  private final String itemName;
  private final double itemPrice;
  private final String itemSKU;
  private final String orderId;

  private Item(Builder paramBuilder)
  {
    this.orderId = paramBuilder.orderId;
    this.itemSKU = paramBuilder.itemSKU;
    this.itemPrice = paramBuilder.itemPrice;
    this.itemCount = paramBuilder.itemCount;
    this.itemName = paramBuilder.itemName;
    this.itemCategory = paramBuilder.itemCategory;
  }

  String getItemCategory()
  {
    return this.itemCategory;
  }

  long getItemCount()
  {
    return this.itemCount;
  }

  String getItemName()
  {
    return this.itemName;
  }

  double getItemPrice()
  {
    return this.itemPrice;
  }

  String getItemSKU()
  {
    return this.itemSKU;
  }

  String getOrderId()
  {
    return this.orderId;
  }

  public static class Builder
  {
    private String itemCategory = null;
    private final long itemCount;
    private String itemName = null;
    private final double itemPrice;
    private final String itemSKU;
    private final String orderId;

    public Builder(String paramString1, String paramString2, double paramDouble, long paramLong)
    {
      if ((paramString1 == null) || (paramString1.trim().length() == 0))
        throw new IllegalArgumentException("orderId must not be empty or null");
      if ((paramString2 == null) || (paramString2.trim().length() == 0))
        throw new IllegalArgumentException("itemSKU must not be empty or null");
      this.orderId = paramString1;
      this.itemSKU = paramString2;
      this.itemPrice = paramDouble;
      this.itemCount = paramLong;
    }

    public Item build()
    {
      return new Item(this, null);
    }

    public Builder setItemCategory(String paramString)
    {
      this.itemCategory = paramString;
      return this;
    }

    public Builder setItemName(String paramString)
    {
      this.itemName = paramString;
      return this;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.Item
 * JD-Core Version:    0.6.2
 */