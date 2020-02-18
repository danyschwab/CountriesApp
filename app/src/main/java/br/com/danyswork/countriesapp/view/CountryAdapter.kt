package br.com.danyswork.countriesapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.danyswork.countriesapp.R
import br.com.danyswork.countriesapp.model.CountryModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_view.view.*

class CountryAdapter(
    private val countries: MutableList<CountryModel>
) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    fun updateCountries(newContruies: MutableList<CountryModel>) {
        countries.clear()
        countries.addAll(newContruies)
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(
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
        holder.bind(countries.get(position))
    }

    class CountryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: CountryModel) = with(itemView) {
            Glide.with(this)
                .load(item.flag)
                .into(flag)
            countryName.text = item.countryName
            capital.text = item.capital
        }
    }

}
