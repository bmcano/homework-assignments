package apps;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Iterator;
import net.datastructures.Entry;
import org.json.JSONObject;
import org.json.JSONTokener;

import net.datastructures.SortedTableMap;
import net.datastructures.SortedMap;

/**
 *
 * @author bdmyers
 */
// An application to demonstrate the SortedListMap, an implementation of SortedMap
public class DictionaryApp {
	public static void main(String[] args) throws URISyntaxException, MalformedURLException, IOException {
		System.out.println("Downloading...");
		URI uri = new URI("https://github.com/adambom/dictionary/blob/master/dictionary.json?raw=true");
		JSONTokener tokener = new JSONTokener(uri.toURL().openStream());
		JSONObject wordsAndDefs = new JSONObject(tokener);
		
		System.out.println("Adding to our dictionary...");
		SortedMap<String, String> dictionary = new SortedTableMap<>();
		// toMap provides the words and definitions as a Java built-in Map,
		// but we need to put each of the word,definition entries into our sorted map 
		for (java.util.Map.Entry<String,Object> wd : wordsAndDefs.toMap().entrySet()) {
			String word = wd.getKey();
			if (word.startsWith("-")) { // don't include weird non-letter words
// DO NOTHING
			} else {
			String definition = wd.getValue().toString();

			// put the word, definition into our dictionary
			dictionary.put(word, definition);
			}
		}

		// demonstrate some of the SortedMap methods
		System.out.println("definition of TEA: "+dictionary.get("TEA"));
		System.out.println("first: "+dictionary.firstEntry());
		String firstWord = dictionary.firstEntry().getKey();
		Entry<String,String> second = dictionary.higherEntry(firstWord);
		Entry<String,String> third = dictionary.higherEntry(second.getKey());
		System.out.println("second:"+second);
		System.out.println("third:"+third);
		System.out.println("last: "+dictionary.lastEntry());
		System.out.println("nearest above IOWA: "+dictionary.ceilingEntry("IOWA"));
		System.out.println("nearest below IOWA: "+dictionary.floorEntry("IOWA"));
		
		// print the first 3 L-words
		System.out.println("First 3 L-words");
		Iterator<Entry<String,String>> iwords = dictionary.subMap("L", "M").iterator();
		System.out.println(iwords.next());		
		System.out.println(iwords.next());		
		System.out.println(iwords.next());		
	}	
}
