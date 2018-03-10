package net.nikonorov.blockchaincurrency.presentation.info.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.pair_info.*
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.data.entity.PairInfo
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
        private const val KEY_PAIR = "key_pair"

        fun newInstance(pair: String): PairInfoFragment {
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pair = arguments?.getString(KEY_PAIR) ?: throw IllegalArgumentException("Pair not initialized!")
        activity?.title = getString(R.string.pair_screen_title, pair)
        repeatButton.setOnClickListener({ presenter.onRetryLoadingClick() })
    }

    override fun onDestroy() {
        super.onDestroy()
        ComponentManager.removePairInfoScreenComponent()
    }

    override fun onStart() {
        super.onStart()
        val pair = arguments?.getString(KEY_PAIR) ?: throw IllegalArgumentException("Pair not initialized!")
        presenter.initPair(pair)
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setProgressBarVisible(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showInfo(pairInfo: PairInfo) {
        midPrice.text = pairInfo.midPrice
        bidPrice.text = pairInfo.bidPrice
        askPrice.text = pairInfo.askPrice
        lastPrice.text = pairInfo.lastPrice
        lowestPrice.text = pairInfo.lowestPrice
        highestPrice.text = pairInfo.highestPrice
        timestamp.text = pairInfo.timeStamp
    }

    override fun setMainViewVisible(visible: Boolean) {
        mainView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setErrorViewVisible(visible: Boolean) {
        errorView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}