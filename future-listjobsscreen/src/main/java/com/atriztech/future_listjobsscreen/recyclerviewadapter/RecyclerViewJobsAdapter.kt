package com.atriztech.future_listjobsscreen.recyclerviewadapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atriztech.future_jobs.JobEntity
import com.atriztech.future_listjobsscreen.R
import com.squareup.picasso.Picasso
import java.util.*
import javax.inject.Inject

class RecyclerViewJobsAdapter @Inject constructor() :RecyclerView.Adapter<RecyclerViewJobsAdapter.ViewHolder>() {
    val listData: MutableList<JobEntity> = LinkedList()
    private var delegate: RecyclerViewJobsItemDelegate? = null

    fun attachDelegate(delegate: RecyclerViewJobsItemDelegate){
        this.delegate = delegate
    }

    fun setData(newList: List<JobEntity>){
        listData.clear()
        listData.addAll(newList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(itemView = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_job, viewGroup, false),
            delegate = delegate)
    }

    override fun getItemCount(): Int {
        return listData.count()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bind(model = listData[position], position = position)
    }

    class ViewHolder(itemView: View, val delegate: RecyclerViewJobsItemDelegate?): RecyclerView.ViewHolder(itemView){
        private val txtCompany: TextView = itemView.findViewById(R.id.item_company)
        private val imgLogo: ImageView = itemView.findViewById(R.id.item_logo)
        private val txtTitle: TextView = itemView.findViewById(R.id.item_title)
        private val txtType: TextView = itemView.findViewById(R.id.item_type)

        fun bind(model: JobEntity, position: Int){
            txtCompany.text = model.company
            txtTitle.text = model.title
            txtType.text = model.type

            Picasso.get()
                .load(model.company_logo)
                .centerInside()
                .resize(200,200)
                .into(imgLogo)

            itemView.setOnClickListener {
                delegate?.openJob(job = model)
            }
        }
    }
}