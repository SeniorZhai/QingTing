package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchHotKeyword;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.utils.QTMSGManage;
import java.util.List;

public class SearchHistoryView extends LinearLayout
{
  private LabelContainer mHotContainer;
  private LayoutInflater mLayoutInflater;
  private SearchView mListener;
  private LabelContainer mRecentsContainer;

  public SearchHistoryView(Context paramContext, SearchView paramSearchView)
  {
    super(paramContext);
    this.mListener = paramSearchView;
    this.mLayoutInflater = LayoutInflater.from(paramContext);
    this.mLayoutInflater.inflate(2130903063, this);
    paramContext = findViewById(2131230870);
    ((TextView)paramContext.findViewById(2131230875)).setText("最近搜索");
    paramContext = (TextView)paramContext.findViewById(2131230876);
    paramContext.setText("清除记录");
    paramContext.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        InfoManager.getInstance().root().mSearchNode.clearKeywords();
        SearchHistoryView.this.setRecents();
      }
    });
    this.mRecentsContainer = ((LabelContainer)findViewById(2131230871));
    setRecents();
    ((TextView)findViewById(2131230872).findViewById(2131230875)).setText("热门搜索");
    this.mHotContainer = ((LabelContainer)findViewById(2131230873));
    setHot();
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((paramMotionEvent.getAction() == 0) && (this.mListener != null))
      this.mListener.closeKeyBoard();
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected void setHot()
  {
    List localList = InfoManager.getInstance().root().mSearchNode.getHotKeywords();
    this.mHotContainer.removeAllViews();
    if ((localList != null) && (localList.size() > 0))
    {
      int i = 0;
      while (i < localList.size())
      {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.mLayoutInflater.inflate(2130903064, null);
        Button localButton = (Button)localRelativeLayout.findViewById(2131230874);
        localButton.setText(((SearchHotKeyword)localList.get(i)).keyword);
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            paramAnonymousView = (String)((Button)paramAnonymousView).getText();
            SearchHistoryView.this.mListener.submitQuery(paramAnonymousView);
            QTMSGManage.getInstance().sendStatistcsMessage("search_clickhotkeyword");
          }
        });
        this.mHotContainer.addView(localRelativeLayout);
        i += 1;
      }
      findViewById(2131230872).setVisibility(0);
      this.mHotContainer.setVisibility(0);
      return;
    }
    InfoManager.getInstance().loadHotSearchKeywords(this.mListener);
    findViewById(2131230872).setVisibility(8);
    this.mHotContainer.setVisibility(8);
  }

  protected void setRecents()
  {
    List localList = InfoManager.getInstance().root().mSearchNode.getRecentKeywords();
    this.mRecentsContainer.removeAllViews();
    if ((localList != null) && (localList.size() > 0))
    {
      int i = 0;
      while (i < localList.size())
      {
        RelativeLayout localRelativeLayout = (RelativeLayout)this.mLayoutInflater.inflate(2130903064, null);
        Button localButton = (Button)localRelativeLayout.findViewById(2131230874);
        localButton.setText((String)localList.get(i));
        localButton.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            QTMSGManage.getInstance().sendStatistcsMessage("search_recent");
            paramAnonymousView = (String)((Button)paramAnonymousView).getText();
            SearchHistoryView.this.mListener.submitQuery(paramAnonymousView);
          }
        });
        this.mRecentsContainer.addView(localRelativeLayout);
        i += 1;
      }
      findViewById(2131230870).setVisibility(0);
      this.mRecentsContainer.setVisibility(0);
      return;
    }
    findViewById(2131230870).setVisibility(8);
    this.mRecentsContainer.setVisibility(8);
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchHistoryView
 * JD-Core Version:    0.6.2
 */