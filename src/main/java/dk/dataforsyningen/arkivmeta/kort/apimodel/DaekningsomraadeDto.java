package dk.dataforsyningen.arkivmeta.kort.apimodel;

public class DaekningsomraadeDto {
  private String daekningsomraade;

  public DaekningsomraadeDto(String daekningsomraade) {
    this.daekningsomraade = daekningsomraade;
  }

  public String getDaekningsomraade() {
    return daekningsomraade;
  }

  public void setDaekningsomraade(String daekningsomraade) {
    this.daekningsomraade = daekningsomraade;
  }
}
