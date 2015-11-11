package fm.qingting.framework.data;

import java.util.List;

public class MutableRequestTrait extends RequestTrait
{
  public MutableRequestTrait(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList1, List<String> paramList2, String paramString6, String paramString7, String paramString8)
  {
    super(paramString1, paramString2, paramString3, paramString4, paramString5, paramList1, paramList2, paramString6, paramString7, paramString8);
  }

  public MutableRequestTrait(String paramString1, String paramString2, List<String> paramList1, String paramString3, String paramString4, List<String> paramList2, List<String> paramList3, String paramString5, String paramString6, String paramString7)
  {
    super(paramString1, paramString2, paramList1, paramString3, paramString4, paramList2, paramList3, paramString5, paramString6, paramString7);
  }

  public void setCommandParamEncode(String paramString)
  {
    this.commandParamEncode = paramString;
  }

  public void setCommands(List<String> paramList)
  {
    this.commands = buildArrayList(paramList);
    resetFullCommands();
  }

  public void setDataSource(String paramString)
  {
    this.dataSource = paramString;
  }

  public void setDataType(String paramString)
  {
    this.dataType = paramString;
  }

  public void setDefaultRoots(List<String> paramList)
  {
    this.defaultRoots = buildArrayList(paramList);
    resetFullCommands();
  }

  public void setEncoding(String paramString)
  {
    this.encoding = paramString;
  }

  public void setMethod(String paramString)
  {
    this.method = paramString;
  }

  public void setRoots(List<String> paramList)
  {
    this.roots = buildArrayList(paramList);
    resetFullCommands();
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.data.MutableRequestTrait
 * JD-Core Version:    0.6.2
 */