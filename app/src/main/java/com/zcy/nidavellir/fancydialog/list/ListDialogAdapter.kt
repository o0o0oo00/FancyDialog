package com.zcy.nidavellir.fancydialog.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zcy.nidavellir.fancydialog.R
import kotlinx.android.synthetic.main.item_list_dialog.view.*

/**
 * @author:         zhaochunyu
 * @description:    ${DESP}
 * @date:           2019/1/8
 */
class ListDialogAdapter : RecyclerView.Adapter<ListDialogViewHolder>() {
    val list: MutableList<Any> = mutableListOf()
    private var onClickListener: ClickListener? = null
    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(p0: ListDialogViewHolder, p1: Int) {
        p0.itemView.text.text = list[p1].toString()
        p0.itemView.tag = list[p1]
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ListDialogViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_list_dialog, p0, false)
        view.setOnClickListener {
            onClickListener?.onItemClick(p1, view)
        }

        return ListDialogViewHolder(view)
    }

    fun setOnClickListener(onClickListener: ClickListener) {
        this.onClickListener = onClickListener
    }

}

class ListDialogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
interface ClickListener {
    fun onItemClick(position: Int, v: View)
    fun onItemLongClick(position: Int, v: View)
}