package org.json;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Iterator;

public class Test
{
  public static void main(String[] paramArrayOfString)
  {
    paramArrayOfString = new JSONString()
    {
      public boolean aBoolean;
      public double aNumber;

      public String getBENT()
      {
        return "All uppercase key";
      }

      public double getNumber()
      {
        return this.aNumber;
      }

      public String getString()
      {
        return Test.this;
      }

      public String getX()
      {
        return "x";
      }

      public boolean isBoolean()
      {
        return this.aBoolean;
      }

      public String toJSONString()
      {
        return "{" + JSONObject.quote(Test.this) + ":" + JSONObject.doubleToString(this.aNumber) + "}";
      }

      public String toString()
      {
        return getString() + " " + getNumber() + " " + isBoolean() + "." + getBENT() + " " + getX();
      }
    };
    try
    {
      localObject = XML.toJSONObject("<![CDATA[This is a collection of test patterns and examples for org.json.]]>  Ignore the stuff past the end.  ");
      System.out.println(((JSONObject)localObject).toString());
      localObject = new JSONObject(paramArrayOfString);
      System.out.println(((JSONObject)localObject).toString());
      localObject = new JSONStringer().object().key("foo").value("bar").key("baz").array().object().key("quux").value("Thanks, Josh!").endObject().endArray().key("obj keys").value(JSONObject.getNames(paramArrayOfString)).endObject().toString();
      System.out.println((String)localObject);
      System.out.println(new JSONStringer().object().key("a").array().array().array().value("b").endArray().endArray().endArray().endObject().toString());
      localObject = new JSONStringer();
      ((JSONStringer)localObject).array();
      ((JSONStringer)localObject).value(1L);
      ((JSONStringer)localObject).array();
      ((JSONStringer)localObject).value(null);
      ((JSONStringer)localObject).array();
      ((JSONStringer)localObject).object();
      ((JSONStringer)localObject).key("empty-array").array().endArray();
      ((JSONStringer)localObject).key("answer").value(42L);
      ((JSONStringer)localObject).key("null").value(null);
      ((JSONStringer)localObject).key("false").value(false);
      ((JSONStringer)localObject).key("true").value(true);
      ((JSONStringer)localObject).key("big").value(1.23456789E+096D);
      ((JSONStringer)localObject).key("small").value(1.23456789E-080D);
      ((JSONStringer)localObject).key("empty-object").object().endObject();
      ((JSONStringer)localObject).key("long");
      ((JSONStringer)localObject).value(9223372036854775807L);
      ((JSONStringer)localObject).endObject();
      ((JSONStringer)localObject).value("two");
      ((JSONStringer)localObject).endArray();
      ((JSONStringer)localObject).value(true);
      ((JSONStringer)localObject).endArray();
      ((JSONStringer)localObject).value(98.599999999999994D);
      ((JSONStringer)localObject).value(-100.0D);
      ((JSONStringer)localObject).object();
      ((JSONStringer)localObject).endObject();
      ((JSONStringer)localObject).object();
      ((JSONStringer)localObject).key("one");
      ((JSONStringer)localObject).value(1.0D);
      ((JSONStringer)localObject).endObject();
      ((JSONStringer)localObject).value(paramArrayOfString);
      ((JSONStringer)localObject).endArray();
      System.out.println(((JSONStringer)localObject).toString());
      System.out.println(new JSONArray(((JSONStringer)localObject).toString()).toString(4));
      localObject = new JSONArray(new int[] { 1, 2, 3 });
      System.out.println(((JSONArray)localObject).toString());
      localObject = new JSONObject(paramArrayOfString, new String[] { "aString", "aNumber", "aBoolean" });
      ((JSONObject)localObject).put("Testing JSONString interface", paramArrayOfString);
      System.out.println(((JSONObject)localObject).toString(4));
      paramArrayOfString = new JSONObject("{slashes: '///', closetag: '</script>', backslash:'\\\\', ei: {quotes: '\"\\''},eo: {a: '\"quoted\"', b:\"don't\"}, quotes: [\"'\", '\"']}");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = new JSONObject("/*comment*/{foo: [true, false,9876543210,    0.0, 1.00000001,  1.000000000001, 1.00000000000000001, .00000000000000001, 2.00, 0.1, 2e100, -32,[],{}, \"string\"],   to   : null, op : 'Good',ten:10} postfix comment");
      paramArrayOfString.put("String", "98.6");
      paramArrayOfString.put("JSONObject", new JSONObject());
      paramArrayOfString.put("JSONArray", new JSONArray());
      paramArrayOfString.put("int", 57);
      paramArrayOfString.put("double", 1.234567890123457E+029D);
      paramArrayOfString.put("true", true);
      paramArrayOfString.put("false", false);
      paramArrayOfString.put("null", JSONObject.NULL);
      paramArrayOfString.put("bool", "true");
      paramArrayOfString.put("zero", 0.0D);
      paramArrayOfString.put("\\u2028", " ");
      paramArrayOfString.put("\\u2029", " ");
      localObject = paramArrayOfString.getJSONArray("foo");
      ((JSONArray)localObject).put(666);
      ((JSONArray)localObject).put(2001.99D);
      ((JSONArray)localObject).put("so \"fine\".");
      ((JSONArray)localObject).put("so <fine>.");
      ((JSONArray)localObject).put(true);
      ((JSONArray)localObject).put(false);
      ((JSONArray)localObject).put(new JSONArray());
      ((JSONArray)localObject).put(new JSONObject());
      paramArrayOfString.put("keys", JSONObject.getNames(paramArrayOfString));
      System.out.println(paramArrayOfString.toString(4));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("String: " + paramArrayOfString.getDouble("String"));
      System.out.println("  bool: " + paramArrayOfString.getBoolean("bool"));
      System.out.println("    to: " + paramArrayOfString.getString("to"));
      System.out.println("  true: " + paramArrayOfString.getString("true"));
      System.out.println("   foo: " + paramArrayOfString.getJSONArray("foo"));
      System.out.println("    op: " + paramArrayOfString.getString("op"));
      System.out.println("   ten: " + paramArrayOfString.getInt("ten"));
      System.out.println("  oops: " + paramArrayOfString.optBoolean("oops"));
      paramArrayOfString = XML.toJSONObject("<xml one = 1 two=' \"2\" '><five></five>First \t&lt;content&gt;<five></five> This is \"content\". <three>  3  </three>JSON does not preserve the sequencing of elements and contents.<three>  III  </three>  <three>  T H R E E</three><four/>Content text is an implied structure in XML. <six content=\"6\"/>JSON does not have implied structure:<seven>7</seven>everything is explicit.<![CDATA[CDATA blocks<are><supported>!]]></xml>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<mapping><empty/>   <class name = \"Customer\">      <field name = \"ID\" type = \"string\">         <bind-xml name=\"ID\" node=\"attribute\"/>      </field>      <field name = \"FirstName\" type = \"FirstName\"/>      <field name = \"MI\" type = \"MI\"/>      <field name = \"LastName\" type = \"LastName\"/>   </class>   <class name = \"FirstName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"MI\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class>   <class name = \"LastName\">      <field name = \"text\">         <bind-xml name = \"text\" node = \"text\"/>      </field>   </class></mapping>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<?xml version=\"1.0\" ?><Book Author=\"Anonymous\"><Title>Sample Book</Title><Chapter id=\"1\">This is chapter 1. It is not very long or interesting.</Chapter><Chapter id=\"2\">This is chapter 2. Although it is longer than chapter 1, it is not any more interesting.</Chapter></Book>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<!DOCTYPE bCard 'http://www.cs.caltech.edu/~adam/schemas/bCard'><bCard><?xml default bCard        firstname = ''        lastname  = '' company   = '' email = '' homepage  = ''?><bCard        firstname = 'Rohit'        lastname  = 'Khare'        company   = 'MCI'        email     = 'khare@mci.net'        homepage  = 'http://pest.w3.org/'/><bCard        firstname = 'Adam'        lastname  = 'Rifkin'        company   = 'Caltech Infospheres Project'        email     = 'adam@cs.caltech.edu'        homepage  = 'http://www.cs.caltech.edu/~adam/'/></bCard>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<?xml version=\"1.0\"?><customer>    <firstName>        <text>Fred</text>    </firstName>    <ID>fbs0001</ID>    <lastName> <text>Scerbo</text>    </lastName>    <MI>        <text>B</text>    </MI></customer>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<!ENTITY tp-address PUBLIC '-//ABC University::Special Collections Library//TEXT (titlepage: name and address)//EN' 'tpspcoll.sgm'><list type='simple'><head>Repository Address </head><item>Special Collections Library</item><item>ABC University</item><item>Main Library, 40 Circle Drive</item><item>Ourtown, Pennsylvania</item><item>17654 USA</item></list>");
      System.out.println(paramArrayOfString.toString());
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<test intertag status=ok><empty/>deluxe<blip sweet=true>&amp;&quot;toot&quot;&toot;&#x41;</blip><x>eks</x><w>bonus</w><w>bonus2</w></test>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = HTTP.toJSONObject("GET / HTTP/1.0\nAccept: image/gif, image/x-xbitmap, image/jpeg, image/pjpeg, application/vnd.ms-powerpoint, application/vnd.ms-excel, application/msword, */*\nAccept-Language: en-us\nUser-Agent: Mozilla/4.0 (compatible; MSIE 5.5; Windows 98; Win 9x 4.90; T312461; Q312461)\nHost: www.nokko.com\nConnection: keep-alive\nAccept-encoding: gzip, deflate\n");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(HTTP.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = HTTP.toJSONObject("HTTP/1.1 200 Oki Doki\nDate: Sun, 26 May 2002 17:38:52 GMT\nServer: Apache/1.3.23 (Unix) mod_perl/1.26\nKeep-Alive: timeout=15, max=100\nConnection: Keep-Alive\nTransfer-Encoding: chunked\nContent-Type: text/html\n");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(HTTP.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = new JSONObject("{nix: null, nux: false, null: 'null', 'Request-URI': '/', Method: 'GET', 'HTTP-Version': 'HTTP/1.0'}");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println("isNull: " + paramArrayOfString.isNull("nix"));
      System.out.println("   has: " + paramArrayOfString.has("nix"));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println(HTTP.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = XML.toJSONObject("<?xml version='1.0' encoding='UTF-8'?>\n\n<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/1999/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/1999/XMLSchema\"><SOAP-ENV:Body><ns1:doGoogleSearch xmlns:ns1=\"urn:GoogleSearch\" SOAP-ENV:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\"><key xsi:type=\"xsd:string\">GOOGLEKEY</key> <q xsi:type=\"xsd:string\">'+search+'</q> <start xsi:type=\"xsd:int\">0</start> <maxResults xsi:type=\"xsd:int\">10</maxResults> <filter xsi:type=\"xsd:boolean\">true</filter> <restrict xsi:type=\"xsd:string\"></restrict> <safeSearch xsi:type=\"xsd:boolean\">false</safeSearch> <lr xsi:type=\"xsd:string\"></lr> <ie xsi:type=\"xsd:string\">latin1</ie> <oe xsi:type=\"xsd:string\">latin1</oe></ns1:doGoogleSearch></SOAP-ENV:Body></SOAP-ENV:Envelope>");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = new JSONObject("{Envelope: {Body: {\"ns1:doGoogleSearch\": {oe: \"latin1\", filter: true, q: \"'+search+'\", key: \"GOOGLEKEY\", maxResults: 10, \"SOAP-ENV:encodingStyle\": \"http://schemas.xmlsoap.org/soap/encoding/\", start: 0, ie: \"latin1\", safeSearch:false, \"xmlns:ns1\": \"urn:GoogleSearch\"}}}}");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = CookieList.toJSONObject("  f%oo = b+l=ah  ; o;n%40e = t.wo ");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(CookieList.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = Cookie.toJSONObject("f%oo=blah; secure ;expires = April 24, 2002");
      System.out.println(paramArrayOfString.toString(2));
      System.out.println(Cookie.toString(paramArrayOfString));
      System.out.println("");
      paramArrayOfString = new JSONObject("{script: 'It is not allowed in HTML to send a close script tag in a string<script>because it confuses browsers</script>so we insert a backslash before the /'}");
      System.out.println(paramArrayOfString.toString());
      System.out.println("");
      paramArrayOfString = new JSONTokener("{op:'test', to:'session', pre:1}{op:'test', to:'session', pre:2}");
      localObject = new JSONObject(paramArrayOfString);
      System.out.println(((JSONObject)localObject).toString());
      System.out.println("pre: " + ((JSONObject)localObject).optInt("pre"));
      int i = paramArrayOfString.skipTo('{');
      System.out.println(i);
      paramArrayOfString = new JSONObject(paramArrayOfString);
      System.out.println(paramArrayOfString.toString());
      System.out.println("");
      paramArrayOfString = CDL.toJSONArray("No quotes, 'Single Quotes', \"Double Quotes\"\n1,'2',\"3\"\n,'It is \"good,\"', \"It works.\"\n\n");
      System.out.println(CDL.toString(paramArrayOfString));
      System.out.println("");
      System.out.println(paramArrayOfString.toString(4));
      System.out.println("");
      paramArrayOfString = new JSONArray(" [\"<escape>\", next is an implied null , , ok,] ");
      System.out.println(paramArrayOfString.toString());
      System.out.println("");
      System.out.println(XML.toString(paramArrayOfString));
      System.out.println("");
      localObject = new JSONObject("{ fun => with non-standard forms ; forgiving => This package can be used to parse formats that are similar to but not stricting conforming to JSON; why=To make it easier to migrate existing data to JSON,one = [[1.00]]; uno=[[{1=>1}]];'+':+6e66 ;pluses=+++;empty = '' , 'double':0.666,true: TRUE, false: FALSE, null=NULL;[true] = [[!,@;*]]; string=>  o. k. ; # comment\r oct=0666; hex=0x666; dec=666; o=0999; noh=0x0x}");
      System.out.println(((JSONObject)localObject).toString(4));
      System.out.println("");
      if ((((JSONObject)localObject).getBoolean("true")) && (!((JSONObject)localObject).getBoolean("false")))
        System.out.println("It's all good");
      System.out.println("");
      localObject = new JSONObject((JSONObject)localObject, new String[] { "dec", "oct", "hex", "missing" });
      System.out.println(((JSONObject)localObject).toString(4));
      System.out.println("");
      System.out.println(new JSONStringer().array().value(paramArrayOfString).value(localObject).endArray());
      paramArrayOfString = new JSONObject("{string: \"98.6\", long: 2147483648, int: 2147483647, longer: 9223372036854775807, double: 9223372036854775808}");
      System.out.println(paramArrayOfString.toString(4));
      System.out.println("\ngetInt");
      System.out.println("int    " + paramArrayOfString.getInt("int"));
      System.out.println("long   " + paramArrayOfString.getInt("long"));
      System.out.println("longer " + paramArrayOfString.getInt("longer"));
      System.out.println("double " + paramArrayOfString.getInt("double"));
      System.out.println("string " + paramArrayOfString.getInt("string"));
      System.out.println("\ngetLong");
      System.out.println("int    " + paramArrayOfString.getLong("int"));
      System.out.println("long   " + paramArrayOfString.getLong("long"));
      System.out.println("longer " + paramArrayOfString.getLong("longer"));
      System.out.println("double " + paramArrayOfString.getLong("double"));
      System.out.println("string " + paramArrayOfString.getLong("string"));
      System.out.println("\ngetDouble");
      System.out.println("int    " + paramArrayOfString.getDouble("int"));
      System.out.println("long   " + paramArrayOfString.getDouble("long"));
      System.out.println("longer " + paramArrayOfString.getDouble("longer"));
      System.out.println("double " + paramArrayOfString.getDouble("double"));
      System.out.println("string " + paramArrayOfString.getDouble("string"));
      paramArrayOfString.put("good sized", 9223372036854775807L);
      System.out.println(paramArrayOfString.toString(4));
      localObject = new JSONArray("[2147483647, 2147483648, 9223372036854775807, 9223372036854775808]");
      System.out.println(((JSONArray)localObject).toString(4));
      System.out.println("\nKeys: ");
      localObject = paramArrayOfString.keys();
      while (((Iterator)localObject).hasNext())
      {
        String str = (String)((Iterator)localObject).next();
        System.out.println(str + ": " + paramArrayOfString.getString(str));
      }
    }
    catch (Exception paramArrayOfString)
    {
      System.out.println(paramArrayOfString.toString());
      return;
    }
    System.out.println("\naccumulate: ");
    paramArrayOfString = new JSONObject();
    paramArrayOfString.accumulate("stooge", "Curly");
    paramArrayOfString.accumulate("stooge", "Larry");
    paramArrayOfString.accumulate("stooge", "Moe");
    paramArrayOfString.getJSONArray("stooge").put(5, "Shemp");
    System.out.println(paramArrayOfString.toString(4));
    System.out.println("\nwrite:");
    System.out.println(paramArrayOfString.write(new StringWriter()));
    paramArrayOfString = XML.toJSONObject("<xml empty><a></a><a>1</a><a>22</a><a>333</a></xml>");
    System.out.println(paramArrayOfString.toString(4));
    System.out.println(XML.toString(paramArrayOfString));
    paramArrayOfString = XML.toJSONObject("<book><chapter>Content of the first chapter</chapter><chapter>Content of the second chapter      <chapter>Content of the first subchapter</chapter>      <chapter>Content of the second subchapter</chapter></chapter><chapter>Third Chapter</chapter></book>");
    System.out.println(paramArrayOfString.toString(4));
    System.out.println(XML.toString(paramArrayOfString));
    Object localObject = new JSONObject(null);
    paramArrayOfString = new JSONArray(null);
    ((JSONObject)localObject).append("stooge", "Joe DeRita");
    ((JSONObject)localObject).append("stooge", "Shemp");
    ((JSONObject)localObject).accumulate("stooges", "Curly");
    ((JSONObject)localObject).accumulate("stooges", "Larry");
    ((JSONObject)localObject).accumulate("stooges", "Moe");
    ((JSONObject)localObject).accumulate("stoogearray", ((JSONObject)localObject).get("stooges"));
    ((JSONObject)localObject).put("map", null);
    ((JSONObject)localObject).put("collection", null);
    ((JSONObject)localObject).put("array", paramArrayOfString);
    paramArrayOfString.put(null);
    paramArrayOfString.put(null);
    System.out.println(((JSONObject)localObject).toString(4));
    System.out.println("\nTesting Exceptions: ");
    System.out.print("Exception: ");
    try
    {
      System.out.println(((JSONObject)localObject).getDouble("stooge"));
      System.out.print("Exception: ");
    }
    catch (Exception paramArrayOfString)
    {
      try
      {
        System.out.println(((JSONObject)localObject).getDouble("howard"));
        System.out.print("Exception: ");
      }
      catch (Exception paramArrayOfString)
      {
        try
        {
          System.out.println(((JSONObject)localObject).put(null, "howard"));
          System.out.print("Exception: ");
        }
        catch (Exception paramArrayOfString)
        {
          try
          {
            System.out.println(paramArrayOfString.getDouble(0));
            System.out.print("Exception: ");
          }
          catch (Exception paramArrayOfString)
          {
            try
            {
              System.out.println(paramArrayOfString.get(-1));
              System.out.print("Exception: ");
            }
            catch (Exception paramArrayOfString)
            {
              try
              {
                System.out.println(paramArrayOfString.put((0.0D / 0.0D)));
                System.out.print("Exception: ");
              }
              catch (Exception paramArrayOfString)
              {
                try
                {
                  XML.toJSONObject("<a><b>    ");
                  System.out.print("Exception: ");
                }
                catch (Exception paramArrayOfString)
                {
                  try
                  {
                    XML.toJSONObject("<a></b>    ");
                    System.out.print("Exception: ");
                  }
                  catch (Exception paramArrayOfString)
                  {
                    try
                    {
                      while (true)
                      {
                        XML.toJSONObject("<a></a    ");
                        System.out.print("Exception: ");
                        try
                        {
                          paramArrayOfString = new JSONArray(new Object());
                          System.out.println(paramArrayOfString.toString());
                          return;
                        }
                        catch (Exception paramArrayOfString)
                        {
                          System.out.println(paramArrayOfString);
                          return;
                        }
                        localException4 = localException4;
                        System.out.println(localException4);
                        continue;
                        localException5 = localException5;
                        System.out.println(localException5);
                        continue;
                        localException1 = localException1;
                        System.out.println(localException1);
                        continue;
                        localException2 = localException2;
                        System.out.println(localException2);
                        continue;
                        localException3 = localException3;
                        System.out.println(localException3);
                        continue;
                        paramArrayOfString = paramArrayOfString;
                        System.out.println(paramArrayOfString);
                        continue;
                        paramArrayOfString = paramArrayOfString;
                        System.out.println(paramArrayOfString);
                      }
                      paramArrayOfString = paramArrayOfString;
                      System.out.println(paramArrayOfString);
                    }
                    catch (Exception paramArrayOfString)
                    {
                      while (true)
                        System.out.println(paramArrayOfString);
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     org.json.Test
 * JD-Core Version:    0.6.2
 */