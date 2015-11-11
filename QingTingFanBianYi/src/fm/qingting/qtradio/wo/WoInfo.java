package fm.qingting.qtradio.wo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WoInfo
{
  public static final String apiServer = "http://qingtingfm.api.10155.com";
  public static final String appKey = "3000004485";
  public static final String appSecret = "C259AC501E15A6056DBF0B1EB3092A7D";
  public static final String httpProxy = "http://3000004485:C259AC501E15A6056DBF0B1EB3092A7D@qingtingfm.gzproxy.10155.com:8080";
  public static final String productId = "0000001010";
  public static String proxyPasswd = "3000004485:C259AC501E15A6056DBF0B1EB3092A7D";
  public static final int proxyPortNumber = Integer.parseInt("8080");
  public static final String proxyPortString = "8080";
  public static final String proxyServer = "qingtingfm.gzproxy.10155.com";
  public static final String wapHttpProxy = "http://3000004485:C259AC501E15A6056DBF0B1EB3092A7D@10.123.254.46:8080";
  public static final int wapProxyPortNumber = 8080;
  public static final String wapProxyPortString = "8080";
  public static final String wapProxyServer = "10.123.254.46";

  public static String genFirstTryMsg()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "您于 " + localSimpleDateFormat.format(new Date()) + " 成功开通蜻蜓FM-包流量畅听，马上去蜻蜓FM收听和下载吧，流量全免(不消耗您原套餐的流量)。资费10元/月，72小时内退订不扣费。客户端退订方式:菜单中设置（或者设置）->蜻蜓FM-包流量畅听->免费退订。可详询蜻蜓FM客服：021-64453178。";
  }

  public static String genOldUserSubMsg()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    return "您于 " + localSimpleDateFormat.format(new Date()) + " 成功开通蜻蜓FM-包流量畅听，马上去蜻蜓FM收听和下载吧，流量全免(不消耗您原套餐的流量)。资费10元/月。客户端退订方式:菜单中更多（或者设置）->蜻蜓FM-包流量畅听->立刻退订。可详询蜻蜓FM客服：021-64453178。";
  }

  public static String getDefaultRuleText()
  {
    return "1. 资费：10元/月，开通期间使用蜻蜓FM手机客户端收听、下载节目，当月内流量全包。\n2. 业务开通后立即生效，每月月初自动续费。 包月业务退订后，当月可继续使用至月底，下月不再扣费。\n3. 联通沃3G预付费20元卡不支持开通此业务。\n4. 联通2G/3G/4G用户上网流量上限为6G，当月流量超出上限后，联通将自动关闭上网功能，下月自动开通。\n5. 请保证使用开通号码联网，谨慎安装流量监控软件，使用业务时请尽量关闭WIFI，避免因WIFI和2G/3G网络切换频繁导致流量包失效。\n6. 分享、聊天等社交功能赞不支持免流量（消耗少量流量）。 \n7. 如有疑问，请咨询客服：021-64453178\n";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.wo.WoInfo
 * JD-Core Version:    0.6.2
 */