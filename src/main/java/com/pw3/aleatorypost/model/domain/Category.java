package com.pw3.aleatorypost.model.domain;

public enum Category {
    
    SPORTS("Esposrtes"),
    GENERAL_CULTURE("Cultura geral"),
    POLITICS("Política"),
    MUSIC("Musica"),
    VIDEO_GAMES("Video Games"),
    RELIGION("Relegião"),
    SCIENCE("Ciência");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
