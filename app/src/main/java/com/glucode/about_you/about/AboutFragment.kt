package com.glucode.about_you.about

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.glucode.about_you.about.views.QuestionCardView
import com.glucode.about_you.databinding.FragmentAboutBinding
import com.glucode.about_you.mockdata.MockData

class AboutFragment : Fragment() {
    private lateinit var binding: FragmentAboutBinding

    private val engineerName: String by lazy { arguments?.getString("name") ?: "Unknown " }
    private val engineer by lazy { MockData.engineers.first { it.name == engineerName } }

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
        setProfileCardInfo()
        setUpQuestions()
    }

    private fun setProfileCardInfo() {
        with(binding.profileCard) {
            name = engineer.name
            role = engineer.role
            quickStats.setStatsDetails(engineer.quickStats)
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
}