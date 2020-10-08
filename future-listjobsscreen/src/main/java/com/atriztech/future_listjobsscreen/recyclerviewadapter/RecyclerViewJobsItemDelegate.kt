package com.atriztech.future_listjobsscreen.recyclerviewadapter

import com.atriztech.future_jobs.JobEntity

interface RecyclerViewJobsItemDelegate {
    fun openJob(job: JobEntity)
}