package com.example.myapplication.stickyheaderlibrary

import androidx.annotation.LayoutRes
import com.saber.stickyheader.stickyData.HeaderData

class HeaderDataImpl(headerType : Int, @LayoutRes layoutRes : Int) : HeaderData {

    companion object{
        val HEADER_TYPE_1 = 1
        val HEADER_TYPE_2 = 2
        val HEADER_TYPE_3 = 3
        val HEADER_TYPE_4 = 4
        val HEADER_TYPE_5 = 5
        val HEADER_TYPE_6 = 6
    }

    private var headerType = 0
    @LayoutRes
    private var layoutResource = 0
    init {
        this.layoutResource = layoutRes
        this.headerType = headerType
    }


    @LayoutRes
    override fun getHeaderLayout(): Int {
        return layoutResource
    }

    override fun getHeaderType(): Int {
        return headerType
    }

}