package net.nikonorov.blockchaincurrency.presentation.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.main_fragment.*
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.di.ComponentManager
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenter
import javax.inject.Inject

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class MainFragment : Fragment(), MainView {
    @Inject
    lateinit var presenter: MainPresenter
    private lateinit var adapter: PairAdapter

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ComponentManager.getMainScreenComponent().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        ComponentManager.removeMainScreenComponent()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainRecyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PairAdapter(presenter)
        mainRecyclerView.adapter = adapter
        activity?.title = getString(R.string.main_screen_title)
        repeatButton.setOnClickListener({ presenter.onRepeatLoadingClick() })
    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView()
    }

    override fun setProgressBarVisible(visible: Boolean) {
        progressBar.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setEmptyListVisible(visible: Boolean) {
        emptyList.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun showCurrencies(list: List<String>) {
        adapter.setData(list)
    }

    override fun setMainListVisible(visible: Boolean) {
        mainRecyclerView.visibility = if (visible) View.VISIBLE else View.GONE
    }

    override fun setErrorViewVisible(visible: Boolean) {
        errorView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
