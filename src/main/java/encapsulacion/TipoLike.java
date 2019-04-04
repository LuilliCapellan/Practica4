package encapsulacion;

public enum TipoLike {

    LIKE("LIKE"),
    DISLIKE("DISLIKE");

    private String like;

    TipoLike(String like) {
        this.like = like;
    }

    public String getLike() {
        return like;
    }
}
