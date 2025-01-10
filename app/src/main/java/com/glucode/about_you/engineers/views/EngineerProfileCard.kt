package com.glucode.about_you.engineers.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.content.res.AppCompatResources
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import com.glucode.about_you.R
import com.glucode.about_you.databinding.ViewEngineerProfileCardBinding

class EngineerProfileCard @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : CardView(context, attrs, defStyleAttr) {

    //region Properties
    private val binding: ViewEngineerProfileCardBinding =
        ViewEngineerProfileCardBinding.inflate(LayoutInflater.from(context), this)

    var name: String = ""
        set(value) {
            binding.name.text = value
            field = value
        }

    var role: String = ""
        set(value) {
            binding.role.text = value
            field = value
        }

    val quickStats: QuickStatsCardView = binding.quickStats

    var clickListener: OnClickListener? = null
    //endregion


    //region Helper Functions
    fun setProfileImage(str: String?) {
        if (!str.isNullOrEmpty()) {
            binding.profileImage.setImageURI(str.toUri())
        } else {
            binding.profileImage.setImageDrawable(
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.ic_person
                )
            )
        }
    }
    //endregion


    init {
        radius = resources.getDimension(R.dimen.corner_radius_normal)
        binding.profileImage.setOnClickListener {
            clickListener?.onClick()
        }
    }

  interface OnClickListener {
        fun onClick()
    }
}