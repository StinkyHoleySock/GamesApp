package com.danila.gamesapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.danila.gamesapp.R
import com.danila.gamesapp.viewmodel.GameListViewModel
import com.danila.gamesapp.databinding.FragmentListBinding

private lateinit var binding: FragmentListBinding

class ListFragment: Fragment(R.layout.fragment_list) {

    private lateinit var gameListViewModel: GameListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Инициализация RecyclerView
        val adapter = GameAdapter()
        val recyclerView = binding.recyclerView

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        //Инициализация ViewModel
        gameListViewModel = ViewModelProvider(this)[GameListViewModel::class.java]
        gameListViewModel.readData.observe(viewLifecycleOwner) { game ->
            adapter.setData(game)
        }

        binding.addGame.setOnClickListener{
            findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
        }
    }
}


