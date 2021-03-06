package dk.dataforsyningen.arkivmeta.datamodel;

import org.hibernate.annotations.Immutable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "a_daekningomraade", schema = "arkivmeta_latest")
@Immutable
public class DaekningsomraadeDB
{
    @Id
    @Column(name = "ogc_fid") // because id is a float(!)
    private Integer id;
    @Column(name = "daekningomraade")
    private String daekningsomraade;

    public DaekningsomraadeDB()
    {
    }

    public DaekningsomraadeDB(Integer id, String daekningsomraade)
    {
        this.id = id;
        this.daekningsomraade = daekningsomraade;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getDaekningsomraade()
    {
        return daekningsomraade;
    }

    public void setDaekningsomraade(String daekningsomraade)
    {
        this.daekningsomraade = daekningsomraade;
    }
}