package com.icolodro.IdealistaChallenge.utils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Esta clase contiene constantes para la aplicación. Consta de un constructor protegido
 * porque sus variables son publicas y estáticas, no se quieren crear instancias de esta
 * clase en memoria.
 * 
 * @author ivan.colodro
 *
 */
public class Constants
{ 
    public static final String JSON_FILE_ADVERTISEMENT = "ads.json";
    
    public static final String JSON_FILE_PICTURES = "pictures.json";
    
    public static final Set<String> WORDS_POINTS = new HashSet<>(Arrays.asList("luminoso","nuevo","centrico","reformado", "atico"));
    
    protected Constants()
    {
        
    }
}
