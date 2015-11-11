package fm.qingting.utils;

import android.content.Context;
import android.content.res.Resources;
import android.text.SpannableString;
import fm.qingting.qtradio.R.drawable;
import fm.qingting.qtradio.view.chatroom.ExpressionSpan;
import fm.qingting.qtradio.view.chatroom.expression.ExpressionItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExpressionUtil
{
  private static ExpressionUtil instance;
  private List<ExpressionItem> expressionList;
  private HashMap<String, String> expressionMap;
  private int mOwenerId;

  private void getExpressionList(Context paramContext)
  {
    try
    {
      paramContext = new BufferedReader(new InputStreamReader(paramContext.getResources().openRawResource(2131165185), "UTF-8"));
      while (true)
      {
        Object localObject = paramContext.readLine();
        if (localObject == null)
          break;
        localObject = ((String)localObject).split(",");
        if (this.expressionList == null)
          this.expressionList = new ArrayList();
        try
        {
          this.expressionList.add(new ExpressionItem(localObject[0], localObject[1]));
        }
        catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
        {
          localIndexOutOfBoundsException.printStackTrace();
        }
      }
    }
    catch (IOException paramContext)
    {
      paramContext.printStackTrace();
      this.expressionMap = list2hash();
    }
  }

  public static ExpressionUtil getInstance()
  {
    if (instance == null)
      instance = new ExpressionUtil();
    return instance;
  }

  private HashMap<String, String> list2hash()
  {
    if (this.expressionList == null)
      return null;
    HashMap localHashMap = new HashMap();
    Iterator localIterator = this.expressionList.iterator();
    while (localIterator.hasNext())
    {
      ExpressionItem localExpressionItem = (ExpressionItem)localIterator.next();
      localHashMap.put(localExpressionItem.getExpressionName(), localExpressionItem.getExpressionIcon());
    }
    return localHashMap;
  }

  public void dealExpression(Context paramContext, SpannableString paramSpannableString, Pattern paramPattern, int paramInt1, int paramInt2, int paramInt3)
    throws SecurityException, NoSuchFieldException, NumberFormatException, IllegalArgumentException, IllegalAccessException, StackOverflowError
  {
    Matcher localMatcher = paramPattern.matcher(paramSpannableString);
    while (true)
    {
      Object localObject;
      int i;
      if (localMatcher.find())
      {
        String str = localMatcher.group();
        if (localMatcher.start() >= paramInt1)
        {
          localObject = (String)this.expressionMap.get(str);
          if (localObject == null)
          {
            i = localMatcher.start() + 1;
            if (i < paramSpannableString.length())
              dealExpression(paramContext, paramSpannableString, paramPattern, i, paramInt2, paramInt3);
          }
          else
          {
            i = Integer.parseInt(R.drawable.class.getDeclaredField((String)localObject).get(null).toString());
            if (i != 0)
            {
              localObject = new ExpressionSpan(paramContext.getResources(), i, paramInt2, paramInt3, this.mOwenerId);
              paramInt1 = str.length();
              i = localMatcher.start();
              if (paramInt1 <= 0);
            }
          }
        }
      }
      else
      {
        while (true)
        {
          paramInt1 = i + paramInt1;
          paramSpannableString.setSpan(localObject, localMatcher.start(), paramInt1, 17);
          if (paramInt1 < paramSpannableString.length())
            dealExpression(paramContext, paramSpannableString, paramPattern, paramInt1, paramInt2, paramInt3);
          return;
          paramInt1 = 1;
        }
        i = localMatcher.start() + 1;
        if (i < paramSpannableString.length())
          dealExpression(paramContext, paramSpannableString, paramPattern, i, paramInt2, paramInt3);
      }
    }
  }

  public int getExpressionCnt()
  {
    if (this.expressionList == null)
      return 0;
    return this.expressionList.size();
  }

  public SpannableString getExpressionString(Context paramContext, String paramString1, String paramString2, int paramInt1, int paramInt2)
  {
    paramString1 = new SpannableString(paramString1);
    if (this.expressionMap == null)
      return paramString1;
    paramString2 = Pattern.compile(paramString2, 2);
    try
    {
      dealExpression(paramContext, paramString1, paramString2, 0, paramInt1, paramInt2);
      return paramString1;
    }
    catch (Exception paramContext)
    {
      return paramString1;
    }
    catch (Error paramContext)
    {
    }
    return paramString1;
  }

  public List<ExpressionItem> getExpressionSubList(int paramInt1, int paramInt2)
  {
    if (this.expressionList == null);
    while ((paramInt1 < 0) || (paramInt2 > this.expressionList.size()))
      return null;
    return this.expressionList.subList(paramInt1, paramInt2);
  }

  public int getOwnerId()
  {
    return this.mOwenerId;
  }

  public void init(Context paramContext, int paramInt)
  {
    if (this.expressionMap == null)
      getExpressionList(paramContext);
    this.mOwenerId = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.utils.ExpressionUtil
 * JD-Core Version:    0.6.2
 */