package com.example.nabil_king.Home.Layanan.Persil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nabil_king.databinding.FragmentDataPersilBinding

class DataPersilFragment : Fragment() {

    private var _binding: FragmentDataPersilBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDataPersilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Mengatur LayoutManager untuk RecyclerView
        binding.rvPersil.layoutManager = LinearLayoutManager(requireContext())

        // Memasang data ke Adapter
        val dataPersil = generateDummyData()
        val adapter = PersilAdapter(dataPersil)
        binding.rvPersil.adapter = adapter
    }

    // Fungsi membuat minimal 10 data realistik dari screenshot tabel
    private fun generateDummyData(): List<PersilModel> {
        val list = ArrayList<PersilModel>()

        // 1
        list.add(
            PersilModel(
                "KODE001", "Cemplunk Viman Nugroho S.Gz", "20.00 m²",
                "Hutan Lindung - Blok 011", "jln sekolah", "001 / 002",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 2
        list.add(
            PersilModel(
                "PRS-47GB26", "Emil Prima Winarno", "3456.00 m²",
                "Taman Kota - Blok 048", "Gg. Ciwastra No. 276, Lhokseumawe 57607, Sulteng", "005 / 002",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 3
        list.add(
            PersilModel(
                "PRS-42LE05", "Iriana Safitri", "3055.00 m²",
                "Sawah - Blok 089", "Kl. Ujung No. 917, Bandung 11919, Sulsel", "009 / 001",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 4
        list.add(
            PersilModel(
                "PRS-34ZO95", "Salwa Wijayanti", "1423.00 m²",
                "Tempat Ibadah - Blok 042", "Kpg. Villa No. 736, Batam 55976, DIY", "004 / 005",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 5
        list.add(
            PersilModel(
                "PRS-03JR93", "Cici Janet Puspasari", "4455.00 m²",
                "Perkebunan - Blok 041", "Dk. Hasanuddin No. 226, Jambi 30442, DKI", "010 / 003",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 6
        list.add(
            PersilModel(
                "PRS-67EF21", "Tri Hartaka Maulana M.Kom.", "2963.00 m²",
                "Ruko (Rumah Toko) - Blok 003", "Kpg. Baranangsiang No. 908, Bogor 16697, Riau", "006 / 004",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 7
        list.add(
            PersilModel(
                "PRS-62LD03", "Belinda Kamaria Lestari", "1915.00 m²",
                "Lahan Kosong - Blok 057", "Kpg. Basket No. 886, Bandung 84646, DIY", "001 / 005",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 8
        list.add(
            PersilModel(
                "PRS-69LX98", "Marsudi Adriansyah", "4893.00 m²",
                "Hutan Lindung - Blok 043", "Psr. Kebangkitan Nasional No. 715, Dumai 55831, Jabar", "010 / 002",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 9
        list.add(
            PersilModel(
                "PRS-99YE71", "Galiono Prasasta", "3624.00 m²",
                "Hutan Lindung - Blok 086", "Gg. Sentot Alibasa No. 424, Palopo 81193, Sulsel", "001 / 005",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )
        // 10
        list.add(
            PersilModel(
                "PRS-76QK08", "Samiah Kiandra Halimah", "3066.00 m²",
                "Hutan Lindung - Blok 076", "Gg. Hasanuddin No. 145, Sabang 10335, Sulteng", "005 / 002",
                android.R.drawable.ic_menu_report_image, android.R.drawable.ic_menu_gallery
            )
        )

        return list
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}