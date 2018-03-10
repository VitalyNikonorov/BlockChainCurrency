package net.nikonorov.blockchaincurrency.presentation.info.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.di.ComponentManager
import net.nikonorov.blockchaincurrency.presentation.info.presenter.PairInfoPresenter
import javax.inject.Inject

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */

class PairInfoFragment : Fragment(), PairInfoView {

    @Inject
    lateinit var presenter: PairInfoPresenter

    companion object {
        private val KEY_PAIR = "key_pair"

        fun newImstance(pair: String): PairInfoFragment {
            val fragment =  PairInfoFragment()
            val args = Bundle()
            args.putString(KEY_PAIR, pair)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.getPairInfoScreenComponent().inject(this)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.pair_info, container, false)
    }

    override fun onDestroy() {
        super.onDestroy()
        ComponentManager.removePairInfoScreenComponent()
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

}