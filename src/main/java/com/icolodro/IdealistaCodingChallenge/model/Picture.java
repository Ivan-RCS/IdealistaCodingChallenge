package com.icolodro.IdealistaCodingChallenge.model;

/**∫
 * @author ivan.colodro
 *
 */
public class Picture
{
    private Long id;
    
    private String url;
    
    private String quality;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getQuality()
    {
        return quality;
    }

    public void setQuality(String quality)
    {
        this.quality = quality;
    }  
}