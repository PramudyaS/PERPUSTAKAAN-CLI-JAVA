package model;


import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "perpustakaan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perpustakaan.findAll", query = "SELECT p FROM Perpustakaan p")
    , @NamedQuery(name = "Perpustakaan.findByIdBuku", query = "SELECT p FROM Perpustakaan p WHERE p.idBuku = :idBuku")
    , @NamedQuery(name = "Perpustakaan.findByJudul", query = "SELECT p FROM Perpustakaan p WHERE p.judul = :judul")
    , @NamedQuery(name = "Perpustakaan.findByPengarang", query = "SELECT p FROM Perpustakaan p WHERE p.pengarang = :pengarang")})
public class Perpustakaan implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_buku")
    private Integer idBuku;

    @Basic(optional = false)
    @Column(name = "judul")
    private String judul;

    @Basic(optional = false)
    @Column(name = "pengarang")
    private String pengarang;

    @Basic(optional = false)
    @Column(name = "isbn")
    private String isbn;

    @Basic(optional = false)
    @Column(name = "tahun_terbit")
    private String tahun_terbit;

    public Perpustakaan() {
    }

    public Perpustakaan(Integer idBuku) {
        this.idBuku = idBuku;
    }

    public Perpustakaan(Integer idBuku, String judul, String pengarang) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
    }

    public Integer getIdBuku() {
        return idBuku;
    }

    public void setIdBuku(Integer idBuku) {
        this.idBuku = idBuku;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTahun_terbit() {
        return tahun_terbit;
    }

    public void setTahun_terbit(String tahun_terbit) {
        this.tahun_terbit = tahun_terbit;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idBuku != null ? idBuku.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perpustakaan)) {
            return false;
        }
        Perpustakaan other = (Perpustakaan) object;
        if ((this.idBuku == null && other.idBuku != null) || (this.idBuku != null && !this.idBuku.equals(other.idBuku))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Perpustakaan[ idBuku=" + idBuku + " ]";
    }
    
}
