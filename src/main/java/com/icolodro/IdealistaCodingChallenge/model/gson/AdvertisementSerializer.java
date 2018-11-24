package com.icolodro.IdealistaCodingChallenge.model.gson;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.icolodro.IdealistaCodingChallenge.model.Advertisement;

public class AdvertisementSerializer implements JsonSerializer<Advertisement>
{

    @Override
    public JsonElement serialize(Advertisement src, Type typeOfSrc, JsonSerializationContext context)
    {
        JsonObject jsonObject = new JsonObject();
        
        jsonObject.addProperty("id", src.getId());
        jsonObject.addProperty("description", src.getDescription());
        jsonObject.addProperty("typology", src.getTypology().name());
        src.getHouseSize().ifPresent(hs -> jsonObject.addProperty("houseSize", hs));
        src.getGardenSize().ifPresent(gs -> jsonObject.addProperty("houseSize", gs));  
        jsonObject.add("pictures", (JsonArray) new Gson().toJsonTree(src));
        
        return jsonObject;
    }

}
