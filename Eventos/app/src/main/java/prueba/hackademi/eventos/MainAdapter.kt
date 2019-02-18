package prueba.hackademi.eventos

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.eventos_row.view.*
import org.json.JSONObject
import java.net.URL


class MainAdapter(val homeFeed: Array<HomeFeed>): RecyclerView.Adapter<CustomViewHolder>() {

    //val listaDeEventos = listOf("Primer evento", "Segundo evento", "Tercer evento", "Otrooooos eventos","1","2","3","4")
    override fun getItemCount(): Int {
        return homeFeed.size

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.eventos_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val titulo = listaDeEventos.get(p1)
        val titulo = homeFeed[position]
        //val titulo = listaDeEventos.get(position)
        holder.view.name_eventos?.text = titulo.name
        holder.view.name_sede?.text = titulo.place
        DownLoadImageTask(holder.view.foto_evento)
            .execute(titulo.image)


        holder.detalle =titulo
    }
}

class CustomViewHolder(val view: View, var detalle: HomeFeed? = null): RecyclerView.ViewHolder(view){

    init {
        view.setOnClickListener{
            val intent = Intent(view.context,DetalleEvento::class.java)

            intent.putExtra("Evento", detalle?.name)
            intent.putExtra("Sede", detalle?.place)

            view.context.startActivity(intent)


        }
    }

}
private class DownLoadImageTask(internal val imageView: ImageView) : AsyncTask<String, Void, Bitmap?>() {
    override fun doInBackground(vararg urls: String): Bitmap? {
        val urlOfImage = urls[0]
        return try {
            val inputStream = URL(urlOfImage).openStream()
            BitmapFactory.decodeStream(inputStream)
        } catch (e: Exception) { // Catch the download exception
            e.printStackTrace()
            null
        }
    }

    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            // Display the downloaded image into image view
            imageView.setImageBitmap(result)
        }
    }
}