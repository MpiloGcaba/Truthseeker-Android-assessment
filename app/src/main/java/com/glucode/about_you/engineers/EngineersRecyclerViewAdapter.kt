package com.glucode.about_you.engineers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.recyclerview.widget.RecyclerView
import com.glucode.about_you.databinding.ItemEngineerBinding
import com.glucode.about_you.engineers.models.Engineer

class EngineersRecyclerViewAdapter(
    private var engineers: List<Engineer>,
    private var pickImageLauncher: ActivityResultLauncher<String>,
    private val onClick: (Engineer) -> Unit
) : RecyclerView.Adapter<EngineersRecyclerViewAdapter.EngineerViewHolder>() {

    override fun getItemCount() = engineers.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EngineerViewHolder {
        return EngineerViewHolder(
            ItemEngineerBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EngineerViewHolder, position: Int) {
        holder.bind(engineers[position], pickImageLauncher, onClick)
    }

    inner class EngineerViewHolder(private val binding: ItemEngineerBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            engineer: Engineer, pickImageLauncher: ActivityResultLauncher<String>,
            onClick: (Engineer) -> Unit
        ) {
            with(binding.profileCard) {
                name = engineer.name
                role = engineer.role
                setProfileImage(engineer.defaultImageName)
                setUpImagePickLauncher(pickImageLauncher)
                quickStats.setStatsDetails(engineer.quickStats)
            }
            binding.root.setOnClickListener {
                onClick(engineer)
            }
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newEngineers: List<Engineer>) {
        engineers = newEngineers
        notifyDataSetChanged()
    }
}