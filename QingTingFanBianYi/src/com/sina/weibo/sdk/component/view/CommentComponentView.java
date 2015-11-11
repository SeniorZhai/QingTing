package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.component.WeiboSdkBrowser;
import com.sina.weibo.sdk.component.WidgetRequestParam;
import com.sina.weibo.sdk.utils.ResourceManager;

public class CommentComponentView extends FrameLayout
{
  private static final String ALREADY_COMMENT_EN = "Comment";
  private static final String ALREADY_COMMENT_ZH_CN = "微博热评";
  private static final String ALREADY_COMMENT_ZH_TW = "微博熱評";
  private static final String COMMENT_H5 = "http://widget.weibo.com/distribution/socail_comments_sdk.php";
  private RequestParam mCommentParam;
  private LinearLayout mContentLy;

  public CommentComponentView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public CommentComponentView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public CommentComponentView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void execAttented()
  {
    Object localObject = new WidgetRequestParam(getContext());
    ((WidgetRequestParam)localObject).setUrl("http://widget.weibo.com/distribution/socail_comments_sdk.php");
    ((WidgetRequestParam)localObject).setSpecifyTitle(ResourceManager.getString(getContext(), "Comment", "微博热评", "微博熱評"));
    ((WidgetRequestParam)localObject).setAppKey(this.mCommentParam.mAppKey);
    ((WidgetRequestParam)localObject).setCommentTopic(this.mCommentParam.mTopic);
    ((WidgetRequestParam)localObject).setCommentContent(this.mCommentParam.mContent);
    ((WidgetRequestParam)localObject).setCommentCategory(this.mCommentParam.mCategory.getValue());
    ((WidgetRequestParam)localObject).setAuthListener(this.mCommentParam.mAuthlistener);
    ((WidgetRequestParam)localObject).setToken(this.mCommentParam.mAccessToken);
    localObject = ((WidgetRequestParam)localObject).createRequestParamBundle();
    Intent localIntent = new Intent(getContext(), WeiboSdkBrowser.class);
    localIntent.putExtras((Bundle)localObject);
    getContext().startActivity(localIntent);
  }

  private void init(Context paramContext)
  {
    this.mContentLy = new LinearLayout(paramContext);
    this.mContentLy.setOrientation(0);
    this.mContentLy.setLayoutParams(new ViewGroup.LayoutParams(-2, -2));
    ImageView localImageView = new ImageView(paramContext);
    localImageView.setImageDrawable(ResourceManager.getDrawable(paramContext, "sdk_weibo_logo.png"));
    Object localObject = new LinearLayout.LayoutParams(ResourceManager.dp2px(getContext(), 20), ResourceManager.dp2px(getContext(), 20));
    ((LinearLayout.LayoutParams)localObject).gravity = 16;
    localImageView.setLayoutParams((ViewGroup.LayoutParams)localObject);
    localObject = new TextView(paramContext);
    ((TextView)localObject).setText(ResourceManager.getString(paramContext, "Comment", "微博热评", "微博熱評"));
    ((TextView)localObject).setTextColor(-32256);
    ((TextView)localObject).setTextSize(2, 15.0F);
    ((TextView)localObject).setIncludeFontPadding(false);
    paramContext = new LinearLayout.LayoutParams(-2, -2);
    paramContext.gravity = 16;
    paramContext.leftMargin = ResourceManager.dp2px(getContext(), 4);
    ((TextView)localObject).setLayoutParams(paramContext);
    this.mContentLy.addView(localImageView);
    this.mContentLy.addView((View)localObject);
    addView(this.mContentLy);
    ((TextView)localObject).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CommentComponentView.this.execAttented();
      }
    });
  }

  public void setCommentParam(RequestParam paramRequestParam)
  {
    this.mCommentParam = paramRequestParam;
  }

  public static enum Category
  {
    MOVIE("1001"), TRAVEL("1002");

    private String mVal;

    private Category(String arg3)
    {
      Object localObject;
      this.mVal = localObject;
    }

    public String getValue()
    {
      return this.mVal;
    }
  }

  public static class RequestParam
  {
    private String mAccessToken;
    private String mAppKey;
    private WeiboAuthListener mAuthlistener;
    private CommentComponentView.Category mCategory;
    private String mContent;
    private String mTopic;

    public static RequestParam createRequestParam(String paramString1, String paramString2, String paramString3, CommentComponentView.Category paramCategory, WeiboAuthListener paramWeiboAuthListener)
    {
      RequestParam localRequestParam = new RequestParam();
      localRequestParam.mAppKey = paramString1;
      localRequestParam.mTopic = paramString2;
      localRequestParam.mContent = paramString3;
      localRequestParam.mCategory = paramCategory;
      localRequestParam.mAuthlistener = paramWeiboAuthListener;
      return localRequestParam;
    }

    public static RequestParam createRequestParam(String paramString1, String paramString2, String paramString3, String paramString4, CommentComponentView.Category paramCategory, WeiboAuthListener paramWeiboAuthListener)
    {
      RequestParam localRequestParam = new RequestParam();
      localRequestParam.mAppKey = paramString1;
      localRequestParam.mAccessToken = paramString2;
      localRequestParam.mTopic = paramString3;
      localRequestParam.mContent = paramString4;
      localRequestParam.mCategory = paramCategory;
      localRequestParam.mAuthlistener = paramWeiboAuthListener;
      return localRequestParam;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.sina.weibo.sdk.component.view.CommentComponentView
 * JD-Core Version:    0.6.2
 */