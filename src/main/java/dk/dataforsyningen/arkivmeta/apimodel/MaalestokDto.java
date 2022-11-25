package dk.dataforsyningen.arkivmeta.apimodel;

public class MaalestokDto {
  private String maalestok;

  public MaalestokDto(String maalestok) {
    this.maalestok = maalestok;
  }

  public String getMaalestok() {
    return maalestok;
  }

  public void setMaalestok(String maalestok) {
    this.maalestok = maalestok;
  }
}