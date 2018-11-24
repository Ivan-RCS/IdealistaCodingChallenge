package com.icolodro.IdealistaCodingChallenge.model.gson;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.icolodro.IdealistaChallenge.utils.Constants;
import com.icolodro.IdealistaCodingChallenge.model.Advertisement;
import com.icolodro.IdealistaCodingChallenge.model.AdvertisementTypology;
import com.icolodro.IdealistaCodingChallenge.model.Picture;

/**
 * 
 * @author ivan.colodro
 *
 */
public class AdvertisementDeserializer implements JsonDeserializer<Advertisement>
{

    private static final Logger LOGGER = LoggerFactory.getLogger(AdvertisementDeserializer.class);
    
    @Override
    public Advertisement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        
        JsonObject obj = json.getAsJsonObject();
        
        Advertisement advertisement = new Advertisement();
        
        advertisement.setId(obj.get("id").getAsLong());
        advertisement.setDescription(obj.get("description").getAsString());
        advertisement.setTypology(AdvertisementTypology.valueOf(obj.get("typology").getAsString()));
        
        Optional.ofNullable(obj.get("houseSize")).ifPresent(o -> advertisement.setHouseSize(Optional.of(o.getAsInt())));
        Optional.ofNullable(obj.get("gardenSize")).ifPresent(o -> advertisement.setHouseSize(Optional.of(o.getAsInt())));
       
        JsonArray idPictures = obj.get("pictures").getAsJsonArray();
        
        try
        {
            if (idPictures.size() > 0)
            {
                Gson gson = new Gson();
                JsonReader reader;

                reader = new JsonReader(new FileReader(Constants.PATH_JSON_FILE_PICTURES));
                
                Type listType = new TypeToken<ArrayList<Picture>>()
                {
                }.getType();

                List<Picture> pictures = gson.fromJson(reader, listType);
                
                for (JsonElement jsonElement : idPictures)
                {
                    pictures.stream().filter(p -> p.getId().equals(jsonElement.getAsLong()))
                                     .findFirst()
                                     .ifPresent(advertisement.getPictures()::add);                                 
                }             
            }

        }
        catch (Exception e)
        {        
            LOGGER.error("Error deserializing the pictures in the advertisement object : " + advertisement.getId(), e);
        }
                
        return advertisement;
    }

}
