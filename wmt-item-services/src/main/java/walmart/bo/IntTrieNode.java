package walmart.bo;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static walmart.dto.enums.NumericsEnum.find;

import java.util.HashMap;
import java.util.Map;

import walmart.dto.Item;
import walmart.dto.enums.NumericsEnum;


public class IntTrieNode {
	private Map<NumericsEnum, IntTrieNode> elements = null;
	private Item item = null;
	
	public IntTrieNode() {
		//at max 10 keys [0-9]
		elements = new HashMap<>();
	}
	
	public Item getItem() {
		return item;
	}
	
	public void addItem(Item itemData) {
		NumericsEnum[] chars = find(itemData.getId());
		addItem(this, chars, 0, itemData);
		
	}
	
	private void addItem(IntTrieNode root, NumericsEnum[] chars, int index, Item value) {
		if(index == chars.length) {
			root.item = value;
			return;
		}
		NumericsEnum c = chars[index];
		IntTrieNode tn = root.elements.get(c);
		if(isNull(tn)) {
			tn = new IntTrieNode();
			root.elements.put(c, tn);
		}
		addItem(tn, chars, index + 1, value);
		
	}

	public Item findItem(String itemId) {
		NumericsEnum[] chars = find(itemId);
		return findItem(chars);
	}

	private Item findItem(NumericsEnum[] chars) {
		IntTrieNode node = findItems(this, chars, 0);
		return nonNull(node) ? node.getItem() : null;
	}
	
	private IntTrieNode findItems(IntTrieNode root, NumericsEnum[] chars, int index) {
		if(root == null || index == chars.length) {
			return root;
		}
		NumericsEnum c = chars[index];
		IntTrieNode tn = root.elements.get(c);
		return findItems(tn, chars, index + 1);
	}

}
