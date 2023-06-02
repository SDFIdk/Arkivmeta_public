package dk.dataforsyningen.arkivmeta;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

public class Kortvaerk {

  @Parameter(
      in = ParameterIn.QUERY,
      name = "kortvaerk",
      content = @Content(array = @ArraySchema(schema = @Schema(type = "string"))),
      description =
      "Kortværk. Hvis der ønskes at søge på flere kortværker på en gang, skal man angive `kortvaerk` query parameteren for hvert eneste en kortværk man vil søge efter." +
          "Eksempel: `kortvaerk=Trap, tegnede kort`&`kortvaerk=Mejer`")
  @Schema(description = "Navnet på en logisk samling af skannede kort som kortet hører til, f.eks. atlasblade.")
  private String[] kortvaerk = new String[] {};

  public Kortvaerk() {
    super();
  }

  public Kortvaerk(String[] kortvaerk) {
    super();
    this.kortvaerk = kortvaerk;
  }

  public String[] getKortvaerk() {
    return kortvaerk;
  }

  public void setKortvaerk(String[] kortvaerk) {
    this.kortvaerk = kortvaerk;
  }

  public List<String> toList() {
    return Arrays.asList(this.kortvaerk);
  }

  @Override
  public String toString() {
    return Arrays.toString(kortvaerk);
  }
}