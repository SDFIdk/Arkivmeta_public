package dk.dataforsyningen.arkivmeta.apimodel;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.ArrayList;
import java.util.List;

public class ArketypeDto {
  @Schema(description = "Kortets arketype.")
  private String arketype;

  @Schema(description = "Kortgruppe.")
  private String arkenavn;

  @Schema(description = "Liste af logiske samlinger af skannede kort som tilhører arketypen.")
  private List<String> kortvaerker = new ArrayList<>();

  public ArketypeDto() {
  }

  public ArketypeDto(String arketype, List<String> kortvaerker) {
    this.arketype = arketype;
    this.kortvaerker = kortvaerker;
  }

  public ArketypeDto(String arketype, String arkenavn, List<String> kortvaerker) {
    this.arketype = arketype;
    this.arkenavn = arkenavn;
    this.kortvaerker = kortvaerker;
  }

  public String getArketype() {
    return arketype;
  }

  public void setArketype(String arketype) {
    this.arketype = arketype;
  }

  public String getArkenavn() {
    return arkenavn;
  }

  public void setArkenavn(String arkenavn) {
    this.arkenavn = arkenavn;
  }

  public List<String> getKortvaerker() {
    return kortvaerker;
  }

  public void setKortvaerker(List<String> kortvaerker) {
    this.kortvaerker = kortvaerker;
  }

  @Override
  public String toString() {
    return "ArketypeDto{" +
        ", arketype='" + arketype + '\'' +
        ", arkenavn='" + arkenavn + '\'' +
        ", kortvaerk='" + kortvaerker + '\'' +
        '}';
  }
}
