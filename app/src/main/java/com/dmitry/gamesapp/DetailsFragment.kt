package com.dmitry.gamesapp

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.dmitry.gamesapp.database.Game
import com.dmitry.gamesapp.database.GameListViewModel
import com.dmitry.gamesapp.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private lateinit var gameListViewModel: GameListViewModel
    private lateinit var binding: FragmentDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Инициализация биндинга
        binding = FragmentDetailsBinding.inflate(inflater, container, false)



        gameListViewModel = ViewModelProvider(this).get(GameListViewModel::class.java)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener {
            insertDataToDatabase()
        }
    }

    //Функция добавления полей в базу данных
    private fun insertDataToDatabase() {
        //Объявление переменных
        val title = binding.etTitle.text.toString()
        val date = binding.etDate.toString()
        val studio = binding.etStudio.toString()
        val genre = binding.etGenre.toString()
        val rating = binding.etRating.toString()
        val description = binding.etDescription.toString()

        //Проверка данных
        if (inputCheck(title, date, studio, genre, rating)) {
            val game = Game(0, title, date, studio, genre, rating, description, R.drawable.ic_game_image)
            //Добавление игры
            gameListViewModel.addGame(game)
            //Вызов сообщения об успехе
            Toast.makeText(requireContext(), "Игра добавлена в список", Toast.LENGTH_SHORT).show()
            //Навигация на фрагмент со списком
            findNavController().navigate(R.id.action_detailsFragment_to_listFragment)
        } else {
            //Сообщение об ошибке
            Toast.makeText(requireContext(), "Заполните поля!", Toast.LENGTH_SHORT).show()
        }


    }

    //Функция проверки данных (наличие необходимых полей)
    private fun inputCheck(
        title: String, date: String, studio: String, genre: String, rating: String
    ) : Boolean {

        return ((title.isNotEmpty()) && (date.isNotEmpty()) && (studio.isNotEmpty())
                && (genre.isNotEmpty()) && (rating.isNotEmpty()))

    }
}