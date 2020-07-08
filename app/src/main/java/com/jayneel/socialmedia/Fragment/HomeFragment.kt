package com.jayneel.socialmedia.Fragment

import android.content.Context
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jayneel.socialmedia.Adapter.postAdapter
import com.jayneel.socialmedia.Adapter.user_Apater
import com.jayneel.socialmedia.Model.PoastData
import com.jayneel.socialmedia.Model.userModel
import com.jayneel.socialmedia.R
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.android.synthetic.main.posts_rec.*
import kotlinx.android.synthetic.main.search_fragment.*

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var ad: postAdapter
    private lateinit var viewModel: HomeViewModel
    var postIteamcount:Int=0
    var lastvisible:Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)

        viewModel.getpost(refreslayout).observe(viewLifecycleOwner, Observer {
            ad=postAdapter(context!!,it)
            rvpost.adapter = ad
            rvpost.layoutManager=LinearLayoutManager(context!!.applicationContext, RecyclerView.VERTICAL, false)
        })
        refreslayout.setOnRefreshListener {

            viewModel.getpost(refreslayout)

        }
        }


}

