package fm.qingting.qtradio.view.virtualchannels;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader.TileMode;
import android.text.TextPaint;
import fm.qingting.framework.view.ViewElement;
import fm.qingting.qtradio.manager.SkinManager;
import fm.qingting.qtradio.model.ActivityNode;
import fm.qingting.qtradio.model.ChannelNode;
import fm.qingting.qtradio.model.Node;
import fm.qingting.qtradio.model.ProgramNode;
import fm.qingting.qtradio.model.RecommendItemNode;
import fm.qingting.qtradio.model.SpecialTopicNode;

public class RatingElement extends ViewElement
{
  public static final int ORIGIN_HEIGHT = 30;
  public static final int ORIGIN_ITEM_HEIGHT = 21;
  public static final int ORIGIN_ITEM_WIDTH = 21;
  public static final int ORIGIN_MARGIN = 8;
  public static final int ORIGIN_WIDTH = 137;
  private boolean mEnableShade = false;
  private final Paint mGradientPaint = new Paint();
  private int mItemHeight = 21;
  private int mItemMargin = 8;
  private int mItemWidth = 21;
  private Node mNode;
  private int mRating;
  private int mRealTotalHeight;
  private int mRealTotalWidth;
  private final Rect mTextBound = new Rect();
  private final TextPaint mTextPaint = new TextPaint();
  private float mTextSize = SkinManager.getInstance().getSubTextSize();
  private int mTotalHeight = 30;
  private int mTotalWidth = 137;

  public RatingElement(Context paramContext)
  {
    super(paramContext);
  }

  private void drawNoRating(Canvas paramCanvas)
  {
    this.mTextPaint.setColor(SkinManager.getTextColorSubInfo());
    this.mTextPaint.setTextSize(this.mTextSize);
    this.mTextPaint.getTextBounds("暂无评分", 0, "暂无评分".length(), this.mTextBound);
    paramCanvas.drawText("暂无评分", getLeftMargin(), getTopMargin() + (getHeight() - this.mTextBound.top - this.mTextBound.bottom) / 2 + getHeight() / 18, this.mTextPaint);
  }

