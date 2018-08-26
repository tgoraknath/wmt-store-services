package walmart.dto.enums;

import java.util.HashMap;
import java.util.Map;
public enum Items {
	//change description as per the lookup attributes.
	_35613901("35613901", "BAckpack"), _35813552("35813552", "backPack"), _23117408("23117408", "BAckpacK"),
	_14225185("14225185", "Mattress"), _14225188("14225188", "Mattress"), _14225187("14225187", "MATTRESS"),
	_39082884("39082884", "Cleaners"), _30146244("30146244", "iPad tablet Apple"), _12662817("12662817", "Patio chairs"),
	_34890820("34890820", "LED HDTV"), _19716431("19716431", "fishing Garcia"), _42391766("42391766", "Ozark shower"),
	_40611708("40611708", "Pants"), _40611825("40611825", "Activewear"),_36248492("36248492", "Starter shorts"), 
	_44109840("44109840", "Sleepwear"), _42248076("42248076", "Roadmaster Bike"),;
	private String itemId;
	private String description;
	private static Map<String, Items> MAP = null;
	static {
		MAP = new HashMap<>();
		for(Items i : Items.values()) {
			MAP.put(i.itemId, i);
		}
	}
	Items(String itemId, String description) {
		this.itemId = itemId;
		this.description = description;
	}
	public String getItemId() {
		return itemId;
	}
	public String getDescription() {
		return description;
	}
	
	public static Items find(String key) {
		return MAP.get(key);
	}
	
}
