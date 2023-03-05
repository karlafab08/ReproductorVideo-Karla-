package com.example.reproductor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView listaVideos;
    private VideoListAdapter listaVideosAdapter;
    private List<VideoElemento> videoElementos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaVideos = findViewById(R.id.video_lista);

        //Definimos que nuestra lista con RecyclerView sera vertical
        listaVideos.setLayoutManager(new LinearLayoutManager(this));

        //Declaramos una arrayLista la cual tendra objectos Videos
        videoElementos = new ArrayList<>();
        cargarListaVideos();

        //Ahora con VideoListAdapter el cual crea y gestiona una lista de elementos de un video,
        //le pasamos el arrayList videosElementos
        listaVideosAdapter = new VideoListAdapter(this, videoElementos);

        //Ahora solo establecemos la lista a nuestro RecyclerView es aquí cuando se visualiza en
        //la app.
        listaVideos.setAdapter(listaVideosAdapter);
    }

    private void cargarListaVideos() {
        //Columnas que se desean recuperar de la base de datos de videos del dispositivo en este caso obtendremos
        //ID, nombre, y la ruta del video
        String[] columnas = {MediaStore.Video.Media._ID, MediaStore.Video.Media.DISPLAY_NAME, MediaStore.Video.Media.DATA};

        //Establecemos en como obtendremos los videos en este caso se ordenaran por la fecha de manera descendente
        String ordenar = MediaStore.Video.Media.DATE_ADDED + " DESC";


        //Ahora realizamos la consulta con getContentResolver().query() donde query() recibe 5 parametros
        //La URI, las columnas, restricción al seleccionar los videos en este caso es null, argumentos de selección
        //el cual es null, y la manera en como se ordenaran los resultados.
        Cursor videos = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columnas, null, null, ordenar);
        //El resultados de esta consulta sera un Cursor que puede ser usado para recorrer dichos resultados,
        //cada fila de videos representara un video y podremos obtener los valores de las diferentes columnas
        //usando sus métodos como getInt(), getString() etc.

        //Validamos que videos tenga resultados
        if (videos != null && videos.moveToFirst()) {
            //Recorremos videos usando el método moveToNext()
            do {

                String titulo = videos.getString(videos.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                String ruta = videos.getString(videos.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));

                //Creamos una minatura con createVideoThumbnail() el cual requiere la ruta del video para genera
                //miniatura y el tamaño de la miniatura
                Bitmap miniatura = ThumbnailUtils.createVideoThumbnail(ruta, MediaStore.Video.Thumbnails.MINI_KIND);

                //Ahora creamos una instancia de mi clase VideoElemento en donde le pasaremos el titulo, ruta y miniatura
                //dicha instancia de almacena en el arreglo videosElementos()
                videoElementos.add(new VideoElemento(titulo, ruta, miniatura));
            } while (videos.moveToNext());
            videos.close(); //Cerramos el objeto Cursor videos

            //En este punto ya tendremos todos los objetos almacenados en el arreglo videosElementos
            //Cada objecto representa un video.
            //Y este objeto es una instancia de la clase VideoElemento
        }
    }
}