package dkurata38.afb.web.api

class ConfigResponse(statusCode: Int, val keyword: String, val scheduled: Boolean?): ApiResponse(statusCode)