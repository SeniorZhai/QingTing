package master.flame.danmaku.danmaku.parser.android;

import android.text.TextUtils;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Locale;
import master.flame.danmaku.danmaku.model.AlphaValue;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.Duration;
import master.flame.danmaku.danmaku.model.IDisplayer;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.danmaku.parser.DanmakuFactory;
import org.json.JSONArray;
import org.json.JSONException;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class BiliDanmukuParser extends BaseDanmakuParser
{
  private float mDispScaleX;
  private float mDispScaleY;

  static
  {
    System.setProperty("org.xml.sax.driver", "org.xmlpull.v1.sax2.Driver");
  }

  public Danmakus parse()
  {
    Object localObject;
    if (this.mDataSource != null)
      localObject = (AndroidFileSource)this.mDataSource;
    try
    {
      XMLReader localXMLReader = XMLReaderFactory.createXMLReader();
      XmlContentHandler localXmlContentHandler = new XmlContentHandler();
      localXMLReader.setContentHandler(localXmlContentHandler);
      localXMLReader.parse(new InputSource(((AndroidFileSource)localObject).data()));
      localObject = localXmlContentHandler.getResult();
      return localObject;
    }
    catch (SAXException localSAXException)
    {
      localSAXException.printStackTrace();
      return null;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public BaseDanmakuParser setDisplayer(IDisplayer paramIDisplayer)
  {
    super.setDisplayer(paramIDisplayer);
    this.mDispScaleX = (this.mDispWidth / 682.0F);
    this.mDispScaleY = (this.mDispHeight / 438.0F);
    return this;
  }

  public class XmlContentHandler extends DefaultHandler
  {
    private static final String TRUE_STRING = "true";
    public boolean completed = false;
    public int index = 0;
    public BaseDanmaku item = null;
    public Danmakus result = null;

    public XmlContentHandler()
    {
    }

    private String decodeXmlString(String paramString)
    {
      String str = paramString;
      if (paramString.contains("&amp;"))
        str = paramString.replace("&amp;", "&");
      paramString = str;
      if (str.contains("&quot;"))
        paramString = str.replace("&quot;", "\"");
      str = paramString;
      if (paramString.contains("&gt;"))
        str = paramString.replace("&gt;", ">");
      paramString = str;
      if (str.contains("&lt;"))
        paramString = str.replace("&lt;", "<");
      return paramString;
    }

    public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    {
      Object localObject2;
      String[] arrayOfString;
      if (this.item != null)
      {
        DanmakuFactory.fillText(this.item, decodeXmlString(new String(paramArrayOfChar, paramInt1, paramInt2)));
        paramArrayOfChar = this.item;
        paramInt1 = this.index;
        this.index = (paramInt1 + 1);
        paramArrayOfChar.index = paramInt1;
        localObject2 = this.item.text.trim();
        if ((this.item.getType() == 7) && (((String)localObject2).startsWith("[")) && (((String)localObject2).endsWith("]")))
        {
          arrayOfString = null;
          paramArrayOfChar = arrayOfString;
        }
      }
      float f5;
      float f6;
      Object localObject1;
      try
      {
        localObject2 = new JSONArray((String)localObject2);
        paramArrayOfChar = arrayOfString;
        arrayOfString = new String[((JSONArray)localObject2).length()];
        paramInt1 = 0;
        while (true)
        {
          paramArrayOfChar = arrayOfString;
          if (paramInt1 >= arrayOfString.length)
            break;
          paramArrayOfChar = arrayOfString;
          arrayOfString[paramInt1] = ((JSONArray)localObject2).getString(paramInt1);
          paramInt1 += 1;
        }
        paramArrayOfChar = arrayOfString;
        if ((paramArrayOfChar == null) || (paramArrayOfChar.length < 5))
        {
          this.item = null;
          return;
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
          localJSONException.printStackTrace();
        this.item.text = paramArrayOfChar[4];
        f5 = Float.parseFloat(paramArrayOfChar[0]);
        f6 = Float.parseFloat(paramArrayOfChar[1]);
        localObject1 = paramArrayOfChar[2].split("-");
        paramInt2 = (int)(AlphaValue.MAX * Float.parseFloat(localObject1[0]));
        if (localObject1.length <= 1)
          break label673;
      }
      float f1 = AlphaValue.MAX;
      label673: for (paramInt1 = (int)(Float.parseFloat(localObject1[1]) * f1); ; paramInt1 = paramInt2)
      {
        long l2 = ()(Float.parseFloat(paramArrayOfChar[3]) * 1000.0F);
        long l3 = 0L;
        float f3 = 0.0F;
        if (paramArrayOfChar.length >= 7)
          f3 = Float.parseFloat(paramArrayOfChar[5]);
        for (float f4 = Float.parseFloat(paramArrayOfChar[6]); ; f4 = 0.0F)
        {
          float f2;
          long l1;
          if (paramArrayOfChar.length >= 11)
          {
            f1 = Float.parseFloat(paramArrayOfChar[7]);
            f2 = Float.parseFloat(paramArrayOfChar[8]);
            if (!"".equals(paramArrayOfChar[9]))
            {
              l1 = Integer.parseInt(paramArrayOfChar[9]);
              label349: if (!"".equals(paramArrayOfChar[10]))
                l3 = ()Float.parseFloat(paramArrayOfChar[10]);
            }
          }
          while (true)
          {
            this.item.duration = new Duration(l2);
            this.item.rotationZ = f3;
            this.item.rotationY = f4;
            DanmakuFactory.fillTranslationData(this.item, f5, f6, f1, f2, l1, l3, BiliDanmukuParser.this.mDispScaleX, BiliDanmukuParser.this.mDispScaleY);
            DanmakuFactory.fillAlphaData(this.item, paramInt2, paramInt1, l2);
            if ((paramArrayOfChar.length >= 12) && (!TextUtils.isEmpty(paramArrayOfChar[11])) && ("true".equals(paramArrayOfChar[11])))
              this.item.textShadowColor = 0;
            if ((paramArrayOfChar.length >= 13) && ((paramArrayOfChar.length >= 14) && ((paramArrayOfChar.length < 15) || ("".equals(paramArrayOfChar[14])))))
              break;
            paramArrayOfChar = paramArrayOfChar[14].substring(1).split("L");
            if ((paramArrayOfChar == null) || (paramArrayOfChar.length <= 0))
              break;
            paramInt1 = paramArrayOfChar.length;
            localObject1 = (float[][])Array.newInstance(Float.TYPE, new int[] { paramInt1, 2 });
            paramInt1 = 0;
            while (paramInt1 < paramArrayOfChar.length)
            {
              localObject2 = paramArrayOfChar[paramInt1].split(",");
              localObject1[paramInt1][0] = Float.parseFloat(localObject2[0]);
              localObject1[paramInt1][1] = Float.parseFloat(localObject2[1]);
              paramInt1 += 1;
            }
            DanmakuFactory.fillLinePathData(this.item, (float[][])localObject1, BiliDanmukuParser.this.mDispScaleX, BiliDanmukuParser.this.mDispScaleY);
            return;
            continue;
            l1 = l2;
            break label349;
            l1 = l2;
            f2 = f6;
            f1 = f5;
          }
        }
      }
    }

    public void endDocument()
      throws SAXException
    {
      this.completed = true;
    }

    public void endElement(String paramString1, String paramString2, String paramString3)
      throws SAXException
    {
      if (this.item != null)
        if (this.item.duration != null)
          if (paramString2.length() == 0)
            break label65;
      while (true)
      {
        if (paramString2.equalsIgnoreCase("d"))
        {
          this.item.setTimer(BiliDanmukuParser.this.mTimer);
          this.result.addItem(this.item);
        }
        this.item = null;
        return;
        label65: paramString2 = paramString3;
      }
    }

    public Danmakus getResult()
    {
      return this.result;
    }

    public void startDocument()
      throws SAXException
    {
      this.result = new Danmakus();
    }

    public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
      throws SAXException
    {
      int i = -16777216;
      if (paramString2.length() != 0);
      while (true)
      {
        if (paramString2.toLowerCase(Locale.getDefault()).trim().equals("d"))
        {
          paramString1 = paramAttributes.getValue("p").split(",");
          if (paramString1.length > 0)
          {
            long l = ()(Float.parseFloat(paramString1[0]) * 1000.0F);
            int k = Integer.parseInt(paramString1[1]);
            float f = Float.parseFloat(paramString1[2]);
            int j = Integer.parseInt(paramString1[3]);
            this.item = DanmakuFactory.createDanmaku(k, BiliDanmukuParser.this.mDisp);
            if (this.item != null)
            {
              this.item.time = l;
              this.item.textSize = ((BiliDanmukuParser.this.mDispDensity - 0.6F) * f);
              this.item.textColor = -11908534;
              paramString1 = this.item;
              if ((j | 0xFF000000) <= -16777216)
                i = -1;
              paramString1.textShadowColor = i;
            }
          }
        }
        return;
        paramString2 = paramString3;
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     master.flame.danmaku.danmaku.parser.android.BiliDanmukuParser
 * JD-Core Version:    0.6.2
 */