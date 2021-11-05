package dk.dataforsyningen.arkivmeta.apimapper;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MapperKortvaerk
{
    public List<String> mapKortvaerk(String kortvaerk)
    {
        return Arrays.asList(kortvaerk.split(";;"));
        //return List.of(kortvaerk);
    }
}
