package net.nikonorov.blockchaincurrency.presentation

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.di.ComponentManager
import net.nikonorov.blockchaincurrency.presentation.info.view.PairInfoFragment
import net.nikonorov.blockchaincurrency.presentation.main.view.MainFragment
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportFragmentNavigator
import javax.inject.Inject

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

class MainActivity : AppCompatActivity() {

    private var toast: Toast? = null
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator: Navigator = object : SupportFragmentNavigator(supportFragmentManager, R.id.fragment_place) {
        override fun exit() {
            finish()
        }

        override fun showSystemMessage(message: String?) {
            toast?.cancel()
            toast = Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG)
            toast?.show()
        }

        override fun createFragment(screenKey: String?, data: Any?): Fragment {
            return when (screenKey) {
                Screen.MAIN_SCREEN -> MainFragment.newInstance()
                Screen.PAIR_DETAILS_SCREEN -> PairInfoFragment.newInstance(data as String)
                else -> throw IllegalArgumentException("Unsupported screen!")
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        ComponentManager.appComponent.inject(this)
        if (savedInstanceState == null) {
            navigatorHolder.setNavigator(navigator)
            router.newRootScreen(Screen.MAIN_SCREEN)
        }
        window.setBackgroundDrawable(null)
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
