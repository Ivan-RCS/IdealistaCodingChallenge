package com.icolodro.IdealistaCodingChallenge;

import java.io.InputStream;
import java.io.InputStreamReader;
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
import com.icolodro.IdealistaCodingChallenge.model.gson.AdvertisementSerializer;

/**
 * Este servicio operala la lógica de negocio de las operaciones requeridas por Idealista. Como se trata de un servicio web, implementa un patrón
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
        
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(Constants.JSON_FILE_ADVERTISEMENT);
     
        JsonReader reader = new JsonReader(new InputStreamReader(inputStream));
        
        return gson.fromJson(reader, listType);
    }
    
    /**
     * Este método calcula la media del ratio de calidad de anuncios según los estándares de idealista
     * 
     * @return media de puntaciónde calidad
     */
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
    
    /**
     * Este método devuelve los anuncios para un usuario, sólo aquellos con 40 o más de ratio de calidad y ordenados de mayor a menor. Devuelve un Json con datos adicionales
     * con array de datos completos de las fotos, fecha y ratio de calidad.
     * 
     * @return json string
     * @see AdvertisementSerializer
     */
    public String getAdsForUser()
    {
        
        String response = EMPTY_RESPONSE;
        try
        {
            List<Advertisement> ads = getAdvertisements().stream()
                                                         .filter(a -> a.getQualityRate() >= 40)
                                                         .sorted(Comparator.comparing(Advertisement::getQualityRate).reversed())                                
                                                         .collect(Collectors.toList());
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Advertisement.class, new AdvertisementSerializer()).create();
            
            response = gson.toJson(ads);

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        
        return response;
    }
    
    /**
     * Este método devuelve los anuncios para un encargado, sólo aquellos con menos de 40 de ratio de calidad y ordenados de menor a mayor. Devuelve un Json con datos adicionales
     * con array de datos completos de las fotos, fecha y ratio de calidad.
     * 
     * @return json string
     * @see AdvertisementSerializer
     */
    public String getIrrelevantAdsForAdmin()
    {
        String response = EMPTY_RESPONSE;
        try
        {
            List<Advertisement> ads = getAdvertisements().stream()
                                                         .filter(a -> a.getQualityRate() < 40)
                                                         .sorted(Comparator.comparing(Advertisement::getQualityRate))                                
                                                         .collect(Collectors.toList());
            
            Gson gson = new GsonBuilder().registerTypeAdapter(Advertisement.class, new AdvertisementSerializer()).create();
            
            response = gson.toJson(ads);

        }
        catch (Exception e)
        {
            LOGGER.error(e.getMessage());
        }
        
        return response;
    }

}
