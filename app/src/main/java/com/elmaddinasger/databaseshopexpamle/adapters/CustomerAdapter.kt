package com.elmaddinasger.databaseshopexpamle.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elmaddinasger.databaseshopexpamle.databinding.ItemCustomerBinding
import com.elmaddinasger.databaseshopexpamle.db.CustomerEntity

class CustomerAdapter (val customerList: List<CustomerEntity>) : RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> () {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val binding = ItemCustomerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomerViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return customerList.size
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val customer = customerList[position]
        holder.bind(customer)
    }

    inner class CustomerViewHolder(private val binding: ItemCustomerBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind (customer: CustomerEntity){
            binding.txtvwCustomerName.text = customer.customerName
            binding.txtvwCustomerBirthday.text = customer.customerBirthDay
        }
    }
}