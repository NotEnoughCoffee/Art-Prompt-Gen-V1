package dev.tg;

public enum Style {
    //Various Art Styles and Mediums
    ASCII_ART, ABSTRACT_ART, ART_DECO, BAROQUE, EXPRESSIONISM, FAUVISM, GLITCH_ART, GRAFFITI_STYLE, IMPRESSIONISM, SYNCHROMISM, CUBISM, SURREALISM, MANNERISM, MINIMALISM, PIXEL_ART, POP_ART, PSYCHEDELIC_ART, ROMANTICISM, STREET_ART, SUPER_FLAT, SUPREMATISM, TONALISM, ANCIENT_ART, ART_FROM_AROUND_THE_WORLD, CAVE_PAINTING, PATTERNED, DOTS, SHAPES, WATERCOLOR, PAINTED, CRAYON, COLOR_PENCIL, INK, GLITTER, SCULPTURE, COLLAGE;
    public String getRarity(){
        return switch(this){
            case ART_FROM_AROUND_THE_WORLD, PSYCHEDELIC_ART, SYNCHROMISM, IMPRESSIONISM, MINIMALISM, GRAFFITI_STYLE, WATERCOLOR, PAINTED, PATTERNED, SHAPES -> "NORMAL";
            case ART_DECO, ANCIENT_ART, BAROQUE, ABSTRACT_ART, EXPRESSIONISM, FAUVISM, GLITCH_ART, CUBISM, SURREALISM, PIXEL_ART, POP_ART, ROMANTICISM, STREET_ART, SUPER_FLAT, SUPREMATISM, TONALISM, GLITTER, INK, COLOR_PENCIL, CRAYON, MANNERISM -> "RARE";
            case ASCII_ART, SCULPTURE, COLLAGE, DOTS, CAVE_PAINTING -> "SUPER_RARE";
            default -> "null";
        };
    }
}
