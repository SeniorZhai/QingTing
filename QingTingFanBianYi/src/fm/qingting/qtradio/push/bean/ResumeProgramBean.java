package fm.qingting.qtradio.push.bean;

public class ResumeProgramBean extends PushBean
{
  public int duration;
  public int position;

  public ResumeProgramBean(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, int paramInt1, int paramInt2)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramString5, paramString6);
    this.position = paramInt1;
    this.duration = paramInt2;
    this.push_type = PushType.ResumeProgram;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.push.bean.ResumeProgramBean
 * JD-Core Version:    0.6.2
 */