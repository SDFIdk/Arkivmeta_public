package dk.dataforsyningen.arkivmeta.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "a_rel_arketype", schema = "arkivmeta_latest")
@Immutable
public class KortvaerkDB {
  @Id
  @Column(name = "ogc_fid") // because id is a float(!)
  private Integer id;

  private String arkenavn;

  private String arketype;

  private String kortvaerk;

  public KortvaerkDB() {
  }

  public KortvaerkDB(Integer id, String arkenavn, String arketype, String kortvaerk) {
    this.id = id;
    this.arkenavn = arkenavn;
    this.arketype = arketype;
    this.kortvaerk = kortvaerk;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
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