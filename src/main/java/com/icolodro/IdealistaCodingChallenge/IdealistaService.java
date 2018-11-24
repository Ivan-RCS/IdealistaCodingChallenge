package com.icolodro.IdealistaCodingChallenge;

import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.icolodro.IdealistaChallenge.utils.Constants;
import com.icolodro.IdealistaCodingChallenge.model.Advertisement;
import com.icolodro.IdealistaCodingChallenge.model.gson.AdvertisementDeserializer;

/**
 * Este servicio operala la logica de negocio de las operaciones requeridas por Idealista. Como se trata de un servicio web, implementa un patrón
 * singleton
 * 
 * @author ivan.colodro
 *
 */
public class IdealistaService
{

    private static final Logger LOGGER = LoggerFactory.getLogger(IdealistaService.class);

    private static IdealistaService instance = null;
    
    private static final String EMPTY_RESPONSE = "{}";

    protected IdealistaService()
    {
    }

    public static synchronized IdealistaService getInstance()
    {
        if (instance == null)
            instance = new IdealistaService();

        return instance;
    }
    
    private List<Advertisement> getAdvertisements() throws Exception
    {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Advertisement.class, new AdvertisementDeserializer());
        Gson gson = gsonBuilder.create();
        
        Type listType = new TypeToken<ArrayList<Advertisement>>()
        {
        }.getType();

        JsonReader reader;

        reader = new JsonReader(new FileReader(Constants.PATH_JSON_FILE_ADVERTISEMENT));
        
        return gson.fromJson(reader, listType);
    }

    public Double getAverageAdsRate()
    {
        Double averageRate = 0D;

        try
        {
            averageRate = getAdvertisements().stream().mapToInt(Advertisement::getQualityRate)
                                                      .summaryStatistics()
                                                      .getAverage();

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }

        return averageRate;
    }

    public String getAdsForUser()
    {
        
        String response = EMPTY_RESPONSE;
        try
        {
            List<Advertisement> ads = getAdvertisements().stream()
                                                         .filter(a -> a.getQualityRate() >= 40)
                                                         .sorted(Comparator.comparing(Advertisement::getQualityRate).reversed())                                
                                                         .collect(Collectors.toList());
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Advertisement.class, new Advertisement()).create();
            
            response = gson.toJson(ads);

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        
        return response;
    }

    public String getIrrelevantAdsForAdmin()
    {
        String response = EMPTY_RESPONSE;
        try
        {
            List<Advertisement> ads = getAdvertisements().stream()
                                                         .filter(a -> a.getQualityRate() < 40)
                                                         .sorted(Comparator.comparing(Advertisement::getQualityRate))                                
                                                         .collect(Collectors.toList());
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Advertisement.class, new Advertisement()).create();
            
            response = gson.toJson(ads);

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        
        return response;
    }

}
