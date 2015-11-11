package fm.qingting.framework.view;

import android.graphics.Canvas;
import android.graphics.Paint.FontMetricsInt;
import android.text.Layout.Alignment;
import android.text.TextPaint;
import android.text.TextUtils;
import fm.qingting.framework.manager.EventDispacthManager;

class QtLayout
{
  private static final char CHAR_COLON = ':';
  private static final char CHAR_COMMA = ',';
  private static final char CHAR_DOT = '.';
  private static final char CHAR_HYPHEN = '-';
  private static final char CHAR_NEW_LINE = '\n';
  private static final char CHAR_SEMICOLON = ';';
  private static final char CHAR_SLASH = '/';
  private static final char CHAR_SPACE = ' ';
  private static final char CHAR_TAB = '\t';
  private static final String SINGLE_CHINESE = "嘿";
  private int mActualLineCnt = 0;
  private Layout.Alignment mAlignment = Layout.Alignment.ALIGN_NORMAL;
  private boolean mHasSeperated = false;
  private float mIndentation = 0.0F;
  int[] mLineBottom;
  int[] mLineEnds;
  int[] mLineMarkers;
  float[] mMarkersWidth;
  private int mMaxHeight;
  private int mMaxLineCnt = 5;
  private int mMaxWidth;
  private TextPaint mPaint;
  private String mText;
  private int mVerticalOffset = 0;
  char[] mWorkChars;
  float[] mWorkWidths;

  private float getXoffset(int paramInt)
  {
    int j = 0;
    int i = 0;
    float f2 = 0.0F;
    float f1 = 0.0F;
    switch ($SWITCH_TABLE$android$text$Layout$Alignment()[this.mAlignment.ordinal()])
    {
    default:
      return 0.0F;
    case 2:
      if (paramInt == 0)
        return this.mIndentation;
      return 0.0F;
    case 1:
      if (paramInt > 0)
        i = this.mLineEnds[(paramInt - 1)];
      paramInt = this.mLineEnds[paramInt];
      while (true)
      {
        if (i >= paramInt)
          return (this.mMaxWidth - f1) / 2.0F;
        f1 += this.mWorkWidths[i];
        i += 1;
      }
    case 3:
    }
    i = j;
    if (paramInt > 0)
      i = this.mLineEnds[(paramInt - 1)];
    paramInt = this.mLineEnds[paramInt];
    f1 = f2;
    while (true)
    {
      if (i >= paramInt)
        return this.mMaxWidth - f1;
      f1 += this.mWorkWidths[i];
      i += 1;
    }
  }

  private float getYoffset(int paramInt)
  {
    return this.mLineBottom[paramInt] + this.mVerticalOffset;
  }

  private void out(char[] paramArrayOfChar, float[] paramArrayOfFloat)
    throws IllegalStateException
  {
    if ((this.mLineMarkers == null) || (this.mLineMarkers.length == 0))
    {
      this.mWorkChars = paramArrayOfChar;
      this.mWorkWidths = paramArrayOfFloat;
    }
    int j;
    int i;
    int k;
    do
    {
      return;
      j = 0;
      i = 0;
      while (true)
      {
        if (i >= this.mActualLineCnt)
        {
          if (j != 0)
            break;
          this.mWorkChars = paramArrayOfChar;
          this.mWorkWidths = paramArrayOfFloat;
          return;
        }
        j += this.mLineMarkers[i];
        i += 1;
      }
      this.mWorkChars = new char[paramArrayOfChar.length + j];
      this.mWorkWidths = new float[paramArrayOfFloat.length + j];
      m = 0;
      i = 0;
      j = 0;
      k = 0;
    }
    while (k >= this.mActualLineCnt);
    int i1 = this.mLineMarkers[k];
    int n = this.mLineEnds[k];
    if (n - m < 0)
      throw new IllegalStateException();
    System.arraycopy(paramArrayOfChar, m, this.mWorkChars, i, n - m);
    System.arraycopy(paramArrayOfFloat, m, this.mWorkWidths, i, n - m);
    int m = n;
    switch (i1)
    {
    default:
    case 0:
    case 1:
    case 2:
    }
    while (true)
    {
      int[] arrayOfInt = this.mLineEnds;
      arrayOfInt[k] += j;
      k += 1;
      break;
      i = n + j;
      continue;
      this.mWorkChars[(n + j)] = '-';
      this.mWorkWidths[(n + j)] = this.mMarkersWidth[0];
      j += 1;
      i = n + j;
      continue;
      this.mWorkChars[(n + j)] = '.';
      this.mWorkChars[(n + j + 1)] = '.';
      float f = this.mMarkersWidth[1];
      this.mWorkWidths[(n + j)] = f;
      this.mWorkWidths[(n + j + 1)] = f;
      j += 2;
      i = n + j;
    }
  }

