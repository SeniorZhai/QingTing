package fm.qingting.qtradio.parser;

import android.app.Activity;
import android.util.Log;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.umeng.analytics.MobclickAgent;
import fm.qingting.framework.data.DataParserImpl;
import fm.qingting.framework.data.Result;
import fm.qingting.qtradio.abtest.ABTest;
import fm.qingting.qtradio.ad.AdConfig;
import fm.qingting.qtradio.ad.AdPos;
import fm.qingting.qtradio.ad.platforms.mediav.MediaVAgent;
import fm.qingting.qtradio.helper.ChannelHelper;
import fm.qingting.qtradio.im.info.GroupInfo;
import fm.qingting.qtradio.im.message.IMMessage;
import fm.qingting.qtradio.jd.data.CommodityInfo;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.AdvertisementItemNode;
import fm.qingting.qtradio.model.AdvertisementItemNode.AdTrackers;
import fm.qingting.qtradio.model.Attribute;
import fm.qingting.qtradio.model.Attributes;
import fm.qingting.qtradio.model.BillboardItemNode;
import fm.qingting.qtradio.model.BroadcasterNode;
import fm.qingting.qtradio.model.CategoryNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.GameBean;
import fm.qingting.qtradio.model.H5Bean;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.MediaCenter;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.PingInfoV6;
import fm.qingting.qtradio.model.PlayHistoryNode;
import fm.qingting.qtradio.model.ProgramABTestBean;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.ProgramSchedule;
import fm.qingting.qtradio.model.ProgramScheduleList;
import fm.qingting.qtradio.model.ProgramTopicNode;
import fm.qingting.qtradio.model.QTADLocation;
import fm.qingting.qtradio.model.QTLocation;
import fm.qingting.qtradio.model.RecommendCategoryNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.RecommendPlayingItemNode;
import fm.qingting.qtradio.model.SharedCfg;
import fm.qingting.qtradio.model.SpecialTopicNode;
import fm.qingting.qtradio.model.WemartBean;
import fm.qingting.qtradio.room.AdminInfo;
import fm.qingting.qtradio.room.SnsInfo;
import fm.qingting.qtradio.room.UserInfo;
import fm.qingting.qtradio.search.SearchHotKeyword;
import fm.qingting.qtradio.search.SearchItemNode;
import fm.qingting.qtradio.search.SearchNode.NewSearchComparator;
import fm.qingting.qtradio.search.SearchNode.SearchResult;
import fm.qingting.qtradio.social.MiniFavNode;
import fm.qingting.qtradio.social.UserProfile;
import fm.qingting.utils.AppInfo;
import fm.qingting.utils.ThirdTracker;
import fm.qingting.utils.TimeUtil;
import fm.qingting.utils.UserDataUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NetParser extends DataParserImpl
{
  private ActivityNode _parseActivity(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        ActivityNode localActivityNode = new ActivityNode();
        localActivityNode.id = paramJSONObject.getIntValue("id");
        localActivityNode.name = paramJSONObject.getString("title");
        localActivityNode.type = paramJSONObject.getString("type");
        localActivityNode.contentUrl = paramJSONObject.getString("url");
        localActivityNode.infoUrl = null;
        localActivityNode.infoTitle = paramJSONObject.getString("description");
        return localActivityNode;
      }
      catch (Exception paramJSONObject)
      {
        return null;
      }
    return null;
  }

  private AdminInfo _parseAdminInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        AdminInfo localAdminInfo = new AdminInfo();
        localAdminInfo.userKey = paramJSONObject.getString("userid");
        localAdminInfo.snsInfo.sns_avatar = paramJSONObject.getString("avatar");
        localAdminInfo.snsInfo.sns_name = paramJSONObject.getString("username");
        if (localAdminInfo.snsInfo.sns_name == null)
          localAdminInfo.snsInfo.sns_name = "蜻蜓管理员";
        localAdminInfo.snsInfo.signature = paramJSONObject.getString("signature");
        localAdminInfo.snsInfo.age = paramJSONObject.getIntValue("age");
        localAdminInfo.snsInfo.sns_gender = paramJSONObject.getString("gender");
        String str = paramJSONObject.getString("is_blocked");
        localAdminInfo.weiboName = paramJSONObject.getString("weiboname");
        if ((str != null) && (!str.equalsIgnoreCase("")))
          if (Integer.valueOf(str).intValue() != 0)
            break label188;
        label188: for (localAdminInfo.isBlocked = false; ; localAdminInfo.isBlocked = true)
        {
          str = paramJSONObject.getString("level");
          paramJSONObject = localAdminInfo;
          if (str == null)
            break;
          paramJSONObject = localAdminInfo;
          if (str.equalsIgnoreCase(""))
            break;
          localAdminInfo.level = Integer.valueOf(str).intValue();
          return localAdminInfo;
        }
      }
      catch (Exception paramJSONObject)
      {
      }
    else
      paramJSONObject = null;
    return paramJSONObject;
  }

  private ChannelNode _parseChannelNode(JSONObject paramJSONObject)
  {
    int j = 0;
    if (paramJSONObject != null)
      try
      {
        ChannelNode localChannelNode = new ChannelNode();
        if (paramJSONObject.containsKey("channel_star"))
        {
          localChannelNode.ratingStar = paramJSONObject.getIntValue("channel_star");
          localChannelNode.channelId = paramJSONObject.getIntValue("id");
          localChannelNode.title = paramJSONObject.getString("title");
          localChannelNode.desc = paramJSONObject.getString("description");
          localChannelNode.groupId = paramJSONObject.getIntValue("chatgroup_id");
          localChannelNode.categoryId = paramJSONObject.getIntValue("category_id");
          localChannelNode.update_time = paramJSONObject.getString("update_time");
          localObject = paramJSONObject.getJSONObject("thumbs");
          if (localObject != null)
          {
            localChannelNode.setSmallThumb(((JSONObject)localObject).getString("200_thumb"));
            localChannelNode.setMediumThumb(((JSONObject)localObject).getString("400_thumb"));
            localChannelNode.setLargeThumb(((JSONObject)localObject).getString("800_thumb"));
            if (localChannelNode.noThumb())
            {
              localChannelNode.setSmallThumb(((JSONObject)localObject).getString("small_thumb"));
              localChannelNode.setMediumThumb(((JSONObject)localObject).getString("medium_thumb"));
              localChannelNode.setLargeThumb(((JSONObject)localObject).getString("large_thumb"));
            }
          }
          localObject = paramJSONObject.getString("type");
          if ((localObject == null) || (!((String)localObject).equalsIgnoreCase("channel_ondemand")))
            break label384;
          localChannelNode.channelType = 1;
          label197: if (paramJSONObject.getIntValue("auto_play") != 0)
            break label392;
          localChannelNode.autoPlay = false;
          label211: if (paramJSONObject.getIntValue("record_enabled") != 0)
            break label400;
          localChannelNode.recordEnable = false;
          label225: if (!localChannelNode.isLiveChannel())
            break label408;
          localChannelNode.audienceCnt = paramJSONObject.getIntValue("audience_count");
          localObject = paramJSONObject.getJSONObject("mediainfo");
          if (localObject != null)
            localChannelNode.resId = ((JSONObject)localObject).getIntValue("id");
        }
        BroadcasterNode localBroadcasterNode;
        while (true)
        {
          localObject = paramJSONObject.getJSONObject("detail");
          paramJSONObject = localChannelNode;
          if (localObject == null)
            break label582;
          localChannelNode.programCnt = ((JSONObject)localObject).getIntValue("program_count");
          paramJSONObject = ((JSONObject)localObject).getJSONArray("authors");
          if (paramJSONObject == null)
            break label422;
          i = 0;
          while (i < paramJSONObject.size())
          {
            localBroadcasterNode = parseBroadcasterNode(paramJSONObject.getJSONObject(i));
            if (localChannelNode.lstAuthors == null)
              localChannelNode.lstAuthors = new ArrayList();
            localChannelNode.lstAuthors.add(localBroadcasterNode);
            i += 1;
          }
          if (!paramJSONObject.containsKey("star"))
            break;
          localChannelNode.ratingStar = paramJSONObject.getIntValue("star");
          break;
          label384: localChannelNode.channelType = 0;
          break label197;
          label392: localChannelNode.autoPlay = true;
          break label211;
          label400: localChannelNode.recordEnable = true;
          break label225;
          label408: localChannelNode.latest_program = paramJSONObject.getString("latest_program");
        }
        label422: paramJSONObject = ((JSONObject)localObject).getJSONArray("broadcasters");
        if (paramJSONObject != null)
        {
          i = 0;
          while (i < paramJSONObject.size())
          {
            localBroadcasterNode = parseBroadcasterNode(paramJSONObject.getJSONObject(i));
            if (localChannelNode.lstBroadcaster == null)
              localChannelNode.lstBroadcaster = new ArrayList();
            localChannelNode.lstBroadcaster.add(localBroadcasterNode);
            i += 1;
          }
        }
        Object localObject = ((JSONObject)localObject).getJSONArray("podcasters");
        paramJSONObject = localChannelNode;
        if (localObject == null)
          break label582;
        int i = j;
        while (true)
        {
          paramJSONObject = localChannelNode;
          if (i >= ((JSONArray)localObject).size())
            break;
          paramJSONObject = _parsePodcaster(((JSONArray)localObject).getJSONObject(i));
          if (localChannelNode.lstPodcasters == null)
            localChannelNode.lstPodcasters = new ArrayList();
          if (paramJSONObject != null)
            localChannelNode.lstPodcasters.add(paramJSONObject);
          i += 1;
        }
      }
      catch (Exception paramJSONObject)
      {
      }
    else
      paramJSONObject = null;
    label582: return paramJSONObject;
  }

  private ProgramNode _parseLiveProgramNode(JSONObject paramJSONObject, int paramInt)
  {
    int i = 0;
    if (paramJSONObject != null)
    {
      ProgramNode localProgramNode = new ProgramNode();
      localProgramNode.id = paramJSONObject.getIntValue("id");
      localProgramNode.startTime = paramJSONObject.getString("start_time");
      localProgramNode.endTime = paramJSONObject.getString("end_time");
      if ((paramInt != 0) || ((localProgramNode.endTime != null) && (localProgramNode.endTime.equalsIgnoreCase("00:00:00"))))
        localProgramNode.endTime = "23:59:00";
      localProgramNode.title = paramJSONObject.getString("title");
      localProgramNode.channelId = paramJSONObject.getIntValue("channel_id");
      localProgramNode.uniqueId = paramJSONObject.getIntValue("program_id");
      localProgramNode.groupId = paramJSONObject.getIntValue("chatgroup_id");
      localProgramNode.dayOfWeek = paramInt;
      localProgramNode.channelType = 0;
      Object localObject = paramJSONObject.getJSONObject("mediainfo");
      if (localObject != null)
        localProgramNode.resId = ((JSONObject)localObject).getIntValue("id");
      localObject = InfoManager.getInstance().getProgramABTest(localProgramNode.channelId, localProgramNode.uniqueId);
      if (localObject != null)
      {
        localProgramNode.resId = ((ProgramABTestBean)localObject).resId;
        localProgramNode.title = ((ProgramABTestBean)localObject).title;
      }
      paramJSONObject = paramJSONObject.getJSONObject("detail");
      if (paramJSONObject != null)
      {
        paramJSONObject = paramJSONObject.getJSONArray("broadcasters");
        if (paramJSONObject != null)
        {
          localProgramNode.lstBroadcaster = new ArrayList();
          paramInt = i;
          while (paramInt < paramJSONObject.size())
          {
            localObject = new BroadcasterNode();
            JSONObject localJSONObject = paramJSONObject.getJSONObject(paramInt);
            ((BroadcasterNode)localObject).id = localJSONObject.getIntValue("id");
            ((BroadcasterNode)localObject).nick = localJSONObject.getString("username");
            ((BroadcasterNode)localObject).avatar = localJSONObject.getString("thumb");
            ((BroadcasterNode)localObject).weiboId = localJSONObject.getString("weibo_id");
            ((BroadcasterNode)localObject).weiboName = localJSONObject.getString("weibo_name");
            ((BroadcasterNode)localObject).qqId = localJSONObject.getString("qq_id");
            ((BroadcasterNode)localObject).qqName = localJSONObject.getString("qq_name");
            localProgramNode.lstBroadcaster.add(localObject);
            paramInt += 1;
          }
        }
      }
      return localProgramNode;
    }
    return null;
  }

  private List<PingInfoV6> _parseMediaCenter(JSONObject paramJSONObject, int paramInt)
  {
    int i = 0;
    if (paramJSONObject != null)
      try
      {
        JSONArray localJSONArray = paramJSONObject.getJSONArray("mediacenters");
        double d = paramJSONObject.getDoubleValue("preference_change_cost");
        if (localJSONArray != null)
        {
          ArrayList localArrayList = new ArrayList();
          paramJSONObject = localArrayList;
          if (i >= localJSONArray.size())
            break label233;
          Object localObject = localJSONArray.getJSONObject(i);
          paramJSONObject = new PingInfoV6();
          paramJSONObject.domain = ((JSONObject)localObject).getString("domain");
          paramJSONObject.backupIP = ((JSONObject)localObject).getString("backup_ips");
          paramJSONObject.weight = ((JSONObject)localObject).getIntValue("weight");
          paramJSONObject.testpath = ((JSONObject)localObject).getString("test_path");
          paramJSONObject.accessExp = ((JSONObject)localObject).getString("access");
          paramJSONObject.replayExp = ((JSONObject)localObject).getString("replay");
          paramJSONObject.res = ((JSONObject)localObject).getString("result");
          paramJSONObject.codename = ((JSONObject)localObject).getString("codename");
          paramJSONObject.channelType = paramInt;
          paramJSONObject.pcc = d;
          localObject = ((JSONObject)localObject).getString("type");
          if ((localObject != null) && (((String)localObject).equalsIgnoreCase("cdn")));
          for (paramJSONObject.isCDN = true; ; paramJSONObject.isCDN = false)
          {
            localArrayList.add(paramJSONObject);
            i += 1;
            break;
          }
        }
      }
      catch (Exception paramJSONObject)
      {
      }
    else
      paramJSONObject = null;
    label233: return paramJSONObject;
  }

  private List<RecommendItemNode> _parseNewRecommendV2Banner(JSONObject paramJSONObject, List<RecommendItemNode> paramList)
  {
    if ((paramJSONObject != null) && (paramList != null))
    {
      JSONArray localJSONArray = paramJSONObject.getJSONArray("data");
      int i = 0;
      while (true)
      {
        paramJSONObject = paramList;
        if (i >= localJSONArray.size())
          break;
        paramJSONObject = _parseRecommendNode(localJSONArray.getJSONObject(i));
        if (paramJSONObject != null)
          paramList.add(paramJSONObject);
        i += 1;
      }
    }
    paramJSONObject = null;
    return paramJSONObject;
  }

  private UserInfo _parsePodcaster(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
    {
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.userId = paramJSONObject.getString("user_system_id");
      localUserInfo.userKey = localUserInfo.userId;
      localUserInfo.isBlocked = false;
      localUserInfo.isPodcaster = true;
      localUserInfo.podcasterId = paramJSONObject.getIntValue("id");
      localUserInfo.podcasterName = paramJSONObject.getString("nickname");
      localUserInfo.fansNumber = paramJSONObject.getLong("fan_num").longValue();
      localUserInfo.snsInfo.signature = paramJSONObject.getString("signature");
      localUserInfo.snsInfo.sns_id = paramJSONObject.getString("weibo_id");
      localUserInfo.snsInfo.sns_name = paramJSONObject.getString("weibo_name");
      if (localUserInfo.snsInfo.sns_name == null)
        localUserInfo.snsInfo.sns_name = "蜻蜓主播";
      localUserInfo.snsInfo.sns_avatar = paramJSONObject.getString("avatar");
      localUserInfo.snsInfo.desc = paramJSONObject.getString("description");
      int i = paramJSONObject.getIntValue("sex");
      if (i == 0)
        localUserInfo.snsInfo.sns_gender = "n";
      do
      {
        return localUserInfo;
        if (i == 1)
        {
          localUserInfo.snsInfo.sns_gender = "m";
          return localUserInfo;
        }
      }
      while (i != 2);
      localUserInfo.snsInfo.sns_gender = "f";
      return localUserInfo;
    }
    return null;
  }

  private UserInfo _parsePodcasterNew(JSONObject paramJSONObject)
  {
    boolean bool = true;
    if (paramJSONObject != null)
    {
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.userId = paramJSONObject.getString("userid");
      localUserInfo.userKey = localUserInfo.userId;
      localUserInfo.isPodcaster = true;
      localUserInfo.podcasterId = paramJSONObject.getIntValue("pid");
      localUserInfo.podcasterName = paramJSONObject.getString("username");
      localUserInfo.fansNumber = paramJSONObject.getLong("fan_num").longValue();
      localUserInfo.snsInfo.signature = paramJSONObject.getString("signature");
      localUserInfo.snsInfo.sns_id = paramJSONObject.getString("weibo_id");
      localUserInfo.snsInfo.sns_name = paramJSONObject.getString("weibo_name");
      if (localUserInfo.snsInfo.sns_name == null)
        localUserInfo.snsInfo.sns_name = "蜻蜓主播";
      localUserInfo.snsInfo.sns_avatar = paramJSONObject.getString("avatar");
      localUserInfo.snsInfo.desc = paramJSONObject.getString("description");
      int i = paramJSONObject.getIntValue("gender");
      if (i == 0)
      {
        localUserInfo.snsInfo.sns_gender = "n";
        if (paramJSONObject.getInteger("is_blocked").intValue() != 1)
          break label238;
      }
      while (true)
      {
        localUserInfo.isBlocked = bool;
        return localUserInfo;
        if (i == 1)
        {
          localUserInfo.snsInfo.sns_gender = "m";
          break;
        }
        if (i != 2)
          break;
        localUserInfo.snsInfo.sns_gender = "f";
        break;
        label238: bool = false;
      }
    }
    return null;
  }

  private ProgramNode _parseProgramTempNode(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        ProgramNode localProgramNode = new ProgramNode();
        localProgramNode.mLiveInVirtual = true;
        localProgramNode.dayOfWeek = 0;
        localProgramNode.id = paramJSONObject.getIntValue("id");
        localProgramNode.uniqueId = localProgramNode.id;
        localProgramNode.channelType = 0;
        localProgramNode.title = paramJSONObject.getString("title");
        localProgramNode.duration = paramJSONObject.getDoubleValue("duration");
        localProgramNode.sequence = paramJSONObject.getIntValue("sequence");
        localProgramNode.startTime = paramJSONObject.getString("start_time");
        long l = TimeUtil.dateToMS(localProgramNode.startTime);
        localProgramNode.startTime = TimeUtil.msToDate3(l);
        localProgramNode.setAbsoluteStartTime(l / 1000L);
        localProgramNode.endTime = paramJSONObject.getString("end_time");
        l = TimeUtil.dateToMS(localProgramNode.endTime);
        localProgramNode.endTime = TimeUtil.msToDate3(l);
        localProgramNode.setAbsoluteEndTime(l / 1000L);
        localProgramNode.sequence = paramJSONObject.getIntValue("sequence");
        localProgramNode.groupId = paramJSONObject.getIntValue("chatgroup_id");
        paramJSONObject = paramJSONObject.getJSONObject("mediainfo");
        if (paramJSONObject != null)
          localProgramNode.resId = paramJSONObject.getIntValue("id");
        return localProgramNode;
      }
      catch (Exception paramJSONObject)
      {
      }
    return null;
  }

  private RecommendItemNode _parseRecommendItemNode(JSONObject paramJSONObject, boolean paramBoolean)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if (paramJSONObject != null);
    try
    {
      RecommendItemNode localRecommendItemNode = new RecommendItemNode();
      localRecommendItemNode.name = paramJSONObject.getString("title");
      JSONObject localJSONObject1;
      JSONObject localJSONObject2;
      int k;
      if (!paramBoolean)
      {
        localJSONObject1 = paramJSONObject.getJSONObject("thumbs");
        if (localJSONObject1 != null)
        {
          localRecommendItemNode.setSmallThumb(localJSONObject1.getString("200_thumb"));
          localRecommendItemNode.setMediumThumb(localJSONObject1.getString("400_thumb"));
          localRecommendItemNode.setLargeThumb(localJSONObject1.getString("800_thumb"));
        }
        if (localRecommendItemNode.noThumb())
          localRecommendItemNode.setSmallThumb(paramJSONObject.getString("thumb"));
        localRecommendItemNode.update_time = paramJSONObject.getString("update_time");
        localJSONObject2 = paramJSONObject.getJSONObject("detail");
        if (localJSONObject2 == null)
          break label667;
        if (!localJSONObject2.containsKey("channel_star"))
          break label332;
        localRecommendItemNode.ratingStar = localJSONObject2.getIntValue("channel_star");
        k = 1;
      }
      while (true)
      {
        label148: localJSONObject1 = paramJSONObject.getJSONObject("parent_info");
        int i;
        int j;
        label211: int m;
        if (localJSONObject1 != null)
        {
          paramJSONObject = localJSONObject1.getString("parent_type");
          if (paramJSONObject != null)
            if ((paramJSONObject.equalsIgnoreCase("channel")) || (paramJSONObject.equalsIgnoreCase("channel_ondemand")))
            {
              i = localJSONObject1.getIntValue("parent_id");
              paramJSONObject = localJSONObject1.getString("parent_name");
              j = 0;
              localObject1 = localJSONObject1.getJSONObject("parent_extra");
              localJSONObject1 = paramJSONObject;
              m = i;
              if (localObject1 != null)
              {
                j = ((JSONObject)localObject1).getIntValue("category_id");
                localRecommendItemNode.mCategoryId = j;
                m = i;
              }
            }
        }
        for (localJSONObject1 = paramJSONObject; ; localJSONObject1 = null)
        {
          paramJSONObject = localJSONObject2.getString("type");
          if (paramJSONObject.equalsIgnoreCase("program_ondemand"))
          {
            paramJSONObject = _parseVirtualProgramNode(localJSONObject2, 0);
            paramJSONObject.channelId = m;
            if (localJSONObject1 != null)
              paramJSONObject.setChannelName(localJSONObject1);
            if (k != 0)
              paramJSONObject.channelRatingStar = localRecommendItemNode.ratingStar;
            localRecommendItemNode.setDetail(paramJSONObject);
            break label667;
            localRecommendItemNode.setSmallThumb(paramJSONObject.getString("thumb"));
            break;
            label332: if (!localJSONObject2.containsKey("star"))
              break label661;
            localRecommendItemNode.ratingStar = localJSONObject2.getIntValue("star");
            k = 1;
            break label148;
            if (!paramJSONObject.equalsIgnoreCase("category"))
              break label639;
            j = localJSONObject1.getIntValue("parent_id");
            i = 0;
            paramJSONObject = null;
            break label211;
          }
          if (paramJSONObject.equalsIgnoreCase("channel_ondemand"))
          {
            paramJSONObject = _parseChannelNode(localJSONObject2);
            paramJSONObject.categoryId = j;
            if (k != 0)
              paramJSONObject.ratingStar = localRecommendItemNode.ratingStar;
            localRecommendItemNode.setDetail(paramJSONObject);
            break label667;
          }
          if (paramJSONObject.equalsIgnoreCase("channel_live"))
          {
            paramJSONObject = _parseChannelNode(localJSONObject2);
            paramJSONObject.categoryId = j;
            if (k != 0)
              paramJSONObject.ratingStar = localRecommendItemNode.ratingStar;
            localRecommendItemNode.setDetail(paramJSONObject);
            break label667;
          }
          if (paramJSONObject.equalsIgnoreCase("topic"))
          {
            paramJSONObject = _parseSpecialTopicNode(localJSONObject2);
            if (paramJSONObject != null)
            {
              localRecommendItemNode.mCategoryId = paramJSONObject.categoryId;
              if (k != 0)
                paramJSONObject.channelStar = localRecommendItemNode.ratingStar;
              localRecommendItemNode.setDetail(paramJSONObject);
            }
            if (k == 0)
              break label667;
            paramJSONObject.channelStar = localRecommendItemNode.ratingStar;
            break label667;
          }
          if (paramJSONObject.equalsIgnoreCase("activity"))
          {
            localRecommendItemNode.setDetail(_parseActivity(localJSONObject2));
            break label667;
          }
          localObject1 = localObject2;
          if (!paramJSONObject.equalsIgnoreCase("program_temp"))
            break label671;
          paramJSONObject = _parseProgramTempNode(localJSONObject2);
          if (paramJSONObject != null)
          {
            paramJSONObject.mLiveInVirtual = true;
            paramJSONObject.channelId = m;
            if (localJSONObject1 != null)
              paramJSONObject.setChannelName(localJSONObject1);
            localRecommendItemNode.setDetail(paramJSONObject);
          }
          if (k == 0)
            break label667;
          paramJSONObject.channelRatingStar = localRecommendItemNode.ratingStar;
          break label667;
          label639: j = 0;
          i = 0;
          paramJSONObject = null;
          break label211;
          j = 0;
          m = 0;
        }
        label661: k = 0;
      }
      label667: localObject1 = localRecommendItemNode;
      label671: return localObject1;
    }
    catch (Exception paramJSONObject)
    {
    }
    return null;
  }

  private RecommendItemNode _parseRecommendNode(JSONObject paramJSONObject)
  {
    return null;
  }

  private RecommendPlayingItemNode _parseRecommendPlayingProgram(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        JSONObject localJSONObject = paramJSONObject.getJSONObject("detail");
        if (localJSONObject == null)
          return null;
        RecommendPlayingItemNode localRecommendPlayingItemNode = new RecommendPlayingItemNode();
        localRecommendPlayingItemNode.channelName = paramJSONObject.getString("sub_title");
        localRecommendPlayingItemNode.programName = paramJSONObject.getString("title");
        localRecommendPlayingItemNode.thumb = paramJSONObject.getString("thumb");
        if (paramJSONObject.containsKey("channel_star"))
          localRecommendPlayingItemNode.ratingStar = paramJSONObject.getIntValue("channel_star");
        if (localJSONObject.containsKey("channel_star"))
          localRecommendPlayingItemNode.ratingStar = localJSONObject.getIntValue("channel_star");
        paramJSONObject = localJSONObject.getJSONObject("mediainfo");
        if (paramJSONObject != null)
          localRecommendPlayingItemNode.resId = paramJSONObject.getIntValue("id");
        localRecommendPlayingItemNode.channelId = localJSONObject.getIntValue("channel_id");
        localRecommendPlayingItemNode.programid = localJSONObject.getIntValue("id");
        localRecommendPlayingItemNode.startTime = localJSONObject.getString("start_time");
        if ((localRecommendPlayingItemNode.startTime != null) && (localRecommendPlayingItemNode.startTime.equalsIgnoreCase("00:00:00")))
          localRecommendPlayingItemNode.startTime = "00:00:01";
        localRecommendPlayingItemNode.endTime = localJSONObject.getString("end_time");
        if ((localRecommendPlayingItemNode.endTime != null) && (localRecommendPlayingItemNode.endTime.equalsIgnoreCase("00:00:00")))
          localRecommendPlayingItemNode.endTime = "23:59:59";
        return localRecommendPlayingItemNode;
      }
      catch (Exception paramJSONObject)
      {
      }
    return null;
  }

  private SpecialTopicNode _parseSpecialTopicNode(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        SpecialTopicNode localSpecialTopicNode = new SpecialTopicNode();
        localSpecialTopicNode.id = (paramJSONObject.getIntValue("id") + 1000001);
        localSpecialTopicNode.title = paramJSONObject.getString("title");
        localSpecialTopicNode.thumb = paramJSONObject.getString("thumb");
        localSpecialTopicNode.desc = paramJSONObject.getString("description");
        localSpecialTopicNode.categoryId = paramJSONObject.getIntValue("category_id");
        localSpecialTopicNode.create_time = paramJSONObject.getString("create_time");
        localSpecialTopicNode.update_time = paramJSONObject.getString("update_time");
        return localSpecialTopicNode;
      }
      catch (Exception paramJSONObject)
      {
      }
    return null;
  }

  private UserInfo _parseUserInfo(JSONObject paramJSONObject)
  {
    if (paramJSONObject != null)
      try
      {
        UserInfo localUserInfo = new UserInfo();
        localUserInfo.userKey = paramJSONObject.getString("userId");
        localUserInfo.snsInfo.sns_avatar = paramJSONObject.getString("avatar");
        localUserInfo.snsInfo.sns_name = paramJSONObject.getString("userName");
        if (localUserInfo.snsInfo.sns_name == null)
          localUserInfo.snsInfo.sns_name = "蜻蜓用户";
        localUserInfo.snsInfo.signature = paramJSONObject.getString("signature");
        localUserInfo.snsInfo.age = paramJSONObject.getIntValue("age");
        localUserInfo.snsInfo.sns_gender = paramJSONObject.getString("gender");
        String str = paramJSONObject.getString("is_blocked");
        if ((str != null) && (!str.equalsIgnoreCase("")))
          if (Integer.valueOf(str).intValue() != 0)
            break label181;
        label181: for (localUserInfo.isBlocked = false; ; localUserInfo.isBlocked = true)
        {
          str = paramJSONObject.getString("level");
          paramJSONObject = localUserInfo;
          if (str == null)
            break;
          paramJSONObject = localUserInfo;
          if (str.equalsIgnoreCase(""))
            break;
          localUserInfo.level = Integer.valueOf(str).intValue();
          return localUserInfo;
        }
      }
      catch (Exception paramJSONObject)
      {
      }
    else
      paramJSONObject = null;
    return paramJSONObject;
  }

  private ProgramNode _parseVirtualProgramNode(JSONObject paramJSONObject, int paramInt)
  {
    Object localObject2 = null;
    Object localObject1 = localObject2;
    String str;
    if (paramJSONObject != null)
    {
      str = paramJSONObject.getString("type");
      if ((str == null) || (str.equalsIgnoreCase("program_ondemand")))
        break label56;
      localObject1 = localObject2;
      if (str.equalsIgnoreCase("program_temp"))
        localObject1 = _parseProgramTempNode(paramJSONObject);
    }
    return localObject1;
    label56: localObject1 = new ProgramNode();
    ((ProgramNode)localObject1).id = paramJSONObject.getIntValue("id");
    ((ProgramNode)localObject1).uniqueId = ((ProgramNode)localObject1).id;
    ((ProgramNode)localObject1).channelType = 1;
    ((ProgramNode)localObject1).title = paramJSONObject.getString("title");
    ((ProgramNode)localObject1).duration = paramJSONObject.getDoubleValue("duration");
    ((ProgramNode)localObject1).sequence = paramJSONObject.getIntValue("sequence");
    ((ProgramNode)localObject1).createTime = paramJSONObject.getString("create_time");
    ((ProgramNode)localObject1).updateTime = paramJSONObject.getString("update_time");
    ((ProgramNode)localObject1).sequence = paramJSONObject.getIntValue("sequence");
    ((ProgramNode)localObject1).groupId = paramJSONObject.getIntValue("chatgroup_id");
    paramJSONObject = paramJSONObject.getJSONObject("mediainfo");
    if (paramJSONObject != null)
    {
      ((ProgramNode)localObject1).resId = paramJSONObject.getIntValue("id");
      paramJSONObject = paramJSONObject.getJSONArray("bitrates_url");
      if (paramJSONObject != null)
      {
        ((ProgramNode)localObject1).lstAudioPath = new ArrayList();
        ((ProgramNode)localObject1).lstBitrate = new ArrayList();
        paramInt = 0;
        while (paramInt < paramJSONObject.size())
        {
          localObject2 = paramJSONObject.getJSONObject(paramInt);
          if (localObject2 != null)
          {
            str = ((JSONObject)localObject2).getString("file_path");
            if (str != null)
              ((ProgramNode)localObject1).lstAudioPath.add(str);
            localObject2 = Integer.valueOf(((JSONObject)localObject2).getIntValue("bitrate"));
            if (localObject2 != null)
              ((ProgramNode)localObject1).lstBitrate.add(localObject2);
          }
          paramInt += 1;
        }
      }
    }
    return localObject1;
  }

  private Result parseADConfig(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject1 = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localObject1 != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < ((JSONArray)localObject1).size())
          {
            Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
            if (localObject2 == null)
              break label300;
            String str1 = ((JSONObject)localObject2).getString("name");
            String str2 = ((JSONObject)localObject2).getString("url");
            int k = ((JSONObject)localObject2).getIntValue("percent");
            localObject2 = ((JSONObject)localObject2).getString("region");
            AdConfig localAdConfig = new AdConfig();
            localAdConfig.name = str1;
            localAdConfig.url = str2;
            localAdConfig.percent = k;
            localAdConfig.mRegions = ((String)localObject2);
            localArrayList.add(localAdConfig);
            if (localAdConfig.percent <= 1000)
              break label300;
            int m = localAdConfig.percent / 1000;
            int j = 0;
            if (j >= m - 1)
              break label300;
            localAdConfig = new AdConfig();
            localAdConfig.name = str1;
            localAdConfig.url = str2;
            localAdConfig.percent = k;
            localAdConfig.mRegions = ((String)localObject2);
            localArrayList.add(localAdConfig);
            j += 1;
            continue;
          }
          MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "parseADConfig", "succ");
          localObject1 = new Result(true, localArrayList);
          return localObject1;
        }
      }
      catch (Exception localException)
      {
      }
      MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "parseADConfig", "f:" + paramString);
      return null;
      label300: i += 1;
    }
  }

  private Result parseADLocation(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        if ((paramString != null) && (paramString.getIntValue("errorno") == 0))
        {
          QTADLocation localQTADLocation = new QTADLocation();
          localQTADLocation.city = paramString.getString("city");
          localQTADLocation.regionCode = paramString.getString("region");
          localQTADLocation.school = paramString.getString("school");
          localQTADLocation.ip = paramString.getString("real_ip");
          paramString = new Result(true, localQTADLocation);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseADVFromAirWave(String paramString)
  {
    int j = 0;
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        Object localObject2 = ((JSONObject)JSON.parse(paramString)).getJSONObject("0A89BCB4E85400C4");
        if (localObject2 != null)
        {
          if (((JSONObject)localObject2).getIntValue("returncode") != 200)
            return null;
          paramString = new RecommendItemNode();
          paramString.name = "广告";
          paramString.setSmallThumb(((JSONObject)localObject2).getString("imgurl"));
          paramString.isweb = true;
          ActivityNode localActivityNode = new ActivityNode();
          localActivityNode.name = "广告";
          localActivityNode.infoUrl = paramString.getApproximativeThumb();
          localActivityNode.contentUrl = ((JSONObject)localObject2).getString("clickurl");
          localActivityNode.desc = localActivityNode.name;
          localActivityNode.infoTitle = localActivityNode.name;
          localActivityNode.putUserInfo = false;
          Object localObject1 = ((JSONObject)localObject2).getJSONArray("imgtracking");
          localObject2 = ((JSONObject)localObject2).getJSONArray("thclkurl");
          int i;
          if ((localObject1 != null) && (((JSONArray)localObject1).size() > 0))
          {
            localActivityNode.imageTracking = new ArrayList();
            i = 0;
            while (i < ((JSONArray)localObject1).size())
            {
              String str = (String)((JSONArray)localObject1).get(i);
              localActivityNode.imageTracking.add(str);
              i += 1;
            }
          }
          if ((localObject2 != null) && (((JSONArray)localObject2).size() > 0))
          {
            localActivityNode.clickTracking = new ArrayList();
            i = j;
            while (i < ((JSONArray)localObject2).size())
            {
              localObject1 = (String)((JSONArray)localObject2).get(i);
              localActivityNode.clickTracking.add(localObject1);
              i += 1;
            }
          }
          paramString.mNode = localActivityNode;
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseADWhiteList(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < paramString.size())
          {
            String str = paramString.getString(i);
            if (str == null)
              break label91;
            localArrayList.add(Integer.valueOf(str));
            break label91;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label91: i += 1;
    }
  }

  private Result parseAMAdConfig(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localObject != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < ((JSONArray)localObject).size())
          {
            JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(i);
            if (localJSONObject == null)
              break label251;
            AdConfig localAdConfig = new AdConfig();
            localAdConfig.mAdvId = localJSONObject.getString("advid");
            localAdConfig.mAdMasterUrl = localJSONObject.getString("admasterurl");
            localAdConfig.mMiaozhenUrl = localJSONObject.getString("miaozhenurl");
            localAdConfig.mCustomerUrl = localJSONObject.getString("customerurl");
            localAdConfig.mMMAUrl = localJSONObject.getString("mmaurl");
            localAdConfig.mRegions = localJSONObject.getString("regions");
            localAdConfig.percent = localJSONObject.getIntValue("percent");
            localAdConfig.mEventType = localJSONObject.getString("eventtype");
            localArrayList.add(localAdConfig);
            break label251;
          }
          MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "parseAMAdConfig", "succ");
          localObject = new Result(true, localArrayList);
          return localObject;
        }
      }
      catch (Exception localException)
      {
      }
      MobclickAgent.onEvent(InfoManager.getInstance().getContext(), "parseAMAdConfig", "f:" + paramString);
      return null;
      label251: i += 1;
    }
  }

  private Result parseAdsPos(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          while (i < paramString.size())
          {
            JSONObject localJSONObject = paramString.getJSONObject(i);
            AdPos localAdPos = new AdPos();
            localAdPos.posdesc = localJSONObject.getString("posdesc");
            localAdPos.posid = localJSONObject.getString("posid");
            localAdPos.posquery = localJSONObject.getString("posquery");
            localAdPos.parseDesc();
            localArrayList.add(localAdPos);
            i += 1;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseAdvertisementInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject1 = (JSONObject)JSON.parse(paramString);
        i = ((JSONObject)localObject1).getIntValue("errorno");
        paramString = new AdvertisementItemNode();
        if (i == 0)
        {
          localObject1 = ((JSONObject)localObject1).getJSONObject("data");
          paramString.id = ((JSONObject)localObject1).getString("id");
          paramString.image = ((JSONObject)localObject1).getString("image");
          paramString.resType = ((JSONObject)localObject1).getIntValue("restype");
          i = ((JSONObject)localObject1).getIntValue("size");
          paramString.width = (i / 10000);
          paramString.height = (i - paramString.width * 10000);
          paramString.audioPath = ((JSONObject)localObject1).getString("audio");
          paramString.duration = ((JSONObject)localObject1).getIntValue("duration");
          paramString.landing = ((JSONObject)localObject1).getString("landing");
          paramString.desc = ((JSONObject)localObject1).getString("subtitle");
          paramString.interval = ((JSONObject)localObject1).getIntValue("interval");
          paramString.splash_landing = ((JSONObject)localObject1).getString("splash_landing");
          paramString.skin = ((JSONObject)localObject1).getString("skin");
          paramString.useLocalWebview = ((JSONObject)localObject1).getBooleanValue("use_default_browser");
          if (paramString.useLocalWebview)
            break label511;
          bool = true;
          paramString.useLocalWebview = bool;
          paramString.internal_landing = ((JSONObject)localObject1).getString("internal_landing");
          Object localObject2;
          if ((paramString.internal_landing != null) && (!paramString.internal_landing.equalsIgnoreCase("")))
          {
            localObject2 = paramString.internal_landing.split("/");
            if ((localObject2 != null) && (localObject2.length >= 5))
            {
              paramString.internal_catid = Integer.valueOf(localObject2[1]).intValue();
              paramString.internal_channelid = Integer.valueOf(localObject2[2]).intValue();
              paramString.interval_programid = Integer.valueOf(localObject2[3]).intValue();
              paramString.interval_channeltype = Integer.valueOf(localObject2[4]).intValue();
            }
          }
          localObject1 = ((JSONObject)localObject1).getJSONArray("trackers");
          if (localObject1 != null)
          {
            paramString.mTracker.lstEventType = new ArrayList();
            paramString.mTracker.lstProvider = new ArrayList();
            paramString.mTracker.lstTrackerUrl = new ArrayList();
            i = 0;
            if (i < ((JSONArray)localObject1).size())
            {
              Object localObject3 = ((JSONArray)localObject1).getJSONObject(i);
              localObject2 = ((JSONObject)localObject3).getString("provider");
              String str = ((JSONObject)localObject3).getString("url");
              localObject3 = ((JSONObject)localObject3).getString("event_type");
              if ((localObject2 == null) || (str == null) || (localObject3 == null))
                break label502;
              paramString.mTracker.lstEventType.add(localObject3);
              paramString.mTracker.lstProvider.add(localObject2);
              paramString.mTracker.lstTrackerUrl.add(str);
              break label502;
            }
          }
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label502: i += 1;
      continue;
      label511: boolean bool = false;
    }
  }

  private Result parseAllChannels(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        JSONObject localJSONObject = (JSONObject)JSON.parse(paramString);
        JSONArray localJSONArray = localJSONObject.getJSONArray("data");
        if (localJSONArray != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          paramString = null;
          if (i < localJSONArray.size())
          {
            ChannelNode localChannelNode = _parseChannelNode(localJSONArray.getJSONObject(i));
            if (localChannelNode == null)
              break label141;
            localArrayList.add(localChannelNode);
            localObject = localChannelNode;
            if (paramString == null)
              break label143;
            paramString.nextSibling = localChannelNode;
            localChannelNode.prevSibling = paramString;
            localObject = localChannelNode;
            break label143;
          }
          paramString = new Result(true, localArrayList, "total", String.valueOf(localJSONObject.getIntValue("total")));
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
      }
      return null;
      label141: Object localObject = paramString;
      label143: i += 1;
      paramString = (String)localObject;
    }
  }

  private Result parseBillboardChannels(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < paramString.size())
          {
            JSONObject localJSONObject1 = paramString.getJSONObject(i);
            if (localJSONObject1 == null)
              break label186;
            BillboardItemNode localBillboardItemNode = new BillboardItemNode();
            JSONObject localJSONObject2 = localJSONObject1.getJSONObject("detail");
            if (localJSONObject2 != null)
            {
              ChannelNode localChannelNode = new ChannelNode();
              String str = localJSONObject2.getString("type");
              if ((str == null) || (!str.equalsIgnoreCase("channel_live")))
                break label186;
              localChannelNode.channelType = 0;
              localBillboardItemNode.setDetail(_parseChannelNode(localJSONObject2));
            }
            localBillboardItemNode.name = localJSONObject1.getString("titile");
            localBillboardItemNode.desc = localJSONObject1.getString("sub_title");
            localArrayList.add(localBillboardItemNode);
            break label186;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label186: i += 1;
    }
  }

  private Result parseBillboardProgram(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          while (true)
          {
            if (i < paramString.size())
            {
              JSONObject localJSONObject1 = paramString.getJSONObject(i);
              if (localJSONObject1 != null)
              {
                JSONObject localJSONObject2 = localJSONObject1.getJSONObject("parent_info");
                if (localJSONObject2 == null)
                  return null;
                BillboardItemNode localBillboardItemNode = new BillboardItemNode();
                Object localObject = localJSONObject1.getJSONObject("detail");
                ProgramNode localProgramNode;
                if (localObject != null)
                {
                  localProgramNode = new ProgramNode();
                  String str = ((JSONObject)localObject).getString("type");
                  if ((str == null) || (!str.equalsIgnoreCase("program_ondemand")))
                    break label228;
                }
                label228: for (localProgramNode.channelType = 1; ; localProgramNode.channelType = 0)
                {
                  localObject = _parseVirtualProgramNode((JSONObject)localObject, 0);
                  ((ProgramNode)localObject).channelId = localJSONObject2.getIntValue("parent_id");
                  localBillboardItemNode.parentId = ((ProgramNode)localObject).channelId;
                  localBillboardItemNode.parentType = localJSONObject2.getString("parent_type");
                  localBillboardItemNode.setDetail((Node)localObject);
                  localBillboardItemNode.name = localJSONObject1.getString("titile");
                  localBillboardItemNode.desc = localJSONObject1.getString("sub_title");
                  localArrayList.add(localBillboardItemNode);
                  break;
                }
              }
            }
            else
            {
              paramString = new Result(true, localArrayList);
              return paramString;
            }
            i += 1;
          }
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private BroadcasterNode parseBroadcasterNode(JSONObject paramJSONObject)
  {
    BroadcasterNode localBroadcasterNode = new BroadcasterNode();
    localBroadcasterNode.avatar = paramJSONObject.getString("thumb");
    localBroadcasterNode.nick = paramJSONObject.getString("username");
    localBroadcasterNode.id = paramJSONObject.getIntValue("id");
    localBroadcasterNode.qqName = paramJSONObject.getString("qq_name");
    localBroadcasterNode.qqId = paramJSONObject.getString("qq_id");
    localBroadcasterNode.weiboName = paramJSONObject.getString("weibo_name");
    localBroadcasterNode.weiboId = paramJSONObject.getString("weibo_id");
    return localBroadcasterNode;
  }

  private Result parseCategoryAttr(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < paramString.size())
          {
            Object localObject = paramString.getJSONObject(i);
            if (localObject == null)
              break label224;
            Attributes localAttributes = new Attributes();
            localAttributes.mLstAttribute = new ArrayList();
            localAttributes.id = ((JSONObject)localObject).getIntValue("id");
            localAttributes.name = ((JSONObject)localObject).getString("name");
            localObject = ((JSONObject)localObject).getJSONArray("values");
            if (localObject != null)
            {
              int j = 0;
              if (j < ((JSONArray)localObject).size())
              {
                JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(j);
                Attribute localAttribute = new Attribute();
                localAttribute.id = localJSONObject.getIntValue("id");
                localAttribute.name = localJSONObject.getString("name");
                localAttributes.mLstAttribute.add(localAttribute);
                j += 1;
                continue;
              }
            }
            localArrayList.add(localAttributes);
            break label224;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label224: i += 1;
    }
  }

  private Result parseChannelNode(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = (JSONObject)JSON.parse(paramString);
        if (paramString != null)
        {
          paramString = _parseChannelNode(paramString.getJSONObject("data"));
          if (paramString != null)
          {
            paramString = new Result(true, paramString);
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseCreateUser(String paramString)
  {
    if (paramString != null)
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data").getString("userid");
        return new Result(true, paramString);
      }
      catch (Exception paramString)
      {
        return null;
      }
    return null;
  }

  // ERROR //
  private Result parseCurrentPlayingPrograms(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +211 -> 212
    //   4: new 255	java/util/ArrayList
    //   7: dup
    //   8: invokespecial 256	java/util/ArrayList:<init>	()V
    //   11: astore_2
    //   12: aload_1
    //   13: invokestatic 663	com/alibaba/fastjson/JSON:parse	(Ljava/lang/String;)Ljava/lang/Object;
    //   16: checkcast 20	com/alibaba/fastjson/JSONObject
    //   19: ldc_w 426
    //   22: invokevirtual 237	com/alibaba/fastjson/JSONObject:getJSONArray	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONArray;
    //   25: astore_1
    //   26: iconst_0
    //   27: istore 6
    //   29: iload 6
    //   31: aload_1
    //   32: invokevirtual 242	com/alibaba/fastjson/JSONArray:size	()I
    //   35: if_icmpge +167 -> 202
    //   38: aload_1
    //   39: iload 6
    //   41: invokevirtual 764	com/alibaba/fastjson/JSONArray:get	(I)Ljava/lang/Object;
    //   44: checkcast 20	com/alibaba/fastjson/JSONObject
    //   47: astore 4
    //   49: aload 4
    //   51: ldc 18
    //   53: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   56: istore 7
    //   58: aload 4
    //   60: ldc 29
    //   62: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   65: astore 5
    //   67: new 983	fm/qingting/qtradio/model/PlayingProgramNode
    //   70: dup
    //   71: invokespecial 984	fm/qingting/qtradio/model/PlayingProgramNode:<init>	()V
    //   74: astore_3
    //   75: aload_3
    //   76: iload 7
    //   78: putfield 985	fm/qingting/qtradio/model/PlayingProgramNode:channelId	I
    //   81: aload_3
    //   82: aload 5
    //   84: putfield 986	fm/qingting/qtradio/model/PlayingProgramNode:channelName	Ljava/lang/String;
    //   87: aload 4
    //   89: ldc_w 988
    //   92: invokevirtual 237	com/alibaba/fastjson/JSONObject:getJSONArray	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONArray;
    //   95: astore 4
    //   97: aload_3
    //   98: new 255	java/util/ArrayList
    //   101: dup
    //   102: invokespecial 256	java/util/ArrayList:<init>	()V
    //   105: putfield 991	fm/qingting/qtradio/model/PlayingProgramNode:lstbroadcastersName	Ljava/util/List;
    //   108: iconst_0
    //   109: istore 7
    //   111: iload 7
    //   113: aload 4
    //   115: invokevirtual 242	com/alibaba/fastjson/JSONArray:size	()I
    //   118: if_icmpge +60 -> 178
    //   121: aload 4
    //   123: iload 7
    //   125: invokevirtual 764	com/alibaba/fastjson/JSONArray:get	(I)Ljava/lang/Object;
    //   128: checkcast 20	com/alibaba/fastjson/JSONObject
    //   131: astore 5
    //   133: aload_3
    //   134: aload 5
    //   136: ldc_w 664
    //   139: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   142: putfield 992	fm/qingting/qtradio/model/PlayingProgramNode:programName	Ljava/lang/String;
    //   145: aload_3
    //   146: aload 5
    //   148: ldc_w 994
    //   151: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   154: putfield 997	fm/qingting/qtradio/model/PlayingProgramNode:broadcastTime	Ljava/lang/String;
    //   157: aload_3
    //   158: aload 5
    //   160: ldc_w 496
    //   163: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   166: putfield 998	fm/qingting/qtradio/model/PlayingProgramNode:duration	I
    //   169: iload 7
    //   171: iconst_1
    //   172: iadd
    //   173: istore 7
    //   175: goto -64 -> 111
    //   178: aload_2
    //   179: aload_3
    //   180: invokeinterface 261 2 0
    //   185: pop
    //   186: iload 6
    //   188: iconst_1
    //   189: iadd
    //   190: istore 6
    //   192: goto -163 -> 29
    //   195: astore_1
    //   196: aload_1
    //   197: invokevirtual 946	java/lang/Exception:printStackTrace	()V
    //   200: aconst_null
    //   201: areturn
    //   202: new 694	fm/qingting/framework/data/Result
    //   205: dup
    //   206: iconst_1
    //   207: aload_2
    //   208: invokespecial 697	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;)V
    //   211: areturn
    //   212: aconst_null
    //   213: areturn
    //   214: astore_3
    //   215: goto -29 -> 186
    //
    // Exception table:
    //   from	to	target	type
    //   12	26	195	java/lang/Exception
    //   29	87	195	java/lang/Exception
    //   178	186	195	java/lang/Exception
    //   87	108	214	java/lang/Exception
    //   111	169	214	java/lang/Exception
  }

  private Result parseGetMyPodcasterList(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = new Result(true, (JSONObject)JSON.parse(paramString));
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseGetPlayHistory(String paramString)
  {
    if (paramString != null)
      try
      {
        if (!paramString.equalsIgnoreCase(""))
        {
          JSONArray localJSONArray = (JSONArray)JSON.parse(((JSONObject)JSON.parse(paramString)).getString("data"));
          Log.d("netparser", "sym:云端播放记录共" + localJSONArray.size() + "条");
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          if (i < localJSONArray.size())
          {
            PlayHistoryNode localPlayHistoryNode = new PlayHistoryNode();
            paramString = localJSONArray.getJSONObject(i);
            int j = paramString.getIntValue("resid");
            int k = paramString.getIntValue("pid");
            String str1 = paramString.getString("pname");
            int m = paramString.getIntValue("cid");
            String str2 = paramString.getString("cname");
            int n = paramString.getIntValue("catid");
            int i1 = paramString.getIntValue("ctype");
            paramString = paramString.getString("cavatar");
            localPlayHistoryNode.categoryId = n;
            localPlayHistoryNode.subCatId = 0;
            localPlayHistoryNode.channelId = m;
            localPlayHistoryNode.channelThumb = paramString;
            localPlayHistoryNode.channelName = str2;
            localPlayHistoryNode.programId = k;
            localPlayHistoryNode.playContent = i1;
            ChannelNode localChannelNode = ChannelHelper.getInstance().getChannel(m, i1, str2);
            paramString = localChannelNode;
            if (localChannelNode == null)
            {
              if (i1 != 1)
                break label394;
              paramString = ChannelHelper.getInstance().getFakeVirtualChannel(m, n, str2);
              InfoManager.getInstance().reloadVirtualProgramsSchedule(paramString, null);
            }
            while (true)
            {
              localPlayHistoryNode.playNode = paramString.getProgramNode(k);
              if (localPlayHistoryNode.playNode == null)
              {
                if (i1 == 1)
                  InfoManager.getInstance().loadProgramInfo(paramString, k, localPlayHistoryNode);
                paramString = new ProgramNode();
                paramString.channelId = m;
                paramString.channelType = i1;
                paramString.id = k;
                if (i1 == 1)
                  paramString.uniqueId = k;
                paramString.title = str1;
                paramString.setChannelName(str2);
                paramString.resId = j;
                paramString.parent = localPlayHistoryNode;
                localPlayHistoryNode.playNode = paramString;
              }
              localArrayList.add(localPlayHistoryNode);
              i += 1;
              break;
              label394: paramString = localChannelNode;
              if (i1 == 0)
                paramString = ChannelHelper.getInstance().getFakeLiveChannel(m, n, str2);
            }
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseGetUserData(String paramString)
  {
    if (paramString != null);
    try
    {
      Object localObject = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
      paramString = new UserProfile();
      String str = ((JSONObject)localObject).getString("_id");
      paramString.setUserKey(str, Integer.valueOf(((JSONObject)localObject).getString("sns_type")).intValue());
      UserInfo localUserInfo = new UserInfo();
      localUserInfo.userKey = str;
      localUserInfo.snsInfo.sns_id = ((JSONObject)localObject).getString("sns_id");
      localUserInfo.snsInfo.sns_name = ((JSONObject)localObject).getString("username");
      localUserInfo.snsInfo.sns_avatar = ((JSONObject)localObject).getString("avatar");
      str = ((JSONObject)localObject).getString("is_blocked");
      if ((str != null) && (!str.equalsIgnoreCase("")))
        if (Integer.valueOf(str).intValue() != 0)
          break label193;
      label193: for (localUserInfo.isBlocked = false; ; localUserInfo.isBlocked = true)
      {
        localObject = ((JSONObject)localObject).getString("level");
        if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
          localUserInfo.level = Integer.valueOf((String)localObject).intValue();
        paramString.setUserInfo(localUserInfo);
        return new Result(true, paramString);
      }
    }
    catch (Exception paramString)
    {
      return null;
    }
    catch (Error paramString)
    {
      label202: break label202;
    }
  }

  private Result parseGroupInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject1 = (JSONObject)((JSONObject)JSON.parse(paramString)).get("data");
        paramString = new GroupInfo();
        paramString.groupId = ((JSONObject)localObject1).getString("groupid");
        paramString.groupName = ((JSONObject)localObject1).getString("groupname");
        paramString.userCnt = ((JSONObject)localObject1).getIntValue("num_of_users");
        paramString.groupDesc = ((JSONObject)localObject1).getString("description");
        paramString.groupThumb = ((JSONObject)localObject1).getString("thumb");
        localObject1 = ((JSONObject)localObject1).getJSONObject("detail");
        if (localObject1 != null)
        {
          Object localObject2 = ((JSONObject)localObject1).getJSONArray("admins");
          if ((localObject2 != null) && (((JSONArray)localObject2).size() > 0))
          {
            paramString.lstAdmins = new ArrayList();
            i = 0;
            if (i < ((JSONArray)localObject2).size())
            {
              AdminInfo localAdminInfo = _parseAdminInfo(((JSONArray)localObject2).getJSONObject(i));
              if (localAdminInfo == null)
                break label263;
              paramString.lstAdmins.add(localAdminInfo);
              break label263;
            }
          }
          localObject1 = ((JSONObject)localObject1).getJSONArray("managers");
          if ((localObject1 != null) && (((JSONArray)localObject1).size() > 0))
          {
            paramString.lstManagers = new ArrayList();
            i = 0;
            if (i < ((JSONArray)localObject1).size())
            {
              localObject2 = _parseUserInfo(((JSONArray)localObject1).getJSONObject(i));
              if (localObject2 == null)
                break label272;
              paramString.lstManagers.add(localObject2);
              break label272;
            }
          }
        }
        paramString = new Result(true, paramString);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label263: i += 1;
      continue;
      label272: i += 1;
    }
  }

  private Result parseGroupUsers(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        if ((paramString != null) && (paramString.size() > 0))
        {
          int j = paramString.size();
          int i = 0;
          if (i < j)
          {
            Object localObject = paramString.getJSONObject(i);
            UserInfo localUserInfo = new UserInfo();
            localUserInfo.userKey = ((JSONObject)localObject).getString("userid");
            localUserInfo.snsInfo.sns_name = ((JSONObject)localObject).getString("username");
            localUserInfo.snsInfo.age = ((JSONObject)localObject).getIntValue("age");
            localUserInfo.snsInfo.signature = ((JSONObject)localObject).getString("signature");
            localUserInfo.snsInfo.sns_gender = ((JSONObject)localObject).getString("gender");
            localUserInfo.snsInfo.sns_avatar = ((JSONObject)localObject).getString("avatar");
            String str = ((JSONObject)localObject).getString("is_blocked");
            if ((str != null) && (!str.equalsIgnoreCase("")))
              if (Integer.valueOf(str).intValue() != 0)
                break label252;
            label252: for (localUserInfo.isBlocked = false; ; localUserInfo.isBlocked = true)
            {
              localObject = ((JSONObject)localObject).getString("level");
              if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
                localUserInfo.level = Integer.valueOf((String)localObject).intValue();
              localArrayList.add(localUserInfo);
              i += 1;
              break;
            }
          }
          if ((localArrayList == null) || (localArrayList.size() <= 0));
        }
        paramString = new Result(true, localArrayList);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseHotSearchKeywords(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        ArrayList localArrayList = new ArrayList();
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          int i = 0;
          while (i < paramString.size())
          {
            JSONObject localJSONObject = (JSONObject)paramString.get(i);
            SearchHotKeyword localSearchHotKeyword = new SearchHotKeyword();
            localSearchHotKeyword.keyword = localJSONObject.getString("name");
            localSearchHotKeyword.searchCnt = localJSONObject.getIntValue("count");
            localArrayList.add(localSearchHotKeyword);
            i += 1;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return null;
      }
    return null;
  }

  private Result parseIMBaseUserInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        Object localObject = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        paramString = new UserInfo();
        paramString.userId = ((JSONObject)localObject).getString("userid");
        paramString.userKey = paramString.userId;
        paramString.snsInfo.sns_avatar = ((JSONObject)localObject).getString("avatar");
        paramString.snsInfo.signature = ((JSONObject)localObject).getString("signature");
        paramString.snsInfo.sns_gender = ((JSONObject)localObject).getString("gender");
        String str = ((JSONObject)localObject).getString("is_blocked");
        if ((str != null) && (!str.equalsIgnoreCase("")))
          if (Integer.valueOf(str).intValue() != 0)
            break label172;
        label172: for (paramString.isBlocked = false; ; paramString.isBlocked = true)
        {
          localObject = ((JSONObject)localObject).getString("level");
          if ((localObject != null) && (!((String)localObject).equalsIgnoreCase("")))
            paramString.level = Integer.valueOf((String)localObject).intValue();
          if (paramString == null)
            break;
          return new Result(true, paramString);
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseIMHistory(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject = (JSONObject)JSON.parse(paramString);
        paramString = new ArrayList();
        localObject = ((JSONObject)localObject).getJSONArray("data");
        if (localObject != null)
        {
          i = 0;
          if (i < ((JSONArray)localObject).size())
          {
            String str = ((JSONArray)localObject).getJSONObject(i).getString("message");
            if (str == null)
              break label118;
            IMMessage localIMMessage = new IMMessage();
            IMMessage.parseEvent(str, localIMMessage);
            if (localIMMessage == null)
              break label118;
            paramString.add(localIMMessage);
            break label118;
          }
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label118: i += 1;
    }
  }

  private Result parseIMServer(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = new Result(true, (String)((JSONObject)JSON.parse(paramString)).get("server"));
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseIPLocation(String paramString)
  {
    if (paramString != null)
      try
      {
        QTLocation localQTLocation = new QTLocation();
        paramString = (JSONObject)JSON.parse(paramString);
        localQTLocation.city = paramString.getString("city");
        localQTLocation.region = paramString.getString("region");
        localQTLocation.ip = paramString.getString("ip");
        paramString = new Result(true, localQTLocation);
        return paramString;
      }
      catch (Exception paramString)
      {
        return null;
      }
    return null;
  }

  private Result parseJdAd(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
      ArrayList localArrayList = new ArrayList();
      int i = 0;
      while (i < paramString.size())
      {
        localArrayList.add(new CommodityInfo());
        i += 1;
      }
      paramString = new Result(true, localArrayList);
      return paramString;
    }
    catch (Exception paramString)
    {
    }
    return null;
  }

  private Result parseLinkInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = _parseRecommendNode(((JSONObject)JSON.parse(paramString)).getJSONObject("data"));
        if (paramString != null)
        {
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseListActivities(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        if (paramString != null)
        {
          i = 0;
          if (i < paramString.size())
          {
            ActivityNode localActivityNode = _parseActivity(paramString.getJSONObject(i));
            if (localActivityNode == null)
              break label92;
            localArrayList.add(localActivityNode);
            break label92;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label92: i += 1;
    }
  }

  private Result parseListCategories(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          i = 0;
          if (i < paramString.size())
          {
            JSONObject localJSONObject = paramString.getJSONObject(i);
            if (localJSONObject == null)
              break label139;
            CategoryNode localCategoryNode = new CategoryNode();
            localCategoryNode.categoryId = localJSONObject.getIntValue("id");
            localCategoryNode.name = localJSONObject.getString("name");
            localCategoryNode.sectionId = localJSONObject.getIntValue("section_id");
            localCategoryNode.type = 1;
            localArrayList.add(localCategoryNode);
            break label139;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label139: i += 1;
    }
  }

  private Result parseLiveCategory(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        ArrayList localArrayList = new ArrayList();
        JSONArray localJSONArray1 = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localJSONArray1 != null)
        {
          i = 0;
          int k;
          if (i < localJSONArray1.size())
          {
            paramString = localJSONArray1.getJSONObject(i);
            JSONArray localJSONArray2 = paramString.getJSONArray("sections");
            String str = paramString.getString("group_name");
            int j = 0;
            if (j >= localJSONArray2.size())
              break label358;
            paramString = localJSONArray2.getJSONObject(j);
            CategoryNode localCategoryNode = new CategoryNode();
            localCategoryNode.type = 0;
            if (str != null)
            {
              if (str.equalsIgnoreCase("地区"))
                localCategoryNode.type = 4;
            }
            else
            {
              localCategoryNode.name = paramString.getString("name");
              localCategoryNode.sectionId = paramString.getIntValue("section_id");
              paramString = paramString.getJSONObject("redirect");
              if (paramString == null)
                continue;
              Object localObject = Integer.valueOf(paramString.getIntValue("category_id"));
              if (localObject != null)
                localCategoryNode.categoryId = ((Integer)localObject).intValue();
              JSONArray localJSONArray3 = paramString.getJSONArray("attributes");
              if (localJSONArray3 == null)
                continue;
              paramString = "";
              k = 0;
              if (k >= localJSONArray3.size())
                continue;
              localObject = paramString + localJSONArray3.getIntValue(k);
              paramString = (String)localObject;
              if (k >= localJSONArray3.size() - 1)
                continue;
              paramString = (String)localObject + "/";
              continue;
            }
            if (!str.equalsIgnoreCase("内容"))
              continue;
            localCategoryNode.type = 3;
            continue;
            localCategoryNode.mAttributesPath = paramString;
            localArrayList.add(localCategoryNode);
            j += 1;
            continue;
          }
          else
          {
            paramString = new Result(true, localArrayList);
            return paramString;
          }
          k += 1;
          continue;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label358: i += 1;
    }
  }

  private Result parseLiveProgramSchedule(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      Object localObject;
      int j;
      try
      {
        JSONObject localJSONObject = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        ProgramScheduleList localProgramScheduleList = new ProgramScheduleList(0);
        i = 1;
        paramString = null;
        if (i <= 7)
        {
          JSONArray localJSONArray = localJSONObject.getJSONArray(String.valueOf(i));
          localObject = paramString;
          if (localJSONArray == null)
            break label223;
          localObject = paramString;
          if (localJSONArray.size() <= 0)
            break label223;
          ProgramSchedule localProgramSchedule = new ProgramSchedule();
          localProgramSchedule.dayOfWeek = i;
          localProgramSchedule.mLstProgramNodes = new ArrayList();
          j = 0;
          if (j < localJSONArray.size())
          {
            localObject = _parseLiveProgramNode(localJSONArray.getJSONObject(j), i);
            if (localObject == null)
              break label214;
            if (paramString != null)
            {
              paramString.nextSibling = ((Node)localObject);
              ((ProgramNode)localObject).prevSibling = paramString;
            }
            localProgramSchedule.mLstProgramNodes.add(localObject);
            paramString = (String)localObject;
            break label214;
          }
          localProgramScheduleList.mLstProgramsScheduleNodes.add(localProgramSchedule);
          localObject = paramString;
          break label223;
        }
        if (localProgramScheduleList.mLstProgramsScheduleNodes.size() > 0)
        {
          paramString = new Result(true, localProgramScheduleList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label214: j += 1;
      continue;
      label223: i += 1;
      paramString = (String)localObject;
    }
  }

  private Result parseLocalRecommendInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int j;
      int m;
      try
      {
        JSONArray localJSONArray = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localJSONArray != null)
        {
          RecommendCategoryNode localRecommendCategoryNode = new RecommendCategoryNode();
          j = 0;
          if (j < localJSONArray.size())
          {
            Object localObject2 = localJSONArray.getJSONObject(j);
            if (localObject2 == null)
              break label424;
            String str1 = ((JSONObject)localObject2).getString("name");
            String str2 = ((JSONObject)localObject2).getString("brief_name");
            if (str1 == null)
              break label424;
            k = ((JSONObject)localObject2).getIntValue("section_id");
            Object localObject1 = "";
            paramString = ((JSONObject)localObject2).getJSONObject("redirect");
            if (paramString == null)
              break label386;
            Object localObject3 = paramString.getString("redirect_type");
            if ((localObject3 == null) || (!((String)localObject3).equalsIgnoreCase("section")))
              break label379;
            i = paramString.getIntValue("section_id");
            m = paramString.getIntValue("category_id");
            localObject3 = paramString.getJSONArray("attributes");
            paramString = (String)localObject1;
            if (localObject3 == null)
              break label404;
            k = 0;
            paramString = (String)localObject1;
            if (k >= ((JSONArray)localObject3).size())
              break label404;
            paramString = (String)localObject1 + ((JSONArray)localObject3).getIntValue(k);
            localObject1 = paramString;
            if (k >= ((JSONArray)localObject3).size() - 1)
              break label395;
            localObject1 = paramString + "/";
            break label395;
            localObject1 = ((JSONObject)localObject2).getJSONArray("recommends");
            if (localObject1 == null)
              break label424;
            m = 0;
            if (m >= ((JSONArray)localObject1).size())
              break label424;
            localObject2 = _parseRecommendItemNode(((JSONArray)localObject1).getJSONObject(m), false);
            if (localObject2 == null)
              break label415;
            if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
              ((RecommendItemNode)localObject2).mAttributesPath = paramString;
            if (i != 0)
              ((RecommendItemNode)localObject2).mCategoryId = i;
            ((RecommendItemNode)localObject2).sectionId = k;
            if (str1 != null)
              ((RecommendItemNode)localObject2).belongName = str1;
            ((RecommendItemNode)localObject2).briefName = str2;
            localRecommendCategoryNode.insertRecCategory((RecommendItemNode)localObject2, 1);
            break label415;
          }
          paramString = new Result(true, localRecommendCategoryNode);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label379: int i = k;
      continue;
      label386: paramString = "";
      i = 0;
      continue;
      label395: k += 1;
      continue;
      label404: int k = i;
      i = m;
      continue;
      label415: m += 1;
      continue;
      label424: j += 1;
    }
  }

  private Result parseMediaCenter(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        Object localObject2 = paramString.getJSONObject("radiostations_hls");
        Object localObject1 = paramString.getJSONObject("radiostations_download");
        JSONObject localJSONObject = paramString.getJSONObject("storedaudio_m4a");
        paramString = new MediaCenter();
        paramString.mapMediaCenters = new HashMap();
        localObject2 = _parseMediaCenter((JSONObject)localObject2, 0);
        if ((localObject2 != null) && (((List)localObject2).size() > 0))
          paramString.mapMediaCenters.put("radiohls", localObject2);
        localObject1 = _parseMediaCenter((JSONObject)localObject1, 0);
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
          paramString.mapMediaCenters.put("radiodownload", localObject1);
        localObject1 = _parseMediaCenter(localJSONObject, 1);
        if ((localObject1 != null) && (((List)localObject1).size() > 0))
          paramString.mapMediaCenters.put("virutalchannel", localObject1);
        paramString = new Result(true, paramString);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private List<Node> parseMiniFav(JSONArray paramJSONArray)
  {
    ArrayList localArrayList;
    int i;
    if ((paramJSONArray != null) && (paramJSONArray.size() > 0))
    {
      localArrayList = new ArrayList();
      i = 0;
    }
    while (true)
    {
      if (i < paramJSONArray.size());
      try
      {
        JSONObject localJSONObject = paramJSONArray.getJSONObject(i);
        MiniFavNode localMiniFavNode = new MiniFavNode();
        localMiniFavNode.parentId = Integer.valueOf(localJSONObject.getString("parentid")).intValue();
        localMiniFavNode.id = Integer.valueOf(localJSONObject.getString("id")).intValue();
        localMiniFavNode.channelType = localJSONObject.getIntValue("type");
        localMiniFavNode.name = localJSONObject.getString("name");
        localMiniFavNode.categoryId = Integer.valueOf(localJSONObject.getString("catid")).intValue();
        localMiniFavNode.time = localJSONObject.getLongValue("time");
        localArrayList.add(localMiniFavNode);
        label144: i += 1;
        continue;
        return localArrayList;
        return null;
      }
      catch (Exception localException)
      {
        break label144;
      }
    }
  }

  private Result parseNewRecommendV2Banner(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        RecommendCategoryNode localRecommendCategoryNode = new RecommendCategoryNode();
        _parseNewRecommendV2Banner((JSONObject)JSON.parse(paramString), localRecommendCategoryNode.getLstBanner());
        paramString = new Result(true, localRecommendCategoryNode);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseNewSearch(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        i = 0;
        int j;
        if (i < paramString.size())
        {
          Object localObject1 = paramString.getJSONObject(i);
          Object localObject2 = ((JSONObject)localObject1).getString("groupValue");
          localObject1 = ((JSONObject)localObject1).getJSONObject("doclist");
          JSONArray localJSONArray = ((JSONObject)localObject1).getJSONArray("docs");
          int k = ((JSONObject)localObject1).getInteger("numFound").intValue();
          localObject1 = new ArrayList();
          j = 0;
          if (j < localJSONArray.size())
          {
            JSONObject localJSONObject = localJSONArray.getJSONObject(j);
            String str = localJSONObject.getString("type");
            if (str != null)
            {
              SearchItemNode localSearchItemNode = new SearchItemNode();
              if (str.equalsIgnoreCase("channel_ondemand"))
              {
                localSearchItemNode.groupType = 2;
                localSearchItemNode.channelType = 1;
                localSearchItemNode.channelId = localJSONObject.getIntValue("id");
                localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
                localSearchItemNode.cover = localJSONObject.getString("cover");
                localSearchItemNode.name = localJSONObject.getString("title");
                localSearchItemNode.catName = localJSONObject.getString("category_name");
                localSearchItemNode.desc = localJSONObject.getString("description");
                localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                localSearchItemNode.cName = localSearchItemNode.name;
                localSearchItemNode.star = localJSONObject.getIntValue("star");
                if (localSearchItemNode.channelId != 0)
                  ((List)localObject1).add(localSearchItemNode);
              }
              else
              {
                if (str.equalsIgnoreCase("program_ondemand"))
                {
                  localSearchItemNode.groupType = 1;
                  localSearchItemNode.channelType = 1;
                  localSearchItemNode.programId = localJSONObject.getIntValue("id");
                  localSearchItemNode.channelId = localJSONObject.getIntValue("parent_id");
                  localSearchItemNode.categoryId = 0;
                  localSearchItemNode.cover = localJSONObject.getString("cover");
                  localSearchItemNode.name = localJSONObject.getString("title");
                  localSearchItemNode.cName = localJSONObject.getString("parent_name");
                  localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                  continue;
                }
                if (str.equalsIgnoreCase("channel_live"))
                {
                  localSearchItemNode.groupType = 0;
                  localSearchItemNode.channelType = 0;
                  localSearchItemNode.channelId = localJSONObject.getIntValue("id");
                  localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
                  localSearchItemNode.cover = localJSONObject.getString("cover");
                  localSearchItemNode.name = localJSONObject.getString("title");
                  localSearchItemNode.catName = localJSONObject.getString("category_name");
                  localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                  localSearchItemNode.freqs = localJSONObject.getString("freqs");
                  localSearchItemNode.cName = localSearchItemNode.name;
                  localSearchItemNode.audience_count = localJSONObject.getIntValue("audience_count");
                  continue;
                }
                if (!str.equalsIgnoreCase("people_podcaster"))
                  continue;
                localSearchItemNode.groupType = 3;
                localSearchItemNode.podcasterId = localJSONObject.getIntValue("id");
                localSearchItemNode.podcasterTitle = localJSONObject.getString("title");
                localSearchItemNode.podcasterDescription = localJSONObject.getString("description");
                localSearchItemNode.podcasterFan_num = localJSONObject.getIntValue("fan_num");
                localSearchItemNode.podcasterNickName = localJSONObject.getString("nickname");
                localSearchItemNode.podcasterAvatar = localJSONObject.getString("avatar");
                localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                localSearchItemNode.name = localSearchItemNode.podcasterNickName;
                localSearchItemNode.cover = localSearchItemNode.podcasterAvatar;
                localSearchItemNode.catName = localSearchItemNode.podcasterDescription;
                localSearchItemNode.channelId = localSearchItemNode.podcasterId;
                continue;
              }
            }
          }
          else
          {
            if (((String)localObject2).equalsIgnoreCase("channel_ondemand"))
            {
              localObject2 = new SearchNode.SearchResult();
              ((SearchNode.SearchResult)localObject2).setResult(k, 1, (List)localObject1);
              UserDataUtil.addUserData((List)localObject1);
              localArrayList.add(localObject2);
              break label883;
            }
            if (((String)localObject2).equalsIgnoreCase("program_ondemand"))
            {
              localObject2 = new SearchNode.SearchResult();
              ((SearchNode.SearchResult)localObject2).setResult(k, 4, (List)localObject1);
              UserDataUtil.addUserData((List)localObject1);
              localArrayList.add(localObject2);
              break label883;
            }
            if (((String)localObject2).equalsIgnoreCase("channel_live"))
            {
              localObject2 = new SearchNode.SearchResult();
              ((SearchNode.SearchResult)localObject2).setResult(k, 3, (List)localObject1);
              localArrayList.add(localObject2);
              break label883;
            }
            if (!((String)localObject2).equalsIgnoreCase("people_podcaster"))
              break label883;
            localObject2 = new SearchNode.SearchResult();
            ((SearchNode.SearchResult)localObject2).setResult(k, 2, (List)localObject1);
            localArrayList.add(localObject2);
            break label883;
          }
        }
        else
        {
          paramString = new Result(true, localArrayList);
          return paramString;
        }
        j += 1;
        continue;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label883: i += 1;
    }
  }

  private Result parseNewSearchLoadMore(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (paramString.size() > 0)
        {
          Object localObject = paramString.getJSONObject(0);
          paramString = ((JSONObject)localObject).getString("groupValue");
          localObject = ((JSONObject)localObject).getJSONObject("doclist").getJSONArray("docs");
          ArrayList localArrayList = new ArrayList();
          SearchNode.SearchResult localSearchResult = new SearchNode.SearchResult();
          int i = 0;
          while (true)
          {
            if (i < ((JSONArray)localObject).size())
            {
              JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(i);
              String str = localJSONObject.getString("type");
              if (str != null)
              {
                SearchItemNode localSearchItemNode = new SearchItemNode();
                if (str.equalsIgnoreCase("channel_ondemand"))
                {
                  localSearchItemNode.groupType = 2;
                  localSearchItemNode.channelType = 1;
                  localSearchItemNode.channelId = localJSONObject.getIntValue("id");
                  localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
                  localSearchItemNode.cover = localJSONObject.getString("cover");
                  localSearchItemNode.name = localJSONObject.getString("title");
                  localSearchItemNode.catName = localJSONObject.getString("category_name");
                  localSearchItemNode.desc = localJSONObject.getString("description");
                  localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                  localSearchItemNode.cName = localSearchItemNode.name;
                  localSearchItemNode.star = localJSONObject.getIntValue("star");
                }
                while (localSearchItemNode.channelId != 0)
                {
                  localArrayList.add(localSearchItemNode);
                  break;
                  if (str.equalsIgnoreCase("program_ondemand"))
                  {
                    localSearchItemNode.groupType = 1;
                    localSearchItemNode.channelType = 1;
                    localSearchItemNode.programId = localJSONObject.getIntValue("id");
                    localSearchItemNode.channelId = localJSONObject.getIntValue("parent_id");
                    localSearchItemNode.categoryId = 0;
                    localSearchItemNode.cover = localJSONObject.getString("cover");
                    localSearchItemNode.name = localJSONObject.getString("title");
                    localSearchItemNode.cName = localJSONObject.getString("parent_name");
                    localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                  }
                  else if (str.equalsIgnoreCase("channel_live"))
                  {
                    localSearchItemNode.groupType = 0;
                    localSearchItemNode.channelType = 0;
                    localSearchItemNode.channelId = localJSONObject.getIntValue("id");
                    localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
                    localSearchItemNode.cover = localJSONObject.getString("cover");
                    localSearchItemNode.name = localJSONObject.getString("title");
                    localSearchItemNode.catName = localJSONObject.getString("category_name");
                    localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                    localSearchItemNode.freqs = localJSONObject.getString("freqs");
                    localSearchItemNode.cName = localSearchItemNode.name;
                    localSearchItemNode.audience_count = localJSONObject.getIntValue("audience_count");
                  }
                  else if (str.equalsIgnoreCase("people_podcaster"))
                  {
                    localSearchItemNode.groupType = 3;
                    localSearchItemNode.podcasterId = localJSONObject.getIntValue("id");
                    localSearchItemNode.podcasterTitle = localJSONObject.getString("title");
                    localSearchItemNode.podcasterDescription = localJSONObject.getString("description");
                    localSearchItemNode.podcasterFan_num = localJSONObject.getIntValue("fan_num");
                    localSearchItemNode.podcasterNickName = localJSONObject.getString("nickname");
                    localSearchItemNode.podcasterAvatar = localJSONObject.getString("avatar");
                    localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                    localSearchItemNode.name = localSearchItemNode.podcasterNickName;
                    localSearchItemNode.cover = localSearchItemNode.podcasterAvatar;
                    localSearchItemNode.catName = localSearchItemNode.podcasterDescription;
                    localSearchItemNode.channelId = localSearchItemNode.podcasterId;
                  }
                }
              }
            }
            else
            {
              if (paramString.equalsIgnoreCase("channel_ondemand"))
              {
                localSearchResult.setResult(0, 1, localArrayList);
                UserDataUtil.addUserData(localArrayList);
              }
              while (true)
              {
                return new Result(true, localSearchResult);
                if (paramString.equalsIgnoreCase("program_ondemand"))
                {
                  localSearchResult.setResult(0, 4, localArrayList);
                  UserDataUtil.addUserData(localArrayList);
                }
                else if (paramString.equalsIgnoreCase("channel_live"))
                {
                  localSearchResult.setResult(0, 3, localArrayList);
                }
                else if (paramString.equalsIgnoreCase("people_podcaster"))
                {
                  localSearchResult.setResult(0, 2, localArrayList);
                }
              }
            }
            i += 1;
          }
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseNewSearchSuggestion(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        i = 0;
        int j;
        if (i < paramString.size())
        {
          JSONArray localJSONArray = paramString.getJSONObject(i).getJSONObject("doclist").getJSONArray("docs");
          j = 0;
          if (j >= localJSONArray.size())
            break label687;
          JSONObject localJSONObject = localJSONArray.getJSONObject(j);
          String str = localJSONObject.getString("type");
          if (str != null)
          {
            SearchItemNode localSearchItemNode = new SearchItemNode();
            if (str.equalsIgnoreCase("channel_ondemand"))
            {
              localSearchItemNode.groupType = 2;
              localSearchItemNode.channelType = 1;
              localSearchItemNode.channelId = localJSONObject.getIntValue("id");
              localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
              localSearchItemNode.cover = localJSONObject.getString("cover");
              localSearchItemNode.name = localJSONObject.getString("title");
              localSearchItemNode.catName = localJSONObject.getString("category_name");
              localSearchItemNode.desc = localJSONObject.getString("description");
              localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
              localSearchItemNode.cName = localSearchItemNode.name;
              localSearchItemNode.star = localJSONObject.getIntValue("star");
              if (localSearchItemNode.channelId != 0)
                localArrayList.add(localSearchItemNode);
            }
            else
            {
              if (str.equalsIgnoreCase("program_ondemand"))
              {
                localSearchItemNode.groupType = 1;
                localSearchItemNode.channelType = 1;
                localSearchItemNode.programId = localJSONObject.getIntValue("id");
                localSearchItemNode.channelId = localJSONObject.getIntValue("parent_id");
                localSearchItemNode.categoryId = 0;
                localSearchItemNode.cover = localJSONObject.getString("cover");
                localSearchItemNode.name = localJSONObject.getString("title");
                localSearchItemNode.cName = localJSONObject.getString("parent_name");
                localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                continue;
              }
              if (str.equalsIgnoreCase("channel_live"))
              {
                localSearchItemNode.groupType = 0;
                localSearchItemNode.channelType = 0;
                localSearchItemNode.channelId = localJSONObject.getIntValue("id");
                localSearchItemNode.categoryId = localJSONObject.getIntValue("category_id");
                localSearchItemNode.cover = localJSONObject.getString("cover");
                localSearchItemNode.name = localJSONObject.getString("title");
                localSearchItemNode.catName = localJSONObject.getString("category_name");
                localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
                localSearchItemNode.freqs = localJSONObject.getString("freqs");
                localSearchItemNode.cName = localSearchItemNode.name;
                localSearchItemNode.audience_count = localJSONObject.getIntValue("audience_count");
                continue;
              }
              if (!str.equalsIgnoreCase("people_podcaster"))
                continue;
              localSearchItemNode.groupType = 3;
              localSearchItemNode.podcasterId = localJSONObject.getIntValue("id");
              localSearchItemNode.podcasterTitle = localJSONObject.getString("title");
              localSearchItemNode.podcasterDescription = localJSONObject.getString("description");
              localSearchItemNode.podcasterFan_num = localJSONObject.getIntValue("fan_num");
              localSearchItemNode.podcasterNickName = localJSONObject.getString("nickname");
              localSearchItemNode.podcasterAvatar = localJSONObject.getString("avatar");
              localSearchItemNode.totalScore = localJSONObject.getDoubleValue("totalscore");
              localSearchItemNode.name = localSearchItemNode.podcasterNickName;
              localSearchItemNode.cover = localSearchItemNode.podcasterAvatar;
              localSearchItemNode.catName = localSearchItemNode.podcasterDescription;
              localSearchItemNode.channelId = localSearchItemNode.podcasterId;
              continue;
            }
          }
        }
        else
        {
          Collections.sort(localArrayList, new SearchNode.NewSearchComparator());
          paramString = new Result(true, localArrayList);
          return paramString;
        }
        j += 1;
        continue;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label687: i += 1;
    }
  }

  private Result parsePodcasterBaseInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        if (paramString == null)
          return null;
        paramString = _parsePodcaster(paramString);
        if (paramString != null)
        {
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parsePodcasterChannels(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          ChannelNode localChannelNode = _parseChannelNode(paramString.getJSONObject(i));
          if (localChannelNode == null)
            break label88;
          localArrayList.add(localChannelNode);
          break label88;
        }
        paramString = new Result(true, localArrayList);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label88: i += 1;
    }
  }

  private Result parsePodcasterDetailInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    return null;
  }

  private Result parsePodcasterLatestInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          JSONObject localJSONObject = paramString.getJSONObject(i);
          if (localJSONObject == null)
            break label137;
          Object localObject = localJSONObject.getJSONObject("program");
          if (localObject == null)
            break label137;
          localObject = _parseVirtualProgramNode((JSONObject)localObject, 0);
          if (localObject == null)
            break label137;
          ((ProgramNode)localObject).channelId = localJSONObject.getIntValue("radio_channel_id");
          ((ProgramNode)localObject).setChannelName(localJSONObject.getString("channel_name"));
          localArrayList.add(localObject);
          break label137;
        }
        paramString = new Result(true, localArrayList);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label137: i += 1;
    }
  }

  private Result parsePostBarrage(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = (JSONObject)JSON.parse(paramString);
        int i = paramString.getIntValue("errorno");
        paramString = paramString.getJSONObject("data");
        IMMessage localIMMessage = new IMMessage();
        localIMMessage.mMessage = paramString.getString("content");
        localIMMessage.mFromAvatar = paramString.getString("sender_avatar");
        localIMMessage.mGender = paramString.getString("sender_gender");
        localIMMessage.publish = paramString.getIntValue("time_point");
        localIMMessage.mFromName = paramString.getString("sender_name");
        localIMMessage.mFromID = paramString.getString("sender_id");
        localIMMessage.mImage = paramString.getString("image");
        localIMMessage.mLike = paramString.getIntValue("like");
        if (paramString.getIntValue("type") == 1);
        for (localIMMessage.chatType = 3; ; localIMMessage.chatType = 2)
        {
          localIMMessage.mMsgId = paramString.getString("id");
          if (i != 0)
            break;
          return new Result(true, localIMMessage);
        }
      }
      catch (Exception paramString)
      {
      }
    else
      return null;
    return null;
  }

  private Result parseProgramBarrage(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = (JSONObject)JSON.parse(paramString);
        Object localObject1 = paramString.getJSONArray("text");
        paramString = paramString.getJSONArray("image");
        ArrayList localArrayList1 = new ArrayList();
        Object localObject2 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        localArrayList1.add(localObject2);
        localArrayList1.add(localArrayList2);
        if (localObject1 == null)
          break label454;
        i = 0;
        if (i >= ((JSONArray)localObject1).size())
          break label454;
        IMMessage localIMMessage = new IMMessage();
        JSONObject localJSONObject = ((JSONArray)localObject1).getJSONObject(i);
        localIMMessage.mMessage = localJSONObject.getString("content");
        localIMMessage.mFromAvatar = localJSONObject.getString("sender_avatar");
        localIMMessage.mGender = localJSONObject.getString("sender_gender");
        localIMMessage.publish = localJSONObject.getIntValue("time_point");
        localIMMessage.mFromName = localJSONObject.getString("sender_name");
        localIMMessage.mFromID = localJSONObject.getString("sender_id");
        if (localJSONObject.getIntValue("type") == 1)
        {
          localIMMessage.chatType = 3;
          localIMMessage.mMsgId = localJSONObject.getString("id");
          ((List)localObject2).add(localIMMessage);
          i += 1;
          continue;
        }
        localIMMessage.chatType = 2;
        continue;
        if (i < paramString.size())
        {
          localObject1 = new IMMessage();
          localObject2 = paramString.getJSONObject(i);
          ((IMMessage)localObject1).mMessage = ((JSONObject)localObject2).getString("content");
          ((IMMessage)localObject1).mFromAvatar = ((JSONObject)localObject2).getString("sender_avatar");
          ((IMMessage)localObject1).mGender = ((JSONObject)localObject2).getString("sender_gender");
          ((IMMessage)localObject1).publish = ((JSONObject)localObject2).getIntValue("time_point");
          ((IMMessage)localObject1).mFromName = ((JSONObject)localObject2).getString("sender_name");
          ((IMMessage)localObject1).mFromID = ((JSONObject)localObject2).getString("sender_id");
          ((IMMessage)localObject1).mImage = ((JSONObject)localObject2).getString("image");
          ((IMMessage)localObject1).mLike = ((JSONObject)localObject2).getIntValue("like");
          if (((JSONObject)localObject2).getIntValue("type") == 1)
          {
            ((IMMessage)localObject1).chatType = 3;
            ((IMMessage)localObject1).mMsgId = ((JSONObject)localObject2).getString("id");
            localArrayList2.add(localObject1);
            i += 1;
            continue;
          }
          ((IMMessage)localObject1).chatType = 2;
          continue;
        }
        paramString = new Result(true, localArrayList1);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label454: if (paramString != null)
        i = 0;
    }
  }

  private Result parseProgramTopics(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      ArrayList localArrayList = new ArrayList();
      while (true)
      {
        int j;
        try
        {
          paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
          int i = 0;
          if (i >= paramString.size())
            break;
          Object localObject = paramString.getJSONObject(i);
          ProgramTopicNode localProgramTopicNode = new ProgramTopicNode();
          localProgramTopicNode.type = ((JSONObject)localObject).getString("type");
          localProgramTopicNode.startTime = ((JSONObject)localObject).getLong("starttime").longValue();
          localProgramTopicNode.endTime = ((JSONObject)localObject).getLong("endtime").longValue();
          localProgramTopicNode.topic = ((JSONObject)localObject).getString("content");
          if (localProgramTopicNode.type.equalsIgnoreCase("channel"))
          {
            localProgramTopicNode.channelId = String.valueOf(((JSONObject)localObject).getIntValue("channelid"));
            localObject = ((JSONObject)localObject).getJSONArray("sns");
            if (localObject != null)
            {
              j = 0;
              if (j < ((JSONArray)localObject).size())
              {
                JSONObject localJSONObject = ((JSONArray)localObject).getJSONObject(j);
                localProgramTopicNode.platform = localJSONObject.getString("platform").trim();
                localProgramTopicNode.mid = localJSONObject.getString("mid").trim();
                if (!localProgramTopicNode.platform.equalsIgnoreCase("weibo"))
                  break label294;
              }
            }
            localArrayList.add(localProgramTopicNode);
            i += 1;
            continue;
          }
          if (!localProgramTopicNode.type.equalsIgnoreCase("program"))
            continue;
          localProgramTopicNode.channelId = String.valueOf(((JSONObject)localObject).getIntValue("channelid"));
          localProgramTopicNode.programId = String.valueOf(((JSONObject)localObject).getIntValue("programid"));
          continue;
        }
        catch (Exception paramString)
        {
          paramString.printStackTrace();
          return null;
        }
        label294: j += 1;
      }
      return new Result(true, localArrayList);
    }
    return null;
  }

  private Result parsePullCollectionData(String paramString)
  {
    if (paramString != null);
    while (true)
    {
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        if (paramString != null)
        {
          paramString = paramString.getJSONArray("favchannels");
          if (paramString == null)
            break label65;
          paramString = parseMiniFav(paramString);
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
        return null;
      }
      return new Result(true, null);
      label65: paramString = null;
    }
  }

  private Result parseQtimeConfig(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        Object localObject1 = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        paramString = new HashMap();
        HashMap localHashMap1 = new HashMap();
        HashMap localHashMap2 = new HashMap();
        HashMap localHashMap3 = new HashMap();
        HashMap localHashMap4 = new HashMap();
        HashMap localHashMap5 = new HashMap();
        HashMap localHashMap6 = new HashMap();
        HashMap localHashMap7 = new HashMap();
        HashMap localHashMap8 = new HashMap();
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        ArrayList localArrayList3 = new ArrayList();
        int i = 0;
        while (true)
        {
          if (i < ((JSONArray)localObject1).size())
          {
            Object localObject2 = ((JSONArray)localObject1).getJSONObject(i);
            if (((JSONObject)localObject2).getString("type").equals("wsq"))
            {
              paramString.put(Integer.valueOf(Integer.parseInt(((JSONObject)localObject2).getString("cid"))), ((JSONObject)localObject2).getString("wid"));
              localHashMap1.put(Integer.valueOf(Integer.parseInt(((JSONObject)localObject2).getString("cid"))), ((JSONObject)localObject2).getString("entry"));
              localHashMap2.put(Integer.valueOf(Integer.parseInt(((JSONObject)localObject2).getString("cid"))), ((JSONObject)localObject2).getString("entryType"));
            }
            else if (((JSONObject)localObject2).getString("type").equals("live"))
            {
              localArrayList3.add(Integer.valueOf(Integer.parseInt(((JSONObject)localObject2).getString("cid"))));
            }
            else if (((JSONObject)localObject2).getString("type").equals("mall"))
            {
              localHashMap3.put(Integer.valueOf(Integer.parseInt(((JSONObject)localObject2).getString("program_id"))), ((JSONObject)localObject2).getString("mall"));
            }
            else
            {
              Object localObject3;
              if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("channel"))
              {
                localObject3 = new H5Bean();
                ((H5Bean)localObject3).type = 1;
                ((H5Bean)localObject3).id = Integer.parseInt(((JSONObject)localObject2).getString("id"));
                ((H5Bean)localObject3).url = ((JSONObject)localObject2).getString("url");
                localObject2 = ((JSONObject)localObject2).getString("abtestNum");
                if ((localObject2 != null) && (!((String)localObject2).equalsIgnoreCase("")))
                  ((H5Bean)localObject3).abtestNum = Integer.valueOf((String)localObject2).intValue();
                localHashMap4.put(Integer.valueOf(((H5Bean)localObject3).id), localObject3);
                ABTest.getInstance().addH5ABTest((H5Bean)localObject3);
              }
              else if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("category"))
              {
                localObject3 = new H5Bean();
                ((H5Bean)localObject3).type = 2;
                ((H5Bean)localObject3).id = Integer.parseInt(((JSONObject)localObject2).getString("id"));
                ((H5Bean)localObject3).url = ((JSONObject)localObject2).getString("url");
                localObject2 = ((JSONObject)localObject2).getString("abtestNum");
                if ((localObject2 != null) && (!((String)localObject2).equalsIgnoreCase("")))
                  ((H5Bean)localObject3).abtestNum = Integer.valueOf((String)localObject2).intValue();
                localHashMap6.put(Integer.valueOf(((H5Bean)localObject3).id), localObject3);
                ABTest.getInstance().addH5ABTest((H5Bean)localObject3);
              }
              else if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("podcaster"))
              {
                localObject3 = new H5Bean();
                ((H5Bean)localObject3).type = 4;
                ((H5Bean)localObject3).id = Integer.parseInt(((JSONObject)localObject2).getString("id"));
                ((H5Bean)localObject3).url = ((JSONObject)localObject2).getString("url");
                localObject2 = ((JSONObject)localObject2).getString("abtestNum");
                if ((localObject2 != null) && (!((String)localObject2).equalsIgnoreCase("")))
                  ((H5Bean)localObject3).abtestNum = Integer.valueOf((String)localObject2).intValue();
                localHashMap7.put(Integer.valueOf(((H5Bean)localObject3).id), localObject3);
                ABTest.getInstance().addH5ABTest((H5Bean)localObject3);
              }
              else if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("specialtopic"))
              {
                localObject3 = new H5Bean();
                ((H5Bean)localObject3).type = 3;
                ((H5Bean)localObject3).id = Integer.parseInt(((JSONObject)localObject2).getString("id"));
                ((H5Bean)localObject3).url = ((JSONObject)localObject2).getString("url");
                localObject2 = ((JSONObject)localObject2).getString("abtestNum");
                if ((localObject2 != null) && (!((String)localObject2).equalsIgnoreCase("")))
                  ((H5Bean)localObject3).abtestNum = Integer.valueOf((String)localObject2).intValue();
                localHashMap5.put(Integer.valueOf(((H5Bean)localObject3).id), localObject3);
                ABTest.getInstance().addH5ABTest((H5Bean)localObject3);
              }
              else if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("programabtest"))
              {
                localObject3 = new ProgramABTestBean();
                ((ProgramABTestBean)localObject3).type = 5;
                ((ProgramABTestBean)localObject3).uniqueId = Integer.parseInt(((JSONObject)localObject2).getString("id"));
                ((ProgramABTestBean)localObject3).channelId = Integer.parseInt(((JSONObject)localObject2).getString("channelId"));
                ((ProgramABTestBean)localObject3).title = ((JSONObject)localObject2).getString("title");
                ((ProgramABTestBean)localObject3).resId = Integer.parseInt(((JSONObject)localObject2).getString("resId"));
                ((ProgramABTestBean)localObject3).abtestNum = Integer.parseInt(((JSONObject)localObject2).getString("abtestNum"));
                if (((ProgramABTestBean)localObject3).resId != 0)
                {
                  localHashMap8.put(Integer.valueOf(((ProgramABTestBean)localObject3).uniqueId), localObject3);
                  ABTest.getInstance().addProgramABTest((ProgramABTestBean)localObject3);
                }
              }
              else
              {
                if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("game"))
                {
                  localObject3 = new GameBean();
                  ((GameBean)localObject3).title = ((JSONObject)localObject2).getString("title");
                  ((GameBean)localObject3).desc = ((JSONObject)localObject2).getString("desc");
                  ((GameBean)localObject3).people = Integer.parseInt(((JSONObject)localObject2).getString("people"));
                  ((GameBean)localObject3).thumb = ((JSONObject)localObject2).getString("thumb");
                  ((GameBean)localObject3).url = ((JSONObject)localObject2).getString("url");
                  localObject2 = ((JSONObject)localObject2).getString("share");
                  if ((localObject2 != null) && (((String)localObject2).equalsIgnoreCase("1")));
                  for (((GameBean)localObject3).hasShared = true; ; ((GameBean)localObject3).hasShared = false)
                  {
                    localArrayList1.add(localObject3);
                    break;
                  }
                }
                if (((JSONObject)localObject2).getString("type").equalsIgnoreCase("wemart"))
                {
                  localObject3 = new WemartBean();
                  ((WemartBean)localObject3).url = ((JSONObject)localObject2).getString("url");
                  ((WemartBean)localObject3).type = Integer.valueOf(((JSONObject)localObject2).getString("wtype")).intValue();
                  ((WemartBean)localObject3).id = Integer.valueOf(((JSONObject)localObject2).getString("id")).intValue();
                  ((WemartBean)localObject3).desc = ((JSONObject)localObject2).getString("desc");
                  localArrayList2.add(localObject3);
                }
              }
            }
          }
          else
          {
            localObject1 = new HashMap();
            ((Map)localObject1).put("config", paramString);
            ((Map)localObject1).put("entry", localHashMap1);
            ((Map)localObject1).put("entryType", localHashMap2);
            ((Map)localObject1).put("live", localArrayList3);
            ((Map)localObject1).put("mall", localHashMap3);
            ((Map)localObject1).put("channel", localHashMap4);
            ((Map)localObject1).put("category", localHashMap6);
            ((Map)localObject1).put("podcaster", localHashMap7);
            ((Map)localObject1).put("specialtopic", localHashMap5);
            ((Map)localObject1).put("game", localArrayList1);
            ((Map)localObject1).put("wemart", localArrayList2);
            ((Map)localObject1).put("programabtest", localHashMap8);
            paramString = new Result(true, localObject1);
            return paramString;
          }
          i += 1;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseRecommendInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int j;
      int m;
      try
      {
        JSONArray localJSONArray = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localJSONArray != null)
        {
          RecommendCategoryNode localRecommendCategoryNode = new RecommendCategoryNode();
          j = 0;
          if (j < localJSONArray.size())
          {
            Object localObject2 = localJSONArray.getJSONObject(j);
            if (localObject2 == null)
              break label490;
            String str1 = ((JSONObject)localObject2).getString("name");
            String str2 = ((JSONObject)localObject2).getString("brief_name");
            if (str1 == null)
              break label490;
            if (str1.equalsIgnoreCase("banner"))
            {
              paramString = ((JSONObject)localObject2).getJSONArray("recommends");
              if (paramString == null)
                break label490;
              i = 0;
              if (i >= paramString.size())
                break label490;
              localObject1 = _parseRecommendItemNode(paramString.getJSONObject(i), true);
              if (localObject1 == null)
                break label452;
              localRecommendCategoryNode.insertRecCategory((RecommendItemNode)localObject1, 0);
              break label452;
            }
            k = ((JSONObject)localObject2).getIntValue("section_id");
            Object localObject1 = "";
            paramString = ((JSONObject)localObject2).getJSONObject("redirect");
            if (paramString == null)
              break label443;
            Object localObject3 = paramString.getString("redirect_type");
            if ((localObject3 == null) || (!((String)localObject3).equalsIgnoreCase("section")))
              break label436;
            i = paramString.getIntValue("section_id");
            m = paramString.getIntValue("category_id");
            localObject3 = paramString.getJSONArray("attributes");
            paramString = (String)localObject1;
            if (localObject3 == null)
              break label470;
            k = 0;
            paramString = (String)localObject1;
            if (k >= ((JSONArray)localObject3).size())
              break label470;
            paramString = (String)localObject1 + ((JSONArray)localObject3).getIntValue(k);
            localObject1 = paramString;
            if (k >= ((JSONArray)localObject3).size() - 1)
              break label461;
            localObject1 = paramString + "/";
            break label461;
            localObject1 = ((JSONObject)localObject2).getJSONArray("recommends");
            if (localObject1 == null)
              break label490;
            m = 0;
            if (m >= ((JSONArray)localObject1).size())
              break label490;
            localObject2 = _parseRecommendItemNode(((JSONArray)localObject1).getJSONObject(m), false);
            if (localObject2 == null)
              break label481;
            if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
              ((RecommendItemNode)localObject2).mAttributesPath = paramString;
            if (i != 0)
              ((RecommendItemNode)localObject2).mCategoryId = i;
            ((RecommendItemNode)localObject2).sectionId = k;
            ((RecommendItemNode)localObject2).belongName = str1;
            ((RecommendItemNode)localObject2).briefName = str2;
            localRecommendCategoryNode.insertRecCategory((RecommendItemNode)localObject2, 1);
            break label481;
          }
          paramString = new Result(true, localRecommendCategoryNode);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label436: int i = k;
      continue;
      label443: paramString = "";
      i = 0;
      continue;
      label452: i += 1;
      continue;
      label461: k += 1;
      continue;
      label470: int k = i;
      i = m;
      continue;
      label481: m += 1;
      continue;
      label490: j += 1;
    }
  }

  private Result parseRecommendPlayingPrograms(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        ArrayList localArrayList = new ArrayList();
        i = 0;
        if (i < paramString.size())
        {
          RecommendPlayingItemNode localRecommendPlayingItemNode = _parseRecommendPlayingProgram(paramString.getJSONObject(i));
          if (localRecommendPlayingItemNode == null)
            break label88;
          localArrayList.add(localRecommendPlayingItemNode);
          break label88;
        }
        paramString = new Result(true, localArrayList);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
      return null;
      label88: i += 1;
    }
  }

  // ERROR //
  private Result parseRingToneList(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +371 -> 372
    //   4: new 255	java/util/ArrayList
    //   7: dup
    //   8: invokespecial 256	java/util/ArrayList:<init>	()V
    //   11: astore_2
    //   12: aload_1
    //   13: invokestatic 663	com/alibaba/fastjson/JSON:parse	(Ljava/lang/String;)Ljava/lang/Object;
    //   16: checkcast 20	com/alibaba/fastjson/JSONObject
    //   19: ldc_w 426
    //   22: invokevirtual 237	com/alibaba/fastjson/JSONObject:getJSONArray	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONArray;
    //   25: astore_1
    //   26: iconst_0
    //   27: istore 7
    //   29: iload 7
    //   31: aload_1
    //   32: invokevirtual 242	com/alibaba/fastjson/JSONArray:size	()I
    //   35: if_icmpge +327 -> 362
    //   38: aload_1
    //   39: iload 7
    //   41: invokevirtual 245	com/alibaba/fastjson/JSONArray:getJSONObject	(I)Lcom/alibaba/fastjson/JSONObject;
    //   44: astore 4
    //   46: new 1605	fm/qingting/qtradio/model/RingToneNode
    //   49: dup
    //   50: invokespecial 1606	fm/qingting/qtradio/model/RingToneNode:<init>	()V
    //   53: astore_3
    //   54: aload_3
    //   55: aload 4
    //   57: ldc_w 1608
    //   60: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   63: putfield 1611	fm/qingting/qtradio/model/RingToneNode:ringToneId	Ljava/lang/String;
    //   66: aload_3
    //   67: aload 4
    //   69: ldc_w 1569
    //   72: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   75: putfield 1614	fm/qingting/qtradio/model/RingToneNode:ringDesc	Ljava/lang/String;
    //   78: aload_3
    //   79: aload 4
    //   81: ldc_w 496
    //   84: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   87: putfield 1615	fm/qingting/qtradio/model/RingToneNode:duration	I
    //   90: aload_3
    //   91: getfield 1619	fm/qingting/qtradio/model/RingToneNode:downloadInfo	Lfm/qingting/qtradio/model/Download;
    //   94: ifnonnull +14 -> 108
    //   97: aload_3
    //   98: new 1621	fm/qingting/qtradio/model/Download
    //   101: dup
    //   102: invokespecial 1622	fm/qingting/qtradio/model/Download:<init>	()V
    //   105: putfield 1619	fm/qingting/qtradio/model/RingToneNode:downloadInfo	Lfm/qingting/qtradio/model/Download;
    //   108: aload_3
    //   109: getfield 1619	fm/qingting/qtradio/model/RingToneNode:downloadInfo	Lfm/qingting/qtradio/model/Download;
    //   112: aload_3
    //   113: getfield 1615	fm/qingting/qtradio/model/RingToneNode:duration	I
    //   116: bipush 24
    //   118: imul
    //   119: bipush 125
    //   121: imul
    //   122: putfield 1625	fm/qingting/qtradio/model/Download:fileSize	I
    //   125: aload_3
    //   126: aload 4
    //   128: ldc_w 1627
    //   131: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   134: invokestatic 940	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   137: putfield 1629	fm/qingting/qtradio/model/RingToneNode:updatetime	Ljava/lang/String;
    //   140: aload_3
    //   141: ldc_w 1631
    //   144: putfield 1634	fm/qingting/qtradio/model/RingToneNode:ringType	Ljava/lang/String;
    //   147: aload 4
    //   149: ldc 221
    //   151: invokevirtual 170	com/alibaba/fastjson/JSONObject:getJSONObject	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONObject;
    //   154: astore 5
    //   156: aload_3
    //   157: aload 5
    //   159: ldc_w 1636
    //   162: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   165: putfield 1639	fm/qingting/qtradio/model/RingToneNode:mDownLoadPath	Ljava/lang/String;
    //   168: aload_3
    //   169: aload 5
    //   171: ldc_w 1641
    //   174: invokevirtual 170	com/alibaba/fastjson/JSONObject:getJSONObject	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONObject;
    //   177: ldc_w 1643
    //   180: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   183: putfield 1646	fm/qingting/qtradio/model/RingToneNode:mTranscode	Ljava/lang/String;
    //   186: aload_3
    //   187: aload 5
    //   189: ldc_w 1648
    //   192: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   195: putfield 1650	fm/qingting/qtradio/model/RingToneNode:downloadnum	I
    //   198: aload 4
    //   200: ldc_w 1652
    //   203: invokevirtual 170	com/alibaba/fastjson/JSONObject:getJSONObject	(Ljava/lang/String;)Lcom/alibaba/fastjson/JSONObject;
    //   206: astore 4
    //   208: new 333	fm/qingting/qtradio/model/BroadcasterNode
    //   211: dup
    //   212: invokespecial 334	fm/qingting/qtradio/model/BroadcasterNode:<init>	()V
    //   215: astore 5
    //   217: new 333	fm/qingting/qtradio/model/BroadcasterNode
    //   220: dup
    //   221: invokespecial 334	fm/qingting/qtradio/model/BroadcasterNode:<init>	()V
    //   224: astore 6
    //   226: aload 6
    //   228: aload 4
    //   230: ldc 18
    //   232: invokevirtual 24	com/alibaba/fastjson/JSONObject:getIntValue	(Ljava/lang/String;)I
    //   235: putfield 335	fm/qingting/qtradio/model/BroadcasterNode:id	I
    //   238: aload 6
    //   240: aload 4
    //   242: ldc 77
    //   244: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   247: putfield 338	fm/qingting/qtradio/model/BroadcasterNode:nick	Ljava/lang/String;
    //   250: aload 6
    //   252: aload 4
    //   254: ldc_w 340
    //   257: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   260: putfield 342	fm/qingting/qtradio/model/BroadcasterNode:avatar	Ljava/lang/String;
    //   263: aload 6
    //   265: aload 4
    //   267: ldc_w 344
    //   270: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   273: putfield 347	fm/qingting/qtradio/model/BroadcasterNode:weiboId	Ljava/lang/String;
    //   276: aload 6
    //   278: aload 4
    //   280: ldc_w 349
    //   283: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   286: putfield 350	fm/qingting/qtradio/model/BroadcasterNode:weiboName	Ljava/lang/String;
    //   289: aload 6
    //   291: aload 4
    //   293: ldc_w 352
    //   296: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   299: putfield 355	fm/qingting/qtradio/model/BroadcasterNode:qqId	Ljava/lang/String;
    //   302: aload 6
    //   304: aload 4
    //   306: ldc_w 357
    //   309: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   312: putfield 360	fm/qingting/qtradio/model/BroadcasterNode:qqName	Ljava/lang/String;
    //   315: aload_3
    //   316: aload 5
    //   318: getfield 338	fm/qingting/qtradio/model/BroadcasterNode:nick	Ljava/lang/String;
    //   321: putfield 1653	fm/qingting/qtradio/model/RingToneNode:title	Ljava/lang/String;
    //   324: aload_3
    //   325: aload 5
    //   327: invokevirtual 1657	fm/qingting/qtradio/model/RingToneNode:setBroadcaster	(Lfm/qingting/qtradio/model/BroadcasterNode;)V
    //   330: aload_3
    //   331: aload 4
    //   333: ldc_w 1659
    //   336: invokevirtual 33	com/alibaba/fastjson/JSONObject:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   339: invokevirtual 1662	fm/qingting/qtradio/model/RingToneNode:setBelongRadio	(Ljava/lang/String;)V
    //   342: aload_2
    //   343: aload_3
    //   344: invokeinterface 261 2 0
    //   349: pop
    //   350: iload 7
    //   352: iconst_1
    //   353: iadd
    //   354: istore 7
    //   356: goto -327 -> 29
    //   359: astore_1
    //   360: aconst_null
    //   361: areturn
    //   362: new 694	fm/qingting/framework/data/Result
    //   365: dup
    //   366: iconst_1
    //   367: aload_2
    //   368: invokespecial 697	fm/qingting/framework/data/Result:<init>	(ZLjava/lang/Object;)V
    //   371: areturn
    //   372: aconst_null
    //   373: areturn
    //   374: astore_3
    //   375: goto -25 -> 350
    //
    // Exception table:
    //   from	to	target	type
    //   12	26	359	java/lang/Exception
    //   29	108	359	java/lang/Exception
    //   108	198	359	java/lang/Exception
    //   342	350	359	java/lang/Exception
    //   198	342	374	java/lang/Exception
  }

  private Result parseSetPlayHistory(String paramString)
  {
    return new Result(true, null);
  }

  private Result parseSetUserData(String paramString)
  {
    if (paramString != null);
    return new Result(true, null);
  }

  private Result parseShareInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        int i = 0;
        while (i < paramString.size())
        {
          RecommendItemNode localRecommendItemNode = _parseRecommendNode(paramString.getJSONObject(i));
          if (localRecommendItemNode != null)
          {
            paramString = new Result(true, localRecommendItemNode);
            return paramString;
          }
          i += 1;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseThirdADConfig(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    try
    {
      Object localObject = (JSONObject)JSON.parse(paramString);
      if (localObject != null)
      {
        paramString = AppInfo.getChannelName(InfoManager.getInstance().getContext());
        String str1 = ((JSONObject)localObject).getString("JDADChannel");
        if ((str1 != null) && (str1 != null))
        {
          if ((!str1.contains(paramString)) && (!str1.equalsIgnoreCase("all")))
            break label590;
          InfoManager.getInstance().sethasJdAd(true);
        }
        while (true)
        {
          str1 = ((JSONObject)localObject).getString("JDADPosition");
          if (str1 != null)
            InfoManager.getInstance().setJdAdPosition(str1);
          str1 = ((JSONObject)localObject).getString("advJDCity");
          String str2;
          if (str1 != null)
          {
            str2 = InfoManager.getInstance().getCurrentCity();
            if (((str2 != null) && (!str1.contains(str2))) || (str1.equalsIgnoreCase("#")))
              InfoManager.getInstance().enableJDCity(true);
          }
          str1 = ((JSONObject)localObject).getString("advJDShow");
          if ((str1 != null) && (!str1.equalsIgnoreCase("#")) && (!str1.equalsIgnoreCase("")))
            InfoManager.getInstance().setJDShow(Integer.valueOf(str1).intValue());
          str1 = ((JSONObject)localObject).getString("advJDClick2");
          if ((str1 != null) && (!str1.equalsIgnoreCase("#")) && (!str1.equalsIgnoreCase("")))
            InfoManager.getInstance().setJDClick((int)(Float.valueOf(str1).floatValue() * 1000.0F));
          str1 = ((JSONObject)localObject).getString("advJDSeed");
          if ((str1 != null) && (!str1.equalsIgnoreCase("")))
            ThirdTracker.getInstance().setJDSeed(Integer.valueOf(str1).intValue());
          str1 = ((JSONObject)localObject).getString("mediav_enable_channels");
          if ((str1 != null) && (!str1.equalsIgnoreCase("")))
          {
            MediaVAgent.getInstance((Activity)InfoManager.getInstance().getContext());
            MediaVAgent.setEnableChannels(str1);
          }
          str1 = ((JSONObject)localObject).getString("advFromAirWave2");
          if ((str1 != null) && ((str1.contains(paramString)) || (str1.equalsIgnoreCase("all"))))
            InfoManager.getInstance().enableAirWave(true);
          str1 = ((JSONObject)localObject).getString("advFromAirWaveCity");
          if (str1 != null)
          {
            str2 = InfoManager.getInstance().getCurrentCity();
            if (((str2 != null) && (!str1.contains(str2))) || (str1.equalsIgnoreCase("#")))
              InfoManager.getInstance().enableAirWaveCity(true);
          }
          str1 = ((JSONObject)localObject).getString("advFromAirWaveShow");
          if ((str1 != null) && (!str1.equalsIgnoreCase("#")) && (!str1.equalsIgnoreCase("")))
            InfoManager.getInstance().setAirWaveShow(Integer.valueOf(str1).intValue());
          str1 = ((JSONObject)localObject).getString("advFromAirWaveClick");
          if ((str1 != null) && (!str1.equalsIgnoreCase("#")) && (!str1.equalsIgnoreCase("")))
            InfoManager.getInstance().setAirWaveClick((int)(Float.valueOf(str1).floatValue() * 1000.0F));
          str1 = ((JSONObject)localObject).getString("advFromAirWaveCategory");
          if (str1 != null)
            InfoManager.getInstance().setAirwaveCategory(str1);
          str1 = ((JSONObject)localObject).getString("advFromAriWaveBanner");
          if ((str1 != null) && (!str1.equalsIgnoreCase("")))
            InfoManager.getInstance().setAirwaveBanner(Integer.valueOf(str1).intValue());
          localObject = ((JSONObject)localObject).getString("iclickAD");
          if (localObject == null)
            break label615;
          if ((!((String)localObject).contains(paramString)) && (!((String)localObject).equalsIgnoreCase("all")))
            break;
          SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "1");
          break label627;
          label590: InfoManager.getInstance().sethasJdAd(false);
        }
        SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "0");
        break label627;
        label615: SharedCfg.getInstance().saveValue("KEY_ENABLE_ICLICK", "0");
      }
      label627: return null;
    }
    catch (Exception paramString)
    {
      break label627;
    }
  }

  private Result parseUserFollowers(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = new Result(true, (JSONObject)JSON.parse(paramString));
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseUserFollowings(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = new Result(true, (JSONObject)JSON.parse(paramString));
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseUserInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = new Result(true, (JSONObject)JSON.parse(paramString));
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseUserTids(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = (JSONArray)JSON.parse(paramString);
        if (paramString != null)
        {
          ArrayList localArrayList = new ArrayList();
          int i = 0;
          while (i < paramString.size())
          {
            localArrayList.add(paramString.getString(i));
            i += 1;
          }
          paramString = new Result(true, localArrayList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseVirtualProgramInfo(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = _parseVirtualProgramNode(((JSONObject)JSON.parse(paramString)).getJSONObject("data"), 0);
        if (paramString != null)
        {
          paramString = new Result(true, paramString);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  private Result parseVirtualProgramSchedule(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        JSONArray localJSONArray = ((JSONObject)JSON.parse(paramString)).getJSONArray("data");
        if (localJSONArray != null)
        {
          ProgramScheduleList localProgramScheduleList = new ProgramScheduleList(1);
          ProgramSchedule localProgramSchedule = new ProgramSchedule();
          localProgramSchedule.dayOfWeek = 0;
          localProgramSchedule.mLstProgramNodes = new ArrayList();
          i = 0;
          paramString = null;
          if (i < localJSONArray.size())
          {
            ProgramNode localProgramNode = _parseVirtualProgramNode(localJSONArray.getJSONObject(i), 0);
            if (localProgramNode == null)
              break label171;
            if (localProgramNode.sequence == 0)
              localProgramNode.sequence = i;
            if (paramString != null)
            {
              paramString.nextSibling = localProgramNode;
              localProgramNode.prevSibling = paramString;
            }
            localProgramSchedule.mLstProgramNodes.add(localProgramNode);
            paramString = localProgramNode;
            break label171;
          }
          localProgramScheduleList.mLstProgramsScheduleNodes.add(localProgramSchedule);
          paramString = new Result(true, localProgramScheduleList);
          return paramString;
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label171: i += 1;
    }
  }

  private Result parseWsqNew(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
      try
      {
        paramString = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        HashMap localHashMap = new HashMap();
        localHashMap.put(paramString.getString("wid"), paramString.getInteger("new"));
        paramString = new Result(true, localHashMap);
        return paramString;
      }
      catch (Exception paramString)
      {
      }
    return null;
  }

  public Result parse(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("new_search_suggestion"))
      return parseNewSearchSuggestion((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_ACTIVITIES"))
      return parseListActivities((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_WHITE_LIST"))
      return parseADWhiteList((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_THIRD_AD_CONFIG"))
      return parseThirdADConfig((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_PODCASTER_BASEINFO"))
      return parsePodcasterBaseInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_PODCASTER_DETAILINFO"))
      return parsePodcasterDetailInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_LOCATION"))
      return parseADLocation((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_PODCASTER_LATESTINFO"))
      return parsePodcasterLatestInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_PODCASTER_CHANNELS"))
      return parsePodcasterChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("POST_PROGRAM_BARRAGE"))
      return parsePostBarrage((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_PROGRAM_BARRAGE"))
      return parseProgramBarrage((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_QTIME_CONFIG"))
      return parseQtimeConfig((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_WSQ_NEW"))
      return parseWsqNew((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LOCAL_CATEGORY"))
      return parseListCategories((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LOCAL_RECOMMEND_INFO"))
      return parseLocalRecommendInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_CONFIG"))
      return parseADConfig((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AM_AD_CONFIG"))
      return parseAMAdConfig((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_ADV_FROM_AIRWAVE"))
      return parseADVFromAirWave((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_CATEGORIES"))
      return parseListCategories((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_CATEGORY_ATTRS"))
      return parseCategoryAttr((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_ALL_CHANNELS"))
      return parseAllChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_CHANNELS"))
      return parseAllChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_LIVE_CHANNELS"))
      return parseAllChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_VIRTUAL_CHANNEL_INFO"))
      return parseChannelNode((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIVE_CHANNEL_INFO"))
      return parseChannelNode((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIVE_PROGRAM_SCHEDULE"))
      return parseLiveProgramSchedule((String)paramObject2);
    if ((paramString.equalsIgnoreCase("GET_VIRTUAL_PROGRAM_SCHEDULE")) || (paramString.equalsIgnoreCase("RELOAD_VIRTUAL_PROGRAMS_SCHEDULE")))
      return parseVirtualProgramSchedule((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_VIRTUAL_PROGRAM_INFO"))
      return parseVirtualProgramInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_MEDIACENTER"))
      return parseMediaCenter((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_POS"))
      return parseAdsPos((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_INFO"))
      return parseAdvertisementInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_AD_INFO_BYCHANNEL"))
      return parseAdvertisementInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_RECOMMEND_PLAYING"))
      return parseRecommendPlayingPrograms((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_SPECIAL_TOPIC_CHANNELS"))
      return parseSpecialTopicChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_RECOMMEND_INFO"))
      return parseRecommendInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_BILLBOARD_CHANNELS"))
      return parseBillboardChannels((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_BILLBOARD_PROGRAMS"))
      return parseBillboardProgram((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_current_playing_programs"))
      return parseCurrentPlayingPrograms((String)paramObject2);
    if (paramString.equalsIgnoreCase("new_search"))
      return parseNewSearch((String)paramObject2);
    if (paramString.equalsIgnoreCase("new_search_loadmore"))
      return parseNewSearchLoadMore((String)paramObject2);
    if (paramString.equalsIgnoreCase("new_recommendv2_banner"))
      return parseNewRecommendV2Banner((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_share_info"))
      return parseShareInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("new_recommendv2_fp_banner"))
      return parseNewRecommendV2Banner((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_user_info"))
      return parseUserInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_IM_HISTORY_FROM_SERVER"))
      return parseIMHistory((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_user_followers"))
      return parseUserFollowers((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_user_followings"))
      return parseUserFollowings((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_group_info"))
      return parseGroupInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_IM_SERVER"))
      return parseIMServer((String)paramObject2);
    if (paramString.equalsIgnoreCase("GET_LIST_LIVE_CATEGORIES"))
      return parseLiveCategory((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_group_users"))
      return parseGroupUsers((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_link_info"))
      return parseLinkInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_im_base_user_info"))
      return parseIMBaseUserInfo((String)paramObject2);
    if (paramString.equalsIgnoreCase("create_user"))
      return parseCreateUser((String)paramObject2);
    if (paramString.equalsIgnoreCase("set_user_data"))
      return parseSetUserData((String)paramObject2);
    if (paramString.equalsIgnoreCase("pull_collection_data"))
      return parsePullCollectionData((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_social_user_data"))
      return parseGetUserData((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_ip_location"))
      return parseIPLocation((String)paramObject2);
    if (paramString.equalsIgnoreCase("current_program_topics"))
      return parseProgramTopics((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_ringtone_list"))
      return parseRingToneList((String)paramObject2);
    if (paramString.equalsIgnoreCase("report_user"))
      return new Result(true, "");
    if ((paramString.equalsIgnoreCase("add_following")) || (paramString.equalsIgnoreCase("cancel_following")))
      return new Result(true, null);
    if (paramString.equalsIgnoreCase("get_user_tids"))
      return parseUserTids((String)paramObject2);
    if (paramString.equalsIgnoreCase("hot_search_keywords"))
      return parseHotSearchKeywords((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_jd_ad"))
      return parseJdAd((String)paramObject2);
    if ((paramString.equalsIgnoreCase("GET_WECHAT_TOKEN")) || (paramString.equalsIgnoreCase("GET_WECHAT_USER_INFO")) || (paramString.equalsIgnoreCase("REFRESH_WECHAT_TOKEN")))
      return new Result(true, paramObject2);
    if (paramString.equalsIgnoreCase("get_podcaster_followings"))
      return parseGetMyPodcasterList((String)paramObject2);
    if (paramString.equalsIgnoreCase("get_play_history"))
      return parseGetPlayHistory((String)paramObject2);
    if (paramString.equalsIgnoreCase("set_play_history"))
      return parseSetPlayHistory((String)paramObject2);
    return super.parse(paramString, paramObject1, paramObject2);
  }

  public Result parseSpecialTopicChannels(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")));
    while (true)
    {
      int i;
      try
      {
        Object localObject = ((JSONObject)JSON.parse(paramString)).getJSONObject("data");
        if (localObject != null)
        {
          paramString = _parseSpecialTopicNode((JSONObject)localObject);
          localObject = ((JSONObject)localObject).getJSONArray("channels");
          if (localObject != null)
          {
            ArrayList localArrayList = new ArrayList();
            i = 0;
            if (i < ((JSONArray)localObject).size())
            {
              ChannelNode localChannelNode = _parseChannelNode(((JSONArray)localObject).getJSONObject(i));
              if (localChannelNode == null)
                break label136;
              localArrayList.add(localChannelNode);
              break label136;
            }
            if (paramString != null)
            {
              paramString.setChannels(localArrayList);
              return new Result(true, paramString);
            }
          }
          if (paramString != null)
          {
            paramString = new Result(true, paramString);
            return paramString;
          }
        }
      }
      catch (Exception paramString)
      {
      }
      return null;
      label136: i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.parser.NetParser
 * JD-Core Version:    0.6.2
 */