package com.icolodro.IdealistaCodingChallenge.model.gson;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.icolodro.IdealistaCodingChallenge.model.Advertisement;

/**
 * <p> Serializer a JSON object del objeto Advertisement. Mantiene campos originales y añade el array completo de fotos, fecha y ratico de calidad. </p>
 * 
 * <ul>Campos adicionales :
 * <li>qualityRate </li>
 * <li>date </li>
 * </ul>
 * 
 * 
 * @author ivan.colodro
 * @see Advertisement
 */
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
        src.getGardenSize().ifPresent(gs -> jsonObject.addProperty("gardenSize", gs));  
        jsonObject.add("pictures", (JsonArray) new Gson().toJsonTree(src.getPictures()));
        
        jsonObject.addProperty("qualityRate", src.getQualityRate());
        jsonObject.addProperty("date", LocalDate.now().format(DateTimeFormatter.ISO_DATE));
        
        return jsonObject;
    }

}
