package com.example.movilessoftware2023a

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

import com.example.movilessoftware2023a.fragments.*

import com.google.android.material.bottomnavigation.BottomNavigationView


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fastFood = ProductoFragment()
        val cart = CarritoF()


        /* Set Fast Food fragment when the app starts*/
        setCurrentFragment(fastFood)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        /* On click for each icon set a fragment */
        bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.fast_food -> {
                    setCurrentFragment(fastFood)
                    true
                }
                R.id.cart -> {
                    setCurrentFragment(cart)
                    true
                }
                else -> {
                    false
                }

            }
        }



    }

    /* Function that set a fragment */
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container_view,fragment)
            commit()
        }
    }
}