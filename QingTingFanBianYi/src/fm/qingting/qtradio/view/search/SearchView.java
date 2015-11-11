package fm.qingting.qtradio.view.search;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View.MeasureSpec;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import fm.qingting.framework.event.IEventHandler;
import fm.qingting.framework.manager.EventDispacthManager;
import fm.qingting.framework.view.IView;
import fm.qingting.framework.view.ViewGroupViewImpl;
import fm.qingting.framework.view.ViewLayout;
import fm.qingting.qtradio.controller.ControllerManager;
import fm.qingting.qtradio.model.InfoManager;
import fm.qingting.qtradio.model.InfoManager.DataExceptionStatus;
import fm.qingting.qtradio.model.InfoManager.ISubscribeEventListener;
import fm.qingting.qtradio.model.RootNode;
import fm.qingting.qtradio.search.SearchNode;
import fm.qingting.qtradio.view.QtViewPager;
import fm.qingting.utils.InputMethodUtil;
import fm.qingting.utils.InputMethodUtil.InputStateDelegate;
import fm.qingting.utils.QTMSGManage;

public class SearchView extends ViewGroupViewImpl
  implements IEventHandler, InfoManager.ISubscribeEventListener
{
  private static final int QUERY = -1;
  private static final int RECENT = 0;
  private static final int RESULT = 2;
  private static final int SUGGESTION = 1;
  private InputMethodUtil.InputStateDelegate mDelegate;
  private EditText mEditText;
  private SearchNaviView mHeadBg;
  private boolean mInputMethodShowing = false;
  private SearchHistoryView mRecentSearchView;
  private int mSmallestHeight = 2147483647;
  private SearchSuggestionView mSuggestionView;
  private MyViewPager mViewPager;
  private int mViewType = 0;
  private final ViewLayout standardLayout = ViewLayout.createViewLayoutWithBoundsLT(720, 1200, 720, 1200, 0, 0, ViewLayout.FILL);

  public SearchView(Context paramContext)
  {
    super(paramContext);
    setBackgroundColor(-723465);
    this.mHeadBg = new SearchNaviView(paramContext);
    this.mHeadBg.setEventHandler(this);
    this.mEditText = this.mHeadBg.getEditText();
    this.mEditText.setOnEditorActionListener(new TextView.OnEditorActionListener()
    {
      public boolean onEditorAction(TextView paramAnonymousTextView, int paramAnonymousInt, KeyEvent paramAnonymousKeyEvent)
      {
        if (paramAnonymousInt == 3)
        {
          if (SearchView.this.mEditText.getText() != null)
          {
            SearchView.this.search(SearchView.this.mEditText.getText().toString());
            SearchView.this.closeKeyBoard();
          }
          return true;
        }
        return false;
      }
    });
    this.mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
      }

      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (paramAnonymousCharSequence.length() == 0)
        {
          SearchView.access$202(SearchView.this, 0);
          SearchView.this.mSuggestionView.setVisibility(8);
          SearchView.this.mViewPager.setVisibility(8);
          SearchView.this.mRecentSearchView.setVisibility(0);
          SearchView.this.mHeadBg.setType(2);
          return;
        }
        SearchView.this.mHeadBg.setType(1);
        if (SearchView.this.mViewType == -1)
        {
          SearchView.this.search(paramAnonymousCharSequence.toString());
          return;
        }
        SearchView.this.loadSuggestions(paramAnonymousCharSequence.toString());
      }
    });
    addView(this.mHeadBg);
    init();
  }

  private boolean hasResult()
  {
    return InfoManager.getInstance().root().mSearchNode.hasResult();
  }

  private void init()
  {
    this.mDelegate = new InputMethodUtil.InputStateDelegate()
    {
      public void closeIfNeed()
      {
        SearchView.this.closeKeyBoard();
      }
    };
    this.mViewPager = new MyViewPager(getContext());
    this.mViewPager.setEventHandler(this);
    this.mViewPager.setInputStateDelegate(this.mDelegate);
    addView(this.mViewPager);
    this.mViewPager.setVisibility(8);
    this.mRecentSearchView = new SearchHistoryView(getContext(), this);
    addView(this.mRecentSearchView);
    this.mSuggestionView = new SearchSuggestionView(getContext());
    this.mSuggestionView.setSubscribeListener(this);
    addView(this.mSuggestionView);
    this.mSuggestionView.setVisibility(8);
  }

  private void loadSuggestions(String paramString)
  {
    this.mSuggestionView.setVisibility(0);
    InfoManager.getInstance().root().mSearchNode.setLastSuggestWord(paramString);
    InfoManager.getInstance().loadSearchSuggestion(paramString, this);
    this.mViewType = 1;
  }

  private void openKeyBoard()
  {
    InputMethodUtil.showSoftInput(this.mEditText);
  }

  private void search(String paramString)
  {
    if ((paramString != null) && (!paramString.equalsIgnoreCase("")))
    {
      InfoManager.getInstance().root().mSearchNode.addRecentKeywords(paramString);
      InfoManager.getInstance().loadSearch(paramString, this);
      this.mRecentSearchView.setRecents();
      InfoManager.getInstance().root().mSearchNode.reset();
      showLoading();
    }
    while (true)
    {
      this.mViewPager.setVisibility(0);
      this.mRecentSearchView.setVisibility(8);
      this.mSuggestionView.setVisibility(8);
      this.mViewType = 2;
      return;
      InfoManager.getInstance().root().mSearchNode.reset();
      setResult();
    }
  }

  private void setResult()
  {
    if (hasResult())
      QTMSGManage.getInstance().sendStatistcsMessage("search_gotresult");
    this.mViewPager.setCurrentItem(0, false);
    this.mViewPager.setAllViewData();
  }

  private void showLoading()
  {
    int i = 0;
    while (i < this.mViewPager.getSubViewCnt())
    {
      this.mViewPager.setSubViewLoading(i);
      i += 1;
    }
  }

  public void close(boolean paramBoolean)
  {
    this.mViewPager.close(paramBoolean);
    InfoManager.getInstance().root().mSearchNode.reset();
    InfoManager.getInstance().unRegisterSubscribeEventListener(this, new String[] { "RSHK", "RSSUGG", "RSRL" });
    super.close(paramBoolean);
  }

  protected boolean closeKeyBoard()
  {
    if (this.mInputMethodShowing)
      return InputMethodUtil.hideSoftInput(this);
    return false;
  }

  public void onEvent(Object paramObject1, String paramString, Object paramObject2)
  {
    if (paramString.equalsIgnoreCase("searchRecent"));
    do
    {
      do
      {
        do
        {
          return;
          if (paramString.equalsIgnoreCase("popcontroller"))
          {
            closeKeyBoard();
            ControllerManager.getInstance().popLastController();
            return;
          }
          if (paramString.equalsIgnoreCase("deleteText"))
          {
            this.mEditText.setText("");
            return;
          }
          if (!paramString.equalsIgnoreCase("search"))
            break;
        }
        while (this.mEditText.getText() == null);
        paramObject1 = this.mEditText.getText().toString();
      }
      while (TextUtils.isEmpty(paramObject1));
      search(paramObject1);
      closeKeyBoard();
      return;
      if (paramString.equalsIgnoreCase("clearRecent"))
      {
        InfoManager.getInstance().root().mSearchNode.clearKeywords();
        return;
      }
      if (paramString.equalsIgnoreCase("voiceSearch"))
      {
        this.mEditText.setText("");
        QTMSGManage.getInstance().sendStatistcsMessage("VoiceRecognition", "voice_search_start");
        closeKeyBoard();
        EventDispacthManager.getInstance().dispatchAction("voice_view", null);
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("pagechanged"));
    int i = ((Integer)paramObject2).intValue();
    InfoManager.getInstance().root().mSearchNode.setSearchPageType(i);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    paramInt1 = this.mHeadBg.getMeasuredHeight();
    this.mHeadBg.layout(0, 0, this.standardLayout.width, paramInt1);
    this.mViewPager.layout(0, paramInt1, this.standardLayout.width, this.standardLayout.height);
    this.mRecentSearchView.layout(0, paramInt1, this.standardLayout.width, this.standardLayout.height);
    this.mSuggestionView.layout(0, paramInt1, this.standardLayout.width, this.standardLayout.height);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    this.standardLayout.scaleToBounds(View.MeasureSpec.getSize(paramInt1), View.MeasureSpec.getSize(paramInt2));
    this.standardLayout.measureView(this.mHeadBg);
    paramInt1 = this.mHeadBg.getMeasuredHeight();
    paramInt1 = View.MeasureSpec.makeMeasureSpec(this.standardLayout.height - paramInt1, 1073741824);
    this.mViewPager.measure(this.standardLayout.getWidthMeasureSpec(), paramInt1);
    this.mRecentSearchView.measure(this.standardLayout.getWidthMeasureSpec(), paramInt1);
    this.mSuggestionView.measure(this.standardLayout.getWidthMeasureSpec(), paramInt1);
    setMeasuredDimension(this.standardLayout.width, this.standardLayout.height);
  }

  public void onNotification(String paramString)
  {
    if (paramString.equalsIgnoreCase("RSSUGG"))
      this.mSuggestionView.update("setData", null);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("RSRL"))
      {
        setResult();
        return;
      }
    }
    while (!paramString.equalsIgnoreCase("RSHK"));
    this.mRecentSearchView.setHot();
  }

  public void onRecvDataException(String paramString, InfoManager.DataExceptionStatus paramDataExceptionStatus)
  {
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt2 < this.mSmallestHeight)
      this.mSmallestHeight = paramInt2;
    if (this.mSmallestHeight == paramInt2);
    for (boolean bool = true; ; bool = false)
    {
      this.mInputMethodShowing = bool;
      super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
  }

  protected void submitQuery(String paramString)
  {
    if (paramString != null)
    {
      this.mViewType = -1;
      this.mEditText.setText(paramString);
      this.mEditText.setSelection(paramString.length());
    }
    closeKeyBoard();
  }

  public void update(String paramString, Object paramObject)
  {
    if (paramString.equalsIgnoreCase("setData"));
    do
    {
      do
      {
        return;
        if (paramString.equalsIgnoreCase("closekeyboard"))
        {
          closeKeyBoard();
          return;
        }
        if (!paramString.equalsIgnoreCase("openKeyBoard"))
          break;
      }
      while ((this.mEditText.getText() != null) && (!this.mEditText.getText().toString().equalsIgnoreCase("")));
      this.mEditText.requestFocus();
      openKeyBoard();
      return;
    }
    while (!paramString.equalsIgnoreCase("selectTab"));
    int i = ((Integer)paramObject).intValue();
    if ((i >= 0) && (i < SearchNode.TABNAMES.length))
      QTMSGManage.getInstance().sendStatistcsMessage("search_viewall", SearchNode.TABNAMES[i]);
    this.mViewPager.setCurrentItem(i, false);
  }

  private class MyViewPager extends QtViewPager
  {
    public MyViewPager(Context arg2)
    {
      super();
      this.mStatisticalType = "search_tab";
      setDataSetMode(1);
    }

    protected boolean enableSlide()
    {
      return true;
    }

    protected IView generateView(int paramInt)
    {
      if (paramInt == 0)
      {
        localObject = new SearchResultAllView(getContext());
        ((SearchResultAllView)localObject).setInputStateDelegate(SearchView.this.mDelegate);
        return localObject;
      }
      if (paramInt == 3)
      {
        localObject = new SearchChannelListView(getContext());
        ((SearchChannelListView)localObject).setInputStateDelegate(SearchView.this.mDelegate);
        return localObject;
      }
      Object localObject = new SearchCommonListView(getContext(), paramInt);
      ((SearchCommonListView)localObject).setInputStateDelegate(SearchView.this.mDelegate);
      return localObject;
    }

    protected int getSubViewCnt()
    {
      return 5;
    }

    protected String getTab(int paramInt)
    {
      return SearchNode.TABNAMES[paramInt];
    }

    protected void setSubViewData(IView paramIView, int paramInt)
    {
      if (paramIView != null)
        paramIView.update("setData", null);
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.search.SearchView
 * JD-Core Version:    0.6.2
 */