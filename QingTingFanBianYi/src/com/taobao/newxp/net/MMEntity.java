package com.taobao.newxp.net;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.taobao.newxp.a;
import com.taobao.newxp.common.AlimmContext;
import com.taobao.newxp.common.ExchangeConstants;
import com.taobao.newxp.controller.ExchangeDataService;
import com.taobao.newxp.controller.TabsDiskCache;
import com.taobao.newxp.view.handler.waketaobao.h;
import com.taobao.newxp.view.handler.waketaobao.j;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class MMEntity extends d
  implements Parcelable, Cloneable
{
  public static Parcelable.Creator<MMEntity> CREATOR = new Parcelable.Creator()
  {
    public MMEntity a(Parcel paramAnonymousParcel)
    {
      return new MMEntity(paramAnonymousParcel, null);
    }

    public MMEntity[] a(int paramAnonymousInt)
    {
      return new MMEntity[paramAnonymousInt];
    }
  };
  protected int a = 0;
  protected long b = -1L;
  protected boolean c = false;
  public int cache = -1;
  public int displayStyle = 0;
  public String displayType = "bigImg";
  public HashMap<String, Object> ecomparms = new HashMap();
  public String entryStr = "";
  public long expire = 0L;
  public boolean filterInstalledApp = true;
  public int image_type = 0;
  public String iscache = "";
  public String ispreload = "";
  public String landingUrl = "";
  public String landing_image = "";
  public String landing_size;
  public a module = a.a;
  public int newTips = -1;
  public String new_image = "";
  public String opensize = "";
  public int sid_expired = 1;
  public long[] timeline = new long[4];
  public boolean wallSwitch = false;

  public MMEntity()
  {
  }

  private MMEntity(Parcel paramParcel)
  {
    super(paramParcel);
    int i = paramParcel.readInt();
    a locala;
    if (i == -1)
    {
      locala = null;
      this.module = locala;
      this.opensize = paramParcel.readString();
      this.landing_image = paramParcel.readString();
      this.landingUrl = paramParcel.readString();
      this.new_image = paramParcel.readString();
      this.image_type = paramParcel.readInt();
      this.displayStyle = paramParcel.readInt();
      this.displayType = paramParcel.readString();
      this.newTips = paramParcel.readInt();
      this.cache = paramParcel.readInt();
      if (paramParcel.readByte() == 0)
        break label371;
      bool1 = true;
      label237: this.filterInstalledApp = bool1;
      if (paramParcel.readByte() == 0)
        break label377;
      bool1 = true;
      label253: this.wallSwitch = bool1;
      this.a = paramParcel.readInt();
      this.b = paramParcel.readLong();
      if (paramParcel.readByte() == 0)
        break label383;
    }
    label371: label377: label383: for (boolean bool1 = bool2; ; bool1 = false)
    {
      this.c = bool1;
      this.expire = paramParcel.readLong();
      this.sid_expired = paramParcel.readInt();
      this.entryStr = paramParcel.readString();
      this.ispreload = paramParcel.readString();
      this.iscache = paramParcel.readString();
      this.landing_size = paramParcel.readString();
      this.timeline = paramParcel.createLongArray();
      this.ecomparms = paramParcel.readHashMap(HashMap.class.getClassLoader());
      return;
      locala = a.values()[i];
      break;
      bool1 = false;
      break label237;
      bool1 = false;
      break label253;
    }
  }

  public Object clone()
    throws CloneNotSupportedException
  {
    return super.clone();
  }

  public int describeContents()
  {
    return 0;
  }

  public void extendFields(MMEntity paramMMEntity)
  {
    this.tabId = paramMMEntity.tabId;
    this.slot_act_params = paramMMEntity.slot_act_params;
    this.urlParams = paramMMEntity.urlParams;
  }

  public String getTimeConsuming()
  {
    if ((this.timeline != null) && (this.timeline.length == 4) && (this.timeline[0] > 0L))
    {
      long l3 = this.timeline[0];
      long l4 = this.timeline[1];
      long l1 = this.timeline[2];
      long l2 = this.timeline[3];
      l3 = l4 - l3;
      if (l3 > 0L)
        return l3 + "_" + (l2 - l1);
    }
    return null;
  }

  public void warp(JSONObject paramJSONObject)
  {
    super.warp(paramJSONObject);
    this.landing_size = paramJSONObject.optString("landing_size");
    boolean bool;
    if (paramJSONObject.optInt("filter", 1) == 1)
      bool = true;
    while (true)
    {
      this.filterInstalledApp = bool;
      label49: Object localObject1;
      Object localObject2;
      if (paramJSONObject.optInt("show_size", 1) == 1)
      {
        bool = true;
        ExchangeConstants.show_size = bool;
        this.cache = paramJSONObject.optInt("cache", -1);
        this.sid_expired = paramJSONObject.optInt("sid_expire", 1);
        this.expire = paramJSONObject.optLong("expire", 0L);
        this.landing_image = paramJSONObject.optString("landing_image", "");
        this.landingUrl = paramJSONObject.optString("landing_url", "");
        this.new_image = paramJSONObject.optString("new_img", "");
        this.displayType = paramJSONObject.optString("display_type", "bigImg");
        localObject1 = paramJSONObject.optString("module", "");
        if (!TextUtils.isEmpty((CharSequence)localObject1))
        {
          localObject2 = a.a((String)localObject1);
          localObject1 = localObject2;
          if (localObject2 == null)
            localObject1 = a.a;
          this.module = ((a)localObject1);
        }
        this.image_type = paramJSONObject.optInt("img_type", 0);
        localObject1 = paramJSONObject.optJSONArray("walls");
        if ((localObject1 != null) && (((JSONArray)localObject1).length() > 1))
          this.wallSwitch = true;
        if (!paramJSONObject.has("opensize"));
      }
      try
      {
        float f = Float.parseFloat(paramJSONObject.getString("opensize")) / 100.0F;
        this.opensize = ("" + f);
        label257: localObject1 = paramJSONObject.optJSONObject("dm");
        label302: JSONArray localJSONArray;
        if (localObject1 != null)
        {
          this.a = ((JSONObject)localObject1).optInt("on");
          this.b = (((JSONObject)localObject1).optInt("tm") * 60 * 60 * 1000);
          int i = paramJSONObject.optInt("interval", 0);
          if ((i > 3) && (!ExchangeConstants.IGNORE_SERVER_INTERVAL))
            ExchangeConstants.REFRESH_INTERVAL = i * 1000;
          this.newTips = paramJSONObject.optInt("new_num", -1);
          this.entryStr = paramJSONObject.optString("landing_text", "");
          this.ispreload = paramJSONObject.optString("ispreload", "");
          this.iscache = paramJSONObject.optString("iscache", "");
          localObject2 = AlimmContext.getAliContext().getAppContext();
          if (paramJSONObject.has("tabs"))
          {
            localJSONArray = paramJSONObject.optJSONArray("tabs");
            if (!TextUtils.isEmpty(this.slotId))
              break label537;
          }
        }
        label537: for (localObject1 = this.appkey; ; localObject1 = this.slotId)
        {
          if (!TextUtils.isEmpty((CharSequence)localObject1))
            TabsDiskCache.a((Context)localObject2, (String)localObject1).a(localJSONArray);
          paramJSONObject = paramJSONObject.optJSONObject("ecom");
          if (paramJSONObject != null)
          {
            localObject1 = paramJSONObject.optString("refpid");
            localObject2 = paramJSONObject.optString("e");
            j.a().a(h.a(paramJSONObject.optJSONObject("wakelist")));
            ExchangeDataService.getVerInfo().a((String)localObject2);
            ExchangeDataService.getVerInfo().b((String)localObject1);
          }
          return;
          bool = false;
          break;
          bool = false;
          break label49;
          this.a = 0;
          this.b = -1L;
          break label302;
        }
      }
      catch (Exception localException)
      {
        break label257;
      }
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    byte b2 = 1;
    super.writeToParcel(paramParcel, paramInt);
    if (this.module == null)
    {
      paramInt = -1;
      paramParcel.writeInt(paramInt);
      paramParcel.writeString(this.opensize);
      paramParcel.writeString(this.landing_image);
      paramParcel.writeString(this.landingUrl);
      paramParcel.writeString(this.new_image);
      paramParcel.writeInt(this.image_type);
      paramParcel.writeInt(this.displayStyle);
      paramParcel.writeString(this.displayType);
      paramParcel.writeInt(this.newTips);
      paramParcel.writeInt(this.cache);
      if (!this.filterInstalledApp)
        break label230;
      b1 = 1;
      label104: paramParcel.writeByte(b1);
      if (!this.wallSwitch)
        break label235;
      b1 = 1;
      label118: paramParcel.writeByte(b1);
      paramParcel.writeInt(this.a);
      paramParcel.writeLong(this.b);
      if (!this.c)
        break label240;
    }
    label230: label235: label240: for (byte b1 = b2; ; b1 = 0)
    {
      paramParcel.writeByte(b1);
      paramParcel.writeLong(this.expire);
      paramParcel.writeInt(this.sid_expired);
      paramParcel.writeString(this.entryStr);
      paramParcel.writeString(this.ispreload);
      paramParcel.writeString(this.iscache);
      paramParcel.writeString(this.landing_size);
      paramParcel.writeLongArray(this.timeline);
      paramParcel.writeMap(this.ecomparms);
      return;
      paramInt = this.module.ordinal();
      break;
      b1 = 0;
      break label104;
      b1 = 0;
      break label118;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.net.MMEntity
 * JD-Core Version:    0.6.2
 */