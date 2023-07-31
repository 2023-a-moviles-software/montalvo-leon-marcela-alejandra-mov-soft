package com.example.movilessoftware2023a.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movilessoftware2023a.R
import com.example.movilessoftware2023a.ProductAdapter
import com.example.movilessoftware2023a.ProductProvider


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProductoFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    lateinit  var recyclerView :RecyclerView
    lateinit var recyclerViewPopulares: RecyclerView


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

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.rv_listas_tipos_productos, container, false)

        // Set Recycler view combos clásicos with its provider
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false) //Set Layaout Manager as Horizontal
        recyclerView.adapter = ProductAdapter(ProductProvider.listaCombos)

        // Set Recycler view Ofertas  its provider
        recyclerViewPopulares = view.findViewById(R.id.recyclerViewPopulares)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewPopulares.adapter = ProductAdapter(ProductProvider.listaOfertas)


        // Set Recycler view Postres with its provider
        recyclerViewPopulares = view.findViewById(R.id.recyclerViewPostres)
        recyclerViewPopulares.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        recyclerViewPopulares.adapter = ProductAdapter(ProductProvider.listaPostres)





        return view
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProductoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}