package com.glucode.about_you.engineers

import android.os.Bundle
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.glucode.about_you.R
import com.glucode.about_you.databinding.FragmentEngineersBinding
import com.glucode.about_you.engineers.models.Engineer
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.utils.registerImagePicker

class EngineersFragment : Fragment() {

    //region Properties
    private lateinit var binding: FragmentEngineersBinding
    private var adapter: EngineersRecyclerViewAdapter? = null
    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    //endregion


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentEngineersBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        pickImageLauncher = registerImagePicker { uri ->  }
        setUpEngineersList(MockData.engineers)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_engineers, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_years -> {
                val newList = MockData.engineers.sortedBy { it.quickStats.years }.toMutableList()
                adapter?.updateData(newList)
                return true
            }

            R.id.action_coffees -> {
                val newList = MockData.engineers.sortedBy { it.quickStats.coffees }.toMutableList()
                adapter?.updateData(newList)
                return true
            }

            R.id.action_bugs -> {
                val newList = MockData.engineers.sortedBy { it.quickStats.bugs }.toMutableList()
                adapter?.updateData(newList)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    private fun setUpEngineersList(engineers: List<Engineer>) {
        adapter = EngineersRecyclerViewAdapter(engineers, pickImageLauncher) {
            goToAbout(it)
        }
        binding.list.adapter = adapter
        val dividerItemDecoration =
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        binding.list.addItemDecoration(dividerItemDecoration)
    }

    private fun goToAbout(engineer: Engineer) {
        val bundle = Bundle().apply {
            putString("name", engineer.name)
        }
        findNavController().navigate(R.id.action_engineersFragment_to_aboutFragment, bundle)
    }
}