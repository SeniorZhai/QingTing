package fm.qingting.qtradio.model;

public class FaqItem
{
  private String answer = null;
  private String question = null;

  public FaqItem(String paramString1, String paramString2)
  {
    this.question = paramString1;
    this.answer = paramString2;
  }

  public String getAnswer()
  {
    return this.answer;
  }

  public String getQuestion()
  {
    return this.question;
  }

  public void setAnswer(String paramString)
  {
    this.answer = paramString;
  }

  public void setQuestion(String paramString)
  {
    this.question = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.FaqItem
 * JD-Core Version:    0.6.2
 */