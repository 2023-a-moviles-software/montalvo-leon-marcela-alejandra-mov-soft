package com.example.movilessoftware2023a

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.entities.Producto

class ItemProducto(private val productList:List<Producto>) : RecyclerView.Adapter<ItemProducto.ViewHolder>() {

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var itemImage: ImageView
        var itemName: TextView
        var itemPrice: TextView

        init {
            itemImage = itemView.findViewById(R.id.item_image_view)
            itemName = itemView.findViewById(R.id.item_name_text_view)
            itemPrice = itemView.findViewById(R.id.item_price_text_view)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.producto_carrito,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
       return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = productList[position].name
        holder.itemPrice.text = productList[position].price.toString()
        holder.itemImage.setImageResource(productList[position].image)
    }
}