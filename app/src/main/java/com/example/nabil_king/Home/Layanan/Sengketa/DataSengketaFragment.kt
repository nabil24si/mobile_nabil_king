package com.example.nabil_king.Home.Layanan.Sengketa

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nabil_king.data.AppDatabase
import com.example.nabil_king.databinding.FragmentDataSengketaBinding
import kotlinx.coroutines.launch

class DataSengketaFragment : Fragment() {

    private var _binding: FragmentDataSengketaBinding? = null
    private val binding get() = _binding!!
    private lateinit var db: AppDatabase
    private lateinit var adapter: SengketaAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDataSengketaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        db = AppDatabase.getInstance(requireContext())

        setupRecyclerView()

        binding.fabAddSengketa.setOnClickListener {
            startActivity(Intent(requireContext(), AddSengketaActivity::class.java))
        }

        fetchData()
    }

    private fun setupRecyclerView() {
        adapter = SengketaAdapter(emptyList()) { sengketa ->
            // Detail logic or delete logic could go here
        }
        binding.rvSengketa.layoutManager = LinearLayoutManager(requireContext())
        binding.rvSengketa.adapter = adapter
    }

    private fun fetchData() {
        lifecycleScope.launch {
            val data = db.sengketaDao().getAll()
            if (data.isEmpty()) {
                binding.tvEmpty.visibility = View.VISIBLE
                binding.rvSengketa.visibility = View.GONE
            } else {
                binding.tvEmpty.visibility = View.GONE
                binding.rvSengketa.visibility = View.VISIBLE
                adapter.updateData(data)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}