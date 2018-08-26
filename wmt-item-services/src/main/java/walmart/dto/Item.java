package walmart.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

	@JsonProperty(value = "itemId")
    private String id;
	@JsonProperty(value = "parentItemId")
	private String parentId;
	@JsonProperty(value = "longDescription")
    private String longDescription;
	@JsonProperty(value = "stock", defaultValue = "Available")
	private String stock;

    public Item() {
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getLongDescription() {
		return longDescription;
	}

	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", parentId=" + parentId + ", longDescription=" + longDescription + ", stock=" + stock
				+ "]";
	}
    
}