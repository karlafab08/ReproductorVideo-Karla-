package com.example.reproductor;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VideoListAdapter extends RecyclerView.Adapter<VideoListAdapter.VideoViewHolder> {

    //Declaramos una lista con los parametros de mi Clase VideoElemento
    private List<VideoElemento> videoElementosList;

    //Context nos proporciona acceso a los recursos y servicios de nuestra aplicación.
    private Context contexto;


    //Recibe el contexto y un Lista de todos lo objetos es decir videos
    public VideoListAdapter(Context context, List<VideoElemento> videoElementos) {
        this.contexto = context;
        this.videoElementosList = videoElementos;
    }

    @NonNull
    @Override
    //Este evento se ejecuta al momento de que se crea una instancia de esta Clase
    //Por lo tanto se ejecutara al momento que le mandemos la lista de videos
    //Lo que se hace aquí es crear la vista mi mis videos y que se visualizen en la app.
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(contexto).inflate(R.layout.video_lista_elemento, parent, false);
        return new VideoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        VideoElemento videoElemento = videoElementosList.get(position);
        holder.bind(videoElemento);
    }

    //Devuelve el número de elemento que se visualizaran en la lista con RecyclerView
    @Override
    public int getItemCount() {
        return videoElementosList.size();
    }

    //Se ocupa para mantener acutalizada la vista de un video en RecyclerView
    public class VideoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView miniatura;
        private TextView titulo;

        //videoVista es basicamente la plantilla creada en video_lista_elemento.xml
        public VideoViewHolder(@NonNull View videoVista) {
            super(videoVista);

            //En esta parte obtenemos los elementos de video_lista_elemento.xml
            miniatura = videoVista.findViewById(R.id.video_miniatura);
            titulo = videoVista.findViewById(R.id.video_titulo);

            //A cada video se le agrega un evento para poder pulsarlo y abrir dicho video.
            videoVista.setOnClickListener(this);
        }

        //Método para actualizar la vista con los datos del objeto VideoElemento recordando que este objeto
        //es un video.
        public void bind(VideoElemento videoElemento) {
            miniatura.setImageBitmap(videoElemento.getMiniatura());//Obtnemos con un getter y Agregamos la miniatura
            titulo.setText(videoElemento.getTitulo());//Obtenemos con un getter y agregamos el titulo
        }

        //Cada que se pulse sobre un video se debera rederijir a ReproductorVideoActivity
        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();//Obtenemos la posición del elemento al que se le hizo un click

            //Obtenemos el video con la posición
            VideoElemento videoElemento = videoElementosList.get(position);

            Intent intent = new Intent(contexto, ReproductorVideoActivity.class);
            intent.putExtra("ruta", videoElemento.getRuta()); //Mandamos la ruta del video
            contexto.startActivity(intent);//Abrimos el ReproductorVideoActivity
        }


    }


}
