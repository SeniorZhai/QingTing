package fm.qingting.qtradio.baidu;

import com.google.protobuf.ByteString;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.notification.Constants;
import fm.qingting.utils.MD5Util;
import fm.qingting.utils.OperatorInfo;
import fm.qingting.utils.ThirdAdv;
import mobads.apiv5.BaiduMobadsApiV50.AdSlot;
import mobads.apiv5.BaiduMobadsApiV50.AdSlot.Builder;
import mobads.apiv5.BaiduMobadsApiV50.App;
import mobads.apiv5.BaiduMobadsApiV50.App.Builder;
import mobads.apiv5.BaiduMobadsApiV50.Device;
import mobads.apiv5.BaiduMobadsApiV50.Device.Builder;
import mobads.apiv5.BaiduMobadsApiV50.Device.DeviceType;
import mobads.apiv5.BaiduMobadsApiV50.Device.OsType;
import mobads.apiv5.BaiduMobadsApiV50.MobadsRequest;
import mobads.apiv5.BaiduMobadsApiV50.MobadsRequest.Builder;
import mobads.apiv5.BaiduMobadsApiV50.Network;
import mobads.apiv5.BaiduMobadsApiV50.Network.Builder;
import mobads.apiv5.BaiduMobadsApiV50.Network.ConnectionType;
import mobads.apiv5.BaiduMobadsApiV50.Network.OperatorType;
import mobads.apiv5.BaiduMobadsApiV50.Size;
import mobads.apiv5.BaiduMobadsApiV50.Size.Builder;
import mobads.apiv5.BaiduMobadsApiV50.UdId;
import mobads.apiv5.BaiduMobadsApiV50.UdId.Builder;
import mobads.apiv5.BaiduMobadsApiV50.Version;
import mobads.apiv5.BaiduMobadsApiV50.Version.Builder;
import org.json.JSONException;
import org.json.JSONObject;

public class BDRequestParam
{
  public String adSlotId = null;

