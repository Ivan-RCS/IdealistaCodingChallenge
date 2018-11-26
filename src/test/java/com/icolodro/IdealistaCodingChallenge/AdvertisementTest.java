package com.icolodro.IdealistaCodingChallenge;

import org.junit.Before;
import org.junit.Test;

import com.icolodro.IdealistaCodingChallenge.model.Advertisement;
import com.icolodro.IdealistaCodingChallenge.model.AdvertisementTypology;
import com.icolodro.IdealistaCodingChallenge.model.Picture;

import java.util.ArrayList;
import java.util.Collections;

import org.junit.Assert;


public class AdvertisementTest
{
    
    private Advertisement advertisment;
    
    @Before
    public void setUp()
    {
        advertisment = new Advertisement();
        advertisment.setDescription("");
        advertisment.setTypology(AdvertisementTypology.CHALET);
       
        Picture picture = new Picture();
        picture.setId(1L);
        picture.setQuality("HD");
        
        advertisment.setPictures(Collections.singletonList(picture));
       
    }
    
    /**
     * -10 objeto sin valores y sin fotos
     * 
     */
    @Test
    public void shouldGetAQualityRateOfMinus10()
    {  
        advertisment.setPictures(new ArrayList<>());
        Assert.assertEquals(-10, advertisment.getQualityRate().intValue());
    }
    
    @Test
    public void shouldGetAQualityRateOf20ByHDPicture()
    {   
        Assert.assertEquals(20, advertisment.getQualityRate().intValue());
    }
    
    @Test
    public void shouldGetAQualityRateOf10BySDPicture()
    {  
        advertisment.getPictures().stream().forEach(p -> p.setQuality("SD"));
        
        Assert.assertEquals(10, advertisment.getQualityRate().intValue());
    }
    
    /**
     *  25 EspecificWords + 5 description is not empty + 20 HD picture
     */
    @Test
    public void shouldGetAQualityRateOf50ByEspecificWords()
    {       
        advertisment.setDescription("Luminoso, Nuevo, Céntrico, Reformado, Ático");
       
        Assert.assertEquals(50, advertisment.getQualityRate().intValue());
    }
    
    /**
     *  5 description is not empty + 20 HD picture + 10 (>= 20 words) 
     */
    @Test
    public void shouldGetAQualityRateOf35By20Words()
    {       
        String description  = "";
        
        for (int i = 0; i < 20; i++)     
            description = description.concat("word ");
        
        advertisment.setDescription(description);
             
        Assert.assertEquals(35, advertisment.getQualityRate().intValue());
    }
    
    /**
     *  5 description is not empty + 20 HD picture + 30 (>= 50 words) + 20 (CHALET)
     */
    @Test
    public void shouldGetAQualityRateOf75By50WordsAndChalet()
    {       
        String description  = "";
        
        for (int i = 0; i < 50; i++)     
            description = description.concat("word ");
        
        advertisment.setDescription(description);
             
        Assert.assertEquals(75, advertisment.getQualityRate().intValue());
    }
    
    /**
     *  5 description is not empty + 20 HD picture + 40 Completed Adv (GARAGE)
     */
    @Test
    public void shouldGetAQualityRateOf65ByCompletedAdvertisment()
    {      
        advertisment.setTypology(AdvertisementTypology.GARAGE);
        advertisment.setDescription("word");
             
        Assert.assertEquals(65, advertisment.getQualityRate().intValue());
    }
}
