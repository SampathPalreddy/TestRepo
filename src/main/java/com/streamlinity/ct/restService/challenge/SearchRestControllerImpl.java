package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;

/*
 * This controller needs to expose the following rest endpoints.  You need to fill in the implementation here
 *
 * Required REST Endpoints
 *
 *      /item                       Get all items
 *      /item?category=C            Get all items in category specified by Category shortName
 *      /item/{itemShortName}       Get item that matches the specified Item category
 */

@Profile("default")
@RestController
@RequestMapping("/item")
public class SearchRestControllerImpl {

    public SearchSvcImpl searchSvc;

    @GetMapping
    public List<Item> getItems(){
      return searchSvc.getItems();
    }

    @GetMapping
    public List<Item> getItems(@RequestParam("category") String category){
        return searchSvc.getItems(category);
    }

    @GetMapping("/{itemShortName}")
    public List<Item> getItem(@PathVariable("itemShortName") String shortName){
        return searchSvc.getItem(shortName);
    }

}
