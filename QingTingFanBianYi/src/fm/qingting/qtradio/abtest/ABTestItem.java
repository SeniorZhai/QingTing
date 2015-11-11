package fm.qingting.qtradio.abtest;

public class ABTestItem
{
  public String OptionA;
  public String OptionB;
  public String OptionName;
  public GenerateMethod generateMethod = GenerateMethod.Auto;
  public int number;

  public static enum GenerateMethod
  {
    Auto, Manual;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.abtest.ABTestItem
 * JD-Core Version:    0.6.2
 */