  private void seperate(TextPaint paramTextPaint, Layout.Alignment paramAlignment, TextViewElement.VerticalAlignment paramVerticalAlignment, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    this.mPaint = paramTextPaint;
    this.mAlignment = paramAlignment;
    this.mMaxWidth = paramInt1;
    this.mMaxHeight = paramInt2;
    if ((this.mHasSeperated) || (this.mText == null));
    int m;
    float[] arrayOfFloat;
    Paint.FontMetricsInt localFontMetricsInt;
    label221: float f1;
    label285: label304: label328: 
    do
    {
      return;
      this.mHasSeperated = true;
      m = this.mText.length();
      paramAlignment = new char[m];
      arrayOfFloat = new float[m];
      if ((this.mLineEnds == null) || (this.mLineEnds.length == 0))
        this.mLineEnds = new int[this.mMaxLineCnt];
      if ((this.mLineBottom == null) || (this.mLineBottom.length == 0))
        this.mLineBottom = new int[this.mMaxLineCnt];
      if ((this.mLineMarkers == null) || (this.mLineMarkers.length == 0))
        this.mLineMarkers = new int[this.mMaxLineCnt];
      if ((this.mMarkersWidth == null) || (this.mMarkersWidth.length == 0))
        this.mMarkersWidth = new float[2];
      localFontMetricsInt = this.mPaint.getFontMetricsInt();
      TextUtils.getChars(this.mText, 0, m, paramAlignment, 0);
      switch ($SWITCH_TABLE$fm$qingting$framework$view$TextViewElement$VerticalAlignment()[paramVerticalAlignment.ordinal()])
      {
      case 2:
      default:
        this.mVerticalOffset = 0;
        this.mPaint.getTextWidths(this.mText, arrayOfFloat);
        paramTextPaint = this.mPaint;
        paramInt1 = this.mMarkersWidth.length;
        paramVerticalAlignment = this.mMarkersWidth;
        paramTextPaint.getTextWidths(new char[] { 45, 46 }, 0, paramInt1, paramVerticalAlignment);
        if (paramInt3 <= 0)
          break label444;
        paramTextPaint = "";
        paramInt1 = 0;
        if (paramInt1 < paramInt3)
          break label415;
        this.mIndentation = this.mPaint.measureText(paramTextPaint);
        f1 = this.mIndentation;
        this.mActualLineCnt = 0;
        paramInt3 = 0;
        paramInt2 = 0;
        if (paramInt2 < m)
          break label453;
        try
        {
          out(paramAlignment, arrayOfFloat);
          return;
        }
        catch (IllegalStateException paramTextPaint)
        {
        }
      case 1:
      case 3:
      }
    }
    while (this.mText == null);
    paramTextPaint = "";
    paramInt1 = 0;
    while (true)
    {
      if (paramInt1 >= this.mLineEnds.length)
      {
        EventDispacthManager.getInstance().dispatchAction("sendErrorLog", this.mText + paramTextPaint);
        return;
        this.mVerticalOffset = (-localFontMetricsInt.descent);
        break label221;
        this.mVerticalOffset = localFontMetricsInt.ascent;
        break;
        label415: paramTextPaint = paramTextPaint + "嘿";
        paramInt1 += 1;
        break label285;
        label444: this.mIndentation = paramFloat;
        break label304;
        label453: char c = paramAlignment[paramInt2];
        float f2 = arrayOfFloat[paramInt2];
        if (paramInt2 == m - 1)
          if (this.mActualLineCnt == this.mMaxLineCnt - 1)
          {
            paramInt3 = localFontMetricsInt.bottom + paramInt3 - localFontMetricsInt.top;
            this.mLineBottom[this.mActualLineCnt] = paramInt3;
            if (f1 + f2 > this.mMaxWidth)
            {
              paramFloat = arrayOfFloat[paramInt2];
              this.mLineMarkers[this.mActualLineCnt] = 2;
              if (f1 - paramFloat + this.mMarkersWidth[1] * 2.0F <= this.mMaxWidth)
              {
                paramTextPaint = this.mLineEnds;
                paramInt1 = this.mActualLineCnt;
                this.mActualLineCnt = (paramInt1 + 1);
                paramTextPaint[paramInt1] = (paramInt2 - 1);
                paramFloat = f1;
                paramInt1 = paramInt3;
              }
            }
          }
        label604: 
        do
        {
          while (true)
          {
            paramInt2 += 1;
            paramInt3 = paramInt1;
            f1 = paramFloat;
            break;
            paramInt1 = paramInt3;
            paramFloat = f1;
            if (paramInt2 > 0)
            {
              paramInt1 = paramInt3;
              paramFloat = f1;
              if (f1 - (arrayOfFloat[paramInt2] + arrayOfFloat[(paramInt2 - 1)]) + this.mMarkersWidth[1] * 2.0F <= this.mMaxWidth)
              {
                paramTextPaint = this.mLineEnds;
                paramInt1 = this.mActualLineCnt;
                this.mActualLineCnt = (paramInt1 + 1);
                paramTextPaint[paramInt1] = (paramInt2 - 2);
                paramInt1 = paramInt3;
                paramFloat = f1;
                continue;
                paramTextPaint = this.mLineEnds;
                paramInt1 = this.mActualLineCnt;
                this.mActualLineCnt = (paramInt1 + 1);
                paramTextPaint[paramInt1] = (paramInt2 + 1);
                paramFloat = 0.0F;
                paramInt1 = paramInt3;
                continue;
                if (this.mActualLineCnt >= this.mMaxLineCnt)
                  break label328;
                paramInt1 = localFontMetricsInt.bottom + paramInt3 - localFontMetricsInt.top;
                if (f1 + f2 > this.mMaxWidth)
                {
                  this.mLineBottom[this.mActualLineCnt] = paramInt1;
                  paramTextPaint = this.mLineEnds;
                  paramInt3 = this.mActualLineCnt;
                  this.mActualLineCnt = (paramInt3 + 1);
                  paramTextPaint[paramInt3] = paramInt2;
                  this.mLineBottom[this.mActualLineCnt] = (localFontMetricsInt.bottom + paramInt1 - localFontMetricsInt.top);
                  paramTextPaint = this.mLineEnds;
                  paramInt3 = this.mActualLineCnt;
                  this.mActualLineCnt = (paramInt3 + 1);
                  paramTextPaint[paramInt3] = (paramInt2 + 1);
                }
                while (true)
                {
                  paramFloat = f2;
                  break;
                  this.mLineBottom[this.mActualLineCnt] = paramInt1;
                  paramTextPaint = this.mLineEnds;
                  paramInt3 = this.mActualLineCnt;
                  this.mActualLineCnt = (paramInt3 + 1);
                  paramTextPaint[paramInt3] = (paramInt2 + 1);
                }
                if (c == '\n')
                {
                  if (this.mActualLineCnt >= this.mMaxLineCnt)
                    break label328;
                  paramInt1 = localFontMetricsInt.bottom + paramInt3 - localFontMetricsInt.top;
                  this.mLineBottom[this.mActualLineCnt] = paramInt1;
                  paramTextPaint = this.mLineEnds;
                  paramInt3 = this.mActualLineCnt;
                  this.mActualLineCnt = (paramInt3 + 1);
                  paramTextPaint[paramInt3] = (paramInt2 + 1);
                  paramFloat = 0.0F;
                  continue;
                }
                if (f1 + f2 > this.mMaxWidth)
                  break label1032;
                paramFloat = f1 + f2;
                paramInt1 = paramInt3;
              }
            }
          }
          paramInt1 = paramInt3;
          paramFloat = f1;
        }
        while (this.mActualLineCnt >= this.mMaxLineCnt);
        label1032: int j = 0;
        int k = 0;
        paramInt1 = j;
        int i = k;
        if (isCharacter(c))
        {
          paramInt1 = j;
          i = k;
          if (paramInt2 > 0)
          {
            paramInt1 = j;
            i = k;
            if (isCharacter(paramAlignment[(paramInt2 - 1)]))
            {
              paramInt1 = j;
              i = k;
              if (paramInt2 > 1)
              {
                c = paramAlignment[(paramInt2 - 2)];
                paramInt1 = 1;
                i = 1;
                if (!isCharacter(c))
                  break label1281;
                this.mLineMarkers[this.mActualLineCnt] = 1;
              }
            }
          }
        }
        label1155: if (this.mActualLineCnt < this.mMaxLineCnt)
        {
          paramInt3 = localFontMetricsInt.bottom + paramInt3 - localFontMetricsInt.top;
          this.mLineBottom[this.mActualLineCnt] = paramInt3;
          if ((this.mActualLineCnt != this.mMaxLineCnt - 1) || (paramInt2 >= m - 1))
            break label1419;
          this.mLineMarkers[this.mActualLineCnt] = 2;
          if (this.mMarkersWidth[1] * 2.0F + f1 > this.mMaxWidth)
            break label1294;
          paramTextPaint = this.mLineEnds;
          paramInt1 = this.mActualLineCnt;
          this.mActualLineCnt = (paramInt1 + 1);
          paramTextPaint[paramInt1] = paramInt2;
        }
        label1281: label1419: 
        while (true)
        {
          paramFloat = f2;
          paramInt1 = paramInt3;
          break label604;
          this.mLineMarkers[this.mActualLineCnt] = 0;
          break label1155;
          break;
          label1294: if (f1 - arrayOfFloat[paramInt2] + this.mMarkersWidth[1] * 2.0F <= this.mMaxWidth)
          {
            paramTextPaint = this.mLineEnds;
            paramInt1 = this.mActualLineCnt;
            this.mActualLineCnt = (paramInt1 + 1);
            paramTextPaint[paramInt1] = (paramInt2 - 1);
          }
          else if ((paramInt2 > 0) && (f1 - (arrayOfFloat[paramInt2] + arrayOfFloat[(paramInt2 - 1)]) + this.mMarkersWidth[1] * 2.0F <= this.mMaxWidth))
          {
            paramTextPaint = this.mLineEnds;
            paramInt1 = this.mActualLineCnt;
            this.mActualLineCnt = (paramInt1 + 1);
            paramTextPaint[paramInt1] = (paramInt2 - 2);
            continue;
            if (i == 0)
              this.mLineMarkers[this.mActualLineCnt] = 0;
            paramTextPaint = this.mLineEnds;
            i = this.mActualLineCnt;
            this.mActualLineCnt = (i + 1);
            paramTextPaint[i] = (paramInt2 - paramInt1);
          }
        }
      }
      paramTextPaint = paramTextPaint + "_" + this.mLineEnds[paramInt1];
      paramInt1 += 1;
    }
  }

