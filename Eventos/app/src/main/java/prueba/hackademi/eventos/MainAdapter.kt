package prueba.hackademi.eventos

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.opengl.Visibility
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.eventos_row.view.*
import org.json.JSONObject
import java.net.URL


class MainAdapter(val homeFeed: Array<HomeFeed>, val tipo: Boolean): RecyclerView.Adapter<CustomViewHolder>() {

    //val listaDeEventos = listOf("Primer evento", "Segundo evento", "Tercer evento", "Otrooooos eventos","1","2","3","4")
    override fun getItemCount(): Int {
        return homeFeed.size

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.eventos_row, p0, false)
        return CustomViewHolder(cellForRow,tipo=tipo)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val titulo = homeFeed[position]
        //tenemos diferencias para eventos y emprendimientos

        if (tipo) {
            //val titulo = listaDeEventos.get(position)
            holder.view.name_eventos?.text = titulo.name
            holder.view.name_sede?.text = titulo.place
            Picasso.with(holder.view.context)
                .load(titulo.image)
                .into(holder.view.foto_evento)
            holder.detalle = titulo

        }else{

            //val titulo = listaDeEventos.get(position)
            holder.view.name_eventos?.text = titulo.name
            holder.view.name_sede?.visibility = View.GONE
            /*DownLoadImageTask(holder.view.foto_evento)
                .execute(titulo.image)
            */
            Picasso.with(holder.view.context)
                .load(titulo.image)
                .into(holder.view.foto_evento)

            holder.detalle = titulo

        }
    }
}

class CustomViewHolder(val view: View, var detalle: HomeFeed? = null,val tipo: Boolean=true): RecyclerView.ViewHolder(view){
    init {
        if(tipo) {
            view.setOnClickListener {
                val intent = Intent(view.context, DetalleEvento::class.java)

                intent.putExtra("Evento", detalle?.name)
                intent.putExtra("Sede", detalle?.place)
                intent.putExtra("Descripcion", detalle?.description)
                intent.putExtra("Imagen",detalle?.image)

                view.context.startActivity(intent)


            }
        }else{
            view.setOnClickListener {
                val intent = Intent(view.context, DetalleEmprendimiento::class.java)

                intent.putExtra("Emprendimiento", detalle?.name)
                intent.putExtra("Descripción", detalle?.description)
                intent.putExtra("Imagen",detalle?.image)

                view.context.startActivity(intent)
            }
        }

    }
}