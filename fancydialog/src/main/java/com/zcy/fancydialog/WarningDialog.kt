package com.zcy.fancydialog

import android.graphics.Color
import android.graphics.PointF
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.*
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.widget_toast.view.*

/**
 * @author:         zhaochunyu
 * @description:    ${DESP}
 * @date:           2019-06-16
 */
inline fun warning(manager: FragmentManager, block: WarningDialog.() -> Unit) {
    WarningDialog().apply {
        block()
        mColor = R.color.colorAccent
//        mDrawable = R.drawable.warning
    }.show(manager, "warning")
}

inline fun success(manager: FragmentManager, block: WarningDialog.() -> Unit) {
    WarningDialog().apply {
        block()
        mColor = R.color.colorPrimary
//        mDrawable = R.drawable.svg_success
    }.show(manager, "success")
}

class WarningDialog : BaseFragmentDialog() {
    private val TRIGGER_OFFSET: Int by lazy { requireActivity().dp2px(8f) }
    private var downPoint: PointF = PointF(0F, 0F) // 触摸位置

    private val flags: Int by lazy {
        WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or // 不获取焦点，以便于在弹出的时候 下层界面仍然可以进行操作
                WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN or
                WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR // 确保你的内容不会被装饰物(如状态栏)掩盖
    }

    var mWarningText: String = ""
    var mColor: Int = Color.WHITE
    var mDrawable: Int? = null

    private val mHandler: Handler by lazy {
        Handler(Looper.getMainLooper())
    }

    private val runnable by lazy {
        { dismiss() }
    }

    init {
        mWidth = WindowManager.LayoutParams.MATCH_PARENT
        mHeight = WindowManager.LayoutParams.WRAP_CONTENT
        mGravity = Gravity.TOP

//        mAnimation = R.style.ToastAnimation

        mHandler.postDelayed(runnable, 2000)
    }

    override fun onDestroy() {
        mHandler.removeCallbacks(runnable)
        super.onDestroy()
    }

    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        dialog?.window?.addFlags(flags)
        dialog?.window?.setDimAmount(0F)

        val view = inflater.inflate(R.layout.widget_toast, container, false)
        view.findViewById<TextView>(R.id.text_view).text = mWarningText
        val up = view.root_view.background
        val drawableUp = DrawableCompat.wrap(up)
        DrawableCompat.setTint(drawableUp, ContextCompat.getColor(mContext, mColor))

        mDrawable?.let { view.image_view.setImageResource(it) }

        view.setOnTouchListener { v, event ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    downPoint = PointF(event.x, event.y)
                }
                MotionEvent.ACTION_MOVE -> {
                    val downX = downPoint.x
                    val downY = downPoint.y
                    if (Math.abs(event.x - downX) > TRIGGER_OFFSET || Math.abs(event.y - downY) > TRIGGER_OFFSET) {
                        dismiss()
                    }
                }
                else -> {
                }
            }
            true
        }
        return view
    }

}