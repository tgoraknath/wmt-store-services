package walmart.bo;

import static org.assertj.core.api.Assertions.setLenientDateParsing;

import java.util.HashSet;
import java.util.Set;

import org.springframework.web.client.RestTemplate;

import walmart.dto.Item;
import walmart.dto.enums.Items;
/**
 * Note: For this THA - in-memory is used as cache with an assumption of 100-200 items to test item/product look ups. 
 * For actually production ready - in-memory cache will be shifted to 
 * centralized cache for better scalability, reliability and maintainability.
 *
 * 
 * @author tullag
 *
 */
public class TrieNodeBO {
	private static final String WMT_ITEM_SRVS = "http://api.walmartlabs.com/v1/items/ITEM_ID?format=json&apiKey=kjybrqfdgp3u4yv2qzcnjndj";
	private CharTrieNode charRoot = null;
	private IntTrieNode intRoot = null;
	/**
	 * IntTrieNode maintains the status of item and used to filter out out-of-stock items from search result.
	 */
	//private IntTrieNode intRoot = null;
	// TODO - production code - make it private.
	public TrieNodeBO() {
		charRoot = new CharTrieNode();
		intRoot = new IntTrieNode();
	}

	/**
	 * offline process to manage cache. 
	 * Invoke backend api with each of item id and manage the key words
	 * from longDescription into a search fields.
	 * @throws InterruptedException 
	 */
	public Item[] load() throws InterruptedException {
		Items[] items = Items.values();
		//Since there is a limitation on api call, lets do it in sequence.
		Item[] retItems = new Item[items.length];
		for (int i = 0; i< items.length ; i++) {
			retItems[i] = load(items[i].getItemId());
			Thread.sleep(1000l);
		}
		return retItems;
	}
	
	public Item load(String itemId) {
		Items item = Items.find(itemId);
		charRoot.addItems(item.getDescription().split(" "), item.getItemId());
		RestTemplate restTemplate = new RestTemplate();
		Item itemData = restTemplate.getForObject(WMT_ITEM_SRVS.replace("ITEM_ID", itemId), Item.class);
        //now hold itemData in cache. 
		intRoot.addItem(itemData);
		return itemData;
	}
	public Set<Item> search(String query) {
		Set<String> itemIds = charRoot.findItems(query);
		Set<Item> items = new HashSet<>(itemIds.size());
		itemIds.parallelStream()
			   .forEach(e ->items.add(intRoot.findItem(e)));
		return items;
	}
	
}
