package dk.dataforsyningen.arkivmeta.apimapper;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class MapperDaekningsomraade
{
    public List<String> mapDaekningsomraade(String daekningsomraade)
    {
        return Arrays.asList(daekningsomraade.split("::"));
    }
}
