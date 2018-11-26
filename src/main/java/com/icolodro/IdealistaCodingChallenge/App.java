package com.icolodro.IdealistaCodingChallenge;

import static spark.Spark.get;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    protected App()
    {
        
    }
    
    public static void main( String[] args )
    {
        LOGGER.info("running idealista code challenge http api");
        
        get("idealista/ads", IdealistaController::getAdsForUser);
        get("idealista/admin/ads/irrelevant", IdealistaController::getIrrelevantAdsForAdmin);
        get("idealista/admin/ads/rate/average", IdealistaController::getAverageAdsRate);
    }
}
