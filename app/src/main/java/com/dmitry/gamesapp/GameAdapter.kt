package com.dmitry.gamesapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dmitry.gamesapp.database.Game

class GameAdapter(
    private val gameList: ArrayList<Game>
) : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    //ViewHolder хранит ссылку на представление элемента
    inner class GameViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById<TextView>(R.id.game_title)
        val image: ImageView = view.findViewById<ImageView>(R.id.game_image)
    }

    //onCreateViewHolder, onBindViewHolder, getItemCount - это
    //встроенные методы для реализации RecyclerView

    //Метод отвечает за создание представления на дисплее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameAdapter.GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.game_item, parent, false)
        return GameViewHolder(view)
    }

    //Метод отвечает за хаполнение холдера из данной позиции
    override fun onBindViewHolder(holder: GameAdapter.GameViewHolder, position: Int) {
        val games = gameList[position]
        holder.title.text = games.title
        holder.image.setImageResource(games.image)
    }

    //Метод возвращает количество элементов
    override fun getItemCount() = gameList.size
}