package com.github.kongpf8848.viewworld.activity

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.github.kongpf8848.androidworld.adapter.FragmentAdapter
import com.github.kongpf8848.viewworld.R
import com.github.kongpf8848.viewworld.base.BaseActivity
import com.github.kongpf8848.viewworld.databinding.ActivityTabLayoutBinding
import com.github.kongpf8848.viewworld.fragment.TitleFragment


class TabLayoutActivity : BaseActivity<ActivityTabLayoutBinding>() {

    private val titlesList = listOf("Microsoft", "Goolge", "Apple", "Samsung", "Facebook", "Amazon")

    override fun getLayoutId(): Int {
        return R.layout.activity_tab_layout
    }

    override fun onCreateEnd(savedInstanceState: Bundle?) {
        super.onCreateEnd(savedInstanceState)
        binding.toolbar.setNavigationOnClickListener {
            finish()
        }
        val fragments = ArrayList<Fragment>()
        titlesList.forEach {
            fragments.add(TitleFragment.newInstance(it))
        }

        val adapter = FragmentAdapter(supportFragmentManager, fragments, titlesList)
        binding.viewPager.adapter = adapter

        binding.tabLayoutOrigin.setupWithViewPager(binding.viewPager)

        binding.tabLayout1.setupWithViewPager(binding.viewPager)
        binding.tabLayout2.setupWithViewPager(binding.viewPager)
        binding.tabLayout3.setupWithViewPager(binding.viewPager)
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d(
                    TAG,
                    "onPageScrolled() called with: position = $position, positionOffset = $positionOffset, positionOffsetPixels = $positionOffsetPixels"
                )
                val itemCount = binding.viewPager.adapter!!.count
                if (itemCount > 1) {
                    // 计算总进度
                    // 当前页索引 + 当前页偏移百分比
                    val currentProgress = position + positionOffset

                    // 总的可移动页数 = 总页数 - 1
                    val totalProgress = (itemCount - 1).toFloat()

                    val ratio = currentProgress / totalProgress

                    Log.d(
                        TAG,
                        "onPageScrolled() called with: ratio = $ratio"
                    )
                    binding.bottomIndicator.updateScrollRatio(ratio)
                }

            }

            override fun onPageSelected(position: Int) {

            }

            override fun onPageScrollStateChanged(state: Int) {

            }

        })

    }
}