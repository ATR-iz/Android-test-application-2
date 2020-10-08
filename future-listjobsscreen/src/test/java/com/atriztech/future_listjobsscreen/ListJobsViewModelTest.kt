package com.atriztech.future_listjobsscreen

import android.accounts.NetworkErrorException
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.atriztech.common.scheduler.TrampolineSchedulerProvider
import com.atriztech.future_jobs.JobEntity
import com.atriztech.future_jobs.JobsApi
import com.atriztech.future_jobs.JobsClientImpl
import io.reactivex.Observable.error
import io.reactivex.Observable.just
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ListJobsViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private var context: Context = mock()
    private lateinit var jobsClientImpl: JobsClientImpl
    private var jobsApi: JobsApi = mock()
    private var schedulerProvider = TrampolineSchedulerProvider()
    private lateinit var viewModel: ListJobsViewModel

    private val observerList: Observer<List<JobEntity>> = mock()
    private val observerStatus: Observer<LoadState> = mock()

    private lateinit var listData: List<JobEntity>

    @Before
    @Throws(Exception::class)
    fun setUp() {
        jobsClientImpl = JobsClientImpl(context)
        jobsClientImpl.jobsApi = jobsApi

        this.viewModel = ListJobsViewModel(schedulerProvider, jobsClientImpl)

        listData = listOf(JobEntity("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"))

        viewModel.listData.observeForever(observerList)
        viewModel.status.observeForever(observerStatus)
    }

    @Test
    fun testGetListData() {
        `when`(jobsClientImpl.jobsApi.getJobs("", "")).thenReturn(just(listData))

        viewModel.getListData()

        verify(observerStatus).onChanged(LoadState.Loading)
        verify(observerStatus).onChanged(LoadState.Loaded)
        verify(observerList).onChanged(listData)
    }

    @Test
    fun testErrorGetListData() {
        `when`(jobsClientImpl.jobsApi?.getJobs("", "")).thenReturn(error(NetworkErrorException()))

        viewModel.getListData()

        verify(observerStatus).onChanged(LoadState.Loading)
        verify(observerStatus).onChanged(LoadState.Error)
        verify(observerList).onChanged(listOf())
    }

    @Test
    fun testDestroyThread(){
        `when`(jobsClientImpl.jobsApi?.getJobs("", "")).thenReturn(just(listData))
        viewModel.getListData()
        assert(viewModel.destroyTread())
    }

    @Test
    fun testClearText(){
        var testData = listOf(
            JobEntity(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "<p>Test1</p><li>Test1</li><p>Test1</p>",
                "10",
                "11"
            )
        )
        var compareData = listOf(
            JobEntity(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "Test1 Test1 Test1",
                "10",
                "11"
            )
        )

        testData = viewModel.clearText(testData)
        assert(testData == compareData)

        testData = viewModel.clearText(compareData)
        assert(testData == compareData)
    }
}