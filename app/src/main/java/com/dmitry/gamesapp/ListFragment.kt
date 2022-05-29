package com.dmitry.gamesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dmitry.gamesapp.databinding.FragmentListBinding

private lateinit var binding: FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(context)

        return binding.root
    }

    private val gameListViewModel: GameListViewModel by lazy {
        ViewModelProvider(this).get(GameListViewModel::class.java)
    }

    //Функция вызывается, чтобы получить экземпляр фрагмента
    companion object {
        fun newInstance(): ListFragment {
            return ListFragment()
        }
    }


}