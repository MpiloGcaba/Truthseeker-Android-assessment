package com.glucode.about_you.engineers.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.cardview.widget.CardView
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewQuickStatCardBinding
import com.glucode.about_you.engineers.models.QuickStats

class QuickStatsCardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    private val binding: ViewQuickStatCardBinding =
        ViewQuickStatCardBinding.inflate(LayoutInflater.from(context), this)

    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
    }

    fun setStatsDetails(quickStats: QuickStats){
        setYearsDetails(quickStats.years.toString())
        setCoffeeDetails(quickStats.coffees.toString())
        setBugsDetails(quickStats.bugs.toString())
    }


    private fun setYearsDetails(str: String) {
        with(binding.years) {
            title.text = "Years"
            info.text = str
        }
    }

    private fun setCoffeeDetails(str: String) {
        with(binding.coffees) {
            title.text = "Coffees"
            info.text = str
        }
    }

   private fun setBugsDetails(str: String) {
        with(binding.bugs) {
            title.text = "Bugs"
            info.text = str
        }
    }

}