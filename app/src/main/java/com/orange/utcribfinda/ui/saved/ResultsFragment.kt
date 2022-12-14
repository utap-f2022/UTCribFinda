package com.orange.utcribfinda.ui.saved

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.utcribfinda.databinding.FragmentHomeBinding
import com.orange.utcribfinda.databinding.FragmentRvBinding
import com.orange.utcribfinda.databinding.FragmentSavedBinding
import com.orange.utcribfinda.ui.PostRowAdapter

class ResultsFragment : Fragment() {

    private var _binding: FragmentSavedBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SavedViewModel by activityViewModels()

    companion object {
        fun newInstance(): ResultsFragment {
            return ResultsFragment()
        }
    }

    private fun initAdapter(binding: FragmentSavedBinding): PostRowAdapter {
        val rv = binding.recyclerView
        val adapter = PostRowAdapter(viewModel)
        rv.layoutManager = LinearLayoutManager(activity)
        rv.adapter = adapter
        return adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val savedViewModel =
//            ViewModelProvider(this)[SavedViewModel::class.java]
        _binding = FragmentSavedBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = initAdapter(binding)
        viewModel.observePostsPicked().observe(viewLifecycleOwner) {
            Log.d("in res" , it.toString())
            adapter.submitList(it)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}