package com.tesmigue.chatapp.presentation.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tesmigue.chatapp.databinding.ItemMensajeBinding

class MensajeAdapter : RecyclerView.Adapter<MensajeAdapter.MensajeViewHolder>() {

    data class MensajeUi(
        val texto: String,
        val uriImagen: String? = null,
        val uriArchivo: String? = null,
        val nombreArchivo: String? = null
    )

    private val mensajes = mutableListOf<MensajeUi>()

    fun submitList(nuevos: List<MensajeUi>) {
        mensajes.clear()
        mensajes.addAll(nuevos)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensajeViewHolder {
        val binding = ItemMensajeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MensajeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MensajeViewHolder, position: Int) {
        val mensaje = mensajes[position]


        if (!mensaje.texto.isNullOrBlank()) {
            holder.binding.tvMensaje.visibility = View.VISIBLE
            holder.binding.tvMensaje.text = mensaje.texto
        } else {
            holder.binding.tvMensaje.visibility = View.GONE
        }


        if (!mensaje.uriImagen.isNullOrBlank()) {
            holder.binding.ivImagen.visibility = View.VISIBLE
            holder.binding.ivImagen.setImageURI(Uri.parse(mensaje.uriImagen))
        } else {
            holder.binding.ivImagen.visibility = View.GONE
        }


        if (!mensaje.uriArchivo.isNullOrBlank()) {
            val nombreArchivo = Uri.parse(mensaje.uriArchivo).lastPathSegment ?: "📄 Archivo"
            holder.binding.tvArchivo.visibility = View.VISIBLE
            holder.binding.tvArchivo.text = "📄 $nombreArchivo"
        } else {
            holder.binding.tvArchivo.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int = mensajes.size

    class MensajeViewHolder(val binding: ItemMensajeBinding) : RecyclerView.ViewHolder(binding.root)
}
