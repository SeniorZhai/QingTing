import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

public class JDOMAbout
{
  public static void main(String[] paramArrayOfString)
    throws Exception
  {
    paramArrayOfString = new JDOMAbout.Info();
    String str = paramArrayOfString.title;
    System.out.println(str + " version " + paramArrayOfString.version);
    System.out.println("Copyright " + paramArrayOfString.copyright);
    System.out.println();
    System.out.println(paramArrayOfString.description);
    System.out.println();
    System.out.println("Authors:");
    Iterator localIterator = paramArrayOfString.authors.iterator();
    while (localIterator.hasNext())
    {
      JDOMAbout.Author localAuthor = (JDOMAbout.Author)localIterator.next();
      System.out.print("  " + localAuthor.name);
      if (localAuthor.email == null)
        System.out.println();
      else
        System.out.println(" <" + localAuthor.email + ">");
    }
    System.out.println();
    System.out.println(str + " license:");
    System.out.println(paramArrayOfString.license);
    System.out.println();
    System.out.println(str + " support:");
    System.out.println(paramArrayOfString.support);
    System.out.println();
    System.out.println(str + " web site: " + paramArrayOfString.website);
    System.out.println();
  }

  private static class Author
  {
    final String email;
    final String name;

    Author(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.email = paramString2;
    }
  }

  private static class Info
  {
    private static final String INFO_FILENAME = "META-INF/jdom-info.xml";
    final List authors = new ArrayList();
    final String copyright;
    final String description;
    final String license;
    final String support;
    final String title;
    final String version;
    final String website;

    Info()
      throws Exception
    {
      Object localObject1 = getInfoFileStream();
      localObject1 = new SAXBuilder().build((InputStream)localObject1).getRootElement();
      this.title = ((Element)localObject1).getChildTextTrim("title");
      this.version = ((Element)localObject1).getChildTextTrim("version");
      this.copyright = ((Element)localObject1).getChildTextTrim("copyright");
      this.description = ((Element)localObject1).getChildTextTrim("description");
      this.license = ((Element)localObject1).getChildTextTrim("license");
      this.support = ((Element)localObject1).getChildTextTrim("support");
      this.website = ((Element)localObject1).getChildTextTrim("web-site");
      localObject1 = ((Element)localObject1).getChildren("author").iterator();
      while (((Iterator)localObject1).hasNext())
      {
        Object localObject2 = (Element)((Iterator)localObject1).next();
        localObject2 = new JDOMAbout.Author(((Element)localObject2).getChildTextTrim("name"), ((Element)localObject2).getChildTextTrim("e-mail"));
        this.authors.add(localObject2);
      }
    }

    private InputStream getInfoFileStream()
      throws FileNotFoundException
    {
      InputStream localInputStream = getClass().getResourceAsStream("META-INF/jdom-info.xml");
      if (localInputStream == null)
        throw new FileNotFoundException("META-INF/jdom-info.xml not found; it should be within the JDOM JAR but wasn't found on the classpath");
      return localInputStream;
    }
  }
}

/* Location:           C:\Users\User\dex2jar-2.0\dex\qting\classes-dex2jar.jar
 * Qualified Name:     JDOMAbout
 * JD-Core Version:    0.6.2
 */