package com.umeng.fb;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.umeng.fb.model.Conversation;
import com.umeng.fb.model.Conversation.SyncListener;
import com.umeng.fb.model.DevReply;
import com.umeng.fb.model.Reply;
import com.umeng.fb.res.AnimMapper;
import com.umeng.fb.res.DrawableMapper;
import com.umeng.fb.res.IdMapper;
import com.umeng.fb.res.LayoutMapper;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class ConversationActivity extends Activity
{
  private static final String TAG = ConversationActivity.class.getName();
  private ReplyListAdapter adapter;
  private FeedbackAgent agent;
  private Conversation defaultConversation;
  RelativeLayout header;
  int headerHeight;
  int headerPaddingOriginal;
  private int mLastMotionY;
  private ListView replyListView;
  EditText userReplyContentEdit;

  private void measureView(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams2 = paramView.getLayoutParams();
    ViewGroup.LayoutParams localLayoutParams1 = localLayoutParams2;
    if (localLayoutParams2 == null)
      localLayoutParams1 = new ViewGroup.LayoutParams(-1, -2);
    int j = ViewGroup.getChildMeasureSpec(0, 0, localLayoutParams1.width);
    int i = localLayoutParams1.height;
    if (i > 0);
    for (i = View.MeasureSpec.makeMeasureSpec(i, 1073741824); ; i = View.MeasureSpec.makeMeasureSpec(0, 0))
    {
      paramView.measure(j, i);
      return;
    }
  }

  private void setListViewHeader()
  {
    this.header = ((RelativeLayout)((LayoutInflater)getSystemService("layout_inflater")).inflate(LayoutMapper.umeng_fb_list_header(this), this.replyListView, false));
    this.replyListView.addHeaderView(this.header);
    measureView(this.header);
    this.headerHeight = this.header.getMeasuredHeight();
    this.headerPaddingOriginal = this.header.getPaddingTop();
    this.header.setPadding(this.header.getPaddingLeft(), -this.headerHeight, this.header.getPaddingRight(), this.header.getPaddingBottom());
    this.header.setVisibility(8);
    this.replyListView.setOnTouchListener(new View.OnTouchListener()
    {
      private void applyHeaderPadding(MotionEvent paramAnonymousMotionEvent)
      {
        int j = paramAnonymousMotionEvent.getHistorySize();
        int i = 0;
        while (i < j)
        {
          if (ConversationActivity.this.replyListView.getFirstVisiblePosition() == 0)
          {
            int k = (int)(((int)paramAnonymousMotionEvent.getHistoricalY(i) - ConversationActivity.this.mLastMotionY - ConversationActivity.this.headerHeight) / 1.7D);
            ConversationActivity.this.header.setVisibility(0);
            ConversationActivity.this.header.setPadding(ConversationActivity.this.header.getPaddingLeft(), k, ConversationActivity.this.header.getPaddingRight(), ConversationActivity.this.header.getPaddingBottom());
          }
          i += 1;
        }
      }

      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        if (ConversationActivity.this.replyListView.getAdapter().getCount() < 2);
        do
        {
          return false;
          switch (paramAnonymousMotionEvent.getAction())
          {
          default:
            return false;
          case 0:
            ConversationActivity.access$202(ConversationActivity.this, (int)paramAnonymousMotionEvent.getY());
            return false;
          case 1:
          case 2:
          }
        }
        while (ConversationActivity.this.replyListView.getFirstVisiblePosition() != 0);
        if ((ConversationActivity.this.header.getBottom() >= ConversationActivity.this.headerHeight + 20) || (ConversationActivity.this.header.getTop() > 0))
        {
          ConversationActivity.this.header.setVisibility(0);
          ConversationActivity.this.header.setPadding(ConversationActivity.this.header.getPaddingLeft(), ConversationActivity.this.headerPaddingOriginal, ConversationActivity.this.header.getPaddingRight(), ConversationActivity.this.header.getPaddingBottom());
          return false;
        }
        ConversationActivity.this.replyListView.setSelection(1);
        ConversationActivity.this.header.setVisibility(8);
        ConversationActivity.this.header.setPadding(ConversationActivity.this.header.getPaddingLeft(), -ConversationActivity.this.headerHeight, ConversationActivity.this.header.getPaddingRight(), ConversationActivity.this.header.getPaddingBottom());
        return false;
        applyHeaderPadding(paramAnonymousMotionEvent);
        return false;
      }
    });
    this.replyListView.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      private int mScrollState;

      public void onScroll(AbsListView paramAnonymousAbsListView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if ((this.mScrollState == 2) && (paramAnonymousInt1 == 0));
      }

      public void onScrollStateChanged(AbsListView paramAnonymousAbsListView, int paramAnonymousInt)
      {
        this.mScrollState = paramAnonymousInt;
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(LayoutMapper.umeng_fb_activity_conversation(this));
    try
    {
      this.agent = new FeedbackAgent(this);
      this.defaultConversation = this.agent.getDefaultConversation();
      this.replyListView = ((ListView)findViewById(IdMapper.umeng_fb_reply_list(this)));
      setListViewHeader();
      this.adapter = new ReplyListAdapter(this);
      this.replyListView.setAdapter(this.adapter);
      sync();
      paramBundle = findViewById(IdMapper.umeng_fb_conversation_contact_entry(this));
      paramBundle.setOnClickListener(new View.OnClickListener()
      {
        @SuppressLint({"NewApi"})
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = new Intent();
          paramAnonymousView.setClass(ConversationActivity.this, ContactActivity.class);
          ConversationActivity.this.startActivity(paramAnonymousView);
          if (Build.VERSION.SDK_INT > 4)
            new Object()
            {
              public void overridePendingTransition(Activity paramAnonymous2Activity)
              {
                paramAnonymous2Activity.overridePendingTransition(AnimMapper.umeng_fb_slide_in_from_right(ConversationActivity.this), AnimMapper.umeng_fb_slide_out_from_left(ConversationActivity.this));
              }
            }
            .overridePendingTransition(ConversationActivity.this);
        }
      });
      if (this.agent.getUserInfoLastUpdateAt() > 0L)
        paramBundle.setVisibility(8);
      findViewById(IdMapper.umeng_fb_back(this)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ConversationActivity.this.finish();
        }
      });
      this.userReplyContentEdit = ((EditText)findViewById(IdMapper.umeng_fb_reply_content(this)));
      findViewById(IdMapper.umeng_fb_send(this)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          paramAnonymousView = ConversationActivity.this.userReplyContentEdit.getEditableText().toString().trim();
          if (TextUtils.isEmpty(paramAnonymousView));
          do
          {
            return;
            ConversationActivity.this.userReplyContentEdit.getEditableText().clear();
            ConversationActivity.this.defaultConversation.addUserReply(paramAnonymousView);
            ConversationActivity.this.sync();
            paramAnonymousView = (InputMethodManager)ConversationActivity.this.getSystemService("input_method");
          }
          while (paramAnonymousView == null);
          paramAnonymousView.hideSoftInputFromWindow(ConversationActivity.this.userReplyContentEdit.getWindowToken(), 0);
        }
      });
      return;
    }
    catch (Exception paramBundle)
    {
      paramBundle.printStackTrace();
      finish();
    }
  }

  void sync()
  {
    Conversation.SyncListener local6 = new Conversation.SyncListener()
    {
      public void onReceiveDevReply(List<DevReply> paramAnonymousList)
      {
      }

      public void onSendUserReply(List<Reply> paramAnonymousList)
      {
        ConversationActivity.this.adapter.notifyDataSetChanged();
      }
    };
    this.defaultConversation.sync(local6);
  }

  class ReplyListAdapter extends BaseAdapter
  {
    Context mContext;
    LayoutInflater mInflater;

    public ReplyListAdapter(Context arg2)
    {
      Object localObject;
      this.mContext = localObject;
      this.mInflater = LayoutInflater.from(this.mContext);
    }

    public int getCount()
    {
      List localList = ConversationActivity.this.defaultConversation.getReplyList();
      if (localList == null)
        return 0;
      return localList.size();
    }

    public Object getItem(int paramInt)
    {
      return ConversationActivity.this.defaultConversation.getReplyList().get(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      Reply localReply;
      RelativeLayout.LayoutParams localLayoutParams;
      if (paramView == null)
      {
        paramView = this.mInflater.inflate(LayoutMapper.umeng_fb_list_item(this.mContext), null);
        paramViewGroup = new ViewHolder();
        paramViewGroup.replyDate = ((TextView)paramView.findViewById(IdMapper.umeng_fb_reply_date(this.mContext)));
        paramViewGroup.replyContent = ((TextView)paramView.findViewById(IdMapper.umeng_fb_reply_content(this.mContext)));
        paramView.setTag(paramViewGroup);
        localReply = (Reply)ConversationActivity.this.defaultConversation.getReplyList().get(paramInt);
        localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
        if (!(localReply instanceof DevReply))
          break label185;
        localLayoutParams.addRule(9);
        paramViewGroup.replyContent.setLayoutParams(localLayoutParams);
        paramViewGroup.replyContent.setBackgroundResource(DrawableMapper.umeng_fb_reply_left_bg(this.mContext));
      }
      while (true)
      {
        paramViewGroup.replyDate.setText(SimpleDateFormat.getDateTimeInstance().format(localReply.getDatetime()));
        paramViewGroup.replyContent.setText(localReply.getContent());
        return paramView;
        paramViewGroup = (ViewHolder)paramView.getTag();
        break;
        label185: localLayoutParams.addRule(11);
        paramViewGroup.replyContent.setLayoutParams(localLayoutParams);
        paramViewGroup.replyContent.setBackgroundResource(DrawableMapper.umeng_fb_reply_right_bg(this.mContext));
      }
    }

    class ViewHolder
    {
      TextView replyContent;
      TextView replyDate;

      ViewHolder()
      {
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     com.umeng.fb.ConversationActivity
 * JD-Core Version:    0.6.2
 */