package com.google.android.apps.analytics;

class CustomVariable
{
  public static final String INDEX_ERROR_MSG = "Index must be between 1 and 5 inclusive.";
  public static final int MAX_CUSTOM_VARIABLES = 5;
  public static final int MAX_CUSTOM_VARIABLE_LENGTH = 64;
  public static final int PAGE_SCOPE = 3;
  public static final int SESSION_SCOPE = 2;
  public static final int VISITOR_SCOPE = 1;
  private final int index;
  private final String name;
  private final int scope;
  private final String value;

  public CustomVariable(int paramInt, String paramString1, String paramString2)
  {
    this(paramInt, paramString1, paramString2, 3);
  }

  public CustomVariable(int paramInt1, String paramString1, String paramString2, int paramInt2)
  {
    if ((paramInt2 != 1) && (paramInt2 != 2) && (paramInt2 != 3))
      throw new IllegalArgumentException("Invalid Scope:" + paramInt2);
    if ((paramInt1 < 1) || (paramInt1 > 5))
      throw new IllegalArgumentException("Index must be between 1 and 5 inclusive.");
    if ((paramString1 == null) || (paramString1.length() == 0))
      throw new IllegalArgumentException("Invalid argument for name:  Cannot be empty");
    if ((paramString2 == null) || (paramString2.length() == 0))
      throw new IllegalArgumentException("Invalid argument for value:  Cannot be empty");
    int i = AnalyticsParameterEncoder.encode(paramString1 + paramString2).length();
    if (i > 64)
      throw new IllegalArgumentException("Encoded form of name and value must not exceed 64 characters combined.  Character length: " + i);
    this.index = paramInt1;
    this.scope = paramInt2;
    this.name = paramString1;
    this.value = paramString2;
  }

  public int getIndex()
  {
    return this.index;
  }

  public String getName()
  {
    return this.name;
  }

  public int getScope()
  {
    return this.scope;
  }

  public String getValue()
  {
    return this.value;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.CustomVariable
 * JD-Core Version:    0.6.2
 */