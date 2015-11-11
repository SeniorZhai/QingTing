package com.google.android.apps.analytics;

class CustomVariableBuffer
{
  private CustomVariable[] customVariables = new CustomVariable[5];

  private void throwOnInvalidIndex(int paramInt)
  {
    if ((paramInt < 1) || (paramInt > 5))
      throw new IllegalArgumentException("Index must be between 1 and 5 inclusive.");
  }

  public void clearCustomVariableAt(int paramInt)
  {
    throwOnInvalidIndex(paramInt);
    this.customVariables[(paramInt - 1)] = null;
  }

  public CustomVariable[] getCustomVariableArray()
  {
    return (CustomVariable[])this.customVariables.clone();
  }

  public CustomVariable getCustomVariableAt(int paramInt)
  {
    throwOnInvalidIndex(paramInt);
    return this.customVariables[(paramInt - 1)];
  }

  public boolean hasCustomVariables()
  {
    boolean bool2 = false;
    int i = 0;
    while (true)
    {
      boolean bool1 = bool2;
      if (i < this.customVariables.length)
      {
        if (this.customVariables[i] != null)
          bool1 = true;
      }
      else
        return bool1;
      i += 1;
    }
  }

  public boolean isIndexAvailable(int paramInt)
  {
    throwOnInvalidIndex(paramInt);
    return this.customVariables[(paramInt - 1)] == null;
  }

  public void setCustomVariable(CustomVariable paramCustomVariable)
  {
    throwOnInvalidIndex(paramCustomVariable.getIndex());
    this.customVariables[(paramCustomVariable.getIndex() - 1)] = paramCustomVariable;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.analytics.CustomVariableBuffer
 * JD-Core Version:    0.6.2
 */