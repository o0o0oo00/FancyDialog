package com.zcy.nidavellir.fancydialog

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT


/**
 * @author:         zhaochunyu
 * @description:    封装基本属性
 *                  赋值mContext
 *                  降级Dialog背景阴影 为 Activity 透明度
 * @date:           2018/12/25
 */
abstract class BaseFragmentDialog : DialogFragment() {

    var mWidth = WRAP_CONTENT
    var mHeight = WRAP_CONTENT
    var mGravity = Gravity.CENTER
    var mOffsetX = 0
    var mOffsetY = 0
    var mAnimation = R.style.DialogAnimation
    var touchOutside: Boolean = true
    lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    protected abstract fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setStyle()
        return setView(inflater, container, savedInstanceState)
    }

    /**** 降低背景的Window等级 ****/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mContext.setBackgroundAlpha(0.35F)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        mContext.setBackgroundAlpha(1F)
        super.onDestroyView()
    }


    /**
     * 设置统一样式
     */
    private fun setStyle() {
        //获取Window
        val window = dialog.window
        //无标题
        dialog.requestWindowFeature(DialogFragment.STYLE_NO_TITLE)
        // 透明背景
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        window.setDimAmount(0F) // 去除 dialog 弹出的阴影
        dialog.setCanceledOnTouchOutside(touchOutside)
        //设置宽高
        window!!.decorView.setPadding(0, 0, 0, 0)
        val wlp = window.attributes
        wlp.width = mWidth
        wlp.height = mHeight
        //设置对齐方式
        wlp.gravity = mGravity
        //设置偏移量
        wlp.x = dialog.context.dp2px(mOffsetX.toFloat())
        wlp.y = dialog.context.dp2px(mOffsetY.toFloat())
        //设置动画
//        window.setWindowAnimations(mAnimation)
        window.attributes = wlp
    }

}