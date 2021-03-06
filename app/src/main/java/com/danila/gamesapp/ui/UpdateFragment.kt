package com.danila.gamesapp.ui

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.danila.gamesapp.R
import com.danila.gamesapp.database.Game
import com.danila.gamesapp.databinding.FragmentUpdateBinding
import com.danila.gamesapp.viewmodel.GameListViewModel

private lateinit var binding: FragmentUpdateBinding

class UpdateFragment: Fragment(R.layout.fragment_update) {

    private val GALLERY_REQUEST_CODE = 100
    private lateinit var gameListViewModel: GameListViewModel
    private var imageUri: Uri? = null
    private val args by navArgs<UpdateFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        gameListViewModel = ViewModelProvider(this)[GameListViewModel::class.java]

        //Обновление данных
        with(binding) {
            etTitle.setText(args.currentGame.title)
            etDate.setText(args.currentGame.date)
            etStudio.setText(args.currentGame.studio)
            etGenre.setText(args.currentGame.genre)
            etRating.setText(args.currentGame.rating)
            etDescription.setText(args.currentGame.description)

            activity?.let {
                Glide.with(it)
                    .load(args.currentGame.image)
                    .into(gameImage)
            }

            gameImage.setOnClickListener {
                launchGallery()
            }
            //Сохранение изменённых данных
            buttonUpdate.setOnClickListener {
                updateDataInDatabase()
            }
        }

        //Добавление меню для удаления игры
        setHasOptionsMenu(true)

        return binding.root
    }

    //Функция обновления полей
    private fun updateDataInDatabase() {

        //Объявление переменных
        val title = binding.etTitle.text.toString()
        val date = binding.etDate.text.toString()
        val studio = binding.etStudio.text.toString()
        val genre = binding.etGenre.text.toString()
        val rating = binding.etRating.text.toString()
        val description = binding.etDescription.text.toString()


        //Проверка данных
        if (inputCheck(title, date, studio, genre, rating) && (imageUri != null)) {
            val updateGame = Game(args.currentGame.id, title, date, studio, genre, rating, description,
                imageUri.toString()
            )
            //Добавление игры
            gameListViewModel.updateGame(updateGame)
            //Вызов сообщения об успехе
            Toast.makeText(requireContext(), "Данные обновлены", Toast.LENGTH_SHORT).show()
            //Навигация на фрагмент со списком
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            //Сообщение об ошибке
            Toast.makeText(requireContext(), "Заполните поля или обновите картинку", Toast.LENGTH_SHORT).show()
        }
    }

    //Функция проверки данных (наличие необходимых полей)
    private fun inputCheck(
        title: String, date: String, studio: String, genre: String, rating: String
    ) : Boolean {

        return ((title.isNotEmpty()) && (date.isNotEmpty()) && (studio.isNotEmpty())
                && (genre.isNotEmpty()) && (rating.isNotEmpty()))

    }

    //Переопределние втроенных методов для меню
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteGame()
        }
        return super.onOptionsItemSelected(item)
    }

    //Функция для удаления игры из списка
    private fun deleteGame() {

        //Создание диалогового окна
        val builder = AlertDialog.Builder(requireContext())

        builder.apply {
            setPositiveButton("Да") { _, _, ->
                gameListViewModel.deleteGame(args.currentGame) //Удалить текущую игру
                Toast.makeText(requireContext(), "Игра удалена", Toast.LENGTH_SHORT).show() //Сообщение
                findNavController().navigate(R.id.action_updateFragment_to_listFragment) //Навигация на список
            }
            setNegativeButton("Нет") { _, _, -> }
            setTitle("Удалить ${args.currentGame.title}?")
            setMessage("Вы действительно хотите удалить ${args.currentGame.title}?")
            create().show()
        }

    }

    private fun launchGallery() {

        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(intent, GALLERY_REQUEST_CODE)
    }

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