  private void simpleOut(char[] paramArrayOfChar, float[] paramArrayOfFloat)
  {
    this.mWorkChars = paramArrayOfChar;
    this.mWorkWidths = paramArrayOfFloat;
  }

  public void draw(Canvas paramCanvas, TextPaint paramTextPaint, Layout.Alignment paramAlignment, TextViewElement.VerticalAlignment paramVerticalAlignment, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    if (this.mText == null);
    do
    {
      return;
      if ((this.mPaint != paramTextPaint) || (this.mAlignment != paramAlignment) || (this.mMaxWidth != paramInt1) || (this.mMaxHeight != paramInt2))
        this.mHasSeperated = false;
      if (!this.mHasSeperated)
        seperate(paramTextPaint, paramAlignment, paramVerticalAlignment, paramInt1, paramInt2, paramInt3, paramFloat);
    }
    while ((this.mWorkChars == null) || (this.mWorkChars.length == 0) || (this.mWorkWidths == null) || (this.mWorkWidths.length == 0) || (this.mLineEnds == null) || (this.mLineEnds.length == 0));
    paramInt1 = 0;
    paramInt2 = 0;
    while (true)
    {
      paramInt3 = paramInt1;
      if (paramInt2 >= this.mActualLineCnt)
        break;
      paramInt1 = this.mLineEnds[paramInt2];
      paramFloat = getXoffset(paramInt2);
      float f = getYoffset(paramInt2);
      if ((paramInt1 > paramInt3) && (this.mWorkChars.length >= paramInt1))
        paramCanvas.drawText(this.mWorkChars, paramInt3, paramInt1 - paramInt3, paramFloat, f, this.mPaint);
      paramInt2 += 1;
    }
  }

