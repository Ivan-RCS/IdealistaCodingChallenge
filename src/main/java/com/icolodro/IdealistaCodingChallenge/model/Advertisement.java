package com.icolodro.IdealistaCodingChallenge.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    }
    
    public Integer getQualityRate()
    {
        return 0;
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
