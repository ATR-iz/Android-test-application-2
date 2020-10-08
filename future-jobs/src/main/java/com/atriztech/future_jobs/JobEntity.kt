package com.atriztech.future_jobs

data class JobEntity(
    val id: String?,
    val type: String?,
    val url: String?,
    val created_at: String?,
    val company: String?,
    val company_url: String?,
    val location: String?,
    var title: String?,
    var description: String?,
    val how_to_apply: String?,
    val company_logo: String?
)