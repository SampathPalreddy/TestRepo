package com.streamlinity.ct.restService.challenge;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.streamlinity.ct.model.Item;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

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

    private List<Item> items = new ArrayList<>();
    @Override
    public void init(String itemPriceJsonFileName) {
        try {
            items = readItemsFromJsonFile(itemPriceJsonFileName+".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(File itemPriceJsonFile) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            Item[] itemsList = mapper.readValue(itemPriceJsonFile, Item[].class);
            items.clear();
            Collections.addAll(items, itemsList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public List<Item> getItems()  {
        try {
            if(items.isEmpty()){
                return readItemsFromJsonFile("itemPrices.json");
            } else {
                return items;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }


    private List<Item> readItemsFromJsonFile(String fileName) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath:"+fileName);
        List<Item> items = new ArrayList<>();
        for(Resource resource: resources){
            try(Reader reader= new InputStreamReader(resource.getInputStream(),UTF_8)){
                Item[] itemsList = new ObjectMapper().readValue(FileCopyUtils.copyToString(reader), Item[].class);
                Collections.addAll(items, itemsList);
            }
        }
        return items;
    }

    @Override
    public List<Item> getItems(String category) {
        try {
           if(items.isEmpty()){
                items =  readItemsFromJsonFile("itemPrices.json");
            }
            return items.stream()
                    .filter(it -> it.getCategory_short_name().equals(category))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> getItem(String itemShortName) {
        try {
            if(items.isEmpty()){
                items =  readItemsFromJsonFile("itemPrices.json");
            }
            return items.stream()
                    .filter(it -> it.getShort_name().equals(itemShortName))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
