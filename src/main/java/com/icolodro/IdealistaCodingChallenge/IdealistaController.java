package com.icolodro.IdealistaCodingChallenge;

import spark.Request;
import spark.Response;

public class IdealistaController
{
    protected IdealistaController()
    {       
    }
    
    public static String getAdsForUser(Request request, Response response)
    {
        return IdealistaService.getInstance().getAdsForUser();
    }
    
    public static String getIrrelevantAdsForAdmin(Request request, Response response)
    {
        return IdealistaService.getInstance().getIrrelevantAdsForAdmin();
    }
    
    public static Double getAverageAdsRate(Request request, Response response)
    {
        return IdealistaService.getInstance().getAverageAdsRate();
    }
}
