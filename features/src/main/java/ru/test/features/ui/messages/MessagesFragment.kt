package ru.test.features.ui.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.test.features.R
import ru.test.features.databinding.FragmentMessagesBinding

class MessagesFragment : Fragment() {

    private var _binding: FragmentMessagesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessagesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.text.text = getString(R.string.this_is_messages_fragment)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
