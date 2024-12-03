package com.elmaddinasger.databaseshopexpamle

import android.app.DatePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.elmaddinasger.databaseshopexpamle.adapters.CustomerAdapter
import com.elmaddinasger.databaseshopexpamle.databinding.FragmentCustomerBinding
import com.elmaddinasger.databaseshopexpamle.db.CustomerDao
import com.elmaddinasger.databaseshopexpamle.db.CustomerEntity
import com.elmaddinasger.databaseshopexpamle.db.ProductDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerFragment : Fragment() {
    private lateinit var binding: FragmentCustomerBinding
    private lateinit var db: ProductDatabase
    private lateinit var customerDao : CustomerDao
    private lateinit var customerAdapter: CustomerAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCustomerBinding.inflate(inflater,container,false)
        db = ProductDatabase.getDatabase(requireContext())
        customerDao = db.customerDao()
        return binding.root
      }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setRecyclerView()
        val defaultBirthday = "Select Birthday"
        binding.txtvwCustomerBirthday.setText(defaultBirthday)
        binding.txtvwCustomerBirthday.setOnClickListener {
           selectBirthDay()
        }
        binding.btnSave.setOnClickListener {
            addCustomer(defaultBirthday)
        }
    }


    private fun selectBirthDay (){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(
            requireContext(),{
                    view, year, monthOfYear, dayOfMonth->
                binding.txtvwCustomerBirthday.setText((dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year))
            },
            year,
            month,
            day
        )
        datePickerDialog.show()
    }

    private fun addCustomer (defaultBirthday: String) {
        val customerName = binding.inpedtCustomerName.text.toString()
        val customerBithday = binding.txtvwCustomerBirthday.text.toString()

        if (customerName.isNotEmpty() && customerBithday != defaultBirthday &&customerBithday.isNotEmpty()){
            CoroutineScope(Dispatchers.IO).launch {
                val customerEntity = CustomerEntity(0,customerName,customerBithday)
                customerDao.insert(customerEntity)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Customer Saved.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun setRecyclerView () {
        CoroutineScope(Dispatchers.IO).launch {
            val customerList = customerDao.getAll()
            withContext(Dispatchers.Main){
                customerAdapter = CustomerAdapter(customerList)
                binding.rvCustomerList.adapter = customerAdapter
            }
        }
    }
}