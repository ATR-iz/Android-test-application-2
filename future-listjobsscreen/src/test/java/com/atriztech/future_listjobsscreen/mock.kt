package com.atriztech.future_listjobsscreen

import org.mockito.Mockito

inline fun <reified T> mock(): T = Mockito.mock(T::class.java)