package com.sina.weibo.sdk.constant;

public class WBPageConstants
{
  public static final class ExceptionMsg
  {
    public static final String CARDID_ERROR = "cardId不能为空";
    public static final String CONTEXT_ERROR = "context不能为空";
    public static final String COUNT_ERROR = "count不能为负数";
    public static final String MBLOGID_ERROR = "mblogId(微博id)不能为空";
    public static final String PAGEID_ERROR = "pageId不能为空";
    public static final String SINAINTERNALBROWSER = "sinainternalbrowser不合法";
    public static final String UID_NICK_ERROR = "uid和nick必须至少有一个不为空";
    public static final String URL_ERROR = "url不能为空";
    public static final String WEIBO_NOT_INSTALLED = "无法找到微博官方客户端";
  }

  public static final class ParamKey
  {
    public static final String CARDID = "cardid";
    public static final String CONTENT = "content";
    public static final String COUNT = "count";
    public static final String EXTPARAM = "extparam";
    public static final String LATITUDE = "latitude";
    public static final String LONGITUDE = "longitude";
    public static final String MBLOGID = "mblogid";
    public static final String NICK = "nick";
    public static final String OFFSET = "offset";
    public static final String PACKAGENAME = "packagename";
    public static final String PAGE = "page";
    public static final String PAGEID = "pageid";
    public static final String POIID = "poiid";
    public static final String POINAME = "poiname";
    public static final String SINAINTERNALBROWSER = "sinainternalbrowser";
    public static final String TITLE = "title";
    public static final String UID = "uid";
    public static final String URL = "url";
  }

  public static final class Scheme
  {
    public static final String BROWSER = "sinaweibo://browser";
    public static final String MAP = "sinaweibo://map";
    public static final String MBLOGDETAIL = "sinaweibo://detail";
    public static final String NEARBYPEOPLE = "sinaweibo://nearbypeople";
    public static final String NEARBYWEIBO = "sinaweibo://nearbyweibo";
    public static final String PAGEDETAILINFO = "sinaweibo://pagedetailinfo";
    public static final String PAGEINFO = "sinaweibo://pageinfo";
    public static final String PAGEPHOTOLIST = "sinaweibo://pagephotolist";
    public static final String PAGEPRODUCTLIST = "sinaweibo://pageproductlist";
    public static final String PAGEUSERLIST = "sinaweibo://pageuserlist";
    public static final String PAGEWEIBOLIST = "sinaweibo://pageweibolist";
    public static final String QRCODE = "sinaweibo://qrcode";
    public static final String SENDWEIBO = "sinaweibo://sendweibo";
    public static final String USERINFO = "sinaweibo://userinfo";
    public static final String USERTRENDS = "sinaweibo://usertrends";
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.constant.WBPageConstants
 * JD-Core Version:    0.6.2
 */