package com.tencent.weibo.sdk.android.component;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.os.MessageQueue;
import android.os.MessageQueue.IdleHandler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableLayout.LayoutParams;
import android.widget.TableRow;
import android.widget.Toast;
import com.tencent.weibo.sdk.android.api.FriendAPI;
import com.tencent.weibo.sdk.android.api.LbsAPI;
import com.tencent.weibo.sdk.android.api.TimeLineAPI;
import com.tencent.weibo.sdk.android.api.UserAPI;
import com.tencent.weibo.sdk.android.api.WeiboAPI;
import com.tencent.weibo.sdk.android.api.util.Util;
import com.tencent.weibo.sdk.android.model.AccountModel;
import com.tencent.weibo.sdk.android.model.ModelResult;
import com.tencent.weibo.sdk.android.network.HttpCallback;

public class GeneralInterfaceActivity extends Activity
  implements View.OnClickListener
{
  private String accessToken;
  private Button addPic;
  private Button addPicUrl;
  private Button addWeibo;
  private Context context = null;
  private FriendAPI friendAPI;
  private Button friendAdd;
  private Button friendCheck;
  private Button friendFunsList;
  private Button friendGetIntimateFriend;
  private Button friendIdolList;
  private Button friendMutualList;
  private Button homeTimeLine;
  private Button htTimeLine;
  private double latitude = 0.0D;
  private LbsAPI lbsAPI;
  private Button lbsGetAroundNew;
  private Button lbsGetAroundPeople;
  private PopupWindow loadingWindow = null;
  private boolean locatedFlag = false;
  private double longitude = 0.0D;
  private HttpCallback mCallBack;
  private final LocationListener mLocationListener = new LocationListener()
  {
    public void onLocationChanged(Location paramAnonymousLocation)
    {
      GeneralInterfaceActivity.this.updateLocation(paramAnonymousLocation);
    }

    public void onProviderDisabled(String paramAnonymousString)
    {
    }

    public void onProviderEnabled(String paramAnonymousString)
    {
    }

    public void onStatusChanged(String paramAnonymousString, int paramAnonymousInt, Bundle paramAnonymousBundle)
    {
    }
  };
  private LocationManager mLocationManager = null;
  private ProgressBar progressBar = null;
  private String requestFormat = "json";
  private ScrollView scrollView = null;
  private Button tReList;
  private TimeLineAPI timeLineAPI;
  private UserAPI userAPI;
  private Button userInfo;
  private Button userInfos;
  private Button userOtherInfo;
  private Button userTimeLine;
  private WeiboAPI weiboAPI;

  private void updateLocation(Location paramLocation)
  {
    if (paramLocation != null)
    {
      this.locatedFlag = true;
      this.longitude = paramLocation.getLongitude();
      this.latitude = paramLocation.getLatitude();
    }
  }

  public void initInterface()
  {
    this.scrollView = new ScrollView(this);
    TableLayout localTableLayout = new TableLayout(this);
    localTableLayout.setLayoutParams(new TableLayout.LayoutParams(-1, -1));
    TableRow localTableRow = new TableRow(this);
    this.homeTimeLine = new Button(this);
    this.homeTimeLine.setText("主人页时间线");
    this.homeTimeLine.setId(1001);
    this.homeTimeLine.setOnClickListener(this);
    localTableRow.addView(this.homeTimeLine);
    this.userTimeLine = new Button(this);
    this.userTimeLine.setText("客人页时间线");
    this.userTimeLine.setId(1002);
    this.userTimeLine.setOnClickListener(this);
    localTableRow.addView(this.userTimeLine);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.addWeibo = new Button(this);
    this.addWeibo.setText("普通发表接口");
    this.addWeibo.setId(1003);
    this.addWeibo.setOnClickListener(this);
    localTableRow.addView(this.addWeibo);
    this.addPic = new Button(this);
    this.addPic.setText("发表带图微博");
    this.addPic.setId(1004);
    this.addPic.setOnClickListener(this);
    localTableRow.addView(this.addPic);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.addPicUrl = new Button(this);
    this.addPicUrl.setText("发表带网络图片微博");
    this.addPicUrl.setId(1005);
    this.addPicUrl.setOnClickListener(this);
    localTableRow.addView(this.addPicUrl);
    this.htTimeLine = new Button(this);
    this.htTimeLine.setText("话题时间线");
    this.htTimeLine.setId(1006);
    this.htTimeLine.setOnClickListener(this);
    localTableRow.addView(this.htTimeLine);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.userInfo = new Button(this);
    this.userInfo.setText("获取用户信息");
    this.userInfo.setId(1007);
    this.userInfo.setOnClickListener(this);
    localTableRow.addView(this.userInfo);
    this.userOtherInfo = new Button(this);
    this.userOtherInfo.setText("获取他人信息");
    this.userOtherInfo.setId(1008);
    this.userOtherInfo.setOnClickListener(this);
    localTableRow.addView(this.userOtherInfo);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.userInfos = new Button(this);
    this.userInfos.setText("获取一批人信息");
    this.userInfos.setId(1009);
    this.userInfos.setOnClickListener(this);
    localTableRow.addView(this.userInfos);
    this.friendAdd = new Button(this);
    this.friendAdd.setText("收听某个用户");
    this.friendAdd.setId(1010);
    this.friendAdd.setOnClickListener(this);
    localTableRow.addView(this.friendAdd);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.friendIdolList = new Button(this);
    this.friendIdolList.setText("获取偶像列表");
    this.friendIdolList.setId(1011);
    this.friendIdolList.setOnClickListener(this);
    localTableRow.addView(this.friendIdolList);
    this.friendFunsList = new Button(this);
    this.friendFunsList.setText("获取粉丝列表");
    this.friendFunsList.setId(1012);
    this.friendFunsList.setOnClickListener(this);
    localTableRow.addView(this.friendFunsList);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.friendMutualList = new Button(this);
    this.friendMutualList.setText("获取互听列表");
    this.friendMutualList.setId(1013);
    this.friendMutualList.setOnClickListener(this);
    localTableRow.addView(this.friendMutualList);
    this.friendCheck = new Button(this);
    this.friendCheck.setText("验证好友关系");
    this.friendCheck.setId(1014);
    this.friendCheck.setOnClickListener(this);
    localTableRow.addView(this.friendCheck);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.tReList = new Button(this);
    this.tReList.setText("转播获取转播列表");
    this.tReList.setId(1015);
    this.tReList.setOnClickListener(this);
    localTableRow.addView(this.tReList);
    this.friendGetIntimateFriend = new Button(this);
    this.friendGetIntimateFriend.setText("获取最近联系人");
    this.friendGetIntimateFriend.setId(1016);
    this.friendGetIntimateFriend.setOnClickListener(this);
    localTableRow.addView(this.friendGetIntimateFriend);
    localTableLayout.addView(localTableRow);
    localTableRow = new TableRow(this);
    this.lbsGetAroundPeople = new Button(this);
    this.lbsGetAroundPeople.setText("获取附近的人");
    this.lbsGetAroundPeople.setId(1017);
    this.lbsGetAroundPeople.setOnClickListener(this);
    localTableRow.addView(this.lbsGetAroundPeople);
    this.lbsGetAroundNew = new Button(this);
    this.lbsGetAroundNew.setText("获取身边最新的微博");
    this.lbsGetAroundNew.setId(1018);
    this.lbsGetAroundNew.setOnClickListener(this);
    localTableRow.addView(this.lbsGetAroundNew);
    localTableLayout.addView(localTableRow);
    this.scrollView.addView(localTableLayout);
    setContentView(this.scrollView);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 1019:
    case 1020:
    default:
    case 1001:
    case 1002:
    case 1003:
    case 1004:
    case 1005:
    case 1006:
    case 1007:
    case 1008:
    case 1009:
    case 1010:
    case 1011:
    case 1012:
    case 1013:
    case 1014:
    case 1015:
    case 1016:
    case 1017:
    case 1018:
    }
    while (true)
    {
      Looper.myQueue().addIdleHandler(new MessageQueue.IdleHandler()
      {
        public boolean queueIdle()
        {
          GeneralInterfaceActivity.this.loadingWindow.showAtLocation(GeneralInterfaceActivity.this.scrollView, 17, 0, 80);
          return false;
        }
      });
      return;
      this.timeLineAPI.getHomeTimeLine(this.context, 0, 0, 30, 0, 0, this.requestFormat, this.mCallBack, null, 4);
      continue;
      this.timeLineAPI.getUserTimeLine(this.context, 0, 0, 20, 0, "t", null, 0, 0, this.requestFormat, this.mCallBack, null, 4);
      continue;
      this.weiboAPI.addWeibo(this.context, "hello world !", this.requestFormat, this.longitude, this.latitude, 0, 0, this.mCallBack, null, 4);
      continue;
      try
      {
        paramView = BitmapFactory.decodeStream(this.context.getAssets().open("logo.png"));
        this.weiboAPI.addPic(this.context, "call telephone OKK", this.requestFormat, this.longitude, this.latitude, paramView, 0, 0, this.mCallBack, null, 4);
      }
      catch (Exception paramView)
      {
        paramView.printStackTrace();
      }
      continue;
      this.weiboAPI.addPicUrl(this.context, "y phone ", this.requestFormat, this.longitude, this.latitude, "http://t2.qpic.cn/mblogpic/9c7e34358608bb61a696/2000", 0, 0, this.mCallBack, null, 4);
      continue;
      this.timeLineAPI.getHTTimeLine(this.context, this.requestFormat, 30, "0", "0", 0, 0, "加油", "0", 1, 128, this.mCallBack, null, 4);
      continue;
      this.userAPI.getUserInfo(this.context, this.requestFormat, this.mCallBack, null, 4);
      continue;
      this.userAPI.getUserOtherInfo(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
      continue;
      this.userAPI.getUserInfos(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
      continue;
      this.friendAPI.addFriend(this.context, this.requestFormat, "api_weibo", null, this.mCallBack, null, 4);
      continue;
      this.friendAPI.friendIDolList(this.context, this.requestFormat, 30, 0, 1, 0, this.mCallBack, null, 4);
      continue;
      this.friendAPI.friendFansList(this.context, this.requestFormat, 30, 0, 1, 0, 0, this.mCallBack, null, 4);
      continue;
      this.friendAPI.getMutualList(this.context, this.requestFormat, "api_weibo", null, 0, 30, 0, this.mCallBack, null, 4);
      continue;
      this.friendAPI.friendCheck(this.context, this.requestFormat, "api_weibo", null, 2, this.mCallBack, null, 4);
      continue;
      this.weiboAPI.reList(this.context, this.requestFormat, 2, "112714089895346", 0, "0", 30, "0", this.mCallBack, null, 4);
      continue;
      this.friendAPI.getIntimateFriends(this.context, this.requestFormat, 30, this.mCallBack, null, 4);
      continue;
      if (!this.locatedFlag)
      {
        Toast.makeText(this.context, "获取位置信息失败，请稍后再试", 0).show();
        return;
      }
      this.lbsAPI.getAroundPeople(this.context, this.requestFormat, this.longitude, this.latitude, "", 20, 0, this.mCallBack, null, 4);
      continue;
      if (!this.locatedFlag)
      {
        Toast.makeText(this.context, "获取位置信息失败，请稍后再试", 0).show();
        return;
      }
      this.lbsAPI.getAroundNew(this.context, this.requestFormat, this.longitude, this.latitude, "", 20, this.mCallBack, null, 4);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.accessToken = Util.getSharePersistent(getApplicationContext(), "ACCESS_TOKEN");
    if ((this.accessToken == null) || ("".equals(this.accessToken)))
    {
      Toast.makeText(this, "请先授权", 0).show();
      finish();
      return;
    }
    paramBundle = new AccountModel(this.accessToken);
    this.friendAPI = new FriendAPI(paramBundle);
    this.timeLineAPI = new TimeLineAPI(paramBundle);
    this.weiboAPI = new WeiboAPI(paramBundle);
    this.userAPI = new UserAPI(paramBundle);
    this.lbsAPI = new LbsAPI(paramBundle);
    this.mCallBack = new HttpCallback()
    {
      public void onResult(Object paramAnonymousObject)
      {
        paramAnonymousObject = (ModelResult)paramAnonymousObject;
        if ((GeneralInterfaceActivity.this.loadingWindow != null) && (GeneralInterfaceActivity.this.loadingWindow.isShowing()))
          GeneralInterfaceActivity.this.loadingWindow.dismiss();
        if ((paramAnonymousObject != null) && (paramAnonymousObject.isSuccess()))
        {
          Toast.makeText(GeneralInterfaceActivity.this, "成功", 0).show();
          Intent localIntent = new Intent(GeneralInterfaceActivity.this, GeneralDataShowActivity.class);
          localIntent.putExtra("data", paramAnonymousObject.getObj().toString());
          GeneralInterfaceActivity.this.startActivity(localIntent);
          return;
        }
        Toast.makeText(GeneralInterfaceActivity.this, "调用失败", 0).show();
      }
    };
    this.progressBar = new ProgressBar(this);
    this.loadingWindow = new PopupWindow(this.progressBar, 100, 100);
    this.context = getApplicationContext();
    this.mLocationManager = ((LocationManager)this.context.getSystemService("location"));
    updateLocation(this.mLocationManager.getLastKnownLocation("gps"));
    initInterface();
  }

  protected void onPause()
  {
    super.onPause();
    this.mLocationManager.removeUpdates(this.mLocationListener);
  }

  protected void onResume()
  {
    super.onResume();
    this.mLocationManager.requestLocationUpdates("gps", 10000L, 0.0F, this.mLocationListener);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.tencent.weibo.sdk.android.component.GeneralInterfaceActivity
 * JD-Core Version:    0.6.2
 */