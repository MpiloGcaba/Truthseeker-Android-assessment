package com.glucode.about_you.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.engineers.views.EngineerProfileCard
import com.glucode.about_you.mockdata.MockData
import com.glucode.about_you.utils.launcherForImagePicker
import com.glucode.about_you.utils.registerImagePicker

class AboutFragment : Fragment() {

    //region Properties
    private lateinit var binding: FragmentAboutBinding

    private val engineerName: String by lazy { arguments?.getString("name") ?: "Unknown " }
    private val engineer by lazy { MockData.engineers.first { it.name == engineerName } }

    private lateinit var pickImageLauncher: ActivityResultLauncher<String>
    //endregion


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pickImageLauncher =
            registerImagePicker { uri ->
                binding.profileCard.setProfileImage(uri.toString())
                MockData.engineers.find { it.name == engineerName }
                    ?.apply { defaultImageName = uri.toString() }
            }

        binding.profileCard.clickListener = object : EngineerProfileCard.OnClickListener {
            override fun onClick() {
                launcherForImagePicker(pickImageLauncher)
            }
        }
        setProfileCardInfo()
        setUpQuestions()
    }


    //region Helper Functions
    private fun setProfileCardInfo() {
        with(binding.profileCard) {
            name = engineer.name
            role = engineer.role
            quickStats.setStatsDetails(engineer.quickStats)
            setProfileImage(engineer.defaultImageName)
        }
    }

    private fun setUpQuestions() {
        engineer.questions.forEach { question ->
            val questionView = QuestionCardView(requireContext())
            questionView.title = question.questionText
            questionView.answers = question.answerOptions
            questionView.selection = question.answer.index

            binding.container.addView(questionView)
        }
    }
    //endregion
}