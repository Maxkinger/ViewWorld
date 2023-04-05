package com.github.kongpf8848.viewworld.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.kongpf8848.androidworld.adapter.FragmentAdapter
import com.github.kongpf8848.viewworld.R
import com.github.kongpf8848.viewworld.base.BaseActivity
import com.github.kongpf8848.viewworld.fragment.TitleFragment
import kotlinx.android.synthetic.main.activity_tab_layout.*

class TabLayoutActivity: BaseActivity(){

    private val titlesList= listOf("Microsoft","Goolge","Apple","Samsung","Facebook","Amazon")

    override fun getLayoutId(): Int {
        return R.layout.activity_tab_layout
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        toolbar.setNavigationOnClickListener {
            finish()
        }
        val fragments = ArrayList<Fragment>()
        titlesList.forEach {
            fragments.add(TitleFragment.newInstance(it))
        }

        val adapter = FragmentAdapter(supportFragmentManager, fragments, titlesList)
        view_pager.adapter = adapter

        tab_layout_origin.setupWithViewPager(view_pager)

        tab_layout_1.setupWithViewPager(view_pager)
        tab_layout_2.setupWithViewPager(view_pager)
        tab_layout_3.setupWithViewPager(view_pager)

    }
}