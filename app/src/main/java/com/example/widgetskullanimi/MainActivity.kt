package com.example.widgetskullanimi

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.SeekBar
import com.example.widgetskullanimi.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonResim1.setOnClickListener {
            binding.imageView.setImageResource(R.drawable.resim1)
        }

        binding.buttonResim2.setOnClickListener {
            binding.imageView.setImageResource(
                resources.getIdentifier(
                    "resim2",
                    "drawable",
                    packageName
                )
            )
        }

        binding.switch1.setOnCheckedChangeListener { s, b ->
            if (b) {
                Log.e("Widgets", "Switch : ON")
            } else Log.e("Widgets", "Switch : OFF")
        }
        binding.checkBox.setOnCheckedChangeListener { c, b ->
            if (b) {
                Log.e("Widgets", "CheckBox : ON")
            } else Log.e("Widgets", "CheckBox : OFF")
        }

        val ulkeler = ArrayList<String>()
        ulkeler.add("Türkiye")
        ulkeler.add("İtalya")
        ulkeler.add("Japonya")

        val adapter =
            ArrayAdapter(this, android.R.layout.simple_list_item_1, android.R.id.text1, ulkeler)

        binding.spinner.adapter = adapter

        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View, p2: Int, p3: Long) {
                Snackbar.make(p1, "${ulkeler[p2]} seçildi", Snackbar.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

        binding.buttonGoster.setOnClickListener {
            Log.e("Widgets", "Switch en son durum : ${binding.switch1.isChecked}")
            Log.e("Widgets", "CheckBox en son durum : ${binding.checkBox.isChecked}")
            Log.e("Widgets", "Barcelona en son durum : ${binding.radioButton.isChecked}")
            Log.e("Widgets", "Real Madrid en son durum : ${binding.radioButton2.isChecked}")
            Log.e("Widgets", "Slider en son durum : ${binding.slider.progress}")
            Log.e("Widgets", "spinner en son indeks : ${binding.spinner.selectedItemPosition}")
            Log.e(
                "Widgets",
                "spinner en son ülke : ${ulkeler[binding.spinner.selectedItemPosition]}"
            )
        }

        binding.radioButton.setOnCheckedChangeListener { r, b ->
            if (b) {
                Log.e("Widgets", "Barcelona seçili")
            }
        }
        binding.radioButton2.setOnCheckedChangeListener { r, b ->
            if (b) {
                Log.e("Widgets", "Real Madrid seçili")
            }
        }

        binding.buttonSaat.setOnClickListener {
            val calender = Calendar.getInstance()
            val saat = calender.get(Calendar.HOUR_OF_DAY)
            val dakika = calender.get(Calendar.MINUTE)

            val tp = TimePickerDialog(this, TimePickerDialog.OnTimeSetListener { t, s, dk ->
                binding.editTextSaat.setText("$s : $dk")
            }, saat, dakika, true)
            tp.setButton(DialogInterface.BUTTON_POSITIVE, "Seç", tp)
            tp.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", tp)
            tp.show()
        }

        binding.buttonTarih.setOnClickListener {
            val calender = Calendar.getInstance()
            val gun = calender.get(Calendar.DAY_OF_MONTH)
            val ay = calender.get(Calendar.MONTH)
            val yil = calender.get(Calendar.YEAR)

            val dp = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { d, y, a, g ->
                binding.editTextTarih.setText("$g / ${a + 1} / $y ")
            }, yil, ay, gun)
            dp.setButton(DialogInterface.BUTTON_POSITIVE, "Seç", dp)
            dp.setButton(DialogInterface.BUTTON_NEGATIVE, "İptal", dp)
            dp.show()
        }

        binding.buttonBasla.setOnClickListener {
            binding.progressBar.visibility = View.VISIBLE
        }

        binding.buttonDur.setOnClickListener {
            binding.progressBar.visibility = View.INVISIBLE
        }




        binding.textViewSonuc.text = binding.slider.progress.toString()
        binding.slider.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                    binding.textViewSonuc.text = p1.toString()
                }

                override fun onStartTrackingTouch(p0: SeekBar?) {

                }

                override fun onStopTrackingTouch(p0: SeekBar?) {

                }
            })
    }
}