package com.streamlinity.ct.restService.challenge;

import com.streamlinity.ct.model.Item;

import java.io.File;
import java.util.List;

/*
 * Provide your implementation of the SearchSvcImpl here.
 * Also annotate your methods with Rest end point wrappers as required in the problem statement
 *
 * You can create any auxiliary classes or interfaces in this package if you need them.
 *
 * Do NOT add annotations as a Bean or Component or Service.   This is being handled in the custom Config class
 * PriceAdjustConfiguration
 */

public class SearchSvcImpl implements SearchSvcInterface {
    @Override
    public void init(String itemPriceJsonFileName) {

    }

    @Override
    public void init(File itemPriceJsonFile) {

    }

    @Override
    public List<Item> getItems() {
        return null;
    }

    @Override
    public List<Item> getItems(String category) {
        return null;
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        return null;
    }
}
