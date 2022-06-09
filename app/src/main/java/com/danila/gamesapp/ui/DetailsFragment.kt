package com.danila.gamesapp.ui

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.danila.gamesapp.R
import com.danila.gamesapp.database.Game
import com.danila.gamesapp.viewmodel.GameListViewModel
import com.danila.gamesapp.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment(R.layout.fragment_details) {

    private val GALLERY_REQUEST_CODE = 100
    private lateinit var gameListViewModel: GameListViewModel
    private lateinit var binding: FragmentDetailsBinding
    private var imageUri: Uri? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Инициализация биндинга
        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        gameListViewModel = ViewModelProvider(this)[GameListViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gameImage.setOnClickListener {
            launchGallery()
        }

        binding.buttonSave.setOnClickListener {
            insertDataToDatabase()
        }
    }

    //Функция добавления полей в базу данных
    private fun insertDataToDatabase() {
        //Объявление переменных
        val title = binding.etTitle.text.toString()
        val date = binding.etDate.text.toString()
        val studio = binding.etStudio.text.toString()
        val genre = binding.etGenre.text.toString()
        val rating = binding.etRating.text.toString()
        val description = binding.etDescription.text.toString()


        //Проверка данных
        if (inputCheck(title, date, studio, genre, rating) && (imageUri != null)) {
            val game = Game(0, title, date, studio, genre, rating, description,
                imageUri.toString()
            )
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
    //Функция для открытия галлереи изображений
    private fun launchGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }
    //Функция выбора и загрузки изображения
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            imageUri = data?.data!!
            activity?.let {
                Glide.with(it)
                    .load(imageUri)
                    .into(binding.gameImage)
            }
        }
    }
}

