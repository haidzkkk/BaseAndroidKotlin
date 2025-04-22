package com.app.motel.data.repository

import com.app.motel.data.local.ComplaintDAO
import com.app.motel.data.local.RegulationDAO
import javax.inject.Inject

class ComplaintRepository @Inject constructor(
    private val complaintDAO: ComplaintDAO,
) {

}