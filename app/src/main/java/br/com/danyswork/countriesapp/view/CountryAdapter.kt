package br.com.danyswork.countriesapp.view

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.danyswork.countriesapp.R
import br.com.danyswork.countriesapp.model.CountryModel
import br.com.danyswork.countriesapp.utils.layoutInflater
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class CountryAdapter(
    private val context: Context,
    private val countries: MutableList<CountryModel>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun updateCountries(newCountries: MutableList<CountryModel>) {
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            context.layoutInflater.inflate(
                R.layout.item_view,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CountryModel) = with(itemView) {
            Glide.with(this)
                .load(item.flagPNG)
                .into(flag)
            countryName.text = item.name
            capital.text = item.capital
        }
    }

}
