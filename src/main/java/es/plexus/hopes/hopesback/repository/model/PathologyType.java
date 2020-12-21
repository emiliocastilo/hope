package es.plexus.hopes.hopesback.repository.model;

public enum PathologyType {
    DERMATOLOGIA (1l),
    REUMATOLOGIA (2l),
    VIH (3l);

    private Long codigo;

    PathologyType(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }
}
