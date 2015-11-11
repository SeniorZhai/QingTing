package weibo4android.http;

import java.util.HashMap;
import java.util.Map;

public class HTMLEntity
{
  private static Map<java.lang.String, java.lang.String> entityEscapeMap = new HashMap();
  private static Map<java.lang.String, java.lang.String> escapeEntityMap = new HashMap();

  static
  {
    java.lang.String[][] arrayOfString; = new java.lang.String['û'][];
    arrayOfString;[0] = { "&nbsp;", "&#160;", " " };
    arrayOfString;[1] = { "&iexcl;", "&#161;", "¡" };
    arrayOfString;[2] = { "&cent;", "&#162;", "¢" };
    arrayOfString;[3] = { "&pound;", "&#163;", "£" };
    arrayOfString;[4] = { "&curren;", "&#164;", "¤" };
    arrayOfString;[5] = { "&yen;", "&#165;", "¥" };
    arrayOfString;[6] = { "&brvbar;", "&#166;", "¦" };
    arrayOfString;[7] = { "&sect;", "&#167;", "§" };
    arrayOfString;[8] = { "&uml;", "&#168;", "¨" };
    arrayOfString;[9] = { "&copy;", "&#169;", "©" };
    arrayOfString;[10] = { "&ordf;", "&#170;", "ª" };
    arrayOfString;[11] = { "&laquo;", "&#171;", "«" };
    arrayOfString;[12] = { "&not;", "&#172;", "¬" };
    arrayOfString;[13] = { "&shy;", "&#173;", "­" };
    arrayOfString;[14] = { "&reg;", "&#174;", "®" };
    arrayOfString;[15] = { "&macr;", "&#175;", "¯" };
    arrayOfString;[16] = { "&deg;", "&#176;", "°" };
    arrayOfString;[17] = { "&plusmn;", "&#177;", "±" };
    arrayOfString;[18] = { "&sup2;", "&#178;", "²" };
    arrayOfString;[19] = { "&sup3;", "&#179;", "³" };
    arrayOfString;[20] = { "&acute;", "&#180;", "´" };
    arrayOfString;[21] = { "&micro;", "&#181;", "µ" };
    arrayOfString;[22] = { "&para;", "&#182;", "¶" };
    arrayOfString;[23] = { "&middot;", "&#183;", "·" };
    arrayOfString;[24] = { "&cedil;", "&#184;", "¸" };
    arrayOfString;[25] = { "&sup1;", "&#185;", "¹" };
    arrayOfString;[26] = { "&ordm;", "&#186;", "º" };
    arrayOfString;[27] = { "&raquo;", "&#187;", "»" };
    arrayOfString;[28] = { "&frac14;", "&#188;", "¼" };
    arrayOfString;[29] = { "&frac12;", "&#189;", "½" };
    arrayOfString;[30] = { "&frac34;", "&#190;", "¾" };
    arrayOfString;[31] = { "&iquest;", "&#191;", "¿" };
    arrayOfString;[32] = { "&Agrave;", "&#192;", "À" };
    arrayOfString;[33] = { "&Aacute;", "&#193;", "Á" };
    arrayOfString;[34] = { "&Acirc;", "&#194;", "Â" };
    arrayOfString;[35] = { "&Atilde;", "&#195;", "Ã" };
    arrayOfString;[36] = { "&Auml;", "&#196;", "Ä" };
    arrayOfString;[37] = { "&Aring;", "&#197;", "Å" };
    arrayOfString;[38] = { "&AElig;", "&#198;", "Æ" };
    arrayOfString;[39] = { "&Ccedil;", "&#199;", "Ç" };
    arrayOfString;[40] = { "&Egrave;", "&#200;", "È" };
    arrayOfString;[41] = { "&Eacute;", "&#201;", "É" };
    arrayOfString;[42] = { "&Ecirc;", "&#202;", "Ê" };
    arrayOfString;[43] = { "&Euml;", "&#203;", "Ë" };
    arrayOfString;[44] = { "&Igrave;", "&#204;", "Ì" };
    arrayOfString;[45] = { "&Iacute;", "&#205;", "Í" };
    arrayOfString;[46] = { "&Icirc;", "&#206;", "Î" };
    arrayOfString;[47] = { "&Iuml;", "&#207;", "Ï" };
    arrayOfString;[48] = { "&ETH;", "&#208;", "Ð" };
    arrayOfString;[49] = { "&Ntilde;", "&#209;", "Ñ" };
    arrayOfString;[50] = { "&Ograve;", "&#210;", "Ò" };
    arrayOfString;[51] = { "&Oacute;", "&#211;", "Ó" };
    arrayOfString;[52] = { "&Ocirc;", "&#212;", "Ô" };
    arrayOfString;[53] = { "&Otilde;", "&#213;", "Õ" };
    arrayOfString;[54] = { "&Ouml;", "&#214;", "Ö" };
    arrayOfString;[55] = { "&times;", "&#215;", "×" };
    arrayOfString;[56] = { "&Oslash;", "&#216;", "Ø" };
    arrayOfString;[57] = { "&Ugrave;", "&#217;", "Ù" };
    arrayOfString;[58] = { "&Uacute;", "&#218;", "Ú" };
    arrayOfString;[59] = { "&Ucirc;", "&#219;", "Û" };
    arrayOfString;[60] = { "&Uuml;", "&#220;", "Ü" };
    arrayOfString;[61] = { "&Yacute;", "&#221;", "Ý" };
    arrayOfString;[62] = { "&THORN;", "&#222;", "Þ" };
    arrayOfString;[63] = { "&szlig;", "&#223;", "ß" };
    arrayOfString;[64] = { "&agrave;", "&#224;", "à" };
    arrayOfString;[65] = { "&aacute;", "&#225;", "á" };
    arrayOfString;[66] = { "&acirc;", "&#226;", "â" };
    arrayOfString;[67] = { "&atilde;", "&#227;", "ã" };
    arrayOfString;[68] = { "&auml;", "&#228;", "ä" };
    arrayOfString;[69] = { "&aring;", "&#229;", "å" };
    arrayOfString;[70] = { "&aelig;", "&#230;", "æ" };
    arrayOfString;[71] = { "&ccedil;", "&#231;", "ç" };
    arrayOfString;[72] = { "&egrave;", "&#232;", "è" };
    arrayOfString;[73] = { "&eacute;", "&#233;", "é" };
    arrayOfString;[74] = { "&ecirc;", "&#234;", "ê" };
    arrayOfString;[75] = { "&euml;", "&#235;", "ë" };
    arrayOfString;[76] = { "&igrave;", "&#236;", "ì" };
    arrayOfString;[77] = { "&iacute;", "&#237;", "í" };
    arrayOfString;[78] = { "&icirc;", "&#238;", "î" };
    arrayOfString;[79] = { "&iuml;", "&#239;", "ï" };
    arrayOfString;[80] = { "&eth;", "&#240;", "ð" };
    arrayOfString;[81] = { "&ntilde;", "&#241;", "ñ" };
    arrayOfString;[82] = { "&ograve;", "&#242;", "ò" };
    arrayOfString;[83] = { "&oacute;", "&#243;", "ó" };
    arrayOfString;[84] = { "&ocirc;", "&#244;", "ô" };
    arrayOfString;[85] = { "&otilde;", "&#245;", "õ" };
    arrayOfString;[86] = { "&ouml;", "&#246;", "ö" };
    arrayOfString;[87] = { "&divide;", "&#247;", "÷" };
    arrayOfString;[88] = { "&oslash;", "&#248;", "ø" };
    arrayOfString;[89] = { "&ugrave;", "&#249;", "ù" };
    arrayOfString;[90] = { "&uacute;", "&#250;", "ú" };
    arrayOfString;[91] = { "&ucirc;", "&#251;", "û" };
    arrayOfString;[92] = { "&uuml;", "&#252;", "ü" };
    arrayOfString;[93] = { "&yacute;", "&#253;", "ý" };
    arrayOfString;[94] = { "&thorn;", "&#254;", "þ" };
    arrayOfString;[95] = { "&yuml;", "&#255;", "ÿ" };
    arrayOfString;[96] = { "&fnof;", "&#402;", "ƒ" };
    arrayOfString;[97] = { "&Alpha;", "&#913;", "Α" };
    arrayOfString;[98] = { "&Beta;", "&#914;", "Β" };
    arrayOfString;[99] = { "&Gamma;", "&#915;", "Γ" };
    arrayOfString;[100] = { "&Delta;", "&#916;", "Δ" };
    arrayOfString;[101] = { "&Epsilon;", "&#917;", "Ε" };
    arrayOfString;[102] = { "&Zeta;", "&#918;", "Ζ" };
    arrayOfString;[103] = { "&Eta;", "&#919;", "Η" };
    arrayOfString;[104] = { "&Theta;", "&#920;", "Θ" };
    arrayOfString;[105] = { "&Iota;", "&#921;", "Ι" };
    arrayOfString;[106] = { "&Kappa;", "&#922;", "Κ" };
    arrayOfString;[107] = { "&Lambda;", "&#923;", "Λ" };
    arrayOfString;[108] = { "&Mu;", "&#924;", "Μ" };
    arrayOfString;[109] = { "&Nu;", "&#925;", "Ν" };
    arrayOfString;[110] = { "&Xi;", "&#926;", "Ξ" };
    arrayOfString;[111] = { "&Omicron;", "&#927;", "Ο" };
    arrayOfString;[112] = { "&Pi;", "&#928;", "Π" };
    arrayOfString;[113] = { "&Rho;", "&#929;", "Ρ" };
    arrayOfString;[114] = { "&Sigma;", "&#931;", "Σ" };
    arrayOfString;[115] = { "&Tau;", "&#932;", "Τ" };
    arrayOfString;[116] = { "&Upsilon;", "&#933;", "Υ" };
    arrayOfString;[117] = { "&Phi;", "&#934;", "Φ" };
    arrayOfString;[118] = { "&Chi;", "&#935;", "Χ" };
    arrayOfString;[119] = { "&Psi;", "&#936;", "Ψ" };
    arrayOfString;[120] = { "&Omega;", "&#937;", "Ω" };
    arrayOfString;[121] = { "&alpha;", "&#945;", "α" };
    arrayOfString;[122] = { "&beta;", "&#946;", "β" };
    arrayOfString;[123] = { "&gamma;", "&#947;", "γ" };
    arrayOfString;[124] = { "&delta;", "&#948;", "δ" };
    arrayOfString;[125] = { "&epsilon;", "&#949;", "ε" };
    arrayOfString;[126] = { "&zeta;", "&#950;", "ζ" };
    arrayOfString;[127] = { "&eta;", "&#951;", "η" };
    arrayOfString;[''] = { "&theta;", "&#952;", "θ" };
    arrayOfString;[''] = { "&iota;", "&#953;", "ι" };
    arrayOfString;[''] = { "&kappa;", "&#954;", "κ" };
    arrayOfString;[''] = { "&lambda;", "&#955;", "λ" };
    arrayOfString;[''] = { "&mu;", "&#956;", "μ" };
    arrayOfString;[''] = { "&nu;", "&#957;", "ν" };
    arrayOfString;[''] = { "&xi;", "&#958;", "ξ" };
    arrayOfString;[''] = { "&omicron;", "&#959;", "ο" };
    arrayOfString;[''] = { "&pi;", "&#960;", "π" };
    arrayOfString;[''] = { "&rho;", "&#961;", "ρ" };
    arrayOfString;[''] = { "&sigmaf;", "&#962;", "ς" };
    arrayOfString;[''] = { "&sigma;", "&#963;", "σ" };
    arrayOfString;[''] = { "&tau;", "&#964;", "τ" };
    arrayOfString;[''] = { "&upsilon;", "&#965;", "υ" };
    arrayOfString;[''] = { "&phi;", "&#966;", "φ" };
    arrayOfString;[''] = { "&chi;", "&#967;", "χ" };
    arrayOfString;[''] = { "&psi;", "&#968;", "ψ" };
    arrayOfString;[''] = { "&omega;", "&#969;", "ω" };
    arrayOfString;[''] = { "&thetasym;", "&#977;", "ϑ" };
    arrayOfString;[''] = { "&upsih;", "&#978;", "ϒ" };
    arrayOfString;[''] = { "&piv;", "&#982;", "ϖ" };
    arrayOfString;[''] = { "&bull;", "&#8226;", "•" };
    arrayOfString;[''] = { "&hellip;", "&#8230;", "…" };
    arrayOfString;[''] = { "&prime;", "&#8242;", "′" };
    arrayOfString;[''] = { "&Prime;", "&#8243;", "″" };
    arrayOfString;[''] = { "&oline;", "&#8254;", "‾" };
    arrayOfString;[''] = { "&frasl;", "&#8260;", "⁄" };
    arrayOfString;[''] = { "&weierp;", "&#8472;", "℘" };
    arrayOfString;[''] = { "&image;", "&#8465;", "ℑ" };
    arrayOfString;[''] = { "&real;", "&#8476;", "ℜ" };
    arrayOfString;[''] = { "&trade;", "&#8482;", "™" };
    arrayOfString;[''] = { "&alefsym;", "&#8501;", "ℵ" };
    arrayOfString;[' '] = { "&larr;", "&#8592;", "←" };
    arrayOfString;['¡'] = { "&uarr;", "&#8593;", "↑" };
    arrayOfString;['¢'] = { "&rarr;", "&#8594;", "→" };
    arrayOfString;['£'] = { "&darr;", "&#8595;", "↓" };
    arrayOfString;['¤'] = { "&harr;", "&#8596;", "↔" };
    arrayOfString;['¥'] = { "&crarr;", "&#8629;", "↵" };
    arrayOfString;['¦'] = { "&lArr;", "&#8656;", "⇐" };
    arrayOfString;['§'] = { "&uArr;", "&#8657;", "⇑" };
    arrayOfString;['¨'] = { "&rArr;", "&#8658;", "⇒" };
    arrayOfString;['©'] = { "&dArr;", "&#8659;", "⇓" };
    arrayOfString;['ª'] = { "&hArr;", "&#8660;", "⇔" };
    arrayOfString;['«'] = { "&forall;", "&#8704;", "∀" };
    arrayOfString;['¬'] = { "&part;", "&#8706;", "∂" };
    arrayOfString;['­'] = { "&exist;", "&#8707;", "∃" };
    arrayOfString;['®'] = { "&empty;", "&#8709;", "∅" };
    arrayOfString;['¯'] = { "&nabla;", "&#8711;", "∇" };
    arrayOfString;['°'] = { "&isin;", "&#8712;", "∈" };
    arrayOfString;['±'] = { "&notin;", "&#8713;", "∉" };
    arrayOfString;['²'] = { "&ni;", "&#8715;", "∋" };
    arrayOfString;['³'] = { "&prod;", "&#8719;", "∏" };
    arrayOfString;['´'] = { "&sum;", "&#8721;", "∑" };
    arrayOfString;['µ'] = { "&minus;", "&#8722;", "−" };
    arrayOfString;['¶'] = { "&lowast;", "&#8727;", "∗" };
    arrayOfString;['·'] = { "&radic;", "&#8730;", "√" };
    arrayOfString;['¸'] = { "&prop;", "&#8733;", "∝" };
    arrayOfString;['¹'] = { "&infin;", "&#8734;", "∞" };
    arrayOfString;['º'] = { "&ang;", "&#8736;", "∠" };
    arrayOfString;['»'] = { "&and;", "&#8743;", "∧" };
    arrayOfString;['¼'] = { "&or;", "&#8744;", "∨" };
    arrayOfString;['½'] = { "&cap;", "&#8745;", "∩" };
    arrayOfString;['¾'] = { "&cup;", "&#8746;", "∪" };
    arrayOfString;['¿'] = { "&int;", "&#8747;", "∫" };
    arrayOfString;['À'] = { "&there4;", "&#8756;", "∴" };
    arrayOfString;['Á'] = { "&sim;", "&#8764;", "∼" };
    arrayOfString;['Â'] = { "&cong;", "&#8773;", "≅" };
    arrayOfString;['Ã'] = { "&asymp;", "&#8776;", "≈" };
    arrayOfString;['Ä'] = { "&ne;", "&#8800;", "≠" };
    arrayOfString;['Å'] = { "&equiv;", "&#8801;", "≡" };
    arrayOfString;['Æ'] = { "&le;", "&#8804;", "≤" };
    arrayOfString;['Ç'] = { "&ge;", "&#8805;", "≥" };
    arrayOfString;['È'] = { "&sub;", "&#8834;", "⊂" };
    arrayOfString;['É'] = { "&sup;", "&#8835;", "⊃" };
    arrayOfString;['Ê'] = { "&sube;", "&#8838;", "⊆" };
    arrayOfString;['Ë'] = { "&supe;", "&#8839;", "⊇" };
    arrayOfString;['Ì'] = { "&oplus;", "&#8853;", "⊕" };
    arrayOfString;['Í'] = { "&otimes;", "&#8855;", "⊗" };
    arrayOfString;['Î'] = { "&perp;", "&#8869;", "⊥" };
    arrayOfString;['Ï'] = { "&sdot;", "&#8901;", "⋅" };
    arrayOfString;['Ð'] = { "&lceil;", "&#8968;", "⌈" };
    arrayOfString;['Ñ'] = { "&rceil;", "&#8969;", "⌉" };
    arrayOfString;['Ò'] = { "&lfloor;", "&#8970;", "⌊" };
    arrayOfString;['Ó'] = { "&rfloor;", "&#8971;", "⌋" };
    arrayOfString;['Ô'] = { "&lang;", "&#9001;", "〈" };
    arrayOfString;['Õ'] = { "&rang;", "&#9002;", "〉" };
    arrayOfString;['Ö'] = { "&loz;", "&#9674;", "◊" };
    arrayOfString;['×'] = { "&spades;", "&#9824;", "♠" };
    arrayOfString;['Ø'] = { "&clubs;", "&#9827;", "♣" };
    arrayOfString;['Ù'] = { "&hearts;", "&#9829;", "♥" };
    arrayOfString;['Ú'] = { "&diams;", "&#9830;", "♦" };
    arrayOfString;['Û'] = { "&quot;", "&#34;", "\"" };
    arrayOfString;['Ü'] = { "&amp;", "&#38;", "&" };
    arrayOfString;['Ý'] = { "&lt;", "&#60;", "<" };
    arrayOfString;['Þ'] = { "&gt;", "&#62;", ">" };
    arrayOfString;['ß'] = { "&OElig;", "&#338;", "Œ" };
    arrayOfString;['à'] = { "&oelig;", "&#339;", "œ" };
    arrayOfString;['á'] = { "&Scaron;", "&#352;", "Š" };
    arrayOfString;['â'] = { "&scaron;", "&#353;", "š" };
    arrayOfString;['ã'] = { "&Yuml;", "&#376;", "Ÿ" };
    arrayOfString;['ä'] = { "&circ;", "&#710;", "ˆ" };
    arrayOfString;['å'] = { "&tilde;", "&#732;", "˜" };
    arrayOfString;['æ'] = { "&ensp;", "&#8194;", " " };
    arrayOfString;['ç'] = { "&emsp;", "&#8195;", " " };
    arrayOfString;['è'] = { "&thinsp;", "&#8201;", " " };
    arrayOfString;['é'] = { "&zwnj;", "&#8204;", "‌" };
    arrayOfString;['ê'] = { "&zwj;", "&#8205;", "‍" };
    arrayOfString;['ë'] = { "&lrm;", "&#8206;", "‎" };
    arrayOfString;['ì'] = { "&rlm;", "&#8207;", "‏" };
    arrayOfString;['í'] = { "&ndash;", "&#8211;", "–" };
    arrayOfString;['î'] = { "&mdash;", "&#8212;", "—" };
    arrayOfString;['ï'] = { "&lsquo;", "&#8216;", "‘" };
    arrayOfString;['ð'] = { "&rsquo;", "&#8217;", "’" };
    arrayOfString;['ñ'] = { "&sbquo;", "&#8218;", "‚" };
    arrayOfString;['ò'] = { "&ldquo;", "&#8220;", "“" };
    arrayOfString;['ó'] = { "&rdquo;", "&#8221;", "”" };
    arrayOfString;['ô'] = { "&bdquo;", "&#8222;", "„" };
    arrayOfString;['õ'] = { "&dagger;", "&#8224;", "†" };
    arrayOfString;['ö'] = { "&Dagger;", "&#8225;", "‡" };
    arrayOfString;['÷'] = { "&permil;", "&#8240;", "‰" };
    arrayOfString;['ø'] = { "&lsaquo;", "&#8249;", "‹" };
    arrayOfString;['ù'] = { "&rsaquo;", "&#8250;", "›" };
    arrayOfString;['ú'] = { "&euro;", "&#8364;", "€" };
    int j = arrayOfString;.length;
    int i = 0;
    while (i < j)
    {
      [Ljava.lang.String localString; = arrayOfString;[i];
      entityEscapeMap.put(localString;[2], localString;[0]);
      escapeEntityMap.put(localString;[0], localString;[2]);
      escapeEntityMap.put(localString;[1], localString;[2]);
      i += 1;
    }
  }

  public static java.lang.String escape(java.lang.String paramString)
  {
    paramString = new StringBuffer(paramString);
    escape(paramString);
    return paramString.toString();
  }

  public static void escape(StringBuffer paramStringBuffer)
  {
    int i = 0;
    while (i < paramStringBuffer.length())
    {
      java.lang.String str = (java.lang.String)entityEscapeMap.get(paramStringBuffer.substring(i, i + 1));
      if (str != null)
      {
        paramStringBuffer.replace(i, i + 1, str);
        i = str.length() + i;
      }
      else
      {
        i += 1;
      }
    }
  }

  public static java.lang.String unescape(java.lang.String paramString)
  {
    paramString = new StringBuffer(paramString);
    unescape(paramString);
    return paramString.toString();
  }

  public static void unescape(StringBuffer paramStringBuffer)
  {
    int i = 0;
    while (true)
    {
      if (i < paramStringBuffer.length())
      {
        i = paramStringBuffer.indexOf("&", i);
        if (-1 != i)
          break label25;
      }
      label25: int j;
      do
      {
        return;
        j = paramStringBuffer.indexOf(";", i);
      }
      while ((-1 == j) || (10 <= j - i));
      java.lang.String str = paramStringBuffer.substring(i, j + 1);
      str = (java.lang.String)escapeEntityMap.get(str);
      if (str != null)
        paramStringBuffer.replace(i, j + 1, str);
      i += 1;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     weibo4android.http.HTMLEntity
 * JD-Core Version:    0.6.2
 */