package com.example.movilessoftware2023a.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.ItemProducto
import com.example.movilessoftware2023a.ProductProvider


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class CarritoF : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit  var recyclerView : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.carrito, container, false)
        recyclerView = view.findViewById(R.id.cart_list_view)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false) //Set Layaout Manager as Vertical
        recyclerView.adapter = ItemProducto(ProductProvider.listaCombos)

        return view


    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CarritoF().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}