  private void drawRating(Canvas paramCanvas)
  {
    int i2 = 32;
    double d1 = this.mRealTotalWidth * 1.0D / this.mTotalWidth;
    double d2 = this.mRealTotalHeight * 1.0D / this.mTotalHeight;
    Bitmap localBitmap;
    int j;
    int i;
    int n;
    if (this.mRating > 0)
    {
      localBitmap = BitmapFactory.decodeResource(getContext().getResources(), 2130837814);
      j = (int)(this.mItemWidth * d1);
      i = (int)(d2 * this.mItemHeight);
      n = j;
      if (j > 32)
        n = 32;
      if (i <= 32)
        break label376;
    }
    while (true)
    {
      localBitmap = Bitmap.createScaledBitmap(localBitmap, n, i2, false);
      i = (int)(this.mItemMargin * d1);
      int i1 = i;
      if (i > 12)
        i1 = 12;
      int i3 = (this.mRealTotalHeight - i2) / 2;
      if (this.mRating > 10)
        this.mRating = 10;
      int m = this.mRating / 2;
      int i4 = this.mRating;
      int k = 0;
      i = 0;
      for (j = 0; k < m; j = n + i1 + j)
      {
        drawStar(paramCanvas, j, i3, localBitmap);
        k += 1;
        i += 1;
      }
      m = i;
      k = j;
      if (i4 % 2 > 0)
      {
        drawStar(paramCanvas, j, i3, Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), 2130837815), n, i2, false));
        k = j + (n + i1);
        m = i + 1;
      }
      if (m < 5)
      {
        localBitmap = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(getContext().getResources(), 2130837813), n, i2, false);
        i = k;
        while (m < 5)
        {
          drawStar(paramCanvas, i, i3, localBitmap);
          i += n + i1;
          m += 1;
          continue;
          if (this.mRating != 0)
            break label367;
          drawNoRating(paramCanvas);
        }
      }
      return;
      label367: drawTag(paramCanvas, d1, d2);
      return;
      label376: i2 = i;
    }
  }

  private void drawStar(Canvas paramCanvas, int paramInt1, int paramInt2, Bitmap paramBitmap)
  {
    paramCanvas.drawBitmap(paramBitmap, getLeftMargin() + paramInt1, getTopMargin() + paramInt2, null);
  }

  private void drawTag(Canvas paramCanvas, double paramDouble1, double paramDouble2)
  {
    RecommendItemNode localRecommendItemNode;
    Object localObject2;
    if (this.mNode != null)
      if ((this.mNode instanceof RecommendItemNode))
      {
        localRecommendItemNode = (RecommendItemNode)this.mNode;
        if ((localRecommendItemNode.mNode instanceof ActivityNode));
        for (localObject1 = "活动"; ; localObject1 = "专题")
        {
          if (localObject1 != null)
          {
            localObject2 = BitmapFactory.decodeResource(getContext().getResources(), 2130837558);
            int i = (int)(78.0D * paramDouble1);
            int j = (int)(30.0D * paramDouble2);
            localObject2 = Bitmap.createScaledBitmap((Bitmap)localObject2, i, j, false);
            j = (this.mRealTotalHeight - j) / 2;
            paramCanvas.drawBitmap((Bitmap)localObject2, getLeftMargin(), j + getTopMargin(), null);
            this.mTextPaint.setColor(SkinManager.getTextColorWhite());
            this.mTextPaint.setTextSize(this.mTextSize);
            this.mTextPaint.getTextBounds((String)localObject1, 0, ((String)localObject1).length(), this.mTextBound);
            paramCanvas.drawText((String)localObject1, (i - this.mTextBound.right + this.mTextBound.left) / 2 + getLeftMargin(), getTopMargin() + (getHeight() - this.mTextBound.top - this.mTextBound.bottom) / 2 + getHeight() / 18, this.mTextPaint);
          }
          return;
          if (!(localRecommendItemNode.mNode instanceof SpecialTopicNode))
            break;
        }
        localObject2 = "直播";
        if ((localRecommendItemNode.mNode != null) && ((localRecommendItemNode.mNode instanceof ChannelNode)))
          if (!((ChannelNode)localRecommendItemNode.mNode).nodeName.equals("channel_ondemand"))
            break label405;
      }
    label405: for (Object localObject1 = null; ; localObject1 = "直播")
    {
      break;
      localObject1 = localObject2;
      if (localRecommendItemNode.mNode == null)
        break;
      localObject1 = localObject2;
      if (!(localRecommendItemNode.mNode instanceof ProgramNode))
        break;
      localObject1 = localObject2;
      if (!((ProgramNode)localRecommendItemNode.mNode).nodeName.equals("program_ondemand"))
        break;
      localObject1 = null;
      break;
      if ((this.mNode instanceof ChannelNode))
      {
        if (this.mNode.nodeName.equals("channel_ondemand"))
        {
          localObject1 = null;
          break;
        }
        localObject1 = "直播";
        break;
      }
      localObject1 = null;
      break;
    }
  }

  public void enableShade(boolean paramBoolean)
  {
    this.mEnableShade = paramBoolean;
  }

  protected void onDrawElement(Canvas paramCanvas)
  {
    if (this.mEnableShade)
      paramCanvas.drawRect(getLeftMargin(), getTopMargin(), getRightMargin(), getBottomMargin(), this.mGradientPaint);
    drawRating(paramCanvas);
  }

  protected void onMeasureElement(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((this.mEnableShade) && (this.mGradientPaint.getShader() == null))
    {
      LinearGradient localLinearGradient = new LinearGradient(getLeftMargin(), getTopMargin(), getLeftMargin(), getBottomMargin(), 0, -872415232, Shader.TileMode.CLAMP);
      this.mGradientPaint.setShader(localLinearGradient);
    }
    this.mRealTotalWidth = (paramInt3 - paramInt1);
    this.mRealTotalHeight = (paramInt4 - paramInt2);
  }

  public void setData(Node paramNode)
  {
    this.mNode = paramNode;
  }

  public void setRating(int paramInt)
  {
    this.mRating = paramInt;
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     fm.qingting.qtradio.view.virtualchannels.RatingElement
 * JD-Core Version:    0.6.2
 */