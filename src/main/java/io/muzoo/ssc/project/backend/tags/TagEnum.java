package io.muzoo.ssc.project.backend.tags;

public enum TagEnum {
    ROMANCE("Romance"),
    DRAMA("Drama"),
    ACTION("Action"),
    FANTASY("Fantasy"),
    ANIME("Anime"),
    HORROR("Horror"),
    MYSTERY("Mystery");

    private String tag;

    TagEnum(String tag){
        this.tag = tag;
    }

    public String getTag(){
        return tag;
    }

}
