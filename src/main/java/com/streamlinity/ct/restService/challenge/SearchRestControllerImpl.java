package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

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

    public SearchSvcInterface searchSvc ;

    public SearchRestControllerImpl(SearchSvcInterface searchSvc) {
        this.searchSvc = searchSvc;
    }

    @GetMapping("/{itemShortName}")
    public ResponseEntity<List<Item>> getItemsWithName(@PathVariable("itemShortName") String shortName){
        return new ResponseEntity<>(searchSvc.getItem(shortName),OK);
    }

    @GetMapping
    public ResponseEntity<List<Item>> getAllItemsWithCategory(@RequestParam("category") Optional<String> category){
        return category.map(s -> new ResponseEntity<>(searchSvc.getItems(s), OK)).orElseGet(() -> new ResponseEntity<>(searchSvc.getItems(), OK));
    }

}
