package dk.dataforsyningen.arkivmeta.kort.apimodel;

public class KortvaerkDto {
  private String arkenavn;

  private String arketype;

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

  public String getArketype() {
    return arketype;
  }

  public void setArketype(String arketype) {
    this.arketype = arketype;
  }

  public String getKortvaerk() {
    return kortvaerk;
  }

  public void setKortvaerk(String kortvaerk) {
    this.kortvaerk = kortvaerk;
  }
}