  public byte[] toBytes()
  {
    try
    {
      Object localObject1 = BaiduMobadsApiV50.Version.newBuilder().setMajor(5).setMinor(0).build();
      Object localObject3 = BaiduMobadsApiV50.Version.newBuilder().setMajor(ThirdAdv.getInstance().getAppMajorVersion()).setMinor(ThirdAdv.getInstance().getAppMinorVersion()).build();
      Object localObject2 = BaiduMobadsApiV50.Version.newBuilder().setMajor(ThirdAdv.getInstance().getMajorVersion()).setMicro(ThirdAdv.getInstance().getMicroVersion()).setMinor(ThirdAdv.getInstance().getMinorVersion()).build();
      localObject3 = BaiduMobadsApiV50.App.newBuilder().setAppId(Constants.BAIDU_APP_ID).setAppVersion((BaiduMobadsApiV50.Version)localObject3).build();
      BaiduMobadsApiV50.Network localNetwork = BaiduMobadsApiV50.Network.newBuilder().setIpv4(ThirdAdv.getInstance().getLocalIp()).setOperatorType(BaiduMobadsApiV50.Network.OperatorType.CHINA_MOBILE).setConnectionType(BaiduMobadsApiV50.Network.ConnectionType.WIFI).build();
      Object localObject4 = ThirdAdv.getInstance().getChangedMac();
      Object localObject5 = ThirdAdv.getInstance().getChangedIMEI();
      localObject4 = BaiduMobadsApiV50.UdId.newBuilder().setAndroidId(ThirdAdv.getInstance().getChangedAndroidId()).setImei(ThirdAdv.getInstance().getChangedIMEI()).setImeiMd5(MD5Util.md5((String)localObject5)).setMac((String)localObject4).build();
      localObject5 = BaiduMobadsApiV50.Size.newBuilder().setWidth(ThirdAdv.getInstance().getScreenWidth()).setHeight(ThirdAdv.getInstance().getScreenHeight()).build();
      Object localObject6 = BaiduMobadsApiV50.Size.newBuilder().setWidth(350).setHeight(250).build();
      localObject6 = BaiduMobadsApiV50.AdSlot.newBuilder().setAdslotId(Constants.BAIDU_ADSLOT_ID).setAdslotSize((BaiduMobadsApiV50.Size)localObject6).build();
      localObject2 = BaiduMobadsApiV50.Device.newBuilder().setOsVersion((BaiduMobadsApiV50.Version)localObject2).setDeviceType(BaiduMobadsApiV50.Device.DeviceType.PHONE).setOsType(BaiduMobadsApiV50.Device.OsType.ANDROID).setScreenSize((BaiduMobadsApiV50.Size)localObject5).setUdid((BaiduMobadsApiV50.UdId)localObject4).setVendor(ByteString.copyFrom(ThirdAdv.getInstance().getVendor(), "UTF-8")).setModel(ByteString.copyFrom(ThirdAdv.getInstance().getPhoneModel(), "UTF-8")).build();
      localObject1 = BaiduMobadsApiV50.MobadsRequest.newBuilder().setRequestId(Constants.BAIDU_REQUEST_ID).setAdslot((BaiduMobadsApiV50.AdSlot)localObject6).setApiVersion((BaiduMobadsApiV50.Version)localObject1).setApp((BaiduMobadsApiV50.App)localObject3).setDevice((BaiduMobadsApiV50.Device)localObject2).setNetwork(localNetwork).build().toByteArray();
      return localObject1;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public String toString()
  {
    Object localObject = new JSONObject();
    try
    {
      ((JSONObject)localObject).put("request_id", Constants.BAIDU_REQUEST_ID);
      JSONObject localJSONObject1 = new JSONObject();
      localJSONObject1.put("major", 5);
      localJSONObject1.put("minor", 0);
      ((JSONObject)localObject).put("api_version", localJSONObject1);
      localJSONObject1 = new JSONObject();
      JSONObject localJSONObject2 = new JSONObject();
      localJSONObject2.put("major", ThirdAdv.getInstance().getAppMajorVersion());
      localJSONObject2.put("minor", ThirdAdv.getInstance().getAppMinorVersion());
      localJSONObject1.put("app_version", localJSONObject2);
      localJSONObject1.put("app_id", Constants.BAIDU_APP_ID);
      ((JSONObject)localObject).put("app", localJSONObject1);
      localJSONObject1 = new JSONObject();
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("major", ThirdAdv.getInstance().getMajorVersion());
      localJSONObject2.put("minor", ThirdAdv.getInstance().getMinorVersion());
      localJSONObject2.put("micor", ThirdAdv.getInstance().getMicroVersion());
      localJSONObject1.put("os_version", localJSONObject2);
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("width", ThirdAdv.getInstance().getScreenWidth());
      localJSONObject2.put("height", ThirdAdv.getInstance().getScreenHeight());
      localJSONObject1.put("screen_size", localJSONObject2);
      localJSONObject1.put("vendor", ThirdAdv.getInstance().getVendor());
      localJSONObject1.put("model", ThirdAdv.getInstance().getPhoneModel());
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("mac", ThirdAdv.getInstance().getChangedMac());
      String str = ThirdAdv.getInstance().getChangedIMEI();
      localJSONObject2.put("imei", str);
      localJSONObject2.put("imei_md5", MD5Util.md5(str));
      localJSONObject2.put("android_id", ThirdAdv.getInstance().getChangedAndroidId());
      localJSONObject1.put("udid", localJSONObject2);
      localJSONObject1.put("device_type", Constants.BAIDU_DEVICE_TYPE);
      localJSONObject1.put("os_type", Constants.BAIDU_OS_TYPE);
      ((JSONObject)localObject).put("device", localJSONObject1);
      localJSONObject1 = new JSONObject();
      localJSONObject1.put("ipv4", ThirdAdv.getInstance().getLocalIp());
      localJSONObject1.put("connection_type", InfoManager.getInstance().getNetWorkType());
      localJSONObject1.put("operator_type", OperatorInfo.getOperator(InfoManager.getInstance().getContext()));
      ((JSONObject)localObject).put("network", localJSONObject1);
      localJSONObject1 = new JSONObject();
      localJSONObject1.put("adslot_id", Constants.BAIDU_ADSLOT_ID);
      localJSONObject2 = new JSONObject();
      localJSONObject2.put("width", 720);
      localJSONObject2.put("height", 278);
      localJSONObject1.put("adslot_size", localJSONObject2);
      ((JSONObject)localObject).put("adslot", localJSONObject1);
      localObject = ((JSONObject)localObject).toString();
      return localObject;
    }
    catch (JSONException localJSONException)
    {
      localJSONException.printStackTrace();
    }
    return null;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.baidu.BDRequestParam
 * JD-Core Version:    0.6.2
 */