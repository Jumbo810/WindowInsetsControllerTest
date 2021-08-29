package com.example.windowinsetscontrollertest

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.windowinsetscontrollertest.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private var hideFlag = 0;

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        hideFlag = 0;
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onResume() {
        super.onResume();
        var controller = activity?.window?.insetsController;
        binding.statusBarButton.setOnClickListener {
            if (hideFlag and WindowInsets.Type.statusBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.statusBars().inv();
                controller?.show(WindowInsets.Type.statusBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.statusBars();
                controller?.hide(WindowInsets.Type.statusBars());
            }
        }
        binding.navigationBarButton.setOnClickListener {
            if (hideFlag and WindowInsets.Type.navigationBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.navigationBars().inv();
                controller?.show(WindowInsets.Type.navigationBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.navigationBars();
                controller?.hide(WindowInsets.Type.navigationBars());
            }
        }
        binding.systemBarButton.setOnClickListener {
            controller?.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE;
            if (hideFlag and WindowInsets.Type.systemBars() > 0) {
                hideFlag = hideFlag and WindowInsets.Type.systemBars().inv();
                controller?.show(WindowInsets.Type.systemBars());
            } else {
                hideFlag = hideFlag or WindowInsets.Type.systemBars();
                controller?.hide(WindowInsets.Type.systemBars());
            }
        }

    }
}