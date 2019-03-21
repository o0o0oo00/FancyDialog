package com.zcy.nidavellir.fancydialog

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.FragmentManager

/**
 * @author:         zhaochunyu
 * @description:    带输入框的Dialog
 * @date:           2019/1/2
 */

inline fun editDialog(fragmentManager: FragmentManager, dsl: DSLEditDialog.() -> Unit) {
    DSLEditDialog.newInstance().apply(dsl).show(fragmentManager, "dialog")
}

class DSLEditDialog : BaseFragmentDialog() {

    /********* 可配置属性 *******/
    var mTitle: String? = null
    var mMessage: String? = null
    var mHint: String? = null
    var mDefault: String = ""
    var mColor: Int = MyApp.instance.color(R.color.colorAccent)
    var mPosition: Int? = null
    var mMaxLength: Int = 16


    private var mLeftText: String = MyApp.instance.string(R.string.cancel)
    private var mRightText: String = MyApp.instance.string(R.string.sure)
    private var leftClicks: (() -> Unit)? = null
    private var rightClicks: ((String) -> Unit)? = null

    private lateinit var edit: AppCompatEditText
    override fun setView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.layout_edit_dialog, container, false)
        edit = view.findViewById<AppCompatEditText>(R.id.labelEdit)
        edit.setText(mDefault)
        mHint?.let {
            edit.hint = it
        }
        edit.setSelection(mDefault.length)
        val cancel = view.findViewById<AppCompatTextView>(R.id.cancel)
        val sure = view.findViewById<AppCompatTextView>(R.id.sure)
        val title = view.findViewById<AppCompatTextView>(R.id.title)
        val message = view.findViewById<AppCompatTextView>(R.id.desc)
        mTitle?.also {
            title.text = mTitle
        }
        mMessage?.let {
            message.isVisibility(true)
            message.text = it
        }
        cancel.text = mLeftText
        cancel.setTextColor(mColor)
        sure.text = mRightText
        sure?.setTextColor(mColor)
        sure?.alpha = 0.4F
        edit.isFocusable = true
        edit.requestFocus()
        edit.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                s?.let TAG@{
                    if (s.trim().isBlank() || s.toString() == mDefault) {
                        sure?.isEnabled = false
                        sure?.alpha = 0.4F
                    } else {
                        sure?.isEnabled = true
                        sure?.alpha = 1F
                        if (s.length > mMaxLength) {
                            Toast.makeText(context, "太多了", Toast.LENGTH_SHORT).show()
                            edit?.let {
                                val selectionStart = it.selectionStart
                                val selectionEnd = it.selectionEnd
                                s.delete(selectionStart - 1, selectionEnd)
                                it.text = s
                                it.setSelection(it.text!!.length)
                                return@TAG
                            }
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

        })
        cancel.setOnClickListener {
            leftClicks?.let { onClick ->
                onClick()
            }
            dismiss()
        }

        sure.setOnClickListener {
            rightClicks?.let { onClick ->
                onClick(edit.text!!.trim().toString())
            }
            dismiss()
        }

        return view
    }

    fun leftClick(key: String? = null, onClick: () -> Unit) {
        key?.let {
            mLeftText = it
        }
        leftClicks = onClick
    }

    fun rightClick(key: String? = null, onClick: (edit: String) -> Unit) {
        key?.let {
            mRightText = it
        }
        rightClicks = onClick
    }

    override fun show(manager: FragmentManager, tag: String?) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (this::edit.isInitialized) {
                edit.showSoft()
            }
        }, 200)
        super.show(manager, tag)
    }

    companion object {
        fun newInstance(): DSLEditDialog {
            return DSLEditDialog()
        }
    }
}