package fm.qingting.qtradio.search;

import fm.qingting.qtradio.log.LogModule;
import fm.qingting.qtradio.logger.QTLogger;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.SharedCfg;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SearchNode extends Node
{
  public static final int ALL = 0;
  public static final int CHANNEL = 3;
  public static final int EPISODE = 4;
  private static final int MAX_KEYWORDS_SIZE = 10;
  public static final int PODCASTER = 2;
  private static final String SEPARATOR = "_";
  public static final int SHOW = 1;
  public static final String[] TABNAMES = { "全部", "专辑", "主播", "电台", "节目" };
  private String mLastKeyword;
  private String mLastSuggestWord;
  private List<SearchHotKeyword> mLstHotKeywords;
  private List<String> mRecentKeywords;
  public int mSearchPageType = 1;
  private SearchResult[] mSearchResults = new SearchResult[5];
  private List<SearchItemNode> mSuggestions;
  private boolean mVoiceSearch;

  public SearchNode()
  {
    this.nodeName = "search";
  }

  private void addKeyword(String paramString)
  {
    if ((paramString == null) || (paramString.equalsIgnoreCase("")))
      return;
    int i = 0;
    while (i < this.mRecentKeywords.size())
    {
      if (paramString.equalsIgnoreCase((String)this.mRecentKeywords.get(i)))
      {
        this.mRecentKeywords.remove(i);
        this.mRecentKeywords.add(0, paramString);
        updateToDB();
        return;
      }
      i += 1;
    }
    if (this.mRecentKeywords.size() >= 10)
      this.mRecentKeywords.remove(this.mRecentKeywords.size() - 1);
    this.mRecentKeywords.add(0, paramString);
    updateToDB();
  }

  public static String getGroupTypeByType(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 1:
      return "channel_ondemand";
    case 3:
      return "channel_live";
    case 4:
      return "program_ondemand";
    case 2:
    }
    return "people_podcaster";
  }

  private static boolean isLegalType(int paramInt)
  {
    return (paramInt >= 0) && (paramInt <= 4);
  }

  private static int pickLargest(double[] paramArrayOfDouble)
  {
    int i = 0;
    if (paramArrayOfDouble == null)
      return 0;
    double d1 = -1.0D;
    int j = 0;
    while (i < paramArrayOfDouble.length)
    {
      double d2 = d1;
      if (d1 < paramArrayOfDouble[i])
      {
        d2 = paramArrayOfDouble[i];
        j = i;
      }
      i += 1;
      d1 = d2;
    }
    return j;
  }

  private void restoreFromDB()
  {
    Object localObject1 = SharedCfg.getInstance().getRecentKeyWords();
    if (localObject1 != null)
    {
      localObject1 = ((String)localObject1).split("_");
      if (localObject1 != null)
      {
        int i = 0;
        while (i < localObject1.length)
        {
          Object localObject2 = localObject1[i];
          if ((localObject2 != null) && (!localObject2.equalsIgnoreCase("")))
            this.mRecentKeywords.add(localObject2);
          i += 1;
        }
      }
    }
  }

  public void addRecentKeywords(String paramString)
  {
    if (paramString == null)
      return;
    addKeyword(paramString);
  }

  public void appendSearchResult(SearchResult paramSearchResult)
  {
    if (paramSearchResult == null);
    int i;
    do
    {
      return;
      i = paramSearchResult.getType();
    }
    while (!isLegalType(i));
    if (this.mSearchResults[i] == null)
      this.mSearchResults[i] = new SearchResult();
    this.mSearchResults[i].append(paramSearchResult);
  }

  public void clearKeywords()
  {
    this.mRecentKeywords.clear();
    updateToDB();
  }

  public List<SearchHotKeyword> getHotKeywords()
  {
    return this.mLstHotKeywords;
  }

  public String getLastKeyword()
  {
    return this.mLastKeyword;
  }

  public String getLastSuggestWord()
  {
    return this.mLastSuggestWord;
  }

  public List<String> getRecentKeywords()
  {
    return this.mRecentKeywords;
  }

  public List<SearchItemNode> getResult(int paramInt)
  {
    if (!isLegalType(paramInt));
    SearchResult localSearchResult;
    do
    {
      return null;
      localSearchResult = this.mSearchResults[paramInt];
    }
    while (localSearchResult == null);
    return localSearchResult.getList();
  }

  public int getResultCountByType(int paramInt)
  {
    if (!isLegalType(paramInt))
      return -1;
    SearchResult localSearchResult = this.mSearchResults[paramInt];
    if (localSearchResult != null)
      return localSearchResult.getListSize();
    return 0;
  }

  public int getSearchIndex(SearchItemNode paramSearchItemNode)
  {
    if (paramSearchItemNode == null);
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              return -1;
              if (this.mSearchPageType != 1)
                break;
            }
            while (this.mSearchResults[0] == null);
            return this.mSearchResults[0].getIndex(paramSearchItemNode);
            if (this.mSearchPageType != 2)
              break;
          }
          while (this.mSearchResults[3] == null);
          return this.mSearchResults[0].getIndex(paramSearchItemNode);
          if (this.mSearchPageType != 3)
            break;
        }
        while (this.mSearchResults[1] == null);
        return this.mSearchResults[0].getIndex(paramSearchItemNode);
        if (this.mSearchPageType != 4)
          break;
      }
      while (this.mSearchResults[4] == null);
      return this.mSearchResults[0].getIndex(paramSearchItemNode);
    }
    while ((this.mSearchPageType != 5) || (this.mSearchResults[2] == null));
    return this.mSearchResults[0].getIndex(paramSearchItemNode);
  }

  public ArrayList<Integer> getSortedTypesByScore()
  {
    int j = 0;
    double[] arrayOfDouble = new double[4];
    double[] tmp8_7 = arrayOfDouble;
    tmp8_7[0] = 0.0D;
    double[] tmp12_8 = tmp8_7;
    tmp12_8[1] = 0.0D;
    double[] tmp16_12 = tmp12_8;
    tmp16_12[2] = 0.0D;
    double[] tmp20_16 = tmp16_12;
    tmp20_16[3] = 0.0D;
    tmp20_16;
    int i = 0;
    if (i < arrayOfDouble.length)
    {
      if (this.mSearchResults[(i + 1)] == null);
      for (double d = 0.0D; ; d = this.mSearchResults[(i + 1)].getHighestScore())
      {
        arrayOfDouble[i] = d;
        i += 1;
        break;
      }
    }
    ArrayList localArrayList = new ArrayList();
    i = tmp8_7;
    while (i < arrayOfDouble.length)
    {
      tmp8_7 = pickLargest(arrayOfDouble);
      arrayOfDouble[tmp8_7] = -1.0D;
      localArrayList.add(Integer.valueOf(tmp8_7 + 1));
      i += 1;
    }
    return localArrayList;
  }

  public List<SearchItemNode> getSuggestions()
  {
    return this.mSuggestions;
  }

  public int getTotalCountByType(int paramInt)
  {
    if (!isLegalType(paramInt))
      return -1;
    SearchResult localSearchResult = this.mSearchResults[paramInt];
    if (localSearchResult != null)
      return localSearchResult.getTotalSize();
    return 0;
  }

  public boolean getVoiceSearch()
  {
    return this.mVoiceSearch;
  }

  public boolean hasResult()
  {
    boolean bool = false;
    SearchResult localSearchResult = this.mSearchResults[0];
    if (localSearchResult != null)
      bool = localSearchResult.hasResult();
    return bool;
  }

  public void init()
  {
    this.mRecentKeywords = new ArrayList();
    restoreFromDB();
  }

  public void reset()
  {
    int i = 0;
    while (i < this.mSearchResults.length)
    {
      this.mSearchResults[i] = null;
      i += 1;
    }
    if (this.mSuggestions != null)
      this.mSuggestions.clear();
  }

  public void setHotKeywords(List<SearchHotKeyword> paramList)
  {
    this.mLstHotKeywords = paramList;
  }

  public void setLastKeyword(String paramString)
  {
    this.mLastKeyword = paramString;
  }

  public void setLastSuggestWord(String paramString)
  {
    this.mLastSuggestWord = paramString;
  }

  public void setSearchPageType(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.mSearchPageType = 1;
      return;
    case 1:
      this.mSearchPageType = 3;
      return;
    case 2:
      this.mSearchPageType = 5;
      return;
    case 3:
      this.mSearchPageType = 2;
      return;
    case 4:
    }
    this.mSearchPageType = 4;
  }

  public void setSearchResult(List<SearchResult> paramList)
  {
    if (paramList == null);
    do
    {
      return;
      SearchResult localSearchResult1 = new SearchResult();
      localSearchResult1.setType(0);
      int i = 0;
      while (i < paramList.size())
      {
        SearchResult localSearchResult2 = (SearchResult)paramList.get(i);
        this.mSearchResults[localSearchResult2.getType()] = localSearchResult2;
        localSearchResult1.appendIgnoreType(localSearchResult2);
        i += 1;
      }
      localSearchResult1.sort();
      this.mSearchResults[0] = localSearchResult1;
      paramList = QTLogger.getInstance().buildSearchKeywordLogString(this.mLastKeyword, localSearchResult1.getList());
    }
    while (paramList == null);
    LogModule.getInstance().send("search_v6", paramList);
  }

  public void setSuggestions(List<SearchItemNode> paramList)
  {
    this.mSuggestions = paramList;
  }

  public void setVoiceSearch(boolean paramBoolean)
  {
    this.mVoiceSearch = false;
  }

  public void updateToDB()
  {
    if (this.mRecentKeywords == null)
      return;
    Object localObject = "";
    int i = 0;
    while (i < this.mRecentKeywords.size())
    {
      String str = (String)localObject + (String)this.mRecentKeywords.get(i);
      localObject = str;
      if (this.mRecentKeywords.size() - 1 != i)
        localObject = str + "_";
      i += 1;
    }
    SharedCfg.getInstance().setRecentKeyWords((String)localObject);
  }

  public static class NewSearchComparator
    implements Comparator<SearchItemNode>
  {
    public int compare(SearchItemNode paramSearchItemNode1, SearchItemNode paramSearchItemNode2)
    {
      if (paramSearchItemNode1.totalScore > paramSearchItemNode2.totalScore)
        return -1;
      if (paramSearchItemNode1.totalScore < paramSearchItemNode2.totalScore)
        return 1;
      return 0;
    }
  }

  public static class SearchResult
  {
    private List<SearchItemNode> mList;
    private int mTotalSize = 0;
    private int mType;

    protected void append(SearchResult paramSearchResult)
    {
      if (paramSearchResult == null);
      while (this.mType != paramSearchResult.getType())
        return;
      if (this.mList == null)
        this.mList = new ArrayList();
      this.mList.addAll(paramSearchResult.getList());
    }

    protected void appendIgnoreType(SearchResult paramSearchResult)
    {
      if (paramSearchResult == null)
        return;
      if (this.mList == null)
        this.mList = new ArrayList();
      this.mList.addAll(paramSearchResult.getList());
    }

    protected double getHighestScore()
    {
      if ((this.mList != null) && (this.mList.size() > 0))
        return ((SearchItemNode)this.mList.get(0)).totalScore;
      return 0.0D;
    }

    protected int getIndex(SearchItemNode paramSearchItemNode)
    {
      if (this.mList == null)
        return -1;
      return this.mList.indexOf(paramSearchItemNode);
    }

    protected List<SearchItemNode> getList()
    {
      return this.mList;
    }

    protected int getListSize()
    {
      if (this.mList == null)
        return 0;
      return this.mList.size();
    }

    protected int getTotalSize()
    {
      return this.mTotalSize;
    }

    public int getType()
    {
      return this.mType;
    }

    protected boolean hasResult()
    {
      if (this.mList == null);
      while (this.mList.size() <= 0)
        return false;
      return true;
    }

    public void setList(List<SearchItemNode> paramList)
    {
      this.mList = paramList;
    }

    public void setResult(int paramInt1, int paramInt2, List<SearchItemNode> paramList)
    {
      this.mList = paramList;
      this.mTotalSize = paramInt1;
      this.mType = paramInt2;
    }

    public void setTotalSize(int paramInt)
    {
      this.mTotalSize = paramInt;
    }

    protected void setType(int paramInt)
    {
      this.mType = paramInt;
    }

    protected void sort()
    {
      if (this.mList == null)
        return;
      Collections.sort(this.mList, new SearchNode.NewSearchComparator());
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.search.SearchNode
 * JD-Core Version:    0.6.2
 */