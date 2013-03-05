package jsoup;

import java.io.IOException;

import javax.print.attribute.standard.Sides;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class jsoupTest
{

	public static void main(String[] args)
	{
		String startPageString = "http://baike.baidu.com/";
		Document doc = null;

		try
		{
			doc = Jsoup.connect(startPageString).get();
		} catch (IOException e)
		{
			System.out.println("can't connect to start page : "
					+ startPageString);
			return;
		}

		Elements startPageElements = doc.getElementsByClass("index");
		Element navigationPageElement = startPageElements.get(0)
				.getElementsByClass("g-row").get(8);

		Elements subNavigationPageElements = navigationPageElement
				.getElementsByTag("h4");

		Elements entryNavigationPageElements = navigationPageElement
				.getElementsByTag("h5");

		for (int i = 0; i < subNavigationPageElements.size(); i++)
		{
			Element subElement = subNavigationPageElements.get(i);
			System.out.println("--" + subElement.getElementsByTag("a").text()
					+ "    "
					+ subElement.getElementsByTag("a").get(0).attr("href"));

			for (int j = i * 5; j < i * 5 + 5; j++)
			{
				System.out.println("|--"
						+ entryNavigationPageElements.get(j)
								.getElementsByTag("a").text()
						+ "    "
						+ entryNavigationPageElements.get(j)
								.getElementsByTag("a").attr("href"));
			}

		}

		// Elements navigationPageElement = startPageElements.get(0)
		// .getElementsByClass("g-row");
		// System.out.println(navigationPageElement.size());
		//
		// Element elements = startPageElements.get(0)
		// .getElementsByClass("g-row");
		// System.out.println(elements.toString());
		//

		// System.out.println(navigationPageElements.toString());
		// for (int i = 0; i < navigationPageElements.size(); i++)
		// {
		// Elements subNavigationPageElements = navigationPageElements.get(i)
		// .getElementsByTag("h4");
		// int subNavigationSize = subNavigationPageElements.size();
		//
		// if (subNavigationSize != 0)
		// {
		// System.out.println(subNavigationPageElements.get(0)
		// .getElementsByTag("a"));
		// }
		//
		// }
	}
}
