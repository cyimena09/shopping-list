package be.ifosup.produit;

public class Produit {
    // ATTRIBUTES
    private Long idProd;
    private String nomPro;
    private Long idCategory;
    private Long idMes;

    public Produit(Long idProd, String nomPro, Long idCategory, Long idMes){
        this.idProd = idProd;
        this.nomPro = nomPro;
        this.idCategory = idCategory;
        this.idMes = idMes;
    }

    public Long getIdProd() {
        return idProd;
    }

    public void setIdProd(Long idProd) {
        this.idProd = idProd;
    }

    public String getNomPro() {
        return nomPro;
    }

    public void setNomPro(String nomPro) {
        this.nomPro = nomPro;
    }

    public Long getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(Long idCategory) {
        this.idCategory = idCategory;
    }

    public Long getIdMes() {
        return idMes;
    }

    public void setIdMes(Long idMes) {
        this.idMes = idMes;
    }

}
