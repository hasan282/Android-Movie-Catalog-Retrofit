package id.trials.moviecatalog

class ConvertDate {
    companion object {
        fun toDate(date: String?): String {
            val month =
                "Januari|Februari|Maret|April|Mei|Juni|Juli|Agustus|September|Oktober|November|Desember"
            val arrayMonth = month.split('|')
            val arrDate = date!!.split('-')
            return arrDate[2] + " " + arrayMonth[arrDate[1].toInt() - 1] + " " + arrDate[0]
        }
    }
}