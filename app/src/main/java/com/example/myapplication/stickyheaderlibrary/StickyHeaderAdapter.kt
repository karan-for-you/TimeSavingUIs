package com.example.myapplication.stickyheaderlibrary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.utils.Logger
import com.saber.stickyheader.stickyView.StickHeaderRecyclerView
import java.lang.Exception


class StickyHeaderAdapter : StickHeaderRecyclerView<Country, HeaderDataImpl>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            HeaderDataImpl.HEADER_TYPE_1->
                HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sticky_header_layout_view,parent,false))
            else->
                ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.sticky_view_layout,parent,false))
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        try {
            if(holder is HeaderViewHolder) {
                val headerPosition = getHeaderDataInPosition(position)
                holder.bindData(headerPosition)
            } else if(holder is ViewHolder) {
                val country : Country = getDataInPosition(position) as Country
                holder.bindData(country)
            }
        }catch (e : Exception){
            Logger.logError("Error ",e.message!!)
        }

    }

    override fun bindHeaderData(header: View?, headerPosition: Int) {

    }

    class HeaderViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        private var tvHeader : TextView? = null
        fun bindData(headerDataImpl: HeaderDataImpl) {
            tvHeader?.text = headerDataImpl.headerType.toString()
        }

        init {
            tvHeader = view.findViewById(R.id.tvHeaderText)
        }
    }


    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        private var tvCountry: TextView? = null
        fun bindData(country: Country) {
            tvCountry?.text = country.name

        }

        init {
            tvCountry = itemView.findViewById(R.id.tvCountryName)
        }

    }

}