package jdev.mentoria.lojavirtual.dto;

public class NotaFiscalVendaDto {

    private Long id;

    private String numero;

    private String serie;

    private String tipo;

    private String xml;

    private String pdf;

    private VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO = new VendaCompraLojaVirtualDTO();

    private String empresa;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public VendaCompraLojaVirtualDTO getVendaCompraLojaVirtualDTO() {
        return vendaCompraLojaVirtualDTO;
    }

    public void setVendaCompraLojaVirtualDTO(VendaCompraLojaVirtualDTO vendaCompraLojaVirtualDTO) {
        this.vendaCompraLojaVirtualDTO = vendaCompraLojaVirtualDTO;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
}
