package dk.dataforsyningen.arkivmeta.apimodel;

import dk.dataforsyningen.arkivmeta.enums.Arketype;

public class KortvaerkDto {
  private String arkenavn;

  private Arketype arketype;

  private String kortvaerk;

  public KortvaerkDto() {
  }

  public KortvaerkDto(String kortvaerk) {
    this.kortvaerk = kortvaerk;
  }

  public String getArkenavn() {
    return arkenavn;
  }

  public void setArkenavn(String arkenavn) {
    this.arkenavn = arkenavn;
  }

  public Arketype getArketype() {
    return arketype;
  }

  public void setArketype(Arketype arketype) {
    this.arketype = arketype;
  }

  public String getKortvaerk() {
    return kortvaerk;
  }

  public void setKortvaerk(String kortvaerk) {
    this.kortvaerk = kortvaerk;
  }
}