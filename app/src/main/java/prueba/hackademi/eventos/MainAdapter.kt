package prueba.hackademi.eventos

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.eventos_row.view.*



class MainAdapter: RecyclerView.Adapter<CustomViewHolder>() {

    val listaDeEventos = listOf("Primer evento", "Segundo evento", "Tercer evento", "Otrooooos eventos","1","2","3","4")
    override fun getItemCount(): Int {
        return listaDeEventos.size
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(p0.context)
        val cellForRow = layoutInflater.inflate(R.layout.eventos_row, p0, false)
        return CustomViewHolder(cellForRow)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {

        //val titulo = listaDeEventos.get(p1)
        val titulo = listaDeEventos.get(position)
        holder?.view?.name_eventos?.text = titulo
    }
}

class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){

}