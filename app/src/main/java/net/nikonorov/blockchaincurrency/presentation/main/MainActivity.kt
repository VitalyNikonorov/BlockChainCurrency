package net.nikonorov.blockchaincurrency.presentation.main

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoFragment
import net.nikonorov.blockchaincurrency.presentation.main.view.MainFragment

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            val mainFragment = MainFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.fragment_place, mainFragment, MainFragment::class.java.simpleName)
                    .commit()
        }
        window.setBackgroundDrawable(null)
    }

    fun openDetailsScreen() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.fragment_place, PairInfoFragment(), PairInfoFragment::class.java.simpleName)
                .commit()
    }
}
