package com.example.movilessoftware2023a
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.entities.Producto

class ProductProvider {
    companion object {
        val listaCombos = listOf<Producto>(
            Producto(1,"Combo Grande Doble Cuarto de Libra",12.5,R.drawable.mc1),
            Producto(1,"Combo Grande McNifica",10.5, R.drawable.mc2),
            Producto(1,"Combo Grande McNuggets x20",8.5,R.drawable.mc1),
            Producto(1,"Combo Grande McNuggets x10",10.5, R.drawable.mc2),
            Producto(1,"Combo grande McBacon",13.5,R.drawable.mc1),
            Producto(1,"Combo Grande Big Mac",17.5, R.drawable.mc2),
            Producto(1,"Combo Grande McPollo Deluxe",10.5,R.drawable.mc3),
        )
        val listaOfertas = listOf<Producto>(
            Producto(1,"Family Box con Papas y Bebidas",20.5,R.drawable.mc3),
            Producto(1,"Duo Pack Imperdible de Hambuguesa con queso",15.5,R.drawable.mc4),
            Producto(1,"Promo McFlurry Oreo 2x1",13.5,R.drawable.mc3),
            Producto(1,"Family Big Box con Papas y Bebidas",13.5,R.drawable.mc4),
            )
        val listaPostres = listOf<Producto>(
            Producto(1,"McFlurry Chips Ahoy",5.5,R.drawable.mcflurry),
            Producto(1,"McFlurry Chips XXL",4.5,R.drawable.mcflurry2),
            Producto(1,"McFlurry M&M's",6.5,R.drawable.mcflurry),
        )
    }
}