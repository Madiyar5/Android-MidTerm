package com.example.aviatickets.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.aviatickets.R
import com.example.aviatickets.databinding.ItemOfferBinding
import com.example.aviatickets.model.entity.Offer

class OfferListAdapter : ListAdapter<Offer, OfferListAdapter.ViewHolder>(DiffUtils()) {

    private val items: ArrayList<Offer> = arrayListOf()
    class DiffUtils: DiffUtil.ItemCallback<Offer>(){
        override fun areItemsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Offer, newItem: Offer): Boolean {
            return oldItem == newItem
        }

    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemOfferBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(
        private val binding: ItemOfferBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        private val context = binding.root.context

        fun bind(offer: Offer) {
            val flight = offer.flight

            with(binding) {
                departureTime.text = flight.departureTimeInfo
                arrivalTime.text = flight.arrivalTimeInfo
                route.text = context.getString(
                    R.string.route_fmt,
                    //doing
                    flight.departureLocation.code,
                    flight.arrivalLocation.code
                )
                duration.text = context.getString(
                    R.string.time_fmt,
                    //dia
                    getTimeFormat(flight.duration).first.toString(),
                    getTimeFormat(flight.duration).second.toString()
                )
                    //IMAGE
                direct.text = context.getString(R.string.direct)
                price.text = context.getString(R.string.price_fmt, offer.price.toString())
                Glide.with(itemView.context)
                    .load("data:image/png;base64,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")
                    .into(airlineImage)
            }
        }

        private fun getTimeFormat(minutes: Int): Pair<Int, Int> = Pair(
            first = minutes / 60,
            second = minutes % 60
        )

    }
}