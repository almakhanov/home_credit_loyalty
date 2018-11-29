package kz.batana.homecreditloyalty.entity

data class History(
        var title: String,
        var data: String,
        var from_field: String,
        var to_field: String,
        var balance: Int
)