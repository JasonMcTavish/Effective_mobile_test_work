package ru.test.features.ui.responses

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.test.features.R
import ru.test.features.databinding.FragmentResponsesBinding

class ResponsesFragment : Fragment() {

    private var _binding: FragmentResponsesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentResponsesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.text.text = getString(R.string.this_is_responses_fragment)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
