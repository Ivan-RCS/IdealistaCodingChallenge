package com.icolodro.IdealistaCodingChallenge.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.icolodro.IdealistaCodingChallenge.utils.Constants;

/**
 * 
 * @author ivan.colodro
 *
 */
public class Advertisement
{
    private Long id;
    
    private String description;
    
    private AdvertisementTypology typology;
    
    private Optional<Integer> houseSize;
    
    private Optional<Integer> gardenSize;
    
    List<Picture> pictures;
    
    public Advertisement()
    {
        this.pictures = new ArrayList<>();
        this.houseSize = Optional.empty();
        this.gardenSize = Optional.empty();
    }
    
    /**
     * <ul> Este método calcula ratio de calidad basado en:
     * <li> sin fotos -10
     * <li> cada foto HD +20, SD +10
     * <li> contiene descripción +5
     * <li> si es casa, si tiene 20 o más palabras +10, si son 50 o más +30
     * <li> si es tipo CHALET y tiene 50 o más palabras +20
     * <li> si contiene palabras valiosas {@link Constants#WORDS_POINTS} +5 por cada una 
     * <li> si el anuncio es completo por su tipología +40
     * </ul>
     *  
     * @return quality rate according to Idealista standars
     * 
     */
    public Integer getQualityRate()
    {
        Integer qualityRate = 0;
        
        if(pictures.isEmpty())
            qualityRate -= 10;
        
        qualityRate += pictures.stream().mapToInt(p -> p.getQuality().equals("HD") ? 20 : 10).sum();
        
        if(!description.isEmpty())
            qualityRate += 5;
            
        List<String> words = Arrays.asList(description.split(" "));
        
        long nWords = words.stream().count();
        
        boolean isCompleted = false;
        
        switch (typology)
        {
            case CHALET:

                qualityRate += getPointsByNumberOfWords(nWords);

                if (nWords >= 50)
                    qualityRate += 20;
                 
                if (nWords > 0 && !description.isEmpty() && houseSize.isPresent() && gardenSize.isPresent() && !pictures.isEmpty())
                    isCompleted = true;

                break;

            case FLAT:

                qualityRate += getPointsByNumberOfWords(nWords);

                if (nWords > 0 && !description.isEmpty() && houseSize.isPresent() && !pictures.isEmpty())
                    isCompleted = true;

                break;

            case GARAGE:

                if (!pictures.isEmpty())
                    isCompleted = true;

                break;

            default:
                break;
        }
        
        
        qualityRate += words.parallelStream()
                            .map(String::toLowerCase)
                            .map(w -> w.replace(",", "").replace(".","").replace(";", ""))
                            .map(w -> Normalizer.normalize(w, Normalizer.Form.NFD))
                            .map(w -> w.replaceAll("[\\p{InCombiningDiacriticalMarks}]", ""))
                            .distinct()                          
                            .mapToInt(w -> Constants.WORDS_POINTS.contains(w) ? 5 : 0)
                            .sum();                                                                                                               
        
        
       if(isCompleted)
           qualityRate += 40;
             
        return qualityRate;
    }
    
    private Integer getPointsByNumberOfWords(long nWords)
    {
        Integer points = 0;
        
        if(nWords >= 20 && nWords < 50)
            points = 10;
        else if (nWords >= 50)
            points = 30;
        
        return points;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public AdvertisementTypology getTypology()
    {
        return typology;
    }

    public void setTypology(AdvertisementTypology typology)
    {
        this.typology = typology;
    }

    public Optional<Integer> getHouseSize()
    {
        return houseSize;
    }

    public void setHouseSize(Optional<Integer> houseSize)
    {
        this.houseSize = houseSize;
    }

    public Optional<Integer> getGardenSize()
    {
        return gardenSize;
    }

    public void setGardenSize(Optional<Integer> gardenSize)
    {
        this.gardenSize = gardenSize;
    }

    public List<Picture> getPictures()
    {
        return pictures;
    }

    public void setPictures(List<Picture> pictures)
    {
        this.pictures = pictures;
    }
}
