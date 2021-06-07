package com.fgt.walletsystem.domains.paystack;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaystackAPIMetaData {
    @JsonProperty("total")
    private int totalTransactions;
    @JsonProperty("skipped")
    private int skippedRecords;
    @JsonProperty("perPage")
    private int perPage;
    @JsonProperty("page")
    private int currentPageNo;
    @JsonProperty("pageCount")
    private int pageCount;
}
