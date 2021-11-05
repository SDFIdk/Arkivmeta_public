package dk.dataforsyningen.arkivmeta.datamodel;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "a_maalestok")
public class MaalestokDB
{
    @Id
    @Column(name = "ogc_fid") // because id is a float(!)
    private Integer id;
    private String maalestok;

    public MaalestokDB()
    {
    }

    public MaalestokDB(Integer id,
                       String maalestok)
    {
        this.id = id;
        this.maalestok = maalestok;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getMaalestok()
    {
        return maalestok;
    }

    public void setMaalestok(String maalestok)
    {
        this.maalestok = maalestok;
    }
}