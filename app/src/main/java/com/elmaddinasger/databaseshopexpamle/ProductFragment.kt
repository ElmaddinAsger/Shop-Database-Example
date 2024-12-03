package com.elmaddinasger.databaseshopexpamle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.elmaddinasger.databaseshopexpamle.adapters.ProductAdapter
import com.elmaddinasger.databaseshopexpamle.databinding.FragmentProductBinding
import com.elmaddinasger.databaseshopexpamle.db.ProductDao
import com.elmaddinasger.databaseshopexpamle.db.ProductDatabase
import com.elmaddinasger.databaseshopexpamle.db.ProductEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductFragment : Fragment() {
    private lateinit var binding: FragmentProductBinding
    private lateinit var productDao: ProductDao
    private lateinit var productDatabase: ProductDatabase
    private lateinit var productAdapter: ProductAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    binding = FragmentProductBinding.inflate(inflater,container,false)
        productDatabase = ProductDatabase.getDatabase(requireContext())
        productDao = productDatabase.productDao()
        getProducts()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnSave.setOnClickListener {
            addProduct()
        }
        binding.btnAddCustomer.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_customerFragment)
        }
        binding.btnAddOrder.setOnClickListener {
            findNavController().navigate(R.id.action_productFragment_to_orderFragment)
        }

    }

    private fun addProduct(){
        val productName = binding.inpedtProductName.text.toString()
        val productQualityString = binding.inpedtProductQuality.text.toString()

        if (productName.isNotEmpty()&& productQualityString.isNotEmpty()){
            val productQuality = productQualityString.toLong()
            CoroutineScope(Dispatchers.IO).launch {
                val productEntity = ProductEntity(0,productName,productQuality)
                productDao.insert(productEntity)
                withContext(Dispatchers.Main){
                    Toast.makeText(requireContext(),"Product saved.",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun setRecyclerView (productList: List<ProductEntity>) {
        productAdapter = ProductAdapter(productList)
        binding.rvProductList.adapter = productAdapter
    }

    fun getProducts(){
        CoroutineScope(Dispatchers.IO).launch {
            val productList= productDao.getAll()
            withContext(Dispatchers.Main){
                setRecyclerView(productList)
            }
        }
    }




}