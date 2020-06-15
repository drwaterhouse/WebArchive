package com.db34.WebArchive.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.db34.WebArchive.domains.db.ArchiveItem;
import com.db34.WebArchive.domains.db.ArchiveItemJdbcRepository;

@RestController
public class ArchiveItemController {

	@Autowired
	private ArchiveItemJdbcRepository repo = new ArchiveItemJdbcRepository();

	@GetMapping(value = "/item/list") // , produces = MediaType.APPLICATION_JSON)
	public List<ArchiveItem> listItems() {
		List<ArchiveItem> list = repo.findAll();
		return list;
	}

	@GetMapping(value = "/item/listCnt", produces = MediaType.TEXT_PLAIN_VALUE)
	public String listItemsCount() {
		List<ArchiveItem> list = repo.findAll();
		if (list != null)
			return String.valueOf(list.size());
		return "emptySet";
	}

//	@GetMapping(value = "/item/{id}") // , produces = MediaType.TEXT_PLAIN_VALUE)
	@RequestMapping(method = RequestMethod.GET, value = "/item/{id}")
	public @ResponseBody ResponseEntity<ArchiveItem> itemById(@PathVariable long id) {
		ArchiveItem item = repo.findById(id);
		ResponseEntity<ArchiveItem> response = new ResponseEntity<ArchiveItem>(item, HttpStatus.OK);
		return response;
	}

//	@GetMapping(value = "/item/new/{id}") // , produces = MediaType.TEXT_PLAIN_VALUE)
//	public void create(@PathVariable long id) {
//		ArchiveItem item = new ArchiveItem();
//		item.setId(id);
//		item.setName("name:" + id);
//		item.setDecription("descn:" + id);
//		repo.insert(item);
//	}

	@RequestMapping(method = RequestMethod.POST, value = "/item/")
	// POST ist weder sicher noch idempotent.
	public ResponseEntity<Void> create() {
		ArchiveItem newItem = new ArchiveItem();
		newItem.setId(this.repo.nextId());
		this.repo.insert(newItem);
//	    customer.setId(String.valueOf(customerIdCounter++));	 
//	    customers.put(customer.getId(), customer);

		HttpHeaders headers = new HttpHeaders();
		try {
			headers.setLocation(new URI("/item/" + newItem.getId()));
		} catch (URISyntaxException e) {
			System.err.println("Invalid URI. Customer id is not valid." + e.toString());
		}

//	    logger.info("Created customer:" + customer.getId());

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/item/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updateCustomer(@PathVariable long id, @RequestBody Map<String, String> itemData) {
//	    logger.info("Try to update the Customer: " + customerId);
		ArchiveItem item = repo.findById(id);
		if (item == null) {
			throw new RuntimeException("Item not found, key invalid " + id);
		}
		HttpHeaders headers = new HttpHeaders();

		if (itemData.containsKey("Name")) {
			item.setName(itemData.get("Name"));
			try {
				headers.setLocation(new URI("/item/" + item.getId()));
			} catch (URISyntaxException e) {
				System.err.println("Invalid URI. Customer id is not valid." + e.toString());
			}
		} else {
			throw new RuntimeException("Request does not contain the key 'Name'");
		}

//	    customer.setCreated(new Date());
		this.repo.update(item);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/foo")
	public String mmrHasUnderstood() {
		return "bar";
	}

}
