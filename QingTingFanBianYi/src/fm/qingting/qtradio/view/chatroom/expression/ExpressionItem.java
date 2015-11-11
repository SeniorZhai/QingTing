package fm.qingting.qtradio.view.chatroom.expression;

public class ExpressionItem
{
  private String expressionIcon;
  private String expressionName;

  public ExpressionItem(String paramString1, String paramString2)
  {
    this.expressionName = paramString1;
    this.expressionIcon = paramString2;
  }

  public String getExpressionIcon()
  {
    return this.expressionIcon;
  }

  public String getExpressionName()
  {
    return this.expressionName;
  }

  public void setExpressionIcon(String paramString)
  {
    this.expressionIcon = paramString;
  }

  public void setExpressionName(String paramString)
  {
    this.expressionName = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.chatroom.expression.ExpressionItem
 * JD-Core Version:    0.6.2
 */