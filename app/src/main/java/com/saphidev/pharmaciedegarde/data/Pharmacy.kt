package com.saphidev.pharmaciedegarde.data

data class Pharmacy(
    var name: String = "",
    var hourFrom: String = "00:00",
    var hourTo: String = "00:00",
    var location: String = "2.98998,4.9990",
    var rating: Int = 5,
    var date: String = "1991-01-28",
)
