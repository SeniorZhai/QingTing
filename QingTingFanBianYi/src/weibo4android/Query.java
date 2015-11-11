package weibo4android;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import weibo4android.http.PostParameter;

public class Query
{
  private boolean base_app = true;
  private Integer city = null;
  private String comorsch = null;
  private Integer count = null;
  private Date endtime;
  private int filter_ori = 0;
  private int filter_pic;
  private long fuid;
  private Gender gender = null;
  private String geocode = null;
  private boolean needcount = false;
  private Integer page = null;
  private Integer province = null;
  private String q;
  private int rpp = 20;
  private Boolean sdomain = null;
  private Boolean sintro = null;
  private Boolean snick = null;
  private int sort = 1;
  private Date starttime;

  private PostParameter getParameterValue(String paramString, Object paramObject)
  {
    if ((paramObject instanceof Boolean))
    {
      if (((Boolean)paramObject).booleanValue());
      for (paramObject = "0"; ; paramObject = "1")
        return new PostParameter(paramString, paramObject);
    }
    if ((paramObject instanceof String))
      return new PostParameter(paramString, paramObject.toString());
    if ((paramObject instanceof Integer))
      return new PostParameter(paramString, Integer.toString(((Integer)paramObject).intValue()));
    if ((paramObject instanceof Gender))
      return new PostParameter(paramString, Gender.valueOf((Gender)paramObject));
    if ((paramObject instanceof Long))
      return new PostParameter(paramString, Long.toString(((Long)paramObject).longValue()));
    return null;
  }

  public boolean getBase_app()
  {
    return this.base_app;
  }

  public Integer getCity()
  {
    return this.city;
  }

  public String getComorsch()
  {
    return this.comorsch;
  }

  public Integer getCount()
  {
    return this.count;
  }

  public Date getEndtime()
  {
    return this.endtime;
  }

  public int getFilter_ori()
  {
    return this.filter_ori;
  }

  public int getFilter_pic()
  {
    return this.filter_pic;
  }

  public long getFuid()
  {
    return this.fuid;
  }

  public Gender getGender()
  {
    return this.gender;
  }

  public String getGeocode()
  {
    return this.geocode;
  }

  public boolean getNeedcount()
  {
    return this.needcount;
  }

  public Integer getPage()
  {
    return this.page;
  }

  public PostParameter[] getParameters()
    throws WeiboException
  {
    ArrayList localArrayList = new ArrayList();
    Field[] arrayOfField = Query.class.getDeclaredFields();
    int j = arrayOfField.length;
    int i = 0;
    while (i < j)
    {
      Object localObject1 = arrayOfField[i];
      ((Field)localObject1).setAccessible(true);
      localObject1 = ((Field)localObject1).getName();
      Object localObject2 = ((String)localObject1).substring(0, 1).toUpperCase();
      localObject2 = "get" + (String)localObject2 + ((String)localObject1).substring(1);
      try
      {
        localObject2 = Query.class.getMethod((String)localObject2, new Class[0]).invoke(this, new Object[0]);
        if (localObject2 != null)
          localArrayList.add(getParameterValue((String)localObject1, localObject2));
        i += 1;
      }
      catch (Exception localException)
      {
        throw new WeiboException(localException);
      }
    }
    return (PostParameter[])localException.toArray(new PostParameter[localException.size()]);
  }

  public Integer getProvince()
  {
    return this.province;
  }

  public String getQ()
  {
    return this.q;
  }

  public int getRpp()
  {
    return this.rpp;
  }

  public Boolean getSdomain()
  {
    return this.sdomain;
  }

  public Boolean getSintro()
  {
    return this.sintro;
  }

  public Boolean getSnick()
  {
    return this.snick;
  }

  public int getSort()
  {
    return this.sort;
  }

  public Date getStarttime()
  {
    return this.starttime;
  }

  public void setBase_app(boolean paramBoolean)
  {
    this.base_app = paramBoolean;
  }

  public void setCity(Integer paramInteger)
  {
    this.city = paramInteger;
  }

  public void setComorsch(String paramString)
  {
    this.comorsch = paramString;
  }

  public void setCount(Integer paramInteger)
  {
    this.count = paramInteger;
  }

  public void setEndtime(Date paramDate)
  {
    this.endtime = paramDate;
  }

  public void setFilter_ori(int paramInt)
  {
    this.filter_ori = paramInt;
  }

  public void setFilter_pic(int paramInt)
  {
    this.filter_pic = paramInt;
  }

  public void setFuid(Integer paramInteger)
  {
    this.fuid = paramInteger.intValue();
  }

  public void setGender(Gender paramGender)
  {
    this.gender = paramGender;
  }

  public void setGeocode(String paramString)
  {
    this.geocode = paramString;
  }

  public void setNeedcount(boolean paramBoolean)
  {
    this.needcount = paramBoolean;
  }

  public void setPage(Integer paramInteger)
  {
    this.page = paramInteger;
  }

  public void setProvince(Integer paramInteger)
  {
    this.province = paramInteger;
  }

  public void setQ(String paramString)
  {
    this.q = paramString;
  }

  public void setRpp(int paramInt)
  {
    this.rpp = paramInt;
  }

  public void setSdomain(Boolean paramBoolean)
  {
    this.sdomain = paramBoolean;
  }

  public void setSintro(Boolean paramBoolean)
  {
    this.sintro = paramBoolean;
  }

  public void setSnick(Boolean paramBoolean)
  {
    this.snick = paramBoolean;
  }

  public void setSort(int paramInt)
  {
    this.sort = paramInt;
  }

  public void setStarttime(Date paramDate)
  {
    this.starttime = paramDate;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.Query
 * JD-Core Version:    0.6.2
 */