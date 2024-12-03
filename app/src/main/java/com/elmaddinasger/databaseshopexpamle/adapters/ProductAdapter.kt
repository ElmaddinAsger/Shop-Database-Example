package com.elmaddinasger.databaseshopexpamle.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elmaddinasger.databaseshopexpamle.databinding.ItemProductBinding
import com.elmaddinasger.databaseshopexpamle.db.ProductEntity

class ProductAdapter (val productList: List<ProductEntity>): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ProductViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.bind(currentProduct)
    }

    inner class ProductViewHolder(val binding : ItemProductBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductEntity){
            binding.txtvwProductName.text = product.productName
            binding.txtvwProductQuality.text = product.productQuality.toString()
        }
    }
}