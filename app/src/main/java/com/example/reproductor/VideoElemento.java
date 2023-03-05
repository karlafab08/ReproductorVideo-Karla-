package com.example.reproductor;

import android.graphics.Bitmap;

//Esta clase vendría siendo el modelo de un video cada instancia de esta clase representaría un video
//con su titulo, ruta, y miniatura los cuales serán visualizados en la app.
public class VideoElemento {
    private String titulo;
    private String ruta;
    private Bitmap miniatura;

    public VideoElemento(String titulo, String ruta, Bitmap miniatura) {
        this.titulo = titulo;
        this.ruta = ruta;
        this.miniatura = miniatura;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getRuta() {
        return ruta;
    }

    public Bitmap getMiniatura(){
        return miniatura;
    }

}