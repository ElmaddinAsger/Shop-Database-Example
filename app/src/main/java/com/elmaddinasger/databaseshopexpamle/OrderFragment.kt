package com.elmaddinasger.databaseshopexpamle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.elmaddinasger.databaseshopexpamle.databinding.FragmentOrderBinding
import com.elmaddinasger.databaseshopexpamle.db.CustomerDao
import com.elmaddinasger.databaseshopexpamle.db.CustomerEntity
import com.elmaddinasger.databaseshopexpamle.db.OrderDao
import com.elmaddinasger.databaseshopexpamle.db.OrderEntity
import com.elmaddinasger.databaseshopexpamle.db.ProductDao
import com.elmaddinasger.databaseshopexpamle.db.ProductDatabase
import com.elmaddinasger.databaseshopexpamle.db.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrderFragment : Fragment() {
    private lateinit var binding: FragmentOrderBinding
    private lateinit var db : ProductDatabase
    private lateinit var customerDoa: CustomerDao
    private lateinit var productDao: ProductDao
    private lateinit var orderDao: OrderDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderBinding.inflate(inflater,container,false)
        db = ProductDatabase.getDatabase(requireContext())
        customerDoa = db.customerDao()
        productDao = db.productDao()
        orderDao = db.orderDao()


        setCustomerSpinner()
        setProductSpinner()
        return binding.root
     }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            addOrder()
        }
    }

    fun setCustomerSpinner () {
        CoroutineScope(Dispatchers.IO).launch {
            val customerList = customerDoa.getAll()
            withContext(Dispatchers.Main){
                val customerSpinnerAdapet = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,customerList)
                customerSpinnerAdapet.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                binding.spnCustomer.adapter = customerSpinnerAdapet
            }
        }
    }

    fun setProductSpinner () {
        CoroutineScope(Dispatchers.IO).launch {
            val productList = productDao.getAll()
            withContext(Dispatchers.Main){
                val customerSpinnerAdapet = ArrayAdapter(requireContext(), androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,productList)
                customerSpinnerAdapet.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
                binding.spnProduct.adapter = customerSpinnerAdapet
            }
        }
    }

    fun addOrder () {
        CoroutineScope(Dispatchers.IO).launch {
            val selectedCustomer = binding.spnCustomer.selectedItem as? CustomerEntity
            val selectedProduct = binding.spnProduct.selectedItem as? ProductEntity
            if (selectedProduct != null && selectedCustomer != null) {
                val orderEntity =
                    OrderEntity(0, selectedCustomer.customerId, selectedProduct.productId)
                orderDao.insert(orderEntity)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Order Saved.",Toast.LENGTH_SHORT).show()                }
            }
        }
    }
}