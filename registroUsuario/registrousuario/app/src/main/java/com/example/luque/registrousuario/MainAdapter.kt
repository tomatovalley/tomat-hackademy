package com.example.luque.registrousuario
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_eventos_row.view.*


class MainAdapter(val homeFeed: Array<HomeFeed>): RecyclerView.Adapter<CustomViewHolder>() {

    //val listaDeEventos = listOf("Primer evento", "Segundo evento", "Tercer evento", "Otrooooos eventos","1","2","3","4")
    override fun getItemCount(): Int {
        return homeFeed.size

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.activity_eventos_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val titulo = listaDeEventos.get(p1)
        val titulo = homeFeed[position]
        //val titulo = listaDeEventos.get(position)
        holder.view.name_eventos?.text = titulo.evento
        holder.view.name_sede?.text = titulo.sede

        holder.detalle =titulo
    }
}

class CustomViewHolder(val view: View, var detalle: HomeFeed? = null): RecyclerView.ViewHolder(view) {

    init {
        view.setOnClickListener {
            val intent = Intent(view.context, DetalleEvento::class.java)

            intent.putExtra("Evento", detalle?.evento)
            intent.putExtra("Sede", detalle?.sede)

            view.context.startActivity(intent)


        }
    }
}
