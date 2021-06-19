package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

    fun clickDatePicker(view: View){
        val myCalendar = Calendar.getInstance();
        val year = myCalendar.get(Calendar.YEAR);
        val month = myCalendar.get(Calendar.MONTH);
        val day = myCalendar.get(Calendar.DAY_OF_MONTH);
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this, "Date Selected", Toast.LENGTH_SHORT).show();
                //tvSelectedDate.setText(selectedDay+"/"+selectedMonth+"/"+selectedYear)
                val selectedDate = "${ selectedDay}/${selectedMonth + 1}/${selectedYear}"
                tvSelectedDate.setText(selectedDate)
                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val dateWithFormat = sdf.parse(selectedDate)
                val selectedDateInMinutes = dateWithFormat!!.time / 6000
                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                val currentDateToMinutes = currentDate!!.time / 6000
                val differenceInMinutes = currentDateToMinutes - selectedDateInMinutes;
                tvSelectedDateInMinutes.setText(differenceInMinutes.toString())
            }, year, month,day);
        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }
}