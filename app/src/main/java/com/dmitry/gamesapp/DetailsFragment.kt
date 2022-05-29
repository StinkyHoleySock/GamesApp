package com.dmitry.gamesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dmitry.gamesapp.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private lateinit var game: Game
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //Создание экземпляра класса
        //game = Game()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Инициализация биндинга
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        //Объявление переменных
        val title = binding.title
        val date = binding.date
        val studio = binding.studio
        val genre = binding.genre
        val rating = binding.rating
        val description = binding.description

        return binding.root
    }


}