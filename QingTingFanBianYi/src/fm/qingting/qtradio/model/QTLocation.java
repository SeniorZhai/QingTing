package fm.qingting.qtradio.model;

import java.util.HashMap;
import java.util.Map;

public class QTLocation
{
  public String city = "";
  public String ip = "";
  private final Map<String, String> mapRegionCode = new HashMap();
  public String region = "";

  public QTLocation()
  {
    this.mapRegionCode.put("安徽", "01");
    this.mapRegionCode.put("浙江", "02");
    this.mapRegionCode.put("江西", "03");
    this.mapRegionCode.put("江苏", "04");
    this.mapRegionCode.put("吉林", "05");
    this.mapRegionCode.put("青海", "06");
    this.mapRegionCode.put("福建", "07");
    this.mapRegionCode.put("黑龙", "08");
    this.mapRegionCode.put("河南", "09");
    this.mapRegionCode.put("河北", "10");
    this.mapRegionCode.put("湖南", "11");
    this.mapRegionCode.put("湖北", "12");
    this.mapRegionCode.put("新疆", "13");
    this.mapRegionCode.put("西藏", "14");
    this.mapRegionCode.put("甘肃", "15");
    this.mapRegionCode.put("广西", "16");
    this.mapRegionCode.put("海南", "17");
    this.mapRegionCode.put("贵州", "18");
    this.mapRegionCode.put("辽宁", "19");
    this.mapRegionCode.put("内蒙", "20");
    this.mapRegionCode.put("宁夏", "21");
    this.mapRegionCode.put("北京", "22");
    this.mapRegionCode.put("上海", "23");
    this.mapRegionCode.put("山西", "24");
    this.mapRegionCode.put("山东", "25");
    this.mapRegionCode.put("陕西", "26");
    this.mapRegionCode.put("四川", "27");
    this.mapRegionCode.put("天津", "28");
    this.mapRegionCode.put("云南", "29");
    this.mapRegionCode.put("广东", "30");
    this.mapRegionCode.put("重庆", "31");
    this.mapRegionCode.put("香港", "32");
    this.mapRegionCode.put("澳门", "33");
    this.mapRegionCode.put("台湾", "34");
  }

  public String getRegionCode()
  {
    if ((this.region != null) && (!this.region.equalsIgnoreCase("")))
    {
      String str = this.region.substring(0, 2);
      str = (String)this.mapRegionCode.get(str);
      if (str != null)
        return str;
    }
    return "CN";
  }

  public String getRegionCode(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      paramString = paramString.substring(0, 2);
      paramString = (String)this.mapRegionCode.get(paramString);
      if (paramString != null)
        return paramString;
    }
    return "CN";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.model.QTLocation
 * JD-Core Version:    0.6.2
 */