  public int getHeight(TextPaint paramTextPaint, Layout.Alignment paramAlignment, TextViewElement.VerticalAlignment paramVerticalAlignment, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    if ((this.mText == null) || (this.mText.equalsIgnoreCase("")));
    do
    {
      return 0;
      if (!this.mHasSeperated)
        seperate(paramTextPaint, paramAlignment, paramVerticalAlignment, paramInt1, paramInt2, paramInt3, paramFloat);
    }
    while (this.mActualLineCnt == 0);
    return (int)this.mLineBottom[(this.mActualLineCnt - 1)];
  }

  int getLineBase(int paramInt)
  {
    return (int)getYoffset(paramInt);
  }

  int getLineCnt(TextPaint paramTextPaint, Layout.Alignment paramAlignment, TextViewElement.VerticalAlignment paramVerticalAlignment, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    if ((this.mText == null) || (this.mText.equalsIgnoreCase("")))
      return 0;
    if (!this.mHasSeperated)
      seperate(paramTextPaint, paramAlignment, paramVerticalAlignment, paramInt1, paramInt2, paramInt3, paramFloat);
    return this.mActualLineCnt;
  }

  public int getWidth(TextPaint paramTextPaint, Layout.Alignment paramAlignment, TextViewElement.VerticalAlignment paramVerticalAlignment, int paramInt1, int paramInt2, int paramInt3, float paramFloat)
  {
    if ((this.mText == null) || (this.mText.equalsIgnoreCase("")));
    do
    {
      return 0;
      if (!this.mHasSeperated)
        seperate(paramTextPaint, paramAlignment, paramVerticalAlignment, paramInt1, paramInt2, paramInt3, paramFloat);
    }
    while (this.mActualLineCnt == 0);
    if (this.mActualLineCnt == 1)
    {
      paramFloat = 0.0F;
      paramInt2 = this.mLineEnds[0];
      paramInt1 = 0;
      while (true)
      {
        if (paramInt1 >= paramInt2)
          return (int)paramFloat;
        paramFloat += this.mWorkWidths[paramInt1];
        paramInt1 += 1;
      }
    }
    return this.mMaxWidth;
  }

  boolean isCharacter(char paramChar)
  {
    return ((paramChar >= 'A') && (paramChar <= 'Z')) || ((paramChar >= 'a') && (paramChar <= 'z'));
  }

  public void setMaxLine(int paramInt)
  {
    this.mMaxLineCnt = paramInt;
  }

  public void setText(String paramString)
  {
    this.mHasSeperated = false;
    if (this.mLineEnds != null)
      this.mLineEnds = null;
    if (this.mLineBottom != null)
      this.mLineBottom = null;
    if (this.mLineMarkers != null)
      this.mLineMarkers = null;
    if (this.mWorkChars != null)
      this.mWorkChars = null;
    if (this.mWorkWidths != null)
      this.mWorkWidths = null;
    if (this.mMarkersWidth != null)
      this.mMarkersWidth = null;
    this.mText = paramString;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.framework.view.QtLayout
 * JD-Core Version:    0.6.2
 */