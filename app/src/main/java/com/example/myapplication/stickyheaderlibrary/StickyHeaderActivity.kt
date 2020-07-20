package com.example.myapplication.stickyheaderlibrary

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityStickyHeaderBinding
import com.saber.stickyheader.stickyView.StickHeaderItemDecoration


class StickyHeaderActivity : BaseActivity() {

    lateinit var bindingStickyHeaderBinding: ActivityStickyHeaderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingStickyHeaderBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_sticky_header
        )
        setupAdapter()
    }

    private fun setupAdapter(){

        val adapter = StickyHeaderAdapter()

        val headerData1 =
            HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_1, R.layout.sticky_header_layout_view)

        var items: java.util.ArrayList<Country> = java.util.ArrayList()
        items.add(Country("Afghanistan","AD"))
        items.add(Country("Albania","AL"))
        items.add(Country("Algeria","AL"))
        items.add(Country("Andorra","AN"))
        adapter.setHeaderAndData(items, headerData1)

        val headerData2 =
            HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_2, R.layout.sticky_header_layout_view)


        items = java.util.ArrayList()
        items.add(Country("Bangladesh","BD"))
        items.add(Country("Bahrain","BH"))
        items.add(Country("Barbabados","BAR"))
        items.add(Country("Belarus","BC"))
        items.add(Country("Bhutan","BH"))
        adapter.setHeaderAndData(items, headerData2)

        val headerData3 =
            HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_3, R.layout.sticky_header_layout_view)

        items = java.util.ArrayList()
        items.add(Country("Canada","CA"))
        items.add(Country("Chad","CHD"))
        items.add(Country("Chile","CHI"))
        items.add(Country("China","CHINA"))
        items.add(Country("Croatia","Crow"))
        items.add(Country("Cuba","Cuba"))
        adapter.setHeaderAndData(items, headerData3)

        val headerData4 =
            HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_4, R.layout.sticky_header_layout_view)

        items = java.util.ArrayList()
        items.add(Country("Denmark","DN"))
        items.add(Country("Djioubai","DJ"))
        items.add(Country("Dominica","DC"))
        items.add(Country("Dominica Republic","DR"))
        adapter.setHeaderAndData(items, headerData4)

        val headerData5 =
            HeaderDataImpl(HeaderDataImpl.HEADER_TYPE_5, R.layout.sticky_header_layout_view)

        items = java.util.ArrayList()
        items.add(Country("Ecudor","EC"))
        items.add(Country("Egypt","EJ"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        items.add(Country("ESTONIA","ET"))
        adapter.setHeaderAndData(items, headerData5)
        val linearLayoutAdapter = LinearLayoutManager(this)
        bindingStickyHeaderBinding.rvStickyHeader.layoutManager = linearLayoutAdapter
        bindingStickyHeaderBinding.rvStickyHeader.adapter = adapter
        bindingStickyHeaderBinding.rvStickyHeader.addItemDecoration(StickHeaderItemDecoration(adapter))

    }

}