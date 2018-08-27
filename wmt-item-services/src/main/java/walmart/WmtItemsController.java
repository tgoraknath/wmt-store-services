package walmart;

import java.util.Set;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import walmart.bo.TrieNodeBO;
import walmart.dto.Item;

@RestController
public class WmtItemsController {
	
	public WmtItemsController() {
	}
	private final TrieNodeBO bo = new TrieNodeBO();
    //
    @RequestMapping("/products")
    public Set<Item> getProducts(@RequestParam(value="query") String query) {
    	 /**
    	  * user will enter query - books
    	  * product api /{i_id} - response - {}
    	  */
    	  /**
    	   * set of item ids
    	   * back end find by id, no bulk
    	   * 
    	   */
    	 return bo.search(query);
    }
    //in-memory loading - bulk by reading the i_id's from Items enum
    //update input memory when the request is to refresh
    // load/{i_id} - 
    @RequestMapping("/manage/all")
	public Item[] loadAll() throws InterruptedException {
		return bo.load();
	}
    
    @RequestMapping("/manage/{item_id}")
    public Item load(@PathVariable("item_id") String itemId) {
    		return bo.load(itemId);
    }
}