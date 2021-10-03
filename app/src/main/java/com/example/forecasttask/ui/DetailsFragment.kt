package com.example.forecasttask.ui

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.example.forecasttask.R
import com.example.forecasttask.helper.NewsAdapter
import com.example.forecasttask.utils.ConnectionDetector
import com.example.forecasttask.viewModel.DetailsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.details_fragment.*

@AndroidEntryPoint
class DetailsFragment : Fragment(), View.OnClickListener {
    private lateinit var connectionDetector: ConnectionDetector
    private lateinit var cityName: String
    private lateinit var newsAdapter: NewsAdapter

    companion object {
        fun newInstance() = DetailsFragment()
    }

    private lateinit var viewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        cityName = arguments?.getString("cityName").toString()
        cityTv.text = cityName
        connectionDetector = ConnectionDetector(requireActivity())
        newsRV.apply {
            layoutManager =
                GridLayoutManager(activity, 1, GridLayoutManager.VERTICAL, false)
            itemAnimator = DefaultItemAnimator()

        }
        retryBtn.setOnClickListener(this)
        getNews()
        collectData()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun collectData() {
        viewModel.list?.observe(requireActivity(), Observer {
            Log.d(
                "TAG",
                "testTag collectData: : ${it?.size}"
            )
           newsAdapter= NewsAdapter(it,requireContext())
            newsAdapter.notifyDataSetChanged()
            newsRV.adapter=newsAdapter
            swipe.isRefreshing = false
            swipe.isEnabled = false


        }
        )
    }



private fun getNews() {
    swipe.isRefreshing = true

    if (connectionDetector.checkInternetConnection()) {
        noConnectionLin.visibility = View.GONE
        newsRV.visibility = View.VISIBLE

        Log.d(
            "TAG", "logTag getNews: checkInternetConnection=True"
        )
        viewModel.getNews(cityName)


    } else {
        noConnectionLin.visibility = View.VISIBLE
        newsRV.visibility = View.GONE
        Log.d(
            "TAG", "logTag getNews: checkInternetConnection=false"
        )

    }
}


override fun onClick(v: View?) {


    if (v == retryBtn) {
        getNews()
    }
}


}