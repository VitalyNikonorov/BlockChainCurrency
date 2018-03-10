package net.nikonorov.blockchaincurrency.presentation.main.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import net.nikonorov.blockchaincurrency.R
import net.nikonorov.blockchaincurrency.di.ComponentManager
import net.nikonorov.blockchaincurrency.presentation.MainActivity
import net.nikonorov.blockchaincurrency.presentation.main.presenter.MainPresenter
import javax.inject.Inject

/**
 * Created by Vitaly Nikonorov on 08.03.2018.
 * email@nikonorov.net
 */
class MainFragment : Fragment(), MainView {
    @Inject
    lateinit var presenter: MainPresenter

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: PairAdapter
    private lateinit var emptyList: View

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
        val view = layoutInflater.inflate(R.layout.main_fragment, container, false)
        progressBar = view.findViewById(R.id.progress_bar)
        recyclerView = view.findViewById(R.id.main_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = PairAdapter(presenter)
        recyclerView.adapter = adapter
        emptyList = view.findViewById(R.id.empty_list)
        return view
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

    override fun showError(message: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showCurrencies(list: List<String>) {
        adapter.setData(list)
    }

    override fun setMainListVisible(visible: Boolean) {
        recyclerView.visibility = if (visible) View.VISIBLE else View.GONE
    }
}
