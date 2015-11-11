package com.taobao.newxp;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.taobao.newxp.common.ExchangeConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class TBItemPromoter extends Promoter
  implements Parcelable
{
  public String ad_location;
  public int ismall;
  public int[] pic_resolution;
  public int postfee;
  public double price;
  public double promoterPrice;
  public String res_name = "商品详情";
  public int sell;

  protected TBItemPromoter(Parcel paramParcel)
  {
    super(paramParcel);
    Log.e(ExchangeConstants.LOG_TAG, "this promoter is not complete parcelable");
  }

  public TBItemPromoter(JSONObject paramJSONObject)
  {
    super(paramJSONObject);
    this.price = paramJSONObject.optDouble("price");
    this.promoterPrice = paramJSONObject.optDouble("promoprice");
    this.ad_location = paramJSONObject.optString("ad_location");
    if (!TextUtils.isEmpty(paramJSONObject.optString("pic_resolution")));
    this.ismall = paramJSONObject.optInt("ismall");
    this.postfee = paramJSONObject.optInt("is_postfree");
    this.sell = paramJSONObject.optInt("sell");
    paramJSONObject = paramJSONObject.optString("resource_name");
    if (!TextUtils.isEmpty(paramJSONObject))
      this.res_name = paramJSONObject;
  }

  public JSONObject toJson()
  {
    JSONObject localJSONObject = super.toJson();
    try
    {
      localJSONObject.put("price", this.price);
      localJSONObject.put("promoprice", this.promoterPrice);
      localJSONObject.put("ad_location", this.ad_location);
      localJSONObject.put("ismall", this.ismall);
      localJSONObject.put("is_postfree", this.postfee);
      localJSONObject.put("sell", this.sell);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
    }
    return localJSONObject;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    Log.e(ExchangeConstants.LOG_TAG, "this promoter is not complete parcelable");
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.taobao.newxp.TBItemPromoter
 * JD-Core Version:    0.6.2
 */