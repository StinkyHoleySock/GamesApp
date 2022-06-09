package com.danila.gamesapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.danila.gamesapp.R
import com.danila.gamesapp.database.Game
import kotlinx.android.synthetic.main.game_item.view.*

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    private var gameList = emptyList<Game>()
    //ViewHolder хранит ссылку на представление элемента
    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    //onCreateViewHolder, onBindViewHolder, getItemCount - это
    //встроенные методы для реализации RecyclerView
    //Метод отвечает за создание представления на дисплее
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        return GameViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false))
    }
    //Метод отвечает за хаполнение холдера из данной позиции
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val currentItem = gameList[position]
        holder.itemView.game_title.text = currentItem.title

        Glide.with(holder.itemView)
            .load(currentItem.image)
            .into(holder.itemView.game_image)

        holder.itemView.game_layout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }
    //Метод возвращает количество элементов
    override fun getItemCount() = gameList.size

    fun setData(game: List<Game>) {
        this.gameList = game
        notifyDataSetChanged()
    }
}

