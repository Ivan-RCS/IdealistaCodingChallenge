package com.icolodro.IdealistaCodingChallenge;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.icolodro.IdealistaCodingChallenge.model.Advertisement;

public class IdealistaServiceTest
{
 
    public static Gson gson;
    
    public static Type listType;
    
   
    
    @BeforeClass
    public static void setUp()
    {
        gson = new GsonBuilder().registerTypeAdapter(Advertisement.class, new AdvertisementDeserializerTest()).create();
        
        listType = new TypeToken<ArrayList<Advertisement>>()
        {
        }.getType();
    }
    
    @Test
    public void shouldGetAverageAdsRateWithCurrentDataSet()
    {      
        Double average = IdealistaService.getInstance().getAverageAdsRate();
        
        //con el actual data set la media es 36.875
        Assert.assertEquals(36.875D, average, 0D);
    }
    
    /**
     * Este método debe devolver 4 anuncios acorde a los estándares de calidad, ordenados de más calidad a menos calidad
     */
    @Test
    public void shouldGetAdsForUserWithCurrentDataSet()
    { 
        String response = IdealistaService.getInstance().getAdsForUser();
           
        List<Advertisement> ads = gson.fromJson(response, listType);
        
        Assert.assertTrue(ads.size() == 4);
        Assert.assertEquals(ads.get(0).getId().longValue(), 4L);
        Assert.assertEquals(ads.get(1).getId().longValue(), 8L);
        Assert.assertEquals(ads.get(2).getId().longValue(), 6L);
        Assert.assertEquals(ads.get(3).getId().longValue(), 2L);
        
    }
    
    /**
     * 
     * Este método debe devolver 4 anuncios, los irrelevantes del data set, ordenados de menos calidad a más calidad
     */
    @Test
    public void shouldGetIrrelevantAdsForAdminWithCurrentDataSet()
    { 
        String response = IdealistaService.getInstance().getIrrelevantAdsForAdmin();
           
        List<Advertisement> ads = gson.fromJson(response, listType);
        
        Assert.assertTrue(ads.size() == 4);
        Assert.assertTrue(ads.get(0).getId().equals(1L) || ads.get(0).getId().equals(7L)); //mismos puntos -5
        Assert.assertTrue(ads.get(1).getId().equals(1L) || ads.get(1).getId().equals(7L));      
        Assert.assertEquals(ads.get(2).getId().longValue(), 3L);
        Assert.assertEquals(ads.get(3).getId().longValue(), 5L);
        
    }
    
    private static class AdvertisementDeserializerTest implements JsonDeserializer<Advertisement>
    {

        @Override
        public Advertisement deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
        {
            JsonObject obj = json.getAsJsonObject();
            Advertisement advertisement = new Advertisement();
            advertisement.setId(obj.get("id").getAsLong());
            
            return advertisement;
        }     
    }
}
