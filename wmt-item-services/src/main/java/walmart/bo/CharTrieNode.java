package walmart.bo;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import walmart.dto.enums.AlphabetsEnum;
import static walmart.dto.enums.AlphabetsEnum.find;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * CharTrieNode is a Trie data structure which holds char as key and CharTrieNode as Value.
 *
 */
public class CharTrieNode {
	private Map<AlphabetsEnum, CharTrieNode> elements = null;
	private Set<String> items = null;
	
	public CharTrieNode() {
		//at max 26 keys [a-z]|[A-Z]
		elements = new HashMap<>();
	}
	
	public Set<String> getItems() {
		if(isNull(items)) {
			return Collections.emptySet();
		}
		return items;
	}
	/**
	 * Items look up api.
	 * @param query
	 * @return non-null unique item ids for given query.
	 */
	public Set<String> findItems(String query) {
		AlphabetsEnum[] chars = find(query);
		return findItems(chars);
	}
	
	public void addItems(String[] keys, String value) {
		for(String key : keys) {
			/**
			//business rule - a key should be of length 3-26 and all alphabets.
			//[a-z][A-Z]{3,26}
			if(key.matches("^[a-z][A-Z]{3,26}$")) {
			}
			*/
			addItem(key, value);
		}
	}
	/**
	 * addItem is used to update the elements collection with the given key and value.
	 * @param key
	 * @param value
	 */
	public void addItem(String key, String value) {
		AlphabetsEnum[] chars = find(key);
		addItem(this, chars, 0, value);
	}
	
	private void addItem(CharTrieNode root, AlphabetsEnum[] chars, int index, String value) {
		if(index == chars.length) {
			root.addItem(value);
			return;
		}
		AlphabetsEnum c = chars[index];
		CharTrieNode tn = root.elements.get(c);
		if(isNull(tn)) {
			tn = new CharTrieNode();
			root.elements.put(c, tn);
		}
		addItem(tn, chars, index + 1, value);
	}
	
	private void addItem(String itemId) {
		if(isNull(items)) {
			items = new HashSet<>();	
		}
		items.add(itemId);
	}
	
	private Set<String> findItems(AlphabetsEnum[] chars) {
		CharTrieNode node = findItems(this, chars, 0);
		return nonNull(node) ? node.getItems() : Collections.emptySet();
	}
	
	private CharTrieNode findItems(CharTrieNode root, AlphabetsEnum[] chars, int index) {
		if(root == null || index == chars.length) {
			return root;
		}
		AlphabetsEnum c = chars[index];
		CharTrieNode tn = root.elements.get(c);
		return findItems(tn, chars, index + 1);
	}
}
